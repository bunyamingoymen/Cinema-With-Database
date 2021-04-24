package Creator;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entity.actor;
import entity.film_actor;
import entity.seans;
import entity.vizyondaki_filmler;
import entity.yonetmenler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class Mediator {

    private static ObservableList<vizyondaki_filmler> data_vizyondaki_filmler_bir;

    public static int data_vizyondaki_filmler_bir_guncellendi_mi;

    public static ObservableList<actor> data_actor;
    
    public static ObservableList<seans> data_seans;
    
    public static ObservableList<yonetmenler> data_yonetmenler;

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

    public static ObservableList<actor> data_Actor(Label lab, Label lab2, Pane pan, Label lab3, GridPane aktorler_grid, GridPane film_actor_grid, FontAwesomeIconView aktorler_geri_tusu, FontAwesomeIconView film_actor_geri_tusu, TableColumn<film_actor, String> film_actor_film_name, TableColumn<film_actor, String> film_actor_film_type, TableColumn<film_actor, String> film_actor_yonetmen, TableColumn<film_actor, Button> film_actor_sil, TableView<film_actor> table_film_actor, TextField filterField_film_actor, Pane film_actor_pane, Pane film_actor_ekle_pane, Pane film_actor_sil_emin_misin_pane, Label film_actor_id_label) {
        if (data_actor == null) {
            data_actor = FXCollections.observableArrayList();
            data_actor = Creator.actorDao().aktorler_select(data_actor, lab, lab2, pan, lab3, aktorler_grid, film_actor_grid, aktorler_geri_tusu, film_actor_geri_tusu, film_actor_film_name, film_actor_film_type, film_actor_yonetmen, film_actor_sil, table_film_actor, filterField_film_actor, film_actor_pane, film_actor_ekle_pane, film_actor_sil_emin_misin_pane, film_actor_id_label);
        } else {
            vizyondaki_filmler v = new vizyondaki_filmler();
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
    
    public static ObservableList<seans> data_Seans(){
        if(data_seans == null){
            data_seans = FXCollections.observableArrayList();
            data_seans = Creator.seansDao().seans_select(data_seans);
        }
        return data_seans;
    }
    
    public static ObservableList<yonetmenler> data_Yonetmenler(){
        if(data_yonetmenler == null){
            data_yonetmenler = FXCollections.observableArrayList();
            data_yonetmenler = Creator.yonetmenlerDao().yonetmenler_select(data_yonetmenler);
        }
        return data_yonetmenler;
    }

}
