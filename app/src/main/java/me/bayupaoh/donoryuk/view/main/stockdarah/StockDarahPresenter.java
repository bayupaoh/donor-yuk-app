package me.bayupaoh.donoryuk.view.main.stockdarah;

import android.util.Log;

import com.google.gson.Gson;

import me.bayupaoh.donoryuk.data.StokDarahDao;
import me.bayupaoh.donoryuk.data.source.remote.ApiService;
import me.bayupaoh.donoryuk.util.AppConstant;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by codelabsunikom on 6/3/17.
 */

public class StockDarahPresenter implements StockDarahContract.Presenter {
    private StockDarahContract.View stokDarahView;
    private CompositeSubscription subscription;

    @Override
    public void loadDataStok(String gol,String produk, String provinsi) {
        stokDarahView.showProgress();
        subscription.clear();
        Observable<StokDarahDao> call = ApiService.factory.create().getStokDarah(AppConstant.APIKey.KEY_SERVICES,gol,produk,provinsi);
        Subscription s = call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<StokDarahDao>() {
                    @Override
                    public void onCompleted() {
                        stokDarahView.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        stokDarahView.hideProgress();
                        stokDarahView.showErrorMessage(e.getMessage());
                    }

                    @Override
                    public void onNext(StokDarahDao modelStokDarah) {
                        Log.i("result",new Gson().toJson(modelStokDarah));
                        stokDarahView.showEventData(modelStokDarah.getData());
                    }
                });

        subscription.add(s);
    }

    @Override
    public void onAttach(StockDarahContract.View view) {
        subscription = new CompositeSubscription();
        stokDarahView = view;
    }

    @Override
    public void onDetach() {
        subscription.clear();
        stokDarahView = null;
    }
}
