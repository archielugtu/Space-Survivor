package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Item;
import main.MedKit;
import main.Food;
import main.Outpost;

class OutpostTest {
	
	Outpost outpost = new Outpost();
	Item item;
	ArrayList<MedKit> medkits;
	ArrayList<Food> foods;
	
	
	@BeforeEach
	public void initOutpost() {
		outpost.initItems();
		medkits = outpost.getMedKit();
		foods = outpost.getFood();
	}

	@Test
	public void buyItemTest() {
		// tests for item when buying from outpost
		// fails if index
		for (int i = 1; i < 8; i++) {
			if (i <= 4) {
				outpost.buyMedKit(i);
				assertTrue(outpost.buyMedKit(i) instanceof MedKit);
			}
			else {
				outpost.buyFood(i);
				assertTrue(outpost.buyFood(i) instanceof Food);
			}
		}
	}
	
	@Test
	public void printItemsTest() {
//		assertTrue(outpost.printItems(true).contains("Discounted:"));
//		assertFalse(outpost.printItems(false).contains("Discounted:"));
	}
	
}
