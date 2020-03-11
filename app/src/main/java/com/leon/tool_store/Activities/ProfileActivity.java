package com.leon.tool_store.Activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.leon.tool_store.BaseItems.BaseActivityDrawer;
import com.leon.tool_store.R;

import java.util.Objects;

import butterknife.ButterKnife;

public class ProfileActivity extends BaseActivityDrawer {
    Context context;

    @Override
    protected void initialize() {
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View childLayout = Objects.requireNonNull(inflater).inflate(R.layout.profile_activity, findViewById(R.id.activity_profile));
        @SuppressLint("CutPasteId") ConstraintLayout parentLayout = findViewById(R.id.base_Content);
        parentLayout.addView(childLayout);
        ButterKnife.bind(this);
        context = this;
    }
}
