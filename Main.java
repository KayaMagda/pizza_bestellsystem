package de.kaya.pizza;

import java.util.Scanner;

public class Main {
    /*Main Klasse ist für die Bedienoberfläche zuständig, enthält alle Variablen und Funktionen
    die für User-Führung notwendig sind*/

    //-------------alle Booleans sind Flags zur User Kontrolle--------------

    /*restart wird durch den Ende Befehl true und sorgt damit für einen weiteren Durchgang des
    do-while loops*/
    static boolean restart = false;
    //Wenn pizza true ist, dann ist eine Pizza in Bearbeitung
    static boolean pizza = false;

    /*Sobald sauce true ist, darf der User weitere Zutaten wählen
    static boolean sauce = false;*/

    //sobald inwarenkorb true ist führt der Befehl Bestellen zum Bestellen
    static boolean inwarenkorb = false;
    //ein statisches Scannerobjekt erlaubt übergreifendes Scannen
    static Scanner scan = new Scanner(System.in);

    /*Objekte: beide Objekte sind statisch, damit sie überall in der Main Klasse aufgerufen werden können
    User brauchen nur ein Pizzaobjekt und einen Warenkorb zur gleichen Zeit
     */
    static Warenkorb warenkorb = new Warenkorb();
    static Pizza eins;

    public static Pizza getEins() {
        return eins;
    }
    /*User kann mehrfach zum startbildschirm zurück kehren um die Befehle nochmal zu sehen
     * Befehl Zutat <ID> aus dem Dokument wurde zu: Zutat ID um das Programm Userfreundlicher zu machen.*/

    static String startbildschirm = String.format(
            "Willkommen bei Luigis!%n" +
                    "_____________________________________________________%n" +
                    "Du kannst folgende Befehle eingeben:%n" +
                    "Zutatenliste : Zeigt dir alle Zutaten für unsere leckeren Pizzas an.%n" +
                    "Neue Pizza : Hiermit startest du eine Pizza die du dir belegen kannst wie du es magst.%n" +
                    "Zutat id : Fügt deiner Pizza eine neue Zutat hinzu, anstatt ID schreibst du die Zahl die zu deiner Zutat gehört.%n" +
                    "Fertig: Hiermit beendest du deine derzeitige Pizza.%n" +
                    "Warenkorb: Zeigt dir deinen Warenkorb.%n" +
                    "Bestellen: Schickt deine Bestellung ab, bitte komm zur Kasse, wenn du bezahlt hast fangen wir an zu backen.%n" +
                    "Ende: Beendet deinen Bestellprozess und löscht deinen Verlauf, egal ob du etwas bestellt hast, oder nicht%n" +
                    "_______________________________________________________________________%n" +
                    "Gib jetzt einen Befehl ein.%n");
    static Main formethods = new Main();

    public static void main(String[] args) {
        do {
            //Instanziierung der Main Methode zum Aufruf der Methoden


            System.out.println(startbildschirm);
            //Der erste Befehl ist speziell, ab dem Zweiten wird eine Methode aufgerufen
            String befehleins = scan.nextLine();

            //die eingabe wird so weit wie möglich von Userfehlern befreit
            String bereinigungeins = befehleins.toLowerCase();
            String bereinigungzwei = bereinigungeins.replaceAll("\\s", "");
            if (bereinigungzwei.matches("[a-z]+\\d+")) {
                System.out.println("Du brauchst erst eine Pizza bevor du Zutaten hinzufügen kannst");
                formethods.befehl();
            } else {
                switch (bereinigungzwei) {
                    case "zutatenliste":
                        System.out.println(zutaten());
                        formethods.befehl();
                        break;
                    case "neuepizza":
                        eins = Pizza.getInstance();
                        pizza = true;
                        System.out.printf("Soll auf deine Pizza was drauf? Mit Zutatenliste kannst du dir die%n" +
                                "Zutaten anzeigen lassen, mit Zutat<id> kannst du anfangen deine Pizza zu belegen%n");
                        formethods.befehl();
                        break;
                    case "fertig":
                        System.out.println("Du hast noch keine Pizza erstellt");
                        break;
                    case "warenkorb":
                        System.out.println(warenkorb.toString());
                        break;
                    case "bestellen":
                        System.out.println("Es gibt nichts zu bestellen");
                        break;
                    case "ende":
                        System.out.println("Tschüss");
                        break;
                    default:
                        System.out.println("Diesen Befehl gibt es nicht");
                        System.out.println(startbildschirm);
                        formethods.befehl();
                }
            }

        } while (restart);
    }


