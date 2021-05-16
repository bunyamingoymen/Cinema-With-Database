
package Pattern;

import DAO.actorDAO;
import DAO.eski_filmlerDAO;
import DAO.film_actorDAO;
import DAO.filmlerDAO;
import DAO.vizyondaki_filmlerDAO;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Facade {
    
    public void aktorler_her_seyi_sil(int aktor_id) throws SQLException{
        actorDAO adao = new actorDAO();
        film_actorDAO fadao = new film_actorDAO();
        vizyondaki_filmlerDAO vfdao = new vizyondaki_filmlerDAO();
        eski_filmlerDAO efdao = new eski_filmlerDAO();
        filmlerDAO fdao = new filmlerDAO();
        
        ResultSet rs = fadao.film_actor_film_id_yolla(aktor_id);
        
        if(rs!= null){
            ResultSet rs2 = rs;
            ResultSet rs3 = rs;
            ResultSet rs4 = rs;
            ResultSet rs5 = rs;
            
            fadao.film_actor_toplu_sil_(rs2);
            vfdao.vizyondaki_filmler_toplu_sil(rs3);
            efdao.eski_filmler_toplu_sil(rs4);
            fdao.filmler_toplu_sil(rs5);
            adao.aktorler_sil(aktor_id);
            
            
        }else{
            System.out.println("Hata kodu: -45 (Pattern.Facade.aktorler_her_seyi_sil)");
        }
    }
    
    
}
