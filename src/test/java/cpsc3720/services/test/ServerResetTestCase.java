package cpsc3720.services.test;



import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import cpsc3720.base.Base;
import cpsc3720.empire.Empire;
import cpsc3720.game.Game;
import cpsc3720.game.Grid;
import cpsc3720.ship.Alert;
import cpsc3720.ship.Ship;
import cpsc3720.user.Player;
import cpsc3720.user.User;
import cpsc3720.userserver.UserServer;
import cpsc3720.userservice.UserService;
import cpsc3720.util.Loc;

public class ServerResetTestCase {
	final static String PORT = "8976";
	
	static UserService service;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String serverTest = System.getProperty("servertest", "0");
		if (serverTest.equals("1")) {
			//start a server
			System.setProperty("server.port", PORT);
			UserServer.main(new String[] {});
			service = new RestAdapter.Builder().setLogLevel(LogLevel.BASIC).setEndpoint("http://localhost:"+PORT).build().create(UserService.class);
		}
		else {
			// Test server API directly
			service = new UserServer();
		}
		service.resetServer();
	}
	@Test
	public void testUsers(){
		List <User> result = service.listUsers();
		User u = result.get(0);
		assertEquals(u.getName(), "admin");
		assertEquals(u.isAdmin(), true);
		u = result.get(1);
		assertEquals(u.getName(), "kirk");
		assertEquals(u.isAdmin(), false);
		u = result.get(2);
		assertEquals(u.getName(), "dax");
		assertEquals(u.isAdmin(), false);
		u = result.get(3);
		assertEquals(u.getName(), "lee");
		assertEquals(u.isAdmin(), false);
		u = result.get(4);
		assertEquals(u.getName(), "grant");
		assertEquals(u.isAdmin(), false);
		
	}
	@Test
	public void testNumberOfGames() {
		
		List<String> result = service.listGames();
		assertEquals(2, result.size());
	}
	@Test 
	public void testStarTrekGameName() {
		Game result = service.getGame(0);
		assertEquals("Star Trek Forever", result.getGameName());
	}
	@Test
	public void testStarTrekEmpires() {
		List<Empire> result = service.getGame(0).getEmpires();
		assertEquals(4, result.size());
		assertEquals("Federation", result.get(0).getName());
		assertEquals(true, result.get(0).isExploration());
		assertEquals("Klingon", result.get(1).getName());
		assertEquals(false, result.get(1).isExploration());
		assertEquals("Bajoran", result.get(2).getName());
		assertEquals(true, result.get(2).isExploration());
		assertEquals("Cardassian", result.get(3).getName());
		assertEquals(false, result.get(3).isExploration());
	}
	
	@Test
	public void testStarTrekBases(){
		Grid result = service.getGame(0).getGrid();
		Object r = result.getSquare(1,3,2,1).getOccupant();
		assertEquals(((Base) r).getE().getName(), "Federation");
		r = result.getSquare(1, 4, 5, 6).getOccupant();
		assertEquals(((Base)r).getE().getName(), "Federation");
		r = result.getSquare(0,0,1,0).getOccupant();
		assertEquals(((Base)r).getE().getName(), "Klingon");
	}
	
	@Test
	public void testStarTrekShips(){
		Grid result = service.getGame(0).getGrid();
		Object r = result.getSquare(1,3,5,4).getOccupant();
		assertEquals(((Ship)r).getShipType().getShipClass(), "Constitution");
		assertEquals(((Ship)r).getShipType().getTitle(), "Starship");
		assertEquals(((Ship)r).getShipType().getEmpire(), "FED");
		assertEquals(((Ship)r).getCurMissile(), 8);
		assertEquals(((Ship)r).getCurEnergy(), 2800);
		assertEquals(((Ship)r).getCurShield(), 300);
		assertEquals(((Ship)r).getAlert(), Alert.Red);
		r = result.getSquare(1,4,3,3).getOccupant();
		assertEquals(((Ship)r).getShipType().getShipClass(), "D-12");
		assertEquals(((Ship)r).getShipType().getTitle(), "Bird of Prey");
		assertEquals(((Ship)r).getShipType().getEmpire(), "KLI");
		assertEquals(((Ship)r).getCurMissile(), 7);
		assertEquals(((Ship)r).getCurEnergy(), 1900);
		assertEquals(((Ship)r).getCurShield(), 400);
		assertEquals(((Ship)r).getAlert(), Alert.Yellow);
		r = result.getSquare(1,4,1,1).getOccupant();
		assertEquals(((Ship)r).getShipType().getShipClass(), "Miranda");
		assertEquals(((Ship)r).getShipType().getTitle(), "Starship");
		assertEquals(((Ship)r).getShipType().getEmpire(), "FED");
		assertEquals(((Ship)r).getCurMissile(), 2);
		assertEquals(((Ship)r).getCurEnergy(), 3000);
		assertEquals(((Ship)r).getCurShield(), 300);
		assertEquals(((Ship)r).getAlert(), Alert.Yellow);
		r = result.getSquare(2,5,0,4).getOccupant();
		assertEquals(((Ship)r).getShipType().getShipClass(), "D-12");
		assertEquals(((Ship)r).getShipType().getTitle(), "Bird of Prey");
		assertEquals(((Ship)r).getShipType().getEmpire(), "KLI");
		assertEquals(((Ship)r).getCurMissile(), 1);
		assertEquals(((Ship)r).getCurEnergy(), 1500);
		assertEquals(((Ship)r).getCurShield(), 5);
		assertEquals(((Ship)r).getAlert(), Alert.Red);
	}
	
	@Test
	public void testStarTrekPlayers(){
		Player p = service.listUsers().get(1).getPlayer(0);
		assertEquals(p.getName(), "kirk");
		assertEquals(p.getAff().getName(), "Federation");
		p = service.listUsers().get(2).getPlayer(0);
		assertEquals(p.getName(), "dax");
		assertEquals(p.getAff().getName(), "Klingon");
	}
	
	
	




	@Test 
	public void testCivilWarGameName() {
		Game result = service.getGame(1);
		assertEquals("Civil War", result.getGameName());
	}
	@Test
	public void testCivilWarEmpires() {
		List<Empire> result = service.getGame(1).getEmpires();
		assertEquals(2, result.size());
		assertEquals("Union", result.get(0).getName());
		assertEquals(false, result.get(0).isExploration());
		assertEquals("Confederacy", result.get(1).getName());
		assertEquals(true, result.get(1).isExploration());
	}

	@Test
	public void testCivilWarBases(){
		Grid result = service.getGame(1).getGrid();
		Object r = result.getSquare(2,4,1,0).getOccupant();
		assertEquals(((Base) r).getE().getName(), "Union");
		r = result.getSquare(1,3,2,1).getOccupant();
		assertEquals(((Base)r).getE().getName(), "Confederacy");
	}

	@Test
	public void testCivilWarShips(){
		Grid result = service.getGame(1).getGrid();
		Object r = result.getSquare(2,3,3,2).getOccupant();
		assertEquals(((Ship)r).getShipType().getShipClass(), "Ironclad");
		assertEquals(((Ship)r).getShipType().getTitle(), "Monitor");
		assertEquals(((Ship)r).getShipType().getEmpire(), "NOR");
		assertEquals(((Ship)r).getCurMissile(), 8);
		assertEquals(((Ship)r).getCurEnergy(), 280);
		assertEquals(((Ship)r).getCurShield(), 30);
		assertEquals(((Ship)r).getAlert(), Alert.Red);
		r = result.getSquare(1,4,1,0).getOccupant();
		assertEquals(((Ship)r).getShipType().getShipClass(), "Submarine");
		assertEquals(((Ship)r).getShipType().getTitle(), "Submarine");
		assertEquals(((Ship)r).getShipType().getEmpire(), "SOU");
		assertEquals(((Ship)r).getCurMissile(), 2);
		assertEquals(((Ship)r).getCurEnergy(), 190);
		assertEquals(((Ship)r).getCurShield(), 40);
		assertEquals(((Ship)r).getAlert(), Alert.Yellow);
	}	
	
	@Test 
	public void testCivilWarPlayers(){
		Player p = service.listUsers().get(3).getPlayer(1);
		assertEquals(p.getName(), "lee");
		assertEquals(p.getAff().getName(), "Confederacy");
		p = service.listUsers().get(4).getPlayer(1);
		assertEquals(p.getName(), "grant");
		assertEquals(p.getAff().getName(), "Union");
	}
}


