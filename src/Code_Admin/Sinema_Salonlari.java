package Code_Admin;

import entity.sinema_salonlari;
import Pattern.Creator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;

public class Sinema_Salonlari extends Seans {

    /*
    
    Biizm belirleidğimize göre toplam 4 tane sinema salonu tipi var. Bu tipler koltuk sayısına göre belirleniyor.
    Tiplerimizin koltuk sayısı şu şekilde: 129,177,265,294. Bunların her biri scene builder'da bir pane tutuyor.
    ve eklenen sinema salonunun koltuk sayısına göre de o pane'leri açıp kapatıyoruz.
    
     */
    //ilk başta sinema salonu için gerekli olan değişkenler tanımlanıyor.
    //combovox'ın içine koltuk sayılarını yazmak için oluşturulan metot. Kısacası parametre olarak hangi combobox gelirse onun içine bizim daha önceden belirlediğimiz koltuk sayılarını yazıyor.
    public void koltuk_sayisi_combo(ComboBox<String> combo, String promp) {
        //ilk olarak içi daha önceden doldurulmuş olabilir diye temizleme işlemi yapıyoruz.
        combo.getItems().clear();
        //Daha sonra ekleme işlemi yapıyoruz ve her koltuk sayısını ekliyoruz.
        combo.getItems().addAll("129");
        combo.getItems().addAll("177");
        combo.getItems().addAll("265");
        combo.getItems().addAll("294");
        //daha sonra parametre olarak gelen değeri de bu combobox'ın başlangıç değeri olarak atıyoruz.
        combo.setPromptText(promp);
    }

    //bu metodun amacı da parametre olarak gele ncombo box'ın içine sinema salonlarını yazdırmak. ve yine parametre olarak gelen Stirng'i de paramtetre olarak gelen combobox'ın açılış yaızıs yapmak
    //sinema_salonlari'nin ana pane'i içinde bulunan Sinema salonlarını gör butonunun metodu. ve yaptığı tek şey sinema_salonlarini_goruntule_pane'i aktif hale getiirp combobox'ın içine sinema salonlarını teker teker yazdırmak.
    @FXML
    public void sinema_salonlarini_gor(ActionEvent event) {
        sinema_salonlari_home_pane.setVisible(false);
        sinema_salonlari_goruntule_pane.setVisible(true);

        sinema_salonlari_goruntule_combo(sinema_salonlari_goruntule, sinema_salonu_goruntule_uyari_mesaj);
    }

    //sinema_salonlarini_goruntule_pane'in içinde bulunan Combobox'ın görevini gören Sinema Slaonunu Görüntüle butonunun metodu. Tek yaptığı şey combobox da seçilen sinema salonunun koltuk düzenini görüntülüyor.
    @FXML
    public void sinema_salonunu_goruntule(ActionEvent event) {
        //ilk başta combobox'ın içindeki değeri String'e atıyoruz.
        String salonlar = sinema_salonlari_goruntule.getValue();
        //ve String'in boş olup olmadığını kontrol ediyoruz. Eğer String boş ise de kullanıcıya uyar ımesajı yazıyoruz ve metodu sonlandırıyoruz.
        if (salonlar == null) {
            sinema_salonu_goruntule_uyari_mesaj.setText("Lütfen İstediğiniz Salonu Seçiniz.");
        } else {
            //kullanıcı bir değeri seçmiş ise devam ediyoruz.
            //ilk başta dosyadan var olan sinema salonlarının bilgilerini alıyoruz

            String[] a = salonlar.split(" | ");
            int salon_id = Integer.valueOf(a[0]);
            int koltuk_sayisi = Creator.sinema_salonlariDao().koltuk_sayisi_getir(salon_id);
            //daha sonra ise koltuk sayısına göre gerekli pane'i aktif hale getiriyoruz.
            switch (koltuk_sayisi) {
                case 129:
                    //Eğer koltuk sayııs 129 ise ilk pane'i açıp diğerlerini kapatıyoruz.
                    salon_bir_pane.setVisible(true);

                    salon_iki_pane.setVisible(false);
                    salon_uc_pane.setVisible(false);
                    salon_dort_pane.setVisible(false);
                    break;
                case 177:
                    //Eğer koltuk sayııs 177 ise ikinci bane'i açıp diğerlerini kapatıyoruz.
                    salon_iki_pane.setVisible(true);

                    salon_bir_pane.setVisible(false);
                    salon_uc_pane.setVisible(false);
                    salon_dort_pane.setVisible(false);
                    break;
                case 265:
                    //Eğer koltuk sayısı 265 ise üçüncü pane'i açıp diğerlerini kapatıyoruz.
                    salon_uc_pane.setVisible(true);

                    salon_bir_pane.setVisible(false);
                    salon_iki_pane.setVisible(false);
                    salon_dort_pane.setVisible(false);
                    break;
                case 294:
                    //Eğer koltuk sayısı 294 ise dördüncü pane'i açıp diğerlerini kapatıyoruz.
                    salon_dort_pane.setVisible(true);

                    salon_bir_pane.setVisible(false);
                    salon_iki_pane.setVisible(false);
                    salon_uc_pane.setVisible(false);
                    break;
                default:
                    break;
            }

        }
    }

