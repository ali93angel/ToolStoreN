package com.leon.tool_store.Activities;

import android.content.Intent;
import android.widget.ImageView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.leon.tool_store.BaseItems.BaseActivityNoDrawer;
import com.leon.tool_store.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends BaseActivityNoDrawer {

    @BindView(R.id.imageViewSplashScreen)
    ImageView imageViewSplash;
    @BindView(R.id.shimmer_view_container)
    ShimmerFrameLayout container;
    private boolean splashLoaded = false;

    @Override
    protected void initialize() {
        if (!splashLoaded) {
            setContentView(R.layout.splash_activity);
            ButterKnife.bind(this);
            int splashResourceId = R.drawable.img_splash;
            imageViewSplash.setImageResource(splashResourceId);
            container.startShimmer();
            startSplash();
        } else {
            Intent goToLoginActivity = new Intent(SplashActivity.this, Main2Activity.class);
            goToLoginActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(goToLoginActivity);
            finish();
        }
    }

    private void startSplash() {
        Thread timerThread = new Thread() {
            public void run() {
                try {
                    sleep(2500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    splashLoaded = true;
                    Intent intent = new Intent(SplashActivity.this, Main2Activity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        timerThread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        imageViewSplash.setImageDrawable(null);
        container = null;
    }

}
