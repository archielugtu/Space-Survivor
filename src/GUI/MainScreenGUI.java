package GUI;


import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JTextArea;

import main.CrewMember;
import main.Food;
import main.GameEnvironment;
import main.MedKit;
import main.Rocketship;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JList;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MainScreenGUI {

	private JFrame mainMenuWindowFrame;
	private GameEnvironment game;
	
	private JTextArea textAreaMemberOne;
	private JTextArea textAreaMemberTwo;
	private JTextArea textAreaMemberThree;
	private JTextArea textAreaMemberFour;
	
	private JList listCrewMedKitInventoryOutposts;
	private JList listCrewFoodInventoryOutpost;
	private JList textAreaPilotList;
	private JTextArea textAreaShipStats;
	
	private JTextArea txtWelcomeBox;
	private JTextArea txtInfoBox;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreenGUI window = new MainScreenGUI();
					window.mainMenuWindowFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public JTextArea getInfoBox() {
		return txtInfoBox;
	}
	
	public void launchSummaryWindow(String gameState) {
		GameSummaryWindow summaryWindow = new GameSummaryWindow(this, gameState);
		closeWindow();
	}
	
	public void closeWindow() {
		mainMenuWindowFrame.dispose();
	}
	
	public void launchConsumeItemWindow(String inventoryName, int memberSelected) {
		mainMenuWindowFrame.disable();
		ConsumeItemWindow itemWindow = new ConsumeItemWindow(this, game, inventoryName, memberSelected);
	}
	
	public void closeConsumeItemWindow(ConsumeItemWindow itemWindow) {
		mainMenuWindowFrame.enable();
		itemWindow.closeWindow();
		listCrewMedKitInventoryOutposts.setListData(game.getCrew().getMedKits().toArray());
		listCrewFoodInventoryOutpost.setListData(game.getCrew().getFoods().toArray());
		updateMembersGUI();
		System.out.println(itemWindow.returnAction());
		updateInfoBox(itemWindow.returnAction());
	}
	
	public void updateWelcomeBox() {
		txtWelcomeBox.setText("Welcome, " + game.getCrew().getName() + "! This is day " + game.getCurrentDay()
		+ " since you crash-landed. \nYou have " + (game.getDays() - game.getCurrentDay())+ " day(s) until your supplies run out.\n"
		+ game.planetAnalysis());
	}
	
	public void updateInfoBox(String update) {
		txtInfoBox.setText(update);
	}
	
	public void updateMembersGUI() {
		textAreaMemberOne.setText(game.getCrew().getMembers().get(0).toString());
		textAreaMemberTwo.setText(game.getCrew().getMembers().get(1).toString());
		if (game.getCrew().getMembers().size() > 2) {
			textAreaMemberThree.setText(game.getCrew().getMembers().get(2).toString());
		}
		if (game.getCrew().getMembers().size() > 3) {
			textAreaMemberFour.setText(game.getCrew().getMembers().get(3).toString());
		}
	}
	
	public int findSelectedButton(ButtonGroup btnMembergrp) {
		Enumeration<AbstractButton> group = btnMembergrp.getElements();
		for (int i = 0; i < game.getCrew().getMembers().size() + 1; i++) {
			AbstractButton button = group.nextElement();
			if (button.isSelected()) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Create the application.
	 */
	public MainScreenGUI() {
		initialize();
	}
	
	public MainScreenGUI(GameEnvironment new_game) {
		game = new_game;
		initialize();
		mainMenuWindowFrame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		game.randomizeTransporter();
		
		mainMenuWindowFrame = new JFrame();
		mainMenuWindowFrame.setTitle("Main Screen");
		mainMenuWindowFrame.setBounds(100, 100, 900, 639);
		mainMenuWindowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainMenuWindowFrame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				listCrewMedKitInventoryOutposts.setListData(game.getCrew().getMedKits().toArray());
				listCrewFoodInventoryOutpost.setListData(game.getCrew().getFoods().toArray());
			}
		});
		tabbedPane.setBounds(10, 176, 869, 399);
		mainMenuWindowFrame.getContentPane().add(tabbedPane);
		
		
		// ----- Crew View -----
		JPanel panelCrew = new JPanel();
		panelCrew.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				updateMembersGUI();
			}
		});
		tabbedPane.addTab("View Crew Members", null, panelCrew, null);
		panelCrew.setLayout(null);
		
		txtInfoBox = new JTextArea();
		txtInfoBox.setBackground(Color.BLACK);
		txtInfoBox.setForeground(Color.RED);
		txtInfoBox.setFont(new Font("Monospaced", Font.BOLD, 14));
		txtInfoBox.setEditable(false);
		txtInfoBox.setBounds(10, 66, 866, 99);
		mainMenuWindowFrame.getContentPane().add(txtInfoBox);
		updateInfoBox("");
		
		txtWelcomeBox = new JTextArea();
		txtWelcomeBox.setForeground(Color.WHITE);
		txtWelcomeBox.setBackground(Color.BLACK);
		txtWelcomeBox.setEditable(false);
		txtWelcomeBox.setFont(new Font("Monospaced", Font.PLAIN, 16));
		txtWelcomeBox.setBounds(10, 0, 866, 71);
		mainMenuWindowFrame.getContentPane().add(txtWelcomeBox);
		updateWelcomeBox();
		
		JLabel lblMainBG = new JLabel("New label");
		lblMainBG.setIcon(new ImageIcon(MainScreenGUI.class.getResource("/Images/black.jpg")));
		lblMainBG.setBounds(0, 0, 886, 602);
		mainMenuWindowFrame.getContentPane().add(lblMainBG);
		
		textAreaMemberThree = new JTextArea();
		textAreaMemberThree.setEditable(false);
		if (game.getCrew().getMembers().size() > 2) {
			textAreaMemberThree.setVisible(true);
			CrewMember member3 = game.getCrew().getMembers().get(2);
			textAreaMemberThree.setText(member3.toString());
		}else {
			textAreaMemberThree.setVisible(false);
		}
		textAreaMemberThree.setBounds(92, 56, 143, 123);
		panelCrew.add(textAreaMemberThree);
		
		
		textAreaMemberOne = new JTextArea();
		textAreaMemberOne.setEditable(false);
		textAreaMemberOne.setBounds(269, 56, 143, 123);
		panelCrew.add(textAreaMemberOne);
		CrewMember member1 = game.getCrew().getMembers().get(0);
		textAreaMemberOne.setText(member1.toString());
