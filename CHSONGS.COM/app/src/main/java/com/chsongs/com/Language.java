package com.chsongs.com;

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
                case "sevenNotif": r = "Утринска молитва"; break;
                case "eighteenNotif": r = "Вечерна молитва"; break;
                case "otherNotif": r = "Останати известувања "; break;

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
            String sr = "http://topinsurance365.com/srbija/brojevi";
            String mk = "http://topinsurance365.com/makedonija/brojki";
        if(lang.equals("Srpski")){
            return sr;
        }
        return mk;

    }
    public static String getSyncUrl(){
        String sr = "http://topinsurance365.com/endpoint-chsongs/get/content/articles/969";
        String mk = "http://topinsurance365.com/endpoint-chsongs/get/content/articles/968";
        if(lang.equals("Srpski")){
            return sr;
        }
        return mk;
    }


}
