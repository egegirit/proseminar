public class TuringMaschine {

    Speicherband myTuringBand;  // Speicherband
    Zustand[] z;  // alle Zustände
    Zustand zustand;  // aktueller Zustand
    char gelesen; // das gelesene Zeichen
    boolean running = false;

    static int operationsAnzahl = 0;

    /* Initialisiert die Turingmaschine. */
    public void initialize() {  // Zu main Methode kopiert
        z = new Zustand[5];
        for (int i = 0; i < z.length; i++)
            z[i] = new Zustand(i + 1);
        z[0].addKonfiguration('*', '1', Konfiguration.RECHTS,z[1]);
        z[0].addKonfiguration('1', '1', Konfiguration.RECHTS,z[0]);
        z[1].addKonfiguration('*', '*', Konfiguration.LINKS, z[2]);
        z[1].addKonfiguration('1', '1', Konfiguration.RECHTS,z[1]);
        z[2].addKonfiguration('*', '*', Konfiguration.STOP, z[2]);
        z[2].addKonfiguration('1', '*', Konfiguration.LINKS, z[3]);
        z[3].addKonfiguration('*', '*', Konfiguration.RECHTS,z[4]);
        z[3].addKonfiguration('1', '1', Konfiguration.LINKS, z[3]);
        z[4].addKonfiguration('*', '*', Konfiguration.STOP, z[4]);
        z[4].addKonfiguration('1', '1', Konfiguration.STOP, z[4]);
        Alphabet alphabet = new Alphabet("*1");
        myTuringBand = new Speicherband(alphabet);
        zustand = z[0];
    }

    /*Führt den nächsten Schritt abhängig von Zustand und gelesenem
      Zeichen aus. * Zeigt an ob die Maschine stoppt, oder ob weitere Schritte
      folgen.
     */
    public boolean nextStep() {
        ++operationsAnzahl;
        System.out.println( operationsAnzahl + ". Operation");
        System.out.println( "Running: " + running);
        if (!running) running = true;

        System.out.println( "  Zeiger Position: " + myTuringBand.getAktuellIndex());
        //System.out.println( "  Zeiger Position2: " + myTuringBand.getAktuellPosition());

        gelesen = myTuringBand.getAktuellZeichen();
        System.out.println( "  Gelesen: " + gelesen);
        Konfiguration k = zustand.getKonfiguration(gelesen);
        char write = k.getWriteSign();
        myTuringBand.setAktuellZeichen(write);
        System.out.println( "  Geschrieben: " + write);

        if (k.getOperation() == k.LINKS) {
            myTuringBand.links();
            System.out.println( "    Band links bewegt!");
        }
        if (k.getOperation() == k.RECHTS) {
            myTuringBand.rechts();
            System.out.println( "    Band rechts bewegt!");
        }
        if (k.getOperation() == k.STOP) {
            System.out.println( "    Band angehalten!");
            running = false;
            return true;
        }
        zustand = k.getFolgeZustand();
        return false;
    }

    /* Liefert true zurück, wenn die Turingmaschine noch läuft */
    public boolean isRunning() {
        return running;
    }

    /* Liefert das Turingband, das von dieser Maschine bearbeitet wird. */
    public Speicherband getTuringBand() {
        return myTuringBand;
    }
}