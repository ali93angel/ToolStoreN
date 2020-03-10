package com.leon.tool_store.Activities;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.leon.tool_store.BaseItems.BaseActivityNoDrawer;
import com.leon.tool_store.R;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
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
                    .listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {
                            Toast.makeText(context, "Clicked " + index, Toast.LENGTH_SHORT).show();
                            Intent intent;
                            switch (index) {
                                case 0:
                                    break;
                                case 1:
                                    break;
                                case 2:
                                    break;
                                case 3:
                                    break;
                                case 4:
                                    break;
                                case 5:
                                    intent = new Intent(getApplicationContext(), AboutActivity.class);
                                    startActivity(intent);
                                    break;
                            }
                        }
                    });
            bmb.addBuilder(builder);
        }
    }
}
