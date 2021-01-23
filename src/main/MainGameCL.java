package main;

import java.util.ArrayList;

public class MainGameCL {

	private GameEnvironment game;
	
	public MainGameCL(GameEnvironment newGame) {
		game = newGame;
		
		launchLoop();
	}
	
	public void launchLoop() {
		
		boolean goMenu = false;
		while (!goMenu) {		
			System.out.println("\nWelcome, " + game.getCrew().getName() + "! This is day " + game.getCurrentDay()
								+ " since you crash-landed. You have " + (game.getDays() - game.getCurrentDay())
								+ " day(s) until your supplies run out.");
			System.out.println(game.planetAnalysis());
			
			System.out.println("\nWhat would you like to do? Please enter a number to do something.");
			System.out.println("0: View Crew, 1: View Ship, 2: Visit Outpost, 3: Next day");
			int action1 = game.returnIntInput(0, 3);
			
			switch(action1) {
			case 0:		// view crew
				viewCrew();
				break;
			case 1:		// view ship
				viewShip();
				break;
			case 2:		// view outpost
				viewOutpost();
				break;
			case 3:		// next day
				goMenu = nextDay();
				break;
			}
		}
	}
	
	public void viewCrew() {
		// pick crew member
		boolean leaveCrewView = false;
		while (!leaveCrewView) {
			
			// print members
			game.getCrew().printMembers();
			
			System.out.println("Please enter the corresponding number to give a crew member an action."
					+ "\nOtherwise, enter 0 if you want to leave crew view.");
			int crewSelect = game.returnIntInput(0, game.getCrewSize());
			
			switch(crewSelect) {
			// exit crew view
			case 0:
				leaveCrewView = true;
				continue;
			}
			
			CrewMember member = game.getMember(crewSelect - 1);
			
			// else
			if (member.viewHealth() == 0) {
				System.out.println(member.viewName() + " is dead! Returning to crew view.");
				continue;
			}
			
			member.printActions();
			int actionSelect = game.returnIntInput(1, 5);
			
			// grab list of item names in inventory
			ArrayList<String> itemNames = new ArrayList<String>();
			String type = "";
			switch(actionSelect) {
			// eat food
			case 1:
				itemNames = game.listFoodNames(game.getCrew().getFoods());
				type = "food";
				break;
			// heal self
			case 2:
				itemNames = game.listMedKitNames(game.getCrew().getMedKits());
				type = "medkits";
				break;
			}
			
			if (actionSelect == 1 || actionSelect == 2) {
				// empty inventory (food or medkits)
				if (itemNames.size() == 0) {
					System.out.println("You don't have any " + type + "! Going back to crew view.");
					continue;
				}
				
				// print inventory names
				for (int i = 0; i < itemNames.size(); i++) {
					System.out.println(i+1 + ": " + itemNames.get(i));
				}
				
				System.out.println("\nEnter a number corresponding to the item number. "
						+ "\nEnter 0 to return to crew view.");
				
				// grab index
				int itemSelect = game.returnIntInput(0, itemNames.size());
				// return if 0
				if (itemSelect == 0) {
					continue;
				}
				
				// use item, does not need to terminate crew loop
				game.consumeItem(crewSelect - 1, actionSelect, itemSelect);

			}
			
			// other actions
			switch (actionSelect) {
			// sleep
			case 3:
				leaveCrewView = game.memberSleep(crewSelect-1).contains("Success");
				break;

			// repair shields
			case 4:
				leaveCrewView = !game.repairShip(crewSelect-1).contains("Success");
				break;
			
			// search planet
			case 5:
				String memberType = game.getMember(crewSelect-1).viewType();
				leaveCrewView = !game.memberSearchPlanet(crewSelect-1, memberType).contains("Success");
				break;
			} // switch end
		} // crew loop end
	} // method end

	
	public void viewShip() {
		boolean leaveShipView = false;
		while (!leaveShipView) {
			Rocketship ship = game.getCrew().getShip();
			System.out.println(ship.printStats() + game.getPieces());	// kinda weird how I added getPieces at the end
			
			System.out.println("\nWhat would you like to do? Please enter a number to do something.");
			System.out.println("0: Move to next planet, 1: Leave ship");
			
			int shipViewAction = game.returnIntInput(0, 1);
			
			switch (shipViewAction) {
			// proceed with picking pilots
			case 0:
				game.getCrew().printMembers();
				
				if (!game.checkTransporterFound()) {
					System.out.println("There's still a transporter on the planet!");
					System.out.println("Going back to ship view.\n");
					continue;
				}
				else {
					System.out.println("Please pick two crew members to pilot the ship.");
					System.out.println("Enter -1 to stop picking pilots.");
					
					// pick pilots for flight
					game.pickPilots(ship, game.returnIntInput(-1, game.getCrewSize()));
					
					// move planets
					game.movePlanets(ship);
				}
				break;
			// quit ship view
			case 1:
				leaveShipView = true;
				break;
			}
		}
	}
	
	public void viewOutpost() {
		boolean leaveOutpostView = false;
		while (!leaveOutpostView) {
			
			// checks if there's a haggler in the crew
			boolean hasHaggler = game.searchType("Haggler");
			game.viewOutpost().printItems(hasHaggler);
			
			
			// Prints Crew's current money and inventory
			ArrayList<String> foodNames = game.listFoodNames(game.getCrew().getFoods());
			ArrayList<String> medkitNames = game.listMedKitNames(game.getCrew().getMedKits());
			System.out.println("\nCurrent money: $" + game.getCrew().getCrewMoney());
			System.out.println("Current inventory:\n~  Medkits: " + medkitNames + 
					"\n~  Food: " + foodNames);
			
			// Prints questions
			System.out.println("\nWhat would you like to do? Please enter a number to do something.");
			System.out.println("0: Leave Outpost, 1: Buy Item");
			
			// 
			int action2 = game.returnIntInput(0, 1);
			System.out.println("\nEnter the corresponding number of the item you would like to buy.");
			int itemIndex = game.returnIntInput(1, 8);
			leaveOutpostView = !game.buyItem(action2, itemIndex).contains("Exit");
		} // outer switch end
	} // outpost loop end
	
	public boolean nextDay() {
		boolean gameOver = false;
		System.out.println("\nGoodnight!");
		game.nextDay();
		
		// pick enemy
		game.pickEvent(false);
		
		// check game over
		if (!game.checkGameOver().equals("")) {
			gameOver = true;
		}
		
		return gameOver;
	}
	
	
}