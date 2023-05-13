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
	
	private static Map<String , Pump> pumps;
	
	private static Map<String , Source> sources;
	
	private static Map<String , Cistern> cisterns;
	
	private static Map<String , Saboteur> saboteurs;
	
	private static Map<String , Plumber> plumbers;


	/**
	 * tárolja hogy elkezdődött-e már a játék
	 */
	private static boolean started;

	/**
	 * Main metódus
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		pipes.put("pipe2", new Pipe());
		System.out.println("-----------------------------------------------------\n"
				         + "|   ___    ___    ____  _______  ____  \n"
				         + "|  |   |  |   |  |    |    |    |    | \n"
				         + "|  |___|  |___|  |    |    |    |    | \n"
				         + "|  |      |\\    |    |    |    |    | \n"
				         + "|  |      | \\   |____|    |    |____| \n"
				         + "-----------------------------------------------------\n");


		input = new Scanner(System.in);
		String input_temp;

		while (true) {
			input_temp = new String();
			input_temp = input.nextLine();
			readCommand(input_temp);
			/*String[] splitted = input_temp.split("\\s+");
			try {
				switch (splitted[0]) {
				// Játék halozat letehozasa, beallitasa
				case "create-network":
					String[] command = new String[splitted.length-1];
					for(int i = 0; i < command.length; i++) {
						command[i] = splitted[i+1];
					}
					createNetwork(command);					
					break;
				case "set-pipe":
					setPipe(splitted);
					break;
				case "set-pump":

					break;
				case "set-cistern":

					break;
				case "set-p":

					break;
				case "set-s":
					command = new String[splitted.length-1];
					for(int i = 0; i < command.length; i++) {
						command[i] = splitted[i+1];
					}
					setS(command);
					break;
				case "set-active":
					setActive(splitted);
					break;
				case "set-game":

					break;
				// Játékosok akciói
				case "start":

					break;
				case "action-puncture":

					break;
				case "action-sticky":
					actionSticky();
					break;
				case "action-slippery":

					break;
				case "action-repair":

					break;
				case "action-placePipe":

					break;
				case "action-placePump":
					actionPlacePump();
					break;
				case "action-grabPipe":

					break;
				case "action-grabPump":
					grabPump(splitted);
					break;
				case "action-move":

					break;
				case "action-pass":
					actionPass();
					break;
				case "action-setpump":
					actionSetPump(splitted);
					break;
				// Allapot lekerdezesek
				case "get-neighbours":
					getNeighbours(splitted[1]);
					break;
				case "get-hasPipe":
					getHasPipe(splitted);
					break;
				case "get-inventory":
					getInventory(splitted);
					break;
				case "get-actionPoints":
					getActionPoint(splitted);
					break;
				case "get-currentField":

					break;
				case "get-place":

					break;
				case "get-sticky":

					break;
				case "get-slippery":
					getSlippery(splitted[1]);
					break;
				case "get-cantPuncture":
					getCantPuncturePipe(splitted);
					break;
				case "get-isBroken":

					break;
				case "get-teamPoints":

					break;
				case "get-connections":

					break;
				case "get-water":

					break;
				case "get-state":

					break;
				case "get-hasPump":

					break;
				// Jatek mentes es betoltes
				case "save":

					break;
				case "load":

					break;
				case "set-random":

					break;
				// Plusszok meg
				case "pumpWater":

					break;
				case "pumpBreak":

					break;
				case "flowWater":

					break;
				case "exit":
					System.out.println("Proto vege!");
					input.close();
					System.exit(0);
				default:
					System.out.println("Invalid parancs!");
					System.out.print("Hibas parancs.");

				}

			} catch (InputMismatchException e) {
				e.printStackTrace();
			}*/
		}

	}
	
	public static void readCommand(String line) {
		String[] splitted = line.split("\\s+");
		switch(splitted[0]) {
		case "create-network":
			String[] command = new String[splitted.length-2];
			for(int i = 0; i < command.length; i++) {
				command[i] = splitted[i+2];
			}
			createNetwork(command);					
			break;
		case "set":
			if (!started) {
				splitted = Arrays.copyOfRange(splitted, 1, splitted.length);
				switch(splitted[0]) {
				case "pipe":
					setPipe(splitted);
					break;
				case "pump":
					
					break;
				case "p":
					setP(splitted);
					break;
				case "s":
					command = new String[splitted.length-1];
					for(int i = 0; i < command.length; i++) {
						command[i] = splitted[i+1];
					}
					setS(command);
					break;
				case "cistern":
					
					break;
				case "active":
					setActive(splitted);
					break;
				case "game":
					
					break;
				case "random":
					
					break;
				default:
					System.out.println("Hibas parancs");
					break;
				}
			} else {
				System.out.println("Mar elkezdodott a jatek");
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
					
					break;
				case "repair":
					
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
					System.out.println("Hibas parancs");
					break;
				}
			} else {
				System.out.println("Meg nem kezdodott el a jatek");
			}
			break;
		case "get":
			splitted = Arrays.copyOfRange(splitted, 1, splitted.length);
			switch(splitted[0]) {
			case "neighbours":
				getNeighbours(splitted[1]);
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

				break;
			case "place":

				break;
			case "sticky":
				getSticky(splitted);
				break;
			case "slippery":
				getSlippery(splitted[1]);
				break;
			case "cantPuncture":
				getCantPuncturePipe(splitted);
				break;
			case "isBroken":

				break;
			case "teamPoints":

				break;
			case "connections":

				break;
			case "water":

				break;
			case "state":

				break;
			case "hasPump":

				break;
			default:
				System.out.println("Hibas parancs.");
				break;
			}
		case "start":
			started = true;
			System.out.println("Jatek elinditva");
			break;
		case "load":
			
			break;
		case "save":
			
			break;
		case "pumpWater":
			
			break;
		case "pumpBreak":
			
			break;
		case "flowWater":
			
			break;
		case "exit":
			System.out.println("Proto vege!");
			input.close();
			System.exit(0);
		default:
			System.out.println("Hibas parancs.");
			break;
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

	public static void grabPump(String[] command) {
		boolean standOnCistern = false;
		boolean activeIsPlumber = false;
		Cistern temp_cistern = new Cistern();
		// Ha cisternan all, akkor meg tudja hivni ezt a parancsot
		for (Map.Entry<String, Cistern> set : cisterns.entrySet()) {
			if (set.getValue().equals(game.getActiveCharacter().getField())) {
				temp_cistern = set.getValue();
				standOnCistern = true;
			}
		}
		// Ha az aktive karakter egy plumber akkor tudja meg
		for (Map.Entry<String, Plumber> set : plumbers.entrySet()) {
			if (set.getValue().equals(game.getActiveCharacter())) {
				activeIsPlumber = true;
			}
		}
		// Ha minden ertek stimmel akkor kap egy uj pumpat az inventoryba a Plumber
		if (temp_cistern.getHasPump() && game.getActiveCharacter().getInventoryPump() == null && standOnCistern
				&& activeIsPlumber) {
			game.getActiveCharacter().setInventoryPump(new Pump());
			System.out.println("Fel tudta venni a pumpat a szerelo");
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

	public static void setCistern(String[] command) {
		String cistern = "cistern_";
		cistern = cistern.concat(command[1]);
		if (cisterns.containsKey(cistern)) {
			for (int i = 2; i < command.length; i += 2) {
				switch (command[i]) {
				case "-pi":
					if (command[i + 1] == "true") {
						cisterns.get(cistern).setHasPipe(true);
						System.out.println("Beallitva.");
					} else if (command[i + 1] == "false") {
						cisterns.get(cistern).setHasPipe(true);
						System.out.println("Beallitva.");
					} else {
						System.out.println("Hibas parancs.");
					}
					break;
				case "-pu":
					if (command[i + 1] == "true") {
						cisterns.get(cistern).setHasPump(true);
						System.out.println("Beallitva.");
					} else if (command[i + 1] == "false") {
						cisterns.get(cistern).setHasPump(true);
						System.out.println("Beallitva.");
					} else {
						System.out.println("Hibas parancs.");
					}
					break;
				case "-w":
					int water = Integer.parseInt(command[i + 1]);
					if (water >= 0) {
						cisterns.get(cistern).setCollectedWater(water);
						System.out.println("Beallitva.");
					} else
						System.out.println("Hibas parancs.");
					break;
				default:
					System.out.println("Hibas parancs.");
					break;
				}
			}
		} else
			System.out.println("Hibas parancs.");
	}
	
	public static void repair() {
		Plumber currentPlumber = null;
		for (Plumber p : plumbers.values()) {
			if (game.getActiveCharacter().equals(p))
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
	
	public static void getPlace(String[] command) {
		if (plumbers.containsKey(command[1])) {
			for (Map.Entry<String, Pump> set : pumps.entrySet()) {
				if (set.getValue().equals(plumbers.get(command[1]).getField())) {
					System.out.println(set.getKey());
				}
			}
			for (Map.Entry<String, Pipe> set : pipes.entrySet()) {
				if (set.getValue().equals(plumbers.get(command[1]).getField())) {
					System.out.println(set.getKey());
				}
			}
			for (Map.Entry<String, Cistern> set : cisterns.entrySet()) {
				if (set.getValue().equals(plumbers.get(command[1]).getField())) {
					System.out.println(set.getKey());
				}
			}
			for (Map.Entry<String, Source> set : sources.entrySet()) {
				if (set.getValue().equals(plumbers.get(command[1]).getField())) {
					System.out.println(set.getKey());
				}
			}
		} else if (saboteurs.containsKey(command[1])) {
			for (Map.Entry<String, Pump> set : pumps.entrySet()) {
				if (set.getValue().equals(saboteurs.get(command[1]).getField())) {
					System.out.println(set.getKey());
				}
			}
			for (Map.Entry<String, Pipe> set : pipes.entrySet()) {
				if (set.getValue().equals(saboteurs.get(command[1]).getField())) {
					System.out.println(set.getKey());
				}
			}
			for (Map.Entry<String, Cistern> set : cisterns.entrySet()) {
				if (set.getValue().equals(saboteurs.get(command[1]).getField())) {
					System.out.println(set.getKey());
				}
			}
			for (Map.Entry<String, Source> set : sources.entrySet()) {
				if (set.getValue().equals(saboteurs.get(command[1]).getField())) {
					System.out.println(set.getKey());
				}
			}
		} else {
			System.out.println("Hibas parancs.");
		}
	}
	
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
	
	public static void getIsBroken(String[] command) {
		if (pipes.containsKey(command[1])) {
			System.out.println(pipes.get(command [1]).getBroken());
		} else if (pipes.containsKey(command[1])) {
			System.out.println(pipes.get(command [1]).getBroken());
		} else {
			System.out.println("Hibas paramcs.");
		}
	}
	
	public static void pumpBreak(String[] command) {
		String pump = "pump_";
		pump = pump.concat(command[1]);
		if (pumps.containsKey(pump)) {
			pumps.get(pump).breakField();
		}
	}
	
	public static void load(String[] command) {
		String file = command[2];
		file = file.concat("/");
		file = file.concat(command[3]);
		try {
			fileInput = new Scanner(new FileInputStream(file));
			while(fileInput.hasNextLine()) {
				String line = fileInput.nextLine();
				readCommand(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void createNetwork(String[] command) {	// a paraméterben a parancs nincs benne
		if(command.length != 5) {
			System.out.println("Hibas parancs.");
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
			network.addField(p);		// TODO a pumpákat a fieldekhez is adja hozzá
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
	
	private static Field getFieldFromMaps(String field) {
		if(pipes.containsKey(field)) return pipes.get(field);
		if(pumps.containsKey(field)) return pumps.get(field);
		if(sources.containsKey(field)) return sources.get(field);
		if(cisterns.containsKey(field)) return cisterns.get(field);
		return null;
	}
	
	public static void setS(String[] command) {
		String saboteur = "Saboteur_" + command[0];
		Saboteur s = saboteurs.get(saboteur);
		if(s == null) {
			System.out.println("Hibas parancs.");	// nincs ennyi szabotőr vagy rosszul van megadva
		}
		Field f = getFieldFromMaps(command[1]);
		if(f != null) {
			s.setCurrentField(f);
			System.out.println("Beallitva.");
		} else {
			System.out.println("Hibas parancs.");	// nincs ilyen mező
		}
	}
	
	public static void actionSticky() {
		game.getActiveCharacter().turnPipeSticky();
		// TODO ???
		// System.out.println("Sikeres parancs.");
		// System.out.println("Akció vége, nincs változás.");
		// System.out.println("Karakter nem ilyen típusú mezőn áll.");
		// TODO System.out.println(actionPoints);!!
	}
	
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
			active.placePump();
			//TODO println
		} else {
			System.out.println("Ehhez a parancshoz nincs hozzaferese.");
		}
	}
	
	public static void getNeighbours(String field) {
		ArrayList<? extends Field> ns;
		Map<String, Field> neighbours = new TreeMap<String, Field>();
		if(pipes.containsKey(field)) {
			ns = pipes.get(field).getNeighbours();
			for(Field n: ns) {
				for(Map.Entry<String, Pump> entry: pumps.entrySet()) {
					if(entry.getValue() == n) {
						neighbours.put(entry.getKey(), n);
						break;
					}
				}
				for(Map.Entry<String, Source> entry: sources.entrySet()) {
					if(entry.getValue() == n) {
						neighbours.put(entry.getKey(), n);
						break;
					}
				}
				for(Map.Entry<String, Cistern> entry: cisterns.entrySet()) {
					if(entry.getValue() == n) {
						neighbours.put(entry.getKey(), n);
						break;
					}
				}
			}
		} else if(pumps.containsKey(field)) {
			ns = pumps.get(field).getNeighbours();
			for(Field n: ns) {
				for(Map.Entry<String, Pipe> entry: pipes.entrySet()) {		// TODO a többi mezőfajtát is nézze végig?
					if(entry.getValue() == n) {
						neighbours.put(entry.getKey(), n);
						break;
					}
				}
			}
		} else if(sources.containsKey(field)) {
			ns = sources.get(field).getNeighbours();
			for(Field n: ns) {
				for(Map.Entry<String, Pipe> entry: pipes.entrySet()) {
					if(entry.getValue() == n) {
						neighbours.put(entry.getKey(), n);
						break;
					}
				}
			}
		} else if(cisterns.containsKey(field)) {
			ns = cisterns.get(field).getNeighbours();
			for(Field n: ns) {
				for(Map.Entry<String, Pipe> entry: pipes.entrySet()) {
					if(entry.getValue() == n) {
						neighbours.put(entry.getKey(), n);
						break;
					}
				}
			}
		} else {
			System.out.println("Hibas parancs.");		// nincs ilyen azonosítójú mező
		}

		for(Map.Entry<String, Field> entry : neighbours.entrySet()) {
            System.out.print(entry.getKey() + " ");
		}
		System.out.println();
	}
	
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
	
	public static void actionPass() {
		game.nextCharacter();
		System.out.println("Sikeres parancs.");
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
}
