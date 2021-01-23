/**
 * This class implements all the different types of items that the player can conume,
 * such as types of medkits and types of foods. Hold the cost, healing value, and name of the items.
 * 
 * @author Raymond Jr Tamse, Rchi Lugtu
 */

package main;

public class Item {

	/**
	 * The cost of the item.
	 */
	private int itemCost;
	/**
	 * The healing value of the item.
	 */
	private int value;
	/**
	 * The name of the item.
	 */
	private String name;

	/**
	 * The constructor of the Item class, which takes in item's name, price, and healing value.
	 * 
	 * @param name 			The name of the item.
	 * @param itemCost		The price of the item in the outpost.
	 * @param value 		The healing value of the item.
	 */
	public Item(String name, int itemCost, int value) {
		this.name = name;
		this.itemCost = itemCost;
		this.value = value;
	}
	
	/** 
	 * Sets the cost of the item.
	 * @param newCost 		The cost of the item.
	 */
	public void setCost(int newCost) {
		itemCost = newCost;
	}
	/**
	 * Sets the name of the item.
	 * @param newName 		The name of the new item being created.
	 */
	public void setName(String newName) {
		name = newName;
	}
	/**
	 * Returns the cost of the item
	 * @return itemCost 		The cost of the item.
	 */
	public int getCost() {
		return itemCost;
	}
	/**
	 * Returns the name of the item.
	 * @return name 		The name of the item.
	 */
	public String getName() {
		return name;
	}
	/**
	 * Returns the healing value of the item.
	 * @return value 		The healing value of the item.
	 */
	public int getValue() {
		return value;
	}

}
