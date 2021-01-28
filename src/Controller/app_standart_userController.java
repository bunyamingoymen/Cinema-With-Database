package Controller;

import DAO.abonelerDAO;
import DAO.haberlerDAO;
import DAO.kampanyalarDAO;
import DAO.usersDAO;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entity.haberler;
import entity.kampanyalar;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class app_standart_userController extends Center implements Initializable {

    /*
    Bunun devamı nerede ve neden Center diye bir şeyi extends ediyor?
    
    Bunun sebebi appController ve app_stanadart_userController sınıflarının ortak noktalarını kalıtım aracılığıyla başka sınıfta tanımlamamızdır..
    
    Hem appController da hem de app_standart_userController da 7.000'den fazla ortak kod bulunmaktadır.
    Bu sebeple ikisinn ortak noktalarını bir sınıfta topladık ve ikisine de extends ettik.
     */
    //Bu metod bu sınıfa özgü olan pane'leri tanımlıyor (appController ya da başka yerde kullanılmayan)
    @FXML
    private AnchorPane pnl_abonelik_0, pnl_abonelik, pnl_abonelik_diger;

    @FXML
    private Pane abonelik_sahip_bir, abonelik_sahip_iki, abonelik_sahip_uc;

    @FXML
    private Label pnl_abonelik_uyari_mesaj;

    @FXML
    private FontAwesomeIconView sana_ozel_kampanyalar_geri_tusu, sana_ozel_haberler_geri_tusu;

    private int abonelik_turu_getir() {
        usersDAO udao = new usersDAO();
        int user_id = udao.bilgi_oku();

        abonelerDAO adao = new abonelerDAO();
        int abonelik = adao.abonelik_turu_bul(user_id);

        return abonelik;
    }

    @FXML
    private void vizyondaki_filmler_giris(ActionEvent event) {
        pnl_vizyondaki_filmler.setVisible(true);

        pnl_settings.setVisible(false);
        pnl_haberler.setVisible(false);
        pnl_kampanyalar.setVisible(false);
        pnl_abonelik.setVisible(false);

        vizyondaki_filmler_table();
    }

    @FXML
    private void vizyondaki_filmler_geri(MouseEvent event) {
        pnl_vizyondaki_filmler.setVisible(false);
    }

    @FXML
    private void settings_giris(MouseEvent event) {
        pnl_settings.setVisible(true);

        pnl_vizyondaki_filmler.setVisible(false);
        pnl_haberler.setVisible(false);
        pnl_kampanyalar.setVisible(false);
        pnl_abonelik.setVisible(false);

        usersDAO udao = new usersDAO();

        int user_id = udao.bilgi_oku();

        user_name.setText(udao.user_name_getir(user_id));

        user_mail.setText(udao.user_mail_getir(user_id));

        user_password.setText(udao.user_password(user_id));

    }

    private void haberler_table_butonsuz(int kullanici_turu) {
        haberlerDAO hdao = new haberlerDAO();

        ObservableList<haberler> data = FXCollections.observableArrayList();

        data = hdao.haberler_select(data, kullanici_turu);

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

    @FXML
    private void haberler_giris(ActionEvent event) {
        haberler_giris_ortak();

        haberler_geri_tusu.setVisible(true);
        sana_ozel_haberler_geri_tusu.setVisible(false);
    }

    private void haberler_giris_ortak() {
        pnl_haberler.setVisible(true);

        pnl_settings.setVisible(false);
        pnl_vizyondaki_filmler.setVisible(false);
        pnl_kampanyalar.setVisible(false);
        pnl_abonelik.setVisible(false);

        haberler_table_butonsuz(abonelik_turu_getir());
    }

    @FXML
    private void haberler_geri(MouseEvent event) {
        pnl_haberler.setVisible(false);
    }

    private void kampanyalar_table_butonsuz(int kullanici_turu) {
        kampanyalarDAO kdao = new kampanyalarDAO();

        ObservableList<kampanyalar> data = FXCollections.observableArrayList();

        data = kdao.kampanyalar_select(data, kullanici_turu);

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

    @FXML
    private void kampanyalar_giris(ActionEvent event) {
        kampanyalar_giris_ortak();

        kampanyalar_geri_tusu.setVisible(true);
        sana_ozel_kampanyalar_geri_tusu.setVisible(false);

    }

    private void kampanyalar_giris_ortak() {
        pnl_kampanyalar.setVisible(true);

        pnl_settings.setVisible(false);
        pnl_vizyondaki_filmler.setVisible(false);
        pnl_haberler.setVisible(false);
        pnl_abonelik.setVisible(false);

        kampanyalar_table_butonsuz(abonelik_turu_getir());
    }

    @FXML
    private void kampanyalar_geri(MouseEvent event) {
        pnl_kampanyalar.setVisible(false);
    }

    @FXML
    private void abonelik_giris(ActionEvent event) {
        abonelik_giris_ortak();
    }

    private void abonelik_giris_ortak() {
        pnl_abonelik.setVisible(true);

        pnl_kampanyalar.setVisible(false);
        pnl_settings.setVisible(false);
        pnl_vizyondaki_filmler.setVisible(false);
        pnl_haberler.setVisible(false);

        abonelik_sahip_bir.setVisible(false);
        abonelik_sahip_iki.setVisible(false);
        abonelik_sahip_uc.setVisible(false);

        int abonelik_turu = abonelik_turu_getir();

        switch (abonelik_turu) {
            case 0:
                pnl_abonelik_0.setVisible(true);

                pnl_abonelik_diger.setVisible(false);
                pnl_abonelik_uyari_mesaj.setVisible(false);
                break;
            case -1:
                pnl_abonelik_uyari_mesaj.setVisible(true);

                pnl_abonelik_0.setVisible(false);
                pnl_abonelik_diger.setVisible(false);

                pnl_abonelik_uyari_mesaj.setText("Bir hata meydana geldi (Hata Kodu -8)");
                break;
            default:
                pnl_abonelik_diger.setVisible(true);

                pnl_abonelik_0.setVisible(false);
                pnl_abonelik_uyari_mesaj.setVisible(false);
                break;
        }
    }

    @FXML
    private void abonelik_bir_satin_al(ActionEvent event) {
        usersDAO udao = new usersDAO();
        int user_id = udao.bilgi_oku();
        int abonelik_turu = 1;

        abonelerDAO adao = new abonelerDAO();

        int sonuc = adao.abonelik_turu_satin_al(abonelik_turu, user_id);

        if (sonuc == 1) {
            pnl_abonelik_0.setVisible(false);
            pnl_abonelik_uyari_mesaj.setVisible(true);

            pnl_abonelik_uyari_mesaj.setText("İşlem Başarılı Bir Şekilde Gerçekleştirildi");
        } else {
            pnl_abonelik_0.setVisible(false);
            pnl_abonelik_uyari_mesaj.setVisible(true);

            pnl_abonelik_uyari_mesaj.setText("Bir hata meydana geldi. (Hata Kodu: -9)");
        }

    }

    @FXML
    private void abonelik_iki_satin_al(ActionEvent event) {
        usersDAO udao = new usersDAO();
        int user_id = udao.bilgi_oku();
        int abonelik_turu = 2;

        abonelerDAO adao = new abonelerDAO();

        int sonuc = adao.abonelik_turu_satin_al(abonelik_turu, user_id);

        if (sonuc == 1) {
            pnl_abonelik_0.setVisible(false);
            pnl_abonelik_uyari_mesaj.setVisible(true);

            pnl_abonelik_uyari_mesaj.setText("İşlem Başarılı Bir Şekilde Gerçekleştirildi");
        } else {
            pnl_abonelik_0.setVisible(false);
            pnl_abonelik_uyari_mesaj.setVisible(true);

            pnl_abonelik_uyari_mesaj.setText("Bir hata meydana geldi. (Hata Kodu: -10)");
        }
    }

    @FXML
    private void abonelik_uc_satin_al(ActionEvent event) {
        usersDAO udao = new usersDAO();
        int user_id = udao.bilgi_oku();
        int abonelik_turu = 3;

        abonelerDAO adao = new abonelerDAO();

        int sonuc = adao.abonelik_turu_satin_al(abonelik_turu, user_id);

        if (sonuc == 1) {
            pnl_abonelik_0.setVisible(false);
            pnl_abonelik_uyari_mesaj.setVisible(true);

            pnl_abonelik_uyari_mesaj.setText("İşlem Başarılı Bir Şekilde Gerçekleştirildi");
        } else {
            pnl_abonelik_0.setVisible(false);
            pnl_abonelik_uyari_mesaj.setVisible(true);

            pnl_abonelik_uyari_mesaj.setText("Bir hata meydana geldi. (Hata Kodu: -11)");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void abonelik_degistir(ActionEvent event) {
        pnl_abonelik_0.setVisible(true);

        pnl_abonelik_diger.setVisible(false);
        pnl_abonelik_uyari_mesaj.setVisible(false);

        int abonelik_turu = abonelik_turu_getir();

        switch (abonelik_turu) {
            case 0:
                abonelik_sahip_bir.setVisible(false);
                abonelik_sahip_iki.setVisible(false);
                abonelik_sahip_uc.setVisible(false);
                break;
            case 1:
                abonelik_sahip_bir.setVisible(true);

                abonelik_sahip_iki.setVisible(false);
                abonelik_sahip_uc.setVisible(false);
                break;
            case 2:
                abonelik_sahip_iki.setVisible(true);

                abonelik_sahip_bir.setVisible(false);
                abonelik_sahip_uc.setVisible(false);
                break;
            case 3:
                abonelik_sahip_uc.setVisible(true);

                abonelik_sahip_bir.setVisible(false);
                abonelik_sahip_iki.setVisible(false);
                break;
            default:
                pnl_abonelik_uyari_mesaj.setVisible(true);

                pnl_abonelik_diger.setVisible(false);
                pnl_abonelik_0.setVisible(false);

                pnl_abonelik_uyari_mesaj.setText("Bir hata Meydana geldi (Hata Kodu: -15)");
                break;
        }
    }

    @FXML
    private void sana_ozel_kampanyalar_giris(ActionEvent event) {
        kampanyalar_giris_ortak();

        kampanyalar_geri_tusu.setVisible(false);
        sana_ozel_kampanyalar_geri_tusu.setVisible(true);

    }

    @FXML
    private void sana_ozel_kampanyalar_geri(MouseEvent event) {
        abonelik_giris_ortak();
    }

    @FXML
    private void sana_ozel_haberler_giris(ActionEvent event) {
        haberler_giris_ortak();
        
        haberler_geri_tusu.setVisible(false);
        sana_ozel_haberler_geri_tusu.setVisible(true);
    }
    
    @FXML
    private void sana_ozel_haberler_geri(MouseEvent event){
        abonelik_giris_ortak();
    }

}
