package Code_Standart_user;

import Creator.Creator;
import DAO.satin_alinan_biletlerDAO;
import entity.satin_alinan_biletler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class Biletlerim extends Eski_Filmler {

    @FXML
    public void biletlerim_giris(ActionEvent event) {
        biletlerim_giris_ortak();
    }

    public void biletlerim_giris_ortak() {
        pnl_biletlerim.setVisible(true);
        home_page_icon.setVisible(true);

        pnl_settings.setVisible(false);
        pnl_vizyondaki_filmler.setVisible(false);
        pnl_haberler.setVisible(false);
        pnl_abonelik.setVisible(false);
        pnl_kampanyalar.setVisible(false);
        pnl_sinema_salonlari.setVisible(false);
        pnl_film_detay.setVisible(false);
        home_page.setVisible(false);

        biletlerim_table(Creator.getU().getUser_id());
    }

    public void biletlerim_table(int user_id) {
        satin_alinan_biletlerDAO edao = new satin_alinan_biletlerDAO();

        ObservableList<satin_alinan_biletler> data = FXCollections.observableArrayList();

        data = edao.satin_alinan_biletler_kullanicinin_biletlerini_goster(data, user_id, biletlerim_film_adi, biletlerim_salon_adi, biletlerim_yonetmen, biletlerim_saat, biletlerim_koltuk, biletlerim_sil, filterField_biletlerim, biletlerim_uyari_mesaj, table_biletlerim);

        biletlerim_film_adi.setCellValueFactory(new PropertyValueFactory("film_name"));
        biletlerim_salon_adi.setCellValueFactory(new PropertyValueFactory("salon_name"));
        biletlerim_yonetmen.setCellValueFactory(new PropertyValueFactory("yonetmen_ad_soyad"));
        biletlerim_saat.setCellValueFactory(new PropertyValueFactory("saat"));
        biletlerim_koltuk.setCellValueFactory(new PropertyValueFactory("koltuk_name"));
        biletlerim_sil.setCellValueFactory(new PropertyValueFactory("biletlerim_sil"));

        FilteredList<satin_alinan_biletler> filteredData = new FilteredList<>(data, b -> true);
        filterField_biletlerim.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(bil -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (bil.getFilm_name().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (bil.getSalon_name().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (bil.getYonetmen_ad_soyad().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (bil.getSaat().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (bil.getKoltuk_name().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }

            });
        });

        SortedList<satin_alinan_biletler> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(table_biletlerim.comparatorProperty());

        table_biletlerim.setItems(sortedData);
    }

    @FXML
    public void biletlerim_geri(MouseEvent event) {
        home_giris_ortak();
    }
}
