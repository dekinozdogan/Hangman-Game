package LeaderBoard;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Player.Player;
import StartPage.StartPage;

import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JTextField;

public class LeaderBoard extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {

		LeaderBoard frame = new LeaderBoard();
		frame.setVisible(true);
	}

	public LeaderBoard() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 300, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		ArrayList<Player> userList = new ArrayList<Player>();
		
		// read from file and create player object and add to userlist.
		try {
			File myObj = new File("C:\\Users\\USER\\Desktop\\GameProject\\GameProject\\src\\LeaderBoard.txt");
			Scanner myReader = new Scanner(myObj);//reader ready to read

			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();//read one line 
				String splitarray[] = data.split(" ");
				Player player = new Player(splitarray[0], Integer.parseInt(splitarray[1]));
				userList.add(player);
			}
			myReader.close();//close scanner.
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		JLabel lblNewLabel = new JLabel("LEADERBOARD");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblNewLabel.setBounds(142, 26, 178, 29);
		contentPane.add(lblNewLabel);
		
		//write name and points to the screen.
		JLabel name;
		int j = 1;
		for (int i = 0; i < userList.size(); i++) {

			name = new JLabel((j) + " - " + userList.get(i).getName() + " - " + userList.get(i).getTotalPoint());
			name.setBounds(39, 71, 339 + (i * 50), 30 + (i * 50));
			name.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
			contentPane.add(name);
			j++;
		}

		JButton previousPage = new JButton("Previous");
		previousPage.setBounds(350, 5, 100, 35);
		// previousPage.setBackground(Color.black);
		contentPane.add(previousPage);
		previousPage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				StartPage frame = new StartPage();
				frame.setVisible(true);
				setVisible(false);
			}
		});

		JButton reset = new JButton("Reset List");
		reset.setBounds(350, 50, 100, 35);
		// previousPage.setBackground(Color.black);
		contentPane.add(reset);
		reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PrintWriter writer;
				try {
					writer = new PrintWriter(
							"C:\\Users\\USER\\Desktop\\GameProject\\GameProject\\src\\LeaderBoard.txt");
					writer.print("");//reset
					writer.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

	}
}
