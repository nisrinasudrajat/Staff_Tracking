package com.pkl.stafftracking.Model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties

public class Pulang {
    //Deklarasi Variable
    private String jam;
    private String hari;
    private String tanggal;
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getJam() {
        return jam;
    }
    public String getTanggal() {
        return tanggal;
    }
    public String getHari() {
        return hari;
    }

    //Membuat Konstuktor kosong untuk membaca data snapshot
    public Pulang(){
    }

    //Konstruktor dengan beberapa parameter, untuk mendapatkan Input Data dari User
    public Pulang(String hari, String tanggal, String jam) {
        this.hari = hari;
        this.tanggal = tanggal;
        this.jam = jam ;
    }
}