//		String stateString1 = "";
//		if (member1.viewHealth() == 0) {
//			stateString1 = "Dead";
//		}else {
//			stateString1 = "Alive";
//		}
//		textAreaMemberOne.setText("Health: " + member1.viewHealth() + 
//				"\nTiredness: " + member1.viewTiredness() + "\nHunger: " + member1.viewHunger() + "\nActions: " 
//				+ member1.viewNumActions() + "\nRepair Value: " + member1.viewRepairValue() + "\nInfect State: " + 
//				member1.viewInfectState() + "\nCurrent State: " + stateString1);
		
		
		textAreaMemberTwo = new JTextArea();
		textAreaMemberTwo.setEditable(false);
		textAreaMemberTwo.setBounds(448, 56, 143, 123);
		panelCrew.add(textAreaMemberTwo);
		CrewMember member2 = game.getCrew().getMembers().get(1);
		textAreaMemberTwo.setText(member2.toString());
//		String stateString2 = "";
//		if (member2.viewHealth() == 0) {
//			stateString2 = "Dead";
//		}else {
//			stateString2 = "Alive";
//		}
//		textAreaMemberTwo.setText("Health: " + member2.viewHealth() + 
//				"\nTiredness: " + member2.viewTiredness() + "\nHunger: " + member2.viewHunger() + "\nActions: " 
//				+ member2.viewNumActions() + "\nRepair Value: " + member2.viewRepairValue() + "\nInfect State: " + 
//				member2.viewInfectState() + "\nCurrent State: " + stateString2);


		textAreaMemberFour = new JTextArea();
		textAreaMemberFour.setEditable(false);
		textAreaMemberFour.setBounds(620, 56, 143, 123);
		panelCrew.add(textAreaMemberFour);
		if (game.getCrew().getMembers().size() > 3) {
			textAreaMemberFour.setVisible(true);
			CrewMember member4 = game.getCrew().getMembers().get(3);
			textAreaMemberFour.setText(member4.toString());
		}else {
			textAreaMemberFour.setVisible(false);
		}
		
		
		JLabel lblMemberThreeName = new JLabel("");
		lblMemberThreeName.setForeground(Color.WHITE);
		lblMemberThreeName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMemberThreeName.setBounds(92, 23, 143, 35);
		panelCrew.add(lblMemberThreeName);
		if (game.getCrew().getMembers().size() > 2) {
			lblMemberThreeName.setVisible(true);
			lblMemberThreeName.setText(game.getCrew().getMembers().get(2).viewName() + ": " +
					game.getCrew().getMembers().get(2).viewType());
		}else {
			lblMemberThreeName.setVisible(false);
		}
		
		JLabel lblMemberOneName = new JLabel("New label");
		lblMemberOneName.setForeground(Color.WHITE);
		lblMemberOneName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMemberOneName.setBounds(269, 23, 143, 35);
		panelCrew.add(lblMemberOneName);
		lblMemberOneName.setText(game.getCrew().getMembers().get(0).viewName() + ": " + 
				game.getCrew().getMembers().get(0).viewType());

		
		JLabel lblMemberTwoName = new JLabel("New label");
		lblMemberTwoName.setForeground(Color.WHITE);
		lblMemberTwoName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMemberTwoName.setBounds(448, 23, 143, 35);
		panelCrew.add(lblMemberTwoName);
		lblMemberTwoName.setText(game.getCrew().getMembers().get(1).viewName() + ": " + 
				game.getCrew().getMembers().get(1).viewType());
		
		JLabel lblMemberFourName = new JLabel("");
		lblMemberFourName.setForeground(Color.WHITE);
		lblMemberFourName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMemberFourName.setBounds(620, 23, 143, 35);
		panelCrew.add(lblMemberFourName);
		if (game.getCrew().getMembers().size() > 3) {
			lblMemberFourName.setVisible(true);
			lblMemberFourName.setText(game.getCrew().getMembers().get(3).viewName() + ": " + 
			game.getCrew().getMembers().get(3).viewType());
		}else {
			lblMemberFourName.setVisible(false);
		}
		
		
		
