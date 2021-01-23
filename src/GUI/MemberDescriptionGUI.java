package GUI;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;

import main.CrewMember;
import main.GameEnvironment;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class MemberDescriptionGUI {

	private JFrame descriptionWindow;
	private JTextField crewMemberNameTextField;
	private SetUpScreenGUI setupGUI;
	private GameEnvironment game;
	private int index;
	
	public MemberDescriptionGUI(SetUpScreenGUI new_setUpGUI, GameEnvironment new_game, int new_index) {
		setupGUI = new_setUpGUI;
		game = new_game;
		index = new_index;
		initialize();
		descriptionWindow.setVisible(true);
	}
	
	public String printHealth(int health) {
		return "Health: " + health + "/" + health;
	}
	
	public String printTiredness(int tired) {
		return "Tiredness: " + tired + "/100";
	}	
	
	public String printHunger(int hunger) {
		return "Hunger: " + hunger + "/100";
	}
	
	public String printRepairValue(int repair) {
		return "Repair value: " + repair;
	}
	
	public void closeWindow() {
		descriptionWindow.dispose();
	}
	
	public void finishWindow() {
		setupGUI.finishDescriptionWindow(this);
	}
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemberDescriptionGUI window = new MemberDescriptionGUI();
					window.descriptionWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MemberDescriptionGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		descriptionWindow = new JFrame();
		descriptionWindow.setBounds(100, 100, 523, 329);
		descriptionWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		descriptionWindow.getContentPane().setLayout(null);
		
		JLabel crewTypeName = new JLabel("Crew Type Name");
		crewTypeName.setForeground(Color.WHITE);
		crewTypeName.setFont(new Font("Georgia", Font.BOLD, 20));
		crewTypeName.setBounds(193, 11, 183, 47);
		descriptionWindow.getContentPane().add(crewTypeName);
		crewTypeName.setText(game.getTypes().get(index));
		
		JLabel lblDescription = new JLabel("Description: ");
		lblDescription.setForeground(Color.WHITE);
		lblDescription.setFont(new Font("Georgia", Font.PLAIN, 14));
		lblDescription.setBounds(10, 58, 88, 47);
		descriptionWindow.getContentPane().add(lblDescription);
		
		JLabel lblAttributes = new JLabel("Attributes:");
		lblAttributes.setForeground(Color.WHITE);
		lblAttributes.setFont(new Font("Georgia", Font.PLAIN, 14));
		lblAttributes.setBounds(10, 117, 74, 28);
		descriptionWindow.getContentPane().add(lblAttributes);
		
		
		JLabel descriptionLabel = new JLabel("");
		descriptionLabel.setForeground(Color.WHITE);
		descriptionLabel.setBounds(110, 69, 389, 57);
		descriptionWindow.getContentPane().add(descriptionLabel);
		descriptionLabel.setText(game.getMemberDescription().get(index));
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishWindow();
			}
		});
		btnCancel.setBounds(311, 256, 89, 23);
		descriptionWindow.getContentPane().add(btnCancel);
		
		JLabel lblErrorLabel = new JLabel("");
		lblErrorLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblErrorLabel.setBounds(10, 260, 201, 21);
		descriptionWindow.getContentPane().add(lblErrorLabel);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setForeground(Color.red);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (crewMemberNameTextField.getText().equals("")) {
					lblErrorLabel.setText("Please type a name!!");
					lblErrorLabel.setForeground(Color.red);
				}
				else {
					game.addMember(crewMemberNameTextField.getText(), game.getTypes().get(index));
					finishWindow();
				}

			}
		});
		btnAdd.setBounds(410, 256, 89, 23);
		descriptionWindow.getContentPane().add(btnAdd);
		
		crewMemberNameTextField = new JTextField();
		crewMemberNameTextField.setBounds(333, 208, 166, 28);
		descriptionWindow.getContentPane().add(crewMemberNameTextField);
		crewMemberNameTextField.setColumns(10);
		
		JLabel lblEnterAName = new JLabel("Enter a name:");
		lblEnterAName.setForeground(Color.WHITE);
		lblEnterAName.setFont(new Font("Georgia", Font.PLAIN, 14));
		lblEnterAName.setBounds(333, 169, 154, 28);
		descriptionWindow.getContentPane().add(lblEnterAName);
		
		JLabel lblHealthLabel = new JLabel("");
		lblHealthLabel.setForeground(Color.WHITE);
		lblHealthLabel.setBounds(101, 157, 201, 23);
		descriptionWindow.getContentPane().add(lblHealthLabel);
		lblHealthLabel.setText(printHealth((new CrewMember("", game.getTypes().get(index)).viewHealth())));
		
		JLabel lblTirednessLabel = new JLabel("");
		lblTirednessLabel.setForeground(Color.WHITE);
		lblTirednessLabel.setBounds(101, 174, 201, 23);
		descriptionWindow.getContentPane().add(lblTirednessLabel);
		lblTirednessLabel.setText(printTiredness(new CrewMember("", game.getTypes().get(index)).viewTiredness()));
		
		JLabel lblHungerLabel = new JLabel("");
		lblHungerLabel.setForeground(Color.WHITE);
		lblHungerLabel.setBounds(101, 191, 201, 23);
		descriptionWindow.getContentPane().add(lblHungerLabel);
		lblHungerLabel.setText(printHunger(new CrewMember("", game.getTypes().get(index)).viewHunger()));
		
		JLabel lblRepairValueLabel = new JLabel("");
		lblRepairValueLabel.setForeground(Color.WHITE);
		lblRepairValueLabel.setBounds(101, 208, 201, 23);
		descriptionWindow.getContentPane().add(lblRepairValueLabel);
		lblRepairValueLabel.setText(printRepairValue(new CrewMember("", game.getTypes().get(index)).viewRepairValue()));
		
		JLabel labelMemberDescBG = new JLabel("");
		labelMemberDescBG.setIcon(new ImageIcon(MemberDescriptionGUI.class.getResource("/Images/earth.jpg")));
		labelMemberDescBG.setBounds(0, 0, 509, 292);
		descriptionWindow.getContentPane().add(labelMemberDescBG);

	}
}
