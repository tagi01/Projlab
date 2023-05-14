package skeletonPackage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Program {

	/**
	 * A felhasznalo inputjainak beolvasasahoz
	 */
	private static Scanner input;
	
	/**
	 * A fájl inputjainak beolvasasahoz
	 */
	private static Scanner fileInput;
	
	private static Game game=new Game();

	private static Network network = new Network();
	/**
	 * az objektumok neveit tartalmazo map
	 */

	private static Map<String , Pipe> pipes = new HashMap<String , Pipe>();
	
	private static Map<String , Pump> pumps = new HashMap<String , Pump>();
	
	private static Map<String , Source> sources = new HashMap<String , Source>();
	
	private static Map<String , Cistern> cisterns = new HashMap<String , Cistern>();
	
	private static Map<String , Saboteur> saboteurs = new HashMap<String , Saboteur>();
	
	private static Map<String , Plumber> plumbers = new HashMap<String , Plumber>();


	/**
	 * tárolja hogy elkezdődött-e már a játék
	 */
	private static boolean started = false;

	/**
	 * Main metódus
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("-----------------------------------------\n"
		         + "|   ___    ___    ____  _______  ____   |\n"
		         + "|  |   |  |   |  |    |    |    |    |  |\n"
		         + "|  |___|  |___|  |    |    |    |    |  |\n"
		         + "|  |      |\\     |    |    |    |    |  |\n"
		         + "|  |      | \\    |____|    |    |____|  |\n"
		         + "-----------------------------------------\n");


		input = new Scanner(System.in);
		String input_temp;

		while (true) {
			input_temp = new String();
			input_temp = input.nextLine();
			System.out.println("Kijutott");
			try {
				readCommand(input_temp);
			}catch(InputMismatchException e) {
				System.out.println("Hiba tortent.");
			}

		}

	}
	
	/**
	 * Kezeli a parancsokat a kapott sorban
	 * @param line a parancsot tartalmazó sor
	 */
	public static void readCommand(String line) {
		String[] splitted = line.split("\\s+");
		switch(splitted[0]) {
		case "create":
			if(splitted.length > 1 && splitted[1].equals("network")) {
				createNetwork(Arrays.copyOfRange(splitted, 2, splitted.length));	
			} else {
				System.out.println("Hibas parancs.");
			}				
			break;
		case "action":
			if(started) {
				splitted = Arrays.copyOfRange(splitted, 1, splitted.length);
				switch(splitted[0]) {
				case "puncture":
					puncture(splitted);
					break;
				case "sticky":
					actionSticky();
					break;
				case "setpump":
					actionSetPump(splitted);
					break;
					case "slippery":
					actionSlippery(splitted);
					break;
				case "repair":
					repair();
					break;
				case "placePipe":
					placePipe(splitted);
					break;
				case "placePump":
					actionPlacePump();
					break;
				case "grabPipe":
					
					break;
				case "grabPump":
					grabPump(splitted);
					break;
				case "move":
					move(splitted);
					break;
				case "pass":
					actionPass();
					break;
				default:
					System.out.println("Hibas parancs.");
					break;
				}
			} else {
				System.out.println("Meg nem kezdodott el a jatek");
			}
			break;
		case "get":
			splitted = Arrays.copyOfRange(splitted, 1, splitted.length);
			System.out.println("switch " + splitted[0]);
			switch(splitted[0]) {
			case "neighbours":
				String c = splitted.length > 1 ? splitted[1] : null;
				getNeighbours(c);
				break;
			case "hasPipe":
				getHasPipe(splitted);
				break;
			case "inventory":
				getInventory(splitted);
				break;
			case "actionPoints":
				getActionPoint(splitted);
				break;
			case "currentField":
				System.out.println(getKeyFromFieldMaps(game.getActiveCharacter().currentField));
				break;
			case "place":
				getPlace(splitted);
				break;
			case "sticky":
				getSticky(splitted);
				break;
			case "slippery":
				if(splitted.length == 1) {
					System.out.println("Hibas parancs.");
					break;
				}
				getSlippery(splitted[1]);
				break;
			case "cantPuncture":
				getCantPuncturePipe(splitted);
				break;
			case "isBroken":
				getIsBroken(splitted);
				break;
			case "teamPoints":
				getTeamPoints(splitted);
				break;
			case "connections":
				if(splitted.length == 1) {
					System.out.println("Hibas parancs.");
					break;
				}
				getConnections(splitted[1]);
				break;
			case "water":
				getWater(splitted);
				break;
			case "state":
				getState(splitted);
				break;
			case "hasPump":
				getHasPump(splitted);
				break;
			default:
				System.out.println("Hibas parancs.");
				break;
			}
			break;
		case "start":
			started = true;
			System.out.println("Sikeres parancs.");
			break;
		case "load":
			load(splitted);
			break;
		case "save":
			
			break;
		case "pumpWater":
			pumpWater(splitted);
			break;
		case "pumpBreak":
			
			break;
		case "flowWater":
			flow(splitted);
			break;
		case "exit":
			System.out.println("Proto vege!");
			input.close();
			System.exit(0);
		default:
			String[] first = splitted[0].split("-");
			System.out.println(first[0] + " " + first[1]);
			if (first[0].contentEquals("set")) {
				splitted[0] = first[1];
				if (!started) {
					//splitted = Arrays.copyOfRange(splitted, 1, splitted.length);
					switch(splitted[0]) {
					case "pipe":
						setPipe(splitted);
						break;
					case "pump":
						setPump(splitted);
						break;
					case "p":
						setP(splitted);
						break;
					case "s":
						setS(Arrays.copyOfRange(splitted, 1, splitted.length));
						break;
					case "cistern":
						setCistern(splitted);
						break;
					case "active":
						setActive(splitted);
						break;
					case "game":
						setGame(splitted);
						break;
					case "random":
						setRandom(splitted);
						break;
					default:
						System.out.println("Hibas parancs.");
						return;
					}
				} else {
					System.out.println("Hibas parancs.");
					return;
				}
				break;
			} else {
				System.out.println("Hibas parancs.");
				return;
			}
		}
	}

	// Beállítja egy adott szamú csőnek a paramétereit
	public static void setPipe(String[] command) {
		if (pipes.containsKey(command[1])) {
			Pipe temp = pipes.get(command[1]);
			if (command[2].equals("-n")) {
				if (command[3].equals("[") && command[6].equals("]")) {

					// Megvizsgalni az eslo szomszedot
					if (pumps.containsKey(command[4])) {
						temp.addNeighbour(pumps.get(command[4]));
					} else if (sources.containsKey(command[4])) {
						temp.addNeighbour(sources.get(command[4]));
					} else if (cisterns.containsKey(command[4])) {
						temp.addNeighbour(cisterns.get(command[4]));
					} else if (plumbers.containsKey(command[4])) {
						Plumber temp_p = plumbers.get(command[4]);
						temp_p.setInventoryPipe(temp);
					} else
						System.out.println(
								"Nincs ilyen mezo vagy szerelo elso szomszedkent akihez a csovet hozza tudod rendelni, kotni");

					// Megvizsgalni a masodik szomszedot
					if (pumps.containsKey(command[5])) {
						temp.addNeighbour(pumps.get(command[5]));
					} else if (sources.containsKey(command[5])) {
						temp.addNeighbour(sources.get(command[5]));
					} else if (cisterns.containsKey(command[5])) {
						temp.addNeighbour(cisterns.get(command[5]));
					} else if (plumbers.containsKey(command[5])) {
						Plumber temp_p = plumbers.get(command[5]);
						temp_p.setInventoryPipe(temp);
					} else
						System.out.println(
								"Nincs ilyen mezo vagy szerelo masodik szomszedkent akihez a csovet hozza tudod rendelni, kotni");

					// tovabbi parancsok kiertekelese
					for (int i = 7; i < command.length; i += 2) {
						// cso meretet allithatod be
						if (command[i].equals("-s")) {
							temp.setSize(Integer.parseInt(command[i + 1]));
						}
						// csobol mennyi viz folyt ki
						else if (command[i].equals("-l")) {
							temp.setLostWater(Integer.parseInt(command[i + 1]));
						}
						// csoben mennyi viz van
						else if (command[i].equals("-w")) {
							temp.setWater(Integer.parseInt(command[i + 1]));
						}
						// cso mennyi ideig nem lyukaszthato meg ki
						else if (command[i].equals("-p")) {
							temp.setCantPuncture(Integer.parseInt(command[i + 1]));
						}
						// cso ki van e lyukasztva vagy ninics
						else if (command[i].equals("-b")) {
							if (command[i + 1].equals("true")) {
								temp.setBroken(true);
							} else
								temp.setBroken(false);
						}
						// ragadosra allitja, ha true az ertek
						else if (command[i].equals("-st")) {
							if (command[i + 1].equals("true")) {
								temp.setStateOfPipeSticky();
							} else
								temp.setStateOfPipeNormal();
						}
						// csusszossa allitja, ha true az ertek
						else if (command[i].equals("-sl")) {
							if (command[i + 1].equals("true")) {
								temp.setStateOfPipeSlippery();
							} else
								temp.setStateOfPipeNormal();
						}
					}

					System.out.println("Befejezodtek a beállítások!");
				} else
					System.out.println("Nem jo a parameterezes nem helyes vagy hianyzik a <[...]> !");
			} else
				System.out.println("Nem jo a parameterezes nem helyes vagy hianyzik a <-n> !");
		} else
			System.out.println("Nincs ilyen cso!");
	}

	public static void setActive(String[] command) {
		if (plumbers.containsKey(command[1])) {
			game.setActiveCharacter(plumbers.get(command[1]));
			if (command.length == 4) {
				if (command[2].equals("-p")) {
					game.setActionPoint(Integer.parseInt(command[4]));
				}
			} else {
				game.setActionPoint(5);
				System.out.println("Sikeresen beallitotta az aktiv karaktert: " + command[1] + " akciopontjai: "
						+ game.getActionPoints());
			}

		} else if (saboteurs.containsKey(command[1])) {
			game.setActiveCharacter(saboteurs.get(command[1]));
			if (command.length == 4) {
				if (command[2].equals("-p")) {
					game.setActionPoint(Integer.parseInt(command[4]));
				}
			} else {
				game.setActionPoint(5);
				System.out.println("Sikeresen beallitotta az aktiv karaktert: " + command[1] + " akciopontjai: "
						+ game.getActionPoints() );
			}
		} else
			System.out.println("Nincsen ilyen nevu karakter");
	}

	public static void actionSetPump(String[] command) {
		if (command[1] != null && command[2] != null && pipes.containsKey(command[1])
				&& pipes.containsKey(command[2])) {
			game.getActiveCharacter().setPump(pipes.get(command[1]), pipes.get(command[2]));

		}
	}

	public static void getActionPoint(String[] command) {
		System.out.println("A hatralevo akciopontok: " + game.getActionPoints());
	}

	public static void getCantPuncturePipe(String[] command) {
		if (pipes.containsKey(command[1])) {
			System.out.println("Az hatralevo ido: " + pipes.get(command[2]).getCantPuncture());
		} else
			System.out.println("Nincsen ilyen pipe: -1");
	}

	public static void getHasPipe(String[] command) {
		if (cisterns.containsKey(command[1])) {
			if (cisterns.get(command[1]).getHasPipe()) {
				System.out.println("Van felveheto cso ennel a cisternanal");
			} else
				System.out.println("Nincsen felveheto cso ennel a cisternanal");
		} else
			System.out.println("Nincsen ilyen cisterna.");
	}

	public static void getInventory(String[] command) {
		if (plumbers.containsKey(command[1])) {
			// ha van nala cso
			if (plumbers.get(command[1]).getInventoryPipe() != null) {
				// ha ket veg van nala akkor megkeresi a referenciakat, es kiirja ketszer
				if (plumbers.get(command[1]).getPipeEnds() == 2) {
					System.out.print("A szerelonel a kovetkezok vannak inventoryba: ");
					for (Map.Entry<String, Pipe> set : pipes.entrySet()) {
						if (set.getValue().equals(plumbers.get(command[1]).getInventoryPipe())) {
							System.out.print("[" + set.getKey() + "] [" + set.getKey());
						}
					}
				}
				// ha nem ket veg van nal, akkor csak egyszer irja ki
				else {
					System.out.print("A szerelonel a kovetkezok vannak inventoryba: ");
					for (Map.Entry<String, Pipe> set : pipes.entrySet()) {
						if (set.getValue().equals(plumbers.get(command[1]).getInventoryPipe())) {
							System.out.print("[" + set.getKey() + "]");
						}
					}
				}
			}
			// ha van nala pumpa
			if (plumbers.get(command[1]).getInventoryPump() != null) {
				for (Map.Entry<String, Pump> set : pumps.entrySet()) {
					if (set.getValue().equals(plumbers.get(command[1]).getInventoryPump())) {
						System.out.print("[" + set.getKey() + "]");
					}
				}
			}
			System.out.println();
		} else
			System.out.println("Nincs ilyen karkter");
	}

	/**
	 * setCistern parancsot valósítja meg
	 * @param command a parancs szavai
	 */
	public static void setCistern(String[] command) {
		String cistern = "cistern_";
		cistern = cistern.concat(command[1]);
		if (cisterns.containsKey(cistern)) {
			for (int i = 2; i < command.length; i += 2) {
				switch (command[i]) {
				case "-pi":
					if (command[i + 1] == "true") {
						cisterns.get(cistern).setHasPipe(true);
						//System.out.println("Beallitva.");
					} else if (command[i + 1] == "false") {
						cisterns.get(cistern).setHasPipe(true);
						//System.out.println("Beallitva.");
					} else {
						System.out.println("Hibas parancs.");
						return;
					}
					break;
				case "-pu":
					if (command[i + 1] == "true") {
						cisterns.get(cistern).setHasPump(true);
						//System.out.println("Beallitva.");
					} else if (command[i + 1] == "false") {
						cisterns.get(cistern).setHasPump(true);
						//System.out.println("Beallitva.");
					} else {
						System.out.println("Hibas parancs.");
						return;
					}
					break;
				case "-w":
					int water;
					try {
						water = Integer.parseInt(command[i + 1]);
					} catch(NumberFormatException e) {
						System.out.println("Hibas parancs.");
						return;
					}
					if (water >= 0) {
						cisterns.get(cistern).setCollectedWater(water);
						//System.out.println("Beallitva.");
					} else {
						System.out.println("Hibas parancs.");
						return;
					}
					break;
				default:
					System.out.println("Hibas parancs.");
					return;
				}
			}
		} else {
			System.out.println("Hibas parancs.");
			return;
		}
		System.out.println("Beallitva.");
	}
	
	/**
	 * repair parancsot valósítja meg
	 * @param command a parancs szavai
	 */
	public static void repair() {
		Plumber currentPlumber = null;
		for (Plumber p : plumbers.values()) {
			if (game.getActiveCharacter() == p)
				currentPlumber = p;
		}
		if (currentPlumber != null) {
			if (pipes.containsValue(game.getActiveCharacter().getField()) || pumps.containsValue(game.getActiveCharacter().getField())) {
				int actionPoint = game.getActionPoints();
				currentPlumber.repair();
				if (actionPoint != game.getActionPoints()) {
					System.out.println("Sikeres parancs.");
				} else {
					System.out.println("Akcio vege, nincs valtozas.");
				}
				System.out.println(game.getActionPoints());
			} else {
				System.out.println("Karakter nem ilyen tipusu mezon all.");
			}
		} else {
			System.out.println("Ehhez a parancshoz nincs hozzaferese.");
		}
	}
	
	/**
	 * getPlace parancsot valósítja meg
	 * @param command a parancs szavai
	 */
	public static void getPlace(String[] command) {
		if (plumbers.containsKey(command[1])) {
			String name = getKeyFromFieldMaps(plumbers.get(command[1]).getField());
			System.out.println(name);
		} else if (saboteurs.containsKey(command[1])) {
			String name = getKeyFromFieldMaps(saboteurs.get(command[1]).getField());
			System.out.println(name);
		} else {
			System.out.println("Hibas parancs.");
		}
	}
	
	/**
	 * getState parancsot valósítja meg
	 * @param command a parancs szavai
	 */
	public static void getState(String[] command) {
		String pipe = "pipe_";
		pipe = pipe.concat(command[1]);
		if (pipes.containsKey(pipe)) {
			if (pipes.get(pipe).getState() == StateOfPipe.NORMAL) {
				System.out.println("Normal");
			} else if (pipes.get(pipe).getState() == StateOfPipe.STICKY) {
				System.out.println("Sticky");
			} else if (pipes.get(pipe).getState() == StateOfPipe.SLIPPERY) {
				System.out.println("Slippery");
			} else if (pipes.get(pipe).getState() == StateOfPipe.SETSTICKY) {
				System.out.println("Setsticky");
			}
		} else {
			System.out.println("Hibas parancs.");
		}
	}

	/**
	 * getIsBroken parancsot valósítja meg
	 * @param command a parancs szavai
	 */
	public static void getIsBroken(String[] command) {
		if (pipes.containsKey(command[1])) {
			System.out.println(pipes.get(command [1]).getBroken());
		} else if (pumps.containsKey(command[1])) {
			System.out.println(pumps.get(command [1]).getBroken());
		} else {
			System.out.println("Hibas parancs.");
		}
	}
	
	
	/**
	 * pumpBreak parancsot, pumpa eltörését valósítja meg
	 * @param command a parancs szavai
	 */
	public static void pumpBreak(String[] command) {
		String pump = "pump_";
		pump = pump.concat(command[1]);
		if (pumps.containsKey(pump)) {
			pumps.get(pump).breakField();
		}
	}
	
	/**
	 * load parancsot, fájl betöltését valósítja meg
	 * @param command a parancs szavai
	 */
	public static void load(String[] command) {
		String file = command[2];
		file = file.concat("/");
		file = file.concat(command[3]);
		try {
			fileInput = new Scanner(new FileInputStream(file));
			while(fileInput.hasNextLine()) {
				String line = fileInput.nextLine();
				try {
					readCommand(line);
				}catch(InputMismatchException e) {
					System.out.println("Hiba tortent.");
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Betoltes sikertelen. Nincs ilyen fajl.");
		}
	}
	/**
	 * A create network parancsot valósítja meg, létrehozza a pálya elemeit.
	 * @param command a parancsban megadott értékek tömbje (parancs nélkül)
	 */
	public static void createNetwork(String[] command) {
		if(command.length < 5) {
			System.out.println("Hibas parancs.");
			return;
		}
		
		int pipeNum, pumpNum, sourceNum, cisternNum, charNum;
		try {
			pipeNum = Integer.parseInt(command[0]);
			pumpNum = Integer.parseInt(command[1]);
			sourceNum = Integer.parseInt(command[2]);
			cisternNum = Integer.parseInt(command[3]);
			charNum = Integer.parseInt(command[4]);
		} catch(NumberFormatException ex){
			System.out.println("Hibas parancs.");		// nem számok
			return;
		}
		if(charNum < 2 || charNum > 3) {
			System.out.println("Hibas parancs.");		// nem megfelelő a karakterek száma
			return;
		}
		
		for(int i = 0; i < pipeNum; i++) {
			Pipe p = new Pipe();
			pipes.put("pipe_" + (i+1), p);
			network.addField(p);
		}
		for(int i = 0; i < pumpNum; i++) {
			Pump p = new Pump();
			pumps.put("pump_" + (i+1), p);
			network.addField(p);
		}
		for(int i = 0; i < sourceNum; i++) {
			Source s = new Source();
			sources.put("source_" + (i+1), s);
			network.addField(s);
		}
		for(int i = 0; i < cisternNum; i++) {
			Cistern c = new Cistern();
			cisterns.put("cistern_" + (i+1), c);
			network.addField(c);
		}
		for(int i = 0; i < charNum; i++) {
			Plumber p = new Plumber(null, network);
			plumbers.put("Plumber_" + (i+1), p);
			game.addCharacter(p);
			Saboteur s = new Saboteur(null, network);
			saboteurs.put("Saboteur_" + (i+1), s);
			game.addCharacter(s);
		}
		System.out.println("Beallitva.");
	}
	
	/**
	 * Visszaadja az átadott kulcsú Field-et a Map-ekből
	 * @param key a keresett Field kulcsa
	 * @return a Field, ha benne van vmelyik Map-ben, null, ha nincs
	 */
	private static Field getValueFromFieldMaps(String key) {
		if(pipes.containsKey(key)) return pipes.get(key);
		if(pumps.containsKey(key)) return pumps.get(key);
		if(sources.containsKey(key)) return sources.get(key);
		if(cisterns.containsKey(key)) return cisterns.get(key);
		return null;
	}

	/** Visszaadja a felvett mező Map-ekből a kulcsot
	 * @param value bármelyik Field leszármazott 
	 * @return a Field kulcsa
	 * */
	private static String getKeyFromFieldMaps(Field value) {
		for(Map.Entry<String, Pipe> entry: pipes.entrySet()) {
			if(entry.getValue() == value) {
				return entry.getKey();
			}
		}
		for(Map.Entry<String, Pump> entry: pumps.entrySet()) {
			if(entry.getValue() == value) {
				return entry.getKey();
			}
		}
		for(Map.Entry<String, Source> entry: sources.entrySet()) {
			if(entry.getValue() == value) {
				return entry.getKey();
			}
		}
		for(Map.Entry<String, Cistern> entry: cisterns.entrySet()) {
			if(entry.getValue() == value) {
				return entry.getKey();
			}
		}
		return null;
	}
	
	/**
	 * A set-s parancsot valósítja meg, ráállítja az adott szabotőrt a mezőre.
	 * @param command a parancsban megadott értékek tömbje (parancs nélkül)
	 */
	public static void setS(String[] command) {
		if(command.length < 2) {
			System.out.println("Hibas parancs.");
			return;
		}
		String saboteurKey = "Saboteur_" + command[0];
		Saboteur saboteur = saboteurs.get(saboteurKey);
		if(saboteur == null) {
			System.out.println("Hibas parancs.");	// nincs ennyi szabotőr vagy rosszul van megadva
		}
		Field f = getValueFromFieldMaps(command[1]);
		if(f != null) {
			saboteur.setCurrentField(f);
			System.out.println("Beallitva.");
		} else {
			System.out.println("Hibas parancs.");	// nincs ilyen mező
		}
	}
	
	/**
	 * Az action sticky parancsot valósítja meg, az aktív karakter ragadóssá teszi a csövet, amin áll.
	 */
	public static void actionSticky() {
		Pipe currentPipe = null;
		for(Map.Entry<String, Pipe> entry: pipes.entrySet()) {
			if(entry.getValue() == game.getActiveCharacter().getField()) {
				currentPipe = entry.getValue();
			}
		}
		if(currentPipe == null) {
			System.out.println("Karakter nem ilyen tipusu mezon all.");
			return;
		}
		if(currentPipe.getState() != StateOfPipe.NORMAL) {
			System.out.println("Akció vége, nincs változás.");
		} else {
			System.out.println("Sikeres parancs.");
		}
		game.getActiveCharacter().turnPipeSticky();
	}
	
	/**
	 * Az action placePump parancsot valósítja meg, az aktív szerelő lerakja a pumpáját a csőre, amin áll.
	 */
	public static void actionPlacePump() {
		Plumber active = null;
		Character current = game.getActiveCharacter();
		for(Map.Entry<String, Plumber> entry: plumbers.entrySet()) {
			if(entry.getValue() == current) {
				active = entry.getValue();
				break;
			}
		}
		if(active != null) {
			Pipe currentPipe = null;
			for(Map.Entry<String, Pipe> entry: pipes.entrySet()) {
				if(entry.getValue() == active.getField()) {
					currentPipe = entry.getValue();
				}
			}
			if(currentPipe == null) {
				System.out.println("Karakter nem ilyen tipusu mezon all.");
				return;
			}
			if(active.getInventoryPump() == null || currentPipe.getBroken()) {
				System.out.println("Akcio vege, nincs valtozas.");
			} else {
				System.out.println("Sikeres parancs.");
			}
			active.placePump();
		} else {
			System.out.println("Ehhez a parancshoz nincs hozzaferese.");
		}
	}
	
	/**
	 * A get neighbours parancsot valósítja meg, az aktív karakter vagy a megadott Field szomszédait írja ki ábécé szerint rendezve.
	 * @param field a lekérdezendő Field azonosítója, ha null, akkor az aktív karakter currentField-je
	 */
	public static void getNeighbours(String field) {
		if(field == null) {
			field = getKeyFromFieldMaps(game.getActiveCharacter().getField());
		}
		ArrayList<? extends Field> ns;
		Map<String, Field> neighbours = new TreeMap<String, Field>();
		if(pipes.containsKey(field)) {
			ns = pipes.get(field).getNeighbours();
			for(Field n: ns) {
				String s = getKeyFromFieldMaps(n);
				if(s != null) neighbours.put(s, n);
			}
		} else if(pumps.containsKey(field)) {
			ns = pumps.get(field).getNeighbours();
			for(Field n: ns) {
				String s = getKeyFromFieldMaps(n);
				if(s != null) neighbours.put(s, n);
			}
		} else if(sources.containsKey(field)) {
			ns = sources.get(field).getNeighbours();
			for(Field n: ns) {
				String s = getKeyFromFieldMaps(n);
				if(s != null) neighbours.put(s, n);
			}
		} else if(cisterns.containsKey(field)) {
			ns = cisterns.get(field).getNeighbours();
			for(Field n: ns) {
				String s = getKeyFromFieldMaps(n);
				if(s != null) neighbours.put(s, n);
			}
		} else {
			System.out.println("Hibas parancs.");		// nincs ilyen azonosítójú mező
		}

		for(Map.Entry<String, Field> entry : neighbours.entrySet()) {
            System.out.print(entry.getKey() + " ");
		}
		System.out.println();
	}
	
	/**
	 * A get slippery parancsot valósítja meg, a megadott cső csúszóssági időzítőjét írja ki.
	 * @param pipeNum a cső sorszáma string-ként
	 */
	public static void getSlippery(String pipeNum) {
		String pipe = "pipe_"+ pipeNum;
		if(pipes.containsKey(pipe)) {
			Pipe p = pipes.get(pipe);
			if(p.getState() == StateOfPipe.SLIPPERY) {
				System.out.println(p.getStateTimer());
			} else {
				System.out.println("0");
			}
		} else {
			System.out.println("Hibas parancs.");	// nem létező cső
		}
	}
	
	/**
	 * Az action pass parancsot valósítja meg, az aktív játékost a soronkövetkezőre váltja.
	 */
	public static void actionPass() {
		game.nextCharacter();
		System.out.println("Sikeres parancs.");
		Character nextCharacter = game.getActiveCharacter();
		String nextChar = null;
		for(Map.Entry<String, Plumber> entry: plumbers.entrySet()) {
			if(entry.getValue() == nextCharacter) {
				nextChar = entry.getKey();
			}
		}
		for(Map.Entry<String, Saboteur> entry: saboteurs.entrySet()) {
			if(entry.getValue() == nextCharacter) {
				nextChar = entry.getKey();
			}
		}
		System.out.println(nextChar);
	}
	
	/**
	 * A get connections parancsot valósítja meg, a megadott pumpa ki- és bemenetét írja ki.
	 * @param pumpNum a pumpa sorszáma string-ként
	 */
	public static void getConnections(String pumpNum) {
		String pumpKey = "pump_"+ pumpNum;
		Pump pump = pumps.get(pumpKey);
		if(pump == null) {
			System.out.println("Hibas parancs.");		// pumpa sorszáma rosszul van megadva
			return;
		}
		String out = null, in = null;
		for(Map.Entry<String, Pipe> entry: pipes.entrySet()) {
			if(entry.getValue() == pump.getOut()) {
				out = entry.getKey();
			}
			if(entry.getValue() == pump.getIn()) {
				in = entry.getKey();
			}
		}
		System.out.println(out + " " + in);
	}
	
	public static void setP(String[] Command){
		if(plumbers.containsKey(Command[1])) {
			if(pipes.containsKey(Command[2])) {
				Plumber pl_tmp = plumbers.get(Command[1]);
				pl_tmp.setCurrentField(pipes.get(Command[2]));
			}
			else if(pumps.containsKey(Command[2])) {
				Plumber pl_tmp = plumbers.get(Command[1]);
				pl_tmp.setCurrentField(pumps.get(Command[2]));
			}
			else if(sources.containsKey(Command[2])) {
				Plumber pl_tmp = plumbers.get(Command[1]);
				pl_tmp.setCurrentField(sources.get(Command[2]));
			}
			else if(cisterns.containsKey(Command[2])) {
				Plumber pl_tmp = plumbers.get(Command[1]);
				pl_tmp.setCurrentField(cisterns.get(Command[2]));
			}
			else
				System.out.println("Nincs ilyen mező a játékban.");
		}
		else
			System.out.println("Nincs ilyen szerelő a játékban.");
	}
	
	public static void puncture(String[] Command){
		if(saboteurs.containsKey(Command[1])) {
			if(pipes.containsKey(Command[2])) {
				saboteurs.get(Command[1]).puncturePipe();
			}
			else 
				System.out.println("Nem csövön áll a játékos.");
		}
		else if(plumbers.containsKey(Command[1])) {
			if(pipes.containsKey(Command[2])) {
				plumbers.get(Command[1]).puncturePipe();
			}
			else 
				System.out.println("Nem csövön áll a játékos.");
		}
		else
			System.out.println("Nincs ilyen karakter a játékban.");
	}
	
	public static void placePipe(String[] Command){
		if(plumbers.containsKey(game.getActiveCharacter())) {
			Plumber tmp = plumbers.get(game.getActiveCharacter());
			tmp.placePipe();
		}
	}
	
	public static void move(String[] Command){
		if(pipes.containsKey(Command[1])) {
			game.getActiveCharacter().move(pipes.get(Command[1]));
		}
		else if(pumps.containsKey(Command[1])) {
			game.getActiveCharacter().move(pumps.get(Command[1]));
		}
		else if(sources.containsKey(Command[1])) {
			game.getActiveCharacter().move(sources.get(Command[1]));
		}
		else if(cisterns.containsKey(Command[1])) {
			game.getActiveCharacter().move(cisterns.get(Command[1]));
		}
		else
			System.out.println("Nincs ilyen mező a játékban, amire lépni szeretne.");
	}
	
	public static void getSticky(String[] Command){
		if(pipes.containsKey(Command[1])) {
			if(pipes.get(Command[1]).getState() == StateOfPipe.STICKY) {
				System.out.println("Ennyi ideig ragadós a cső: " + pipes.get(Command[1]).getStateTimer());
			}
			System.out.println("Nem ragadós a cső.");
		}
	}
	
	public static void getTeamPoints(String[] Command){
		if(Command[1] == "-s") {
			System.out.print("A szerelők pontszáma: " + game.getPointsOfPlumber());
		}
		else if(Command[1] == "-p") {
			System.out.print("A szabotőrök pontszáma: " + game.getPointsOfSaboteur());
		}
		else
			System.out.println("Hibás bemenet!");
	}
	
	public static void flow(String[] Command){
		if(pipes.containsKey(Command[1])) {
			pipes.get(Command[1]).flowWater();
		}
		else if(pumps.containsKey(Command[1])) {
			pumps.get(Command[1]).flowWater();
		}
		else if(sources.containsKey(Command[1])) {
			sources.get(Command[1]).flowWater();
		}
		else if(cisterns.containsKey(Command[1])) {
			cisterns.get(Command[1]).flowWater();
		}
		else
			System.out.println("Nincs ilyen mező a játékban.");
	}

	public static void setPump(String[] command) {
		if(command[2]==null && command[3]==null) {
			System.out.println("Hibas parancs");
			return;
		}
		String opt = command[2];
		String param = command[3];

		boolean failed = false;
		Pump p = new Pump();
		Pipe pipe = new Pipe();

		if(pumps.containsKey("pump_"+command[1])) {
			p = pumps.get("pump_"+command[1]);
		} else { failed = true;	}

		if(opt.equals("-i")) { // pumpa bemenete
			if(!pipes.containsKey(param)) { failed = true; }
			else {
				pipe = pipes.get(param);
				p.setIn(pipe);
			}
		}

		if(opt.equals("-o")) { // pumpa kimenete
			if(!pipes.containsKey(param)) { failed = true; }
			else {
				pipe = pipes.get(param);
				p.setOut(pipe);
			}
		}

		if(opt.equals("-inv")) { // szerelonel van-e, szerelo azonosítója !
			if(!plumbers.containsKey(param)) { failed = true; }
			else {
				Plumber plumber = plumbers.get(param);

				plumber.setInventoryPump(p);
			}
		}

		if(opt.equals("-b")) { // pumpa eltörve-e
			if(param.equals("true") || param.equals("True")) {
				p.setBroken(true);
			}
			if(param.equals("false") || param.equals("False")) {
				p.setBroken(false);
			}
			else { failed = true; }
		}

		if(failed) {
			System.out.print("Hibas parancs.");
		}

	}

	public static void setGame(String[] command) {

		if(command[2]==null && command[3]==null || Integer.parseInt(command[3]) < 0) {
			System.out.println("Hibas parancs.");
			return;
		}

		int input = Integer.parseInt(command[3]);
		if(command[2].equals("-r")) { game.setRound(input); }
		if(command[2].equals("-s")) { game.setPointsOfSaboteur(input); }
		if(command[2].equals("-p")) { game.setPointsOfPlumber(input); }

		else System.out.println("Hibas parancs.");
	}

	public static void actionSlippery(String[] command) {
		boolean isPipe = false, isSaboteur = false;
		Pipe temp_p = new Pipe();
		String key ="";

		for(Map.Entry<String, Pipe> pipe : pipes.entrySet()) {
			if(pipe.getValue().equals(game.getActiveCharacter().getField())) {
				if(pipe.getValue().getState()!=StateOfPipe.NORMAL) {
					System.out.println("Akció vége, nincs változás. \n"+ game.getActionPoints());
					return;
				}
				isPipe = true;
				temp_p = pipe.getValue();
			}
		}

		if(!isPipe) {
			System.out.println("Karakter nem ilyen típusú mezőn áll.");
			return;
		}

		for (Map.Entry<String, Saboteur> s : saboteurs.entrySet()) {
			if (s.getValue().equals(game.getActiveCharacter())) {
				isSaboteur = true;
				key = s.getKey();
			}
		}

		if(!isSaboteur) {
			System.out.println("Ehhez a parancshoz nincs hozzáférése.");
			return;
		}

		Saboteur s = saboteurs.get(key);
		s.turnPipeSlippery();
		System.out.println("Sikeres parancs");
		System.out.println(game.getActionPoints());

	}
	public static void grabPump(String[] command) {
		boolean standOnCistern = false;
		boolean activeIsPlumber = false;
		String cistern_key ="";
		String plumber_key="";

		for (Map.Entry<String, Cistern> cistern : cisterns.entrySet()) {
			if (cistern.getValue().equals(game.getActiveCharacter().getField())) {
				cistern_key= cistern.getKey();
				standOnCistern = true;
			}
		}

		if(!standOnCistern) {
			System.out.println("Karakter nem ilyen típusú mezőn áll.");
			return;
		}

		// Ha az aktive karakter egy plumber akkor tudja meg
		for (Map.Entry<String, Plumber> plumber : plumbers.entrySet()) {
			if (plumber.getValue().equals(game.getActiveCharacter())) {
				activeIsPlumber = true;
				plumber_key = plumber.getKey();
			}
		}

		if(!activeIsPlumber) {
			System.out.println("Ehhez a parancshoz nincs hozzáférése.");
			return;
		}

		if(standOnCistern && activeIsPlumber) {
			Plumber temp_plumber = plumbers.get(plumber_key);
			if(cisterns.get(cistern_key).getHasPump() && temp_plumber.getInventoryPump() == null) {
				game.getActiveCharacter().getField().interactPlumber(temp_plumber, new Pump());
				System.out.println("Sikeres parancs");
				System.out.println(game.getActionPoints());
			}
		}
		else {
			System.out.println("Hibás parancs");
			return;
		}
	}

	public static void getWater(String[] command) {
		String pipe = "pipe_";
		pipe = pipe.concat(command[2]);

		if(command[2]==null || Integer.parseInt(command[2]) > pipes.size()-1 || !pipes.containsKey(pipe)) {
			System.out.println("Hibas parancs");
			return;
		}

		System.out.println(pipes.get(pipe).getWater());
	}

	public static void getHasPump(String[] command){
		String c = "cistern_";
		c.concat(command[2]);

		if(command[2]==null || !cisterns.containsKey(c)) {
			System.out.println("Hibas parancs");
			return;
		}

		if(cisterns.containsKey(c)){
			System.out.println(cisterns.get(c).getHasPump());
		}
	}

	public static void pumpWater(String[] command) {
		String p = "cistern_";
		p.concat(command[2]);

		if(command[2]==null || !pumps.containsKey(p)) {
			System.out.println("Hibas parancs");
			return;
		}

		if(pumps.containsKey(p)){
			pumps.get(p).flowWater();
		}
	}
	
	public static void setRandom(String[] command) {
		if(command[1] == "true") {
			network.setRandom(true);
		}
		else if(command[1] == "false") {
			network.setRandom(false);
		}
		else
			System.out.println("Hibás bemenet!");
	}
}
