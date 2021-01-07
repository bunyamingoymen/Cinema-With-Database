package Controller;

import DAO.usersDAO;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class app_standart_userController implements Initializable {

    /*
    Bunun devamı nerede ve neden Center diye bir şeyi extends ediyor?
    
    Bunun sebebi appController ve app_stanadart_userController sınıflarının ortak noktalarını kalıtım aracılığıyla başka sınıfta tanımlamamızdır..
    
    Hem appController da hem de app_standart_userController da 7.000'den fazla ortak kod bulunmaktadır.
    Bu sebeple ikisinn ortak noktalarını bir sınıfta topladık ve ikisine de extends ettik.
     */
    //Bu metod bu sınıfa özgü olan pane'leri tanımlıyor (appController ya da başka yerde kullanılmayan)
    @FXML
    private Pane bilet_satin_al_pane, bilet_satin_al_uyari_pane, bilet_satin_al_filmi_sec_pane, bilet_satin_al_seans_sec_pane, bilet_satin_al_koltuk_sec_pane;

    @FXML
    private Label bilet_satin_al_uyari_mesaj, filmi_sec_uyari_mesaj, seans_sec_uyari_mesaj, bilet_satin_al_film_id, bilet_satin_al_salon_id, bilet_satin_al_seans_id, bilet_satin_al_buton_bilet_satin_al_uyari_mesaj;

    @FXML
    private ComboBox<String> filmleri_goster, seanslari_goster;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    //Bu metot sol tarafta bulunan Ana Sayfa yazısına tıklandığında ne yapılacağını söylüyor. Anasayfa pane'i dışında bütün pane'leri kapatıyor.
    @FXML
    private void home(MouseEvent event) {

    }

    @FXML
    private void settings(MouseEvent event) {

    }

    //Sol tarafta bulunan Vizyondaki Filmler butonuna tıklandığında ne olacağını söylüyor
    @FXML
    private void vizyondai_filmler(ActionEvent event) {

    }

    //Sol tarafta bulunan Eski Filmler butonuna tıklandığında ne olacağını söylüyor.
    @FXML
    private void eski_filmler(ActionEvent event) {

    }

    //Sol tarafta bulunan kampanyalar butonuna tıklandığında ne olacağını söylüyor.
    @FXML
    private void kampanyalar(ActionEvent event) {

    }

    //Sol tarafta bulunan haberler butonuna tıklandığıdna ne olacağını söylüyor.
    @FXML
    private void haberler(ActionEvent event) {

    }

    //Sol tarafta bulunan Bilet satın al metodu tarafıntan aktif hale getirilen metotdur.
    @FXML
    private void bilet_satin_al(ActionEvent event) {

    }

    //bu metot bilet_Satin_al içinde bulunan filmi seç butonuna tıklandığında ne yapılacağını belirtiyor.
    @FXML
    private void filmi_sec(ActionEvent event) {

    }

    //Bu metot seans seç butonuna tıklandığında ne yapılacağını gösteriyor.
    @FXML
    private void seans_sec(ActionEvent event) {

    }

    //bu buton en son kademede bulunan(koltuk seçme kademesi) bilet al butonuna basıldığında aktif oluyor. 
    @FXML
    private void bilet_satin_al_buton_bilet_satin_al(ActionEvent event) {

    }

    //bu metot bilet satın al içindeki seanslari göster butonununa tıklandıktan sonra combo box'ın içini doldurmak için yazılmış bir metoddur.
    private void seanslari_goster() {

    }

    @FXML
    private void close(MouseEvent event) {
        usersDAO udao = new usersDAO();
        int sonuc = udao.bilgi_sil();

        if (sonuc == 1) {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void cikis(MouseEvent event) throws IOException {

        usersDAO udao = new usersDAO();
        int sonuc = udao.bilgi_sil();

        if (sonuc == 1) {
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/login.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);

            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
            stage.setTitle("Cinema-Admin");
            Node node = (Node) event.getSource();
            Stage stage2 = (Stage) node.getScene().getWindow();
            stage2.close();
        }
    }

}
