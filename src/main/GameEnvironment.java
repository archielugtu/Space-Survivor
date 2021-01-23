/**
 * 
 * 
 * @author Raymond Tamse Jr, Rchi Lugtu
 * @version 1.6
 */


package main;

import java.util.ArrayList;
import java.util.Scanner;

import GUI.OpenScreenGUI;

import java.util.InputMismatchException;


public class GameEnvironment {
	
	/**
	 * Number of days for the game.
	 */
	private int numDays;
	/**
	 * Number of transporter needed to find to complete the game.
	 */
	private int numPieces;
	/**
	 * The current day of the game.
	 */
	private int currentDay = 1;
	/**
	 * Number of crew members in the crew.
	 */
	private int crewSize;
	/**
	 * Array list of strings filled with member descriptions.
	 */
	private ArrayList<String> memberDescription = new ArrayList<String>();
	/**
	 * Array list of strings filled with 6 different crew member types.
	 */
	private ArrayList<String> crewTypes = new ArrayList<String>();
	/**
	 * Array list of integers filled with daily scores of the player.
	 */
	private ArrayList<Integer> dayScores = new ArrayList<Integer>();
	/**
	 * Initializing an object of type Crew.
	 */
	private Crew crew = new Crew("");
	/**
	 * Initializing an object of type Outpost.
	 */
	private Outpost outpost = new Outpost();
	/**
	 * Boolean that is true if a transporter is found, false otherwise.
	 */
	private boolean transporterFound = false;
	/**
	 * Boolean that is true if a transporter is inside a planet, false otherwise.
	 */
	private boolean transporterOnPlanet = false;
	
	
	/**
	 * Initializes the crew member types and their description, by adding it to their own
	 * array lists
	 */
	public void initTypes() {
		crewTypes.add("Brute");
		crewTypes.add("Scout");
		crewTypes.add("Pilot");
		crewTypes.add("Mechanic");
		crewTypes.add("Haggler");
		crewTypes.add("Medic");
		
		memberDescription.add("More health. Attributes daily damage is reduced.");
		memberDescription.add("Higher chance of finding a transporter on planet.");
		memberDescription.add("Reduces damage from asteroid belt when flying ship.");
		memberDescription.add("Increase in repair value when reparing ship's shield.");
		memberDescription.add("Discounted item prices in the Outpost.");
		memberDescription.add("Increase healing values from medkits. No daily health damage");
	}
	/**
	 * Getter for the ArrayList member description.
	 * @return memberDescription 	Array list of strings of member descriptions.
	 */
	public ArrayList<String> getMemberDescription() {
		return memberDescription;
	}
	
	/**
	 * Returns an integer between min and max.
	 * Prevents user from entering illegal arguments.
	 * 
	 * @param min	Minimum expected input from user.
	 * @param max	Maximum expected input from user.
	 * @return result	User's integer input.
	 */
	
	public int returnIntInput(int min, int max) {
		Scanner input = new Scanner(System.in);
		int result = -999;
		while (result == -999) {
			try {
				int tempInput = input.nextInt();
				if (min == max && tempInput != min) {
					System.out.println("Invalid input! Please enter an integer.");
					continue;
				}
				else if (tempInput < min || max < tempInput) {
					System.out.println("Invalid integer! Please enter an integer between "
										+ min + " and " + max + ".");
					continue;
				}
				else {
					result = tempInput;
				}
			}
			catch (InputMismatchException e) {
				System.out.println("Invalid input! Please enter an integer.");
				result = -999;
				String throwaway = input.nextLine();
				continue;
			}
		}
		return result;
	}

	
	// getters
	
	public int getDays() {
		return numDays;
	}
	
	public int getPieces() {
		return numPieces;
	}
	
	public int getCurrentDay() {
		return currentDay;
	}
	
	public ArrayList<String> getTypes() {
		return crewTypes;
	}
	
	public Outpost viewOutpost() {
		return outpost;
	}
	
	public Crew getCrew() {
		return crew;
	}
	
	public int getCrewSize() {
		return crewSize;
	}
	
	public boolean checkTransporterFound() {
		return transporterFound;
	}
	
	public boolean checkTransporterOnPlanet() {
		return transporterOnPlanet;
	}
	
	// setters
	public void setDay(int amount) {
		numDays = amount;
	}
	
	public void setPieces(int amount) {
		numPieces = amount;
	}
	
	public void setCrewSize(int amount) {
		crewSize = amount;
	}
	
