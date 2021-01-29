/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author bgoymen
 */
public class satin_alinan_biletler {

    private int satin_alinan_bilet_id;
    private int seans_id;
    private int user_id;
    private String koltuk_name;

    private String film_name;
    private String salon_name;
    private String saat;
    private String yonetmen_ad_soyad;

    public satin_alinan_biletler() {
    }

    public satin_alinan_biletler(int satin_alinan_bilet_id, int seans_id, int user_id, String koltuk_name) {
        this.satin_alinan_bilet_id = satin_alinan_bilet_id;
        this.seans_id = seans_id;
        this.user_id = user_id;
        this.koltuk_name = koltuk_name;
    }

    public satin_alinan_biletler(int seans_id, int user_id, String koltuk_name) {
        this.seans_id = seans_id;
        this.user_id = user_id;
        this.koltuk_name = koltuk_name;
    }

    public satin_alinan_biletler(String film_name, String salon_name, String saat, String ad, String soyad, String koltuk_name) {
        this.koltuk_name = koltuk_name;
        this.film_name = film_name;
        this.salon_name = salon_name;
        this.saat = saat;
        this.yonetmen_ad_soyad = ad + " " + soyad;
    }

    public int getSatin_alinan_bilet_id() {
        return satin_alinan_bilet_id;
    }

    public void setSatin_alinan_bilet_id(int satin_alinan_bilet_id) {
        this.satin_alinan_bilet_id = satin_alinan_bilet_id;
    }

    public int getSeans_id() {
        return seans_id;
    }

    public void setSeans_id(int seans_id) {
        this.seans_id = seans_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getKoltuk_name() {
        return koltuk_name;
    }

    public void setKoltuk_name(String koltuk_name) {
        this.koltuk_name = koltuk_name;
    }

    public String getFilm_name() {
        return film_name;
    }

    public void setFilm_name(String film_name) {
        this.film_name = film_name;
    }

    public String getSalon_name() {
        return salon_name;
    }

    public void setSalon_name(String salon_name) {
        this.salon_name = salon_name;
    }

    public String getSaat() {
        return saat;
    }

    public void setSaat(String saat) {
        this.saat = saat;
    }

    public String getYonetmen_ad_soyad() {
        return yonetmen_ad_soyad;
    }

    public void setYonetmen_ad_soyad(String yonetmen_ad_soyad) {
        this.yonetmen_ad_soyad = yonetmen_ad_soyad;
    }

}
