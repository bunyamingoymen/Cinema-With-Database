/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author bgoym
 */
public class films_photos {

    private int film_photo_id;
    private int film_id;
    private String photo_name;
    private String photo_parent;
    private String photo_path;

    public films_photos() {
    }

    public films_photos(int film_photo_id, int film_id, String photo_name, String photo_parent, String photo_path) {
        this.film_photo_id = film_photo_id;
        this.film_id = film_id;
        this.photo_name = photo_name;
        this.photo_parent = photo_parent;
        this.photo_path = photo_path;
    }

    public films_photos(int film_id, String photo_name, String photo_parent, String photo_path) {
        this.film_id = film_id;
        this.photo_name = photo_name;
        this.photo_parent = photo_parent;
        this.photo_path = photo_path;
    }

    public int getFilm_photo_id() {
        return film_photo_id;
    }

    public void setFilm_photo_id(int film_photo_id) {
        this.film_photo_id = film_photo_id;
    }

    public int getFilm_id() {
        return film_id;
    }

    public void setFilm_id(int film_id) {
        this.film_id = film_id;
    }

    public String getPhoto_name() {
        return photo_name;
    }

    public void setPhoto_name(String photo_name) {
        this.photo_name = photo_name;
    }

    public String getPhoto_parent() {
        return photo_parent;
    }

    public void setPhoto_parent(String photo_parent) {
        this.photo_parent = photo_parent;
    }

    public String getPhoto_path() {
        return photo_path;
    }

    public void setPhoto_path(String photo_path) {
        this.photo_path = photo_path;
    }

}
