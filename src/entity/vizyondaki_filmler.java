package entity;

import java.time.LocalDate;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class vizyondaki_filmler extends filmler {

    private int vizyondaki_filmler_id;

    private LocalDate vizyondan_kalkis_tarihi;
    private int seans_sayisi;
    private Button film_detayi;

    public vizyondaki_filmler() {
    }

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

    public vizyondaki_filmler(int vizyondaki_filmler_id, LocalDate vizyondan_kalkis_tarihi, int film_id, String film_name, String film_type, int film_suresi, String yonetmen_ad_soyad, float kullanici_puani, int yonetmen_id) {
        super( film_id, film_name, film_type, film_suresi, yonetmen_id, kullanici_puani, yonetmen_ad_soyad);
        this.vizyondaki_filmler_id = vizyondaki_filmler_id;
        this.vizyondan_kalkis_tarihi = vizyondan_kalkis_tarihi;
    }

    public vizyondaki_filmler(int vizyondaki_filmler_id, int film_id, String film_name, String film_type, int film_suresi, String ad_soyad, LocalDate vizyondan_kalkis_tarihi, float kullanici_puani, Button detay, Label film_detay_film_id, Label film_detay_film_adi, Label film_detay_film_turu, Label film_detay_film_suresi, Label film_detay_yonetmen, Label film_detay_kalkis_tarihi, Label film_detay_kullanici_puani, AnchorPane pnl_vizyondaki_filmler, AnchorPane pnl_eski_filmler, AnchorPane pnl_film_detayi, Label film_detay_aldigi_odul_sayisi, Label film_detay_hangi_abone_turu, Label film_detay_kalksi_tarihi_oncesi, Label film_detay_aldigi_odul_sayisi_oncesi, Label film_detay_hangi_abone_turu_oncesi, Label film_detay_id, Label film_detay_id_oncesi) {
        super(film_id, film_name, film_type, film_suresi, ad_soyad, kullanici_puani);
        this.vizyondaki_filmler_id = vizyondaki_filmler_id;
        this.vizyondan_kalkis_tarihi = vizyondan_kalkis_tarihi;
        
        vizyondaki_filmler_controller(detay, film_detay_film_id, film_detay_film_adi, film_detay_film_turu, film_detay_film_suresi, film_detay_yonetmen, film_detay_kalkis_tarihi, film_detay_kullanici_puani, pnl_vizyondaki_filmler, pnl_eski_filmler, pnl_film_detayi, film_detay_aldigi_odul_sayisi, film_detay_hangi_abone_turu, film_detay_kalksi_tarihi_oncesi, film_detay_aldigi_odul_sayisi_oncesi, film_detay_hangi_abone_turu_oncesi, film_detay_id, film_detay_id_oncesi);
    }

    public void vizyondaki_filmler_controller(Button detay, Label film_detay_film_id, Label film_detay_film_adi, Label film_detay_film_turu, Label film_detay_film_suresi, Label film_detay_yonetmen, Label film_detay_kalkis_tarihi, Label film_detay_kullanici_puani, AnchorPane pnl_vizyondaki_filmler, AnchorPane pnl_eski_filmler, AnchorPane pnl_film_detay, Label film_detay_aldigi_odul_sayisi, Label film_detay_hangi_abone_turu, Label film_detay_kalksi_tarihi_oncesi, Label film_detay_aldigi_odul_sayisi_oncesi, Label film_detay_hangi_abone_turu_oncesi, Label film_detay_id, Label film_detay_id_oncesi) {
        this.film_detayi = detay;

        detay.setOnAction(e -> {
            pnl_film_detay.setVisible(true);

            pnl_vizyondaki_filmler.setVisible(false);
            pnl_eski_filmler.setVisible(false);

            film_detay_kalkis_tarihi.setVisible(true);
            film_detay_aldigi_odul_sayisi.setVisible(false);
            film_detay_hangi_abone_turu.setVisible(false);

            film_detay_kalksi_tarihi_oncesi.setVisible(true);
            film_detay_aldigi_odul_sayisi_oncesi.setVisible(false);
            film_detay_hangi_abone_turu_oncesi.setVisible(false);

            film_detay_id_oncesi.setText("vizyon_id");

            film_detay_film_id.setText(String.valueOf(super.getFilm_id()));
            film_detay_film_adi.setText(super.getFilm_name());
            film_detay_film_turu.setText(super.getFilm_type());
            film_detay_film_suresi.setText(String.valueOf(super.getFilm_suresi()));
            film_detay_yonetmen.setText(super.getYonetmen_ad_soyad());
            film_detay_kalkis_tarihi.setText(String.valueOf(vizyondan_kalkis_tarihi));
            film_detay_kullanici_puani.setText(String.valueOf(super.getKullanici_puani()));
            film_detay_id.setText(String.valueOf(this.vizyondaki_filmler_id));
            
        });
    }

    public vizyondaki_filmler(int vizyondaki_filmler_id, int film_id, String film_name, String film_type, int film_suresi, String ad_soyad, LocalDate vizyondan_kalkis_tarihi, float kullanici_puani, Button detay, Label film_detay_film_id, Label film_detay_film_adi, Label film_detay_film_turu, Label film_detay_film_suresi, Label film_detay_yonetmen, Label film_detay_kalkis_tarihi, Label film_detay_kullanici_puani, AnchorPane pnl_vizyondaki_filmler, AnchorPane pnl_eski_filmler, AnchorPane pnl_film_detayi, Label film_detay_aldigi_odul_sayisi, Label film_detay_hangi_abone_turu, Label film_detay_kalksi_tarihi_oncesi, Label film_detay_aldigi_odul_sayisi_oncesi, Label film_detay_hangi_abone_turu_oncesi, Label film_detay_id, Label film_detay_id_oncesi, Pane film_detay_ana_pane, Pane film_detay_sil_emin_misin_pane, Pane film_detay_guncelle_pane) {
        super(film_id, film_name, film_type, film_suresi, ad_soyad, kullanici_puani);
        this.vizyondaki_filmler_id = vizyondaki_filmler_id;
        this.vizyondan_kalkis_tarihi = vizyondan_kalkis_tarihi;
        
        vizyondaki_filmler_controller(detay, film_detay_film_id, film_detay_film_adi, film_detay_film_turu, film_detay_film_suresi, film_detay_yonetmen, film_detay_kalkis_tarihi, film_detay_kullanici_puani, pnl_vizyondaki_filmler, pnl_eski_filmler, pnl_film_detayi, film_detay_aldigi_odul_sayisi, film_detay_hangi_abone_turu, film_detay_kalksi_tarihi_oncesi, film_detay_aldigi_odul_sayisi_oncesi, film_detay_hangi_abone_turu_oncesi, film_detay_id, film_detay_id_oncesi, film_detay_ana_pane, film_detay_sil_emin_misin_pane, film_detay_guncelle_pane);
    }

    public void vizyondaki_filmler_controller(Button detay, Label film_detay_film_id, Label film_detay_film_adi, Label film_detay_film_turu, Label film_detay_film_suresi, Label film_detay_yonetmen, Label film_detay_kalkis_tarihi, Label film_detay_kullanici_puani, AnchorPane pnl_vizyondaki_filmler, AnchorPane pnl_eski_filmler, AnchorPane pnl_film_detay, Label film_detay_aldigi_odul_sayisi, Label film_detay_hangi_abone_turu, Label film_detay_kalksi_tarihi_oncesi, Label film_detay_aldigi_odul_sayisi_oncesi, Label film_detay_hangi_abone_turu_oncesi, Label film_detay_id, Label film_detay_id_oncesi, Pane film_detay_ana_pane, Pane film_detay_sil_emin_misin_pane, Pane film_detay_guncelle_pane) {
        this.film_detayi = detay;

        detay.setOnAction(e -> {
            pnl_film_detay.setVisible(true);

            pnl_vizyondaki_filmler.setVisible(false);
            pnl_eski_filmler.setVisible(false);

            film_detay_kalkis_tarihi.setVisible(true);
            film_detay_aldigi_odul_sayisi.setVisible(false);
            film_detay_hangi_abone_turu.setVisible(false);

            film_detay_kalksi_tarihi_oncesi.setVisible(true);
            film_detay_aldigi_odul_sayisi_oncesi.setVisible(false);
            film_detay_hangi_abone_turu_oncesi.setVisible(false);

            film_detay_id_oncesi.setText("vizyon_id");

            film_detay_ana_pane.setVisible(true);
            film_detay_sil_emin_misin_pane.setVisible(false);
            film_detay_guncelle_pane.setVisible(false);

            film_detay_film_id.setText(String.valueOf(super.getFilm_id()));
            film_detay_film_adi.setText(super.getFilm_name());
            film_detay_film_turu.setText(super.getFilm_type());
            film_detay_film_suresi.setText(String.valueOf(super.getFilm_suresi()));
            film_detay_yonetmen.setText(super.getYonetmen_ad_soyad());
            film_detay_kalkis_tarihi.setText(String.valueOf(vizyondan_kalkis_tarihi));
            film_detay_kullanici_puani.setText(String.valueOf(super.getKullanici_puani()));
            film_detay_id.setText(String.valueOf(this.vizyondaki_filmler_id));
            
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

    @Override
    public String toString() {
        return (getVizyondaki_filmler_id() + " " + getVizyondan_kalkis_tarihi() + " " + super.getFilm_id() + " " + super.getFilm_name() + " " + super.getFilm_type() + " " + super.getFilm_suresi() + " " + super.getYonetmen_ad_soyad() + " " + super.getKullanici_puani());
    }

}
