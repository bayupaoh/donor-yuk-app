package me.bayupaoh.donoryuk.view.main.stockdarah;


import android.app.Dialog;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.bayupaoh.donoryuk.R;
import me.bayupaoh.donoryuk.data.ContentDao;
import me.bayupaoh.donoryuk.data.StokDarahDao;
import me.bayupaoh.donoryuk.util.ViewUtils;
import me.bayupaoh.donoryuk.view.main.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class StockDarahFragment extends Fragment implements StockDarahContract.View,SwipeRefreshLayout.OnRefreshListener,StockDarahAdapter.StokDarahListener{
    private StockDarahAdapter adapter;
    private StockDarahPresenter presenter;

    private ArrayList<ContentDao> listGol = new ArrayList<>();

    @BindView(R.id.stok_recycler)
    RecyclerView recJadwal;
    @BindView(R.id.sr_stok)
    SwipeRefreshLayout srJadwal;

    String prov = "Jawa Barat";
    String prod = "AHF";
    String gol = "a_pos";


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
        loadData(gol,prod,prov);
        setupSwipeRefresh();
        setupProdAndGol();
        return view;
    }

    private void setupProdAndGol() {
        listGol.add(new ContentDao("a_pos","A+"));
        listGol.add(new ContentDao("b_pos","B+"));
        listGol.add(new ContentDao("o_pos","O+"));
        listGol.add(new ContentDao("ab_pos","AB+"));
        listGol.add(new ContentDao("a_neg","A-"));
        listGol.add(new ContentDao("b_neg","B-"));
        listGol.add(new ContentDao("o_neg","O-"));
        listGol.add(new ContentDao("ab_neg","AB-"));
    }

    private void setupSwipeRefresh() {
        srJadwal.setColorSchemeColors(ContextCompat.getColor(getContext(), R.color.primary_dark));
        srJadwal.setOnRefreshListener(this);
    }

    private void loadData(String gol,String prod, String prov) {
        presenter.loadDataStok(gol,prod,prov);
    }

    private void setupRecyclerView() {
        recJadwal.setLayoutManager(new LinearLayoutManager(getContext()) {
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
            }
        });
        adapter = new StockDarahAdapter(getContext(), new ArrayList<StokDarahDao.DataBean>(),this);
        recJadwal.setAdapter(adapter);
    }

    private void initPresenter() {
        presenter = new StockDarahPresenter();
    }

    @Override
    public void onRefresh() {
        loadData(gol,prod,prov);
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
    public void showEventData(List<StokDarahDao.DataBean> stokDarahModel) {
        adapter.replaceData(stokDarahModel);
        ((MainActivity)getActivity()).setupSubTitle(prov);
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

    @OnClick(R.id.stock_fab)
    public void filterHasil(){
        showDialogFilter(getView());
    }

    private void showDialogFilter(View view) {
        final Dialog dialog = new Dialog(view.getContext());
        dialog.setContentView(R.layout.fragment_stock_darah_alert_filter);
        dialog.setTitle("Filter Stock Darah");

        final Spinner spnProd = (Spinner) dialog.findViewById(R.id.filter_spn_prod);
        final Spinner spnProv = (Spinner) dialog.findViewById(R.id.filter_spn_proc);
        final Spinner spnGol = (Spinner) dialog.findViewById(R.id.filter_spn_gol);
        Button btnFilter = (Button) dialog.findViewById(R.id.filter_btn_stock);

        String[] golDarah = {"A+","B+","O+","AB+","A-","B-","O-","AB-"};
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, golDarah);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnGol.setAdapter(spinnerArrayAdapter);


        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prov = spnProv.getSelectedItem().toString();
                prod = spnProd.getSelectedItem().toString();
                gol = listGol.get(spnGol.getSelectedItemPosition()).getValue();

                loadData(gol,prod,prov);
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
