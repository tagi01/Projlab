package skeletonPackage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Program {

	/** A felhasznalo inputjainak beolvasasahoz */
	private static Scanner input;
	
	/** A fájl inputjainak beolvasasahoz */
	private static Scanner fileInput;
	
	private static Game game;

	private static Network network = new Network();
	/** Az objektumok neveit tartalmazo map */

	private static Map<String , Pipe> pipes = new HashMap<String , Pipe>();
	
	private static Map<String , Pump> pumps = new HashMap<String , Pump>();
	
	private static Map<String , Source> sources = new HashMap<String , Source>();
	
	private static Map<String , Cistern> cisterns = new HashMap<String , Cistern>();
	
	private static Map<String , Saboteur> saboteurs = new HashMap<String , Saboteur>();
	
	private static Map<String , Plumber> plumbers = new HashMap<String , Plumber>();
	
	public static Map<String , Pipe> getPipes() { return pipes; }
	
	public static Map<String , Pump> getPumps() { return pumps; }
	
	public static Map<String , Source> getSources() { return sources; }
	
	public static Map<String, Cistern> getCisterns() { return cisterns; }
	
	public static Map<String, Saboteur> getSaboteurs() { return saboteurs; }
	
	public static Map<String, Plumber> getPlumbers() { return plumbers; }


	/** Tárolja hogy elkezdődött-e már a játék */
	private static boolean started = false;

	/** Main metódus
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
					puncture();
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
					actionRepair();
					break;
				case "placePipe":
					placePipe();
					break;
				case "placePump":
					actionPlacePump();
					break;
				case "grabPipe":
					String c = splitted.length > 1 ? splitted[1] : null;
					actionGrabPipe(c);
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
				System.out.println(game.getActionPoints());
			} else {
				System.out.println("Meg nem kezdodott el a jatek");
			}
			break;
		case "get":
			splitted = Arrays.copyOfRange(splitted, 1, splitted.length);
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
			if (first[0].contentEquals("set") && first.length > 1) {
				splitted[0] = first[1];
				if (!started) {
					//splitted = Arrays.copyOfRange(splitted, 1, splitted.length);/
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
		if (pipes.containsKey("pipe_" + command[1])) {
			Pipe temp = pipes.get("pipe_"+command[1]);
			if (command[2].equals("-n")) {
				if (command[3].equals("[") && command[6].equals("]")) {

					if(getValueFromFieldMaps(command[4])==null && !plumbers.containsKey(command[4])) {
						System.out.println("Hibas parancs.");
						return;
					}

					if (plumbers.containsKey(command[4])) {
						Plumber temp_p = plumbers.get(command[4]);
						temp_p.setInventoryPipe(temp);
					}
					else {
						temp.addNeighbour(getValueFromFieldMaps(command[4]));
						getValueFromFieldMaps(command[4]).addNeighbour(temp);
					}

					if(getValueFromFieldMaps(command[5])==null && !plumbers.containsKey(command[5])) {
						System.out.println("Hibas parancs.");
						return;
					}

					if (plumbers.containsKey(command[5])) {
						Plumber temp_p = plumbers.get(command[5]);
						temp_p.setInventoryPipe(temp);
					} else {
						temp.addNeighbour(getValueFromFieldMaps(command[5]));
						getValueFromFieldMaps(command[5]).addNeighbour(temp);
					}
					// tovabbi parancsok kiertekelese
					for (int i = 7; i < command.length; i += 2) {
						switch(command[i]) {
							case "-s":  temp.setSize(Integer.parseInt(command[i + 1])); break; // cso meretet allithatod be
							case "-l": temp.setLostWater(Integer.parseInt(command[i + 1])); break; // csobol mennyi viz folyt ki
							case "-w": temp.setWater(Integer.parseInt(command[i + 1])); break; // csoben mennyi viz van
							case "-p": temp.setCantPuncture(Integer.parseInt(command[i + 1])); break; // cso mennyi ideig nem lyukaszthato meg ki
							case "-b":  if (command[i + 1].equals("true")) { temp.setBroken(true); } break; // cso ki van e lyukasztva vagy nincs
							case "-st": if(command[i + 1].equals("true")) { temp.setState(StateOfPipe.STICKY); } break; // ragadosra allitja, ha true az ertek
							case "-sl": if(command[i+1].equals("true")) { temp.setState(StateOfPipe.SLIPPERY); } break; // csusszossa allitja, ha true az ertek
						}
					}
					System.out.println("Beallitva.");
				} else {
					System.out.println("Hibas parancs.");
					return;
				}
			} else {
				System.out.println("Hibas parancs.");
				return;
			}
		} else {
			System.out.println("Hibas parancs.");
			return;
		}
	}

	public static void setActive(String[] command) {
		if (plumbers.containsKey(command[1])) {
			game.setActiveCharacter(plumbers.get(command[1]));
			if (command.length == 4) {
				if (command[2].equals("-p")) {
					game.setActionPoint(Integer.parseInt(command[3]));
				}
			} else {
				game.setActionPoint(5);
			}
			System.out.println("Beallitva");

		} else if (saboteurs.containsKey(command[1])) {
			game.setActiveCharacter(saboteurs.get(command[1]));
			if (command.length == 4) {
				if (command[2].equals("-p")) {
					game.setActionPoint(Integer.parseInt(command[3]));
				}
			} else {
				game.setActionPoint(5);
			}
			System.out.println("Beallitva");
		} else
			System.out.println("Hibas parancs.");
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
		if (pipes.containsKey("pipe_" + command[1])) {
			System.out.println("Az hatralevo ido: " + pipes.get(command[1]).getCantPuncture());
		} else
			System.out.println("Nincsen ilyen pipe: -1");
	}

	public static void getHasPipe(String[] command) {
		if (cisterns.containsKey("cistern_" + command[1])) {
			if (cisterns.get("cistern_" + command[1]).getHasPipe()) {
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
					for (Map.Entry<String, Pipe> set : pipes.entrySet()) {
						if (set.getValue().equals(plumbers.get(command[1]).getInventoryPipe())) {
							System.out.print("[" + set.getKey() + "] [" + set.getKey() + "]");
						}
					}
				}
				// ha nem ket veg van nal, akkor csak egyszer irja ki
				else if (plumbers.get(command[1]).getPipeEnds() == 1) {
					for (Map.Entry<String, Pipe> set : pipes.entrySet()) {
						if (set.getValue().equals(plumbers.get(command[1]).getInventoryPipe())) {
							System.out.print("[" + set.getKey() + "]" + "[]");
						}
					}	
				}
			}
			else
				System.out.print("[][]");
			
			// ha van nala pumpa
			if (plumbers.get(command[1]).getInventoryPump() != null) {
				for (Map.Entry<String, Pump> set : pumps.entrySet()) {
					if (set.getValue().equals(plumbers.get(command[1]).getInventoryPump())) {
						System.out.print("[" + set.getKey() + "]");
					}
				}
			}
			else
				System.out.print("[]");
			System.out.println();
		} else
			System.out.println("Nincs ilyen karkter");
	}

	/**
	 * setCistern parancsot valósítja meg
	 * @param command a parancs szavai
	 */
	public static void setCistern(String[] command) {
		if (command.length < 4) {
			System.out.println("Hibas parancs.");
			return;
		}
		String cistern = "cistern_";
		cistern = cistern.concat(command[1]);
		if (cisterns.containsKey(cistern)) {
			for (int i = 2; i < command.length; i += 2) {
				switch (command[i]) {
				case "-pi":
					if (command[i + 1].equals("true")) {
						cisterns.get(cistern).setHasPipe(true);
						//System.out.println("Beallitva.");
					} else if (command[i + 1].equals("false")) {
						cisterns.get(cistern).setHasPipe(true);
						//System.out.println("Beallitva.");
					} else {
						System.out.println("Hibas parancs.");
						return;
					}
					break;
				case "-pu":
					if (command[i + 1].equals("true")) {
						cisterns.get(cistern).setHasPump(true);
						//System.out.println("Beallitva.");
					} else if (command[i + 1].equals("false")) {
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
	 */
	public static void actionRepair() {
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
		if (command.length < 2) {
			System.out.println("Hibas parancs.");
			return;
		}
		if (plumbers.containsKey(command[1])) {
			String name = getKeyFromFieldMaps(plumbers.get(command[1]).getField());
			System.out.println(name);
		} else if (saboteurs.containsKey(command[1])) {
			String name = getKeyFromFieldMaps(saboteurs.get(command[1]).getField());
			System.out.println(name);
		} else {
			System.out.println("Hibas parancs.");
			return;
		}
	}
	
	/**
	 * getState parancsot valósítja meg
	 * @param command a parancs szavai
	 */
	public static void getState(String[] command) {
		if (command.length < 2) {
			System.out.println("Hibas parancs.");
			return;
		}
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
		if (command.length < 2) {
			System.out.println("Hibas parancs.");
			return;
		}
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
		if (command.length < 2) {
			System.out.println("Hibas parancs.");
			return;
		}
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
		if (command.length < 3) {
			System.out.println("Hibas parancs.");
			return;
		}
		String file = command[1];
		file = file.concat("/");
		file = file.concat(command[2]);
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
		
		game = Game.getInstance();
		network = new Network();
		game.setNetwork(network);
		pipes.clear();
		pumps.clear();
		sources.clear();
		cisterns.clear();
		saboteurs.clear();
		plumbers.clear();
		started = false;
		
		for(int i = 0; i < pipeNum; i++) {
			Pipe p = new Pipe();
			p.setGame(game);
			p.setNetwork(network);
			pipes.put("pipe_" + (i+1), p);
			network.addField(p);
		}
		for(int i = 0; i < pumpNum; i++) {
			Pump p = new Pump();
			p.setGame(game);
			p.setNetwork(network);
			pumps.put("pump_" + (i+1), p);
			network.addField(p);
		}
		for(int i = 0; i < sourceNum; i++) {
			Source s = new Source();
			s.setGame(game);
			s.setNetwork(network);
			sources.put("source_" + (i+1), s);
			network.addField(s);
		}
		for(int i = 0; i < cisternNum; i++) {
			Cistern c = new Cistern();
			c.setGame(game);
			c.setNetwork(network);
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
			return;
		}
		Field f = getValueFromFieldMaps(command[1]);
		if(f != null) {
			if(f.acceptCharacter()) {
				saboteur.setCurrentField(f);
				f.setCurrentCharacters(saboteur);
				System.out.println("Beallitva.");
			} else {
				System.out.println("Hibas parancs.");	// nincs hely a mezőn
			}
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
		
		int actionPoint = game.getActionPoints();
		game.getActiveCharacter().turnPipeSticky();

		if (actionPoint != game.getActionPoints()) {
			System.out.println("Sikeres parancs.");
		} else {
			System.out.println("Akcio vege, nincs valtozas.");
		}
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
			
			int actionPoint = game.getActionPoints();
			active.placePump();
			if (actionPoint != game.getActionPoints()) {
				System.out.println("Sikeres parancs.");
			} else {
				System.out.println("Akcio vege, nincs valtozas.");
			}
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
			return;
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
	
	/**
	 * Az action grabPipe parancsot valósítja meg, az aktív szerelő felveszi egy cső egyik végét.
	 * @param pipeNum a felvevendő cső sorszáma string-ként, ha null, ciszternáról veszi fel az új cső végét
	 */
	public static void actionGrabPipe(String pipeNum) {
		Plumber active = null;
		for(Map.Entry<String, Plumber> entry: plumbers.entrySet()) {
			if(entry.getValue() == game.getActiveCharacter()) {
				active = entry.getValue();
			}
		}
		if(active == null) {
			System.out.println("Ehhez a parancshoz nincs hozzaferese.");
			return;
		}
		
		if(pipeNum == null) {
			Cistern currentField = null;
			for(Map.Entry<String, Cistern> entry: cisterns.entrySet()) {
				if(entry.getValue() == game.getActiveCharacter().getField()) {
					currentField = entry.getValue();
				}
			}
			if(currentField == null) {
				System.out.println("Karakter nem ilyen tipusu mezon all.");
				return;
			}
			
			if(active.getInventoryPump() != null || active.getInventoryPipe() != null ||  currentField.getHasPipe() == false) {
				System.out.println("Akcio vege, nincs valtozas.");
			} else {
				System.out.println("Sikeres parancs.");
			}
			active.getPipe();
		}
		
		else {
			Field currentField = game.getActiveCharacter().getField();
			if(pipes.containsValue(currentField)) {
				System.out.println("Karakter nem ilyen tipusu mezon all.");
				return;
			}
			if(!pipes.containsKey("pipe_" + pipeNum)) {
				System.out.println("Hibas parancs.");		// cső sorszáma rosszul van megadva
				return;
			}
			
			Pipe pipe = pipes.get("pipe_" + pipeNum);		
			ArrayList<? extends Field> pumpNeighbours = currentField.getNeighbours();
			if(!pumpNeighbours.contains(pipe)) {
				System.out.println("Hibas parancs.");		// a cső nem szomszédja a mezőnek
				return;
			}
			/*if(active.getInventoryPump() != null || active.getInventoryPipe() != null || !currentField.removeNeighbour(pipe)) {
				System.out.println("Akcio vege, nincs valtozas.");
			} else {
				currentField.addNeighbour(pipe);				// TODO? az if-ben lévő removeNeighbour() miatt... -_- 
				System.out.println("Sikeres parancs.");
			}*/
			int actionPoint = game.getActionPoints();
			active.grabPipe(pipe);
			if (actionPoint != game.getActionPoints()) {
				System.out.println("Sikeres parancs.");
			} else {
				System.out.println("Akcio vege, nincs valtozas.");
			}
		}
	}
	
	/**
	 * A set-p parancsot valósítja meg, a megadott szerelőt beállitja a megadott mezőre
	 * @param A parancs szavai
	 */
	public static void setP(String[] command){
		if(command.length < 3) {
			System.out.println("Hibas parancs.");
			return;
		}
		String plumberKey = "Plumber_" + command[1];
		Plumber plumber = plumbers.get(plumberKey);
		if(plumber == null) {
			System.out.println("Hibas parancs.");	// nincs ennyi szabotőr vagy rosszul van megadva
			return;
		}
		Field f = getValueFromFieldMaps(command[2]);
		if(f != null) {
			if(f.acceptCharacter()) {
				plumber.setCurrentField(f);
				f.setCurrentCharacters(plumber);
				System.out.println("Beallitva.");
			} else {
				System.out.println("Hibas parancs.");	// nincs hely a mezőn
			}
		} else {
			System.out.println("Hibas parancs.");	// nincs ilyen mező
		}
	}
	
	/**
	 * A puncture parancsot valósítja meg, kilyukaszta a csövet, amin áll éppen az aktiv karakter
	 * @param A parancs szavai
	 */
	public static void puncture(){
		Plumber currentPlumber = null;
		Saboteur currentSaboteur = null;
		for (Plumber p : plumbers.values()) {
			if (game.getActiveCharacter() == p)
				currentPlumber = p;
		}
		for (Saboteur p : saboteurs.values()) {
			if (game.getActiveCharacter() == p)
				currentSaboteur = p;
		}
		if (currentPlumber != null) {
			if (pipes.containsValue(game.getActiveCharacter().getField())) {
				int actionPoint = game.getActionPoints();
				currentPlumber.puncturePipe();
				if (actionPoint != game.getActionPoints()) {
					System.out.println("Sikeres parancs.");
				}
				else {
					System.out.println("Akcio vege, nincs valtozas.");
				}
			}
			else {
				System.out.println("Karakter nem ilyen tipusu mezon all.");
			}
		}
		else if (currentSaboteur != null) {
			if (pipes.containsValue(game.getActiveCharacter().getField())) {
				int actionPoint = game.getActionPoints();
				currentSaboteur.puncturePipe();
				if (actionPoint != game.getActionPoints()) {
					System.out.println("Sikeres parancs.");
				}
				else {
					System.out.println("Akcio vege, nincs valtozas.");
				}
			}
			else {
				System.out.println("Karakter nem ilyen tipusu mezon all.");
			}
		}
		else {
			System.out.println("Ehhez a parancshoz nincs hozzaferese.");
		}
	}
	
	/**
	 * Az action placePipe parancsot valósítja meg, az aktiv szerelő lrak egy csövet
	 */
	public static void placePipe(){
		Plumber currentPlumber = null;
		for (Plumber p : plumbers.values()) {
			if (game.getActiveCharacter() == p)
				currentPlumber = p;
		}
		if (currentPlumber != null) {
			if (pumps.containsValue(game.getActiveCharacter().getField()) || sources.containsValue(game.getActiveCharacter().getField()) || cisterns.containsValue(game.getActiveCharacter().getField())) {
				int actionPoint = game.getActionPoints();
				currentPlumber.placePipe();
				if (actionPoint != game.getActionPoints()) {
					System.out.println("Sikeres parancs.");
				}
				else {
					System.out.println("Akcio vege, nincs valtozas.");
				}
			}
			else {
				System.out.println("Karakter nem ilyen tipusu mezon all.");
			}
		}
		else {
			System.out.println("Ehhez a parancshoz nincs hozzaferese.");
		}
	}
	
	/**
	 * Az action move parancsot valósítja meg, a játékos átlép egy általa választott mezőre
	 * @param A parancs szavai
	 */
	public static void move(String[] command){
		try {
			Field tmp = getValueFromFieldMaps(command[1]);
			if (tmp == null) {
				System.out.println("Hibas parancs.");
			}

			int actionPoint = game.getActionPoints();
			game.getActiveCharacter().move(tmp);
			if (actionPoint != game.getActionPoints()) {
				System.out.println("Sikeres parancs.");
			} else {
				System.out.println("Akcio vege, nincs valtozas.");
			}
		} catch (Exception e) {
			System.out.println("Hibas parancs.");
		}
	}
	
	/**
	 * A get stiky parancsot valósítja meg, megadja, hogy az adott cső meddig ragadós.
	 * @param A parancs szavai
	 */
	public static void getSticky(String[] Command){
		try {
			if (pipes.containsKey("pipe_" + Command[1])) {
				if (pipes.get("pipe_" + Command[1]).getState() == StateOfPipe.STICKY) {
					System.out.println(pipes.get("pipe_" + Command[1]).getStateTimer());
				}
				System.out.println("0");
			}
			else
				System.out.println("Hibas parancs.");
		}
		catch(Exception e) { System.out.println("Hibas parancs."); }
	}
	
	/**
	 * A get teamPoints parancsot valósítja meg, megadja, hogy az adott csapatnak mennyi pontja van éppen.
	 * @param A parancs szavai
	 */
	public static void getTeamPoints(String[] Command){
		if(Command[1] == "-s") {
			System.out.print(game.getPointsOfPlumber());
		}
		else if(Command[1] == "-p") {
			System.out.print(game.getPointsOfSaboteur());
		}
		else
			System.out.println("Hibas parancs!");
	}
	
	/**
	 * A flowWater parancsot valósítja meg, a megadott mezőben folyatja a vizet.
	 * @param A parancs szavai
	 */
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
			System.out.println("Hibas parancs!");
	}

	public static void setPump(String[] command) {

		Pump p = new Pump();

		try {
			if (command.length%2==0 && command.length>=3 && command.length <= 9) { // ha jo hosszusagu a command

				if(!pumps.containsKey("pump_"+command[1])) {
					throw new InvalidParameterException();
				}
				p = pumps.get("pump_"+command[1]);

				int x =2; // elso opcio helye
				for(int i=0; i<4;i++){
					switch(command[x]) {
						case "-i": // pumpa bemenete
							if(!pipes.containsKey(command[x+1])) { throw new InvalidParameterException(); }
							p.setIn(pipes.get(command[x+1]));
							p.addNeighbour(pipes.get(command[x+1]));
							pipes.get(command[x+1]).addNeighbour(p);
							break;
						case "-o": // pumpa kimenete
							if(!pipes.containsKey(command[x+1])) { throw new InvalidParameterException(); }
							p.setOut(pipes.get(command[x+1]));
							p.addNeighbour(pipes.get(command[x+1]));
							pipes.get(command[x+1]).addNeighbour(p);
							break;
						case "-inv": // szerelonel van-e
							if(!plumbers.containsKey(command[x+1])) { throw new InvalidParameterException(); }
							p.setIn(null); p.setOut(null);
							plumbers.get(command[x+1]).setInventoryPump(p);
							break;
						case "-b": // elvan-e torve
							if(command[x+1].equals("false")) { p.setBroken(false); }
							if(command[x+1].equals("true")) { p.setBroken(true); }
							else { throw new InvalidParameterException(); }
							break;
						default:
							continue;
					}
					x+=2;

					if(command.length==x) {
						System.out.println("Beallitva.");
						return;
					}
				}
			}
			System.out.println("Hibas parancs.");
		}
		catch (InvalidParameterException e) {
			System.out.println("Hibas parancs.");
		}
		catch (Exception exc) {
			System.out.println("Hiba tortent.");
		}
	}

	public static void setGame(String[] command) {

		for (int i = 1; i < command.length; i += 2) {
			if (command[i] == null && command[i + 1] == null || Integer.parseInt(command[i + 1]) < 0) {
				System.out.println("Hibas parancs.");
				return;
			}

			int input = Integer.parseInt(command[i + 1]);
			if (command[i].equals("-r")) {
				game.setRound(input);
			} else if (command[i].equals("-s")) {
				game.setPointsOfSaboteur(input);
			} else if (command[i].equals("-p")) {
				game.setPointsOfPlumber(input);
			} else {
				System.out.println("Hibas parancs.");
				return;
			}
		}
		System.out.println("Beallitva");
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
				Pump p = temp_plumber.getInventoryPump();
				int i=pumps.size();
				i++;
				pumps.put("pump_"+i, p);
				if(temp_plumber.getInventoryPump().equals(p))
					System.out.println("Sikeres parancs");
			}
		}
		else {
			System.out.println("Hibás parancs");
			return;
		}
	}

	public static void getWater(String[] command) {
		String pipe = "pipe_";
		pipe = pipe.concat(command[1]);

		if(command[1]==null || !pipes.containsKey(pipe)) {
			System.out.println("Hibas parancs");
			return;
		}

		System.out.println(pipes.get(pipe).getWater());
	}

	public static void getHasPump(String[] command){
		String c = "cistern_" + command[1];
		if(command[1]==null || !cisterns.containsKey(c)) {
			System.out.println("Hibas parancs");
			return;
		}

		if(cisterns.containsKey(c)){
			System.out.println(cisterns.get(c).getHasPump());
		}
	}

	public static void pumpWater(String[] command) {
		String p = "pump_";
		p = p.concat(command[1]);

		if(command[1]==null || !pumps.containsKey(p)) {
			System.out.println("Hibas parancs");
			return;
		}

		if(pumps.containsKey(p)){
			pumps.get(p).flowWater();
		}
	}
	
	/**
	 * A set random parancsot valósítja meg, beállitja, hogy véletlenszerűen törjenek el a pumpák, vagy nem.
	 * @param A parancs szavai
	 */
	public static void setRandom(String[] command) {
		if (command.length < 2) {
			System.out.println("Hibas parancs.");
			return;
		}
		if(command[1].equals("true")) {
			network.setRandom(true);
			System.out.println("Beallitva.");
		}
		else if(command[1].equals("false")) {
			network.setRandom(false);
			System.out.println("Beallitva.");
		}
		else
			System.out.println("Hibas parancs!");
	}
}
