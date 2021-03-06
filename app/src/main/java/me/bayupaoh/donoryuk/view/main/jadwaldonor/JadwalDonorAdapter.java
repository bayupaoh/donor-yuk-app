package me.bayupaoh.donoryuk.view.main.jadwaldonor;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.bayupaoh.donoryuk.R;
import me.bayupaoh.donoryuk.data.JadwalDonorDao;

/**
 * Created by codelabsunikom on 6/2/17.
 */

public class JadwalDonorAdapter extends RecyclerView.Adapter<JadwalDonorAdapter.JadwalDonorViewHolder> {

    private Context context;
    private List<JadwalDonorDao.DataBean> list;
    private JadwalDonorAdapter.JadwalDonorListener jadwalDonorListener;
    private String date;

    public JadwalDonorAdapter(Context context, List<JadwalDonorDao.DataBean> list, JadwalDonorListener jadwalDonorListener) {
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

    public void replaceData(List<JadwalDonorDao.DataBean> listJadwalDonor) {
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
        ImageView btnShare;
        @BindView(R.id.row_jadwal_bookmark)
        ImageView btnBookmark;

        public JadwalDonorViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.row_jadwal_share)
        public void shareSocialMedia(){
            jadwalDonorListener.share("Yuk datang ke "+list.get(getAdapterPosition()).getInstansi()+" di alamat "+list.get(getAdapterPosition()).getAlamat()+" untuk donor darah");
        }

        @OnClick(R.id.row_jadwal_bookmark)
        public void addBookmark(){
            jadwalDonorListener.addBookmark("Acara Donor Darah di"+list.get(getAdapterPosition()).getInstansi(),list.get(getAdapterPosition()).getAlamat(),"Acara Donor Darah");
        }
    }

    public interface JadwalDonorListener {
        void share(String message);
        void addBookmark(String tittle,String location,String description);
    }
}
