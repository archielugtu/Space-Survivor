package main;

import java.util.Scanner;

public class SetupGameCL {

	private GameEnvironment game;
	
	public SetupGameCL(GameEnvironment newGame) {
		game = newGame;
		setSize();
		setPlayerCrewMembers();
		setDaysOfGame();
		setName();
	}
	
	public void setSize() {
		System.out.println("Welcome! How many members do you want in your crew? (2-4)");
		int crewSize = game.returnIntInput(2, 4);
		
		/////////////////////////////////////
		game.setCrewSize(crewSize);
		/////////////////////////////////////
	}
	
	
	public void setPlayerCrewMembers() {
		// Add members (name and type)
		System.out.println("\nPick " + game.getCrewSize() + " members for your crew.");
		System.out.println("Crew types:");
		System.out.println("Brute:  \t +100 Health");
		System.out.println("Mechanic:\t +15 Repair value");
		System.out.println("Scout:  \t Higher chance of finding transporter");
		System.out.println("Haggler:\t 30% off outpost prices");
		System.out.println("Medic:  \t +15 MedKit heal value");
		System.out.println("Pilot:  \t Lower damage from asteroid belt");

		
		Scanner inputAddMember = new Scanner(System.in);
		for (int i = 1; i <= game.getCrewSize(); i++) {
			System.out.println("\nWhat is the name of member #" + i + "?");
			String name = inputAddMember.nextLine();
			
			System.out.println("\nType for " + name + ":");
			String type = inputAddMember.nextLine();
			while(!game.getTypes().contains(type)) {
				System.out.println("Invalid crew type! Please enter a valid one.");
				System.out.println("Type for " + name + ":");
				type = inputAddMember.nextLine();
			}
			
			game.addMember(name, type);
		}
	}
	
	
	public void setDaysOfGame() {
		// Set days (and pieces to find)
		System.out.println("\nHow many days would you like to play for? (3-10 days)");
		
		int numDays = game.returnIntInput(3, 10);
		
		game.setDay(numDays);
		game.setPieces(Math.round(numDays * 2 / 3));
		// some reason numDays * (2 / 3) doesn't work
	}
	
	public void setName() {
		System.out.println("\nPlease enter your crew name: ");
		Scanner inputName = new Scanner(System.in);
		game.getCrew().setName(inputName.nextLine());
		
		System.out.println("\nPlease enter your ship's name: ");
		game.getCrew().getShip().setShipName(inputName.nextLine());
	}
	
}
