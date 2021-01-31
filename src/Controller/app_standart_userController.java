package Controller;

import DAO.abonelerDAO;
import DAO.eski_filmlerDAO;
import DAO.haberlerDAO;
import DAO.kampanyalarDAO;
import DAO.satin_alinan_biletlerDAO;
import DAO.seansDAO;
import DAO.sinema_salonlariDAO;
import DAO.usersDAO;
import DAO.yesil_olanDAO;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entity.eski_filmler;
import entity.haberler;
import entity.kampanyalar;
import entity.satin_alinan_biletler;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    private AnchorPane pnl_abonelik_0, pnl_abonelik, pnl_abonelik_diger, pnl_biletlerim;
    
    @FXML
    private Pane abonelik_sahip_bir, abonelik_sahip_iki, abonelik_sahip_uc;
    
    @FXML
    private Label pnl_abonelik_uyari_mesaj;
    
    @FXML
    private FontAwesomeIconView sana_ozel_kampanyalar_geri_tusu, sana_ozel_haberler_geri_tusu, biletlerim_geri_tusu;
    
    @FXML
    private TableView<satin_alinan_biletler> table_biletlerim;
    
    @FXML
    private TableColumn<satin_alinan_biletler, String> biletlerim_film_adi, biletlerim_salon_adi, biletlerim_yonetmen, biletlerim_saat, biletlerim_koltuk;
    
    @FXML
    private TextField filterField_biletlerim;
    
    @FXML
    private Pane satin_al_filmler_pane, satin_al_filmler_seans_pane, satin_al_koltuk_ust_pane, satin_al_odeme_yontemi_pane;
    
    @FXML
    private ComboBox<String> satin_al_filmler_como, satin_al_filmler_seans_combo;
    
    @FXML
    private Label satin_al_filmler_uyari_mesaj, satin_al_koltuk_film_adi, satin_al_koltuk_seans_saati, satin_al_koltuk_salon_adi, satin_al_koltuk_seans_id, satin_al_kalan_ucretsiz_bilet_sayisi_hakki, satin_al_odeme_yontemi_uyari_mesaj, satin_al_uyari_mesaj;
    
    private int abonelik_turu_getir() {
        abonelerDAO adao = new abonelerDAO();
        int abonelik = adao.abonelik_turu_bul(user_id_getir());
        
        return abonelik;
    }
    
    private int user_id_getir() {
        usersDAO udao = new usersDAO();
        int user_id = udao.bilgi_oku();
        
        return user_id;
    }
    
    @FXML
    private void vizyondaki_filmler_giris(ActionEvent event) {
        pnl_vizyondaki_filmler.setVisible(true);
        
        pnl_settings.setVisible(false);
        pnl_haberler.setVisible(false);
        pnl_kampanyalar.setVisible(false);
        pnl_abonelik.setVisible(false);
        pnl_biletlerim.setVisible(false);
        
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
        pnl_biletlerim.setVisible(false);
        
        usersDAO udao = new usersDAO();
        
        int user_id = user_id_getir();
        
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
        pnl_biletlerim.setVisible(false);
        
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
        pnl_biletlerim.setVisible(false);
        
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
        pnl_biletlerim.setVisible(false);
        
        abonelik_sahip_bir.setVisible(false);
        abonelik_sahip_iki.setVisible(false);
        abonelik_sahip_uc.setVisible(false);
        pnl_eski_filmler.setVisible(false);
        
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
        int user_id = user_id_getir();
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
        int user_id = user_id_getir();
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
        int user_id = user_id_getir();
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
    private void sana_ozel_haberler_geri(MouseEvent event) {
        abonelik_giris_ortak();
    }
    
    @FXML
    private void abonelik_iptal_et(ActionEvent event) {
        int user_id = user_id_getir();
        
        abonelerDAO adao = new abonelerDAO();
        
        int sonuc = adao.aboneler_sil(user_id);
        
        pnl_abonelik_0.setVisible(false);
        pnl_abonelik_diger.setVisible(false);
        pnl_abonelik_uyari_mesaj.setVisible(true);
        
        if (sonuc == 1) {
            pnl_abonelik_uyari_mesaj.setText("İşlem Başarılı Bir şekilde gerçekleştirildi.");
        } else {
            pnl_abonelik_uyari_mesaj.setText("Bir hata meydana geldi. (Hata kodu: -16)");
        }
    }
    
    private void eski_filmler_table_abonesiz(int kullanici_turu) {
        eski_filmlerDAO edao = new eski_filmlerDAO();
        
        ObservableList<eski_filmler> data = FXCollections.observableArrayList();
        
        data = edao.eski_filmler_select_abone_ozel(data, kullanici_turu);
        
        eski_filmler_film_adi.setCellValueFactory(new PropertyValueFactory("film_name"));
        eski_filmler_film_type.setCellValueFactory(new PropertyValueFactory("film_type"));
        eski_filmler_film_suresi.setCellValueFactory(new PropertyValueFactory("film_suresi"));
        eski_filmler_yonetmen.setCellValueFactory(new PropertyValueFactory("yonetmen_ad_soyad"));
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
    
    @FXML
    private void eski_filmler_giris() {
        pnl_eski_filmler.setVisible(true);
        
        pnl_abonelik_0.setVisible(false);
        pnl_abonelik_diger.setVisible(false);
        pnl_abonelik_uyari_mesaj.setVisible(false);
        
        int kullanici_turu = abonelik_turu_getir();
        
        eski_filmler_table_abonesiz(kullanici_turu);
    }
    
    @FXML
    private void eski_filmler_geri(MouseEvent event) {
        pnl_abonelik_diger.setVisible(true);
        
        pnl_eski_filmler.setVisible(false);
    }
    
    @FXML
    private void biletlerim_giris(ActionEvent event) {
        pnl_biletlerim.setVisible(true);
        
        pnl_settings.setVisible(false);
        pnl_vizyondaki_filmler.setVisible(false);
        pnl_haberler.setVisible(false);
        pnl_abonelik.setVisible(false);
        pnl_kampanyalar.setVisible(false);
        
        biletlerim_table(user_id_getir());
    }
    
    private void biletlerim_table(int user_id) {
        satin_alinan_biletlerDAO edao = new satin_alinan_biletlerDAO();
        
        ObservableList<satin_alinan_biletler> data = FXCollections.observableArrayList();
        
        data = edao.satin_alinan_biletler_kullanicinin_biletlerini_goster(data, user_id);
        
        biletlerim_film_adi.setCellValueFactory(new PropertyValueFactory("film_name"));
        biletlerim_salon_adi.setCellValueFactory(new PropertyValueFactory("salon_name"));
        biletlerim_yonetmen.setCellValueFactory(new PropertyValueFactory("yonetmen_ad_soyad"));
        biletlerim_saat.setCellValueFactory(new PropertyValueFactory("saat"));
        biletlerim_koltuk.setCellValueFactory(new PropertyValueFactory("koltuk_name"));
        
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
    private void biletlerim_geri(MouseEvent event) {
        pnl_biletlerim.setVisible(false);
    }
    
    @FXML
    private void bilet_satin_al_giris(ActionEvent event) {
        pnl_sinema_salonlari.setVisible(true);
        
        pnl_vizyondaki_filmler.setVisible(false);
        pnl_abonelik.setVisible(false);
        pnl_haberler.setVisible(false);
        pnl_kampanyalar.setVisible(false);
        pnl_biletlerim.setVisible(false);
        pnl_settings.setVisible(false);
        
        salon_bir_pane.setVisible(false);
        salon_iki_pane.setVisible(false);
        salon_uc_pane.setVisible(false);
        salon_dort_pane.setVisible(false);
        satin_al_koltuk_ust_pane.setVisible(false);
        satin_al_odeme_yontemi_pane.setVisible(false);
        satin_al_uyari_mesaj.setVisible(false);
        
        satin_al_filmler_pane.setVisible(true);
        
        satin_al_filmler_seans_pane.setVisible(false);
        
        vizyondaki_filmler_combo(satin_al_filmler_como, satin_al_filmler_uyari_mesaj);
    }
    
    private int seans_combo(ComboBox<String> combo, int vizyondaki_filmler) {
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
    
    @FXML
    private void satin_al_filmler_filmi_getir(ActionEvent event) {
        if (satin_al_filmler_como.getValue() == null) {
            satin_al_filmler_uyari_mesaj.setText("Lütfen Bir film seçiniz");
        } else {
            String[] ab = satin_al_filmler_como.getValue().split(" | ");
            int vizyondaki_film_id = Integer.parseInt(ab[0]);
            int sonuc = seans_combo(satin_al_filmler_seans_combo, vizyondaki_film_id);
            if (sonuc == 0) {
                satin_al_filmler_uyari_mesaj.setText("Bu filme ait herhangi bir seans tanımlı değil. Lütfen farklı bir film seçiniz.");
                satin_al_filmler_seans_pane.setVisible(false);
            } else {
                satin_al_filmler_seans_pane.setVisible(true);
                satin_al_filmler_uyari_mesaj.setText("");
            }
        }
    }
    
    @FXML
    private void satin_al_filmler_koltuk_sec(ActionEvent event) {
        if (satin_al_filmler_seans_combo.getValue() == null) {
            satin_al_filmler_uyari_mesaj.setText("Lütfen Bir Film Seçiniz");
        } else {
            String[] ab = satin_al_filmler_seans_combo.getValue().split(" | ");
            
            int seans_id = Integer.parseInt(ab[0]);
            
            seansDAO sdao = new seansDAO();
            int salon_id = sdao.salon_id_getir(seans_id);
            
            sinema_salonlariDAO sinema_dao = new sinema_salonlariDAO();
            int koltuk_sayisi = sinema_dao.koltuk_sayisi_getir(salon_id);
            
            String film_adi = sdao.film_adi_getir(seans_id);
            String salon_adi = sdao.salon_adi_getir(seans_id);
            String saat = sdao.saat_getir(seans_id);
            
            satin_al_koltuk_film_adi.setText(film_adi);
            satin_al_koltuk_salon_adi.setText(salon_adi);
            satin_al_koltuk_seans_saati.setText(saat);
            satin_al_koltuk_seans_id.setText(String.valueOf(seans_id));
            
            satin_al_koltuk_ust_pane.setVisible(true);
            
            satin_al_filmler_pane.setVisible(false);
            
            yesil_olanDAO ydao = new yesil_olanDAO();
            ydao.yesil_olan_dao_toplu_sil();
            
            switch (koltuk_sayisi) {
                case 129:
                    salon_bir_pane.setVisible(true);
                    
                    salon_iki_pane.setVisible(false);
                    salon_uc_pane.setVisible(false);
                    salon_dort_pane.setVisible(false);
                    
                    bir_koltuk_dolu(seans_id);
                    break;
                case 177:
                    salon_iki_pane.setVisible(true);
                    
                    salon_bir_pane.setVisible(false);
                    salon_uc_pane.setVisible(false);
                    salon_dort_pane.setVisible(false);
                    
                    iki_koltuk_dolu(seans_id);
                    break;
                case 265:
                    salon_uc_pane.setVisible(true);
                    
                    salon_bir_pane.setVisible(false);
                    salon_iki_pane.setVisible(false);
                    salon_dort_pane.setVisible(false);
                    
                    uc_koltuk_dolu(seans_id);
                    break;
                case 294:
                    salon_dort_pane.setVisible(true);
                    
                    salon_bir_pane.setVisible(false);
                    salon_iki_pane.setVisible(false);
                    salon_uc_pane.setVisible(false);
                    
                    dort_koltuk_dolu(seans_id);
                    break;
                default:
                    satin_al_uyari_mesaj.setVisible(true);
                    
                    salon_bir_pane.setVisible(false);
                    salon_iki_pane.setVisible(false);
                    salon_uc_pane.setVisible(false);
                    salon_dort_pane.setVisible(false);
                    satin_al_koltuk_ust_pane.setVisible(false);
                    satin_al_odeme_yontemi_pane.setVisible(false);
                    
                    satin_al_uyari_mesaj.setText("Bir hata meydana geldi (Hata kodu: -17)");
                    break;
            }
            
        }
    }
    
    @FXML
    private void odeme_yontemi_giris(ActionEvent event) {
        satin_al_odeme_yontemi_pane.setVisible(true);
        
        satin_al_koltuk_ust_pane.setVisible(false);
        satin_al_filmler_pane.setVisible(false);
        salon_bir_pane.setVisible(false);
        salon_iki_pane.setVisible(false);
        salon_uc_pane.setVisible(false);
        salon_dort_pane.setVisible(false);
        
        int user_id = user_id_getir();
        
        abonelerDAO adao = new abonelerDAO();
        int kalan_ucretsiz_bilet_sayisi = adao.kalan_ucretsiz_bilet_sayisi(user_id);
        
        if (kalan_ucretsiz_bilet_sayisi == -1) {
            
            satin_al_odeme_yontemi_pane.setVisible(false);
            satin_al_uyari_mesaj.setVisible(true);
            satin_al_uyari_mesaj.setText("Bir hata meydana geldi(Hata Kodu: -18)");
        } else {
            satin_al_kalan_ucretsiz_bilet_sayisi_hakki.setText(String.valueOf(kalan_ucretsiz_bilet_sayisi));
        }
        
    }
    
    @FXML
    private void satin_al_havale_eft(ActionEvent event) {
        satin_al_uyari_mesaj.setVisible(true);
        
        satin_al_odeme_yontemi_pane.setVisible(false);
        
        satin_alinan_biletlerDAO sdao = new satin_alinan_biletlerDAO();
        
        int user_id = user_id_getir();
        int seans_id = Integer.parseInt(satin_al_koltuk_seans_id.getText());
        
        int sonuc = sdao.satin_alinan_bilet_satin_al(user_id, seans_id);
        
        if(sonuc == 1){
            satin_al_uyari_mesaj.setText("İşlem Başarılı Bir Şekilde Gerçekleşti");
        }
        else{
            satin_al_uyari_mesaj.setText("Bir hata Meydana Geldi (Hata Kodu: -19)");
        }
    }
    
    @FXML
    private void satin_al_kredi_banka_karti(ActionEvent event) {
        
    }
    
    @FXML
    private void satin_al_ucretsiz_bilet_hakki(ActionEvent event) {
        
    }
}
