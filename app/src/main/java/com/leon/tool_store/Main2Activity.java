package com.leon.tool_store;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.leon.tool_store.BaseItems.BaseActivityDrawer;
import com.leon.tool_store.Utils.CustomDialogue.CustomDialog;
import com.leon.tool_store.Utils.CustomDialogue.LovelyStandardDialog;
import com.leon.tool_store.Utils.Networks.CheckNetwork;

import java.util.Objects;

import butterknife.ButterKnife;

public class Main2Activity extends BaseActivityDrawer {

    Context context;
    @Override
    protected void initialize() {
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View childLayout = Objects.requireNonNull(inflater).inflate(R.layout.main2_activity, findViewById(R.id.main_activity_parent));
        @SuppressLint("CutPasteId") ConstraintLayout parentLayout = findViewById(R.id.base_Content);
        parentLayout.addView(childLayout);
        ButterKnife.bind(this);
        context = this;
        new NetworkUtil().execute();
        CheckNetwork checkNetwork = new CheckNetwork(context);
        @SuppressLint("HandlerLeak") Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what != 1) {
                    Log.e("result", "// code if not connected");
                } else {
                    Log.e("result", "// code if connected");
                }
            }
        };
        checkNetwork.isNetworkAvailable(handler, 1000);
//        new CustomDialog(DialogType.Green, context, "simpleMessage.getMessage()",
//                "context.getString(R.string.dear_user)",
//                "context.getString(R.string.support)", "context.getString(R.string.accepted)");
//        ShowDialogue showDialogue1 = new ShowDialogue("test1", "test2", "test3",
//                R.color.blue2, "test4", R.color.green2, R.color.red1);
//        ShowDialogue showDialogue2 = new ShowDialogue("test1", "test2", "test3",
//                R.color.black, "test5",
//                R.color.blue2, "test4", R.color.green2, R.color.red1);

    }

    class ShowDialogue implements CustomDialog.Inline {
        private LovelyStandardDialog lovelyStandardDialog;

        ShowDialogue(String message, String title, String top,
                     int positiveButtonColor, String positiveButtonText,
                     int negativeButtonColor, String negativeButtonText,
                     int topColor, int topTitleColor) {
            lovelyStandardDialog = new LovelyStandardDialog(context)
                    .setTitle(title)
                    .setMessage(message)
                    .setTopTitle(top)
                    .setTopColorRes(topColor)
                    .setTopTitleColor(getResources().getColor(topTitleColor))
                    .setPositiveButtonColor(getResources().getColor(positiveButtonColor))
                    .setPositiveButton(positiveButtonText, v -> {
                        Log.e("clicked", "1");
                    });
            inline(negativeButtonText, negativeButtonColor);
        }

        ShowDialogue(String message, String title, String top,
                     int positiveButtonColor, String positiveButtonText,
                     int topColor, int topTitleColor) {
            lovelyStandardDialog = new LovelyStandardDialog(context)
                    .setTitle(title)
                    .setMessage(message)
                    .setTopTitle(top)
                    .setTopColorRes(topColor)
                    .setTopTitleColor(getResources().getColor(topTitleColor))
                    .setPositiveButtonColor(getResources().getColor(positiveButtonColor))
                    .setPositiveButton(positiveButtonText, v -> {
                        Log.e("clicked", "1");
                    });
            lovelyStandardDialog.show();
        }

        @Override
        public void inline(String negative, int negativeColor) {
            lovelyStandardDialog.setNegativeButtonColor(getResources().getColor(negativeColor))
                    .setNegativeButton(negative, v -> {
                        Log.e("clicked", "2");
                    }).show();
        }
    }

    public class NetworkUtil extends AsyncTask<String, Void, String> {

        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(Main2Activity.this);
            dialog.setMessage("Loading...");
            dialog.setCancelable(false);
            dialog.show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... arg0) {
            if (new CheckNetwork(Main2Activity.this).isNetworkAvailable()) {
                Log.e("internet", "your get/post related code..like HttpPost = new HttpPost(url);");
            } else {
                Toast.makeText(Main2Activity.this, "no internet!", Toast.LENGTH_LONG).show();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }

}