//-------------------------------------RADIOBUTTONS------------------------------------------------
		JRadioButton btnMember1 = new JRadioButton("Pick");
		btnMember1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnMember1.setBounds(308, 181, 73, 35);
		panelCrew.add(btnMember1);
		
		JRadioButton btnMember2 = new JRadioButton("Pick");
		btnMember2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnMember2.setBounds(481, 181, 73, 35);
		panelCrew.add(btnMember2);
		
		JRadioButton btnMember3 = new JRadioButton("Pick");
		btnMember3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnMember3.setBounds(127, 181, 73, 35);
		panelCrew.add(btnMember3);
		if (game.getCrew().getMembers().size() > 2) {
			btnMember3.setVisible(true);
		}else {
			btnMember3.setVisible(false);
		}
		
		JRadioButton btnMember4 = new JRadioButton("Pick");
		btnMember4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnMember4.setBounds(656, 181, 73, 35);
		panelCrew.add(btnMember4);
		if (game.getCrew().getMembers().size() > 3) {
			btnMember4.setVisible(true);
		}else {
			btnMember4.setVisible(false);
		}
		
		ButtonGroup btnMembergrp = new ButtonGroup();
		btnMembergrp.add(btnMember1);
		btnMembergrp.add(btnMember2);
		btnMembergrp.add(btnMember3);
		btnMembergrp.add(btnMember4);
