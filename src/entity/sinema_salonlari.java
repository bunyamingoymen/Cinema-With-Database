/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author bgoymen
 */
public class sinema_salonlari {
    private int salon_id;
    private String salon_name;
    private int koltuk_sayisi;

    public sinema_salonlari() {
    }

    public sinema_salonlari(int salon_id, String salon_name, int koltuk_sayisi) {
        this.salon_id = salon_id;
        this.salon_name = salon_name;
        this.koltuk_sayisi = koltuk_sayisi;
    }

    public sinema_salonlari(String salon_name, int koltuk_sayisi) {
        this.salon_name = salon_name;
        this.koltuk_sayisi = koltuk_sayisi;
    }

    public int getSalon_id() {
        return salon_id;
    }

    public void setSalon_id(int salon_id) {
        this.salon_id = salon_id;
    }

    public String getSalon_name() {
        return salon_name;
    }

    public void setSalon_name(String salon_name) {
        this.salon_name = salon_name;
    }

    public int getKoltuk_sayisi() {
        return koltuk_sayisi;
    }

    public void setKoltuk_sayisi(int koltuk_sayisi) {
        this.koltuk_sayisi = koltuk_sayisi;
    }
    
    
}
