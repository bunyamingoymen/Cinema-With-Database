package Code_Admin;

import DAO.films_photosDAO;
import DAO_Controller.Vizyondaki_Filmler_Controller;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import Pattern.Creator;
import entity.vizyondaki_filmler;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;

public class Par extends Variables {

    public void home_page() {
        vizyondaki_film_sayisi.setText(String.valueOf(Creator.vizyondaki_filmlerDao().count()));

        eski_film_sayisi.setText(String.valueOf(Creator.eski_filmlerDao().count()));

        haber_sayisi.setText(String.valueOf(Creator.haberlerDao().count()));

        kampanya_sayisi.setText(String.valueOf(Creator.kampanyalarDao().count()));

        sinema_salonu_sayisi.setText(String.valueOf(Creator.sinema_salonlariDao().count()));

        yonetmen_sayisi.setText(String.valueOf(Creator.yonetmenlerDao().count()));

        aktor_sayisi.setText(String.valueOf(Creator.actorDao().count()));

        kullanici_sayisi.setText(String.valueOf(Creator.usersDao().count()));

    }

    public void yonetmen_combo(ComboBox<String> combo, Label uyari_mesaji) {

        String[][] arr = Creator.yonetmenlerDao().select();

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
        pnl_film_detay.setVisible(false);

        home_pane.setVisible(true);
        home_page();

        home.setVisible(false);
    }

    public void vizyondaki_filmler_combo(ComboBox<String> combo, Label uyari_mesaj) {
        String[] arr = Creator.vizyondaki_filmlerDao().select_string();
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

    public void vizyondaki_filmler_gosterim_oncesi_ortak() {
        LinkedList<vizyondaki_filmler> list = Vizyondaki_Filmler_Controller.getVizyondaki_filmler_list();

        if (list.size() <= 10) {
            vizyondaki_filmler_gosterim(list, 0);
        } else {
            LinkedList<vizyondaki_filmler> list2 = new LinkedList<>();
            for (int i = 0; i < 10; i++) {
                list2.add(list.get(i));
            }
            vizyondaki_filmler_gosterim(list2, 2);
        }

    }

    public void vizyondaki_filmler_gosterim(LinkedList<vizyondaki_filmler> list, int secim) {

        switch (secim) {
            case -1:
                vizyondaki_filmler_gosterim_pane_sayfa.setVisible(true);
                vizyondaki_filmler_gosterim_sonraki.setVisible(true);
                vizyondaki_filmler_gosterim_onceki.setVisible(true);
                break;
            case 0:
                vizyondaki_filmler_gosterim_pane_sayfa.setVisible(false);
                break;
            case 1:
                vizyondaki_filmler_gosterim_pane_sayfa.setVisible(true);
                vizyondaki_filmler_gosterim_sonraki.setVisible(false);
                vizyondaki_filmler_gosterim_onceki.setVisible(true);
                break;
            case 2:
                vizyondaki_filmler_gosterim_pane_sayfa.setVisible(true);
                vizyondaki_filmler_gosterim_onceki.setVisible(false);
                vizyondaki_filmler_gosterim_sonraki.setVisible(true);
                break;
            default:
                break;
        }

        switch (list.size()) {
            case 0:
                break;
            case 1:
                vizyondaki_filmler_gosterim_pane_bir.setVisible(true);

                vizyondaki_filmler_gosterim_pane_iki.setVisible(false);
                vizyondaki_filmler_gosterim_pane_uc.setVisible(false);
                vizyondaki_filmler_gosterim_pane_dort.setVisible(false);
                vizyondaki_filmler_gosterim_pane_bes.setVisible(false);
                vizyondaki_filmler_gosterim_pane_alti.setVisible(false);
                vizyondaki_filmler_gosterim_pane_yedi.setVisible(false);
                vizyondaki_filmler_gosterim_pane_sekiz.setVisible(false);
                vizyondaki_filmler_gosterim_pane_dokuz.setVisible(false);
                vizyondaki_filmler_gosterim_pane_on.setVisible(false);

                vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_bir, list.get(0).getFilm_id(), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_bir, vizyondaki_filmler_gosterim_adi_bir);
                break;
            case 2:
                vizyondaki_filmler_gosterim_pane_bir.setVisible(true);
                vizyondaki_filmler_gosterim_pane_iki.setVisible(true);

                vizyondaki_filmler_gosterim_pane_uc.setVisible(false);
                vizyondaki_filmler_gosterim_pane_dort.setVisible(false);
                vizyondaki_filmler_gosterim_pane_bes.setVisible(false);
                vizyondaki_filmler_gosterim_pane_alti.setVisible(false);
                vizyondaki_filmler_gosterim_pane_yedi.setVisible(false);
                vizyondaki_filmler_gosterim_pane_sekiz.setVisible(false);
                vizyondaki_filmler_gosterim_pane_dokuz.setVisible(false);
                vizyondaki_filmler_gosterim_pane_on.setVisible(false);

                vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_bir, list.get(0).getFilm_id(), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_bir, vizyondaki_filmler_gosterim_adi_bir);
                vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_iki, list.get(1).getFilm_id(), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_iki, vizyondaki_filmler_gosterim_adi_iki);
                break;
            case 3:
                vizyondaki_filmler_gosterim_pane_bir.setVisible(true);
                vizyondaki_filmler_gosterim_pane_iki.setVisible(true);
                vizyondaki_filmler_gosterim_pane_uc.setVisible(true);

