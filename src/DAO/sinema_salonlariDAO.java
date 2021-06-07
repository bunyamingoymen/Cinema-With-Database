package DAO;

import entity.Center;
import entity.sinema_salonlari;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import util.DBConnector;

public class sinema_salonlariDAO implements IDAO {

    @Override
    public int create(Center nw) {
        int sonuc = 0;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();

            int sonuc2 = count(nw.getSinema_salonlari().getSalon_name());
            if (sonuc2 == 0) {
                String komut = "insert into sinema_salonlari (salon_name,koltuk_sayisi) values ('" + nw.getSinema_salonlari().getSalon_name() + "','" + nw.getSinema_salonlari().getKoltuk_sayisi() + "')";
                sonuc = st.executeUpdate(komut);
            } else if (sonuc2 > 0) {
                sonuc = -1;
            } else {
                sonuc = -2;
            }

            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu :196 - " + e.getMessage());
        }

        return sonuc;
    }

    @Override
    public LinkedList read() {

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select * from sinema_salonlari ";
            ResultSet rs = st.executeQuery(komut);
            LinkedList<sinema_salonlari> list = new LinkedList<>();
            int i = 0;

            while (rs.next()) {
                sinema_salonlari ss = new sinema_salonlari(rs.getInt("salon_id"), rs.getString("salon_name"), rs.getInt("koltuk_sayisi"));

                list.add(ss);

            }

            c.close();
            st.close();
            rs.close();

            return list;

        } catch (SQLException e) {
            System.out.println("Hata kodu: 193 - " + e.getMessage());
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

            int sonuc2 = count(nw.getSinema_salonlari().getSalon_name());
            if (sonuc2 == 0) {
                String komut = "update sinema_salonlari set  salon_name= '" + nw.getSinema_salonlari().getSalon_name() + "', koltuk_sayisi = (" + nw.getSinema_salonlari().getKoltuk_sayisi() + ")where salon_id =" + nw.getSinema_salonlari().getSalon_id();
                sonuc = st.executeUpdate(komut);
            } else if (sonuc2 > 0) {
                sonuc = -1;
            } else {
                sonuc = -2;
            }

            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu :199 - " + e.getMessage());
        }

        return sonuc;
    }

    @Override
    public int delete(int sinema_salonlari_id) {
        int sonuc = 0;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "delete from sinema_salonlari where salon_id=" + sinema_salonlari_id;
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu :198 - " + e.getMessage());
        }

        return sonuc;
    }

    @Override
    public int count() {

        LinkedList<sinema_salonlari> list = read();

        return list.size();
    }

    public int count(String salon_name) {

        int sonuc = -1;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select count (salon_id) from sinema_salonlari where salon_name ='" + salon_name + "'";
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            sonuc = rs.getInt("count");

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 200 - " + e.getMessage());
        }

        return sonuc;

    }

    //combo doldurmak için kullanılan metot
    public String[] select() {
        String[] arr = new String[count()];
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select * from sinema_salonlari ";
            ResultSet rs = st.executeQuery(komut);
            int i = 0;

            while (rs.next()) {
                String salonlar_combo = rs.getInt("salon_id") + " | " + rs.getString("salon_name") + " | " + rs.getInt("koltuk_sayisi");
                int id = rs.getInt("salon_id");
                arr[i] = salonlar_combo;
                i++;
            }

            c.close();
            st.close();
            rs.close();

            return arr;

        } catch (SQLException e) {
            System.out.println("Hata kodu: 193 - " + e.getMessage());
        }

        return null;
    }

    public String search_string(int salon_id) {

        String name = null;
        LinkedList<sinema_salonlari> list = read();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getSalon_id() == salon_id) {
                name = list.get(i).getSalon_name();
                break;
            }
        }
        return name;
    }

    public int search_int(int salon_id) {
        int koltuk_sayisi = 0;
        LinkedList<sinema_salonlari> list = read();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getSalon_id() == salon_id) {
                koltuk_sayisi = list.get(i).getKoltuk_sayisi();
                break;
            }
        }
        return koltuk_sayisi;

    }

    /*
    //public String[] salonlar_combo_doldur() {
        String[] arr = new String[kac_tane_salon_var()];
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select * from sinema_salonlari ";
            ResultSet rs = st.executeQuery(komut);
            int i = 0;

            while (rs.next()) {
                String salonlar_combo = rs.getInt("salon_id") + " | " + rs.getString("salon_name") + " | " + rs.getInt("koltuk_sayisi");
                int id = rs.getInt("salon_id");
                arr[i] = salonlar_combo;
                i++;
            }

            c.close();
            st.close();
            rs.close();

            return arr;

        } catch (SQLException e) {
            System.out.println("Hata kodu: 193 - " + e.getMessage());
        }

        return null;
    }

    //public int kac_tane_salon_var() {
        int sonuc = -1;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select count (salon_id) from sinema_salonlari ";
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            sonuc = rs.getInt("count");

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 194 - " + e.getMessage());
        }

        return sonuc;
    }

    //public int koltuk_sayisi_getir(int id) {
        int koltuk_sayisi = 0;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select *  from sinema_salonlari where salon_id='" + id + "'";
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            koltuk_sayisi = rs.getInt("koltuk_sayisi");

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 195 - " + e.getMessage());
        }
        return koltuk_sayisi;
    }

    //public int sinema_salonlari_dao_ekle(sinema_salonlari s) {
        int sonuc = 0;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();

            int sonuc2 = salon_name_sayaci(s.getSalon_name());
            if (sonuc2 == 0) {
                String komut = "insert into sinema_salonlari (salon_name,koltuk_sayisi) values ('" + s.getSalon_name() + "','" + s.getKoltuk_sayisi() + "')";
                sonuc = st.executeUpdate(komut);
            } else if (sonuc2 > 0) {
                sonuc = -1;
            } else {
                sonuc = -2;
            }

            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu :196 - " + e.getMessage());
        }

        return sonuc;
    }

    //public String salon_adi_getir(int id) {
        String name = null;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select *  from sinema_salonlari where salon_id='" + id + "'";
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            name = rs.getString("salon_name");

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 197 - " + e.getMessage());
        }
        return name;
    }

    //public int sinema_salonlari_dao_sil(int id) {
        int sonuc = 0;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "delete from sinema_salonlari where salon_id=" + id;
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu :198 - " + e.getMessage());
        }

        return sonuc;

    }

    //public int sinema_salonlari_degistir(sinema_salonlari s) {
        int sonuc = 0;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();

            int sonuc2 = salon_name_sayaci(s.getSalon_name());
            if (sonuc2 == 0) {
                String komut = "update sinema_salonlari set  salon_name= '" + s.getSalon_name() + "', koltuk_sayisi = (" + s.getKoltuk_sayisi() + ")where salon_id =" + s.getSalon_id();
                sonuc = st.executeUpdate(komut);
            } else if (sonuc2 > 0) {
                sonuc = -1;
            } else {
                sonuc = -2;
            }

            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu :199 - " + e.getMessage());
        }

        return sonuc;
    }

    //public int salon_name_sayaci(String salon_name) {

        int sonuc = -1;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select count (salon_id) from sinema_salonlari where salon_name ='" + salon_name + "'";
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            sonuc = rs.getInt("count");

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 200 - " + e.getMessage());
        }

        return sonuc;

    }
     */
}
