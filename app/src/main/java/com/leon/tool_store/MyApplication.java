package com.leon.tool_store;

import android.app.Application;
import android.content.Context;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;

public class MyApplication extends Application {
    static Context context;
    static String fontName = "fonts/Sahel.ttf";

    @Override
    public void onCreate() {
        context = getApplicationContext();
        super.onCreate();
        ViewPump.init(ViewPump.builder()
                .addInterceptor(new CalligraphyInterceptor(
                        new CalligraphyConfig.Builder()
                                .setDefaultFontPath(getFontName())
                                .setFontAttrId(R.attr.fontPath)
                                .build()))
                .build());
    }

    public static Context getContext() {
        return context;
    }

    public static String getFontName() {
        return fontName;
    }
}
