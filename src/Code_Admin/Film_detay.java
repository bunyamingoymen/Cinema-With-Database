/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Code_Admin;

import javafx.fxml.FXML;

/**
 *
 * @author bgoym
 */
public class Film_detay extends Film_Actor {

    @FXML
    public void film_detay_geri() {

        pnl_film_detay.setVisible(false);

        if (film_detay_id_oncesi.getText().equals("vizyon_id")) {
            pnl_vizyondaki_filmler.setVisible(true);

            vizyondaki_filmler_geri_tusu.setVisible(true);
            vizyondaki_filmler_ekle_geri_tusu.setVisible(false);
            //vizyondaki_filmler_degistir_geri_tusu.setVisible(false);
            vizyondaki_filmler_gosterim_geri_tusu.setVisible(false);

            vizyondaki_filmler_resimli_gosterim.setVisible(true);
            vizyondaki_filmler_grid.setVisible(false);
            vizyondaki_filmler_ekle_pane.setVisible(false);
            //vizyondaki_filmler_degistir_pane.setVisible(false);

            vizyondaki_filmler_gosterim_oncesi_ortak();

        } else if (film_detay_id_oncesi.getText().equals("eski_id")) {

            pnl_eski_filmler.setVisible(true);

            eski_filmler_geri_tusu.setVisible(true);
            eski_filmler_ekle_geri_tusu.setVisible(false);
            eski_filmler_degistir_geri_tusu.setVisible(false);

            eski_filmler_grid.setVisible(true);
            eski_filmler_ekle_pane.setVisible(false);
            eski_filmler_degistir_pane.setVisible(false);

            eski_filmler_table_aboneli();

        } else {
            System.out.println("Hata");
        }
    }

    @FXML
    public void film_detay_sil() {
        film_detay_sil_emin_misin_pane.setVisible(true);
        if (film_detay_id_oncesi.getText().equals("vizyon_id")) {

            film_detay_sadece_sil_butonu.setText("Sadece Vizyondan Sil Ve Eski Filmlere Ekle");

        } else if (film_detay_id_oncesi.getText().equals("eski_id")) {

            film_detay_sadece_sil_butonu.setText("Sadece Eskiden Sil Ve Eski Filmlere Ekle");

        } else {

            System.out.println("Hata");

        }
    }

    @FXML
    public void film_detay_sil_vazgec() {
        film_detay_sil_emin_misin_pane.setVisible(false);
    }

    @FXML
    public void film_detay_sil_sadece_sil() {
        if (film_detay_id_oncesi.getText().equals("vizyon_id")) {

        } else if (film_detay_id_oncesi.getText().equals("eski_id")) {

        } else {

            System.out.println("Hata");

        }
    }

    @FXML
    public void film_detay_sil_tamamen_sil() {
        if (film_detay_id_oncesi.getText().equals("vizyon_id")) {

        } else if (film_detay_id_oncesi.getText().equals("eski_id")) {

        } else {

            System.out.println("Hata");

        }
    }

}
