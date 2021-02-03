package entity;

public class yesil_olan {

    private int yesil_olan_id;
    private String koltuk_adi;

    public yesil_olan() {
    }

    public yesil_olan(int yesil_olan_id, String koltuk_adi) {
        this.yesil_olan_id = yesil_olan_id;
        this.koltuk_adi = koltuk_adi;
    }

    public yesil_olan(String koltuk_adi) {
        this.koltuk_adi = koltuk_adi;
    }

    public int getYesil_olan_id() {
        return yesil_olan_id;
    }

    public void setYesil_olan_id(int yesil_olan_id) {
        this.yesil_olan_id = yesil_olan_id;
    }

    public String getKoltuk_adi() {
        return koltuk_adi;
    }

    public void setKoltuk_adi(String koltuk_adi) {
        this.koltuk_adi = koltuk_adi;
    }

}
