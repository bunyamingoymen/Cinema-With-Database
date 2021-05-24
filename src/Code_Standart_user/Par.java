package Code_Standart_user;

import Pattern.Creator;
import DAO.*;
import entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;

public class Par extends Sinema_Salonlari_Koltık_Dolu_Bos {

    public void vizyondaki_filmler_combo(ComboBox<String> combo, Label uyari_mesaj) {
        vizyondaki_filmlerDAO vizyondaki_film_islemleri = new vizyondaki_filmlerDAO();
        String[] arr = vizyondaki_film_islemleri.select_string();
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

    public void home_giris_ortak() {
        home_page.setVisible(true);

        pnl_vizyondaki_filmler.setVisible(false);
        pnl_abonelik.setVisible(false);
        pnl_haberler.setVisible(false);
        pnl_kampanyalar.setVisible(false);
        pnl_sinema_salonlari.setVisible(false);
        pnl_biletlerim.setVisible(false);
        pnl_settings.setVisible(false);
        pnl_film_detay.setVisible(false);

        home_page_icon.setVisible(false);
    }

    public int abonelik_turu_getir() {
        abonelerDAO adao = new abonelerDAO();
        int abonelik = adao.search(Creator.getU().getUser_id());

        return abonelik;
    }

    public void haberler_table_butonsuz(int kullanici_turu) {
        haberlerDAO hdao = new haberlerDAO();

        ObservableList<haberler> data = FXCollections.observableArrayList();

        data = hdao.select(data, kullanici_turu);

        haberler_title.setCellValueFactory(new PropertyValueFactory("Title"));
        haberler_haber.setCellValueFactory(new PropertyValueFactory("Haber"));
        haberler_tarih.setCellValueFactory(new PropertyValueFactory("Tarih"));
        haberler_haber_kategorisi.setCellValueFactory(new PropertyValueFactory("Haber_Kategorisi"));

        FilteredList<haberler> filteredData = new FilteredList<>(data, b -> true);

        filterField_haberler.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(hab -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (hab.getTitle().toLowerCase().indexOf(lowerCaseFilter) != -1) {
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

    public void haberler_giris_ortak() {
        pnl_haberler.setVisible(true);
        home_page_icon.setVisible(true);

        pnl_settings.setVisible(false);
        pnl_vizyondaki_filmler.setVisible(false);
        pnl_kampanyalar.setVisible(false);
        pnl_abonelik.setVisible(false);
        pnl_biletlerim.setVisible(false);
        pnl_sinema_salonlari.setVisible(false);
        pnl_film_detay.setVisible(false);
        home_page.setVisible(false);

        haberler_table_butonsuz(abonelik_turu_getir());
    }

    public void kampanyalar_table_butonsuz(int kullanici_turu) {
        kampanyalarDAO kdao = new kampanyalarDAO();

        ObservableList<kampanyalar> data = FXCollections.observableArrayList();

        data = kdao.select(data, kullanici_turu);

        kampanyalar_title.setCellValueFactory(new PropertyValueFactory("Title"));
        kampanyalar_kampanya.setCellValueFactory(new PropertyValueFactory("Kampanya"));
        kampanyalar_tarih.setCellValueFactory(new PropertyValueFactory("Tarih"));
        kampanyalar_kampanya_kategorisi.setCellValueFactory(new PropertyValueFactory("Kampanya_Kategorisi"));

        FilteredList<kampanyalar> filteredData = new FilteredList<>(data, b -> true);

        filterField_kampanyalar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(kam -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (kam.getTitle().toLowerCase().indexOf(lowerCaseFilter) != -1) {
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

    public void kampanyalar_giris_ortak() {
        pnl_kampanyalar.setVisible(true);
        home_page_icon.setVisible(true);

        pnl_settings.setVisible(false);
        pnl_vizyondaki_filmler.setVisible(false);
        pnl_haberler.setVisible(false);
        pnl_abonelik.setVisible(false);
        pnl_biletlerim.setVisible(false);
        pnl_sinema_salonlari.setVisible(false);
        pnl_film_detay.setVisible(false);
        home_page.setVisible(false);

        kampanyalar_table_butonsuz(abonelik_turu_getir());
    }

    public int seans_combo(ComboBox<String> combo, int vizyondaki_filmler) {
        seansDAO seans_islemleri = new seansDAO();
        String[] arr = seans_islemleri.seans_combo_doldur(vizyondaki_filmler);
        combo.getItems().clear();
        if (arr.length == 0) {
            return 0;

        } else {
            for (String s : arr) {
                combo.getItems().addAll(s);
            }

            combo.setPromptText("İstediğiniz seansı seçiniz.");
            return 1;
        }
    }

}
