package entity;

import javafx.scene.control.Button;

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

    public eski_filmler(String film_name, String film_type, int film_suresi, String yonetmen_ad_soyad, int hangi_aboneler_izleyebilir, int aldigi_odul_sayisi, float kullanici_puani, Button detay) {
        super(film_name, film_type, film_suresi, yonetmen_ad_soyad, kullanici_puani);
        this.hangi_aboneler_izleyebilir = hangi_aboneler_izleyebilir;
        this.aldigi_odul_sayisi = aldigi_odul_sayisi;
        this.detay = detay;

        detay.setOnAction(e -> {
            System.out.println("1");
        });

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
