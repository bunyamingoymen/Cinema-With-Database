/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entity.film_actor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import util.DBConnector;

/**
 *
 * @author bgoymen
 */
public class film_actorDAO {

    public ObservableList<film_actor> film_actor_select(ObservableList<film_actor> data, int actor_id, Pane film_actor_sil_emin_misin_pane, Label film_actor_id_label) {
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select film_actor.film_actor_id, film_actor.film_id, filmler.film_name, filmler.film_type, yonetmenler.ad, "
                    + "yonetmenler.soyad from film_actor inner join filmler on film_actor.film_id = filmler.film_id inner join "
                    + "yonetmenler on filmler.yonetmen_id = yonetmenler.yonetmen_id inner join actor on film_actor.actor_id = "
                    + "actor.actor_id where actor.actor_id = " + actor_id;
            ResultSet rs = st.executeQuery(komut);

            while (rs.next()) {
                int film_actor_id = rs.getInt("film_actor_id");
                int film_id = rs.getInt("film_id");
                String film_name = rs.getString("film_name");
                String film_type = rs.getString("film_type");
                String ad = rs.getString("ad");
                String soyad = rs.getString("soyad");
                String ad_soyad = ad + " " + soyad;

                Button sil = new Button();
                sil.setText("Sil");
                sil.setStyle("-fx-background-color : #FA2C56; -fx-background-radius :  20; -fx-text-fill: white");

                data.addAll(FXCollections.observableArrayList(new film_actor(film_actor_id, film_id, actor_id, film_name, film_type, ad_soyad, sil, film_actor_sil_emin_misin_pane, film_actor_id_label)));
            }
            
            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 125 - " + e.getMessage());
        }

        return data;
    }

    public int film_actor_delete(int id) {
        int sonuc = 0;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "delete from film_actor where film_actor_id = " + id;
            sonuc = st.executeUpdate(komut);
            
            c.close();
            st.close();
        } catch (SQLException e) {
            System.out.println("Hata Kodu: 126 - " + e.getMessage());
        }
        return sonuc;
    }

    public int film_actor_insert(film_actor fa) {
        int sonuc = 0;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "insert into film_actor (film_id,actor_id) values ('"+fa.getFilm_id() +"','"+fa.getActor_id()+"')";
            sonuc = st.executeUpdate(komut);
            
            c.close();
            st.close();
        } catch (SQLException e) {
            System.out.println("Hata Kodu: 127 - " + e.getMessage());
        }
        return sonuc;
    }
}
