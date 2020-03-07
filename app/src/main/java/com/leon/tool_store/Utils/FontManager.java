package com.leon.tool_store.Utils;

import android.content.Context;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.leon.tool_store.MyApplication;

import java.util.Stack;

public class FontManager {
    private Typeface typeface;
    private Context context;

    public FontManager(Context context) {
        this.context = context;
        initializeTypeface();
    }

    public FontManager() {
        this.context = MyApplication.getContext();
        initializeTypeface();
    }

    private void initializeTypeface() {
        typeface = Typeface.createFromAsset(context.getAssets(), MyApplication.getFontName());
    }

    public void setFont(ViewGroup viewGroup) {
        Stack<ViewGroup> stackOfViewGroup = new Stack<ViewGroup>();
        stackOfViewGroup.push(viewGroup);
        while (!stackOfViewGroup.isEmpty()) {
            ViewGroup tree = stackOfViewGroup.pop();
            for (int i = 0; i < tree.getChildCount(); i++) {
                View child = tree.getChildAt(i);
                if (child instanceof ViewGroup) {
                    stackOfViewGroup.push((ViewGroup) child);
                } else if (child instanceof Button) {
                    ((Button) child).setTypeface(typeface);
                } else if (child instanceof EditText) {
                    ((EditText) child).setTypeface(typeface);
                } else if (child instanceof TextView) {
                    ((TextView) child).setTypeface(typeface);
                }
            }
        }
    }


    public void setFont(SpannableString spannableString) {
        spannableString.setSpan(typeface, 0, spannableString.length(), 0);
    }

    public void setFont(View view) {
        ((TextView) view).setTypeface(typeface);
    }
}
