package Code_Admin;

import DAO.films_photosDAO;
import DAO_Controller.Vizyondaki_Filmler_Controller;
import entity.vizyondaki_filmler;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javax.imageio.ImageIO;
import Pattern.Creator;
import Pattern.Mediator;
import Pattern.Table;
import java.util.LinkedList;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.cell.PropertyValueFactory;

public class Vizyondaki_Filmler extends Sinema_Salonlari_Koltık_Dolu_Bos {

    @FXML
    public void vizyondaki_filmler_geri(MouseEvent event) {
        geri_don_admin();
    }

    @FXML
    public void vizyondaki_filmler_ekle_geri(MouseEvent event) {
        vizyondaki_filmler_resimli_gosterim.setVisible(true);
        vizyondaki_filmler_ekle_pane.setVisible(false);

        vizyondaki_filmler_geri_tusu.setVisible(true);
        vizyondaki_filmler_ekle_geri_tusu.setVisible(false);

        vizyondaki_filmler_gosterim_oncesi_ortak();

    }



    @FXML
    public void vizyondaki_filmler_ekle_giris(ActionEvent event) {

        vizyondaki_filmler_grid.setVisible(false);
        vizyondaki_filmler_resimli_gosterim.setVisible(false);
        vizyondaki_filmler_ekle_pane.setVisible(true);
        yonetmen_combo(vizyondaki_filmler_ekle_yonetmenler, vizyondaki_filmler_ekle_uyari_mesaj);
        vizyondaki_filmler_geri_tusu.setVisible(false);
        vizyondaki_filmler_ekle_geri_tusu.setVisible(true);
        vizyondaki_filmler_gosterim_geri_tusu.setVisible(false);
    }

    @FXML
    public void vizyondaki_filmler_ekle(ActionEvent event) {
        String film_name = vizyondaki_filmler_ekle_film_adi.getText();
        int film_suresi = 0;
        int control = -1;
        try {
            film_suresi = Integer.parseInt(vizyondaki_filmler_ekle_film_suresi.getText());
            control = 1;
        } catch (NumberFormatException e) {
            vizyondaki_filmler_ekle_uyari_mesaj.setText("Lütfen film süresine sadeca sayı giriniz.(dk olarak)");
        }
        if (control != -1) {
            String film_type = vizyondaki_filmler_ekle_film_type.getText();
            String yonetmen = vizyondaki_filmler_ekle_yonetmenler.getValue();
            if ((film_name.length() == 0) || (vizyondaki_filmler_film_suresi.getText().length() == 0) || (film_type.length() == 0) || (vizyondaki_filmler_ekle_kalkis.getValue() == null) || (yonetmen == null)) {
                vizyondaki_filmler_ekle_uyari_mesaj.setText("Lütfen gerekli yerleri doldurunuz.");
            } else {
                LocalDate kalkis = vizyondaki_filmler_ekle_kalkis.getValue();
                String[][] arr = Creator.yonetmenlerDao().select();
                int yonetmen_id = 0;
                for (int i = 0; i < arr.length; i++) {
                    if (arr[i][0].equals(yonetmen)) {
                        yonetmen_id = Integer.valueOf(arr[i][1]);
                        i = arr.length + 1;
                    }
                }

                Mediator m = new Mediator();

                int sonuc = m.vizyondaki_filmler_ve_filmler_ekle(film_name, film_suresi, film_type, yonetmen_id, kalkis);

                if (sonuc == 1) {
                    vizyondaki_filmler_ekle_uyari_mesaj.setText("İşlem Başarılı Bir Şekilde Gerçekleşti.");
                }

            }
        }
    }

    @FXML
    public void vizyondaki_filmler_ekle_sifirla(ActionEvent event) {
        vizyondaki_filmler_ekle_film_adi.setText("");
        vizyondaki_filmler_ekle_film_suresi.setText("");
        vizyondaki_filmler_ekle_film_type.setText("");
        vizyondaki_filmler_ekle_kalkis.setValue(null);
        vizyondaki_filmler_ekle_yonetmenler.getItems().clear();
        vizyondaki_filmler_ekle_yonetmenler.setPromptText("İstediğiniz yönetmeni seçiniz.");
        yonetmen_combo(vizyondaki_filmler_ekle_yonetmenler, vizyondaki_filmler_ekle_uyari_mesaj);
    }

