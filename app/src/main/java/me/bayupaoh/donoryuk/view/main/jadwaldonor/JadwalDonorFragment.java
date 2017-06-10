package me.bayupaoh.donoryuk.view.main.jadwaldonor;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.bayupaoh.donoryuk.R;
import me.bayupaoh.donoryuk.data.ModelJadwalDonor;
import me.bayupaoh.donoryuk.util.StringUtils;
import me.bayupaoh.donoryuk.util.ViewUtils;
import me.bayupaoh.donoryuk.view.main.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class JadwalDonorFragment extends Fragment implements JadwalDonorContract.View, SwipeRefreshLayout.OnRefreshListener, JadwalDonorAdapter.JadwalDonorListener {


    private JadwalDonorAdapter adapter;
    private JadwalDonorPresenter presenter;
    private String date = "";
    private String provinsi = "Jawa Barat";

    @BindView(R.id.jadwal_recycler)
    RecyclerView recJadwal;
    @BindView(R.id.sr_jadwal)
    SwipeRefreshLayout srJadwal;

    TextView txtWaktu;

    public JadwalDonorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_jadwal_donor, container, false);
        ;
        ButterKnife.bind(this, view);
        initPresenter();
        onAttachView();
        settingDate();
        setupRecyclerView();
        loadData(provinsi);
        setupSwipeRefresh();
        return view;
    }

    private void settingDate() {
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        date = dateFormat.format(calendar.getTime());
        Log.d("waktu", date);
    }

    private void setupRecyclerView() {
        recJadwal.setLayoutManager(new LinearLayoutManager(getContext()) {
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
            }
        });
        adapter = new JadwalDonorAdapter(getContext(), new ArrayList<ModelJadwalDonor.DataBean>(), this);
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

    private void loadData(String provinsi) {
        presenter.loadDataEvent(date, provinsi);
    }

    @Override
    public void onRefresh() {
        loadData(provinsi);
    }

    private void setupSwipeRefresh() {
        srJadwal.setColorSchemeColors(ContextCompat.getColor(getContext(), R.color.primary_dark));
        srJadwal.setOnRefreshListener(this);
    }

    @Override
    public void share(String message) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, message);
        startActivity(Intent.createChooser(shareIntent, "Bagikan ke sosial media"));

    }

    @Override
    public void addBookmark(String tittle, String location, String description) {
        Intent calIntent = new Intent(Intent.ACTION_INSERT);
        calIntent.setType("vnd.android.cursor.item/event");
        calIntent.putExtra(CalendarContract.Events.TITLE, tittle);
        calIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, location);
        calIntent.putExtra(CalendarContract.Events.DESCRIPTION, description);

        GregorianCalendar calDate = new GregorianCalendar(2017, 6, 5);
        calIntent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);
        calIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
                calDate.getTimeInMillis());
        calIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,
                calDate.getTimeInMillis());

        startActivity(calIntent);
    }

    @OnClick(R.id.jadwal_fab)
    public void filter() {
        showDialogFilter(getView());
    }

    private void showDialogFilter(View view) {
        final Dialog dialog = new Dialog(view.getContext());
        dialog.setContentView(R.layout.fragment_jadwal_donor_alert_filter);
        dialog.setTitle("Filter Jadwal Donor");
        final Spinner spinnerProvinsi = (Spinner) dialog.findViewById(R.id.filter_spn_prov);
        txtWaktu = (TextView) dialog.findViewById(R.id.filter_txt_waktu);
        Button btnFilter = (Button) dialog.findViewById(R.id.filter_btn_jadwal);

        txtWaktu.setText(date);

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                provinsi = spinnerProvinsi.getSelectedItem().toString();
                loadData(provinsi);
                dialog.dismiss();
            }
        });

        txtWaktu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimeDialog();
            }
        });
        dialog.show();
    }

    private void showTimeDialog() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),DatePickerDialog.THEME_DEVICE_DEFAULT_DARK, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                date = StringUtils.convertCalendarToString(i,i1,i2);
                txtWaktu.setText(date);
            }
        },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }
}
