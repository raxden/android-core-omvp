package com.omvp.app.injector.module;

import android.app.Activity;
import android.app.FragmentManager;

import com.omvp.app.helper.AnimationHelper;
import com.omvp.app.helper.DialogHelper;
import com.omvp.app.helper.NavigationHelper;
import com.omvp.app.helper.SnackBarHelper;
import com.omvp.app.injector.scope.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rakshakhegde on 26/04/17.
 */
@Module
public abstract class HelperModule {

	@Provides
	@PerActivity
	static NavigationHelper navigationHelper(Activity activity) {
		return new NavigationHelper(activity);
	}

	@Provides
	@PerActivity
	static DialogHelper dialogHelper(Activity activity, FragmentManager fragmentManager) {
		return new DialogHelper(activity, fragmentManager);
	}

	@Provides
	@PerActivity
	static SnackBarHelper snackBarHelper(Activity activity) {
		return new SnackBarHelper(activity);
	}

	@Provides
	@PerActivity
	static AnimationHelper animationHelper(Activity activity) {
		return new AnimationHelper(activity);
	}

}
