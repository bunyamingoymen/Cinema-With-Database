/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entity.filmler;
import entity.vizyondaki_filmler;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DBConnector;

/**
 *
 * @author bgoymenen
 */
public class vizyondaki_filmlerDAO {

    public vizyondaki_filmlerDAO() {
    }

    public int vizyondaki_filmler_tamamen_sil(int id) {
        int sonuc = 0;
        int sonuc2 = 0;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            int film_id = film_id_getir(id);
            String komut = "delete from vizyondaki_filmler where vizyondaki_film_id=" + id;
            sonuc = st.executeUpdate(komut);
            filmlerDAO fdao = new filmlerDAO();
            sonuc2 = fdao.filmler_dao_delete(film_id);

            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu :111" + e.getMessage());;
        }

        if (sonuc == 1 && sonuc2 == 1) {
            return 1;
        } else {
            return 0;
        }

    }

    public int vizyondaki_filmler_sadece_vizyondan_sil(int id) {
        int sonuc = 0;
        int sonuc_2 = 0;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            eski_filmlerDAO edao = new eski_filmlerDAO();
            String komut = "delete from vizyondaki_filmler where vizyondaki_film_id=" + id;
            int film_id = film_id_getir(id);
            sonuc_2 = edao.eski_filmler_dao_sadece_film_id_ekle(film_id);
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu :112" + e.getMessage());;
        }

        if ((sonuc == 1) && (sonuc_2 == 1)) {
            return 1;
        } else {
            return 0;
        }

    }

    public int vizyondaki_filmler_dao_ekle(vizyondaki_filmler v) {
        int sonuc = 0;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "insert into vizyondaki_filmler (film_id,vizyondan_kalkis_tarihi,kullanici_puani,seans_sayisi) values ('" + v.getFilm_id() + "','" + v.getVizyondan_kalkis_tarihi() + "','" + v.getKullanici_puani() + "','" + v.getSeans_sayisi() + "')";
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu :113" + e.getMessage());;
        }

        return sonuc;
    }

    public int vizyondaki_filmler_dao_sadece_film_id_ekle(int film_id) {
        int sonuc = 0;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "insert into vizyondaki_filmler (film_id) values ('" + film_id + "')";
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu :148" + e.getMessage());;
        }

        return sonuc;
    }

    public ObservableList<vizyondaki_filmler> vizyondaki_filmler_select(ObservableList<vizyondaki_filmler> data) {

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select * from vizyondaki_filmler_tablo";
            ResultSet rs = st.executeQuery(komut);

            while (rs.next()) {
                String film_name = rs.getString("film_name");
                String film_type = rs.getString("film_type");
                int film_suresi = rs.getInt("film_suresi");
                String ad = rs.getString("ad");
                String soyad = rs.getString("soyad");
                String kalkis = rs.getString("vizyondan_kalkis_tarihi");
                int kullanici_puani = rs.getInt("kullanici_puani");
                data.addAll(FXCollections.observableArrayList(new vizyondaki_filmler(film_name, film_type, film_suresi, ad, soyad, kalkis, kullanici_puani)));
            }

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 114  " + e.getMessage());;
        }

        return data;
    }

    public int vizyondaki_filmler_degistir(vizyondaki_filmler v, filmler f) {
        int sonuc1 = 0, sonuc2 = 0;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "update vizyondaki_filmler set vizyondan_kalkis_tarihi = '" + v.getVizyondan_kalkis_tarihi() + "' where vizyondaki_film_id =" + v.getVizyondaki_filmler_id();
            sonuc1 = st.executeUpdate(komut);
            String komut2 = "update filmler set film_name = '" + f.getFilm_name() + "', film_type = '" + f.getFilm_type() + " ', film_suresi= ' " + f.getFilm_suresi() + " ', yonetmen_id= '" + f.getYonetmen_id() + " ' where film_id=" + f.getFilm_id();
            sonuc2 = st.executeUpdate(komut2);

            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu :115" + e.getMessage());;
        }
        if ((sonuc1 == 1) && (sonuc2 == 1)) {
            return 1;
        } else {
            return 0;
        }

    }

    public String[][] vizyondaki_filmler_combo_doldur() {
        String[][] arr = new String[kac_tane_vizyonda_film_var()][2];
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select * from vizyondaki_filmler ";
            ResultSet rs = st.executeQuery(komut);
            int i = 0;
            filmlerDAO fdao = new filmlerDAO();
            yonetmenlerDAO ydao = new yonetmenlerDAO();
            while (rs.next()) {
                String vizyondaki_filmler_combo = fdao.filmler_film_adi_getir(rs.getInt("film_id")) + " | " + fdao.filmler_film_type_getir(rs.getInt("film_id")) + " | " + fdao.filmler_film_suresi_getir(rs.getInt("film_id")) + " | " + ydao.yonetmenler_yonetmen_getir(fdao.filmler_yonetmen_id_getir(rs.getInt("film_id")));
                int id = rs.getInt("vizyondaki_film_id");
                arr[i][0] = vizyondaki_filmler_combo;
                arr[i][1] = String.valueOf(id);
                i++;
            }

            c.close();
            st.close();
            rs.close();

            return arr;

        } catch (SQLException e) {
            System.out.println("Hata kodu: 116  " + e.getMessage());;
        }

        return null;
    }

    public int kac_tane_vizyonda_film_var() {
        int sonuc = -1;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select count (vizyondaki_film_id) from vizyondaki_filmler ";
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            sonuc = rs.getInt("count");

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 117  " + e.getMessage());;
        }

        return sonuc;
    }

    public String film_adi_getir(int id) {
        String film_adi = null;
        filmlerDAO fdao = new filmlerDAO();

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select *  from vizyondaki_filmler where vizyondaki_film_id='" + id + "'";
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            film_adi = fdao.filmler_film_adi_getir(rs.getInt("film_id"));

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 118  " + e.getMessage());;
        }

        return film_adi;
    }

    public int film_id_getir(int id) {
        int film_id = 0;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select *  from vizyondaki_filmler where vizyondaki_film_id='" + id + "'";
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            film_id = rs.getInt("film_id");

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 119  " + e.getMessage());;
        }
        return film_id;
    }

    public String film_type_getir(int id) {
        String film_type = null;
        filmlerDAO fdao = new filmlerDAO();

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select *  from vizyondaki_filmler where vizyondaki_film_id='" + id + "'";
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            film_type = fdao.filmler_film_type_getir(rs.getInt("film_id"));

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 120  " + e.getMessage());;
        }

        return film_type;
    }

    public int film_suresi_getir(int id) {
        int film_suresi = 0;
        filmlerDAO fdao = new filmlerDAO();

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select *  from vizyondaki_filmler where vizyondaki_film_id='" + id + "'";
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            film_suresi = fdao.filmler_film_suresi_getir(rs.getInt("film_id"));

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 121  " + e.getMessage());;
        }

        return film_suresi;
    }

    public String yonetmen_getir(int id) {
        String name_surname = null;
        filmlerDAO fdao = new filmlerDAO();
        yonetmenlerDAO ydao = new yonetmenlerDAO();

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select *  from vizyondaki_filmler where vizyondaki_film_id='" + id + "'";
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            name_surname = ydao.yonetmenler_yonetmen_getir(fdao.filmler_yonetmen_id_getir(rs.getInt("film_id")));

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 122  " + e.getMessage());;
        }

        return name_surname;
    }

    public String vizyondan_kalkis_tarihi_getir(int id) {
        String kalkis = null;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select *  from vizyondaki_filmler where vizyondaki_film_id='" + id + "'";
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            kalkis = rs.getString("vizyondan_kalkis_tarihi");

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 123  " + e.getMessage());;
        }

        return kalkis;
    }

    public int kullanici_puani_getir(int id) {
        int kullanici_puani = 0;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select *  from vizyondaki_filmler where vizyondaki_film_id='" + id + "'";
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            kullanici_puani = rs.getInt("kullanici_puani");

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 124  " + e.getMessage());;
        }
        return kullanici_puani;
    }

    public int seans_sayisi_getir(int id) {
        int seans_sayisi_getir = 0;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select *  from vizyondaki_filmler where vizyondaki_film_id='" + id + "'";
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            seans_sayisi_getir = rs.getInt("seans_sayisi");

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 125  " + e.getMessage());;
        }
        return seans_sayisi_getir;
    }
}
