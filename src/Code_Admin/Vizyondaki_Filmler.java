package Code_Admin;

import DAO.filmlerDAO;
import DAO.films_photosDAO;
import entity.filmler;
import entity.vizyondaki_filmler;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javax.imageio.ImageIO;
import Creator.Creator;
import Creator.Mediator;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.cell.PropertyValueFactory;

public class Vizyondaki_Filmler extends Sinema_Salonlari_Koltık_Dolu_Bos {

    @FXML
    public void vizyondaki_filmler_geri(MouseEvent event) {
        geri_don_admin();
    }

    @FXML
    public void vizyondaki_filmler_ekle_geri(MouseEvent event) {
        vizyondaki_filmler_resimli_gosterim.setVisible(true);
        vizyondaki_filmler_ekle_pane.setVisible(false);

        vizyondaki_filmler_geri_tusu.setVisible(true);
        vizyondaki_filmler_ekle_geri_tusu.setVisible(false);

        vizyondaki_filmler_gosterim();
    }

    @FXML
    public void vizyondaki_filmler_ekle_giris(ActionEvent event) {

        vizyondaki_filmler_grid.setVisible(false);
        vizyondaki_filmler_resimli_gosterim.setVisible(false);
        vizyondaki_filmler_ekle_pane.setVisible(true);
        yonetmen_combo(vizyondaki_filmler_ekle_yonetmenler, vizyondaki_filmler_ekle_uyari_mesaj);
        vizyondaki_filmler_geri_tusu.setVisible(false);
        vizyondaki_filmler_ekle_geri_tusu.setVisible(true);
        vizyondaki_filmler_degistir_geri_tusu.setVisible(false);
        vizyondaki_filmler_gosterim_geri_tusu.setVisible(false);
        vizyondaki_filmler_degistir_pane.setVisible(false);
    }

    @FXML
    public void vizyondaki_filmler_ekle(ActionEvent event) {
        String film_name = vizyondaki_filmler_ekle_film_adi.getText();
        int film_suresi = 0;
        int control = -1;
        try {
            film_suresi = Integer.parseInt(vizyondaki_filmler_ekle_film_suresi.getText());
            control = 1;
        } catch (NumberFormatException e) {
            vizyondaki_filmler_ekle_uyari_mesaj.setText("Lütfen film süresine sadeca sayı giriniz.(dk olarak)");
        }
        if (control != -1) {
            String film_type = vizyondaki_filmler_ekle_film_type.getText();

            String yonetmen = vizyondaki_filmler_ekle_yonetmenler.getValue();
            if ((film_name.length() == 0) || (vizyondaki_filmler_film_suresi.getText().length() == 0) || (film_type.length() == 0) || (vizyondaki_filmler_ekle_kalkis.getValue() == null) || (yonetmen == null)) {
                vizyondaki_filmler_ekle_uyari_mesaj.setText("Lütfen gerekli yerleri doldurunuz.");
            } else {
                LocalDate kalkis = vizyondaki_filmler_ekle_kalkis.getValue();
                String[][] arr = Creator.yonetmenlerDao().yonetmen_combo_doldur();
                int yonetmen_id = 0;
                for (int i = 0; i < arr.length; i++) {
                    if (arr[i][0].equals(yonetmen)) {
                        yonetmen_id = Integer.valueOf(arr[i][1]);
                        i = arr.length + 1;
                    }
                }

                filmler f = new filmler(film_name, film_suresi, film_type, yonetmen_id, 0);
                int film_id = Creator.filmlerDao().filmler_ekle_id_gonder(f);
                vizyondaki_filmler v = new vizyondaki_filmler(film_id, kalkis, 0);
                int sonuc = Creator.vizyondaki_filmlerDao().vizyondaki_filmler_dao_ekle(v);
                if (sonuc == 1) {
                    vizyondaki_filmler_ekle_uyari_mesaj.setText("İşlem Başarılı Bir Şekilde Gerçekleşti.");
                }

            }
        }
    }

