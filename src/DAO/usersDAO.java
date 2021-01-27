package DAO;

import entity.users;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import util.DBConnector;

/*
Bu sınıfın asıl amacı dosyada ki verileri okumak ve dosyaya yeni veri yazmak.
 */
public class usersDAO {

    public usersDAO() {

    }

    //user tablosuna veri eklemek içim kullandığımız method
    public int user_ekle(users User) {

        int sonuc = -1;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "insert into users (user_name,user_mail,user_password,user_type) values ('" + User.getUser_name() + "','" + User.getUser_mail() + "','" + User.getUser_password() + "','" + User.getUser_type() + "')";
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();
        } catch (SQLException e) {
            System.out.println("Hata kodu: 179" + e.getMessage());;
        }

        return sonuc;

    }

    //user tablosundan veri okumak için yazılan method
    public int user_giris_kontrol(String user_name, String password) {

        int kullanici_adi_var_mi = user_name_sayaci(user_name);

        switch (kullanici_adi_var_mi) {

            case 1: //girilen kullanıcı adından 1 tane var ve şifre kontrolü başlıyor.
                return user_password_control(user_name, password);
            case 0: //kullanıcı adı yok
                int mail_var_mi = user_mail_sayaci(user_name);//Kullanıcı adı yerine Mail adresi yazılmış olabilir. Bu sebeple maili de kullanıcı adı gibi kontrol edioyruz.
                switch (mail_var_mi) {
                    case 1:
                        return user_password_control(user_name, password);
                    case 0:
                        return 0; //mail  yok
                    default:
                        return -1;  //birdem fazla mail var. Bu yüzden hata meydana geldi.

                }
            default:
                return -1; //birdem fazla kullanıcı adı var. Bu yüzden hata meydana geldi.

        }

    }

    //bu methodumuz user tablosuna yazmak için
    public void user_degis(users User) {

    }

    public int user_password_control(String user_name, String password) {
        int user_id = user_id(user_name);
        String sql_password = user_password(user_id);
        if (sql_password == null) {
            return -2; //kullanici adı 1 tane var ama kullanıcının şifesi Veri tabanında yok. Hata meydana geldi.
        } else if (sql_password.equals(password)) {
            bilgi_ekle(user_id);
            return user_id; //girilen değerler doğru ve kullanının bilgilerini gönderiyoruz.
        } else {
            return 0; //girilen şifre yanlış
        }
    }

    public int user_id(String user_name) {
        int sonuc = -1;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select * from users where user_name='" + user_name + "'";
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            sonuc = rs.getInt("user_id");

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 180  " + e.getMessage());;
        }

        return sonuc;
    }

    public int user_name_sayaci(String user_name) {

        int sonuc = -1;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select count (user_id) from users where user_name='" + user_name + "'";
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            sonuc = rs.getInt("count");

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 181  " + e.getMessage());;
        }

        return sonuc;

    }

    public int user_mail_sayaci(String user_mail) {

        int sonuc = -1;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select count (user_id) from users where user_mail='" + user_mail + "'";
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            sonuc = rs.getInt("count");

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 182  " + e.getMessage());;
        }

        return sonuc;

    }

    public String user_password(int id) {

        String password = null;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select * from users where user_id=" + id;
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            password = rs.getString("user_password");

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 183  " + e.getMessage());;
        }

        return password;
    }

    public boolean user_name_control(String user_name) {
        boolean a = false;
        int sonuc = user_name_sayaci(user_name);

        if (sonuc == 0) {
            a = true;
        }

        return a;
    }

    public boolean user_mail_control(String user_mail) {
        boolean a = false;
        int sonuc = user_mail_sayaci(user_mail);

        if (sonuc == 0) {
            a = true;
        }

        return a;
    }

    public int bilgi_ekle(int id) {
        int sonuc = -1;
        bilgi_sil();

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "insert into bilgi (giren_user_id) values ('" + id + "')";
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 184" + e.getMessage());;
        }

        return sonuc;
    }

    public int bilgi_sil() {
        int sonuc = 0;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "delete from bilgi ";
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();
        } catch (SQLException e) {
            System.out.println("Hata kodu :185" + e.getMessage());;
        }
        return sonuc;
    }

    public int bilgi_oku() {
        int bilgi = 0;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select * from bilgi";
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            bilgi = rs.getInt("giren_user_id");

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 186" + e.getMessage());;
        }

        return bilgi;
    }

    public int user_type_getir(int user_id) {
        int user_type = 0;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();

            String komut = "select * from users where user_id=" + user_id;
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            user_type = rs.getInt("user_type");

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 187" + e.getMessage());;
        }

        return user_type;
    }

    public int user_guncelle(users u) {
        int sonuc = 0;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();

            String komut = "update users set user_name='" + u.getUser_name() + "', user_mail='" + u.getUser_mail() + "', user_password = '" + u.getUser_password() + "', user_type = '" + u.getUser_type() + "' where user_id = " + u.getUser_id();
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 188" + e.getMessage());;
        }

        return sonuc;
    }

    public int kac_tane_user_var() {
        int sonuc = -1;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select count (user_id) from users";
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            sonuc = rs.getInt("count");

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 189  " + e.getMessage());;
        }

        return sonuc;
    }

    public String user_name_getir(int id) {
        String name = null;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select *  from users where user_id='" + id + "'";
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            name = rs.getString("user_name");

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 190  " + e.getMessage());;
        }

        return name;
    }

    public String user_mail_getir(int id) {
        String name = null;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select *  from users where user_id='" + id + "'";
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            name = rs.getString("user_mail");

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 191  " + e.getMessage());;
        }

        return name;
    }

    public String user_password_getir(int id) {
        String name = null;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select *  from users where user_id='" + id + "'";
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            name = rs.getString("user_password");

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 192  " + e.getMessage());;
        }

        return name;
    }

}
