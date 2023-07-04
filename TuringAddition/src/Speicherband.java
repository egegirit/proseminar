public class Speicherband {

    int index = 0; // Aktuelle Stand des Bandes, kann negativ sein
    int position = 0; // Gelesene Feld, kann nicht negativ sein
    String band;   // Aktueller Bandinhalt
    Alphabet myAlphabet;  // Erlaubte Zeichen

    public Speicherband(Alphabet alphabet) {  this.myAlphabet = alphabet; }

    /* Erlaubte Zeichen für das TuringBand   */
    public Speicherband(Alphabet myAlphabet, char[] band) {
        this.myAlphabet = myAlphabet;
        String temp = "";
        for (int i = 0; i < band.length; i++) {
            temp = temp + band[i]; } this.band = temp;
    }

    /* Konstruktor */
    public Speicherband(Alphabet myAlphabet, String band) {
        this.myAlphabet = myAlphabet;
        this.band = band;
    }

    /* Liefert das zum Band gehörende Alphabet */
    public Alphabet getAlphabet() {
        return myAlphabet;
    }

    public void setBeschriftung (String band){
        this.band = band;
    }

    /* Gibt das Zeichen an der aktuellen Position des
     Schreiblesekopfes zurück.  */
    public char getAktuellZeichen() {
        return band.charAt(position);
    }

    /* Schreibt das übergebene Zeichen an der aktuellen Position des
     Schreib/Lese-Kopfes auf das Turing-Band.  */
    public void setAktuellZeichen(char zeichen) {
        if (!myAlphabet.isInAlphabet(zeichen)) {
            System.out.println("Zeichen ist nicht im Alphabet!");
            System.exit(1);
        }
        StringBuffer b = new StringBuffer(band);
        b.setCharAt(position, zeichen);
        band = b.toString();
    }

    /* Gibt die aktuelle Position des Schreib/Lesekopfes auf dem
     String der mit getString() zurückgegeben wird. */
    public int getAktuellPosition() {
        return position;
    }

    /* Gibt den aktuellen Index der Position des Schreib-Lesekopfes.
     Der Index * 0 steht hierbei bei dem ersten Zeichen des Eingabewortes.  */
    public int getAktuellIndex() {
        return index;
    }

    /* setzt die aktuelle Position des Schreib/Lesekopfes ein Feld
     nach rechts. */
    public void rechts() {
        ++index; ++position;
        if (position == band.length()) {
            band = band + myAlphabet.getZeichen(0);
        }
    }

    /* Setzt die aktuelle Position des Schreib/Lesekopfes um ein
     Feld nach Links. */
    public void links() {
        --index; --position; if (position == -1) {
            band = myAlphabet.getZeichen(0) + band;
            ++position;
        }
    }

    /* Liefert die Beschriftung des TuringBandes im relevanten
     Bereich als String. */
    public String getString() {
        return band;
    }  // DELETE?

}