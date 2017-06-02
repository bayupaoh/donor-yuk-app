package me.bayupaoh.donoryuk;

import android.content.Context;

/**
 * Created by codelabsunikom on 6/2/17.
 */

public interface BaseView {
    Context getContext();

    void onAttachView();

    void onDetachView();
}
