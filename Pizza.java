package de.kaya.pizza;

import java.util.ArrayList;
import java.util.List;

public class Pizza {
    /* Die Pizzaklasse erlaubt dem User innerhalb des Bestellablaufs Pizzen nach eigenem
    Geschmack zu belegen. Im gesamten Programmablauf gibt es nur ein Pizzaobjekt.
     */
    final private static Pizza eins = new Pizza();

    public static Pizza getInstance(){
        return eins;
    }
    private String name; /*als Entwickler würde ich dem Kunden davon abraten
    eine Namenseingabe zu erlauben*/

    private double preis = 4.99;//bezieht sich auf das derzeitige Pizzaobjekt

    // belaege sammelt die Beläge auf der aktuellen Pizza, belaege ist statisch, weil die
    //Methode zum Aufrufen des gewünschten Belags von der Belagklasse aus auf die selbe belaege Instanziierung
    //zugreifen soll wie die Methode zum Hinzufügen des Belags
    static List<Belag> belaege = new ArrayList<>();
    //die doppelt Liste versammelt alle Zutaten die bereits doppelt auf der Pizza sind
    static List<Belag> doppelt = new ArrayList<>();


    //clearAll erlaubt das statische Pizzaobjekt in der Main Methode mehrfach zu verwenden
    public void clearAll() {
        name = null;
        preis = 4.99;
        belaege.clear();
        doppelt.clear();
    }
    //getter und setter ab hier


    public void setName(String name) {
        this.name = name;
    }

    public String getBelaege() {

        return belaege.toString();
    }

    public double getPreis() {
        return preis;
    }


    public String getName() {
        return name;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    public void teurerMachen(double preis) {
        this.preis += preis;
    }


    public void addBelag(int id) {
        //die addBelag Methode macht aus einem leeren Pizzaobjekt eine leckere Pizza
        switch (id) {
//Die ID wird während des Programmablaufs in der Main Methode übergeben
            case 1:
                //Soßen sind Sonderfälle, der user soll nur eine wählen dürfen,
                //die soße soll der erste Belag sein,
                //außerdem kosten sie nichts
                Belag tomate = new Belag("Tomatensauce", 0.00, 1);
                //Wenn die Liste nicht empty ist, ist schon eine Soße da
                if (!belaege.isEmpty()) {
                    System.out.println("Du kannst nur ein Soße wählen");
                } else {
                    belaege.add(tomate);
                    //User informieren
                    System.out.printf("%s wurde der Pizza hinzugefügt%n", tomate.nameB);
                    System.out.println("Du kannst noch 7 weitere Zutaten wählen");
                }
                break;
            case 2:
                Belag bbq = new Belag("Barbecuesauce", 0.00, 2);
                //bbq.belagaufrufen();
                if (!belaege.isEmpty()) {
                    System.out.println("Du kannst nur ein Soße wählen");
                } else {
                    belaege.add(bbq);
                    System.out.printf("%s wurde der Pizza hinzugefügt%n", bbq.nameB);
                    System.out.println("Du kannst noch 7 weitere Zutaten wählen");
                }
                break;
            case 11:
                Belag gouda = new Belag("Gouda", 0.99, 11);
                gouda.belagaufrufen();
                break;
            case 12:
                Belag rella = new Belag("Mozarella", 0.99, 12);
                rella.belagaufrufen();
                break;
            case 13:
                Belag sala = new Belag("Salami", 1.39, 13);
                sala.belagaufrufen();
                break;
            case 14:
                Belag schink = new Belag("Schinken", 1.39, 14);
                schink.belagaufrufen();
                break;
            case 15:
                Belag fisch = new Belag("Thunfisch", 0.99, 15);
                fisch.belagaufrufen();
                break;
            case 16:
                Belag roni = new Belag("Pepperoni", 0.59, 16);
                roni.belagaufrufen();
                break;
            case 17:
                Belag sweet = new Belag("Ananas", 0.59, 17);
                sweet.belagaufrufen();
                break;
            case 18:
                Belag mais = new Belag("Mais", 0.59, 18);
                mais.belagaufrufen();
                break;
            case 19:
                Belag zwieb = new Belag("Zwiebeln", 0.59, 19);
                zwieb.belagaufrufen();
                break;
            case 20:
                Belag oli = new Belag("Oliven", 0.99, 20);
                oli.belagaufrufen();
                break;
            case 21:
                Belag ei = new Belag("Ei", 0.59, 11);
                ei.belagaufrufen();
                break;
            case 22:
                Belag pap = new Belag("Paprika", 0.59, 22);
                pap.belagaufrufen();
                break;
            case 23:
                Belag maten = new Belag("Tomaten", 0.59, 23);
                maten.belagaufrufen();
                break;
            case 24:
                Belag pilze = new Belag("Champignons", 0.59, 24);
                pilze.belagaufrufen();
                break;
            default:
                throw new IllegalStateException("Diese Zutat gibt es nicht: " + id);
        }

    }



    @Override
    public String toString() {
        return
                "Pizza " + name + '\'' +
                        "belegt mit" + belaege.toString() +
                        "____________________________________Preis: " + preis + ""
                ;
    }
}