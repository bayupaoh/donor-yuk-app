package me.bayupaoh.donoryuk.view.main.jadwaldonor;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.bayupaoh.donoryuk.R;
import me.bayupaoh.donoryuk.data.ModelJadwalDonor;

/**
 * Created by codelabsunikom on 6/2/17.
 */

public class JadwalDonorAdapter extends RecyclerView.Adapter<JadwalDonorAdapter.JadwalDonorViewHolder> {

    private Context context;
    private List<ModelJadwalDonor.DataBean> list;
    private JadwalDonorAdapter.JadwalDonorListener jadwalDonorListener;

    public JadwalDonorAdapter(Context context, List<ModelJadwalDonor.DataBean> list, JadwalDonorListener jadwalDonorListener) {
        this.context = context;
        this.list = list;
        this.jadwalDonorListener = jadwalDonorListener;
    }

    @Override
    public JadwalDonorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_jadwal_donor_row_item, null);
        return new JadwalDonorAdapter.JadwalDonorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(JadwalDonorViewHolder holder, int position) {
        holder.txtInstansi.setText(list.get(position).getInstansi());
        holder.txtAlamat.setText(list.get(position).getAlamat());
        holder.txtPeople.setText(list.get(position).getRencana_donor()+" Kantong");
        holder.txtWaktu.setText(list.get(position).getJam());
    }

    public void replaceData(List<ModelJadwalDonor.DataBean> listJadwalDonor) {
        this.list = listJadwalDonor;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class JadwalDonorViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.row_jadwal_instansi)
        TextView txtInstansi;
        @BindView(R.id.row_jadwal_alamat)
        TextView txtAlamat;
        @BindView(R.id.row_jadwal_people)
        TextView txtPeople;
        @BindView(R.id.row_jadwal_waktu)
        TextView txtWaktu;
        @BindView(R.id.row_jadwal_share)
        Button btnShare;

        public JadwalDonorViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.row_jadwal_share)
        public void shareSocialMedia(){
            jadwalDonorListener.share("Yuk datang ke "+list.get(getAdapterPosition()).getInstansi()+" di alamat "+list.get(getAdapterPosition()).getAlamat()+" untuk donor darah");
        }
    }

    public interface JadwalDonorListener {
        void share(String message);
    }
}
