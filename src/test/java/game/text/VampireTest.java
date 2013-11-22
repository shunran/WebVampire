package game.text;

import static org.junit.Assert.*;

import org.junit.Test;

import game.text.exceptions.ActionException;
import game.text.vampire.Vampire;
import game.text.vampire.items.Bookcase;
import game.text.vampire.items.BrickFireplace;



public class VampireTest {

	
	@Test
	public void testPlayerStartLocationIsEntranceHall() {
		Vampire vamp = start();
		assertEquals("Entrance Hall", vamp.getPlayer().getLocation().getName());
	}
	
	@Test
	public void testGoFromEntranceHallToStudy() {
		Vampire vamp = start();
		vamp.getPlayer().go(vamp.getDirection("west"));
		assertEquals("Study", vamp.getPlayer().getLocation().getName());
	}
	
	@Test
	public void testGoFromStudyToEntranceHall() {
		Vampire vamp = start();
		vamp.getPlayer().setLocation(vamp.getPlace("study"));
		vamp.getPlayer().go(vamp.getDirection("east"));
		assertEquals("Entrance Hall", vamp.getPlayer().getLocation().getName());
	}
	
	@Test
	public void testGoFromStudyNorthWhenNothingIsThere() {
		Vampire vamp = start();
		vamp.getPlayer().setLocation(vamp.getPlace("study"));
		assertFalse(vamp.getPlayer().go(vamp.getDirection("north")));
		
	}
	
	@Test(expected = ActionException.class )
	public void testGetObjectWhenNoneExists() throws ActionException {
		Vampire vamp = start();
		vamp.getPlayer().pickUp(vamp.getItem("wine"));
	}
	
	@Test
	public void testGetWineFromStudyAndCheckInventory() throws ActionException {
		Vampire vamp = start();
		vamp.getPlayer().setLocation(vamp.getPlace("study"));
		vamp.getPlayer().pickUp(vamp.getItem("wine"));
		assertEquals("Wine", vamp.getPlayer().getAll().iterator().next().getName());
	}
	
	@Test(expected = ActionException.class )
	public void testDropObjectThatYouDontHave() throws ActionException {
		Vampire vamp = start();
		vamp.getPlayer().drop(vamp.getItem("wine"));
	}
	
	
	@Test
	public void testDropWineInTheEntranceHall() throws ActionException {
		Vampire vamp = start();
		vamp.getPlayer().addOne(vamp.getItem("wine"));
		vamp.getPlayer().drop(vamp.getItem("wine"));
		assertTrue("Droped wine is not in the room", vamp.getPlayer().getLocation().getAll().contains(vamp.getItem("wine")));
	}
	
	@Test
	public void testPushBookcaseInTheLibrary() {
		Vampire vamp = start();
		Player player = vamp.getPlayer();
		player.setLocation(vamp.getPlace("library"));
		Item bookcase = new Bookcase(vamp);
		assertTrue(bookcase.executeAction("push", player).getMessage().contains("Aha! - You have revealed a Doorway"));
	}
	
	@Test
	public void testPushBookcaseIfItIsNotInTheRoom() {
		Vampire game = start();
		Player player = game.getPlayer();
		player.setLocation(game.getPlace("study"));
		Item bookcase = new Bookcase(game);
		assertFalse(bookcase.executeAction("push", player).getMessage().contains("Aha! - You have revealed a Doorway"));
	}
	
	@Test(expected = NullPointerException.class )
	public void testPushFireplace() {
		Vampire game = start();
		Player player = game.getPlayer();
		player.setLocation(game.getPlace("study"));
		Item fireplace = new BrickFireplace(game);
		fireplace.executeAction("push", player).getMessage();
	}
	
	@Test
	public void testTieRopeToParapetsAndGoDown() {
		Vampire game = start();
		game.getItem("rope").setData("tied", game.getItem("_parapets"));
		assertNotNull("Could not tie the Coil of Rope to Parapets in the Tower",
				game.getItem("rope").getData("tied"));
		Player player = game.getPlayer();
		player.setLocation(game.getPlace("tower"));
		assertEquals(false, player.go(game.getDirection("down")));
	}
	
	@Test
	public void testDoNotTieTheRopeToParapetsAndGoDown() {
		Vampire game = start();
		Player player = game.getPlayer();
		player.setLocation(game.getPlace("tower"));
		assertEquals(false, player.go(game.getDirection("down")));
	}
	

	@Test
	public void testStartTurnBeforeAndAfterWin() {
		Vampire game = start();
		assertTrue(game.startTurn());
		game.end(true);
		assertFalse( game.startTurn());
	}
	
