package SelectCategory;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import GamePlay.GamePlay;
import PlayerSelection.PlayerSelection;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CategorySelection extends JFrame {

	private JPanel contentPane;
	private int numberOfPlayer;

	public CategorySelection(int numberOfPlayer) {
		this.numberOfPlayer = numberOfPlayer;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 300, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton previousPage = new JButton("Previous Page");
		previousPage.setBounds(300, 5, 150, 35);
		// previousPage.setBackground(Color.black);
		contentPane.add(previousPage);
		previousPage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PlayerSelection frame = new PlayerSelection();
				frame.setVisible(true);
				setVisible(false);
			}
		});

		JButton animalButton = new JButton("Animal");

		animalButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String word = WordGenerator.generate("C:\\Users\\USER\\Desktop\\GameProject\\GameProject\\src\\Animalwords.txt");
				GamePlay game = new GamePlay(numberOfPlayer, word, "Animal");
				setVisible(false);	
			}
		});

		animalButton.setBounds(80, 75, 107, 35);
		contentPane.add(animalButton);

		JButton foodButton = new JButton("Food");
		foodButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String word = WordGenerator.generate("C:\\Users\\USER\\Desktop\\GameProject\\GameProject\\src\\Foodwords.txt");
				GamePlay game = new GamePlay(numberOfPlayer, word, "Food");
				setVisible(false);
			}
		});
		foodButton.setBounds(245, 75, 107, 35);
		contentPane.add(foodButton);

		JButton countryButton = new JButton("Country");
		countryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String word = WordGenerator.generate("C:\\Users\\USER\\Desktop\\GameProject\\GameProject\\src\\Countrywords.txt");
				GamePlay game = new GamePlay(numberOfPlayer, word, "Country");
				setVisible(false);
			}
		});
		countryButton.setBounds(80, 134, 107, 35);
		contentPane.add(countryButton);

		JButton nameButton = new JButton("Name");
		nameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String word = WordGenerator.generate("C:\\Users\\USER\\Desktop\\GameProject\\GameProject\\src\\Namewords.txt");
				GamePlay game = new GamePlay(numberOfPlayer, word, "Name");
				setVisible(false);
			}
		});
		nameButton.setBounds(245, 134, 107, 35);
		contentPane.add(nameButton);

		JLabel categoryName = new JLabel("Choose Category");
		categoryName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		categoryName.setBounds(142, 24, 183, 41);
		contentPane.add(categoryName);
	}

	public static void main(String[] args) {
		CategorySelection frame = new CategorySelection(1);
		frame.setVisible(true);
	}
}
