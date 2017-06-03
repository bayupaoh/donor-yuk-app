package me.bayupaoh.donoryuk.util;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

/**
 * Created by codelabsunikom on 6/2/17.
 */

public class ViewUtils {
    public static void showToast(View view, String message) {
        if (view != null && message != null)
            Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }
}