    //sinema_Salonlari_goruntule_pane'in içinde bulunan geri iconunun MouseEvent'ı dır. Bu sinema salonlarındaki home pane i açar diğerlerini kapatır.
    @FXML
    public void sinema_salonunu_goruntule_geri(MouseEvent event) {
        sinema_salonlari_home_pane.setVisible(true);

        sinema_salonlari_goruntule_pane.setVisible(false);
        salon_bir_pane.setVisible(false);
        salon_iki_pane.setVisible(false);
        salon_uc_pane.setVisible(false);
        salon_dort_pane.setVisible(false);

        Creator.yesil_olanDao().yesil_olan_dao_toplu_sil();
    }

    //sinema_Salonu_home_pane'in içinde bulunan yeni sinema salonu ekle butonunun metodudur. Yaptığı tek şey sinema_salonları için oluşturulmuş ekle pane'ini açıp home pane'ini kapatıyor. ve koltuk sayısını combobox'ın içine yazdırıyor.
    @FXML
    public void yeni_sinema_salonu_ekle(ActionEvent event) {
        sinema_salonlari_home_pane.setVisible(false);
        sinema_salonu_ekle_pane.setVisible(true);

        koltuk_sayisi_combo(sinema_salonu_ekle_koltuk_sayisi, "İstediğiniz Koltuk Sayısı...");
    }

    @FXML
    public void salonu_ekle(ActionEvent event) {
        String name = sinema_salonu_ekle_name.getText();
        String koltuk_sayisi = sinema_salonu_ekle_koltuk_sayisi.getValue();
        if ((koltuk_sayisi == null) || (name.length() == 0)) {
            sinema_salonu_ekle_uyari_mesaj.setText("Lütfen Gerekli Yerleri Doldurunuz.");
        } else {
            sinema_salonlari s = new sinema_salonlari(name, Integer.parseInt(koltuk_sayisi));
            int sonuc = Creator.sinema_salonlariDao().sinema_salonlari_dao_ekle(s);

            switch (sonuc) {
                case 1:
                    sinema_salonu_ekle_uyari_mesaj.setText("İşlem Başarılı Bir Şekilde Gerçekleştirildi.");
                    break;
                case -1:
                    sinema_salonu_ekle_uyari_mesaj.setText("Aynı ada sahip farklı bir salon bulunmakta. Lütfen fakrlı bir ad giriniz.");
                    break;
                default:
                    sinema_salonu_ekle_uyari_mesaj.setText("Bir hata meydana geldi Lütfen Daha Sonra Tekrar Deneyiniz");
                    break;
            }
        }
    }

    @FXML
    public void yeni_sinema_salonu_ekle_geri(MouseEvent event) {
        sinema_salonlari_home_pane.setVisible(true);
        sinema_salonu_ekle_pane.setVisible(false);

    }

    @FXML
    public void sinema_salonu_duzenle_sil(ActionEvent event) {
        sinema_salonlari_home_pane.setVisible(false);
        sinema_salonu_duzenle_pane.setVisible(true);
        sinema_salonu_duzenle_pane_1.setVisible(true);
        sinema_salonu_duzenle_pane_2.setVisible(false);

        sinema_salonlari_goruntule_combo(sinema_salonlarini_duzenle_combo, sinema_salonu_guncelle_uyari_mesaj);

    }

