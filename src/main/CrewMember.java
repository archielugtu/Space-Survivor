/**
 * This class implements the space crew member that will be added to the crew.
 * It holds the attibutes of a crew member, which are name, type, hunger value, tiredness value,
 * health, number of actions, repair value, infect state, maximum healt.h
 * 
 * @author Raymond Jr Tamse, Rchi Lugtu
 * @version 1.2, May 2019 
 */

package main;

public class CrewMember {
	/**
	 * The name of the crew member.
	 */
	private String name;
	/**
	 * The type of the crew member.
	 */
	private String type;
	/**
	 * The hunger value of the crew member.
	 */
	private int hunger = 0;
	/**
	 * The tiredness value of the crew member.
	 */
	private int tiredness = 0;
	/**
	 * The health value of the crew member.
	 */
	private int health = 100;
	/**
	 * The max health of the crew member.
	 */
	private int maxHealth;
	/**
	 * The number of actions of the crew member.
	 */
	private int numActions = 2;
	/**
	 * The infect state of the crew member.
	 */
	private boolean infectState = false;
	/**
	 * The repair value of the crew member.
	 */
	private int repairValue = 25;
	/**
	 * The constructor of CrewMember class. Which constructs a new CrewMember object
	 * and sets the name and type, and repair value if the type is a Mechanic.
	 * 
	 * @param newName 		A string, to set the name of the crew member.
	 * @param newType		A string, to set the type of the crew member.
	 */
	public CrewMember(String newName, String newType) {
		name = newName;
		type = newType;
		maxHealth = 100;
		if (type.equals("Brute")) {
			health += 100;
			maxHealth = health;
		}
		else if (type.equals("Mechanic")) {
			repairValue += 15;
		}
		else if (type.equals("Scout")) {
			
		}
		else if (type.equals("Haggler")) {
			
		}
		else if (type.equals("Medic")) {
			
		}
		else if (type.equals("Pilot")) {
			
		}
	}
		
	
	/**
	 * Returns the name of the crew member.
	 * 
	 * @return name 		A String object, name of the crew member.
	 */
	public String viewName() {
		return name;
	}
	/**
	 * Returns the type of the crew member.
	 * 
	 * @return type 		A String object, type of the crew member.
	 */
	public String viewType() {
		return type;
	}
	/**
	 * Returns the hunger value of the crew member.
	 * 
	 * @return hunger 		An int, the hunger value of the crew member.
	 */
	public int viewHunger() {
		return hunger;
	}
	/**
	 * Returns the tiredness value of the crew member.
	 * 
	 * @return tiredness 		An int, the tiredness value of the crew member.
	 */
	public int viewTiredness() {
		return tiredness;
	}
	/**
	 * Returns the health value of the crew member.
	 * 
	 * @return health 		An int, the current health value of the crew member.
	 */
	public int viewHealth() {
		return health;
	}
	/**
	 * Returns the max health value of the crew member.
	 * 
	 * @return maxHealth 		An int, the max health value of the crew member.
	 */
	public int viewMaxHealth() {
		return maxHealth;
	}
	/**
	 * Returns the number of actions of the crew member.
	 * 
	 * @return numActions 		An int, number of actions of the crew member.
	 */
	public int viewNumActions() {
		return numActions;
	}
	/**
	 * Returns a boolean, the infect state of the crew member.
	 * 
	 * @return infectState 		An int, number of actions of the crew member.
	 */
	public boolean viewInfectState() {
		return infectState;
	}
	/**
	 * Returns the repair value of the crew member.
	 * 
	 * @return repairValue 		An int, repair value of the crew member.
	 */
	public int viewRepairValue() {
		return repairValue;
	}
	
	/**
	 * A method that return true if the crew member still has actions and is not tired, otherwise false.
	 * 
	 * @param willSleep 		A boolean that gets passed on to the canAct method.
	 * @return output 		Sets to true initially and false if number of actions is 0, or if tiredness is 100.
	 */
	public String canAct(boolean willSleep) {
		if (numActions == 0) {
			return name + " has no more actions left. Wait until next day.";
		}
		else if (tiredness == 100 && !willSleep) {
			return name + " is too tired. Please sleep.";
		}
		return "Success";
	}
	
	
	/**
	 * 
	 * Crew member eats food and decreases the hunger value of the crew member by an amount int quality. 
	 * Checks first if the crew member needs to sleep, otherwise decrease the hunger value, and return a boolean true.
	 * 
	 * @param quality 		The amount the hunger value of the crew member decreases by, type int.
	 * @return result		A boolean type which is true if the hunger value is decreased, otherwise false.
	 */
	public String eatFood(int quality) {
		String result = canAct(false);
		if (result.contains("Success")) {
			hunger -= quality;
			numActions -= 1;
			if (hunger < 0) {
				hunger = 0;
			}
		}
		return result;
	}
	
	/**
	 * Crew member heals self, which increases the current health level by an amount int quality, 
	 * also checks if the it can cure the infect state of the crew member or not.
	 * 
	 * @param quality 		The amount the health level of the crew member increases by, type int.
	 * @param canCure 		A boolean, which is true if it cures the infect state of the crew member, otherwise false.
	 * @return result		A boolean type which is true if the health level increases, otherwise false.
	 */
	public String healSelf(int quality, boolean canCure) {
		String result = canAct(false);
		if (result.contains("Success")) {
			health += quality;
			numActions -= 1;
			if (health > maxHealth) {
				health = maxHealth;
			}
			if (canCure) {
				infectState = false;
			}
		}
		return result;
	}
	
