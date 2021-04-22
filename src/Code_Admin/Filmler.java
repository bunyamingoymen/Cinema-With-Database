package Code_Admin;

import DAO.filmlerDAO;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class Filmler extends Eski_Filmler {

    public void filmler_combo(ComboBox<String> combo, Label uyari_mesaj) {
        filmlerDAO film_islemleri = new filmlerDAO();
        String[][] arr = film_islemleri.filmler_combo_doldur();
        combo.getItems().clear();
        if (arr.length == 0) {
            uyari_mesaj.setText("Kayıtlı Film Bulunamadı. Lütfen önce bir film ekleyiniz ekleyiniz.");

        } else {
            for (String[] arr1 : arr) {
                String v_filmler = arr1[0];
                combo.getItems().addAll(v_filmler);
            }

            combo.setPromptText("İstediğiniz filmi seçiniz.");
        }
    }
}