    //sinema_salonu_duzenle_sil_pane'indeki sinema_Salonunu getir botununun metodudur.
    @FXML
    public void sinema_salonunu_getir(ActionEvent event) {
        String secilen = sinema_salonlarini_duzenle_combo.getValue();
        if (secilen == null) {
            sinema_salonu_guncelle_uyari_mesaj.setText("Lütfen Bir Değer Seçiniz");
        } else {
            String[] a = secilen.split(" | ");

            sinema_salonunu_guncelle_id.setText(a[0]);
            int salon_id = Integer.parseInt(a[0]);
            int koltuk_sayisi = Creator.sinema_salonlariDao().koltuk_sayisi_getir(salon_id);
            koltuk_sayisi_combo(sinema_salonlarini_duzenle_koltuk_sayisi, "");
            sinema_salonlarini_duzenle_koltuk_sayisi.setValue(String.valueOf(koltuk_sayisi));
            sinema_salonu_guncelle_name.setText(Creator.sinema_salonlariDao().salon_adi_getir(salon_id));

            sinema_salonu_duzenle_pane_2.setVisible(true);
        }
    }

    //sinema_Salonu_duzenle_sil pane'deki Sinema_Salonunu sil botununun metodu.
    @FXML
    public void sinema_salonunu_sil(ActionEvent event) {

        if (sinema_salonunu_guncelle_id.getText().length() == 0) {
            sinema_salonu_guncelle_uyari_mesaj.setText("Lütfen bir değer seçiniz.");
        } else {
            int salon_id = Integer.valueOf(sinema_salonunu_guncelle_id.getText());
            int sonuc = Creator.sinema_salonlariDao().sinema_salonlari_dao_sil(salon_id);

            if (sonuc == 1) {

                sinema_salonlari_goruntule_combo(sinema_salonlarini_duzenle_combo, sinema_salonu_guncelle_uyari_mesaj);
                koltuk_sayisi_combo(sinema_salonlarini_duzenle_koltuk_sayisi, "");
                sinema_salonu_guncelle_name.setText("");
                sinema_salonunu_guncelle_id.setText("");

                sinema_salonu_guncelle_uyari_mesaj.setText("İşlem Başarılı Bir şekilde Yapıldı");

            } else {
                sinema_salonu_guncelle_uyari_mesaj.setText("Bir hata meydana geldi(appController.sinema_salonunu_sil(ActionEvent event))");
            }
        }

    }

    //Sinema_Salonu_guncelle_sil pane'in içinde bulunan Sinema Salonunu Güncelle butonunun metodu. Tek yaptığı şey kullanıcnın veridği verilere göre salon bilgilerini güncelleyip dosyaya yazmak.
    @FXML
    public void sinema_salonunu_guncelle(ActionEvent event) {
        if ((sinema_salonu_guncelle_name.getText().length() == 0) || (sinema_salonlarini_duzenle_koltuk_sayisi.getValue() == null)) {
            sinema_salonu_guncelle_uyari_mesaj.setText("Lütfen Gerekli Yerleri Doldurunuz.");
        } else {
            String name = sinema_salonu_guncelle_name.getText();

            int koltuk_sayisi = Integer.valueOf(sinema_salonlarini_duzenle_koltuk_sayisi.getValue());

            sinema_salonlari s = new sinema_salonlari(Integer.valueOf(sinema_salonunu_guncelle_id.getText()), name, koltuk_sayisi);
            int control = Creator.sinema_salonlariDao().sinema_salonlari_degistir(s);

            switch (control) {
                case 1:
                    sinema_salonlari_goruntule_combo(sinema_salonlarini_duzenle_combo, sinema_salonu_guncelle_uyari_mesaj);
                    sinema_salonu_guncelle_uyari_mesaj.setText("İşlem Başarılı bir şekilde gerçekleştirildi");
                    break;
                case -1:
                    sinema_salonu_guncelle_uyari_mesaj.setText("Aynı ada sahip başka bir salon bulunmakta. Lütfen farklı bir ad giriniz.");
                    break;
                default:
                    sinema_salonu_guncelle_uyari_mesaj.setText("Bir hata meydana geldi. Lütfen daha sonra tekrar deneyiniz(Hata Kodu: -7)");
                    break;
            }

        }
    }

    @FXML
    public void sinema_salonu_duzenle_sil_geri(MouseEvent event) {
        sinema_salonu_duzenle_pane.setVisible(false);
        sinema_salonlari_home_pane.setVisible(true);
    }
}