	/**
	 * Crew member sleeps, which sets the tiredness level of the member back to 0.
	 * Passes in a boolean true, to canAct which checks if the crew member can do the following action.
	 * 
	 * @return result		A String type which is true if the tiredness level goes to 0, otherwise false.
	 */
	public String sleep() {
		String result = canAct(true);
		if (result.contains("Success")) {
			tiredness = 0;
			numActions -= 1;
			System.out.println(viewName() + " went to sleep. Tiredness reset to 0.");
		}
		return result;
	}

	/**
	 * Crew member repairs the shield of the rocketship. Shield level increases depending on their repair value.
	 * 
	 * @return result		A boolean type which is true if the shield level increases, otherwise false.
	 */
	public String repairedShields() {
		String result = canAct(false);
		if (result.contains("Success")) {
			numActions -= 1;
		}
		return result;
	}
	
	/**
	 * Crew member searches the planet, and returns true if the following action is executed, otherwise false.
	 * 
	 * @return canSearch 		A boolean type which is true if the action is performed, otherwise false.
	 */
	public String searchPlanet() {
		String result = canAct(false);
		if (result.contains("Success")) {
			numActions -= 1;
		}
		return result;
	}
	/**
	 * Crew member pilots the ship, and returns true if the following action is executed, otherwise false.
	 * 
	 * @return canPilot 		A boolean type which is true if the action is performed, otherwise false.
	 */
	public String pilotShip(boolean hasFlew) {
		String result = canAct(false);
		if (result.contains("Success")) {
			if (hasFlew) {
				numActions -= 1;
			}
		}
		return result;
	}
	
	/** 
	 * This degrades the attributes of the crew member by an amount, when the player goes to the next day. The amount depends on the type of the crew member.
	 * 
	 */
	public void nextDay() {
		// defaults
		if (health != 0) {
			if (hunger == 100) {
				takeDamage(15);
			}
			else {
				takeDamage(5);
			}
		}
		hunger += 15;
		tiredness += 20;
		
		// if infected
		if (infectState && health != 0) {
			takeDamage(20);
		}
		
		// type checkers
		if (health > 0) {
			if (type.equals("Brute")) {
				health += 2;
				hunger -= 5;
				tiredness -= 10;
			}
			else if(type.equals("Mechanic")) {
				tiredness -= 10;
			}
			else if(type.equals("Scout")) {
				hunger -= 5;
				tiredness -= 5;
			}
			else if(type.equals("Haggler")) {
				
			}
			else if(type.equals("Medic")) {
				health += 5; // never gets daily damage
			}
			else if(type.equals("Pilot")) {
				tiredness -= 5;
			}
		}
		else {
			health = 0;
		}
		
		// limits
		if (hunger > 100) {
			hunger = 100;
		}
		if (tiredness > 100) {
			tiredness = 100;
		}
		numActions = 2;
	}
	/**
	 * Crew member takes a damage, and decreases the health by an amount int quality.
	 * Returns true if health is decreased otherwise false.
	 * 
	 * @param quality 		The amount the health of the crew member decreases by, type int.
	 * @return isDead 		A boolean, which is true if health is decreased, otherwise false.
	 */
	public boolean takeDamage(int quality) {
		this.health -= quality;
		boolean isDead = false;
		if (health < 0) {
			health = 0;
			isDead = true;
			System.out.println("Crew member " + name + " has died.");
		}
		return isDead;
	}
	/**
	 * Prints out a message if the crew member has been infected.
	 * 
	 */
	public String infect() {
		if (infectState) {
			return name + " has been infected, again!";
		}
		else {
			infectState = true;
			return name + " has been infected!";
		}
	}
	/**
	 * Increases the number of actions of the crew member by 1.
	 * Resets to 2 if it goes over 2.
	 */
	public void addNumActions() {
		numActions += 1;
		if (numActions > 2) {
			numActions = 2;
		}
	}
	
	/**
	 * Returns the current stats of the crew members.
	 * 
	 * @return output 		A String type, which has the information about the attributes of the crew member.
	 */
	public String printStats() {
		String output = name + " (" + type + ")";
		// health checker
		if (health == 0) {
			output += " ---DEAD--- ";
		}
		// infect state checker
		if (infectState) {
			output += " ---INFECTED--- ";
		}
		output += "\nHealth: " + health 
				+ ", Tiredness: " + tiredness
				+ ", Hunger: " + hunger 
				+ ", Actions left: " + numActions 
				+ "\n";
		return output;
	}
	/**
	 * Prints the list of actions a crew member can perform.
	 */
	public void printActions() {
		System.out.println("\nYou have chosen " + name + ". Actions left: " + numActions);
		System.out.println("1: Eat Food\n"
		+ "2: Use MedKit\n"
		+ "3: Sleep\n"
		+ "4: Repair ship shields\n"
		+ "5: Search planet for parts\n");
	}
	
	/**
	 * Returns the crew member stats.
	 */
	public String toString() {
		
		String stateString1 = "";
		if (this.viewHealth() == 0) {
			stateString1 = "Dead";
		}else {
			stateString1 = "Alive";
		}
		return "Health: " + health + 
				"\nTiredness: " + tiredness + "\nHunger: " + hunger + "\nActions: " 
				+ numActions + "\nRepair Value: " + repairValue + "\nInfect State: " + 
				infectState + "\nCurrent State: " + stateString1;
	}
	
//	public String actionString (int index) {
//		switch (index-1) {
//		case 0:
//			return name + ""
//		}
//	}
	
}