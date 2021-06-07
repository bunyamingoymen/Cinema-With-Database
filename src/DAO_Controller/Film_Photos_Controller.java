package DAO_Controller;

import Pattern.Creator;
import entity.films_photos;
import java.util.LinkedList;

public class Film_Photos_Controller {

    public static LinkedList<films_photos> film_photos_list;

    public static LinkedList<films_photos> getFilm_photos_list() {
        if(film_photos_list == null){
            setFilm_photos_list();
        }
        return film_photos_list;
    }

    public static void setFilm_photos_list() {
        film_photos_list = Creator.films_photoDAO().read();
    }

}
