package de.kaya.pizza;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Warenkorb {
    //Die Warenkorb Klasse versammelt alle Pizzen der Bestellung und enthält ausserdem den
    //Preis der gesamten Bestellung
    private double gesamt;
    private List<String> bestellt = new ArrayList<String>();

    public void setGesamt(double gesamt) {
        this.gesamt = gesamt;
    }
    public void bestellungspreis(double preis){
        this.gesamt += preis;
    }

    public void fillBestellt(Pizza pizza) {
       //Nimmt Pizzaobjekte und macht sie zu gutaussehenden Strings die im warenkorb angezeigt
       //werden können
       String belegtmit = pizza.getBelaege();
       String neuepizza = String.format("Pizza %s belegt mit:" +
               "%s" +
               "_________________________________Preis:__%.2f %n",pizza.getName(), belegtmit, pizza.getPreis() );
       bestellt.add(neuepizza);
    }
@Override
    public String toString() {
        //Warenkorbausgabe, der user sieht seine Bestellung und den Gesamtpreis
        String eins = bestellt.toString();
        String zwei = eins.replaceAll(",","");
            return String.format("%s%nDu zahlst insgesamt:____%.2f",zwei,gesamt);

    }
    public void warenkorbleeren(){
        //methode zum Zurücksetzen des Warenkorbs
        gesamt = 0.00;
        bestellt.clear();
    }
}




