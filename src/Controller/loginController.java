package Controller;

import Pattern.Creator;
import DAO.usersDAO;
import entity.users;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import java.io.IOException;
import javafx.animation.TranslateTransition;
import javafx.scene.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class loginController implements Initializable {

    private boolean a = false;

    private boolean sifre_gosterim = false;

    private boolean sifre_gosterim2 = false;

    @FXML
    private TextField tf_username, tf_password, tf_username2, tf_password2, tf_mail;

    @FXML
    private PasswordField pf_password, pf_password2;

    @FXML
    private Label label, label2, yazi1, yazi2;

    @FXML
    private HBox gizli, acik, gizli2, acik2;

    @FXML
    private Button ortadaki_btn, sifremi_unuttum, kayit_olmadan;

    @FXML
    private AnchorPane ortadaki_pane, kayit_ol_paneli, oturum_ac_paneli;

    @FXML
    private void login(ActionEvent event) throws IOException {

        String user_name = tf_username.getText();
        String password = null;

        if (sifre_gosterim == false) {
            password = pf_password.getText();
        } else {
            password = tf_password.getText();
        }

        if ((password.length() == 0) || (user_name.length() == 0)) {

            label.setText("Lütfen Gerekli Yerleri Doldurunuz.");

        } else {

            usersDAO users_islemleri = new usersDAO();
            int user_id = users_islemleri.user_giris_kontrol(user_name, password);

            pf_password.setText("");
            tf_password.setText("");
            tf_username.setText("");

            if ((user_id == -1) || (user_id == -2)) {

                label.setText("Bir hata meydana geldi.Hata Kodu= -13");

            } else if (user_id == 0) {
                label.setText("Kullanıcı adı veya şifre hatalı");
            } else {

                int authority = users_islemleri.user_type_getir(user_id);
                String name = users_islemleri.user_name_getir(user_id);
                String pass = users_islemleri.user_password_getir(user_id);
                String mail = users_islemleri.user_mail_getir(user_id);
                
                users u = new users(user_id, name, mail, password, authority);
                
                Creator.setU(u);
                

                switch (authority) {
                    case 0: {
                        Parent root = FXMLLoader.load(getClass().getResource("/FXML/app_standart.fxml"));
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.setScene(scene);

                        scene.setFill(Color.TRANSPARENT);
                        stage.setScene(scene);
                        stage.initStyle(StageStyle.UNDECORATED);
                        stage.show();
                        stage.setTitle("Cinema");
                        Node node = (Node) event.getSource();
                        Stage stage2 = (Stage) node.getScene().getWindow();
                        stage2.close();
                        break;
                    }
                    case 1: {
                        Parent root = FXMLLoader.load(getClass().getResource("/FXML/app_admin.fxml"));
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

                        break;
                    }
                    default:
                        label.setText("Bir Hata Meydana Geldi. Hata kodu=-14");
                        break;
                }

            }
        }

    }

    @FXML
    private void kayit_ol(ActionEvent event) throws IOException {
        String user_name = tf_username2.getText();
        String user_mail = tf_mail.getText();
        String password = null;

        if (sifre_gosterim2 == false) {
            password = pf_password2.getText();
        } else {
            password = tf_password2.getText();
        }

        if ((password.length() == 0) || (user_name.length() == 0)) {

            label2.setText("Lütfen Gerekli Yerleri Doldurunuz.");

        } else {
            usersDAO users_islemleri = new usersDAO();
            if (users_islemleri.user_name_control(user_name)) {
                if (users_islemleri.user_mail_control(user_mail)) {
                    users User = new users(user_name, user_mail, password, 0);
                    int sonuc = users_islemleri.user_ekle(User);
                    if (sonuc == 1) {
                        tf_username2.setText("");
                        tf_mail.setText("");
                        tf_password2.setText("");
                        pf_password2.setText("");
                        label2.setText("Başarılı bir şekilde kayıt oldunuz. Giriş yapabilirsiniz.");
                    } else {
                        label2.setText("Hata meydana geldi. Lütffen daha sonra tekrar deneyiniz. (Hata kodu: 105)");
                    }
                } else {
                    label2.setText("Bu mail adresi zaten kayıtlı");
                }
            } else {
                label2.setText("Bu kullanıcı adı alınamaz");
            }

        }
    }

    @FXML
    private void btn(MouseEvent event) throws IOException {
        if (ortadaki_btn.getText().equals("Kayıt Ol")) {

            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.5));
            slide.setNode(ortadaki_pane);

            slide.setToX(832);
            slide.play();

            kayit_ol_paneli.setVisible(true);

            acik2.setVisible(false);
            gizli2.setVisible(true);

            ortadaki_btn.setText("Giriş Yap");

            yazi1.setText("Üyemiz Misin?");
            yazi2.setText("Hemen Hesabına giriş yap");

            oturum_ac_paneli.setVisible(false);
            sifremi_unuttum.setVisible(false);
            kayit_olmadan.setVisible(false);

            slide.setOnFinished((e -> {

            }));

        } else if (ortadaki_btn.getText().equals("Giriş Yap")) {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.5));
            slide.setNode(ortadaki_pane);

            slide.setToX(0);
            slide.play();

            oturum_ac_paneli.setVisible(true);

            ortadaki_btn.setText("Kayıt Ol");

            kayit_ol_paneli.setVisible(false);
            sifremi_unuttum.setVisible(true);
            kayit_olmadan.setVisible(true);

            slide.setOnFinished((e -> {

            }));

            yazi1.setText("Kayıtlı Üyemiz Değil misin?");
            yazi2.setText("Kayıt Ol Ve Sana Özel Fırsatlardan Yararlan");

        } else {
            //doldurmayı unutma
            System.out.println("Hata: loginController.java, btn fonk.");
        }
    }

    //görünüş açısından gizlenen ve manuel olarak elle eklenen kapatma tuşunun methodu
    @FXML
    private void close(MouseEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

    //görünüş açısından gizlenen ve manuel olarak elle eklenen tam ekran moda geçme tuşunun methodu
    @FXML
    private void max(MouseEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        //yukarıda tanımladığımız değişkene göre tam ekran moda alıyoruz ya da normal boyuta alıyoruz.
        if (a == false) {
            //a false ise daha küçük demektir ve büyültüyoruz.
            stage.setFullScreenExitHint("Tam moda geçildi çıkmak için 'esc' tuşuna basınız");
            stage.setFullScreen(true);
            a = true;
            //a'yı true yapyıyoruz. Çünkü program artık tam ekran modunda.
        } else {
            //eğer a true ise program tak eran modundadır o zaman da normal noyuta geçiyoruz.
            stage.setFullScreen(false);
            // a'yı false yapıyoruz çünkü artık tam ekranda değil nomral boyutta
            a = false;
        }
    }

    //görünüş açısından gizlenen ve manuel olarak elle eklenen aşağı alma tuşunun methodu
    @FXML
    private void min(MouseEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setIconified(true);
    }

    //bu method şifreyi gösterme işine yarıyor. Kullanıcı şifreyi göster simgesine tıklarsa passwordfield'ın buludnuğu hbox'ı kapatıp textfield'ın bulunduğu hbox'ı açıyor ve passwordfiled'Dan veriyi alıp kendine yazıyor.
    @FXML
    private void goster(MouseEvent event) {
        acik.setVisible(true);
        sifre_gosterim = true;
        gizli.setVisible(false);
        tf_password.setText(pf_password.getText());
    }

    //bu method şifreyi gizleme işine yarıyor. Kullanıcı şifreyi gizle simgesine tıklarsa textfield'ın buludnuğu hbox'ı kapatıp passwordfield'ın bulunduğu hbox'ı açıyor ve textfiled'dan veriyi alıp kendine yazıyor.
    @FXML
    private void gizle(MouseEvent event) {
        gizli.setVisible(true);
        sifre_gosterim = false;
        acik.setVisible(false);
        pf_password.setText(tf_password.getText());
    }

    @FXML
    private void goster2(MouseEvent event) {
        acik2.setVisible(true);
        sifre_gosterim2 = true;
        gizli2.setVisible(false);
        tf_password2.setText(pf_password2.getText());
    }

    @FXML
    private void gizle2(MouseEvent event) {
        gizli2.setVisible(true);
        sifre_gosterim2 = false;
        acik2.setVisible(false);
        pf_password2.setText(tf_password2.getText());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
