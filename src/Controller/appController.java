package Controller;

import Code_Admin.Center_Admin;
import Creator.Creator;
import DAO.*;
import entity.*;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;

public class appController extends Center_Admin implements Initializable {

    /*
    Eski Filmler
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
     */
    private void eski_filmler_combo(ComboBox<String> combo, Label uyari_mesaj) {
        eski_filmlerDAO eski_film_islemleri = new eski_filmlerDAO();
        String[][] arr = eski_film_islemleri.eski_filmler_combo_doldur();
        combo.getItems().clear();
        if (arr.length == 0) {
            uyari_mesaj.setText("Kayıtlı Eski Film Bulunamadı. Lütfen önce bir vizyona film ekleyiniz ekleyiniz.");

        } else {
            for (String[] arr1 : arr) {
                String v_filmler = arr1[0];
                combo.getItems().addAll(v_filmler);
            }

            combo.setPromptText("İstediğiniz filmi seçiniz.");
        }
    }

    @FXML
    private void eski_filmler_geri(MouseEvent event) {
        geri_don_admin();
    }

    @FXML
    private void eski_filmler_ekle_geri(MouseEvent event) {
        eski_filmler_grid.setVisible(true);
        eski_filmler_ekle_pane.setVisible(false);

        eski_filmler_geri_tusu.setVisible(true);
        eski_filmler_ekle_geri_tusu.setVisible(false);

        eski_filmler_table_aboneli();
    }

    @FXML
    private void eski_filmler_degistir_geri(MouseEvent event) {
        eski_filmler_grid.setVisible(true);
        eski_filmler_degistir_pane.setVisible(false);

        eski_filmler_degistir_geri_tusu.setVisible(false);
        eski_filmler_geri_tusu.setVisible(true);

        eski_filmler_table_aboneli();

    }

    @FXML
    private void eski_filmler_ekle_giris(ActionEvent event) {

        eski_filmler_grid.setVisible(false);
        eski_filmler_ekle_pane.setVisible(true);
        yonetmen_combo(eski_ekle_yonetmenler, eski_ekle_uyari_mesaj);
        hangi_aboneler_combo(eski_hangi_aboneler);
        eski_filmler_geri_tusu.setVisible(false);
        eski_filmler_ekle_geri_tusu.setVisible(true);
        eski_filmler_degistir_geri_tusu.setVisible(false);
        eski_filmler_degistir_pane.setVisible(false);
    }

    private void hangi_aboneler_combo(ComboBox<String> combo) {

        combo.getItems().clear();

        String a = "0";
        String b = "1";
        String c = "2";
        String d = "3";

        combo.getItems().addAll(a);
        combo.getItems().addAll(b);
        combo.getItems().addAll(c);
        combo.getItems().addAll(d);

        combo.setPromptText("İstediğiniz Abone Türünü seçiniz.");

    }

    @FXML
    private void eski_filmler_ekle_sifirla(ActionEvent event) {
        eski_film_name.setText("");
        eski_film_type.setText("");
        eski_film_suresi.setText("");
        eski_aldigi_odul_sayisi.setText("");
        yonetmen_combo(eski_ekle_yonetmenler, eski_ekle_uyari_mesaj);
        hangi_aboneler_combo(eski_hangi_aboneler);
    }

    @FXML
    private void eski_filmler_ekle(ActionEvent event) {
        String film_name = eski_film_name.getText();
        int film_suresi = 0;
        int control = -1;
        try {
            film_suresi = Integer.parseInt(eski_film_suresi.getText());
            control = 1;
        } catch (NumberFormatException e) {
            eski_ekle_uyari_mesaj.setText("Lütfen film süresine sadeca sayı giriniz.(dk olarak)");
        }
        if (control != -1) {
            String film_type = eski_film_type.getText();
            int aldigi_odul = 0;
            control = -1;
            try {
                aldigi_odul = Integer.parseInt(eski_aldigi_odul_sayisi.getText());
                control = 1;
            } catch (NumberFormatException e) {
                eski_ekle_uyari_mesaj.setText("Lütfen aldığı ödül sayısını sayı olarak giriniz.");

            }
            if (control != -1) {
                String yonetmen = eski_ekle_yonetmenler.getValue();
                String hangi = eski_hangi_aboneler.getValue();
                if ((film_name.length() == 0) || (eski_filmler_film_suresi.getText().length() == 0) || (film_type.length() == 0) || eski_aldigi_odul_sayisi.getLength() == 0 || (yonetmen == null) || (hangi == null)) {
                    eski_ekle_uyari_mesaj.setText("Lütfen gerekli yerleri doldurunuz.");
                } else {
                    yonetmenlerDAO y = new yonetmenlerDAO();
                    String[][] arr = y.yonetmen_combo_doldur();
                    int yonetmen_id = 0;
                    for (int i = 0; i < arr.length; i++) {
                        if (arr[i][0].equals(yonetmen)) {
                            yonetmen_id = Integer.valueOf(arr[i][1]);
                            i = arr.length + 1;
                        }
                    }
                    int hangi_abone = Integer.valueOf(hangi);

                    filmler f = new filmler(film_name, film_suresi, film_type, yonetmen_id, 0);
                    filmlerDAO fdao = new filmlerDAO();
                    int film_id = fdao.filmler_ekle_id_gonder(f);
                    eski_filmler ef = new eski_filmler(film_id, hangi_abone, aldigi_odul);
                    eski_filmlerDAO edao = new eski_filmlerDAO();
                    int sonuc = edao.eski_filmler_dao_ekle(ef);

                    if ((sonuc == 1)) {
                        eski_ekle_uyari_mesaj.setText("İşlem Başarılı Bir Şekilde Gerçekleşti.");
                    }

                }
            }
        }
    }

    @FXML
    private void eski_filmler_degistir_sil_giris(ActionEvent event) {

        eski_filmler_grid.setVisible(false);
        eski_filmler_degistir_pane.setVisible(true);
        eski_filmler_combo(eski_degistir_sil_filmler, eski_filmler_degistir_sil_uyari_mesaj_1);
        eski_filmler_degistir_pane_1.setVisible(true);
        eski_filmler_degistir_pane_2.setVisible(false);
        eski_filmler_degistir_geri_tusu.setVisible(true);
        eski_filmler_geri_tusu.setVisible(false);
        eski_filmler_degistir_sil_emin_misin.setVisible(false);

    }

    @FXML
    private void eski_filmler_degistir_sil_filmi_getir(ActionEvent event) {

        if (eski_degistir_sil_filmler.getValue() == null) {
            eski_filmler_degistir_sil_uyari_mesaj_1.setText("Lütfen bir film seçiniz.");
        } else {
            String secilen = eski_degistir_sil_filmler.getValue();
            eski_filmler_degistir_pane_2.setVisible(true);
            eski_filmlerDAO eski_film_islemleri = new eski_filmlerDAO();
            String[][] arr = eski_film_islemleri.eski_filmler_combo_doldur();
            int eski_film_id = 0;
            for (int i = 0; i < arr.length; i++) {
                if (secilen.equals(arr[i][0])) {
                    eski_film_id = Integer.valueOf(arr[i][1]);
                }
            }
            String film_name = eski_film_islemleri.film_adi_getir(eski_film_id);
            String film_type = eski_film_islemleri.film_type_getir(eski_film_id);
            int film_suresi = eski_film_islemleri.film_suresi_getir(eski_film_id);
            String yonetmen = eski_film_islemleri.yonetmen_id_getir(eski_film_id) + " " + eski_film_islemleri.yonetmen_getir(eski_film_id);
            int hangi_abone = eski_film_islemleri.hangi_abone_getir(eski_film_id);
            int aldigi_odul = eski_film_islemleri.aldigi_odul_sayisi_getir(eski_film_id);

            eski_filmleri_degistir_sil_film_name.setText(film_name);
            eski_filmleri_degistir_sil_film_type.setText(film_type);
            eski_filmleri_degistir_sil_film_suresi.setText(String.valueOf(film_suresi));
            eski_filmleri_degistir_sil_aldigi_odul.setText(String.valueOf(aldigi_odul));

            yonetmen_combo(eski_filmleri_degistir_sil_yonetmen, eski_filmler_degistir_sil_uyari_mesaj_2);
            eski_filmleri_degistir_sil_yonetmen.setValue(yonetmen);

            hangi_aboneler_combo(eski_filmleri_degistir_sil_hangi_abone);
            eski_filmleri_degistir_sil_hangi_abone.setValue(String.valueOf(hangi_abone));

            eski_filmler_degistir_sil_eski_id.setText(String.valueOf(eski_film_id));
        }

    }

