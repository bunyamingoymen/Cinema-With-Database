package Code_Standart_user;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entity.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class Variables {

    public boolean sifre_gosterim = false;

    public boolean a = false;

    public boolean a2 = false;

    @FXML
    public TextField user_name, user_mail;

    @FXML
    public Pane settings_pane;

    @FXML
    public TextField tf_user_password;

    @FXML
    public PasswordField user_password;

    @FXML
    public Pane gizli, acik;

    @FXML
    public Label guncelle_mesaj;

    @FXML
    public AnchorPane pnl_settings;

    @FXML
    public TextField filterField;

    @FXML
    public TableView<vizyondaki_filmler> table_vizyondaki_filmler;

    @FXML
    public TableColumn<vizyondaki_filmler, String> vizyondaki_filmler_film_adi, vizyondaki_filmler_film_type, vizyondaki_filmler_film_suresi, vizyondaki_filmler_yonetmen, vizyondaki_filmler_kullanici_puani;

    @FXML
    public TableColumn<vizyondaki_filmler, DatePicker> vizyondaki_filmler_kalkis;

    @FXML
    public TableColumn<vizyondaki_filmler, Button> vizyondaki_filmler_detay;

    @FXML
    public AnchorPane pnl_vizyondaki_filmler;

    @FXML
    public Pane salon_bir_pane, salon_iki_pane, salon_uc_pane, salon_dort_pane;

    @FXML
    public AnchorPane pnl_sinema_salonlari;

    @FXML
    public AnchorPane pnl_seans;

    @FXML
    public Pane ust_pnl_seans;

    @FXML
    public FontAwesomeIconView seans_geri_tusu;

    @FXML
    public AnchorPane pnl_eski_filmler;

    @FXML
    public TableView<eski_filmler> table_eski_filmler;

    @FXML
    public TextField filterField_eski;

    @FXML
    public TableColumn<eski_filmler, String> eski_filmler_film_adi, eski_filmler_film_type, eski_filmler_film_suresi, eski_filmler_yonetmen, eski_filmler_aldigi_odul_sayisi, eski_filmler_kullanici_puani;

    @FXML
    public TableColumn<eski_filmler, Button> eski_filmler_detay;

    @FXML
    public TextField filterField_haberler;

    @FXML
    public TableColumn<haberler, String> haberler_title, haberler_haber, haberler_tarih, haberler_haber_kategorisi, haberler_hangi_kullanıcı;

    @FXML
    public TableView<haberler> table_haberler;

    @FXML
    public AnchorPane pnl_haberler;

    @FXML
    public FontAwesomeIconView haberler_geri_tusu;

    @FXML
    public TableView<kampanyalar> table_kampanyalar;

    @FXML
    public TableColumn<kampanyalar, String> kampanyalar_title, kampanyalar_kampanya, kampanyalar_tarih, kampanyalar_kampanya_kategorisi;

    @FXML
    public TextField filterField_kampanyalar;

    @FXML
    public AnchorPane pnl_kampanyalar;

    @FXML
    public FontAwesomeIconView kampanyalar_geri_tusu;

    @FXML
    public TextField kullanici_islemleri_user_name, kullanici_islemleri_user_mail, filterField_users, kullanici_islemleri_acik_pf;

    @FXML
    public PasswordField kullanici_islemleri_gizli_pf;

    @FXML
    public Button btn1, btn2, btn3, btn4, btn5;

    @FXML
    public AnchorPane home_pane;

    @FXML
    public FontAwesomeIconView home;
    
    @FXML
    public Label vizyondaki_filmler_detay_film_id, vizyondaki_filmler_detay_film_adi, vizyondaki_filmler_detay_film_turu, vizyondaki_filmler_detay_film_suresi, vizyondaki_filmler_detay_yonetmen, vizyondaki_filmler_detay_kalkis_tarihi, vizyondaki_filmler_detay_kullanici_puani;

 
    @FXML
    public TableColumn<eski_filmler, String> eski_filmler_hangi_abone;

    @FXML
    public FontAwesomeIconView eski_filmler_geri_tusu;

    @FXML
    public AnchorPane pnl_abonelik_0, pnl_abonelik, pnl_abonelik_diger, pnl_biletlerim, home_page;

    @FXML
    public Pane abonelik_sahip_bir, abonelik_sahip_iki, abonelik_sahip_uc;

    @FXML
    public Label pnl_abonelik_uyari_mesaj, biletlerim_uyari_mesaj;

    @FXML
    public FontAwesomeIconView sana_ozel_kampanyalar_geri_tusu, sana_ozel_haberler_geri_tusu, biletlerim_geri_tusu, home_page_icon;

    @FXML
    public TableView<satin_alinan_biletler> table_biletlerim;

    @FXML
    public TableColumn<satin_alinan_biletler, String> biletlerim_film_adi, biletlerim_salon_adi, biletlerim_yonetmen, biletlerim_saat, biletlerim_koltuk;

    @FXML
    public TableColumn<satin_alinan_biletler, Button> biletlerim_sil;

    @FXML
    public TextField filterField_biletlerim;

    @FXML
    public Pane satin_al_filmler_pane, satin_al_filmler_seans_pane, satin_al_koltuk_ust_pane, satin_al_odeme_yontemi_pane;

    @FXML
    public ComboBox<String> satin_al_filmler_como, satin_al_filmler_seans_combo;

    @FXML
    public Label satin_al_filmler_uyari_mesaj, satin_al_koltuk_film_adi, satin_al_koltuk_seans_saati, satin_al_koltuk_salon_adi, satin_al_koltuk_seans_id, satin_al_kalan_ucretsiz_bilet_sayisi_hakki, satin_al_odeme_yontemi_uyari_mesaj, satin_al_uyari_mesaj;

    @FXML
    public AnchorPane pnl_film_detay;

    @FXML
    public Label film_detay_hangi_abone_turu, film_detay_aldigi_odul_sayisi, film_detay_kalksi_tarihi_oncesi, film_detay_aldigi_odul_sayisi_oncesi, film_detay_hangi_abone_turu_oncesi, film_detay_id, film_detay_id_oncesi;

    @FXML
    public Label film_detay_film_id, film_detay_film_adi, film_detay_film_turu, film_detay_film_suresi, film_detay_yonetmen, film_detay_kullanici_puani, film_detay_verilen_puan, film_detay_kalkis_tarihi;

    @FXML
    public ImageView bir_bos, iki_bos, uc_bos, dort_bos, bes_bos, alti_bos, yedi_bos, sekiz_bos, dokuz_bos, on_bos, bir_dolu, iki_dolu, uc_dolu, dort_dolu, bes_dolu, alti_dolu, yedi_dolu, sekiz_dolu, dokuz_dolu, on_dolu;

    @FXML
    public Pane kullanici_degerlendirme_pane;

    @FXML
    public Label film_detay_uyari_mesaj;

    @FXML
    public ImageView profile_photo;

}
