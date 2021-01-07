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
public class filmler {
    
    private int film_id;
    private String film_name;
    private int film_suresi;
    private String film_type;
    private int yonetmen_id;

    public filmler(String film_name, int film_suresi, String film_type, int yonetmen_id) {
        this.film_name = film_name;
        this.film_suresi = film_suresi;
        this.film_type = film_type;
        this.yonetmen_id = yonetmen_id;
    }

    public filmler(int film_id, String film_name, int film_suresi, String film_type, int yonetmen_id) {
        this.film_id = film_id;
        this.film_name = film_name;
        this.film_suresi = film_suresi;
        this.film_type = film_type;
        this.yonetmen_id = yonetmen_id;
    }
    

    public filmler() {
    }

    public int getFilm_id() {
        return film_id;
    }

    public void setFilm_id(int film_id) {
        this.film_id = film_id;
    }

    public String getFilm_name() {
        return film_name;
    }

    public void setFilm_name(String film_name) {
        this.film_name = film_name;
    }

    public int getFilm_suresi() {
        return film_suresi;
    }

    public void setFilm_suresi(int film_suresi) {
        this.film_suresi = film_suresi;
    }

    public String getFilm_type() {
        return film_type;
    }

    public void setFilm_type(String film_type) {
        this.film_type = film_type;
    }

    public int getYonetmen_id() {
        return yonetmen_id;
    }

    public void setYonetmen_id(int yonetmen_id) {
        this.yonetmen_id = yonetmen_id;
    }
    
    
}
