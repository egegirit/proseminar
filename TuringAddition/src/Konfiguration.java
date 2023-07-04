public class Konfiguration {

    // Bandbewegungsrichtungen
    public static final int RECHTS = 1;
    public static final int LINKS = -1;
    public static final int STOP = 0;

    char readSign;   // gelesenes Zeichen
    char writeSign;  // zu schreibendes Zeichen
    int operation;   // auszuführende Operation als int
    Zustand folgeZustand; //Zustand nach Ausführung dieser Konfiguration

    // Konstruktor
    public Konfiguration( char readSign, char writeSign, int operation, Zustand folgeZustand)
    {
        this.readSign = readSign;
        this.writeSign = writeSign;
        this.folgeZustand = folgeZustand;
        this.operation = operation;
    }

    /* Überprüft, ob diese Konfiguration ein bestimmtes Zeichen als * Eingangsbedingung hat. * @param a Zeichen, das verglichen werden soll * @return boolean true, wenn dieses Zeichen Eingangsbedingung
     für
     * diese Konfiguration ist. */
    public boolean checkReadSign(char a) {
        if (this.readSign == a)
            return true;
        return false;
    }

    /* Gibt das als Eingangsbedingung zu lesende Zeichen zurück. */
    public char getReadSign() { return readSign; }

    /* Gibt die Operation zurück, die bei Ausführung dieser
     Konfiguration * ausgeführt werden soll  */
    public int getOperation() {
        return operation;
    }

    /* Gibt das Zeichen zurück, dass an die aktuelle Stelle auf das TuringBand geschrieben werden soll */
    public char getWriteSign() {
        return writeSign;
    }

    /* Gibt den Folgezustand nach Ausführung dieser Konfiguration
     zurück. */
    public Zustand getFolgeZustand() {
        return folgeZustand; }

}