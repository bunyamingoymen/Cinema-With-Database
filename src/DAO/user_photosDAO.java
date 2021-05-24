package DAO;

import entity.Center;
import entity.user_photos;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import util.DBConnector;

public class user_photosDAO implements IDAO {

    @Override
    public int create(Center nw) {
        int sonuc = 0;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();

            String komut = "insert into user_photos (user_id,photo_name,photo_parent,photo_path) values (" + nw.getUser_photos().getUser_id() + ",'" + nw.getUser_photos().getPhoto_name() + "','" + nw.getUser_photos().getPhoto_parent() + "', '" + nw.getUser_photos().getPhoto_path() + "')";
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu :246 - " + e.getMessage());
        }

        return sonuc;
    }

    @Override
    public LinkedList read() {
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select *  from user_photos";
            ResultSet rs = st.executeQuery(komut);
            LinkedList<user_photos> list = new LinkedList<>();
            while (rs.next()) {
                user_photos up = new user_photos(rs.getInt("user_photo_id"), rs.getInt("user_id"), rs.getString("photo_name"), rs.getString("photo_parent"), rs.getString("photo_path"));

                list.add(up);
            }

            c.close();
            st.close();
            rs.close();

            return list;

        } catch (SQLException e) {
            System.out.println("Hata kodu: 247 - " + e.getMessage());
            return null;
        }
    }

    @Override
    public int update(Center nw) {
        int sonuc = 0;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();

            String komut = "update user_photos set photo_name = '" + nw.getUser_photos().getPhoto_name() + "', photo_parent = '" + nw.getUser_photos().getPhoto_parent() + "', photo_path='" + nw.getUser_photos().getPhoto_path() + "' where user_id =" + nw.getUser_photos().getUser_id();
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();
        } catch (SQLException e) {
            System.out.println("Hata kodu :245 - " + e.getMessage());
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

    //user_id sayısını getirir
    public int count(int user_id) {
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

    //photo path getirir
    public String search_string(int user_id) {
        String path = "";
        LinkedList<user_photos> list = read();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUser_id() == user_id) {
                path = list.get(i).getPhoto_path();
                break;
            }
        }

        return path;
    }

    public int create_or_update(Center nw) {
        int control = count(nw.getUser_photos().getUser_id());

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

    /*

    //public int kac_tane_user_id_var(int user_id) {
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

    //public int user_photo_dao_ekle_veya_guncelle(user_photos up) {
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

    //public int user_photo_guncelle(user_photos up) {
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

    //public int user_photo_ekle(user_photos up) {
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

    //public String photo_path_getir(int user_id) {
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

     */
}