    @FXML
    public void vizyondaki_filmler_ekle_sifirla(ActionEvent event) {
        vizyondaki_filmler_ekle_film_adi.setText("");
        vizyondaki_filmler_ekle_film_suresi.setText("");
        vizyondaki_filmler_ekle_film_type.setText("");
        vizyondaki_filmler_ekle_kalkis.setValue(null);
        vizyondaki_filmler_ekle_yonetmenler.getItems().clear();
        vizyondaki_filmler_ekle_yonetmenler.setPromptText("İstediğiniz yönetmeni seçiniz.");
        yonetmen_combo(vizyondaki_filmler_ekle_yonetmenler, vizyondaki_filmler_ekle_uyari_mesaj);
    }

    @FXML
    public void vizyondaki_filmler_degistir_sil_giris(ActionEvent event) {

        vizyondaki_filmler_grid.setVisible(false);
        vizyondaki_filmler_degistir_pane.setVisible(true);
        vizyondaki_filmler_combo(vizyondaki_filmler_degistir_sil_filmler, vizyondaki_filmler_degistir_sil_uyari_mesaj_1);
        vizyondaki_filmler_degistir_pane_1.setVisible(true);
        vizyondaki_filmler_degistir_pane_2.setVisible(false);
        vizyondaki_filmler_degistir_geri_tusu.setVisible(true);
        vizyondaki_filmler_gosterim_geri_tusu.setVisible(false);
        vizyondaki_filmler_geri_tusu.setVisible(false);
        vizyondaki_filmler_degistir_sil_emin_misin.setVisible(false);

    }

    @FXML
    public void vizyondaki_filmler_degistir_geri(MouseEvent event) {
        vizyondaki_filmler_grid.setVisible(true);
        vizyondaki_filmler_degistir_pane.setVisible(false);

        vizyondaki_filmler_degistir_geri_tusu.setVisible(false);
        vizyondaki_filmler_geri_tusu.setVisible(true);

        //vizyondaki_filmler_table_admin();
    }

    @FXML
    public void vizyondaki_filmleri_degistir_sil_filmi_getir(ActionEvent event) {

        if (vizyondaki_filmler_degistir_sil_filmler.getValue() == null) {
            vizyondaki_filmler_degistir_sil_uyari_mesaj_1.setText("Lütfen bir film seçiniz.");
        } else {
            String[] secilen = vizyondaki_filmler_degistir_sil_filmler.getValue().split(" | ");
            vizyondaki_filmler_degistir_pane_2.setVisible(true);
            int vizyondaki_film_id = Integer.parseInt(secilen[0]);

            String film_name = Creator.vizyondaki_filmlerDao().film_adi_getir(vizyondaki_film_id);
            String film_type = Creator.vizyondaki_filmlerDao().film_type_getir(vizyondaki_film_id);
            int film_suresi = Creator.vizyondaki_filmlerDao().film_suresi_getir(vizyondaki_film_id);
            String yonetmen = Creator.filmlerDao().filmler_yonetmen_id_getir(Creator.vizyondaki_filmlerDao().film_id_getir(vizyondaki_film_id)) + " " + Creator.vizyondaki_filmlerDao().yonetmen_getir(vizyondaki_film_id);
            LocalDate kalkis = Creator.vizyondaki_filmlerDao().vizyondan_kalkis_tarihi_getir(vizyondaki_film_id);

            vizyondaki_filmleri_degistir_sil_film_name.setText(film_name);
            vizyondaki_filmleri_degistir_sil_film_type.setText(film_type);
            vizyondaki_filmleri_degistir_sil_film_suresi.setText(String.valueOf(film_suresi));
            vizyondaki_filmleri_degistir_sil_kalkis.setValue(kalkis);
            yonetmen_combo(vizyondaki_filmleri_degistir_sil_yonetmen, vizyondaki_filmler_degistir_sil_uyari_mesaj_2);
            vizyondaki_filmleri_degistir_sil_yonetmen.setValue(yonetmen);
            vizyondaki_filmler_degistir_sil_vizyon_id.setText(String.valueOf(vizyondaki_film_id));
            vizyondaki_filmler_degistir_sil_film_id.setText(String.valueOf(Creator.vizyondaki_filmlerDao().film_id_getir(vizyondaki_film_id)));
        }

    }

