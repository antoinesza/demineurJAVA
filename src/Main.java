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
					System.out.println("Saisir la hauteur du démineur");
					int height = sc.nextInt();sc.nextLine();
					System.out.println("Saisir la longeur du démineur");
					int width = sc.nextInt();sc.nextLine();
					new ConsoleSocket(height, width);
					str = "quitter";
					break;
        	}
        }while(!str.equals("quitter"));
        sc.close();
    }
}
