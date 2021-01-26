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
public class haberler {

    private int haber_id;
    private int hangi_kullanici_turu;
    private String Title;
    private String Haber;
    private String Tarih;
    private String Haber_Kategorisi;
    private Button sil;

    public haberler() {
    }

    public haberler(int haber_id, int hangi_kullanici_turu, String Title, String Haber, String Tarih, String Haber_Kategorisi, Button sil, Label haberler_silmekten_emin_haber_id, Pane haberler_sil_emin_misin_pane) {
        this.haber_id = haber_id;
        this.hangi_kullanici_turu = hangi_kullanici_turu;
        this.Title = Title;
        this.Haber = Haber;
        this.Tarih = Tarih;
        this.Haber_Kategorisi = Haber_Kategorisi;
        this.sil = sil;

        sil.setOnAction(e -> {
            haberler_silmekten_emin_haber_id.setText(String.valueOf(this.haber_id));
            haberler_sil_emin_misin_pane.setVisible(true);
        });
    }

    public haberler(int haber_id, int hangi_kullanici_turu, String Title, String Haber, String Tarih, String Haber_Kategorisi) {
        this.haber_id = haber_id;
        this.hangi_kullanici_turu = hangi_kullanici_turu;
        this.Title = Title;
        this.Haber = Haber;
        this.Tarih = Tarih;
        this.Haber_Kategorisi = Haber_Kategorisi;
    }

    public haberler(int hangi_kullanici_turu, String Title, String Haber, String Tarih, String Haber_Kategorisi) {
        this.hangi_kullanici_turu = hangi_kullanici_turu;
        this.Title = Title;
        this.Haber = Haber;
        this.Tarih = Tarih;
        this.Haber_Kategorisi = Haber_Kategorisi;
    }

    public haberler(String Title, String Haber, String Tarih, String Haber_Kategorisi) {
        this.Title = Title;
        this.Haber = Haber;
        this.Tarih = Tarih;
        this.Haber_Kategorisi = Haber_Kategorisi;
    }
    
    

    public int getHaber_id() {
        return haber_id;
    }

    public void setHaber_id(int haber_id) {
        this.haber_id = haber_id;
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

    public String getHaber() {
        return Haber;
    }

    public void setHaber(String Haber) {
        this.Haber = Haber;
    }

    public String getTarih() {
        return Tarih;
    }

    public void setTarih(String Tarih) {
        this.Tarih = Tarih;
    }

    public String getHaber_Kategorisi() {
        return Haber_Kategorisi;
    }

    public void setHaber_Kategorisi(String Haber_Kategorisi) {
        this.Haber_Kategorisi = Haber_Kategorisi;
    }

    public Button getSil() {
        return sil;
    }

    public void setSil(Button sil) {
        this.sil = sil;
    }
    
    

}