    @FXML
    public void vizyondaki_filmler_degistir_sil_degistir(ActionEvent event) {
        if ((vizyondaki_filmleri_degistir_sil_film_name.getText().length() == 0) || (vizyondaki_filmleri_degistir_sil_film_type.getText().length() == 0) || (vizyondaki_filmleri_degistir_sil_film_suresi.getText().length() == 0) || (vizyondaki_filmleri_degistir_sil_kalkis.getValue() == null)) {
            vizyondaki_filmler_degistir_sil_uyari_mesaj_2.setText("Lütfen Gerekli Yerleri Doldurunuz.");
        } else {
            String film_name = vizyondaki_filmleri_degistir_sil_film_name.getText();
            String film_type = vizyondaki_filmleri_degistir_sil_film_type.getText();
            String film_suresi = vizyondaki_filmleri_degistir_sil_film_suresi.getText();
            LocalDate kalkis = vizyondaki_filmleri_degistir_sil_kalkis.getValue();
            String yonetmen = vizyondaki_filmleri_degistir_sil_yonetmen.getValue();
            int yonetmen_id = 0;
            String[][] arr = Creator.yonetmenlerDao().yonetmen_combo_doldur();

            for (String[] arr1 : arr) {
                if (yonetmen.equals(arr1[0])) {
                    yonetmen_id = Integer.valueOf(arr1[1]);
                }
            }

            String vizyondaki_film_id = vizyondaki_filmler_degistir_sil_vizyon_id.getText();
            String film_id = vizyondaki_filmler_degistir_sil_film_id.getText();

            vizyondaki_filmler v = new vizyondaki_filmler(Integer.valueOf(vizyondaki_film_id), Integer.valueOf(film_id), kalkis, Creator.filmlerDao().kullanici_puani_getir(Integer.valueOf(film_id)), Creator.vizyondaki_filmlerDao().seans_sayisi_getir(Integer.valueOf(vizyondaki_film_id)));
            filmler f = new filmler(Integer.valueOf(film_id), film_name, Integer.valueOf(film_suresi), film_type, yonetmen_id);

            int sonuc = Creator.vizyondaki_filmlerDao().vizyondaki_filmler_degistir(v, f);
            if (sonuc == 1) {
                vizyondaki_filmler_degistir_sil_uyari_mesaj_1.setText("İşlem başarılı bir şekilde gerçekleştirildi.");

            } else {
                vizyondaki_filmler_degistir_sil_uyari_mesaj_2.setText("Bir hata meydana geldi. Lütfen daha sonra tekrar deneyiniz.");
            }
        }
    }

    @FXML
    public void vizyondaki_filmler_degistir_sil_sil(ActionEvent event) {
        vizyondaki_filmler_degistir_sil_emin_misin.setVisible(true);
    }

    public void vizyondaki_filmler_degistir_sil_tamamen_sil(ActionEvent event) {
        int vizyondaki_film_id = Integer.valueOf(vizyondaki_filmler_degistir_sil_vizyon_id.getText());
        int sonuc = Creator.vizyondaki_filmlerDao().vizyondaki_filmler_tamamen_sil(vizyondaki_film_id);
        if (sonuc == 1) {
            vizyondaki_filmler_degistir_sil_uyari_mesaj_1.setText("İstenilen Veri Tamamiyle Silindi");
            vizyondaki_filmler_degistir_sil_filmler.getItems().clear();
            vizyondaki_filmler_combo(vizyondaki_filmler_degistir_sil_filmler, vizyondaki_filmler_degistir_sil_uyari_mesaj_1);
            vizyondaki_filmler_degistir_pane_2.setVisible(false);
        } else {
            vizyondaki_filmler_degistir_sil_uyari_mesaj_2.setText("Bir hata meydana geldi lütfen daha sonra tekrar denetyiniz.");
        }
        vizyondaki_filmler_degistir_sil_emin_misin.setVisible(false);
    }

    public void vizyondaki_filmler_degistir_sil_vazgec(ActionEvent event) {
        vizyondaki_filmler_degistir_sil_emin_misin.setVisible(false);
    }

