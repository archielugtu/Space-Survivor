package main;

import java.util.ArrayList;

/**
 * This class implements a Rocketship that the space crew can use
 * while moving planets. Contains a shield level with a default maximum of
 * 100, and displays the number of transporter parts found. Pilots can be
 * designated here.
 * 
 * @author Raymond Jr Tamse, Rchi Lugtu
 * @version 1.1
 */
public class Rocketship {

	/**
	 * The rocketship's shield level (default maximum of 100).
	 */
	private int shieldLevel = 100;
	
	/**
	 * Number of transporter parts found (default 0).
	 */
	private int partsFound = 0;
	
	/**
	 * The rocketship's name.
	 */
	private String shipName;
	
	/**
	 * List that records the designated pilots when the player tries to move planets.
	 */
	private ArrayList<CrewMember> pilotList = new ArrayList<CrewMember>();
	
	/**
	 * Returns the rocketship's name.
	 * @return shipName 		The rocketship's name.
	 */
	public String getShipName() {
		return shipName;
	}
	
	/**
	 * Returns the rocketship's shield level.
	 * @return shieldLevel 		The rocketship's shield level.
	 */
	public int getShield() {
		return shieldLevel;
	}
	
	/**
	 * Returns the number of parts found.
	 * @return partsFound 		The number of transporter parts found.
	 */
	public int getParts() {
		return partsFound;
	}
	
	/**
	 * Returns the list of designated pilots while trying to move planets.
	 * @return pilotList 		List of designated pilots.
	 */
	public ArrayList<CrewMember> viewPilots() {
		return pilotList;
	}
	
	/**
	 * Sets the ship's name.
	 * @param name 		The ship's name.
	 */
	public void setShipName(String name) {
		shipName = name;
	}
	

	/**
	 * Repairs the shield by taking an int amount and adding it to the current shieldLevel.
	 * Prints the amount and the new shieldLevel.
	 * 
	 * @param amount 		The amount, the shield level of the ship increases by.
	 */
	public String repairShields(int amount) {
		shieldLevel += amount;
		if (shieldLevel > 100) {
			shieldLevel = 100;
		}
		return "Shields have been repaired by " + amount
				+ ". Shield level is now " + shieldLevel + ".";
	}
	
	/**
	 * Decreases the shield level subtracting an int amount to the current shieldLevel.
	 * Damage taken is decreased when a Pilot is present, and scaled by amount - (shieldLevel / 100) - 10.
	 * The -10 is to prevent the shield level from ever reaching zero.
	 * 
	 * @param amount 		The amount, the shield level of the ship gets decreased by.
	 */
	public String damageShields(int amount) {
		boolean hasPilot = false;
		for (int i = 0; i < pilotList.size(); i++) {
			CrewMember member = pilotList.get(i);
			if (member.viewType().equals("Pilot")) {
				hasPilot = true;
			}
		}
		shieldLevel -= amount;
		if (hasPilot) {
			shieldLevel += 10;
		}
		if (shieldLevel < 0) {
			shieldLevel = 0;
		}
		return "\nShields have been damaged by asteroid belt for " + amount
				+ ". Shield level is now " + shieldLevel + ".";
	}
	
	/**
	 * Adds 1 transporter part.
	 */
	public void addPart() {
		partsFound += 1;
	}
	
	/**
	 * Adds a CrewMember member into pilotList.
	 * @param member  		An object of type CrewMember. Member that gets added to the designated pilot list.
	 */
	public String addPilot(CrewMember member) {
		if (pilotList.size() < 2 && !pilotList.contains(member)) {
			pilotList.add(member);
			return member.viewName() + " has been designated as a pilot.";
		}
		else {
			return "Only 2 unique pilots allowed!";
		}
	}
	
	/**
	 * Clears the pilots in pilotList. Takes a boolean success to which says if the player was able to move planets successfully.
	 * If unsuccessful, pilots are given back an action count due to an action being removed when a member is added to the list.
	 * 
	 * @param success 		A boolean that is true if the crew member cant pilot the ship.
	 */
	public void clearPilots(boolean success) {
		// if crew unsuccessfully forms a pilot crew to move planets
		if (!success) {
			for (int i = 0; i < pilotList.size(); i++) {
				pilotList.get(i).addNumActions(); // numActions += 1
			}
		}
		else {
			for (int i = 0; i < pilotList.size(); i++) {
				pilotList.get(i).pilotShip(true); // numActions += 1
			}
		}
		pilotList = new ArrayList<CrewMember>();
	}
	
	/**
	 * Returns a string of the rocketship's name, shield level, and the number of parts found.
	 * 
	 * @return outputString 	A String type, that has rocketship's name, shield level, and number of parts found.
	 */
	public String printStats() {
		String outputString = "";
		outputString += "Ship name: " + shipName
				+ "\nShield level: " + shieldLevel 
				+ "\nTransporter parts found: " + partsFound
				+ "/";
		return outputString;
	}
	
}