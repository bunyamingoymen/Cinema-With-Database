package Code_Standart_user;

import Pattern.Table;
import entity.vizyondaki_filmler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class Vizyondaki_Filmler extends Kampanyalar {

    @FXML
    public void vizyondaki_filmler_giris(ActionEvent event) {
        vizyondaki_filmler_giris_ortak();
    }

    public void vizyondaki_filmler_giris_ortak() {
        pnl_vizyondaki_filmler.setVisible(true);
        home_page_icon.setVisible(true);

        pnl_settings.setVisible(false);
        pnl_haberler.setVisible(false);
        pnl_kampanyalar.setVisible(false);
        pnl_abonelik.setVisible(false);
        pnl_biletlerim.setVisible(false);
        pnl_sinema_salonlari.setVisible(false);
        pnl_film_detay.setVisible(false);
        home_page.setVisible(false);

        vizyondaki_filmler_table_normal_kullanici();
    }

    public void vizyondaki_filmler_table_normal_kullanici() {

        ObservableList<vizyondaki_filmler> data = FXCollections.observableArrayList();

        data = Table.vizyondaki_filmler_bir(film_detay_film_id, film_detay_film_adi, film_detay_film_turu, film_detay_film_suresi, film_detay_yonetmen, film_detay_kalkis_tarihi, film_detay_kullanici_puani, pnl_vizyondaki_filmler, pnl_eski_filmler, pnl_film_detay, film_detay_aldigi_odul_sayisi, film_detay_hangi_abone_turu, film_detay_kalksi_tarihi_oncesi, film_detay_aldigi_odul_sayisi_oncesi, film_detay_hangi_abone_turu_oncesi, film_detay_id, film_detay_id_oncesi);

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

    @FXML
    public void vizyondaki_filmler_geri(MouseEvent event) {
        home_giris_ortak();
    }
}
