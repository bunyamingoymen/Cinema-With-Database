package Pattern;

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
    
    public void eski_filmler_degistir(){
        /*
            burada ilk olarak filmler tablosunda değişim ardından da eski filmler tablosunda değişim olmalı
        */
    }
    
    public void filmler_film_sayisi_azalt(){
        //filmler silme işlemi buradan yapılacak. Filmler silinirken filmlere bağlı olan yönetmen_id'nin film sayısını bir azaltmamız gerek. Bu metot onu yapıyor.
        /*
        Bu metoda yollanan film id'ye göre ilk yonetmen_id bul
        sonra filmler tablosundan sil
        ardından yonetmenler tablosundaki gerekli metoda yollayarak film sayısını azalt
        */
    }
}