    @FXML
    private void eski_filmler_degistir_sil_degistir(ActionEvent event) {
        if ((eski_filmleri_degistir_sil_film_name.getText().length() == 0) || (eski_filmleri_degistir_sil_film_type.getText().length() == 0) || (eski_filmleri_degistir_sil_film_suresi.getText().length() == 0) || (eski_filmleri_degistir_sil_aldigi_odul.getText().length() == 0)
                || (eski_filmleri_degistir_sil_hangi_abone.getValue() == null) || (eski_filmleri_degistir_sil_yonetmen.getValue() == null)) {
            eski_filmler_degistir_sil_uyari_mesaj_2.setText("Lütfen Gerekli Yerleri doldurunuz.");
        } else {
            String film_name = eski_filmleri_degistir_sil_film_name.getText();
            String film_type = eski_filmleri_degistir_sil_film_type.getText();
            try {
                int film_suresi = Integer.parseInt(eski_filmleri_degistir_sil_film_suresi.getText());
                try {
                    int aldigi_odul = Integer.parseInt(eski_filmleri_degistir_sil_aldigi_odul.getText());
                    int hangi_abone = Integer.parseInt(eski_filmleri_degistir_sil_hangi_abone.getValue());
                    yonetmenlerDAO y = new yonetmenlerDAO();
                    String yonetmen = eski_filmleri_degistir_sil_yonetmen.getValue();
                    String[][] arr = y.yonetmen_combo_doldur();
                    int yonetmen_id = 0;
                    for (int i = 0; i < arr.length; i++) {
                        if (arr[i][0].equals(yonetmen)) {
                            yonetmen_id = Integer.valueOf(arr[i][1]);
                            break;
                        }
                    }
                    int eski_film_id = Integer.parseInt(eski_filmler_degistir_sil_eski_id.getText());
                    eski_filmlerDAO edao = new eski_filmlerDAO();
                    int film_id = edao.film_id_getir(eski_film_id);
                    eski_filmler e = new eski_filmler(eski_film_id, film_id, film_name, film_type, film_suresi, aldigi_odul, hangi_abone, yonetmen_id);

                    int sonuc = edao.eski_filmler_degistir(e);
                    if (sonuc == 1) {
                        eski_filmler_degistir_sil_uyari_mesaj_2.setText("İşlem başarılı bir şekilde gerçekleştirildi.");
                    } else {
                        eski_filmler_degistir_sil_uyari_mesaj_2.setText("Bir hata meydana geldi lütfen daha sonra tekrar deneyiniz..");
                    }

                } catch (NumberFormatException e) {
                    eski_filmler_degistir_sil_uyari_mesaj_2.setText("Lütfen aldığı ödülleri sadece sayı olarak giriniz.");
                }
            } catch (NumberFormatException e) {
                eski_filmler_degistir_sil_uyari_mesaj_2.setText("Lütfen Süreyi sadece sayı olarak giriniz (dk olarak).");
            }
        }
    }

    @FXML
    private void eski_filmler_degistir_sil_sil(ActionEvent event) {
        eski_filmler_degistir_sil_emin_misin.setVisible(true);
    }

    @FXML
    private void eski_filmler_degistir_sil_silmekten_emin_vazgec(ActionEvent event) {
        eski_filmler_degistir_sil_emin_misin.setVisible(false);
    }

    @FXML
    private void eski_filmler_degistir_sil_silmekten_emin_tamamen_sil(ActionEvent event) {
        int eski_film_id = Integer.parseInt(eski_filmler_degistir_sil_eski_id.getText());

        eski_filmlerDAO edao = new eski_filmlerDAO();

        int sonuc = edao.eski_filmler_tamamen_sil(eski_film_id);

        if (sonuc == 1) {
            eski_filmler_combo(eski_degistir_sil_filmler, eski_filmler_degistir_sil_uyari_mesaj_1);
            eski_filmleri_degistir_sil_film_name.setText("");
            eski_filmleri_degistir_sil_film_type.setText("");
            eski_filmleri_degistir_sil_film_suresi.setText("");
            eski_filmleri_degistir_sil_aldigi_odul.setText("");
            hangi_aboneler_combo(eski_filmleri_degistir_sil_hangi_abone);
            yonetmen_combo(eski_filmleri_degistir_sil_yonetmen, eski_filmler_degistir_sil_uyari_mesaj_2);

            eski_filmler_degistir_pane_2.setVisible(false);
            eski_filmler_degistir_sil_emin_misin.setVisible(false);

            eski_filmler_degistir_sil_uyari_mesaj_1.setText("İşlem başarılı bir şekilde gerçekleştirildi.");
        } else {
            eski_filmler_degistir_sil_uyari_mesaj_2.setText("Bir hata meydana geldi.Lütfen daha sonra tekrar deneyiniz.");
        }
    }

    @FXML
    private void eski_filmler_degistir_sil_silmekten_emin_sadece_eski_filmden_sil(ActionEvent event) {
        int eski_film_id = Integer.parseInt(eski_filmler_degistir_sil_eski_id.getText());

        eski_filmlerDAO edao = new eski_filmlerDAO();

        int sonuc = edao.eski_filmler_sadece_eskiden_sil(eski_film_id);

        if (sonuc == 1) {
            eski_filmler_combo(eski_degistir_sil_filmler, eski_filmler_degistir_sil_uyari_mesaj_1);
            eski_filmleri_degistir_sil_film_name.setText("");
            eski_filmleri_degistir_sil_film_type.setText("");
            eski_filmleri_degistir_sil_film_suresi.setText("");
            eski_filmleri_degistir_sil_aldigi_odul.setText("");
            hangi_aboneler_combo(eski_filmleri_degistir_sil_hangi_abone);
            yonetmen_combo(eski_filmleri_degistir_sil_yonetmen, eski_filmler_degistir_sil_uyari_mesaj_2);

            eski_filmler_degistir_pane_2.setVisible(false);
            eski_filmler_degistir_sil_emin_misin.setVisible(false);

            eski_filmler_degistir_sil_uyari_mesaj_1.setText("İşlem başarılı bir şekilde gerçekleştirildi.");
        } else {
            eski_filmler_degistir_sil_uyari_mesaj_2.setText("Bir hata meydana geldi.Lütfen daha sonra tekrar deneyiniz.");
        }
    }

