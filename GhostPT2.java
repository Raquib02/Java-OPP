package GhostPt2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

public class GhostPT2 {
	
	public static void main(String[] args) throws FileNotFoundException, IOException{
//I imported the file right here.
		File file = new File("words.txt");
		Scanner scan = new Scanner(file);
//Lines 15 to 19 convert the word text file into an Array with the name of "dic" short for dictionary.
		String[] dic = new String[279496];
		for(int i = 0; i < dic.length; i++) {
			dic[i] = scan.nextLine();
		}
		boolean gameEnd = false;
//This variable takes in every letter from the players
		String letter = "";
//This variable combines all the letter to form words.
		String word = "";
//This allows players to input their responses.
		Scanner myObj = new Scanner(System.in);
//lines 27 through 34 take in the # of players and player names and add them to an arraylist of the object class PlayerClass
		System.out.println("Enter the number of players: ");
		int players = myObj.nextInt();
		System.out.println("Enter the name of the players: ");
		ArrayList<PlayerClass> Players = new ArrayList<PlayerClass>();
		for(int i = 0; i < players; i++) {
			String name = myObj.next();
			Players.add(new PlayerClass(name));
		}
//while loop to keep the game going, until someone reaches ghost
		while(!gameEnd) {
			boolean round = false;
//while loop to keep the round going until someone loses and gets a letter
			while(!round) {
//Lines 41 through 44 takes in players letters until someone loses.
			for(int i = 0; i < Players.size(); i++) {
			    System.out.println(Players.get(i)+ ", it's your turn. The letters are " + word + ". Enter a letter or enter * to challenge.");
			    letter = myObj.next();
//Lines 45 through 58 is the challange when a player enters a star.
				if(letter.equals("*")) {
					round = true;
					boolean good = false;
					for(int a = 0; a < dic.length; a++) {
						if((dic[a].length() > 3 && dic[a].length() == word.length()+1) && (dic[a].substring(0, dic[a].length()-1).equals(word))) {
							Players.get(i).roundsloss();
							System.out.println( dic[a] + " begins with those letters. " + Players.get(i) + " loses!");
							good = true;
							word = "";
							//if a player has ghost they are eliminated and removed from the arraylist
							if(Players.get(i).isEliminated()){
								System.out.println(Players.get(i) + " is eliminated ");
								Players.remove(i);
							}
						}	
					}
					if(!good) {
						if(i == 0) i = i+1;
						System.out.println("No word begins with those letters. " + Players.get(i-1) + " loses!");
						Players.get(i-1).roundsloss();
						word = "";
						if(Players.get(i-1).isEliminated()){
							System.out.println(Players.get(i-1) + " is eliminated ");
							Players.remove(i-1);
						}
					}
				}
//Lines 73 to 84 says the last player entered a letter to form a word based on dic so they are the loser.				
					else {
						word += letter;
						for(int a = 0; a < dic.length; a++) {
							if(dic[a].length() > 3 && (word).equals(dic[a])) {
								Players.get(i).roundsloss();
								System.out.println(word + " is a word. " +Players.get(i) + " loses!");
								word = "";
								if(Players.get(i).isEliminated()){
									System.out.println(Players.get(i) + " is eliminated ");
									Players.remove(i);
								}
								round = true;
							}
						}
					}
				}
//lines 90 to 93 declare the last player to be the winner because the arraylist is only of size one meaning everyone else is eliminated
				if(Players.size() == 1) {
					System.out.println(Players.get(0) + " is the winnter! ");
					System.exit(0);
				}
			}
		}
		myObj.close();
		scan.close();
	}
}