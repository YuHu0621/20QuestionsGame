package gameutil;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import database.BinaryTree;
import database.BinaryTreeNode;
import database.DefaultBinaryTree;
import database.DefaultBinaryTreeNode;

/**
 * Guessing Game is a gui panel. It has new class ImageIcon and scrollPane
 * 
 * @author yu hu
 *
 */
@SuppressWarnings("serial")
public class GuessingGame extends JPanel {
	
	//imageIcon
	private ImageIcon frontImage;
	private ImageIcon frontImage1;
	private ImageIcon frontImage2;
	private JPanel images = new JPanel(new GridLayout(3,1));
	
	//buttons
	private JLabel question = new JLabel("");
	private JButton yes = new JButton("Yes");
	private JButton no = new JButton("No");
	private JButton restart = new JButton("Restart");
	
	//instant variable
	private BinaryTree<String> game = new DefaultBinaryTree<String>();
	private BinaryTreeNode<String> currentNode = new DefaultBinaryTreeNode<String>();
	
	private JPanel center;

	public GuessingGame() {
		
		//add a scrollPane
		JScrollPane scroll = new JScrollPane(images);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.
		                                   VERTICAL_SCROLLBAR_ALWAYS);

		
		add(scroll);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		game = questionReader.readQuestion("Question.xml");
		currentNode = game.getRoot();

		// add image
		
		frontImage = new ImageIcon(getClass().getResource("zootopiaposter1.jpg"));
		JLabel image = new JLabel(frontImage);
		images.add(image);
		frontImage1 = new ImageIcon(getClass()
				.getResource("char1.JPG"));
		JLabel image1 = new JLabel(frontImage1);
		images.add(image1);

		frontImage2 = new ImageIcon(getClass()
				.getResource("char2.JPG"));
		JLabel image2 = new JLabel(frontImage2);
		images.add(image2);
		
		
		// add question
		question.setText(currentNode.getData());
		add(question);

		// add yes/no buttons panel
		center = new JPanel(new GridLayout(1, 3));
		add(question);

		center.add(yes);
		center.add(no);
		center.add(restart);
		add(center);

		
		//yes button goes to left child
		yes.addActionListener(new ActionListener() {
			@Override
			/**
			 * getLeftChild
			 */
			public void actionPerformed(ActionEvent arg0) {

				if (currentNode.getLeftChild() != null) 
				{
					currentNode = currentNode.getLeftChild();
					question.setText(currentNode.getData());
				}else{
					//if guess right
					if(!question.getText().equals("I'm sorry. I don't know your character :("))
						question.setText("Yeah! I'm right!");
				}
				
			}
		});

		
		//no button goes to right child
		no.addActionListener(new ActionListener() {
			@Override
			/**
			 * 
			 */
			public void actionPerformed(ActionEvent arg0) {
				
				if (currentNode.getRightChild() != null) 
				{
					currentNode = currentNode.getRightChild();
					question.setText(currentNode.getData());
				} 
				else{
					//if guess wrong
					if(!question.getText().equals("Yeah! I'm right!"))
						question.setText("I'm sorry. I don't know your character :(");
				}
			}
		});

		//restart will restart the game
		restart.addActionListener(new ActionListener() {
			@Override
			/**
			 * restart the game
			 */
			public void actionPerformed(ActionEvent arg0) {
				currentNode = game.getRoot();
				question.setText(currentNode.getData());
				
			}
		});

	}
}
