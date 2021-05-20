/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB_Test;

import DAO.vizyondaki_filmlerDAO;
import entity.vizyondaki_filmler;
import java.util.LinkedList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author bgoym
 */
public class Test_2 {
    
    public Test_2() {
    }
    
    @Test
    public void baglanti(){
        vizyondaki_filmlerDAO vfdao = new vizyondaki_filmlerDAO();
        
        LinkedList<vizyondaki_filmler> list = vfdao.vizyondaki_filmler_hepsini_getir();
        
        for(int i= 0; i<list.size();i++){
            vizyondaki_filmler v = list.get(i);
            System.out.println(v.toString());
        }
    }
    
    @Before
    public void test_before(){
        System.out.println("Before çalıştı");
    }
    
    @After
    public void test_after(){
        System.out.println("After çalıştı");
    }
    
}
