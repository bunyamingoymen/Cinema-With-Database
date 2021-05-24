package entity;

import DAO.satin_alinan_biletlerDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class satin_alinan_biletler {

    private int satin_alinan_bilet_id;
    private int seans_id;
    private int user_id;
    private String koltuk_name;

    private String film_name;
    private String salon_name;
    private String saat;
    private String yonetmen_ad_soyad;

    private Button biletlerim_sil;

    public satin_alinan_biletler() {
    }

    public satin_alinan_biletler(int satin_alinan_bilet_id, int seans_id, int user_id, String koltuk_name) {
        this.satin_alinan_bilet_id = satin_alinan_bilet_id;
        this.seans_id = seans_id;
        this.user_id = user_id;
        this.koltuk_name = koltuk_name;
    }

    public satin_alinan_biletler(int satin_alinan_bilet_id, String film_name, String salon_name, String saat, String yonetmen_ad_soyad, String koltuk_name) {
        this.satin_alinan_bilet_id = satin_alinan_bilet_id;
        this.koltuk_name = koltuk_name;
        this.film_name = film_name;
        this.salon_name = salon_name;
        this.saat = saat;
        this.yonetmen_ad_soyad = yonetmen_ad_soyad;
    }
    
    

    public satin_alinan_biletler(int seans_id, int user_id, String koltuk_name) {
        this.seans_id = seans_id;
        this.user_id = user_id;
        this.koltuk_name = koltuk_name;
    }

    public satin_alinan_biletler(int user_id, int satin_alinan_bilet_id, String film_name, String salon_name, String saat, String ad_soyad, String koltuk_name, Button sil, TableColumn<satin_alinan_biletler, String> biletlerim_film_adi, TableColumn<satin_alinan_biletler, String> biletlerim_salon_adi, TableColumn<satin_alinan_biletler, String> biletlerim_yonetmen, TableColumn<satin_alinan_biletler, String> biletlerim_saat, TableColumn<satin_alinan_biletler, String> biletlerim_koltuk, TableColumn<satin_alinan_biletler, Button> biletlerim_sil, TextField filterField_biletlerim, Label biletlerim_uyari_mesaj, TableView<satin_alinan_biletler> table_biletlerim) {
        this.koltuk_name = koltuk_name;
        this.film_name = film_name;
        this.salon_name = salon_name;
        this.saat = saat;
        this.yonetmen_ad_soyad = ad_soyad;
        this.biletlerim_sil = sil;

        sil.setOnAction(e -> {
            satin_alinan_biletlerDAO sdao = new satin_alinan_biletlerDAO();

            int sonuc = sdao.satin_alinan_biletler_sil(satin_alinan_bilet_id);

            if (sonuc == 1) {
                biletlerim_uyari_mesaj.setText("İşlem Başarılı Bir Şekilde Gerçekleşti.");
                biletlerim_table(user_id, biletlerim_film_adi, biletlerim_salon_adi, biletlerim_yonetmen, biletlerim_saat, biletlerim_koltuk, biletlerim_sil, filterField_biletlerim, biletlerim_uyari_mesaj, table_biletlerim);

            } else {
                biletlerim_uyari_mesaj.setText("Bir hata Meydana Geldi. (Hata Kodu: -23)");
            }
        });
    }

    public void biletlerim_table(int user_id, TableColumn<satin_alinan_biletler, String> biletlerim_film_adi, TableColumn<satin_alinan_biletler, String> biletlerim_salon_adi, TableColumn<satin_alinan_biletler, String> biletlerim_yonetmen, TableColumn<satin_alinan_biletler, String> biletlerim_saat, TableColumn<satin_alinan_biletler, String> biletlerim_koltuk, TableColumn<satin_alinan_biletler, Button> biletlerim_sil, TextField filterField_biletlerim, Label biletlerim_uyari_mesaj, TableView<satin_alinan_biletler> table_biletlerim) {
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

    public int getSatin_alinan_bilet_id() {
        return satin_alinan_bilet_id;
    }

    public void setSatin_alinan_bilet_id(int satin_alinan_bilet_id) {
        this.satin_alinan_bilet_id = satin_alinan_bilet_id;
    }

    public int getSeans_id() {
        return seans_id;
    }

    public void setSeans_id(int seans_id) {
        this.seans_id = seans_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getKoltuk_name() {
        return koltuk_name;
    }

    public void setKoltuk_name(String koltuk_name) {
        this.koltuk_name = koltuk_name;
    }

    public String getFilm_name() {
        return film_name;
    }

    public void setFilm_name(String film_name) {
        this.film_name = film_name;
    }

    public String getSalon_name() {
        return salon_name;
    }

    public void setSalon_name(String salon_name) {
        this.salon_name = salon_name;
    }

    public String getSaat() {
        return saat;
    }

    public void setSaat(String saat) {
        this.saat = saat;
    }

    public String getYonetmen_ad_soyad() {
        return yonetmen_ad_soyad;
    }

    public void setYonetmen_ad_soyad(String yonetmen_ad_soyad) {
        this.yonetmen_ad_soyad = yonetmen_ad_soyad;
    }

    public Button getBiletlerim_sil() {
        return biletlerim_sil;
    }

    public void setBiletlerim_sil(Button biletlerim_sil) {
        this.biletlerim_sil = biletlerim_sil;
    }

}
