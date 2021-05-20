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

        adao.aktorler_ekle(a);
        System.out.println("Aktör eklendi");
        actor_id = adao.son_actor_id_getir();

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

        film_id[0] = fdao.filmler_ekle_id_gonder(f);
        film_id[1] = fdao.filmler_ekle_id_gonder(f2);
        film_id[2] = fdao.filmler_ekle_id_gonder(f3);
        film_id[3] = fdao.filmler_ekle_id_gonder(f4);
        film_id[4] = fdao.filmler_ekle_id_gonder(f5);
        film_id[5] = fdao.filmler_ekle_id_gonder(f6);
        film_id[6] = fdao.filmler_ekle_id_gonder(f7);
        film_id[7] = fdao.filmler_ekle_id_gonder(f8);
        film_id[8] = fdao.filmler_ekle_id_gonder(f9);
        film_id[9] = fdao.filmler_ekle_id_gonder(f10);

        System.out.println("Filmler eklendi");

        vizyondaki_filmlerDAO vfdao = new vizyondaki_filmlerDAO();
        vizyondaki_filmler v = new vizyondaki_filmler(film_id[0], LocalDate.now(), 0);
        vizyondaki_filmler v2 = new vizyondaki_filmler(film_id[1], LocalDate.now(), 0);
        vizyondaki_filmler v3 = new vizyondaki_filmler(film_id[2], LocalDate.now(), 0);
        vizyondaki_filmler v4 = new vizyondaki_filmler(film_id[3], LocalDate.now(), 0);
        vizyondaki_filmler v5 = new vizyondaki_filmler(film_id[4], LocalDate.now(), 0);

        vfdao.vizyondaki_filmler_dao_ekle(v);
        vfdao.vizyondaki_filmler_dao_ekle(v2);
        vfdao.vizyondaki_filmler_dao_ekle(v3);
        vfdao.vizyondaki_filmler_dao_ekle(v4);
        vfdao.vizyondaki_filmler_dao_ekle(v5);

        System.out.println("Vizyondaki Filmler Eklendi");

        eski_filmlerDAO efdao = new eski_filmlerDAO();
        eski_filmler ed = new eski_filmler(film_id[5], 0, 1);
        eski_filmler ed2 = new eski_filmler(film_id[6], 0, 1);
        eski_filmler ed3 = new eski_filmler(film_id[7], 0, 1);
        eski_filmler ed4 = new eski_filmler(film_id[8], 0, 1);
        eski_filmler ed5 = new eski_filmler(film_id[9], 0, 1);

        efdao.eski_filmler_dao_ekle(ed);
        efdao.eski_filmler_dao_ekle(ed2);
        efdao.eski_filmler_dao_ekle(ed3);
        efdao.eski_filmler_dao_ekle(ed4);
        efdao.eski_filmler_dao_ekle(ed5);

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

        fadao.film_actor_insert(fa);
        fadao.film_actor_insert(fa2);
        fadao.film_actor_insert(fa3);
        fadao.film_actor_insert(fa4);
        fadao.film_actor_insert(fa5);
        fadao.film_actor_insert(fa6);
        fadao.film_actor_insert(fa7);
        fadao.film_actor_insert(fa8);
        fadao.film_actor_insert(fa9);
        fadao.film_actor_insert(fa10);

        System.out.println("Film Actor Eklendi");

    }

    @After
    public void After_Test_aktorler_her_seyi_sil() {
        int[] test = new int[10];
        filmlerDAO fdao = new filmlerDAO();

        test[0] = fdao.film_id_var_mi(film_id[0]);
        test[1] = fdao.film_id_var_mi(film_id[1]);
        test[2] = fdao.film_id_var_mi(film_id[2]);
        test[3] = fdao.film_id_var_mi(film_id[3]);
        test[4] = fdao.film_id_var_mi(film_id[4]);
        test[5] = fdao.film_id_var_mi(film_id[5]);
        test[6] = fdao.film_id_var_mi(film_id[6]);
        test[7] = fdao.film_id_var_mi(film_id[7]);
        test[8] = fdao.film_id_var_mi(film_id[8]);
        test[9] = fdao.film_id_var_mi(film_id[9]);

        if (test[0] == 0 && test[1] == 0 && test[2] == 0 && test[3] == 0 && test[4] == 0 && test[5] == 0 && test[6] == 0 && test[7] == 0 && test[8] == 0 && test[9] == 0) {
            System.out.println("Test Basarili");
        }else{
            System.out.println("Test Basarisiz");
        }
    }

}
