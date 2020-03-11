package com.leon.tool_store.Activities;

import android.content.Context;
import android.content.Intent;

import com.leon.tool_store.BaseItems.BaseActivityNoDrawer;
import com.leon.tool_store.R;
import com.leon.tool_store.Utils.CustomDialogue.CustomDialog;
import com.leon.tool_store.Utils.CustomDialogue.LovelyStandardDialog;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivityNoDrawer {
    Context context;
    @BindView(R.id.bmb)
    BoomMenuButton bmb;
    String[] menu;// = getResources().getStringArray(R.array.menu);

    @Override
    protected void initialize() {
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);
        context = this;
        menu = getResources().getStringArray(R.array.menu);
        initializeMenu();
    }

    void initializeMenu() {
        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
            TextOutsideCircleButton.Builder builder = new TextOutsideCircleButton.Builder()
                    .normalImageRes(R.drawable.ic_checkout_circle)
//                    .normalText("Butter Doesn't fly!")
                    .normalText(menu[i])
                    .listener(index -> {
                        Intent intent;
                        switch (index) {
                            case 0:
                                break;
                            case 1:
                                intent = new Intent(getApplicationContext(), CartActivity.class);
                                startActivity(intent);
                                break;
                            case 2:
                                intent = new Intent(getApplicationContext(), CheckoutActivity.class);
                                startActivity(intent);
                                break;
                            case 3:
                                break;
                            case 4:
                                intent = new Intent(getApplicationContext(), ProfileActivity.class);
                                startActivity(intent);
                                break;
                            case 5:
                                intent = new Intent(getApplicationContext(), AboutActivity.class);
                                startActivity(intent);
                                break;
                        }
                    });
            bmb.addBuilder(builder);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new ShowDialogue(getString(R.string.exit_question),
                getString(R.string.dear_user), getString(R.string.exit), getString(R.string.yes), getString(R.string.no),
                R.color.red1, R.color.green2, R.color.yellow2, R.color.white);
    }

    class ShowDialogue implements CustomDialog.Inline {
        private LovelyStandardDialog lovelyStandardDialog;

        ShowDialogue(String message, String title, String top,
                     String positiveButtonText, String negativeButtonText,
                     int positiveButtonColor, int negativeButtonColor,
                     int topColor, int topTitleColor) {
            lovelyStandardDialog = new LovelyStandardDialog(context)
                    .setTitle(title).setMessage(message).setTopTitle(top).setTopColorRes(topColor)
                    .setTopTitleColor(getResources().getColor(topTitleColor))
                    .setPositiveButtonColor(getResources().getColor(positiveButtonColor))
                    .setPositiveButton(positiveButtonText, v -> {
                        finishAffinity();
                    });
            inline(negativeButtonText, negativeButtonColor);
        }

        @Override
        public void inline(String negative, int negativeColor) {
            lovelyStandardDialog.setNegativeButtonColor(getResources().getColor(negativeColor))
                    .setNegativeButton(negative, v -> {
                        lovelyStandardDialog.dismiss();
                    }).show();
        }
    }
}
