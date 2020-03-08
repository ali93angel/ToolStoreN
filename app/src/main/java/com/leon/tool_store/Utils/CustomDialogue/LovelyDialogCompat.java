package com.leon.tool_store.Utils.CustomDialogue;

import android.content.DialogInterface;
import android.view.View;

public class LovelyDialogCompat {
    public LovelyDialogCompat() {
    }

    public static View.OnClickListener wrap(android.content.DialogInterface.OnClickListener listener) {
        return new DialogOnClickListenerAdapter(listener);
    }

    static class DialogOnClickListenerAdapter implements View.OnClickListener {
        private android.content.DialogInterface.OnClickListener adapted;

        DialogOnClickListenerAdapter(android.content.DialogInterface.OnClickListener adapted) {
            this.adapted = adapted;
        }

        public void onClick(DialogInterface dialogInterface, int which) {
            if (this.adapted != null) {
                this.adapted.onClick(dialogInterface, which);
            }

        }

        public void onClick(View v) {
        }
    }
}

