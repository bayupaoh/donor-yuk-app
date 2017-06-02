package me.bayupaoh.donoryuk.data;

import java.util.List;

/**
 * Created by codelabsunikom on 6/2/17.
 */

public class ModelStokDarah {

    /**
     * status : success
     * data : [{"id":"m3MqK0Q703GtAbpazZbY0MWG+NxP9LmWOkHcFKZ0Z0g=","unit":"UTD PMI Kota Bandung\nJl. Aceh No. 79 Bandung\n(022) 4207052","provinsi":"Jawa Barat","jumlah":"0"},{"id":"QpHuYNxswzqmugMoioE8Sltq8I9LIoZCpzPuiCNjAHQ=","unit":"UTD PMI Kabupaten Bandung\n Jl. Kopo Bihbul Km 16 Blk No. 14 (Rsu Bihbul) Bandung\n(022) 5426689","provinsi":"Jawa Barat","jumlah":"0"},{"id":"Skwha23JY6eBgKr5oq8H9avNiKp5RYZ1B67V0q5Yqn0=","unit":"UTD PMI Kota Bogor\nJl. Kresna Raya Perum Bumi Indraprasta No.43 A, Bogor\n(0251) 8342864","provinsi":"Jawa Barat","jumlah":"0"},{"id":"XFs3wq38Okd9BMLeEaE1w4FMnj827PoMc/XA3S2ufe0=","unit":"UTD PMI Kabupaten Garut\nJl. RSU No. 5 B Belakang Perpustakaan Umum, Garut\n(0262) 233672","provinsi":"Jawa Barat","jumlah":"0"},{"id":"A11MzN9TQYkhnUt9XtKQPKMkoaFsI1qTkoYu0m/I//Q=","unit":"UTD PMI Kabupaten Karawang\nJl. Jend. Ahmad Yani 68, Karawang\n(0267) 405190/402255","provinsi":"Jawa Barat","jumlah":"0"},{"id":"yi4CvxOP/UJhGIpq5xs3ivJSf+mfKnDVDI9iFEz/6L0=","unit":"UTD PMI Kota Cirebon\nJl.Sudarsono No. 1 A, Cirebon\n(0231) 201003/ 204964","provinsi":"Jawa Barat","jumlah":"0"},{"id":"67raYIb6R2yl3a5qLPrUqPGd/S+ytjaQR1x2QIHbgPo=","unit":"UTD PMI Kota Bekasi\nJl. Lapangan Serbaguna No. 34-35 Bekasi\n(021) 88960247","provinsi":"Jawa Barat","jumlah":"0"},{"id":"N+5FEJKM+XYQuUZsayy/X8p6ZiX2UrzOe5huK5HJvTk=","unit":"UTD PMI Kabupaten Kuningan\nJl. Ciloa No. 1, Kuningan\n(0232) 871505/872336","provinsi":"Jawa Barat","jumlah":"0"}]
     */

    private String status;
    private List<DataBean> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : m3MqK0Q703GtAbpazZbY0MWG+NxP9LmWOkHcFKZ0Z0g=
         * unit : UTD PMI Kota Bandung
         Jl. Aceh No. 79 Bandung
         (022) 4207052
         * provinsi : Jawa Barat
         * jumlah : 0
         */

        private String id;
        private String unit;
        private String provinsi;
        private String jumlah;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getProvinsi() {
            return provinsi;
        }

        public void setProvinsi(String provinsi) {
            this.provinsi = provinsi;
        }

        public String getJumlah() {
            return jumlah;
        }

        public void setJumlah(String jumlah) {
            this.jumlah = jumlah;
        }
    }
}
