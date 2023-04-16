package skeletonPackage;

import java.util.ArrayList;
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
	
	private static int indentation = 0;
	
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
						 + "11. Szerelo megjavit egy csovet/pumpat\n"
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
		indentation = 2;
		if(numb == 1) {
			boolean hasPump = askQuestion("Van pumpa a ciszternan?");
			indentation = 3;
			c.setHasPump(hasPump);
			p.getPump();
		} else if(numb == 2) {
			boolean hasPipe = askQuestion("Van cso a ciszternan?");
			indentation = 3;
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
		indentation = 1;
		boolean hasPump = askQuestion("Van pumpa a szerelonek?");
		if(hasPump) {
			Pump inventoryPump = new Pump();
			names.put(inventoryPump, "inventoryPump");
			Pump pump2 = new Pump();
			names.put(pump2, "pump2");
			plumber.setInventoryPump(inventoryPump);
			currentField.addNeighbour(pump2);
			pump2.addNeighbour(currentField);
			network.addField(currentField);
			network.addField(pump2);
		}
		indentation = 2;
		plumber.placePump();
		System.out.println("Teszt vege");
		
	}
	
	/** TEST 3 - Szerelo, szabotor mozgatasa masik mezore */
	public static void three() {
		System.out.println("TEST 3 - Szerelo, szabotor mozgatasa masik mezore");
		names = new HashMap<Object, String>();
		Network network = new Network();
		names.put(network, "network");
		Source source = new Source();
		names.put(source, "source");
		Pipe p1 = new Pipe();
		names.put(p1, "p1");
		Pump pump = new Pump();
		names.put(pump, "pump");
		Pipe p2 = new Pipe();
		names.put(p2, "p2");
		Cistern cistern = new Cistern();
		names.put(cistern, "cistern");
		ArrayList<Field> fields = new ArrayList<Field>();
		fields.add(cistern);
		fields.add(source);
		fields.add(p2);
		fields.add(pump);
		fields.add(p1);
		source.addNeighbour(p1);
		p1.addNeighbour(p1);
		p1.addNeighbour(pump);
		pump.addNeighbour(p1);
		pump.addNeighbour(p2);
		p2.addNeighbour(pump);
		p2.addNeighbour(cistern);
		cistern.addNeighbour(p2);
		network.setField(fields);
		System.out.println("\t1. Szerelo lep ciszternara\n"
							+ "\t2, Szabotor lep ciszternara\n"
							+ "\t3. Szerelo lep forrasra\n"
							+ "\t4. Szabotor lep forrasra\n"
							+ "\t5. Szerelo lep csore\n"
							+ "\t6. Szabotor lep csore\n"
							+ "\t7. Szerelo lep pumpara\n"
							+ "\t8. Szerelo lep pumpara");
		int numb = 0;
		try {
			boolean validAnswer = false;
			while(!validAnswer){
				numb = input.nextInt();
				if(numb > 0 && numb <9){
					validAnswer = true;
				} else {
					System.out.println("\tValassz a megadott menupontok kozul!");
				}
			}
		} catch (InputMismatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		indentation = 2;
		Plumber plumber = new Plumber(p2, network);
		Saboteur saboteur = new Saboteur(p1, network);
		switch(numb) {
		case 1:
			p2.onField(plumber);
			plumber.move(cistern);
			break;
		case 2:
			saboteur.setCurrentField(p2);
			p2.onField(saboteur);
			saboteur.move(cistern);
			break;
		case 3:
			plumber.setCurrentField(p1);
			p1.onField(plumber);
			plumber.move(source);
			break;
		case 4:
			saboteur.setCurrentField(p1);
			p1.onField(saboteur);
			saboteur.move(source);
			break;
		case 5:
			plumber.setCurrentField(pump);
			pump.onField(plumber);
			boolean pipeIsNotEmpty = askQuestion("Van mar valaki a csovon?");
			if(pipeIsNotEmpty) {
				saboteur.setCurrentField(p1);
				p1.onField(saboteur);
			}
			indentation = 3;
			plumber.move(p1);
			break;
		case 6:
			saboteur.setCurrentField(pump);
			pump.onField(saboteur);
			pipeIsNotEmpty = askQuestion("Van mar valaki a csovon?");
			if(pipeIsNotEmpty) {
				plumber.setCurrentField(p1);
				p1.onField(plumber);
			}
			indentation = 3;
			saboteur.move(p1);
			break;
		case 7:
			plumber.setCurrentField(p1);
			p1.onField(plumber);
			plumber.move(pump);
			break;
		case 8:
			saboteur.setCurrentField(p1);
			p1.onField(saboteur);
			saboteur.move(pump);
			break;
		}
		System.out.println("Teszt vege");
	}
	
	/** TEST 4 - Pumpaban viz mozgatasa */
	public static void four() {
		System.out.println("TEST 4 - Pumpaban viz mozgatasa");
		System.out.println("\tMekkora a bemeneti cso merete? (egesz szam) ");
		int inSize = input.nextInt();
		System.out.println("\tMennyi viz van a bemeneti csoben? (egesz szam) ");
		int inWater = input.nextInt();
		while(inWater > inSize || inWater < 0) {
			System.out.println("\tNem megfelelo ertek");
			inWater = input.nextInt();
		}
		System.out.println("\tMekkora a kimeneti cso merete? (egesz szam) ");
		int outSize = input.nextInt();
		System.out.println("\tMennyi viz van a kimeneti csoben? (egesz szam) ");
		int outWater = input.nextInt();
		while(outWater > outSize || outWater < 0) {
			System.out.println("\tNem megfelelo ertek");
			outWater = input.nextInt();
		}
		indentation = 2;
		names = new HashMap<Object, String>();
		Pipe in = new Pipe(inSize, inWater);
		names.put(in, "in");
		Pipe out = new Pipe(outSize, outWater);
		names.put(out, "out");
		Pump pump = new Pump();
		names.put(pump, "pump");
		pump.addNeighbour(in);
		pump.setIn(in);
		pump.addNeighbour(out);
		pump.setOut(out);
		pump.pumpWater();
		System.out.println("Teszt vege");
	}
	
	/** TEST 5 - Ciszterna begyűjti a vizet*/
	public static void five() {
		System.out.println("TEST 5 - Ciszterna viz fogadasa");
		//System.out.println("\tVan cso csatlakoztatva a ciszternahoz? (i, n) ");
		System.out.println("\t Mennyi viz van a csoben?");
		int water = input.nextInt();
		Cistern cistern = new Cistern();
		names.put(cistern, "cistern");
		Pipe p = new Pipe(water, water);
		names.put(p, "p");
		cistern.addNeighbour(p);
		p.addNeighbour(cistern);
		indentation = 2;
		cistern.collectWater();
		System.out.println("Teszt vege");
	}
	
	/** TEST 6 - Forras vizet ad */
	public static void six() {
		System.out.println("TEST 6 - Forras vizet ad");
		//System.out.println("\tVan cso csatlakoztatva a forrashoz? (i, n) ");
		System.out.println("\t Mennyi vizet bir el a cso?");
		int size = input.nextInt();
		System.out.println("\t Mennyi viz van mar a csoben?");
		int water = input.nextInt();
		while(water > size || water < 0) {
			System.out.println("\tNem megfelelo ertek");
			water = input.nextInt();
		}
		Pipe p = new Pipe(size, water);
		names.put(p, "p");
		Source s = new Source();
		names.put(s, "s");
		p.addNeighbour(s);
		s.addNeighbour(p);
		s.giveWater();
		System.out.println("Teszt vege");
	}
	
	/** TEST 7 - Pumpa eltorese */
	public static void seven() {
		System.out.println("TEST 7 - Pumpa eltorese");
		indentation = 1;
		boolean broken = askQuestion("El van mar torve a pumpa?");
		Network n = new Network();
		names.put(n, "n");
		Pump p = new Pump();
		names.put(p, "p");
		n.addField(p);
		p.setBroken(broken);
		indentation = 2;
		n.breakPump();
		System.out.println("Teszt vege");
	}
	
	/** TEST 8 - Szabotor kilyukaszt egy csovet */
	public static void eight() {
		System.out.println("TEST 8 - Szabotor kilyukaszt egy csovet");
		names = new HashMap<Object, String>();
		Pipe currentField = new Pipe();
		names.put(currentField, "currentField");
		Saboteur s = new Saboteur(null, null);
		names.put(s, "s");
		s.setCurrentField(currentField);
		currentField.setCurrentCharacters(s);
		indentation = 2;		
		boolean broken = askQuestion("El van torve a cso?");
		indentation = 3;
		if(broken) currentField.breakField();
		s.puncturePipe();
		System.out.println("Teszt vege");
	}
	
	/** TEST 9 - Cso felvetele egy pumpanal */
	public static void nine() {
		System.out.println("TEST 9 - Cso felvetele egy pumpanal");
		names = new HashMap<Object, String>();
		Pipe pipe = new Pipe();
		names.put(pipe, "pipe");
		Pump pump = new Pump();
		names.put(pump, "pump");
		Plumber plumber = new Plumber(pump, null);
		names.put(plumber, "plumber");
		pump.addNeighbour(pipe);
		pipe.addNeighbour(pump);	
		indentation = 2;		
		boolean empty = askQuestion("Ures az inventory-ja?");		
		boolean inUse = askQuestion("Hasznalja a pumpa ezt a csovet?");
		indentation = 3;
		if(!empty) plumber.setInventoryPipe(new Pipe());
		if(inUse) pump.setIn(pipe);
		plumber.grabPipe(pipe);
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
		names = new HashMap<Object, String>();
		Plumber p = new Plumber(null, null);
		names.put(p, "p");
		System.out.println("\t1. Cso megjavitasa\n"
						+ "\t2. Pumpa megjavitasa");
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
		indentation = 2;
		if(numb == 1) {
			Pipe currentField = new Pipe();
			names.put(currentField, "currentField");
			p.setCurrentField(currentField);
			currentField.setCurrentCharacters(p);
			boolean broken = askQuestion("El van romolva a cso?");
			indentation = 3;
			if(broken) currentField.breakField();
			p.repair();
		} else if(numb == 2) {
			Pump currentField = new Pump();
			names.put(currentField, "currentField");
			p.setCurrentField(currentField);
			currentField.setCurrentCharacters(p);
			boolean broken = askQuestion("El van romolva a pumpa?");
			indentation = 3;
			if(broken) currentField.breakField();
			p.repair();
		}
		System.out.println("Teszt vege");
	}
	
	/**
	 * Az indentálásért felelős
	 */
	public static void indent() {
		for(int i = 0; i < indentation; i++) {
			System.out.print("\t");
		}
	}
	
	/**
	 * A felhasznalotol kerdez egy eldontendo kerdest
	 * @param question Az eldontendo kerdes
	 * @return a valasz
	 */
	public static boolean askQuestion(String question) {
		boolean answer = false;
		indent();
		System.out.println(question + " I/N");
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
	 * Kiírja az objetum típusát, nevét, és a rajta meghívott függvényt
	 * @param object a kiírandó objektum
	 * @param method a kiírandó függvény
	 */
	public static void printMethod(Object object, String method) {
		String name;
		name = object.getClass().getSimpleName() + " " + names.get(object);
		indent();
		System.out.println(name + "." + method + "()");
	}

}
