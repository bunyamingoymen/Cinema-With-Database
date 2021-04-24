package Code_Standart_user;

import Creator.Creator;
import Creator.Mediator;
import DAO.kullanici_degerlendirmesiDAO;
import entity.kullanici_degerlendirmesi;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class Kullanici_Islemleri extends Biletlerim {

    @FXML
    public void bir_bos(MouseEvent event) {
        bir_ortak();
    }

    @FXML
    public void iki_bos(MouseEvent event) {
        iki_ortak();
    }

    @FXML
    public void uc_bos(MouseEvent event) {
        uc_ortak();
    }

    @FXML
    public void dort_bos(MouseEvent event) {
        dort_ortak();
    }

    @FXML
    public void bes_bos(MouseEvent event) {
        bes_ortak();
    }

    @FXML
    public void alti_bos(MouseEvent event) {
        alti_ortak();
    }

    @FXML
    public void yedi_bos(MouseEvent event) {
        yedi_ortak();
    }

    @FXML
    public void sekiz_bos(MouseEvent event) {
        sekiz_ortak();
    }

    @FXML
    public void dokuz_bos(MouseEvent event) {
        dokuz_ortak();
    }

    @FXML
    public void on_bos(MouseEvent event) {
        on_ortak();
    }

    @FXML
    public void bir_dolu(MouseEvent event) {
        bir_ortak();
    }

    @FXML
    public void iki_dolu(MouseEvent event) {
        iki_ortak();
    }

    @FXML
    public void uc_dolu(MouseEvent event) {
        uc_ortak();
    }

    @FXML
    public void dort_dolu(MouseEvent event) {
        dort_ortak();
    }

    @FXML
    public void bes_dolu(MouseEvent event) {
        bes_ortak();
    }

    @FXML
    public void alti_dolu(MouseEvent event) {
        alti_ortak();
    }

    @FXML
    public void yedi_dolu(MouseEvent event) {
        yedi_ortak();
    }

    @FXML
    public void sekiz_dolu(MouseEvent event) {
        sekiz_ortak();
    }

    @FXML
    public void dokuz_dolu(MouseEvent event) {
        dokuz_ortak();
    }

    @FXML
    public void on_dolu(MouseEvent event) {
        on_ortak();
    }

    public void bir_ortak() {
        bir_dolu.setVisible(true);
        iki_bos.setVisible(true);
        uc_bos.setVisible(true);
        dort_bos.setVisible(true);
        bes_bos.setVisible(true);
        alti_bos.setVisible(true);
        yedi_bos.setVisible(true);
        sekiz_bos.setVisible(true);
        dokuz_bos.setVisible(true);
        on_bos.setVisible(true);

        bir_bos.setVisible(false);
        iki_dolu.setVisible(false);
        uc_dolu.setVisible(false);
        dort_dolu.setVisible(false);
        bes_dolu.setVisible(false);
        alti_dolu.setVisible(false);
        yedi_dolu.setVisible(false);
        sekiz_dolu.setVisible(false);
        dokuz_dolu.setVisible(false);
        on_dolu.setVisible(false);

        film_detay_verilen_puan.setText("1");
    }

    public void iki_ortak() {
        bir_dolu.setVisible(true);
        iki_dolu.setVisible(true);
        uc_bos.setVisible(true);
        dort_bos.setVisible(true);
        bes_bos.setVisible(true);
        alti_bos.setVisible(true);
        yedi_bos.setVisible(true);
        sekiz_bos.setVisible(true);
        dokuz_bos.setVisible(true);
        on_bos.setVisible(true);

        bir_bos.setVisible(false);
        iki_bos.setVisible(false);
        uc_dolu.setVisible(false);
        dort_dolu.setVisible(false);
        bes_dolu.setVisible(false);
        alti_dolu.setVisible(false);
        yedi_dolu.setVisible(false);
        sekiz_dolu.setVisible(false);
        dokuz_dolu.setVisible(false);
        on_dolu.setVisible(false);

        film_detay_verilen_puan.setText("2");
    }

    public void uc_ortak() {
        bir_dolu.setVisible(true);
        iki_dolu.setVisible(true);
        uc_dolu.setVisible(true);
        dort_bos.setVisible(true);
        bes_bos.setVisible(true);
        alti_bos.setVisible(true);
        yedi_bos.setVisible(true);
        sekiz_bos.setVisible(true);
        dokuz_bos.setVisible(true);
        on_bos.setVisible(true);

        bir_bos.setVisible(false);
        iki_bos.setVisible(false);
        uc_bos.setVisible(false);
        dort_dolu.setVisible(false);
        bes_dolu.setVisible(false);
        alti_dolu.setVisible(false);
        yedi_dolu.setVisible(false);
        sekiz_dolu.setVisible(false);
        dokuz_dolu.setVisible(false);
        on_dolu.setVisible(false);

        film_detay_verilen_puan.setText("3");
    }

    public void dort_ortak() {
        bir_dolu.setVisible(true);
        iki_dolu.setVisible(true);
        uc_dolu.setVisible(true);
        dort_dolu.setVisible(true);
        bes_bos.setVisible(true);
        alti_bos.setVisible(true);
        yedi_bos.setVisible(true);
        sekiz_bos.setVisible(true);
        dokuz_bos.setVisible(true);
        on_bos.setVisible(true);

        bir_bos.setVisible(false);
        iki_bos.setVisible(false);
        uc_bos.setVisible(false);
        dort_bos.setVisible(false);
        bes_dolu.setVisible(false);
        alti_dolu.setVisible(false);
        yedi_dolu.setVisible(false);
        sekiz_dolu.setVisible(false);
        dokuz_dolu.setVisible(false);
        on_dolu.setVisible(false);

        film_detay_verilen_puan.setText("4");
    }

    public void bes_ortak() {
        bir_dolu.setVisible(true);
        iki_dolu.setVisible(true);
        uc_dolu.setVisible(true);
        dort_dolu.setVisible(true);
        bes_dolu.setVisible(true);
        alti_bos.setVisible(true);
        yedi_bos.setVisible(true);
        sekiz_bos.setVisible(true);
        dokuz_bos.setVisible(true);
        on_bos.setVisible(true);

        bir_bos.setVisible(false);
        iki_bos.setVisible(false);
        uc_bos.setVisible(false);
        dort_bos.setVisible(false);
        bes_bos.setVisible(false);
        alti_dolu.setVisible(false);
        yedi_dolu.setVisible(false);
        sekiz_dolu.setVisible(false);
        dokuz_dolu.setVisible(false);
        on_dolu.setVisible(false);

        film_detay_verilen_puan.setText("5");
    }

    public void alti_ortak() {
        bir_dolu.setVisible(true);
        iki_dolu.setVisible(true);
        uc_dolu.setVisible(true);
        dort_dolu.setVisible(true);
        bes_dolu.setVisible(true);
        alti_dolu.setVisible(true);
        yedi_bos.setVisible(true);
        sekiz_bos.setVisible(true);
        dokuz_bos.setVisible(true);
        on_bos.setVisible(true);

        bir_bos.setVisible(false);
        iki_bos.setVisible(false);
        uc_bos.setVisible(false);
        dort_bos.setVisible(false);
        bes_bos.setVisible(false);
        alti_bos.setVisible(false);
        yedi_dolu.setVisible(false);
        sekiz_dolu.setVisible(false);
        dokuz_dolu.setVisible(false);
        on_dolu.setVisible(false);

        film_detay_verilen_puan.setText("6");
    }

    public void yedi_ortak() {
        bir_dolu.setVisible(true);
        iki_dolu.setVisible(true);
        uc_dolu.setVisible(true);
        dort_dolu.setVisible(true);
        bes_dolu.setVisible(true);
        alti_dolu.setVisible(true);
        yedi_dolu.setVisible(true);
        sekiz_bos.setVisible(true);
        dokuz_bos.setVisible(true);
        on_bos.setVisible(true);

        bir_bos.setVisible(false);
        iki_bos.setVisible(false);
        uc_bos.setVisible(false);
        dort_bos.setVisible(false);
        bes_bos.setVisible(false);
        alti_bos.setVisible(false);
        yedi_bos.setVisible(false);
        sekiz_dolu.setVisible(false);
        dokuz_dolu.setVisible(false);
        on_dolu.setVisible(false);

        film_detay_verilen_puan.setText("7");
    }

    public void sekiz_ortak() {
        bir_dolu.setVisible(true);
        iki_dolu.setVisible(true);
        uc_dolu.setVisible(true);
        dort_dolu.setVisible(true);
        bes_dolu.setVisible(true);
        alti_dolu.setVisible(true);
        yedi_dolu.setVisible(true);
        sekiz_dolu.setVisible(true);
        dokuz_bos.setVisible(true);
        on_bos.setVisible(true);

        bir_bos.setVisible(false);
        iki_bos.setVisible(false);
        uc_bos.setVisible(false);
        dort_bos.setVisible(false);
        bes_bos.setVisible(false);
        alti_bos.setVisible(false);
        yedi_bos.setVisible(false);
        sekiz_bos.setVisible(false);
        dokuz_dolu.setVisible(false);
        on_dolu.setVisible(false);

        film_detay_verilen_puan.setText("8");
    }

    public void dokuz_ortak() {
        bir_dolu.setVisible(true);
        iki_dolu.setVisible(true);
        uc_dolu.setVisible(true);
        dort_dolu.setVisible(true);
        bes_dolu.setVisible(true);
        alti_dolu.setVisible(true);
        yedi_dolu.setVisible(true);
        sekiz_dolu.setVisible(true);
        dokuz_dolu.setVisible(true);
        on_bos.setVisible(true);

        bir_bos.setVisible(false);
        iki_bos.setVisible(false);
        uc_bos.setVisible(false);
        dort_bos.setVisible(false);
        bes_bos.setVisible(false);
        alti_bos.setVisible(false);
        yedi_bos.setVisible(false);
        sekiz_bos.setVisible(false);
        dokuz_bos.setVisible(false);
        on_dolu.setVisible(false);

        film_detay_verilen_puan.setText("9");
    }

    public void on_ortak() {
        bir_dolu.setVisible(true);
        iki_dolu.setVisible(true);
        uc_dolu.setVisible(true);
        dort_dolu.setVisible(true);
        bes_dolu.setVisible(true);
        alti_dolu.setVisible(true);
        yedi_dolu.setVisible(true);
        sekiz_dolu.setVisible(true);
        dokuz_dolu.setVisible(true);
        on_dolu.setVisible(true);

        bir_bos.setVisible(false);
        iki_bos.setVisible(false);
        uc_bos.setVisible(false);
        dort_bos.setVisible(false);
        bes_bos.setVisible(false);
        alti_bos.setVisible(false);
        yedi_bos.setVisible(false);
        sekiz_bos.setVisible(false);
        dokuz_bos.setVisible(false);
        on_bos.setVisible(false);

        film_detay_verilen_puan.setText("10");
    }

    @FXML
    public void film_detay_sifirla(ActionEvent event) {
        bir_bos.setVisible(true);
        iki_bos.setVisible(true);
        uc_bos.setVisible(true);
        dort_bos.setVisible(true);
        bes_bos.setVisible(true);
        alti_bos.setVisible(true);
        yedi_bos.setVisible(true);
        sekiz_bos.setVisible(true);
        dokuz_bos.setVisible(true);
        on_bos.setVisible(true);

        bir_dolu.setVisible(false);
        iki_dolu.setVisible(false);
        uc_dolu.setVisible(false);
        dort_dolu.setVisible(false);
        bes_dolu.setVisible(false);
        alti_dolu.setVisible(false);
        yedi_dolu.setVisible(false);
        sekiz_dolu.setVisible(false);
        dokuz_dolu.setVisible(false);
        on_dolu.setVisible(false);

        film_detay_verilen_puan.setText("");

    }

    @FXML
    public void film_detay_puan_ver(ActionEvent event) {
        int film_id = Integer.parseInt(film_detay_film_id.getText());
        int user_id = Creator.getU().getUser_id();
        int degerlendirme = Integer.parseInt(film_detay_verilen_puan.getText());

        kullanici_degerlendirmesi k = new kullanici_degerlendirmesi(user_id, film_id, degerlendirme);

        kullanici_degerlendirmesiDAO kdao = new kullanici_degerlendirmesiDAO();

        int sonuc = kdao.kullanici_degerlendirmesi_yeni_degerlendirme(k);
        Mediator m = new Mediator();
        switch (sonuc) {
            case 1:
            case 2:
                film_detay_uyari_mesaj.setText("İşlem Başarılı Bir Şekilde Gerçekleştirildi.");
                break;
            case -1:
                film_detay_uyari_mesaj.setText("Bir hata meydana geldi (Hata kodu: -24)");
                break;
            case -2:
                film_detay_uyari_mesaj.setText("Bir hata meydana geldi (Hata kodu: -25)");
                break;
            case -3:
                film_detay_uyari_mesaj.setText("Bir hata meydana geldi (Hata kodu: -26)");
                break;
            case -4:
                film_detay_uyari_mesaj.setText("Bir hata meydana geldi (Hata kodu: -27)");
                break;
            default:
                film_detay_uyari_mesaj.setText("Bir hata meydana geldi (Hata kodu: -28)");
                break;
        }
    }
}
