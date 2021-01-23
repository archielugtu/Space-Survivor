/**
 * This class implements the Crew of the Space Survival Game, and holds the crew's
 * rocketship, money, medkit inventory, food inventory, and members. It also contains 
 * getters and setters for each of these attributes
 * 
 * @author Raymond Jr Tamse, Rchi Lugtu
 * @version 1.1, May 2019
 */


package main;
import java.util.ArrayList;

public class Crew {
	
	/**
	 * The name of the crew
	 */
	private String crewName;
	/**
	 * The crew's rocketship. An object of type Rocketship
	 */
	private Rocketship crewShip = new Rocketship();
	/**
	 * The members of the crew. An array list of objects of type CrewMember.
	 */
	private ArrayList<CrewMember> crewMembers = new ArrayList<CrewMember>();
	/**
	 * The medkit inventory of the crew. An array list of objects of type MedKit.
	 */
	private ArrayList<MedKit> medkitList = new ArrayList<MedKit>();
	/** 
	 * The food inventory of the crew. An array list of objects of type Food.
	 */
	private ArrayList<Food> foodList = new ArrayList<Food>();
	/**
	 * The money of the crew.
	 */
	private int money = 200; 
	
	/**
	 * The constructor of the Crew class. Constructs a new Crew object.
	 * Takes in a string name and sets it to the attribute crewName.
	 * 
	 * @param name		The name of the crew.
	 */
	public Crew(String name) {
		crewName = name;
	}
	/**
	 * Returns the string attribute crewName.
	 * 
	 * @return crewName 		The name of the crew.
	 */
	public String getName() {
		return crewName;
	}
	/**
	 * Returns the ArrayList attribute, crewMembers.
	 * 
	 * @return crewMembers 			An ArrayList of objects of type CrewMember.
	 */
	public ArrayList<CrewMember> getMembers() {
		return crewMembers;
	}
	/**
	 * Returns the ArrayList attribute, medkitList.
	 * 
	 * @return medkitList 			An ArrayList of objects of type MedKit.
	 */
	public ArrayList<MedKit> getMedKits() {
		return medkitList;
	}
	/**
	 * Returns the ArrayList attribute, medkitList.
	 * 
	 * @return foodList 			An ArrayList of objects of type Food.
	 */
	public ArrayList<Food> getFoods() {
		return foodList;
	}
	/**
	 * Returns the int attribute, money.
	 * 
	 * @return money 			The crew's money, type int.
	 */
	public int getCrewMoney() {
		return money;
	}
	/**
	 * Returns the attribute crewShip of type Rocketship.
	 * 
	 * @return crewShip 			The crew's rocketship.
	 */
	public Rocketship getShip() {
		return crewShip;
	}
	/**
	 * Prints each member of the crew and their status attributes.
	 */
	public void printMembers() {
		// print crew members
		System.out.println("\nViewing " + crewName + " members:");
		for (int i = 0; i < crewMembers.size(); i++) {
			System.out.println(i + 1 + ": " + crewMembers.get(i).printStats());
		}
	}
	
	/**
	 * Sets the attribute crewName, to the input parameter name.
	 * @param name 		The name of the the crew.
	 */
	public void setName(String name) {
		crewName = name;
	}
	
	// game mechanics
	/**
	 * Adds a member to be part of the space crew.
	 * Adds an object of type CrewMember into the arraylist crewMembers.
	 * 
	 * @param member 		An object of type CrewMember
	 */
	public void addMember(CrewMember member) {
		crewMembers.add(member);
	}
	/**
	 * Adds a MedKit object to the ArrayList medkitList.
	 * 
	 * @param medkit 		An object of type MedKit
	 */
	public void addMedKit(MedKit medkit) {
		medkitList.add(medkit);
	}
	/**
	 * Adds a Food object to the ArrayList foodList.
	 * 
	 * @param food 		An object of type Food
	 */
	public void addFood(Food food) {
		foodList.add(food);
	}
	/**
	 * Increases the crew's money by int amount.
	 * 
	 * @param amount 		The amount the crew's money will increase by.
	 */
	public void increaseMoney(int amount) {
		money += amount;
	}
	/**
	 * Decreases the crew's money by int amount, amount decreased depends if the boolean
	 * Haggler is true.
	 * 
	 * @param amount 			The amount the crew's money will decrease by.
	 * @param hasHaggler 			A boolean that sets to true if a Haggler is in your crew.
	 */
	public void decreaseMoney(int amount, boolean hasHaggler) {
		if (hasHaggler) {
			double discountedAmount = amount*0.7;
			money -= (int) discountedAmount; // amount will only be 70% when a haggler is present in the Crew.
		}
		else {
			money -= amount;
		}
	}
}