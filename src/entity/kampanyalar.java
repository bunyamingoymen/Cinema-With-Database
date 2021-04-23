package entity;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class kampanyalar extends duyurular {

    private Button sil;

    public kampanyalar() {
    }

    public kampanyalar(int kampanya_id, int hangi_kullanici_turu, String Title, String Kampanya, String Tarih, String Kampanya_Kategorisi, Button sil, Pane kampanyalar_sil_emin_misin_pane, Label kampanyalar_silmekten_emin_kampanya_id) {
        super(kampanya_id, hangi_kullanici_turu, Title, Kampanya, Tarih, Kampanya_Kategorisi);
        this.sil = sil;

        sil.setOnAction(e -> {
            kampanyalar_silmekten_emin_kampanya_id.setText(String.valueOf(kampanya_id));
            kampanyalar_sil_emin_misin_pane.setVisible(true);
        });
    }

    public kampanyalar(int hangi_kullanici_turu, String Title, String Kampanya, String Tarih, String Kampanya_Kategorisi) {
        super(hangi_kullanici_turu, Title, Kampanya, Tarih, Kampanya_Kategorisi);
    }

    public kampanyalar(int kampanya_id, int hangi_kullanici_turu, String Title, String Kampanya, String Tarih, String Kampanya_Kategorisi) {
        super(kampanya_id, hangi_kullanici_turu, Title, Kampanya, Tarih, Kampanya_Kategorisi);
    }

    public kampanyalar(String Title, String Kampanya, String Tarih, String Kampanya_Kategorisi) {
        super(Title, Kampanya, Tarih, Kampanya_Kategorisi);
    }

    public Button getSil() {
        return sil;
    }

    public void setSil(Button sil) {
        this.sil = sil;
    }

}