                vizyondaki_filmler_gosterim_pane_dort.setVisible(false);
                vizyondaki_filmler_gosterim_pane_bes.setVisible(false);
                vizyondaki_filmler_gosterim_pane_alti.setVisible(false);
                vizyondaki_filmler_gosterim_pane_yedi.setVisible(false);
                vizyondaki_filmler_gosterim_pane_sekiz.setVisible(false);
                vizyondaki_filmler_gosterim_pane_dokuz.setVisible(false);
                vizyondaki_filmler_gosterim_pane_on.setVisible(false);

                vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_bir, list.get(0).getFilm_id(), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_bir, vizyondaki_filmler_gosterim_adi_bir);
                vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_iki, list.get(1).getFilm_id(), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_iki, vizyondaki_filmler_gosterim_adi_iki);
                vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_uc, list.get(2).getFilm_id(), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_uc, vizyondaki_filmler_gosterim_adi_uc);
                break;
            case 4:
                vizyondaki_filmler_gosterim_pane_bir.setVisible(true);
                vizyondaki_filmler_gosterim_pane_iki.setVisible(true);
                vizyondaki_filmler_gosterim_pane_uc.setVisible(true);
                vizyondaki_filmler_gosterim_pane_dort.setVisible(true);

                vizyondaki_filmler_gosterim_pane_bes.setVisible(false);
                vizyondaki_filmler_gosterim_pane_alti.setVisible(false);
                vizyondaki_filmler_gosterim_pane_yedi.setVisible(false);
                vizyondaki_filmler_gosterim_pane_sekiz.setVisible(false);
                vizyondaki_filmler_gosterim_pane_dokuz.setVisible(false);
                vizyondaki_filmler_gosterim_pane_on.setVisible(false);

                vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_bir, list.get(0).getFilm_id(), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_bir, vizyondaki_filmler_gosterim_adi_bir);
                vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_iki, list.get(1).getFilm_id(), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_iki, vizyondaki_filmler_gosterim_adi_iki);
                vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_uc, list.get(2).getFilm_id(), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_uc, vizyondaki_filmler_gosterim_adi_uc);
                vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_dort, list.get(3).getFilm_id(), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_dort, vizyondaki_filmler_gosterim_adi_dort);
                break;
            case 5:
                vizyondaki_filmler_gosterim_pane_bir.setVisible(true);
                vizyondaki_filmler_gosterim_pane_iki.setVisible(true);
                vizyondaki_filmler_gosterim_pane_uc.setVisible(true);
                vizyondaki_filmler_gosterim_pane_dort.setVisible(true);
                vizyondaki_filmler_gosterim_pane_bes.setVisible(true);

                vizyondaki_filmler_gosterim_pane_alti.setVisible(false);
                vizyondaki_filmler_gosterim_pane_yedi.setVisible(false);
                vizyondaki_filmler_gosterim_pane_sekiz.setVisible(false);
                vizyondaki_filmler_gosterim_pane_dokuz.setVisible(false);
                vizyondaki_filmler_gosterim_pane_on.setVisible(false);

                vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_bir, list.get(0).getFilm_id(), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_bir, vizyondaki_filmler_gosterim_adi_bir);
                vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_iki, list.get(1).getFilm_id(), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_iki, vizyondaki_filmler_gosterim_adi_iki);
                vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_uc, list.get(2).getFilm_id(), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_uc, vizyondaki_filmler_gosterim_adi_uc);
                vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_dort, list.get(3).getFilm_id(), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_dort, vizyondaki_filmler_gosterim_adi_dort);
                vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_bes, list.get(4).getFilm_id(), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_bes, vizyondaki_filmler_gosterim_adi_bes);
                break;
            case 6:
                vizyondaki_filmler_gosterim_pane_bir.setVisible(true);
                vizyondaki_filmler_gosterim_pane_iki.setVisible(true);
                vizyondaki_filmler_gosterim_pane_uc.setVisible(true);
                vizyondaki_filmler_gosterim_pane_dort.setVisible(true);
                vizyondaki_filmler_gosterim_pane_bes.setVisible(true);
                vizyondaki_filmler_gosterim_pane_alti.setVisible(true);

                vizyondaki_filmler_gosterim_pane_yedi.setVisible(false);
                vizyondaki_filmler_gosterim_pane_sekiz.setVisible(false);
                vizyondaki_filmler_gosterim_pane_dokuz.setVisible(false);
                vizyondaki_filmler_gosterim_pane_on.setVisible(false);

                vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_bir, list.get(0).getFilm_id(), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_bir, vizyondaki_filmler_gosterim_adi_bir);
                vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_iki, list.get(1).getFilm_id(), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_iki, vizyondaki_filmler_gosterim_adi_iki);
                vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_uc, list.get(2).getFilm_id(), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_uc, vizyondaki_filmler_gosterim_adi_uc);
                vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_dort, list.get(3).getFilm_id(), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_dort, vizyondaki_filmler_gosterim_adi_dort);
                vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_bes, list.get(4).getFilm_id(), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_bes, vizyondaki_filmler_gosterim_adi_bes);
                vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_alti, list.get(5).getFilm_id(), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_alti, vizyondaki_filmler_gosterim_adi_alti);
                break;
            case 7:
                vizyondaki_filmler_gosterim_pane_bir.setVisible(true);
                vizyondaki_filmler_gosterim_pane_iki.setVisible(true);
                vizyondaki_filmler_gosterim_pane_uc.setVisible(true);
                vizyondaki_filmler_gosterim_pane_dort.setVisible(true);
                vizyondaki_filmler_gosterim_pane_bes.setVisible(true);
                vizyondaki_filmler_gosterim_pane_alti.setVisible(true);
                vizyondaki_filmler_gosterim_pane_yedi.setVisible(true);

                vizyondaki_filmler_gosterim_pane_sekiz.setVisible(false);
                vizyondaki_filmler_gosterim_pane_dokuz.setVisible(false);
                vizyondaki_filmler_gosterim_pane_on.setVisible(false);

                vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_bir, list.get(0).getFilm_id(), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_bir, vizyondaki_filmler_gosterim_adi_bir);
                vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_iki, list.get(1).getFilm_id(), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_iki, vizyondaki_filmler_gosterim_adi_iki);
                vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_uc, list.get(2).getFilm_id(), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_uc, vizyondaki_filmler_gosterim_adi_uc);
                vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_dort, list.get(3).getFilm_id(), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_dort, vizyondaki_filmler_gosterim_adi_dort);
                vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_bes, list.get(4).getFilm_id(), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_bes, vizyondaki_filmler_gosterim_adi_bes);
                vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_alti, list.get(5).getFilm_id(), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_alti, vizyondaki_filmler_gosterim_adi_alti);
                vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_yedi, list.get(6).getFilm_id(), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_yedi, vizyondaki_filmler_gosterim_adi_yedi);
                break;
            case 8:
                vizyondaki_filmler_gosterim_pane_bir.setVisible(true);
                vizyondaki_filmler_gosterim_pane_iki.setVisible(true);
                vizyondaki_filmler_gosterim_pane_uc.setVisible(true);
                vizyondaki_filmler_gosterim_pane_dort.setVisible(true);
                vizyondaki_filmler_gosterim_pane_bes.setVisible(true);
                vizyondaki_filmler_gosterim_pane_alti.setVisible(true);
                vizyondaki_filmler_gosterim_pane_yedi.setVisible(true);
                vizyondaki_filmler_gosterim_pane_sekiz.setVisible(true);

                vizyondaki_filmler_gosterim_pane_dokuz.setVisible(false);
                vizyondaki_filmler_gosterim_pane_on.setVisible(false);

                vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_bir, list.get(0).getFilm_id(), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_bir, vizyondaki_filmler_gosterim_adi_bir);
                vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_iki, list.get(1).getFilm_id(), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_iki, vizyondaki_filmler_gosterim_adi_iki);
                vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_uc, list.get(2).getFilm_id(), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_uc, vizyondaki_filmler_gosterim_adi_uc);
                vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_dort, list.get(3).getFilm_id(), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_dort, vizyondaki_filmler_gosterim_adi_dort);
                vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_bes, list.get(4).getFilm_id(), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_bes, vizyondaki_filmler_gosterim_adi_bes);
                vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_alti, list.get(5).getFilm_id(), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_alti, vizyondaki_filmler_gosterim_adi_alti);
                vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_yedi, list.get(6).getFilm_id(), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_yedi, vizyondaki_filmler_gosterim_adi_yedi);
                vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_sekiz, list.get(7).getFilm_id(), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_sekiz, vizyondaki_filmler_gosterim_adi_sekiz);
                break;
            case 9:
                vizyondaki_filmler_gosterim_pane_bir.setVisible(true);
                vizyondaki_filmler_gosterim_pane_iki.setVisible(true);
                vizyondaki_filmler_gosterim_pane_uc.setVisible(true);
                vizyondaki_filmler_gosterim_pane_dort.setVisible(true);
                vizyondaki_filmler_gosterim_pane_bes.setVisible(true);
                vizyondaki_filmler_gosterim_pane_alti.setVisible(true);
                vizyondaki_filmler_gosterim_pane_yedi.setVisible(true);
                vizyondaki_filmler_gosterim_pane_sekiz.setVisible(true);
                vizyondaki_filmler_gosterim_pane_dokuz.setVisible(true);

                vizyondaki_filmler_gosterim_pane_on.setVisible(false);

                vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_bir, list.get(0).getFilm_id(), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_bir, vizyondaki_filmler_gosterim_adi_bir);
                vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_iki, list.get(1).getFilm_id(), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_iki, vizyondaki_filmler_gosterim_adi_iki);
                vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_uc, list.get(2).getFilm_id(), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_uc, vizyondaki_filmler_gosterim_adi_uc);
                vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_dort, list.get(3).getFilm_id(), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_dort, vizyondaki_filmler_gosterim_adi_dort);
                vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_bes, list.get(4).getFilm_id(), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_bes, vizyondaki_filmler_gosterim_adi_bes);
                vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_alti, list.get(5).getFilm_id(), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_alti, vizyondaki_filmler_gosterim_adi_alti);
                vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_yedi, list.get(6).getFilm_id(), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_yedi, vizyondaki_filmler_gosterim_adi_yedi);
                vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_sekiz, list.get(7).getFilm_id(), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_sekiz, vizyondaki_filmler_gosterim_adi_sekiz);
                vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_dokuz, list.get(8).getFilm_id(), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_dokuz, vizyondaki_filmler_gosterim_adi_dokuz);
                break;
            case 10:
                vizyondaki_filmler_gosterim_pane_bir.setVisible(true);
                vizyondaki_filmler_gosterim_pane_iki.setVisible(true);
                vizyondaki_filmler_gosterim_pane_uc.setVisible(true);
                vizyondaki_filmler_gosterim_pane_dort.setVisible(true);
                vizyondaki_filmler_gosterim_pane_bes.setVisible(true);
                vizyondaki_filmler_gosterim_pane_alti.setVisible(true);
                vizyondaki_filmler_gosterim_pane_yedi.setVisible(true);
                vizyondaki_filmler_gosterim_pane_sekiz.setVisible(true);
                vizyondaki_filmler_gosterim_pane_dokuz.setVisible(true);
                vizyondaki_filmler_gosterim_pane_on.setVisible(true);

                vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_bir, list.get(0).getFilm_id(), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_bir, vizyondaki_filmler_gosterim_adi_bir);
                vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_iki, list.get(1).getFilm_id(), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_iki, vizyondaki_filmler_gosterim_adi_iki);
                vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_uc, list.get(2).getFilm_id(), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_uc, vizyondaki_filmler_gosterim_adi_uc);
                vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_dort, list.get(3).getFilm_id(), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_dort, vizyondaki_filmler_gosterim_adi_dort);
                vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_bes, list.get(4).getFilm_id(), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_bes, vizyondaki_filmler_gosterim_adi_bes);
                vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_alti, list.get(5).getFilm_id(), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_alti, vizyondaki_filmler_gosterim_adi_alti);
                vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_yedi, list.get(6).getFilm_id(), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_yedi, vizyondaki_filmler_gosterim_adi_yedi);
                vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_sekiz, list.get(7).getFilm_id(), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_sekiz, vizyondaki_filmler_gosterim_adi_sekiz);
                vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_dokuz, list.get(8).getFilm_id(), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_dokuz, vizyondaki_filmler_gosterim_adi_dokuz);
                vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_on, list.get(9).getFilm_id(), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_on, vizyondaki_filmler_gosterim_adi_on);
                break;
            default:
                break;
        }
    }

    public void vizyondaki_filmler_gosterim_doldur(ImageView img, int film_id, Label msg, Label id, Label ad) {
        vizyondaki_filmler_imageview_doldur(img, film_id, msg);
        id.setText(String.valueOf(film_id));
        ad.setText(Creator.filmlerDao().search_string(film_id, 1));
    }

    public void vizyondaki_filmler_imageview_doldur(ImageView img, int film_id, Label msg) {
        int control = new films_photosDAO().count(film_id);

        switch (control) {
            case 1: {
                BufferedImage bufferedImage = null;
                try {
                    String photo_path = new films_photosDAO().search(film_id);
                    bufferedImage = ImageIO.read(new File(photo_path));
                } catch (IOException ex) {
                    System.out.println(ex);
                    msg.setText("Bir Hata Meydana Geldi (Hata Kodu: -38)");
                }
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                img.setImage(image);
                break;
            }
            case 0: {
                BufferedImage bufferedImage = null;
                try {
                    String photo_path = Creator.films_photoDAO().default_photo();
                    bufferedImage = ImageIO.read(new File(photo_path));
                } catch (IOException ex) {
                    System.out.println(ex);
                    msg.setText("Bir Hata Meydana Geldi (Hata Kodu: -38)");
                }
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                img.setImage(image);
                break;
            }
            default:
                msg.setText("Bir Hata Meydana Geldi (Hata Kodu: -39)");
                System.out.println(control);
                break;
        }
    }

    public void sinema_salonlari_goruntule_combo(ComboBox<String> combo, Label uyari_mesaj) {

        String[] arr = Creator.sinema_salonlariDao().select();
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
