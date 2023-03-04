package StartPage;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import LeaderBoard.LeaderBoard;
import PlayerSelection.PlayerSelection;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StartPage extends JFrame {

	private JPanel contentPane;

	//test
	public static void main(String[] args) {

		StartPage frame = new StartPage();
		frame.setVisible(true);

	}

	public StartPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 300, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// button creation
		JButton startButton = new JButton("START");
		
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlayerSelection selection = new PlayerSelection();
				selection.setVisible(true);
				setVisible(false);
			}
		});
		startButton.setBounds(155, 32, 128, 49);
		contentPane.add(startButton);
		
		JButton btnLeaderBoard = new JButton("LEADER BOARD");
		btnLeaderBoard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LeaderBoard leaderBoard = new LeaderBoard();
				leaderBoard.setVisible(true);
				setVisible(false);
			}
		});
		btnLeaderBoard.setBounds(155, 108, 128, 49);
		contentPane.add(btnLeaderBoard);
		
		
		JButton btnExt = new JButton("EXIT");
		btnExt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExt.setBounds(155, 181, 128, 49);
		contentPane.add(btnExt);
	}

}
