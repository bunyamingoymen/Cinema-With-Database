package DAO;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entity.actor;
import entity.film_actor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import util.DBConnector;

public class actorDAO {

    public ObservableList<actor> aktorler_select(ObservableList<actor> data, Label lab, Label lab2, Pane pan, Label lab3, GridPane aktorler_grid, GridPane film_actor_grid, FontAwesomeIconView aktorler_geri_tusu, FontAwesomeIconView film_actor_geri_tusu, TableColumn<film_actor, String> film_actor_film_name, TableColumn<film_actor, String> film_actor_film_type, TableColumn<film_actor, String> film_actor_yonetmen, TableColumn<film_actor, Button> film_actor_sil, TableView<film_actor> table_film_actor, TextField filterField_film_actor, Pane film_actor_pane, Pane film_actor_ekle_pane, Pane film_actor_sil_emin_misin_pane, Label film_actor_id_label) {
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select * from actor";
            ResultSet rs = st.executeQuery(komut);

            while (rs.next()) {
                int actor_id = rs.getInt("actor_id");
                String ad = rs.getString("ad");
                String soyad = rs.getString("soyad");

                Button guncelle = new Button();
                guncelle.setText("Güncelle");
                guncelle.setStyle("-fx-background-color : #393351; -fx-background-radius :  20; -fx-text-fill: white");

                Button filmleri_goster = new Button();
                filmleri_goster.setText("Filmleri Göster");
                filmleri_goster.setStyle("-fx-background-color : #393351; -fx-background-radius :  20; -fx-text-fill: white");

                Button sil = new Button();
                sil.setText("Sil");
                sil.setStyle("-fx-background-color : #FA2C56; -fx-background-radius :  20; -fx-text-fill: white");

                data.addAll(FXCollections.observableArrayList(new actor(actor_id, ad, soyad, guncelle, sil, filmleri_goster, lab, lab2, pan, lab3, aktorler_grid, film_actor_grid, aktorler_geri_tusu, film_actor_geri_tusu, film_actor_film_name, film_actor_film_type, film_actor_yonetmen, film_actor_sil, table_film_actor, filterField_film_actor, film_actor_pane, film_actor_ekle_pane, film_actor_sil_emin_misin_pane, film_actor_id_label)));
            }
            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 104 - " + e.getMessage());
        }

        return data;
    }

    public int aktorler_degistir(actor a) {
        int sonuc = 0;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "update actor set ad = '" + a.getAd() + "' , soyad = '" + a.getSoyad() + "' where actor_id =" + a.getActor_id();
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu :105 - " + e.getMessage());
        }
        return sonuc;
    }

    public int aktorler_sil(int id) {
        int sonuc = 0;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "delete from actor where actor_id = " + id;
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu :106 - " + e.getMessage());
        }
        return sonuc;
    }

    public int aktorler_ekle(actor a) {
        int sonuc = 0;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "insert into actor (ad,soyad) values ('" + a.getAd() + "','" + a.getSoyad() + "')";
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();
        } catch (SQLException e) {
            System.out.println("Hata Kodu: 107 - " + e.getMessage());
        }

        return sonuc;
    }

    public int kac_tane_actor_var() {
        int sonuc = -1;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select count (actor_id) from actor";
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            sonuc = rs.getInt("count");

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 108 - " + e.getMessage());
        }

        return sonuc;
    }

}
