/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import util.DBConnector;

/**
 *
 * @author bgoymen
 */
public class abonelerDAO {

    public int abonelik_turu_bul(int user_id) {
        int kac_tane = kac_tane_user_id_var(user_id);

        switch (kac_tane) {
            case -1:
                return -1;
            case 0:
                return 0;
            case 1:
                int abone_type = abone_type_getir(user_id);
                return abone_type;
            default:
                return -1;
        }
    }

    public int kac_tane_user_id_var(int user_id) {
        int sonuc = -1;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select count (user_id) from aboneler where user_id = " + user_id;
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            sonuc = rs.getInt("count");

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 100  " + e.getMessage());;
        }

        return sonuc;
    }

    public int abone_type_getir(int id) {
        int abone_type = 0;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select *  from aboneler where user_id='" + id + "'";
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            abone_type = rs.getInt("abone_type");

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 101  " + e.getMessage());;
        }
        return abone_type;
    }

    public int abonelik_turu_satin_al(int abonelik_turu, int user_id) {
        int user_id_sayisi = kac_tane_user_id_var(user_id);

        switch (user_id_sayisi) {
            case -1:
                return 0;
            case 0:
                return abonelik_turu_ekle(abonelik_turu, user_id);
            case 1:
                return abonelik_turu_guncelle(abonelik_turu, user_id);
            default:
                return 0;
        }

    }

    public int abonelik_turu_ekle(int abonelik_turu, int user_id) {
        int sonuc = 0;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();

            int kalan_ucretsiz_bilet_sayisi = 0;

            switch (abonelik_turu) {
                case 1:
                    kalan_ucretsiz_bilet_sayisi = 2;
                    break;
                case 2:
                    kalan_ucretsiz_bilet_sayisi = 3;
                    break;
                case 3:
                    kalan_ucretsiz_bilet_sayisi = 6;
                    break;
                default:
                    return 0;
            }
            String komut = "insert into aboneler (user_id,abone_type,kalan_ucretsiz_bilet_sayisi) values (" + user_id + "," + abonelik_turu + "," + kalan_ucretsiz_bilet_sayisi + ")";
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu :102" + e.getMessage());;
        }

        return sonuc;
    }

    public int abonelik_turu_guncelle(int abonelik_turu, int user_id) {
        int sonuc = 0;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            int kalan_ucretsiz_bilet_sayisi = 0;
            
            switch (abonelik_turu) {
                case 1:
                    kalan_ucretsiz_bilet_sayisi = 2;
                    break;
                case 2:
                    kalan_ucretsiz_bilet_sayisi = 3;
                    break;
                case 3:
                    kalan_ucretsiz_bilet_sayisi = 6;
                    break;
                default:
                    return 0;
            }
            String komut = "update aboneler set abone_type = " + abonelik_turu + ", kalan_ucretsiz_bilet_sayisi = "+ kalan_ucretsiz_bilet_sayisi +" where user_id =" + user_id;
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();
        } catch (SQLException e) {
            System.out.println("Hata kodu :103" + e.getMessage());
        }
        return sonuc;
    }

}
