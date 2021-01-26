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
            System.out.println("Hata kodu: 300  " + e.getMessage());;
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
            System.out.println("Hata kodu: 301  " + e.getMessage());;
        }
        return abone_type;
    }
}
