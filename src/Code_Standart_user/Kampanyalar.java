
package Code_Standart_user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class Kampanyalar extends Kullanici_Islemleri {

    @FXML
    public void kampanyalar_giris(ActionEvent event) {
        kampanyalar_giris_ortak();

        kampanyalar_geri_tusu.setVisible(true);
        sana_ozel_kampanyalar_geri_tusu.setVisible(false);

    }

    @FXML
    public void kampanyalar_geri(MouseEvent event) {
        home_giris_ortak();
    }
}