//-------------------------------------------------------------------------------------------
//------------------------------ACTIONS------------------------------------------------------
		JButton btnEatFood = new JButton("Eat Food");
		btnEatFood.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (game.getCrew().getFoods().size() > 0) {
					
					int memberIndex = findSelectedButton(btnMembergrp);
					if (memberIndex != -1) {
						CrewMember member = game.getMember(memberIndex);
						String result = member.canAct(false);
						if (result.contains("Success")) {
							launchConsumeItemWindow("Crew Food Inventory", memberIndex);
						}
						else {
							txtInfoBox.setText(result);
							
						}
					}
					else {
						txtInfoBox.setText("Please select a member to perform an action!!");
					}
				}
				else {
					txtInfoBox.setText("Sorry, you don't have any foods!!");
				}
			}
		});
		btnEatFood.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEatFood.setBounds(38, 252, 144, 95);
		panelCrew.add(btnEatFood);
		
		JButton btnUseMedkit = new JButton("Use Medkit");
		btnUseMedkit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (game.getCrew().getMedKits().size() > 0) {
					
					int memberIndex = findSelectedButton(btnMembergrp);
					if (memberIndex != -1) {
						String result = game.getMember(memberIndex).canAct(false);
						if (!result.contains("Success")) {
							txtInfoBox.setText(result);
						}
						else {
							launchConsumeItemWindow("Crew MedKit Inventory", memberIndex);
						}
					}
					else {
						txtInfoBox.setText("Please select a member to perform an action!!");
					}
				}
				else {
					txtInfoBox.setText("Sorry, you don't have any medkits!!");
				}
			}
		});
		btnUseMedkit.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnUseMedkit.setBounds(192, 252, 144, 95);
		panelCrew.add(btnUseMedkit);
		
		JButton btnRepairRocketshipShield = new JButton("Repair Shield");
		btnRepairRocketshipShield.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int index = findSelectedButton(btnMembergrp);
				if (index != -1) {
					CrewMember member = game.getMember(index);
					String result = game.repairShip(index);
					if (result.contains("Success")) {
						txtInfoBox.setText(result);
						updateMembersGUI();
					}
					else {
						txtInfoBox.setText(result);
					}
				}
				else {
					txtInfoBox.setText("Please select a member to perform following action!!");
				}
			}
		});
		btnRepairRocketshipShield.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnRepairRocketshipShield.setBounds(346, 252, 144, 95);
		panelCrew.add(btnRepairRocketshipShield);
		
		JButton btnSleep = new JButton("Sleep");
		btnSleep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				int index = findSelectedButton(btnMembergrp);
				if (index != -1) {
					String result = game.memberSleep(index);
					if (result.contains("Success")) {
						txtInfoBox.setText(game.getMember(index).viewName() + " has slept. Tiredness reset to 0.");
						updateMembersGUI();
					}
					else {
						txtInfoBox.setText(result);
					}
				}
				else {
					txtInfoBox.setText("Please select a member to perform following action!!");
				}
				
			}
		});
		btnSleep.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSleep.setBounds(500, 252, 144, 95);
		panelCrew.add(btnSleep);
		
		JButton btnSearchPlanet = new JButton("Search Planet");
		btnSearchPlanet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = findSelectedButton(btnMembergrp);
				String itemFound = game.randomizeSearch(game.getMember(index).viewType());
				String searchReport = game.memberSearchPlanet(index, itemFound);
				if (index != -1) {
					if (searchReport.contains("added") || searchReport.equals("")) {
						String result = "While searching the planet, " + itemFound + " was found."
								+ "\n" + searchReport;
						if (game.getMember(index).viewType().equals("Scout")) {
							result += "\nSince a scout was sent, there was a higher chance of"
									+ " finding a transporter, if not yet found.";
						}
						txtInfoBox.setText(result);
						updateMembersGUI();
						updateWelcomeBox();
					}
					else {
						txtInfoBox.setText(searchReport);
					}
				}
				else {
					txtInfoBox.setText("Please select a member to perform following action!!");
				}
			}
		});
		btnSearchPlanet.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSearchPlanet.setBounds(656, 252, 144, 95);
		panelCrew.add(btnSearchPlanet);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(MainScreenGUI.class.getResource("/Images/earth.jpg")));
		label.setBounds(0, 0, 864, 372);
		panelCrew.add(label);
	
//----------------------------------------------------------------------------------------------

		
		JPanel panelOutpost = new JPanel();
