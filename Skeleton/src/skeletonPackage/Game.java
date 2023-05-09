package skeletonPackage;

/** Game singleton osztály,
 *  tehát konstruktorai private,
 *  egy példányára mutató referenciát tud visszaadni
 *  egy metódusa van, ami által létre lehet egyszer hozni (getInstance)
 *
 *  private static variable-ként kell tárolni.
 * */
public class Game {

// ATTRIBUTUMOK
    /** Szerelő csapat pontszáma */
    private int pointsOfPlumber;
    /** Szabotőr csapat pontszáma */
    private int pointsOfSaboteur;
    /** Kör sorszáma */
    private int round;
    /** Aktív játékos akciópontja [0,5] intervallumban */
    private int actionPoints;

    /** Ennyi akciópontja lehet legfeljebb az aktív karakternek*/
    private static int default_actionP = 5;

// GETTER, SETTER

    public int getPointsOfPlumber() { return pointsOfPlumber; }
    public int getGetPointsOfSaboteur(){ return pointsOfSaboteur; }
    public int getRound() { return round; }
    public int getActionPoints() { return actionPoints; }

    /** Növeli a szerelők pontszámát
     * @param n, amennyivel növeljük a pontszámot
     * */
    public void givePlumberPoint(int n) { pointsOfPlumber += n; }

    /** Növeli a szabotőrök pontszámát
     * @param n, amennyivel növeljük a pontszámot
     * */
    public void giveSaboteurPoint(int n) { pointsOfSaboteur += n; }

    /** Beállítja a kör sorszámát
     *  @param round, erre fogja beállítani, egész szám
     * */
    public void setRound(int round) { this.round = round; }

    /** Beállítja az aktív karakter akciópontjait max-ra */
    public void resetActionPoints() { actionPoints = default_actionP; }

    /** Csökkenti eggyel az aktív karakter akciópontjait */
    public void removeActionPoints() { actionPoints--; }

// METODUSOK

    public void nextCharacter() {}

    public void gameOver() {}
}
