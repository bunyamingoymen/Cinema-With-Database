package Creator;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class Mediator {

    private static ObservableList<vizyondaki_filmler> data_vizyondaki_filmler_bir;
    
    private static ObservableList<vizyondaki_filmler> data_vizyondaki_filmler_iki;

    private static int data_vizyondaki_filmler_bir_guncellendi_mi;

    private static ObservableList<actor> data_actor;

    private static ObservableList<seans> data_seans;

    private static ObservableList<yonetmenler> data_yonetmenler;

    private static ObservableList<eski_filmler> data_eski_filmler;

    private static ObservableList<kampanyalar> data_kampanyalar;

    private static ObservableList<haberler> data_haberler;

    private static ObservableList<users> data_users;
    

    public Mediator() {
        data_vizyondaki_filmler_bir_guncellendi_mi = 1;
    }

    public static ObservableList<vizyondaki_filmler> vizyondaki_filmler_bir(Label film_detay_film_id, Label film_detay_film_adi, Label film_detay_film_turu, Label film_detay_film_suresi, Label film_detay_yonetmen, Label film_detay_kalkis_tarihi, Label film_detay_kullanici_puani, AnchorPane pnl_vizyondaki_filmler, AnchorPane pnl_eski_filmler, AnchorPane pnl_film_detay) {
        if (data_vizyondaki_filmler_bir == null || data_vizyondaki_filmler_bir_guncellendi_mi == 1) {
            data_vizyondaki_filmler_bir = FXCollections.observableArrayList();
            data_vizyondaki_filmler_bir = Creator.vizyondaki_filmlerDao().vizyondaki_filmler_select_butonlu(data_vizyondaki_filmler_bir, film_detay_film_id, film_detay_film_adi, film_detay_film_turu, film_detay_film_suresi, film_detay_yonetmen, film_detay_kalkis_tarihi, film_detay_kullanici_puani, pnl_vizyondaki_filmler, pnl_eski_filmler, pnl_film_detay);
            data_vizyondaki_filmler_bir_guncellendi_mi = 0;
        } else {
            Button detay = new Button();
            detay.setText("Detay");
            detay.setStyle("-fx-background-color : #393351; -fx-background-radius :  20; -fx-text-fill: white");

            Creator.vizyondaki_filmler().vizyondaki_filmler_controller(detay, film_detay_film_id, film_detay_film_adi, film_detay_film_turu, film_detay_film_suresi, film_detay_yonetmen, film_detay_kalkis_tarihi, film_detay_kullanici_puani, pnl_vizyondaki_filmler, pnl_eski_filmler, pnl_film_detay);

        }
        return data_vizyondaki_filmler_bir;
    }
    
    public static ObservableList<vizyondaki_filmler> data_vizyondaki_filmler_iki() {
        if (data_vizyondaki_filmler_iki == null) {
            data_vizyondaki_filmler_iki = FXCollections.observableArrayList();
            data_vizyondaki_filmler_iki = Creator.vizyondaki_filmlerDao().vizyondaki_filmler_select(data_vizyondaki_filmler_iki);
        }
        return data_vizyondaki_filmler_iki;
    }

    public static ObservableList<actor> data_Actor(Label lab, Label lab2, Pane pan, Label lab3, GridPane aktorler_grid, GridPane film_actor_grid, FontAwesomeIconView aktorler_geri_tusu, FontAwesomeIconView film_actor_geri_tusu, TableColumn<film_actor, String> film_actor_film_name, TableColumn<film_actor, String> film_actor_film_type, TableColumn<film_actor, String> film_actor_yonetmen, TableColumn<film_actor, Button> film_actor_sil, TableView<film_actor> table_film_actor, TextField filterField_film_actor, Pane film_actor_pane, Pane film_actor_ekle_pane, Pane film_actor_sil_emin_misin_pane, Label film_actor_id_label) {
        if (data_actor == null) {
            data_actor = FXCollections.observableArrayList();
            data_actor = Creator.actorDao().aktorler_select(data_actor, lab, lab2, pan, lab3, aktorler_grid, film_actor_grid, aktorler_geri_tusu, film_actor_geri_tusu, film_actor_film_name, film_actor_film_type, film_actor_yonetmen, film_actor_sil, table_film_actor, filterField_film_actor, film_actor_pane, film_actor_ekle_pane, film_actor_sil_emin_misin_pane, film_actor_id_label);
        } else {
            Button guncelle = new Button();
            guncelle.setText("Güncelle");
            guncelle.setStyle("-fx-background-color : #393351; -fx-background-radius :  20; -fx-text-fill: white");

            Button filmleri_goster = new Button();
            filmleri_goster.setText("Filmleri Göster");
            filmleri_goster.setStyle("-fx-background-color : #393351; -fx-background-radius :  20; -fx-text-fill: white");

            Button sil = new Button();
            sil.setText("Sil");
            sil.setStyle("-fx-background-color : #FA2C56; -fx-background-radius :  20; -fx-text-fill: white");

            Creator.actor().actor_controller(guncelle, sil, filmleri_goster, lab, lab2, pan, lab3, aktorler_grid, film_actor_grid, aktorler_geri_tusu, film_actor_geri_tusu, film_actor_film_name, film_actor_film_type, film_actor_yonetmen, film_actor_sil, table_film_actor, filterField_film_actor, film_actor_pane, film_actor_ekle_pane, film_actor_sil_emin_misin_pane, film_actor_id_label);

        }
        return data_actor;
    }

    public static ObservableList<seans> data_Seans() {
        if (data_seans == null) {
            data_seans = FXCollections.observableArrayList();
            data_seans = Creator.seansDao().seans_select(data_seans);
        }
        return data_seans;
    }

    public static ObservableList<yonetmenler> data_Yonetmenler() {
        if (data_yonetmenler == null) {
            data_yonetmenler = FXCollections.observableArrayList();
            data_yonetmenler = Creator.yonetmenlerDao().yonetmenler_select(data_yonetmenler);
        }
        return data_yonetmenler;
    }

    public static ObservableList<eski_filmler> data_Eski_Filmler() {
        if (data_eski_filmler == null) {
            data_eski_filmler = FXCollections.observableArrayList();
            data_eski_filmler = Creator.eski_filmlerDao().eski_filmler_select(data_eski_filmler);
        }
        return data_eski_filmler;
    }

    public static ObservableList<kampanyalar> data_Kampanyalar(Pane kampanyalar_sil_emin_misin_pane, Label kampanyalar_silmekten_emin_kampanya_id) {
        if (data_kampanyalar == null) {
            data_kampanyalar = FXCollections.observableArrayList();
            data_kampanyalar = Creator.kampanyalarDao().kampanyalar_select(data_kampanyalar, kampanyalar_sil_emin_misin_pane, kampanyalar_silmekten_emin_kampanya_id);
        } else {

            Button sil = new Button();
            sil.setText("Sil");
            sil.setStyle("-fx-background-color : #FA2C56; -fx-background-radius :  20; -fx-text-fill: white");

            Creator.kampanyalar().kampanya_controller(sil, kampanyalar_sil_emin_misin_pane, kampanyalar_silmekten_emin_kampanya_id);

        }
        return data_kampanyalar;
    }

    public static ObservableList<haberler> data_Haberler(Pane haberler_sil_emin_misin_pane, Label haberler_silmekten_emin_kampanya_id) {
        if (data_haberler == null) {
            data_haberler = FXCollections.observableArrayList();
            data_haberler = Creator.haberlerDao().haberler_select(data_haberler, haberler_sil_emin_misin_pane, haberler_silmekten_emin_kampanya_id);
        } else {

            Button sil = new Button();
            sil.setText("Sil");
            sil.setStyle("-fx-background-color : #FA2C56; -fx-background-radius :  20; -fx-text-fill: white");

            Creator.haberler().haber_controller(sil, haberler_silmekten_emin_kampanya_id, haberler_sil_emin_misin_pane);

        }
        return data_haberler;
    }

    public static ObservableList<users> data_Users(Label kullanici_islemleri_user_id, TextField kullanici_islemleri_user_name, TextField kullanici_islemleri_user_mail, PasswordField kullanici_islemleri_gizli_pf, ComboBox<String> kullanici_islemleri_user_turu, ComboBox<String> kullanici_islemleri_abone_turu, Pane gizli_pane, Pane acik_pane, Pane tablo_pane, Pane yonet_pane, FontAwesomeIconView geri_tusu, FontAwesomeIconView yonet_geri_tusu, Pane sil_pane) {
        if (data_users == null) {
            data_users = FXCollections.observableArrayList();
            data_users = Creator.usersDao().user_select(data_users, kullanici_islemleri_user_id, kullanici_islemleri_user_name, kullanici_islemleri_user_mail, kullanici_islemleri_gizli_pf, kullanici_islemleri_user_turu, kullanici_islemleri_abone_turu, gizli_pane, acik_pane, tablo_pane, yonet_pane, geri_tusu, yonet_geri_tusu, sil_pane);
        } else {

            Button yonet = new Button();
            yonet.setText("Yönet");
            yonet.setStyle("-fx-background-color : #393351; -fx-background-radius :  20; -fx-text-fill: white");
            
            Creator.users().users_controller(yonet, kullanici_islemleri_user_id, kullanici_islemleri_user_name, kullanici_islemleri_user_mail, kullanici_islemleri_gizli_pf, kullanici_islemleri_user_turu, kullanici_islemleri_abone_turu, gizli_pane, acik_pane, tablo_pane, yonet_pane, geri_tusu, yonet_geri_tusu, sil_pane);

        }
        return data_users;
    }

}
