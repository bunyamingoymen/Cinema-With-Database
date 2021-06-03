/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Code_Admin;

import Pattern.Creator;
import Pattern.Mediator;
import javafx.fxml.FXML;

/**
 *
 * @author bgoym
 */
public class Film_detay extends Film_Actor {

    @FXML
    public void film_detay_geri() {

        pnl_film_detay.setVisible(false);

        if (film_detay_id_oncesi.getText().equals("vizyon_id")) {
            pnl_vizyondaki_filmler.setVisible(true);

            vizyondaki_filmler_geri_tusu.setVisible(true);
            vizyondaki_filmler_ekle_geri_tusu.setVisible(false);
            //vizyondaki_filmler_degistir_geri_tusu.setVisible(false);
            vizyondaki_filmler_gosterim_geri_tusu.setVisible(false);

            vizyondaki_filmler_resimli_gosterim.setVisible(true);
            vizyondaki_filmler_grid.setVisible(false);
            vizyondaki_filmler_ekle_pane.setVisible(false);
            //vizyondaki_filmler_degistir_pane.setVisible(false);

            vizyondaki_filmler_gosterim_oncesi_ortak();

        } else if (film_detay_id_oncesi.getText().equals("eski_id")) {

            pnl_eski_filmler.setVisible(true);

            eski_filmler_geri_tusu.setVisible(true);
            eski_filmler_ekle_geri_tusu.setVisible(false);
            eski_filmler_degistir_geri_tusu.setVisible(false);

            eski_filmler_grid.setVisible(true);
            eski_filmler_ekle_pane.setVisible(false);
            eski_filmler_degistir_pane.setVisible(false);

            eski_filmler_table_aboneli();

        } else {
            System.out.println("Hata");
        }
    }

    @FXML
    public void film_detay_sil_giris() {
        film_detay_sil_emin_misin_pane.setVisible(true);
        if (film_detay_id_oncesi.getText().equals("vizyon_id")) {

            film_detay_sadece_sil_butonu.setText("Sadece Vizyondan Sil Ve Eski Filmlere Ekle");

        } else if (film_detay_id_oncesi.getText().equals("eski_id")) {

            film_detay_sadece_sil_butonu.setText("Sadece Eskiden Sil Ve Eski Filmlere Ekle");

        } else {

            System.out.println("Hata");

        }
    }

    @FXML
    public void film_detay_sil_vazgec() {
        film_detay_sil_emin_misin_pane.setVisible(false);
    }

    @FXML
    public void film_detay_sil_sadece_sil() {

        film_detay_sil_emin_misin_pane.setVisible(false);

        int id = Integer.parseInt(film_detay_id.getText());

        Mediator m = new Mediator();

        if (film_detay_id_oncesi.getText().equals("vizyon_id")) {

            int sonuc = m.vizyondaki_filmler_sadece_vziyodnan_sil(id);
            if (sonuc == 1) {
                film_detay_uyari_mesaj.setText("İstenilen Veri Sadece Vizyonan Silindi. Geri Dönembilirsiniz");
            } else {
                film_detay_uyari_mesaj.setText("Bir hata meydana geldi lütfen daha sonra tekrar denetyiniz.");
            }

        } else if (film_detay_id_oncesi.getText().equals("eski_id")) {

            int sonuc = m.eski_filmler_sadece_eskiden_sil(id);

            if (sonuc == 1) {
                film_detay_uyari_mesaj.setText("İşlem Veri Sadece Eskilerden Silindş. Geri Dönebilirsiniz,");
            } else {
                film_detay_uyari_mesaj.setText("Bir hata meydana geldi.Lütfen daha sonra tekrar deneyiniz.");
            }

        } else {
            System.out.println("Hata");
        }
    }

    @FXML
    public void film_detay_sil_tamamen_sil() {

        film_detay_sil_emin_misin_pane.setVisible(false);

        int id = Integer.parseInt(film_detay_id.getText());

        Mediator m = new Mediator();

        if (film_detay_id_oncesi.getText().equals("vizyon_id")) {

            int sonuc = m.vizyondaki_filmler_tamamen_sil(id);
            if (sonuc == 1) {
                film_detay_uyari_mesaj.setText("İstenilen Veri Tamamiyle Silindi. Geri Dönebilrsiniz");
            } else {
                film_detay_uyari_mesaj.setText("Bir hata meydana geldi lütfen daha sonra tekrar denetyiniz.");
            }

        } else if (film_detay_id_oncesi.getText().equals("eski_id")) {

            int sonuc = m.eski_filmler_tamamen_sil(id);

            if (sonuc == 1) {
                film_detay_uyari_mesaj.setText("İstenilen Veri Tamamiyle Silindi. Geri Dönebilrsiniz");
            } else {
                film_detay_uyari_mesaj.setText("Bir hata meydana geldi.Lütfen daha sonra tekrar deneyiniz.");
            }

        } else {

            System.out.println("Hata");

        }
    }

