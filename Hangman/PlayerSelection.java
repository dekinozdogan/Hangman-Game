package PlayerSelection;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import SelectCategory.CategorySelection;
import StartPage.StartPage;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PlayerSelection extends JFrame {

	private JPanel contentPane;
	
	public PlayerSelection() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 300, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("SINGLE PLAYER");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CategorySelection category = new CategorySelection(1);
				category.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton.setBounds(179, 99, 147, 41);
		contentPane.add(btnNewButton);
		
		JButton previousPage = new JButton("Previous Page");
		previousPage.setBounds(300, 5, 150, 35);
	//	previousPage.setBackground(Color.black);
		contentPane.add(previousPage);
		previousPage.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				StartPage frame = new StartPage();
				frame.setVisible(true);
				setVisible(false);
			}
		});
		
		JButton btnPlayer = new JButton("2 PLAYER");
		btnPlayer.setBounds(181, 157, 145, 41);
		contentPane.add(btnPlayer);
	}

	public static void main(String[] args) {

		PlayerSelection frame = new PlayerSelection();
		frame.setVisible(true);

	}		
}