//		panelOutpost.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseEntered(MouseEvent e) {
//				System.out.println("here");
//				listCrewMedKitInventoryOutposts.setListData(game.getCrew().getMedKits().toArray());
//				listCrewFoodInventoryOutpost.setListData(game.getCrew().getFoods().toArray());
//			}
//		});
		tabbedPane.addTab("View Outpost", null, panelOutpost, null);
		panelOutpost.setLayout(null);
		
		JLabel lblCurrentMoney = new JLabel("Crew Money:");
		lblCurrentMoney.setForeground(Color.WHITE);
		lblCurrentMoney.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCurrentMoney.setBounds(314, 56, 229, 27);
		panelOutpost.add(lblCurrentMoney);
		lblCurrentMoney.setText("Crew Money: $" + game.getCrew().getCrewMoney());
		
		JLabel lblOutpostItems = new JLabel("Outpost Items: ");
		lblOutpostItems.setForeground(Color.WHITE);
		lblOutpostItems.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblOutpostItems.setBounds(10, 20, 132, 27);
		panelOutpost.add(lblOutpostItems);
		
		

		JLabel lblCrewsInventory = new JLabel("Crew's Inventory:");
		lblCrewsInventory.setForeground(Color.WHITE);
		lblCrewsInventory.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCrewsInventory.setBounds(572, 20, 282, 27);
		panelOutpost.add(lblCrewsInventory);
		
		
		listCrewMedKitInventoryOutposts = new JList();
		listCrewMedKitInventoryOutposts.setFont(new Font("Tahoma", Font.BOLD, 12));
		listCrewMedKitInventoryOutposts.setBounds(572, 62, 282, 140);
		panelOutpost.add(listCrewMedKitInventoryOutposts);
		listCrewMedKitInventoryOutposts.setVisible(true);
		
		listCrewFoodInventoryOutpost = new JList();
		listCrewFoodInventoryOutpost.setFont(new Font("Tahoma", Font.BOLD, 12));
		listCrewFoodInventoryOutpost.setBounds(572, 213, 282, 147);
		panelOutpost.add(listCrewFoodInventoryOutpost);


		boolean hasHaggler = game.searchType("Haggler");
		
		JList listMedKitList = new JList();
		listMedKitList.setBounds(10, 56, 284, 146);
		panelOutpost.add(listMedKitList);
		DefaultListModel<String> medkitsDefaultListModel = new DefaultListModel<String>();
		for (MedKit med: game.viewOutpost().getMedKit()) {
			if (hasHaggler) {
				med.setCost((int) Math.round(med.getCost() * 0.75));
			}
			medkitsDefaultListModel.addElement(med.toString());
		}
		listMedKitList.setModel(medkitsDefaultListModel);
		
		
		JList listFoodsList = new JList();
		listFoodsList.setBounds(10, 214, 284, 146);
		panelOutpost.add(listFoodsList);
		
		DefaultListModel<String> foodsDefaultListModel = new DefaultListModel<String>();
		for (Food food: game.viewOutpost().getFood()) {
			if (hasHaggler) {
				food.setCost((int) Math.round(food.getCost() * 0.75));
			}
			foodsDefaultListModel.addElement(food.toString());
		}
		listFoodsList.setModel(foodsDefaultListModel);
		
		JButton btnBuyAMedkit = new JButton("Buy medkit!");
		btnBuyAMedkit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int indexMedkit = listMedKitList.getSelectedIndex();
				if (indexMedkit != -1) {
					String buyResult = game.buyItem(1, indexMedkit+1);
					updateInfoBox(buyResult);
					listCrewMedKitInventoryOutposts.setListData(game.getCrew().getMedKits().toArray());
					lblCurrentMoney.setText("Crew Money: $" + Integer.toString(game.getCrew().getCrewMoney()));
				}
				else {
					updateInfoBox("\nPlease select a MedKit item to buy!");
				}
			}
		});
		btnBuyAMedkit.setBounds(306, 156, 132, 35);
		panelOutpost.add(btnBuyAMedkit);
		
		JButton btnBuy = new JButton("Buy food!");
		btnBuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int indexFood = listFoodsList.getSelectedIndex();
				if (indexFood != -1) {
					String buyResult = game.buyItem(1, indexFood+5);
					updateInfoBox(buyResult);
					listCrewFoodInventoryOutpost.setListData(game.getCrew().getFoods().toArray());
					lblCurrentMoney.setText("Crew Money: $" + Integer.toString(game.getCrew().getCrewMoney()));
				}
				else {
					updateInfoBox("Please select a Food item to buy!");
				}
			}
		});
		btnBuy.setBounds(304, 321, 134, 35);
		panelOutpost.add(btnBuy);
	
		
		JLabel lblOutpostBG = new JLabel("");
		lblOutpostBG.setIcon(new ImageIcon(MainScreenGUI.class.getResource("/Images/earth.jpg")));
		lblOutpostBG.setBounds(0, 0, 864, 371);
		panelOutpost.add(lblOutpostBG);
		
		// ----- Rocketship View -----
		
		JPanel RocketshipPanel = new JPanel();
		RocketshipPanel.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentHidden(ComponentEvent e) {
				game.getCrew().getShip().clearPilots(false);
				textAreaPilotList.setListData(game.getCrew().getShip().viewPilots().toArray());
			}
			
			@Override
			public void componentShown(ComponentEvent e) {
				textAreaShipStats.setText(game.getCrew().getShip().printStats() + game.getPieces());
			}
		});
		tabbedPane.addTab("View Rocketship", null, RocketshipPanel, null);
		RocketshipPanel.setLayout(null);
		
		
		JLabel lblShipImage = new JLabel("Ship image");
		lblShipImage.setBounds(538, 28, 287, 321);
		RocketshipPanel.add(lblShipImage);
		
		JLabel lblShipTitle = new JLabel("Ship Title");
		lblShipTitle.setForeground(Color.WHITE);
		lblShipTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblShipTitle.setBounds(20, 10, 287, 38);
		RocketshipPanel.add(lblShipTitle);
		lblShipTitle.setText(game.getCrew().getShip().getShipName());
		
		JLabel lblNewLabel = new JLabel("Move to new Planet: ");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(20, 96, 295, 33);
		RocketshipPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Choose 2 pilots with 1 action remaining:");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(20, 116, 295, 33);
		RocketshipPanel.add(lblNewLabel_1);
		
		JLabel lblErrorMessageShip = new JLabel("Error Message");
		lblErrorMessageShip.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblErrorMessageShip.setForeground(Color.RED);
		lblErrorMessageShip.setBounds(20, 140, 260, 38);
		lblErrorMessageShip.setVisible(false);
		RocketshipPanel.add(lblErrorMessageShip);
		
		textAreaPilotList = new JList();
		textAreaPilotList.setBounds(215, 180, 258, 130);
		RocketshipPanel.add(textAreaPilotList);
		
		JButton btnPilotMember1 = new JButton("Member1");
		btnPilotMember1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Rocketship ship = game.getCrew().getShip();
				
				String result = game.pickPilots(game.getCrew().getShip(), 1);
				txtInfoBox.setText(result);
				
				//update list
				DefaultListModel crewListModel = new DefaultListModel();
				for (CrewMember member: ship.viewPilots()) {
					crewListModel.addElement(member.viewName() + " - " + member.viewType());
				}
				textAreaPilotList.setModel(crewListModel);
			}
		});
		btnPilotMember1.setBounds(20, 184, 183, 33);
		RocketshipPanel.add(btnPilotMember1);
		btnPilotMember1.setText(game.getCrew().getMembers().get(0).viewName());
		
		JButton btnPilotMember2 = new JButton("Member2");
		btnPilotMember2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Rocketship ship = game.getCrew().getShip();
				String result = game.pickPilots(game.getCrew().getShip(), 2);
				txtInfoBox.setText(result);
				
				//update list
				DefaultListModel crewListModel = new DefaultListModel();
				for (CrewMember member: ship.viewPilots()) {
					crewListModel.addElement(member.viewName() + " - " + member.viewType());
				}
				textAreaPilotList.setModel(crewListModel);
			}
		});
		btnPilotMember2.setBounds(20, 228, 183, 33);
		RocketshipPanel.add(btnPilotMember2);
		btnPilotMember2.setText(game.getCrew().getMembers().get(1).viewName());
		
		JButton btnPilotMember3 = new JButton("Member3");
		btnPilotMember3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Rocketship ship = game.getCrew().getShip();
				String result = game.pickPilots(game.getCrew().getShip(), 3);
				txtInfoBox.setText(result);
				
				//update list
				DefaultListModel crewListModel = new DefaultListModel();
				for (CrewMember member: ship.viewPilots()) {
					crewListModel.addElement(member.viewName() + " - " + member.viewType());
				}
				textAreaPilotList.setModel(crewListModel);
			}
		});
		btnPilotMember3.setBounds(20, 272, 183, 33);
		RocketshipPanel.add(btnPilotMember3);
		if (game.getCrew().getMembers().size() > 2) {
			btnPilotMember3.setVisible(true);
			btnPilotMember3.setText(game.getCrew().getMembers().get(2).viewName());
		}else {
			btnPilotMember3.setVisible(false);
		}
		
		
		JButton btnPilotMember4 = new JButton("Member4");
		btnPilotMember4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Rocketship ship = game.getCrew().getShip();
				String result = game.pickPilots(game.getCrew().getShip(), 4);
				txtInfoBox.setText(result);
				
				//update list
				DefaultListModel crewListModel = new DefaultListModel();
				for (CrewMember member: ship.viewPilots()) {
					crewListModel.addElement(member.viewName() + " - " + member.viewType());
				}
				textAreaPilotList.setModel(crewListModel);
			}
		});
		btnPilotMember4.setBounds(20, 316, 183, 33);
		RocketshipPanel.add(btnPilotMember4);
		if (game.getCrew().getMembers().size() > 3) {
			btnPilotMember4.setVisible(true);
			btnPilotMember4.setText(game.getCrew().getMembers().get(3).viewName());
		}else {
			btnPilotMember4.setVisible(false);
		}
		
		JButton btnClearList = new JButton("Clear List");
		btnClearList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Rocketship ship = game.getCrew().getShip();
				ship.clearPilots(false);
				textAreaPilotList.setListData(game.getCrew().getShip().viewPilots().toArray());
			}
		});
		btnClearList.setBounds(213, 322, 102, 23);
		RocketshipPanel.add(btnClearList);
		
		JButton btnMovePlanet = new JButton("Take Off!!");
		btnMovePlanet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Rocketship ship = game.getCrew().getShip();
				String result = game.movePlanets(ship);
				if (result.equals("")) {
					updateInfoBox("Launch failure - not enough pilots!");
				}
				else {
					updateInfoBox(result);
					ship.clearPilots(true);
					textAreaPilotList.setListData(ship.viewPilots().toArray());
					textAreaShipStats.setText(game.getCrew().getShip().printStats() + game.getPieces());
					game.randomizeTransporter();
					updateWelcomeBox();
				}
			}
		});
		btnMovePlanet.setBounds(361, 321, 102, 23);
		RocketshipPanel.add(btnMovePlanet);
		
		textAreaShipStats = new JTextArea();
		textAreaShipStats.setFont(new Font("Dialog", Font.PLAIN, 14));
		textAreaShipStats.setForeground(Color.WHITE);
		textAreaShipStats.setEditable(false);
		textAreaShipStats.setOpaque(false);
		textAreaShipStats.setBounds(254, 28, 219, 66);
		RocketshipPanel.add(textAreaShipStats);
		textAreaShipStats.setText(game.getCrew().getShip().printStats() + game.getPieces());
		
		JLabel labelRocketshipBG = new JLabel("");
		labelRocketshipBG.setIcon(new ImageIcon(MainScreenGUI.class.getResource("/Images/earth.jpg")));
		labelRocketshipBG.setBounds(0, 0, 864, 371);
		RocketshipPanel.add(labelRocketshipBG);
		
		JPanel panel_7 = new JPanel();
		tabbedPane.addTab("Next Day", null, panel_7, null);
		panel_7.setLayout(null);
		
		JButton btnNextDay = new JButton("Next Day");
		btnNextDay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.nextDay();
				txtInfoBox.setText(game.pickEvent(false));
				
				updateWelcomeBox();
				updateMembersGUI();
				String gameState = game.checkGameOver();
				if (game.checkGameOver().equals("lose")) {
					launchSummaryWindow(game.printSummary(true, false));
				}
				else if (game.checkGameOver().equals("win")){
					launchSummaryWindow(game.printSummary(false, true));
				}

			}
		});
		btnNextDay.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNextDay.setBounds(347, 157, 180, 42);
		panel_7.add(btnNextDay);
		
		JLabel labelNextDayBG = new JLabel("");
		labelNextDayBG.setIcon(new ImageIcon(MainScreenGUI.class.getResource("/Images/earth.jpg")));
		labelNextDayBG.setBounds(0, 0, 864, 371);
		panel_7.add(labelNextDayBG);
		

	}
}