package skeletonPackage;

import java.util.ArrayList;

/** Game singleton osztály,
 *  tehát konstruktorai private,
 *  egy példányára mutató referenciát tud visszaadni.
 *  Egy metódusa van, ami által létre lehet egyszer hozni (getInstance)
 *  private static variable-ként kell tárolni.
 * */
public class Game {

// ATTRIBUTUMOK
    /** Egy példányára mutató referencia */
    private static Game instance = null;
    /** Szerelő csapat pontszáma */
    private int pointsOfPlumber = 0;
    /** Szabotőr csapat pontszáma */
    private int pointsOfSaboteur = 0;
    /** Kör sorszáma */
    private int round = 1;
    private static int max_round = 10;
    /** Aktív játékos akciópontja [0,5] intervallumban */
    private int actionPoints;

    /** Ennyi akciópontja lehet legfeljebb az aktív karakternek*/
    private static int default_actionP = 5;

    private Network network;

    private ArrayList<Character> characters = new ArrayList<Character>(); // játék sorrendben a játékosok karakterei
    private int activeCharacter; // aktív karakter sorszáma a listában 0-tól kezdve

// GETTER, SETTER

    public int getPointsOfPlumber() { return pointsOfPlumber; }
    public int getPointsOfSaboteur() { return pointsOfSaboteur; }
    public int getRound() { return round; }
    public int getActionPoints() { return actionPoints; }
    public Character getActiveCharacter() { return characters.get(activeCharacter); }

    public int getActiveCharNum() { return activeCharacter; }

    /** Növeli a szerelők pontszámát
     * @param n, amennyivel növeljük a pontszámot
     * */
    public void givePlumberPoint(int n) { pointsOfPlumber += n; }
    public void setPointsOfPlumber(int n) { pointsOfPlumber = n; }

    /** Növeli a szabotőrök pontszámát
     * @param n, amennyivel növeljük a pontszámot
     * */
    public void giveSaboteurPoint(int n) { pointsOfSaboteur += n; }
    public void setPointsOfSaboteur(int n) { pointsOfSaboteur = n; }

    /** Beállítja a kör sorszámát
     *  @param round, erre fogja beállítani, egész szám
     * */
    public void setRound(int round) { this.round = round; }

    /** Beállítja az aktív karakter akciópontjait max-ra */
    public void resetActionPoints() { actionPoints = default_actionP; }
    public void setNetwork(Network n) { this.network = n; }

    public void setActionPoint(int value) { actionPoints = value; }

    /** hozzáadhatunk egy karaktert a karakterek listájához */
    public void addCharacter(Character c) { characters.add(c); }
    /** beállíthatjuk a karakter listát */
    public void setCharacters(ArrayList<Character> c) { characters = c; }
    /** Paraméterben kapott c karaktert beállítja aktív karakternek */
    public void setActiveCharacter(Character c) {
        if(characters.contains(c)) {
            activeCharacter = characters.indexOf(c);
        }
    }

// METODUSOK

    private Game() {
        pointsOfSaboteur = 0;
        pointsOfPlumber = 0;
        round = 0;
        actionPoints = 5;
        network = new Network();
        characters = new ArrayList<Character>();
        activeCharacter = 0;
    }

    /** Visszaadja a singelton egyetlen példányát, ha nincs, akkor létrehoz és azt adja vissza */
    public static synchronized Game getInstance() {
        if(instance==null) {
            instance = new Game();
        }
        return instance;
    }

    /** Reseteli a példányt az alapbeállításokra */
    public void resetInstance() {
        if(instance!=null) {
            instance = new Game();
        }
    }

    /** Csökkenti eggyel az aktív karakter akciópontjait */
    public void removeActionPoints() {
        actionPoints--;
        if(actionPoints==0) {
            nextCharacter();
        }
    }

    /** Következő karakter jön, network-öt meghívva végig megy a víz folyása (flowWater) */
    public void nextCharacter() {
        // kovi activeCharacter beállítása
        activeCharacter++;
        actionPoints = default_actionP;

        // ha utolso az activeCharacter volt, akkor round++
        if(activeCharacter == characters.size()) {
            round++;
            if (round > max_round) {
                gameOver();
            }
            activeCharacter=0;
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
