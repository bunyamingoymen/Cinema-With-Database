package Code_Admin;

import DAO.haberlerDAO;
import entity.haberler;
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

public class Haberler extends Film_Actor {
    
public void haberler_combo_doldur(ComboBox<String> combo, Label uyari_mesaj) {
        String[][] arr = Creator.haberlerDao().select();
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
    public void haberler_ekle_giris(ActionEvent event) {
        haberler_grid.setVisible(false);
        haberler_ekle_pane.setVisible(true);
        haberler_degistir_pane.setVisible(false);

        hangi_aboneler_combo(haberler_ekle_hangi_kullanici);

        haberler_ekle_geri_tusu.setVisible(true);
        haberler_geri_tusu.setVisible(false);
        haberler_degistir_geri_tusu.setVisible(false);
    }

    @FXML
    public void haberler_degistir_giris(ActionEvent event) {
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
    public void haberler_ekle_sifirla(ActionEvent event) {
        haberler_ekle_title.setText("");
        haberler_ekle_tarih.setText("");
        haberler_ekle_kategori.setText("");
        haberler_ekle_haber.setText("");
        hangi_aboneler_combo(haberler_ekle_hangi_kullanici);

    }

    @FXML
    public void haberler_ekle_ekle(ActionEvent event) {
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
            
            Center nw = new Center(h);

            int sonuc = Creator.haberlerDao().create(nw);

            if (sonuc == 1) {
                haberler_ekle_uyari_mesaj.setText("İşlem Başarılı Bir Şekilde Gerçekleşti");
            } else {
                haberler_ekle_uyari_mesaj.setText("Bir Hata Meydana Geldi (Hata Kodu = -4)");
            }
        }
    }

    @FXML
    public void haberler_degistir_haberi_getir(ActionEvent event) {
        if (haberler_degistir_haberleri_getir.getValue() == null) {
            haberler_degistir_uyari_mesaj_1.setText("Lütfen bir Haber seçiniz.");
        } else {
            String secilen = haberler_degistir_haberleri_getir.getValue();
            haberler_degistir_pane_2.setVisible(true);
            String[][] arr = Creator.haberlerDao().select();
            int haber_id = 0;
            for (int i = 0; i < arr.length; i++) {
                if (secilen.equals(arr[i][0])) {
                    haber_id = Integer.valueOf(arr[i][1]);
                }
            }
            int hangi = Creator.haberlerDao().haberler_hangi_kullanici_getir(haber_id);
            String Title = Creator.haberlerDao().haberler_title_getir(haber_id);
            String Haber = Creator.haberlerDao().haberler_haber_getir(haber_id);
            String Tarih = Creator.haberlerDao().haberler_tarih_getir(haber_id);
            String Haber_Kategorisi = Creator.haberlerDao().haberler_haber_kategorisi_getir(haber_id);

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
    public void haberler_degistir_degistir(ActionEvent event) {
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

            Center nw = new Center(h);
            
            int sonuc = Creator.haberlerDao().update(nw);

            if (sonuc == 1) {
                haberler_degistir_uyari_mesaj_2.setText("İşlem Başarılı Bir Şekilde Gerçekleti.");
            } else {
                haberler_degistir_uyari_mesaj_2.setText("Bir Hata Meydaha Geldi (Hata Kodu = -5)");
            }
        }
    }

    @FXML
    public void haberler_sil_emin_misin_vazgec(ActionEvent event) {
        haberler_sil_emin_misin_pane.setVisible(false);
    }

    @FXML
    public void haberler_sil_emin_misin_sil(ActionEvent event) {
        int haber_id = Integer.valueOf(haberler_silmekten_emin_haber_id.getText());
        haberlerDAO hdao = new haberlerDAO();
        int sonuc = hdao.delete(haber_id);

        haberler_sil_emin_misin_pane.setVisible(false);

        if (sonuc == 1) {
            haberler_table_uyari_mesaj.setText("İşlem Başarılı Bir Şekilde Gerçekleştirildi");
        } else {
            haberler_table_uyari_mesaj.setText("Bir Hata Meydana geldi (Hata Kodu = -6)");
        }

    }

    @FXML
    public void haberler_geri(MouseEvent event) {
        geri_don_admin();
    }

    @FXML
    public void haberler_ekle_geri(MouseEvent event) {
        haberler_grid.setVisible(true);
        haberler_ekle_pane.setVisible(false);

        haberler_geri_tusu.setVisible(true);
        haberler_ekle_geri_tusu.setVisible(false);

        haberler_table_butonlu();
    }

    @FXML
    public void haberler_degistir_geri(MouseEvent event) {
        haberler_grid.setVisible(true);
        haberler_degistir_pane.setVisible(false);

        haberler_degistir_geri_tusu.setVisible(false);
        haberler_geri_tusu.setVisible(true);

        haberler_table_butonlu();
    }

    public void haberler_table_butonlu() {

        ObservableList<haberler> data = Table.data_Haberler(haberler_sil_emin_misin_pane, haberler_silmekten_emin_haber_id);

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
}
