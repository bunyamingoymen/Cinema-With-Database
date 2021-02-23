/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author bgoym
 */
public class user_photos {

    private int user_photo_id;
    private int user_id;
    private String photo_name;
    private String photo_parent;
    private String photo_path;

    public user_photos() {
    }

    public user_photos(int user_photo_id, int user_id, String photo_name, String photo_parent, String photo_path) {
        this.user_photo_id = user_photo_id;
        this.user_id = user_id;
        this.photo_name = photo_name;
        this.photo_parent = photo_parent;
        this.photo_path = photo_path;
    }

    public user_photos(int user_id, String photo_name, String photo_parent, String photo_path) {
        this.user_id = user_id;
        this.photo_name = photo_name;
        this.photo_parent = photo_parent;
        this.photo_path = photo_path;
    }

    public int getUser_photo_id() {
        return user_photo_id;
    }

    public void setUser_photo_id(int user_photo_id) {
        this.user_photo_id = user_photo_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getPhoto_name() {
        return photo_name;
    }

    public void setPhoto_name(String photo_name) {
        this.photo_name = photo_name;
    }

    public String getPhoto_parent() {
        return photo_parent;
    }

    public void setPhoto_parent(String photo_parent) {
        this.photo_parent = photo_parent;
    }

    public String getPhoto_path() {
        return photo_path;
    }

    public void setPhoto_path(String photo_path) {
        this.photo_path = photo_path;
    }

}
