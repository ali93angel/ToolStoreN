package com.leon.tool_store.Utils.Networks;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.leon.tool_store.Enums.DialogType;
import com.leon.tool_store.Enums.ErrorHandlerType;
import com.leon.tool_store.Enums.ProgressType;
import com.leon.tool_store.Infrastructure.ICallback;
import com.leon.tool_store.R;
import com.leon.tool_store.Utils.CustomDialogue.CustomDialog;
import com.leon.tool_store.Utils.CustomProgressBar;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HttpClientWrapper {
    private HttpClientWrapper() {
    }

    public static <T> void callHttpAsync(Call<T> call, final ICallback callback, final Context context, int dialogType) {
        callHttpAsync(call, callback, context, dialogType, ErrorHandlerType.ordinary);
    }

    public static <T> void callHttpAsync(Call<T> call, final ICallback callback, final Context context, int dialogType, final ErrorHandlerType errorHandlerType) {
        CustomProgressBar progressBar = new CustomProgressBar();
        if (dialogType == ProgressType.SHOW.getValue() || dialogType == ProgressType.SHOW_CANCELABLE.getValue()) {
            progressBar.show(context, context.getString(R.string.please_wait), true);
        }
        CheckNetwork checkNetwork = new CheckNetwork(context);
        if (checkNetwork.isNetworkAvailable()) {
            final String[] error = new String[1];
            call.enqueue(new Callback<T>() {
                @Override
                public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
                    try {
                        if (response.isSuccessful()) {
                            T responseT = response.body();
                            callback.execute(responseT);
                            progressBar.getDialog().dismiss();
                        } else {
                            try {
                                JSONObject jsonObject = new JSONObject(response.errorBody().string());
                                error[0] = jsonObject.getString(context.getString(R.string.message));
                            } catch (Exception e) {
                                CustomErrorHandling customErrorHandling = new CustomErrorHandling(context);
                                error[0] = customErrorHandling.getErrorMessage(response.code(), errorHandlerType);
                            }
                            new CustomDialog(DialogType.Yellow, context, error[0], context.getString(R.string.dear_user),
                                    context.getString(R.string.error), context.getString(R.string.accepted));
                            progressBar.getDialog().dismiss();
                        }
                    } catch (Exception e) {
                        try {
                            JSONObject jsonObject = new JSONObject(response.errorBody().string());
                            error[0] = jsonObject.getString(context.getString(R.string.message));
                        } catch (Exception e1) {
                            CustomErrorHandling customErrorHandling = new CustomErrorHandling(context);
                            error[0] = customErrorHandling.getErrorMessage(response.code(), errorHandlerType);
                        }
                        new CustomDialog(DialogType.Yellow, context, error[0], context.getString(R.string.dear_user),
                                context.getString(R.string.error), context.getString(R.string.accepted));
                        progressBar.getDialog().dismiss();
                    }
                }

                @Override
                public void onFailure(Call<T> call, Throwable t) {
                    Log.e("error", t.getMessage());
                    Activity activity = (Activity) context;
                    if (!activity.isFinishing()) {
                        CustomErrorHandling customErrorHandling = new CustomErrorHandling(context);
                        error[0] = customErrorHandling.getErrorMessageTotal(t);
                        new CustomDialog(DialogType.Red, context, error[0], context.getString(R.string.dear_user),
                                context.getString(R.string.error), context.getString(R.string.accepted));
                    }
                    progressBar.getDialog().dismiss();
                }
            });
        } else {
            progressBar.getDialog().dismiss();
            Toast.makeText(context, R.string.connect_internet, Toast.LENGTH_LONG).show();
        }
    }

}
