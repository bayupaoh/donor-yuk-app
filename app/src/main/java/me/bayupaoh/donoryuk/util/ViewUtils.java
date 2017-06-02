package me.bayupaoh.donoryuk.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by codelabsunikom on 6/2/17.
 */

public class ViewUtils {
    public static void showToast(Context context, String message) {
        if (context != null && message != null)
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
