package Controller;

import Code_Admin.Center_Admin;
import Pattern.Creator;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

public class appController extends Center_Admin implements Initializable {
    
    @FXML
    private void btn10(ActionEvent event) {
        
        if (btn1.getText().equals("Filmler")) {
            btn1.setText("Vizyondaki Filmler");
            btn2.setText("Eski Filmler");
            
            btn3.setVisible(false);
            btn4.setVisible(false);
            btn5.setVisible(false);
            
            home.setVisible(true);
        } else if (btn1.getText().equals("Vizyondaki Filmler")) {
            
            pnl_vizyondaki_filmler.setVisible(true);
            pnl_eski_filmler.setVisible(false);
            pnl_sinema_salonlari.setVisible(false);
            pnl_seans.setVisible(false);
            home_pane.setVisible(false);
            pnl_settings.setVisible(false);
            pnl_kullanici_islemleri.setVisible(false);
            pnl_film_detay.setVisible(false);
            
            vizyondaki_filmler_geri_tusu.setVisible(true);
            vizyondaki_filmler_ekle_geri_tusu.setVisible(false);
            vizyondaki_filmler_gosterim_geri_tusu.setVisible(false);
            
            vizyondaki_filmler_resimli_gosterim.setVisible(true);
            vizyondaki_filmler_grid.setVisible(false);
            vizyondaki_filmler_ekle_pane.setVisible(false);
            
            vizyondaki_filmler_gosterim_oncesi_ortak();
            
        } else if (btn1.getText().equals("Kampanyalar")) {
            
            pnl_kampanyalar.setVisible(true);
            pnl_haberler.setVisible(false);
            pnl_sinema_salonlari.setVisible(false);
            home_pane.setVisible(false);
            pnl_settings.setVisible(false);
            pnl_kullanici_islemleri.setVisible(false);
            
            kampanyalar_geri_tusu.setVisible(true);
            kampanyalar_ekle_geri_tusu.setVisible(false);
            kampanyalar_degistir_geri_tusu.setVisible(false);
            
            kampanyalar_grid.setVisible(true);
            kampanyalar_ekle_pane.setVisible(false);
            kampanyalar_degistir_pane.setVisible(false);
            
            kampanyalar_table_butonlu();
            
        } else if (btn1.getText().equals("Yönetmenler")) {
            
            pnl_yonetmenler.setVisible(true);
            pnl_aktorler.setVisible(false);
            pnl_sinema_salonlari.setVisible(false);
            home_pane.setVisible(false);
            pnl_settings.setVisible(false);
            pnl_kullanici_islemleri.setVisible(false);
            
            yonetmenler_geri_tusu.setVisible(true);
            yonetmenler_ekle_geri_tusu.setVisible(false);
            yonetmenler_degistir_geri_tusu.setVisible(false);
            
            yonetmenler_grid.setVisible(true);
            yonetmenler_ekle_pane.setVisible(false);
            yonetmenler_degistir_pane.setVisible(false);
            
            yonetmenler_table();
        }
    }
    
