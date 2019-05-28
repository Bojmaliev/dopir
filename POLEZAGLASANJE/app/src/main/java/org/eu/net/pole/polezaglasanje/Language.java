package org.eu.net.pole.polezaglasanje;

/**
 * Created by ninja on 11/30/2017.
 */

public class Language {

    public static String lang = "Македонски";
    public static boolean changedLang = false;
    public Language(){
        lang = "Македонски";
    }

    public static void changeLang(String langg){lang = langg;}


    public static String getString(String what){
        String r;
        if(lang.equals("Srpski")){

            switch (what){
                case "save": r= "Sačuvaj";break;
                case "clickOneMoreTime": r = "Kliknite još jedanput za izlaz..."; break;
                case "saved": r = "Sačuvano"; break;
                case "settings": r = "Postavke"; break;
                case "chooseLang": r = "Odaberi jezik"; break;
                case "notiflications": r = "Notifikacije"; break;
                case "sevenNotif": r = "Jutarnja molitva"; break;
                case "eighteenNotif": r = "Večernja molitva"; break;
                case "otherNotif": r = "Ostale notifikacije "; break;
                default: return "";
            }

        }else{
            switch (what){
                case "save": r= "Зачувај"; break;
                case "clickOneMoreTime": r = "Кликнете уште еднаш за да излезете..."; break;
                case "saved": r = "Зачувано"; break;
                case "settings": r = "Поставки"; break;
                case "chooseLang": r = "Изберете јазик"; break;
                case "notiflications": r = "Известувања"; break;
                case "sevenNotif": r = "Известување 1"; break;
                case "eighteenNotif": r = "Известување 2"; break;
                case "otherNotif": r = "Известување 3"; break;

                default: return "";
            }
        }
        return r;
    }
    public static String getShort(String jj){
        String r="MK";
        if(jj.equals("Srpski"))r = "RS";
        return r;
    }
    public static String getUrl(){
        String sr = "http://topinsurance365.com/pole";
        String mk = "http://topinsurance365.com/pole";
        if(lang.equals("Srpski")){
            return sr;
        }
        return mk;

    }
    public static String getSyncUrl(){
        String sr = "http://topinsurance365.com/endpoint-chsongs/get/content/articles/969";
        String mk = "http://topinsurance365.com/endpoint-chsongs/get/content/articles/982";
        if(lang.equals("Srpski")){
            return sr;
        }
        return mk;
    }


}
