package me.bayupaoh.donoryuk.data;

import java.util.List;

/**
 * Created by codelabsunikom on 6/2/17.
 */

public class ListContentDao {

    /**
     * status : success
     * data : {"gol":[{"value":"a_pos","content":"A+"},{"value":"b_pos","content":"B+"},{"value":"o_pos","content":"O+"},{"value":"ab_pos","content":"AB+"},{"value":"a_neg","content":"A-"},{"value":"b_neg","content":"B-"},{"value":"o_neg","content":"O-"},{"value":"ab_neg","content":"AB-"}],"provinsi":[{"value":"Aceh","content":" Aceh"},{"value":"Bali","content":" Bali"},{"value":"Bangka Belitung","content":" Bangka Belitung"},{"value":"Banten","content":" Banten"},{"value":"Bengkulu","content":" Bengkulu"},{"value":"Daerah Istimewa Yogyakarta","content":" Daerah Istimewa Yogyakarta"},{"value":"DKI Jakarta","content":" DKI Jakarta"},{"value":"Gorontalo","content":" Gorontalo"},{"value":"Jawa Barat","content":" Jawa Barat"},{"value":"Jawa Tengah","content":" Jawa Tengah"},{"value":"Jawa Timur","content":" Jawa Timur"},{"value":"Kalimantan Barat","content":" Kalimantan Barat"},{"value":"Kalimantan Selatan","content":" Kalimantan Selatan"},{"value":"Kalimantan Tengah","content":" Kalimantan Tengah"},{"value":"Kalimantan Timur","content":" Kalimantan Timur"},{"value":"Kepulauan Riau","content":" Kepulauan Riau"},{"value":"Lampung","content":" Lampung"},{"value":"Maluku","content":" Maluku"},{"value":"Maluku Utara","content":" Maluku Utara"},{"value":"Nusa Tenggara Barat","content":" Nusa Tenggara Barat"},{"value":"Nusa Tenggara Timur","content":" Nusa Tenggara Timur"},{"value":"Papua Barat","content":" Papua Barat"},{"value":"Papua Tengah","content":" Papua Tengah"},{"value":"Papua Timur","content":" Papua Timur"},{"value":"Riau","content":" Riau"},{"value":"Sulawesi Barat","content":" Sulawesi Barat"},{"value":"Sulawesi Selatan","content":" Sulawesi Selatan"},{"value":"Sulawesi Tengah","content":" Sulawesi Tengah"},{"value":"Sulawesi Tenggara","content":" Sulawesi Tenggara"},{"value":"Sulawesi Utara","content":" Sulawesi Utara"},{"value":"Sumatera Barat","content":" Sumatera Barat"},{"value":"Sumatera Selatan","content":" Sumatera Selatan"},{"value":"Sumatera Utara","content":" Sumatera Utara"}],"produk":[{"value":"AHF","content":"AHF"},{"value":"BC","content":"BC"},{"value":"FFP","content":"FFP"},{"value":"FP","content":"FP"},{"value":"Lekosit Aferesis","content":"Lekosit Aferesis"},{"value":"Leucodepleted","content":"Leucodepleted"},{"value":"Leucodepletet","content":"Leucodepletet"},{"value":"Leucoreduced","content":"Leucoreduced"},{"value":"LP","content":"LP"},{"value":"LP Aferesis","content":"LP Aferesis"},{"value":"PCL","content":"PCL"},{"value":"PCLs","content":"PCLs"},{"value":"PRC","content":"PRC"},{"value":"PRC Aferesis","content":"PRC Aferesis"},{"value":"TC","content":"TC"},{"value":"TC Aferesis","content":"TC Aferesis"},{"value":"TC Apheresi","content":"TC Apheresi"},{"value":"TC APR","content":"TC APR"},{"value":"TCP","content":"TCP"},{"value":"WB","content":"WB"},{"value":"WB FRESH","content":"WB FRESH"},{"value":"WE","content":"WE"}]}
     */

    private String status;
    private DataBean data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<GolBean> gol;
        private List<ProvinsiBean> provinsi;
        private List<ProdukBean> produk;

        public List<GolBean> getGol() {
            return gol;
        }

        public void setGol(List<GolBean> gol) {
            this.gol = gol;
        }

        public List<ProvinsiBean> getProvinsi() {
            return provinsi;
        }

        public void setProvinsi(List<ProvinsiBean> provinsi) {
            this.provinsi = provinsi;
        }

        public List<ProdukBean> getProduk() {
            return produk;
        }

        public void setProduk(List<ProdukBean> produk) {
            this.produk = produk;
        }

        public static class GolBean {
            /**
             * value : a_pos
             * content : A+
             */

            private String value;
            private String content;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }

        public static class ProvinsiBean {
            /**
             * value : Aceh
             * content :  Aceh
             */

            private String value;
            private String content;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }

        public static class ProdukBean {
            /**
             * value : AHF
             * content : AHF
             */

            private String value;
            private String content;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }
    }
}
