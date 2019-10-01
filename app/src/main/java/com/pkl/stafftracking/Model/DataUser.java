package com.pkl.stafftracking.Model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class DataUser {

    //Deklarasi Variable
    private String nama;
    private String konsultan;
    private String nama_paket;
    private String ppk_pjn;
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKonsultan() {
        return konsultan;
    }

    public void setKonsultan(String konsultan) {
        this.konsultan = konsultan;
    }

    public String getNama_paket() {
        return nama_paket;
    }

    public void setNama_paket(String nama_paket) {
        this.nama_paket = nama_paket;
    }
    public String getPpk_pjn() {
        return ppk_pjn;
    }

    public void setPpk_pjn(String ppk_pjn) {
        this.ppk_pjn = ppk_pjn;
    }
    //Membuat Konstuktor kosong untuk membaca data snapshot
    public DataUser(){
    }

    //Konstruktor dengan beberapa parameter, untuk mendapatkan Input Data dari User
    public DataUser(String nama, String konsultan, String nama_paket, String ppk_pjn) {
        this.nama = nama;
        this.konsultan = konsultan;
        this.nama_paket = nama_paket;
        this.ppk_pjn = ppk_pjn;
    }
}

