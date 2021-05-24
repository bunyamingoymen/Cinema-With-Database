package Code_Standart_user;

import Pattern.Creator;
import DAO.user_photosDAO;
import entity.Center;
import entity.user_photos;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

public class Settings extends Kullanici_Islemleri {

    @FXML
    private void user_profile_change(MouseEvent event) throws IOException {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Pictures", "*.jpg", "*.jpeg", "*.png"));
        File selectedFile = fc.showOpenDialog(null);
        int user_id = Creator.getU().getUser_id();

        if (selectedFile == null) {
            guncelle_mesaj.setText("Bir resim seçmediniz.");
        } else {
            File ada = new File("src/lib/pic/users/" + user_id + ".png");
            ada.delete();

            File ada2 = new File("src/lib/pic/users/" + user_id + ".png");
            Files.copy(selectedFile.toPath(), ada2.toPath()); //dosyayı kopyalamyı sağlıyor. Ancak ya try catch içinde olacak yada throws olacak.

            BufferedImage bufferedImage = ImageIO.read(ada2);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);

            user_photos up = new user_photos(user_id, ada2.getName(), ada2.getParent(), ada2.getPath());

            user_photosDAO updao = new user_photosDAO();

            Center nw = new Center(up);

            int sonuc = updao.create_or_update(nw);

            switch (sonuc) {
                case 1:
                    profile_photo.setImage(image);
                    break;
                case 0:
                    guncelle_mesaj.setText("Bir hata meydana geldi. (Hata kodu: -32)");
                    break;
                case -1:
                    guncelle_mesaj.setText("Bir hata meydana geldi. (Hata kodu: -33)");
                    break;
                case -2:
                    guncelle_mesaj.setText("Bir hata meydana geldi. (Hata kodu: -34)");
                    break;
                default:
                    guncelle_mesaj.setText("Bir hata meydana geldi. (Hata Kodu: -35)");
                    break;
            }
        }

    }

    @FXML
    public void settings_giris(MouseEvent event) {
        pnl_settings.setVisible(true);
        home_page_icon.setVisible(true);

        pnl_vizyondaki_filmler.setVisible(false);
        pnl_haberler.setVisible(false);
        pnl_kampanyalar.setVisible(false);
        pnl_abonelik.setVisible(false);
        pnl_biletlerim.setVisible(false);
        pnl_sinema_salonlari.setVisible(false);
        pnl_film_detay.setVisible(false);
        home_page.setVisible(false);

        int user_id = Creator.getU().getUser_id();

        user_name.setText(Creator.getU().getUser_name());

        user_mail.setText(Creator.getU().getUser_mail());

        user_password.setText(Creator.getU().getUser_password());

        int control = new user_photosDAO().count(user_id);

        if (control == 1) {
            BufferedImage bufferedImage = null;
            try {
                String photo_path = new user_photosDAO().search_string(user_id);
                bufferedImage = ImageIO.read(new File(photo_path));
            } catch (IOException ex) {
                System.out.println(ex);
                guncelle_mesaj.setText("Bir Hata Meydana Geldi (Hata Kodu: -36)");
            }
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            profile_photo.setImage(image);
        } else if (control != 0) {
            guncelle_mesaj.setText("Bir Hata Meydana Geldi (Hata Kodu: -37)");
            System.out.println(control);
        }

    }
}
