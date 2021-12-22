package id.anekoinda.vaksini4;

public class ModelRs {
    String id_rs, nama_rs, jalan, waktu, id_vaksin;


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

    public String getId_vaksin() {
        return id_vaksin;
    }

    public void setId_vaksin(String id_vaksin) {
        this.id_vaksin = id_vaksin;
    }

    public ModelRs(String id_rs, String nama_rs, String jalan, String waktu, String id_vaksin){
        this.id_rs = id_rs;
        this.nama_rs = nama_rs;
        this.jalan = jalan;
        this.waktu = waktu;
        this.id_vaksin = id_vaksin;
    }
}
