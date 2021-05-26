/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pattern_Test;

import DAO.actorDAO;
import DAO.eski_filmlerDAO;
import DAO.film_actorDAO;
import DAO.filmlerDAO;
import DAO.vizyondaki_filmlerDAO;
import Pattern.Facade;
import entity.Center;
import entity.actor;
import entity.eski_filmler;
import entity.film_actor;
import entity.filmler;
import entity.vizyondaki_filmler;
import java.sql.SQLException;
import java.time.LocalDate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author bgoym
 */
public class Facade_Test {

    static int[] film_id = new int[10];
    static int actor_id;

    public Facade_Test() {
    }

    @Test
    public void Test_aktorler_her_seyi_sil() throws SQLException {
        Facade f = new Facade();

        f.aktorler_her_seyi_sil(actor_id);

        System.out.println("Facade Calisti");
    }

    @Before
    public void Before_Test_aktorler_her_seyi_sil() {
        actorDAO adao = new actorDAO();
        actor a = new actor("Test_ad", "Test_soyad");
        Center actor = new Center(a);

        adao.create(actor);
        System.out.println("Aktör eklendi");
        actor_id = adao.search();

        filmlerDAO fdao = new filmlerDAO();
        filmler f = new filmler("Test_film_name_1", 120, "Aksiyon", 3981, 1);
        filmler f2 = new filmler("Test_film_name_2", 120, "Aksiyon", 3981, 1);
        filmler f3 = new filmler("Test_film_name_3", 120, "Aksiyon", 3981, 1);
        filmler f4 = new filmler("Test_film_name_4", 120, "Aksiyon", 3981, 1);
        filmler f5 = new filmler("Test_film_name_5", 120, "Aksiyon", 3981, 1);
        filmler f6 = new filmler("Test_film_name_6", 120, "Aksiyon", 3981, 1);
        filmler f7 = new filmler("Test_film_name_7", 120, "Aksiyon", 3981, 1);
        filmler f8 = new filmler("Test_film_name_8", 120, "Aksiyon", 3981, 1);
        filmler f9 = new filmler("Test_film_name_9", 120, "Aksiyon", 3981, 1);
        filmler f10 = new filmler("Test_film_name_10", 120, "Aksiyon", 3981, 1);

        Center nw = new Center(f);
        Center nw2 = new Center(f2);
        Center nw3 = new Center(f3);
        Center nw4 = new Center(f4);
        Center nw5 = new Center(f5);
        Center nw6 = new Center(f6);
        Center nw7 = new Center(f7);
        Center nw8 = new Center(f8);
        Center nw9 = new Center(f9);
        Center nw10 = new Center(f10);

        film_id[0] = fdao.create(nw);
        film_id[1] = fdao.create(nw2);
        film_id[2] = fdao.create(nw3);
        film_id[3] = fdao.create(nw4);
        film_id[4] = fdao.create(nw5);
        film_id[5] = fdao.create(nw6);
        film_id[6] = fdao.create(nw7);
        film_id[7] = fdao.create(nw8);
        film_id[8] = fdao.create(nw9);
        film_id[9] = fdao.create(nw10);

        System.out.println("Filmler eklendi");
        

        vizyondaki_filmlerDAO vfdao = new vizyondaki_filmlerDAO();
        vizyondaki_filmler v = new vizyondaki_filmler(film_id[0], LocalDate.now(), 0);
        vizyondaki_filmler v2 = new vizyondaki_filmler(film_id[1], LocalDate.now(), 0);
        vizyondaki_filmler v3 = new vizyondaki_filmler(film_id[2], LocalDate.now(), 0);
        vizyondaki_filmler v4 = new vizyondaki_filmler(film_id[3], LocalDate.now(), 0);
        vizyondaki_filmler v5 = new vizyondaki_filmler(film_id[4], LocalDate.now(), 0);

        Center nw11 = new Center(v);
        Center nw12 = new Center(v2);
        Center nw13 = new Center(v3);
        Center nw14 = new Center(v4);
        Center nw15 = new Center(v5);

        vfdao.create(nw11);
        vfdao.create(nw12);
        vfdao.create(nw13);
        vfdao.create(nw14);
        vfdao.create(nw15);

        System.out.println("Vizyondaki Filmler Eklendi");

        eski_filmlerDAO efdao = new eski_filmlerDAO();
        eski_filmler ed = new eski_filmler(film_id[5], 0, 1,(float)0);
        eski_filmler ed2 = new eski_filmler(film_id[6], 0, 1,(float)0);
        eski_filmler ed3 = new eski_filmler(film_id[7], 0, 1,(float)0);
        eski_filmler ed4 = new eski_filmler(film_id[8], 0, 1,(float)0);
        eski_filmler ed5 = new eski_filmler(film_id[9], 0, 1,(float)0);
        
        Center nw16 = new Center(ed);
        Center nw17 = new Center(ed2);
        Center nw18 = new Center(ed3);
        Center nw19 = new Center(ed4);
        Center nw20 = new Center(ed5);


        efdao.create(nw16);
        efdao.create(nw17);
        efdao.create(nw18);
        efdao.create(nw19);
        efdao.create(nw20);

        System.out.println("Eski Filmler Eklendi");

        film_actorDAO fadao = new film_actorDAO();
        film_actor fa = new film_actor(film_id[0], actor_id);
        film_actor fa2 = new film_actor(film_id[1], actor_id);
        film_actor fa3 = new film_actor(film_id[2], actor_id);
        film_actor fa4 = new film_actor(film_id[3], actor_id);
        film_actor fa5 = new film_actor(film_id[4], actor_id);
        film_actor fa6 = new film_actor(film_id[5], actor_id);
        film_actor fa7 = new film_actor(film_id[6], actor_id);
        film_actor fa8 = new film_actor(film_id[7], actor_id);
        film_actor fa9 = new film_actor(film_id[8], actor_id);
        film_actor fa10 = new film_actor(film_id[9], actor_id);

        Center nw21 = new Center(fa);
        Center nw22 = new Center(fa2);
        Center nw23 = new Center(fa3);
        Center nw24 = new Center(fa4);
        Center nw25 = new Center(fa5);
        Center nw26 = new Center(fa6);
        Center nw27 = new Center(fa7);
        Center nw28 = new Center(fa8);
        Center nw29 = new Center(fa9);
        Center nw30 = new Center(fa10);

        fadao.create(nw21);
        fadao.create(nw22);
        fadao.create(nw23);
        fadao.create(nw24);
        fadao.create(nw25);
        fadao.create(nw26);
        fadao.create(nw27);
        fadao.create(nw28);
        fadao.create(nw29);
        fadao.create(nw30);

        System.out.println("Film Actor Eklendi");

    }

    @After
    public void After_Test_aktorler_her_seyi_sil() {
        int[] test = new int[10];
        filmlerDAO fdao = new filmlerDAO();

        test[0] = fdao.count(film_id[0]);
        test[1] = fdao.count(film_id[1]);
        test[2] = fdao.count(film_id[2]);
        test[3] = fdao.count(film_id[3]);
        test[4] = fdao.count(film_id[4]);
        test[5] = fdao.count(film_id[5]);
        test[6] = fdao.count(film_id[6]);
        test[7] = fdao.count(film_id[7]);
        test[8] = fdao.count(film_id[8]);
        test[9] = fdao.count(film_id[9]);

        if (test[0] == 0 && test[1] == 0 && test[2] == 0 && test[3] == 0 && test[4] == 0 && test[5] == 0 && test[6] == 0 && test[7] == 0 && test[8] == 0 && test[9] == 0) {
            System.out.println("Test Basarili");
        } else {
            System.out.println("Test Basarisiz");
        }
    }

}
