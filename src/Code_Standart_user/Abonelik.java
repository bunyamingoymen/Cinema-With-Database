package Code_Standart_user;

import Pattern.Creator;
import DAO.abonelerDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class Abonelik extends Par {

    abonelerDAO adao = new abonelerDAO();

    @FXML
    public void abonelik_giris(ActionEvent event) {
        abonelik_giris_ortak();
    }

    public void abonelik_giris_ortak() {
        pnl_abonelik.setVisible(true);
        home_page_icon.setVisible(true);

        pnl_kampanyalar.setVisible(false);
        pnl_settings.setVisible(false);
        pnl_vizyondaki_filmler.setVisible(false);
        pnl_haberler.setVisible(false);
        pnl_biletlerim.setVisible(false);
        pnl_sinema_salonlari.setVisible(false);
        pnl_film_detay.setVisible(false);
        home_page.setVisible(false);

        abonelik_sahip_bir.setVisible(false);
        abonelik_sahip_iki.setVisible(false);
        abonelik_sahip_uc.setVisible(false);
        pnl_eski_filmler.setVisible(false);

        int abonelik_turu = abonelik_turu_getir();

        switch (abonelik_turu) {
            case 0:
                pnl_abonelik_0.setVisible(true);

                pnl_abonelik_diger.setVisible(false);
                pnl_abonelik_uyari_mesaj.setVisible(false);
                break;
            case -1:
                pnl_abonelik_uyari_mesaj.setVisible(true);

                pnl_abonelik_0.setVisible(false);
                pnl_abonelik_diger.setVisible(false);

                pnl_abonelik_uyari_mesaj.setText("Bir hata meydana geldi (Hata Kodu -8)");
                break;
            default:
                pnl_abonelik_diger.setVisible(true);

                pnl_abonelik_0.setVisible(false);
                pnl_abonelik_uyari_mesaj.setVisible(false);
                break;
        }
    }

    @FXML
    public void abonelik_bir_satin_al(ActionEvent event) {
        int user_id = Creator.getU().getUser_id();
        int abonelik_turu = 1;

        int sonuc = adao.abonelik_turu_satin_al(abonelik_turu, user_id);

        if (sonuc == 1) {
            pnl_abonelik_0.setVisible(false);
            pnl_abonelik_uyari_mesaj.setVisible(true);

            pnl_abonelik_uyari_mesaj.setText("İşlem Başarılı Bir Şekilde Gerçekleştirildi");
        } else {
            pnl_abonelik_0.setVisible(false);
            pnl_abonelik_uyari_mesaj.setVisible(true);

            pnl_abonelik_uyari_mesaj.setText("Bir hata meydana geldi. (Hata Kodu: -9)");
        }

    }

    @FXML
    public void abonelik_iki_satin_al(ActionEvent event) {
        int user_id = Creator.getU().getUser_id();
        int abonelik_turu = 2;

        int sonuc = adao.abonelik_turu_satin_al(abonelik_turu, user_id);

        if (sonuc == 1) {
            pnl_abonelik_0.setVisible(false);
            pnl_abonelik_uyari_mesaj.setVisible(true);

            pnl_abonelik_uyari_mesaj.setText("İşlem Başarılı Bir Şekilde Gerçekleştirildi");
        } else {
            pnl_abonelik_0.setVisible(false);
            pnl_abonelik_uyari_mesaj.setVisible(true);

            pnl_abonelik_uyari_mesaj.setText("Bir hata meydana geldi. (Hata Kodu: -10)");
        }
    }

    @FXML
    public void abonelik_uc_satin_al(ActionEvent event) {
        int user_id = Creator.getU().getUser_id();
        int abonelik_turu = 3;

        int sonuc = adao.abonelik_turu_satin_al(abonelik_turu, user_id);

        if (sonuc == 1) {
            pnl_abonelik_0.setVisible(false);
            pnl_abonelik_uyari_mesaj.setVisible(true);

            pnl_abonelik_uyari_mesaj.setText("İşlem Başarılı Bir Şekilde Gerçekleştirildi");
        } else {
            pnl_abonelik_0.setVisible(false);
            pnl_abonelik_uyari_mesaj.setVisible(true);

            pnl_abonelik_uyari_mesaj.setText("Bir hata meydana geldi. (Hata Kodu: -11)");
        }
    }

    @FXML
    public void abonelik_degistir(ActionEvent event) {
        pnl_abonelik_0.setVisible(true);

        pnl_abonelik_diger.setVisible(false);
        pnl_abonelik_uyari_mesaj.setVisible(false);

        int abonelik_turu = abonelik_turu_getir();

        switch (abonelik_turu) {
            case 0:
                abonelik_sahip_bir.setVisible(false);
                abonelik_sahip_iki.setVisible(false);
                abonelik_sahip_uc.setVisible(false);
                break;
            case 1:
                abonelik_sahip_bir.setVisible(true);

                abonelik_sahip_iki.setVisible(false);
                abonelik_sahip_uc.setVisible(false);
                break;
            case 2:
                abonelik_sahip_iki.setVisible(true);

                abonelik_sahip_bir.setVisible(false);
                abonelik_sahip_uc.setVisible(false);
                break;
            case 3:
                abonelik_sahip_uc.setVisible(true);

                abonelik_sahip_bir.setVisible(false);
                abonelik_sahip_iki.setVisible(false);
                break;
            default:
                pnl_abonelik_uyari_mesaj.setVisible(true);

                pnl_abonelik_diger.setVisible(false);
                pnl_abonelik_0.setVisible(false);

                pnl_abonelik_uyari_mesaj.setText("Bir hata Meydana geldi (Hata Kodu: -15)");
                break;
        }
    }

    @FXML
    private void sana_ozel_kampanyalar_giris(ActionEvent event) {
        kampanyalar_giris_ortak();

        kampanyalar_geri_tusu.setVisible(false);
        sana_ozel_kampanyalar_geri_tusu.setVisible(true);

    }

    @FXML
    private void sana_ozel_kampanyalar_geri(MouseEvent event) {
        abonelik_giris_ortak();
    }

    @FXML
    private void sana_ozel_haberler_giris(ActionEvent event) {
        haberler_giris_ortak();

        haberler_geri_tusu.setVisible(false);
        sana_ozel_haberler_geri_tusu.setVisible(true);
    }

    @FXML
    private void sana_ozel_haberler_geri(MouseEvent event) {
        abonelik_giris_ortak();
    }

    @FXML
    private void abonelik_iptal_et(ActionEvent event) {
        int user_id = Creator.getU().getUser_id();

        int sonuc = adao.aboneler_sil(user_id);

        pnl_abonelik_0.setVisible(false);
        pnl_abonelik_diger.setVisible(false);
        pnl_abonelik_uyari_mesaj.setVisible(true);

        if (sonuc == 1) {
            pnl_abonelik_uyari_mesaj.setText("İşlem Başarılı Bir şekilde gerçekleştirildi.");
        } else {
            pnl_abonelik_uyari_mesaj.setText("Bir hata meydana geldi. (Hata kodu: -16)");
        }
    }
}
