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
		this.Generate(horizontalBoxNumber, verticalBoxNumber, 50);
	}
	
	//Retourne true si aucune bombe a explosé
	public boolean discoverTheBox(int horizontalBox, int verticalBox) {
		if (!this.getBoxValueOfBombsCheckerboard(horizontalBox, verticalBox)) {
			this.positionOfTheUncoveredBoxOnTheCheckerboard[horizontalBox][verticalBox] = true;
			
			return true;
		}
		
		this.endOfGame = true;
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
		
		if (getBoxValueOfUncoversCheckerboard(horizontalBox, verticalBox)) {
			return "covered";
		}
		
		return "discovered";
	}
	
	//Génère le damier du démineur
	private void Generate(int horizontalBoxNumber, int verticalBoxNumber, int bombsPercentage) {      
		for (int horizontalIterator = 0; horizontalIterator < this.positionOfTheBombsOnTheCheckerboard.length; horizontalIterator++) {
			for (int verticalIterator = 0; verticalIterator < this.positionOfTheBombsOnTheCheckerboard.length; verticalIterator++) {
				this.positionOfTheBombsOnTheCheckerboard[horizontalIterator][verticalIterator] = Math.random() * 100 < bombsPercentage;
			}
		}
    }
	
	private boolean getBoxValueOfBombsCheckerboard(int horizontalBox, int verticalBox) {
		return this.positionOfTheBombsOnTheCheckerboard[horizontalBox][verticalBox];
	}
	
	private boolean getBoxValueOfUncoversCheckerboard(int horizontalBox, int verticalBox) {
		return this.positionOfTheUncoveredBoxOnTheCheckerboard[horizontalBox][verticalBox];
	}
	
	private boolean getBoxValueOfFlagCheckerboard(int horizontalBox, int verticalBox) {
		return this.positionOfTheFlagsOnTheCheckerboard[horizontalBox][verticalBox];
	}
}