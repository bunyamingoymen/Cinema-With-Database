package DAO;

import entity.Center;
import entity.yesil_olan;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import util.DBConnector;

public class yesil_olanDAO implements IDAO {

    @Override
    public int create(Center nw) {
        int sonuc = 0;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "insert into yesil_olan (koltuk_adi) values ('" + nw.getYesil_olan().getKoltuk_adi() + "')";
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu :159 - " + e.getMessage());
        }

        return sonuc;
    }

    @Override
    public LinkedList read() {
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select * from yesil_olan";
            ResultSet rs = st.executeQuery(komut);
            LinkedList<yesil_olan> list = new LinkedList<>();
            while (rs.next()) {
                 yesil_olan y = new yesil_olan(rs.getInt("yesil_olan_id"),rs.getString("koltuk_adi"));
                 list.add(y);
            }

            c.close();
            st.close();
            rs.close();
            
            return list;

        } catch (SQLException e) {
            System.out.println("Hata kodu: 229 - " + e.getMessage());
            
            return null;
        }
    }

    @Override
    public int update(Center nw) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        int sonuc = 0;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "delete from yesil_olan where yesil_olan_id = " + id;
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu :160 - " + e.getMessage());
        }

        return sonuc;
    }

    public int delete(String koltuk_adi) {
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

    public int delete() {
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

    @Override
    public int count() {
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

    //bütün yeşil olanları toplu gönderen select
    public String[] select() {
        String[] arr = new String[count()];
        LinkedList<yesil_olan> list = read();
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i).getKoltuk_adi();
        }

        return arr;
    }

    /*

    public yesil_olanDAO() {
    }

    //public int yesil_olan_dao_ekle(String koltuk_adi) {
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

    //public int yesil_olan_dao_sil(String koltuk_adi) {
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

    //public int kac_tane_yesil_olan_var() {
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

    //public int yesil_olan_dao_toplu_sil() {
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

    //public String[] yesil_olanlar_toplu_gonder() {
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

     */
}