    @FXML
    public void film_detay_guncelle_giris() {

        film_detay_guncelle_pane.setVisible(true);
        film_detay_ana_pane.setVisible(false);
        film_detay_sil_emin_misin_pane.setVisible(false);

        film_detay_degistir_film_id.setText(vizyondaki_filmler_detay_film_id.getText());

        film_detay_degistir_film_adi.setText(Creator.filmlerDao().search_string(Integer.parseInt(film_detay_degistir_film_id.getText()), 1));

        film_detay_degistir_film_turu.setText(Creator.filmlerDao().search_string(Integer.parseInt(film_detay_degistir_film_id.getText()), 2));

        film_detay_degistir_film_suresi.setText(String.valueOf(Creator.filmlerDao().search_int(Integer.parseInt(film_detay_degistir_film_id.getText()), 1)));

        yonetmen_combo(film_detay_degistir_yonetmenler, film_detay_degistir_uyari_mesaj);

        film_detay_degistir_id.setText(film_detay_id.getText());

        if (film_detay_id_oncesi.getText().equals("vizyon_id")) {

            film_detay_degistir_vizyondan_kalkis_tarihi.setVisible(true);
            film_detay_degistir_aldigi_odul_sayisi.setVisible(false);
            film_detay_degistir_hangi_aboneler.setVisible(false);

            film_detay_degistir_vizyondan_kalkis_tarihi_oncesi.setVisible(true);
            film_detay_degistir_aldigi_odul_sayisi_oncesi.setVisible(false);
            film_detay_degistir_hangi_aboneler_izleyebilir_oncesi.setVisible(false);

            film_detay_degistir_id_oncesi.setText("vizyon_id");

            film_detay_degistir_vizyondan_kalkis_tarihi.setValue(Creator.vizyondaki_filmlerDao().search_localdate(Integer.parseInt(film_detay_id.getText())));

            film_detay_degistir_yonetmenler.setValue(String.valueOf(Creator.vizyondaki_filmlerDao().search_int(Integer.parseInt(film_detay_degistir_id.getText()), 4, 1)) + " " + Creator.vizyondaki_filmlerDao().search_string(Integer.parseInt(film_detay_id.getText()), 3));

        } else if (film_detay_id_oncesi.getText().equals("eski_id")) {

            film_detay_degistir_aldigi_odul_sayisi.setVisible(true);
            film_detay_degistir_hangi_aboneler.setVisible(true);
            film_detay_degistir_vizyondan_kalkis_tarihi.setVisible(false);

            film_detay_degistir_aldigi_odul_sayisi_oncesi.setVisible(true);
            film_detay_degistir_hangi_aboneler_izleyebilir_oncesi.setVisible(true);
            film_detay_degistir_vizyondan_kalkis_tarihi_oncesi.setVisible(false);

            film_detay_degistir_id_oncesi.setText("eski_id");

            film_detay_degistir_aldigi_odul_sayisi.setText(String.valueOf(Creator.eski_filmlerDao().search_int(Integer.parseInt(film_detay_degistir_id.getText()), 4)));

            hangi_aboneler_combo(film_detay_degistir_hangi_aboneler);
            eski_filmleri_degistir_sil_hangi_abone.setValue(String.valueOf(Creator.eski_filmlerDao().search_int(Integer.parseInt(film_detay_degistir_id.getText()), 3)));

            film_detay_degistir_yonetmenler.setValue(String.valueOf(Creator.eski_filmlerDao().search_int(Integer.parseInt(film_detay_degistir_id.getText()), 1)) + " " + Creator.eski_filmlerDao().search_string(Integer.parseInt(film_detay_id.getText()), 3));

        } else {

            System.out.println("Hata");

        }

    }
    
    @FXML
    public void film_detay_guncelle_guncelle(){
        
    }

}