package entity;

public class kullanici_degerlendirmesi {

    private int kullanici_degerlendirme_id;
    private int user_id;
    private int film_id;
    private int degerlendirme;

    public kullanici_degerlendirmesi() {
    }

    public kullanici_degerlendirmesi(int kullanici_degerlendirme_id, int user_id, int film_id, int degerlendirme) {
        this.kullanici_degerlendirme_id = kullanici_degerlendirme_id;
        this.user_id = user_id;
        this.film_id = film_id;
        this.degerlendirme = degerlendirme;
    }

    public kullanici_degerlendirmesi(int user_id, int film_id, int degerlendirme) {
        this.user_id = user_id;
        this.film_id = film_id;
        this.degerlendirme = degerlendirme;
    }

    public int getKullanici_degerlendirme_id() {
        return kullanici_degerlendirme_id;
    }

    public void setKullanici_degerlendirme_id(int kullanici_degerlendirme_id) {
        this.kullanici_degerlendirme_id = kullanici_degerlendirme_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getFilm_id() {
        return film_id;
    }

    public void setFilm_id(int film_id) {
        this.film_id = film_id;
    }

    public int getDegerlendirme() {
        return degerlendirme;
    }

    public void setDegerlendirme(int degerlendirme) {
        this.degerlendirme = degerlendirme;
    }

}
