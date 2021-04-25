package Code_Admin;

import Creator.Creator;
import DAO.films_photosDAO;
import entity.*;
import java.awt.image.BufferedImage;
import java.io.File;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.imageio.ImageIO;

public class Center_Admin extends Yonetmenler implements Initializable {

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
        //Her giriş yapıldığında. Giriş yapan kullanıcının id'sini bir txt dosyasında tutuluyor(bilgi.txt) oradan en son giriş yapan kullanıcının id'sine erişiliyor. 
        int user_id = Creator.getU().getUser_id();

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

            int user_type = Creator.usersDao().user_type_getir(user_id);

            //yukarıda aldığımız kullanıcının girdiği bilgileri User adlı sınıfın içindeki metoda yolluyor. Bu metodun yaptığı işlevi ksaca anlatmak gerekirse. Yapılan değiişikliği ilk önce bağlı listede değiştiriyor ardından ise bunu dosyaya yazıp kalıcı hale getiriyor. 
            users u = new users(user_id, name, mail, password, user_type);
            int control = Creator.usersDao().user_guncelle(u);

            //Az önce gönderdiğimiz metot bir değer yolluyor bu değer 1 ise işlem herhangi bir hataya uğramadan başarılı bir şekilde gerçekleştiğini yazıyor. Eğer başarılı bir şekilde gerçekleşmiyor ise de Hata meydana gleidğini ekrana yazdırıyor.
            if (control == 1) {
                guncelle_mesaj.setText("İşlem Başarılı Bir Şekilde Gerçekleştirildi");
            } else {
                guncelle_mesaj.setText("Bir Hata Meydana Geldi (Hata kodu = -12)");
            }
        }

    }

    @FXML
    private void film_photo_change(MouseEvent event) throws IOException {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Pictures", "*.jpg", "*.jpeg", "*.png"));
        File selectedFile = fc.showOpenDialog(null);
        int film_id = Integer.parseInt(vizyondaki_filmler_detay_film_id.getText());

        if (selectedFile == null) {
            film_detay_guncelle_mesaj.setText("Bir resim seçmediniz.");
        } else {
            File ada = new File("src/lib/pic/films/" + film_id + ".png");
            ada.delete();

            File ada2 = new File("src/lib/pic/films/" + film_id + ".png");
            Files.copy(selectedFile.toPath(), ada2.toPath()); //dosyayı kopyalamyı sağlıyor. Ancak ya try catch içinde olacak yada throws olacak.

            BufferedImage bufferedImage = ImageIO.read(ada2);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);

            films_photos fp = new films_photos(film_id, ada2.getName(), ada2.getParent(), ada2.getPath());

            films_photosDAO fpdao = new films_photosDAO();

            int sonuc = fpdao.film_photo_dao_ekle_veya_guncelle(fp);

            switch (sonuc) {
                case 1:
                    vizyondaki_filmler_film_detay_photo.setImage(image);
                    break;
                case 0:
                    film_detay_guncelle_mesaj.setText("Bir hata meydana geldi. (Hata kodu: -32)");
                    break;
                case -1:
                    film_detay_guncelle_mesaj.setText("Bir hata meydana geldi. (Hata kodu: -33)");
                    break;
                case -2:
                    film_detay_guncelle_mesaj.setText("Bir hata meydana geldi. (Hata kodu: -34)");
                    break;
                default:
                    film_detay_guncelle_mesaj.setText("Bir hata meydana geldi. (Hata Kodu: -35)");
                    break;
            }
        }

    }

}
