package main;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class implements the random events that occur throughout the game, such as Asteroid belt, space pirates, and plague.
 * 
 * @author Raymond Jr Tamse, Rchi Lugtu
 * @version 1.2, May 2019 
 *
 */
public class RandomEvents {
	/**
	 * An object of type GameEnvironment.
	 */
	private GameEnvironment game;
	private String output;
	
	/**
	 * The constructor the RandomEvents class, which creates a RandomEvents object and 
	 * chooses the random events randomly.
	 * 
	 * @param newGame 		An object of type GameEnvironment.
	 * @param isFlying  	A boolean that's true if crew is flying in space, otherwise false.
	 */
	public RandomEvents(GameEnvironment newGame, boolean isFlying) {
		game = newGame;
		output = "";
		double enemyChosen = Math.random();
		if (enemyChosen <= 0.2) {
			choosePirates();
		}
		else if (enemyChosen <= 0.4) {
			choosePlague();
		}
		///////
		else if (enemyChosen <= 0.9 && isFlying) {
			chooseAsteroid();
		}
		else if (isFlying){
			output += "...nothing happened!";
		}
	}
	
	/**
	 * Prints out a message that pirates have boarded, and then it takes a random item from crew's inventory
	 */
	public void choosePirates() {
		output += "\nAlien pirates have boarded the ship!";
		double stolenChance = Math.random();
		Item stolenItem;
		
		
		// check inventory size
		int foodListSize = game.getCrew().getFoods().size();
		int medkitListSize = game.getCrew().getMedKits().size();
		// steal food
		if (stolenChance <= 0.5 && foodListSize > 0) {
			Random rand = new Random();
			int stolenIndex = rand.nextInt(game.getCrew().getFoods().size());
			
			stolenItem = game.getCrew().getFoods().get(stolenIndex);
			game.getCrew().getFoods().remove(stolenIndex);
			output += "\nThey left the ship stealing a " + stolenItem.getName() 
			+ " from your inventory.";
		}
		// steal medkit
		else if (medkitListSize > 0) {
			Random rand = new Random();
			int stolenIndex = rand.nextInt(game.getCrew().getMedKits().size());
			
			stolenItem = game.getCrew().getMedKits().get(stolenIndex);
			game.getCrew().getMedKits().remove(stolenIndex);
			output += "\nThey left the ship stealing a " + stolenItem.getName() 
			+ " from your inventory.";
		}
		else {
			output += "\n...but they have nothing to steal! Your inventory is empty! They leave empty-handed.";
		}
	}
	/**
	 * Randomly chooses one or more crew members to be infected by the space plague.
	 */
	public void choosePlague() {
		// space plague
		int numInfect = new Random().nextInt(game.getCrewSize());
		if (numInfect == 0) {
			numInfect = 1;
		}
		output += "\nYou just found out the that " + numInfect 
				+ " of your crew members have been infected by the space plague!";
		for (int i = 0; i < numInfect; i++) {
			int randIndex = new Random().nextInt(game.getCrewSize());
			game.getCrew().getMembers().get(randIndex).infect();
		}
	}
	/**
	 * Randomly damages the shield of the rocketship when the crew is flying in space.
	 */
	public void chooseAsteroid() {
		Rocketship ship = game.getCrew().getShip();
		// scale the damage down depending on the ship's shield level
		int amount = (int) Math.round(0.25 * 100);
		output += "\n" + ship.damageShields(amount);
		if (game.searchType("Pilot")) {
			output += "\nSince there is a pilot present, damage from asteroid belt collision is reduced by 10.";
		}
	}
	
	public String returnOutput() {
		return output;
	}
	
}