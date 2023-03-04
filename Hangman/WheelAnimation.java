package WheelAnim;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import jaco.mp3.player.MP3Player;

import javax.swing.JLabel;

public class WheelAnimation extends JFrame {

	private JPanel contentPane;
	JLabel showPoint = new JLabel("");
	Timer mytimer;
	
	public static int point = 0;

	ImageIcon[] icons = { new ImageIcon("C:\\Users\\USER\\Desktop\\GameProject\\GameProject\\img2\\1.JPG"),
			new ImageIcon("C:\\Users\\USER\\Desktop\\GameProject\\GameProject\\img2\\2.JPG"),
			new ImageIcon("C:\\Users\\USER\\Desktop\\GameProject\\GameProject\\img2\\3.JPG"),
			new ImageIcon("C:\\Users\\USER\\Desktop\\GameProject\\GameProject\\img2\\4.JPG"),
			new ImageIcon("C:\\Users\\USER\\Desktop\\GameProject\\GameProject\\img2\\5.JPG"),
			new ImageIcon("C:\\Users\\USER\\Desktop\\GameProject\\GameProject\\img2\\6.JPG"),
			new ImageIcon("C:\\\\Users\\\\USER\\\\Desktop\\\\GameProject\\\\GameProject\\\\img2\\\\7.JPG"),
			new ImageIcon("C:\\Users\\USER\\Desktop\\GameProject\\GameProject\\img2\\8.JPG") };


	JLabel showImage = new JLabel("");
	JButton turnButton = new JButton("TURN");
	JButton stopButton = new JButton("STOP");
	
	
	
	public WheelAnimation() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1600, 900);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		contentPane.add(turnButton);
		turnButton.setBounds(700, 500, 136, 46);
		turnButton.setVisible(true);
		
		contentPane.add(stopButton);
		stopButton.setBounds(1000, 500, 136, 46);
		turnButton.setVisible(true);

		showImage.setIcon(icons[0]);
		contentPane.add(showImage);

		showPoint.setBounds(693, 219, 136, 54);
		this.setVisible(true);
		
		contentPane.add(showPoint); 
		
		turnButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					new MP3Player(new File("C:\\Users\\USER\\Desktop\\GameProject\\GameProject\\src\\sounds\\Spinning Wheel.mp3")).play();
					start();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		stopButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					stop();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
	}
	TimerTask task = new TimerTask() {
		@Override
		public void run() {
			
			for(int i=0;i<50;i++) {
			
			showImage.setIcon(icons[i%7]);//set image
			
			try {
				TimeUnit.MILLISECONDS.sleep(50);//time changing between image
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("ERROR.");
			}
			}
			
			showImage.setBounds(200, 200, 500, 500);
			contentPane.add(showImage);
		}
	};
	
		
	public void start() throws Exception {	
		mytimer = new Timer();
		mytimer.schedule(task,0,1000);
	}
	
	public void stop() throws Exception {
		mytimer.cancel();
		TimeUnit.SECONDS.sleep(3);//wait 3 second
		dispose();//close frame
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawLine(475, 200, 475, 250);
	}
	
	public static void main(String[] args) throws Exception {
		WheelAnimation frame = new WheelAnimation();
	}
	
	
}
