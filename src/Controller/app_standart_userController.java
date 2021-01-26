package Controller;

import DAO.abonelerDAO;
import DAO.haberlerDAO;
import DAO.usersDAO;
import entity.haberler;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private void vizyondaki_filmler_giris(ActionEvent event) {
        pnl_vizyondaki_filmler.setVisible(true);

        pnl_settings.setVisible(false);
        pnl_haberler.setVisible(false);

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
        pnl_haberler.setVisible(true);

        pnl_settings.setVisible(false);
        pnl_vizyondaki_filmler.setVisible(false);

        usersDAO udao = new usersDAO();
        int user_id = udao.bilgi_oku();
        
        abonelerDAO adao = new abonelerDAO();
        int abonelik = adao.abonelik_turu_bul(user_id);

        haberler_table_butonsuz(abonelik);
    }

    
    @FXML
    private void haberler_geri(MouseEvent event){
        pnl_haberler.setVisible(false);
    }
    @FXML
    private void kampanyalar_giris(ActionEvent event) {

    }
    
    @FXML
    private void kampanyalar_geri(MouseEvent event){
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
