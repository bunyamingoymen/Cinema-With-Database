/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Code_Admin;

import Pattern.Creator;
import Pattern.Mediator;
import entity.eski_filmler;
import entity.filmler;
import entity.vizyondaki_filmler;
import java.time.LocalDate;
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
            vizyondaki_filmler_gosterim_geri_tusu.setVisible(false);

            vizyondaki_filmler_resimli_gosterim.setVisible(true);
            vizyondaki_filmler_grid.setVisible(false);
            vizyondaki_filmler_ekle_pane.setVisible(false);

            vizyondaki_filmler_gosterim_oncesi_ortak();

        } else if (film_detay_id_oncesi.getText().equals("eski_id")) {

            pnl_eski_filmler.setVisible(true);

            eski_filmler_geri_tusu.setVisible(true);
            eski_filmler_ekle_geri_tusu.setVisible(false);
            
            eski_filmler_grid.setVisible(true);
            eski_filmler_ekle_pane.setVisible(false);

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
            film_detay_degistir_hangi_aboneler.setValue(String.valueOf(Creator.eski_filmlerDao().search_int(Integer.parseInt(film_detay_degistir_id.getText()), 3)));

            film_detay_degistir_yonetmenler.setValue(String.valueOf(Creator.eski_filmlerDao().search_int(Integer.parseInt(film_detay_degistir_id.getText()), 1)) + " " + Creator.eski_filmlerDao().search_string(Integer.parseInt(film_detay_id.getText()), 3));

        } else {

            System.out.println("Hata");

        }

    }

    @FXML
    public void film_detay_guncelle_guncelle() {

        if (film_detay_id_oncesi.getText().equals("vizyon_id")) {

            if ((film_detay_degistir_film_adi.getText().length() == 0) || (film_detay_degistir_film_turu.getText().length() == 0) || (film_detay_degistir_film_suresi.getText().length() == 0) || (film_detay_degistir_vizyondan_kalkis_tarihi.getValue() == null) || (film_detay_degistir_vizyondan_kalkis_tarihi.getValue() == null)) {
                film_detay_degistir_uyari_mesaj.setText("Lütfen Gerekli Yerleri Doldurunuz.");
            } else {
                String film_name = film_detay_degistir_film_adi.getText();
                String film_type = film_detay_degistir_film_turu.getText();

                try {
                    int film_suresi = Integer.parseInt(film_detay_degistir_film_suresi.getText());
                    LocalDate kalkis = film_detay_degistir_vizyondan_kalkis_tarihi.getValue();
                    String yonetmen = film_detay_degistir_yonetmenler.getValue();
                    int yonetmen_id = 0;
                    String[][] arr = Creator.yonetmenlerDao().select();

                    for (String[] arr1 : arr) {
                        if (yonetmen.equals(arr1[0])) {
                            yonetmen_id = Integer.valueOf(arr1[1]);
                        }
                    }

                    String vizyondaki_film_id = film_detay_degistir_id.getText();
                    String film_id = film_detay_degistir_film_id.getText();

                    vizyondaki_filmler v = new vizyondaki_filmler(Integer.valueOf(vizyondaki_film_id), Integer.valueOf(film_id), kalkis, Creator.filmlerDao().search_float(Integer.valueOf(film_id)), Creator.vizyondaki_filmlerDao().search_int(Integer.valueOf(vizyondaki_film_id), 3, 1));
                    filmler f = new filmler(Integer.valueOf(film_id), film_name, Integer.valueOf(film_suresi), film_type, yonetmen_id);

                    Mediator m = new Mediator();

                    int sonuc = m.vizyondaki_filmler_degiştir(v, f);
                    if (sonuc == 1) {
                        film_detay_degistir_uyari_mesaj.setText("İşlem başarılı bir şekilde gerçekleştirildi.");

                    } else {
                        film_detay_degistir_uyari_mesaj.setText("Bir hata meydana geldi. Lütfen daha sonra tekrar deneyiniz.");
                    }
                } catch (NumberFormatException e) {
                    film_detay_degistir_uyari_mesaj.setText("Lütfen Film Süresini sayı giriniz (dk olarak)");
                }
            }

        } else if (film_detay_id_oncesi.getText().equals("eski_id")) {

            if ((film_detay_degistir_film_adi.getText().length() == 0) || (film_detay_degistir_film_turu.getText().length() == 0) || (film_detay_degistir_film_suresi.getText().length() == 0) || (film_detay_degistir_aldigi_odul_sayisi.getText().length() == 0)
                    || (film_detay_degistir_hangi_aboneler.getValue() == null) || (film_detay_degistir_yonetmenler.getValue() == null)) {
                film_detay_degistir_uyari_mesaj.setText("Lütfen Gerekli Yerleri doldurunuz.");
            } else {
                String film_name = film_detay_degistir_film_adi.getText();
                String film_type = film_detay_degistir_film_turu.getText();
                try {
                    int film_suresi = Integer.parseInt(film_detay_degistir_film_suresi.getText());
                    try {
                        int aldigi_odul = Integer.parseInt(film_detay_degistir_aldigi_odul_sayisi.getText());
                        int hangi_abone = Integer.parseInt(film_detay_degistir_hangi_aboneler.getValue());
                        String yonetmen = film_detay_degistir_yonetmenler.getValue();
                        String[][] arr = Creator.yonetmenlerDao().select();
                        int yonetmen_id = 0;
                        for (int i = 0; i < arr.length; i++) {
                            if (arr[i][0].equals(yonetmen)) {
                                yonetmen_id = Integer.valueOf(arr[i][1]);
                                break;
                            }
                        }
                        int eski_film_id = Integer.parseInt(film_detay_degistir_id.getText());
                        int film_id = Integer.parseInt(film_detay_degistir_film_id.getText());
                        filmler f = new filmler(film_id, film_name, film_suresi, film_type, yonetmen_id);
                        eski_filmler e = new eski_filmler(eski_film_id, hangi_abone, aldigi_odul);

                        Mediator m = new Mediator();

                        int sonuc = m.eski_filmler_degistir(e, f);

                        if (sonuc == 1) {
                            film_detay_degistir_uyari_mesaj.setText("İşlem başarılı bir şekilde gerçekleştirildi.");
                        } else {
                            film_detay_degistir_uyari_mesaj.setText("Bir hata meydana geldi lütfen daha sonra tekrar deneyiniz..");
                        }

                    } catch (NumberFormatException e) {
                        film_detay_degistir_uyari_mesaj.setText("Lütfen aldığı ödülleri sadece sayı olarak giriniz.");
                    }
                } catch (NumberFormatException e) {
                    film_detay_degistir_uyari_mesaj.setText("Lütfen Süreyi sadece sayı olarak giriniz (dk olarak).");
                }
            }

        } else {
            System.out.println("Hata");
        }
    }

}
