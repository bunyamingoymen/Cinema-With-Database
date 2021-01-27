/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entity.haberler;
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
public class haberlerDAO {

    public ObservableList<haberler> haberler_select(ObservableList<haberler> data, Pane haberler_sil_emin_misin_pane, Label haberler_silmekten_emin_haber_id) {

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select * from haberler";
            ResultSet rs = st.executeQuery(komut);

            while (rs.next()) {
                int haber_id = rs.getInt("haber_id");
                int hangi_kullanıcı_turu = rs.getInt("hangi_kullanici_turu");
                String Title = rs.getString("Title");
                String Haber = rs.getString("Haber");
                String Tarih = rs.getString("Tarih");
                String Haber_Kategorisi = rs.getString("Haber_Kategorisi");
                Button sil = new Button();

                sil.setText("Sil");
                sil.setStyle("-fx-background-color : #FA2C56; -fx-background-radius :  20; -fx-text-fill: white");

                data.addAll(FXCollections.observableArrayList(new haberler(haber_id, hangi_kullanıcı_turu, Title, Haber, Tarih, Haber_Kategorisi, sil, haberler_silmekten_emin_haber_id, haberler_sil_emin_misin_pane)));
            }

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 136  " + e.getMessage());;
        }

        return data;
    }

    public ObservableList<haberler> haberler_select(ObservableList<haberler> data, int kullanici_turu) {

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select * from haberler";
            ResultSet rs = st.executeQuery(komut);

            while (rs.next()) {
                if (rs.getInt("hangi_kullanici_turu") <= kullanici_turu) {
                    String Title = rs.getString("Title");
                    String Haber = rs.getString("Haber");
                    String Tarih = rs.getString("Tarih");
                    String Haber_Kategorisi = rs.getString("Haber_Kategorisi");

                    data.addAll(FXCollections.observableArrayList(new haberler(Title, Haber, Tarih, Haber_Kategorisi)));
                }

            }

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 137  " + e.getMessage());;
        }

        return data;
    }

    public String[][] haberler_combo_doldur() {
        String[][] arr = new String[kac_tane_haber_var()][2];
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select * from haberler ";
            ResultSet rs = st.executeQuery(komut);
            int i = 0;

            while (rs.next()) {
                String haberler_combo = rs.getInt("haber_id") + " | " + rs.getString("Title") + " | " + rs.getString("Haber");
                int id = rs.getInt("haber_id");
                arr[i][0] = haberler_combo;
                arr[i][1] = String.valueOf(id);
                i++;
            }

            c.close();
            st.close();
            rs.close();

            return arr;

        } catch (SQLException e) {
            System.out.println("Hata kodu: 138  " + e.getMessage());;
        }

        return null;
    }

    public int kac_tane_haber_var() {
        int sonuc = -1;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select count (haber_id) from haberler ";
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            sonuc = rs.getInt("count");

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 139  " + e.getMessage());;
        }

        return sonuc;
    }

    public int haberler_dao_ekle(haberler h) {
        int sonuc = 0;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "insert into haberler (hangi_kullanici_turu, Title, Haber, Tarih, Haber_Kategorisi) values ('" + h.getHangi_kullanici_turu() + "','" + h.getTitle() + "','" + h.getHaber() + "','" + h.getTarih() + "','" + h.getHaber_Kategorisi() + "')";

            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu :140" + e.getMessage());;
        }

        return sonuc;
    }

    public int haberler_degistir(haberler h) {
        int sonuc = 0;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "update haberler set hangi_kullanici_turu = " + h.getHangi_kullanici_turu() + " , Title = '" + h.getTitle() + "', Haber = '" + h.getHaber() + "', Tarih = '" + h.getTarih() + "', Haber_Kategorisi = '" + h.getHaber_Kategorisi() + "' where haber_id =" + h.getHaber_id();
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();
        } catch (SQLException e) {
            System.out.println("Hata kodu :141" + e.getMessage());
        }
        return sonuc;

    }

    public int haberler_sil(int haber_id) {
        int sonuc = 0;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "delete from haberler where haber_id=" + haber_id;
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu :142" + e.getMessage());;
        }

        return sonuc;

    }

    public int haberler_hangi_kullanici_getir(int haber_id) {
        int hangi_kullanici = -1;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select *  from haberler where haber_id=" + haber_id;
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            hangi_kullanici = rs.getInt("hangi_kullanici_turu");

            c.close();
            st.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Hata kodu: 143  " + e.getMessage());;
        }

        return hangi_kullanici;
    }

    public String haberler_title_getir(int haber_id) {
        String title = null;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select *  from haberler where haber_id=" + haber_id;
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            title = rs.getString("Title");

            c.close();
            st.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Hata kodu: 144  " + e.getMessage());;
        }

        return title;
    }

    public String haberler_haber_getir(int haber_id) {
        String haber = null;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select *  from haberler where haber_id=" + haber_id;
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            haber = rs.getString("Haber");

            c.close();
            st.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Hata kodu: 145  " + e.getMessage());;
        }

        return haber;
    }

    public String haberler_tarih_getir(int haber_id) {
        String tarih = null;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select *  from haberler where haber_id=" + haber_id;
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            tarih = rs.getString("Tarih");

            c.close();
            st.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Hata kodu: 146  " + e.getMessage());;
        }

        return tarih;
    }

    public String haberler_haber_kategorisi_getir(int haber_id) {
        String kategori = null;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select *  from haberler where haber_id=" + haber_id;
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            kategori = rs.getString("Haber_Kategorisi");

            c.close();
            st.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Hata kodu: 147  " + e.getMessage());;
        }

        return kategori;
    }
}
