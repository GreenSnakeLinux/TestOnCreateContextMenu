package com.example.testoncreatectcmenu;

import android.app.Application;

import com.google.android.material.color.DynamicColors;
import com.google.android.material.color.DynamicColorsOptions;

public class MyApplication extends Application {
        @Override
        public void onCreate() {
            super.onCreate();

            DynamicColors.applyToActivitiesIfAvailable(this, new DynamicColorsOptions.Builder()
                .setThemeOverlay(R.style.AppDynamicTheme)
                .setPrecondition((activity, themeResId) -> true).build());
        }
}
