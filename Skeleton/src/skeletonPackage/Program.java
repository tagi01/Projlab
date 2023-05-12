package skeletonPackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Program {
	
	/**
	 * A felhasznalo inputjainak beolvasasahoz
	 */
	private static Scanner input;
	
	private static Network network = new Network();
	/**
	 * az objektumok neveit tartalmazo map
	 */
	private static Map<String , Pipe> pipes;
	
	private static Map<String , Pump> pumps;
	
	private static Map<String , Source> sources;
	
	private static Map<String , Cistern> cisterns;
	
	private static Map<String , Saboteur> saboteurs;
	
	private static Map<String , Plumber> plumbers;
	
	/**
	 * Az indentálás mélységét tárolja
	 */
	private static int indentation = 0;
	
	/**
	 * Main metódus
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
		
		while(true) {
			input_temp=new String();
			input_temp = input.nextLine();
			String[] splitted = input_temp.split("\\s+");
			try {		
				switch(splitted[0]) {
		//Játék halozat letehozasa, beallitasa
				case "create-netwrok":
					
					break;
				case "set-pipe":
					
					break;
				case "set-pump":
					
					break;
				case "set-cistern":
					
					break;
				case "set-plumber":
					
					break;
				case "set-saboteur":
					
					break;
				case "set-active":
					
					break;
				case "set-game":
					
					break;
		//Játékosok akciói
				case "action-puncture":
					
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
					
					break;
				case "action-move":
					
					break;
				case "action-pass":
					
					break;
				case "action-setpump":
					
					break;
		//Allapot lekerdezesek
				case "get-neighbours":
					
					break;
				case "get-inventory":
					
					break;
				case "get-actionPoints":
					
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
					
					break;
				case "get-isBroken":
					
					break;
				case "get-teamPoints":
					
					break;
				case "get-connections":
					
					break;
		//Jatek mentes es betoltes
				case "save":
					
					break;
				case "load":
					
					break;
		//Plusszok meg
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
	//Beállítja egy adott szamú csőnek a paramétereit
		public static void setPipe(String[] command) {
			if(pipes.containsKey(command[1])) {
				Pipe temp = pipes.get(command[1]);
				if(command[2].equals("-n")) {
					if(command[3].equals("[") && command[6].equals("]")) {
						
						//Megvizsgalni az eslo szomszedot
						if(pumps.containsKey(command[4])) {
							temp.addNeighbour(pumps.get(command[4]));
						}
						else if(sources.containsKey(command[4])) {
							temp.addNeighbour(sources.get(command[4]));
						}
						else if(cisterns.containsKey(command[4])) {
							temp.addNeighbour(cisterns.get(command[4]));
						}
						else if(plumbers.containsKey(command[4])) {
							Plumber temp_p = plumbers.get(command[4]);
							temp_p.setInventoryPipe(temp);
						}
						else
							System.out.println("Nincs ilyen mezo vagy szerelo elso szomszedkent akihez a csovet hozza tudod rendelni, kotni");
						
						// Megvizsgalni a masodik szomszedot
						if(pumps.containsKey(command[5])) {
							temp.addNeighbour(pumps.get(command[5]));
						}
						else if(sources.containsKey(command[5])) {
							temp.addNeighbour(sources.get(command[5]));
						}
						else if(cisterns.containsKey(command[5])) {
							temp.addNeighbour(cisterns.get(command[5]));
						}
						else if(plumbers.containsKey(command[5])) {
							Plumber temp_p = plumbers.get(command[5]);
							temp_p.setInventoryPipe(temp);
						}
						else
							System.out.println("Nincs ilyen mezo vagy szerelo masodik szomszedkent akihez a csovet hozza tudod rendelni, kotni");

						//tovabbi parancsok kiertekelese
						for(int i=7; i < command.length; i+=2 ) {
							//cso meretet allithatod be
							if(command[i].equals("-s")) {
								temp.setSize(Integer.parseInt(command[i+1]));
							}
							//csobol mennyi viz folyt ki
							else if(command[i].equals("-l")) {
								temp.setLostWater(Integer.parseInt(command[i+1]));
							}
							//csoben mennyi viz van
							else if(command[i].equals("-w")) {
								temp.setWater(Integer.parseInt(command[i+1]));
							}
							//cso mennyi ideig nem lyukaszthato meg ki
							else if(command[i].equals("-p")) {
								temp.setCantPuncture(Integer.parseInt(command[i+1]));
							}
							//cso ki van e lyukasztva vagy ninics
							else if(command[i].equals("-b")) {
								if(command[i+1].equals("true")) {
									temp.setBroken(true);
								}
								else
									temp.setBroken(false);
							}
							//ragadosra allitja, ha true az ertek
							else if(command[i].equals("-st")) {
								if(command[i+1].equals("true")) {
									temp.setStateOfPipeSticky();
								}
								else
									temp.setStateOfPipeNormal();
							}
							//csusszossa allitja, ha true az ertek
							else if(command[i].equals("-sl")) {
								if(command[i+1].equals("true")) {
									temp.setStateOfPipeSlippery();
								}
								else
									temp.setStateOfPipeNormal();
							}
						}
						
						System.out.println("Befejezodtek a beállítások!");
					}
					else
						System.out.println("Nem jo a parameterezes nem helyes vagy hianyzik a <[...]> !");
				}
				else
					System.out.println("Nem jo a parameterezes nem helyes vagy hianyzik a <-n> !");
			}
			else
				System.out.println("Nincs ilyen cso!");
		}

		public static void setActive(String[] command) {
			if(plumbers.containsKey(command[1])) {
				network.setActiveCharacter(plumbers.get(command[1]));
				if(command.length==4) {
					if(command[2].equals("-p")) {
						network.setActionPoint(Integer.parseInt(command[4]));
					}
				}
				else {
					network.setActionPoint(5);
					System.out.println("Sikeresen beallitotta az aktiv karaktert: " + command[1] + " akciopontjai: " + network.getActionPoint() );
				}
					
				
			}
			else if(saboteurs.containsKey(command[1])) {
				network.setActiveCharacter(saboteurs.get(command[1]));
				if(command.length==4) {
					if(command[2].equals("-p")) {
						network.setActionPoint(Integer.parseInt(command[4]));
					}
				}
				else {
					network.setActionPoint(5);
					System.out.println("Sikeresen beallitotta az aktiv karaktert: " + command[1] + " akciopontjai: " + network.getActionPoint() );
				}
			}
			else
				System.out.println("Nincsen ilyen nevu karakter");
		}

		public static void actionSetPump(String[] command) {
			if(command[1]!=null && command[2]!=null && pipes.containsKey(command[1]) && pipes.containsKey(command[2])) {
				network.getCurrentCharacter().setPump(pipes.get(command[1]), pipes.get(command[2]));
	
			}
		}

		public static void gabPipe(String[] command) {
			
		}
}
