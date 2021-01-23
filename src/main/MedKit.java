/**
 * This class implements the medkits that the space crew member will use.
 * It extends the parent Item Class, and passes in the medkit's name, cost, and healing value, which
 * is the amount of the crew member's health level is restored by.
 * 
 * @author Raymond Jr Tamse, Rchi Lugtu
 * @version 1.2, May 2019 
 *
 */
package main;

public class MedKit extends Item{
	
	/**
	 * Determines if medkit heals the plague or not.
	 */
	private boolean healPlague;
	/**
	 * The constructor of the MedKit class, which takes in the medkit's name, cost, healing value, and the cure boolean.
	 * Sets the healPlague attribute of the medkit if it can cure the plague or not.
	 * 
	 * @param name 			The name of the medkit.
	 * @param cost			The cost of the medkit.
	 * @param healValue 	The healing value of the medkit.
	 * @param canCure		Boolean which sets the healPlague boolean if medkit can cure the plague or not.
	 */
	public MedKit(String name, int cost, int healValue, boolean canCure) {
		super(name, cost, healValue);
		healPlague = canCure;
	}
	
	/**
	 * Returns the healPlague boolean.
	 * @return healPlague  		A boolean which determines if item can cure the plague of not.
	 */
	public boolean canCure() {
		return healPlague;
	}
	
	public String toString() {
		return super.getName() + ": $" + super.getCost() + ", Healing value: " + super.getValue();
	}
}