package Code_Admin;

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
    public Pane home_page, settings_pane;

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
    public Pane ust_pnl_seans, seans_ekle_pane, seans_degistir_sil_pane_1, seans_degistir_sil_pane_2, seans_degistir_sil_pane, seans_degistir_sil_silmekten_emin_pane;

    @FXML
    public FontAwesomeIconView seans_geri_tusu, seans_ekle_geri_tusu, seans_degistir_geri_tusu;

    @FXML
    public TextField filterField_seans;

    @FXML
    public ComboBox<String> seans_ekle_film_combo, seans_ekle_salon_combo, seans_ekle_saat_combo, seans_degistir_seans_getir_combo, seans_degistir_sil_film_combo, seans_degistir_sil_salon_combo, seans_degistir_sil_saat_combo;

    @FXML
    public Label seans_degistir_sil_uyari_mesaj_1, seans_degistir_sil_uyari_mesaj_2, seans_ekle_uyari_mesaj, seans_degistir_sil_seans_id;

    @FXML
    public GridPane seans_grid;

    @FXML
    public TableView<seans> table_seans;

    @FXML
    public TableColumn<seans, String> seans_film_adi, seans_salon_adi, seans_saat;

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
    public Pane ust_pnl_yonetmenler, yonetmenler_ekle_pane, yonetmenler_degistir_pane, yonetmenler_degistir_pane_1, yonetmenler_degistir_pane_2, yonetmenler_degistir_sil_emin_misin;

    @FXML
    public AnchorPane pnl_yonetmenler;

    @FXML
    public TableColumn<yonetmenler, String> yonetmenler_yonetmen_id, yonetmenler_ad, yonetmenler_soyad, yonetmenler_film_sayisi;

    @FXML
    public TableView<yonetmenler> table_yonetmenler;

    @FXML
    public TextField filterField_yonetmenler, yonetmenler_ekle_ad, yonetmenler_ekle_soyad, yonetmenler_ekle_film_sayisi, yonetmenler_degistir_sil_ad, yonetmenler_degistir_sil_soyad, yonetmenler_degistir_sil_film_sayisi;

    @FXML
    public ComboBox<String> yonetmenler_degistir_sil_combo;

    @FXML
    public GridPane yonetmenler_grid;

    @FXML
    public Label yonetmenler_degistir_sil_uyari_mesaj_1, yonetmenler_ekle_uyari_mesaj, yonetmenler_degistir_sil_uyari_mesaj_2, yonetmen_degistir_sil_yonetmen_id;

    @FXML
    public FontAwesomeIconView yonetmenler_geri_tusu, yonetmenler_ekle_geri_tusu, yonetmenler_degistir_geri_tusu;

    @FXML
    public Pane ust_pnl_aktorler, aktorler_ekle_pane, aktorler_sil_emin_misin;

    @FXML
    public AnchorPane pnl_aktorler;

    @FXML
    public TableColumn<actor, String> aktorler_aktor_id, aktorler_ad, aktorler_soyad;

    @FXML
    public TableColumn<actor, Button> aktorler_guncelle, aktorler_sil, aktorler_filmler;

    @FXML
    public TableView<actor> table_aktorler;

    @FXML
    public TextField filterField_aktorler, aktorler_ekle_ad, aktorler_ekle_soyad;

    @FXML
    public ComboBox<String> aktorler_degistir_sil_combo;

    @FXML
    public GridPane aktorler_grid;

    @FXML
    public Label aktorler_table_uyari_mesaj, aktorler_ekle_uyari_mesaj, aktorler_silmekten_emin_id;

    @FXML
    public FontAwesomeIconView aktorler_geri_tusu, aktorler_ekle_geri_tusu;

    @FXML
    public AnchorPane pnl_kullanici_islemleri;

    @FXML
    public Pane kullanici_islemleri_table_pane, kullanici_islemleri_yonet_pane, kullanici_islemleri_gizli_pane, kullanici_islemleri_acik_pane, kullanici_islemleri_silmekten_emin_misin_pane;

    @FXML
    public Label kullanici_islemleri_user_id, kullanici_islemleri_yonet_uyari_pane;

    @FXML
    public TextField kullanici_islemleri_user_name, kullanici_islemleri_user_mail, filterField_users, kullanici_islemleri_acik_pf;

    @FXML
    public ComboBox<String> kullanici_islemleri_user_turu, kullanici_islemleri_abone_turu;

    @FXML
    public TableView<users> table_users;

    @FXML
    public TableColumn<users, String> users_user_id, users_user_name, users_user_mail, users_user_password, users_user_type, users_abone_turu;

    @FXML
    public TableColumn<users, Button> users_yonet;

    @FXML
    public PasswordField kullanici_islemleri_gizli_pf;

    @FXML
    public Button btn1, btn2, btn3, btn4, btn5;

    @FXML
    public AnchorPane home_pane;

    @FXML
    public Label vizyondaki_film_sayisi, eski_film_sayisi, haber_sayisi, kampanya_sayisi, sinema_salonu_sayisi, yonetmen_sayisi, aktor_sayisi, kullanici_sayisi;

    @FXML
    public FontAwesomeIconView home;

    @FXML
    public Label film_actor_table_actor_id, film_actor_table_uyari_mesaj, film_actor_ekle_actor_id, film_actor_silmekten_emin_actor_id, film_actor_ekle_uyari_mesaj, film_actor_id;

    @FXML
    public GridPane film_actor_grid;

    @FXML
    public Pane film_actor_ekle_pane, film_actor_sil_emin_misin_pane, film_actor_pane;

    @FXML
    public TableColumn<film_actor, String> film_actor_film_name, film_actor_film_type, film_actor_yonetmen;

    @FXML
    public TableColumn<film_actor, Button> film_actor_sil;

    @FXML
    public TableView<film_actor> table_film_actor;

    @FXML
    public TextField filterField_film_actor;

    @FXML
    public ComboBox<String> film_actor_ekle_filmler;

    @FXML
    public FontAwesomeIconView film_actor_geri_tusu, film_actor_ekle_geri_tusu;

    @FXML
    public Pane ust_pnl_vizyondaki_filmler, vizyondaki_filmler_ekle_pane, /*vizyondaki_filmler_degistir_pane,*/ /*vizyondaki_filmler_degistir_pane_1,*/ /*vizyondaki_filmler_degistir_pane_2,*/ /*vizyondaki_filmler_degistir_sil_emin_misin,*/ vizyondaki_filmler_resimli_gosterim;

    @FXML
    public TextField vizyondaki_filmler_ekle_film_adi, vizyondaki_filmler_ekle_film_suresi, vizyondaki_filmler_ekle_film_type /*,*/ /*vizyondaki_filmleri_degistir_sil_film_name,*/ /*vizyondaki_filmleri_degistir_sil_film_suresi,*/ /*vizyondaki_filmleri_degistir_sil_film_type*/;

    @FXML
    public DatePicker vizyondaki_filmler_ekle_kalkis/*, vizyondaki_filmleri_degistir_sil_kalkis*/;

    @FXML
    public ComboBox<String> vizyondaki_filmler_ekle_yonetmenler /*,*/ /*vizyondaki_filmler_degistir_sil_filmler,*/ /*vizyondaki_filmleri_degistir_sil_yonetmen*/;

    @FXML
    public GridPane vizyondaki_filmler_grid;

    @FXML
    public Label vizyondaki_filmler_ekle_uyari_mesaj /*,*/ /*vizyondaki_filmler_degistir_sil_uyari_mesaj_1,*/ /*vizyondaki_filmler_degistir_sil_uyari_mesaj_2,*/  /*vizyondaki_filmler_degistir_sil_vizyon_id*/ /*, vizyondaki_filmler_degistir_sil_film_id*/;

    @FXML
    public FontAwesomeIconView vizyondaki_filmler_geri_tusu, vizyondaki_filmler_ekle_geri_tusu, /*vizyondaki_filmler_degistir_geri_tusu,*/ vizyondaki_filmler_gosterim_geri_tusu;

    @FXML
    public ImageView vizyondaki_filmler_gosterim_bir, vizyondaki_filmler_gosterim_iki, vizyondaki_filmler_gosterim_uc, vizyondaki_filmler_gosterim_dort, vizyondaki_filmler_gosterim_bes, vizyondaki_filmler_gosterim_alti, vizyondaki_filmler_gosterim_yedi, vizyondaki_filmler_gosterim_sekiz, vizyondaki_filmler_gosterim_dokuz, vizyondaki_filmler_gosterim_on;

    @FXML
    public Label vizyondaki_filmler_gosterim_id_bir, vizyondaki_filmler_gosterim_id_iki, vizyondaki_filmler_gosterim_id_uc, vizyondaki_filmler_gosterim_id_dort, vizyondaki_filmler_gosterim_id_bes, vizyondaki_filmler_gosterim_id_alti, vizyondaki_filmler_gosterim_id_yedi, vizyondaki_filmler_gosterim_id_sekiz, vizyondaki_filmler_gosterim_id_dokuz, vizyondaki_filmler_gosterim_id_on;

    @FXML
    public Label vizyondaki_filmler_gosterim_adi_bir, vizyondaki_filmler_gosterim_adi_iki, vizyondaki_filmler_gosterim_adi_uc, vizyondaki_filmler_gosterim_adi_dort, vizyondaki_filmler_gosterim_adi_bes, vizyondaki_filmler_gosterim_adi_alti, vizyondaki_filmler_gosterim_adi_yedi, vizyondaki_filmler_gosterim_adi_sekiz, vizyondaki_filmler_gosterim_adi_dokuz, vizyondaki_filmler_gosterim_adi_on;

    @FXML
    public Label vizyondaki_filmler_gosterim_sayfa_sayisi, vizyondaki_filmler_gosterim_uyari_mesaj;
    
    @FXML
    public Button vizyondaki_filmler_gosterim_sonraki, vizyondaki_filmler_gosterim_onceki;

    @FXML
    public Pane vizyondaki_filmler_gosterim_pane_bir, vizyondaki_filmler_gosterim_pane_iki, vizyondaki_filmler_gosterim_pane_uc, vizyondaki_filmler_gosterim_pane_dort, vizyondaki_filmler_gosterim_pane_bes, vizyondaki_filmler_gosterim_pane_alti, vizyondaki_filmler_gosterim_pane_yedi, vizyondaki_filmler_gosterim_pane_sekiz, vizyondaki_filmler_gosterim_pane_dokuz, vizyondaki_filmler_gosterim_pane_on, vizyondaki_filmler_gosterim_pane_sayfa;

    @FXML
    public AnchorPane pnl_film_detay;

    @FXML
    public Label vizyondaki_filmler_detay_film_id, vizyondaki_filmler_detay_film_adi, vizyondaki_filmler_detay_film_turu, vizyondaki_filmler_detay_film_suresi, vizyondaki_filmler_detay_yonetmen, vizyondaki_filmler_detay_kalkis_tarihi, vizyondaki_filmler_detay_kullanici_puani;
    
    @FXML
    public Label film_detay_hangi_abone_turu, film_detay_aldigi_odul_sayisi, film_detay_kalksi_tarihi_oncesi, film_detay_aldigi_odul_sayisi_oncesi, film_detay_hangi_abone_turu_oncesi, film_detay_id, film_detay_id_oncesi;

    @FXML
    public Pane ust_pnl_eski_filmler, eski_filmler_ekle_pane, eski_filmler_degistir_pane, eski_filmler_degistir_pane_1, eski_filmler_degistir_pane_2, eski_filmler_degistir_sil_emin_misin;

    @FXML
    public TableColumn<eski_filmler, String> eski_filmler_hangi_abone;

    @FXML
    public TextField eski_film_name, eski_film_type, eski_film_suresi, eski_aldigi_odul_sayisi, eski_filmleri_degistir_sil_film_name, eski_filmleri_degistir_sil_film_type, eski_filmleri_degistir_sil_film_suresi, eski_filmleri_degistir_sil_aldigi_odul;

    @FXML
    public ComboBox<String> eski_hangi_aboneler, eski_ekle_yonetmenler, eski_degistir_sil_filmler, eski_filmleri_degistir_sil_yonetmen, eski_filmleri_degistir_sil_hangi_abone;

    @FXML
    public GridPane eski_filmler_grid;

    @FXML
    public Label eski_ekle_uyari_mesaj, eski_filmler_degistir_sil_uyari_mesaj_1, eski_filmler_degistir_sil_uyari_mesaj_2, eski_filmler_degistir_sil_eski_id;

    @FXML
    public FontAwesomeIconView eski_filmler_geri_tusu, eski_filmler_ekle_geri_tusu, eski_filmler_degistir_geri_tusu;

    @FXML
    public Pane ust_pnl_kampanyalar, kampanyalar_ekle_pane, kampanyalar_degistir_pane, kampanyalar_degistir_pane_1, kampanyalar_degistir_pane_2, kampanyalar_sil_emin_misin_pane;

    @FXML
    public GridPane kampanyalar_grid;

    @FXML
    public FontAwesomeIconView kampanyalar_ekle_geri_tusu, kampanyalar_degistir_geri_tusu;

    @FXML
    public TableColumn<kampanyalar, String> kampanyalar_hangi_kullanıcı;

    @FXML
    public TableColumn<kampanyalar, Button> kampanyalar_sil;

    @FXML
    public TextField kampanyalar_ekle_title, kampanyalar_ekle_kategori, kampanyalar_degistir_title, kampanyalar_degistir_kategori;
    
    @FXML
    public DatePicker kampanyalar_ekle_tarih, kampanyalar_degistir_tarih;

    @FXML
    public ComboBox<String> kampanyalar_ekle_hangi_kullanici, kampanyalar_degistir_hangi_kullanici, kampanyalar_degistir_kampanyalari_getir;

    @FXML
    public TextArea kampanyalar_ekle_kampanya, kampanyalar_degistir_kampanya;

    @FXML
    public Label kampanyalar_ekle_uyari_mesaj, kampanyalar_degistir_uyari_mesaj_1, kampanyalar_degistir_uyari_mesaj_2, kampanyalar_silmekten_emin_kampanya_id, kampanyalar_degistir_kampanya_id, kampanyalar_table_uyari_mesaj;

    @FXML
    public Pane ust_pnl_haberler, haberler_ekle_pane, haberler_degistir_pane, haberler_degistir_pane_1, haberler_degistir_pane_2, haberler_sil_emin_misin_pane;

    @FXML
    public GridPane haberler_grid;

    @FXML
    public FontAwesomeIconView haberler_ekle_geri_tusu, haberler_degistir_geri_tusu;

    @FXML
    public TableColumn<haberler, Button> haberler_sil;

    @FXML
    public TextField haberler_ekle_title, haberler_ekle_kategori, haberler_degistir_title, haberler_degistir_kategori;
    
    @FXML
    public DatePicker haberler_degistir_tarih;
    
    @FXML
    public DatePicker haberler_ekle_tarih;

    @FXML
    public ComboBox<String> haberler_ekle_hangi_kullanici, haberler_degistir_hangi_kullanici, haberler_degistir_haberleri_getir;

    @FXML
    public TextArea haberler_ekle_haber, haberler_degistir_haber;

    @FXML
    public Label haberler_ekle_uyari_mesaj, haberler_degistir_uyari_mesaj_1, haberler_degistir_uyari_mesaj_2, haberler_silmekten_emin_haber_id, haberler_degistir_haber_id, haberler_table_uyari_mesaj;

    @FXML
    public Pane sinema_salonlari_home_pane, sinema_salonlari_goruntule_pane, sinema_salonu_ekle_pane, sinema_salonu_duzenle_pane, sinema_salonu_duzenle_pane_1, sinema_salonu_duzenle_pane_2;

    @FXML
    public Label sinema_salonu_goruntule_uyari_mesaj, sinema_salonu_ekle_uyari_mesaj, sinema_salonunu_guncelle_id, sinema_salonu_guncelle_uyari_mesaj;

    @FXML
    public ComboBox<String> sinema_salonlari_goruntule, sinema_salonu_ekle_koltuk_sayisi, sinema_salonlarini_duzenle_combo, sinema_salonlarini_duzenle_koltuk_sayisi;

    @FXML
    public TextField sinema_salonu_ekle_name, sinema_salonu_guncelle_name;

    @FXML
    public FontAwesomeIconView kullanici_islemleri_geri_tusu, kullanici_islemleri_yonet_geri_tusu;
    
    @FXML
    public Label film_detay_guncelle_mesaj;
    
    @FXML
    public ImageView vizyondaki_filmler_film_detay_photo;

}
