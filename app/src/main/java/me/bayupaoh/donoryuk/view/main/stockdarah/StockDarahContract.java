package me.bayupaoh.donoryuk.view.main.stockdarah;

import java.util.List;

import me.bayupaoh.donoryuk.BasePresenter;
import me.bayupaoh.donoryuk.BaseView;
import me.bayupaoh.donoryuk.data.StokDarahDao;

/**
 * Created by codelabsunikom on 6/3/17.
 */

public class StockDarahContract {
    interface View extends BaseView {
        void showProgress();

        void hideProgress();

        void showErrorMessage(String message);

        void showEventData(List<StokDarahDao.DataBean> stokDarahModel);
    }

    interface Presenter extends BasePresenter<StockDarahContract.View> {
        void loadDataStok(String gol,String produk,String provinsi);
    }
}
