import java.util.*;

public class Main {

    public static void main(String[] args){

        /* 2 Zahleneingaben ueber Scanner */
        Scanner input = new Scanner( System.in );

        int zahl_1;
        int zahl_2;

        System.out.print( "Erste Zahl eingeben: " );
        zahl_1 = input.nextInt();
        System.out.print( "Zweite Zahl eingeben: " );
        zahl_2 = input.nextInt();

        /* Uberpruefen ob zahlen Negativ */
        if(zahl_1 < 0 || zahl_2 < 0){
            System.out.print( "Negative Zahlen nicht erlaubt" );
            System.exit(1);
        }

        /* Beide Zahlen in unaere Darstellung umwandeln  */
        String zahl_1_unary = "";
        String zahl_2_unary = "";

        for(int i = 0; i < zahl_1; i++){
            zahl_1_unary = zahl_1_unary + "1";
        }

        for(int i = 0; i < zahl_2; i++){
            zahl_2_unary = zahl_2_unary + "1";
        }

        /* Ausgabe der Konvertierte Zahlen */
        System.out.println( "Erste Zahl in Unary Darstellung:  " + zahl_1_unary );
        System.out.println( "Zweite Zahl in Unary Darstellung: " + zahl_2_unary );

        String bandEingabe = zahl_1_unary + "*" + zahl_2_unary;  // Auf dem Band steht zu Beginn die beide Zahlen, getrennt mit einem * Zeichen
        String alphabet = "1*";      // Eingabealphabet
        String bandAlphabet = "1*";  // Bandalphabet

        TuringMaschine m = new TuringMaschine();

        m.initialize(); //initialisieren der Turingmaschine

        // m.init()
        Zustand[] z = new Zustand[5];
        for (int i = 0; i < z.length; i++)
            z[i] = new Zustand(i + 1);

        // Algorithmus von https://youtu.be/yfmw5qCDwkE (Tutorials Point Ltd.) implementiert

        // Insgesamt 4 Zustaende
        // Wie die Konfiguration zu lesen ist:
        // Aus Zustand 0 bei lesen von *, 1 auf Band schreiben und nach rechts gehen, zu Zustand 1 waechseln
        z[0].addKonfiguration('*', '1', Konfiguration.RECHTS,z[1]);  // Ersetze * durch 1, gehe rechts, zu z1 waechseln da wir nach einem * nicht weiter 1 machen dürfen
        z[0].addKonfiguration('1', '1', Konfiguration.RECHTS,z[0]);  // Wir wissen nicht wie viele 1'en gibt daher bei z0 blieben und nach rechts gehen
        z[1].addKonfiguration('*', '*', Konfiguration.LINKS, z[2]);  // * bliebt *, zu z2, bei z2 wir haben nur 1'en
        z[1].addKonfiguration('1', '1', Konfiguration.RECHTS,z[1]);  // iteriere bis zum * Symbol
        z[2].addKonfiguration('*', '*', Konfiguration.STOP, z[2]);   // - (Wir sollten nur 1'en haben, wenn * auftritt dann fehler)
        z[2].addKonfiguration('1', '*', Konfiguration.LINKS, z[3]);  // 1 durch * ersetzen, links gehen, zu z3
        z[3].addKonfiguration('*', '*', Konfiguration.RECHTS,z[4]);  // * bleibt *, zu z4
        z[3].addKonfiguration('1', '1', Konfiguration.LINKS, z[3]);  // z3 iteriert bis * nach links
        z[4].addKonfiguration('*', '*', Konfiguration.STOP, z[4]);   // Endzustand
        z[4].addKonfiguration('1', '1', Konfiguration.STOP, z[4]);   // Endzustand

        Alphabet alphabetA = new Alphabet("*1");  // Zulaessige Zeichen sind 1 und *
        Speicherband myTuringBand = new Speicherband(alphabetA);
        Zustand zustand = z[0];

        // initialisieren der Turingmaschine
        m.myTuringBand = myTuringBand;
        m.zustand = zustand;
        m.z = z;

        m.getTuringBand().setBeschriftung(bandEingabe);  // Benutzereingabe auf das Band schreiben

        System.out.println("Bandinhalt nach der Eingabe: " + m.myTuringBand.getString());

        System.out.println("********* Berechnungen Der Turing-Maschine *********");

        // So lange die Konfigurationen durchlaufen bis Turingmaschine stoppt.
        boolean exit = false;

        do{
            exit = m.nextStep();
            System.out.println( "    Aktueller Bandinhalt: " + m.getTuringBand().getString() );
        }
        while(!exit);

        // Die Anzahl 1'en am Ende auf dem Band ist das Ergebnis
        String ergebnis = m.getTuringBand().getString();

        int count = 0;
        for (int i = 0; i < ergebnis .length(); i++) {
            if (ergebnis .charAt(i) == '1') {
                count++;
            }
        }

        // Ergebnisse auf der Konsole zeigen
        System.out.println( "******* ERGEBNIS *******" );
        System.out.println( zahl_1_unary + " + " + zahl_2_unary + " (Unary)  = " + count + " (Decimal)" );
        System.out.println( "Bandinhalt nach der Berechnung: " + m.getTuringBand().getString() );


    }

}
