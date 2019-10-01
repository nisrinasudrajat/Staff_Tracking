package com.pkl.stafftracking.Model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties

public class Absen {
    //Deklarasi Variable
    private String tanggal;
    private String hari;
    private String alasan_absen;
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTanggal() {
        return tanggal;
    }
    public String getAlasan_absen() {
        return alasan_absen;
    }
    public String getHari() {
        return hari;
    }

    //Membuat Konstuktor kosong untuk membaca data snapshot
    public Absen(){
    }

    //Konstruktor dengan beberapa parameter, untuk mendapatkan Input Data dari User
    public Absen(String hari, String alasan_absen, String tanggal) {
        this.hari = hari;
        this.alasan_absen = alasan_absen;
        this.tanggal= tanggal ;
    }
}
