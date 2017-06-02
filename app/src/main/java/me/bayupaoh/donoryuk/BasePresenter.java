package me.bayupaoh.donoryuk;

/**
 * Created by codelabsunikom on 6/2/17.
 */

public interface BasePresenter<T extends BaseView>  {
    void onAttach(T view);

    void onDetach();
}
