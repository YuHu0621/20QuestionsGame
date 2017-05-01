package gameutil;

import javax.swing.JFrame;

/**
 * Guessing Game Frame that has a guessing game panel
 * not unrestricted
 * 
 * @author yu hu
 *
 */
public class GuessingGameApplication {

	public static void main(String[] args) {
		JFrame guiFrame;
		
		guiFrame = new JFrame();
		guiFrame.setTitle("Guessing Game");		
		// set size
		guiFrame.setSize( 800, 600 );

		guiFrame.add( new GuessingGame() );
		
		guiFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

		// show frame
		guiFrame.setVisible( true );

		
	}

}
