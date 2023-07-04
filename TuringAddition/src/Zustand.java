public class Zustand {

    int zustandsNummer;
    Konfiguration[] myKonfiguration;

    public Zustand(int zustandsnummer) {
        this.zustandsNummer = zustandsNummer;
        myKonfiguration = new Konfiguration[0];
    }
    public boolean equals(Zustand zustand) {
        if (this.zustandsNummer == zustand.zustandsNummer)
            return true;
        return false;
    }

    /* Diese Methode fügt dem Zustand eine weitere Konfigurationszeile hinzu */
    public void addKonfiguration( char readSign, char writeSign, int operation, Zustand folgeZustand) {
        Konfiguration k = new Konfiguration(readSign, writeSign, operation, folgeZustand);
        Konfiguration[] temp = new Konfiguration[myKonfiguration.length + 1];
        for (int i = 0; i < myKonfiguration.length; i++) {
            temp[i] = myKonfiguration[i];
        }
        temp[myKonfiguration.length] = k; myKonfiguration = temp;
    }

    // Nicht gebraucht
    public int getNummer() {
        return zustandsNummer;
    }

    /* Gibt die Konfiguration zu einem bestimmten gelesenen Zeichen zurück. */
    public Konfiguration getKonfiguration(char readSign) {
        for (int i = 0; i < myKonfiguration.length; i++) {
            if (myKonfiguration[i].checkReadSign(readSign))
                return myKonfiguration[i];
        }
        return null;
    }

    // Nicht gebraucht
    /* Liefert alle Konfigurationen eines Zustandes zurück.  */
    public Konfiguration[] getKonfigurationen() {
        return myKonfiguration;
    }

}