/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author bgoym
 */
public class Center {

    private aboneler aboneler;
    private actor actor;
    private duyurular duyurular;
    private eski_filmler eski_filmler;
    private film_actor film_actor;
    private filmler filmler;
    private films_photos films_photos;
    private haberler haberler;
    private kampanyalar kampanyalar;
    private kullanici_degerlendirmesi kullanici_degerlendirmesi;
    private satin_alinan_biletler satin_alinan_biletler;
    private seans seans;
    private sinema_salonlari sinema_salonlari;
    private user_photos user_photos;
    private users users;
    private vizyondaki_filmler vizyondaki_filmler;
    private yesil_olan yesil_olan;
    private yonetmenler yonetmenler;

    public Center(aboneler aboneler) {
        this.aboneler = aboneler;
    }
    
    public Center(int user_id ,int abone_type) {
        aboneler aboneler = new aboneler(user_id, abone_type);
        this.aboneler = aboneler;
    }

    public Center(actor actor) {
        this.actor = actor;
    }
    
    public Center(int actor_id, String ad, String soyad){
        actor actor = new actor(actor_id, ad, soyad);
        this.actor = actor;
    }

    public Center(duyurular duyurular) {
        this.duyurular = duyurular;
    }

    public Center(eski_filmler eski_filmler) {
        this.eski_filmler = eski_filmler;
    }

    public Center(film_actor film_actor) {
        this.film_actor = film_actor;
    }

    public Center(filmler filmler) {
        this.filmler = filmler;
    }

    public Center(films_photos films_photos) {
        this.films_photos = films_photos;
    }

    public Center(haberler haberler) {
        this.haberler = haberler;
    }

    public Center(kampanyalar kampanyalar) {
        this.kampanyalar = kampanyalar;
    }

    public Center(kullanici_degerlendirmesi kullanici_degerlendirmesi) {
        this.kullanici_degerlendirmesi = kullanici_degerlendirmesi;
    }

    public Center(satin_alinan_biletler satin_alinan_biletler) {
        this.satin_alinan_biletler = satin_alinan_biletler;
    }

    public Center(seans seans) {
        this.seans = seans;
    }

    public Center(sinema_salonlari sinema_salonlari) {
        this.sinema_salonlari = sinema_salonlari;
    }

    public Center(user_photos user_photos) {
        this.user_photos = user_photos;
    }

    public Center(users users) {
        this.users = users;
    }

    public Center(vizyondaki_filmler vizyondaki_filmler) {
        this.vizyondaki_filmler = vizyondaki_filmler;
    }

    public Center(yesil_olan yesil_olan) {
        this.yesil_olan = yesil_olan;
    }

    public Center(yonetmenler yonetmenler) {
        this.yonetmenler = yonetmenler;
    }

    public aboneler getAboneler() {
        return aboneler;
    }

    public void setAboneler(aboneler aboneler) {
        this.aboneler = aboneler;
    }

    public actor getActor() {
        return actor;
    }

    public void setActor(actor actor) {
        this.actor = actor;
    }

    public duyurular getDuyurular() {
        return duyurular;
    }

    public void setDuyurular(duyurular duyurular) {
        this.duyurular = duyurular;
    }

    public eski_filmler getEski_filmler() {
        return eski_filmler;
    }

    public void setEski_filmler(eski_filmler eski_filmler) {
        this.eski_filmler = eski_filmler;
    }

    public film_actor getFilm_actor() {
        return film_actor;
    }

    public void setFilm_actor(film_actor film_actor) {
        this.film_actor = film_actor;
    }

    public filmler getFilmler() {
        return filmler;
    }

    public void setFilmler(filmler filmler) {
        this.filmler = filmler;
    }

    public films_photos getFilms_photos() {
        return films_photos;
    }

    public void setFilms_photos(films_photos films_photos) {
        this.films_photos = films_photos;
    }

    public haberler getHaberler() {
        return haberler;
    }

    public void setHaberler(haberler haberler) {
        this.haberler = haberler;
    }

    public kampanyalar getKampanyalar() {
        return kampanyalar;
    }

    public void setKampanyalar(kampanyalar kampanyalar) {
        this.kampanyalar = kampanyalar;
    }

    public kullanici_degerlendirmesi getKullanici_degerlendirmesi() {
        return kullanici_degerlendirmesi;
    }

    public void setKullanici_degerlendirmesi(kullanici_degerlendirmesi kullanici_degerlendirmesi) {
        this.kullanici_degerlendirmesi = kullanici_degerlendirmesi;
    }

    public satin_alinan_biletler getSatin_alinan_biletler() {
        return satin_alinan_biletler;
    }

    public void setSatin_alinan_biletler(satin_alinan_biletler satin_alinan_biletler) {
        this.satin_alinan_biletler = satin_alinan_biletler;
    }

    public seans getSeans() {
        return seans;
    }

    public void setSeans(seans seans) {
        this.seans = seans;
    }

    public sinema_salonlari getSinema_salonlari() {
        return sinema_salonlari;
    }

    public void setSinema_salonlari(sinema_salonlari sinema_salonlari) {
        this.sinema_salonlari = sinema_salonlari;
    }

    public user_photos getUser_photos() {
        return user_photos;
    }

    public void setUser_photos(user_photos user_photos) {
        this.user_photos = user_photos;
    }

    public users getUsers() {
        return users;
    }

    public void setUsers(users users) {
        this.users = users;
    }

    public vizyondaki_filmler getVizyondaki_filmler() {
        return vizyondaki_filmler;
    }

    public void setVizyondaki_filmler(vizyondaki_filmler vizyondaki_filmler) {
        this.vizyondaki_filmler = vizyondaki_filmler;
    }

    public yesil_olan getYesil_olan() {
        return yesil_olan;
    }

    public void setYesil_olan(yesil_olan yesil_olan) {
        this.yesil_olan = yesil_olan;
    }

    public yonetmenler getYonetmenler() {
        return yonetmenler;
    }

    public void setYonetmenler(yonetmenler yonetmenler) {
        this.yonetmenler = yonetmenler;
    }

}
