package DAO;

import entity.Center;
import entity.aboneler;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import util.DBConnector;

public class abonelerDAO implements IDAO {

    @Override
    public int create(Center nw) {
        int sonuc = 0;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();

            int kalan_ucretsiz_bilet_sayisi = 0;

            switch (nw.getAboneler().getAbone_type()) {
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
            String komut = "insert into aboneler (user_id,abone_type,kalan_ucretsiz_bilet_sayisi) values (" + nw.getAboneler().getUser_id() + "," + nw.getAboneler().getAbone_type() + "," + kalan_ucretsiz_bilet_sayisi + ")";
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu :102 - " + e.getMessage());
        }

        return sonuc;
    }

    @Override
    public LinkedList read() {
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select * from aboneler";
            ResultSet rs = st.executeQuery(komut);

            LinkedList<aboneler> list = new LinkedList<>();

            while (rs.next()) {
                int abone_id = rs.getInt("abone_id");
                int user_id = rs.getInt("user_id");
                int abone_type = rs.getInt("abone_type");
                int kalan_ucretsiz_bilet_sayisi = rs.getInt("kalan_ucretsiz_bilet_sayisi");
                aboneler a = new aboneler(abone_id, user_id, abone_type, kalan_ucretsiz_bilet_sayisi);
                list.add(a);

            }

            c.close();
            st.close();
            rs.close();

            return list;

        } catch (SQLException e) {
            System.out.println("Hata Kod: 103 - " + e.getMessage());

            return null;
        }
    }

    //abone type getirir
    public int read_abone_type(int user_id) {
        int abone_type = 0;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "select *  from aboneler where user_id='" + user_id + "'";
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            abone_type = rs.getInt("abone_type");

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 101 - " + e.getMessage());
        }
        return abone_type;
    }

    //kalan ücretsiz bilet sayisini getirir
    public int read_bilet_sayisi(int user_id) {
        int sonuc = 0;

        int abonelik_turu = search(user_id);

        switch (abonelik_turu) {
            case 0:
                return 0;
            case -1:
                return -1;
            default:
                try {
                    DBConnector d = new DBConnector();
                    Connection c = d.connect();
                    Statement st = c.createStatement();
                    String komut = "select kalan_ucretsiz_bilet_sayisi from aboneler where user_id=" + user_id;
                    ResultSet rs = st.executeQuery(komut);

                    rs.next();
                    sonuc = rs.getInt("kalan_ucretsiz_bilet_sayisi");

                    c.close();
                    st.close();
                    rs.close();

                } catch (SQLException e) {
                    System.out.println("Hata kodu :228 - " + e.getMessage());
                }

                return sonuc;
        }
    }

    @Override
    public int update(Center nw) {
        int sonuc = 0;
        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            int kalan_ucretsiz_bilet_sayisi = 0;

            switch (nw.getAboneler().getAbone_type()) {
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
            String komut = "update aboneler set abone_type = " + nw.getAboneler().getAbone_type() + ", kalan_ucretsiz_bilet_sayisi = " + kalan_ucretsiz_bilet_sayisi + " where user_id =" + nw.getAboneler().getUser_id();
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();
        } catch (SQLException e) {
            System.out.println("Hata kodu :103 - " + e.getMessage());
        }
        return sonuc;
    }

    //kullanıcı yönetimi kısmındaki bölüm
    public int update(int abonelik_turu, int user_id) {
        int sonuc = 0;
        int kalan_ucretsiz_bilet_sayisi = 0;
        int eski_abonelik_turu = search(user_id);
        switch (abonelik_turu) {
            case 0:
                kalan_ucretsiz_bilet_sayisi = 0;
                sonuc = delete(user_id);
                return sonuc;
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
        Center nw = new Center(user_id, abonelik_turu);
        if (eski_abonelik_turu == 0) {
            sonuc = create(nw);
        } else if (eski_abonelik_turu == 1 || eski_abonelik_turu == 2 || eski_abonelik_turu == 3) {
            sonuc = update(nw);
        }

        return sonuc;
    }

    //kalan ücretsiz bilet sayısını düşürür.
    public int update(int user_id) {
        int sonuc = 0;
        int kalan_ucretsiz_bilet_sayisi = read_bilet_sayisi(user_id);
        switch (kalan_ucretsiz_bilet_sayisi) {
            case -1: //Hata çıktı anlamıan gelir
                return -2;
            case 0: //bilet hakkı yok anlamına gelir
                return -1;
            default: // bilet hakkı var ve düşürülme uygulanıyor.
                try {

                    DBConnector d = new DBConnector();
                    Connection c = d.connect();
                    Statement st = c.createStatement();
                    int guncellenen_bilet_hakki = kalan_ucretsiz_bilet_sayisi - 1;
                    String komut = "update aboneler set kalan_ucretsiz_bilet_sayisi=" + guncellenen_bilet_hakki + "where user_id = " + user_id;
                    sonuc = st.executeUpdate(komut);

                    c.close();
                    st.close();

                } catch (SQLException e) {
                    System.out.println("Hata kodu: 188 - " + e.getMessage());
                }
                return sonuc;
        }

    }

    @Override
    public int delete(int user_id) {
        int sonuc = 0;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "delete from aboneler where user_id=" + user_id;
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu :225 - " + e.getMessage());
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
            String komut = "select count (user_id) from aboneler";
            ResultSet rs = st.executeQuery(komut);
            rs.next();
            sonuc = rs.getInt("count");

            c.close();
            st.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu: 100 - " + e.getMessage());
        }

        return sonuc;
    }

    //kaç tane user_id'nin olduğunu buluyor
    public int count(int user_id) {
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
            System.out.println("Hata kodu: 100 - " + e.getMessage());
        }

        return sonuc;
    }

    //abone type getirir. Ondan önce kontrol yapar
    public int search(int user_id) {
        int se = count(user_id);

        switch (se) {
            case -1:
                return -1;
            case 0:
                return 0;
            case 1:
                int abone_type = read_abone_type(user_id);
                return abone_type;
            default:
                return -1;
        }
    }

    public int buy(Center nw) {
        int user_id_sayisi = count(nw.getAboneler().getUser_id());

        switch (user_id_sayisi) {
            case -1:
                System.out.println("yanlis");
                return 0;
            case 0:
                System.out.println("yanlis");
                return create(nw);
            case 1:
                System.out.println("doğru");
                return update(nw);
            default:
                return 0;
        }

    }

    /*

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
            System.out.println("Hata kodu: 100 - " + e.getMessage());
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
            System.out.println("Hata kodu: 101 - " + e.getMessage());
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
            System.out.println("Hata kodu :102 - " + e.getMessage());
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
            String komut = "update aboneler set abone_type = " + abonelik_turu + ", kalan_ucretsiz_bilet_sayisi = " + kalan_ucretsiz_bilet_sayisi + " where user_id =" + user_id;
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();
        } catch (SQLException e) {
            System.out.println("Hata kodu :103 - " + e.getMessage());
        }
        return sonuc;
    }

    public int abonelik_turu_guncelle_kullanici_yonetimi(int abonelik_turu, int user_id) {
        int sonuc = 0;
        int kalan_ucretsiz_bilet_sayisi = 0;
        int eski_abonelik_turu = abonelik_turu_bul(user_id);
        switch (abonelik_turu) {
            case 0:
                kalan_ucretsiz_bilet_sayisi = 0;
                sonuc = aboneler_sil(user_id);
                return sonuc;
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
        if (eski_abonelik_turu == 0) {
            sonuc = abonelik_turu_ekle(abonelik_turu, user_id);
        } else if (eski_abonelik_turu == 1 || eski_abonelik_turu == 2 || eski_abonelik_turu == 3) {
            sonuc = abonelik_turu_guncelle(abonelik_turu, user_id);
        }

        return sonuc;
    }

    public int aboneler_sil(int user_id) {
        int sonuc = 0;

        try {
            DBConnector d = new DBConnector();
            Connection c = d.connect();
            Statement st = c.createStatement();
            String komut = "delete from aboneler where user_id=" + user_id;
            sonuc = st.executeUpdate(komut);

            c.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Hata kodu :225 - " + e.getMessage());
        }

        return sonuc;

    }

    public int kalan_ucretsiz_bilet_sayisi(int user_id) {
        int sonuc = 0;

        int abonelik_turu = abonelik_turu_bul(user_id);

        switch (abonelik_turu) {
            case 0:
                return 0;
            case -1:
                return -1;
            default:
                try {
                    DBConnector d = new DBConnector();
                    Connection c = d.connect();
                    Statement st = c.createStatement();
                    String komut = "select kalan_ucretsiz_bilet_sayisi from aboneler where user_id=" + user_id;
                    ResultSet rs = st.executeQuery(komut);

                    rs.next();
                    sonuc = rs.getInt("kalan_ucretsiz_bilet_sayisi");

                    c.close();
                    st.close();
                    rs.close();

                } catch (SQLException e) {
                    System.out.println("Hata kodu :228 - " + e.getMessage());
                }

                return sonuc;
        }

    }

    public int ucretsiz_bilet_sayisi_dusur(int user_id) {
        int sonuc = 0;
        int kalan_ucretsiz_bilet_sayisi = kalan_ucretsiz_bilet_sayisi(user_id);
        switch (kalan_ucretsiz_bilet_sayisi) {
            case -1: //Hata çıktı anlamıan gelir
                return -2;
            case 0: //bilet hakkı yok anlamına gelir
                return -1;
            default: // bilet hakkı var ve düşürülme uygulanıyor.
                try {

                    DBConnector d = new DBConnector();
                    Connection c = d.connect();
                    Statement st = c.createStatement();
                    int guncellenen_bilet_hakki = kalan_ucretsiz_bilet_sayisi - 1;
                    String komut = "update aboneler set kalan_ucretsiz_bilet_sayisi=" + guncellenen_bilet_hakki + "where user_id = " + user_id;
                    sonuc = st.executeUpdate(komut);

                    c.close();
                    st.close();

                } catch (SQLException e) {
                    System.out.println("Hata kodu: 188 - " + e.getMessage());
                }
                return sonuc;
        }

    }

     */
}
