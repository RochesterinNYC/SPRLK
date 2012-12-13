import javax.swing.ImageIcon;
import java.text.*;

/**
 * <b>GameHandler class</b>
 * <p>
 * Handles and carries out all Scissors, Paper, Rock, Lizard, Spock game operations, 
 * including recording number of wins, ties, games, etc. and applying the game rules.
 * <p>
 * Separate entity from Computer/Game AI, although does provide input information
 * for the AI.
 * @author James Wen - jrw2175
 */
public class GameHandler {
	private int gamesPlayed;
	private int tieGames;
	private int playerWins;
	private int compWins;
	private GameGesture playerGesture;
	private GameGesture compGesture;
	private String gameStatus;
	private GameAI smarts;
	
	/**
	 * Constructs a SPRLK game Hander, initiates a computer AI, and 
	 * starts a new game (all information set to 0).
	 */
	public GameHandler(){
		smarts = new GameAI();
		gamesPlayed = 0;
		tieGames = 0;
		playerWins = 0;
		compWins = 0;
	}
	
	/**
	  * playGame
	  * <p>
	  * Applies the rules of SPRLK to the hands that the player and the 
	  * computer chose to determine a winner or tie.
	  * <p>
	  * Also inputs player gesture information to the AI through the GameAI's 
	  * inputGesture method.
	  * @param playerGesture - the game gesture chosen by the player through the GUI
      */
	public void playGame(GameGesture playerGesture){
		boolean playerWon = false;
		boolean tie = false;
		this.playerGesture = playerGesture;
		//Computer chooses a gesture, based on user's preferences if not first round
		compGesture = smarts.gestureComp(smarts.playerPref(gamesPlayed));
		
		//Tie occurs
		if (playerGesture == compGesture){
			tie = true;
		}
		
		else if (playerGesture == GameGesture.SCISSORS){//Scissors
			if(compGesture == GameGesture.PAPER || compGesture == GameGesture.LIZARD){
				playerWon = true;
			}
		}
		else if (playerGesture == GameGesture.PAPER){//Paper
			if(compGesture == GameGesture.ROCK || compGesture == GameGesture.SPOCK){
				playerWon = true;
			}			
		}
		else if (playerGesture == GameGesture.ROCK){//Rock
			if(compGesture == GameGesture.SCISSORS || compGesture == GameGesture.LIZARD){
				playerWon = true;
			}			
		}
		else if (playerGesture == GameGesture.LIZARD){//Lizard
			if(compGesture == GameGesture.PAPER || compGesture == GameGesture.SPOCK){
				playerWon = true;
			}			
		}
		else if (playerGesture == GameGesture.SPOCK){//Spock
			if(compGesture == GameGesture.SCISSORS || compGesture == GameGesture.ROCK){
				playerWon = true;
			}			
		}
		
		//Records Data
		if(tie){
			tieGames++;
			gameStatus = "Tie Round";
		}
		else if(playerWon){
			playerWins++;
			gameStatus = "Player Won";
		}
		else{
			compWins++;
			gameStatus = "Computer Won";
		}
		gamesPlayed++;
		
		//Inputs Player info to AI
		smarts.inputGesture(playerGesture);
	}	
	
	/**
	  * getGesture
	  * <p>
	  * Gives a String representation of either the player or computer's current
	  * game gesture.
	  * <p>
	  * Modified from originally two separate getPlayerGesture and getCompGesture 
	  * methods. These methods were combined and reconciled through the 
	  * wantPlayer boolean because of the repeat code in the two methods for
	  * selecting a string representation from the type of current throw.
	  * @param wantPlayer - whether the current game gesture of the player or 
	  * computer is desired
	  * <p>
	  * @return playHand - a String presentation of either the player or computer's 
	  * current game gesture
	  */
	public String getGesture(boolean wantPlayer){
		GameGesture targetGesture = GameGesture.NONE;
		if (wantPlayer){//Desires game throw of player
			targetGesture = playerGesture;
		}
		else {//Desires game throw of computer
			targetGesture = compGesture;
		}
		String playHand = "None";
		if(targetGesture == GameGesture.SCISSORS){
			playHand = "Scissors";
		}
		else if(targetGesture == GameGesture.PAPER){
			playHand = "Paper";
		}
		else if(targetGesture == GameGesture.ROCK){
			playHand = "Rock";
		}
		else if(targetGesture == GameGesture.LIZARD){
			playHand = "Lizard";
		}
		else if(targetGesture == GameGesture.SPOCK){
			playHand = "Spock";
		}
		return playHand;
	}
	
	/**
	  * getIcon
	  * <p>
	  * Gives an Icon image representation of either the player or computer's current
	  * game gesture.
	  * <p>
	  * Modified from originally two separate getPlayerIcon and getCompIcon 
	  * methods. These methods were combined and reconciled through the 
	  * wantPlayer boolean because of the repeat code in the two methods for
	  * selecting an image from the type of current gesture.
	  * <p>
	  * Note that the scissor.jpg, paper.jpg, rock.jpg, lizard.jpg, and spock.jpg
	  * image files are all necessary for this function's purpose.
	  * @param wantPlayer - whether the icon for the current game gesture of the 
	  * player or computer is desired
	  * <p>
	  * @return targetIcon - an icon image of either the player or computer's current
	  * game gesture
	  */
	public ImageIcon getIcon(boolean wantPlayer){
		GameGesture targetGesture = GameGesture.NONE;
		ImageIcon targetIcon = new ImageIcon();
		if (wantPlayer){//Desires game throw of player
			targetGesture = playerGesture;
		}
		else {//Desires game throw of computer
			targetGesture = compGesture;
		}
		if(targetGesture == GameGesture.SCISSORS){
			targetIcon = new ImageIcon("scissor.jpg");
		}
		else if(targetGesture == GameGesture.PAPER){
			targetIcon = new ImageIcon("paper.jpg");
		}
		else if(targetGesture == GameGesture.ROCK){
			targetIcon = new ImageIcon("rock.jpg");
		}
		else if(targetGesture == GameGesture.LIZARD){
			targetIcon = new ImageIcon("lizard.jpg");
		}
		else if(targetGesture == GameGesture.SPOCK){
			targetIcon = new ImageIcon("spock.jpg");
		}
		return targetIcon;		
	}
	
	/**
	  * getGameStatus
	  * <p>
	  * Gives a string declaration of current status of the game.
	  * @return gameStatus - a string representation of the current game status
	  */
	public String getGameStatus(){
		return gameStatus;
	}

	/**
	  * getStats
	  * <p>
	  * Presents and formats all the relevant game statistics.
	  * <p>
	  * Percentage of wins and ties are formatted to 2 decimal places.
	  * @return a String presentation of all the game statistics
	  */
	public String getStats(){
		double percentPlayerWins = (double) 100 * playerWins / (playerWins + compWins);
		double percentTies = (double) 100 * tieGames / gamesPlayed;
        DecimalFormat df = new DecimalFormat("#.##");
        df.format(percentTies);
		return ("Game Statistics:\n\n" + "Games Played: " + gamesPlayed + "\n" + 
				"Games Computer Won: " + compWins + "\n" + "Games Player Won: " + 
				playerWins + "\n" + "Games Tied: " + tieGames + "\n" + 
				"Percentage of Player Wins: " + "(Out of Total Wins): " + 
				df.format(percentPlayerWins) +"%" + "\n" + "Percentage of Ties: " + 
				df.format(percentTies) +"%");
	}
	
}//End of GameHandler class