package Code_Admin;

import entity.eski_filmler;
import entity.filmler;
import Pattern.Creator;
import Pattern.Mediator;
import Pattern.Table;
import entity.Center;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class Eski_Filmler extends Aktorler {

    public void eski_filmler_combo(ComboBox<String> combo, Label uyari_mesaj) {
        String[][] arr = Creator.eski_filmlerDao().select();
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
    public void eski_filmler_geri(MouseEvent event) {
        geri_don_admin();
    }

    @FXML
    public void eski_filmler_ekle_geri(MouseEvent event) {
        eski_filmler_grid.setVisible(true);
        eski_filmler_ekle_pane.setVisible(false);

        eski_filmler_geri_tusu.setVisible(true);
        eski_filmler_ekle_geri_tusu.setVisible(false);

        eski_filmler_table_aboneli();
    }

    @FXML
    public void eski_filmler_degistir_geri(MouseEvent event) {
        eski_filmler_grid.setVisible(true);
        eski_filmler_degistir_pane.setVisible(false);

        eski_filmler_degistir_geri_tusu.setVisible(false);
        eski_filmler_geri_tusu.setVisible(true);

        eski_filmler_table_aboneli();

    }

    @FXML
    public void eski_filmler_ekle_giris(ActionEvent event) {

        eski_filmler_grid.setVisible(false);
        eski_filmler_ekle_pane.setVisible(true);
        yonetmen_combo(eski_ekle_yonetmenler, eski_ekle_uyari_mesaj);
        hangi_aboneler_combo(eski_hangi_aboneler);
        eski_filmler_geri_tusu.setVisible(false);
        eski_filmler_ekle_geri_tusu.setVisible(true);
        eski_filmler_degistir_geri_tusu.setVisible(false);
        eski_filmler_degistir_pane.setVisible(false);
    }

    public void hangi_aboneler_combo(ComboBox<String> combo) {

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
    public void eski_filmler_ekle_sifirla(ActionEvent event) {
        eski_film_name.setText("");
        eski_film_type.setText("");
        eski_film_suresi.setText("");
        eski_aldigi_odul_sayisi.setText("");
        yonetmen_combo(eski_ekle_yonetmenler, eski_ekle_uyari_mesaj);
        hangi_aboneler_combo(eski_hangi_aboneler);
    }

    @FXML
    public void eski_filmler_ekle(ActionEvent event) {
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
                    String[][] arr = Creator.yonetmenlerDao().select();
                    int yonetmen_id = 0;
                    for (int i = 0; i < arr.length; i++) {
                        if (arr[i][0].equals(yonetmen)) {
                            yonetmen_id = Integer.valueOf(arr[i][1]);
                            i = arr.length + 1;
                        }
                    }
                    int hangi_abone = Integer.valueOf(hangi);

                    Mediator m = new Mediator();
                    int sonuc = m.eski_filmler_ve_filmler_ekle(film_name, film_suresi, film_type, yonetmen_id, hangi_abone, aldigi_odul);

                    if ((sonuc == 1)) {
                        eski_ekle_uyari_mesaj.setText("İşlem Başarılı Bir Şekilde Gerçekleşti.");
                    }

                }
            }
        }
    }

    @FXML
    public void eski_filmler_degistir_sil_giris(ActionEvent event) {

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
    public void eski_filmler_degistir_sil_filmi_getir(ActionEvent event) {

        if (eski_degistir_sil_filmler.getValue() == null) {
            eski_filmler_degistir_sil_uyari_mesaj_1.setText("Lütfen bir film seçiniz.");
        } else {
            String secilen = eski_degistir_sil_filmler.getValue();
            eski_filmler_degistir_pane_2.setVisible(true);
            String[][] arr = Creator.eski_filmlerDao().select();
            int eski_film_id = 0;
            for (int i = 0; i < arr.length; i++) {
                if (secilen.equals(arr[i][0])) {
                    eski_film_id = Integer.valueOf(arr[i][1]);
                }
            }
            String film_name = Creator.eski_filmlerDao().search_string(eski_film_id, 1);
            String film_type = Creator.eski_filmlerDao().search_string(eski_film_id, 2);
            int film_suresi = Creator.eski_filmlerDao().search_int(eski_film_id, 2);
            String yonetmen = Creator.eski_filmlerDao().search_int(eski_film_id, 1) + " " + Creator.eski_filmlerDao().search_string(eski_film_id, 3);
            int hangi_abone = Creator.eski_filmlerDao().search_int(eski_film_id, 3);
            int aldigi_odul = Creator.eski_filmlerDao().search_int(eski_film_id, 4);

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
    public void eski_filmler_degistir_sil_degistir(ActionEvent event) {
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
                    String yonetmen = eski_filmleri_degistir_sil_yonetmen.getValue();
                    String[][] arr = Creator.yonetmenlerDao().select();
                    int yonetmen_id = 0;
                    for (int i = 0; i < arr.length; i++) {
                        if (arr[i][0].equals(yonetmen)) {
                            yonetmen_id = Integer.valueOf(arr[i][1]);
                            break;
                        }
                    }
                    int eski_film_id = Integer.parseInt(eski_filmler_degistir_sil_eski_id.getText());
                    int film_id = Creator.eski_filmlerDao().search_int(eski_film_id, 5);
                    filmler f = new filmler(film_id, film_name, film_suresi, film_type, yonetmen_id);
                    eski_filmler e = new eski_filmler(eski_film_id, aldigi_odul, hangi_abone);

                    Mediator m = new Mediator();

                    int sonuc = m.eski_filmler_degistir(e, f);
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
    public void eski_filmler_degistir_sil_sil(ActionEvent event) {
        eski_filmler_degistir_sil_emin_misin.setVisible(true);
    }

    @FXML
    public void eski_filmler_degistir_sil_silmekten_emin_vazgec(ActionEvent event) {
        eski_filmler_degistir_sil_emin_misin.setVisible(false);
    }

    @FXML
    public void eski_filmler_degistir_sil_silmekten_emin_tamamen_sil(ActionEvent event) {
        int eski_film_id = Integer.parseInt(eski_filmler_degistir_sil_eski_id.getText());

        Mediator m = new Mediator();

        int sonuc = m.eski_filmler_tamamen_sil(eski_film_id);

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
    public void eski_filmler_degistir_sil_silmekten_emin_sadece_eski_filmden_sil(ActionEvent event) {
        int eski_film_id = Integer.parseInt(eski_filmler_degistir_sil_eski_id.getText());

        Mediator m = new Mediator();

        int sonuc = m.eski_filmler_sadece_eskiden_sil(eski_film_id);

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

    public void eski_filmler_table_aboneli() {

        ObservableList<eski_filmler> data = Table.data_Eski_Filmler(vizyondaki_filmler_detay_film_id, vizyondaki_filmler_detay_film_adi, vizyondaki_filmler_detay_film_turu, vizyondaki_filmler_detay_film_suresi, vizyondaki_filmler_detay_yonetmen, vizyondaki_filmler_detay_kalkis_tarihi, vizyondaki_filmler_detay_kullanici_puani, pnl_eski_filmler, pnl_vizyondaki_filmler, pnl_film_detay, film_detay_aldigi_odul_sayisi, film_detay_hangi_abone_turu, film_detay_kalksi_tarihi_oncesi, film_detay_aldigi_odul_sayisi_oncesi, film_detay_hangi_abone_turu_oncesi, film_detay_id, film_detay_id_oncesi);


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
}
