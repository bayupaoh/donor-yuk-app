package me.bayupaoh.donoryuk.view.main.jadwaldonor;

import java.util.List;

import me.bayupaoh.donoryuk.BasePresenter;
import me.bayupaoh.donoryuk.BaseView;
import me.bayupaoh.donoryuk.data.JadwalDonorDao;

/**
 * Created by codelabsunikom on 6/2/17.
 */

public class JadwalDonorContract {
    interface View extends BaseView {
        void showProgress();

        void hideProgress();

        void showErrorMessage(String message);

        void showEventData(List<JadwalDonorDao.DataBean> scheduledModel);
    }

    interface Presenter extends BasePresenter<View> {
        void loadDataEvent(String tanggal,String provinsi);
    }
}
