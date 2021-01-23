package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JSlider;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;

import main.CrewMember;
import main.GameEnvironment;

public class SetUpScreenGUI {

	private JFrame setUpScreenFrame;
	private JTextField crewNameTextField;
	private JTextField shipNameTextField;
	private GameEnvironment game;
	private OpenScreenGUI openScreen;
	private JList crewMemberJList;
	private JLabel lblErrorLabel;
	
	
	public void launchDescriptionWindow(int index) {
		setUpScreenFrame.disable();
		MemberDescriptionGUI descWindow = new MemberDescriptionGUI(this, game, index);
	}
	
	public void finishDescriptionWindow(MemberDescriptionGUI descriptionGUI) {
		setUpScreenFrame.enable();
		descriptionGUI.closeWindow();
		DefaultListModel crewListModel = new DefaultListModel();
		for (CrewMember member: game.getCrew().getMembers()) {
			crewListModel.addElement(member.viewName() + " - " + member.viewType());
		}
		crewMemberJList.setModel(crewListModel);
	}
	
	public void launchMainMenuWindow() {
		MainScreenGUI mainMenuGui = new MainScreenGUI(game);
		setUpScreenFrame.dispose();
	}
	
	public boolean checkCrewSizeError() {
		if (game.getCrew().getMembers().size()+1 > 4) {
			return true;
		}
		return false;
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SetUpScreenGUI window = new SetUpScreenGUI();
					window.setUpScreenFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SetUpScreenGUI() {
		initialize();
	}
	
	/**
	 * Create the application.
	 */
	public SetUpScreenGUI(GameEnvironment newGame) {
		game = newGame;
		initialize();
		setUpScreenFrame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setUpScreenFrame = new JFrame();
		setUpScreenFrame.setBounds(100, 100, 900, 630);
		setUpScreenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUpScreenFrame.getContentPane().setLayout(null);
		
		JLabel lblSe = new JLabel("Set Up Game");
		lblSe.setHorizontalAlignment(SwingConstants.CENTER);
		lblSe.setForeground(Color.WHITE);
		lblSe.setFont(new Font("Dialog", Font.PLAIN, 55));
		lblSe.setBounds(238, 11, 428, 77);
		setUpScreenFrame.getContentPane().add(lblSe);
		
		JLabel lblNameYourCrew = new JLabel("Name your Crew: ");
		lblNameYourCrew.setForeground(Color.WHITE);
		lblNameYourCrew.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblNameYourCrew.setBounds(31, 126, 185, 38);
		setUpScreenFrame.getContentPane().add(lblNameYourCrew);
		
		JLabel lblNameYourShip = new JLabel("Name your ship: ");
		lblNameYourShip.setForeground(Color.WHITE);
		lblNameYourShip.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblNameYourShip.setBounds(31, 177, 185, 38);
		setUpScreenFrame.getContentPane().add(lblNameYourShip);
		
		// JList
		crewMemberJList = new JList();
		crewMemberJList.setBounds(563, 126, 296, 189);
		setUpScreenFrame.getContentPane().add(crewMemberJList);
		
		DefaultListModel crewListModel = new DefaultListModel();
		for (CrewMember member: game.getCrew().getMembers()) {
			crewListModel.addElement(member.viewName() + " - " + member.viewType());
		}
		crewMemberJList.setModel(crewListModel);

//		crewMemberJList.setListData(game.getCrew().getMembers().toArray());
		
		
		
		
		crewNameTextField = new JTextField();
		crewNameTextField.setBounds(238, 137, 219, 25);
		setUpScreenFrame.getContentPane().add(crewNameTextField);
		crewNameTextField.setColumns(10);
		
		shipNameTextField = new JTextField();
		shipNameTextField.setColumns(10);
		shipNameTextField.setBounds(238, 188, 219, 25);
		setUpScreenFrame.getContentPane().add(shipNameTextField);
		
		JLabel lblNumberOfDays = new JLabel("Number of Days:");
		lblNumberOfDays.setForeground(Color.WHITE);
		lblNumberOfDays.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblNumberOfDays.setBounds(31, 230, 185, 38);
		setUpScreenFrame.getContentPane().add(lblNumberOfDays);
		
		JSlider numDaysSlider = new JSlider();
		numDaysSlider.setForeground(Color.white);
		numDaysSlider.setBackground(Color.BLACK);
		numDaysSlider.setOpaque(false);
		numDaysSlider.setMajorTickSpacing(1);
		numDaysSlider.setMinorTickSpacing(1);
		numDaysSlider.setPaintTicks(true);
		numDaysSlider.setPaintLabels(true);
		numDaysSlider.setSnapToTicks(true);
		numDaysSlider.setMinimum(3);
		numDaysSlider.setMaximum(10);
		numDaysSlider.setBounds(238, 232, 269, 36);
		setUpScreenFrame.getContentPane().add(numDaysSlider);
		
		JLabel lblErrorLabel = new JLabel("");
		lblErrorLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblErrorLabel.setBounds(567, 86, 292, 36);
		setUpScreenFrame.getContentPane().add(lblErrorLabel);
		
		JLabel lblCrewTypes = new JLabel("Crew Types (Choose 2-4 members): ");
		lblCrewTypes.setForeground(Color.WHITE);
		lblCrewTypes.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblCrewTypes.setBounds(29, 292, 379, 38);
		setUpScreenFrame.getContentPane().add(lblCrewTypes);
		
		JButton launchMain = new JButton("");
		launchMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (game.getCrew().getMembers().size() < 2 || game.getCrew().getMembers().size() > 4) {
					lblErrorLabel.setText("Only 2-4 crew members are allowed.");
					lblErrorLabel.setForeground(Color.red);
				}
				else if (crewNameTextField.getText().equals("") || shipNameTextField.getText().equals("")) {
					lblErrorLabel.setText("Don't leave any fields blank!!");
					lblErrorLabel.setForeground(Color.red);
				}
				else {
					game.getCrew().setName(crewNameTextField.getText());
					game.getCrew().getShip().setShipName(shipNameTextField.getText());
					game.setCrewSize(game.getCrew().getMembers().size());
					game.setDay(numDaysSlider.getValue());
					game.setPieces(Math.round(numDaysSlider.getValue()*2/3));
					launchMainMenuWindow();
				}

				
			}
		});
		launchMain.setIcon(new ImageIcon(SetUpScreenGUI.class.getResource("/Images/rocket-launch-icon.png")));
		launchMain.setFont(new Font("Georgia", Font.BOLD, 15));
		launchMain.setBounds(760, 400, 99, 96);
		setUpScreenFrame.getContentPane().add(launchMain);
		
		JLabel lblLaunch = new JLabel("LAUNCH!");
		lblLaunch.setForeground(Color.WHITE);
		lblLaunch.setFont(new Font("Georgia", Font.BOLD, 17));
		lblLaunch.setBounds(770, 499, 99, 30);
		setUpScreenFrame.getContentPane().add(lblLaunch);
		
		JButton bruteButton = new JButton("");
		bruteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkCrewSizeError()) {
					lblErrorLabel.setText("Only 2-4 crew members are allowed.");
					lblErrorLabel.setForeground(Color.red);
				}
				else {
					launchDescriptionWindow(0);
					setUpScreenFrame.disable();
				}

				
