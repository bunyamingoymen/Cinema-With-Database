package DAO;

import entity.Center;
import entity.vizyondaki_filmler;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.LinkedList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import util.DBConnector;

public class vizyondaki_filmlerDAO implements IDAO {

    @Override
    public int create(Center nw) {
        int sonuc = 0;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "insert into vizyondaki_filmler (film_id,vizyondan_kalkis_tarihi,seans_sayisi) values ('" + nw.getVizyondaki_filmler().getFilm_id() + "','" + nw.getVizyondaki_filmler().getVizyondan_kalkis_tarihi() + "','" + nw.getVizyondaki_filmler().getSeans_sayisi() + "')";
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu :165 - " + e.getMessage());
        }

        return sonuc;
    }

    //sadece film_id ekler
    public int create(int film_id) {
        int sonuc = 0;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "insert into vizyondaki_filmler (film_id,vizyondan_kalkis_tarihi,seans_sayisi) values ('" + film_id + "','" + LocalDate.now() + "','" + 0 + "')";
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu :166 - " + e.getMessage());
        }

        return sonuc;
    }

    @Override
    public LinkedList read() {

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select *  from vizyondaki_filmler_tablo";
            ResultSet rs = st.executeQuery(komut);
            LinkedList<vizyondaki_filmler> list = new LinkedList<>();

            while (rs.next()) {
                vizyondaki_filmler v = new vizyondaki_filmler(rs.getInt("vizyondaki_film_id"), rs.getDate("vizyondan_kalkis_tarihi").toLocalDate(), rs.getInt("film_id"), rs.getString("film_name"), rs.getString("film_type"), rs.getInt("film_suresi"), rs.getString("ad") + " " + rs.getString("soyad"), rs.getFloat("kullanici_puani"));
                list.add(v);
            }

            c.close();
            st.close();
            rs.close();

            return list;

        } catch (SQLException e) {
            System.out.println("Hata kodu: 260 - " + e.getMessage());
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
            String komut = "update vizyondaki_filmler set vizyondan_kalkis_tarihi = '" + nw.getVizyondaki_filmler().getVizyondan_kalkis_tarihi() + "' where vizyondaki_film_id =" + nw.getVizyondaki_filmler().getVizyondaki_filmler_id();
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu :168 - " + e.getMessage());
        }
        return sonuc;
    }

    @Override
    public int delete(int vizyondaki_film_id) {
        int sonuc = 0;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "delete from vizyondaki_filmler where vizyondaki_film_id=" + vizyondaki_film_id;
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu :163 - " + e.getMessage());
        }

        return sonuc;

    }

    public int delete_film_id(int film_id) {
        int sonuc = 0;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "delete from vizyondaki_filmler where film_id=" + film_id;
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu :163 - " + e.getMessage());
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
            String komut = "select count (vizyondaki_film_id) from vizyondaki_filmler ";
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            sonuc = rs.getInt("count");

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 170 - " + e.getMessage());
        }

        return sonuc;
    }