	@Test
	public void testStartTimeIsOnePastEight() {
		Vampire game = start();
		assertEquals("08:01",game.getTime());
	}
	
	
	@Test
	public void testVampireWakesAtMidnight() {
		Vampire game = start();
		game.setTime(12*60);
		assertTrue("The Vampire did not wake up at Midnigth!",
				game.takeTurn("go east").getMessage().contains("It's midnight: the Vampire is awake, He's at your neck"));
	}
	
	@Test
	public void testGameEndsAtMidnight() {
		Vampire game = start();
		game.setTime(12*60);
		game.takeTurn("");
		assertTrue("The game did not end at midnight",
				game.isEnded());
		
	}
	
	
	@Test
	public void testGrammarForUndefinedUserinput() {
		Vampire game = start();
		assertTrue("Someone added lets dance command to the grammar or Exeption does not work",
				game.takeTurn("lets dance").getMessage().contains("I don't know that word."));
	}
	
	@Test
	public void testBrakeFireplaceWithHammer() throws ActionException {
		Vampire game = start();
		Player player = game.getPlayer();
		game.getItem("hammer").move(game.getPlayer());
		player.setLocation(game.getPlace("study"));
		game.takeTurn("hit fireplace");
		game.takeTurn("hammer");
		assertTrue("Fireplace did not brake after hitting it with the hammer",
				game.getItem("fireplace").getData("broken").equals(true));
	}
	
	
	
	
	@Test
	public void testPutOutFireAndBrakeAndCheckIfNewRoomConnectionWasMade() throws ActionException {
		Vampire game = start();
		Player player = game.getPlayer();
		game.getItem("hammer").move(game.getPlayer());
		game.getItem("holywater").move(game.getPlayer());
		player.setLocation(game.getPlace("study"));
		game.takeTurn("hit fireplace");
		game.takeTurn("hammer");
		game.takeTurn("drop holywater");
		assertTrue(game.getPlace("_fireplace").getConnection(game.getDirection("north")).equals(game.getPlace("secret passage")));
	}
	
	@Test
	public void testPlayerDiesIfGoesInToBurningFireplace() throws ActionException {
		Vampire game = start();
		Player player = game.getPlayer();
		player.setLocation(game.getPlace("study"));
		assertTrue("Player has not recieved 'You have Burned to Death' message",
				game.takeTurn("go fireplace").getMessage().contains("You have Burned to Death"));
		assertTrue("Game has not ended after going into a burning fireplace",
				game.isEnded());
		
	}
	
	@Test
	public void testHitFireplaceWithAxe() throws ActionException {
		Vampire game = start();
		Player player = game.getPlayer();
		game.getItem("axe").move(game.getPlayer());
		player.setLocation(game.getPlace("study"));
		game.takeTurn("hit fireplace"); 
		game.takeTurn("axe");
//		System.out.println(game.getItem("brick fireplace").executeAction("hit_with", (Actor)player).getMessage());
		assertNull("Axe should not brake fireplace",
				game.getItem("fireplace").getData("broken"));
	}
	
	@Test
	public void testFireplaceIsInitialyNotBroken() {
		Vampire game = start();
		assertNull("Brick fireplace should not be broken at game start",
				game.getItem("fireplace").getData("broken"));
	}
	
	@Test
	public void testPickUpHolywaterInToBucket() throws ActionException {
		Vampire game = start();
		Player player = game.getPlayer();
		game.getItem("bucket").move(game.getPlayer());
		player.setLocation(game.getPlace("chapel"));
		
		game.takeTurn("get holywater");
		game.takeTurn("bucket");
		assertTrue("Player should be able to get holywater into bucket",
				player.hasOne("holywater"));
	}
	
	@Test
	public void testKeyAppearsAfterLookingAtTheRatAndCanBePickedUp() {
		Vampire game = start();
		Player player = game.getPlayer();
		
		player.setLocation(game.getPlace("torture chamber"));
		assertTrue("Torture Chamber must have a rat",
				player.getLocation().hasOne("rat"));
		assertFalse("Key should not be visible before looking at rat",
				player.getLocation().hasOne("key"));
		game.takeTurn("look rat");
		assertTrue("Key should be visible after looking at rat",
				player.getLocation().hasOne("key"));
		game.takeTurn("get key");
		assertTrue("Player should have the key",
				player.hasOne("key"));
	}
	
	
	@Test
	public void testPlayerCanGetIntoBoatAndRowWithOar() throws ActionException {
		Vampire game = start();
		Player player = game.getPlayer();
		
		
		player.setLocation(game.getPlace("underground lake chamber"));
		assertTrue("Torture Chamber must have a boat",
				player.getLocation().hasOne("boat"));
		game.takeTurn("go boat");
		assertTrue("Player should be in the boat",
				player.getLocation().getName().equals("Boat"));
		game.takeTurn("row");
		assertTrue("Player cant row without our",
				player.getLocation().getName().equals("Boat"));
		game.getItem("oar").move(game.getPlayer());
		game.takeTurn("row");
		assertTrue("Player should be in the Gallery",
				player.getLocation().getName().equals("Gallery"));
		
	}
	
