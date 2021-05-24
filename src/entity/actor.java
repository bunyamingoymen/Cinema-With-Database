package entity;

import Controller.appController;
import DAO.actorDAO;
import DAO.film_actorDAO;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class actor {

    private int actor_id;
    private String ad;
    private String soyad;
    private Button guncelle;
    private Button sil;
    private Button filmleri_goster;

    public actor() {
    }

    public actor(int actor_id, String ad, String soyad, Button guncelle, Button sil, Button filmleri_goster, Label lab, Label lab2, Pane pan, Label lab3, GridPane aktorler_grid, GridPane film_actor_grid, FontAwesomeIconView aktorler_geri_tusu, FontAwesomeIconView film_actor_geri_tusu, TableColumn<film_actor, String> film_actor_film_name, TableColumn<film_actor, String> film_actor_film_type, TableColumn<film_actor, String> film_actor_yonetmen, TableColumn<film_actor, Button> film_actor_sil, TableView<film_actor> table_film_actor, TextField filterField_film_actor, Pane film_actor_pane, Pane film_actor_ekle_pane, Pane film_actor_sil_emin_misin_pane, Label film_actor_id_label) {
        this.actor_id = actor_id;
        this.ad = ad;
        this.soyad = soyad;

        actor_controller(guncelle, sil, filmleri_goster, lab, lab2, pan, lab3, aktorler_grid, film_actor_grid, aktorler_geri_tusu, film_actor_geri_tusu, film_actor_film_name, film_actor_film_type, film_actor_yonetmen, film_actor_sil, table_film_actor, filterField_film_actor, film_actor_pane, film_actor_ekle_pane, film_actor_sil_emin_misin_pane, film_actor_id_label);

    }

    public void actor_controller(Button guncelle, Button sil, Button filmleri_goster, Label lab, Label lab2, Pane pan, Label lab3, GridPane aktorler_grid, GridPane film_actor_grid, FontAwesomeIconView aktorler_geri_tusu, FontAwesomeIconView film_actor_geri_tusu, TableColumn<film_actor, String> film_actor_film_name, TableColumn<film_actor, String> film_actor_film_type, TableColumn<film_actor, String> film_actor_yonetmen, TableColumn<film_actor, Button> film_actor_sil, TableView<film_actor> table_film_actor, TextField filterField_film_actor, Pane film_actor_pane, Pane film_actor_ekle_pane, Pane film_actor_sil_emin_misin_pane, Label film_actor_id_label) {

        this.guncelle = guncelle;
        this.sil = sil;
        this.filmleri_goster = filmleri_goster;

        guncelle.setOnAction(e -> {
            Center nw = new Center(this.actor_id, this.ad, this.soyad);
            actorDAO adao = new actorDAO();
            adao.update(nw);

            lab.setText("İşlem Başarılı bir şekilde gerçekleşti.");
        });
        sil.setOnAction(r -> {

            lab2.setText(String.valueOf(this.actor_id));
            pan.setVisible(true);

        });
        filmleri_goster.setOnAction(t -> {
            appController ac = new appController();
            aktorler_grid.setVisible(false);
            film_actor_grid.setVisible(true);
            aktorler_geri_tusu.setVisible(false);
            film_actor_geri_tusu.setVisible(true);
            film_actor_pane.setVisible(true);
            lab3.setText(String.valueOf(actor_id));
            film_actor_ekle_pane.setVisible(false);
            film_actor_sil_emin_misin_pane.setVisible(false);

            film_actor_table(aktorler_grid, film_actor_grid, aktorler_geri_tusu, film_actor_geri_tusu, film_actor_film_name, film_actor_film_type, film_actor_yonetmen, film_actor_sil, table_film_actor, filterField_film_actor, film_actor_sil_emin_misin_pane, film_actor_id_label);

        });
    }

    public void film_actor_table(GridPane aktorler_grid, GridPane film_actor_grid, FontAwesomeIconView aktorler_geri_tusu, FontAwesomeIconView film_actor_geri_tusu, TableColumn<film_actor, String> film_actor_film_name, TableColumn<film_actor, String> film_actor_film_type, TableColumn<film_actor, String> film_actor_yonetmen, TableColumn<film_actor, Button> film_actor_sil, TableView<film_actor> table_film_actor, TextField filterField_film_actor, Pane film_actor_sil_emin_misin_pane, Label film_actor_id_label) {
        film_actor_film_name.setCellValueFactory(new PropertyValueFactory("film_name"));
        film_actor_film_type.setCellValueFactory(new PropertyValueFactory("film_type"));
        film_actor_yonetmen.setCellValueFactory(new PropertyValueFactory("ad_soyad"));
        film_actor_sil.setCellValueFactory(new PropertyValueFactory("sil"));

        film_actorDAO fdao = new film_actorDAO();
        ObservableList<film_actor> data = FXCollections.observableArrayList();
        data = fdao.select(data, actor_id, film_actor_sil_emin_misin_pane, film_actor_id_label);

        FilteredList<film_actor> filteredData = new FilteredList<>(data, b -> true);
        filterField_film_actor.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(fc -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (fc.getFilm_name().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (fc.getFilm_type().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (fc.getAd_soyad().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }

            });
        });

        SortedList<film_actor> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(table_film_actor.comparatorProperty());

        table_film_actor.setItems(sortedData);
    }

    public actor(int actor_id, String ad, String soyad) {
        this.actor_id = actor_id;
        this.ad = ad;
        this.soyad = soyad;
    }

    public actor(String ad, String soyad) {
        this.ad = ad;
        this.soyad = soyad;
    }

    public int getActor_id() {
        return actor_id;
    }

    public void setActor_id(int actor_id) {
        this.actor_id = actor_id;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public Button getGuncelle() {
        return guncelle;
    }

    public void setGuncelle(Button guncelle) {
        this.guncelle = guncelle;
    }

    public Button getSil() {
        return sil;
    }

    public void setSil(Button sil) {
        this.sil = sil;
    }

    public Button getFilmleri_goster() {
        return filmleri_goster;
    }

    public void setFilmleri_goster(Button filmleri_goster) {
        this.filmleri_goster = filmleri_goster;
    }

}
