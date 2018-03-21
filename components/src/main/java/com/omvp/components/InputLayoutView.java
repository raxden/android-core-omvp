package com.omvp.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.ContextCompat;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Input layout custom view
 * <p>
 * Example of use:
 * <com.hobbiespot.mobile.components.InputLayout
 * android:layout_width="match_parent"
 * app:hspot_hint="hint"
 * app:hspot_input_type="password"
 * android:layout_height="match_parent"/>
 */
public class InputLayoutView extends BaseComponentView {

    private static final int LAYOUT_ID = R.layout.input_layout;

    public enum HintType {NONE, FIXED, FIXED_TO_LEFT}

    private TextAdvancedInputLayout mTextInputLayout;
    private TextInputEditText mTextInputEditText;

    private TextView mHintFixedToLeftText;

    private String mText;
    private String mHint;
    private boolean mHintAnimationEnabled;
    private HintType mHintType;
    private int mHintWidth;
    private boolean mPasswordToggleEnabled;

    private int mInputType;
    private int mInputGravity;
    private int mImeOptions;
    private boolean mEnabled;
    private boolean mFocusable;

    private int mNextFocusForward;
    private int mNextFocusUp;
    private int mNextFocusDown;
    private int mNextFocusRight;
    private int mNextFocusLeft;

    private boolean mEditable;

    private boolean mCounterEnabled;
    private int mCounterMaxLength;

    private int mMaxLines;

    private CharSequence mMask;
    private char mNotMaskedSymbol;
    private MaskedInputFilter mMaskedInputFilter;

    private Drawable mSuccessDrawable;
    private Drawable mSuccessTransparentDrawable;
    private Drawable mErrorDrawable;
    private Drawable mErrorTransparentDrawable;

    public InputLayoutView(Context context) {
        super(context);
    }

    public InputLayoutView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InputLayoutView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int getLayoutId() {
        return LAYOUT_ID;
    }

    @Override
    protected void loadData() {
        setHint(mHint);
        setText(mText);
        setInputType(mInputType);
        setInputGravity(mInputGravity);
        setImeOptions(mImeOptions);
        setNextFocusForward(mNextFocusForward);
        setNextFocusUp(mNextFocusUp);
        setNextFocusDown(mNextFocusDown);
        setNextFocusRight(mNextFocusRight);
        setNextFocusLeft(mNextFocusLeft);
        setCounterEnabled(mCounterEnabled);
        setEditTextEnabled(mEnabled);
        setEditTextFocusable(mFocusable);
        setPasswordToggleEnabled(mPasswordToggleEnabled);
        setMaxLines(mMaxLines);

        setupFilters();
    }

