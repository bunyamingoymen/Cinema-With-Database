package Code_Admin;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import Pattern.Creator;

public class Par extends Variables {

    public void home_page() {
        vizyondaki_film_sayisi.setText(String.valueOf(Creator.vizyondaki_filmlerDao().count()));

        eski_film_sayisi.setText(String.valueOf(Creator.eski_filmlerDao().count()));

        haber_sayisi.setText(String.valueOf(Creator.haberlerDao().count()));

        kampanya_sayisi.setText(String.valueOf(Creator.kampanyalarDao().count()));

        sinema_salonu_sayisi.setText(String.valueOf(Creator.sinema_salonlariDao().count()));

        yonetmen_sayisi.setText(String.valueOf(Creator.yonetmenlerDao().count()));

        aktor_sayisi.setText(String.valueOf(Creator.actorDao().count()));

        kullanici_sayisi.setText(String.valueOf(Creator.usersDao().count()));

    }

    public void yonetmen_combo(ComboBox<String> combo, Label uyari_mesaji) {

        String[][] arr = Creator.yonetmenlerDao().select();

        combo.getItems().clear();

        if (arr.length == 0) {

            uyari_mesaji.setText("Kayıtlı Yönetmen Bulunamadı. Lütfen önce bir yönetmen ekleyiniz.");

        } else {

            for (String[] arr1 : arr) {
                String name_surname = arr1[0];
                combo.getItems().addAll(name_surname);
            }

            combo.setPromptText("İstediğiniz yönetmeni seçiniz.");
        }

    }

    public void geri_don_admin() {
        btn1.setText("Filmler");
        btn2.setText("Duyurular");

        btn3.setVisible(true);
        btn4.setVisible(true);
        btn5.setVisible(true);

        pnl_vizyondaki_filmler.setVisible(false);
        pnl_seans.setVisible(false);
        pnl_sinema_salonlari.setVisible(false);
        pnl_eski_filmler.setVisible(false);
        pnl_kampanyalar.setVisible(false);
        pnl_haberler.setVisible(false);
        pnl_yonetmenler.setVisible(false);
        pnl_aktorler.setVisible(false);
        pnl_kullanici_islemleri.setVisible(false);

        home_pane.setVisible(true);
        home_page();

        home.setVisible(false);
    }

    public void vizyondaki_filmler_combo(ComboBox<String> combo, Label uyari_mesaj) {
        String[] arr = Creator.vizyondaki_filmlerDao().select_string();
        combo.getItems().clear();
        if (arr.length == 0) {
            uyari_mesaj.setText("Kayıtlı Vizyondaki Film Bulunamadı. Lütfen önce bir vizyona film ekleyiniz ekleyiniz.");

        } else {
            for (int i = 0; i < arr.length; i++) {
                String v_filmler = arr[i];
                combo.getItems().addAll(v_filmler);
            }

            combo.setPromptText("İstediğiniz filmi seçiniz.");
        }
    }

    public void sinema_salonlari_goruntule_combo(ComboBox<String> combo, Label uyari_mesaj) {

        String[] arr = Creator.sinema_salonlariDao().select();
        combo.getItems().clear();
        if (arr.length == 0) {
            uyari_mesaj.setText("Kayıtlı Haber Bulunamadı. Lütfen Önce Bir Haber Ekleyiniz.");

        } else {
            for (String k : arr) {
                combo.getItems().addAll(k);
            }
            combo.setPromptText("İstediğiniz Haberi seçiniz.");
        }
    }
    
}
