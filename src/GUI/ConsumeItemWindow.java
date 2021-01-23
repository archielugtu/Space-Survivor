package GUI;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;

import main.GameEnvironment;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class ConsumeItemWindow {

	private JFrame itemWindowFrame;
	private MainScreenGUI mainScreen;
	private GameEnvironment game;
	private int memberSelected;
	private String inventoryName;
	private String itemUsed;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsumeItemWindow window = new ConsumeItemWindow();
					window.itemWindowFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public String returnAction() {
		return itemUsed;
	}

	public void closeWindow() {
		itemWindowFrame.dispose();
	}
	
	public void closeConsumeWindow() {
		mainScreen.closeConsumeItemWindow(this);
	}
	
	/**
	 * Create the application.
	 */
	public ConsumeItemWindow() {
		initialize();
	}
	
	public ConsumeItemWindow(MainScreenGUI new_mainScreen, GameEnvironment new_game, String new_inventoryName, int new_memberSelected) {
		mainScreen = new_mainScreen;
		game = new_game;
		inventoryName = new_inventoryName;
		memberSelected = new_memberSelected;
		itemUsed = "";
		initialize();
		itemWindowFrame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		itemWindowFrame = new JFrame();
		itemWindowFrame.setBounds(100, 100, 450, 300);
		itemWindowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		itemWindowFrame.getContentPane().setLayout(null);
		
		JLabel lblInventoryName = new JLabel("InventoryName");
		lblInventoryName.setForeground(Color.WHITE);
		lblInventoryName.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblInventoryName.setBounds(88, 11, 255, 47);
		itemWindowFrame.getContentPane().add(lblInventoryName);
		lblInventoryName.setText(inventoryName);
		
		
		JList listItems = new JList();
		listItems.setBounds(10, 67, 271, 185);
		itemWindowFrame.getContentPane().add(listItems);
		if (inventoryName.contains("MedKit")){
			listItems.setListData(game.getCrew().getMedKits().toArray());
		}else {
			listItems.setListData(game.getCrew().getFoods().toArray());

		}
		
		
		JButton btnUse = new JButton("Use");
		btnUse.setForeground(Color.red);
		btnUse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int itemIndex = listItems.getSelectedIndex();
				String actionResult = "";
				if (inventoryName.contains("MedKit")) {
					actionResult = game.consumeItem(memberSelected, 2, itemIndex+1);
				}else {
					actionResult = game.consumeItem(memberSelected, 1, itemIndex+1);
				}
				itemUsed = actionResult;
				closeConsumeWindow();
			}
		});
		btnUse.setBounds(291, 170, 135, 34);
		itemWindowFrame.getContentPane().add(btnUse);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeConsumeWindow();
			}
		});
		btnCancel.setBounds(291, 215, 135, 34);
		itemWindowFrame.getContentPane().add(btnCancel);
		
		JLabel lblConsumeItemBG = new JLabel("");
		lblConsumeItemBG.setIcon(new ImageIcon(ConsumeItemWindow.class.getResource("/Images/earth.jpg")));
		lblConsumeItemBG.setBounds(0, 0, 436, 263);
		itemWindowFrame.getContentPane().add(lblConsumeItemBG);
	}
}