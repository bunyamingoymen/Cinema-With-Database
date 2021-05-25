
package Pattern;

import java.sql.SQLException;
import java.util.LinkedList;

public class Facade {
    
    public void aktorler_her_seyi_sil(int aktor_id) throws SQLException{
        
        LinkedList<Integer> list = Creator.film_actorDao().read_film_id(aktor_id);
        
        if(list != null){
            
            LinkedList<Integer> list2 = list;
            
            Mediator m = new Mediator();
            m.film_actor_toplu_sil_film_id_uzerinden(list2);
            m.vizyondaki_filmler_toplu_sil(list2);
            m.eski_filmler_toplu_sil(list2);
            m.filmler_toplu_sil(list2);
            Creator.actorDao().delete(aktor_id);
            
            
        }else{
            System.out.println("Hata kodu: -45 (Pattern.Facade.aktorler_her_seyi_sil)");
        }
    }
    
    
}
