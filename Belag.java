package de.kaya.pizza;

public class Belag {
    protected String nameB;
    protected double preisB;
    protected int id;
    static Pizza pizza = new Pizza();

    Belag() {
        System.out.println("Es fehlen Name und Preis");
    }

    Belag(String name, double preisB, int id) {
        this.nameB = name;
        this.preisB = preisB;
        this.id = id;
    }


    @Override
    public String toString() {

        return nameB;
    }
    public void belagaufrufen() {
        Pizza pizza = Main.getEins();
        String zutat = pizza.belaege.toString();
        String zweimal = pizza.doppelt.toString();
        //überprüfen ob Zutat schon doppelt auf der Pizza ist:
        if (zweimal.contains(this.nameB)) {
            System.out.println("Du kannst jede Zutat nur zweimal wählen. Doppelt ist genug.");
            //überprüfen ob eine soße da ist:
        } else if (pizza.belaege.isEmpty()) {
            System.out.println("Bitte wähle erst eine Soße aus");
        }//überprüfen ob pizza voll ist:
        else if (pizza.belaege.size() == 8) {
            System.out.println("Pizza ist voll, bitte gib \"Fertig\" ein.");
            //überprüfen ob gouda schon einmal auf der pizza ist:
        } else if (zutat.contains(this.nameB)) {
            pizza.doppelt.add(this);//speichern, dass die Zutat ab jetzt doppelt auf der Pizza ist
            pizza.belaege.add(this);//Zutat nochmal auf die Pizza legen
            pizza.teurerMachen(this.preisB);//Preis der Pizza ändern
            //user informieren:
            System.out.printf("Pizza kostet jetzt: %.2f %n", pizza.getPreis());
            System.out.printf("%s wurde der Pizza hinzugefügt%n", this.nameB);
            System.out.printf("Das liegt alles auf deiner Pizza:___%s%n", zutat );
            int verbleibend = (8 - pizza.belaege.size());
            System.out.printf("Du kannst noch %d Zutaten wählen. %n", verbleibend);
            System.out.println();
        } else {
           pizza.belaege.add(this);
            pizza.teurerMachen(this.preisB);
            System.out.printf("Pizza kostet jetzt: %.2f %n", pizza.getPreis());
            System.out.printf("%s wurde der Pizza hinzugefügt%n", this.nameB);
            System.out.printf("Das liegt alles auf deiner Pizza:___%s%n", pizza.belaege.toString());
            int verbleibend = (8 - pizza.belaege.size());
            System.out.printf("Du kannst noch %d Zutaten wählen. %n", verbleibend);
            System.out.println();
        }


    }

}
