package me.bayupaoh.donoryuk.view.main.jadwaldonor;


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
import me.bayupaoh.donoryuk.util.ViewUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class JadwalDonorFragment extends Fragment implements JadwalDonorContract.View,SwipeRefreshLayout.OnRefreshListener {


    private JadwalDonorAdapter adapter;
    private JadwalDonorPresenter presenter;

    @BindView(R.id.jadwal_recycler)
    RecyclerView recJadwal;
    @BindView(R.id.sr_jadwal)
    SwipeRefreshLayout srJadwal;

    public JadwalDonorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_jadwal_donor, container, false);;
        ButterKnife.bind(this, view);
        initPresenter();
        onAttachView();
        setupRecyclerView();
        loadData();
        setupSwipeRefresh();
        return view;
    }

    private void setupRecyclerView() {
        recJadwal.setLayoutManager(new LinearLayoutManager(getContext()) {
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
            }
        });
        adapter = new JadwalDonorAdapter(getContext(), new ArrayList<ModelJadwalDonor.DataBean>());
        recJadwal.setAdapter(adapter);
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
    public void showEventData(List<ModelJadwalDonor.DataBean> scheduledModel) {
        adapter.replaceData(scheduledModel);
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


    private void initPresenter() {
        presenter = new JadwalDonorPresenter();
    }

    private void loadData() {
        presenter.loadDataEvent("05/16/2017", "Jawa Barat");
    }

    @Override
    public void onRefresh() {
        loadData();
    }

    private void setupSwipeRefresh() {
        srJadwal.setColorSchemeColors(ContextCompat.getColor(getContext(), R.color.primary_dark));
        srJadwal.setOnRefreshListener(this);
    }
}
