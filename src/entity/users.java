package entity;

import javafx.scene.control.Button;

public class users {

    private int user_id;
    private String user_name;
    private String user_mail;
    private String user_password;
    private int user_type;

    private int abonelik_turu;
    private Button yonet;

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

    public users(int user_id, String user_name, String user_mail, String user_password, int user_type, int abonelik_turu, Button yonet) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_mail = user_mail;
        this.user_password = user_password;
        this.user_type = user_type;
        this.abonelik_turu = abonelik_turu;
        this.yonet = yonet;

        yonet.setOnAction(e -> {

        });
    }

    public users() {
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
