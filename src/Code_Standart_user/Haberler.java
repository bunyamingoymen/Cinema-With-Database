package Code_Standart_user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class Haberler extends Kampanyalar {

    @FXML
    public void haberler_giris(ActionEvent event) {
        haberler_giris_ortak();

        haberler_geri_tusu.setVisible(true);
        sana_ozel_haberler_geri_tusu.setVisible(false);
    }

    @FXML
    public void haberler_geri(MouseEvent event) {
        home_giris_ortak();
    }

}
