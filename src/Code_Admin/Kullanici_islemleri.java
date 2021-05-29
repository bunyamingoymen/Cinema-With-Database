package Code_Admin;

import Pattern.Creator;
import Pattern.Mediator;
import Pattern.Table;
import entity.Center;
import entity.users;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class Kullanici_islemleri extends Kampanyalar {

    public void users_tablo() {

        ObservableList<users> data = Table.data_Users(kullanici_islemleri_user_id, kullanici_islemleri_user_name, kullanici_islemleri_user_mail, kullanici_islemleri_gizli_pf, kullanici_islemleri_user_turu, kullanici_islemleri_abone_turu, kullanici_islemleri_gizli_pane, kullanici_islemleri_acik_pane, kullanici_islemleri_table_pane, kullanici_islemleri_yonet_pane, kullanici_islemleri_geri_tusu, kullanici_islemleri_yonet_geri_tusu, kullanici_islemleri_silmekten_emin_misin_pane);

        users_user_id.setCellValueFactory(new PropertyValueFactory("user_id"));
        users_user_name.setCellValueFactory(new PropertyValueFactory("user_name"));
        users_user_mail.setCellValueFactory(new PropertyValueFactory("user_mail"));
        users_user_password.setCellValueFactory(new PropertyValueFactory("user_password"));
        users_user_type.setCellValueFactory(new PropertyValueFactory("user_type"));
        users_abone_turu.setCellValueFactory(new PropertyValueFactory("abonelik_turu"));
        users_yonet.setCellValueFactory(new PropertyValueFactory("yonet"));

        FilteredList<users> filteredData = new FilteredList<>(data, b -> true);

        filterField_users.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(us -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(us.getUser_id()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (us.getUser_name().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (us.getUser_mail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (us.getUser_password().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(us.getUser_type()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(us.getAbonelik_turu()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }

            });
        });

        SortedList<users> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(table_users.comparatorProperty());

        table_users.setItems(sortedData);
    }

    @FXML
    public void kullanici_islemleri_yonet_geri(MouseEvent event) {
        kullanici_islemleri_ortak_giris();
    }

    @FXML
    public void kullanici_islemleri_geri(MouseEvent event) {
        geri_don_admin();
    }

    public void kullanici_islemleri_ortak_giris() {
        pnl_kullanici_islemleri.setVisible(true);
        pnl_sinema_salonlari.setVisible(false);
        pnl_vizyondaki_filmler.setVisible(false);
        pnl_seans.setVisible(false);
        pnl_eski_filmler.setVisible(false);
        pnl_kampanyalar.setVisible(false);
        pnl_haberler.setVisible(false);
        pnl_yonetmenler.setVisible(false);
        pnl_aktorler.setVisible(false);
        home_pane.setVisible(false);
        pnl_settings.setVisible(false);

        kullanici_islemleri_table_pane.setVisible(true);
        kullanici_islemleri_yonet_pane.setVisible(false);

        kullanici_islemleri_yonet_geri_tusu.setVisible(false);
        kullanici_islemleri_geri_tusu.setVisible(true);

        home.setVisible(true);

        users_tablo();
    }

    @FXML
    public void kullanici_islemleri_sifre_goster(MouseEvent event) {
        a2 = true;
        kullanici_islemleri_acik_pf.setText(kullanici_islemleri_gizli_pf.getText());

        kullanici_islemleri_acik_pane.setVisible(true);
        kullanici_islemleri_gizli_pane.setVisible(false);
    }

    @FXML
    public void kullanici_islemleri_sifre_gizle(MouseEvent event) {
        a2 = false;
        kullanici_islemleri_gizli_pf.setText(kullanici_islemleri_acik_pf.getText());

        kullanici_islemleri_acik_pane.setVisible(false);
        kullanici_islemleri_gizli_pane.setVisible(true);
    }

    @FXML
    public void kullanici_islemleri_sil_giris(ActionEvent event) {
        kullanici_islemleri_silmekten_emin_misin_pane.setVisible(true);
    }

    @FXML
    public void kullanici_islemleri_sil_sil(ActionEvent event) {
        int user_id = Integer.parseInt(kullanici_islemleri_user_id.getText());

        int sonuc = Creator.usersDao().delete(user_id);

        if (sonuc == 1) {
            kullanici_islemleri_yonet_uyari_pane.setText("İşlem başarılı bir şekilde gerçekleştirildi.");
        } else {
            kullanici_islemleri_yonet_uyari_pane.setText("Bir hata meydana geldi hata kodu: -30");
        }

        kullanici_islemleri_silmekten_emin_misin_pane.setVisible(false);
    }

    @FXML
    public void kullanici_islemleri_sil_vazgec(ActionEvent event) {
        kullanici_islemleri_silmekten_emin_misin_pane.setVisible(false);
    }

    @FXML
    public void kullanici_islemleri_guncelle(ActionEvent event) {
        if ((kullanici_islemleri_user_name.getText().length() == 0) || (kullanici_islemleri_user_mail.getText().length() == 0) || (kullanici_islemleri_user_turu.getValue() == null) || (kullanici_islemleri_abone_turu.getValue() == null)) {
            kullanici_islemleri_yonet_uyari_pane.setText("Lütfen Gerekli Yerleri Doldurunuz.");
        } else {
            String user_password = null;
            if (a2 == false) {
                if (kullanici_islemleri_gizli_pf.getText().length() == 0) {
                    kullanici_islemleri_yonet_uyari_pane.setText("Lütfen Gerekli Yerleri Doldurunuz.");
                } else {
                    user_password = kullanici_islemleri_gizli_pf.getText();
                }
            } else {
                if (kullanici_islemleri_acik_pf.getText().length() == 0) {
                    kullanici_islemleri_yonet_uyari_pane.setText("Lütfen Gerekli Yerleri Doldurunuz.");
                } else {
                    user_password = kullanici_islemleri_acik_pf.getText();
                }
            }
            if (user_password != null) {
                int user_id = Integer.parseInt(kullanici_islemleri_user_id.getText());
                String user_name = kullanici_islemleri_user_name.getText();
                String user_mail = kullanici_islemleri_user_mail.getText();
                int abone_turu = Integer.parseInt(kullanici_islemleri_abone_turu.getValue());
                int user_turu = -1;
                if (kullanici_islemleri_user_turu.getValue().equals("Normal")) {
                    user_turu = 0;
                } else if (kullanici_islemleri_user_turu.getValue().equals("Admin")) {
                    user_turu = 1;
                }
                if (user_turu == -1) {
                    kullanici_islemleri_yonet_uyari_pane.setText("Bir hata meydana geldi. Hata kodu: -31.");
                } else {
                    users u = new users(user_id, user_name, user_mail, user_password, user_turu);
                    
                    Center nw = new Center(u);
                    
                    Mediator m = new Mediator();
                    
                    int sonuc = m.user_guncelle_aboneli(nw, abone_turu);
                    
                    if (sonuc == 1) {
                        kullanici_islemleri_yonet_uyari_pane.setText("İşlem Başarılı Bir Şekilde Gerçekleştirildi.");
                    } else {
                        kullanici_islemleri_yonet_uyari_pane.setText("Bir hata meydana geldi. Hata kodu: -32.");
                    }
                }
            }
        }
    }

}
