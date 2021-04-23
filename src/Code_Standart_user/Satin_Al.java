package Code_Standart_user;

import DAO.abonelerDAO;
import DAO.satin_alinan_biletlerDAO;
import DAO.seansDAO;
import DAO.sinema_salonlariDAO;
import DAO.yesil_olanDAO;
import entity.users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class Satin_Al extends Vizyondaki_Filmler {

    @FXML
    public void satin_al_filmler_filmi_getir(ActionEvent event) {
        if (satin_al_filmler_como.getValue() == null) {
            satin_al_filmler_uyari_mesaj.setText("Lütfen Bir film seçiniz");
        } else {
            String[] ab = satin_al_filmler_como.getValue().split(" | ");
            int vizyondaki_film_id = Integer.parseInt(ab[0]);
            int sonuc = seans_combo(satin_al_filmler_seans_combo, vizyondaki_film_id);
            if (sonuc == 0) {
                satin_al_filmler_uyari_mesaj.setText("Bu filme ait herhangi bir seans tanımlı değil. Lütfen farklı bir film seçiniz.");
                satin_al_filmler_seans_pane.setVisible(false);
            } else {
                satin_al_filmler_seans_pane.setVisible(true);
                satin_al_filmler_uyari_mesaj.setText("");
            }
        }
    }

    @FXML
    public void satin_al_filmler_koltuk_sec(ActionEvent event) {
        if (satin_al_filmler_seans_combo.getValue() == null) {
            satin_al_filmler_uyari_mesaj.setText("Lütfen Bir Film Seçiniz");
        } else {
            String[] ab = satin_al_filmler_seans_combo.getValue().split(" | ");

            int seans_id = Integer.parseInt(ab[0]);

            seansDAO sdao = new seansDAO();
            int salon_id = sdao.salon_id_getir(seans_id);

            sinema_salonlariDAO sinema_dao = new sinema_salonlariDAO();
            int koltuk_sayisi = sinema_dao.koltuk_sayisi_getir(salon_id);

            String film_adi = sdao.film_adi_getir(seans_id);
            String salon_adi = sdao.salon_adi_getir(seans_id);
            String saat = sdao.saat_getir(seans_id);

            satin_al_koltuk_film_adi.setText(film_adi);
            satin_al_koltuk_salon_adi.setText(salon_adi);
            satin_al_koltuk_seans_saati.setText(saat);
            satin_al_koltuk_seans_id.setText(String.valueOf(seans_id));

            satin_al_koltuk_ust_pane.setVisible(true);

            satin_al_filmler_pane.setVisible(false);

            yesil_olanDAO ydao = new yesil_olanDAO();
            ydao.yesil_olan_dao_toplu_sil();

            switch (koltuk_sayisi) {
                case 129:
                    salon_bir_pane.setVisible(true);

                    salon_iki_pane.setVisible(false);
                    salon_uc_pane.setVisible(false);
                    salon_dort_pane.setVisible(false);

                    bir_koltuk_dolu(seans_id);
                    break;
                case 177:
                    salon_iki_pane.setVisible(true);

                    salon_bir_pane.setVisible(false);
                    salon_uc_pane.setVisible(false);
                    salon_dort_pane.setVisible(false);

                    iki_koltuk_dolu(seans_id);
                    break;
                case 265:
                    salon_uc_pane.setVisible(true);

                    salon_bir_pane.setVisible(false);
                    salon_iki_pane.setVisible(false);
                    salon_dort_pane.setVisible(false);

                    uc_koltuk_dolu(seans_id);
                    break;
                case 294:
                    salon_dort_pane.setVisible(true);

                    salon_bir_pane.setVisible(false);
                    salon_iki_pane.setVisible(false);
                    salon_uc_pane.setVisible(false);

                    dort_koltuk_dolu(seans_id);
                    break;
                default:
                    satin_al_uyari_mesaj.setVisible(true);

                    salon_bir_pane.setVisible(false);
                    salon_iki_pane.setVisible(false);
                    salon_uc_pane.setVisible(false);
                    salon_dort_pane.setVisible(false);
                    satin_al_koltuk_ust_pane.setVisible(false);
                    satin_al_odeme_yontemi_pane.setVisible(false);

                    satin_al_uyari_mesaj.setText("Bir hata meydana geldi (Hata kodu: -17)");
                    break;
            }

        }
    }

    @FXML
    public void odeme_yontemi_giris(ActionEvent event) {
        satin_al_odeme_yontemi_pane.setVisible(true);

        satin_al_koltuk_ust_pane.setVisible(false);
        satin_al_filmler_pane.setVisible(false);
        salon_bir_pane.setVisible(false);
        salon_iki_pane.setVisible(false);
        salon_uc_pane.setVisible(false);
        salon_dort_pane.setVisible(false);

        int user_id = users.getU().getUser_id();

        abonelerDAO adao = new abonelerDAO();
        int kalan_ucretsiz_bilet_sayisi = adao.kalan_ucretsiz_bilet_sayisi(user_id);

        if (kalan_ucretsiz_bilet_sayisi == -1) {

            satin_al_odeme_yontemi_pane.setVisible(false);
            satin_al_uyari_mesaj.setVisible(true);
            satin_al_uyari_mesaj.setText("Bir hata meydana geldi(Hata Kodu: -18)");
        } else {
            satin_al_kalan_ucretsiz_bilet_sayisi_hakki.setText(String.valueOf(kalan_ucretsiz_bilet_sayisi));
        }

    }

    public void satin_al_ortak() {
        satin_al_uyari_mesaj.setVisible(true);

        satin_al_odeme_yontemi_pane.setVisible(false);

        satin_alinan_biletlerDAO sdao = new satin_alinan_biletlerDAO();

        int user_id = users.getU().getUser_id();
        int seans_id = Integer.parseInt(satin_al_koltuk_seans_id.getText());

        int sonuc = sdao.satin_alinan_bilet_satin_al(user_id, seans_id);

        if (sonuc == 1) {
            satin_al_uyari_mesaj.setText("İşlem Başarılı Bir Şekilde Gerçekleşti");
        } else {
            satin_al_uyari_mesaj.setText("Bir hata Meydana Geldi (Hata Kodu: -19)");
        }
    }

    @FXML
    public void satin_al_havale_eft(ActionEvent event) {
        satin_al_ortak();
    }

    @FXML
    public void satin_al_kredi_banka_karti(ActionEvent event) {
        satin_al_ortak();
    }

    @FXML
    public void satin_al_ucretsiz_bilet_hakki(ActionEvent event) {
        abonelerDAO adao = new abonelerDAO();

        int sonuc = adao.ucretsiz_bilet_sayisi_dusur(users.getU().getUser_id());

        switch (sonuc) {
            case -2:
                satin_al_uyari_mesaj.setVisible(true);

                satin_al_odeme_yontemi_pane.setVisible(false);

                satin_al_uyari_mesaj.setText("Bir hata meydana geldi. (Hata kodu: -20)");

                break;
            case -1:
                satin_al_odeme_yontemi_uyari_mesaj.setText("Ücretsiz Bilet Hakkınız Bulunmamaktadır.");
                break;
            case 0:
                satin_al_uyari_mesaj.setVisible(true);

                satin_al_odeme_yontemi_pane.setVisible(false);

                satin_al_uyari_mesaj.setText("Bir hata meydana geldi. (Hata kodu: -21)");
                break;
            case 1:
                satin_al_ortak();
                break;
            default:
                satin_al_uyari_mesaj.setVisible(true);

                satin_al_odeme_yontemi_pane.setVisible(false);

                satin_al_uyari_mesaj.setText("Bir hata meydana geldi. (Hata kodu: -22)");
                break;
        }
    }

    @FXML
    public void bilet_satin_al_giris(ActionEvent event) {
        bilet_satin_al_giris_ortak();
    }

    public void bilet_satin_al_giris_ortak() {
        pnl_sinema_salonlari.setVisible(true);
        home_page_icon.setVisible(true);

        pnl_vizyondaki_filmler.setVisible(false);
        pnl_abonelik.setVisible(false);
        pnl_haberler.setVisible(false);
        pnl_kampanyalar.setVisible(false);
        pnl_biletlerim.setVisible(false);
        pnl_settings.setVisible(false);
        pnl_film_detay.setVisible(false);
        home_page.setVisible(false);

        salon_bir_pane.setVisible(false);
        salon_iki_pane.setVisible(false);
        salon_uc_pane.setVisible(false);
        salon_dort_pane.setVisible(false);
        satin_al_koltuk_ust_pane.setVisible(false);
        satin_al_odeme_yontemi_pane.setVisible(false);
        satin_al_uyari_mesaj.setVisible(false);

        satin_al_filmler_pane.setVisible(true);

        satin_al_filmler_seans_pane.setVisible(false);

        vizyondaki_filmler_combo(satin_al_filmler_como, satin_al_filmler_uyari_mesaj);
    }
}
