package com.pkl.stafftracking.Model;

public class UserInfo {
    //Deklarasi Variable
    private String nama;
    private String satker;
    private String jabatan;
    private String key;

    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }

    public String getSatker() {
        return satker;
    }
    public void setSatker(String satker) {
        this.satker = satker;
    }

    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJabatan() {
        return jabatan;
    }
    public void setJabatan(String jabatan) { this.jabatan = jabatan; }

    //Membuat Konstuktor kosong untuk membaca data snapshot
    public UserInfo(String s){
    }



    //Konstruktor dengan beberapa parameter, untuk mendapatkan Input Data dari User

    public UserInfo() {
    }

    public UserInfo(String nama, String jabatan, String satker) {
        this.satker = satker;
        this.nama = nama;
        this.jabatan = jabatan;
    }
}