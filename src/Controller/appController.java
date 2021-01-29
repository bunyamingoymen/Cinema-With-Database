package Controller;

import DAO.*;
import entity.*;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class appController extends Center implements Initializable {

    /*
    Filmler
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     */
    private void filmler_combo(ComboBox<String> combo, Label uyari_mesaj) {
        filmlerDAO film_islemleri = new filmlerDAO();
        String[][] arr = film_islemleri.filmler_combo_doldur();
        combo.getItems().clear();
        if (arr.length == 0) {
            uyari_mesaj.setText("Kayıtlı Film Bulunamadı. Lütfen önce bir film ekleyiniz ekleyiniz.");

        } else {
            for (String[] arr1 : arr) {
                String v_filmler = arr1[0];
                combo.getItems().addAll(v_filmler);
            }

            combo.setPromptText("İstediğiniz filmi seçiniz.");
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /*
    Vizyondaki Filmler
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
     */
    @FXML
    private Pane ust_pnl_vizyondaki_filmler, vizyondaki_filmler_ekle_pane, vizyondaki_filmler_degistir_pane, vizyondaki_filmler_degistir_pane_1, vizyondaki_filmler_degistir_pane_2, vizyondaki_filmler_degistir_sil_emin_misin;

    @FXML
    private TextField vizyondaki_filmler_ekle_film_adi, vizyondaki_filmler_ekle_film_suresi, vizyondaki_filmler_ekle_film_type, vizyondaki_filmler_ekle_kalkis, vizyondaki_filmleri_degistir_sil_film_name, vizyondaki_filmleri_degistir_sil_film_suresi, vizyondaki_filmleri_degistir_sil_film_type, vizyondaki_filmleri_degistir_sil_kalkis;

    @FXML
    private ComboBox<String> vizyondaki_filmler_ekle_yonetmenler, vizyondaki_filmler_degistir_sil_filmler, vizyondaki_filmleri_degistir_sil_yonetmen;

    @FXML
    private GridPane vizyondaki_filmler_grid;

    @FXML
    private Label vizyondaki_filmler_ekle_uyari_mesaj, vizyondaki_filmler_degistir_sil_uyari_mesaj_1, vizyondaki_filmler_degistir_sil_uyari_mesaj_2, vizyondaki_filmler_degistir_sil_vizyon_id, vizyondaki_filmler_degistir_sil_film_id;

    @FXML
    private FontAwesomeIconView vizyondaki_filmler_geri_tusu, vizyondaki_filmler_ekle_geri_tusu, vizyondaki_filmler_degistir_geri_tusu;

    private void vizyondaki_filmler_combo(ComboBox<String> combo, Label uyari_mesaj) {
        vizyondaki_filmlerDAO vizyondaki_film_islemleri = new vizyondaki_filmlerDAO();
        String[] arr = vizyondaki_film_islemleri.vizyondaki_filmler_combo_doldur();
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

    @FXML
    private void vizyondaki_filmler_geri(MouseEvent event) {
        geri_don();
    }

    @FXML
    private void vizyondaki_filmler_ekle_geri(MouseEvent event) {
        vizyondaki_filmler_grid.setVisible(true);
        vizyondaki_filmler_ekle_pane.setVisible(false);

        vizyondaki_filmler_geri_tusu.setVisible(true);
        vizyondaki_filmler_ekle_geri_tusu.setVisible(false);

        vizyondaki_filmler_table();
    }

    @FXML
    private void vizyondaki_filmler_ekle_giris(ActionEvent event) {

        vizyondaki_filmler_grid.setVisible(false);
        vizyondaki_filmler_ekle_pane.setVisible(true);
        yonetmen_combo(vizyondaki_filmler_ekle_yonetmenler, vizyondaki_filmler_ekle_uyari_mesaj);
        vizyondaki_filmler_geri_tusu.setVisible(false);
        vizyondaki_filmler_ekle_geri_tusu.setVisible(true);
        vizyondaki_filmler_degistir_geri_tusu.setVisible(false);
        vizyondaki_filmler_degistir_pane.setVisible(false);
    }

    @FXML
    private void vizyondaki_filmler_ekle(ActionEvent event) {
        String film_name = vizyondaki_filmler_ekle_film_adi.getText();
        int film_suresi = 0;
        int control = -1;
        try {
            film_suresi = Integer.parseInt(vizyondaki_filmler_ekle_film_suresi.getText());
            control = 1;
        } catch (NumberFormatException e) {
            vizyondaki_filmler_ekle_uyari_mesaj.setText("Lütfen film süresine sadeca sayı giriniz.(dk olarak)");
        }
        if (control != -1) {
            String film_type = vizyondaki_filmler_ekle_film_type.getText();
            String kalkis = vizyondaki_filmler_ekle_kalkis.getText();
            String yonetmen = vizyondaki_filmler_ekle_yonetmenler.getValue();
            if ((film_name.length() == 0) || (vizyondaki_filmler_film_suresi.getText().length() == 0) || (film_type.length() == 0) || (kalkis.length() == 0) || (yonetmen == null)) {
                vizyondaki_filmler_ekle_uyari_mesaj.setText("Lütfen gerekli yerleri doldurunuz.");
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

                filmler f = new filmler(film_name, film_suresi, film_type, yonetmen_id);
                filmlerDAO fdao = new filmlerDAO();
                int film_id = fdao.filmler_ekle_id_gonder(f);
                vizyondaki_filmler v = new vizyondaki_filmler(film_id, kalkis, 0, 0);
                vizyondaki_filmlerDAO vdao = new vizyondaki_filmlerDAO();
                int sonuc = vdao.vizyondaki_filmler_dao_ekle(v);
                if (sonuc == 1) {
                    vizyondaki_filmler_ekle_uyari_mesaj.setText("İşlem Başarılı Bir Şekilde Gerçekleşti.");
                }

            }
        }
    }

    @FXML
    private void vizyondaki_filmler_ekle_sifirla(ActionEvent event) {
        vizyondaki_filmler_ekle_film_adi.setText("");
        vizyondaki_filmler_ekle_film_suresi.setText("");
        vizyondaki_filmler_ekle_film_type.setText("");
        vizyondaki_filmler_ekle_kalkis.setText("");
        vizyondaki_filmler_ekle_yonetmenler.getItems().clear();
        vizyondaki_filmler_ekle_yonetmenler.setPromptText("İstediğiniz yönetmeni seçiniz.");
        yonetmen_combo(vizyondaki_filmler_ekle_yonetmenler, vizyondaki_filmler_ekle_uyari_mesaj);
    }

    @FXML
    private void vizyondaki_filmler_degistir_sil_giris(ActionEvent event) {

        vizyondaki_filmler_grid.setVisible(false);
        vizyondaki_filmler_degistir_pane.setVisible(true);
        vizyondaki_filmler_combo(vizyondaki_filmler_degistir_sil_filmler, vizyondaki_filmler_degistir_sil_uyari_mesaj_1);
        vizyondaki_filmler_degistir_pane_1.setVisible(true);
        vizyondaki_filmler_degistir_pane_2.setVisible(false);
        vizyondaki_filmler_degistir_geri_tusu.setVisible(true);
        vizyondaki_filmler_geri_tusu.setVisible(false);
        vizyondaki_filmler_degistir_sil_emin_misin.setVisible(false);

    }

    @FXML
    private void vizyondaki_filmler_degistir_geri(MouseEvent event) {
        vizyondaki_filmler_grid.setVisible(true);
        vizyondaki_filmler_degistir_pane.setVisible(false);

        vizyondaki_filmler_degistir_geri_tusu.setVisible(false);
        vizyondaki_filmler_geri_tusu.setVisible(true);

        vizyondaki_filmler_table();

    }

    @FXML
    private void vizyondaki_filmleri_degistir_sil_filmi_getir(ActionEvent event) {

        if (vizyondaki_filmler_degistir_sil_filmler.getValue() == null) {
            vizyondaki_filmler_degistir_sil_uyari_mesaj_1.setText("Lütfen bir film seçiniz.");
        } else {
            String[] secilen = vizyondaki_filmler_degistir_sil_filmler.getValue().split(" | ");
            vizyondaki_filmler_degistir_pane_2.setVisible(true);
            vizyondaki_filmlerDAO vizyondaki_film_islemleri = new vizyondaki_filmlerDAO();
            int vizyondaki_film_id = Integer.parseInt(secilen[0]);
            filmlerDAO fdao = new filmlerDAO();

            String film_name = vizyondaki_film_islemleri.film_adi_getir(vizyondaki_film_id);
            String film_type = vizyondaki_film_islemleri.film_type_getir(vizyondaki_film_id);
            int film_suresi = vizyondaki_film_islemleri.film_suresi_getir(vizyondaki_film_id);
            String yonetmen = fdao.filmler_yonetmen_id_getir(vizyondaki_film_islemleri.film_id_getir(vizyondaki_film_id)) + " " + vizyondaki_film_islemleri.yonetmen_getir(vizyondaki_film_id);
            String kalkis = vizyondaki_film_islemleri.vizyondan_kalkis_tarihi_getir(vizyondaki_film_id);

            vizyondaki_filmleri_degistir_sil_film_name.setText(film_name);
            vizyondaki_filmleri_degistir_sil_film_type.setText(film_type);
            vizyondaki_filmleri_degistir_sil_film_suresi.setText(String.valueOf(film_suresi));
            vizyondaki_filmleri_degistir_sil_kalkis.setText(kalkis);
            yonetmen_combo(vizyondaki_filmleri_degistir_sil_yonetmen, vizyondaki_filmler_degistir_sil_uyari_mesaj_2);
            vizyondaki_filmleri_degistir_sil_yonetmen.setValue(yonetmen);
            vizyondaki_filmler_degistir_sil_vizyon_id.setText(String.valueOf(vizyondaki_film_id));
            vizyondaki_filmler_degistir_sil_film_id.setText(String.valueOf(vizyondaki_film_islemleri.film_id_getir(vizyondaki_film_id)));
        }

    }

    @FXML
    public void vizyondaki_filmler_degistir_sil_degistir(ActionEvent event) {
        if ((vizyondaki_filmleri_degistir_sil_film_name.getText().length() == 0) || (vizyondaki_filmleri_degistir_sil_film_type.getText().length() == 0) || (vizyondaki_filmleri_degistir_sil_film_suresi.getText().length() == 0) || (vizyondaki_filmleri_degistir_sil_kalkis.getText().length() == 0) || (vizyondaki_filmleri_degistir_sil_yonetmen.getValue() == null)) {
            vizyondaki_filmler_degistir_sil_uyari_mesaj_2.setText("Lütfen Gerekli Yerleri Doldurunuz.");
        } else {
            String film_name = vizyondaki_filmleri_degistir_sil_film_name.getText();
            String film_type = vizyondaki_filmleri_degistir_sil_film_type.getText();
            String film_suresi = vizyondaki_filmleri_degistir_sil_film_suresi.getText();
            String kalkis = vizyondaki_filmleri_degistir_sil_kalkis.getText();
            String yonetmen = vizyondaki_filmleri_degistir_sil_yonetmen.getValue();
            yonetmenlerDAO y = new yonetmenlerDAO();
            int secilen_yonetmen_id = 0;
            String[][] arr = y.yonetmen_combo_doldur();

            for (int i = 0; i < arr.length; i++) {
                if (yonetmen.equals(arr[i][0])) {
                    secilen_yonetmen_id = Integer.valueOf(arr[i][1]);
                }
            }

            String vizyondaki_film_id = vizyondaki_filmler_degistir_sil_vizyon_id.getText();
            String film_id = vizyondaki_filmler_degistir_sil_film_id.getText();
            vizyondaki_filmlerDAO vdao = new vizyondaki_filmlerDAO();
            filmlerDAO fdao = new filmlerDAO();

            vizyondaki_filmler v = new vizyondaki_filmler(Integer.valueOf(vizyondaki_film_id), Integer.valueOf(film_id), kalkis, vdao.kullanici_puani_getir(Integer.valueOf(vizyondaki_film_id)), vdao.seans_sayisi_getir(Integer.valueOf(vizyondaki_film_id)));
            filmler f = new filmler(Integer.valueOf(film_id), film_name, Integer.valueOf(film_suresi), film_type, secilen_yonetmen_id);

            int sonuc = vdao.vizyondaki_filmler_degistir(v, f);
            if (sonuc == 1) {
                vizyondaki_filmler_degistir_sil_uyari_mesaj_1.setText("İşlem başarılı bir şekilde gerçekleştirildi.");

            } else {
                vizyondaki_filmler_degistir_sil_uyari_mesaj_2.setText("Bir hata meydana geldi. Lütfen daha sonra tekrar deneyiniz.");
            }
        }
    }

    @FXML
    public void vizyondaki_filmler_degistir_sil_sil(ActionEvent event) {
        vizyondaki_filmler_degistir_sil_emin_misin.setVisible(true);
    }

    public void vizyondaki_filmler_degistir_sil_tamamen_sil(ActionEvent event) {
        int vizyondaki_film_id = Integer.valueOf(vizyondaki_filmler_degistir_sil_vizyon_id.getText());
        vizyondaki_filmlerDAO vdao = new vizyondaki_filmlerDAO();
        int sonuc = vdao.vizyondaki_filmler_tamamen_sil(vizyondaki_film_id);
        if (sonuc == 1) {
            vizyondaki_filmler_degistir_sil_uyari_mesaj_1.setText("İstenilen Veri Tamamiyle Silindi");
            vizyondaki_filmler_degistir_sil_filmler.getItems().clear();
            vizyondaki_filmler_degistir_pane_2.setVisible(false);
        } else {
            vizyondaki_filmler_degistir_sil_uyari_mesaj_2.setText("Bir hata meydana geldi lütfen daha sonra tekrar denetyiniz.");
        }
        vizyondaki_filmler_degistir_sil_emin_misin.setVisible(false);
    }

    public void vizyondaki_filmler_degistir_sil_vazgec(ActionEvent event) {
        vizyondaki_filmler_degistir_sil_emin_misin.setVisible(false);
    }

    public void vizyondaki_filmler_degistir_sil_sadece_vizyondan_sil(ActionEvent event) {
        vizyondaki_filmler_degistir_sil_emin_misin.setVisible(false);

        int vizyondaki_film_id = Integer.valueOf(vizyondaki_filmler_degistir_sil_vizyon_id.getText());
        vizyondaki_filmlerDAO vdao = new vizyondaki_filmlerDAO();
        int sonuc = vdao.vizyondaki_filmler_sadece_vizyondan_sil(vizyondaki_film_id);
        if (sonuc == 1) {
            vizyondaki_filmler_degistir_sil_uyari_mesaj_1.setText("İstenilen Veri Sadece Vizyonan Silindi");
            vizyondaki_filmler_degistir_sil_filmler.getItems().clear();
            vizyondaki_filmler_degistir_pane_2.setVisible(false);
        } else {
            vizyondaki_filmler_degistir_sil_uyari_mesaj_2.setText("Bir hata meydana geldi lütfen daha sonra tekrar denetyiniz.");
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /*
    Seans İşlemleri
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     */
    @FXML
    private AnchorPane pnl_seans;

    @FXML
    private Pane ust_pnl_seans, seans_ekle_pane, seans_degistir_sil_pane_1, seans_degistir_sil_pane_2, seans_degistir_sil_pane, seans_degistir_sil_silmekten_emin_pane;

    @FXML
    private FontAwesomeIconView seans_geri_tusu, seans_ekle_geri_tusu, seans_degistir_geri_tusu;

    @FXML
    private TextField filterField_seans;

    @FXML
    private ComboBox<String> seans_ekle_film_combo, seans_ekle_salon_combo, seans_ekle_saat_combo, seans_degistir_seans_getir_combo, seans_degistir_sil_film_combo, seans_degistir_sil_salon_combo, seans_degistir_sil_saat_combo;

    @FXML
    private Label seans_degistir_sil_uyari_mesaj_1, seans_degistir_sil_uyari_mesaj_2, seans_ekle_uyari_mesaj, seans_degistir_sil_seans_id;

    @FXML
    private GridPane seans_grid;

    @FXML
    private TableView<seans> table_seans;

    @FXML
    private TableColumn<seans, String> seans_film_adi, seans_salon_adi, seans_saat;

    private void saat_combo(ComboBox<String> combo) {

        combo.getItems().clear();

        combo.getItems().addAll("10.00-12.00");
        combo.getItems().addAll("12.30-13.30");
        combo.getItems().addAll("14.00-16.00");
        combo.getItems().addAll("16.30-18.30");
        combo.getItems().addAll("19.00-21.00");

        combo.setPromptText("İstediğiniz saati seçiniz.");
    }

    private void seans_combo(ComboBox<String> combo, Label uyari_mesaj) {
        seansDAO seans_islemleri = new seansDAO();
        String[] arr = seans_islemleri.seans_combo_doldur();
        combo.getItems().clear();
        if (arr.length == 0) {
            uyari_mesaj.setText("Kayıtlı Vizyondaki Film Bulunamadı. Lütfen önce bir vizyona film ekleyiniz ekleyiniz.");

        } else {
            for (String s : arr) {
                combo.getItems().addAll(s);
            }

            combo.setPromptText("İstediğiniz filmi seçiniz.");
        }
    }

    @FXML
    private void seans_islemleri_giris(ActionEvent event) {
        pnl_vizyondaki_filmler.setVisible(false);
        pnl_seans.setVisible(true);

        seans_grid.setVisible(true);
        seans_ekle_pane.setVisible(false);
        seans_degistir_sil_pane.setVisible(false);
        seans_table();

    }

    private void seans_table() {
        seansDAO s = new seansDAO();

        ObservableList<seans> data = FXCollections.observableArrayList();

        data = s.seans_select(data);

        seans_film_adi.setCellValueFactory(new PropertyValueFactory("film_name"));
        seans_salon_adi.setCellValueFactory(new PropertyValueFactory("salon_name"));
        seans_saat.setCellValueFactory(new PropertyValueFactory("saat"));

        FilteredList<seans> filteredData = new FilteredList<>(data, b -> true);

        filterField_seans.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(sea -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (sea.getFilm_name().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (sea.getSalon_name().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (sea.getSaat().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }

            });
        });

        SortedList<seans> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(table_seans.comparatorProperty());

        table_seans.setItems(sortedData);
    }

    @FXML
    private void seans_ekle_giris(ActionEvent event) {
        seans_grid.setVisible(false);
        seans_ekle_pane.setVisible(true);
        seans_degistir_sil_pane.setVisible(false);

        seans_degistir_geri_tusu.setVisible(false);
        seans_ekle_geri_tusu.setVisible(true);
        seans_geri_tusu.setVisible(false);

        vizyondaki_filmler_combo(seans_ekle_film_combo, seans_ekle_uyari_mesaj);
        sinema_salonlari_goruntule_combo(seans_ekle_salon_combo, seans_ekle_uyari_mesaj);
        saat_combo(seans_ekle_saat_combo);
    }

    @FXML
    private void seans_degistir_sil_giris(ActionEvent event) {
        seans_grid.setVisible(false);
        seans_ekle_pane.setVisible(false);
        seans_degistir_sil_pane.setVisible(true);
        seans_degistir_sil_pane_2.setVisible(false);
        seans_degistir_sil_silmekten_emin_pane.setVisible(false);

        seans_degistir_geri_tusu.setVisible(true);
        seans_ekle_geri_tusu.setVisible(false);
        seans_geri_tusu.setVisible(false);

        seans_degistir_sil_film_combo.getItems().clear();
        seans_degistir_sil_salon_combo.getItems().clear();
        seans_degistir_sil_saat_combo.getItems().clear();

        seans_combo(seans_degistir_seans_getir_combo, seans_degistir_sil_uyari_mesaj_1);

    }

    @FXML
    private void seans_degistir_sil_seans_getir(ActionEvent event) {
        if (seans_degistir_seans_getir_combo.getValue() == null) {
            seans_degistir_sil_uyari_mesaj_1.setText("Lütfen bir seans seçiniz.");
        } else {
            String[] secilen = seans_degistir_seans_getir_combo.getValue().split(" | ");
            int seans_id = Integer.parseInt(secilen[0]);
            seans_degistir_sil_seans_id.setText(String.valueOf(seans_id));
            seansDAO sdao = new seansDAO();
            int vizyondaki_film_id = sdao.vizyondaki_film_id_getir(seans_id);
            vizyondaki_filmlerDAO vdao = new vizyondaki_filmlerDAO();
            int film_id = vdao.film_id_getir(vizyondaki_film_id);
            filmlerDAO fdao = new filmlerDAO();
            yonetmenlerDAO ydao = new yonetmenlerDAO();

            int yonetmen_id = fdao.filmler_yonetmen_id_getir(film_id);
            String secilen_film = vizyondaki_film_id + " | " + vdao.film_adi_getir(vizyondaki_film_id) + " | " + fdao.filmler_film_type_getir(film_id) + " | " + fdao.filmler_film_suresi_getir(film_id) + " | " + ydao.yonetmenler_ad_getir(yonetmen_id);

            vizyondaki_filmler_combo(seans_degistir_sil_film_combo, seans_degistir_sil_uyari_mesaj_2);
            seans_degistir_sil_film_combo.setValue(secilen_film);

            int salon_id = sdao.salon_id_getir(seans_id);

            sinema_salonlariDAO sinemadao = new sinema_salonlariDAO();

            String secilen_salon = salon_id + " | " + sinemadao.salon_adi_getir(salon_id) + " | " + sinemadao.koltuk_sayisi_getir(salon_id);

            sinema_salonlari_goruntule_combo(seans_degistir_sil_salon_combo, seans_degistir_sil_uyari_mesaj_2);
            seans_degistir_sil_salon_combo.setValue(secilen_salon);

            String saat = sdao.saat_getir(seans_id);

            saat_combo(seans_degistir_sil_saat_combo);
            seans_degistir_sil_saat_combo.setValue(saat);

            seans_degistir_sil_pane_2.setVisible(true);
        }
    }

    @FXML
    private void seans_degsitir_sil_sil(ActionEvent event) {
        seans_degistir_sil_silmekten_emin_pane.setVisible(true);
    }

    @FXML
    private void seans_degistir_sil_silmekten_emin_sil(ActionEvent event) {
        seansDAO sdao = new seansDAO();
        int sonuc = sdao.seans_dao_sil(Integer.parseInt(seans_degistir_sil_seans_id.getText()));

        if (sonuc == 1) {
            seans_degistir_sil_pane_2.setVisible(false);
            seans_combo(seans_degistir_seans_getir_combo, seans_degistir_sil_uyari_mesaj_1);
            seans_degistir_sil_uyari_mesaj_1.setText("İşlem Başarılı bir şekilde gerçekleşti.");

            seans_degistir_sil_silmekten_emin_pane.setVisible(false);
        } else {
            seans_degistir_sil_uyari_mesaj_2.setText("Bir hata meydana geldi. Lütfen daha sonra tekrar deneyiniz.");
            seans_degistir_sil_silmekten_emin_pane.setVisible(false);
        }
    }

    @FXML
    private void seans_degistir_sil_silmekten_emin_vazgec(ActionEvent event) {
        seans_degistir_sil_silmekten_emin_pane.setVisible(false);
    }

    @FXML
    private void seans_degistir_sil_degistir(ActionEvent event) {
        if ((seans_degistir_sil_film_combo.getValue().length() == 0) || (seans_degistir_sil_salon_combo.getValue().length() == 0) || (seans_degistir_sil_saat_combo.getValue().length() == 0)) {
            seans_degistir_sil_uyari_mesaj_2.setText("Lütfen gerekli yerleri doldurunuz.");
        } else {
            String[] secilen_film = seans_degistir_sil_film_combo.getValue().split(" | ");
            int vizyondaki_film_id = Integer.parseInt(secilen_film[0]);

            String[] secilen_salon = seans_degistir_sil_salon_combo.getValue().split(" | ");
            int salon_id = Integer.parseInt(secilen_salon[0]);

            String saat = seans_degistir_sil_saat_combo.getValue();

            int seans_id = Integer.parseInt(seans_degistir_sil_seans_id.getText());

            seans s = new seans(seans_id, salon_id, vizyondaki_film_id, saat);

            seansDAO sdao = new seansDAO();

            int sonuc = sdao.seans_degistir(s);

            switch (sonuc) {
                case 1:
                    seans_degistir_sil_uyari_mesaj_2.setText("İşlem Başarılı Bir Şekilde Gerçekleştirildi.");
                    break;
                case -1:
                    seans_degistir_sil_uyari_mesaj_2.setText("Aynı salon ve aynı satte başka film oynamaktadır.");
                    break;
                default:
                    seans_degistir_sil_uyari_mesaj_2.setText("Bir hata meydana geldi lütfen daha sonra tekrar deneyiniz.");
                    break;
            }
        }
    }

    @FXML
    private void seans_ekle_ekle(ActionEvent event) {
        if ((seans_ekle_film_combo.getValue() == null) || (seans_ekle_salon_combo.getValue() == null) || (seans_ekle_saat_combo == null)) {
            seans_ekle_uyari_mesaj.setText("Lütfen gerekli bilgileir seçiniz.");
        } else {
            seansDAO sdao = new seansDAO();
            String[] secilen_vizyondaki_film = seans_ekle_film_combo.getValue().split(" | ");
            int vizyondaki_film_id = Integer.parseInt(secilen_vizyondaki_film[0]);

            String[] secilen_salon = seans_ekle_salon_combo.getValue().split(" | ");
            int salon_id = Integer.parseInt(secilen_salon[0]);

            String saat = seans_ekle_saat_combo.getValue();

            seans s = new seans(salon_id, vizyondaki_film_id, saat);
            int sonuc = sdao.seans_dao_ekle(s);
            switch (sonuc) {
                case 1:

                    vizyondaki_filmler_combo(seans_ekle_film_combo, seans_ekle_uyari_mesaj);
                    sinema_salonlari_goruntule_combo(seans_ekle_salon_combo, seans_ekle_uyari_mesaj);
                    saat_combo(seans_ekle_saat_combo);

                    seans_ekle_uyari_mesaj.setText("İşlem Başarılı bir şekilde gerçekleştirldi.");

                    break;
                case -1:
                    seans_ekle_uyari_mesaj.setText("Bu saate aynı salonda seans zaten var. Lütfen ya farklı salo seçiniz ya da farklı saat seçiniz.");
                    break;
                default:
                    seans_ekle_uyari_mesaj.setText("Bir hata meydana geldi. Lütfen daha sonra tekrar deneyiniz.");
                    break;
            }

        }
    }

    @FXML
    private void seans_ekle_sifirla(ActionEvent event) {
        vizyondaki_filmler_combo(seans_ekle_film_combo, seans_ekle_uyari_mesaj);
        sinema_salonlari_goruntule_combo(seans_ekle_salon_combo, seans_ekle_uyari_mesaj);
        saat_combo(seans_ekle_saat_combo);
    }

    @FXML
    private void seans_geri(MouseEvent event) {
        pnl_seans.setVisible(false);
        pnl_vizyondaki_filmler.setVisible(true);

        vizyondaki_filmler_grid.setVisible(true);
        vizyondaki_filmler_ekle_pane.setVisible(false);
        vizyondaki_filmler_degistir_pane.setVisible(false);

        vizyondaki_filmler_geri_tusu.setVisible(true);
        vizyondaki_filmler_ekle_geri_tusu.setVisible(false);
        vizyondaki_filmler_degistir_geri_tusu.setVisible(false);

    }

    @FXML
    private void seans_ekle_geri(MouseEvent event) {
        seans_grid.setVisible(true);
        seans_ekle_pane.setVisible(false);

        seans_geri_tusu.setVisible(true);
        seans_ekle_geri_tusu.setVisible(false);

        seans_table();
    }

    @FXML
    private void seans_degistir_geri(MouseEvent event) {
        seans_grid.setVisible(true);
        seans_degistir_sil_pane.setVisible(false);

        seans_degistir_geri_tusu.setVisible(false);
        seans_geri_tusu.setVisible(true);

        seans_table();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /*
    Eski Filmler
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
     */
    @FXML
    private Pane ust_pnl_eski_filmler, eski_filmler_ekle_pane, eski_filmler_degistir_pane, eski_filmler_degistir_pane_1, eski_filmler_degistir_pane_2, eski_filmler_degistir_sil_emin_misin;

    @FXML
    private TableColumn<eski_filmler, String>  eski_filmler_hangi_abone;
    
    @FXML
    private TextField eski_film_name, eski_film_type, eski_film_suresi, eski_aldigi_odul_sayisi, eski_filmleri_degistir_sil_film_name, eski_filmleri_degistir_sil_film_type, eski_filmleri_degistir_sil_film_suresi, eski_filmleri_degistir_sil_aldigi_odul;

    @FXML
    private ComboBox<String> eski_hangi_aboneler, eski_ekle_yonetmenler, eski_degistir_sil_filmler, eski_filmleri_degistir_sil_yonetmen, eski_filmleri_degistir_sil_hangi_abone;

    @FXML
    private GridPane eski_filmler_grid;

    @FXML
    private Label eski_ekle_uyari_mesaj, eski_filmler_degistir_sil_uyari_mesaj_1, eski_filmler_degistir_sil_uyari_mesaj_2, eski_filmler_degistir_sil_eski_id;

    @FXML
    private FontAwesomeIconView eski_filmler_geri_tusu, eski_filmler_ekle_geri_tusu, eski_filmler_degistir_geri_tusu;

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
        geri_don();
    }

    @FXML
    private void eski_filmler_ekle_geri(MouseEvent event) {
        eski_filmler_grid.setVisible(true);
        eski_filmler_ekle_pane.setVisible(false);

        eski_filmler_geri_tusu.setVisible(true);
        eski_filmler_ekle_geri_tusu.setVisible(false);

        eski_filmler_table();
    }

    @FXML
    private void eski_filmler_degistir_geri(MouseEvent event) {
        eski_filmler_grid.setVisible(true);
        eski_filmler_degistir_pane.setVisible(false);

        eski_filmler_degistir_geri_tusu.setVisible(false);
        eski_filmler_geri_tusu.setVisible(true);

        eski_filmler_table();

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

        yonetmenlerDAO yonetmen_islemleri = new yonetmenlerDAO();

        String[][] arr = yonetmen_islemleri.yonetmen_combo_doldur();

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

                    filmler f = new filmler(film_name, film_suresi, film_type, yonetmen_id);
                    filmlerDAO fdao = new filmlerDAO();
                    int film_id = fdao.filmler_ekle_id_gonder(f);
                    eski_filmler ef = new eski_filmler(film_id, hangi_abone, aldigi_odul);
                    eski_filmlerDAO edao = new eski_filmlerDAO();
                    int sonuc = edao.eski_filmler_dao_ekle(ef);
                    int sonuc2 = y.yonetmen_film_sayisi_arttir(yonetmen_id);
                    if ((sonuc == 1) && (sonuc2 == 1)) {
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

    private void eski_filmler_table() {
        eski_filmlerDAO edao = new eski_filmlerDAO();

        ObservableList<eski_filmler> data = FXCollections.observableArrayList();

        data = edao.eski_filmler_select(data);

        eski_filmler_film_adi.setCellValueFactory(new PropertyValueFactory("film_name"));
        eski_filmler_film_type.setCellValueFactory(new PropertyValueFactory("film_type"));
        eski_filmler_film_suresi.setCellValueFactory(new PropertyValueFactory("film_suresi"));
        eski_filmler_yonetmen.setCellValueFactory(new PropertyValueFactory("yonetmen_ad_soyad"));
        eski_filmler_hangi_abone.setCellValueFactory(new PropertyValueFactory("hangi_aboneler_izleyebilir"));
        eski_filmler_aldigi_odul_sayisi.setCellValueFactory(new PropertyValueFactory("aldigi_odul_sayisi"));

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

    @FXML
    private Pane ust_pnl_kampanyalar, kampanyalar_ekle_pane, kampanyalar_degistir_pane, kampanyalar_degistir_pane_1, kampanyalar_degistir_pane_2, kampanyalar_sil_emin_misin_pane;

    @FXML
    private GridPane kampanyalar_grid;

    @FXML
    private FontAwesomeIconView kampanyalar_ekle_geri_tusu, kampanyalar_degistir_geri_tusu;

    @FXML
    private TableColumn<kampanyalar, String>kampanyalar_hangi_kullanıcı;

    @FXML
    private TableColumn<kampanyalar, Button> kampanyalar_sil;

    @FXML
    private TextField kampanyalar_ekle_title, kampanyalar_ekle_tarih, kampanyalar_ekle_kategori, kampanyalar_degistir_title, kampanyalar_degistir_tarih, kampanyalar_degistir_kategori;

    @FXML
    private ComboBox<String> kampanyalar_ekle_hangi_kullanici, kampanyalar_degistir_hangi_kullanici, kampanyalar_degistir_kampanyalari_getir;

    @FXML
    private TextArea kampanyalar_ekle_kampanya, kampanyalar_degistir_kampanya;

    @FXML
    private Label kampanyalar_ekle_uyari_mesaj, kampanyalar_degistir_uyari_mesaj_1, kampanyalar_degistir_uyari_mesaj_2, kampanyalar_silmekten_emin_kampanya_id, kampanyalar_degistir_kampanya_id, kampanyalar_table_uyari_mesaj;

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
        geri_don();
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
                } else if (kam.getKampanya().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (kam.getTarih().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (kam.getKampanya_Kategorisi().toLowerCase().indexOf(lowerCaseFilter) != -1) {
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
    @FXML
    private Pane ust_pnl_haberler, haberler_ekle_pane, haberler_degistir_pane, haberler_degistir_pane_1, haberler_degistir_pane_2, haberler_sil_emin_misin_pane;

    @FXML
    private GridPane haberler_grid;

    @FXML
    private FontAwesomeIconView haberler_ekle_geri_tusu, haberler_degistir_geri_tusu;

    @FXML
    private TableColumn<haberler, Button> haberler_sil;

    @FXML
    private TextField haberler_ekle_title, haberler_ekle_tarih, haberler_ekle_kategori, haberler_degistir_title, haberler_degistir_tarih, haberler_degistir_kategori;

    @FXML
    private ComboBox<String> haberler_ekle_hangi_kullanici, haberler_degistir_hangi_kullanici, haberler_degistir_haberleri_getir;

    @FXML
    private TextArea haberler_ekle_haber, haberler_degistir_haber;

    @FXML
    private Label haberler_ekle_uyari_mesaj, haberler_degistir_uyari_mesaj_1, haberler_degistir_uyari_mesaj_2, haberler_silmekten_emin_haber_id, haberler_degistir_haber_id, haberler_table_uyari_mesaj;

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
        geri_don();
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
                } else if (hab.getHaber().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (hab.getTarih().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (hab.getHaber_Kategorisi().toLowerCase().indexOf(lowerCaseFilter) != -1) {
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
    @FXML
    private Pane sinema_salonlari_home_pane, sinema_salonlari_goruntule_pane, sinema_salonu_ekle_pane, sinema_salonu_duzenle_pane, sinema_salonu_duzenle_pane_1, sinema_salonu_duzenle_pane_2;

    @FXML
    private Label sinema_salonu_goruntule_uyari_mesaj, sinema_salonu_ekle_uyari_mesaj, sinema_salonunu_guncelle_id, sinema_salonu_guncelle_uyari_mesaj;

    @FXML
    private ComboBox<String> sinema_salonlari_goruntule, sinema_salonu_ekle_koltuk_sayisi, sinema_salonlarini_duzenle_combo, sinema_salonlarini_duzenle_koltuk_sayisi;

    @FXML
    private TextField sinema_salonu_ekle_name, sinema_salonu_guncelle_name;

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
    private void sinema_salonlari_goruntule_combo(ComboBox<String> combo, Label uyari_mesaj) {

        sinema_salonlariDAO sdao = new sinema_salonlariDAO();
        String[] arr = sdao.salonlar_combo_doldur();
        combo.getItems().clear();
        if (arr.length == 0) {
            uyari_mesaj.setText("Kayıtlı Haber Bulunamadı. Lütfen Önce Bir Haber Ekleyiniz.");

        } else {
            for (String k : arr) {
                combo.getItems().addAll(k);
            }
            combo.setPromptText("İstediğiniz Haberi seçiniz.");
        }
    }

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

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /*
    Yönetmenler
     */
    @FXML
    private Pane ust_pnl_yonetmenler, yonetmenler_ekle_pane, yonetmenler_degistir_pane, yonetmenler_degistir_pane_1, yonetmenler_degistir_pane_2, yonetmenler_degistir_sil_emin_misin;

    @FXML
    private AnchorPane pnl_yonetmenler;

    @FXML
    private TableColumn<yonetmenler, String> yonetmenler_yonetmen_id, yonetmenler_ad, yonetmenler_soyad, yonetmenler_film_sayisi;

    @FXML
    private TableView<yonetmenler> table_yonetmenler;

    @FXML
    private TextField filterField_yonetmenler, yonetmenler_ekle_ad, yonetmenler_ekle_soyad, yonetmenler_ekle_film_sayisi, yonetmenler_degistir_sil_ad, yonetmenler_degistir_sil_soyad, yonetmenler_degistir_sil_film_sayisi;

    @FXML
    private ComboBox<String> yonetmenler_degistir_sil_combo;

    @FXML
    private GridPane yonetmenler_grid;

    @FXML
    private Label yonetmenler_degistir_sil_uyari_mesaj_1, yonetmenler_ekle_uyari_mesaj, yonetmenler_degistir_sil_uyari_mesaj_2, yonetmen_degistir_sil_yonetmen_id;

    @FXML
    private FontAwesomeIconView yonetmenler_geri_tusu, yonetmenler_ekle_geri_tusu, yonetmenler_degistir_geri_tusu;

    private void yonetmen_combo(ComboBox<String> combo, Label uyari_mesaji) {

        yonetmenlerDAO yonetmen_islemleri = new yonetmenlerDAO();

        String[][] arr = yonetmen_islemleri.yonetmen_combo_doldur();

        combo.getItems().clear();

        if (arr.length == 0) {

            uyari_mesaji.setText("Kayıtlı Yönetmen Bulunamadı. Lütfen önce bir yönetmen ekleyiniz.");

        } else {

            for (String[] arr1 : arr) {
                String name_surname = arr1[0];
                combo.getItems().addAll(name_surname);
            }

            combo.setPromptText("İstediğiniz yönetmeni seçiniz.");
        }

    }

    @FXML
    private void yonetmenler_ekle_giris(ActionEvent event) {
        yonetmenler_grid.setVisible(false);
        yonetmenler_ekle_pane.setVisible(true);
        yonetmenler_geri_tusu.setVisible(false);
        yonetmenler_ekle_geri_tusu.setVisible(true);
        yonetmenler_degistir_geri_tusu.setVisible(false);
        yonetmenler_degistir_pane.setVisible(false);
        yonetmenler_ekle_film_sayisi.setText("0");
    }

    @FXML
    private void yonetmenler_ekle(ActionEvent event) {
        if ((yonetmenler_ekle_ad.getText().length() == 0) || (yonetmenler_ekle_soyad.getText().length() == 0) || (yonetmenler_ekle_film_sayisi.getText().length() == 0)) {
            yonetmenler_ekle_uyari_mesaj.setText("Lütfen gerekli yerleri doldurunuz");
        } else {
            String ad = yonetmenler_ekle_ad.getText();
            String soyad = yonetmenler_ekle_soyad.getText();
            int film_sayisi = 0;
            int control = -1;
            try {
                film_sayisi = Integer.parseInt(yonetmenler_ekle_film_sayisi.getText());
                control = 1;
            } catch (NumberFormatException e) {
                yonetmenler_ekle_uyari_mesaj.setText("Lütfen film sayısını sadeca sayı olarak giriniz");
            }
            if (control != -1) {
                yonetmenler y = new yonetmenler(ad, soyad, film_sayisi);
                yonetmenlerDAO ydao = new yonetmenlerDAO();

                int sonuc = ydao.yonetmenler_dao_ekle(y);

                if (sonuc == 1) {
                    yonetmenler_ekle_uyari_mesaj.setText("İşlem Başarılı Bir şekilde gerçekleşti");
                } else {
                    yonetmenler_ekle_uyari_mesaj.setText("Bir hata meydana geldi lütfen daha sonra tekrar deneyiniz");
                }
            }
        }

    }

    @FXML
    private void yonetmenler_ekle_sifirla() {
        yonetmenler_ekle_ad.setText("");
        yonetmenler_ekle_soyad.setText("");
        yonetmenler_ekle_film_sayisi.setText("0");
    }

    @FXML
    private void yonetmenler_degistir_sil_giris(ActionEvent event) {
        yonetmenler_grid.setVisible(false);
        yonetmenler_degistir_pane.setVisible(true);
        yonetmen_combo(yonetmenler_degistir_sil_combo, yonetmenler_degistir_sil_uyari_mesaj_1);
        yonetmenler_degistir_pane_1.setVisible(true);
        yonetmenler_degistir_pane_2.setVisible(false);
        yonetmenler_degistir_geri_tusu.setVisible(true);
        yonetmenler_geri_tusu.setVisible(false);
        yonetmenler_degistir_sil_emin_misin.setVisible(false);

    }

    @FXML
    private void yonetmenler_degistir_sil_yonetmen_getir(ActionEvent event) {
        if (yonetmenler_degistir_sil_combo.getValue() == null) {
            yonetmenler_degistir_sil_uyari_mesaj_1.setText("Lütfen bir değer seçiniz");
        } else {

            String yonetmen = yonetmenler_degistir_sil_combo.getValue();
            yonetmenlerDAO ydao = new yonetmenlerDAO();

            String arr[][] = ydao.yonetmen_combo_doldur();

            int yonetmen_id = 0;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i][0].equals(yonetmen)) {
                    yonetmen_id = Integer.valueOf(arr[i][1]);
                    break;
                }
            }
            if (yonetmen_id != 0) {
                yonetmenler_degistir_pane_2.setVisible(true);
                yonetmenler_degistir_sil_ad.setText(ydao.yonetmenler_ad_getir(yonetmen_id));
                yonetmenler_degistir_sil_soyad.setText(ydao.yonetmenler_soyad_getir(yonetmen_id));
                yonetmenler_degistir_sil_film_sayisi.setText(String.valueOf(ydao.yonetmenler_film_sayisi_getir(yonetmen_id)));
                yonetmen_degistir_sil_yonetmen_id.setText(String.valueOf(yonetmen_id));
            } else {
                yonetmenler_degistir_sil_uyari_mesaj_1.setText("Bir hata meydana geldi. Lütfen daha sonra tekrar deneyiniz. ");
            }
        }

    }

    @FXML
    private void yonetmenler_degistir_sil_degistir(ActionEvent event) {
        if ((yonetmenler_degistir_sil_ad.getText().length() == 0) || (yonetmenler_degistir_sil_soyad.getText().length() == 0) || (yonetmenler_degistir_sil_film_sayisi.getText().length() == 0)) {
            yonetmenler_degistir_sil_uyari_mesaj_2.setText("Lütfen Gerekli Yerleri Doldurunuz. ");
        } else {
            try {
                int film_sayisi = Integer.parseInt(yonetmenler_degistir_sil_film_sayisi.getText());
                String ad = yonetmenler_degistir_sil_ad.getText();
                String soyad = yonetmenler_degistir_sil_soyad.getText();
                int yonetmen_id = Integer.parseInt(yonetmen_degistir_sil_yonetmen_id.getText());
                yonetmenlerDAO ydao = new yonetmenlerDAO();
                yonetmenler y = new yonetmenler(yonetmen_id, ad, soyad, film_sayisi);

                int sonuc = ydao.yonetmenler_dao_degistir(y);

                if (sonuc == 1) {
                    yonetmenler_degistir_sil_uyari_mesaj_2.setText("İşlem Başarılı bir şekilde gerçekleştirildi ");
                } else {
                    yonetmenler_degistir_sil_uyari_mesaj_2.setText("Bir hata meydana geldi lütfen daha sonra tekrar deneyiniz. ");
                }

            } catch (NumberFormatException e) {
                yonetmenler_degistir_sil_uyari_mesaj_2.setText("Lütfen Film sayısını sayı olarak giriniz.");
            }
        }
    }

    @FXML
    private void yonetmenler_degistir_sil_sil(ActionEvent event) {
        yonetmenler_degistir_sil_emin_misin.setVisible(true);
    }

    @FXML
    private void yonetmenler_degistir_sil_emin_misin_vazgec(ActionEvent event) {
        yonetmenler_degistir_sil_emin_misin.setVisible(false);
    }

    @FXML
    private void yonetmenler_degistir_sil_emin_misin_sil(ActionEvent event) {
        int yonetmen_id = Integer.parseInt(yonetmen_degistir_sil_yonetmen_id.getText());

        yonetmenlerDAO ydao = new yonetmenlerDAO();

        int sonuc = ydao.yonetmenler_dao_sil(yonetmen_id);

        yonetmenler_degistir_sil_emin_misin.setVisible(false);

        if (sonuc == 1) {
            yonetmenler_degistir_sil_ad.setText("");
            yonetmenler_degistir_sil_soyad.setText("");
            yonetmenler_degistir_sil_film_sayisi.setText("");

            yonetmen_combo(yonetmenler_degistir_sil_combo, yonetmenler_degistir_sil_uyari_mesaj_1);

            yonetmenler_degistir_pane_2.setVisible(false);

            yonetmenler_degistir_sil_uyari_mesaj_1.setText("İşlem Başarılı Bir şekilde gerçekleştirildi.");
        } else {
            yonetmenler_degistir_sil_uyari_mesaj_2.setText("Bir hata meydana geldi. Lütfen daha sonra tekrar deneyiniz.");
        }
    }

    @FXML
    private void yonetmenler_ekle_geri(MouseEvent event) {
        yonetmenler_grid.setVisible(true);
        yonetmenler_ekle_pane.setVisible(false);

        yonetmenler_geri_tusu.setVisible(true);
        yonetmenler_ekle_geri_tusu.setVisible(false);

        yonetmenler_table();
    }

    @FXML
    private void yonetmenler_geri(MouseEvent event) {
        geri_don();
    }

    @FXML
    private void yonetmenler_degistir_geri(MouseEvent event) {
        yonetmenler_grid.setVisible(true);
        yonetmenler_degistir_pane.setVisible(false);

        yonetmenler_degistir_geri_tusu.setVisible(false);
        yonetmenler_geri_tusu.setVisible(true);

        yonetmenler_table();

    }

    private void yonetmenler_table() {

        yonetmenlerDAO ydao = new yonetmenlerDAO();

        ObservableList<yonetmenler> data = FXCollections.observableArrayList();

        data = ydao.yonetmenler_select(data);

        yonetmenler_yonetmen_id.setCellValueFactory(new PropertyValueFactory("yonetmen_id"));
        yonetmenler_ad.setCellValueFactory(new PropertyValueFactory("yonetmen_name"));
        yonetmenler_soyad.setCellValueFactory(new PropertyValueFactory("yonetmen_sur_name"));
        yonetmenler_film_sayisi.setCellValueFactory(new PropertyValueFactory("film_sayisi"));

        FilteredList<yonetmenler> filteredData = new FilteredList<>(data, b -> true);

        filterField_yonetmenler.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(yon -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(yon.getYonetmen_id()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (yon.getYonetmen_name().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (yon.getYonetmen_sur_name().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(yon.getFilm_sayisi()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }

            });
        });

        SortedList<yonetmenler> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(table_yonetmenler.comparatorProperty());

        table_yonetmenler.setItems(sortedData);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /*
    Aktörler
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     */
    @FXML
    private Pane ust_pnl_aktorler, aktorler_ekle_pane, aktorler_sil_emin_misin;

    @FXML
    private AnchorPane pnl_aktorler;

    @FXML
    private TableColumn<actor, String> aktorler_aktor_id, aktorler_ad, aktorler_soyad;

    @FXML
    private TableColumn<actor, Button> aktorler_guncelle, aktorler_sil, aktorler_filmler;

    @FXML
    private TableView<actor> table_aktorler;

    @FXML
    private TextField filterField_aktorler, aktorler_ekle_ad, aktorler_ekle_soyad;

    @FXML
    private ComboBox<String> aktorler_degistir_sil_combo;

    @FXML
    private GridPane aktorler_grid;

    @FXML
    private Label aktorler_table_uyari_mesaj, aktorler_ekle_uyari_mesaj, aktorler_silmekten_emin_id;

    @FXML
    private FontAwesomeIconView aktorler_geri_tusu, aktorler_ekle_geri_tusu;

    @FXML
    private void aktorler_ekle_giris(ActionEvent event) {
        aktorler_grid.setVisible(false);
        aktorler_ekle_pane.setVisible(true);
        aktorler_geri_tusu.setVisible(false);
        aktorler_ekle_geri_tusu.setVisible(true);
    }

    @FXML
    private void aktorler_degistir_sil_emin_misin_vazgec(ActionEvent event) {
        aktorler_sil_emin_misin.setVisible(false);
    }

    @FXML
    private void aktorler_degistir_sil_emin_misin_sil(ActionEvent event) {
        int id = Integer.parseInt(aktorler_silmekten_emin_id.getText());
        actorDAO adao = new actorDAO();
        adao.aktorler_sil(id);
        aktorler_sil_emin_misin.setVisible(false);

        aktorler_table();

        aktorler_table_uyari_mesaj.setText("İşlem Başarılı bir şekilde gerçekleşti.");
    }

    @FXML
    private void aktorler_ekle_ekle(ActionEvent event) {
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
    private void aktorler_ekle_sifirla(ActionEvent event) {
        aktorler_ekle_ad.setText("");
        aktorler_ekle_soyad.setText("");
    }

    @FXML
    private void aktorler_geri(MouseEvent event) {
        geri_don();
    }

    @FXML
    private void aktorler_ekle_geri(MouseEvent event) {
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

    private ObservableList<actor> actor_data() {
        actorDAO adao = new actorDAO();
        ObservableList<actor> data = FXCollections.observableArrayList();
        data = adao.aktorler_select(data, aktorler_table_uyari_mesaj, aktorler_silmekten_emin_id, aktorler_sil_emin_misin, film_actor_table_actor_id, aktorler_grid, film_actor_grid, aktorler_geri_tusu, film_actor_geri_tusu, film_actor_film_name, film_actor_film_type, film_actor_yonetmen, film_actor_sil, table_film_actor, filterField_film_actor, film_actor_pane, film_actor_ekle_pane, film_actor_sil_emin_misin_pane, film_actor_id);
        return data;
    }

    private void aktorler_table_edit() {

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

    private void aktorler_table_filter(ObservableList<actor> data) {
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

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /*
    Film_Actor
     */
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    private Label film_actor_table_actor_id, film_actor_table_uyari_mesaj, film_actor_ekle_actor_id, film_actor_silmekten_emin_actor_id, film_actor_ekle_uyari_mesaj, film_actor_id;

    @FXML
    private GridPane film_actor_grid;

    @FXML
    private Pane film_actor_ekle_pane, film_actor_sil_emin_misin_pane, film_actor_pane;

    @FXML
    private TableColumn<film_actor, String> film_actor_film_name, film_actor_film_type, film_actor_yonetmen;

    @FXML
    private TableColumn<film_actor, Button> film_actor_sil;

    @FXML
    private TableView<film_actor> table_film_actor;

    @FXML
    private TextField filterField_film_actor;

    @FXML
    private ComboBox<String> film_actor_ekle_filmler;

    @FXML
    private FontAwesomeIconView film_actor_geri_tusu, film_actor_ekle_geri_tusu;

    @FXML
    private void film_actor_ekle_giris(ActionEvent event) {
        film_actor_ekle_pane.setVisible(true);
        film_actor_grid.setVisible(false);
        film_actor_sil_emin_misin_pane.setVisible(false);

        film_actor_geri_tusu.setVisible(false);
        film_actor_ekle_geri_tusu.setVisible(true);

        filmler_combo(film_actor_ekle_filmler, film_actor_ekle_uyari_mesaj);
        film_actor_ekle_actor_id.setText(film_actor_table_actor_id.getText());

    }

    @FXML
    private void film_actor_ekle_sifirla(ActionEvent event) {
        filmler_combo(film_actor_ekle_filmler, film_actor_ekle_uyari_mesaj);
    }

    @FXML
    private void film_actor_ekle_ekle(ActionEvent event) {
        if (film_actor_ekle_filmler.getValue() == null) {
            film_actor_ekle_uyari_mesaj.setText("Lütfen önce bir film seçiniz");
        } else {
            filmlerDAO film_islemleri = new filmlerDAO();
            String[][] arr = film_islemleri.filmler_combo_doldur();
            String secilen = film_actor_ekle_filmler.getValue();
            int film_id = -1;

            for (int i = 0; i < arr.length; i++) {
                if (arr[i][0].equals(secilen)) {
                    film_id = Integer.parseInt(arr[i][1]);
                    break;
                }
            }

            if (film_id == -1) {
                film_actor_ekle_uyari_mesaj.setText("Bir hata meydana geldi Lütfen daha sonra tekrar deneyiniz");
            } else {
                film_actorDAO fadao = new film_actorDAO();
                int actor_id = Integer.parseInt(film_actor_ekle_actor_id.getText());
                film_actor fa = new film_actor(film_id, actor_id);
                int sonuc = fadao.film_actor_insert(fa);
                if (sonuc == 1) {
                    film_actor_ekle_uyari_mesaj.setText("İşlem Başarılı Bir Şekilde Gerçekleştirildi");
                } else {
                    film_actor_ekle_uyari_mesaj.setText("Bir hata meydana geldi.");
                }
            }

        }
    }

    @FXML
    private void film_actor_sil_silmekten_emin_vazgec(ActionEvent event) {
        film_actor_sil_emin_misin_pane.setVisible(false);
    }

    @FXML
    private void film_actor_sil_silmekten_emin_sil(ActionEvent event) {
        film_actorDAO fdao = new film_actorDAO();
        fdao.film_actor_delete(Integer.parseInt(film_actor_id.getText()));

        actor a = new actor();
        a.film_actor_table(aktorler_grid, film_actor_grid, aktorler_geri_tusu, film_actor_geri_tusu, film_actor_film_name, film_actor_film_type, film_actor_yonetmen, film_actor_sil, table_film_actor, filterField_film_actor, film_actor_sil_emin_misin_pane, film_actor_id);

        film_actor_sil_emin_misin_pane.setVisible(false);

        film_actor_table_uyari_mesaj.setText("İşlem Başarılı Bir şekilde gerçekleştirildi.");

    }

    @FXML
    private void film_actor_geri(MouseEvent event) {
        aktorler_grid.setVisible(true);
        film_actor_pane.setVisible(false);
        film_actor_geri_tusu.setVisible(false);
        aktorler_geri_tusu.setVisible(true);

    }

    @FXML
    private void film_actor_ekle_geri(MouseEvent event) {
        film_actor_grid.setVisible(true);
        film_actor_ekle_pane.setVisible(false);

        actor a = new actor();
        a.film_actor_table(aktorler_grid, film_actor_grid, aktorler_geri_tusu, film_actor_geri_tusu, film_actor_film_name, film_actor_film_type, film_actor_yonetmen, film_actor_sil, table_film_actor, filterField_film_actor, film_actor_sil_emin_misin_pane, film_actor_id);

        film_actor_ekle_geri_tusu.setVisible(false);
        film_actor_geri_tusu.setVisible(true);

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /*
    Ortak Alan
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     */
    private boolean a = false;

    @FXML
    private Button btn1, btn2, btn3, btn4;

    @FXML
    private AnchorPane home_pane;

    @FXML
    private Label vizyondaki_film_sayisi, eski_film_sayisi, haber_sayisi, kampanya_sayisi, sinema_salonu_sayisi, yonetmen_sayisi, aktor_sayisi, kullanici_sayisi;

    @FXML
    private FontAwesomeIconView home;

    @FXML
    private void btn10(ActionEvent event) throws IOException, SQLException {

        if (btn1.getText().equals("Filmler")) {
            btn1.setText("Vizyondaki Filmler");
            btn2.setText("Eski Filmler");

            btn3.setVisible(false);
            btn4.setVisible(false);

            home.setVisible(true);
        } else if (btn1.getText().equals("Vizyondaki Filmler")) {

            pnl_vizyondaki_filmler.setVisible(true);
            pnl_eski_filmler.setVisible(false);
            pnl_sinema_salonlari.setVisible(false);
            pnl_seans.setVisible(false);
            home_pane.setVisible(false);
            pnl_settings.setVisible(false);

            vizyondaki_filmler_geri_tusu.setVisible(true);
            vizyondaki_filmler_ekle_geri_tusu.setVisible(false);
            vizyondaki_filmler_degistir_geri_tusu.setVisible(false);

            vizyondaki_filmler_grid.setVisible(true);
            vizyondaki_filmler_ekle_pane.setVisible(false);
            vizyondaki_filmler_degistir_pane.setVisible(false);

            vizyondaki_filmler_table();

        } else if (btn1.getText().equals("Kampanyalar")) {

            pnl_kampanyalar.setVisible(true);
            pnl_haberler.setVisible(false);
            pnl_sinema_salonlari.setVisible(false);
            home_pane.setVisible(false);
            pnl_settings.setVisible(false);

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
    private void btn20(ActionEvent event) throws IOException {
        if (btn2.getText().equals("Duyurular")) {

            btn1.setText("Kampanyalar");
            btn2.setText("Haberler");

            btn3.setVisible(false);
            btn4.setVisible(false);

            home.setVisible(true);
        } else if (btn2.getText().equals("Eski Filmler")) {

            pnl_eski_filmler.setVisible(true);
            pnl_vizyondaki_filmler.setVisible(false);
            pnl_sinema_salonlari.setVisible(false);
            pnl_seans.setVisible(false);
            home_pane.setVisible(false);
            pnl_settings.setVisible(false);

            eski_filmler_geri_tusu.setVisible(true);
            eski_filmler_ekle_geri_tusu.setVisible(false);
            eski_filmler_degistir_geri_tusu.setVisible(false);

            eski_filmler_grid.setVisible(true);
            eski_filmler_ekle_pane.setVisible(false);
            eski_filmler_degistir_pane.setVisible(false);

            eski_filmler_table();

        } else if (btn2.getText().equals("Haberler")) {

            pnl_haberler.setVisible(true);
            pnl_kampanyalar.setVisible(false);
            pnl_sinema_salonlari.setVisible(false);
            home_pane.setVisible(false);
            pnl_settings.setVisible(false);

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
    private void btn30(ActionEvent event) throws IOException {
        pnl_sinema_salonlari.setVisible(true);
        home_pane.setVisible(false);
        pnl_settings.setVisible(false);

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
    private void btn40(ActionEvent event) throws IOException {
        btn1.setText("Yönetmenler");
        btn2.setText("Aktörler");

        btn3.setVisible(false);
        btn4.setVisible(false);

        home.setVisible(true);
    }

    private void home_page() {
        vizyondaki_filmlerDAO vdao = new vizyondaki_filmlerDAO();
        vizyondaki_film_sayisi.setText(String.valueOf(vdao.kac_tane_vizyonda_film_var()));

        eski_filmlerDAO edao = new eski_filmlerDAO();
        eski_film_sayisi.setText(String.valueOf(edao.kac_tane_eski_film_var()));

        haberlerDAO hdao = new haberlerDAO();
        haber_sayisi.setText(String.valueOf(hdao.kac_tane_haber_var()));

        kampanyalarDAO kdao = new kampanyalarDAO();
        kampanya_sayisi.setText(String.valueOf(kdao.kac_tane_kampanya_var()));

        sinema_salonlariDAO sdao = new sinema_salonlariDAO();
        sinema_salonu_sayisi.setText(String.valueOf(sdao.kac_tane_salon_var()));

        yonetmenlerDAO ydao = new yonetmenlerDAO();
        yonetmen_sayisi.setText(String.valueOf(ydao.kac_tane_yonetmen_var()));

        actorDAO adao = new actorDAO();
        aktor_sayisi.setText(String.valueOf(adao.kac_tane_actor_var()));

        usersDAO udao = new usersDAO();
        kullanici_sayisi.setText(String.valueOf(udao.kac_tane_user_var()));

    }

    @FXML
    private void home_geri_gel(MouseEvent event) {
        geri_don();
    }

    private void geri_don() {
        btn1.setText("Filmler");
        btn2.setText("Duyurular");

        btn3.setVisible(true);
        btn4.setVisible(true);

        pnl_vizyondaki_filmler.setVisible(false);
        pnl_seans.setVisible(false);
        pnl_sinema_salonlari.setVisible(false);
        pnl_eski_filmler.setVisible(false);
        pnl_kampanyalar.setVisible(false);
        pnl_haberler.setVisible(false);
        pnl_yonetmenler.setVisible(false);
        pnl_aktorler.setVisible(false);

        home_pane.setVisible(true);
        home_page();

        home.setVisible(false);
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

        pnl_settings.setVisible(true);

        usersDAO udao = new usersDAO();
        int user_id = udao.bilgi_oku();

        this.user_name.setText(udao.user_name_getir(user_id));
        this.user_mail.setText(udao.user_mail_getir(user_id));
        this.user_password.setText(udao.user_password(user_id));

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        home_page();
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
