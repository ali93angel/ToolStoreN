package com.leon.tool_store.Infrastructure;


import android.view.View;

public interface ViewConfigurator<T extends View> {
    void configureView(T v);
}
