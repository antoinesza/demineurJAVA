import java.util.Scanner;

public class ConsoleSocket {
	private Engine demineur;
	private int horizontalHeight;
	private int verticalHeight;
	private Scanner scanner = new Scanner(System.in);
	
	public ConsoleSocket() {
		this.horizontalHeight = 10;
		this.verticalHeight = 10;
		this.demineur = new Engine(this.horizontalHeight, this.verticalHeight);
		
		
		this.run();
	}
	
	public ConsoleSocket(int horizontalBox, int verticalBox) {
		this.horizontalHeight = horizontalBox;
		this.verticalHeight = verticalBox;
		this.demineur = new Engine(this.horizontalHeight, this.verticalHeight);
		
		
		this.run();
	}
	
	private void run() {
		String saisi;
		do {
			String line = "  ";
			String[][] checkerboard = this.buildTheCheckerboard();
			for (int iterator = 0; iterator < this.verticalHeight; iterator++) {
				line += iterator + " ";
			}
			System.out.println(line);
			line = "";
			
			for (int horizontalIterator = 0; horizontalIterator < checkerboard.length; horizontalIterator++) {
				line += horizontalIterator + " ";
				for (int verticalIterator = 0; verticalIterator < checkerboard[horizontalIterator].length; verticalIterator++) {
					line += checkerboard[horizontalIterator][verticalIterator] + " ";
				}
				System.out.println(line);
				line = "";
			}
			
			System.out.println("Pour découvrir une case taper : 'case'.");
			System.out.println("Pour poser un drapeau taper : 'poser'.");
			System.out.println("Pour retirer un drapeau taper : 'retirer'.");
			System.out.println("Pour quitter taper : 'quitter'.");
			saisi = scanner.nextLine();	
			this.interpretationOfTheUserInput(saisi);
			
		} while (!saisi.equals("quitter"));
	}
	
	private void interpretationOfTheUserInput(String saisi) {
		int horizontalHeight;
		int verticalHeight;
		switch (saisi) {
		case "case":
			System.out.println("Sélectionner le numéro de la ligne horizontal.");
				horizontalHeight = scanner.nextInt();
				scanner.nextLine();
			System.out.println("Sélectionner le numéro de la ligne vertical.");
				verticalHeight = scanner.nextInt();
				scanner.nextLine();
			
			demineur.discoverTheBox(horizontalHeight, verticalHeight);
			break;
		case "poser":
			System.out.println("Sélectionner le numéro de la ligne horizontal.");
				horizontalHeight = scanner.nextInt();
				scanner.nextLine();
			System.out.println("Sélectionner le numéro de la ligne vertical.");
				verticalHeight = scanner.nextInt();
				scanner.nextLine();
				
			demineur.putTheFlag(horizontalHeight, verticalHeight);
			break;
		case "retirer":
			System.out.println("Sélectionner le numéro de la ligne horizontal.");
				horizontalHeight = scanner.nextInt();
				scanner.nextLine();
			System.out.println("Sélectionner le numéro de la ligne vertical.");
				verticalHeight = scanner.nextInt();
				scanner.nextLine();
				
			demineur.removeTheFlag(horizontalHeight, verticalHeight);
			break;
		}
	}
	
	private String[][] buildTheCheckerboard() {
		String[][] checkerboard = new String[this.horizontalHeight][this.verticalHeight];;
		for (int horizontalIterator = 0; horizontalIterator < this.horizontalHeight; horizontalIterator++) {
			for (int verticalIterator = 0; verticalIterator < this.verticalHeight; verticalIterator++) {
				checkerboard[horizontalIterator][verticalIterator] = transformsValueIntoSymbol(horizontalIterator, verticalIterator);
			}
		}
		
		return checkerboard;
	}
	
	private String transformsValueIntoSymbol(int horizontalBox, int verticalBox) {
		String symbol = null;
		String value = this.demineur.getBoxType(horizontalBox, verticalBox);
		if (value.equals("flagged")) symbol = "F";
		if (value.equals("covered")) symbol = "-";
		if (value.equals("discovered")) {
			symbol = String.valueOf(this.demineur.getBoxValue(horizontalBox, verticalBox));
			if (symbol.equals("0")) {
				symbol = " ";
			}
		}
		if (value.equals("bomb")) symbol = "B";
		
		return symbol;
	}
}
