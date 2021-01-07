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
public class satin_alinan_biletlerDAO {

    public String[] satin_alinan_biletler_satyin_alinanlari_gonder(int seans_id) {
        
        String arr[] = new String[kac_tane_satin_alinan_bilet_var(seans_id)];

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select * from satin alinan biletler where seans_id = " + String.valueOf(seans_id);
            ResultSet rs = st.executeQuery(komut);
            int i = 0;
            while (rs.next()) {
                String koltuk_name = rs.getString("koltuk_name");
                arr[i] = koltuk_name;
                i++;
            }

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 197  " + e.getMessage());;
        }

        return arr;
    }

    public int kac_tane_satin_alinan_bilet_var(int seans_id) {
        int sonuc = -1;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select count (satin_alinan_bilet_id) from satin_alinan_biletler where seans_id = " + seans_id;
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            sonuc = rs.getInt("count");

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 196  " + e.getMessage());;
        }

        return sonuc;
    }
}
