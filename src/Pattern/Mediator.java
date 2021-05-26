package Pattern;

import entity.Center;
import entity.aboneler;
import entity.eski_filmler;
import entity.filmler;
import entity.vizyondaki_filmler;
import java.time.LocalDate;
import java.util.LinkedList;

public class Mediator {

    public int eski_filmler_ve_filmler_ekle(String film_name, int film_suresi, String film_type, int yonetmen_id, int hangi_abone, int aldigi_odul) {
        filmler f = new filmler(film_name, film_suresi, film_type, yonetmen_id, 0);
        Center nw = new Center(f);
        int film_id = Creator.filmlerDao().create(nw);

        eski_filmler ef = new eski_filmler(film_id, hangi_abone, aldigi_odul, (float) 0);
        Center nw2 = new Center(ef);
        int sonuc = Creator.eski_filmlerDao().create(nw2);

        return sonuc;
    }

    public int eski_filmler_tamamen_sil(int eski_film_id) {
        /*
        burası hem eski filmlerden hemde filmlerden silmeli
         */
        int film_id = Creator.eski_filmlerDao().search_int(eski_film_id, 5);
        int sonuc = Creator.eski_filmlerDao().delete(eski_film_id);
        int sonuc2 = Creator.filmlerDao().delete(film_id);
        if (sonuc == 1 && sonuc2 == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    public int eski_filmler_sadece_eskiden_sil(int eski_film_id) {
        /*
            burası eski filmlerden silip vizyondaki filmlere eklemeli
         */
        int film_id = Creator.eski_filmlerDao().search_int(eski_film_id, 5);
        int sonuc = Creator.vizyondaki_filmlerDao().create(film_id);
        if (sonuc == 1) {
            int sonuc2 = Creator.eski_filmlerDao().delete(eski_film_id);
            return sonuc2;
        }
        return sonuc;

    }

    public int eski_filmler_degistir(eski_filmler ef, filmler f) {
        /*
            burada ilk olarak filmler tablosunda değişim ardından da eski filmler tablosunda değişim olmalı
         */

        Center nw = new Center(f);
        int sonuc = Creator.filmlerDao().update(nw);

        Center nw2 = new Center(ef);
        int sonuc2 = Creator.eski_filmlerDao().update(nw2);

        if (sonuc == 1 && sonuc2 == 1) {
            return 1;
        }
        return 0;

    }

    public void eski_filmler_toplu_sil(LinkedList<Integer> list) {
        //sadece veski filmlerden silme işlemiş yapılacak

        for (int i = 0; i < list.size(); i++) {
            Creator.eski_filmlerDao().delete(list.get(i));
        }
    }

    public void eski_filmler_toplu_sil_film_id_ile(LinkedList<Integer> list) {
        //sadece veski filmlerden silme işlemiş yapılacak

        for (int i = 0; i < list.size(); i++) {
            Creator.eski_filmlerDao().delete_film_id(list.get(i));
        }
    }

    public void filmler_film_sayisi_azalt() {
        //filmler silme işlemi buradan yapılacak. Filmler silinirken filmlere bağlı olan yönetmen_id'nin film sayısını bir azaltmamız gerek. Bu metot onu yapıyor.
        /*
        Bu metoda yollanan film id'ye göre ilk yonetmen_id bul
        sonra filmler tablosundan sil
        ardından yonetmenler tablosundaki gerekli metoda yollayarak film sayısını azalt
         */
    }

    public int user_guncelle_aboneli(Center nw, int abone_type) {
        /*
        user bilgilerini güncellerken abone değişikliği olacak ise hem user sınıfında güncelleme olmalı hem de abone sınıfında güncelleme olmalı.
         */
        int sonuc = 0;
        if (abone_type == 0) {
            sonuc = Creator.abonelerDao().delete(nw.getUsers().getUser_id());
        } else {

            aboneler a = new aboneler(nw.getUsers().getUser_id(), abone_type);
            Center nw2 = new Center(a);

            sonuc = Creator.abonelerDao().buy(nw2);
        }

        return sonuc;
    }

    public int vizyondaki_filmler_ve_filmler_ekle(String film_name, int film_suresi, String film_type, int yonetmen_id, LocalDate kalkis) {
        filmler f = new filmler(film_name, film_suresi, film_type, yonetmen_id, 0);
        Center nw = new Center(f);
        int film_id = Creator.filmlerDao().create(nw);

        vizyondaki_filmler v = new vizyondaki_filmler(film_id, kalkis, 0);
        Center nw2 = new Center(v);
        int sonuc = Creator.vizyondaki_filmlerDao().create(nw2);

        return sonuc;
    }

    public int vizyondaki_filmler_tamamen_sil(int vizyondaki_film_id) {
        /* Hem vizyondaki_filmlerden, hem filmlerden hem de film_actor den silmeli
         */
        int film_id = Creator.vizyondaki_filmlerDao().search_int(vizyondaki_film_id, 1, 1);
        int sonuc = Creator.vizyondaki_filmlerDao().delete(vizyondaki_film_id);
        if (sonuc == 1) {
            int sonuc2 = Creator.filmlerDao().delete(film_id);
            return sonuc2;
        }
        return sonuc;
    }

    public int vizyondaki_filmler_sadece_vziyodnan_sil(int vizyondaki_film_id) {
        /* Vizyondaki filmlerden silip eski filme eklemei
         */
        int film_id = Creator.vizyondaki_filmlerDao().search_int(vizyondaki_film_id, 1, 1);
        int sonuc = Creator.eski_filmlerDao().create(film_id);
        if (sonuc == 1) {
            int sonuc2 = Creator.vizyondaki_filmlerDao().delete(vizyondaki_film_id);
            return sonuc2;
        }
        return sonuc;
    }

    public int vizyondaki_filmler_degiştir(vizyondaki_filmler v, filmler f) {
        //hem vizyondaki bilgileri hemde eski filmlerdeki bilgileir değiştirir.
        Center nw = new Center(f);
        int sonuc = Creator.filmlerDao().update(nw);
        Center nw2 = new Center(v);
        int sonuc2 = Creator.vizyondaki_filmlerDao().update(nw2);

        if (sonuc == 1 && sonuc2 == 1) {
            return 1;
        }
        return 0;

    }

    public void vizyondaki_filmler_toplu_sil(LinkedList<Integer> list) {
        //sadee vizyodnaki filmlerden toplu silme işlemi yapılacak
        for (int i = 0; i < list.size(); i++) {
            Creator.vizyondaki_filmlerDao().delete(list.get(i));
        }
    }

    public void vizyondaki_filmler_toplu_sil_film_id_ile(LinkedList<Integer> list) {
        //sadee vizyodnaki filmlerden toplu silme işlemi yapılacak
        for (int i = 0; i < list.size(); i++) {
            Creator.vizyondaki_filmlerDao().delete_film_id(list.get(i));
        }
    }

    public void filmler_toplu_sil(LinkedList<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            Creator.filmlerDao().delete(list.get(i));
        }
    }

    public void film_actor_toplu_sil_film_id_uzerinden(LinkedList<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            Creator.film_actorDao().delete_film_id(list.get(i));
        }
    }
}
