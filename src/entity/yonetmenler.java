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
public class yonetmenler {
    
    private int yonetmen_id;
    private String yonetmen_name;
    private String yonetmen_sur_name;
    private int film_sayisi;

    public yonetmenler(String yonetmen_name, String yonetmen_sur_name, int film_sayisi) {
        this.yonetmen_name = yonetmen_name;
        this.yonetmen_sur_name = yonetmen_sur_name;
        this.film_sayisi = film_sayisi;
    }

    public yonetmenler(int yonetmen_id, String yonetmen_name, String yonetmen_sur_name, int film_sayisi) {
        this.yonetmen_id = yonetmen_id;
        this.yonetmen_name = yonetmen_name;
        this.yonetmen_sur_name = yonetmen_sur_name;
        this.film_sayisi = film_sayisi;
    }

    public yonetmenler() {
    }

    public int getYonetmen_id() {
        return yonetmen_id;
    }

    public void setYonetmen_id(int yonetmen_id) {
        this.yonetmen_id = yonetmen_id;
    }

    public String getYonetmen_name() {
        return yonetmen_name;
    }

    public void setYonetmen_name(String yonetmen_name) {
        this.yonetmen_name = yonetmen_name;
    }

    public String getYonetmen_sur_name() {
        return yonetmen_sur_name;
    }

    public void setYonetmen_sur_name(String yonetmen_sur_name) {
        this.yonetmen_sur_name = yonetmen_sur_name;
    }

    public int getFilm_sayisi() {
        return film_sayisi;
    }

    public void setFilm_sayisi(int film_sayisi) {
        this.film_sayisi = film_sayisi;
    }
    
    
}
