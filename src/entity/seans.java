package entity;

public class seans {

    private int seans_id;
    private int salon_id;
    private int vizyondaki_film_id;
    private String film_name;
    private String salon_name;
    private String saat;

    public seans() {
    }

    public seans(int seans_id, int salon_id, int vizyondaki_film_id, String saat) {
        this.seans_id = seans_id;
        this.salon_id = salon_id;
        this.vizyondaki_film_id = vizyondaki_film_id;
        this.saat = saat;
    }

    public seans(int salon_id, int vizyondaki_film_id, String saat) {
        this.salon_id = salon_id;
        this.vizyondaki_film_id = vizyondaki_film_id;
        this.saat = saat;
    }

    public seans(int seans_id, int salon_id, int vizyondaki_film_id, String film_name, String salon_name, String saat) {
        this.seans_id = seans_id;
        this.salon_id = salon_id;
        this.vizyondaki_film_id = vizyondaki_film_id;
        this.film_name = film_name;
        this.salon_name = salon_name;
        this.saat = saat;
    }


    public seans(String film_name, String salon_name, String saat) {
        this.film_name = film_name;
        this.salon_name = salon_name;
        this.saat = saat;
    }

    public int getSeans_id() {
        return seans_id;
    }

    public void setSeans_id(int seans_id) {
        this.seans_id = seans_id;
    }

    public int getSalon_id() {
        return salon_id;
    }

    public void setSalon_id(int salon_id) {
        this.salon_id = salon_id;
    }

    public int getVizyondaki_film_id() {
        return vizyondaki_film_id;
    }

    public void setVizyondaki_film_id(int vizyondaki_film_id) {
        this.vizyondaki_film_id = vizyondaki_film_id;
    }

    public String getFilm_name() {
        return film_name;
    }

    public void setFilm_name(String film_name) {
        this.film_name = film_name;
    }

    public String getSalon_name() {
        return salon_name;
    }

    public void setSalon_name(String salon_name) {
        this.salon_name = salon_name;
    }

    public String getSaat() {
        return saat;
    }

    public void setSaat(String saat) {
        this.saat = saat;
    }

}
