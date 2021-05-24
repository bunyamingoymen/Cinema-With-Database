package DAO;

import entity.Center;
import entity.eski_filmler;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import util.DBConnector;

public class eski_filmlerDAO implements IDAO {

    @Override
    public int create(Center nw) {
        int sonuc = 0;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "insert into eski_filmler (film_id,hangi_aboneler_izleyebilir,aldigi_odul_sayisi) values ('" + nw.getEski_filmler().getFilm_id() + "','" + nw.getEski_filmler().getHangi_aboneler_izleyebilir() + "','" + nw.getEski_filmler().getAldigi_odul_sayisi() + "')";
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu :112 - " + e.getMessage());
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
            String komut = "insert into eski_filmler (film_id) values ('" + film_id + "')";
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu :114 - " + e.getMessage());
        }

        return sonuc;
    }

    @Override
    public LinkedList read() {
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select * from eski_filmler_tablo";
            ResultSet rs = st.executeQuery(komut);

            LinkedList<eski_filmler> list = new LinkedList<>();

            while (rs.next()) {
                String film_name = rs.getString("film_name");
                String film_type = rs.getString("film_type");
                int film_suresi = rs.getInt("film_suresi");
                String ad = rs.getString("ad");
                String soyad = rs.getString("soyad");
                int hangi = rs.getInt("hangi_aboneler_izleyebilir");
                int aldigi_odul_sayisi = rs.getInt("aldigi_odul_sayisi");
                float kullanici_puani = rs.getFloat("kullanici_puani");

                eski_filmler es = new eski_filmler(film_name, film_type, film_suresi, ad, soyad, hangi, aldigi_odul_sayisi, kullanici_puani);

                list.add(es);
            }

            c.close();
            st.close();
            rs.close();

            return list;

        } catch (SQLException e) {
            System.out.println("Hata kodu: 109 - " + e.getMessage());
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
            String komut = "update eski_filmler set hangi_aboneler_izleyebilir = " + nw.getEski_filmler().getHangi_aboneler_izleyebilir() + " , aldigi_odul_sayisi = " + nw.getEski_filmler().getAldigi_odul_sayisi() + " where eski_film_id =" + nw.getEski_filmler().getEski_film_id();
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();
        } catch (SQLException e) {
            System.out.println("Hata kodu :113 - " + e.getMessage());
        }
        return sonuc;
    }

    @Override
    public int delete(int eski_film_id) {
        int sonuc = 0;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "delete from eski_filmler where eski_film_id=" + eski_film_id;
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu :110 - " + e.getMessage());
        }

        return sonuc;
    }

    public void delete(LinkedList<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            delete(list.get(i));
        }
    }

    @Override
    public int count() {
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
            System.out.println("Hata kodu: 116 - " + e.getMessage());
        }

        return sonuc;
    }

    //admin kısmındaki select
    public ObservableList<eski_filmler> select(ObservableList<eski_filmler> data) {
        LinkedList<eski_filmler> list = read();
        for (int i = 0; i < list.size(); i++) {

            Button detay = new Button();
            detay.setText("Detay");
            detay.setStyle("-fx-background-color : #393351; -fx-background-radius :  20; -fx-text-fill: white");

            data.addAll(FXCollections.observableArrayList(new eski_filmler(list.get(i).getFilm_name(), list.get(i).getFilm_type(), list.get(i).getFilm_suresi(), list.get(i).getYonetmen_ad_soyad(), list.get(i).getHangi_aboneler_izleyebilir(), list.get(i).getAldigi_odul_sayisi(), list.get(i).getKullanici_puani(), detay)));
        }

        return data;
    }

    //abonelere özel kısımdaki select
    public ObservableList<eski_filmler> select(ObservableList<eski_filmler> data, int kullanici_turu) {
        LinkedList<eski_filmler> list = read();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getHangi_aboneler_izleyebilir() <= kullanici_turu) {

                Button detay = new Button();
                detay.setText("Detay");
                detay.setStyle("-fx-background-color : #393351; -fx-background-radius :  20; -fx-text-fill: white");

                data.addAll(FXCollections.observableArrayList(new eski_filmler(list.get(i).getFilm_name(), list.get(i).getFilm_type(), list.get(i).getFilm_suresi(), list.get(i).getYonetmen_ad_soyad(), list.get(i).getAldigi_odul_sayisi(), detay)));

            }
        }

        return data;
    }

    //combo doldurmak için kullanılan select
    public String[][] select() {
        String[][] arr = new String[count()][2];

        LinkedList<eski_filmler> list = read();

        filmlerDAO fdao = new filmlerDAO();
        yonetmenlerDAO ydao = new yonetmenlerDAO();

        for (int i = 0; i < list.size(); i++) {
            String eski_filmler_combo = list.get(i).getFilm_name() + " | " + list.get(i).getFilm_type() + " | " + list.get(i).getFilm_suresi() + " | " + list.get(i).getYonetmen_ad_soyad();
            int id = list.get(i).getEski_film_id();
            arr[i][0] = eski_filmler_combo;
            arr[i][1] = String.valueOf(id);
        }
        return arr;
    }

    //film_adi, film_type, yonetmen getirir
    public String search_string(int eski_film_id, int secim) {
        String sonuc = null;
        LinkedList<eski_filmler> list = read();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getEski_film_id() == eski_film_id) {
                switch (secim) {
                    //film_adi_getirir
                    case 1:
                        sonuc = list.get(i).getFilm_name();
                        break;
                    /*film_type getirir*/
                    case 2:
                        sonuc = list.get(i).getFilm_type();
                        break;
                    /*yonetmen getirir*/
                    case 3:
                        sonuc = list.get(i).getYonetmen_ad_soyad();
                        break;
                    default:
                        System.out.println("Hata kodu: 201");
                        return null;
                }
            }
        }

        return sonuc;
    }

    //yonetmen_id, film_suresi, hangi_abone, aldigi_odul_sayisi,film_id getirir
    public int search_int(int eski_film_id, int secim) {
        int sonuc = -1;
        LinkedList<eski_filmler> list = read();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getEski_film_id() == eski_film_id) {
                switch (secim) {
                    //yonetmen_id getirir
                    case 1:
                        sonuc = list.get(i).getYonetmen_id();
                        break;
                    /*film_suresi getirir*/
                    case 2:
                        sonuc = list.get(i).getFilm_suresi();
                        break;
                    /*hangi abone getirir*/
                    case 3:
                        sonuc = list.get(i).getHangi_aboneler_izleyebilir();
                        break;
                    /*aldigi_odul_sayisi getirir*/
                    case 4:
                        sonuc = list.get(i).getAldigi_odul_sayisi();
                        break;
                    /*film_id getirir*/
                    case 5:
                        sonuc = list.get(i).getFilm_id();
                        break;
                    default:
                        sonuc = -1;
                        System.out.println("Hata kodu: 200");
                        break;
                }
            }
        }

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select *  from eski_filmler_tablo where eski_film_id='" + eski_film_id + "'";
            ResultSet rs = st.executeQuery(komut);
            rs.next();

            switch (secim) {
                //yonetmen_id getirir
                case 1:
                    sonuc = rs.getInt("yonetmen_id");
                    break;
                /*film_suresi getirir*/
                case 2:
                    sonuc = rs.getInt("film_suresi");
                    break;
                /*hangi abone getirir*/
                case 3:
                    sonuc = rs.getInt("hangi_aboneler_izleyebilir");
                    break;
                /*aldigi_odul_sayisi getirir*/
                case 4:
                    sonuc = rs.getInt("aldigi_odul_sayisi");
                    break;
                /*film_id getirir*/
                case 5:
                    sonuc = rs.getInt("film_id");
                    break;
                default:
                    sonuc = -1;
                    System.out.println("Hata kodu: 200");
                    break;
            }

            c.close();
            st.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Hata kodu: 120 - " + e.getMessage());
        }

        return sonuc;

    }

    /*

    //public ObservableList<eski_filmler> eski_filmler_select(ObservableList<eski_filmler> data) {
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
                float kullanici_puani = rs.getFloat("kullanici_puani");

                Button detay = new Button();
                detay.setText("Detay");
                detay.setStyle("-fx-background-color : #393351; -fx-background-radius :  20; -fx-text-fill: white");

                data.addAll(FXCollections.observableArrayList(new eski_filmler(film_name, film_type, film_suresi, ad, soyad, hangi, aldigi_odul_sayisi, kullanici_puani, detay)));
            }

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 109 - " + e.getMessage());
        }

        return data;
    }

    //public ObservableList<eski_filmler> eski_filmler_select_abone_ozel(ObservableList<eski_filmler> data, int kullanici_turu) {
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select * from eski_filmler_tablo";
            ResultSet rs = st.executeQuery(komut);

            while (rs.next()) {
                if (rs.getInt("hangi_aboneler_izleyebilir") <= kullanici_turu) {

                }
                String film_name = rs.getString("film_name");
                String film_type = rs.getString("film_type");
                int film_suresi = rs.getInt("film_suresi");
                String ad = rs.getString("ad");
                String soyad = rs.getString("soyad");
                int aldigi_odul_sayisi = rs.getInt("aldigi_odul_sayisi");

                Button detay = new Button();
                detay.setText("Detay");
                detay.setStyle("-fx-background-color : #393351; -fx-background-radius :  20; -fx-text-fill: white");

                data.addAll(FXCollections.observableArrayList(new eski_filmler(film_name, film_type, film_suresi, ad, soyad, aldigi_odul_sayisi, detay)));
            }

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 226 - " + e.getMessage());
        }

        return data;
    }

    //public int eski_filmler_tamamen_sil(int id) {
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
            System.out.println("Hata kodu :110 - " + e.getMessage());
        }

        if (sonuc == 1 && sonuc2 == 1) {
            return 1;
        } else {
            return 0;
        }

    }

    //public int eski_filmler_sadece_eskiden_sil(int id) {
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
            System.out.println("Hata kodu :111 - " + e.getMessage());
        }

        if ((sonuc == 1) && (sonuc_2 == 1)) {
            return 1;
        } else {
            return 0;
        }

    }

    //public int eski_filmler_sil_vizyondaki_filmlere_ekleme(int film_id) {
        int sonuc = 0;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            vizyondaki_filmlerDAO vdao = new vizyondaki_filmlerDAO();
            String komut = "delete from eski_filmler where film_id=" + film_id;
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu :257 - " + e.getMessage());
        }

        return sonuc;
    }
    
    //public void eski_filmler_toplu_sil(LinkedList<Integer> list){
        for(int i = 0; i<list.size();i++){
            eski_filmler_sil_vizyondaki_filmlere_ekleme(list.get(i));
        }
    }

    //public int eski_filmler_dao_ekle(eski_filmler ed) {
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
            System.out.println("Hata kodu :112 - " + e.getMessage());
        }

        return sonuc;
    }

    //public int eski_filmler_degistir(eski_filmler ed) {
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
            System.out.println("Hata kodu :113 - " + e.getMessage());
        }
        if ((sonuc1 == 1) && (sonuc2 == 1)) {
            return 1;
        } else {
            return 0;
        }

    }

    //public int eski_filmler_dao_sadece_film_id_ekle(int film_id) {
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
            System.out.println("Hata kodu :114 - " + e.getMessage());
        }

        return sonuc;
    }

    //public String[][] eski_filmler_combo_doldur() {
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
            System.out.println("Hata kodu: 115 - " + e.getMessage());
        }

        return null;
    }

    //public int kac_tane_eski_film_var() {
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
            System.out.println("Hata kodu: 116 - " + e.getMessage());
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
            String komut = "select *  from eski_filmler where eski_film_id='" + id + "'";
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            film_adi = fdao.filmler_film_adi_getir(rs.getInt("film_id"));

            c.close();
            st.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Hata kodu: 117 - " + e.getMessage());
        }

        return film_adi;
    }

    //public String film_type_getir(int id) {
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
            System.out.println("Hata kodu: 118 - " + e.getMessage());
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
            String komut = "select *  from eski_filmler where eski_film_id='" + id + "'";
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            film_suresi = fdao.filmler_film_suresi_getir(rs.getInt("film_id"));

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 119 - " + e.getMessage());
        }

        return film_suresi;
    }

    //public int yonetmen_id_getir(int id) {
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
            System.out.println("Hata kodu: 120 - " + e.getMessage());
        }

        return yonetmen_id;
    }

    //public String yonetmen_getir(int id) {
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
            System.out.println("Hata kodu: 121 - " + e.getMessage());
        }

        return name_surname;
    }

    //public int hangi_abone_getir(int id) {
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
            System.out.println("Hata kodu: 122 - " + e.getMessage());
        }

        return hangi_abone;
    }

    //public int aldigi_odul_sayisi_getir(int id) {
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
            System.out.println("Hata kodu: 123 - " + e.getMessage());
        }

        return odul;
    }

    //public int film_id_getir(int id) {
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
            System.out.println("Hata kodu: 124 - " + e.getMessage());
        }
        return film_id;
    }

     */
}
