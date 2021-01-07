/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entity.eski_filmler;
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
public class eski_filmlerDAO {

    public ObservableList<eski_filmler> eski_filmler_select(ObservableList<eski_filmler> data) {
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select * from eski_filmler_tablo";
            ResultSet rs = st.executeQuery(komut);

            while (rs.next()) {
                String film_name = rs.getString("film_name");
                String film_type = rs.getString("film_type");
                int film_suresi = rs.getInt("film_suresi");
                String ad = rs.getString("ad");
                String soyad = rs.getString("soyad");
                int hangi = rs.getInt("hangi_aboneler_izleyebilir");
                int aldigi_odul_sayisi = rs.getInt("aldigi_odul_sayisi");
                data.addAll(FXCollections.observableArrayList(new eski_filmler(film_name, film_type, film_suresi, ad, soyad, hangi, aldigi_odul_sayisi)));
            }

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 131  " + e.getMessage());;
        }

        return data;
    }

    public int eski_filmler_tamamen_sil(int id) {
        int sonuc = 0;
        int sonuc2 = 0;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            int film_id = film_id_getir(id);
            String komut = "delete from eski_filmler where eski_film_id=" + id;
            sonuc = st.executeUpdate(komut);
            filmlerDAO fdao = new filmlerDAO();
            sonuc2 = fdao.filmler_dao_delete(film_id);

            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu :150" + e.getMessage());;
        }

        if (sonuc == 1 && sonuc2 == 1) {
            return 1;
        } else {
            return 0;
        }

    }

    public int eski_filmler_sadece_eskiden_sil(int id) {
        int sonuc = 0;
        int sonuc_2 = 0;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            vizyondaki_filmlerDAO vdao = new vizyondaki_filmlerDAO();
            String komut = "delete from eski_filmler where eski_film_id=" + id;
            int film_id = film_id_getir(id);
            sonuc_2 = vdao.vizyondaki_filmler_dao_sadece_film_id_ekle(film_id);
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu :151 " + e.getMessage());;
        }

        if ((sonuc == 1) && (sonuc_2 == 1)) {
            return 1;
        } else {
            return 0;
        }

    }

    public int eski_filmler_dao_ekle(eski_filmler ed) {
        int sonuc = 0;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "insert into eski_filmler (film_id,hangi_aboneler_izleyebilir,aldigi_odul_sayisi) values ('" + ed.getFilm_id() + "','" + ed.getHangi_aboneler_izleyebilir() + "','" + ed.getAldigi_odul_sayisi() + "')";
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu :132" + e.getMessage());;
        }

        return sonuc;
    }

    public int eski_filmler_degistir(eski_filmler ed) {
        int sonuc1 = 0, sonuc2 = 0;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "update eski_filmler set hangi_aboneler_izleyebilir = " + ed.getHangi_aboneler_izleyebilir() + " , aldigi_odul_sayisi = " + ed.getAldigi_odul_sayisi() + " where eski_film_id =" + ed.getEski_film_id();
            sonuc1 = st.executeUpdate(komut);
            String komut2 = "update filmler set film_name = '" + ed.getFilm_name() + "', film_type = '" + ed.getFilm_type() + " ', film_suresi=  " + ed.getFilm_suresi() + " , yonetmen_id= " + ed.getYonetmen_id() + "  where film_id=" + ed.getFilm_id();
            sonuc2 = st.executeUpdate(komut2);

            c.close();
            st.close();
        } catch (SQLException e) {
            System.out.println("Hata kodu :147" + e.getMessage());;
        }
        if ((sonuc1 == 1) && (sonuc2 == 1)) {
            return 1;
        } else {
            return 0;
        }

    }

    public int eski_filmler_dao_sadece_film_id_ekle(int film_id) {
        int sonuc = 0;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "insert into eski_filmler (film_id) values ('" + film_id + "')";
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu :144" + e.getMessage());;
        }

        return sonuc;
    }

    public String[][] eski_filmler_combo_doldur() {
        String[][] arr = new String[kac_tane_eski_film_var()][2];
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select * from eski_filmler ";
            ResultSet rs = st.executeQuery(komut);
            int i = 0;
            filmlerDAO fdao = new filmlerDAO();
            yonetmenlerDAO ydao = new yonetmenlerDAO();
            while (rs.next()) {
                String eski_filmler_combo = fdao.filmler_film_adi_getir(rs.getInt("film_id")) + " | " + fdao.filmler_film_type_getir(rs.getInt("film_id")) + " | " + fdao.filmler_film_suresi_getir(rs.getInt("film_id")) + " | " + ydao.yonetmenler_yonetmen_getir(fdao.filmler_yonetmen_id_getir(rs.getInt("film_id")));
                int id = rs.getInt("eski_film_id");
                arr[i][0] = eski_filmler_combo;
                arr[i][1] = String.valueOf(id);
                i++;
            }

            c.close();
            st.close();
            rs.close();

            return arr;

        } catch (SQLException e) {
            System.out.println("Hata kodu: 133  " + e.getMessage());;
        }

        return null;
    }

    public int kac_tane_eski_film_var() {
        int sonuc = -1;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select count (eski_film_id) from eski_filmler ";
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            sonuc = rs.getInt("count");

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 134  " + e.getMessage());;
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
            String komut = "select *  from eski_filmler where eski_film_id='" + id + "'";
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            film_adi = fdao.filmler_film_adi_getir(rs.getInt("film_id"));

            c.close();
            st.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Hata kodu: 135  " + e.getMessage());;
        }

        return film_adi;
    }

    public String film_type_getir(int id) {
        String film_type = null;
        filmlerDAO fdao = new filmlerDAO();

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select *  from eski_filmler where eski_film_id='" + id + "'";
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            film_type = fdao.filmler_film_type_getir(rs.getInt("film_id"));

            c.close();
            st.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Hata kodu: 136  " + e.getMessage());;
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
            String komut = "select *  from eski_filmler where eski_film_id='" + id + "'";
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            film_suresi = fdao.filmler_film_suresi_getir(rs.getInt("film_id"));
            
            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 137  " + e.getMessage());;
        }

        return film_suresi;
    }

    public int yonetmen_id_getir(int id) {
        int yonetmen_id = 0;
        filmlerDAO fdao = new filmlerDAO();
        yonetmenlerDAO ydao = new yonetmenlerDAO();

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select *  from eski_filmler where eski_film_id='" + id + "'";
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            yonetmen_id = fdao.filmler_yonetmen_id_getir(rs.getInt("film_id"));
            
            c.close();
            st.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Hata kodu: 146  " + e.getMessage());;
        }

        return yonetmen_id;
    }

    public String yonetmen_getir(int id) {
        String name_surname = null;
        filmlerDAO fdao = new filmlerDAO();
        yonetmenlerDAO ydao = new yonetmenlerDAO();

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select *  from eski_filmler where eski_film_id='" + id + "'";
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            name_surname = ydao.yonetmenler_yonetmen_getir(fdao.filmler_yonetmen_id_getir(rs.getInt("film_id")));
            
            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 138  " + e.getMessage());;
        }

        return name_surname;
    }

    public int hangi_abone_getir(int id) {
        int hangi_abone = 0;
        filmlerDAO fdao = new filmlerDAO();

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select *  from eski_filmler where eski_film_id='" + id + "'";
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            hangi_abone = rs.getInt("hangi_aboneler_izleyebilir");
            
            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 139  " + e.getMessage());;
        }

        return hangi_abone;
    }

    public int aldigi_odul_sayisi_getir(int id) {
        int odul = 0;
        filmlerDAO fdao = new filmlerDAO();

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select *  from eski_filmler where eski_film_id='" + id + "'";
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            odul = rs.getInt("aldigi_odul_sayisi");
            
            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 140  " + e.getMessage());;
        }

        return odul;
    }

    public int film_id_getir(int id) {
        int film_id = 0;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select *  from eski_filmler where eski_film_id='" + id + "'";
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            film_id = rs.getInt("film_id");
            System.out.println("film_id = " + film_id);
            
            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 141  " + e.getMessage());;
        }
        return film_id;
    }

}
