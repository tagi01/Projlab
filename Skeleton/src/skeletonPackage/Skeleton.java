package skeletonPackage;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Skeleton {
	
	/**
	 * A felhasznalo inputjainak beolvasasahoz
	 */
	private static Scanner input;
	/**
	 * az objektumok neveit tartalmazo map
	 */
	private static Map<Object, String> names;
	
	public static void main(String[] args) {
		System.out.println("-----------------------------------------------------\n"
				         + "|    -----    ------    -----    -----    ------    |\n"
				         + "|      |      |         |        \\          |       |\n"
				         + "|      |      ------    -----     \\         |       |\n"
				         + "|      |      |             |      \\        |       |\n"
				         + "|      |      ------    ----     -----      |       |\n"
				         + "-----------------------------------------------------\n");
		System.out.println("1.  Pumpa vagy cso felvetele ciszternanal\n"
						 + "2.  Pumpa lerakasa\n"
						 + "3.  Szerelo, szabotor mozgatasa masik mezore.\n"
						 + "4.  Pumpaban viz mozgatasa\n"
						 + "5.  Ciszterna viz fogadasa\n"
						 + "6.  Forras vizet ad\n"
						 + "7.  Pumpa eltorese\n"
						 + "8.  Szabotor kilyukaszt egy csovet\n"
						 + "9.  Cso felvetele egy pumpanal\n"
						 + "10. Pumpa beallitasa szerelokent/szabotorkent.\n"
						 + "11. Szerelo megjavitja egy csovet/pumpat\n"
						 + "12. Kilepes\n");
		input = new Scanner(System.in);
		int numb = 0;
		
		while(true) {
			numb = input.nextInt();
			try {		
				switch(numb) {
				case 1:
					one();
					break;
				case 2:
					two();
					break;
				case 3:
					three();
					break;
				case 4:
					four();
					break;
				case 5:
					five();
					break;
				case 6:
					six();
					break;
				case 7:
					seven();
					break;
				case 8:
					eight();
					break;
				case 9:
					nine();
					break;
				case 10:
					ten();
					break;
				case 11:
					eleven();
					break;
				case 12:
					System.out.println("Tesztek vege!");
					input.close();
					System.exit(0);
				default:
					System.out.print("Valassz a megadott menupontok kozul: ");
				}
				
			} catch (InputMismatchException e) {
                    e.printStackTrace();
            }
		}
		
	}
	
	/** TEST 1 - Pumpa vagy Pipe felvetele ciszternanal */
	public static void one() {
		System.out.println("TEST 1 - Pumpa vagy Pipe felvetele ciszternanal");
		names = new HashMap<Object, String>();
		Cistern c = new Cistern();
		names.put(c, "c");
		Network n = new Network();
		names.put(n, "n");
		Plumber p = new Plumber(c, n);
		names.put(p, "p");
		n.addField(c);
		System.out.println("\t1. Pumpa felvetele ciszternanal\n"
							+ "\t2. Cso felvetele ciszternanal");
		int numb = 0;
		try {
			boolean validAnswer = false;
			while(!validAnswer){
				numb = input.nextInt();
				if(numb == 1 || numb == 2){
					validAnswer = true;
				} else {
					System.out.println("\tValassz a megadott menupontok kozul!");
				}
			}
		} catch (InputMismatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(numb == 1) {
			boolean hasPump = askQuestion("\tVan pumpa a ciszternan?");
			c.setHasPump(hasPump);
			p.getPump();
		} else if(numb == 2) {
			boolean hasPipe = askQuestion("\tVan cso a ciszternan?");
			c.setHasPipe(hasPipe);
			p.getPipe();
		}
		System.out.println("Teszt vege");
	}

	/** TEST 2 - Pumpa lerakasa */
	public static void two() {
		System.out.println("TEST 2 - Pumpa lerakasa");
		names = new HashMap<Object, String>();
		Network network = new Network();
		names.put(network, "network");
		Pipe currentField = new Pipe();
		names.put(currentField, "currentField");
		Plumber plumber = new Plumber(currentField, network);
		names.put(plumber, "plumber");
		boolean hasPump = askQuestion("Van pumpa a szerelonek?");
		if(hasPump) {
			Pump inventoryPump = new Pump();
			names.put(inventoryPump, "inventoryPump");
			Pump pump2 = new Pump();
			names.put(pump2, "pump2");
			plumber.addInventory(inventoryPump);
			currentField.addNeighbour(pump2);
			pump2.addNeighbour(currentField);
			network.addField(currentField);
			network.addField(pump2);
		}
		plumber.placePump();
		System.out.println("Teszt vege");
		
	}
	
	/** TEST 3 - Szerelo, szabotor mozgatasa masik mezore */
	public static void three() {
		System.out.println("TEST 3 - Szerelo, szabotor mozgatasa masik mezore");
		
		System.out.println("\tMelyik karakterrel szerentel lepni: szerelovel(p) vagy szabotorrel(s)? ");
		// egy karakter bekerese
		System.out.println("\tMelyik mezon van a karaktered?\n\tForrason(s), elso csovon(p1), pumpan(p), masodik csovon(p2) vagy ciszternan(c)? ");
		// sor bekerese
		
		System.out.println("\tMelyik mezore szeretnel lepni?\\n\\tForrasra(s), elso csore(p1), pumpan(p), masodik csore(p2) vagy ciszternara(c)? ");
		// sor bekerese
		
		// TODO Test 3, Move Plumber and Saboteur to Fields teszkornyezet
		
		// fuggveny meghivasa
		
		
		System.out.println("Teszt vege");
	}
	
	/** TEST 4 - Pumpaban viz mozgatasa */
	public static void four() {
		System.out.println("TEST 4 - Pumpaban viz mozgatasa");
		System.out.println("\tMekkora a bemeneti cso merete? (egesz szam) ");
		// szam beolvasasa
		System.out.println("\tMennyi viz van a bemeneti csoben? (egesz szam) ");
		// szam beolvasasa
		
		System.out.println("\tMekkora a kimeneti cso merete? (egesz szam) ");
		// szam beolvasasa
		System.out.println("\tMennyi viz van a kimeneti csoben? (egesz szam) ");
		// szam beolvasasa
		
		// TODO Test 4, Pump moves water tesztkornyezet
		
		// fuggveny meghivasa
		
		
		System.out.println("Teszt vege");
	}
	
	/***/
	public static void five() {
		System.out.println("TEST 5 - Ciszterna viz fogadasa");
		System.out.println("\tVan cso csatlakoztatva a ciszternahoz? (i, n) ");
		// karakter beolvasasa
		
		// TODO Test 5, Cistern takes water tesztkornyezet
		
		// fuggveny meghivasa
		
		
		System.out.println("Teszt vege");
	}
	
	/** TEST 6 - Forras vizet ad */
	public static void six() {
		System.out.println("TEST 6 - Forras vizet ad");
		
		System.out.println("\tVan cso csatlakoztatva a forrashoz? (i, n) ");
		// karakter beolvasasa
		
		// TODO Test 6, Source gives water tesztkornyezet
		
		// fuggveny meghivasa
		
		
		System.out.println("Teszt vege");
	}
	
	/** TEST 7 - Pumpa eltorese */
	public static void seven() {
		System.out.println("TEST 7 - Pumpa eltorese");
		System.out.println("\tElvan torve a pumpa? (i, n) ");
		// karakter beolvasasa
		
		// TODO Test 7, Break pump tesztkornyezet
		
		// fuggveny meghivasa
		
	
		System.out.println("Teszt vege");
	}
	
	/** TEST 8 - Szabotor kilyukaszt egy csovet */
	public static void eight() {
		System.out.println("TEST 8 - Szabotor kilyukaszt egy csovet");
		System.out.println("\tElvan torve a cso? (i, n) ");
		// karakter beolvasasa
		
		//TODO Test 8, Saboteur punctures pipe tesztkornyezet
		
		// fuggveny meghivasa
		
		
		System.out.println("Teszt vege");
	}
	
	/** TEST 9 - Cso felvetele egy pumpanal */
	public static void nine() {
		System.out.println("TEST 9 - Cso felvetele egy pumpanal");
		System.out.println("\tUres az inventory-ja? (i, n) ");
		// karakter beolvasasa
		System.out.println("\tHasznalja a pumpa ezt a csovet? (i, n) ");
		// karakter beolvasasa
		
		// TODO Test 9, Grab pipe tesztkornyezet
		
		// fuggveny meghivasa
		
		System.out.println("Teszt vege");
	}
	
	/** TEST 10 - Pumpa beallitasa szerelokent/szabotorkent */
	public static void ten() {
		System.out.println("TEST 10 - Pumpa beallitasa szerelokent/szabotorkent");
		System.out.println("\tMelyik karakterrel szerentel lepni: szerelovel(p) vagy szabotorrel(s)? ");
		// karakter beolvasasa
		System.out.println("\t4 cso kozul melyik legyen a pumpa bemenete? (1-4) ");
		// szam beolvasasa
		System.out.println("\t4 cso kozul melyik legyen a pumpa kimenete? (1-4) ");
		// szam beolvasasa, ellenorzese, elozovel megegyezik-e
		
		// TODO Test 10, Plumber sets pump tesztkornyezet
		// TODO Test 10, Saboteur sets pump tesztkornyezet
		
		// fuggveny meghivasa
		
		
		System.out.println("Teszt vege");
	}
	
	/** TEST 11 - Szerelo megjavit egy csovet/pumpat */
	public static void eleven() {
		System.out.println("TEST 11 - Szerelo megjavit egy csovet/pumpat");
		System.out.println("\tEl van romolva a cso? (i, n) ");
		// karakter beolvasasa
		
		// TODO Test 11, Plumber repairs pump tesztkornyezet
		// TODO Test 11, Plumber repairs pipe tesztkornyezet
		
		// fuggveny meghivasa
		
		
		System.out.println("Teszt vege");
	}
	
	/**
	 * A felhasznalotol kerdez egy eldontendo kerdest
	 * @param question Az eldontendo kerdes
	 * @return a valasz
	 */
	public static boolean askQuestion(String question) {
		boolean answer = false;
		System.out.println("\t" + question + " I/N");
		String line;
		boolean validAnswer = false;
		try {
			while(!validAnswer){
				line = input.next();
				if(line.equals("i") || line.equals("I")){
					answer = true;
					validAnswer = true;
				} else if(line.equals("n") || line.equals("N")) {
					answer = false;
					validAnswer = true;
				} else {
					System.out.println("\t Hibas valasz");
				}
			}
		} catch (InputMismatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return answer;
	}
	
	/**
	 * Visszaadja egy objektum tipusat es nevet
	 * @param object a keresett objektum
	 * @return az objektum tipusa es neve egy stringben
	 */
	public static String getName(Object object) {
		String name;
		name = object.getClass().getSimpleName() + " " + names.get(object);
		return name;
	}

}