    @FXML
    private void btn20(ActionEvent event) {
        if (btn2.getText().equals("Duyurular")) {
            
            btn1.setText("Kampanyalar");
            btn2.setText("Haberler");
            
            btn3.setVisible(false);
            btn4.setVisible(false);
            btn5.setVisible(false);
            
            home.setVisible(true);
        } else if (btn2.getText().equals("Eski Filmler")) {
            
            pnl_eski_filmler.setVisible(true);
            pnl_vizyondaki_filmler.setVisible(false);
            pnl_sinema_salonlari.setVisible(false);
            pnl_seans.setVisible(false);
            home_pane.setVisible(false);
            pnl_settings.setVisible(false);
            pnl_kullanici_islemleri.setVisible(false);
            pnl_film_detay.setVisible(false);
            
            eski_filmler_geri_tusu.setVisible(true);
            eski_filmler_ekle_geri_tusu.setVisible(false);
            
            eski_filmler_grid.setVisible(true);
            eski_filmler_ekle_pane.setVisible(false);
            
            eski_filmler_table_aboneli();
            
        } else if (btn2.getText().equals("Haberler")) {
            
            pnl_haberler.setVisible(true);
            pnl_kampanyalar.setVisible(false);
            pnl_sinema_salonlari.setVisible(false);
            home_pane.setVisible(false);
            pnl_settings.setVisible(false);
            pnl_kullanici_islemleri.setVisible(false);
            
            haberler_geri_tusu.setVisible(true);
            haberler_ekle_geri_tusu.setVisible(false);
            haberler_degistir_geri_tusu.setVisible(false);
            
            haberler_grid.setVisible(true);
            haberler_ekle_pane.setVisible(false);
            haberler_degistir_pane.setVisible(false);
            
            haberler_table_butonlu();
            
        } else if (btn2.getText().equals("Aktörler")) {
            pnl_aktorler.setVisible(true);
            pnl_yonetmenler.setVisible(false);
            pnl_sinema_salonlari.setVisible(false);
            home_pane.setVisible(false);
            pnl_settings.setVisible(false);
            pnl_kullanici_islemleri.setVisible(false);
            
            aktorler_geri_tusu.setVisible(true);
            aktorler_ekle_geri_tusu.setVisible(false);
            film_actor_geri_tusu.setVisible(false);
            film_actor_ekle_geri_tusu.setVisible(false);
            
            aktorler_grid.setVisible(true);
            aktorler_ekle_pane.setVisible(false);
            aktorler_sil_emin_misin.setVisible(false);
            film_actor_pane.setVisible(false);
            
            aktorler_table();
        }
    }
    
    @FXML
    private void btn30(ActionEvent event) {
        pnl_sinema_salonlari.setVisible(true);
        pnl_vizyondaki_filmler.setVisible(false);
        pnl_seans.setVisible(false);
        pnl_eski_filmler.setVisible(false);
        pnl_kampanyalar.setVisible(false);
        pnl_haberler.setVisible(false);
        pnl_yonetmenler.setVisible(false);
        pnl_aktorler.setVisible(false);
        home_pane.setVisible(false);
        pnl_settings.setVisible(false);
        pnl_kullanici_islemleri.setVisible(false);
        
        sinema_salonlari_home_pane.setVisible(true);
        
        salon_bir_pane.setVisible(false);
        salon_iki_pane.setVisible(false);
        salon_uc_pane.setVisible(false);
        salon_dort_pane.setVisible(false);
        
        sinema_salonlari_goruntule_pane.setVisible(false);
        sinema_salonu_ekle_pane.setVisible(false);
        sinema_salonu_duzenle_pane.setVisible(false);
        
        home.setVisible(true);
    }
    
    @FXML
    private void btn40(ActionEvent event) {
        btn1.setText("Yönetmenler");
        btn2.setText("Aktörler");
        
        btn3.setVisible(false);
        btn4.setVisible(false);
        btn5.setVisible(false);
        
        home.setVisible(true);
    }
    
    @FXML
    private void btn50(ActionEvent event) {
        kullanici_islemleri_ortak_giris();
    }
    
    @FXML
    private void home_geri_gel(MouseEvent event) {
        geri_don_admin();
    }
    
    @FXML
    private void settings_giris(MouseEvent event) {
        pnl_vizyondaki_filmler.setVisible(false);
        pnl_seans.setVisible(false);
        pnl_eski_filmler.setVisible(false);
        pnl_kampanyalar.setVisible(false);
        pnl_haberler.setVisible(false);
        pnl_sinema_salonlari.setVisible(false);
        pnl_yonetmenler.setVisible(false);
        pnl_aktorler.setVisible(false);
        home_pane.setVisible(false);
        pnl_kullanici_islemleri.setVisible(false);
        pnl_film_detay.setVisible(false);
        
        pnl_settings.setVisible(true);
        
        int user_id = Creator.getU().getUser_id();
        
        this.user_name.setText(Creator.getU().getUser_name());
        this.user_mail.setText(Creator.getU().getUser_mail());
        this.user_password.setText(Creator.getU().getUser_password());
        
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        home_page();
    }
}