    @Override
    protected void loadAttributes(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.getTheme().
                obtainStyledAttributes(attrs, R.styleable.InputLayoutView, 0, 0);
        mText = typedArray.getString(R.styleable.InputLayoutView_android_text);
        mHint = typedArray.getString(R.styleable.InputLayoutView_android_hint);
        mInputType = typedArray.getInteger(R.styleable.InputLayoutView_android_inputType, InputType.TYPE_CLASS_TEXT);
        mInputGravity = typedArray.getInteger(R.styleable.InputLayoutView_android_gravity, Gravity.TOP | Gravity.LEFT);
        mSuccessDrawable = ContextCompat.getDrawable(getContext(), R.drawable.input_layout_success);
        mErrorDrawable = ContextCompat.getDrawable(getContext(), R.drawable.input_layout_error);
        if (mSuccessDrawable != null) {
            int width = mSuccessDrawable.getIntrinsicWidth();
            int height = mSuccessDrawable.getIntrinsicHeight();
//            mSuccessTransparentDrawable = createDummyDrawable(width, height);
        }
        if (mErrorDrawable != null) {
            int width = mErrorDrawable.getIntrinsicWidth();
            int height = mErrorDrawable.getIntrinsicHeight();
//            mErrorTransparentDrawable = createDummyDrawable(width, height);
        }
        mImeOptions = typedArray.getInteger(R.styleable.InputLayoutView_android_imeOptions, 0);
        mNextFocusForward = typedArray.getResourceId(R.styleable.InputLayoutView_android_nextFocusForward, 0);
        mNextFocusUp = typedArray.getResourceId(R.styleable.InputLayoutView_android_nextFocusUp, 0);
        mNextFocusDown = typedArray.getResourceId(R.styleable.InputLayoutView_android_nextFocusDown, 0);
        mNextFocusRight = typedArray.getResourceId(R.styleable.InputLayoutView_android_nextFocusRight, 0);
        mNextFocusLeft = typedArray.getResourceId(R.styleable.InputLayoutView_android_nextFocusLeft, 0);
        mCounterEnabled = typedArray.getBoolean(R.styleable.InputLayoutView_counterEnabled, false);
        mCounterMaxLength = typedArray.getInteger(R.styleable.InputLayoutView_counterMaxLength, -1);
        mHintAnimationEnabled = typedArray.getBoolean(R.styleable.InputLayoutView_hintAnimationEnabled, true);
        mHintType = HintType.values()[typedArray.getInteger(R.styleable.InputLayoutView_hintType, HintType.NONE.ordinal())];
        mHintWidth = typedArray.getDimensionPixelSize(R.styleable.InputLayoutView_hintWidth, -1);
        mEnabled = typedArray.getBoolean(R.styleable.InputLayoutView_android_enabled, true);
        mFocusable = typedArray.getBoolean(R.styleable.InputLayoutView_android_focusable, true);
        mPasswordToggleEnabled = typedArray.getBoolean(R.styleable.InputLayoutView_passwordToggleEnabled, false);
        mEditable = typedArray.getBoolean(R.styleable.InputLayoutView_editable, true);

        mMask = typedArray.getString(R.styleable.InputLayoutView_mask);
        String notMaskedSymbol = typedArray.getString(R.styleable.InputLayoutView_notMaskedSymbol);
        if (TextUtils.isEmpty(notMaskedSymbol)) {
            mNotMaskedSymbol = '#';
        } else {
            mNotMaskedSymbol = notMaskedSymbol.charAt(0);
        }

        mMaxLines = typedArray.getInteger(R.styleable.InputLayoutView_android_maxLines, 1);

        typedArray.recycle();
    }