//				CrewMember member = game.getCrew().getMembers().get(-1);

//				String memberDescrition = member.viewName() + ": " + member.viewType();
//				DefaultListModel DLM = new DefaultListModel();
//				DLM.addElement(memberDescrition);
//				crewMemberJList.setModel(DLM);
				
			}
		});
		bruteButton.setBounds(81, 360, 99, 139);
		setUpScreenFrame.getContentPane().add(bruteButton);
		
		JButton scoutButton = new JButton("");
		scoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkCrewSizeError()) {
					lblErrorLabel.setText("Only 2-4 crew members are allowed.");
					lblErrorLabel.setForeground(Color.red);
				}
				else {
					launchDescriptionWindow(1);
					setUpScreenFrame.disable();
				}

				
			}
		});
		scoutButton.setBounds(190, 360, 99, 139);
		setUpScreenFrame.getContentPane().add(scoutButton);
		
		JButton pilotButton = new JButton("");
		pilotButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkCrewSizeError()) {
					lblErrorLabel.setText("Only 2-4 crew members are allowed.");
					lblErrorLabel.setForeground(Color.red);
				}
				else {
					launchDescriptionWindow(2);
					setUpScreenFrame.disable();
				}
			}
		});
		pilotButton.setBounds(299, 360, 99, 139);
		setUpScreenFrame.getContentPane().add(pilotButton);
		
		JButton mechanicButton = new JButton("");
		mechanicButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkCrewSizeError()) {
					lblErrorLabel.setText("Only 2-4 crew members are allowed.");
					lblErrorLabel.setForeground(Color.red);
				}
				else {
					launchDescriptionWindow(3);
					setUpScreenFrame.disable();
				}
			}
		});
		mechanicButton.setBounds(408, 360, 99, 139);
		setUpScreenFrame.getContentPane().add(mechanicButton);
		
		JButton hagglerButton = new JButton("");
		hagglerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkCrewSizeError()) {
					lblErrorLabel.setText("Only 2-4 crew members are allowed.");
					lblErrorLabel.setForeground(Color.red);
				}
				else {
					launchDescriptionWindow(4);
					setUpScreenFrame.disable();
				}
			}
		});
		hagglerButton.setBounds(517, 360, 99, 139);
		setUpScreenFrame.getContentPane().add(hagglerButton);
		
		JButton medicButton = new JButton("");
		medicButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkCrewSizeError()) {
					lblErrorLabel.setText("Only 2-4 crew members are allowed.");
					lblErrorLabel.setForeground(Color.red);
				}
				else {
					launchDescriptionWindow(5);
					setUpScreenFrame.disable();
				}
			}
		});
		medicButton.setBounds(626, 360, 99, 139);
		setUpScreenFrame.getContentPane().add(medicButton);
		
		JLabel lblPilot = new JLabel("        Pilot");
		lblPilot.setForeground(Color.WHITE);
		lblPilot.setFont(new Font("Georgia", Font.PLAIN, 17));
		lblPilot.setBounds(299, 499, 99, 25);
		setUpScreenFrame.getContentPane().add(lblPilot);
		
		JLabel lblMechanic = new JLabel("   Mechanic");
		lblMechanic.setForeground(Color.WHITE);
		lblMechanic.setFont(new Font("Georgia", Font.PLAIN, 17));
		lblMechanic.setBounds(408, 499, 99, 25);
		setUpScreenFrame.getContentPane().add(lblMechanic);
		
		JLabel lblHaggler = new JLabel("     Haggler");
		lblHaggler.setForeground(Color.WHITE);
		lblHaggler.setFont(new Font("Georgia", Font.PLAIN, 17));
		lblHaggler.setBounds(517, 499, 99, 25);
		setUpScreenFrame.getContentPane().add(lblHaggler);
		
		JLabel lblMedic = new JLabel("       Medic");
		lblMedic.setForeground(Color.WHITE);
		lblMedic.setFont(new Font("Georgia", Font.PLAIN, 17));
		lblMedic.setBounds(626, 499, 99, 25);
		setUpScreenFrame.getContentPane().add(lblMedic);
		
		JLabel lblBrute = new JLabel("      Brute");
		lblBrute.setForeground(Color.WHITE);
		lblBrute.setFont(new Font("Georgia", Font.PLAIN, 17));
		lblBrute.setBounds(81, 500, 99, 25);
		setUpScreenFrame.getContentPane().add(lblBrute);
		
		JLabel lblScout = new JLabel("       Scout");
		lblScout.setForeground(Color.WHITE);
		lblScout.setFont(new Font("Georgia", Font.PLAIN, 17));
		lblScout.setBounds(190, 499, 99, 25);
		setUpScreenFrame.getContentPane().add(lblScout);
		
		JLabel lblSetupBackground = new JLabel("");
		lblSetupBackground.setIcon(new ImageIcon(SetUpScreenGUI.class.getResource("/Images/earth.jpg")));
		lblSetupBackground.setBounds(0, 0, 886, 593);
		setUpScreenFrame.getContentPane().add(lblSetupBackground);
		

		

		

	}
}