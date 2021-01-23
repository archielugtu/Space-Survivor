package test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.CrewMember;
import main.GameEnvironment;

class CrewMemberTest {
	private ArrayList<CrewMember> members = new ArrayList<CrewMember>();
	private CrewMember member;
	

	@BeforeEach
	public void initMember() {
		members.add(new CrewMember("Brute", "Brute"));
		members.add(new CrewMember("Mechanic", "Mechanic"));
		members.add(new CrewMember("Haggler", "Haggler"));
		members.add(new CrewMember("Scout", "Scout"));
		members.add(new CrewMember("Medic", "Medic"));
		members.add(new CrewMember("Pilot", "Pilot"));
	}

	
	@Test
	public void typeHealthTest() {
		// brute should have 200, the rest 100
		// checks max too
		assertEquals(200, members.get(0).viewHealth());
		assertEquals(200, members.get(0).viewMaxHealth());
		for (int i = 1; i < members.size()-1; i++) {
			assertEquals(100, members.get(i).viewHealth());
			assertEquals(200, members.get(0).viewMaxHealth());
		}
	}
	
	@Test
	public void typeRepairValueTest() {
		for (CrewMember member:members) {
			int expectedValue = 25;
			if (member.viewType().equals("Mechanic")) {
				expectedValue += 15;
			}
			assertEquals(expectedValue, member.viewRepairValue());
		}
	}
	
	@Test
	public void addNumActionsTest() {
		CrewMember member = members.get(0);
		// test limit
		member.addNumActions();
		assertEquals(2, member.viewNumActions());
		// test when number of actions change
		member.eatFood(0);
		assertEquals(1, member.viewNumActions());
		// test if number of actions get incremented
		member.addNumActions();
		assertEquals(2, member.viewNumActions());
		
	}
	
	@Test
	public void infectTest() {
		// infects and heals members and checks if infect state is true and then false
		for (int i = 0; i < members.size(); i++) {
			members.get(i).infect();
			assertTrue(members.get(i).viewInfectState());
			members.get(i).infect();
			assertTrue(members.get(i).viewInfectState());
			members.get(i).healSelf(0, true);
			assertFalse(members.get(i).viewInfectState());
		}	
	}
	
	@Test
	public void numActionsTest() {
		for (int i = 0; i < members.size(); i++) {
			assertEquals(2, members.get(i).viewNumActions());
			members.get(i).eatFood(0);
			assertEquals(1, members.get(i).viewNumActions());
		}
	}
	
	
	@Test
	public void healthTest() {
		// tests damage and healing
		// brute test
		members.get(0).takeDamage(50);
		assertEquals(150, members.get(0).viewHealth());
		members.get(0).healSelf(50, false);
		assertEquals(200, members.get(0).viewHealth());
		
		// health max test (with brute)
		members.get(0).healSelf(999, false);
		assertEquals(200, members.get(0).viewHealth());
		
		// other type test
		for (int i = 1; i < members.size()-1; i++) {
			members.get(i).takeDamage(50);
			assertEquals(50, members.get(i).viewHealth());
			members.get(i).healSelf(50, false);
			assertEquals(100, members.get(i).viewHealth());
		}
	}
	
	@Test
	public void killTest() {
		// kill all of them
		for (CrewMember member:members) {
			member.takeDamage(999);
			assertEquals(0, member.viewHealth());
		}
	}
	
	@Test
	public void hungerTest() {
		for (int i = 0; i < members.size(); i++) {
			member = members.get(i);
			member.nextDay();
			assertNotEquals("Hunger never increased!", member.viewHunger(), 0);
			member.eatFood(100);
			assertEquals(0, member.viewHunger());
		}
	}
	
	@Test
	public void tirednessTest() {
		for (int i = 0; i < members.size(); i++) {
			member = members.get(i);
			members.get(i).nextDay();
			assertNotEquals("Tiredness never increased!", member.viewTiredness(), 0);
			members.get(i).sleep();
			assertEquals(0, members.get(i).viewTiredness());
		}
	}
	
	@Test
	public void tooManyDaysTest() {
		// found a bug for brute
		// brute is dead but gets refunded 2HP every time...
		member = members.get(0);
		for (int i = 0; i < 50; i++) {
			System.out.println(member.viewHunger());
			member.nextDay();
		}
		assertEquals(0, member.viewHealth());
		assertEquals(100, member.viewTiredness());
		assertEquals(100, member.viewHunger());
		
//		assertTrue(member.canAct(false));
		
		member = members.get(4);
		member.infect();
		for (int i = 0; i < 20; i++) {
			System.out.println(member.viewHealth());
			member.nextDay();
		}
		assertEquals(0, member.viewHealth());
		assertEquals(100, member.viewTiredness());
		assertEquals(100, member.viewHunger());
		
//		assertTrue(member.canAct(false));
	}
	
	@Test
	public void canActTest() {
		System.out.println();
		for (int i = 0; i < members.size(); i++) {
			// fresh
			member = members.get(i);
			assertTrue(member.canAct(true).equals("Success"));
			// use up all actions
			member.eatFood(0);
			member.eatFood(0);
			assertFalse(member.canAct(true).equals("Success"));
			// build up tiredness and check if member is too tired
			for (int j = 0; j <= 10; j++) {
				member.nextDay();
			}
			assertFalse(member.canAct(false).equals("Success"));
		}
	}
	
	@Test
	public void otherActionsTest() {
		member = members.get(0);
		assertTrue(member.repairedShields().equals("Success"));
		assertTrue(member.searchPlanet().equals("Success"));
		// no more actions left
		assertFalse(member.pilotShip(false).equals("Success"));
		member.addNumActions();
		assertTrue(member.pilotShip(false).equals("Success"));
	}
	
	@Test
	public void printTest() {
		member = members.get(0);
		// should display member type
		assertTrue(member.printStats().contains(member.viewType()));
		// should display infected message
		member.infect();
		assertTrue(member.printStats().contains(" ---INFECTED--- "));
		member.takeDamage(999);
		// should display dead message
		assertTrue(member.printStats().contains(" ---DEAD--- "));
	}
}
