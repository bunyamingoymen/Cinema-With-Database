package DAO;

import entity.filmler;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import util.DBConnector;

public class filmlerDAO {

    public int filmler_ekle_id_gonder(filmler f) {
        int id = 0;
        int sonuc = -1;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "insert into filmler (film_name,film_type,film_suresi,yonetmen_id,kullanici_puani) values ('" + f.getFilm_name() + "','" + f.getFilm_type() + "','" + f.getFilm_suresi() + "','" + f.getYonetmen_id() + "'," + f.getKullanici_puani() + ")";
            sonuc = st.executeUpdate(komut);

            if (sonuc != 1) {
                System.out.println("FilmlerDAO, filmler_vizytondaki_filmler, Hata kodu 130");
            } else {
                String komut2 = "select * from filmler order by film_id desc limit 1";
                ResultSet rs = st.executeQuery(komut2);
                rs.next();
                id = rs.getInt("film_id");
            }

            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 128 - " + e.getMessage());
        }

        return id;

    }

    public String filmler_film_adi_getir(int id) {
        String film_adi = null;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select * from filmler where film_id ='" + id + "'";
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            film_adi = rs.getString("film_name");

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 129 - " + e.getMessage());
        }

        return film_adi;
    }

    public String filmler_film_type_getir(int id) {
        String film_type = null;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select * from filmler where film_id ='" + id + "'";
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            film_type = rs.getString("film_type");

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 130 - " + e.getMessage());
        }

        return film_type;
    }

    public int filmler_film_suresi_getir(int id) {
        int film_suresi = 0;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select * from filmler where film_id ='" + id + "'";
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            film_suresi = rs.getInt("film_suresi");

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 131 - " + e.getMessage());
        }

        return film_suresi;
    }

    public int filmler_yonetmen_id_getir(int id) {
        int yonetmen_id = 0;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select * from filmler where film_id ='" + id + "'";
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            yonetmen_id = rs.getInt("yonetmen_id");

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 132 - " + e.getMessage());
        }

        return yonetmen_id;
    }

    public int filmler_dao_delete(int film_id) {
        int sonuc = 0;
        int sonuc2 = 0;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            int yonetmen_id = filmler_yonetmen_id_getir(film_id);
            String komut = "delete from filmler where film_id=" + film_id;
            sonuc = st.executeUpdate(komut);
            yonetmenlerDAO ydao = new yonetmenlerDAO();
            sonuc2 = ydao.yonetmenler_film_sayisi_azalt(yonetmen_id);
            c.close();
            st.close();
        } catch (SQLException e) {
            System.out.println("Hata kodu :133 - " + e.getMessage());
        }

        if (sonuc == 1 && sonuc2 == 1) {
            return 1;
        } else {
            return 0;
        }
    }
    
    public void filmler_toplu_sil(LinkedList<Integer> list){
        for(int i = 0;i <list.size();i++){
            filmler_dao_delete(list.get(i));
        }
    }

    public String[][] filmler_combo_doldur() {
        String[][] arr = new String[kac_tane_film_var()][2];
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select * from filmler ";
            ResultSet rs = st.executeQuery(komut);
            int i = 0;
            yonetmenlerDAO ydao = new yonetmenlerDAO();
            while (rs.next()) {
                String vizyondaki_filmler_combo = filmler_film_adi_getir(rs.getInt("film_id")) + " | " + filmler_film_type_getir(rs.getInt("film_id")) + " | " + filmler_film_suresi_getir(rs.getInt("film_id")) + " | " + ydao.yonetmenler_yonetmen_getir(filmler_yonetmen_id_getir(rs.getInt("film_id")));
                int id = rs.getInt("film_id");
                arr[i][0] = vizyondaki_filmler_combo;
                arr[i][1] = String.valueOf(id);
                i++;
            }

            c.close();
            st.close();
            rs.close();

            return arr;

        } catch (SQLException e) {
            System.out.println("Hata kodu: 134 - " + e.getMessage());
        }

        return null;
    }

    public int kac_tane_film_var() {
        int sonuc = -1;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select count (film_id) from filmler ";
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            sonuc = rs.getInt("count");

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 135 - " + e.getMessage());
        }

        return sonuc;
    }
    
        public int film_id_var_mi(int film_id) {
        int sonuc = -1;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select count (film_id) from filmler where film_id= " + film_id;
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            sonuc = rs.getInt("count");

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 262 - " + e.getMessage());
        }

        return sonuc;
    }

    public float kullanici_puani_getir(int id) {
        float kullanici_puani = (float) -1;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select * from filmler where film_id ='" + id + "'";
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            kullanici_puani = rs.getFloat("kullanici_puani");

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 177 - " + e.getMessage());
        }

        return kullanici_puani;
    }

    public int kullanici_puani_degsitir(int film_id, float kullanici_puani) {
        int sonuc = 0;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "update filmler set kullanici_puani = " + kullanici_puani + " where film_id =" + film_id;
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();
        } catch (SQLException e) {
            System.out.println("Hata kodu :239 - " + e.getMessage());
        }
        return sonuc;
    }
}
