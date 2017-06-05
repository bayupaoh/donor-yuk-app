package me.bayupaoh.donoryuk.view.main.stockdarah;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.bayupaoh.donoryuk.R;
import me.bayupaoh.donoryuk.data.ModelJadwalDonor;
import me.bayupaoh.donoryuk.data.ModelStokDarah;
import me.bayupaoh.donoryuk.view.main.jadwaldonor.JadwalDonorAdapter;

/**
 * Created by codelabsunikom on 6/3/17.
 */

public class StockDarahAdapter extends RecyclerView.Adapter<StockDarahAdapter.StockDarahViewHolder> {
    private Context context;
    private List<ModelStokDarah.DataBean> list;
    private StockDarahAdapter.StokDarahListener stokDarahListener;

    public StockDarahAdapter(Context context, List<ModelStokDarah.DataBean> list, StokDarahListener stokDarahListener) {
        this.context = context;
        this.list = list;
        this.stokDarahListener = stokDarahListener;
    }

    @Override
    public StockDarahViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_stock_darah_row_item, null);
        return new StockDarahAdapter.StockDarahViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StockDarahViewHolder holder, int position) {
        holder.txtUnit.setText(list.get(position).getUnit());
        holder.txtProv.setText(list.get(position).getProvinsi());
        holder.txtstock.setText(list.get(position).getJumlah()+" Kantong");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void replaceData(List<ModelStokDarah.DataBean> listStokDarah) {
        this.list = listStokDarah;
        notifyDataSetChanged();
    }


    public class StockDarahViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.row_stok_unit)
        TextView txtUnit;
        @BindView(R.id.row_stok_prov)
        TextView txtProv;
        @BindView(R.id.row_stok)
        TextView txtstock;
        @BindView(R.id.row_stock_share)
        ImageView btnShare;

        public StockDarahViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }

        @OnClick(R.id.row_stock_share)
        public void shareSocialMedia(){
            stokDarahListener.share(list.get(getAdapterPosition()).getUnit()+" tersedia "+list.get(getAdapterPosition()).getJumlah()+" kantong");
        }
    }

    public interface StokDarahListener {
        void share(String message);
    }
}
