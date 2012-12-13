import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * <b>GamePanel Class</b>
 * <p>
 * Creates and maintains a visual GUI for the Scissors, Paper, Rock, Lizard,
 * Spock game.
 * @author James Wen - jrw2175
 */
public class GamePanel extends JPanel
{
	private JButton playSPRLK;
	private JButton rulesGuide;
	private JButton endGame;
	
	private JLabel playerGesture;
	private JLabel compGesture;
	private JLabel gameResult;
	private JLabel playerLabel;
	private JLabel compLabel;

	private ButtonGroup playerOptions;
	private JCheckBox scissors;
	private JCheckBox paper;
	private JCheckBox rock;
	private JCheckBox lizard;
	private JCheckBox spock;
	
	private GameHandler gameOperator;
	
	/**
	 * Constructs a SPRLK visual GUI panel through a variety of frames, layouts 
	 * styles, labels, buttons, and borders.
	 * <p>
	 * Distinctive red borders are used to distinguish between the different primary
	 * panels of the top bar, bottom bar, game info section, visual gesture section,
	 * and play option sections. The purpose of this is to divide up the sections
	 * by functionality. To play the game, all the player would need to do is really
	 * click CheckBoxes and hit the "Play SPRLK" on the right side of the GUI. 
	 * Any game info and the "Rules Guide" are on the left side. The player can exit
	 * the game and look over the game statistics through the "End Game" button in
	 * the bottom left corner.
	 * <p>
	 * Titled borders are also used to further distinguish the game info section and
	 * the player option sections. The aim of the "Player Options" title is to 
	 * indicate to the player that the Checkboxes are for him/her and are how he/she
	 * chooses which gesture to throw.
	 */
	public GamePanel(){
		
		gameOperator = new GameHandler();
		setLayout (new BorderLayout());
		
		//Creates all the required checkboxes and configures the button group
		scissors = new JCheckBox ("Scissors");
		paper = new JCheckBox ("Paper");		
		rock = new JCheckBox ("Rock");
		lizard = new JCheckBox ("Lizard");
		spock = new JCheckBox ("Spock");
		playerOptions = new ButtonGroup();
		playerOptions.add(scissors);
		playerOptions.add(paper);
		playerOptions.add(rock);
		playerOptions.add(lizard);
		playerOptions.add(spock);
		
		//Creates and configures all the required buttons
		playSPRLK = new JButton ("Play SPRLK");
		playSPRLK.addActionListener(new PlayListener());
		rulesGuide = new JButton ("Rules Guide");
		rulesGuide.addActionListener(new NewRulesListener());
		endGame = new JButton ("End Game");
		endGame.addActionListener(new EndGameListener());
		
		//Creates and configures all the required labels
		playerGesture = new JLabel ("Player gestures: None");
		compGesture = new JLabel ("Computer gestures: None");
		gameResult = new JLabel ("Game Status: Waiting on Player");
		playerLabel = new JLabel ("Player Gesture", SwingConstants.CENTER);
		compLabel = new JLabel ("Comp Gesture", SwingConstants.CENTER);
		
		//Creates all the subpanels that create the GUI
		JPanel topBar = new JPanel();
		JPanel bottomBar = new JPanel();
		JPanel gameInfoSec = new JPanel();
		JPanel gameInfo = new JPanel();
		JPanel gameInfoPadding = new JPanel();
		JPanel gesturePanel = new JPanel();
		JPanel gestureOptions = new JPanel();
		JPanel gesturePadding = new JPanel();
		JPanel versusGraphics = new JPanel(new GridLayout(0,2));

		//Sets the layouts of the panels
		topBar.setLayout (new BoxLayout (topBar, BoxLayout.X_AXIS));
		bottomBar.setLayout (new BoxLayout (bottomBar, BoxLayout.X_AXIS));
		gameInfoSec.setLayout (new BoxLayout (gameInfoSec, BoxLayout.X_AXIS));
		gameInfo.setLayout (new BoxLayout (gameInfo, BoxLayout.Y_AXIS));
		gameInfoPadding.setLayout (new BoxLayout (gameInfoPadding, BoxLayout.Y_AXIS));
		gesturePanel.setLayout (new BoxLayout (gesturePanel, BoxLayout.X_AXIS));
		gestureOptions.setLayout (new BoxLayout (gestureOptions, BoxLayout.Y_AXIS));
		gesturePadding.setLayout (new BoxLayout (gesturePadding, BoxLayout.Y_AXIS));
		
		//Creates the Top Bar Area
		topBar.add(rulesGuide);
		topBar.add(Box.createRigidArea (new Dimension (0, 10)));
		topBar.add(Box.createHorizontalGlue());

		gameInfoPadding.add(Box.createRigidArea(new Dimension(10,5)));
		gameInfo.add(playerGesture);
		gameInfo.add(Box.createRigidArea(new Dimension(10,10)));
		gameInfo.add(compGesture);
		gameInfo.add(Box.createRigidArea(new Dimension(10,10)));
		gameInfo.add(gameResult);
		gameInfo.add(Box.createRigidArea(new Dimension(10,10)));
		gameInfoSec.add(gameInfo);
		gameInfoSec.add(gameInfoPadding);

		//Creates the Center Graphic Area
		playerLabel.setHorizontalTextPosition (SwingConstants.CENTER);
		playerLabel.setVerticalTextPosition (SwingConstants.TOP);
		compLabel.setHorizontalTextPosition (SwingConstants.CENTER);
		compLabel.setVerticalTextPosition (SwingConstants.TOP);
		versusGraphics.add(playerLabel);
		versusGraphics.add(compLabel);
		
		//Creates the Player Options Area
		gestureOptions.add(scissors);
		gestureOptions.add(paper);
		gestureOptions.add(rock);
		gestureOptions.add(lizard);
		gestureOptions.add(spock);
		gesturePadding.add((Box.createRigidArea(new Dimension(10,5))));
		gesturePanel.add(gesturePadding);
		gesturePanel.add(gestureOptions);
		gesturePanel.add((Box.createRigidArea(new Dimension(3,5))));

		//Creates the Bottom Bar Area
		bottomBar.add(endGame);
		bottomBar.add(Box.createHorizontalGlue());
		bottomBar.add(playSPRLK);		

		//Sets the Area background colors
		Color cuBlue = new Color(117, 178, 221);
		Color darkerBlue = new Color(0, 88, 169);
		
		//Sets the Area backgrounds
		topBar.setBackground(darkerBlue);
		scissors.setBackground(cuBlue);
		paper.setBackground(cuBlue);
		rock.setBackground(cuBlue);
		lizard.setBackground(cuBlue);
		spock.setBackground(cuBlue);
		gameInfo.setBackground(cuBlue);
		gameInfoPadding.setBackground(cuBlue);
		gameInfoSec.setBackground(cuBlue);
		gesturePadding.setBackground(cuBlue);
		gestureOptions.setBackground(cuBlue);
		gesturePanel.setBackground(cuBlue);
		bottomBar.setBackground(darkerBlue);
		
		//Creates the Area borders
		topBar.setBorder (BorderFactory.createLineBorder (Color.red, 1));
		gameInfo.setBorder (BorderFactory.createTitledBorder ("Game Info"));
		gameInfoSec.setBorder (BorderFactory.createLineBorder (Color.red, 1));
		gestureOptions.setBorder (BorderFactory.createTitledBorder ("Play Options"));
		gesturePanel.setBorder (BorderFactory.createLineBorder (Color.red, 1));
		bottomBar.setBorder (BorderFactory.createLineBorder (Color.red, 1));
		
		//Adds the composite frames to the main panel
		add(topBar, BorderLayout.NORTH);
		add(gameInfoSec, BorderLayout.WEST);
		add(versusGraphics, BorderLayout.CENTER);
		add(gesturePanel, BorderLayout.EAST);
		add(bottomBar, BorderLayout.SOUTH);
		
		//Sets panel size
		setPreferredSize (new Dimension (700,220));
	}
	
