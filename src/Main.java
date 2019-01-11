import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	String str = "";
        do {
        	System.out.println("Pour acc�der � la version console du d�mineur taper :        'console'");
        	System.out.println("Pour acc�der � la version graphique du d�mineur taper :      'graphique'");
        	System.out.println("Pour quitter le programme taper :                            'quitter'");
        	
        	str = sc.nextLine();
        	
        	switch (str) {
				case "graphique":
					Frame frame = new Frame("D�mineur");
					TimerThread Timer = new TimerThread();
					Timer.start();
					str = "quitter";
					break;  
				
				case "console":
					System.out.println("Saisir la hauteur du d�mineur");
					int height = sc.nextInt();sc.nextLine();
					System.out.println("Saisir la longeur du d�mineur");
					int width = sc.nextInt();sc.nextLine();
					new ConsoleSocket(height, width);
					str = "quitter";
					break;
        	}
        }while(!str.equals("quitter"));
        sc.close();
    }
}
