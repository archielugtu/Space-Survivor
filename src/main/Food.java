/**
 * This class implements the foods that the space crew member will consume.
 * It extends the parent Item Class, and passes in the food's name, cost, and feed value, which
 * is the amount of the crew member's hunger level is restored by.
 * 
 * @author Raymond Jr Tamse, Rchi Lugtu
 * @version 1.2, May 2019
 */

package main;

public class Food extends Item {
	
	/**
	 * The constructor of the Food class. This sets the food's name, cost and feed value
	 * of the food. This calls the constructor of the parent Item class and passes in these values.
	 * 
	 * @param name  		The name of the food.
	 * @param cost 			The price of the food in the outpost.
	 * @param feedValue 	The healing value of the food.
	 */
	public Food(String name, int cost, int feedValue) {
		super(name, cost, feedValue);
	}
	
	public String toString() {
		return super.getName() + ": $" + super.getCost() + ", Healing value: " + super.getValue();
	}
	
	
}
