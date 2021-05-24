package DAO;

import entity.Center;
import entity.seans;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DBConnector;

public class seansDAO implements IDAO {

    @Override
    public int create(Center nw) {
        int sonuc = 0;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();

            int sonuc2 = control(nw);
            if (sonuc2 == 0) {
                String komut = "insert into seans (salon_id,vizyondaki_film_id,saat) values (" + nw.getSeans().getSalon_id() + "," + nw.getSeans().getVizyondaki_film_id() + ", '" + nw.getSeans().getSaat() + "')";
                sonuc = st.executeUpdate(komut);
            } else if (sonuc2 > 0) {
                sonuc = -1;
            } else {
                sonuc = -2;
            }

            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu :208 - " + e.getMessage());
        }

        return sonuc;
    }

    @Override
    public LinkedList read() {
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select * from seanslar_tablo";
            ResultSet rs = st.executeQuery(komut);

            LinkedList<seans> list = new LinkedList<>();

            while (rs.next()) {

                seans s = new seans(rs.getInt("seans_id"), rs.getInt("salon_id"), rs.getInt("vizyondaki_film_id"), rs.getString("film_name"), rs.getString("salon_name"), rs.getString("saat"));
                list.add(s);
            }

            c.close();
            st.close();
            rs.close();

            return list;

        } catch (SQLException e) {
            System.out.println("Hata kodu: 201 - " + e.getMessage());
            return null;
        }

    }

    public LinkedList read(int vizyondaki_film_id) {
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select * from seanslar_tablo where vizyondaki_film_id= " + vizyondaki_film_id;
            ResultSet rs = st.executeQuery(komut);

            LinkedList<seans> list = new LinkedList<>();

            while (rs.next()) {

                seans s = new seans(rs.getInt("seans_id"), rs.getInt("salon_id"), rs.getInt("vizyondaki_film_id"), rs.getString("film_name"), rs.getString("salon_name"), rs.getString("saat"));
                list.add(s);
            }

            c.close();
            st.close();
            rs.close();

            return list;

        } catch (SQLException e) {
            System.out.println("Hata kodu: 201 - " + e.getMessage());
            return null;
        }

    }

    @Override
    public int update(Center nw) {
        int sonuc = -2;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();

            int sonuc2 = control(nw);
            if (sonuc2 == 0) {
                String komut = "update seans set  salon_id= '" + nw.getSeans().getSalon_id() + "', vizyondaki_film_id = (" + nw.getSeans().getVizyondaki_film_id() + "), saat = ('" + nw.getSeans().getSaat() + "') where seans_id =" + nw.getSeans().getSeans_id();
                sonuc = st.executeUpdate(komut);
            } else if (sonuc2 > 0) {
                sonuc = -1;
            } else {
                sonuc = -2;
            }
            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu :210 - " + e.getMessage());
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
            String komut = "delete from seans where seans_id=" + id;
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu :209 - " + e.getMessage());
        }

        return sonuc;
    }

    @Override
    public int count() {
        int sonuc = -1;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select count (seans_id) from seans ";
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            sonuc = rs.getInt("count");

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 203 - " + e.getMessage());
        }

        return sonuc;
    }

    public int count(int vizyondaki_film_id) {
        int sonuc = -1;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select count (seans_id) from seans where vizyondaki_film_id =  " + vizyondaki_film_id;
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            sonuc = rs.getInt("count");

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 203 - " + e.getMessage());
        }

        return sonuc;
    }

    public int control(Center nw) {
        int sonuc = -1;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select count (seans_id) from seans where  salon_id=" + nw.getSeans().getSalon_id() + " and saat = '" + nw.getSeans().getSaat() + "'";
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            sonuc = rs.getInt("count");

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 207 - " + e.getMessage());
        }

        return sonuc;

    }

    //seans_select
    public ObservableList<seans> select(ObservableList<seans> data) {

        LinkedList<seans> list = read();

        for (int i = 0; i < list.size(); i++) {
            data.addAll(FXCollections.observableArrayList(new seans(list.get(i).getFilm_name(), list.get(i).getSalon_name(), list.get(i).getSaat())));
        }

        return data;
    }

    //combo doldurmak için kullanılan select
    public String[] select() {
        String[] arr = new String[count()];
        LinkedList<seans> list = new LinkedList<>();

        for (int i = 0; i < list.size(); i++) {
            String seans_combo = list.get(i).getSeans_id() + " | " + list.get(i).getFilm_name() + " | " + list.get(i).getSalon_name();
            arr[i] = seans_combo;
            i++;
        }

        return arr;
    }

    public String[] seans_combo_doldur(int vizyondaki_film_id) {
        String[] arr = new String[count(vizyondaki_film_id)];
        LinkedList<seans> list = new LinkedList<>();

        for (int i = 0; i < list.size(); i++) {
            String seans_combo = list.get(i).getSeans_id() + " | " + list.get(i).getFilm_name() + " | " + list.get(i).getSalon_name() + " | " + list.get(i).getSaat();
            arr[i] = seans_combo;
            i++;
        }

        return arr;
    }

    public String search_string(int seans_id, int secim) {

        String sonuc = null;

        LinkedList<seans> list = read();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getSeans_id() == seans_id) {
                switch (secim) {
                    //film_adi döndürür
                    case 1:
                        sonuc = list.get(i).getFilm_name();
                        break;
                    //salon_adi döndürür    
                    case 2:
                        sonuc = list.get(i).getSalon_name();
                        break;
                    //saat döndürür    
                    case 3:
                        sonuc = list.get(i).getSaat();
                        break;
                    default:
                        System.out.println("Hata 206");
                        return null;
                }
            }
        }

        return sonuc;

    }

    public int search_int(int seans_id, int secim) {
        int sonuc = 0;

        LinkedList<seans> list = new LinkedList<>();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getSeans_id() == seans_id) {
                if (secim == 1) {
                    sonuc = list.get(i).getVizyondaki_film_id();
                } else if (secim == 2) {
                    sonuc = list.get(i).getSalon_id();
                } else {
                    System.out.println("Hata: 204");
                    return -1;
                }
            }
        }

        return sonuc;
    }

    /*

    //public ObservableList<seans> seans_select(ObservableList<seans> data) {

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select * from seanslar_tablo";
            ResultSet rs = st.executeQuery(komut);

            while (rs.next()) {
                String film_name = rs.getString("film_name");
                String salon_name = rs.getString("salon_name");
                String saat = rs.getString("saat");

                data.addAll(FXCollections.observableArrayList(new seans(film_name, salon_name, saat)));
            }

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 201 - " + e.getMessage());
        }

        return data;
    }

    //public String[] seans_combo_doldur() {
        String[] arr = new String[kac_tane_seans_var()];
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select * from seanslar_tablo";
            ResultSet rs = st.executeQuery(komut);
            int i = 0;
            while (rs.next()) {
                String seans_combo = rs.getInt("seans_id") + " | " + rs.getString("film_name") + " | " + rs.getString("salon_name");
                arr[i] = seans_combo;
                i++;
            }

            c.close();
            st.close();
            rs.close();

            return arr;

        } catch (SQLException e) {
            System.out.println("Hata kodu: 202 - " + e.getMessage());
        }

        return null;
    }

    //public int kac_tane_seans_var() {
        int sonuc = -1;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select count (seans_id) from seans ";
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            sonuc = rs.getInt("count");

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 203 - " + e.getMessage());
        }

        return sonuc;
    }

    //public String[] seans_combo_doldur(int vizyondaki_film_id) {
        String[] arr = new String[kac_tane_seans_var(vizyondaki_film_id)];
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select * from seanslar_tablo where vizyondaki_film_id = " + vizyondaki_film_id;
            ResultSet rs = st.executeQuery(komut);
            int i = 0;
            while (rs.next()) {
                String seans_combo = rs.getInt("seans_id") + " | " + rs.getString("film_name") + " | " + rs.getString("salon_name") + " | " + rs.getString("saat");
                arr[i] = seans_combo;
                i++;
            }

            c.close();
            st.close();
            rs.close();

            return arr;

        } catch (SQLException e) {
            System.out.println("Hata kodu: 202 - " + e.getMessage());
        }

        return null;
    }

    //public int kac_tane_seans_var(int vizyondaki_film_id) {
        int sonuc = -1;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select count (seans_id) from seans where vizyondaki_film_id =  " + vizyondaki_film_id;
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            sonuc = rs.getInt("count");

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 203 - " + e.getMessage());
        }

        return sonuc;
    }

    //public int vizyondaki_film_id_getir(int id) {
        int film_id = 0;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select *  from seanslar_tablo where seans_id='" + id + "'";
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            film_id = rs.getInt("vizyondaki_film_id");

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 204 - " + e.getMessage());
        }

        return film_id;
    }

    //public String film_adi_getir(int seans_id) {
        int vizyondaki_film_id = vizyondaki_film_id_getir(seans_id);

        vizyondaki_filmlerDAO vdao = new vizyondaki_filmlerDAO();

        return vdao.film_adi_getir(vizyondaki_film_id);
    }

    //public String salon_adi_getir(int seans_id) {
        int salon_id = salon_id_getir(seans_id);

        sinema_salonlariDAO sdao = new sinema_salonlariDAO();

        return sdao.salon_adi_getir(salon_id);
    }

    //public int salon_id_getir(int id) {
        int salon_id = 0;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select *  from seans where seans_id='" + id + "'";
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            salon_id = rs.getInt("salon_id");

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 205 - " + e.getMessage());
        }

        return salon_id;
    }

    //public String saat_getir(int id) {
        String saat = null;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select *  from seans where seans_id='" + id + "'";
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            saat = rs.getString("saat");

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 206 - " + e.getMessage());
        }

        return saat;
    }

    //public int seans_control(seans s) {
        int sonuc = -1;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select count (seans_id) from seans where  salon_id=" + s.getSalon_id() + " and saat = '" + s.getSaat() + "'";
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            sonuc = rs.getInt("count");

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 207 - " + e.getMessage());
        }

        return sonuc;

    }

    //public int seans_dao_ekle(seans s) {
        int sonuc = 0;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();

            int sonuc2 = seans_control(s);
            if (sonuc2 == 0) {
                String komut = "insert into seans (salon_id,vizyondaki_film_id,saat) values (" + s.getSalon_id() + "," + s.getVizyondaki_film_id() + ", '" + s.getSaat() + "')";
                sonuc = st.executeUpdate(komut);
            } else if (sonuc2 > 0) {
                sonuc = -1;
            } else {
                sonuc = -2;
            }

            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu :208 - " + e.getMessage());
        }

        return sonuc;
    }

    //public int seans_dao_sil(int id) {
        int sonuc = 0;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "delete from seans where seans_id=" + id;
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu :209 - " + e.getMessage());
        }

        return sonuc;

    }

    //public int seans_degistir(seans s) {
        int sonuc = -2;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();

            int sonuc2 = seans_control(s);
            if (sonuc2 == 0) {
                String komut = "update seans set  salon_id= '" + s.getSalon_id() + "', vizyondaki_film_id = (" + s.getVizyondaki_film_id() + "), saat = ('" + s.getSaat() + "') where seans_id =" + s.getSeans_id();
                sonuc = st.executeUpdate(komut);
            } else if (sonuc2 > 0) {
                sonuc = -1;
            } else {
                sonuc = -2;
            }
            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu :210 - " + e.getMessage());
        }

        return sonuc;
    }

     */
}
