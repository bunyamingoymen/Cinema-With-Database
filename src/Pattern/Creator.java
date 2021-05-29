package Pattern;

import DAO.*;
import entity.*;

public class Creator {

    private static users u = null;
    
    private static abonelerDAO abdao = null;
    
    private static aboneler aboneler = null;

    private static actorDAO adao = null;

    private static actor actor = null;

    private static vizyondaki_filmlerDAO vfdao = null;

    private static vizyondaki_filmler vizyondaki_filmler = null;

    private static usersDAO udao = null;
    
    private static users users = null;

    private static filmlerDAO fdao = null;
    
    private static films_photosDAO fpdao = null;

    private static film_actorDAO fadao = null;

    private static filmler filmler = null;

    private static eski_filmlerDAO efdao = null;
    
    private static eski_filmler eski_filmler = null;

    private static haberlerDAO hdao = null;
    
    private static haberler haberler = null;

    private static kampanyalarDAO kdao = null;
    
    private static kampanyalar kampanyalar = null;

    private static sinema_salonlariDAO ssdao = null;

    private static yonetmenlerDAO ydao = null;

    private static seansDAO sdao = null;
    
    private static yesil_olanDAO yodao = null;

    public static users getU() {
        return u;
    }

    public static void setU(users u) {
        Creator.u = u;
    }
    
    public static abonelerDAO abonelerDao() {
        if (abdao == null) {
            abdao = new abonelerDAO();
        }
        return abdao;
    }

    public static aboneler aboneler() {
        if (aboneler == null) {
            aboneler = new aboneler();
        }
        return aboneler;
    }

    public static actorDAO actorDao() {
        if (adao == null) {
            adao = new actorDAO();
        }
        return adao;
    }

    public static actor actor() {
        if (actor == null) {
            actor = new actor();
        }
        return actor;
    }

    public static vizyondaki_filmlerDAO vizyondaki_filmlerDao() {
        if (vfdao == null) {
            vfdao = new vizyondaki_filmlerDAO();
        }
        return vfdao;
    }

    public static vizyondaki_filmler vizyondaki_filmler() {
        if (vizyondaki_filmler == null) {
            vizyondaki_filmler = new vizyondaki_filmler();
        }
        return vizyondaki_filmler;
    }

    public static usersDAO usersDao() {
        if (udao == null) {
            udao = new usersDAO();
        }
        return udao;
    }
    
    public static users users(){
        if(users == null){
            users = new  users();
        }
        return users;
    }

    public static filmlerDAO filmlerDao() {
        if (fdao == null) {
            fdao = new filmlerDAO();
        }
        return fdao;
    }
    
    public static films_photosDAO films_photoDAO(){
        if(fpdao == null){
            fpdao = new films_photosDAO();
        }
        return fpdao;
    }

    public static film_actorDAO film_actorDao() {
        if (fadao == null) {
            fadao = new film_actorDAO();
        }
        return fadao;
    }

    public static eski_filmlerDAO eski_filmlerDao() {
        if (efdao == null) {
            efdao = new eski_filmlerDAO();
        }
        return efdao;
    }
    
    public static eski_filmler eski_filmler(){
        if(eski_filmler == null){
            eski_filmler = new eski_filmler();
        }
        return eski_filmler;
    }

    public static haberlerDAO haberlerDao() {
        if (hdao == null) {
            hdao = new haberlerDAO();
        }
        return hdao;
    }
    
    public static haberler haberler() {
        if (haberler == null) {
            haberler = new haberler();
        }
        return haberler;
    }

    public static kampanyalarDAO kampanyalarDao() {
        if (kdao == null) {
            kdao = new kampanyalarDAO();
        }
        return kdao;
    }
    
    public static kampanyalar kampanyalar() {
        if (kampanyalar == null) {
            kampanyalar = new kampanyalar();
        }
        return kampanyalar;
    }

    public static sinema_salonlariDAO sinema_salonlariDao() {
        if (ssdao == null) {
            ssdao = new sinema_salonlariDAO();
        }
        return ssdao;
    }

    public static yonetmenlerDAO yonetmenlerDao() {
        if (ydao == null) {
            ydao = new yonetmenlerDAO();
        }
        return ydao;
    }

    public static seansDAO seansDao() {
        if (sdao == null) {
            sdao = new seansDAO();
        }
        return sdao;
    }
    
    public static yesil_olanDAO yesil_olanDao() {
        if (yodao == null) {
            yodao = new yesil_olanDAO();
        }
        return yodao;
    }

    public duyurular duyurular(int secim) {
        duyurular duyurular = null;
        switch (secim) {
            case 1:
                duyurular = new haberler();
                break;
            case 0:
                duyurular = new kampanyalar();
                break;
            default:
                System.out.println("Hata kodu: -44");
                break;
        }
        return duyurular;
    }

    public filmler filmler(int secim) {
        filmler filmler = null;
        switch (secim) {
            case 1:
                filmler = new vizyondaki_filmler();
                break;
            case 0:
                filmler = new eski_filmler();
                break;
            default:
                System.out.println("Hata kodu: -41");
                break;
        }
        return filmler;
    }

}
