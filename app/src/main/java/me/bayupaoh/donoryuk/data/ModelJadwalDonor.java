package me.bayupaoh.donoryuk.data;

import java.util.List;

/**
 * Created by codelabsunikom on 6/2/17.
 */

public class ModelJadwalDonor {

    /**
     * status : success
     * data : [{"instansi":"UTD PMI Kabupaten Bandung\nPT CAHAYA TUNGGAL SANTOSA","alamat":"Jl.Antapani","jam":"09:00:00","rencana_donor":"80"},{"instansi":"UTD PMI Kabupaten Bandung\nPT ISS","alamat":"BANDUNG","jam":"14:00:00","rencana_donor":"60"},{"instansi":"UTD PMI Kota Bekasi\nPT BRIDGESTONE","alamat":"BEKASI","jam":"08:00:00","rencana_donor":"150"},{"instansi":"UTD PMI Kabupaten Bogor\nFIF CILEUNGSI","alamat":"JL RAYA NAROGONG CILEUNGSI","jam":"08:30:00","rencana_donor":"60"},{"instansi":"UTD PMI Kabupaten Majalengka\nFIF CILEUNGSI","alamat":"JL RAYA NAROGONG CILEUNGSI","jam":"08:30:00","rencana_donor":"60"},{"instansi":"UTD PMI Kabupaten Bogor\nPT MEDIFARMA","alamat":"Cimanggis bogor","jam":"13:00:00","rencana_donor":"60"},{"instansi":"UTD PMI Kabupaten Majalengka\nPT MEDIFARMA","alamat":"Cimanggis bogor","jam":"13:00:00","rencana_donor":"60"},{"instansi":"UTD PMI Kota Bekasi\nUDD METROPOLITAN MALL BEKASI","alamat":"","jam":"11:00:00","rencana_donor":"30"},{"instansi":"UTD PMI Kota Cirebon\nRw 07 karang jalak mekar sunyaragi","alamat":"Sunyaragi","jam":"09:00:00","rencana_donor":"20"},{"instansi":"UTD PMI Kota Cirebon\nRS. PELABUHAN","alamat":"JL.SISINGAMANGARAJA","jam":"09:00:00","rencana_donor":"30"}]
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
         * instansi : UTD PMI Kabupaten Bandung
         PT CAHAYA TUNGGAL SANTOSA
         * alamat : Jl.Antapani
         * jam : 09:00:00
         * rencana_donor : 80
         */

        private String instansi;
        private String alamat;
        private String jam;
        private String rencana_donor;

        public String getInstansi() {
            return instansi;
        }

        public void setInstansi(String instansi) {
            this.instansi = instansi;
        }

        public String getAlamat() {
            return alamat;
        }

        public void setAlamat(String alamat) {
            this.alamat = alamat;
        }

        public String getJam() {
            return jam;
        }

        public void setJam(String jam) {
            this.jam = jam;
        }

        public String getRencana_donor() {
            return rencana_donor;
        }

        public void setRencana_donor(String rencana_donor) {
            this.rencana_donor = rencana_donor;
        }
    }
}
