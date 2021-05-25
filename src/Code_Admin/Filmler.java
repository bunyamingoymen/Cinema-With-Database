package Code_Admin;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import Pattern.Creator;

public class Filmler extends Eski_Filmler {

    public void filmler_combo(ComboBox<String> combo, Label uyari_mesaj) {
        String[][] arr = Creator.filmlerDao().select();
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
