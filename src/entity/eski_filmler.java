package entity;

import javafx.scene.control.Button;

public class eski_filmler {

    private int eski_film_id;
    private int film_id;
    private String film_name;
    private String film_type;
    private int film_suresi;
    private String yonetmen_ad_soyad;
    private int hangi_aboneler_izleyebilir;
    private int aldigi_odul_sayisi;
    private int yonetmen_id;
    private float kullanici_puani;
    private Button detay;

    public eski_filmler() {
    }

    public eski_filmler(int eski_film_id, int film_id, int hangi_aboneler_izleyebilir, int aldigi_odul_sayisi) {
        this.eski_film_id = eski_film_id;
        this.film_id = film_id;
        this.hangi_aboneler_izleyebilir = hangi_aboneler_izleyebilir;
        this.aldigi_odul_sayisi = aldigi_odul_sayisi;
    }

    public eski_filmler(int film_id, int hangi_aboneler_izleyebilir, int aldigi_odul_sayisi) {
        this.film_id = film_id;
        this.hangi_aboneler_izleyebilir = hangi_aboneler_izleyebilir;
        this.aldigi_odul_sayisi = aldigi_odul_sayisi;
    }

    public eski_filmler(String film_name, String film_type, int film_suresi, String yonetmen_ad, String yonetmen_soyad, int hangi_aboneler_izleyebilir, int aldigi_odul_sayisi, float kullanici_puani, Button detay) {
        this.film_name = film_name;
        this.film_type = film_type;
        this.film_suresi = film_suresi;
        this.yonetmen_ad_soyad = yonetmen_ad + " " + yonetmen_soyad;
        this.hangi_aboneler_izleyebilir = hangi_aboneler_izleyebilir;
        this.aldigi_odul_sayisi = aldigi_odul_sayisi;
        this.kullanici_puani = kullanici_puani;
        this.detay = detay;

        detay.setOnAction(e -> {
            System.out.println("1");
        });

    }

    public eski_filmler(int eski_film_id, int film_id, String film_name, String film_type, int film_suresi, int aldigi_odul, int hangi_abone, int yonetmen_id) {
        this.eski_film_id = eski_film_id;
        this.film_id = film_id;
        this.film_name = film_name;
        this.film_type = film_type;
        this.film_suresi = film_suresi;
        this.aldigi_odul_sayisi = aldigi_odul;
        this.hangi_aboneler_izleyebilir = hangi_abone;
        this.yonetmen_id = yonetmen_id;
    }

    public eski_filmler(String film_name, String film_type, int film_suresi, String ad, String soyad, int aldigi_odul_sayisi, Button detay) {
        this.film_name = film_name;
        this.film_type = film_type;
        this.film_suresi = film_suresi;
        this.yonetmen_ad_soyad = ad + " " + soyad;
        this.aldigi_odul_sayisi = aldigi_odul_sayisi;
        this.detay = detay;

        detay.setOnAction(e -> {
            System.out.println("1");
        });
    }

    public int getEski_film_id() {
        return eski_film_id;
    }

    public void setEski_film_id(int eski_film_id) {
        this.eski_film_id = eski_film_id;
    }

    public int getFilm_id() {
        return film_id;
    }

    public void setFilm_id(int film_id) {
        this.film_id = film_id;
    }

    public int getHangi_aboneler_izleyebilir() {
        return hangi_aboneler_izleyebilir;
    }

    public void setHangi_aboneler_izleyebilir(int hangi_aboneler_izleyebilir) {
        this.hangi_aboneler_izleyebilir = hangi_aboneler_izleyebilir;
    }

    public int getAldigi_odul_sayisi() {
        return aldigi_odul_sayisi;
    }

    public void setAldigi_odul_sayisi(int aldigi_odul_sayisi) {
        this.aldigi_odul_sayisi = aldigi_odul_sayisi;
    }

    public String getFilm_name() {
        return film_name;
    }

    public void setFilm_name(String film_name) {
        this.film_name = film_name;
    }

    public String getFilm_type() {
        return film_type;
    }

    public void setFilm_type(String film_type) {
        this.film_type = film_type;
    }

    public int getFilm_suresi() {
        return film_suresi;
    }

    public void setFilm_suresi(int film_suresi) {
        this.film_suresi = film_suresi;
    }

    public String getYonetmen_ad_soyad() {
        return yonetmen_ad_soyad;
    }

    public void setYonetmen_ad_soyad(String yonetmen_ad_soyad) {
        this.yonetmen_ad_soyad = yonetmen_ad_soyad;
    }

    public int getYonetmen_id() {
        return yonetmen_id;
    }

    public void setYonetmen_id(int yonetmen_id) {
        this.yonetmen_id = yonetmen_id;
    }

    public float getKullanici_puani() {
        return kullanici_puani;
    }

    public void setKullanici_puani(float kullanici_puani) {
        this.kullanici_puani = kullanici_puani;
    }

    public Button getDetay() {
        return detay;
    }

    public void setDetay(Button detay) {
        this.detay = detay;
    }

}