	@Test
	public void testPlayerGetsToOverhangWithCrates() throws ActionException {
		Vampire game = start();
		Player player = game.getPlayer();
		
		player.setLocation(game.getPlace("gallery"));
		assertFalse("Torture Chamber must not have a crate initialy",
				player.getLocation().hasOne("crate"));
		
		assertFalse("Player should not be able to get to overhang withoud crate in the room",
				player.getLocation().equals(game.getPlace("overhang")));
		
		game.getItem("crate").move(player.getLocation());
		game.takeTurn("go overhang");
		
		assertTrue("Player should be able to get to overhang with crate in the room",
				player.getLocation().equals(game.getPlace("overhang")));
		
	}
	
	@Test
	public void testPlayerGetsNailsWithHammer() throws ActionException {
		Vampire game = start();
		Player player = game.getPlayer();
		
		player.setLocation(game.getPlace("overhang"));
		game.takeTurn("get nails");
		
		assertFalse("Player should not be able to get nails without hammer",
				player.hasOne("nails"));
		
		game.getItem("hammer").move(player);
		game.takeTurn("get nails");
		
		assertTrue("Player should be able to get nails with hammer",
				player.hasOne("nails"));
		
	}
	
	
	@Test
	public void testPlayerCanOpenOnlyOiledDoor() throws ActionException {
		Vampire game = start();
		Player player = game.getPlayer();
		
		player.setLocation(game.getPlace("antechamber"));
		game.takeTurn("open door");
		assertNull("Door should not open if not oiled",
				game.getPlace("antechamber").getConnection(game.getDirection("north")));
		
		game.getItem("oil").move(player);
		game.takeTurn("oil door");
		game.takeTurn("open door");
		assertTrue("oiled and open door should create a connection to Vampires tomb",
				game.getPlace("antechamber").getConnection(game.getDirection("north")).equals(game.getPlace("vampire's tomb")));
		
	}
	
	
	
	
	@Test
	public void testRoomConnections() {
		Vampire game = start();
//		twoway connections
		checkConnections("study", "entrance hall", "east", game, true);
		checkConnections("library", "entrance hall", "west", game, true);
		checkConnections("library", "armory", "east", game, true);
		checkConnections("armory", "tower", "east", game, true);
		checkConnections("hidden corridor", "alchemist's lab", "north", game, true);
		checkConnections("alchemist's lab", "storeroom", "north", game, true);
		checkConnections("lower tower", "chapel", "south", game, true);
		checkConnections("secret passage", "underground lake chamber", "north", game, true);
		checkConnections("secret passage", "torture chamber", "west", game, true);
		
		
//		oneway connections
		checkConnections("_fireplace", "study", "south", game, false);
		checkConnections("lower tower", "tower", "up", game, false);
		checkConnections("chapel", "armory", "up", game, false);
		checkConnections("secret passage", "_fireplace", "south", game, false);
		checkConnections("torture chamber", "alchemist's lab", "west", game, false);
		checkConnections("boat", "lake", "south", game, false);
		checkConnections("overhang", "gallery", "down", game, false);
		checkConnections("storeroom", "study", "up", game, false);
		
	}
	
	
	private Vampire start() {
		Vampire game = new Vampire();
		game.initialize();
		return game;
	}
	
	private void checkConnections(String fromKey, String toKey, String viaKey, Vampire game, boolean twoWay) {
		Place from = game.getPlace(fromKey);
		Place to = game.getPlace(toKey);
		Direction via = game.getDirection(viaKey);
		
		String errorMessage = "'" + from.getName() + "' is not connected to '" + to.getName() + "' via Direction '"+ via.getName() + "'";
		assertTrue(errorMessage,from.getConnection(via).equals(to));
		
		Place temp = from;
		from = to;
		to = temp;
		via = via.getOpposite();
		
		if(twoWay) {
			errorMessage = "Can not get back from '" + from.getName() + "' "
					+ "to '" + to.getName() + "' via Direction '"+ via.getName() + "'";
			assertTrue(errorMessage,from.getConnection(via).equals(to));
		}else{
			errorMessage = "Should not be able to go back from '" + from.getName() 
					+"' to '" + to.getName();
			assertNull(errorMessage, from.getConnection(via));	 
		}
		
	}


}