    public void vizyondaki_filmler_table_admin() {

        ObservableList<vizyondaki_filmler> data = Table.vizyondaki_filmler(vizyondaki_filmler_detay_film_id, vizyondaki_filmler_detay_film_adi, vizyondaki_filmler_detay_film_turu, vizyondaki_filmler_detay_film_suresi, vizyondaki_filmler_detay_yonetmen, vizyondaki_filmler_detay_kalkis_tarihi, vizyondaki_filmler_detay_kullanici_puani, pnl_vizyondaki_filmler, pnl_eski_filmler, pnl_film_detay, film_detay_aldigi_odul_sayisi, film_detay_hangi_abone_turu, film_detay_kalksi_tarihi_oncesi, film_detay_aldigi_odul_sayisi_oncesi, film_detay_hangi_abone_turu_oncesi, film_detay_id, film_detay_id_oncesi, film_detay_ana_pane, film_detay_sil_emin_misin_pane, film_detay_guncelle_pane);

        vizyondaki_filmler_film_adi.setCellValueFactory(new PropertyValueFactory("film_name"));
        vizyondaki_filmler_film_type.setCellValueFactory(new PropertyValueFactory("film_type"));
        vizyondaki_filmler_film_suresi.setCellValueFactory(new PropertyValueFactory("film_suresi"));
        vizyondaki_filmler_yonetmen.setCellValueFactory(new PropertyValueFactory("yonetmen_ad_soyad"));
        vizyondaki_filmler_kalkis.setCellValueFactory(new PropertyValueFactory("vizyondan_kalkis_tarihi"));
        vizyondaki_filmler_kullanici_puani.setCellValueFactory(new PropertyValueFactory("kullanici_puani"));
        vizyondaki_filmler_detay.setCellValueFactory(new PropertyValueFactory("film_detayi"));

        FilteredList<vizyondaki_filmler> filteredData = new FilteredList<>(data, b -> true);

        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(viz -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (viz.getFilm_name().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (viz.getFilm_type().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(viz.getFilm_suresi()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (viz.getYonetmen_ad_soyad().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (viz.getVizyondan_kalkis_tarihi().toString().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(viz.getKullanici_puani()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }

            });
        });

        SortedList<vizyondaki_filmler> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(table_vizyondaki_filmler.comparatorProperty());

        table_vizyondaki_filmler.setItems(sortedData);
    }

    @FXML
    public void vizyondaki_filmler_imageview_bir(MouseEvent event) {
        imageview_gosterim_ortak(vizyondaki_filmler_gosterim_bir, Integer.parseInt(vizyondaki_filmler_gosterim_id_bir.getText()));
    }

    @FXML
    public void vizyondaki_filmler_imageview_iki(MouseEvent event) {
        imageview_gosterim_ortak(vizyondaki_filmler_gosterim_iki, Integer.parseInt(vizyondaki_filmler_gosterim_id_iki.getText()));
    }

    @FXML
    public void vizyondaki_filmler_imageview_uc(MouseEvent event) {
        imageview_gosterim_ortak(vizyondaki_filmler_gosterim_uc, Integer.parseInt(vizyondaki_filmler_gosterim_id_uc.getText()));
    }

    @FXML
    public void vizyondaki_filmler_imageview_dort(MouseEvent event) {
        imageview_gosterim_ortak(vizyondaki_filmler_gosterim_dort, Integer.parseInt(vizyondaki_filmler_gosterim_id_dort.getText()));
    }

    @FXML
    public void vizyondaki_filmler_imageview_bes(MouseEvent event) {
        imageview_gosterim_ortak(vizyondaki_filmler_gosterim_bes, Integer.parseInt(vizyondaki_filmler_gosterim_id_bes.getText()));
    }

    @FXML
    public void vizyondaki_filmler_imageview_alti(MouseEvent event) {
        imageview_gosterim_ortak(vizyondaki_filmler_gosterim_alti, Integer.parseInt(vizyondaki_filmler_gosterim_id_alti.getText()));
    }

    @FXML
    public void vizyondaki_filmler_imageview_yedi(MouseEvent event) {
        imageview_gosterim_ortak(vizyondaki_filmler_gosterim_yedi, Integer.parseInt(vizyondaki_filmler_gosterim_id_yedi.getText()));
    }

    @FXML
    public void vizyondaki_filmler_imageview_sekiz(MouseEvent event) {
        imageview_gosterim_ortak(vizyondaki_filmler_gosterim_sekiz, Integer.parseInt(vizyondaki_filmler_gosterim_id_sekiz.getText()));
    }

    @FXML
    public void vizyondaki_filmler_imageview_dokuz(MouseEvent event) {
        imageview_gosterim_ortak(vizyondaki_filmler_gosterim_dokuz, Integer.parseInt(vizyondaki_filmler_gosterim_id_dokuz.getText()));
    }

    @FXML
    public void vizyondaki_filmler_imageview_on(MouseEvent event) {
        imageview_gosterim_ortak(vizyondaki_filmler_gosterim_on, Integer.parseInt(vizyondaki_filmler_gosterim_id_on.getText()));
    }

    public void imageview_gosterim_ortak(ImageView img, int film_id) {
        pnl_vizyondaki_filmler.setVisible(false);
        pnl_film_detay.setVisible(true);

        int vizyondaki_film_id = Creator.vizyondaki_filmlerDao().search_int(film_id, 0, 2);

        vizyondaki_filmler_detay_kalkis_tarihi.setVisible(true);
        film_detay_aldigi_odul_sayisi.setVisible(false);
        film_detay_hangi_abone_turu.setVisible(false);

        film_detay_kalksi_tarihi_oncesi.setVisible(true);
        film_detay_aldigi_odul_sayisi_oncesi.setVisible(false);
        film_detay_hangi_abone_turu_oncesi.setVisible(false);
        
        film_detay_id_oncesi.setText("vizyon_id");
        
        film_detay_ana_pane.setVisible(true);
        film_detay_sil_emin_misin_pane.setVisible(false);
        film_detay_guncelle_pane.setVisible(false);

        vizyondaki_filmler_detay_film_id.setText(String.valueOf(film_id));
        vizyondaki_filmler_detay_film_adi.setText(Creator.filmlerDao().search_string(film_id, 1));
        vizyondaki_filmler_detay_film_turu.setText(Creator.filmlerDao().search_string(film_id, 2));
        vizyondaki_filmler_detay_film_suresi.setText(String.valueOf(Creator.filmlerDao().search_int(film_id, 1)));
        vizyondaki_filmler_detay_yonetmen.setText(Creator.vizyondaki_filmlerDao().search_string(vizyondaki_film_id, 3));
        vizyondaki_filmler_detay_kalkis_tarihi.setText(Creator.vizyondaki_filmlerDao().search_localdate(vizyondaki_film_id).toString());
        vizyondaki_filmler_detay_kullanici_puani.setText(String.valueOf(Creator.filmlerDao().search_float(film_id)));
        film_detay_id.setText(String.valueOf(vizyondaki_film_id));

        films_photosDAO fpdao = new films_photosDAO();

        int control = fpdao.count(film_id);

        if (control == 1) {
            BufferedImage bufferedImage = null;
            try {
                String photo_path = new films_photosDAO().search(film_id);
                bufferedImage = ImageIO.read(new File(photo_path));
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                img.setImage(image);
                vizyondaki_filmler_film_detay_photo.setImage(image);
            } catch (IOException ex) {
                System.out.println(ex);
                guncelle_mesaj.setText("Bir Hata Meydana Geldi (Hata Kodu: -40)");
            }
        } else if (control == 0) {
            try {
                BufferedImage bufferedImage = null;
                String photo_path = "src/lib/pic/Movies.png";
                bufferedImage = ImageIO.read(new File(photo_path));
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                vizyondaki_filmler_film_detay_photo.setImage(image);
            } catch (IOException ex) {
                System.out.println(ex);
                guncelle_mesaj.setText("Bir Hata Meydana Geldi (Hata Kodu: -42)");
            }
        } else {
            guncelle_mesaj.setText("Bir Hata Meydana Geldi (Hata Kodu: -43)");
        }
    }

    @FXML
    public void vizyondaki_filmler_gosterim_sonraki(ActionEvent event) {
        int kacinci_sayfa = Integer.parseInt(vizyondaki_filmler_gosterim_sayfa_sayisi.getText());
        int sonraki_sayfa = kacinci_sayfa + 1;
        int minimum_gosterim = sonraki_sayfa * 10;
        int maksimum_gosteirm = minimum_gosterim + 10;

        LinkedList<vizyondaki_filmler> list = Vizyondaki_Filmler_Controller.getVizyondaki_filmler_list();

        LinkedList<vizyondaki_filmler> list2 = new LinkedList<>();

        if (list.size() <= maksimum_gosteirm) {
            for (int i = minimum_gosterim; i < list.size(); i++) {
                list2.add(list.get(i));
            }
            vizyondaki_filmler_gosterim(list2, 1);
        } else {
            for (int i = minimum_gosterim; i < maksimum_gosteirm; i++) {
                list2.add(list.get(i));
            }
            vizyondaki_filmler_gosterim(list2, -1);
        }

        vizyondaki_filmler_gosterim_sayfa_sayisi.setText(String.valueOf(sonraki_sayfa));
    }

    @FXML
    public void vizyondaki_filmler_gosterim_onceki(ActionEvent event) {
        int kacinci_sayfa = Integer.parseInt(vizyondaki_filmler_gosterim_sayfa_sayisi.getText());
        int onceki_sayfa = kacinci_sayfa - 1;

        if (onceki_sayfa == 1) {
            vizyondaki_filmler_gosterim_oncesi_ortak();
        } else {
            int minimum_gosterim = onceki_sayfa * 10;
            int maksimum_gosteirm = minimum_gosterim + 10;

            LinkedList<vizyondaki_filmler> list = Vizyondaki_Filmler_Controller.getVizyondaki_filmler_list();

            LinkedList<vizyondaki_filmler> list2 = new LinkedList<>();

            for (int i = minimum_gosterim; i < maksimum_gosteirm; i++) {
                list2.add(list.get(i));
            }
            vizyondaki_filmler_gosterim(list2, -1);
        }

        vizyondaki_filmler_gosterim_sayfa_sayisi.setText(String.valueOf(onceki_sayfa));
    }

    @FXML
    public void vizyondaki_filmler_gosterim_bas(ActionEvent event) {
        vizyondaki_filmler_gosterim_oncesi_ortak();
        vizyondaki_filmler_gosterim_sayfa_sayisi.setText(String.valueOf(1));
    }

    @FXML
    public void vizyondaki_filmler_gosterim_son(ActionEvent event) {
        LinkedList<vizyondaki_filmler> list =  Vizyondaki_Filmler_Controller.getVizyondaki_filmler_list();
        int sayfa_sayisi = list.size() / 10;

        int mod = list.size() % 10;

        LinkedList<vizyondaki_filmler> list2 = new LinkedList<>();

        if (mod == 0) {
            for (int i = list.size() - 10; i < list.size(); i++) {
                list2.add(list.get(i));
            }

        } else {

            for (int i = list.size() - mod; i < list.size(); i++) {
                list2.add(list.get(i));
            }

        }

        vizyondaki_filmler_gosterim(list2, 1);

        vizyondaki_filmler_gosterim_sayfa_sayisi.setText(String.valueOf(sayfa_sayisi));
    }

    @FXML
    public void vizyondak_filmler_gosterim_arama(ActionEvent event) {
        vizyondaki_filmler_grid.setVisible(true);
        vizyondaki_filmler_resimli_gosterim.setVisible(false);

        vizyondaki_filmler_geri_tusu.setVisible(false);
        vizyondaki_filmler_ekle_geri_tusu.setVisible(false);
        vizyondaki_filmler_gosterim_geri_tusu.setVisible(true);

        vizyondaki_filmler_table_admin();
    }

    @FXML
    public void vizyondaki_filmler_arama_geri(MouseEvent event) {
        vizyondaki_filmler_resimli_gosterim.setVisible(true);
        vizyondaki_filmler_grid.setVisible(false);

        vizyondaki_filmler_geri_tusu.setVisible(true);
        vizyondaki_filmler_gosterim_geri_tusu.setVisible(false);

        vizyondaki_filmler_gosterim_oncesi_ortak();
    }

}
