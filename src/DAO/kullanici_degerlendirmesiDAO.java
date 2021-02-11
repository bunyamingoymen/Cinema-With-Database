package DAO;

import entity.kullanici_degerlendirmesi;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import util.DBConnector;

public class kullanici_degerlendirmesiDAO {

    public int kullanici_degerlendirmesi_yeni_degerlendirme(kullanici_degerlendirmesi k) {
        int sonuc = daha_once_degerlendirilmis_mi(k.getUser_id(), k.getFilm_id());

        switch (sonuc) {
            case -1:
                return -1;
            case 0:
                int sonuc2 = kullanici_degerlendirmesi_ekle(k);
                int sonuc3 = ortalama_yazdir(k.getFilm_id());
                if (sonuc2 == 1 && sonuc3 ==1) {
                    return 1;
                } else {
                    return -3;
                }
            case 1:
                int sonuc4 = kullanici_degerlendirmesi_guncelle(k);
                int sonuc5 = ortalama_yazdir(k.getFilm_id());
                if (sonuc4 == 1 && sonuc5 == 1) {
                    return 2;
                } else {
                    return -4;
                }
            default:
                return -2;
        }
    }

    public int daha_once_degerlendirilmis_mi(int user_id, int film_id) {

        int sonuc = -1;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select count(kullanici_Degerlendirme_id) from kullanici_degerlendirmesi where user_id =" + user_id + " and film_id =" + film_id;
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            sonuc = rs.getInt("count");

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 235 - " + e.getMessage());
        }

        return sonuc;
    }

    public int kullanici_degerlendirmesi_ekle(kullanici_degerlendirmesi k) {
        int sonuc = 0;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "insert into kullanici_degerlendirmesi (user_id,film_id,degerlendirme) values (" + k.getUser_id() + "," + k.getFilm_id() + "," + k.getDegerlendirme() + ")";
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu :236 - " + e.getMessage());
        }

        return sonuc;
    }

    public int kullanici_degerlendirmesi_guncelle(kullanici_degerlendirmesi k) {
        int sonuc = 0;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "update kullanici_degerlendirmesi set degerlendirme = " + k.getDegerlendirme() + " where user_id =" + k.getUser_id() + "and film_id = " + k.getFilm_id();
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();
        } catch (SQLException e) {
            System.out.println("Hata kodu :237 - " + e.getMessage());
        }
        return sonuc;
    }

    public float ortalama_hesapla(int film_id) {
        float sonuc = 0;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select degerlendirme from kullanici_degerlendirmesi where film_id = " + film_id;
            ResultSet rs = st.executeQuery(komut);
            int toplam = 0;
            int sayac = 0;
            while (rs.next()) {
                toplam = toplam + rs.getInt("degerlendirme");
                sayac++;
            }

            sonuc = toplam / sayac;

            c.close();
            st.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Hata kodu :238 - " + e.getMessage());
        }
        return sonuc;
    }
    
    public int ortalama_yazdir(int film_id){
        float ortalama = ortalama_hesapla(film_id);
        
        filmlerDAO fdao = new filmlerDAO();
        
        int sonuc = fdao.kullanici_puani_degsitir(film_id, ortalama);
        
        
        return sonuc;
    }
}
