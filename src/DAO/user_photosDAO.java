package DAO;

import entity.user_photos;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import util.DBConnector;

public class user_photosDAO {

    public int kac_tane_user_id_var(int user_id) {
        int sonuc = -1;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select count (user_id) from user_photos where user_id = " + user_id;
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            sonuc = rs.getInt("count");

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 244 - " + e.getMessage());
        }

        return sonuc;
    }

    public int user_photo_dao_ekle_veya_guncelle(user_photos up) {
        int control = kac_tane_user_id_var(up.getUser_id());

        switch (control) {
            case -1:
                return -1;
            case 0:
                return user_photo_ekle(up);
            case 1:
                return user_photo_guncelle(up);
            default:
                return -2;
        }
    }

    public int user_photo_guncelle(user_photos up) {
        int sonuc = 0;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();

            String komut = "update user_photos set photo_name = '" + up.getPhoto_name() + "', photo_parent = '" + up.getPhoto_parent() + "', photo_path='" + up.getPhoto_path() + "' where user_id =" + up.getUser_id();
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();
        } catch (SQLException e) {
            System.out.println("Hata kodu :245 - " + e.getMessage());
        }
        return sonuc;
    }

    public int user_photo_ekle(user_photos up) {
        int sonuc = 0;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();

            String komut = "insert into user_photos (user_id,photo_name,photo_parent,photo_path) values (" + up.getUser_id() + ",'" + up.getPhoto_name() + "','" + up.getPhoto_parent() + "', '" + up.getPhoto_path() + "')";
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu :246 - " + e.getMessage());
        }

        return sonuc;
    }

    public String photo_path_getir(int user_id) {
        String path = "";
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select *  from user_photos where user_id='" + user_id + "'";
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            path = rs.getString("photo_path");

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 247 - " + e.getMessage());
        }
        return path;
    }
}