    public void befehl() {
        //Methode wird bei jeder Befehlseingabe aufgerufen

        System.out.printf("Gib hier ein:%n");
        String eingabe = scan.nextLine();
        String bereinigung = eingabe.toLowerCase();
        String nutzbar = bereinigung.replaceAll("\\s", "");
        //regex hat die Form von Zutat ID
        if (nutzbar.matches("[a-z]{5}\\d+")) {
            //falls der Zutat Befehl eingegeben wurde wird zur Weiterverarbeitung die ID herausgeparst
            try {
                String[] getid = eingabe.split(" ");//zutat 12 ["zutat", "12"]
                String strid = getid[1];
                int id = Integer.parseInt(strid);
                System.out.println(id);
                if (pizza) {
                    /*falls eine Pizza in Bearbeitung ist.*/
                    try {
                        eins.addBelag(id);
                    } catch (IllegalStateException e) {
                        //bei falscher ID eingabe
                        System.out.println("Die gewählte Zutat gibt es nicht " + id);
                    }
                    befehl();
                }

            } catch (NumberFormatException e) {
                //bei Tippfehler
                System.out.println("Hast du den Befehl richtig eingegeben?");
                befehl();
            }

                catch (ArrayIndexOutOfBoundsException e) {
                //wenn Leerzeichen weggelassen wurde
                System.out.println("Vergiss das Leerzeichen nicht");
                befehl();
            }

            //TODO: addBelag Funktion in Klasse Pizza umschreiben um sie den neuen Gegebenheiten anzupassen:
            //größtenteils erledigt
        } else {
            switch (nutzbar) {
                case "zutatenliste":
                    System.out.println(zutaten());
                    befehl();
                    //Rekursion hört auf wenn User "Ende" Befehl eingibt
                    break;
                case "start":
                    System.out.println(startbildschirm);
                    befehl();
                case "neuepizza":
                    if (!pizza) {
                        //neue Pizza wird ab jetzt bearbeitet
                        eins = Pizza.getInstance();
                        pizza = true;
                        System.out.printf("Soll auf deine Pizza was drauf? Mit Zutatenliste kannst du dir die%n" +
                                "Zutaten anzeigen lassen, mit Zutat id kannst du anfangen deine Pizza zu belegen%n");
                    } else {
                        System.out.printf("Bitte beende erst die aktuelle Pizza bevor du eine Neue startest%n");
                    }
                    befehl();
                    break;
                case "fertig":
                    if (pizza) {
                    /*falls eine Pizza in Bearbeitung ist wird diese in den Warenkorb verschoben,
                    dann kann ein neue Pizza bearbeitet werden*/

                        System.out.printf("Gib deiner Pizza noch einen Namen:%n");
                        String name = scan.nextLine();
                        eins.setName(name);
                        warenkorb.fillBestellt(eins);
                        warenkorb.bestellungspreis(eins.getPreis());
                        eins.clearAll();
                        inwarenkorb = true;
                        pizza = false;
                        System.out.printf("Möchtest du eine weitere Pizza oder möchtest du beenden? %n");
                    } else {
                        System.out.println("Du hast noch keine Pizza erstellt.");
                    }
                    befehl();
                    break;
                case "warenkorb":
                    if (inwarenkorb) {
                        System.out.println(warenkorb.toString());
                        befehl();
                    } else {
                        System.out.println("Warenkorb ist leer");
                        befehl();
                    }
                    break;
                case "bestellen":
                    //Die Bestellung kann nur abgeschickt werden wenn keine Pizza in Bearbeitung ist
                    if (inwarenkorb && !pizza) {
                        System.out.printf("Deine Bestellung ist in der Küche, bitte komm zur Kasse,%n" +
                                "sobald du bezahlt hast wird gebacken!");
                        System.out.println("Möchtest du beenden?");
                        befehl();
                    } else {
                        System.out.println("Es gibt nichts zu bestellen");
                    }
                    break;
                case "ende":
            /*Ende funktioniert jederzeit, User kann sich also entscheiden vor Abschicken der Bestellung
            abzubrechen.
            Ende setzt alle Flags auf false zurück und setzt beide Objekte zurück. Letzendlich wird
            die Bedingung für den do while loop true gemacht, damit wird ein neuer Durchlauf bedingt.*/

                    pizza = false;
                    inwarenkorb = false;
                    eins.clearAll();
                    warenkorb.warenkorbleeren();
                    restart = true;
                    break;
                default:
                    System.out.println("Diesen Befehl gibt es nicht");
                    System.out.println(startbildschirm);
                    befehl();
            }
        }


    }

    public static String zutaten() {
        return String.format("        Saucen                             Zutaten     %n" +
                "ID                    Preis   ID               Preis  ID                   Preis%n" +
                "01     Tomatensauce   0,00    11     Gouda      0,99  18      Mais         0,59%n" +
                "02     BBQ-Sauce      0.00    12     Mozzarella 0,99  19      Zwiebeln     0,59%n" +
                "                              13     Salami     1,39  20      Oliven       0,99%n" +
                "                              14     Schinken   1,39  21      Ei           0,59%n" +
                "                              15     Thunfisch  0,99  22      Paprika      0,59%n" +
                "                              16     Pepperoni  0,59  23      Tomaten      0,59%n" +
                "                              17     Ananas     0,59  24      Champignons  0,59%n " +
                "Mit START kommst du zum Startbildschirm!%n"
        );
    }
}
