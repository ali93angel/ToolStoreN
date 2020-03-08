package com.leon.tool_store.Utils.CustomDialogue;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;

import com.leon.tool_store.Activities.MainActivity;
import com.leon.tool_store.Enums.DialogType;
import com.leon.tool_store.R;


public class CustomDialog {

    @SuppressLint("StaticFieldLeak")
    private static LovelyStandardDialog lovelyStandardDialog;
    private Context context;
    private String Top, Title, Message, ButtonText;

    public CustomDialog(DialogType choose, Context context, String message, String title, String top, String buttonText) {
        this.context = context;
        Message = message;
        Title = title;
        Top = top;
        ButtonText = buttonText;
        lovelyStandardDialog = new LovelyStandardDialog(context)
                .setTitle(Title)
                .setMessage(Message)
                .setTopTitle(Top);
        if (choose == DialogType.Green)
            CustomGreenDialog(this.context, ButtonText);
        else if (choose == DialogType.Yellow)
            CustomYellowDialog(this.context, ButtonText);
        else if (choose == DialogType.Red)
            CustomRedDialog(this.context, ButtonText);
        else if (choose == DialogType.GreenRedirect)
            CustomGreenDialogRedirect(this.context, ButtonText);
        else if (choose == DialogType.YellowRedirect)
            CustomYellowDialogRedirect(this.context, ButtonText);
        else if (choose == DialogType.RedRedirect)
            CustomRedDialogRedirect(this.context, ButtonText);
        else if (choose == DialogType.Blue)
            CustomBlueDialog(this.context, ButtonText);
    }

    public CustomDialog(String message, String title, String top,
                        int positiveButtonColor, int topColor, int topTitleColor) {
        lovelyStandardDialog = new LovelyStandardDialog(context)
                .setTitle(title)
                .setMessage(message)
                .setTopTitle(top)
                .setTopColorRes(topColor)
                .setTopTitleColor(context.getResources().getColor(topTitleColor))
                .setPositiveButtonColor(context.getResources().getColor(positiveButtonColor));
    }

    private static void CustomBlueDialog(final Context context, String ButtonText) {
        lovelyStandardDialog
                .setTopColorRes(R.color.blue5)
                .setTopTitleColor(context.getResources().getColor(R.color.white))
                .setButtonsBackground(R.drawable.border_blue_4)
                .setPositiveButton(ButtonText, v -> lovelyStandardDialog.dismiss())
                .show();
    }

    private static void CustomGreenDialog(final Context context, String ButtonText) {
        lovelyStandardDialog
                .setTopColorRes(R.color.green2)
                .setTopTitleColor(context.getResources().getColor(R.color.white))
                .setButtonsBackground(R.drawable.border_green_2)
                .setPositiveButton(ButtonText, v -> lovelyStandardDialog.dismiss())
                .show();
    }

    private static void CustomYellowDialog(final Context context, String buttonText) {
        lovelyStandardDialog
                .setTopColorRes(R.color.yellow1)
                .setTopTitleColor(context.getResources().getColor(R.color.white))
                .setButtonsBackground(R.drawable.border_yellow_2)
                .setPositiveButton(buttonText, v -> {
                })
                .show();
    }

    private static void CustomRedDialog(final Context context, String buttonText) {
        lovelyStandardDialog
                .setTopColorRes(R.color.red1)
                .setTopTitleColor(context.getResources().getColor(R.color.white))
                .setButtonsBackground(R.drawable.border_red_2)
                .setPositiveButton(buttonText, v -> lovelyStandardDialog.dismiss())
                .show();
    }

    private static void CustomGreenDialogRedirect(final Context context, String ButtonText) {
        lovelyStandardDialog
                .setTopColorRes(R.color.green2)
                .setTopTitleColor(context.getResources().getColor(R.color.white))
                .setButtonsBackground(R.drawable.border_green_2)
                .setPositiveButton(ButtonText, v -> {
                    Intent intent = new Intent(context, MainActivity.class);
                    context.startActivity(intent);
                });
        lovelyStandardDialog.show();
    }

    private static void CustomYellowDialogRedirect(final Context context, String buttonText) {
        lovelyStandardDialog
                .setButtonsBackground(R.drawable.border_yellow_2)
                .setTopTitleColor(context.getResources().getColor(R.color.white))
                .setTopColorRes(R.color.yellow1)
                .setPositiveButton(buttonText, v -> {
                    Intent intent = new Intent(context, MainActivity.class);
                    context.startActivity(intent);
                })
                .show();
    }

    private static void CustomRedDialogRedirect(final Context context, String buttonText) {
        lovelyStandardDialog
                .setTopColorRes(R.color.red1)
                .setTopTitleColor(context.getResources().getColor(R.color.white))
                .setButtonsBackground(R.drawable.border_red_2)
                .setPositiveButton(buttonText, v -> {
                    Intent intent = new Intent(context, MainActivity.class);
                    context.startActivity(intent);
                })
                .show();
    }

    public static LovelyStandardDialog getLovelyStandardDialog() {
        return lovelyStandardDialog;
    }

    public interface Inline {
        void inline(String negative, int negativeColor);
    }
}

