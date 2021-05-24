package DAO;

import entity.Center;
import entity.kampanyalar;
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

public class kampanyalarDAO implements IDAO {

    @Override
    public int create(Center nw) {
        int sonuc = 0;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "insert into kampanyalar (hangi_kullanici_turu, Title, Kampanya, Tarih, Kampanya_Kategorisi) values ('" + nw.getKampanyalar().getHangi_kullanici_turu() + "','" + nw.getKampanyalar().getTitle() + "','" + nw.getKampanyalar().getDuyuru() + "','" + nw.getKampanyalar().getTarih() + "','" + nw.getKampanyalar().getKategori() + "') ";

            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu :217" + e.getMessage());;
        }

        return sonuc;
    }

    @Override
    public LinkedList read() {
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select * from kampanyalar";
            ResultSet rs = st.executeQuery(komut);
            LinkedList<kampanyalar> list = new LinkedList<>();

            while (rs.next()) {

                kampanyalar k = new kampanyalar(rs.getInt("kampanya_id"), rs.getInt("hangi_kullanici_turu"), rs.getString("Title"), rs.getString("Kampanya"), rs.getString("Tarih"), rs.getString("Kampanya_Kategorisi"));

                list.add(k);

            }

            c.close();
            st.close();
            rs.close();

            return list;

        } catch (SQLException e) {
            System.out.println("Hata kodu: 213  " + e.getMessage());;
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
            String komut = "update kampanyalar set hangi_kullanici_turu = " + nw.getKampanyalar().getHangi_kullanici_turu() + " , Title = '" + nw.getKampanyalar().getTitle() + "', Kampanya = '" + nw.getKampanyalar().getDuyuru() + "', Tarih = '" + nw.getKampanyalar().getTarih() + "', Kampanya_Kategorisi = '" + nw.getKampanyalar().getKategori() + "' where kampanya_id =" + nw.getKampanyalar().getId();
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();
        } catch (SQLException e) {
            System.out.println("Hata kodu :218" + e.getMessage());;
        }
        return sonuc;
    }

    @Override
    public int delete(int kampanya_id) {
        int sonuc = 0;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "delete from kampanyalar where kampanya_id=" + kampanya_id;
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu :219" + e.getMessage());;
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
            String komut = "select count (kampanya_id) from kampanyalar ";
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            sonuc = rs.getInt("count");

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 216  " + e.getMessage());;
        }

        return sonuc;
    }

    public ObservableList<kampanyalar> select(ObservableList<kampanyalar> data, Pane kampanyalar_sil_emin_misin_pane, Label kampanyalar_silmekten_emin_kampanya_id) {
        LinkedList<kampanyalar> list = read();

        for (int i = 0; i < list.size(); i++) {

            Button sil = new Button();
            sil.setText("Sil");
            sil.setStyle("-fx-background-color : #FA2C56; -fx-background-radius :  20; -fx-text-fill: white");

            data.addAll(FXCollections.observableArrayList(new kampanyalar(list.get(i).getId(), list.get(i).getHangi_kullanici_turu(), list.get(i).getTitle(), list.get(i).getDuyuru(), list.get(i).getTarih(), list.get(i).getKategori(), sil, kampanyalar_sil_emin_misin_pane, kampanyalar_silmekten_emin_kampanya_id)));
        }

        return data;
    }

    public ObservableList<kampanyalar> select(ObservableList<kampanyalar> data, int kullanici_turu) {

        LinkedList<kampanyalar> list = new LinkedList<>();

        for (int i = 0; i < list.size(); i++) {

            if (list.get(i).getHangi_kullanici_turu() <= kullanici_turu) {
                data.addAll(FXCollections.observableArrayList(new kampanyalar(list.get(i).getTitle(), list.get(i).getDuyuru(), list.get(i).getTarih(), list.get(i).getKategori())));
            }

        }

        return data;
    }

    //combo doldurmak için kullanılan select
    public String[][] select() {

        LinkedList<kampanyalar> list = read();
        String[][] arr = new String[count()][2];

        for (int i = 0; i < list.size(); i++) {
            String kampanyalar_combo = list.get(i).getId() + " | " + list.get(i).getTitle() + " | " + list.get(i).getDuyuru();
            int id = list.get(i).getId();
            arr[i][0] = kampanyalar_combo;
            arr[i][1] = String.valueOf(id);
        }

        return arr;
    }

    //Title, Kampanya, Tarih ve Kategori getirir
    public String search_string(int kampanya_id, int secim) {
        String sonuc = null;
        LinkedList<kampanyalar> list = read();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == kampanya_id) {
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
                        System.out.println("Hata kodu: 204");
                        return null;
                }
            }
        }

        return sonuc;
    }

    //sadece hangi kullanıcı tüürnü getirir
    public int searc_int(int kampanya_id) {
        int hangi_kullanici = -1;

        LinkedList<kampanyalar> list = read();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == kampanya_id) {
                hangi_kullanici = list.get(i).getHangi_kullanici_turu();
            }
        }

        return hangi_kullanici;
    }

    /*

    //public ObservableList<kampanyalar> kampanyalar_select(ObservableList<kampanyalar> data, Pane kampanyalar_sil_emin_misin_pane, Label kampanyalar_silmekten_emin_kampanya_id) {

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select * from kampanyalar";
            ResultSet rs = st.executeQuery(komut);

            while (rs.next()) {
                int kampanya_id = rs.getInt("kampanya_id");
                int hangi_kullanıcı_turu = rs.getInt("hangi_kullanici_turu");
                String Title = rs.getString("Title");
                String Kampanya = rs.getString("Kampanya");
                String Tarih = rs.getString("Tarih");
                String Kampanya_Kategorisi = rs.getString("Kampanya_Kategorisi");
                Button sil = new Button();

                sil.setText("Sil");
                sil.setStyle("-fx-background-color : #FA2C56; -fx-background-radius :  20; -fx-text-fill: white");

                data.addAll(FXCollections.observableArrayList(new kampanyalar(kampanya_id, hangi_kullanıcı_turu, Title, Kampanya, Tarih, Kampanya_Kategorisi, sil, kampanyalar_sil_emin_misin_pane, kampanyalar_silmekten_emin_kampanya_id)));
            }

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 213  " + e.getMessage());;
        }

        return data;
    }

    //public ObservableList<kampanyalar> kampanyalar_select(ObservableList<kampanyalar> data, int kullanici_turu) {

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select * from kampanyalar";
            ResultSet rs = st.executeQuery(komut);

            while (rs.next()) {
                if (rs.getInt("hangi_kullanici_turu") <= kullanici_turu) {
                    String Title = rs.getString("Title");
                    String Kampanya = rs.getString("Kampanya");
                    String Tarih = rs.getString("Tarih");
                    String Kampanya_Kategorisi = rs.getString("Kampanya_Kategorisi");

                    data.addAll(FXCollections.observableArrayList(new kampanyalar(Title, Kampanya, Tarih, Kampanya_Kategorisi)));
                }

            }

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 214  " + e.getMessage());;
        }

        return data;
    }

    //public String[][] kampanyalar_combo_doldur() {
        String[][] arr = new String[kac_tane_kampanya_var()][2];
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select * from kampanyalar ";
            ResultSet rs = st.executeQuery(komut);
            int i = 0;

            while (rs.next()) {
                String kampanyalar_combo = rs.getInt("kampanya_id") + " | " + rs.getString("Title") + " | " + rs.getString("Kampanya");
                int id = rs.getInt("kampanya_id");
                arr[i][0] = kampanyalar_combo;
                arr[i][1] = String.valueOf(id);
                i++;
            }

            c.close();
            st.close();
            rs.close();

            return arr;

        } catch (SQLException e) {
            System.out.println("Hata kodu: 215  " + e.getMessage());;
        }

        return null;
    }

    //public int kac_tane_kampanya_var() {
        int sonuc = -1;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select count (kampanya_id) from kampanyalar ";
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            sonuc = rs.getInt("count");

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 216  " + e.getMessage());;
        }

        return sonuc;
    }

    //public int kampanyalar_dao_ekle(kampanyalar k) {
        int sonuc = 0;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "insert into kampanyalar (hangi_kullanici_turu, Title, Kampanya, Tarih, Kampanya_Kategorisi) values ('" + k.getHangi_kullanici_turu() + "','" + k.getTitle() + "','" + k.getDuyuru() + "','" + k.getTarih() + "','" + k.getKategori() + "') ";

            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu :217" + e.getMessage());;
        }

        return sonuc;
    }

    //public int kampanyalar_degistir(kampanyalar k) {
        int sonuc = 0;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "update kampanyalar set hangi_kullanici_turu = " + k.getHangi_kullanici_turu() + " , Title = '" + k.getTitle() + "', Kampanya = '" + k.getDuyuru() + "', Tarih = '" + k.getTarih() + "', Kampanya_Kategorisi = '" + k.getKategori() + "' where kampanya_id =" + k.getId();
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();
        } catch (SQLException e) {
            System.out.println("Hata kodu :218" + e.getMessage());;
        }
        return sonuc;

    }

    //public int kampanyalar_sil(int kampanya_id) {
        int sonuc = 0;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "delete from kampanyalar where kampanya_id=" + kampanya_id;
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu :219" + e.getMessage());;
        }

        return sonuc;

    }

    //public int kampanyalar_hangi_kullanici_getir(int kampanya_id) {
        int hangi_kullanici = -1;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select *  from kampanyalar where kampanya_id=" + kampanya_id;
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            hangi_kullanici = rs.getInt("hangi_kullanici_turu");

            c.close();
            st.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Hata kodu: 220  " + e.getMessage());;
        }

        return hangi_kullanici;
    }

    //public String kampanyalar_title_getir(int kampanya_id) {
        String title = null;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select *  from kampanyalar where kampanya_id=" + kampanya_id;
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            title = rs.getString("Title");

            c.close();
            st.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Hata kodu: 221  " + e.getMessage());;
        }

        return title;
    }

    //public String kampanyalar_kampanya_getir(int kampanya_id) {
        String kampanya = null;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select *  from kampanyalar where kampanya_id=" + kampanya_id;
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            kampanya = rs.getString("Kampanya");

            c.close();
            st.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Hata kodu: 222  " + e.getMessage());;
        }

        return kampanya;
    }

    //public String kampanyalar_tarih_getir(int kampanya_id) {
        String tarih = null;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select *  from kampanyalar where kampanya_id=" + kampanya_id;
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            tarih = rs.getString("Tarih");

            c.close();
            st.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Hata kodu: 223  " + e.getMessage());;
        }

        return tarih;
    }

    //public String kampanyalar_kampanya_kategorisi_getir(int kampanya_id) {
        String kategori = null;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select *  from kampanyalar where kampanya_id=" + kampanya_id;
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            kategori = rs.getString("Kampanya_Kategorisi");

            c.close();
            st.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Hata kodu: 224  " + e.getMessage());;
        }

        return kategori;
    }

     */
}
