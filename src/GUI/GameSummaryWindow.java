package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;

public class GameSummaryWindow {

	private JFrame frameGameSummary;
	private MainScreenGUI main;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameSummaryWindow window = new GameSummaryWindow();
					window.frameGameSummary.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GameSummaryWindow() {
		initialize();
	}
	
	public void updateSummaryArea(String update) {
		textArea.setText(update);
	}
	
	public GameSummaryWindow(MainScreenGUI newMain, String state) {
		main = newMain;
		initialize();
		updateSummaryArea(state);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameGameSummary = new JFrame();
		frameGameSummary.setVisible(true);
		frameGameSummary.setBounds(100, 100, 474, 370);
		frameGameSummary.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameGameSummary.getContentPane().setLayout(null);
		
		JLabel labelSummaryName = new JLabel("");
		labelSummaryName.setText("End Game Summary");
		labelSummaryName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelSummaryName.setBounds(126, 11, 211, 56);
		frameGameSummary.getContentPane().add(labelSummaryName);
		
		textArea = new JTextArea();
		textArea.setBounds(61, 64, 342, 241);
		frameGameSummary.getContentPane().add(textArea);
		updateSummaryArea("");
	}
}
