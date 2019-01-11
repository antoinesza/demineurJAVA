import java.nio.Buffer;


public class Engine {
	private int numberOfCasesDiscovered;
	private int numberOfFlagsPosed;
	private int numberOfBombsGenerated;
	private boolean[][] positionOfTheBombsOnTheCheckerboard;
	private boolean[][] positionOfTheUncoveredBoxOnTheCheckerboard;
	private boolean[][] positionOfTheFlagsOnTheCheckerboard;
	private boolean endOfGame;
	
	
	public Engine(int horizontalBoxNumber, int verticalBoxNumber) {
		this.positionOfTheBombsOnTheCheckerboard = new boolean[horizontalBoxNumber][verticalBoxNumber];
		this.positionOfTheUncoveredBoxOnTheCheckerboard = new boolean[horizontalBoxNumber][verticalBoxNumber];
		this.positionOfTheFlagsOnTheCheckerboard = new boolean[horizontalBoxNumber][verticalBoxNumber];
		this.Generate(horizontalBoxNumber, verticalBoxNumber, 30);
		this.numberOfBombsGenerated = 0;
		this.numberOfFlagsPosed = 0;
	}
	
	//Retourne true si aucune bombe a explosé
	public boolean discoverTheBox(int horizontalBox, int verticalBox) {
		if (!this.getBoxValueOfFlagCheckerboard(horizontalBox, verticalBox)) {
			if (!this.getBoxValueOfBombsCheckerboard(horizontalBox, verticalBox)) {
				this.positionOfTheUncoveredBoxOnTheCheckerboard[horizontalBox][verticalBox] = true;
				
				return true;
			}
			
			this.endOfGame = true;
		}
		return false;
	}
	
	//Retourner true si le drapeau a été posé
	public boolean putTheFlag(int horizontalBox, int verticalBox) {
		if (!this.getBoxValueOfUncoversCheckerboard(horizontalBox, verticalBox)) {
			this.positionOfTheFlagsOnTheCheckerboard[horizontalBox][verticalBox] = true;
			if (this.getBoxValueOfBombsCheckerboard(horizontalBox, verticalBox)) {
				this.numberOfFlagsPosed++;
			}
			
			return true;
		}
		
		return false;
	}
	
	//Retourner true si le drapeau a été retiré
	public boolean removeTheFlag(int horizontalBox, int verticalBox) {
		if (this.getBoxValueOfUncoversCheckerboard(horizontalBox, verticalBox)) {
			this.positionOfTheFlagsOnTheCheckerboard[horizontalBox][verticalBox] = false;
			if (this.getBoxValueOfBombsCheckerboard(horizontalBox, verticalBox)) {
				this.numberOfFlagsPosed--;
			}
			
			return true;
		}
		
		return false;
	}
	
	//Retourne le type de la boite
	public String getBoxType(int horizontalBox, int verticalBox) {
	
		if (getBoxValueOfFlagCheckerboard(horizontalBox, verticalBox)) {
			return "flagged";
		}
		
		if (!getBoxValueOfUncoversCheckerboard(horizontalBox, verticalBox) && !this.endOfGame) {
			return "covered";
		}
		
		if (this.getBoxValueOfBombsCheckerboard(horizontalBox, verticalBox)) {
			return "bomb";
		}
		
		return "discovered";
	}
	
	//Retourne la valeur la case
	public int getBoxValue(int horizontalBox, int verticalBox) {
		int buffer = 0;
		if (this.getBombBoxType(horizontalBox + 1, verticalBox)) buffer++;
		if (this.getBombBoxType(horizontalBox + 1, verticalBox + 1)) buffer++;
		if (this.getBombBoxType(horizontalBox + 1, verticalBox - 1)) buffer++;
		if (this.getBombBoxType(horizontalBox, verticalBox + 1)) buffer++;
		if (this.getBombBoxType(horizontalBox, verticalBox - 1)) buffer++;
		if (this.getBombBoxType(horizontalBox - 1, verticalBox)) buffer++;
		if (this.getBombBoxType(horizontalBox - 1, verticalBox + 1)) buffer++;
		if (this.getBombBoxType(horizontalBox - 1, verticalBox - 1)) buffer++;
		
		return buffer;
	}
	
	//Retourne true si c'est une bombe
	private boolean getBombBoxType(int horizontalBox, int verticalBox) {
		if(this.positionOfTheBombsOnTheCheckerboard.length > horizontalBox && 0 <= horizontalBox) {
			if(this.positionOfTheBombsOnTheCheckerboard[horizontalBox].length > verticalBox && 0 <= verticalBox) {
				return this.positionOfTheBombsOnTheCheckerboard[horizontalBox][verticalBox];
			}
		}
		
		return false;
	}
	
	//Génère le damier du démineur
	private void Generate(int horizontalBoxNumber, int verticalBoxNumber, int bombsPercentage) {      
		for (int horizontalIterator = 0; horizontalIterator < this.positionOfTheBombsOnTheCheckerboard.length; horizontalIterator++) {
			for (int verticalIterator = 0; verticalIterator < this.positionOfTheBombsOnTheCheckerboard.length; verticalIterator++) {
				this.positionOfTheBombsOnTheCheckerboard[horizontalIterator][verticalIterator] = false;
				if (Math.random() * 100 < bombsPercentage) {
					this.positionOfTheBombsOnTheCheckerboard[horizontalIterator][verticalIterator] = true;
					this.numberOfBombsGenerated++;
					
				}
			}
		}
    }
	
	private boolean getBoxValueOfBombsCheckerboard(int horizontalBox, int verticalBox) {
		if (this.positionOfTheBombsOnTheCheckerboard.length > horizontalBox) {
			if (this.positionOfTheBombsOnTheCheckerboard[horizontalBox].length > verticalBox) {
				return this.positionOfTheBombsOnTheCheckerboard[horizontalBox][verticalBox];				
			}
		}
		
		return false;
	}
	
	private boolean getBoxValueOfUncoversCheckerboard(int horizontalBox, int verticalBox) {
		return this.positionOfTheUncoveredBoxOnTheCheckerboard[horizontalBox][verticalBox];
	}
	
	private boolean getBoxValueOfFlagCheckerboard(int horizontalBox, int verticalBox) {
		return this.positionOfTheFlagsOnTheCheckerboard[horizontalBox][verticalBox];
	}
}