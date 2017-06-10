package me.bayupaoh.donoryuk.view.main.jadwaldonor;

import android.util.Log;

import com.google.gson.Gson;

import me.bayupaoh.donoryuk.data.ModelJadwalDonor;
import me.bayupaoh.donoryuk.data.source.remote.ApiService;
import me.bayupaoh.donoryuk.util.AppConstant;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by codelabsunikom on 6/2/17.
 */

public class JadwalDonorPresenter implements JadwalDonorContract.Presenter {
    private JadwalDonorContract.View jadwalDonorView;
    private CompositeSubscription subscription;

    @Override
    public void onAttach(JadwalDonorContract.View view) {
        subscription = new CompositeSubscription();
        jadwalDonorView = view;
    }

    @Override
    public void onDetach() {
        subscription.clear();
        jadwalDonorView = null;
    }

    @Override
    public void loadDataEvent(String tanggal, String provinsi) {
        jadwalDonorView.showProgress();
        subscription.clear();
        Observable<ModelJadwalDonor> call = ApiService.factory.create().getJadwalDonor(AppConstant.APIKey.KEY_SERVICES,tanggal,provinsi);
        Subscription s = call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ModelJadwalDonor>() {
                    @Override
                    public void onCompleted() {
                        jadwalDonorView.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        jadwalDonorView.hideProgress();
                        jadwalDonorView.showErrorMessage(e.getMessage());
                    }

                    @Override
                    public void onNext(ModelJadwalDonor modelJadwalDonor) {
                        Log.i("result",new Gson().toJson(modelJadwalDonor));
                        if(modelJadwalDonor.getData() == null) {
                            jadwalDonorView.showErrorMessage("Data Tidak Tersedia");
                        }else{
                            jadwalDonorView.showEventData(modelJadwalDonor.getData());
                        }
                    }
                });

        subscription.add(s);
    }
}
