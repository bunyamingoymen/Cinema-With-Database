package entity;

public class aboneler {

    private int abone_id;
    private int user_id;
    private int abone_type;
    private int kalan_ucretsiz_bilet_sayisi;

    public aboneler() {
    }

    public aboneler(int abone_id, int user_id, int abone_type) {
        this.abone_id = abone_id;
        this.user_id = user_id;
        this.abone_type = abone_type;
    }

    public aboneler(int user_id, int abone_type) {
        this.user_id = user_id;
        this.abone_type = abone_type;
    }

    public aboneler(int abone_id, int user_id, int abone_type, int kalan_ucretsiz_bilet_sayisi) {
        this.abone_id = abone_id;
        this.user_id = user_id;
        this.abone_type = abone_type;
        this.kalan_ucretsiz_bilet_sayisi = kalan_ucretsiz_bilet_sayisi;
    }

    public int getAbone_id() {
        return abone_id;
    }

    public void setAbone_id(int abone_id) {
        this.abone_id = abone_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getAbone_type() {
        return abone_type;
    }

    public void setAbone_type(int abone_type) {
        this.abone_type = abone_type;
    }

    public int getKalan_ucretsiz_bilet_sayisi() {
        return kalan_ucretsiz_bilet_sayisi;
    }

    public void setKalan_ucretsiz_bilet_sayisi(int kalan_ucretsiz_bilet_sayisi) {
        this.kalan_ucretsiz_bilet_sayisi = kalan_ucretsiz_bilet_sayisi;
    }

}
