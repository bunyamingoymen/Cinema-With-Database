package DAO;

import entity.Center;
import entity.filmler;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import util.DBConnector;

public class filmlerDAO implements IDAO {

    @Override
    public int create(Center nw) {
        //çoğu create metodunun aksine burasi dönüş değeri olarak 1 ya da 0 değil, film_id gönderir.
        int id = 0;
        int sonuc = -1;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "insert into filmler (film_name,film_type,film_suresi,yonetmen_id,kullanici_puani) values ('" + nw.getFilmler().getFilm_name() + "','" + nw.getFilmler().getFilm_type() + "','" + nw.getFilmler().getFilm_suresi() + "','" + nw.getFilmler().getYonetmen_id() + "'," + nw.getFilmler().getKullanici_puani() + ")";
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

    @Override
    public LinkedList read() {
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select * from filmler_tablo ";
            ResultSet rs = st.executeQuery(komut);
            LinkedList<filmler> list = new LinkedList<>();
            while (rs.next()) {
                int film_id = rs.getInt("film_id");
                String film_name = rs.getString("film_name");
                String film_type = rs.getString("film_type");
                int film_suresi = rs.getInt("film_suresi");
                int yonetmen_id = rs.getInt("yonetmen_id");
                int kullanici_puani = rs.getInt("kullanici_puani");
                String ad_soyad = rs.getString("ad") + " " + rs.getString("soyad");

                filmler f = new filmler(film_id, film_name, film_type, film_suresi, yonetmen_id, kullanici_puani, ad_soyad);

                list.add(f);
            }

            c.close();
            st.close();
            rs.close();

            return list;

        } catch (SQLException e) {
            System.out.println("Hata kodu: 134 - " + e.getMessage());
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
            String komut = "update filmler set film_name = '" + nw.getFilmler().getFilm_name() + "', film_type = '" + nw.getFilmler().getFilm_type() + " ', film_suresi=  " + nw.getFilmler().getFilm_suresi() + " , yonetmen_id= " + nw.getFilmler().getYonetmen_id() + "  where film_id=" + nw.getFilmler().getFilm_id();
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();
        } catch (SQLException e) {
            System.out.println("Hata kodu :239 - " + e.getMessage());
        }
        return sonuc;
    }

    //sadece kullanıcı puanını değiştirir. (gelen film_id'ye göre)    
    public int update(int film_id, float kullanici_puani) {
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

    @Override
    public int delete(int film_id) {
        int sonuc = 0;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "delete from filmler where film_id=" + film_id;
            sonuc = st.executeUpdate(komut);
            yonetmenlerDAO ydao = new yonetmenlerDAO();
            c.close();
            st.close();
        } catch (SQLException e) {
            System.out.println("Hata kodu :133 - " + e.getMessage());
        }

        return sonuc;
    }

    public void filmler_toplu_sil(LinkedList<Integer> list) {
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

    //gelen film_id'nin kaç tane olduğunu buluyor.
    public int count(int film_id) {
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

    //combo doldurmak için kullanılan select
    public String[][] select() {
        String[][] arr = new String[count()][2];

        LinkedList<filmler> list = read();

        for (int i = 0; i < list.size(); i++) {
            String vizyondaki_filmler_combo = list.get(i).getFilm_name() + " | " + list.get(i).getFilm_type() + " | " + list.get(i).getFilm_suresi() + " | " + list.get(i).getYonetmen_ad_soyad();
            int id = list.get(i).getFilm_id();
            arr[i][0] = vizyondaki_filmler_combo;
            arr[i][1] = String.valueOf(id);
        }

        return arr;
    }

    //film_id, film_type getirir
    public String search_string(int film_id, int secim) {
        String sonuc = null;
        LinkedList<filmler> list = read();

        for (int i = 0; i < list.size(); i++) {
            switch (secim) {
                //film_adi getirir
                case 1:
                    sonuc = list.get(i).getFilm_name();
                    break;
                /*film_type getirir*/
                case 2:
                    sonuc = list.get(i).getFilm_type();
                    break;
                default:
                    System.out.println("Hata kodu: -202");
                    return null;
            }
        }

        return sonuc;
    }

    //film_suresi, yonetmen_id getirir
    public int search_int(int film_id, int secim) {
        int sonuc = 0;

        LinkedList<filmler> list = read();

        for (int i = 0; i < list.size(); i++) {
            switch (secim) {
                //film_suresi getirir
                case 1:
                    sonuc = list.get(i).getFilm_suresi();
                    break;
                /*yonetmen_id getirir*/
                case 2:
                    sonuc = list.get(i).getYonetmen_id();
                    break;
                default:
                    System.out.println("Hata kodu: -202");
                    return -1;
            }
        }

        return sonuc;
    }

    //kullanici_puani getirir
    public float search_float(int film_id) {
        float kullanici_puani = (float) -1;
        LinkedList<filmler> list = read();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getFilm_id() == film_id) {
                kullanici_puani = (float) list.get(i).getKullanici_puani();
                break;
            }
        }

        return kullanici_puani;
    }

    /*

    //public int filmler_ekle_id_gonder(filmler f) {
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

    //public String filmler_film_adi_getir(int id) {
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

    //public String filmler_film_type_getir(int id) {
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

    //public int filmler_film_suresi_getir(int id) {
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

    //public int filmler_yonetmen_id_getir(int id) {
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
    
    //public float kullanici_puani_getir(int id) {
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

    //public int filmler_dao_delete(int film_id) {
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
    
    //public void filmler_toplu_sil(LinkedList<Integer> list){
        for(int i = 0;i <list.size();i++){
            filmler_dao_delete(list.get(i));
        }
    }

    //public String[][] filmler_combo_doldur() {
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

    //public int kac_tane_film_var() {
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
    
    //public int film_id_var_mi(int film_id) {
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



    //public int kullanici_puani_degsitir(int film_id, float kullanici_puani) {
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

     */
}