	/**
	 * updateGame
	 * <p>
	 * Updates all labels and icons to represent current state of gestures and game.
	 */
	private void updateGame(){
		playerGesture.setText("Player Gestures: " + gameOperator.getGesture(true));
		compGesture.setText("Computer Gestures: " + gameOperator.getGesture(false));
		gameResult.setText("Game Status: " + gameOperator.getGameStatus());
		playerLabel.setIcon(gameOperator.getIcon(true));
		compLabel.setIcon(gameOperator.getIcon(false));
	}
	
	/**
	 * <b>NewRulesListener Class</b>
	 * <p>
	 * An action listener that initiates a popup message with the game rules when 
	 * the Rules Guide button is clicked.
	 * <p>
	 * Rules are taken from the wikipedia article for SPRLK and formatting is done
	 * primarily with new line chars and spacing.
	 * @author James Wen - jrw2175
	 */
	private class NewRulesListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			String helpMessage = "The basic rules of Scissors-Paper-Rock-Lizard-" +
					"Spock are as follows: \n\n- Scissors cuts paper\n- Paper " +
					"covers rock\n- Rock crushes lizard\n- Lizard poisons Spock\n" +
					"-Spock smashes scissors\n- Scissors decapitates lizard\n- " +
					"Lizard eats paper\n- Paper disproves Spock\n- Spock vaporizes" +
					" rock\n- Rock crushes scissors\n\nFor more information, " +
					"please refer to: http://en.wikipedia.org/wiki/Rock-paper-" +
					"scissors-lizard-Spock\n\nHave fun playing!";
			JOptionPane.showMessageDialog(null, helpMessage);
		}
	}

	/**
	 * <b>PlayListener Class</b>
	 * <p>
	 * An action listener that takes note of the gesture that the player has 
	 * selected and initiates a round of SPRLK against the computer. Also updates
	 * the GUI labels.
	 * @author James Wen - jrw2175
	 */
	private class PlayListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			GameGesture playerGesture = GameGesture.NONE;
			if(scissors.isSelected()){
				playerGesture = GameGesture.SCISSORS;
			}
			if(paper.isSelected()){
				playerGesture = GameGesture.PAPER;
			}
			if(rock.isSelected()){
				playerGesture = GameGesture.ROCK;
			}
			if(lizard.isSelected()){
				playerGesture = GameGesture.LIZARD;
			}
			if(spock.isSelected()){
				playerGesture = GameGesture.SPOCK;
			}
			gameOperator.playGame(playerGesture);
			updateGame();
		}
	}

	/**
	 * <b>EndGameListener Class</b>
	 * <p>
	 * An action listener that initiates a popup message with the overall game
	 * statistics when the End Game button is clicked. When the popup message is 
	 * closed, the game also automatically closes.
	 * @author James Wen - jrw2175
	 */
	private class EndGameListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			JOptionPane.showMessageDialog(null, gameOperator.getStats());
			System.exit(0);//Exits window and program
		}
	}
	
} //End of GamePanel class