    private Drawable createDummyDrawable(int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ALPHA_8);
        return new BitmapDrawable(getResources(), bitmap);
    }

    @Override
    protected void bindViews() {
        mTextInputLayout = findViewById(R.id.input_layout);
        mTextInputEditText = findViewById(R.id.input_text);
        mHintFixedToLeftText = findViewById(R.id.hint_fixed_to_left);
    }

    public void setHint(String hint) {
        if (!TextUtils.isEmpty(hint)) {
            switch (mHintType) {
                case NONE:
                    mTextInputLayout.setHint(hint);
                    mTextInputLayout.setHintAnimationEnabled(mHintAnimationEnabled);
                    break;
                case FIXED:
                    mTextInputLayout.setHint("");
                    mTextInputEditText.setHint(hint);
                    break;
                case FIXED_TO_LEFT:
                    mTextInputLayout.setHint("");
                    mTextInputEditText.setHint("");
                    mTextInputEditText.setPadding(mHintWidth, 0, 0, 0);
                    mHintFixedToLeftText.setText(hint);
                    mHintFixedToLeftText.setWidth(mHintWidth);
                    break;
            }
        }
    }

    public void setText(String text) {
        if (!TextUtils.isEmpty(text)) {
            mTextInputEditText.setText(text);
        }
    }

    public void clearText() {
        mTextInputEditText.setText("");
    }

    public void setInputType(int inputType) {
        switch (inputType) {
            case InputType.TYPE_NULL:
                mTextInputEditText.setFocusable(false);
                mTextInputEditText.setEnabled(false);
                mTextInputEditText.setClickable(false);
                break;
            case InputType.TYPE_CLASS_TEXT:
            case InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS:
            case InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS:
                Drawable drawable = mTextInputEditText.getBackground();
                int color = Color.parseColor("#FF0000");
                drawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
                break;
            case InputType.TYPE_TEXT_FLAG_MULTI_LINE | InputType.TYPE_CLASS_TEXT:
                ((RelativeLayout.LayoutParams) mTextInputLayout.getLayoutParams()).setMargins(0, 0, 0, 0);
                break;
        }
        if (!TextUtils.isEmpty(mMask)) {
            inputType = inputType | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS;
        }
        mTextInputEditText.setInputType(inputType);
    }

    public void setInputGravity(int inputGravity) {
        mTextInputLayout.setGravity(inputGravity);
        mTextInputEditText.setGravity(inputGravity);
        if (mInputGravity != inputGravity) {
            mInputGravity = inputGravity;
            invalidate();
        }
    }

    public void setErrorEnabled(boolean enabled) {
        mTextInputLayout.setErrorEnabled(enabled);
    }

    public void clearError() {
        mTextInputLayout.setErrorEnabled(false);
        mTextInputLayout.setError("");
        mTextInputEditText.setCompoundDrawablesWithIntrinsicBounds(
                null,
                null,
                null,
                null);
    }

    public void setSuccess() {
        mTextInputLayout.setErrorEnabled(false);
        mTextInputLayout.setError("");
        mTextInputEditText.setCompoundDrawablesWithIntrinsicBounds(
                null,
                null,
                null,
                null);
    }

    public void setError(CharSequence error) {
        if (!TextUtils.isEmpty(error)) {
            mTextInputLayout.setErrorEnabled(true);
            mTextInputLayout.setError(error);
            mTextInputEditText.setCompoundDrawablesWithIntrinsicBounds(
                    null,
                    null,
                    mErrorDrawable,
                    null);
        } else {
            clearError();
        }
    }

    public String getText() {
        if (mTextInputEditText != null) {
            return mTextInputEditText.getText().toString();
        }
        return "";
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        setEditTextEnabled(enabled);
    }

    @Override
    public void setFocusable(boolean focusable) {
        super.setFocusable(focusable);
        setEditTextFocusable(focusable);
    }

    public void setEditTextEnabled(boolean enabled) {
        mTextInputEditText.setEnabled(enabled);
    }

    public void setOnEditorActionListener(TextView.OnEditorActionListener listener) {
        mTextInputEditText.setOnEditorActionListener(listener);
    }

    public void addTextChangedListener(TextWatcher listener) {
        mTextInputEditText.addTextChangedListener(listener);
    }

    public void setOnFocusChangeListener(final OnFocusChangeListener listener) {
        if (listener == null) {
            mTextInputEditText.setOnFocusChangeListener(null);
            return;
        }

        mTextInputEditText.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                listener.onFocusChange(InputLayoutView.this, hasFocus);
            }
        });
    }

    public int getEditTextId() {
        return mTextInputEditText.getId();
    }

    public void setEditTextFocusable(boolean focusable) {
        mTextInputEditText.setFocusable(focusable);
        mTextInputEditText.setFocusableInTouchMode(focusable);
    }

    public void setEditTextClickListener(final OnClickListener listener) {
        if (listener == null){
            mTextInputEditText.setOnClickListener(null);
            return;
        }

        mTextInputEditText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(InputLayoutView.this);
            }
        });
    }

    public boolean requestEditTextFocus(){
        return mTextInputEditText.requestFocus();
    }

    private void setPasswordToggleEnabled(boolean passwordToggleEnabled) {
        mTextInputLayout.setPasswordVisibilityToggleEnabled(passwordToggleEnabled);
    }

    private void setImeOptions(int imeOptions) {
        if (imeOptions != 0) {
            mTextInputEditText.setImeOptions(imeOptions);
        }
    }

    private void setNextFocusForward(int nextFocusForwardId) {
        if (nextFocusForwardId != 0) {
            mTextInputEditText.setNextFocusForwardId(nextFocusForwardId);
        }
    }

    private void setNextFocusUp(int nextFocusUpId) {
        if (nextFocusUpId != 0) {
            mTextInputEditText.setNextFocusUpId(nextFocusUpId);
        }
    }

    private void setNextFocusDown(int nextFocusDownId) {
        if (nextFocusDownId != 0) {
            mTextInputEditText.setNextFocusDownId(nextFocusDownId);
        }
    }

    private void setNextFocusRight(int nextFocusRightId) {
        if (nextFocusRightId != 0) {
            mTextInputEditText.setNextFocusRightId(nextFocusRightId);
        }
    }

    private void setNextFocusLeft(int nextFocusLeftId) {
        if (nextFocusLeftId != 0) {
            mTextInputEditText.setNextFocusLeftId(nextFocusLeftId);
        }
    }

    private void setCounterEnabled(boolean counterEnabled) {
        mTextInputLayout.setCounterEnabled(counterEnabled);
    }

    private void setupFilters() {
        List<InputFilter> filterList = new ArrayList<>();
        if (!mEditable) {
            filterList.add(new DisableEditInputFilter());
        } else if (!TextUtils.isEmpty(mMask)) {
            filterList.add(new MaskedInputFilter());
            filterList.add(new InputFilter.LengthFilter(mMask.length()));
        } else if (mCounterMaxLength != -1) {
            filterList.add(new InputFilter.LengthFilter(mCounterMaxLength));
            mTextInputLayout.setCounterMaxLength(mCounterMaxLength);
        }
        mTextInputEditText.setFilters(filterList.toArray(new InputFilter[filterList.size()]));
    }

    private void setMaxLines(int maxLines) {
        mTextInputEditText.setMaxLines(maxLines);
    }

    private class DisableEditInputFilter implements InputFilter {
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            return source.length() < 1 ? dest.subSequence(dstart, dend) : "";
        }
    }

    private class MaskedInputFilter implements InputFilter {

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

            boolean newCharToEndOfWord = dstart == dend && dest.length() <= dstart;
            boolean newCharBetweenOfWord = dstart == dend && dest.length() > dstart;
            boolean newCharEntered = newCharToEndOfWord || newCharBetweenOfWord;
            boolean charRemoved = !newCharToEndOfWord && !newCharBetweenOfWord;
            int currentTextLength = dest.length();
            int maskLenght = mMask.length();
            boolean exceededMaxLength = currentTextLength >= maskLenght;
            int nextCharPosition = dstart + 1;
            int currentCharPosition = dstart;
            int previousCharPosition = dstart - 1;

            Timber.d("%s %d %d %s %d %d - %s %s", source, start, end, dest, dstart, dend, newCharToEndOfWord, newCharBetweenOfWord);

            if (newCharEntered && exceededMaxLength) {
                return "";
            } else if (newCharToEndOfWord) {
                char currentMaskChar = mMask.charAt(dstart);
                if (nextCharPosition < maskLenght) {
                    char nextMaskChar = mMask.charAt(nextCharPosition);
                    if (nextMaskChar != mNotMaskedSymbol) {
                        source = source + "" + nextMaskChar;
                    } else if (currentMaskChar != mNotMaskedSymbol) {
                        source = currentMaskChar + "" + source;
                    }
                }
            } else if (charRemoved && previousCharPosition > 0) {
                char currentMaskChar = mMask.charAt(currentCharPosition);
                if (currentMaskChar != mNotMaskedSymbol) {
                    mTextInputEditText.setText(dest);
                    mTextInputEditText.setSelection(dend);
                }
                char previousMaskChar = mMask.charAt(previousCharPosition);
                if (previousMaskChar != mNotMaskedSymbol) {
                    mTextInputEditText.setText(dest.subSequence(0, previousCharPosition));
                    mTextInputEditText.setSelection(mTextInputEditText.getText().length());
                }
            }

            return source;
        }

    }

}
