package DAO;

import entity.satin_alinan_biletler;
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
import util.DBConnector;

public class satin_alinan_biletlerDAO {

    public String[] satin_alinan_biletler_satyin_alinanlari_gonder(int seans_id) {

        String arr[] = new String[kac_tane_satin_alinan_bilet_var(seans_id)];

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select * from satin_alinan_biletler where seans_id = " + String.valueOf(seans_id);
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

    public ObservableList<satin_alinan_biletler> satin_alinan_biletler_kullanicinin_biletlerini_goster(ObservableList<satin_alinan_biletler> data, int user_id, TableColumn<satin_alinan_biletler, String> biletlerim_film_adi, TableColumn<satin_alinan_biletler, String> biletlerim_salon_adi, TableColumn<satin_alinan_biletler, String> biletlerim_yonetmen, TableColumn<satin_alinan_biletler, String> biletlerim_saat, TableColumn<satin_alinan_biletler, String> biletlerim_koltuk, TableColumn<satin_alinan_biletler, Button> biletlerim_sil, TextField filterField_biletlerim, Label biletlerim_uyari_mesaj, TableView<satin_alinan_biletler> table_biletlerim) {

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select satin_alinan_biletler.satin_alinan_bilet_id, satin_alinan_biletler.user_id ,filmler.film_name, sinema_salonlari.salon_name, seans.saat, yonetmenler.ad, "
                    + "yonetmenler.soyad, satin_alinan_biletler.koltuk_name from satin_alinan_biletler inner join seans on satin_alinan_biletler.seans_id = seans.seans_id inner join  "
                    + "vizyondaki_filmler on seans.vizyondaki_film_id = vizyondaki_filmler.vizyondaki_film_id inner join filmler on vizyondaki_filmler.film_id = "
                    + "filmler.film_id inner join sinema_Salonlari on sinema_salonlari.salon_id = seans.salon_id inner join yonetmenler on filmler.yonetmen_id = "
                    + "yonetmenler.yonetmen_id where satin_alinan_biletler.user_id = " + user_id;
            ResultSet rs = st.executeQuery(komut);
            int i = 0;
            while (rs.next()) {
                int satin_alinan_bilet_id = rs.getInt("satin_alinan_bilet_id");
                String film_name = rs.getString("film_name");
                String salon_name = rs.getString("salon_name");
                String saat = rs.getString("saat");
                String ad = rs.getString("ad");
                String soyad = rs.getString("soyad");
                String koltuk_name = rs.getString("koltuk_name");

                Button sil = new Button();
                sil.setText("Sil");
                sil.setStyle("-fx-background-color : #FA2C56; -fx-background-radius :  20; -fx-text-fill: white");

                data.addAll(FXCollections.observableArrayList(new satin_alinan_biletler(user_id, satin_alinan_bilet_id, film_name, salon_name, saat, ad, soyad, koltuk_name, sil, biletlerim_film_adi, biletlerim_salon_adi, biletlerim_yonetmen, biletlerim_saat, biletlerim_koltuk, biletlerim_sil, filterField_biletlerim, biletlerim_uyari_mesaj, table_biletlerim)));
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

    public int satin_alinan_bilet_satin_al(int user_id, int seans_id) {
        yesil_olanDAO ydao = new yesil_olanDAO();
        String arr[] = ydao.yesil_olanlar_toplu_gonder();

        for (int i = 0; i < arr.length; i++) {
            int sonuc = satin_alinan_biletler_ekle(user_id, seans_id, arr[i]);
            if (sonuc == 0) {
                System.out.println("Hata kodu : 231 - ");
                return 0;
            }
        }

        return 1;
    }

    public int satin_alinan_biletler_ekle(int user_id, int seans_id, String koltuk_adi) {
        int sonuc = 0;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "insert into satin_alinan_biletler (user_id, seans_id, koltuk_name) values (" + user_id + "," + seans_id + ",'" + koltuk_adi + "')";
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu :230 - " + e.getMessage());
        }

        return sonuc;
    }

    public int satin_alinan_biletler_sil(int satin_alinan_bilet_id) {
        int sonuc = 0;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "delete from satin_alinan_biletler where satin_alinan_bilet_id = " + satin_alinan_bilet_id;
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu :232 - " + e.getMessage());
        }
        return sonuc;
    }
}
