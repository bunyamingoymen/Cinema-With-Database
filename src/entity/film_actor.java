package entity;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class film_actor {

    private int film_actor_id;
    private int film_id;
    private int actor_id;
    private String film_name;
    private String film_type;
    private String ad_soyad;
    private Button sil;

    public film_actor(Label film_actor_id_label, Pane film_actor_sil_emin_misin_pane) {
        sil.setOnAction(e -> {
            film_actor_id_label.setText(String.valueOf(this.film_actor_id));
            film_actor_sil_emin_misin_pane.setVisible(true);
        });
    }

    public film_actor(int film_actor_id, int film_id, int actor_id) {
        this.film_actor_id = film_actor_id;
        this.film_id = film_id;
        this.actor_id = actor_id;
    }

    public film_actor(int film_actor_id, String film_name, String film_type, String ad_soyad) {
        this.film_actor_id = film_actor_id;
        this.film_name = film_name;
        this.film_type = film_type;
        this.ad_soyad = ad_soyad;
    }

    public film_actor(int film_actor_id, int film_id, int actor_id, String film_name, String film_type, String ad_soyad, Button sil, Pane film_actor_sil_emin_misin_pane, Label film_actor_id_label) {
        this.film_actor_id = film_actor_id;
        this.film_id = film_id;
        this.actor_id = actor_id;
        this.film_name = film_name;
        this.film_type = film_type;
        this.ad_soyad = ad_soyad;
        this.sil = sil;

        sil.setOnAction(e -> {
            film_actor_id_label.setText(String.valueOf(this.film_actor_id));
            film_actor_sil_emin_misin_pane.setVisible(true);
        });
    }

    public film_actor(int film_id, int actor_id) {
        this.film_id = film_id;
        this.actor_id = actor_id;
    }

    public int getFilm_actor_id() {
        return film_actor_id;
    }

    public void setFilm_actor_id(int film_actor_id) {
        this.film_actor_id = film_actor_id;
    }

    public int getFilm_id() {
        return film_id;
    }

    public void setFilm_id(int film_id) {
        this.film_id = film_id;
    }

    public int getActor_id() {
        return actor_id;
    }

    public void setActor_id(int actor_id) {
        this.actor_id = actor_id;
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

    public String getAd_soyad() {
        return ad_soyad;
    }

    public void setAd_soyad(String ad_soyad) {
        this.ad_soyad = ad_soyad;
    }

    public Button getSil() {
        return sil;
    }

    public void setSil(Button sil) {
        this.sil = sil;
    }

}
