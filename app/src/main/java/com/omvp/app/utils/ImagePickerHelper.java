/*
 * Copyright 2014 Ángel Gómez
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.omvp.app.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;

import com.raxdenstudios.commons.util.BitmapUtils;
import com.raxdenstudios.commons.util.Utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import timber.log.Timber;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

/**
 *
 */
public class ImagePickerHelper {

    private static final String TEMP_IMAGE_NAME = "tempImage";

    public static Intent getPickGalleryImageIntent(Context context) {
        return new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
    }

    public static Intent getPickCameraImageIntent(Context context) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(context.getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile(context);
            } catch (IOException ex) {
                Timber.e(ex, ex.getMessage());
            }
            if (photoFile != null) {
                saveImagePath(context, photoFile.getAbsolutePath());
                String authority = Utils.getPackageName(context) + ".fileprovider";
                Uri photoURI = FileProvider.getUriForFile(context, authority, photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
            }
        }
        return takePictureIntent;
    }

    public static Intent getPickImageIntent(Context context, String chooserTitle) {
        Intent chooserIntent = null;

        List<Intent> intentList = new ArrayList<>();

        Intent pickIntent = getPickGalleryImageIntent(context);
        intentList = addIntentsToList(context, intentList, pickIntent);

        Intent takePhotoIntent = getPickCameraImageIntent(context);
        intentList = addIntentsToList(context, intentList, takePhotoIntent);

        if (intentList.size() > 0) {
            chooserIntent = Intent.createChooser(intentList.remove(intentList.size() - 1), chooserTitle);
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentList.toArray(new Parcelable[]{}));
        }

        return chooserIntent;
    }

    public static Bitmap getImageFromResult(Context context, int resultCode, Intent intent) {
        Timber.d("getImageFromResult, resultCode: %d", resultCode);
        Uri selectedImage = getUriFromResult(context, resultCode, intent);
        if (selectedImage != null) {
            Bitmap bm = BitmapUtils.decode(context, selectedImage);
            int rotation = getRotation(context, selectedImage, isCamera(intent));
            bm = BitmapUtils.rotate(bm, rotation);
            return bm;
        }
        return null;
    }

    public static String getPathFromResult(Context context, int resultCode, Intent intent) {
        Timber.d("getPathFromResult, resultCode: %d", resultCode);
        Uri selectedImage = getUriFromResult(context, resultCode, intent);
        if (selectedImage != null) {
            return selectedImage.getPath();
        }
        return null;
    }

    public static Uri getUriFromResult(Context context, int resultCode, Intent intent) {
        Timber.d("getUriFromResult, resultCode: %d", resultCode);
        if (resultCode == Activity.RESULT_OK) {
            Uri selectedImage;
            if (isCamera(intent)) {     /** CAMERA **/
                File imageFile = new File(recoverImagePath(context));
                selectedImage = Uri.fromFile(imageFile);
            } else {                    /** ALBUM **/
                selectedImage = intent.getData();
            }
            Timber.d("selectedImage: %s", selectedImage != null ? selectedImage.toString() : "empty image");
            return selectedImage;
        }
        return null;
    }

    private static boolean isCamera(Intent intent) {
        if (intent == null) {
            return true;
        } else if (intent.getData() == null) {
            return true;
        } else {
            return false;
        }
    }

    private static int getRotation(Context context, Uri imageUri, boolean isCamera) {
        int rotation;
        if (isCamera) {
            rotation = getRotationFromCamera(context, imageUri);
        } else {
            rotation = getRotationFromGallery(context, imageUri);
        }
        Timber.d("Image rotation: %d", rotation);
        return rotation;
    }

    private static int getRotationFromCamera(Context context, Uri imageFile) {
        int rotate = 0;
        try {
            context.getContentResolver().notifyChange(imageFile, null);
            ExifInterface exif = new ExifInterface(imageFile.getPath());
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotate = 270;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotate = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotate = 90;
                    break;
            }
        } catch (Exception e) {
            Timber.e(e);
        }
        return rotate;
    }

    private static int getRotationFromGallery(Context context, Uri imageUri) {
        String[] columns = {MediaStore.Images.Media.ORIENTATION};
        Cursor cursor = context.getContentResolver().query(imageUri, columns, null, null, null);
        if (cursor == null) return 0;

        cursor.moveToFirst();

        int orientationColumnIndex = cursor.getColumnIndex(columns[0]);
        return cursor.getInt(orientationColumnIndex);
    }

    private static List<Intent> addIntentsToList(Context context, List<Intent> list, Intent intent) {
        List<ResolveInfo> resInfo = context.getPackageManager().queryIntentActivities(intent, 0);
        for (ResolveInfo resolveInfo : resInfo) {
            String packageName = resolveInfo.activityInfo.packageName;
            Intent targetedIntent = new Intent(intent);
            targetedIntent.setPackage(packageName);
            list.add(targetedIntent);
            Timber.d("Intent: %s package: %s", intent.getAction(), packageName);
        }
        return list;
    }

    private static File createImageFile(Context context) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        return image;
    }

    private static void saveImagePath(Context context, String path) {
        getDefaultSharedPreferences(context).edit().putString(TEMP_IMAGE_NAME, path).apply();
    }

    private static String recoverImagePath(Context context) {
        SharedPreferences sharedPreferences = getDefaultSharedPreferences(context);
        String path = sharedPreferences.getString(TEMP_IMAGE_NAME, "");
        sharedPreferences.edit().putString(TEMP_IMAGE_NAME, "");
        return path;
    }

}
