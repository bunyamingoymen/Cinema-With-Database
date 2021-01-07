/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 *
 * @author bgoymen
 */
public class kampanyalar {
    private int kampanya_id;
    private int hangi_kullanici_turu;
    private String Title;
    private String Kampanya;
    private String Tarih;
    private String Kampanya_Kategorisi;
    private Button sil;

    public kampanyalar() {
    }

    public kampanyalar(int kampanya_id, int hangi_kullanici_turu, String Title, String Kampanya, String Tarih, String Kampanya_Kategorisi, Button sil, Pane kampanyalar_sil_emin_misin_pane, Label kampanyalar_silmekten_emin_kampanya_id) {
        this.kampanya_id = kampanya_id;
        this.hangi_kullanici_turu = hangi_kullanici_turu;
        this.Title = Title;
        this.Kampanya = Kampanya;
        this.Tarih = Tarih;
        this.Kampanya_Kategorisi = Kampanya_Kategorisi;
        this.sil = sil;
        
        sil.setOnAction(e -> {
            kampanyalar_silmekten_emin_kampanya_id.setText(String.valueOf(this.kampanya_id));
            kampanyalar_sil_emin_misin_pane.setVisible(true);
        });
    }

    public kampanyalar(int hangi_kullanici_turu, String Title, String Kampanya, String Tarih, String Kampanya_Kategorisi) {
        this.hangi_kullanici_turu = hangi_kullanici_turu;
        this.Title = Title;
        this.Kampanya = Kampanya;
        this.Tarih = Tarih;
        this.Kampanya_Kategorisi = Kampanya_Kategorisi;
    }

    public kampanyalar(int kampanya_id, int hangi_kullanici_turu, String Title, String Kampanya, String Tarih, String Kampanya_Kategorisi) {
        this.kampanya_id = kampanya_id;
        this.hangi_kullanici_turu = hangi_kullanici_turu;
        this.Title = Title;
        this.Kampanya = Kampanya;
        this.Tarih = Tarih;
        this.Kampanya_Kategorisi = Kampanya_Kategorisi;
    }

    

    public int getKampanya_id() {
        return kampanya_id;
    }

    public void setKampanya_id(int kampanya_id) {
        this.kampanya_id = kampanya_id;
    }

    public int getHangi_kullanici_turu() {
        return hangi_kullanici_turu;
    }

    public void setHangi_kullanici_turu(int hangi_kullanici_turu) {
        this.hangi_kullanici_turu = hangi_kullanici_turu;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getKampanya() {
        return Kampanya;
    }

    public void setKampanya(String Kampanya) {
        this.Kampanya = Kampanya;
    }

    public String getTarih() {
        return Tarih;
    }

    public void setTarih(String Tarih) {
        this.Tarih = Tarih;
    }

    public String getKampanya_Kategorisi() {
        return Kampanya_Kategorisi;
    }

    public void setKampanya_Kategorisi(String Kampanya_Kategorisi) {
        this.Kampanya_Kategorisi = Kampanya_Kategorisi;
    }

    public Button getSil() {
        return sil;
    }

    public void setSil(Button sil) {
        this.sil = sil;
    }
    
    
    
}
