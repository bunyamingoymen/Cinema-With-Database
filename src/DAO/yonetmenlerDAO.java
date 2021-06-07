package DAO;

import entity.Center;
import entity.yonetmenler;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DBConnector;

public class yonetmenlerDAO implements IDAO {

    @Override
    public int create(Center nw) {
        int sonuc = 0;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "insert into yonetmenler (ad,soyad,film_sayisi) values ('" + nw.getYonetmenler().getYonetmen_name() + "','" + nw.getYonetmenler().getYonetmen_sur_name() + "','" + nw.getYonetmenler().getFilm_sayisi() + "')";
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu :148 - " + e.getMessage());
        }

        return sonuc;
    }

    @Override
    public LinkedList read() {
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select * from yonetmenler ";
            ResultSet rs = st.executeQuery(komut);
            LinkedList<yonetmenler> list = new LinkedList<>();
            while (rs.next()) {
                yonetmenler y = new yonetmenler(rs.getInt("yonetmen_id"), rs.getString("ad"), rs.getString("soyad"), rs.getInt("film_sayisi"));
                list.add(y);
            }

            c.close();
            st.close();
            rs.close();

            return list;

        } catch (SQLException e) {
            System.out.println("Hata kodu: 149 - " + e.getMessage());
        }

        return null;
    }

    @Override
    public int update(Center nw) {
        int sonuc = 0;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "update yonetmenler set  ad = '" + nw.getYonetmenler().getYonetmen_name() + "' , soyad = '" + nw.getYonetmenler().getYonetmen_sur_name() + "' , film_sayisi = " + nw.getYonetmenler().getFilm_sayisi() + " where yonetmen_id =" + nw.getYonetmenler().getYonetmen_id();
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu :157 - " + e.getMessage());
        }
        return sonuc;
    }

    @Override
    public int delete(int id) {
        int sonuc = 0;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "delete from yonetmenler where yonetmen_id=" + id;
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu :158 - " + e.getMessage());
        }

        return sonuc;
    }

    @Override
    public int count() {
        LinkedList<yonetmenler> list = read();

        return list.size();
    }

    public ObservableList<yonetmenler> select(ObservableList<yonetmenler> data) {
        LinkedList<yonetmenler> list = read();

        for (int i = 0; i < list.size(); i++) {
            data.addAll(FXCollections.observableArrayList(new yonetmenler(list.get(i).getYonetmen_id(), list.get(i).getYonetmen_name(), list.get(i).getYonetmen_sur_name(), list.get(i).getFilm_sayisi())));
        }

        return data;
    }

    //combo doldurmak için kullanılan select
    public String[][] select() {
        String[][] arr = new String[count()][2];
        LinkedList<yonetmenler> list = read();

        for (int i = 0; i < list.size(); i++) {
            String yonetmen = list.get(i).getYonetmen_id() + " " + list.get(i).getYonetmen_name() + " " + list.get(i).getYonetmen_sur_name();
            int id = list.get(i).getYonetmen_id();
            arr[i][0] = yonetmen;
            arr[i][1] = String.valueOf(id);
        }

        return arr;
    }

    public String search_string(int yonetmen_id, int secim) {
        String sonuc = null;
        LinkedList<yonetmenler> list = read();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getYonetmen_id() == yonetmen_id) {
                if (secim == 1) {
                    sonuc = list.get(i).getYonetmen_name();
                } else if (secim == 2) {
                    sonuc = list.get(i).getYonetmen_sur_name();
                } else if (secim == 3) {
                    sonuc = list.get(i).getYonetmen_name() + " " + list.get(i).getYonetmen_sur_name();
                } else {
                    System.out.println("Hata kodu 205");
                    return null;
                }
                break;
            }
        }

        return sonuc;
    }

    public int search_int(int yonetmen_id) {
        int film_sayisi = 0;
        LinkedList<yonetmenler> list = read();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getYonetmen_id() == yonetmen_id) {
                film_sayisi = list.get(i).getFilm_sayisi();
                break;
            }
        }

        return film_sayisi;
    }

    public int yonetmenler_film_sayisi_azalt(int yonetmen_id) {
        int sonuc = 0;
        LinkedList<yonetmenler> list = read();
        yonetmenler y = new yonetmenler();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getYonetmen_id() == yonetmen_id) {
                y.setFilm_sayisi(list.get(i).getFilm_sayisi() - 1);
                y.setYonetmen_id(yonetmen_id);
                y.setYonetmen_name(list.get(i).getYonetmen_name());
                y.setYonetmen_sur_name(list.get(i).getYonetmen_sur_name());
                break;
            }
        }
        Center nw = new Center(y);

        sonuc = update(nw);

        return sonuc;
    }

    /*

    public yonetmenlerDAO() {
    }

    //public int yonetmenler_dao_ekle(yonetmenler y) {
        int sonuc = 0;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "insert into yonetmenler (ad,soyad,film_sayisi) values ('" + y.getYonetmen_name() + "','" + y.getYonetmen_sur_name() + "','" + y.getFilm_sayisi() + "')";
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu :148 - " + e.getMessage());
        }

        return sonuc;
    }

    //public String[][] yonetmen_combo_doldur() {
        String[][] arr = new String[kac_tane_yonetmen_var()][2];
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select * from yonetmenler ";
            ResultSet rs = st.executeQuery(komut);
            int i = 0;
            while (rs.next()) {
                String name_surname = rs.getInt("yonetmen_id") + " " + rs.getString("ad") + " " + rs.getString("soyad");
                int id = rs.getInt("yonetmen_id");
                arr[i][0] = name_surname;
                arr[i][1] = String.valueOf(id);
                i++;
            }

            c.close();
            st.close();
            rs.close();

            return arr;

        } catch (SQLException e) {
            System.out.println("Hata kodu: 149 - " + e.getMessage());
        }

        return null;
    }

    //public int kac_tane_yonetmen_var() {
        int sonuc = -1;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select count (yonetmen_id) from yonetmenler ";
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            sonuc = rs.getInt("count");

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 150 - " + e.getMessage());
        }

        return sonuc;
    }

    //public String yonetmenler_yonetmen_getir(int id) {
        return yonetmenler_ad_getir(id) + " " + yonetmenler_soyad_getir(id);
    }

    //public ObservableList<yonetmenler> yonetmenler_select(ObservableList<yonetmenler> data) {

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select * from yonetmenler";
            ResultSet rs = st.executeQuery(komut);

            while (rs.next()) {
                int yonetmen_id = rs.getInt("yonetmen_id");
                String ad = rs.getString("ad");
                String soyad = rs.getString("soyad");
                int film_sayisi = rs.getInt("film_sayisi");

                data.addAll(FXCollections.observableArrayList(new yonetmenler(yonetmen_id, ad, soyad, film_sayisi)));
            }

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 153 - " + e.getMessage());
        }

        return data;
    }

   // public String yonetmenler_ad_getir(int id) {
        String name = null;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select ad from yonetmenler where yonetmen_id ='" + id + "'";
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            name = rs.getString("ad");

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 154 - " + e.getMessage());
        }

        return name;
    }

    //public String yonetmenler_soyad_getir(int id) {
        String surname = null;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select soyad from yonetmenler where yonetmen_id ='" + id + "'";
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            surname = rs.getString("soyad");

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 155 - " + e.getMessage());
        }

        return surname;
    }

    //public int yonetmenler_film_sayisi_getir(int id) {
        int film_sayisi = 0;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select film_sayisi from yonetmenler where yonetmen_id ='" + id + "'";
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            film_sayisi = rs.getInt("film_sayisi");

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 156 - " + e.getMessage());
        }

        return film_sayisi;
    }

   // public int yonetmenler_dao_degistir(yonetmenler y) {

        int sonuc = 0;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "update yonetmenler set  ad = '" + y.getYonetmen_name() + "' , soyad = '" + y.getYonetmen_sur_name() + "' , film_sayisi = " + y.getFilm_sayisi() + " where yonetmen_id =" + y.getYonetmen_id();
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu :157 - " + e.getMessage());
        }
        return sonuc;
    }

    //public int yonetmenler_dao_sil(int id) {
        int sonuc = 0;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "delete from yonetmenler where yonetmen_id=" + id;
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu :158 - " + e.getMessage());
        }

        return sonuc;
    }

    public int yonetmenler_film_sayisi_azalt(int id) {
        int sonuc = 0;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            int eski_film_sayisi = yonetmenler_film_sayisi_getir(id);
            int yeni_film_sayisi = eski_film_sayisi - 1;
            String komut = "update yonetmenler set film_sayisi =" + yeni_film_sayisi + "where yonetmen_id = " + id;
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu :151 - " + e.getMessage());
        }

        return sonuc;
    }

     */
}
