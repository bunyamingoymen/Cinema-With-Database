package Code_Admin;

import DAO.actorDAO;
import entity.actor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;

public class Aktorler extends Par {

    @FXML
    public void aktorler_ekle_giris(ActionEvent event) {
        aktorler_grid.setVisible(false);
        aktorler_ekle_pane.setVisible(true);
        aktorler_geri_tusu.setVisible(false);
        aktorler_ekle_geri_tusu.setVisible(true);
    }

    @FXML
    public void aktorler_degistir_sil_emin_misin_vazgec(ActionEvent event) {
        aktorler_sil_emin_misin.setVisible(false);
    }

    @FXML
    public void aktorler_degistir_sil_emin_misin_sil(ActionEvent event) {
        int id = Integer.parseInt(aktorler_silmekten_emin_id.getText());
        actorDAO adao = new actorDAO();
        adao.aktorler_sil(id);
        aktorler_sil_emin_misin.setVisible(false);

        aktorler_table();

        aktorler_table_uyari_mesaj.setText("İşlem Başarılı bir şekilde gerçekleşti.");
    }

    @FXML
    public void aktorler_ekle_ekle(ActionEvent event) {
        if ((aktorler_ekle_ad.getText().length() == 0) || (aktorler_ekle_soyad.getText().length() == 0)) {
            aktorler_ekle_uyari_mesaj.setText("Lütfen Gerekli Yerleri Doldurunuz.");
        } else {
            String ad = aktorler_ekle_ad.getText();
            String soyad = aktorler_ekle_soyad.getText();
            actor a = new actor(ad, soyad);
            actorDAO adao = new actorDAO();
            int sonuc = adao.aktorler_ekle(a);
            if (sonuc == 1) {
                aktorler_ekle_uyari_mesaj.setText("İşlem başarılı bir şekilde gerçekleitirldi.");
            } else {
                aktorler_ekle_uyari_mesaj.setText("Bir hata meydana geldi. Lütfen daha sonra tekrar deneyiniz.");
            }
        }
    }

    @FXML
    public void aktorler_ekle_sifirla(ActionEvent event) {
        aktorler_ekle_ad.setText("");
        aktorler_ekle_soyad.setText("");
    }

    @FXML
    public void aktorler_geri(MouseEvent event) {
        geri_don_admin();
    }

    @FXML
    public void aktorler_ekle_geri(MouseEvent event) {
        aktorler_ekle_pane.setVisible(false);
        aktorler_ekle_geri_tusu.setVisible(false);

        aktorler_grid.setVisible(true);
        aktorler_geri_tusu.setVisible(true);

        aktorler_table();

    }

    @FXML
    public void aktorler_table() {

        aktorler_aktor_id.setCellValueFactory(new PropertyValueFactory("actor_id"));
        aktorler_ad.setCellValueFactory(new PropertyValueFactory("ad"));
        aktorler_soyad.setCellValueFactory(new PropertyValueFactory("soyad"));
        aktorler_guncelle.setCellValueFactory(new PropertyValueFactory("guncelle"));
        aktorler_sil.setCellValueFactory(new PropertyValueFactory("sil"));
        aktorler_filmler.setCellValueFactory(new PropertyValueFactory("filmleri_goster"));

        ObservableList<actor> data = actor_data();

        aktorler_table_edit();
        aktorler_table_filter(data);
    }

    public ObservableList<actor> actor_data() {
        actorDAO adao = new actorDAO();
        ObservableList<actor> data = FXCollections.observableArrayList();
        data = adao.aktorler_select(data, aktorler_table_uyari_mesaj, aktorler_silmekten_emin_id, aktorler_sil_emin_misin, film_actor_table_actor_id, aktorler_grid, film_actor_grid, aktorler_geri_tusu, film_actor_geri_tusu, film_actor_film_name, film_actor_film_type, film_actor_yonetmen, film_actor_sil, table_film_actor, filterField_film_actor, film_actor_pane, film_actor_ekle_pane, film_actor_sil_emin_misin_pane, film_actor_id);
        return data;
    }

    public void aktorler_table_edit() {

        aktorler_ad.setCellFactory(TextFieldTableCell.forTableColumn());

        aktorler_ad.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setAd(e.getNewValue());
        });
        aktorler_soyad.setCellFactory(TextFieldTableCell.forTableColumn());
        aktorler_soyad.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setSoyad(e.getNewValue());
        });
        table_aktorler.setEditable(true);

    }

    public void aktorler_table_filter(ObservableList<actor> data) {
        FilteredList<actor> filteredData = new FilteredList<>(data, b -> true);
        filterField_aktorler.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(act -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(act.getActor_id()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (act.getAd().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (act.getSoyad().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }

            });
        });

        SortedList<actor> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(table_aktorler.comparatorProperty());

        table_aktorler.setItems(sortedData);
    }
}
