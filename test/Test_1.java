/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.SQLException;
import org.junit.Test;
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
    
}
