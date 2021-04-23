package Creator;

import DAO.vizyondaki_filmlerDAO;
import entity.vizyondaki_filmler;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class Mediator {

    private static ObservableList<vizyondaki_filmler> data_vizyondaki_filmler_bir;

    public static int data_vizyondaki_filmler_bir_guncellendi_mi;

    public static ObservableList<vizyondaki_filmler> vizyondaki_filmler_bir(ObservableList<vizyondaki_filmler> data, Label film_detay_film_id, Label film_detay_film_adi, Label film_detay_film_turu, Label film_detay_film_suresi, Label film_detay_yonetmen, Label film_detay_kalkis_tarihi, Label film_detay_kullanici_puani, AnchorPane pnl_vizyondaki_filmler, AnchorPane pnl_eski_filmler, AnchorPane pnl_film_detay) {
        if (data_vizyondaki_filmler_bir == null || data_vizyondaki_filmler_bir_guncellendi_mi == 1) {
            vizyondaki_filmlerDAO vf = new vizyondaki_filmlerDAO();
            data_vizyondaki_filmler_bir = vf.vizyondaki_filmler_select_butonlu(data, film_detay_film_id, film_detay_film_adi, film_detay_film_turu, film_detay_film_suresi, film_detay_yonetmen, film_detay_kalkis_tarihi, film_detay_kullanici_puani, pnl_vizyondaki_filmler, pnl_eski_filmler, pnl_film_detay);
            data_vizyondaki_filmler_bir_guncellendi_mi = 0;
        } else {
            vizyondaki_filmler v = new vizyondaki_filmler();
            Button detay = new Button();
            detay.setText("Detay");
            detay.setStyle("-fx-background-color : #393351; -fx-background-radius :  20; -fx-text-fill: white");

            v.vizyondaki_filmler_controller(detay, film_detay_film_id, film_detay_film_adi, film_detay_film_turu, film_detay_film_suresi, film_detay_yonetmen, film_detay_kalkis_tarihi, film_detay_kullanici_puani, pnl_vizyondaki_filmler, pnl_eski_filmler, pnl_film_detay);

        }
        return data_vizyondaki_filmler_bir;
    }

    public Mediator() {
        data_vizyondaki_filmler_bir_guncellendi_mi = 1;
    }

}
