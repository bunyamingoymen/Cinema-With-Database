package entity;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class users {

    private int user_id;
    private String user_name;
    private String user_mail;
    private String user_password;
    private int user_type;

    private int abonelik_turu;
    private Button yonet;

    public users() {

    }

    public users(String user_name, String user_mail, String user_password, int user_type) {
        this.user_name = user_name;
        this.user_mail = user_mail;
        this.user_password = user_password;
        this.user_type = user_type;
    }

    public users(int user_id, String user_name, String user_mail, String user_password, int user_type) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_mail = user_mail;
        this.user_password = user_password;
        this.user_type = user_type;
    }
    
     public users(int user_id, String user_name, String user_mail, String user_password, int user_type, int abonelik_turu) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_mail = user_mail;
        this.user_password = user_password;
        this.user_type = user_type;
        this.abonelik_turu = abonelik_turu;
    }

    public users(int user_id, String user_name, String user_mail, String user_password, int user_type, int abonelik_turu, Button yonet, Label kullanici_islemleri_user_id, TextField kullanici_islemleri_user_name, TextField kullanici_islemleri_user_mail, PasswordField kullanici_islemleri_gizli_pf, ComboBox<String> kullanici_islemleri_user_turu, ComboBox<String> kullanici_islemleri_abone_turu, Pane gizli_pane, Pane acik_pane, Pane tablo_pane, Pane yonet_pane, FontAwesomeIconView geri_tusu, FontAwesomeIconView yonet_geri_tusu, Pane sil_pane) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_mail = user_mail;
        this.user_password = user_password;
        this.user_type = user_type;
        this.abonelik_turu = abonelik_turu;
        
    }

    public void users_controller(Button yonet, Label kullanici_islemleri_user_id, TextField kullanici_islemleri_user_name, TextField kullanici_islemleri_user_mail, PasswordField kullanici_islemleri_gizli_pf, ComboBox<String> kullanici_islemleri_user_turu, ComboBox<String> kullanici_islemleri_abone_turu, Pane gizli_pane, Pane acik_pane, Pane tablo_pane, Pane yonet_pane, FontAwesomeIconView geri_tusu, FontAwesomeIconView yonet_geri_tusu, Pane sil_pane) {
        
        this.yonet = yonet;
        
        this.yonet.setOnAction(e -> {
            kullanici_islemleri_user_id.setText(String.valueOf(this.user_id));
            kullanici_islemleri_user_name.setText(this.user_name);
            kullanici_islemleri_user_mail.setText(this.user_mail);
            kullanici_islemleri_gizli_pf.setText(this.user_password);
            user_type_combo_doldur(kullanici_islemleri_user_turu, user_type);
            abonelik_turu_combo_doldur(kullanici_islemleri_abone_turu, abonelik_turu);

            tablo_pane.setVisible(false);
            yonet_pane.setVisible(true);

            gizli_pane.setVisible(true);
            acik_pane.setVisible(false);

            yonet_geri_tusu.setVisible(true);
            geri_tusu.setVisible(false);

            sil_pane.setVisible(false);
        });
    }

    public void abonelik_turu_combo_doldur(ComboBox<String> combo, int abonelik_turu) {
        combo.getItems().clear();

        String a = "0";
        String b = "1";
        String c = "2";
        String d = "3";

        combo.getItems().addAll(a);
        combo.getItems().addAll(b);
        combo.getItems().addAll(c);
        combo.getItems().addAll(d);

        combo.setValue(String.valueOf(abonelik_turu));
    }

    public void user_type_combo_doldur(ComboBox<String> combo, int user_type) {
        combo.getItems().clear();

        String a = "Normal";
        String b = "Admin";

        combo.getItems().addAll(a);
        combo.getItems().addAll(b);

        switch (user_type) {
            case 0:
                combo.setValue(String.valueOf(a));
                break;
            case 1:
                combo.setValue(String.valueOf(b));
                break;
            default:
                combo.setValue(String.valueOf("Hata Kodu: -29"));
                break;
        }
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_mail() {
        return user_mail;
    }

    public void setUser_mail(String user_mail) {
        this.user_mail = user_mail;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }

    public int getAbonelik_turu() {
        return abonelik_turu;
    }

    public void setAbonelik_turu(int abonelik_turu) {
        this.abonelik_turu = abonelik_turu;
    }

    public Button getYonet() {
        return yonet;
    }

    public void setYonet(Button yonet) {
        this.yonet = yonet;
    }

}
