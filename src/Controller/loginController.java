package Controller;

import Pattern.Creator;
import DAO.usersDAO;
import DAO_Controller.Film_Photos_Controller;
import DAO_Controller.Users_Controller;
import DAO_Controller.Vizyondaki_Filmler_Controller;
import entity.Center;
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

    private boolean sifre_gosterim = false;

    private boolean sifre_gosterim2 = false;

    @FXML
    private AnchorPane login_pane;

    @FXML
    private TextField tf_username, tf_password, tf_username2, tf_password2, tf_mail, tf_username3;

    @FXML
    private PasswordField pf_password, pf_password2;

    @FXML
    private Label label, label2, yazi1, yazi2, label_password;

    @FXML
    private HBox gizli, acik, gizli2, acik2;

    @FXML
    private Button ortadaki_btn, sifremi_unuttum, kayit_olmadan;

    @FXML
    private AnchorPane ortadaki_pane, kayit_ol_paneli, oturum_ac_paneli, sifremi_unuttum_paneli;

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
            int user_id = users_islemleri.control_login(user_name, password);

            pf_password.setText("");
            tf_password.setText("");
            tf_username.setText("");

            if ((user_id == -1) || (user_id == -2)) {

                label.setText("Bir hata meydana geldi.Hata Kodu= -13");

            } else if (user_id == 0) {
                label.setText("Kullanıcı adı veya şifre hatalı");
            } else {

                int authority = users_islemleri.search_int(user_id);
                String name = users_islemleri.search_string(user_id, 2);
                String pass = users_islemleri.search_string(user_id, 1);
                String mail = users_islemleri.search_string(user_id, 3);

                users u = new users(user_id, name, mail, pass, authority);

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

                        Stage stage2 = (Stage) login_pane.getScene().getWindow();
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

                        Stage stage2 = (Stage) login_pane.getScene().getWindow();
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
            if (users_islemleri.control_user_name(user_name)) {
                if (users_islemleri.control_user_mail(user_mail)) {
                    users User = new users(user_name, user_mail, password, 0);
                    Center nw = new Center(User);
                    int sonuc = users_islemleri.create(nw);
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
    private void sifremi_goster(ActionEvent event) {
        String username = tf_username3.getText();
        if (username.length() == 0) {
            label_password.setText("Lütfen Gerekli Yerleri Doldurunuz.");
        } else {
            int user_id = Creator.usersDao().search_int(username);
            String user_password = Creator.usersDao().search_string(user_id, 1);
            label_password.setText(user_password);
        }

    }

    @FXML
    private void btn(MouseEvent event) throws IOException {
        if (ortadaki_btn.getText().equals("Kayıt Ol")) {

            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.5));
            slide.setNode(ortadaki_pane);

            slide.setToX(824);
            slide.play();

            kayit_ol_paneli.setVisible(true);

            acik2.setVisible(false);
            gizli2.setVisible(true);

            ortadaki_btn.setText("Giriş Yap");

            yazi1.setText("Üyemiz Misin?");
            yazi2.setText("Hemen Hesabına giriş yap");

            oturum_ac_paneli.setVisible(false);
            sifremi_unuttum_paneli.setVisible(false);
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
            sifremi_unuttum_paneli.setVisible(false);
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

    @FXML
    private void btn_2(MouseEvent event) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.5));
        slide.setNode(ortadaki_pane);

        slide.setToX(824);
        slide.play();

        sifremi_unuttum_paneli.setVisible(true);

        ortadaki_btn.setText("Giriş Yap");

        yazi1.setText("Şifreni mi unuttum?");
        yazi2.setText("Hemen Öğren");

        oturum_ac_paneli.setVisible(false);
        kayit_ol_paneli.setVisible(false);
        sifremi_unuttum.setVisible(false);
        kayit_olmadan.setVisible(false);

        slide.setOnFinished((e -> {

        }));
    }

    //görünüş açısından gizlenen ve manuel olarak elle eklenen kapatma tuşunun methodu
    @FXML
    private void close(MouseEvent event) {
        Stage stage = (Stage) login_pane.getScene().getWindow();
        stage.close();
    }

    //görünüş açısından gizlenen ve manuel olarak elle eklenen tam ekran moda geçme tuşunun methodu
    @FXML
    private void max(MouseEvent event) {
        Stage stage = (Stage) login_pane.getScene().getWindow();

        if (stage.isMaximized()) {
            stage.setMaximized(false);
        } else {
            stage.setMaximized(true);
        }
    }

    //görünüş açısından gizlenen ve manuel olarak elle eklenen aşağı alma tuşunun methodu
    @FXML
    private void min(MouseEvent event) {
        Stage stage = (Stage) login_pane.getScene().getWindow();
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

        Users_Controller.setUser_list();
        Vizyondaki_Filmler_Controller.setVizyondaki_filmler_list();
        Film_Photos_Controller.setFilm_photos_list();

    }
}
