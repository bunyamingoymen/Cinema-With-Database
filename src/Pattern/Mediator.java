package Pattern;

import entity.Center;
import entity.aboneler;

public class Mediator {

    public void eski_filmler_tamamen_sil() {
        /*
        burası hem eski filmlerden hemde filmlerden silmeli
         */
    }

    public void eski_filmler_Sadece_eskiden_sil() {
        /*
            burası eski filmlerden silip vizyondaki filmlere eklemeli
         */

    }

    public void eski_filmler_degistir() {
        /*
            burada ilk olarak filmler tablosunda değişim ardından da eski filmler tablosunda değişim olmalı
         */
    }

    public void eski_filmler_toplu_sil() {
        //sadece veski filmlerden silme işlemiş yapılacak
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

            sonuc = Creator.abonelerDao().buy(nw);
        }

        return sonuc;
    }

    public void vizyondaki_filmler_tamamen_sil() {
        /* Hem vizyondaki_filmlerden, hem filmlerden hem de film_actor den silmeli
         */
    }

    public void vizyondaki_filmler_sadece_vziyodnan_sil() {
        /* Vizyondaki filmlerden silip eski filme eklemei
         */
    }

    public void vizyondaki_filmler_değiştir() {
        //hem vizyondaki bilgileri hemde eski filmlerdeki bilgileir değiştirir.
    }

    public void vizyondaki_filmler_toplu_sil() {
        //sadee vizyodnaki filmlerden toplu silme işlemi yapılacak
    }
}
