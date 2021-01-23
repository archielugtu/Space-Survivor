package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;

import main.GameEnvironment;

public class OpenScreenGUI {

	private JFrame frmWelcomeScreen;
	private GameEnvironment game;
	
	public OpenScreenGUI(GameEnvironment new_game) {
		game = new_game;
		initialize();
		frmWelcomeScreen.setVisible(true);
	}
	
	public void launchSetupScreenGUI() {
		SetUpScreenGUI setupGui = new SetUpScreenGUI(game);
		closeWindow();
	}
	
	public void closeWindow() {
		frmWelcomeScreen.dispose();
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OpenScreenGUI window = new OpenScreenGUI();
					window.frmWelcomeScreen.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public OpenScreenGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmWelcomeScreen = new JFrame();
		frmWelcomeScreen.setTitle("Welcome Screen");
//		frmWelcomeScreen.setIconImage(Toolkit.getDefaultToolkit().getImage(OpenScreenGUI.class.getResource("src/Images/rocketshippng_225.png")));

//		frmWelcomeScreen.setIconImage(Toolkit.getDefaultToolkit().getImage(OpenScreenGUI.class.getResource("/Images/rocketshippng_225.png")));
		frmWelcomeScreen.setBounds(100, 100, 900, 640);
		frmWelcomeScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmWelcomeScreen.getContentPane().setLayout(null);
		
		JLabel lblSpaceSurvival = new JLabel("Space Survival");
		lblSpaceSurvival.setHorizontalAlignment(SwingConstants.CENTER);
		lblSpaceSurvival.setForeground(Color.WHITE);
		lblSpaceSurvival.setFont(new Font("Dialog", Font.PLAIN, 77));
		lblSpaceSurvival.setBounds(130, 54, 659, 152);
		frmWelcomeScreen.getContentPane().add(lblSpaceSurvival);
		
		JLabel lblGame = new JLabel("Game");
		lblGame.setHorizontalAlignment(SwingConstants.CENTER);
		lblGame.setForeground(Color.WHITE);
		lblGame.setFont(new Font("Dialog", Font.PLAIN, 77));
		lblGame.setBounds(305, 167, 257, 152);
		frmWelcomeScreen.getContentPane().add(lblGame);
		
		JButton btnPlay = new JButton("");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				launchSetupScreenGUI();
				
			}
		});
		btnPlay.setIcon(new ImageIcon(OpenScreenGUI.class.getResource("/Images/rocket-icon.png")));
		btnPlay.setBounds(382, 348, 100, 105);
		frmWelcomeScreen.getContentPane().add(btnPlay);
		
		JLabel lblBackgroundImageLabel = new JLabel("");
		lblBackgroundImageLabel.setIcon(new ImageIcon(OpenScreenGUI.class.getResource("/Images/earth.jpg")));
		lblBackgroundImageLabel.setBounds(0, 0, 886, 603);
		frmWelcomeScreen.getContentPane().add(lblBackgroundImageLabel);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(0, 0, 886, 603);
		frmWelcomeScreen.getContentPane().add(lblNewLabel);
	}
}