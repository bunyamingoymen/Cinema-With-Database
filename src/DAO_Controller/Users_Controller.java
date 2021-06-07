package DAO_Controller;

import Pattern.Creator;
import entity.Center;
import entity.users;
import java.util.LinkedList;

public class Users_Controller {

    private static LinkedList<users> user_list;

    public static LinkedList getUser_list() {
        if (user_list == null) {
            setUser_list();
        }
        return user_list;
    }

    public static void setUser_list() {
        user_list = Creator.usersDao().read();
    }

    public int create(Center nw) {

        int sonuc = Creator.usersDao().create(nw);

        setUser_list();

        return sonuc;
    }

}
