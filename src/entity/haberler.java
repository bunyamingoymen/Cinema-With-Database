package entity;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class haberler extends duyurular {

    private Button sil;

    public haberler() {
    }

    public haberler(int haber_id, int hangi_kullanici_turu, String Title, String Haber, String Tarih, String Haber_Kategorisi, Button sil, Label haberler_silmekten_emin_haber_id, Pane haberler_sil_emin_misin_pane) {
        super(haber_id, hangi_kullanici_turu, Title, Haber, Tarih, Haber_Kategorisi);

        this.sil = sil;

        sil.setOnAction(e -> {
            haberler_silmekten_emin_haber_id.setText(String.valueOf(haber_id));
            haberler_sil_emin_misin_pane.setVisible(true);
        });
    }

    public haberler(int haber_id, int hangi_kullanici_turu, String Title, String Haber, String Tarih, String Haber_Kategorisi) {
        super(haber_id, hangi_kullanici_turu, Title, Haber, Tarih, Haber_Kategorisi);
    }

    public haberler(int hangi_kullanici_turu, String Title, String Haber, String Tarih, String Haber_Kategorisi) {
        super(hangi_kullanici_turu, Title, Haber, Tarih, Haber_Kategorisi);
    }

    public haberler(String Title, String Haber, String Tarih, String Haber_Kategorisi) {
        super(Title, Haber, Tarih, Haber_Kategorisi);
    }

    public Button getSil() {
        return sil;
    }

    public void setSil(Button sil) {
        this.sil = sil;
    }

}
