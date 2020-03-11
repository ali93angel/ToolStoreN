package com.leon.tool_store.BaseItems;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.leon.tool_store.MyApplication;
import com.leon.tool_store.R;

import java.util.List;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public abstract class BaseActivityNoDrawer extends AppCompatActivity {
    protected abstract void initialize();

    @SuppressLint({"NewApi", "RtlHardcoded", "WrongConstant"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        overridePendingTransition(R.anim.slide_up_info, R.anim.no_change);
        initializeBase();
        initialize();
    }

    @SuppressLint("WrongConstant")
    void initializeBase() {
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @Override
    public void onBackPressed() {
        ActivityManager activityManager = (ActivityManager) MyApplication.getContext()
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTasks = activityManager
                .getRunningTasks(1);
        for (ActivityManager.RunningTaskInfo aTask : runningTasks) {
            Log.e("back", aTask.topActivity.getClassName());
            if (!aTask.topActivity.getClassName().equals("com.leon.tool_store.Activities.MainActivity")) {
                super.onBackPressed();
            }
        }
    }

}
