package com.leon.tool_store.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import java.util.Objects;

public class CheckNetwork {
    private Context context;

    public CheckNetwork(Context context) {
        this.context = context;
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = Objects.requireNonNull(connectivityManager)
                .getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    public void isNetworkAvailable(final Handler handler, final int timeout) {
        final Runnable r = new Runnable() {
            public void run() {
                if (isNetworkAvailable()) {
                    Log.e("internet", "your get/post related code..like HttpPost = new HttpPost(url);");
                } else {
                    Toast.makeText(context, "no internet!", Toast.LENGTH_LONG).show();
                }
                handler.postDelayed(this, timeout);
            }
        };
        handler.postDelayed(r, timeout);

    }
}