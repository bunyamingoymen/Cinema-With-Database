package DAO;

import entity.Center;
import entity.films_photos;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import util.DBConnector;

public class films_photosDAO implements IDAO {

    @Override
    public int create(Center nw) {
        int sonuc = 0;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();

            String komut = "insert into films_photos (film_id,photo_name,photo_parent,photo_path) values (" + nw.getFilms_photos().getFilm_id() + ",'" + nw.getFilms_photos().getPhoto_name() + "','" + nw.getFilms_photos().getPhoto_parent() + "', '" + nw.getFilms_photos().getPhoto_path() + "')";
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu :250 - " + e.getMessage());
        }

        return sonuc;
    }

    @Override
    public LinkedList read() {
        return null;
    }

    @Override
    public int update(Center nw) {
        int sonuc = 0;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();

            String komut = "update films_photos set photo_name = '" + nw.getFilms_photos().getPhoto_name() + "', photo_parent = '" + nw.getFilms_photos().getPhoto_parent() + "', photo_path='" + nw.getFilms_photos().getPhoto_path() + "' where user_id =" + nw.getFilms_photos().getFilm_id();
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();
        } catch (SQLException e) {
            System.out.println("Hata kodu :249 - " + e.getMessage());
        }
        return sonuc;
    }

    @Override
    public int delete(int id) {
        return -1;
    }

    @Override
    public int count() {
        return -1;
    }

    //film_id sayar
    public int count(int film_id) {
        int sonuc = -1;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select count (film_id) from films_photos where film_id = " + film_id;
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            sonuc = rs.getInt("count");

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 248 - " + e.getMessage());
        }

        return sonuc;
    }

    //güncelleme yada ekleme fonskiyonuna götürür
    public int create_or_update(Center nw) {
        int control = count(nw.getFilms_photos().getFilm_id());

        switch (control) {
            case -1:
                return -1;
            case 0:
                return create(nw);
            case 1:
                return update(nw);
            default:
                return -2;
        }
    }

    //photo path getirir
    public String search(int film_id) {
        String path = "";
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select *  from films_photos where film_id=" + film_id;
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            path = rs.getString("photo_path");

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 251 - " + e.getMessage());
        }
        return path;
    }

    /*

    //public int kac_tane_film_id_var(int film_id) {
        int sonuc = -1;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select count (film_id) from films_photos where film_id = " + film_id;
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            sonuc = rs.getInt("count");

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 248 - " + e.getMessage());
        }

        return sonuc;
    }

    //public int film_photo_dao_ekle_veya_guncelle(films_photos fp) {
        int control = kac_tane_film_id_var(fp.getFilm_id());

        switch (control) {
            case -1:
                return -1;
            case 0:
                return film_photo_ekle(fp);
            case 1:
                return film_photo_guncelle(fp);
            default:
                return -2;
        }
    }

    //public int film_photo_guncelle(films_photos fp) {
        int sonuc = 0;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();

            String komut = "update films_photos set photo_name = '" + fp.getPhoto_name() + "', photo_parent = '" + fp.getPhoto_parent() + "', photo_path='" + fp.getPhoto_path() + "' where user_id =" + fp.getFilm_id();
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();
        } catch (SQLException e) {
            System.out.println("Hata kodu :249 - " + e.getMessage());
        }
        return sonuc;
    }

    //public int film_photo_ekle(films_photos fp) {
        int sonuc = 0;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();

            String komut = "insert into films_photos (film_id,photo_name,photo_parent,photo_path) values (" + fp.getFilm_id() + ",'" + fp.getPhoto_name() + "','" + fp.getPhoto_parent() + "', '" + fp.getPhoto_path() + "')";
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu :250 - " + e.getMessage());
        }

        return sonuc;
    }

    //public String photo_path_getir(int film_id) {
        String path = "";
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select *  from films_photos where film_id=" + film_id;
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            path = rs.getString("photo_path");

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 251 - " + e.getMessage());
        }
        return path;
    }

     */
}
