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
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import database.BinaryTree;
import database.BinaryTreeNode;
import database.DefaultBinaryTree;
import database.DefaultBinaryTreeNode;

/**
 * Guessing Game is a gui panel. It has new class ImageIcon and scrollPane
 * QuestionUpdate.xml for unrestrictedGuessingGame
 * 
 * @author yu hu
 *
 */
@SuppressWarnings("serial")
public class UnlimitedGuessingGame extends JPanel {

	//images panel
	private ImageIcon frontImage;
	private ImageIcon frontImage1;
	private ImageIcon frontImage2;
	private JPanel images = new JPanel(new GridLayout(3, 1));

	
	//question panel
	private JLabel question = new JLabel("");
	private JButton yes = new JButton("Yes");
	private JButton no = new JButton("No");
	private JButton restart = new JButton("Restart");
	
	//initialize game binTree
	private BinaryTree<String> game = new DefaultBinaryTree<String>();
	private BinaryTreeNode<String> currentNode = new DefaultBinaryTreeNode<String>();

	
	//question panel
	private JPanel center;
	private String name;
	private String inputQuestion;
	private String YN;

	private BinaryTreeNode<String> parentNode = new DefaultBinaryTreeNode<String>();
	private boolean isLeft = true;

	private JTextField inputChar = new JTextField();
	private JButton enterChar = new JButton("Enter");

	private JTextField inputQ = new JTextField();
	private JButton enterQ = new JButton("Enter");

	private JTextField inputYN = new JTextField();
	private JButton enterYN = new JButton("Enter");

	public UnlimitedGuessingGame() {

		JScrollPane scroll = new JScrollPane(images);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		// scrollPane
		add(scroll);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		game = questionReader.readQuestion("QuestionUpdate.xml");
		currentNode = game.getRoot();

		// add image
		frontImage = new ImageIcon(getClass()
				.getResource("zootopiaposter1.jpg"));
		JLabel image = new JLabel(frontImage);
		images.add(image);
		frontImage1 = new ImageIcon(getClass().getResource("char1.JPG"));
		JLabel image1 = new JLabel(frontImage1);
		images.add(image1);

		frontImage2 = new ImageIcon(getClass().getResource("char2.JPG"));
		JLabel image2 = new JLabel(frontImage2);
		images.add(image2);

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		game = questionReader.readQuestion("QuestionUpdate.xml");
		currentNode = game.getRoot();

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

		
		//yes button get left child
		yes.addActionListener(new ActionListener() {
			@Override
			/**
			 * getLeftChild
			 */
			public void actionPerformed(ActionEvent arg0) {

				//not leaf
				if (currentNode.getLeftChild() != null) {
					parentNode = currentNode;
					isLeft = true;
					currentNode = currentNode.getLeftChild();
					question.setText(currentNode.getData());
				} 
				//leaf
				else 
				{
					question.setText("I'm right! Hit restart to play another round!");
				}
			}
		});

		
		//no button get right child
		no.addActionListener(new ActionListener() {
			@Override
			/**
			 * no button get right Child. if the node is the leaf, learn from the user a new character
			 */
			public void actionPerformed(ActionEvent arg0) {
				if (currentNode.getRightChild() != null) {
					parentNode = currentNode;
					isLeft = false;
					currentNode = currentNode.getRightChild();
					question.setText(currentNode.getData());

				} else {

					question.setText("What characters are you thinking of?");

					// add new jtextfield and jbutton
					add(inputChar);
					add(enterChar);
					validate();
					repaint();

					enterChar.addActionListener(new ActionListener() {

						@Override
						/**
						 * enterChar get the input character
						 */
						public void actionPerformed(ActionEvent e) {
							name = inputChar.getText();
							remove(inputChar);
							revalidate();
							remove(enterChar);
							revalidate();

							question.setText("Please give me a yes/no question that would have determined your thing.");

							add(inputQ);
							validate();
							add(enterQ);
							validate();
							repaint();

							enterQ.addActionListener(new ActionListener() {

								@Override
								/**
								 * enterQ get the input question
								 */
								public void actionPerformed(ActionEvent e) {
									// get the question
									inputQuestion = inputQ.getText();

									// remove the jtextfield and jbutton
									remove(inputQ);
									revalidate();
									remove(enterQ);
									revalidate();

									// set new question
									question.setText("Is the answer to your question yes or no?");

									// set new jtextfield and jbutton
									add(inputYN);
									validate();
									add(enterYN);
									validate();
									repaint();

									enterYN.addActionListener(new ActionListener() {

										@Override
										/**
										 * enterYN get yes or no for the input question
										 */
										public void actionPerformed(ActionEvent e) {
											
											// get yes and no
											YN = inputYN.getText();
											inputYN.setText("");

											// create a new node with the
											// question as the value
											BinaryTreeNode<String> newNode = new DefaultBinaryTreeNode<String>();
											newNode.setData(inputQuestion);

											// create a leaf
											BinaryTreeNode<String> child = new DefaultBinaryTreeNode<String>();
											child.setData(name);

											if (isLeft) 
											{
												parentNode.setLeftChild(newNode);
											} 
											else 
												
											{
												parentNode
														.setRightChild(newNode);
											}

											if (YN.equals("yes") || YN.equals("Yes")) 
											{
												newNode.setLeftChild(child);
												newNode.setRightChild(currentNode);
											} 
											
											else if (YN.equals("No") || YN.equals("no")) 
											{
												newNode.setRightChild(child);
												newNode.setLeftChild(currentNode);
											}

											// write xml file
											questionWritter.writeQuestion(game,"QuestionUpdate.xml");

											// tell user that the question is
											// added
											question.setText("your question has been added successfully! Hit restart to play again!");

										}

									});

								}
							});

						}

					}

					);
				}
			}
		});

		restart.addActionListener(new ActionListener() {
			@Override
			/**
			 * restart the game
			 */
			public void actionPerformed(ActionEvent arg0) {
				restart();
			}
		});

	}

	public void restart() {
		currentNode = game.getRoot();
		question.setText(currentNode.getData());

		// in case that the user hit restart button when answering question

		name = "";
		inputQuestion = "";
		YN = "";

		// empty the text field
		inputYN.setText("");
		inputChar.setText("");
		inputQ.setText("");

		// remove the textfield and buttons
		remove(inputYN);
		remove(enterYN);
		remove(inputChar);
		remove(enterChar);
		remove(inputQ);
		remove(enterQ);
		revalidate();
		repaint();
	}
}
