package Controller;

import Code_Admin.Center_Admin;
import Creator.Creator;
import DAO.*;
import entity.*;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;

public class appController extends Center_Admin implements Initializable {

    /*
    Eski Filmler
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
     */
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /*
    Kampanyalar
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     */
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /*
    Haberler
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     */
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /*
    Sinema Salonları
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     */

    //Sinema salonu işlemleri burada yapılıyor.
    /*
    
    Biizm belirleidğimize göre toplam 4 tane sinema salonu tipi var. Bu tipler koltuk sayısına göre belirleniyor.
    Tiplerimizin koltuk sayısı şu şekilde: 129,177,265,294. Bunların her biri scene builder'da bir pane tutuyor.
    ve eklenen sinema salonunun koltuk sayısına göre de o pane'leri açıp kapatıyoruz.
    
     */
    //ilk başta sinema salonu için gerekli olan değişkenler tanımlanıyor.
   

    //combovox'ın içine koltuk sayılarını yazmak için oluşturulan metot. Kısacası parametre olarak hangi combobox gelirse onun içine bizim daha önceden belirlediğimiz koltuk sayılarını yazıyor.
    private void koltuk_sayisi_combo(ComboBox<String> combo, String promp) {
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
    private void sinema_salonlarini_gor(ActionEvent event) {
        sinema_salonlari_home_pane.setVisible(false);
        sinema_salonlari_goruntule_pane.setVisible(true);

        sinema_salonlari_goruntule_combo(sinema_salonlari_goruntule, sinema_salonu_goruntule_uyari_mesaj);
    }

    //sinema_salonlarini_goruntule_pane'in içinde bulunan Combobox'ın görevini gören Sinema Slaonunu Görüntüle butonunun metodu. Tek yaptığı şey combobox da seçilen sinema salonunun koltuk düzenini görüntülüyor.
    @FXML
    private void sinema_salonunu_goruntule(ActionEvent event) {
        //ilk başta combobox'ın içindeki değeri String'e atıyoruz.
        String salonlar = sinema_salonlari_goruntule.getValue();
        //ve String'in boş olup olmadığını kontrol ediyoruz. Eğer String boş ise de kullanıcıya uyar ımesajı yazıyoruz ve metodu sonlandırıyoruz.
        if (salonlar == null) {
            sinema_salonu_goruntule_uyari_mesaj.setText("Lütfen İstediğiniz Salonu Seçiniz.");
        } else {
            //kullanıcı bir değeri seçmiş ise devam ediyoruz.
            //ilk başta dosyadan var olan sinema salonlarının bilgilerini alıyoruz
            sinema_salonlariDAO sdao = new sinema_salonlariDAO();

            String[] a = salonlar.split(" | ");
            int salon_id = Integer.valueOf(a[0]);
            int koltuk_sayisi = sdao.koltuk_sayisi_getir(salon_id);
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
    private void sinema_salonunu_goruntule_geri(MouseEvent event) {
        sinema_salonlari_home_pane.setVisible(true);

        sinema_salonlari_goruntule_pane.setVisible(false);
        salon_bir_pane.setVisible(false);
        salon_iki_pane.setVisible(false);
        salon_uc_pane.setVisible(false);
        salon_dort_pane.setVisible(false);

        yesil_olanDAO ydao = new yesil_olanDAO();
        ydao.yesil_olan_dao_toplu_sil();
    }

    //sinema_Salonu_home_pane'in içinde bulunan yeni sinema salonu ekle butonunun metodudur. Yaptığı tek şey sinema_salonları için oluşturulmuş ekle pane'ini açıp home pane'ini kapatıyor. ve koltuk sayısını combobox'ın içine yazdırıyor.
    @FXML
    private void yeni_sinema_salonu_ekle(ActionEvent event) {
        sinema_salonlari_home_pane.setVisible(false);
        sinema_salonu_ekle_pane.setVisible(true);

        koltuk_sayisi_combo(sinema_salonu_ekle_koltuk_sayisi, "İstediğiniz Koltuk Sayısı...");
    }

    @FXML
    private void salonu_ekle(ActionEvent event) {
        String name = sinema_salonu_ekle_name.getText();
        String koltuk_sayisi = sinema_salonu_ekle_koltuk_sayisi.getValue();
        if ((koltuk_sayisi == null) || (name.length() == 0)) {
            sinema_salonu_ekle_uyari_mesaj.setText("Lütfen Gerekli Yerleri Doldurunuz.");
        } else {
            sinema_salonlariDAO sdao = new sinema_salonlariDAO();
            sinema_salonlari s = new sinema_salonlari(name, Integer.parseInt(koltuk_sayisi));
            int sonuc = sdao.sinema_salonlari_dao_ekle(s);

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
    private void yeni_sinema_salonu_ekle_geri(MouseEvent event) {
        sinema_salonlari_home_pane.setVisible(true);
        sinema_salonu_ekle_pane.setVisible(false);

    }

    @FXML
    private void sinema_salonu_duzenle_sil(ActionEvent event) {
        sinema_salonlari_home_pane.setVisible(false);
        sinema_salonu_duzenle_pane.setVisible(true);
        sinema_salonu_duzenle_pane_1.setVisible(true);
        sinema_salonu_duzenle_pane_2.setVisible(false);

        sinema_salonlari_goruntule_combo(sinema_salonlarini_duzenle_combo, sinema_salonu_guncelle_uyari_mesaj);

    }

    //sinema_salonu_duzenle_sil_pane'indeki sinema_Salonunu getir botununun metodudur.
    @FXML
    private void sinema_salonunu_getir(ActionEvent event) {
        String secilen = sinema_salonlarini_duzenle_combo.getValue();
        if (secilen == null) {
            sinema_salonu_guncelle_uyari_mesaj.setText("Lütfen Bir Değer Seçiniz");
        } else {
            String[] a = secilen.split(" | ");
            sinema_salonlariDAO sdao = new sinema_salonlariDAO();

            sinema_salonunu_guncelle_id.setText(a[0]);
            int salon_id = Integer.parseInt(a[0]);
            int koltuk_sayisi = sdao.koltuk_sayisi_getir(salon_id);
            koltuk_sayisi_combo(sinema_salonlarini_duzenle_koltuk_sayisi, "");
            sinema_salonlarini_duzenle_koltuk_sayisi.setValue(String.valueOf(koltuk_sayisi));
            sinema_salonu_guncelle_name.setText(sdao.salon_adi_getir(salon_id));

            sinema_salonu_duzenle_pane_2.setVisible(true);
        }
    }

    //sinema_Salonu_duzenle_sil pane'deki Sinema_Salonunu sil botununun metodu.
    @FXML
    private void sinema_salonunu_sil(ActionEvent event) {

        if (sinema_salonunu_guncelle_id.getText().length() == 0) {
            sinema_salonu_guncelle_uyari_mesaj.setText("Lütfen bir değer seçiniz.");
        } else {
            int salon_id = Integer.valueOf(sinema_salonunu_guncelle_id.getText());
            sinema_salonlariDAO sdao = new sinema_salonlariDAO();
            int sonuc = sdao.sinema_salonlari_dao_sil(salon_id);

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
    private void sinema_salonunu_guncelle(ActionEvent event) {
        if ((sinema_salonu_guncelle_name.getText().length() == 0) || (sinema_salonlarini_duzenle_koltuk_sayisi.getValue() == null)) {
            sinema_salonu_guncelle_uyari_mesaj.setText("Lütfen Gerekli Yerleri Doldurunuz.");
        } else {
            String name = sinema_salonu_guncelle_name.getText();

            int koltuk_sayisi = Integer.valueOf(sinema_salonlarini_duzenle_koltuk_sayisi.getValue());
            sinema_salonlariDAO sdao = new sinema_salonlariDAO();

            sinema_salonlari s = new sinema_salonlari(Integer.valueOf(sinema_salonunu_guncelle_id.getText()), name, koltuk_sayisi);
            int control = sdao.sinema_salonlari_degistir(s);

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
    private void sinema_salonu_duzenle_sil_geri(MouseEvent event) {
        sinema_salonu_duzenle_pane.setVisible(false);
        sinema_salonlari_home_pane.setVisible(true);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /*
    Film_Actor
     */
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /*
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     */
 /*
    Kullanıcı İşlemleri
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     */


    private void users_tablo() {
        usersDAO ud = new usersDAO();

        ObservableList<users> data = FXCollections.observableArrayList();

        data = ud.user_select(data, kullanici_islemleri_user_id, kullanici_islemleri_user_name, kullanici_islemleri_user_mail, kullanici_islemleri_gizli_pf, kullanici_islemleri_user_turu, kullanici_islemleri_abone_turu, kullanici_islemleri_gizli_pane, kullanici_islemleri_acik_pane, kullanici_islemleri_table_pane, kullanici_islemleri_yonet_pane, kullanici_islemleri_geri_tusu, kullanici_islemleri_yonet_geri_tusu, kullanici_islemleri_silmekten_emin_misin_pane);

        users_user_id.setCellValueFactory(new PropertyValueFactory("user_id"));
        users_user_name.setCellValueFactory(new PropertyValueFactory("user_name"));
        users_user_mail.setCellValueFactory(new PropertyValueFactory("user_mail"));
        users_user_password.setCellValueFactory(new PropertyValueFactory("user_password"));
        users_user_type.setCellValueFactory(new PropertyValueFactory("user_type"));
        users_abone_turu.setCellValueFactory(new PropertyValueFactory("abonelik_turu"));
        users_yonet.setCellValueFactory(new PropertyValueFactory("yonet"));

        FilteredList<users> filteredData = new FilteredList<>(data, b -> true);

        filterField_users.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(us -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(us.getUser_id()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (us.getUser_name().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (us.getUser_mail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (us.getUser_password().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(us.getUser_type()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(us.getAbonelik_turu()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }

            });
        });

        SortedList<users> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(table_users.comparatorProperty());

        table_users.setItems(sortedData);
    }

    @FXML
    private void kullanici_islemleri_yonet_geri(MouseEvent event) {
        kullanici_islemleri_ortak_giris();
    }

    @FXML
    private void kullanici_islemleri_geri(MouseEvent event) {
        geri_don_admin();
    }

    private void kullanici_islemleri_ortak_giris() {
        pnl_kullanici_islemleri.setVisible(true);
        pnl_sinema_salonlari.setVisible(false);
        pnl_vizyondaki_filmler.setVisible(false);
        pnl_seans.setVisible(false);
        pnl_eski_filmler.setVisible(false);
        pnl_kampanyalar.setVisible(false);
        pnl_haberler.setVisible(false);
        pnl_yonetmenler.setVisible(false);
        pnl_aktorler.setVisible(false);
        home_pane.setVisible(false);
        pnl_settings.setVisible(false);

        kullanici_islemleri_table_pane.setVisible(true);
        kullanici_islemleri_yonet_pane.setVisible(false);

        kullanici_islemleri_yonet_geri_tusu.setVisible(false);
        kullanici_islemleri_geri_tusu.setVisible(true);

        home.setVisible(true);

        users_tablo();
    }

    @FXML
    private void kullanici_islemleri_sifre_goster(MouseEvent event) {
        a2 = true;
        kullanici_islemleri_acik_pf.setText(kullanici_islemleri_gizli_pf.getText());

        kullanici_islemleri_acik_pane.setVisible(true);
        kullanici_islemleri_gizli_pane.setVisible(false);
    }

    @FXML
    private void kullanici_islemleri_sifre_gizle(MouseEvent event) {
        a2 = false;
        kullanici_islemleri_gizli_pf.setText(kullanici_islemleri_acik_pf.getText());

        kullanici_islemleri_acik_pane.setVisible(false);
        kullanici_islemleri_gizli_pane.setVisible(true);
    }

    @FXML
    private void kullanici_islemleri_sil_giris(ActionEvent event) {
        kullanici_islemleri_silmekten_emin_misin_pane.setVisible(true);
    }

    @FXML
    private void kullanici_islemleri_sil_sil(ActionEvent event) {
        int user_id = Integer.parseInt(kullanici_islemleri_user_id.getText());

        usersDAO udao = new usersDAO();

        int sonuc = udao.user_dao_sil(user_id);

        if (sonuc == 1) {
            kullanici_islemleri_yonet_uyari_pane.setText("İşlem başarılı bir şekilde gerçekleştirildi.");
        } else {
            kullanici_islemleri_yonet_uyari_pane.setText("Bir hata meydana geldi hata kodu: -30");
        }

        kullanici_islemleri_silmekten_emin_misin_pane.setVisible(false);
    }

    @FXML
    private void kullanici_islemleri_sil_vazgec(ActionEvent event) {
        kullanici_islemleri_silmekten_emin_misin_pane.setVisible(false);
    }

    @FXML
    private void kullanici_islemleri_guncelle(ActionEvent event) {
        if ((kullanici_islemleri_user_name.getText().length() == 0) || (kullanici_islemleri_user_mail.getText().length() == 0) || (kullanici_islemleri_user_turu.getValue() == null) || (kullanici_islemleri_abone_turu.getValue() == null)) {
            kullanici_islemleri_yonet_uyari_pane.setText("Lütfen Gerekli Yerleri Doldurunuz.");
        } else {
            String user_password = null;
            if (a2 == false) {
                if (kullanici_islemleri_gizli_pf.getText().length() == 0) {
                    kullanici_islemleri_yonet_uyari_pane.setText("Lütfen Gerekli Yerleri Doldurunuz.");
                } else {
                    user_password = kullanici_islemleri_gizli_pf.getText();
                }
            } else {
                if (kullanici_islemleri_acik_pf.getText().length() == 0) {
                    kullanici_islemleri_yonet_uyari_pane.setText("Lütfen Gerekli Yerleri Doldurunuz.");
                } else {
                    user_password = kullanici_islemleri_acik_pf.getText();
                }
            }
            if (user_password != null) {
                int user_id = Integer.parseInt(kullanici_islemleri_user_id.getText());
                String user_name = kullanici_islemleri_user_name.getText();
                String user_mail = kullanici_islemleri_user_mail.getText();
                int abone_turu = Integer.parseInt(kullanici_islemleri_abone_turu.getValue());
                int user_turu = -1;
                if (kullanici_islemleri_user_turu.getValue().equals("Normal")) {
                    user_turu = 0;
                } else if (kullanici_islemleri_user_turu.getValue().equals("Admin")) {
                    user_turu = 1;
                }
                if (user_turu == -1) {
                    kullanici_islemleri_yonet_uyari_pane.setText("Bir hata meydana geldi. Hata kodu: -31.");
                } else {
                    users u = new users(user_id, user_name, user_mail, user_password, user_turu);
                    usersDAO udao = new usersDAO();

                    int sonuc = udao.user_guncelle_aboneli(u, abone_turu);

                    if (sonuc == 1) {
                        kullanici_islemleri_yonet_uyari_pane.setText("İşlem Başarılı Bir Şekilde Gerçekleştirildi.");
                    } else {
                        kullanici_islemleri_yonet_uyari_pane.setText("Bir hata meydana geldi. Hata kodu: -32.");
                    }
                }
            }
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /*
    Ortak Alan
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     */
    @FXML
    private void btn10(ActionEvent event) {

        if (btn1.getText().equals("Filmler")) {
            btn1.setText("Vizyondaki Filmler");
            btn2.setText("Eski Filmler");

            btn3.setVisible(false);
            btn4.setVisible(false);
            btn5.setVisible(false);

            home.setVisible(true);
        } else if (btn1.getText().equals("Vizyondaki Filmler")) {

            pnl_vizyondaki_filmler.setVisible(true);
            pnl_eski_filmler.setVisible(false);
            pnl_sinema_salonlari.setVisible(false);
            pnl_seans.setVisible(false);
            home_pane.setVisible(false);
            pnl_settings.setVisible(false);
            pnl_kullanici_islemleri.setVisible(false);

            vizyondaki_filmler_geri_tusu.setVisible(true);
            vizyondaki_filmler_ekle_geri_tusu.setVisible(false);
            vizyondaki_filmler_degistir_geri_tusu.setVisible(false);
            vizyondaki_filmler_gosterim_geri_tusu.setVisible(false);

            vizyondaki_filmler_resimli_gosterim.setVisible(true);
            vizyondaki_filmler_grid.setVisible(false);
            vizyondaki_filmler_ekle_pane.setVisible(false);
            vizyondaki_filmler_degistir_pane.setVisible(false);
            pnl_film_detay.setVisible(false);

            vizyondaki_filmler_gosterim();

        } else if (btn1.getText().equals("Kampanyalar")) {

            pnl_kampanyalar.setVisible(true);
            pnl_haberler.setVisible(false);
            pnl_sinema_salonlari.setVisible(false);
            home_pane.setVisible(false);
            pnl_settings.setVisible(false);
            pnl_kullanici_islemleri.setVisible(false);

            kampanyalar_geri_tusu.setVisible(true);
            kampanyalar_ekle_geri_tusu.setVisible(false);
            kampanyalar_degistir_geri_tusu.setVisible(false);

            kampanyalar_grid.setVisible(true);
            kampanyalar_ekle_pane.setVisible(false);
            kampanyalar_degistir_pane.setVisible(false);

            kampanyalar_table_butonlu();

        } else if (btn1.getText().equals("Yönetmenler")) {

            pnl_yonetmenler.setVisible(true);
            pnl_aktorler.setVisible(false);
            pnl_sinema_salonlari.setVisible(false);
            home_pane.setVisible(false);
            pnl_settings.setVisible(false);
            pnl_kullanici_islemleri.setVisible(false);

            yonetmenler_geri_tusu.setVisible(true);
            yonetmenler_ekle_geri_tusu.setVisible(false);
            yonetmenler_degistir_geri_tusu.setVisible(false);

            yonetmenler_grid.setVisible(true);
            yonetmenler_ekle_pane.setVisible(false);
            yonetmenler_degistir_pane.setVisible(false);

            yonetmenler_table();
        }
    }

    @FXML
    private void btn20(ActionEvent event) {
        if (btn2.getText().equals("Duyurular")) {

            btn1.setText("Kampanyalar");
            btn2.setText("Haberler");

            btn3.setVisible(false);
            btn4.setVisible(false);
            btn5.setVisible(false);

            home.setVisible(true);
        } else if (btn2.getText().equals("Eski Filmler")) {

            pnl_eski_filmler.setVisible(true);
            pnl_vizyondaki_filmler.setVisible(false);
            pnl_sinema_salonlari.setVisible(false);
            pnl_seans.setVisible(false);
            home_pane.setVisible(false);
            pnl_settings.setVisible(false);
            pnl_kullanici_islemleri.setVisible(false);

            eski_filmler_geri_tusu.setVisible(true);
            eski_filmler_ekle_geri_tusu.setVisible(false);
            eski_filmler_degistir_geri_tusu.setVisible(false);

            eski_filmler_grid.setVisible(true);
            eski_filmler_ekle_pane.setVisible(false);
            eski_filmler_degistir_pane.setVisible(false);

            eski_filmler_table_aboneli();

        } else if (btn2.getText().equals("Haberler")) {

            pnl_haberler.setVisible(true);
            pnl_kampanyalar.setVisible(false);
            pnl_sinema_salonlari.setVisible(false);
            home_pane.setVisible(false);
            pnl_settings.setVisible(false);
            pnl_kullanici_islemleri.setVisible(false);

            haberler_geri_tusu.setVisible(true);
            haberler_ekle_geri_tusu.setVisible(false);
            haberler_degistir_geri_tusu.setVisible(false);

            haberler_grid.setVisible(true);
            haberler_ekle_pane.setVisible(false);
            haberler_degistir_pane.setVisible(false);

            haberler_table_butonlu();

        } else if (btn2.getText().equals("Aktörler")) {
            pnl_aktorler.setVisible(true);
            pnl_yonetmenler.setVisible(false);
            pnl_sinema_salonlari.setVisible(false);
            home_pane.setVisible(false);
            pnl_settings.setVisible(false);
            pnl_kullanici_islemleri.setVisible(false);

            aktorler_geri_tusu.setVisible(true);
            aktorler_ekle_geri_tusu.setVisible(false);
            film_actor_geri_tusu.setVisible(false);
            film_actor_ekle_geri_tusu.setVisible(false);

            aktorler_grid.setVisible(true);
            aktorler_ekle_pane.setVisible(false);
            aktorler_sil_emin_misin.setVisible(false);
            film_actor_pane.setVisible(false);

            aktorler_table();
        }
    }

    @FXML
    private void btn30(ActionEvent event) {
        pnl_sinema_salonlari.setVisible(true);
        pnl_vizyondaki_filmler.setVisible(false);
        pnl_seans.setVisible(false);
        pnl_eski_filmler.setVisible(false);
        pnl_kampanyalar.setVisible(false);
        pnl_haberler.setVisible(false);
        pnl_yonetmenler.setVisible(false);
        pnl_aktorler.setVisible(false);
        home_pane.setVisible(false);
        pnl_settings.setVisible(false);
        pnl_kullanici_islemleri.setVisible(false);

        sinema_salonlari_home_pane.setVisible(true);

        salon_bir_pane.setVisible(false);
        salon_iki_pane.setVisible(false);
        salon_uc_pane.setVisible(false);
        salon_dort_pane.setVisible(false);

        sinema_salonlari_goruntule_pane.setVisible(false);
        sinema_salonu_ekle_pane.setVisible(false);
        sinema_salonu_duzenle_pane.setVisible(false);

        home.setVisible(true);
    }

    @FXML
    private void btn40(ActionEvent event) {
        btn1.setText("Yönetmenler");
        btn2.setText("Aktörler");

        btn3.setVisible(false);
        btn4.setVisible(false);
        btn5.setVisible(false);

        home.setVisible(true);
    }

    @FXML
    private void btn50(ActionEvent event) {
        kullanici_islemleri_ortak_giris();
    }

    @FXML
    private void home_geri_gel(MouseEvent event) {
        geri_don_admin();
    }
    
    @FXML
    private void settings_giris(MouseEvent event) {
        pnl_vizyondaki_filmler.setVisible(false);
        pnl_seans.setVisible(false);
        pnl_eski_filmler.setVisible(false);
        pnl_kampanyalar.setVisible(false);
        pnl_haberler.setVisible(false);
        pnl_sinema_salonlari.setVisible(false);
        pnl_yonetmenler.setVisible(false);
        pnl_aktorler.setVisible(false);
        home_pane.setVisible(false);
        pnl_kullanici_islemleri.setVisible(false);

        pnl_settings.setVisible(true);

        int user_id = Creator.getU().getUser_id();

        this.user_name.setText(Creator.getU().getUser_name());
        this.user_mail.setText(Creator.getU().getUser_mail());
        this.user_password.setText(Creator.getU().getUser_password());

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        home_page();
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
