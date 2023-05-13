package skeletonPackage;

import java.util.ArrayList;

/** Game singleton osztály,
 *  tehát konstruktorai private,
 *  egy példányára mutató referenciát tud visszaadni.
 *  Egy metódusa van, ami által létre lehet egyszer hozni (getInstance)
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

    private Network network;

    private ArrayList<Character> characters; // játék sorrendben a játékosok karakterei
    private int activeCharacter; // aktív karakter sorszáma a listában

// GETTER, SETTER

    public int getPointsOfPlumber() { return pointsOfPlumber; }
    public int getGetPointsOfSaboteur(){ return pointsOfSaboteur; }
    public int getRound() { return round; }
    public int getActionPoints() { return actionPoints; }
    public Character getActiveCharacter() { return characters.get(activeCharacter-1); }

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
    public void setNetwork(Network n) { this.network = n; }

    /** hozzáadhatunk egy karaktert a karakterek listájához */
    public void addCharacter(Character c) { characters.add(c); }
    /** beállíthatjuk a karakter listát */
    public void setCharacters(ArrayList<Character> c) { characters = c; }

// METODUSOK

    /** Csökkenti eggyel az aktív karakter akciópontjait */
    public void removeActionPoints() { actionPoints--; }

    /** Következő karakter jön, network-öt meghívva végig megy a víz folyása (flowWater) */
    public void nextCharacter() {
        // kovi activeCharacter beállítása
        // ha utolso az activeCharacter volt, akkor round++
        activeCharacter++;
        actionPoints = default_actionP;

        if(activeCharacter == characters.size()) {
            round++;
        }

        //networkon flowWater meghívása
        network.flowWaterOnField();
    }

    /** Győztes meghatározása, játék vége */
    public void gameOver() {
        // ide még nem tudom mit kéne írni
        if(pointsOfPlumber > pointsOfSaboteur) { System.out.println("Szerelok nyertek!"); }
        if(pointsOfSaboteur > pointsOfPlumber) { System.out.println("Szabotorok nyertek!"); }
        else {
            System.out.println("Dontetlen!");
        }
    }
}
