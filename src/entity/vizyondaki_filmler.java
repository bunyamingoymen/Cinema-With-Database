package entity;

import java.time.LocalDate;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class vizyondaki_filmler extends filmler {

    private int vizyondaki_filmler_id;

    private LocalDate vizyondan_kalkis_tarihi;
    private int seans_sayisi;
    private Button film_detayi;

    public vizyondaki_filmler(int film_id, LocalDate vizyondan_kalkis_tarihi, float kullanici_puani, int seans_sayisi) {
        super(film_id, kullanici_puani);
        this.vizyondan_kalkis_tarihi = vizyondan_kalkis_tarihi;
        this.seans_sayisi = seans_sayisi;
    }

    public vizyondaki_filmler(int vizyondaki_filmler_id, int film_id, LocalDate vizyondan_kalkis_tarihi, float kullanici_puani, int seans_sayisi) {
        super(film_id, kullanici_puani);
        this.vizyondaki_filmler_id = vizyondaki_filmler_id;
        this.vizyondan_kalkis_tarihi = vizyondan_kalkis_tarihi;
        this.seans_sayisi = seans_sayisi;
    }

    public vizyondaki_filmler(String film_name, String film_type, int film_suresi, String yonetmen_ad, String yonetmen_soyad, LocalDate vizyondan_kalkis_tarihi, float kullanici_puani, Button detay) {
        super(film_name, film_type, film_suresi, yonetmen_ad + " " + yonetmen_soyad, kullanici_puani);
        this.vizyondan_kalkis_tarihi = vizyondan_kalkis_tarihi;
        this.film_detayi = detay;

        detay.setOnAction(e -> {
            System.out.println("1");
        });

    }

    public vizyondaki_filmler(int vizyondaki_filmler_id, int film_id, String film_name, String film_type, int film_suresi, String ad, String soyad, LocalDate vizyondan_kalkis_tarihi, float kullanici_puani, Button detay, Label film_detay_film_id, Label film_detay_film_adi, Label film_detay_film_turu, Label film_detay_film_suresi, Label film_detay_yonetmen, Label film_detay_kalkis_tarihi, Label film_detay_kullanici_puani, AnchorPane pnl_vizyondaki_filmler, AnchorPane pnl_eski_filmler, AnchorPane pnl_film_detayi) {
        super(film_id, film_name, film_type, film_suresi, ad + " " + soyad, kullanici_puani);
        this.vizyondaki_filmler_id = vizyondaki_filmler_id;
        this.vizyondan_kalkis_tarihi = vizyondan_kalkis_tarihi;
        this.film_detayi = detay;

        detay.setOnAction(e -> {
            pnl_film_detayi.setVisible(true);

            pnl_vizyondaki_filmler.setVisible(false);
            pnl_eski_filmler.setVisible(false);

            film_detay_film_id.setText(String.valueOf(film_id));
            film_detay_film_adi.setText(film_name);
            film_detay_film_turu.setText(film_type);
            film_detay_film_suresi.setText(String.valueOf(film_suresi));
            film_detay_yonetmen.setText(ad + " " + soyad);
            film_detay_kalkis_tarihi.setText(String.valueOf(vizyondan_kalkis_tarihi));
            film_detay_kullanici_puani.setText(String.valueOf(kullanici_puani));

        });
    }

    public vizyondaki_filmler(int film_id, LocalDate vizyondan_kalkis_tarihi, int seans_sayisi) {
        super(film_id);
        this.vizyondan_kalkis_tarihi = vizyondan_kalkis_tarihi;
        this.seans_sayisi = seans_sayisi;
    }

    public int getVizyondaki_filmler_id() {
        return vizyondaki_filmler_id;
    }

    public void setVizyondaki_filmler_id(int vizyondaki_filmler_id) {
        this.vizyondaki_filmler_id = vizyondaki_filmler_id;
    }

    public LocalDate getVizyondan_kalkis_tarihi() {
        return vizyondan_kalkis_tarihi;
    }

    public void setVizyondan_kalkis_tarihi(LocalDate vizyondan_kalkis_tarihi) {
        this.vizyondan_kalkis_tarihi = vizyondan_kalkis_tarihi;
    }

    public int getSeans_sayisi() {
        return seans_sayisi;
    }

    public void setSeans_sayisi(int seans_sayisi) {
        this.seans_sayisi = seans_sayisi;
    }

    public Button getFilm_detayi() {
        return film_detayi;
    }

    public void setFilm_detayi(Button film_detayi) {
        this.film_detayi = film_detayi;
    }

}
