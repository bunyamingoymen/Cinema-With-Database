package Controller;

import DAO.usersDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class app_standart_userController extends Center implements Initializable {

    /*
    Bunun devamı nerede ve neden Center diye bir şeyi extends ediyor?
    
    Bunun sebebi appController ve app_stanadart_userController sınıflarının ortak noktalarını kalıtım aracılığıyla başka sınıfta tanımlamamızdır..
    
    Hem appController da hem de app_standart_userController da 7.000'den fazla ortak kod bulunmaktadır.
    Bu sebeple ikisinn ortak noktalarını bir sınıfta topladık ve ikisine de extends ettik.
     */
    //Bu metod bu sınıfa özgü olan pane'leri tanımlıyor (appController ya da başka yerde kullanılmayan)
    @FXML
    private AnchorPane pnl_abonelik_0;
    
    @FXML
    private void vizyondaki_filmler_giris(ActionEvent event){
        pnl_vizyondaki_filmler.setVisible(true);
        
        
        pnl_settings.setVisible(false);
        
        vizyondaki_filmler_table();
    }
    
    @FXML
    private void vizyondaki_filmler_geri(MouseEvent event){
        pnl_vizyondaki_filmler.setVisible(false);
    }

    @FXML
    private void settings_giris(MouseEvent event) {
        pnl_settings.setVisible(true);
        
        
        pnl_vizyondaki_filmler.setVisible(false);
        
        usersDAO udao = new usersDAO();
        
        int user_id = udao.bilgi_oku();
        
        user_name.setText(udao.user_name_getir(user_id));
        
        user_mail.setText(udao.user_mail_getir(user_id));
        
        user_password.setText(udao.user_password(user_id));
        
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
