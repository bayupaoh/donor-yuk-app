package me.bayupaoh.donoryuk.data.source.remote;

import java.util.concurrent.TimeUnit;

import me.bayupaoh.donoryuk.data.ModelJadwalDonor;
import me.bayupaoh.donoryuk.data.ModelListContent;
import me.bayupaoh.donoryuk.data.ModelStokDarah;
import me.bayupaoh.donoryuk.util.AppConstant;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by codelabsunikom on 6/2/17.
 */

public interface ApiService {
    String BASE_URL = AppConstant.APIUrl.BASE_API;

    @GET("exec")
    Observable<ModelJadwalDonor> getJadwalDonor(@Query("service") String service, @Query("tanggal") String tanggal, @Query("provinsi") String provinsi);

    @GET("exec")
    Observable<ModelListContent> getListContent();

    @GET("exec")
    Observable<ModelStokDarah> getStokDarah(@Query("service") String service, @Query("gol") String gol, @Query("produk") String produk, @Query("provinsi") String provinsi);

    class factory {
        public static ApiService create() {
            OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
            builder.readTimeout(20, TimeUnit.SECONDS);
            builder.connectTimeout(10, TimeUnit.SECONDS);
            builder.writeTimeout(10, TimeUnit.SECONDS);

            OkHttpClient client = builder.build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(ApiService.class);
        }
    }
}
