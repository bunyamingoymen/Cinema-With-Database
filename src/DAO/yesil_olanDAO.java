package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import util.DBConnector;

public class yesil_olanDAO {

    public yesil_olanDAO() {
    }

    public int yesil_olan_dao_ekle(String koltuk_adi) {
        int sonuc = 0;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "insert into yesil_olan (koltuk_adi) values ('" + koltuk_adi + "')";
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu :159 - " + e.getMessage());
        }

        return sonuc;
    }

    public int yesil_olan_dao_sil(String koltuk_adi) {
        int sonuc = 0;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "delete from yesil_olan where koltuk_adi =  '" + koltuk_adi + "'";
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu :160 - " + e.getMessage());
        }

        return sonuc;
    }

    public int kac_tane_yesil_olan_var() {
        int sonuc = -1;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select count (yesil_olan_id) from yesil_olan";
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            sonuc = rs.getInt("count");

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 161 - " + e.getMessage());
        }

        return sonuc;
    }

    public int yesil_olan_dao_toplu_sil() {
        int sonuc = 0;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "delete from yesil_olan";
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu :162 - " + e.getMessage());
        }

        return sonuc;
    }

    public String[] yesil_olanlar_toplu_gonder() {
        String[] arr = new String[kac_tane_yesil_olan_var()];
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select * from yesil_olan";
            ResultSet rs = st.executeQuery(komut);
            int i = 0;
            while (rs.next()) {
                arr[i] = rs.getString("koltuk_adi");
                i++;
            }

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 229 - " + e.getMessage());
        }

        return arr;
    }

}
