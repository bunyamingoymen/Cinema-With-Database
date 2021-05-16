/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import util.DBConnector;

/**
 *
 * @author bgoym
 */
public class Test_1 {
    
    DBConnector db = new DBConnector();
    
    @Test
    public void db_connection_test() throws SQLException{
        Connection c = db.connect();
        if(c == null){
            System.out.println("Error");
        }else{
            System.out.println("Bağlandı");
        }
    }
    
//    public Test_1() {
//    }
//    
//    @BeforeClass
//    public static void setUpClass() {
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
//    
//    @Before
//    public void setUp() {
//    }
//    
//    @After
//    public void tearDown() {
//    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