    private void eski_filmler_table_aboneli() {
        eski_filmlerDAO edao = new eski_filmlerDAO();

        ObservableList<eski_filmler> data = FXCollections.observableArrayList();

        data = edao.eski_filmler_select(data);

        eski_filmler_film_adi.setCellValueFactory(new PropertyValueFactory("film_name"));
        eski_filmler_film_type.setCellValueFactory(new PropertyValueFactory("film_type"));
        eski_filmler_film_suresi.setCellValueFactory(new PropertyValueFactory("film_suresi"));
        eski_filmler_yonetmen.setCellValueFactory(new PropertyValueFactory("yonetmen_ad_soyad"));
        eski_filmler_hangi_abone.setCellValueFactory(new PropertyValueFactory("hangi_aboneler_izleyebilir"));
        eski_filmler_aldigi_odul_sayisi.setCellValueFactory(new PropertyValueFactory("aldigi_odul_sayisi"));
        eski_filmler_kullanici_puani.setCellValueFactory(new PropertyValueFactory("kullanici_puani"));
        eski_filmler_detay.setCellValueFactory(new PropertyValueFactory("detay"));

        FilteredList<eski_filmler> filteredData = new FilteredList<>(data, b -> true);
        filterField_eski.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(esk -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (esk.getFilm_name().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (esk.getFilm_type().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(esk.getFilm_suresi()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (esk.getYonetmen_ad_soyad().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(esk.getHangi_aboneler_izleyebilir()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(esk.getAldigi_odul_sayisi()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(esk.getKullanici_puani()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }

            });
        });

        SortedList<eski_filmler> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(table_eski_filmler.comparatorProperty());

        table_eski_filmler.setItems(sortedData);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /*
    Kampanyalar
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     */
    
    private void kampanyalar_combo_doldur(ComboBox<String> combo, Label uyari_mesaj) {
        kampanyalarDAO kampanya_islemleri = new kampanyalarDAO();
        String[][] arr = kampanya_islemleri.kampanyalar_combo_doldur();
        combo.getItems().clear();
        if (arr.length == 0) {
            uyari_mesaj.setText("Kayıtlı Kampanya Bulunamadı. Lütfen Önce Bir Kampanya Ekleyiniz.");

        } else {
            for (int i = 0; i < arr.length; i++) {
                String k = arr[i][0];
                combo.getItems().addAll(k);
            }
            combo.setPromptText("İstediğiniz Kategoriyi seçiniz.");
        }
    }

    @FXML
    private void kampanyalar_ekle_giris(ActionEvent event) {
        kampanyalar_grid.setVisible(false);
        kampanyalar_ekle_pane.setVisible(true);
        kampanyalar_degistir_pane.setVisible(false);

        hangi_aboneler_combo(kampanyalar_ekle_hangi_kullanici);

        kampanyalar_ekle_geri_tusu.setVisible(true);
        kampanyalar_geri_tusu.setVisible(false);
        kampanyalar_degistir_geri_tusu.setVisible(false);
    }

    @FXML
    private void kampanyalar_degistir_giris(ActionEvent event) {
        kampanyalar_grid.setVisible(false);
        kampanyalar_ekle_pane.setVisible(false);
        kampanyalar_degistir_pane.setVisible(true);

        hangi_aboneler_combo(kampanyalar_degistir_hangi_kullanici);

        kampanyalar_combo_doldur(kampanyalar_degistir_kampanyalari_getir, kampanyalar_degistir_uyari_mesaj_1);

        kampanyalar_geri_tusu.setVisible(false);
        kampanyalar_ekle_geri_tusu.setVisible(false);
        kampanyalar_degistir_geri_tusu.setVisible(true);
    }

    @FXML
    private void kampanyalar_ekle_sifirla(ActionEvent event) {
        kampanyalar_ekle_title.setText("");
        kampanyalar_ekle_tarih.setText("");
        kampanyalar_ekle_kategori.setText("");
        kampanyalar_ekle_kampanya.setText("");
        hangi_aboneler_combo(kampanyalar_ekle_hangi_kullanici);

    }

    @FXML
    private void kampanyalar_ekle_ekle(ActionEvent event) {
        if ((kampanyalar_ekle_title.getText().length() == 0) || (kampanyalar_ekle_tarih.getText().length() == 0) || (kampanyalar_ekle_kategori.getText().length() == 0)
                || (kampanyalar_ekle_kampanya.getText().length() == 0) || (kampanyalar_ekle_hangi_kullanici.getValue() == null)) {
            kampanyalar_ekle_uyari_mesaj.setText("Lütfen Gerekli Yerleri Doldurnuz.");
        } else {
            String title = kampanyalar_ekle_title.getText();
            String tarih = kampanyalar_ekle_tarih.getText();
            String kategori = kampanyalar_ekle_kategori.getText();
            String kampanya = kampanyalar_ekle_kampanya.getText();
            String hangi = kampanyalar_ekle_hangi_kullanici.getValue();

            kampanyalar k = new kampanyalar(Integer.parseInt(hangi), title, kampanya, tarih, kategori);

            kampanyalarDAO kdao = new kampanyalarDAO();
            int sonuc = kdao.kampanyalar_dao_ekle(k);

            if (sonuc == 1) {
                kampanyalar_ekle_uyari_mesaj.setText("İşlem Başarılı Bir Şekilde Gerçekleşti");
            } else {
                kampanyalar_ekle_uyari_mesaj.setText("Bir Hata Meydana Geldi. (Hata Kodu: -1)");
            }
        }
    }

    @FXML
    private void kampanyalar_degistir_kampanyayi_getir(ActionEvent event) {
        if (kampanyalar_degistir_kampanyalari_getir.getValue() == null) {
            kampanyalar_degistir_uyari_mesaj_1.setText("Lütfen bir kampanya seçiniz.");
        } else {
            String secilen = kampanyalar_degistir_kampanyalari_getir.getValue();
            kampanyalar_degistir_pane_2.setVisible(true);
            kampanyalarDAO kampanyalar_islemleri = new kampanyalarDAO();
            String[][] arr = kampanyalar_islemleri.kampanyalar_combo_doldur();
            int kampanya_id = 0;
            for (int i = 0; i < arr.length; i++) {
                if (secilen.equals(arr[i][0])) {
                    kampanya_id = Integer.valueOf(arr[i][1]);
                }
            }
            int hangi = kampanyalar_islemleri.kampanyalar_hangi_kullanici_getir(kampanya_id);
            String Title = kampanyalar_islemleri.kampanyalar_title_getir(kampanya_id);
            String Kampanya = kampanyalar_islemleri.kampanyalar_kampanya_getir(kampanya_id);
            String Tarih = kampanyalar_islemleri.kampanyalar_tarih_getir(kampanya_id);
            String Kampanya_Kategorisi = kampanyalar_islemleri.kampanyalar_kampanya_kategorisi_getir(kampanya_id);

            kampanyalar_degistir_title.setText(Title);
            kampanyalar_degistir_kampanya.setText(Kampanya);
            kampanyalar_degistir_tarih.setText(Tarih);
            kampanyalar_degistir_kategori.setText(Kampanya_Kategorisi);

            hangi_aboneler_combo(kampanyalar_degistir_hangi_kullanici);
            kampanyalar_degistir_hangi_kullanici.setValue(String.valueOf(hangi));

            kampanyalar_degistir_kampanya_id.setText(String.valueOf(kampanya_id));
        }
    }

    @FXML
    private void kampanyalar_degistir_degistir(ActionEvent event) {
        if ((kampanyalar_degistir_title.getText().length() == 0) || (kampanyalar_degistir_kampanya.getText().length() == 0)
                || (kampanyalar_degistir_tarih.getText().length() == 0) || (kampanyalar_degistir_kategori.getText().length() == 0)
                || (kampanyalar_degistir_hangi_kullanici.getValue() == null)) {
            kampanyalar_degistir_uyari_mesaj_2.setText("Lütfen Gerekli Yerleri Degistiriniz.");
        } else {
            int hangi = Integer.parseInt(kampanyalar_degistir_hangi_kullanici.getValue());
            String Title = kampanyalar_degistir_title.getText();
            String Kampanya = kampanyalar_degistir_kampanya.getText();
            String Tarih = kampanyalar_degistir_tarih.getText();
            String Kampanya_Kategorisi = kampanyalar_degistir_kategori.getText();

            int kampanya_id = Integer.parseInt(kampanyalar_degistir_kampanya_id.getText());

            kampanyalar k = new kampanyalar(kampanya_id, hangi, Title, Kampanya, Tarih, Kampanya_Kategorisi);

            kampanyalarDAO kdao = new kampanyalarDAO();

            int sonuc = kdao.kampanyalar_degistir(k);

            if (sonuc == 1) {
                kampanyalar_degistir_uyari_mesaj_2.setText("İşlem Başarılı Bir Şekilde Gerçekleti.");
            } else {
                kampanyalar_degistir_uyari_mesaj_2.setText("Bir Hata Meydaha Geldi (Hata Kodu = -2)");
            }
        }
    }

    @FXML
    private void kampanyalar_sil_emin_misin_vazgec(ActionEvent event) {
        kampanyalar_sil_emin_misin_pane.setVisible(false);
    }

    @FXML
    private void kampanyalar_sil_emin_misin_sil(ActionEvent event) {
        int kampanya_id = Integer.valueOf(kampanyalar_silmekten_emin_kampanya_id.getText());
        kampanyalarDAO kdao = new kampanyalarDAO();
        int sonuc = kdao.kampanyalar_sil(kampanya_id);

        kampanyalar_sil_emin_misin_pane.setVisible(false);

        if (sonuc == 1) {
            kampanyalar_table_uyari_mesaj.setText("İşlem Başarılı Bir Şekilde Gerçekleştirildi");
        } else {
            kampanyalar_table_uyari_mesaj.setText("Bir Hata Meydana geldi (Hata Kodu = -3)");
        }

    }

    @FXML
    private void kampanyalar_geri(MouseEvent event) {
        geri_don_admin();
    }

    @FXML
    private void kampanyalar_ekle_geri(MouseEvent event) {
        kampanyalar_grid.setVisible(true);
        kampanyalar_ekle_pane.setVisible(false);

        kampanyalar_geri_tusu.setVisible(true);
        kampanyalar_ekle_geri_tusu.setVisible(false);

        kampanyalar_table_butonlu();
    }

    @FXML
    private void kampanyalar_degistir_geri(MouseEvent event) {
        kampanyalar_grid.setVisible(true);
        kampanyalar_degistir_pane.setVisible(false);

        kampanyalar_degistir_geri_tusu.setVisible(false);
        kampanyalar_geri_tusu.setVisible(true);

        kampanyalar_table_butonlu();
    }

    private void kampanyalar_table_butonlu() {
        kampanyalarDAO kdao = new kampanyalarDAO();

        ObservableList<kampanyalar> data = FXCollections.observableArrayList();

        data = kdao.kampanyalar_select(data, kampanyalar_sil_emin_misin_pane, kampanyalar_silmekten_emin_kampanya_id);

        kampanyalar_hangi_kullanıcı.setCellValueFactory(new PropertyValueFactory("hangi_kullanici_turu"));
        kampanyalar_title.setCellValueFactory(new PropertyValueFactory("Title"));
        kampanyalar_kampanya.setCellValueFactory(new PropertyValueFactory("Kampanya"));
        kampanyalar_tarih.setCellValueFactory(new PropertyValueFactory("Tarih"));
        kampanyalar_kampanya_kategorisi.setCellValueFactory(new PropertyValueFactory("Kampanya_Kategorisi"));
        kampanyalar_sil.setCellValueFactory(new PropertyValueFactory("sil"));

        FilteredList<kampanyalar> filteredData = new FilteredList<>(data, b -> true);

        filterField_kampanyalar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(kam -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(kam.getHangi_kullanici_turu()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (kam.getTitle().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (kam.getDuyuru().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (kam.getTarih().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (kam.getKategori().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }

            });
        });

        SortedList<kampanyalar> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(table_kampanyalar.comparatorProperty());

        table_kampanyalar.setItems(sortedData);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /*
    Haberler
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     */
    
    private void haberler_combo_doldur(ComboBox<String> combo, Label uyari_mesaj) {
        haberlerDAO haber_islemleri = new haberlerDAO();
        String[][] arr = haber_islemleri.haberler_combo_doldur();
        combo.getItems().clear();
        if (arr.length == 0) {
            uyari_mesaj.setText("Kayıtlı Haber Bulunamadı. Lütfen Önce Bir Haber Ekleyiniz.");

        } else {
            for (int i = 0; i < arr.length; i++) {
                String k = arr[i][0];
                combo.getItems().addAll(k);
            }
            combo.setPromptText("İstediğiniz Haberi seçiniz.");
        }
    }

    @FXML
    private void haberler_ekle_giris(ActionEvent event) {
        haberler_grid.setVisible(false);
        haberler_ekle_pane.setVisible(true);
        haberler_degistir_pane.setVisible(false);

        hangi_aboneler_combo(haberler_ekle_hangi_kullanici);

        haberler_ekle_geri_tusu.setVisible(true);
        haberler_geri_tusu.setVisible(false);
        haberler_degistir_geri_tusu.setVisible(false);
    }

    @FXML
    private void haberler_degistir_giris(ActionEvent event) {
        haberler_grid.setVisible(false);
        haberler_ekle_pane.setVisible(false);
        haberler_degistir_pane.setVisible(true);

        hangi_aboneler_combo(haberler_degistir_hangi_kullanici);

        haberler_combo_doldur(haberler_degistir_haberleri_getir, haberler_degistir_uyari_mesaj_1);

        haberler_geri_tusu.setVisible(false);
        haberler_ekle_geri_tusu.setVisible(false);
        haberler_degistir_geri_tusu.setVisible(true);
    }

    @FXML
    private void haberler_ekle_sifirla(ActionEvent event) {
        haberler_ekle_title.setText("");
        haberler_ekle_tarih.setText("");
        haberler_ekle_kategori.setText("");
        haberler_ekle_haber.setText("");
        hangi_aboneler_combo(haberler_ekle_hangi_kullanici);

    }

    @FXML
    private void haberler_ekle_ekle(ActionEvent event) {
        if ((haberler_ekle_title.getText().length() == 0) || (haberler_ekle_tarih.getText().length() == 0) || (haberler_ekle_kategori.getText().length() == 0)
                || (haberler_ekle_haber.getText().length() == 0) || (haberler_ekle_hangi_kullanici.getValue() == null)) {
            haberler_ekle_uyari_mesaj.setText("Lütfen Gerekli Yerleri Doldurnuz.");
        } else {
            String title = haberler_ekle_title.getText();
            String tarih = haberler_ekle_tarih.getText();
            String kategori = haberler_ekle_kategori.getText();
            String haber = haberler_ekle_haber.getText();
            String hangi = haberler_ekle_hangi_kullanici.getValue();

            haberler h = new haberler(Integer.parseInt(hangi), title, haber, tarih, kategori);

            haberlerDAO hdao = new haberlerDAO();
            int sonuc = hdao.haberler_dao_ekle(h);

            if (sonuc == 1) {
                haberler_ekle_uyari_mesaj.setText("İşlem Başarılı Bir Şekilde Gerçekleşti");
            } else {
                haberler_ekle_uyari_mesaj.setText("Bir Hata Meydana Geldi (Hata Kodu = -4)");
            }
        }
    }

    @FXML
    private void haberler_degistir_haberi_getir(ActionEvent event) {
        if (haberler_degistir_haberleri_getir.getValue() == null) {
            haberler_degistir_uyari_mesaj_1.setText("Lütfen bir Haber seçiniz.");
        } else {
            String secilen = haberler_degistir_haberleri_getir.getValue();
            haberler_degistir_pane_2.setVisible(true);
            haberlerDAO haber_islemleri = new haberlerDAO();
            String[][] arr = haber_islemleri.haberler_combo_doldur();
            int haber_id = 0;
            for (int i = 0; i < arr.length; i++) {
                if (secilen.equals(arr[i][0])) {
                    haber_id = Integer.valueOf(arr[i][1]);
                }
            }
            int hangi = haber_islemleri.haberler_hangi_kullanici_getir(haber_id);
            String Title = haber_islemleri.haberler_title_getir(haber_id);
            String Haber = haber_islemleri.haberler_haber_getir(haber_id);
            String Tarih = haber_islemleri.haberler_tarih_getir(haber_id);
            String Haber_Kategorisi = haber_islemleri.haberler_haber_kategorisi_getir(haber_id);

            haberler_degistir_title.setText(Title);
            haberler_degistir_haber.setText(Haber);
            haberler_degistir_tarih.setText(Tarih);
            haberler_degistir_kategori.setText(Haber_Kategorisi);

            hangi_aboneler_combo(haberler_degistir_hangi_kullanici);
            haberler_degistir_hangi_kullanici.setValue(String.valueOf(hangi));

            haberler_degistir_haber_id.setText(String.valueOf(haber_id));
        }
    }

    @FXML
    private void haberler_degistir_degistir(ActionEvent event) {
        if ((haberler_degistir_title.getText().length() == 0) || (haberler_degistir_haber.getText().length() == 0)
                || (haberler_degistir_tarih.getText().length() == 0) || (haberler_degistir_kategori.getText().length() == 0)
                || (haberler_degistir_hangi_kullanici.getValue() == null)) {
            haberler_degistir_uyari_mesaj_2.setText("Lütfen Gerekli Yerleri Degistiriniz.");
        } else {
            int hangi = Integer.parseInt(haberler_degistir_hangi_kullanici.getValue());
            String Title = haberler_degistir_title.getText();
            String Haber = haberler_degistir_haber.getText();
            String Tarih = haberler_degistir_tarih.getText();
            String Haber_Kategorisi = haberler_degistir_kategori.getText();

            int haber_id = Integer.parseInt(haberler_degistir_haber_id.getText());

            haberler h = new haberler(haber_id, hangi, Title, Haber, Tarih, Haber_Kategorisi);

            haberlerDAO hdao = new haberlerDAO();

            int sonuc = hdao.haberler_degistir(h);

            if (sonuc == 1) {
                haberler_degistir_uyari_mesaj_2.setText("İşlem Başarılı Bir Şekilde Gerçekleti.");
            } else {
                haberler_degistir_uyari_mesaj_2.setText("Bir Hata Meydaha Geldi (Hata Kodu = -5)");
            }
        }
    }

    @FXML
    private void haberler_sil_emin_misin_vazgec(ActionEvent event) {
        haberler_sil_emin_misin_pane.setVisible(false);
    }

    @FXML
    private void haberler_sil_emin_misin_sil(ActionEvent event) {
        int haber_id = Integer.valueOf(haberler_silmekten_emin_haber_id.getText());
        haberlerDAO hdao = new haberlerDAO();
        int sonuc = hdao.haberler_sil(haber_id);

        haberler_sil_emin_misin_pane.setVisible(false);

        if (sonuc == 1) {
            haberler_table_uyari_mesaj.setText("İşlem Başarılı Bir Şekilde Gerçekleştirildi");
        } else {
            haberler_table_uyari_mesaj.setText("Bir Hata Meydana geldi (Hata Kodu = -6)");
        }

    }

    @FXML
    private void haberler_geri(MouseEvent event) {
        geri_don_admin();
    }

    @FXML
    private void haberler_ekle_geri(MouseEvent event) {
        haberler_grid.setVisible(true);
        haberler_ekle_pane.setVisible(false);

        haberler_geri_tusu.setVisible(true);
        haberler_ekle_geri_tusu.setVisible(false);

        haberler_table_butonlu();
    }

    @FXML
    private void haberler_degistir_geri(MouseEvent event) {
        haberler_grid.setVisible(true);
        haberler_degistir_pane.setVisible(false);

        haberler_degistir_geri_tusu.setVisible(false);
        haberler_geri_tusu.setVisible(true);

        haberler_table_butonlu();
    }

    private void haberler_table_butonlu() {
        haberlerDAO hdao = new haberlerDAO();

        ObservableList<haberler> data = FXCollections.observableArrayList();

        data = hdao.haberler_select(data, haberler_sil_emin_misin_pane, haberler_silmekten_emin_haber_id);

        haberler_hangi_kullanıcı.setCellValueFactory(new PropertyValueFactory("hangi_kullanici_turu"));
        haberler_title.setCellValueFactory(new PropertyValueFactory("Title"));
        haberler_haber.setCellValueFactory(new PropertyValueFactory("Haber"));
        haberler_tarih.setCellValueFactory(new PropertyValueFactory("Tarih"));
        haberler_haber_kategorisi.setCellValueFactory(new PropertyValueFactory("Haber_Kategorisi"));
        haberler_sil.setCellValueFactory(new PropertyValueFactory("sil"));

        FilteredList<haberler> filteredData = new FilteredList<>(data, b -> true);

        filterField_haberler.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(hab -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(hab.getHangi_kullanici_turu()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (hab.getTitle().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (hab.getDuyuru().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (hab.getTarih().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (hab.getKategori().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }

            });
        });

        SortedList<haberler> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(table_haberler.comparatorProperty());

        table_haberler.setItems(sortedData);
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /*
    Sinema Salonları
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     */

    //Sinema salonu işlemleri burada yapılıyor.
    /*
    
    Biizm belirleidğimize göre toplam 4 tane sinema salonu tipi var. Bu tipler koltuk sayısına göre belirleniyor.
    Tiplerimizin koltuk sayısı şu şekilde: 129,177,265,294. Bunların her biri scene builder'da bir pane tutuyor.
    ve eklenen sinema salonunun koltuk sayısına göre de o pane'leri açıp kapatıyoruz.
    
     */
    //ilk başta sinema salonu için gerekli olan değişkenler tanımlanıyor.
   

    //combovox'ın içine koltuk sayılarını yazmak için oluşturulan metot. Kısacası parametre olarak hangi combobox gelirse onun içine bizim daha önceden belirlediğimiz koltuk sayılarını yazıyor.
    private void koltuk_sayisi_combo(ComboBox<String> combo, String promp) {
        //ilk olarak içi daha önceden doldurulmuş olabilir diye temizleme işlemi yapıyoruz.
        combo.getItems().clear();
        //Daha sonra ekleme işlemi yapıyoruz ve her koltuk sayısını ekliyoruz.
        combo.getItems().addAll("129");
        combo.getItems().addAll("177");
        combo.getItems().addAll("265");
        combo.getItems().addAll("294");
        //daha sonra parametre olarak gelen değeri de bu combobox'ın başlangıç değeri olarak atıyoruz.
        combo.setPromptText(promp);
    }

    //bu metodun amacı da parametre olarak gele ncombo box'ın içine sinema salonlarını yazdırmak. ve yine parametre olarak gelen Stirng'i de paramtetre olarak gelen combobox'ın açılış yaızıs yapmak
    //sinema_salonlari'nin ana pane'i içinde bulunan Sinema salonlarını gör butonunun metodu. ve yaptığı tek şey sinema_salonlarini_goruntule_pane'i aktif hale getiirp combobox'ın içine sinema salonlarını teker teker yazdırmak.
    @FXML
    private void sinema_salonlarini_gor(ActionEvent event) {
        sinema_salonlari_home_pane.setVisible(false);
        sinema_salonlari_goruntule_pane.setVisible(true);

        sinema_salonlari_goruntule_combo(sinema_salonlari_goruntule, sinema_salonu_goruntule_uyari_mesaj);
    }

    //sinema_salonlarini_goruntule_pane'in içinde bulunan Combobox'ın görevini gören Sinema Slaonunu Görüntüle butonunun metodu. Tek yaptığı şey combobox da seçilen sinema salonunun koltuk düzenini görüntülüyor.
    @FXML
    private void sinema_salonunu_goruntule(ActionEvent event) {
        //ilk başta combobox'ın içindeki değeri String'e atıyoruz.
        String salonlar = sinema_salonlari_goruntule.getValue();
        //ve String'in boş olup olmadığını kontrol ediyoruz. Eğer String boş ise de kullanıcıya uyar ımesajı yazıyoruz ve metodu sonlandırıyoruz.
        if (salonlar == null) {
            sinema_salonu_goruntule_uyari_mesaj.setText("Lütfen İstediğiniz Salonu Seçiniz.");
        } else {
            //kullanıcı bir değeri seçmiş ise devam ediyoruz.
            //ilk başta dosyadan var olan sinema salonlarının bilgilerini alıyoruz
            sinema_salonlariDAO sdao = new sinema_salonlariDAO();

            String[] a = salonlar.split(" | ");
            int salon_id = Integer.valueOf(a[0]);
            int koltuk_sayisi = sdao.koltuk_sayisi_getir(salon_id);
            //daha sonra ise koltuk sayısına göre gerekli pane'i aktif hale getiriyoruz.
            switch (koltuk_sayisi) {
                case 129:
                    //Eğer koltuk sayııs 129 ise ilk pane'i açıp diğerlerini kapatıyoruz.
                    salon_bir_pane.setVisible(true);

                    salon_iki_pane.setVisible(false);
                    salon_uc_pane.setVisible(false);
                    salon_dort_pane.setVisible(false);
                    break;
                case 177:
                    //Eğer koltuk sayııs 177 ise ikinci bane'i açıp diğerlerini kapatıyoruz.
                    salon_iki_pane.setVisible(true);

                    salon_bir_pane.setVisible(false);
                    salon_uc_pane.setVisible(false);
                    salon_dort_pane.setVisible(false);
                    break;
                case 265:
                    //Eğer koltuk sayısı 265 ise üçüncü pane'i açıp diğerlerini kapatıyoruz.
                    salon_uc_pane.setVisible(true);

                    salon_bir_pane.setVisible(false);
                    salon_iki_pane.setVisible(false);
                    salon_dort_pane.setVisible(false);
                    break;
                case 294:
                    //Eğer koltuk sayısı 294 ise dördüncü pane'i açıp diğerlerini kapatıyoruz.
                    salon_dort_pane.setVisible(true);

                    salon_bir_pane.setVisible(false);
                    salon_iki_pane.setVisible(false);
                    salon_uc_pane.setVisible(false);
                    break;
                default:
                    break;
            }

        }
    }

    //sinema_Salonlari_goruntule_pane'in içinde bulunan geri iconunun MouseEvent'ı dır. Bu sinema salonlarındaki home pane i açar diğerlerini kapatır.
    @FXML
    private void sinema_salonunu_goruntule_geri(MouseEvent event) {
        sinema_salonlari_home_pane.setVisible(true);

        sinema_salonlari_goruntule_pane.setVisible(false);
        salon_bir_pane.setVisible(false);
        salon_iki_pane.setVisible(false);
        salon_uc_pane.setVisible(false);
        salon_dort_pane.setVisible(false);

        yesil_olanDAO ydao = new yesil_olanDAO();
        ydao.yesil_olan_dao_toplu_sil();
    }

    //sinema_Salonu_home_pane'in içinde bulunan yeni sinema salonu ekle butonunun metodudur. Yaptığı tek şey sinema_salonları için oluşturulmuş ekle pane'ini açıp home pane'ini kapatıyor. ve koltuk sayısını combobox'ın içine yazdırıyor.
    @FXML
    private void yeni_sinema_salonu_ekle(ActionEvent event) {
        sinema_salonlari_home_pane.setVisible(false);
        sinema_salonu_ekle_pane.setVisible(true);

        koltuk_sayisi_combo(sinema_salonu_ekle_koltuk_sayisi, "İstediğiniz Koltuk Sayısı...");
    }

    @FXML
    private void salonu_ekle(ActionEvent event) {
        String name = sinema_salonu_ekle_name.getText();
        String koltuk_sayisi = sinema_salonu_ekle_koltuk_sayisi.getValue();
        if ((koltuk_sayisi == null) || (name.length() == 0)) {
            sinema_salonu_ekle_uyari_mesaj.setText("Lütfen Gerekli Yerleri Doldurunuz.");
        } else {
            sinema_salonlariDAO sdao = new sinema_salonlariDAO();
            sinema_salonlari s = new sinema_salonlari(name, Integer.parseInt(koltuk_sayisi));
            int sonuc = sdao.sinema_salonlari_dao_ekle(s);

            switch (sonuc) {
                case 1:
                    sinema_salonu_ekle_uyari_mesaj.setText("İşlem Başarılı Bir Şekilde Gerçekleştirildi.");
                    break;
                case -1:
                    sinema_salonu_ekle_uyari_mesaj.setText("Aynı ada sahip farklı bir salon bulunmakta. Lütfen fakrlı bir ad giriniz.");
                    break;
                default:
                    sinema_salonu_ekle_uyari_mesaj.setText("Bir hata meydana geldi Lütfen Daha Sonra Tekrar Deneyiniz");
                    break;
            }
        }
    }

    @FXML
    private void yeni_sinema_salonu_ekle_geri(MouseEvent event) {
        sinema_salonlari_home_pane.setVisible(true);
        sinema_salonu_ekle_pane.setVisible(false);

    }

    @FXML
    private void sinema_salonu_duzenle_sil(ActionEvent event) {
        sinema_salonlari_home_pane.setVisible(false);
        sinema_salonu_duzenle_pane.setVisible(true);
        sinema_salonu_duzenle_pane_1.setVisible(true);
        sinema_salonu_duzenle_pane_2.setVisible(false);

        sinema_salonlari_goruntule_combo(sinema_salonlarini_duzenle_combo, sinema_salonu_guncelle_uyari_mesaj);

    }

    //sinema_salonu_duzenle_sil_pane'indeki sinema_Salonunu getir botununun metodudur.
    @FXML
    private void sinema_salonunu_getir(ActionEvent event) {
        String secilen = sinema_salonlarini_duzenle_combo.getValue();
        if (secilen == null) {
            sinema_salonu_guncelle_uyari_mesaj.setText("Lütfen Bir Değer Seçiniz");
        } else {
            String[] a = secilen.split(" | ");
            sinema_salonlariDAO sdao = new sinema_salonlariDAO();

            sinema_salonunu_guncelle_id.setText(a[0]);
            int salon_id = Integer.parseInt(a[0]);
            int koltuk_sayisi = sdao.koltuk_sayisi_getir(salon_id);
            koltuk_sayisi_combo(sinema_salonlarini_duzenle_koltuk_sayisi, "");
            sinema_salonlarini_duzenle_koltuk_sayisi.setValue(String.valueOf(koltuk_sayisi));
            sinema_salonu_guncelle_name.setText(sdao.salon_adi_getir(salon_id));

            sinema_salonu_duzenle_pane_2.setVisible(true);
        }
    }

    //sinema_Salonu_duzenle_sil pane'deki Sinema_Salonunu sil botununun metodu.
    @FXML
    private void sinema_salonunu_sil(ActionEvent event) {

        if (sinema_salonunu_guncelle_id.getText().length() == 0) {
            sinema_salonu_guncelle_uyari_mesaj.setText("Lütfen bir değer seçiniz.");
        } else {
            int salon_id = Integer.valueOf(sinema_salonunu_guncelle_id.getText());
            sinema_salonlariDAO sdao = new sinema_salonlariDAO();
            int sonuc = sdao.sinema_salonlari_dao_sil(salon_id);

            if (sonuc == 1) {

                sinema_salonlari_goruntule_combo(sinema_salonlarini_duzenle_combo, sinema_salonu_guncelle_uyari_mesaj);
                koltuk_sayisi_combo(sinema_salonlarini_duzenle_koltuk_sayisi, "");
                sinema_salonu_guncelle_name.setText("");
                sinema_salonunu_guncelle_id.setText("");

                sinema_salonu_guncelle_uyari_mesaj.setText("İşlem Başarılı Bir şekilde Yapıldı");

            } else {
                sinema_salonu_guncelle_uyari_mesaj.setText("Bir hata meydana geldi(appController.sinema_salonunu_sil(ActionEvent event))");
            }
        }

    }

    //Sinema_Salonu_guncelle_sil pane'in içinde bulunan Sinema Salonunu Güncelle butonunun metodu. Tek yaptığı şey kullanıcnın veridği verilere göre salon bilgilerini güncelleyip dosyaya yazmak.
    @FXML
    private void sinema_salonunu_guncelle(ActionEvent event) {
        if ((sinema_salonu_guncelle_name.getText().length() == 0) || (sinema_salonlarini_duzenle_koltuk_sayisi.getValue() == null)) {
            sinema_salonu_guncelle_uyari_mesaj.setText("Lütfen Gerekli Yerleri Doldurunuz.");
        } else {
            String name = sinema_salonu_guncelle_name.getText();

            int koltuk_sayisi = Integer.valueOf(sinema_salonlarini_duzenle_koltuk_sayisi.getValue());
            sinema_salonlariDAO sdao = new sinema_salonlariDAO();

            sinema_salonlari s = new sinema_salonlari(Integer.valueOf(sinema_salonunu_guncelle_id.getText()), name, koltuk_sayisi);
            int control = sdao.sinema_salonlari_degistir(s);

            switch (control) {
                case 1:
                    sinema_salonlari_goruntule_combo(sinema_salonlarini_duzenle_combo, sinema_salonu_guncelle_uyari_mesaj);
                    sinema_salonu_guncelle_uyari_mesaj.setText("İşlem Başarılı bir şekilde gerçekleştirildi");
                    break;
                case -1:
                    sinema_salonu_guncelle_uyari_mesaj.setText("Aynı ada sahip başka bir salon bulunmakta. Lütfen farklı bir ad giriniz.");
                    break;
                default:
                    sinema_salonu_guncelle_uyari_mesaj.setText("Bir hata meydana geldi. Lütfen daha sonra tekrar deneyiniz(Hata Kodu: -7)");
                    break;
            }

        }
    }

    @FXML
    private void sinema_salonu_duzenle_sil_geri(MouseEvent event) {
        sinema_salonu_duzenle_pane.setVisible(false);
        sinema_salonlari_home_pane.setVisible(true);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /*
    Film_Actor
     */
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /*
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     */
 /*
    Kullanıcı İşlemleri
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     */


    private void users_tablo() {
        usersDAO ud = new usersDAO();

        ObservableList<users> data = FXCollections.observableArrayList();

        data = ud.user_select(data, kullanici_islemleri_user_id, kullanici_islemleri_user_name, kullanici_islemleri_user_mail, kullanici_islemleri_gizli_pf, kullanici_islemleri_user_turu, kullanici_islemleri_abone_turu, kullanici_islemleri_gizli_pane, kullanici_islemleri_acik_pane, kullanici_islemleri_table_pane, kullanici_islemleri_yonet_pane, kullanici_islemleri_geri_tusu, kullanici_islemleri_yonet_geri_tusu, kullanici_islemleri_silmekten_emin_misin_pane);

        users_user_id.setCellValueFactory(new PropertyValueFactory("user_id"));
        users_user_name.setCellValueFactory(new PropertyValueFactory("user_name"));
        users_user_mail.setCellValueFactory(new PropertyValueFactory("user_mail"));
        users_user_password.setCellValueFactory(new PropertyValueFactory("user_password"));
        users_user_type.setCellValueFactory(new PropertyValueFactory("user_type"));
        users_abone_turu.setCellValueFactory(new PropertyValueFactory("abonelik_turu"));
        users_yonet.setCellValueFactory(new PropertyValueFactory("yonet"));

        FilteredList<users> filteredData = new FilteredList<>(data, b -> true);

        filterField_users.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(us -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(us.getUser_id()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (us.getUser_name().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (us.getUser_mail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (us.getUser_password().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(us.getUser_type()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(us.getAbonelik_turu()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }

            });
        });

        SortedList<users> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(table_users.comparatorProperty());

        table_users.setItems(sortedData);
    }

    @FXML
    private void kullanici_islemleri_yonet_geri(MouseEvent event) {
        kullanici_islemleri_ortak_giris();
    }

    @FXML
    private void kullanici_islemleri_geri(MouseEvent event) {
        geri_don_admin();
    }

    private void kullanici_islemleri_ortak_giris() {
        pnl_kullanici_islemleri.setVisible(true);
        pnl_sinema_salonlari.setVisible(false);
        pnl_vizyondaki_filmler.setVisible(false);
        pnl_seans.setVisible(false);
        pnl_eski_filmler.setVisible(false);
        pnl_kampanyalar.setVisible(false);
        pnl_haberler.setVisible(false);
        pnl_yonetmenler.setVisible(false);
        pnl_aktorler.setVisible(false);
        home_pane.setVisible(false);
        pnl_settings.setVisible(false);

        kullanici_islemleri_table_pane.setVisible(true);
        kullanici_islemleri_yonet_pane.setVisible(false);

        kullanici_islemleri_yonet_geri_tusu.setVisible(false);
        kullanici_islemleri_geri_tusu.setVisible(true);

        home.setVisible(true);

        users_tablo();
    }

    @FXML
    private void kullanici_islemleri_sifre_goster(MouseEvent event) {
        a2 = true;
        kullanici_islemleri_acik_pf.setText(kullanici_islemleri_gizli_pf.getText());

        kullanici_islemleri_acik_pane.setVisible(true);
        kullanici_islemleri_gizli_pane.setVisible(false);
    }

    @FXML
    private void kullanici_islemleri_sifre_gizle(MouseEvent event) {
        a2 = false;
        kullanici_islemleri_gizli_pf.setText(kullanici_islemleri_acik_pf.getText());

        kullanici_islemleri_acik_pane.setVisible(false);
        kullanici_islemleri_gizli_pane.setVisible(true);
    }

    @FXML
    private void kullanici_islemleri_sil_giris(ActionEvent event) {
        kullanici_islemleri_silmekten_emin_misin_pane.setVisible(true);
    }

    @FXML
    private void kullanici_islemleri_sil_sil(ActionEvent event) {
        int user_id = Integer.parseInt(kullanici_islemleri_user_id.getText());

        usersDAO udao = new usersDAO();

        int sonuc = udao.user_dao_sil(user_id);

        if (sonuc == 1) {
            kullanici_islemleri_yonet_uyari_pane.setText("İşlem başarılı bir şekilde gerçekleştirildi.");
        } else {
            kullanici_islemleri_yonet_uyari_pane.setText("Bir hata meydana geldi hata kodu: -30");
        }

        kullanici_islemleri_silmekten_emin_misin_pane.setVisible(false);
    }

    @FXML
    private void kullanici_islemleri_sil_vazgec(ActionEvent event) {
        kullanici_islemleri_silmekten_emin_misin_pane.setVisible(false);
    }

    @FXML
    private void kullanici_islemleri_guncelle(ActionEvent event) {
        if ((kullanici_islemleri_user_name.getText().length() == 0) || (kullanici_islemleri_user_mail.getText().length() == 0) || (kullanici_islemleri_user_turu.getValue() == null) || (kullanici_islemleri_abone_turu.getValue() == null)) {
            kullanici_islemleri_yonet_uyari_pane.setText("Lütfen Gerekli Yerleri Doldurunuz.");
        } else {
            String user_password = null;
            if (a2 == false) {
                if (kullanici_islemleri_gizli_pf.getText().length() == 0) {
                    kullanici_islemleri_yonet_uyari_pane.setText("Lütfen Gerekli Yerleri Doldurunuz.");
                } else {
                    user_password = kullanici_islemleri_gizli_pf.getText();
                }
            } else {
                if (kullanici_islemleri_acik_pf.getText().length() == 0) {
                    kullanici_islemleri_yonet_uyari_pane.setText("Lütfen Gerekli Yerleri Doldurunuz.");
                } else {
                    user_password = kullanici_islemleri_acik_pf.getText();
                }
            }
            if (user_password != null) {
                int user_id = Integer.parseInt(kullanici_islemleri_user_id.getText());
                String user_name = kullanici_islemleri_user_name.getText();
                String user_mail = kullanici_islemleri_user_mail.getText();
                int abone_turu = Integer.parseInt(kullanici_islemleri_abone_turu.getValue());
                int user_turu = -1;
                if (kullanici_islemleri_user_turu.getValue().equals("Normal")) {
                    user_turu = 0;
                } else if (kullanici_islemleri_user_turu.getValue().equals("Admin")) {
                    user_turu = 1;
                }
                if (user_turu == -1) {
                    kullanici_islemleri_yonet_uyari_pane.setText("Bir hata meydana geldi. Hata kodu: -31.");
                } else {
                    users u = new users(user_id, user_name, user_mail, user_password, user_turu);
                    usersDAO udao = new usersDAO();

                    int sonuc = udao.user_guncelle_aboneli(u, abone_turu);

                    if (sonuc == 1) {
                        kullanici_islemleri_yonet_uyari_pane.setText("İşlem Başarılı Bir Şekilde Gerçekleştirildi.");
                    } else {
                        kullanici_islemleri_yonet_uyari_pane.setText("Bir hata meydana geldi. Hata kodu: -32.");
                    }
                }
            }
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /*
    Ortak Alan
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     */
    @FXML
    private void btn10(ActionEvent event) {

        if (btn1.getText().equals("Filmler")) {
            btn1.setText("Vizyondaki Filmler");
            btn2.setText("Eski Filmler");

            btn3.setVisible(false);
            btn4.setVisible(false);
            btn5.setVisible(false);

            home.setVisible(true);
        } else if (btn1.getText().equals("Vizyondaki Filmler")) {

            pnl_vizyondaki_filmler.setVisible(true);
            pnl_eski_filmler.setVisible(false);
            pnl_sinema_salonlari.setVisible(false);
            pnl_seans.setVisible(false);
            home_pane.setVisible(false);
            pnl_settings.setVisible(false);
            pnl_kullanici_islemleri.setVisible(false);

            vizyondaki_filmler_geri_tusu.setVisible(true);
            vizyondaki_filmler_ekle_geri_tusu.setVisible(false);
            vizyondaki_filmler_degistir_geri_tusu.setVisible(false);
            vizyondaki_filmler_gosterim_geri_tusu.setVisible(false);

            vizyondaki_filmler_resimli_gosterim.setVisible(true);
            vizyondaki_filmler_grid.setVisible(false);
            vizyondaki_filmler_ekle_pane.setVisible(false);
            vizyondaki_filmler_degistir_pane.setVisible(false);

            vizyondaki_filmler_gosterim();

        } else if (btn1.getText().equals("Kampanyalar")) {

            pnl_kampanyalar.setVisible(true);
            pnl_haberler.setVisible(false);
            pnl_sinema_salonlari.setVisible(false);
            home_pane.setVisible(false);
            pnl_settings.setVisible(false);
            pnl_kullanici_islemleri.setVisible(false);

            kampanyalar_geri_tusu.setVisible(true);
            kampanyalar_ekle_geri_tusu.setVisible(false);
            kampanyalar_degistir_geri_tusu.setVisible(false);

            kampanyalar_grid.setVisible(true);
            kampanyalar_ekle_pane.setVisible(false);
            kampanyalar_degistir_pane.setVisible(false);

            kampanyalar_table_butonlu();

        } else if (btn1.getText().equals("Yönetmenler")) {

            pnl_yonetmenler.setVisible(true);
            pnl_aktorler.setVisible(false);
            pnl_sinema_salonlari.setVisible(false);
            home_pane.setVisible(false);
            pnl_settings.setVisible(false);
            pnl_kullanici_islemleri.setVisible(false);

            yonetmenler_geri_tusu.setVisible(true);
            yonetmenler_ekle_geri_tusu.setVisible(false);
            yonetmenler_degistir_geri_tusu.setVisible(false);

            yonetmenler_grid.setVisible(true);
            yonetmenler_ekle_pane.setVisible(false);
            yonetmenler_degistir_pane.setVisible(false);

            yonetmenler_table();
        }
    }

    @FXML
    private void btn20(ActionEvent event) {
        if (btn2.getText().equals("Duyurular")) {

            btn1.setText("Kampanyalar");
            btn2.setText("Haberler");

            btn3.setVisible(false);
            btn4.setVisible(false);
            btn5.setVisible(false);

            home.setVisible(true);
        } else if (btn2.getText().equals("Eski Filmler")) {

            pnl_eski_filmler.setVisible(true);
            pnl_vizyondaki_filmler.setVisible(false);
            pnl_sinema_salonlari.setVisible(false);
            pnl_seans.setVisible(false);
            home_pane.setVisible(false);
            pnl_settings.setVisible(false);
            pnl_kullanici_islemleri.setVisible(false);

            eski_filmler_geri_tusu.setVisible(true);
            eski_filmler_ekle_geri_tusu.setVisible(false);
            eski_filmler_degistir_geri_tusu.setVisible(false);

            eski_filmler_grid.setVisible(true);
            eski_filmler_ekle_pane.setVisible(false);
            eski_filmler_degistir_pane.setVisible(false);

            eski_filmler_table_aboneli();

        } else if (btn2.getText().equals("Haberler")) {

            pnl_haberler.setVisible(true);
            pnl_kampanyalar.setVisible(false);
            pnl_sinema_salonlari.setVisible(false);
            home_pane.setVisible(false);
            pnl_settings.setVisible(false);
            pnl_kullanici_islemleri.setVisible(false);

            haberler_geri_tusu.setVisible(true);
            haberler_ekle_geri_tusu.setVisible(false);
            haberler_degistir_geri_tusu.setVisible(false);

            haberler_grid.setVisible(true);
            haberler_ekle_pane.setVisible(false);
            haberler_degistir_pane.setVisible(false);

            haberler_table_butonlu();

        } else if (btn2.getText().equals("Aktörler")) {
            pnl_aktorler.setVisible(true);
            pnl_yonetmenler.setVisible(false);
            pnl_sinema_salonlari.setVisible(false);
            home_pane.setVisible(false);
            pnl_settings.setVisible(false);
            pnl_kullanici_islemleri.setVisible(false);

            aktorler_geri_tusu.setVisible(true);
            aktorler_ekle_geri_tusu.setVisible(false);
            film_actor_geri_tusu.setVisible(false);
            film_actor_ekle_geri_tusu.setVisible(false);

            aktorler_grid.setVisible(true);
            aktorler_ekle_pane.setVisible(false);
            aktorler_sil_emin_misin.setVisible(false);
            film_actor_pane.setVisible(false);

            aktorler_table();
        }
    }

    @FXML
    private void btn30(ActionEvent event) {
        pnl_sinema_salonlari.setVisible(true);
        pnl_vizyondaki_filmler.setVisible(false);
        pnl_seans.setVisible(false);
        pnl_eski_filmler.setVisible(false);
        pnl_kampanyalar.setVisible(false);
        pnl_haberler.setVisible(false);
        pnl_yonetmenler.setVisible(false);
        pnl_aktorler.setVisible(false);
        home_pane.setVisible(false);
        pnl_settings.setVisible(false);
        pnl_kullanici_islemleri.setVisible(false);

        sinema_salonlari_home_pane.setVisible(true);

        salon_bir_pane.setVisible(false);
        salon_iki_pane.setVisible(false);
        salon_uc_pane.setVisible(false);
        salon_dort_pane.setVisible(false);

        sinema_salonlari_goruntule_pane.setVisible(false);
        sinema_salonu_ekle_pane.setVisible(false);
        sinema_salonu_duzenle_pane.setVisible(false);

        home.setVisible(true);
    }

    @FXML
    private void btn40(ActionEvent event) {
        btn1.setText("Yönetmenler");
        btn2.setText("Aktörler");

        btn3.setVisible(false);
        btn4.setVisible(false);
        btn5.setVisible(false);

        home.setVisible(true);
    }

    @FXML
    private void btn50(ActionEvent event) {
        kullanici_islemleri_ortak_giris();
    }

    @FXML
    private void home_geri_gel(MouseEvent event) {
        geri_don_admin();
    }

    @FXML
    private void settings_giris(MouseEvent event) {
        pnl_vizyondaki_filmler.setVisible(false);
        pnl_seans.setVisible(false);
        pnl_eski_filmler.setVisible(false);
        pnl_kampanyalar.setVisible(false);
        pnl_haberler.setVisible(false);
        pnl_sinema_salonlari.setVisible(false);
        pnl_yonetmenler.setVisible(false);
        pnl_aktorler.setVisible(false);
        home_pane.setVisible(false);
        pnl_kullanici_islemleri.setVisible(false);

        pnl_settings.setVisible(true);

        int user_id = Creator.getU().getUser_id();

        this.user_name.setText(Creator.getU().getUser_name());
        this.user_mail.setText(Creator.getU().getUser_mail());
        this.user_password.setText(Creator.getU().getUser_password());

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        home_page();
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
