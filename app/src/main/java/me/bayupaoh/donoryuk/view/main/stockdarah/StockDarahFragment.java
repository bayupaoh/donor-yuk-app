package me.bayupaoh.donoryuk.view.main.stockdarah;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.bayupaoh.donoryuk.R;
import me.bayupaoh.donoryuk.data.ModelJadwalDonor;
import me.bayupaoh.donoryuk.data.ModelStokDarah;
import me.bayupaoh.donoryuk.util.ViewUtils;
import me.bayupaoh.donoryuk.view.main.jadwaldonor.JadwalDonorAdapter;
import me.bayupaoh.donoryuk.view.main.jadwaldonor.JadwalDonorPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class StockDarahFragment extends Fragment implements StockDarahContract.View,SwipeRefreshLayout.OnRefreshListener,StockDarahAdapter.StokDarahListener{
    private StockDarahAdapter adapter;
    private StockDarahPresenter presenter;

    @BindView(R.id.stok_recycler)
    RecyclerView recJadwal;
    @BindView(R.id.sr_stok)
    SwipeRefreshLayout srJadwal;


    public StockDarahFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_stock_darah, container, false);;
        ButterKnife.bind(this, view);
        initPresenter();
        onAttachView();
        setupRecyclerView();
        loadData();
        setupSwipeRefresh();
        return view;
    }

    private void setupSwipeRefresh() {
        srJadwal.setColorSchemeColors(ContextCompat.getColor(getContext(), R.color.primary_dark));
        srJadwal.setOnRefreshListener(this);
    }

    private void loadData() {
        presenter.loadDataStok("a_pos","AHF","Jawa Barat");
    }

    private void setupRecyclerView() {
        recJadwal.setLayoutManager(new LinearLayoutManager(getContext()) {
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
            }
        });
        adapter = new StockDarahAdapter(getContext(), new ArrayList<ModelStokDarah.DataBean>(),this);
        recJadwal.setAdapter(adapter);
    }

    private void initPresenter() {
        presenter = new StockDarahPresenter();
    }

    @Override
    public void onRefresh() {
        loadData();
    }

    @Override
    public void showProgress() {
        srJadwal.post(new Runnable() {
            @Override
            public void run() {
                srJadwal.setRefreshing(true);
            }
        });
    }

    @Override
    public void hideProgress() {
        srJadwal.post(new Runnable() {
            @Override
            public void run() {
                srJadwal.setRefreshing(false);
            }
        });
    }

    @Override
    public void showErrorMessage(String message) {
        ViewUtils.showToast(getView(), message);
    }

    @Override
    public void showEventData(List<ModelStokDarah.DataBean> stokDarahModel) {
        adapter.replaceData(stokDarahModel);
    }

    @Override
    public void onAttachView() {
        presenter.onAttach(this);
    }

    @Override
    public void onDetachView() {
        presenter.onDetach();
    }

    @Override
    public void onDestroy() {
        onDetachView();
        super.onDestroy();
    }

    @Override
    public void share(String message) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, message);
        startActivity(Intent.createChooser(shareIntent, "Bagikan ke sosial media"));
    }
}
