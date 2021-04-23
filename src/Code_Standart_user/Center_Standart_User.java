package Code_Standart_user;

import entity.*;
import DAO.*;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Center_Standart_User extends Settings implements Initializable {

    //////////////////////////////////////////////////////////////////////////
    //hem appController da hem de appStandart_userController da karışık ve ortak olanlar için ayrılan alan
    //program da aktif olarak çalışan paneler
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    //bu method şifreyi gösterme işine yarıyor. Kullanıcı şifreyi göster simgesine tıklarsa passwordfield'ın buludnuğu hbox'ı kapatıp textfield'ın bulunduğu hbox'ı açıyor ve passwordfiled'Dan veriyi alıp kendine yazıyor.
    @FXML
    protected void goster(MouseEvent event) {
        acik.setVisible(true);
        sifre_gosterim = true;
        gizli.setVisible(false);
        tf_user_password.setText(user_password.getText());

    }

    //bu method şifreyi gizleme işine yarıyor. Kullanıcı şifreyi gizle simgesine tıklarsa textfield'ın buludnuğu hbox'ı kapatıp passwordfield'ın bulunduğu hbox'ı açıyor ve textfiled'dan veriyi alıp kendine yazıyor.
    @FXML
    protected void gizle(MouseEvent event) {
        gizli.setVisible(true);
        sifre_gosterim = false;
        acik.setVisible(false);
        user_password.setText(tf_user_password.getText());
    }

    @FXML
    protected void close(MouseEvent event) {
        usersDAO udao = new usersDAO();

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

    //görünüş açısından gizlenen ve manuel olarak elle eklenen tam ekran moda geçme tuşunun methodu
    @FXML
    protected void max(MouseEvent event) {
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
    protected void min(MouseEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setIconified(true);
    }

    //bu metot ise kullnıcının çıkış yapmasına olanak sağlıyor. Yani kısacası app_standart_user.fxml ile app.fxml'i kapatıp login.fxml'i açıyor.
    @FXML
    protected void cikis(MouseEvent event) throws IOException {

        usersDAO udao = new usersDAO();

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

    //setting pane'in içinde bulunan Güncelle mbutonunun metodudur. Yaptığı şey kısaca kullanıcının verilerini textfield gibi yerlerden alıp dosyaya kaydetme işlevini görüyor.
    @FXML
    protected void guncelle(ActionEvent event) {
        //Bu metot ayarlar kısmındaki Güncelle butonunun işlevini görmektedir. Yani kısacası Bu butona basıldığında kullanıcıların verileri isteklerine göre değişebiliyor.

        //Dosya işlemlerini yapabilmek adına bir dosya nesnesi oluşturuluyor.
        //Bu 2 satır var olan kullanıcıları bir bağlı listeye aktarıyor (dosya işlemleri ile)
        usersDAO udao = new usersDAO();

        //Her giriş yapıldığında. Giriş yapan kullanıcının id'sini bir txt dosyasında tutuluyor(bilgi.txt) oradan en son giriş yapan kullanıcının id'sine erişiliyor. 
        int user_id = users.getU().getUser_id();

        //bu if ve else gerekli olan TextField ve PasswordField'lerin dolu olup olmadığını kontrol ediyor. Eğer doldurulması gereken bir yeri doldurmamışsa kullanıcı o zaman if'in içine giriyor ve bir uyarı veriyor. Doldurmuşsa da else'nin içine giriyor ve işlemleri yapyıor.
        if ((user_name.getText().length() == 0) || (user_mail.getText().length() == 0)) {
            guncelle_mesaj.setText("Lütfen Gerekli Yerleri Doldurunuz.");

        } else if ((user_password.getText().length() == 0) && (sifre_gosterim == false)) {
            guncelle_mesaj.setText("Lütfen Gerekli Yerleri Doldurunuz.");
        } else if ((sifre_gosterim == true) && (tf_user_password.getText().length() == 0)) {
            guncelle_mesaj.setText("Lütfen Gerekli Yerleri Doldurunuz.");
        } else {
            //Kullanıcının TextField ve PasswordFiled'lere yazdığı verileri bir String'e aktarıyoruz.
            String name = user_name.getText();
            String mail = user_mail.getText();
            String password = null;
            if (sifre_gosterim == false) {
                password = user_password.getText();
            } else {
                password = tf_user_password.getText();
            }

            int user_type = udao.user_type_getir(user_id);

            //yukarıda aldığımız kullanıcının girdiği bilgileri User adlı sınıfın içindeki metoda yolluyor. Bu metodun yaptığı işlevi ksaca anlatmak gerekirse. Yapılan değiişikliği ilk önce bağlı listede değiştiriyor ardından ise bunu dosyaya yazıp kalıcı hale getiriyor. 
            users u = new users(user_id, name, mail, password, user_type);
            int control = udao.user_guncelle(u);

            //Az önce gönderdiğimiz metot bir değer yolluyor bu değer 1 ise işlem herhangi bir hataya uğramadan başarılı bir şekilde gerçekleştiğini yazıyor. Eğer başarılı bir şekilde gerçekleşmiyor ise de Hata meydana gleidğini ekrana yazdırıyor.
            if (control == 1) {
                guncelle_mesaj.setText("İşlem Başarılı Bir Şekilde Gerçekleştirildi");
            } else {
                guncelle_mesaj.setText("Bir Hata Meydana Geldi (Hata kodu = -12)");
            }
        }

    }

    // Vizyondaki Filmler için ayrılmış alan
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Eski Filmler için
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Haberler için ayrılmış kısım
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Sinema Salnları için ortak olan değişkenler burada bulunuyor.
    //bu alanda sadece değişkenler tanımlanmıştır. Çünü hem appController'da hem de app_standart_userController'da ortak bir metot bulnmamaktadır ancka ortak değişkenler bulunmaktadır. 
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Bilet satın alma kısmı için
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //bu alan scene builderdaki 1.salona denk gelmektedir. ve 129 tane koltuk bulunmaktadır.
    //Not: Sadece buradaki değişkenler ve metotların ne olduğu açıklanmıştır. Daha aşağıdaki salonlar buraya benzediği için açıklamalar bulunmamaktadır.
    //ilk başta tanımlamamız gereken butonları tanımlıyoruz (Her bir buton her bir koltuğu temsil etmekteidr.)
}
