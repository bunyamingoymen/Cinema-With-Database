package entity;

import java.time.LocalDate;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class vizyondaki_filmler {

    private int vizyondaki_filmler_id;
    private int film_id;
    private String film_name;
    private String film_type;
    private int film_suresi;
    private String yonetmen_ad_soyad;
    private LocalDate vizyondan_kalkis_tarihi;
    private float kullanici_puani;
    private int seans_sayisi;
    private Button film_detayi;

    public vizyondaki_filmler(int film_id, LocalDate vizyondan_kalkis_tarihi, float kullanici_puani, int seans_sayisi) {
        this.film_id = film_id;
        this.vizyondan_kalkis_tarihi = vizyondan_kalkis_tarihi;
        this.kullanici_puani = kullanici_puani;
        this.seans_sayisi = seans_sayisi;
    }

    public vizyondaki_filmler(int vizyondaki_filmler_id, int film_id, LocalDate vizyondan_kalkis_tarihi, float kullanici_puani, int seans_sayisi) {
        this.vizyondaki_filmler_id = vizyondaki_filmler_id;
        this.film_id = film_id;
        this.vizyondan_kalkis_tarihi = vizyondan_kalkis_tarihi;
        this.kullanici_puani = kullanici_puani;
        this.seans_sayisi = seans_sayisi;
    }

    public vizyondaki_filmler(String film_name, String film_type, int film_suresi, String yonetmen_ad, String yonetmen_soyad, LocalDate vizyondan_kalkis_tarihi, float kullanici_puani, Button detay) {
        this.film_name = film_name;
        this.film_type = film_type;
        this.film_suresi = film_suresi;
        this.yonetmen_ad_soyad = yonetmen_ad + " " + yonetmen_soyad;
        this.vizyondan_kalkis_tarihi = vizyondan_kalkis_tarihi;
        this.kullanici_puani = kullanici_puani;
        this.film_detayi = detay;

        detay.setOnAction(e -> {
            System.out.println("1");
        });

    }

    public vizyondaki_filmler(int vizyondaki_filmler_id, int film_id, String film_name, String film_type, int film_suresi, String ad, String soyad, LocalDate vizyondan_kalkis_tarihi, float kullanici_puani, Button detay, Label film_detay_film_id, Label film_detay_film_adi, Label film_detay_film_turu, Label film_detay_film_suresi, Label film_detay_yonetmen, Label film_detay_kalkis_tarihi, Label film_detay_kullanici_puani ,AnchorPane pnl_vizyondaki_filmler, AnchorPane pnl_eski_filmler, AnchorPane pnl_film_detayi) {
        this.vizyondaki_filmler_id = vizyondaki_filmler_id;
        this.film_id = film_id;
        this.film_name = film_name;
        this.film_type = film_type;
        this.film_suresi = film_suresi;
        this.yonetmen_ad_soyad = ad + " " + soyad;
        this.vizyondan_kalkis_tarihi = vizyondan_kalkis_tarihi;
        this.kullanici_puani = kullanici_puani;
        this.film_detayi = detay;

        detay.setOnAction(e -> {
            pnl_film_detayi.setVisible(true);
            
            pnl_vizyondaki_filmler.setVisible(false);
            pnl_eski_filmler.setVisible(false);
            
            film_detay_film_id.setText(String.valueOf(this.film_id));
            film_detay_film_adi.setText(this.film_name);
            film_detay_film_turu.setText(this.film_type);
            film_detay_film_suresi.setText(String.valueOf(this.film_suresi));
            film_detay_yonetmen.setText(this.yonetmen_ad_soyad);
            film_detay_kalkis_tarihi.setText(String.valueOf(this.vizyondan_kalkis_tarihi));
            film_detay_kullanici_puani.setText(String.valueOf(this.kullanici_puani));
            
        });
    }

    public vizyondaki_filmler(int film_id, LocalDate vizyondan_kalkis_tarihi, int seans_sayisi) {
        this.film_id = film_id;
        this.vizyondan_kalkis_tarihi = vizyondan_kalkis_tarihi;
        this.seans_sayisi = seans_sayisi;
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

    public LocalDate getVizyondan_kalkis_tarihi() {
        return vizyondan_kalkis_tarihi;
    }

    public void setVizyondan_kalkis_tarihi(LocalDate vizyondan_kalkis_tarihi) {
        this.vizyondan_kalkis_tarihi = vizyondan_kalkis_tarihi;
    }

    public float getKullanici_puani() {
        return kullanici_puani;
    }

    public void setKullanici_puani(float kullanici_puani) {
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
