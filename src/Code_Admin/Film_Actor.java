package Code_Admin;

import Pattern.Creator;
import entity.Center;
import entity.film_actor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class Film_Actor extends Filmler {

    @FXML
    public void film_actor_ekle_giris(ActionEvent event) {
        film_actor_ekle_pane.setVisible(true);
        film_actor_grid.setVisible(false);
        film_actor_sil_emin_misin_pane.setVisible(false);

        film_actor_geri_tusu.setVisible(false);
        film_actor_ekle_geri_tusu.setVisible(true);

        filmler_combo(film_actor_ekle_filmler, film_actor_ekle_uyari_mesaj);
        film_actor_ekle_actor_id.setText(film_actor_table_actor_id.getText());

    }

    @FXML
    public void film_actor_ekle_sifirla(ActionEvent event) {
        filmler_combo(film_actor_ekle_filmler, film_actor_ekle_uyari_mesaj);
    }

    @FXML
    public void film_actor_ekle_ekle(ActionEvent event) {
        if (film_actor_ekle_filmler.getValue() == null) {
            film_actor_ekle_uyari_mesaj.setText("Lütfen önce bir film seçiniz");
        } else {
            String[][] arr = Creator.filmlerDao().select();
            String secilen = film_actor_ekle_filmler.getValue();
            int film_id = -1;

            for (int i = 0; i < arr.length; i++) {
                if (arr[i][0].equals(secilen)) {
                    film_id = Integer.parseInt(arr[i][1]);
                    break;
                }
            }

            if (film_id == -1) {
                film_actor_ekle_uyari_mesaj.setText("Bir hata meydana geldi Lütfen daha sonra tekrar deneyiniz");
            } else {
                int actor_id = Integer.parseInt(film_actor_ekle_actor_id.getText());
                film_actor fa = new film_actor(film_id, actor_id);
                Center nw = new Center(fa);
                int sonuc = Creator.film_actorDao().create(nw);
                if (sonuc == 1) {
                    film_actor_ekle_uyari_mesaj.setText("İşlem Başarılı Bir Şekilde Gerçekleştirildi");
                } else {
                    film_actor_ekle_uyari_mesaj.setText("Bir hata meydana geldi.");
                }
            }

        }
    }

    @FXML
    public void film_actor_sil_silmekten_emin_vazgec(ActionEvent event) {
        film_actor_sil_emin_misin_pane.setVisible(false);
    }

    @FXML
    public void film_actor_sil_silmekten_emin_sil(ActionEvent event) {
        Creator.film_actorDao().delete(Integer.parseInt(film_actor_id.getText()));
        
        Creator.actor().film_actor_table(aktorler_grid, film_actor_grid, aktorler_geri_tusu, film_actor_geri_tusu, film_actor_film_name, film_actor_film_type, film_actor_yonetmen, film_actor_sil, table_film_actor, filterField_film_actor, film_actor_sil_emin_misin_pane, film_actor_id);

        film_actor_sil_emin_misin_pane.setVisible(false);

        film_actor_table_uyari_mesaj.setText("İşlem Başarılı Bir şekilde gerçekleştirildi.");

    }

    @FXML
    public void film_actor_geri(MouseEvent event) {
        aktorler_grid.setVisible(true);
        film_actor_pane.setVisible(false);
        film_actor_geri_tusu.setVisible(false);
        aktorler_geri_tusu.setVisible(true);

    }

    @FXML
    public void film_actor_ekle_geri(MouseEvent event) {
        film_actor_grid.setVisible(true);
        film_actor_ekle_pane.setVisible(false);

        Creator.actor().film_actor_table(aktorler_grid, film_actor_grid, aktorler_geri_tusu, film_actor_geri_tusu, film_actor_film_name, film_actor_film_type, film_actor_yonetmen, film_actor_sil, table_film_actor, filterField_film_actor, film_actor_sil_emin_misin_pane, film_actor_id);

        film_actor_ekle_geri_tusu.setVisible(false);
        film_actor_geri_tusu.setVisible(true);

    }
}
