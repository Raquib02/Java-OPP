package GhostPt2;

public class PlayerClass {
	private String playerName;
	private String letter;
	
//PlayerClass constructor with one paraneter player name and letter to keep track of the letters the player has.
	public PlayerClass(String playerName) {
		this.playerName = playerName;
		letter = "";
	}
//instance method roundsLoss everytime a player looses they gain a char from Ghost and if they reach size 5 they automatically get eliminated
	public void roundsloss() {
		String a = "GHOST";
		letter += a.charAt(letter.length());
		if(letter.length() == 5) isEliminated();
	}
//instance method isEliminated when this is called it lets users know the player abtained ghost.
	public boolean isEliminated() {
		return letter.equals("GHOST");
	}
//the toString method keeps tracks of the player names and what letters they have from ghost.
	public String toString() {
		return playerName + " (" + letter + ") ";
	}
}