    public void vizyondaki_filmler_degistir_sil_sadece_vizyondan_sil(ActionEvent event) {
        vizyondaki_filmler_degistir_sil_emin_misin.setVisible(false);

        int vizyondaki_film_id = Integer.valueOf(vizyondaki_filmler_degistir_sil_vizyon_id.getText());
        int sonuc = Creator.vizyondaki_filmlerDao().vizyondaki_filmler_sadece_vizyondan_sil(vizyondaki_film_id);
        if (sonuc == 1) {
            vizyondaki_filmler_degistir_sil_uyari_mesaj_1.setText("İstenilen Veri Sadece Vizyonan Silindi");
            vizyondaki_filmler_degistir_sil_filmler.getItems().clear();
            vizyondaki_filmler_degistir_pane_2.setVisible(false);
            vizyondaki_filmler_combo(vizyondaki_filmler_degistir_sil_filmler, vizyondaki_filmler_degistir_sil_uyari_mesaj_1);
        } else {
            vizyondaki_filmler_degistir_sil_uyari_mesaj_2.setText("Bir hata meydana geldi lütfen daha sonra tekrar denetyiniz.");
        }
    }

    public void vizyondaki_filmler_table_admin() {

        ObservableList<vizyondaki_filmler> data = Mediator.data_vizyondaki_filmler_iki();

        vizyondaki_filmler_film_adi.setCellValueFactory(new PropertyValueFactory("film_name"));
        vizyondaki_filmler_film_type.setCellValueFactory(new PropertyValueFactory("film_type"));
        vizyondaki_filmler_film_suresi.setCellValueFactory(new PropertyValueFactory("film_suresi"));
        vizyondaki_filmler_yonetmen.setCellValueFactory(new PropertyValueFactory("yonetmen_ad_soyad"));
        vizyondaki_filmler_kalkis.setCellValueFactory(new PropertyValueFactory("vizyondan_kalkis_tarihi"));
        vizyondaki_filmler_kullanici_puani.setCellValueFactory(new PropertyValueFactory("kullanici_puani"));
        vizyondaki_filmler_detay.setCellValueFactory(new PropertyValueFactory("film_detayi"));

        FilteredList<vizyondaki_filmler> filteredData = new FilteredList<>(data, b -> true);

        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(viz -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (viz.getFilm_name().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (viz.getFilm_type().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(viz.getFilm_suresi()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (viz.getYonetmen_ad_soyad().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (viz.getVizyondan_kalkis_tarihi().toString().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(viz.getKullanici_puani()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }

            });
        });

        SortedList<vizyondaki_filmler> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(table_vizyondaki_filmler.comparatorProperty());

        table_vizyondaki_filmler.setItems(sortedData);
    }

    public void vizyondaki_filmler_gosterim() {
        int[] arr = Creator.vizyondaki_filmlerDao().vizyondaki_filmler_dizi_doldur();
        if (arr.length <= 10) {
            vizyondaki_filmler_gosterim_pane_sayfa.setVisible(false);
            switch (arr.length) {
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

                    vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_bir, Creator.vizyondaki_filmlerDao().film_id_getir(arr[0]), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_bir, vizyondaki_filmler_gosterim_adi_bir);
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

                    vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_bir, Creator.vizyondaki_filmlerDao().film_id_getir(arr[0]), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_bir, vizyondaki_filmler_gosterim_adi_bir);
                    vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_iki, Creator.vizyondaki_filmlerDao().film_id_getir(arr[1]), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_iki, vizyondaki_filmler_gosterim_adi_iki);
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

                    vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_bir, Creator.vizyondaki_filmlerDao().film_id_getir(arr[0]), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_bir, vizyondaki_filmler_gosterim_adi_bir);
                    vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_iki, Creator.vizyondaki_filmlerDao().film_id_getir(arr[1]), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_iki, vizyondaki_filmler_gosterim_adi_iki);
                    vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_uc, Creator.vizyondaki_filmlerDao().film_id_getir(arr[2]), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_uc, vizyondaki_filmler_gosterim_adi_uc);
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

                    vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_bir, Creator.vizyondaki_filmlerDao().film_id_getir(arr[0]), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_bir, vizyondaki_filmler_gosterim_adi_bir);
                    vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_iki, Creator.vizyondaki_filmlerDao().film_id_getir(arr[1]), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_iki, vizyondaki_filmler_gosterim_adi_iki);
                    vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_uc, Creator.vizyondaki_filmlerDao().film_id_getir(arr[2]), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_uc, vizyondaki_filmler_gosterim_adi_uc);
                    vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_dort, Creator.vizyondaki_filmlerDao().film_id_getir(arr[3]), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_dort, vizyondaki_filmler_gosterim_adi_dort);
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

                    vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_bir, Creator.vizyondaki_filmlerDao().film_id_getir(arr[0]), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_bir, vizyondaki_filmler_gosterim_adi_bir);
                    vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_iki, Creator.vizyondaki_filmlerDao().film_id_getir(arr[1]), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_iki, vizyondaki_filmler_gosterim_adi_iki);
                    vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_uc, Creator.vizyondaki_filmlerDao().film_id_getir(arr[2]), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_uc, vizyondaki_filmler_gosterim_adi_uc);
                    vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_dort, Creator.vizyondaki_filmlerDao().film_id_getir(arr[3]), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_dort, vizyondaki_filmler_gosterim_adi_dort);
                    vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_bes, Creator.vizyondaki_filmlerDao().film_id_getir(arr[4]), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_bes, vizyondaki_filmler_gosterim_adi_bes);
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

                    vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_bir, Creator.vizyondaki_filmlerDao().film_id_getir(arr[0]), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_bir, vizyondaki_filmler_gosterim_adi_bir);
                    vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_iki, Creator.vizyondaki_filmlerDao().film_id_getir(arr[1]), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_iki, vizyondaki_filmler_gosterim_adi_iki);
                    vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_uc, Creator.vizyondaki_filmlerDao().film_id_getir(arr[2]), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_uc, vizyondaki_filmler_gosterim_adi_uc);
                    vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_dort, Creator.vizyondaki_filmlerDao().film_id_getir(arr[3]), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_dort, vizyondaki_filmler_gosterim_adi_dort);
                    vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_bes, Creator.vizyondaki_filmlerDao().film_id_getir(arr[4]), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_bes, vizyondaki_filmler_gosterim_adi_bes);
                    vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_alti, Creator.vizyondaki_filmlerDao().film_id_getir(arr[5]), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_alti, vizyondaki_filmler_gosterim_adi_alti);
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

                    vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_bir, Creator.vizyondaki_filmlerDao().film_id_getir(arr[0]), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_bir, vizyondaki_filmler_gosterim_adi_bir);
                    vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_iki, Creator.vizyondaki_filmlerDao().film_id_getir(arr[1]), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_iki, vizyondaki_filmler_gosterim_adi_iki);
                    vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_uc, Creator.vizyondaki_filmlerDao().film_id_getir(arr[2]), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_uc, vizyondaki_filmler_gosterim_adi_uc);
                    vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_dort, Creator.vizyondaki_filmlerDao().film_id_getir(arr[3]), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_dort, vizyondaki_filmler_gosterim_adi_dort);
                    vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_bes, Creator.vizyondaki_filmlerDao().film_id_getir(arr[4]), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_bes, vizyondaki_filmler_gosterim_adi_bes);
                    vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_alti, Creator.vizyondaki_filmlerDao().film_id_getir(arr[5]), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_alti, vizyondaki_filmler_gosterim_adi_alti);
                    vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_yedi, Creator.vizyondaki_filmlerDao().film_id_getir(arr[6]), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_yedi, vizyondaki_filmler_gosterim_adi_yedi);
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

                    vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_bir, Creator.vizyondaki_filmlerDao().film_id_getir(arr[0]), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_bir, vizyondaki_filmler_gosterim_adi_bir);
                    vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_iki, Creator.vizyondaki_filmlerDao().film_id_getir(arr[1]), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_iki, vizyondaki_filmler_gosterim_adi_iki);
                    vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_uc, Creator.vizyondaki_filmlerDao().film_id_getir(arr[2]), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_uc, vizyondaki_filmler_gosterim_adi_uc);
                    vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_dort, Creator.vizyondaki_filmlerDao().film_id_getir(arr[3]), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_dort, vizyondaki_filmler_gosterim_adi_dort);
                    vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_bes, Creator.vizyondaki_filmlerDao().film_id_getir(arr[4]), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_bes, vizyondaki_filmler_gosterim_adi_bes);
                    vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_alti, Creator.vizyondaki_filmlerDao().film_id_getir(arr[5]), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_alti, vizyondaki_filmler_gosterim_adi_alti);
                    vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_yedi, Creator.vizyondaki_filmlerDao().film_id_getir(arr[6]), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_yedi, vizyondaki_filmler_gosterim_adi_yedi);
                    vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_sekiz, Creator.vizyondaki_filmlerDao().film_id_getir(arr[7]), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_sekiz, vizyondaki_filmler_gosterim_adi_sekiz);
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

                    vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_bir, Creator.vizyondaki_filmlerDao().film_id_getir(arr[0]), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_bir, vizyondaki_filmler_gosterim_adi_bir);
                    vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_iki, Creator.vizyondaki_filmlerDao().film_id_getir(arr[1]), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_iki, vizyondaki_filmler_gosterim_adi_iki);
                    vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_uc, Creator.vizyondaki_filmlerDao().film_id_getir(arr[2]), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_uc, vizyondaki_filmler_gosterim_adi_uc);
                    vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_dort, Creator.vizyondaki_filmlerDao().film_id_getir(arr[3]), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_dort, vizyondaki_filmler_gosterim_adi_dort);
                    vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_bes, Creator.vizyondaki_filmlerDao().film_id_getir(arr[4]), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_bes, vizyondaki_filmler_gosterim_adi_bes);
                    vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_alti, Creator.vizyondaki_filmlerDao().film_id_getir(arr[5]), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_alti, vizyondaki_filmler_gosterim_adi_alti);
                    vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_yedi, Creator.vizyondaki_filmlerDao().film_id_getir(arr[6]), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_yedi, vizyondaki_filmler_gosterim_adi_yedi);
                    vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_sekiz, Creator.vizyondaki_filmlerDao().film_id_getir(arr[7]), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_sekiz, vizyondaki_filmler_gosterim_adi_sekiz);
                    vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_dokuz, Creator.vizyondaki_filmlerDao().film_id_getir(arr[8]), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_dokuz, vizyondaki_filmler_gosterim_adi_dokuz);
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

                    vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_bir, Creator.vizyondaki_filmlerDao().film_id_getir(arr[0]), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_bir, vizyondaki_filmler_gosterim_adi_bir);
                    vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_iki, Creator.vizyondaki_filmlerDao().film_id_getir(arr[1]), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_iki, vizyondaki_filmler_gosterim_adi_iki);
                    vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_uc, Creator.vizyondaki_filmlerDao().film_id_getir(arr[2]), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_uc, vizyondaki_filmler_gosterim_adi_uc);
                    vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_dort, Creator.vizyondaki_filmlerDao().film_id_getir(arr[3]), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_dort, vizyondaki_filmler_gosterim_adi_dort);
                    vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_bes, Creator.vizyondaki_filmlerDao().film_id_getir(arr[4]), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_bes, vizyondaki_filmler_gosterim_adi_bes);
                    vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_alti, Creator.vizyondaki_filmlerDao().film_id_getir(arr[5]), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_alti, vizyondaki_filmler_gosterim_adi_alti);
                    vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_yedi, Creator.vizyondaki_filmlerDao().film_id_getir(arr[6]), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_yedi, vizyondaki_filmler_gosterim_adi_yedi);
                    vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_sekiz, Creator.vizyondaki_filmlerDao().film_id_getir(arr[7]), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_sekiz, vizyondaki_filmler_gosterim_adi_sekiz);
                    vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_dokuz, Creator.vizyondaki_filmlerDao().film_id_getir(arr[8]), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_dokuz, vizyondaki_filmler_gosterim_adi_dokuz);
                    vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_on, Creator.vizyondaki_filmlerDao().film_id_getir(arr[9]), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_on, vizyondaki_filmler_gosterim_adi_on);
                    break;
                default:
                    break;
            }

        } else {

            vizyondaki_filmler_gosterim_pane_sayfa.setVisible(true);

            vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_bir, Creator.vizyondaki_filmlerDao().film_id_getir(arr[0]), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_bir, vizyondaki_filmler_gosterim_adi_bir);
            vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_iki, Creator.vizyondaki_filmlerDao().film_id_getir(arr[1]), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_iki, vizyondaki_filmler_gosterim_adi_iki);
            vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_uc, Creator.vizyondaki_filmlerDao().film_id_getir(arr[2]), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_uc, vizyondaki_filmler_gosterim_adi_uc);
            vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_dort, Creator.vizyondaki_filmlerDao().film_id_getir(arr[3]), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_dort, vizyondaki_filmler_gosterim_adi_dort);
            vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_bes, Creator.vizyondaki_filmlerDao().film_id_getir(arr[4]), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_bes, vizyondaki_filmler_gosterim_adi_bes);
            vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_alti, Creator.vizyondaki_filmlerDao().film_id_getir(arr[5]), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_alti, vizyondaki_filmler_gosterim_adi_alti);
            vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_yedi, Creator.vizyondaki_filmlerDao().film_id_getir(arr[6]), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_yedi, vizyondaki_filmler_gosterim_adi_yedi);
            vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_sekiz, Creator.vizyondaki_filmlerDao().film_id_getir(arr[7]), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_sekiz, vizyondaki_filmler_gosterim_adi_sekiz);
            vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_dokuz, Creator.vizyondaki_filmlerDao().film_id_getir(arr[8]), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_dokuz, vizyondaki_filmler_gosterim_adi_dokuz);
            vizyondaki_filmler_gosterim_doldur(vizyondaki_filmler_gosterim_on, Creator.vizyondaki_filmlerDao().film_id_getir(arr[9]), vizyondaki_filmler_gosterim_uyari_mesaj, vizyondaki_filmler_gosterim_id_on, vizyondaki_filmler_gosterim_adi_on);

        }
    }

    public void vizyondaki_filmler_gosterim_doldur(ImageView img, int film_id, Label msg, Label id, Label ad) {
        vizyondaki_filmler_imageview_doldur(img, film_id, msg);
        id.setText(String.valueOf(film_id));
        ad.setText(new filmlerDAO().filmler_film_adi_getir(film_id));
    }

    public void vizyondaki_filmler_imageview_doldur(ImageView img, int film_id, Label msg) {
        int control = new films_photosDAO().kac_tane_film_id_var(film_id);

        if (control == 1) {
            BufferedImage bufferedImage = null;
            try {
                String photo_path = new films_photosDAO().photo_path_getir(film_id);
                bufferedImage = ImageIO.read(new File(photo_path));
            } catch (IOException ex) {
                System.out.println(ex);
                msg.setText("Bir Hata Meydana Geldi (Hata Kodu: -38)");
            }
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            img.setImage(image);
        } else if (control != 0) {
            msg.setText("Bir Hata Meydana Geldi (Hata Kodu: -39)");
            System.out.println(control);
        }
    }

    @FXML
    public void vizyondaki_filmler_imageview_bir(MouseEvent event) {
        imageview_gosterim_ortak(vizyondaki_filmler_gosterim_bir, Integer.parseInt(vizyondaki_filmler_gosterim_id_bir.getText()));
    }

    @FXML
    public void vizyondaki_filmler_imageview_iki(MouseEvent event) {
        imageview_gosterim_ortak(vizyondaki_filmler_gosterim_iki, Integer.parseInt(vizyondaki_filmler_gosterim_id_iki.getText()));
    }

    @FXML
    public void vizyondaki_filmler_imageview_uc(MouseEvent event) {
        imageview_gosterim_ortak(vizyondaki_filmler_gosterim_uc, Integer.parseInt(vizyondaki_filmler_gosterim_id_uc.getText()));
    }

    @FXML
    public void vizyondaki_filmler_imageview_dort(MouseEvent event) {
        imageview_gosterim_ortak(vizyondaki_filmler_gosterim_dort, Integer.parseInt(vizyondaki_filmler_gosterim_id_dort.getText()));
    }

    @FXML
    public void vizyondaki_filmler_imageview_bes(MouseEvent event) {
        imageview_gosterim_ortak(vizyondaki_filmler_gosterim_bes, Integer.parseInt(vizyondaki_filmler_gosterim_id_bes.getText()));
    }

    @FXML
    public void vizyondaki_filmler_imageview_alti(MouseEvent event) {
        imageview_gosterim_ortak(vizyondaki_filmler_gosterim_alti, Integer.parseInt(vizyondaki_filmler_gosterim_id_alti.getText()));
    }

    @FXML
    public void vizyondaki_filmler_imageview_yedi(MouseEvent event) {
        imageview_gosterim_ortak(vizyondaki_filmler_gosterim_yedi, Integer.parseInt(vizyondaki_filmler_gosterim_id_yedi.getText()));
    }

    @FXML
    public void vizyondaki_filmler_imageview_sekiz(MouseEvent event) {
        imageview_gosterim_ortak(vizyondaki_filmler_gosterim_sekiz, Integer.parseInt(vizyondaki_filmler_gosterim_id_sekiz.getText()));
    }

    @FXML
    public void vizyondaki_filmler_imageview_dokuz(MouseEvent event) {
        imageview_gosterim_ortak(vizyondaki_filmler_gosterim_dokuz, Integer.parseInt(vizyondaki_filmler_gosterim_id_dokuz.getText()));
    }

    @FXML
    public void vizyondaki_filmler_imageview_on(MouseEvent event) {
        imageview_gosterim_ortak(vizyondaki_filmler_gosterim_on, Integer.parseInt(vizyondaki_filmler_gosterim_id_on.getText()));
    }

    public void imageview_gosterim_ortak(ImageView img, int film_id) {
        vizyondaki_filmler_resimli_gosterim.setVisible(false);
        pnl_film_detay.setVisible(true);

        int vizyondaki_film_id = Creator.vizyondaki_filmlerDao().film_id_ile_vizyondaki_film_id_getir(film_id);

        vizyondaki_filmler_detay_film_id.setText(String.valueOf(film_id));
        vizyondaki_filmler_detay_film_adi.setText(Creator.filmlerDao().filmler_film_adi_getir(film_id));
        vizyondaki_filmler_detay_film_turu.setText(Creator.filmlerDao().filmler_film_type_getir(film_id));
        vizyondaki_filmler_detay_film_suresi.setText(String.valueOf(Creator.filmlerDao().filmler_film_suresi_getir(film_id)));
        vizyondaki_filmler_detay_yonetmen.setText(Creator.yonetmenlerDao().yonetmenler_yonetmen_getir(Creator.filmlerDao().filmler_yonetmen_id_getir(film_id)));
        vizyondaki_filmler_detay_kalkis_tarihi.setText(Creator.vizyondaki_filmlerDao().vizyondan_kalkis_tarihi_getir(vizyondaki_film_id).toString());
        vizyondaki_filmler_detay_kullanici_puani.setText(String.valueOf(Creator.filmlerDao().kullanici_puani_getir(film_id)));

        films_photosDAO fpdao = new films_photosDAO();

        int control = fpdao.kac_tane_film_id_var(film_id);

        if (new films_photosDAO().kac_tane_film_id_var(film_id) == 1) {
            BufferedImage bufferedImage = null;
            try {
                String photo_path = new films_photosDAO().photo_path_getir(film_id);
                bufferedImage = ImageIO.read(new File(photo_path));
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                img.setImage(image);
                vizyondaki_filmler_film_detay_photo.setImage(image);
            } catch (IOException ex) {
                System.out.println(ex);
                guncelle_mesaj.setText("Bir Hata Meydana Geldi (Hata Kodu: -40)");
            }
        } else if (control == 0) {
            try {
                BufferedImage bufferedImage = null;
                String photo_path = "src/lib/pic/Movies.png";
                bufferedImage = ImageIO.read(new File(photo_path));
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                vizyondaki_filmler_film_detay_photo.setImage(image);
            } catch (IOException ex) {
                System.out.println(ex);
                guncelle_mesaj.setText("Bir Hata Meydana Geldi (Hata Kodu: -42)");
            }
        } else {
            guncelle_mesaj.setText("Bir Hata Meydana Geldi (Hata Kodu: -43)");
        }
    }

    @FXML
    public void film_detay_geri() {
        vizyondaki_filmler_resimli_gosterim.setVisible(true);
        pnl_film_detay.setVisible(false);

        vizyondaki_filmler_gosterim();
    }

    @FXML
    public void vizyondak_filmler_gosterim_arama(ActionEvent event) {
        vizyondaki_filmler_grid.setVisible(true);
        vizyondaki_filmler_resimli_gosterim.setVisible(false);

        vizyondaki_filmler_geri_tusu.setVisible(false);
        vizyondaki_filmler_gosterim_geri_tusu.setVisible(true);

        vizyondaki_filmler_table_admin();
    }

    @FXML
    public void vizyondaki_filmler_arama_geri(MouseEvent event) {
        vizyondaki_filmler_resimli_gosterim.setVisible(true);
        vizyondaki_filmler_grid.setVisible(false);

        vizyondaki_filmler_geri_tusu.setVisible(true);
        vizyondaki_filmler_gosterim_geri_tusu.setVisible(false);

        vizyondaki_filmler_gosterim();
    }

}
