package com.pkl.stafftracking.Model;

public class User {
    //Deklarasi Variable
    private String satker;
    private String kepalaSatker;
    private String nip;
    private String tahunAnggaran;
    private String noKontrak;
    private String tanggalKontrak;
    private String nama;
    private String username;
    private String password;
    private String noTelp;
    private String tanggalMobilisasi;
    private String tanggalDemobilisasi;
    private String jam;
    private String latitude;
    private String longitude;
    private String kategoriTenaga;
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

    public String getKepalaSatker() {
        return kepalaSatker;
    }
    public void setKepalaSatker(String kepalaSatker) {
        this.kepalaSatker = kepalaSatker;
    }

    public String getNip() {
        return nip;
    }
    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getTahunAnggaran() {
        return tahunAnggaran;
    }
    public void setTahunAnggaran(String tahunAnggaran) {
        this.tahunAnggaran = tahunAnggaran;
    }

    public String getTanggalKontrak() { return tanggalKontrak; }
    public void setTanggalKontrakKontrak(String tanggalKontrak) { this.tanggalKontrak = tanggalKontrak; }

    public String getNoKontrak() {
        return noKontrak;
    }
    public void setNoKontrak(String noKontrak) {
        this.noKontrak = noKontrak;
    }

    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getNoTelp() {
        return noTelp;
    }
    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getTanggalMobilisasi() {
        return tanggalMobilisasi;
    }
    public void setTanggalMobilisasi(String tanggalMobilisasi) {
        this.tanggalMobilisasi = tanggalMobilisasi;
    }

    public String getTanggalDemobilisasi() {
        return tanggalDemobilisasi;
    }
    public void setTanggalDemobilisasi(String tanggalDemobilisasi) { this.tanggalDemobilisasi = tanggalDemobilisasi; }

    public String getJam() {
        return jam;
    }
    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getLatitude() {
        return latitude;
    }
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getKategoriTenaga() {
        return kategoriTenaga;
    }
    public void setKategoriTenaga(String kategoriTenaga) {
        this.kategoriTenaga = kategoriTenaga;
    }

    public String getJabatan() {
        return jabatan;
    }
    public void setJabatan(String jabatan) { this.jabatan = jabatan; }

    //Membuat Konstuktor kosong untuk membaca data snapshot
    public User(String s){
    }



    //Konstruktor dengan beberapa parameter, untuk mendapatkan Input Data dari User
    public User(String satker, String kepalaSatker, String nip, String tahunAnggaran, String noKontrak, String tanggalKontrak,
                    String nama, String username, String password, String noTelp, String tanggalMobilisasi, String tanggalDemobilisasi,
                    String jam, String latitude, String longitude, String kategoriTenaga, String jabatan) {
        this.satker = satker;
        this.kepalaSatker = kepalaSatker;
        this.nip = nip;
        this.tahunAnggaran = tahunAnggaran;
        this.noKontrak = noKontrak;
        this.tanggalKontrak = tanggalKontrak;
        this.nama = nama;
        this.username = username;
        this.password = password;
        this.noTelp = noTelp;
        this.tanggalMobilisasi = tanggalMobilisasi;
        this.tanggalDemobilisasi = tanggalDemobilisasi;
        this.jam = jam;
        this.latitude = latitude;
        this.longitude = longitude;
        this.kategoriTenaga = kategoriTenaga;
        this.jabatan = jabatan;

    }

    public User() {
    }

    public User(String nama, String jabatan, String satker) {
        this.satker = satker;
        this.nama = nama;
        this.jabatan = jabatan;
    }
}