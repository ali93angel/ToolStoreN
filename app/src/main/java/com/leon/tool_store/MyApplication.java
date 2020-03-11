package com.leon.tool_store;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;

public class MyApplication extends Application {
    @SuppressLint("StaticFieldLeak")
    static Context context;

    public static Context getContext() {
        return context;
    }

    public static String getFontName() {
        return Config.fontName;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        context = this;
        ViewPump.init(ViewPump.builder()
                .addInterceptor(new CalligraphyInterceptor(
                        new CalligraphyConfig.Builder()
                                .setDefaultFontPath(getFontName())
                                .setFontAttrId(R.attr.fontPath)
                                .build()))
                .build());
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
