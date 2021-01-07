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
public class aboneler {
    private int abone_id;
    private int user_id;
    private int abone_type;

    public aboneler() {
    }

    public aboneler(int abone_id, int user_id, int abone_type) {
        this.abone_id = abone_id;
        this.user_id = user_id;
        this.abone_type = abone_type;
    }

    public aboneler(int user_id, int abone_type) {
        this.user_id = user_id;
        this.abone_type = abone_type;
    }

    public int getAbone_id() {
        return abone_id;
    }

    public void setAbone_id(int abone_id) {
        this.abone_id = abone_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getAbone_type() {
        return abone_type;
    }

    public void setAbone_type(int abone_type) {
        this.abone_type = abone_type;
    }
    
    
}
