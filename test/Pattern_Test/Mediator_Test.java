/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pattern_Test;

import Pattern.Creator;
import Pattern.Mediator;
import entity.eski_filmler;
import entity.filmler;
import java.util.LinkedList;
import org.junit.Test;

/**
 *
 * @author bgoym
 */
public class Mediator_Test {

    @Test
    public void testmain() {
        test_eski_filmler_ve_filmler_ekle();
        System.out.println("eski_filmler_ve_filmler_ekle calisti");
        System.out.println(" ");

        test_eski_filmler_tamamen_sil();
        System.out.println("test_eski_filmler_tamamen_sil calisti");
        System.out.println(" ");

        test_eski_filmler_sadece_eskiden_sil();
        System.out.println("test_eski_filmler_sadece_eskiden_sil calisti");
        System.out.println(" ");

        test_eski_filmler_degistir();
        System.out.println("test_eski_filmler_degistir calisti");
        System.out.println(" ");

        test_eski_filmler_toplu_sil();
        System.out.println("test_eski_filmler_toplu_sil calisti");
        System.out.println(" ");

        test_eski_filmler_toplu_sil_film_id_ile();
        System.out.println("test_eski_filmler_toplu_sil_film_id_ile calisti");
        System.out.println(" ");

    }

    public void test_eski_filmler_ve_filmler_ekle() {
        Mediator m = new Mediator();
        int sonuc = m.eski_filmler_ve_filmler_ekle("Mediator_Test", 155, "Aksiyon", 3978, 1, 5);

        if (sonuc == 1) {
            System.out.println("Basarili: eski_filmler_ve_filmler_ekle");
        } else {
            System.out.println("Basarisiz: eski_filmler_ve_filmler_ekle");
        }
    }

    public void test_eski_filmler_tamamen_sil() {
        LinkedList<eski_filmler> list = Creator.eski_filmlerDao().read();
        int eski_film_id = list.getLast().getEski_film_id();

        Mediator m = new Mediator();
        int sonuc = m.eski_filmler_tamamen_sil(eski_film_id);

        if (sonuc == 1) {
            System.out.println("Basarili: eski_filmler_tamamen_sil");
        } else {
            System.out.println("Basarisiz: eski_filmler_tamamen_sil");
        }

    }

    public void test_eski_filmler_sadece_eskiden_sil() {

        Mediator m = new Mediator();
        int sonuc = m.eski_filmler_ve_filmler_ekle("Mediator_Test", 155, "Aksiyon", 3978, 1, 5);

        if (sonuc == 1) {
            LinkedList<eski_filmler> list = Creator.eski_filmlerDao().read();
            int eski_film_id = list.getLast().getEski_film_id();
            int sonuc2 = m.eski_filmler_sadece_eskiden_sil(eski_film_id);
            if (sonuc2 == 1) {
                System.out.println("Basarili: eski_filmler_sadece_eskiden_sil");
            } else {
                System.out.println("Basarisiz(2): eski_filmler_sadece_eskiden_sil");
            }
        } else {
            System.out.println("Basarisiz(1): eski_filmler_sadece_eskiden_sil");
        }
    }

    public void test_eski_filmler_degistir() {
        Mediator m = new Mediator();
        int sonuc = m.eski_filmler_ve_filmler_ekle("Mediator_Test", 155, "Aksiyon", 3978, 1, 5);

        if (sonuc == 1) {
            LinkedList<eski_filmler> list_eski_filmler = Creator.eski_filmlerDao().read();
            eski_filmler e = list_eski_filmler.getLast();

            LinkedList<eski_filmler> list_filmler = Creator.filmlerDao().read();
            filmler f = list_filmler.getLast();

            int sonuc2 = m.eski_filmler_degistir(list_eski_filmler.getLast(), list_filmler.getLast());
            if (sonuc2 == 1) {
                System.out.println("Basarili: eski_filmler_degistir");
            } else {
                System.out.println("Basarisi(2): eski_filmler_degistir");
            }
        } else {
            System.out.println("Basarisi(1): eski_filmler_degistir");
        }
    }

    public void test_eski_filmler_toplu_sil() {
        Mediator m = new Mediator();
        int sonuc = m.eski_filmler_ve_filmler_ekle("Mediator_Test", 155, "Aksiyon", 3978, 1, 5);
        int sonuc2 = m.eski_filmler_ve_filmler_ekle("Mediator_Test", 155, "Aksiyon", 3978, 1, 5);
        int sonuc3 = m.eski_filmler_ve_filmler_ekle("Mediator_Test", 155, "Aksiyon", 3978, 1, 5);

        if (sonuc == 1 && sonuc2 == 1 && sonuc3 == 1) {
            LinkedList<eski_filmler> list_eski_filmler = Creator.eski_filmlerDao().read();
            LinkedList<Integer> list = new LinkedList<>();
            list.add(list_eski_filmler.get(list_eski_filmler.size()-1).getEski_film_id());
            list.add(list_eski_filmler.get(list_eski_filmler.size()-2).getEski_film_id());
            list.add(list_eski_filmler.get(list_eski_filmler.size()-3).getEski_film_id());
            m.eski_filmler_toplu_sil(list);

            LinkedList<eski_filmler> list_eski_filmler2 = Creator.eski_filmlerDao().read();
            if (list_eski_filmler2.size() == list_eski_filmler.size()) {
                System.out.println("Basarisiz(2): eski_filmler_toplu_sil - list_eski_filmler2.size(): " + list_eski_filmler2.size());
            } else {
                System.out.println("Basarili: eski_filmler_toplu_sil");
            }
        } else {
            System.out.println("Basarisiz(1): eski_filmler_toplu_sil - sonuc: " + sonuc + ", sonuc2: " + sonuc2 + ", sonuc3 = " + sonuc3);
        }
    }

    public void test_eski_filmler_toplu_sil_film_id_ile() {
        Mediator m = new Mediator();
        int sonuc = m.eski_filmler_ve_filmler_ekle("Mediator_Test", 155, "Aksiyon", 3978, 1, 5);
        int sonuc2 = m.eski_filmler_ve_filmler_ekle("Mediator_Test", 155, "Aksiyon", 3978, 1, 5);
        int sonuc3 = m.eski_filmler_ve_filmler_ekle("Mediator_Test", 155, "Aksiyon", 3978, 1, 5);

        if (sonuc == 1 && sonuc2 == 1 && sonuc3 == 1) {
            LinkedList<filmler> list_filmler = Creator.filmlerDao().read();
            LinkedList<Integer> list = new LinkedList<>();
            list.add(list_filmler.get(list_filmler.size()-1).getFilm_id());
            list.add(list_filmler.get(list_filmler.size()-2).getFilm_id());
            list.add(list_filmler.get(list_filmler.size()-3).getFilm_id());
            m.eski_filmler_toplu_sil_film_id_ile(list);

            LinkedList<filmler> list_filmler2 = Creator.eski_filmlerDao().read();
            if (list_filmler2.size() == list_filmler.size()) {
                System.out.println("Basarisiz(2): eski_filmler_toplu_sil_film_id_ile - list_filmler2.size(): " + list_filmler2.size());
            } else {
                System.out.println("Basarili: eski_filmler_toplu_sil_film_id_ile");
            }
        } else {
            System.out.println("Basarisiz(1): eski_filmler_toplu_sil_film_id_ile - sonuc: " + sonuc + ", sonuc2: " + sonuc2 + ", sonuc3 = " + sonuc3);
        }

    }
}
