package Code_Standart_user;

import DAO.vizyondaki_filmlerDAO;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class Par extends Variables {

    public void vizyondaki_filmler_combo(ComboBox<String> combo, Label uyari_mesaj) {
        vizyondaki_filmlerDAO vizyondaki_film_islemleri = new vizyondaki_filmlerDAO();
        String[] arr = vizyondaki_film_islemleri.vizyondaki_filmler_combo_doldur();
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
}
