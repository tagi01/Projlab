package skeletonPackage;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Program {

	/**
	 * A felhasznalo inputjainak beolvasasahoz
	 */
	private static Scanner input;
	
	private static Game game;

	private static Network network = new Network();
	/**
	 * az objektumok neveit tartalmazo map
	 */
	private static Map<String, Pipe> pipes;

	private static Map<String, Pump> pumps;

	private static Map<String, Source> sources;

	private static Map<String, Cistern> cisterns;

	private static Map<String, Saboteur> saboteurs;

	private static Map<String, Plumber> plumbers;

	/**
	 * Az indentálás mélységét tárolja
	 */
	private static int indentation = 0;

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
			String[] splitted = input_temp.split("\\s+");
			try {
				switch (splitted[0]) {
				// Játék halozat letehozasa, beallitasa
				case "create-netwrok":

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

					break;
				case "action-slipery":

					break;
				case "action-repair":

					break;
				case "action-placePipe":

					break;
				case "action-placePump":

					break;
				case "action-grabPipe":

					break;
				case "action-grapPump":
					grabPump(splitted);
					break;
				case "action-move":

					break;
				case "action-pass":

					break;
				case "action-setpump":
					actionSetPump(splitted);
					break;
				// Allapot lekerdezesek
				case "get-neighbours":

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
				case "set-Random":

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
					System.out.print("Invalid parancs!");
				}

			} catch (InputMismatchException e) {
				e.printStackTrace();
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
			network.setActiveCharacter(plumbers.get(command[1]));
			if (command.length == 4) {
				if (command[2].equals("-p")) {
					network.setActionPoint(Integer.parseInt(command[4]));
				}
			} else {
				network.setActionPoint(5);
				System.out.println("Sikeresen beallitotta az aktiv karaktert: " + command[1] + " akciopontjai: "
						+ network.getActionPoint());
			}

		} else if (saboteurs.containsKey(command[1])) {
			network.setActiveCharacter(saboteurs.get(command[1]));
			if (command.length == 4) {
				if (command[2].equals("-p")) {
					network.setActionPoint(Integer.parseInt(command[4]));
				}
			} else {
				network.setActionPoint(5);
				System.out.println("Sikeresen beallitotta az aktiv karaktert: " + command[1] + " akciopontjai: "
						+ network.getActionPoint());
			}
		} else
			System.out.println("Nincsen ilyen nevu karakter");
	}

	public static void actionSetPump(String[] command) {
		if (command[1] != null && command[2] != null && pipes.containsKey(command[1])
				&& pipes.containsKey(command[2])) {
			network.getCurrentCharacter().setPump(pipes.get(command[1]), pipes.get(command[2]));

		}
	}

	public static void grabPump(String[] command) {
		boolean standOnCistern = false;
		boolean activeIsPlumber = false;
		Cistern temp_cistern = new Cistern();
		// Ha cisternan all, akkor meg tudja hivni ezt a parancsot
		for (Map.Entry<String, Cistern> set : cisterns.entrySet()) {
			if (set.getValue().equals(network.getCurrentCharacter().getField())) {
				temp_cistern = set.getValue();
				standOnCistern = true;
			}
		}
		// Ha az aktive karakter egy plumber akkor tudja meg
		for (Map.Entry<String, Plumber> set : plumbers.entrySet()) {
			if (set.getValue().equals(network.getCurrentCharacter())) {
				activeIsPlumber = true;
			}
		}
		// Ha minden ertek stimmel akkor kap egy uj pumpat az inventoryba a Plumber
		if (temp_cistern.getHasPump() && network.getCurrentCharacter().getInventoryPump() == null && standOnCistern
				&& activeIsPlumber) {
			network.getCurrentCharacter().setInventoryPump(new Pump());
			System.out.println("Fel tudta venni a pumpat a szerelo");
		}

	}

	public static void getActionPoint(String[] command) {
		System.out.println("A hatralevo akciopontok: " + network.getActionPoint());
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
			if (network.getCurrentCharacter().equals(p))
				currentPlumber = p;
		}
		if (currentPlumber != null) {
			if (pipes.containsValue(network.getCurrentCharacter().getField()) || pumps.containsValue(network.getCurrentCharacter().getField())) {
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
					System.out.print(set.getKey());
				}
			}
		}
	}
}