//    public ObservableList<vizyondaki_filmler> select(ObservableList<vizyondaki_filmler> data) {
//        LinkedList<vizyondaki_filmler> list = read();
//        for (int i = 0; i < list.size(); i++) {
//
//            Button detay = new Button();
//            detay.setText("Detay");
//            detay.setStyle("-fx-background-color : #393351; -fx-background-radius :  20; -fx-text-fill: white");
//
//            data.addAll(FXCollections.observableArrayList(new vizyondaki_filmler(list.get(i).getFilm_name(), list.get(i).getFilm_type(), list.get(i).getFilm_suresi(), list.get(i).getYonetmen_ad_soyad(), list.get(i).getVizyondan_kalkis_tarihi(), list.get(i).getKullanici_puani(), detay)));
//        }
//
//        return data;
//    }

    public ObservableList<vizyondaki_filmler> select(ObservableList<vizyondaki_filmler> data, Label film_detay_film_id, Label film_detay_film_adi, Label film_detay_film_turu, Label film_detay_film_suresi, Label film_detay_yonetmen, Label film_detay_kalkis_tarihi, Label film_detay_kullanici_puani, AnchorPane pnl_vizyondaki_filmler, AnchorPane pnl_eski_filmler, AnchorPane pnl_film_detayi,Label film_detay_aldigi_odul_sayisi, Label film_detay_hangi_abone_turu, Label film_detay_kalksi_tarihi_oncesi,  Label film_detay_aldigi_odul_sayisi_oncesi, Label film_detay_hangi_abone_turu_oncesi, Label film_detay_id, Label film_detay_id_oncesi) {
        LinkedList<vizyondaki_filmler> list = read();

        for (int i = 0; i < list.size(); i++) {

            Button detay = new Button();
            detay.setText("Detay");
            detay.setStyle("-fx-background-color : #393351; -fx-background-radius :  20; -fx-text-fill: white");

            data.addAll(FXCollections.observableArrayList(new vizyondaki_filmler(list.get(i).getVizyondaki_filmler_id(), list.get(i).getFilm_id(), list.get(i).getFilm_name(), list.get(i).getFilm_type(), list.get(i).getFilm_suresi(), list.get(i).getYonetmen_ad_soyad(), list.get(i).getVizyondan_kalkis_tarihi(), list.get(i).getKullanici_puani(), detay, film_detay_film_id, film_detay_film_adi, film_detay_film_turu, film_detay_film_suresi, film_detay_yonetmen, film_detay_kalkis_tarihi, film_detay_kullanici_puani, pnl_vizyondaki_filmler, pnl_eski_filmler, pnl_film_detayi, film_detay_aldigi_odul_sayisi, film_detay_hangi_abone_turu, film_detay_kalksi_tarihi_oncesi,  film_detay_aldigi_odul_sayisi_oncesi, film_detay_hangi_abone_turu_oncesi, film_detay_id, film_detay_id_oncesi)));

        }

        return data;
    }

    //combo doldurmak için kullanılan select
    public String[] select_string() {
        String[] arr = new String[count()];
        LinkedList<vizyondaki_filmler> list = read();
        for (int i = 0; i < list.size(); i++) {
            String vizyondaki_filmler_combo = list.get(i).getVizyondaki_filmler_id() + " | " + list.get(i).getFilm_name() + " | " + list.get(i).getFilm_type() + " | " + list.get(i).getFilm_suresi() + " | " + list.get(i).getYonetmen_ad_soyad();
            arr[i] = vizyondaki_filmler_combo;
        }

        return arr;
    }

    //sadece vizyondaki_film_id'yi diziye aktarip gönderen metot
    public int[] select_int() {
        int[] arr = new int[count()];

        LinkedList<vizyondaki_filmler> list = read();

        for (int i = 0; i < list.size(); i++) {
            int vizyondaki_filmler = list.get(i).getVizyondaki_filmler_id();
            arr[i] = vizyondaki_filmler;
        }

        return arr;
    }

    public String search_string(int vizyondaki_film_id, int secim) {
        String sonuc = null;

        LinkedList<vizyondaki_filmler> list = read();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getVizyondaki_filmler_id() == vizyondaki_film_id) {
                switch (secim) {
                    case 1:
                        sonuc = list.get(i).getFilm_name();
                        break;
                    case 2:
                        sonuc = list.get(i).getFilm_type();
                        break;
                    case 3:
                        sonuc = list.get(i).getYonetmen_ad_soyad();
                        break;
                    default:
                        break;
                }
                break;
            }
        }

        return sonuc;
    }

    public int search_int(int id, int secim, int ust_secim) {
        int sonuc = 0;
        LinkedList<vizyondaki_filmler> list = read();
        OUTER:
        for (int i = 0; i < list.size(); i++) {
            switch (ust_secim) {
                case 1:
                    if (list.get(i).getVizyondaki_filmler_id() == id) {
                        switch (secim) {
                            //film_id'yi gönderir
                            case 1:
                                sonuc = list.get(i).getFilm_id();
                                break;
                            //film süesi gönderir
                            case 2:
                                sonuc = list.get(i).getFilm_suresi();
                                break;
                            //seans_sayisi_gonderir
                            case 3:
                                sonuc = list.get(i).getSeans_sayisi();
                                break;
                            case 4:
                                sonuc = list.get(i).getYonetmen_id();
                                break;
                            default:
                                System.out.println("Hata kodu: 206");
                                return -1;
                        }
                        break OUTER;
                    }
                    break;
                case 2:
                    //film_id'ye göre vizyondaki filmi gönderir
                    if (list.get(i).getFilm_id() == id) {
                        sonuc = list.get(i).getVizyondaki_filmler_id();
                        break;
                    }
                    break;
                default:
                    System.out.println("Hata kodu 207");
                    return -1;
            }
        }

        return sonuc;
    }

    //vizyondan_kalkis_tareihi ni gönderir
    public LocalDate search_localdate(int vizyondaki_film_id) {
        LocalDate kalkis = null;
        LinkedList<vizyondaki_filmler> list = read();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getVizyondaki_filmler_id() == vizyondaki_film_id) {
                kalkis = list.get(i).getVizyondan_kalkis_tarihi();
                break;
            }
        }

        return kalkis;
    }

    /*

    public vizyondaki_filmlerDAO() {
    }

    //public int vizyondaki_filmler_tamamen_sil(int id) {
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
            System.out.println("Hata kodu :163 - " + e.getMessage());
        }

        if (sonuc == 1 && sonuc2 == 1) {
            return 1;
        } else {
            return 0;
        }

    }

    //public int vizyondaki_filmler_sadece_vizyondan_sil(int id) {
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
            System.out.println("Hata kodu :164 - " + e.getMessage());
        }

        if ((sonuc == 1) && (sonuc_2 == 1)) {
            return 1;
        } else {
            return 0;
        }

    }
    
    //public LinkedList<vizyondaki_filmler> vizyondaki_filmler_hepsini_getir(){
        LinkedList list = new LinkedList<vizyondaki_filmler>();
        
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select *  from vizyondaki_filmler_tablo";
            ResultSet rs = st.executeQuery(komut);
            while(rs.next()){
                vizyondaki_filmler v = new vizyondaki_filmler( rs.getInt("vizyondaki_film_id"), rs.getDate("vizyondan_kalkis_tarihi").toLocalDate(), rs.getInt("film_id"), rs.getString("film_name"), rs.getString("film_type"), rs.getInt("film_suresi"), rs.getString("ad") + " " + rs.getString("soyad"), rs.getFloat("kullanici_puani"));
                list.add(v);
            }

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 260 - " + e.getMessage());
        }
        
        
        return list;
    }
    
    //public int vizyondaki_filmler_sil_eski_filmlere_ekleme(int film_id){
        int sonuc = 0;
        try{
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement sterDAO edao = new eski_filmlerDAO(); = c.createStatement();
            eski_filmlerDAO edao = new eski_filmlerDAO();
            String komut = "delete from vizyondaki_filmler where film_id=" + film_id;
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();
        }catch(SQLException e){
            System.out.println("Hata kodu :256 - " + e.getMessage());
        }
        
        return sonuc;
    }
    
    //public void vizyondaki_filmler_toplu_sil(LinkedList<Integer> list){
        for(int i = 0; i<list.size();i++){
            vizyondaki_filmler_sil_eski_filmlere_ekleme(list.get(i));
        }
    }

    //public int vizyondaki_filmler_dao_ekle(vizyondaki_filmler v) {
        int sonuc = 0;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "insert into vizyondaki_filmler (film_id,vizyondan_kalkis_tarihi,seans_sayisi) values ('" + v.getFilm_id() + "','" + v.getVizyondan_kalkis_tarihi() + "','" + v.getSeans_sayisi() + "')";
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu :165 - " + e.getMessage());
        }

        return sonuc;
    }

    //public int vizyondaki_filmler_dao_sadece_film_id_ekle(int film_id) {
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
            System.out.println("Hata kodu :166 - " + e.getMessage());
        }

        return sonuc;
    }

    //public ObservableList<vizyondaki_filmler> vizyondaki_filmler_select(ObservableList<vizyondaki_filmler> data) {

        try {

            ResultSet rs = ort();

            while (rs.next()) {
                String film_name = rs.getString("film_name");
                String film_type = rs.getString("film_type");
                int film_suresi = rs.getInt("film_suresi");
                String ad = rs.getString("ad");
                String soyad = rs.getString("soyad");
                LocalDate kalkis = rs.getDate("vizyondan_kalkis_tarihi").toLocalDate();
                int kullanici_puani = rs.getInt("kullanici_puani");

                Button detay = new Button();
                detay.setText("Detay");
                detay.setStyle("-fx-background-color : #393351; -fx-background-radius :  20; -fx-text-fill: white");

                data.addAll(FXCollections.observableArrayList(new vizyondaki_filmler(film_name, film_type, film_suresi, ad, soyad, kalkis, kullanici_puani, detay)));
            }

            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 167 - " + e.getMessage());
        }

        return data;
    }
    
    //Bu metodun işini read metodu yapmaktadır.
    //public ResultSet ort() {
        ResultSet rs = null;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select * from vizyondaki_filmler_tablo";
            rs = st.executeQuery(komut);

            c.close();
            st.close();
        } catch (SQLException ex) {
            System.out.println("Hata kodu: 254 - " + ex.getMessage());
        }

        return rs;

    }

    //public ObservableList<vizyondaki_filmler> vizyondaki_filmler_select_butonlu(ObservableList<vizyondaki_filmler> data, Label film_detay_film_id, Label film_detay_film_adi, Label film_detay_film_turu, Label film_detay_film_suresi, Label film_detay_yonetmen, Label film_detay_kalkis_tarihi, Label film_detay_kullanici_puani, AnchorPane pnl_vizyondaki_filmler, AnchorPane pnl_eski_filmler, AnchorPane pnl_film_detayi) {

        try {
            ResultSet rs = ort();

            while (rs.next()) {
                int vizyondaki_film_id = rs.getInt("vizyondaki_film_id");
                int film_id = rs.getInt("film_id");
                String film_name = rs.getString("film_name");
                String film_type = rs.getString("film_type");
                int film_suresi = rs.getInt("film_suresi");
                String ad = rs.getString("ad");
                String soyad = rs.getString("soyad");
                LocalDate kalkis = rs.getDate("vizyondan_kalkis_tarihi").toLocalDate();
                int kullanici_puani = rs.getInt("kullanici_puani");

                Button detay = new Button();
                detay.setText("Detay");
                detay.setStyle("-fx-background-color : #393351; -fx-background-radius :  20; -fx-text-fill: white");

                data.addAll(FXCollections.observableArrayList(new vizyondaki_filmler(vizyondaki_film_id, film_id, film_name, film_type, film_suresi, ad, soyad, kalkis, kullanici_puani, detay, film_detay_film_id, film_detay_film_adi, film_detay_film_turu, film_detay_film_suresi, film_detay_yonetmen, film_detay_kalkis_tarihi, film_detay_kullanici_puani, pnl_vizyondaki_filmler, pnl_eski_filmler, pnl_film_detayi)));
            }
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 233 - " + e.getMessage());
        }

        return data;
    }

    //public int vizyondaki_filmler_degistir(vizyondaki_filmler v, filmler f) {
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
            System.out.println("Hata kodu :168 - " + e.getMessage());
        }
        if ((sonuc1 == 1) && (sonuc2 == 1)) {
            return 1;
        } else {
            return 0;
        }

    }

    //public String[] vizyondaki_filmler_combo_doldur() {
        String[] arr = new String[kac_tane_vizyonda_film_var()];
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
                String vizyondaki_filmler_combo = rs.getInt("vizyondaki_film_id") + " | " + fdao.filmler_film_adi_getir(rs.getInt("film_id")) + " | " + fdao.filmler_film_type_getir(rs.getInt("film_id")) + " | " + fdao.filmler_film_suresi_getir(rs.getInt("film_id")) + " | " + ydao.yonetmenler_yonetmen_getir(fdao.filmler_yonetmen_id_getir(rs.getInt("film_id")));
                arr[i] = vizyondaki_filmler_combo;
                i++;
            }

            c.close();
            st.close();
            rs.close();

            return arr;

        } catch (SQLException e) {
            System.out.println("Hata kodu: 169 - " + e.getMessage());
        }

        return null;
    }

    //public int[] vizyondaki_filmler_dizi_doldur() {
        int[] arr = new int[kac_tane_vizyonda_film_var()];
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
                int vizyondaki_filmler = rs.getInt("vizyondaki_film_id");
                arr[i] = vizyondaki_filmler;
                i++;
            }

            c.close();
            st.close();
            rs.close();

            return arr;

        } catch (SQLException e) {
            System.out.println("Hata kodu: 252 - " + e.getMessage());
        }

        return null;
    }

    //public int kac_tane_vizyonda_film_var() {
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
            System.out.println("Hata kodu: 170 - " + e.getMessage());
        }

        return sonuc;
    }

    //public String film_adi_getir(int id) {
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
            System.out.println("Hata kodu: 171 - " + e.getMessage());
        }

        return film_adi;
    }

    //public int film_id_getir(int id) {
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
            System.out.println("Hata kodu: 172 - " + e.getMessage());
        }
        return film_id;
    }

    //public String film_type_getir(int id) {
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
            System.out.println("Hata kodu: 173 - " + e.getMessage());
        }

        return film_type;
    }

    //public int film_suresi_getir(int id) {
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
            System.out.println("Hata kodu: 174 - " + e.getMessage());
        }

        return film_suresi;
    }

    //public String yonetmen_getir(int id) {
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
            System.out.println("Hata kodu: 175 - " + e.getMessage());
        }

        return name_surname;
    }

    //public LocalDate vizyondan_kalkis_tarihi_getir(int id) {
        LocalDate kalkis = null;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select *  from vizyondaki_filmler where vizyondaki_film_id='" + id + "'";
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            kalkis = rs.getDate("vizyondan_kalkis_tarihi").toLocalDate();

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 176 - " + e.getMessage());
        }

        return kalkis;
    }

    //public int seans_sayisi_getir(int id) {
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
            System.out.println("Hata kodu: 178 - " + e.getMessage());
        }
        return seans_sayisi_getir;
    }

    //public int film_id_ile_vizyondaki_film_id_getir(int film_id) {
        int vizyondaki_film_id = 0;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select *  from vizyondaki_filmler where film_id=" + film_id;
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            vizyondaki_film_id = rs.getInt("vizyondaki_film_id");

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 152 - " + e.getMessage());
        }
        return vizyondaki_film_id;
    }

     */
}
