import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	String str = "";
        do {
        	System.out.println("Pour accèder à la version console du démineur taper :        'console'");
        	System.out.println("Pour accèder à la version graphique du démineur taper :      'graphique'");
        	System.out.println("Pour quitter le programme taper :                            'quitter'");
        	
        	str = sc.nextLine();
        	
        	switch (str) {
				case "graphique":
					Frame frame = new Frame("Démineur");
					TimerThread Timer = new TimerThread();
					Timer.start();
					str = "quitter";
					break;  
				
				case "console":
					System.out.println("Hello world!");
					break;
        	}
        }while(!str.equals("quitter"));
        sc.close();
    }
}
