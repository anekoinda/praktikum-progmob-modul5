package id.anekoinda.vaksini4;

public class ModelRs {
    String id_rs, nama_rs, jalan, waktu;


    public String getId_rs() {
        return id_rs;
    }

    public void setId_rs(String id_rs) {
        this.id_rs = id_rs;
    }

    public String getNama_rs() {
        return nama_rs;
    }

    public void setNama_rs(String nama_rs) {
        this.nama_rs = nama_rs;
    }

    public String getJalan() {
        return jalan;
    }

    public void setJalan(String jalan) {
        this.jalan = jalan;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public ModelRs(String id_rs, String nama_rs, String jalan, String waktu){
        this.id_rs = id_rs;
        this.nama_rs = nama_rs;
        this.jalan = jalan;
        this.waktu = waktu;
    }
}
