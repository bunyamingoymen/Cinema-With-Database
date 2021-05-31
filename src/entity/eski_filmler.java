package entity;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class eski_filmler extends filmler {

    private int eski_film_id;

    private int hangi_aboneler_izleyebilir;
    private int aldigi_odul_sayisi;

    private Button detay;

    public eski_filmler() {
    }

    public eski_filmler(int eski_film_id, int film_id, int hangi_aboneler_izleyebilir, int aldigi_odul_sayisi) {
        super(film_id);
        this.eski_film_id = eski_film_id;
        this.hangi_aboneler_izleyebilir = hangi_aboneler_izleyebilir;
        this.aldigi_odul_sayisi = aldigi_odul_sayisi;
    }

    public eski_filmler(int eski_film_id, int hangi_aboneler_izleyebilir, int aldigi_odul_sayisi) {
        this.eski_film_id = eski_film_id;
        this.hangi_aboneler_izleyebilir = hangi_aboneler_izleyebilir;
        this.aldigi_odul_sayisi = aldigi_odul_sayisi;
    }

    public eski_filmler(int film_id, int hangi_aboneler_izleyebilir, int aldigi_odul_sayisi, float random_float) {
        super(film_id);
        this.hangi_aboneler_izleyebilir = hangi_aboneler_izleyebilir;
        this.aldigi_odul_sayisi = aldigi_odul_sayisi;
    }

    public eski_filmler(int film_id, int eski_film_id, int yonetmen_id, String film_name, String film_type, int film_suresi, String ad, String soyad, int hangi_aboneler_izleyebilir, int aldigi_odul_sayisi, float kullanici_puani) {
        super(film_id, film_name, film_type, film_suresi, yonetmen_id, kullanici_puani, ad + " " + soyad);
        this.eski_film_id = eski_film_id;
        this.hangi_aboneler_izleyebilir = hangi_aboneler_izleyebilir;
        this.aldigi_odul_sayisi = aldigi_odul_sayisi;
    }

    public eski_filmler(int eski_film_id, int film_id, String film_name, String film_type, int film_suresi, int aldigi_odul, int hangi_abone, int yonetmen_id) {
        super(film_id, film_name, film_suresi, film_type, yonetmen_id);
        this.eski_film_id = eski_film_id;
        this.aldigi_odul_sayisi = aldigi_odul;
        this.hangi_aboneler_izleyebilir = hangi_abone;
    }

    public eski_filmler(String film_name, String film_type, int film_suresi, String ad_soyad, int aldigi_odul_sayisi, Button detay) {
        super(film_name, film_suresi, film_type, ad_soyad);
        this.aldigi_odul_sayisi = aldigi_odul_sayisi;
        this.detay = detay;

        detay.setOnAction(e -> {
            System.out.println("1");
        });
    }

    public eski_filmler(int eski_film_id, int film_id, String film_name, String film_type, int film_suresi, String ad_soyad, float kullanici_puani, int aldigi_odul, int hangi_abone, Button detay, Label film_detay_film_id, Label film_detay_film_adi, Label film_detay_film_turu, Label film_detay_film_suresi, Label film_detay_yonetmen, Label film_detay_kalkis_tarihi, Label film_detay_kullanici_puani, AnchorPane pnl_eski_filmler, AnchorPane pnl_vizyondaki_filmler, AnchorPane pnl_film_detayi, Label film_detay_aldigi_odul_sayisi, Label film_detay_hangi_abone_turu, Label film_detay_kalksi_tarihi_oncesi, Label film_detay_aldigi_odul_sayisi_oncesi, Label film_detay_hangi_abone_turu_oncesi) {
        super(film_id, film_name, film_type, film_suresi, ad_soyad, kullanici_puani);
        this.eski_film_id = eski_film_id;
        this.aldigi_odul_sayisi = aldigi_odul;
        this.hangi_aboneler_izleyebilir = hangi_abone;
        eski_filmler_controller(detay, film_detay_film_id, film_detay_film_adi, film_detay_film_turu, film_detay_film_suresi, film_detay_yonetmen, film_detay_kalkis_tarihi, film_detay_kullanici_puani, pnl_vizyondaki_filmler, pnl_eski_filmler, pnl_film_detayi, film_detay_aldigi_odul_sayisi, film_detay_hangi_abone_turu, film_detay_kalksi_tarihi_oncesi, film_detay_aldigi_odul_sayisi_oncesi, film_detay_hangi_abone_turu_oncesi);
    }

    public void eski_filmler_controller(Button detay, Label film_detay_film_id, Label film_detay_film_adi, Label film_detay_film_turu, Label film_detay_film_suresi, Label film_detay_yonetmen, Label film_detay_kalkis_tarihi, Label film_detay_kullanici_puani, AnchorPane pnl_vizyondaki_filmler, AnchorPane pnl_eski_filmler, AnchorPane pnl_film_detay, Label film_detay_aldigi_odul_sayisi, Label film_detay_hangi_abone_turu, Label film_detay_kalksi_tarihi_oncesi, Label film_detay_aldigi_odul_sayisi_oncesi, Label film_detay_hangi_abone_turu_oncesi) {
        this.detay = detay;

        detay.setOnAction(e -> {
            pnl_film_detay.setVisible(true);

            pnl_vizyondaki_filmler.setVisible(false);
            pnl_eski_filmler.setVisible(false);

            film_detay_kalkis_tarihi.setVisible(false);
            film_detay_aldigi_odul_sayisi.setVisible(true);
            film_detay_hangi_abone_turu.setVisible(true);

            film_detay_kalksi_tarihi_oncesi.setVisible(false);
            film_detay_aldigi_odul_sayisi_oncesi.setVisible(true);
            film_detay_hangi_abone_turu_oncesi.setVisible(true);

            film_detay_film_id.setText(String.valueOf(super.getFilm_id()));
            film_detay_film_adi.setText(super.getFilm_name());
            film_detay_film_turu.setText(super.getFilm_type());
            film_detay_film_suresi.setText(String.valueOf(super.getFilm_suresi()));
            film_detay_yonetmen.setText(super.getYonetmen_ad_soyad());
            film_detay_kullanici_puani.setText(String.valueOf(super.getKullanici_puani()));
            film_detay_aldigi_odul_sayisi.setText(String.valueOf(getAldigi_odul_sayisi()));
            film_detay_hangi_abone_turu.setText(String.valueOf(getHangi_aboneler_izleyebilir()));

        });
    }

    public int getEski_film_id() {
        return eski_film_id;
    }

    public void setEski_film_id(int eski_film_id) {
        this.eski_film_id = eski_film_id;
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

    public Button getDetay() {
        return detay;
    }

    public void setDetay(Button detay) {
        this.detay = detay;
    }

}
