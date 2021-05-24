package DAO;

import entity.Center;
import entity.haberler;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import util.DBConnector;

public class haberlerDAO implements IDAO {

    @Override
    public int create(Center nw) {
        int sonuc = 0;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "insert into haberler (hangi_kullanici_turu, Title, Haber, Tarih, Haber_Kategorisi) values ('" + nw.getHaberler().getHangi_kullanici_turu() + "','" + nw.getHaberler().getTitle() + "','" + nw.getHaberler().getDuyuru() + "','" + nw.getHaberler().getTarih() + "','" + nw.getHaberler().getKategori() + "')";

            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu :140 - " + e.getMessage());
        }

        return sonuc;
    }

    @Override
    public LinkedList read() {
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select * from haberler";
            ResultSet rs = st.executeQuery(komut);

            LinkedList<haberler> list = new LinkedList<>();

            while (rs.next()) {
                int haber_id = rs.getInt("haber_id");
                int hangi_kullanıcı_turu = rs.getInt("hangi_kullanici_turu");
                String Title = rs.getString("Title");
                String Haber = rs.getString("Haber");
                String Tarih = rs.getString("Tarih");
                String Haber_Kategorisi = rs.getString("Haber_Kategorisi");

                haberler h = new haberler(haber_id, hangi_kullanıcı_turu, Title, Haber, Tarih, Haber_Kategorisi);
                list.add(h);
            }

            c.close();
            st.close();
            rs.close();

            return list;

        } catch (SQLException e) {
            System.out.println("Hata kodu: 136 - " + e.getMessage());
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
            String komut = "update haberler set hangi_kullanici_turu = " + nw.getHaberler().getHangi_kullanici_turu() + " , Title = '" + nw.getHaberler().getTitle() + "', Haber = '" + nw.getHaberler().getDuyuru() + "', Tarih = '" + nw.getHaberler().getTarih() + "', Haber_Kategorisi = '" + nw.getHaberler().getKategori() + "' where haber_id =" + nw.getHaberler().getId();
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();
        } catch (SQLException e) {
            System.out.println("Hata kodu :141 - " + e.getMessage());
        }
        return sonuc;
    }

    @Override
    public int delete(int haber_id) {
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
            System.out.println("Hata kodu :142 - " + e.getMessage());
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
            String komut = "select count (haber_id) from haberler ";
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            sonuc = rs.getInt("count");

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 139 - " + e.getMessage());
        }

        return sonuc;
    }

    public ObservableList<haberler> select(ObservableList<haberler> data, Pane haberler_sil_emin_misin_pane, Label haberler_silmekten_emin_haber_id) {

        LinkedList<haberler> list = read();
        for (int i = 0; i < list.size(); i++) {

            Button sil = new Button();
            sil.setText("Sil");
            sil.setStyle("-fx-background-color : #FA2C56; -fx-background-radius :  20; -fx-text-fill: white");

            data.addAll(FXCollections.observableArrayList(new haberler(list.get(i).getId(), list.get(i).getHangi_kullanici_turu(), list.get(i).getTitle(), list.get(i).getDuyuru(), list.get(i).getTarih(), list.get(i).getKategori(), sil, haberler_silmekten_emin_haber_id, haberler_sil_emin_misin_pane)));
        }

        return data;
    }

    public ObservableList<haberler> select(ObservableList<haberler> data, int kullanici_turu) {

        LinkedList<haberler> list = read();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getHangi_kullanici_turu() <= kullanici_turu) {
                data.addAll(FXCollections.observableArrayList(new haberler(list.get(i).getTitle(), list.get(i).getDuyuru(), list.get(i).getTarih(), list.get(i).getKategori())));
            }
        }

        return data;
    }

    //combo için kullanılan select
    public String[][] select() {
        String[][] arr = new String[count()][2];
        LinkedList<haberler> list = read();

        for (int i = 0; i < list.size(); i++) {
            String haberler_combo = list.get(i).getId() + " | " + list.get(i).getTitle() + " | " + list.get(i).getDuyuru();
            int id = list.get(i).getId();
            arr[i][0] = haberler_combo;
            arr[i][1] = String.valueOf(id);
        }
        return arr;

    }

    //Title, Haber, Tarih, Haber Kategorisi getirir
    public String search_string(int haber_id, int secim) {
        String sonuc = null;
        LinkedList<haberler> list = new LinkedList<>();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == haber_id) {
                switch (secim) {
                    case 1:
                        sonuc = list.get(i).getTitle();
                        break;
                    case 2:
                        sonuc = list.get(i).getDuyuru();
                        break;
                    case 3:
                        sonuc = list.get(i).getTarih();
                        break;
                    case 4:
                        sonuc = list.get(i).getKategori();
                        break;
                    default:
                        System.out.println("Hata kodu: 203");
                        return null;
                }
            }
        }

        return sonuc;
    }

    //sadece hangi kukkanıcyı getirir
    public int search_int(int haber_id) {
        int hangi_kullanici = -1;

        LinkedList<haberler> list = new LinkedList<>();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == haber_id) {
                hangi_kullanici = list.get(i).getHangi_kullanici_turu();
            }
        }

        return hangi_kullanici;
    }


    /*
    //public ObservableList<haberler> haberler_select(ObservableList<haberler> data, Pane haberler_sil_emin_misin_pane, Label haberler_silmekten_emin_haber_id) {

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
            System.out.println("Hata kodu: 136 - " + e.getMessage());
        }

        return data;
    }

    //public ObservableList<haberler> haberler_select(ObservableList<haberler> data, int kullanici_turu) {

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
            System.out.println("Hata kodu: 137 - " + e.getMessage());
        }

        return data;
    }

    //public String[][] haberler_combo_doldur() {
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
            System.out.println("Hata kodu: 138 - " + e.getMessage());
        }

        return null;
    }

    //public int kac_tane_haber_var() {
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
            System.out.println("Hata kodu: 139 - " + e.getMessage());
        }

        return sonuc;
    }

    //public int haberler_dao_ekle(haberler h) {
        int sonuc = 0;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "insert into haberler (hangi_kullanici_turu, Title, Haber, Tarih, Haber_Kategorisi) values ('" + h.getHangi_kullanici_turu() + "','" + h.getTitle() + "','" + h.getDuyuru() + "','" + h.getTarih() + "','" + h.getKategori() + "')";

            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu :140 - " + e.getMessage());
        }

        return sonuc;
    }

    //public int haberler_degistir(haberler h) {
        int sonuc = 0;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "update haberler set hangi_kullanici_turu = " + h.getHangi_kullanici_turu() + " , Title = '" + h.getTitle() + "', Haber = '" + h.getDuyuru() + "', Tarih = '" + h.getTarih() + "', Haber_Kategorisi = '" + h.getKategori() + "' where haber_id =" + h.getId();
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();
        } catch (SQLException e) {
            System.out.println("Hata kodu :141 - " + e.getMessage());
        }
        return sonuc;

    }

    //public int haberler_sil(int haber_id) {
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
            System.out.println("Hata kodu :142 - " + e.getMessage());
        }

        return sonuc;

    }

    //public int haberler_hangi_kullanici_getir(int haber_id) {
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
            System.out.println("Hata kodu: 143 - " + e.getMessage());
        }

        return hangi_kullanici;
    }

    //public String haberler_title_getir(int haber_id) {
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
            System.out.println("Hata kodu: 144 - " + e.getMessage());
        }

        return title;
    }

    //public String haberler_haber_getir(int haber_id) {
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
            System.out.println("Hata kodu: 145 - " + e.getMessage());
        }

        return haber;
    }

    //public String haberler_tarih_getir(int haber_id) {
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
            System.out.println("Hata kodu: 146 - " + e.getMessage());
        }

        return tarih;
    }

    //public String haberler_haber_kategorisi_getir(int haber_id) {
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
            System.out.println("Hata kodu: 147 - " + e.getMessage());
        }

        return kategori;
    }
     */
}
