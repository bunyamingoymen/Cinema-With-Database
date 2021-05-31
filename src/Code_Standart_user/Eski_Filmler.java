package Code_Standart_user;

import DAO.eski_filmlerDAO;
import entity.eski_filmler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class Eski_Filmler extends Satin_Al {

    public void eski_filmler_table_abonesiz(int kullanici_turu) {
        eski_filmlerDAO edao = new eski_filmlerDAO();

        ObservableList<eski_filmler> data = FXCollections.observableArrayList();

        data = edao.select(data, kullanici_turu, film_detay_film_id, film_detay_film_adi, film_detay_film_turu, film_detay_film_suresi, film_detay_yonetmen, film_detay_kalkis_tarihi, film_detay_kullanici_puani, pnl_eski_filmler, pnl_vizyondaki_filmler, pnl_film_detay, film_detay_aldigi_odul_sayisi, film_detay_hangi_abone_turu, film_detay_kalksi_tarihi_oncesi, film_detay_aldigi_odul_sayisi_oncesi, film_detay_hangi_abone_turu_oncesi);

        eski_filmler_film_adi.setCellValueFactory(new PropertyValueFactory("film_name"));
        eski_filmler_film_type.setCellValueFactory(new PropertyValueFactory("film_type"));
        eski_filmler_film_suresi.setCellValueFactory(new PropertyValueFactory("film_suresi"));
        eski_filmler_yonetmen.setCellValueFactory(new PropertyValueFactory("yonetmen_ad_soyad"));
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

    @FXML
    public void eski_filmler_giris() {
        pnl_eski_filmler.setVisible(true);

        pnl_abonelik_0.setVisible(false);
        pnl_abonelik_diger.setVisible(false);
        pnl_abonelik_uyari_mesaj.setVisible(false);

        int kullanici_turu = abonelik_turu_getir();

        eski_filmler_table_abonesiz(kullanici_turu);
    }

    @FXML
    public void eski_filmler_geri(MouseEvent event) {
        pnl_abonelik_diger.setVisible(true);

        pnl_eski_filmler.setVisible(false);
    }
}
