package com.leon.tool_store.Activities;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.leon.tool_store.BaseItems.BaseActivityNoDrawer;
import com.leon.tool_store.R;
import com.leon.tool_store.Utils.CustomDialogue.CustomDialog;
import com.leon.tool_store.Utils.CustomDialogue.LovelyStandardDialog;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.ramotion.circlemenu.CircleMenuView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivityNoDrawer {
    Context context;
    @BindView(R.id.bmb)
    BoomMenuButton boomMenuButton;
    String[] menuItem;
    @BindView(R.id.circle_menu)
    CircleMenuView circleMenuView;

    @Override
    protected void initialize() {
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);
        context = this;
        menuItem = getResources().getStringArray(R.array.menu);
        initializeMenu();
    }

    void initializeMenu() {
        circleMenuView.setEventListener(new CircleMenuView.EventListener() {
            @Override
            public void onMenuOpenAnimationStart(@NonNull CircleMenuView view) {
                Log.e("D", "onMenuOpenAnimationStart");
            }

            @Override
            public void onMenuOpenAnimationEnd(@NonNull CircleMenuView view) {
                Log.e("D", "onMenuOpenAnimationEnd");
            }

            @Override
            public void onMenuCloseAnimationStart(@NonNull CircleMenuView view) {
                Log.e("D", "onMenuCloseAnimationStart");
            }

            @Override
            public void onMenuCloseAnimationEnd(@NonNull CircleMenuView view) {
                Log.e("D", "onMenuCloseAnimationEnd");
            }

            @Override
            public void onButtonClickAnimationStart(@NonNull CircleMenuView view, int index) {
                Log.e("D", "onButtonClickAnimationStart| index: " + index);
            }

            @Override
            public void onButtonClickAnimationEnd(@NonNull CircleMenuView view, int index) {
                Log.e("D", "onButtonClickAnimationEnd| index: " + index);
            }

            @Override
            public boolean onButtonLongClick(@NonNull CircleMenuView view, int index) {
                Log.e("D", "onButtonLongClick| index: " + index);
                return true;
            }

            @Override
            public void onButtonLongClickAnimationStart(@NonNull CircleMenuView view, int index) {
                Log.e("D", "onButtonLongClickAnimationStart| index: " + index);
            }

            @Override
            public void onButtonLongClickAnimationEnd(@NonNull CircleMenuView view, int index) {
                Log.e("D", "onButtonLongClickAnimationEnd| index: " + index);
            }
        });

        for (int i = 0; i < boomMenuButton.getPiecePlaceEnum().pieceNumber(); i++) {
            TextOutsideCircleButton.Builder builder = new TextOutsideCircleButton.Builder()
                    .normalImageRes(R.drawable.ic_checkout_circle)
                    .normalText(menuItem[i])
                    .listener(index -> {
                        Intent intent;
                        switch (index) {
                            case 1:
                                intent = new Intent(getApplicationContext(), CartActivity.class);
                                startActivity(intent);
                                break;
                            case 2:
                                intent = new Intent(getApplicationContext(), CheckoutActivity.class);
                                startActivity(intent);
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
            boomMenuButton.addBuilder(builder);
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

        ShowDialogue(String message, String title, String top, String positiveButtonText, String negativeButtonText,
                     int positiveButtonColor, int negativeButtonColor, int topColor, int topTitleColor) {
            lovelyStandardDialog = new LovelyStandardDialog(context)
                    .setTitle(title).setMessage(message).setTopTitle(top).setTopColorRes(topColor)
                    .setTopTitleColor(getResources().getColor(topTitleColor))
                    .setPositiveButtonColor(getResources().getColor(positiveButtonColor))
                    .setPositiveButton(positiveButtonText, v -> finishAffinity());
            inline(negativeButtonText, negativeButtonColor);
        }

        @Override
        public void inline(String negative, int negativeColor) {
            lovelyStandardDialog.setNegativeButtonColor(getResources().getColor(negativeColor))
                    .setNegativeButton(negative, v -> lovelyStandardDialog.dismiss()).show();
        }
    }
}
