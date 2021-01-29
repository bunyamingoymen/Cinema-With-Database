/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entity.satin_alinan_biletler;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DBConnector;

/**
 *
 * @author bgoymen
 */
public class satin_alinan_biletlerDAO {

    public String[] satin_alinan_biletler_satyin_alinanlari_gonder(int seans_id) {

        String arr[] = new String[kac_tane_satin_alinan_bilet_var(seans_id)];

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select * from satin alinan biletler where seans_id = " + String.valueOf(seans_id);
            ResultSet rs = st.executeQuery(komut);
            int i = 0;
            while (rs.next()) {
                String koltuk_name = rs.getString("koltuk_name");
                arr[i] = koltuk_name;
                i++;
            }

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 211 - " + e.getMessage());
        }

        return arr;
    }

    public ObservableList<satin_alinan_biletler> satin_alinan_biletler_kullanicinin_biletlerini_goster(ObservableList<satin_alinan_biletler> data, int user_id) {

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select filmler.film_name, sinema_salonlari.salon_name, seans.saat, yonetmenler.ad, "
                    + "yonetmenler.soyad, satin_alinan_biletler.koltuk_name from satin_alinan_biletler inner join seans on satin_alinan_biletler.seans_id = seans.seans_id inner join  "
                    + "vizyondaki_filmler on seans.vizyondaki_film_id = vizyondaki_filmler.vizyondaki_film_id inner join filmler on vizyondaki_filmler.film_id = "
                    + "filmler.film_id inner join sinema_Salonlari on sinema_salonlari.salon_id = seans.salon_id inner join yonetmenler on filmler.yonetmen_id = "
                    + "yonetmenler.yonetmen_id where satin_alinan_biletler.user_id = " + user_id;
            ResultSet rs = st.executeQuery(komut);
            int i = 0;
            while (rs.next()) {
                String film_name = rs.getString("film_name");
                String salon_name = rs.getString("salon_name");
                String saat = rs.getString("saat");
                String ad = rs.getString("ad");
                String soyad = rs.getString("soyad");
                String koltuk_name = rs.getString("koltuk_name");
                
                data.addAll(FXCollections.observableArrayList(new satin_alinan_biletler(film_name, salon_name, saat, ad,soyad, koltuk_name)));
            }

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 227 - " + e.getMessage());
        }

        return data;
    }

    public int kac_tane_satin_alinan_bilet_var(int seans_id) {
        int sonuc = -1;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select count (satin_alinan_bilet_id) from satin_alinan_biletler where seans_id = " + seans_id;
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            sonuc = rs.getInt("count");

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 212 - " + e.getMessage());
        }

        return sonuc;
    }
}
