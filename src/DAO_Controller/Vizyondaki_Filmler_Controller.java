
package DAO_Controller;

import Pattern.Creator;
import entity.vizyondaki_filmler;
import java.util.LinkedList;

public class Vizyondaki_Filmler_Controller {
    
    public static LinkedList<vizyondaki_filmler>vizyondaki_filmler_list;

    public static LinkedList<vizyondaki_filmler> getVizyondaki_filmler_list() {
        if(vizyondaki_filmler_list == null){
            setVizyondaki_filmler_list();
        }
        return vizyondaki_filmler_list;
    }

    public static void setVizyondaki_filmler_list() {
        vizyondaki_filmler_list = Creator.vizyondaki_filmlerDao().read();
    }
    
    
    
}
