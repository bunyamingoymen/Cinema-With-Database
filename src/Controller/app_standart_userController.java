package Controller;

import Code_Standart_user.Center_Standart_User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

public class app_standart_userController extends Center_Standart_User implements Initializable {

    @FXML
    private void home_vizyondaki_filmler_giris(MouseEvent event) {
        vizyondaki_filmler_giris_ortak();
    }

    @FXML
    private void home_abonelik_giris(MouseEvent event) {
        abonelik_giris_ortak();
    }

    @FXML
    private void home_bilet_satin_al_giris(MouseEvent event) {
        bilet_satin_al_giris_ortak();
    }

    @FXML
    private void home_haberler_giris(MouseEvent event) {
        haberler_giris_ortak();
        
    }

    @FXML
    private void home_kampanyalar_giris(MouseEvent event) {
        kampanyalar_giris_ortak();
    }

    @FXML
    private void home_biletlerim_giris(MouseEvent event) {
        biletlerim_giris_ortak();
    }

    @FXML
    private void home_giris(MouseEvent event) {
        home_giris_ortak();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
