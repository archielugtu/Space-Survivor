package main;

import java.util.ArrayList;
/**
 * This class implements the Outpost area of the space exploration game. This is where
 * items such as medkits and foods are bought. Holds the list of medkits and foods that is sold.
 * 
 * @author Raymond Jr Tamse, Rchi Lugtu
 * @version 1.2, May 2019 
 */
public class Outpost {

	/**
	 * The list of medkits sold in the outpost.
	 */
	private ArrayList<MedKit> medkitList;
	/**
	 * The list of foods sold in the outpost.
	 */
	private ArrayList<Food> foodList;
	
	/**
	 * Initialises the outpost, and creates objects of types MedKit and Food that will be sold.
	 * Adds medkits to the medkit list and foods to the foods list.
	 */
	public void initItems() {
		
		medkitList = new ArrayList<MedKit>();
		foodList = new ArrayList<Food>();
		
		MedKit smallMedKit = new MedKit("Small MedKit", 30, 25, false);
		MedKit mediumMedKit = new MedKit("Medium MedKit", 60, 50, false);
		MedKit largeMedKit = new MedKit("Large Medkit", 100, 100, false);
		MedKit antidote = new MedKit("Antidote", 80, 0, true);
		medkitList.add(smallMedKit);
		medkitList.add(mediumMedKit);
		medkitList.add(largeMedKit);
		medkitList.add(antidote);
		
		Food smallMeal = new Food("Small Meal", 15, 20);
		Food mediumMeal = new Food("Medium Meal", 35, 40);
		Food largeMeal = new Food("Large Meal", 50, 60);
		Food buffet = new Food("Buffet", 100, 100);
//		Food boost = new Food("Action Boost", 0, 200);
//		Food energyDrink = new Food("Energy Drink", 10, 50);
		
		foodList.add(smallMeal);
		foodList.add(mediumMeal);
		foodList.add(largeMeal);
		foodList.add(buffet);
		
		//THINKING ABOUT ADDING VARIABLES FOR Food THAT CHECKS IF FOOD IS ABLE TO
		//REPLENISH TIREDNESS LEVEL OR INCREASE NUM OF ACTIONS BY 1
		
//		foodList.add(boost);
//		foodList.add(energyDrink);
	
	}
	
	public ArrayList<MedKit> getMedKit() {
		return medkitList;
	}
	
	public ArrayList<Food> getFood() {
		return foodList;
	}
	
	public Food buyFood(int index) {
		return foodList.get(index-5);
	}
	
	public MedKit buyMedKit(int index) {
		return medkitList.get(index-1);
	}

	public void printItems(boolean hasHaggler) {
		// View the Outpost
		System.out.println("\nWelcome to the Outpost!");
		System.out.println("Items for sale:\n");
		System.out.println("Format - Name: Cost, Healing Value\n");
		
		// Prints the MedKit items for sale
		for (int i=0; i < medkitList.size(); i++) {
			MedKit outpostMed = medkitList.get(i);
			if (outpostMed.getName().equals("Antidote")) {
				System.out.println(i+1 + ". " + outpostMed.getName() + ":\t\t $" + 
			outpostMed.getCost() + ", " + outpostMed.getValue() +", Cures infection");
			}
			else {
				System.out.println(i+1 + ". " + outpostMed.getName() + ":\t $" + 
				outpostMed.getCost() + ", " + outpostMed.getValue());
			}
			
			if (hasHaggler) {
				System.out.println(" (Discounted: $" + (int) (outpostMed.getCost() * 0.75) + ")");
			}
		}
		
		System.out.println();
		
		// Prints the foods items for sale
		for (int i=0; i< foodList.size(); i++) {
			Food outpostFood = foodList.get(i);
			System.out.println(i+5 + ". " + outpostFood.getName() + ":\t $" + 
					outpostFood.getCost() + ", " + outpostFood.getValue());
			
			if (hasHaggler) {
				System.out.println(" (Discounted: $" + (int) (outpostFood.getCost() * 0.75) + ")");
			}
		}
	}
}