package GamePlay;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Player.Player;
import SelectCategory.CategorySelection;
import WheelAnim.WheelAnimation;
import jaco.mp3.player.MP3Player;

public class GamePlay extends JFrame {

	private String word;

	private int numberOfPlayer;

	private ArrayList<Player> players;

	private JLabel userName = new JLabel("");
	private JLabel Point = new JLabel("");

	private char[] board;
	private String wordType;
	
	
	private char[] guesses = new char[50];
	private int totalpoint = 0;

	private int countSuccessGuess = 0;
	private int countWrongGuess = 0;
	private int gainPoint = 0;
	private int count = 0;

	private JTextField textField;
	
	private String displayWord;
	private String displayGuess;
	
	private boolean givenHint = true;
	

	private char emptyhang[][] = { { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
							{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
							{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
							{ ' ', ' ', '-', '-', '-', '-', '-', ' ', ' ', ' ' },
							{ ' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' ', ' ' },
							{ ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
							{ ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
							{ ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
							{ ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
							{ ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ' } };
	
	Scanner keyboard = new Scanner(System.in);
	Random rand = new Random();

	

	public GamePlay(int numberOfPlayer, String word, String wordType) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.numberOfPlayer = numberOfPlayer;
		this.word = word;
		this.wordType = wordType;
		
		new MP3Player(new File("C:\\Users\\USER\\Desktop\\GameProject\\GameProject\\src\\sounds\\intro.mp3")).play();
		
		
		//board creation
		this.board = new char[word.length() * 2];
		players = new ArrayList<Player>(this.numberOfPlayer);

		
		System.out.println("Word :" + word);

		initializeGame();

		JButton guess = new JButton("Enter your Guess");
		guess.setBounds(750, 350, 200, 50);
		contentPane.add(guess);
		
		
		
		guess.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				repaint();
				
					new MP3Player(new File("C:\\Users\\USER\\Desktop\\GameProject\\GameProject\\src\\sounds\\click.mp3")).play();
					
					try {
						play();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			
			}
		});
		
		JButton vocab = new JButton("Guess Entire Vocabulary");
		vocab.setBounds(750, 450, 200, 50);
		contentPane.add(vocab);

		vocab.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				repaint();
				String guess = new JOptionPane("Enter your Guess").showInputDialog("Enter your Guess");
              //no hint 
				if (countSuccessGuess == word.length()) {
					System.out.println("Congratulations...");
					JOptionPane.showMessageDialog(null, "Congratulations...\n You successfully find the word.");
					System.out.println("Your point is " + totalpoint);
					JOptionPane.showMessageDialog(null, "Your point is " + totalpoint);
					
					players.get(0).setTotalPoint(players.get(0).getTotalPoint());
					System.out.println(players.get(0).getName() + " " + players.get(0).getTotalPoint());
					
					
					try {
						FileWriter writer = new FileWriter(
								"C:\\\\Users\\\\USER\\\\Desktop\\\\GameProject\\\\GameProject\\\\src\\\\sounds\\LeaderBoard.txt", true);
						PrintWriter outputStream = new PrintWriter(writer);
						outputStream.println(players.get(0).getName() + " " + players.get(0).getTotalPoint());
						outputStream.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
				}
				
			int boardCount = 0;

				for (int i = 0; i < board.length; i++) {
					if (board[i] != '\u0000' && board[i] != '_') {
						boardCount++;
					}
				}
				
				if (boardCount == word.length() * 2) {
					System.out.println();
					JOptionPane.showMessageDialog(null, "*****CONGRATS*****\n Your point is :" + totalpoint);
					JOptionPane.showMessageDialog(null, "*****You WON*****\n");
					System.out.println("You Won");
					System.out.println("Your point is " + totalpoint);
					
					players.get(0).setTotalPoint(players.get(0).getTotalPoint());
					System.out.println(players.get(0).getName() + " " + players.get(0).getTotalPoint());
					
					
					try {
						FileWriter writer = new FileWriter(
								"C:\\Users\\USER\\Desktop\\GameProject\\GameProject\\src\\LeaderBoard.txt", true);
						PrintWriter outputStream = new PrintWriter(writer);
						outputStream.println(players.get(0).getName() + " " + players.get(0).getTotalPoint());
						outputStream.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					//System.exit(0);
				}
				
				
				if (guess.equals(word)) {
					totalpoint += 500;
					players.get(0).setTotalPoint(totalpoint);
					Point.setText("" + players.get(0).getTotalPoint());
					new MP3Player(	
							new File("C:\\\\Users\\\\USER\\\\Desktop\\\\GameProject\\\\GameProject\\\\src\\sounds\\youWin.mp3"))	
									.play();
					new JOptionPane("").showMessageDialog(null, "You won.");
					
					new JOptionPane("").showMessageDialog(null, "Word is "+ word);
					new JOptionPane("").showMessageDialog(null, "Point is "+ totalpoint);
					//System.exit(0);
					
					players.get(0).setTotalPoint(players.get(0).getTotalPoint());
					System.out.println(players.get(0).getName() + " " + players.get(0).getTotalPoint());
					
					
					try {
						FileWriter writer = new FileWriter(
								"C:\\Users\\USER\\Desktop\\GameProject\\GameProject\\src\\\\LeaderBoard.txt", true);
						PrintWriter outputStream = new PrintWriter(writer);
						outputStream.println(players.get(0).getName() + " " + players.get(0).getTotalPoint());
						outputStream.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					
				} else {
					new JOptionPane("Enter your Guess").showMessageDialog(null, "Wrong guess.Try Again..");
				}
			}}
		);
		
		
		JButton previousPage = new JButton("Previous Page");
		previousPage
				.setIcon(new ImageIcon("C:\\\\Users\\\\USER\\\\Desktop\\\\GameProject\\\\GameProject\\\\img2\\previouspage.png"));
		previousPage.setBounds(1320, 4, 200, 50);
		previousPage.setBackground(Color.LIGHT_GRAY);
		contentPane.add(previousPage);
		previousPage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CategorySelection frame = new CategorySelection(1);
				frame.setVisible(true);
				setVisible(false);
			}
		});

		JLabel user = new JLabel("Point\n");
		user.setBounds(1100, 100, 400, 400);
		user.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
		contentPane.add(user);

		Point.setBounds(1100, 300, 100, 100);
		Point.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
		userName.setBounds(1100, 200, 200, 100);
		userName.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
		//userName.setBackground(Color.RED);
		add(userName);
		add(Point);

		setSize(new Dimension(1600, 900));
		setVisible(true);

		String username;
		JOptionPane pane;
		int j = 0;
		do {
//			pane = new JOptionPane("Enter your Name");
			username = JOptionPane.showInputDialog("Enter your Name");
			players.add(new Player(username, 0));
			userName.setText(username);
			Point.setText("" + players.get(0).getTotalPoint());
			j++;
		} while (j < this.numberOfPlayer|| (username.isEmpty() || username.isBlank() || username.matches("[0-9]+")));
//		add(pane);

	}
	//TEST PURPOSES
	public static void main(String[] args) {
		GamePlay game = new GamePlay(1, "llama", "Animal");

		// game.playGame();
	}

	



	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		//casting to 2d from g
		Graphics2D g2d = (Graphics2D) g;
		Stroke stroke = new BasicStroke(12f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
		g2d.setStroke(stroke);
		
		g2d.draw(new Line2D.Double(60, 100, 420, 100));// -
		g2d.draw(new Line2D.Double(60, 100, 60, 600));// | long hang
		g2d.draw(new Line2D.Double(120, 600, 0, 600));// base
	
		
		g2d.setFont(new Font("TimesRoman", Font.PLAIN, 50));
		g2d.drawString(this.wordType, 700, 200);
		g2d.drawString(displayWord, 700, 300);
		g2d.drawString(displayGuess, 700, 700);

		if (countWrongGuess == 1) {
			new MP3Player(new File("C:\\\\Users\\\\USER\\\\Desktop\\\\GameProject\\\\GameProject\\\\src\\sounds\\hang\\rope.mp3"))	
			.play();
			g2d.draw(new Line2D.Double(420, 100, 420, 150));// rope
		} else if (countWrongGuess == 2) {
			g2d.draw(new Line2D.Double(420, 100, 420, 150));// rope
			g2d.drawOval(370, 150, 100, 100);// head
		} else if (countWrongGuess == 3) {
			g2d.draw(new Line2D.Double(420, 100, 420, 150));// rope
			g2d.drawOval(370, 150, 100, 100);// head
			g2d.draw(new Line2D.Double(420, 250, 420, 500));// body
		} else if (countWrongGuess == 4) {
			g2d.draw(new Line2D.Double(420, 100, 420, 150));// rope
			g2d.drawOval(370, 150, 100, 100);// head
			g2d.draw(new Line2D.Double(420, 250, 420, 500));// body
			g2d.draw(new Line2D.Double(420, 300, 500, 380));// right arm

		} else if (countWrongGuess == 5) {
			g2d.draw(new Line2D.Double(420, 100, 420, 150));// rope
			g2d.drawOval(370, 150, 100, 100);// head
			g2d.draw(new Line2D.Double(420, 250, 420, 500));// body
			g2d.draw(new Line2D.Double(420, 300, 500, 380));// right arm
			g2d.draw(new Line2D.Double(420, 300, 340, 380));// left arm
			// right foot
			// left foot
		} else if (countWrongGuess == 6) {
			g2d.draw(new Line2D.Double(420, 100, 420, 150));// rope
			g2d.drawOval(370, 150, 100, 100);// head
			g2d.draw(new Line2D.Double(420, 250, 420, 500));// body
			g2d.draw(new Line2D.Double(420, 300, 500, 380));// right arm
			g2d.draw(new Line2D.Double(420, 300, 340, 380));// left arm
			g2d.draw(new Line2D.Double(420, 500, 500, 580));// right foot
		} else if (countWrongGuess == 7) {
			g2d.draw(new Line2D.Double(420, 100, 420, 150));// rope
			g2d.drawOval(370, 150, 100, 100);// head
			g2d.draw(new Line2D.Double(420, 250, 420, 500));// body
			g2d.draw(new Line2D.Double(420, 300, 500, 380));// right arm
			g2d.draw(new Line2D.Double(420, 300, 340, 380));// left arm
			g2d.draw(new Line2D.Double(420, 500, 500, 580));// right foot
			g2d.draw(new Line2D.Double(420, 500, 340, 580));// right foot
		}

	}



	public void initializeGame() {

		int wordLength = ((word.length() * 2) - 1);
		for (int i = 0; i < wordLength; i++) {
			board[i] = '_';
			board[++i] = ' ';	
			//System.out.print(board[i] + " ");
		}
		
		displayWord = new String(board);
		//we called the paint method.
		repaint();
		displayGuess = "[ " + new String(guesses) + " ]";
		System.out.println();
	}



	public void play() throws Exception {

		System.out.println();
		
		// check if congrats
		congrats();
		//check if game over
		gameOver();

		System.out.println("Guess a letter:(A-Z)");
		String getGuess;
	
		
		do {
			//pane = new JOptionPane("");
			getGuess = JOptionPane.showInputDialog("Enter your Guess");
		} while (getGuess.isEmpty() || getGuess.isBlank() || getGuess.matches("[0-9]+"));
		
		//add(pane);
		
		char guess = getGuess.toLowerCase().charAt(0);

		int alreadyGuess = 0;

		for (int i = 0; i < guesses.length; i++) {
			if (guesses[i] == guess) {
				alreadyGuess = 1;
			}
		}
		
		//not found
		boolean notfound = true;
		int sameLetter = 0;

		if (alreadyGuess != 1) {
			//check if guess in the list, if in the list found = true
			for (int i = 0; i < board.length; i++) {
				if (i < word.length() && word.charAt(i) == guess) {

				
					board[i * 2] = guess;
					countSuccessGuess += 1;
					
					sameLetter++;

					System.out.println("You gain " + (gainPoint) + " points...");
					
					displayWord = new String(board);
					notfound = false;

					repaint();
				}
			}
			
			if (notfound) {
				System.out.println("*****Wrong Guess*****");
				new MP3Player(	
						new File("C:\\\\Users\\\\USER\\\\Desktop\\\\GameProject\\\\GameProject\\\\src\\sounds\\Wrong-answer.mp3"))	
								.play();
				JOptionPane.showMessageDialog(null, "*****Wrong Guess*****");
				countWrongGuess++;
			} else {
				JOptionPane.showMessageDialog(null, "*****Correct Guess*****");
				new MP3Player(new File("C:\\\\Users\\\\USER\\\\Desktop\\\\GameProject\\\\GameProject\\\\src\\sounds\\Correct-answer.mp3")).play();		
				WheelAnimation wheel = new WheelAnimation();
				
				gainPoint = (int) (Math.random()*10) + 1;
				totalpoint += gainPoint * 10 * sameLetter;//10 to 80 
				players.get(0).setTotalPoint(totalpoint);
				Point.setText("" + players.get(0).getTotalPoint());
			}

			// System.out.println("You gain " + (gainPoint) + " points...");
			if (countWrongGuess == 1) {
				emptyhang[5][6] = 'O';
				//repaint();
			} else if (countWrongGuess == 2) {
				emptyhang[6][6] = '|';
				//repaint();
			} else if (countWrongGuess == 3) {
				emptyhang[7][6] = '|';
				//repaint();
			} else if (countWrongGuess == 4) {
				emptyhang[7][7] = '\\';
				//repaint();
			} else if (countWrongGuess == 5) {
				emptyhang[7][5] = '/';
				//repaint();
			} else if (countWrongGuess == 6) {
				emptyhang[8][7] = '\\';
				//repaint();
			} else if (countWrongGuess == 7) {
				emptyhang[8][5] = '/';
				//repaint();
			}

			System.out.println("----------------------------------");
			
			for (int i = 0; i < board.length; i++) {
				System.out.print(board[i] + " ");
			}
			
//			displayWord = new String(board);
//			repaint();
	
			System.out.println();
			System.out.println("CONTINUE TO GUESS");

			printHang();
			// HINT
			
			// hint position
			char choosenHint = 0;

			if (givenHint && (countWrongGuess == 3 || countWrongGuess == 5)) {

				System.out.println("Do you want hint ?(y/n)");
				JOptionPane hintPane = new JOptionPane();
				
				int input = JOptionPane.showConfirmDialog(null, "Do you want hint?", "Hint Message...",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				add(hintPane);
				
				
				System.out.println(input);
				//input= 0 yes
				if (input == 0) {

					boolean again = false;

					do {
						int randomnumber = (int) (Math.random() * word.length());
						// System.out.println("Random number is:" + randomnumber);

						choosenHint = word.charAt(randomnumber);
						// System.out.println("Choosen hint is:" + choosenHint);

						for (int i = 0; i < board.length; i++) {
							if (board[i] == choosenHint) {
								again = true;
								break;
							} else {
								again = false;
							}
						}
					} while (again);

					for (int i = 0; i < word.length(); i++) {
						if (word.charAt(i) == choosenHint) {
							givenHint = false;
							board[i * 2] = choosenHint;
							displayWord = new String(board);
							repaint();
							break;
						}
					}

					for (int i = 0; i < word.length(); i++) {
						System.out.print(board[i] + " ");
					}
					System.out.println();
					int boardCount = 0;

					for (int i = 0; i < board.length; i++) {
						if (board[i] != '\u0000' && board[i] != '_') {
							boardCount++;
						}
					}

					if (boardCount == word.length() * 2) {
						System.out.println();
						JOptionPane.showMessageDialog(null, "*****CONGRATS*****\n Your point is :" + totalpoint);
						JOptionPane.showMessageDialog(null, "*****You WON*****\n");
						System.out.println("You Won");
						System.out.println("Your point is " + totalpoint);
						
						players.get(0).setTotalPoint(players.get(0).getTotalPoint());
						System.out.println(players.get(0).getName() + " " + players.get(0).getTotalPoint());
						
						displayWord = new String(word);
						repaint();
						
						try {
							FileWriter writer = new FileWriter(
									"C:\\\\Users\\\\USER\\\\Desktop\\\\GameProject\\\\GameProject\\\\src\\LeaderBoard.txt", true);
							PrintWriter outputStream = new PrintWriter(writer);
							outputStream.println(players.get(0).getName() + " " + players.get(0).getTotalPoint());
							outputStream.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						
					}
				}
			}
			guesses[count++] = ' ';
			guesses[count++] = guess;
			
			displayGuess = " " + new String(guesses) + " ";
			repaint();

		} else {
			System.out.println("You already guess " + guess);
			JOptionPane.showMessageDialog(null, "You already guess " + guess);
		}

	}
	public void gameOver() {
		if (countWrongGuess == 7) {
			System.out.println("Game Over...");
			
			new MP3Player(new File("C:\\\\Users\\\\USER\\\\Desktop\\\\GameProject\\\\GameProject\\\\src\\sounds\\gameOver.mp3")).play();
			
			JOptionPane.showMessageDialog(null, "Game Over...");
			JOptionPane.showMessageDialog(null, "WORD IS " + word.toUpperCase());
			JOptionPane.showMessageDialog(null, "Your point is " + totalpoint);
			System.out.println("WORD IS " + word.toUpperCase());
			
			players.get(0).setTotalPoint(players.get(0).getTotalPoint());//
			//System.out.println(players.get(0).getName() + " " + players.get(0).getTotalPoint());
			
			
			try {
				FileWriter writer = new FileWriter("C:\\Users\\USER\\Desktop\\GameProject\\GameProject\\src\\\\LeaderBoard.txt", true);
				PrintWriter outputStream = new PrintWriter(writer);
				outputStream.println(players.get(0).getName() + " " + players.get(0).getTotalPoint());
				outputStream.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
	}
	public void congrats() {
		
		if (countSuccessGuess == word.length()) {
			System.out.println("Congratulations...");
			JOptionPane.showMessageDialog(null, "Congratulations...\n You successfully find the word.");
			System.out.println("Your point is " + totalpoint);
			JOptionPane.showMessageDialog(null, "Your point is " + totalpoint);
			
			System.out.println(players.get(0).getName() + " " + players.get(0).getTotalPoint());
			
			displayWord = new String(word);
			repaint();
			
			try {
				//creating new file if file is not exist, if exist append to exist one. true because didnot reset list, append list.
				FileWriter writer = new FileWriter("C:\\Users\\USER\\Desktop\\GameProject\\GameProject\\src\\\\LeaderBoard.txt",true);
				
				PrintWriter outputStream = new PrintWriter(writer);
				outputStream.println(players.get(0).getName() + " " + players.get(0).getTotalPoint());
				outputStream.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	public void printHang() {
		for (int i = 0; i < emptyhang.length; i++) {
			for (int j = 0; j < emptyhang.length; j++) {
				System.out.print(emptyhang[i][j] + " ");
			}
			System.out.println();
		}
	}
}
