package entity;

import javafx.scene.control.Button;

public class vizyondaki_filmler {

    private int vizyondaki_filmler_id;
    private int film_id;
    private String film_name;
    private String film_type;
    private int film_suresi;
    private String yonetmen_ad_soyad;
    private String vizyondan_kalkis_tarihi;
    private int kullanici_puani;
    private int seans_sayisi;
    private Button film_detayi;

    public vizyondaki_filmler(int film_id, String vizyondan_kalkis_tarihi, int kullanici_puani, int seans_sayisi) {
        this.film_id = film_id;
        this.vizyondan_kalkis_tarihi = vizyondan_kalkis_tarihi;
        this.kullanici_puani = kullanici_puani;
        this.seans_sayisi = seans_sayisi;
    }

    public vizyondaki_filmler(int vizyondaki_filmler_id, int film_id, String vizyondan_kalkis_tarihi, int kullanici_puani, int seans_sayisi) {
        this.vizyondaki_filmler_id = vizyondaki_filmler_id;
        this.film_id = film_id;
        this.vizyondan_kalkis_tarihi = vizyondan_kalkis_tarihi;
        this.kullanici_puani = kullanici_puani;
        this.seans_sayisi = seans_sayisi;
    }

    public vizyondaki_filmler(String film_name, String film_type, int film_suresi, String yonetmen_ad, String yonetmen_soyad, String vizyondan_kalkis_tarihi, int kullanici_puani) {
        this.film_name = film_name;
        this.film_type = film_type;
        this.film_suresi = film_suresi;
        this.yonetmen_ad_soyad = yonetmen_ad + " " + yonetmen_soyad;
        this.vizyondan_kalkis_tarihi = vizyondan_kalkis_tarihi;
        this.kullanici_puani = kullanici_puani;
    }

    public vizyondaki_filmler(int vizyondaki_filmler_id, String film_name, String film_type, int film_suresi, String ad, String soyad, String vizyondan_kalkis_tarihi, int kullanici_puani, Button detay) {
        this.vizyondaki_filmler_id = vizyondaki_filmler_id;
        this.film_name = film_name;
        this.film_type = film_type;
        this.film_suresi = film_suresi;
        this.yonetmen_ad_soyad = ad + " " + soyad;
        this.vizyondan_kalkis_tarihi = vizyondan_kalkis_tarihi;
        this.kullanici_puani = kullanici_puani;
        this.film_detayi = detay;

        detay.setOnAction(e -> {
            System.out.println("1");
        });
    }

    public int getVizyondaki_filmler_id() {
        return vizyondaki_filmler_id;
    }

    public void setVizyondaki_filmler_id(int vizyondaki_filmler_id) {
        this.vizyondaki_filmler_id = vizyondaki_filmler_id;
    }

    public vizyondaki_filmler() {
    }

    public int getFilm_id() {
        return film_id;
    }

    public void setFilm_id(int film_id) {
        this.film_id = film_id;
    }

    public String getVizyondan_kalkis_tarihi() {
        return vizyondan_kalkis_tarihi;
    }

    public void setVizyondan_kalkis_tarihi(String vizyondan_kalkis_tarihi) {
        this.vizyondan_kalkis_tarihi = vizyondan_kalkis_tarihi;
    }

    public int getKullanici_puani() {
        return kullanici_puani;
    }

    public void setKullanici_puani(int kullanici_puani) {
        this.kullanici_puani = kullanici_puani;
    }

    public int getSeans_sayisi() {
        return seans_sayisi;
    }

    public void setSeans_sayisi(int seans_sayisi) {
        this.seans_sayisi = seans_sayisi;
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

    public Button getFilm_detayi() {
        return film_detayi;
    }

    public void setFilm_detayi(Button film_detayi) {
        this.film_detayi = film_detayi;
    }


}
