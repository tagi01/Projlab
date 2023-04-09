package skeletonPackage;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Skeleton {
	public static void main(String[] args) {
		System.out.println("-----------------------------------------------------\n"
				         + "|    -----    ------    -----    -----    ------    |\n"
				         + "|      |      |         |        \\          |       |\n"
				         + "|      |      ------    -----     \\         |       |\n"
				         + "|      |      |             |      \\        |       |\n"
				         + "|      |      ------    ----     -----      |       |\n"
				         + "-----------------------------------------------------\n");
		System.out.println("1. Pipe felvétele egy pumpánál.\n"
						 + "2. Pumpa beállítása szerelőként\\szabotorkent.\n"
						 + "3. Szerelő, szabotor mozgatasa másik mezore.\n"
						 + "4. Pumpa vagy Pipe felvétele cisternánál\n"
						 + "5. Pumpa lerakása.\n"
						 + "6. Pumpa viz mozgatasa.\n"
						 + "7. Cisterna viz fogadasa.\n"
						 + "8. Forras viz adasa.\n"
						 + "9. Pumpa eltorese.\n"
						 + "10. Szabotor pipe kilyukasztasa.\n"
						 + "11. Szerelo pumpa/pipe megjavítása.\n");
		Scanner input = new Scanner(System.in);
		int numb = input.nextInt();
		do {
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
				default:
					System.out.println("Valassz jot");
				}
				
			}catch (InputMismatchException e) {
                    e.printStackTrace();
            }
			
		numb = input.nextInt();
		}while(numb <= 11 && numb >= 1);
	}
	
	//Ha elsot valasztottak akkor idelep es ebben a fuggvényben lehet majd beállítani a paramétereket
	public static void one() {
		
	}
	
	//Ha másodikat valasztottak akkor idelep es ebben a fuggvényben lehet majd beállítani a paramétereket
	public static void two() {
		
	}
	
	//Ha harmadikat valasztottak akkor idelep es ebben a fuggvényben lehet majd beállítani a paramétereket
	public static void three() {
		
	}
	
	//Ha negyediket valasztottak akkor idelep es ebben a fuggvényben lehet majd beállítani a paramétereket
	public static void four() {
		
	}
	
	//Ha ötödiket valasztottak akkor idelep es ebben a fuggvényben lehet majd beállítani a paramétereket
	public static void five() {
		
	}
	
	//Ha hatodikat valasztottak akkor idelep es ebben a fuggvényben lehet majd beállítani a paramétereket
	public static void six() {
		
	}
	
	//Ha hetediket valasztottak akkor idelep es ebben a fuggvényben lehet majd beállítani a paramétereket
	public static void seven() {
		
	}
	
	//Ha nyolcadikat valasztottak akkor idelep es ebben a fuggvényben lehet majd beállítani a paramétereket
	public static void eight() {
		
	}
	
	//Ha kilencediket valasztottak akkor idelep es ebben a fuggvényben lehet majd beállítani a paramétereket
	public static void nine() {
		
	}
	
	//Ha tizediket valasztottak akkor idelep es ebben a fuggvényben lehet majd beállítani a paramétereket
	public static void ten() {
		
	}
	
	//Ha tizenegyediket valasztottak akkor idelep es ebben a fuggvényben lehet majd beállítani a paramétereket
	public static void eleven() {
		
	}

}
