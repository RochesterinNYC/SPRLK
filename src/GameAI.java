/**
 * <b>GameAI class</b>
 * <p>
 * Acts as an artificial intelligence that tries to predict the player's next game
 * gesture based on the frequencies of the past game gestures that the player has thrown.
 * <p>
 * Step 4: Not exceptionally intelligent. Shows an advantage against an unwitting, unaware
 * player. 
 * <p>
 * However, if the player is aware of the algorithm that this AI operates on,
 * it is possible to only lose one game/round and to win every following round. If 
 * you keep track of their own gesture counts, then you will always know what the
 * AI thinks that your preferred gesture is (the player knowledge of what the
 * AI thinks is the player's preferred gesture destroys the AI's advantage). 
 * <p>
 * Then, if you know what the AI's perceived player preference is, you also know what 
 * two possible hands that the computer will throw against you (the two hands that 
 * would beat whatever the AI perceives as the player's preferred gesture). Hence, 
 * the next round, you can throw the gesture that beats the computer's two possible 
 * gestures. Using this method and keeping track of your counts, you can win every 
 * single game/round after the first one. 
 * <p>
 * It is also important to note that you must know the order of the logic statements
 * for the AI to be able to correctly predict what the AI will perceive as your 
 * preferred gesture when two or more player gesture counts are tied for the most. 
 * Due to the nature of the if-else statements, whatever is first in the order of 
 * scissors, paper, rock, lizard, and spock will be considered the preference. 
 * Against an unaware player, this might not be a big deal, but to someone who knows
 * the ins and outs of the AI code, it allows for the player to completely know what
 * the AI is "thinking."
 * @author James Wen - jrw2175
 */
public class GameAI {
	private int scissorGestures;
	private int paperGestures;
	private int rockGestures;
	private int lizardGestures;
	private int spockGestures;
	private final int OPTIONS = 5;//number of possible gestures that one can throw
	
	/**
	 * Constructs a SPRLK Computer AI and initiates all its player gesture counts to 
	 * 0.
	 */
	public GameAI(){
		scissorGestures = 0;
		paperGestures = 0;
		rockGestures = 0;
		lizardGestures = 0;
		spockGestures = 0;
	}

	/**
	 * inputGesture
	 * <p>
	 * Updates the AI's frequency counts of the player's thrown gestures.
	 */
	public void inputGesture(GameGesture playerGesture){
		if (playerGesture == GameGesture.SCISSORS){
			scissorGestures++;
		}
		else if(playerGesture == GameGesture.PAPER){
			paperGestures++;
		}
		else if(playerGesture == GameGesture.ROCK){
			rockGestures++;
		}
		else if(playerGesture == GameGesture.LIZARD){
			lizardGestures++;
		}
		else if(playerGesture == GameGesture.SPOCK){
			spockGestures++;
		}
	}
	
	/**
	 * playerPref
	 * <p>
	 * Determines if the player has a preference for any specific game gesture.
	 * <p>
	 * Algorithm for finding a preference is based on the fact that no player
	 * gesture count can be greater than the number of games that have been played.
	 * Hence, whichever gesture count is closest in frequency to the total number 
	 * of games played is the one that the player prefers. Preference ties are 
	 * negligible since they do not hurt the computer in any way.
	 * @param gamesPlayed - the number of games played so far
	 * <p>
	 * @return playerPref - the gesture that the player prefers to throw by
	 * frequency
	 */
	//Used to determine if the player has a preference
	public GameGesture playerPref(int gamesPlayed){
		boolean preferenceFound = false;
		GameGesture playerPref = GameGesture.NONE;
		if (gamesPlayed == 0){//if no games played yet, skips finding preference
			preferenceFound = true;
		}
		while(!preferenceFound){//finds player preference
			if (scissorGestures == gamesPlayed){
				playerPref = GameGesture.SCISSORS;
				preferenceFound = true;
			}
			else if (paperGestures == gamesPlayed){
				playerPref = GameGesture.PAPER;
				preferenceFound = true;
			}
			else if (rockGestures == gamesPlayed){
				playerPref = GameGesture.ROCK;
				preferenceFound = true;
			}
			else if (lizardGestures == gamesPlayed){
				playerPref = GameGesture.LIZARD;
				preferenceFound = true;
			}
			else if (spockGestures == gamesPlayed){
				playerPref = GameGesture.SPOCK;
				preferenceFound = true;
			}
			gamesPlayed--;
		}
		return playerPref;
	}

	/**
	 * gestureComp
	 * <p>
	 * Decides what gesture the computer is going to throw.
	 * <p>
	 * If this is the first game (no gesture have been thrown yet), then the computer
	 * will randomly choose a gesture to throw. After the first round/game, the
	 * player will automatically have a preference for a gesture by frequency, so the
	 * computer throw will no longer be completely random.
	 * <p>
	 * Once the computer has determined a player gesture preference, there are two
	 * possible gestures that can beat the player's preferred gesture. The computer
	 * randomly chooses one of these two possible gestures (decideBetween = 1 is the
	 * gesture that is physically next to the player preferred gesture in the SPRLK 
	 * diagrams). 
	 * @param playerPref - the gesture that the player prefers to throw by frequency
	 * <p>
	 * @return compGesture - the gesture that the computer has selected
	 */
	public GameGesture gestureComp(GameGesture playerPref){
		GameGesture compGesture = GameGesture.NONE;
		int decideBetween = 0;
		if (playerPref != GameGesture.NONE){
			decideBetween = (int)(Math.random() * 2) + 1;
		}
		if (playerPref == GameGesture.NONE){//if player has no current preference
			int randomGesture = (int)(Math.random() * OPTIONS) + 1;
			if(randomGesture==1){
				compGesture = GameGesture.SCISSORS;
			}
			if(randomGesture==2){
				compGesture = GameGesture.PAPER;
			}
			if(randomGesture==3){
				compGesture = GameGesture.ROCK;
			}
			if(randomGesture==4){
				compGesture = GameGesture.LIZARD;
			}
			if(randomGesture==5){
				compGesture = GameGesture.SPOCK;
			}
		}
		else if (playerPref == GameGesture.SCISSORS){//Player prefers Scissors
			if (decideBetween == 1){
				compGesture = GameGesture.SPOCK;
			}
			else{
				compGesture = GameGesture.ROCK;
			}
		}
		else if (playerPref == GameGesture.PAPER){//Player prefers Paper
			if (decideBetween == 1){
				compGesture = GameGesture.SCISSORS;
			}
			else{
				compGesture = GameGesture.LIZARD;
			}
		}
		else if (playerPref == GameGesture.ROCK){//Player prefers Rock
			if (decideBetween == 1){
				compGesture = GameGesture.PAPER;
			}
			else{
				compGesture = GameGesture.SPOCK;
			}
		}
		else if (playerPref == GameGesture.LIZARD){//Player prefers Lizard
			if (decideBetween == 1){
				compGesture = GameGesture.ROCK;
			}
			else{
				compGesture = GameGesture.SCISSORS;
			}
		}
		else if (playerPref == GameGesture.SPOCK){//Player prefers Spock
			if (decideBetween == 1){
				compGesture = GameGesture.LIZARD;
			}
			else{
				compGesture = GameGesture.PAPER;
			}
		}
		return compGesture;
	}
	
}//End of GameAI class