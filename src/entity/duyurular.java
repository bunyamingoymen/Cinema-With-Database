package entity;

import java.time.LocalDate;

public class duyurular {

    private int id;
    private int hangi_kullanici_turu;
    private String Title;
    private String Duyuru;
    private LocalDate Tarih;
    private String Kategori;

    public duyurular() {
    }

    public duyurular(int id, int hangi_kullanici_turu, String Title, String Duyuru, LocalDate Tarih, String Kategori) {
        this.id = id;
        this.hangi_kullanici_turu = hangi_kullanici_turu;
        this.Title = Title;
        this.Duyuru = Duyuru;
        this.Tarih = Tarih;
        this.Kategori = Kategori;
    }

    public duyurular(int hangi_kullanici_turu, String Title, String Duyuru, LocalDate Tarih, String Kategori) {
        this.hangi_kullanici_turu = hangi_kullanici_turu;
        this.Title = Title;
        this.Duyuru = Duyuru;
        this.Tarih = Tarih;
        this.Kategori = Kategori;

    }

    public duyurular(String Title, String Duyuru, LocalDate Tarih, String Kategori) {
        this.Title = Title;
        this.Duyuru = Duyuru;
        this.Tarih = Tarih;
        this.Kategori = Kategori;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHangi_kullanici_turu() {
        return hangi_kullanici_turu;
    }

    public void setHangi_kullanici_turu(int hangi_kullanici_turu) {
        this.hangi_kullanici_turu = hangi_kullanici_turu;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getDuyuru() {
        return Duyuru;
    }

    public void setDuyuru(String Duyuru) {
        this.Duyuru = Duyuru;
    }

    public LocalDate getTarih() {
        return Tarih;
    }

    public void setTarih(LocalDate Tarih) {
        this.Tarih = Tarih;
    }

    public String getKategori() {
        return Kategori;
    }

    public void setKategori(String Kategori) {
        this.Kategori = Kategori;
    }

}
