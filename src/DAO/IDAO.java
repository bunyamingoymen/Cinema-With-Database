/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entity.Center;
import java.util.LinkedList;

/**
 *
 * @author bgoym
 */
public interface IDAO {

    public int create(Center nw);

    public LinkedList read();

    public int update(Center nw);

    public int delete(int id);
    
    public int count();
}
