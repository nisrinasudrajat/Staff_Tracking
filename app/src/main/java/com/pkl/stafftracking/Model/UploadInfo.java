package com.pkl.stafftracking.Model;

public class UploadInfo {

    public String keterangan;
    public String hari;
    public String tanggal;
    public String jam;
    public String fileURL;

    public UploadInfo(String hari, String tanggal, String jam, String ket, String url) {
        this.keterangan = ket;
        this.fileURL= url;
        this.hari = hari;
        this.tanggal = tanggal;
        this.jam = jam;
    }
    public String getHari(){
        return hari;
    }
    public String getJam(){
        return jam;
    }
    public String getTanggal(){
        return tanggal;
    }
    public String getKeterangan() {
        return keterangan;
    }
    public String getFileURL() {
        return fileURL;
    }
}
