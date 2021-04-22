package Code_Admin;

import DAO.actorDAO;
import DAO.eski_filmlerDAO;
import DAO.haberlerDAO;
import DAO.kampanyalarDAO;
import DAO.sinema_salonlariDAO;
import DAO.usersDAO;
import DAO.vizyondaki_filmlerDAO;
import DAO.yonetmenlerDAO;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class Par extends Variables {

    public void home_page() {
        vizyondaki_filmlerDAO vdao = new vizyondaki_filmlerDAO();
        vizyondaki_film_sayisi.setText(String.valueOf(vdao.kac_tane_vizyonda_film_var()));

        eski_filmlerDAO edao = new eski_filmlerDAO();
        eski_film_sayisi.setText(String.valueOf(edao.kac_tane_eski_film_var()));

        haberlerDAO hdao = new haberlerDAO();
        haber_sayisi.setText(String.valueOf(hdao.kac_tane_haber_var()));

        kampanyalarDAO kdao = new kampanyalarDAO();
        kampanya_sayisi.setText(String.valueOf(kdao.kac_tane_kampanya_var()));

        sinema_salonlariDAO sdao = new sinema_salonlariDAO();
        sinema_salonu_sayisi.setText(String.valueOf(sdao.kac_tane_salon_var()));

        yonetmenlerDAO ydao = new yonetmenlerDAO();
        yonetmen_sayisi.setText(String.valueOf(ydao.kac_tane_yonetmen_var()));

        actorDAO adao = new actorDAO();
        aktor_sayisi.setText(String.valueOf(adao.kac_tane_actor_var()));

        usersDAO udao = new usersDAO();
        kullanici_sayisi.setText(String.valueOf(udao.kac_tane_user_var()));

    }

    public void yonetmen_combo(ComboBox<String> combo, Label uyari_mesaji) {

        yonetmenlerDAO yonetmen_islemleri = new yonetmenlerDAO();

        String[][] arr = yonetmen_islemleri.yonetmen_combo_doldur();

        combo.getItems().clear();

        if (arr.length == 0) {

            uyari_mesaji.setText("Kayıtlı Yönetmen Bulunamadı. Lütfen önce bir yönetmen ekleyiniz.");

        } else {

            for (String[] arr1 : arr) {
                String name_surname = arr1[0];
                combo.getItems().addAll(name_surname);
            }

            combo.setPromptText("İstediğiniz yönetmeni seçiniz.");
        }

    }

    public void geri_don_admin() {
        btn1.setText("Filmler");
        btn2.setText("Duyurular");

        btn3.setVisible(true);
        btn4.setVisible(true);
        btn5.setVisible(true);

        pnl_vizyondaki_filmler.setVisible(false);
        pnl_seans.setVisible(false);
        pnl_sinema_salonlari.setVisible(false);
        pnl_eski_filmler.setVisible(false);
        pnl_kampanyalar.setVisible(false);
        pnl_haberler.setVisible(false);
        pnl_yonetmenler.setVisible(false);
        pnl_aktorler.setVisible(false);
        pnl_kullanici_islemleri.setVisible(false);

        home_pane.setVisible(true);
        home_page();

        home.setVisible(false);
    }

    public void vizyondaki_filmler_combo(ComboBox<String> combo, Label uyari_mesaj) {
        vizyondaki_filmlerDAO vizyondaki_film_islemleri = new vizyondaki_filmlerDAO();
        String[] arr = vizyondaki_film_islemleri.vizyondaki_filmler_combo_doldur();
        combo.getItems().clear();
        if (arr.length == 0) {
            uyari_mesaj.setText("Kayıtlı Vizyondaki Film Bulunamadı. Lütfen önce bir vizyona film ekleyiniz ekleyiniz.");

        } else {
            for (int i = 0; i < arr.length; i++) {
                String v_filmler = arr[i];
                combo.getItems().addAll(v_filmler);
            }

            combo.setPromptText("İstediğiniz filmi seçiniz.");
        }
    }

    public void sinema_salonlari_goruntule_combo(ComboBox<String> combo, Label uyari_mesaj) {

        sinema_salonlariDAO sdao = new sinema_salonlariDAO();
        String[] arr = sdao.salonlar_combo_doldur();
        combo.getItems().clear();
        if (arr.length == 0) {
            uyari_mesaj.setText("Kayıtlı Haber Bulunamadı. Lütfen Önce Bir Haber Ekleyiniz.");

        } else {
            for (String k : arr) {
                combo.getItems().addAll(k);
            }
            combo.setPromptText("İstediğiniz Haberi seçiniz.");
        }
    }

}