	public void addScore(int amount) {
		dayScores.add(amount);
	}
	
	public void setTransporter(boolean setter) {
		transporterFound = setter;
	}
	
	public ArrayList<String> listFoodNames(ArrayList<Food> list) {
		ArrayList<String> returnList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			returnList.add(list.get(i).getName());
		}
		return returnList;
	}
	
	public ArrayList<String> listMedKitNames(ArrayList<MedKit> list) {
		ArrayList<String> returnList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			returnList.add(list.get(i).getName());
		}
		return returnList;
	}
	
	// game mechanics
	
	public void nextDay() {
		currentDay += 1;
		for (int i = 0; i < crewSize; i++) {
			crew.getMembers().get(i).nextDay();
		}
		dayScores.add(calculateScore());
	}
	
	public void addMember(String name, String type) {
		CrewMember member = new CrewMember(name, type);
		crew.addMember(member);
	}
	
	public CrewMember getMember(int index) {
		return crew.getMembers().get(index);
	}
	
	public String feedMember(CrewMember member, int quality) {
		return member.eatFood(quality);
	}
	
	public String consumeItem(int index, int actionSelect, int itemSelect) {
		// consume item
		CrewMember member = getMember(index);
		
		String itemName = "";
		boolean actionSuccess = false;
		switch (actionSelect) {
		// eat food
		case 1:
			//grab item with index
			Food food = crew.getFoods().get(itemSelect-1);
			int foodValue = food.getValue();
			if (feedMember(member, foodValue).contains("Success")) { 
				actionSuccess = true;
				crew.getFoods().remove(itemSelect-1);
			}
			itemName = food.getName();
			break;
		// use medkit
		case 2:
			//grab item with index
			MedKit medkit = crew.getMedKits().get(itemSelect-1);
			int medkitValue = medkit.getValue();
			// Checks if a medic is in your Crew!!!
			if (searchType("Medic")) {
				medkitValue += 15;
			}
			if (healMember(index, medkitValue, medkit.canCure()).contains("Success")) {
				actionSuccess = true;
				crew.getMedKits().remove(itemSelect-1);
			}
			itemName = medkit.getName();
			break;
		}
		
		// item consumed
		if (actionSuccess) {
			return "Member used " + itemName + ".";
		}
		return "Action unsuccessful.";
	}
	
	public void damageMember(int index, int quality) {
		crew.getMembers().get(index).takeDamage(quality);
	}

	public String healMember(int index, int quality, boolean canCure) {
		return crew.getMembers().get(index).healSelf(quality, canCure);
	}
	
	public String memberSleep(int index) {
		return crew.getMembers().get(index).sleep();
	}
	
	public String randomizeSearch(String type) {
		String itemFound = "";
		double extraChance = 1.0;
		if (type.equals("Scout")) {
			extraChance = 2.0;
		}
		
		double randNum = Math.random();
		// 25% chance (or 50% chance if scavenger)
		if (randNum <= 0.25 * extraChance && !transporterFound) {
			itemFound = "Transporter";
		}
		// 45% chance (or 20% chance if scavenger and !transporterFound)
		else if (randNum <= 0.7) {
			itemFound = "nothing";
		}
		// 10% chance
		else if (randNum <= 0.8) {
			itemFound = "Food";
		}
		// 10% chance
		else if (randNum <= 0.9) {
			itemFound = "MedKit";
		}
		// 10% chance
		else if (randNum <= 1.0) {
			itemFound = "Money";
		}
		return itemFound;
	}
	
	public String memberSearchPlanet(int index, String itemFound) {
		CrewMember member = getMember(index);
		Rocketship ship = crew.getShip();
		String output = "";
		
		// check if member can't perform action
		String result = member.searchPlanet();
		if (!result.contains("Success")) {
			return result;			// go back to crew view
		}
		if (itemFound == "Transporter") {
			ship.addPart();
			setTransporter(true);
			output += "Nice! Transporter part has been added to your collection."
					+ " \nTotal parts found: " + ship.getParts();
		}
		else if (itemFound == "Food") {
			double randNum = Math.random();
			ArrayList<Food> foods = viewOutpost().getFood();
			ArrayList<String> foodNames = listFoodNames(crew.getFoods());
			Food food = foods.get(3);	// default buffet, changed by random if below
			// randomized food selection
			if (randNum <= 0.6) {
				food = foods.get(0);	// finds small meal
			}
			else if (randNum <= 0.8) {
				food = foods.get(1);	// finds medium meal
			}
			else if (randNum <= 0.95) {
				food = foods.get(2);	// finds large meal
			}
			// else food found still buffet, but 5% chance only
			crew.addFood(food);
			foodNames.add(food.getName());
			output += food.getName() + " has been added to your inventory.";
		}
		else if (itemFound.equals("MedKit")) {
			double randNum = Math.random();
			ArrayList<MedKit> medkits = outpost.getMedKit();
			ArrayList<String> medkitNames = listMedKitNames(crew.getMedKits());
			MedKit medkit = medkits.get(2);	// default large medkit, changed by random if below
			// randomized medkit selection
			if (randNum <= 0.4) {
				medkit = medkits.get(0);	// finds small medkit
			}
			else if (randNum <= 0.6) {
				medkit = medkits.get(1);	// finds medium medkit
			}
			else if (randNum <= 0.8) {
				medkit = medkits.get(3);	// finds antidote
				
			}
			// else medkit found still large medkit, but 10% chance only
			crew.addMedKit(medkit);
			medkitNames.add(medkit.getName());
			output += medkit.getName() + " has been added to your inventory.";
		}
		else if (itemFound == "Money") {
			long addedMoney = Math.round(Math.random() * 100);
			crew.increaseMoney((int) addedMoney);
			output += "$" + (int) addedMoney + " has been added to your money.";
		}
		return output;
	}
	
	public String repairShip(int index) {
		CrewMember member = getMember(index);
		String output = member.repairedShields();
		if (output.contains("Success")) {
			output = crew.getShip().repairShields(member.viewRepairValue());
			output += "\n" + member.viewName() + " repaired the ship's shields.";
		}
		return output;
	}
	
	public String pickPilots(Rocketship ship, int memberIndex) {
		// pilot selection
		if (!transporterFound && transporterOnPlanet) {
			return "There is still a transporter on the planet!";
		}
		if (memberIndex == -1) {
			ship.clearPilots(false);
		}
		if (memberIndex == 0) {
			memberIndex = 1;
		}
		
		CrewMember member = crew.getMembers().get(memberIndex-1);
		String result = member.pilotShip(false);
		if (result.contains("Success")) {		// if member can pilot
			return ship.addPilot(member);
		}
		return result;
	}
	
	public String movePlanets(Rocketship ship) {
		String output = "";
		// move planets
		if (ship.viewPilots().size() == 2) {
			System.out.println("here");
			output += "\nWhile the crew was moving planets...";
			output += pickEvent(true);
			
			output += "\n" + crew.getName() + " has moved to the next planet.";
			ship.clearPilots(true);
			checkGameOver();
			
			// randomize transporter
			// when moving planets, transporterFound = false, then true, then false if transporterOnPlanet.
			randomizeTransporter();
			transporterFound = false;
			transporterOnPlanet = false;
		}
		System.out.println(output);
		return output;
	}
	
	// outpost
	public String buyItem(int action2, int itemIndex) {
		String itemString = "";
		// player wants to buy something
		switch(action2) {
		case 0:
			itemString += "Exit";
			break;
		case 1:
			
			Item itemBought = new Item("placeholder", 0, 0);
			switch(itemIndex) {
			case 1:
			case 2:
			case 3:
			case 4:
				itemBought = outpost.buyMedKit(itemIndex);
				break;
			case 5:
			case 6:
			case 7:
			case 8:
				itemBought = outpost.buyFood(itemIndex);
				break;
			}
			
			if (crew.getCrewMoney() - itemBought.getCost() < 0) {
				itemString += "\nSorry, you have insufficient funds!";
			}
			else {
				if (itemBought instanceof Food) {
					crew.addFood((Food) itemBought);
				}
				else {
					crew.addMedKit((MedKit) itemBought);
				}
				crew.decreaseMoney(itemBought.getCost(), searchType("Haggler"));
				
				itemString += "\nYou bought a " + itemBought.getName() + ".";
			} // else end
			break;
		} // inner switch end
		return itemString;
	}
	
	// game handling
	public void launchSetupCL() {
		SetupGameCL setup = new SetupGameCL(this);
	}
	
	public void launchMainCL() {
		MainGameCL main = new MainGameCL(this);
	}
	
	public void launchOpenScreen() {
		OpenScreenGUI start = new OpenScreenGUI(this);
	}
	
	public String pickEvent(boolean state) {
		RandomEvents event = new RandomEvents(this, state);
		return event.returnOutput();
	}
	
	public String checkGameOver() {
		// ---game over scenarios---
		String gameOver = "";
		
		// count how many crew members are dead
		int totalDead = countDeadMembers();
		
		// dead crew
		if (totalDead == crewSize) {
			System.out.println("\nYour whole crew has died.");
			gameOver = "lose";
		}
		else if (crewSize - totalDead < 1) {
			System.out.println("\nThere are not enough pilots to pilot the ship.");
			gameOver = "lose";
		}
		// no more days
		else if (currentDay == numDays + 1) {
			System.out.println("\nYou have run out of days.");
			gameOver = "lose";
		}
		// shields zero
		else if (crew.getShip().getShield() == 0) {
			System.out.println("\nYour ship was destroyed.");
			gameOver = "lose";
		}
		
		else if (crew.getShip().getParts() == numPieces) {
			gameOver = "win";
		}
		
		return gameOver;
	}
	
	public String printSummary(boolean gameLost, boolean gameWon) {
		if (gameLost) {
			return gameOver("lose");
		}
		else if (gameWon) {
			return gameOver("win");
		}
		return "";
	}
	
	public String gameOver(String state) {
		String output = "\nYour crew, " + crew.getName() + ", has ";
		String finalMessage = "";
		if (state.equals("lose")) {
			output += "perished.";
		}
		else {
			output += "has successfully collected all transporter parts.";
			finalMessage = "Congratulations! You win!";
		}
		output += "\nShip's name: " + crew.getShip().getShipName();
		output += "\nNumber of days taken to complete: " + numDays + "\n";
		
		output += "\n---Score---";
		int totalScore = 0;
		for (int i = 0; i < currentDay-1; i++) {
			int dayNum = i+1;
			totalScore += dayScores.get(i);
			output += "\nDay " + dayNum + ": " + dayScores.get(i);
		}
		totalScore += (currentDay - numDays) * 100;
		output += "\nOverall Score: " + totalScore;
		output += "\n" + finalMessage;
		output += "\nGAME OVER";
		return output;
	}
	
	public int calculateScore() {
		int score = 0;
		ArrayList<CrewMember> members = crew.getMembers();
		Rocketship ship = crew.getShip();
		// calculate the score through every member's health - tiredness - hunger
		for (int i = 0; i < crewSize; i++) {
			CrewMember member = members.get(i);
			score += member.viewHealth() - member.viewHunger() - member.viewTiredness();
			if (member.viewInfectState()) {
				score -= 25;
			}
			score -= countDeadMembers() * 50;
		}
		// calculate the extra score from finding a transporter in x day - last day * 100
		if (transporterFound && transporterOnPlanet) {
			score += 100;
		}
		// calculate the score from ship attributes
		score += ship.getShield();
		if (score < 0) {
			score = 0;
		}
		return score;
	}
	
	public int countDeadMembers() {
		int count = 0;
		for (int i = 0; i <= crewSize-1; i++) {
			CrewMember member = crew.getMembers().get(i);
			if (member.viewHealth() == 0) {
				count += 1;
			}
		}
		return count;
	}
	
	public void randomizeTransporter() {
		double randNum = Math.random();
		if (randNum <= 0.6) {
			transporterOnPlanet = true;
			transporterFound = false;
		}
	}
	
	public String planetAnalysis() {
		String output = "";
		if (transporterOnPlanet && !transporterFound) {
			output = "The ship detected a transporter part on this planet. Go find it.";
		}
		else {
			output = "There is no transporter on the planet.";
		}
		if (transporterOnPlanet && transporterFound) {
			output = "The transporter on this planet has already been retrieved.";
		}
		return output;
	}
	
	public boolean searchType(String type) {
		boolean hasType = false;
		ArrayList<CrewMember> memberList = crew.getMembers();
		for (int i = 0; i < memberList.size(); i++) {
			CrewMember member = memberList.get(i);
			if (member.viewType().equals(type)) {
				hasType = true;
			}
		}
		return hasType;
	}
	
	//----------TEST----------
	
public static void main(String[] args) {
		
		GameEnvironment game = new GameEnvironment();
		
		// Initialize the game
		game.initTypes();
		game.viewOutpost().initItems();
		game.randomizeTransporter();
		// Starts up the Open Screen of the game
		game.launchOpenScreen();
		
		
	}
}