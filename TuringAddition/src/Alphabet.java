public class Alphabet {

    char[] zeichen;  // Erlaubte Zeichen

    /* Konstruktor: Alphabet ueber ein String definieren */
    public Alphabet(String alphabet) {
        zeichen = new char[alphabet.length()];
        for (int i = 0; i < alphabet.length(); i++) {
            zeichen[i] = alphabet.charAt(i);
        }
    }

    /*Erzeugt ein neues Alphabet, mit allen Zeichen die übergeben
     werden. Beim index 0 wird dabei das Zeichen das blank symbolisieren
     soll übergeben. */
    public Alphabet(char[] alphabet) {  zeichen = alphabet;  }

    /* Gibt das i-te Zeichen im Alphabet zurück. */
    public char getZeichen(int index) { return zeichen[index]; }

    /* Liefert den Index zu einem bestimmten Zeichen. */
    public int getIndex(char a) {
        for (int i = 0; i < zeichen.length; i++) {
            if (zeichen[i] == a) return i;
        }
        return -1;
    }

    /* Überprüft, ob ein bestimmtes Zeichen innerhalb des Alphabets ist.  */
    public boolean isInAlphabet(char a) {
        boolean temp = false;
        for (int i = 0; i < zeichen.length; i++) {
            if (zeichen[i] == a)
                temp = true;
        } return temp;
    }

}