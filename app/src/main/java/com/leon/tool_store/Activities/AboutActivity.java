package com.leon.tool_store.Activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.leon.tool_store.Adapter.AdapterList;
import com.leon.tool_store.BaseItems.BaseActivityDrawer;
import com.leon.tool_store.R;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutActivity extends BaseActivityDrawer {
    Context context;
    @BindView(R.id.list)
    ListView list;
    String[] titleId;
    String[] subtitleId;
    Integer[] imageId = {
            R.drawable.ic_other_appname,
            R.drawable.ic_other_build,
            R.drawable.ic_other_email,
            R.drawable.ic_other_copyright,
            R.drawable.ic_other_share,
            R.drawable.ic_other_rate,
            R.drawable.ic_other_more
    };

    @Override
    protected void initialize() {
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View childLayout = Objects.requireNonNull(inflater).inflate(R.layout.about_activity, findViewById(R.id.about_activity));
        @SuppressLint("CutPasteId") ConstraintLayout parentLayout = findViewById(R.id.base_Content);
        parentLayout.addView(childLayout);
        ButterKnife.bind(this);
        context = this;
        initializeListView();
    }

    void initializeListView() {
        titleId = getResources().getStringArray(R.array.title);
        subtitleId = getResources().getStringArray(R.array.subtitle);
        AdapterList adapter = new AdapterList(AboutActivity.this, titleId, subtitleId, imageId);
        list.setAdapter(adapter);
    }
}
