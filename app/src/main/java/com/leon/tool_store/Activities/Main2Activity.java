package com.leon.tool_store.Activities;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.leon.tool_store.BaseItems.BaseActivityDrawer;
import com.leon.tool_store.R;

import java.util.Objects;

import butterknife.ButterKnife;

public class Main2Activity extends BaseActivityDrawer {

    @Override
    protected void initialize() {
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View childLayout = Objects.requireNonNull(inflater).inflate(R.layout.main2_activity, findViewById(R.id.main_activity_parent));
        @SuppressLint("CutPasteId") ConstraintLayout parentLayout = findViewById(R.id.base_Content);
        parentLayout.addView(childLayout);
        ButterKnife.bind(this);
    }
}
