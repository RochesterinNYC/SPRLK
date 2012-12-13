import javax.swing.JFrame;

/**
 * <b>SPRLKGame Class</b>
 * <p>
 * Creates a Scissors, Paper, Rock, Lizard, Spock game with a visual GUI.
 * @author James Wen - jrw2175
 */

public class SPRLKGame {
	public static void main(String[] args){
		JFrame SPRLKFrame = new JFrame("Scissors, Paper, Rock, Lizard, Spock");
		SPRLKFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SPRLKFrame.getContentPane().add(new GamePanel());
		SPRLKFrame.pack();
		SPRLKFrame.setVisible(true);
	}
}