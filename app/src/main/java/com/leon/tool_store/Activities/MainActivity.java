package com.leon.tool_store.Activities;

import com.leon.tool_store.BaseItems.BaseActivityNoDrawer;
import com.leon.tool_store.R;

import butterknife.ButterKnife;


public class MainActivity extends BaseActivityNoDrawer {
    @Override
    protected void initialize() {
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);
    }
}
