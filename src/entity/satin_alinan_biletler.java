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
public class satin_alinan_biletler {

    private int satin_alinan_bilet_id;
    private int seans_id;
    private int user_id;
    private String koltuk_name;

    public satin_alinan_biletler() {
    }

    public satin_alinan_biletler(int satin_alinan_bilet_id, int seans_id, int user_id, String koltuk_name) {
        this.satin_alinan_bilet_id = satin_alinan_bilet_id;
        this.seans_id = seans_id;
        this.user_id = user_id;
        this.koltuk_name = koltuk_name;
    }

    public satin_alinan_biletler(int seans_id, int user_id, String koltuk_name) {
        this.seans_id = seans_id;
        this.user_id = user_id;
        this.koltuk_name = koltuk_name;
    }

    public int getSatin_alinan_bilet_id() {
        return satin_alinan_bilet_id;
    }

    public void setSatin_alinan_bilet_id(int satin_alinan_bilet_id) {
        this.satin_alinan_bilet_id = satin_alinan_bilet_id;
    }

    public int getSeans_id() {
        return seans_id;
    }

    public void setSeans_id(int seans_id) {
        this.seans_id = seans_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getKoltuk_name() {
        return koltuk_name;
    }

    public void setKoltuk_name(String koltuk_name) {
        this.koltuk_name = koltuk_name;
    }

}
