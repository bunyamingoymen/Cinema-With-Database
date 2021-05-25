package Code_Admin;

import entity.kampanyalar;
import Pattern.Creator;
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

public class Kampanyalar extends Haberler {

    public void kampanyalar_combo_doldur(ComboBox<String> combo, Label uyari_mesaj) {
        String[][] arr = Creator.kampanyalarDao().select();
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
    public void kampanyalar_ekle_giris(ActionEvent event) {
        kampanyalar_grid.setVisible(false);
        kampanyalar_ekle_pane.setVisible(true);
        kampanyalar_degistir_pane.setVisible(false);

        hangi_aboneler_combo(kampanyalar_ekle_hangi_kullanici);

        kampanyalar_ekle_geri_tusu.setVisible(true);
        kampanyalar_geri_tusu.setVisible(false);
        kampanyalar_degistir_geri_tusu.setVisible(false);
    }

    @FXML
    public void kampanyalar_degistir_giris(ActionEvent event) {
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
    public void kampanyalar_ekle_sifirla(ActionEvent event) {
        kampanyalar_ekle_title.setText("");
        kampanyalar_ekle_tarih.setText("");
        kampanyalar_ekle_kategori.setText("");
        kampanyalar_ekle_kampanya.setText("");
        hangi_aboneler_combo(kampanyalar_ekle_hangi_kullanici);

    }

    @FXML
    public void kampanyalar_ekle_ekle(ActionEvent event) {
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

            Center nw = new Center(k);

            int sonuc = Creator.kampanyalarDao().create(nw);

            if (sonuc == 1) {
                kampanyalar_ekle_uyari_mesaj.setText("İşlem Başarılı Bir Şekilde Gerçekleşti");
            } else {
                kampanyalar_ekle_uyari_mesaj.setText("Bir Hata Meydana Geldi. (Hata Kodu: -1)");
            }
        }
    }

    @FXML
    public void kampanyalar_degistir_kampanyayi_getir(ActionEvent event) {
        if (kampanyalar_degistir_kampanyalari_getir.getValue() == null) {
            kampanyalar_degistir_uyari_mesaj_1.setText("Lütfen bir kampanya seçiniz.");
        } else {
            String secilen = kampanyalar_degistir_kampanyalari_getir.getValue();
            kampanyalar_degistir_pane_2.setVisible(true);
            String[][] arr = Creator.kampanyalarDao().select();
            int kampanya_id = 0;
            for (int i = 0; i < arr.length; i++) {
                if (secilen.equals(arr[i][0])) {
                    kampanya_id = Integer.valueOf(arr[i][1]);
                }
            }
            int hangi = Creator.kampanyalarDao().searc_int(kampanya_id);
            String Title = Creator.kampanyalarDao().search_string(kampanya_id, 1);
            String Kampanya = Creator.kampanyalarDao().search_string(kampanya_id, 2);
            String Tarih = Creator.kampanyalarDao().search_string(kampanya_id, 3);
            String Kampanya_Kategorisi = Creator.kampanyalarDao().search_string(kampanya_id, 4);

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
    public void kampanyalar_degistir_degistir(ActionEvent event) {
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

            Center nw = new Center(k);
            int sonuc = Creator.kampanyalarDao().update(nw);

            if (sonuc == 1) {
                kampanyalar_degistir_uyari_mesaj_2.setText("İşlem Başarılı Bir Şekilde Gerçekleti.");
            } else {
                kampanyalar_degistir_uyari_mesaj_2.setText("Bir Hata Meydaha Geldi (Hata Kodu = -2)");
            }
        }
    }

    @FXML
    public void kampanyalar_sil_emin_misin_vazgec(ActionEvent event) {
        kampanyalar_sil_emin_misin_pane.setVisible(false);
    }

    @FXML
    public void kampanyalar_sil_emin_misin_sil(ActionEvent event) {
        int kampanya_id = Integer.valueOf(kampanyalar_silmekten_emin_kampanya_id.getText());

        int sonuc = Creator.kampanyalarDao().delete(kampanya_id);

        kampanyalar_sil_emin_misin_pane.setVisible(false);

        if (sonuc == 1) {
            kampanyalar_table_uyari_mesaj.setText("İşlem Başarılı Bir Şekilde Gerçekleştirildi");
        } else {
            kampanyalar_table_uyari_mesaj.setText("Bir Hata Meydana geldi (Hata Kodu = -3)");
        }

    }

    @FXML
    public void kampanyalar_geri(MouseEvent event) {
        geri_don_admin();
    }

    @FXML
    public void kampanyalar_ekle_geri(MouseEvent event) {
        kampanyalar_grid.setVisible(true);
        kampanyalar_ekle_pane.setVisible(false);

        kampanyalar_geri_tusu.setVisible(true);
        kampanyalar_ekle_geri_tusu.setVisible(false);

        kampanyalar_table_butonlu();
    }

    @FXML
    public void kampanyalar_degistir_geri(MouseEvent event) {
        kampanyalar_grid.setVisible(true);
        kampanyalar_degistir_pane.setVisible(false);

        kampanyalar_degistir_geri_tusu.setVisible(false);
        kampanyalar_geri_tusu.setVisible(true);

        kampanyalar_table_butonlu();
    }

    public void kampanyalar_table_butonlu() {

        ObservableList<kampanyalar> data = Table.data_Kampanyalar(kampanyalar_sil_emin_misin_pane, kampanyalar_silmekten_emin_kampanya_id);

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

}
