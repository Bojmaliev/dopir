package org.eu.dopis.dopir;

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
                case "otherNotif": r = "Известување 3 "; break;
                case "thankYouHumanity": r = "БЛАГОДАРИМЕ ЗА ВАШАТА ХУМАНОСТ"; break;
                case "thankYouForInstallingThisApp": r= "Ви благодариме што ја инсталиравте оваа апликација"; break;
                case "guideForUsingOnMail": r= "Упатството за користење веќе е испратено на Вашиот меил"; break;
                case "pleaseReadItCarefully": r= "Ве молиме прочитајте го внимателно!"; break;
                case "gameRulesTitle": r= "ПРАВИЛА ЗА ИГРА"; break;
                case "gameRules": r= "Малиот палавко ТОТО сака само да шета и да спие.<br /><br />Вие треба да го прошетате 2 до 3 пати дневно, наутро, напладне и навечер!<br /><br />Немојте да го шетате ТОТО повеќе од ова.<br /><br />Помеѓу секоја прошетка МОРА да поминат најмалку 3 часа!!!<br /><br /> <b>ШЕТАЊЕТО ЌЕ ЗАПОЧНЕ КОГА ЌЕ КЛИКНЕТЕ НА БИЛО КОЈА РЕКЛАМА .</b> <br /><br />Во меѓувреме ТОТО сака да спие за да порасне…"; break;
                case "gameRules1": r= "После секоја прошетка ќе бидете наградени со еден црвен поен во светлечкиот бар.<br /><br /> Со собрани 20 поени добивате едно срце! <br /><br />А со собрани 5 срца добивате благодарница во знак на Вашата искажана хуманост!<br /><br /><b>Ајде да играме со Тото сака!</b><br /><br /><i>(преку оваа апликација, секојдневно помагате во собирањето средства за помош на децата со посебни потреби и ретки болести во Македонија)</i>"; break;
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
        String sr = "http://topinsurance365.com/mk/za-nas";
        String mk = "http://topinsurance365.com/mk/za-nas";
        if(lang.equals("Srpski")){
            return sr;
        }
        return mk;

    }
    public static String getSyncUrl(){
        String sr = "http://topinsurance365.com/endpoint-chsongs/get/content/articles/952";
        String mk = "http://topinsurance365.com/endpoint-chsongs/get/content/articles/952";
        if(lang.equals("Srpski")){
            return sr;
        }
        return mk;
    }


}
