package gameutil;
import javax.swing.JFrame;

/**
 * Unrestricted version 
 * A JFrame that has unlimitedGuessingGame panel
 * @author yu hu
 *
 */
public class UnlimitedGuessingGameApplication {
	public static void main(String[] args) {
		JFrame guiFrame;
		
		guiFrame = new JFrame();
		guiFrame.setTitle("Zootopia Unrestricted Guessing Game");		
		// set size
		guiFrame.setSize( 800, 600 );

		guiFrame.add( new UnlimitedGuessingGame() );
		
		guiFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

		// show frame
		guiFrame.setVisible( true );

	}
}
