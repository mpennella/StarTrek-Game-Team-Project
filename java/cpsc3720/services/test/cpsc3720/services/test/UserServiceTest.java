package cpsc3720.services.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import cpsc3720.empire.Empire;
import cpsc3720.ship.ShipType;
import cpsc3720.user.User;
import cpsc3720.userserver.UserServer;
import cpsc3720.userservice.UserService;
import cpsc3720.weapon.Weapon;

public class UserServiceTest {

	final static String PORT = "8976";

	static UserService service;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String serverTest = System.getProperty("servertest", "0");
		if (serverTest.equals("1")) {
			// Start a server
			System.setProperty("server.port", PORT);
			UserServer.main(new String[] {});
	
			service = new RestAdapter.Builder()
					.setLogLevel(LogLevel.BASIC)
					.setEndpoint("http://localhost:" + PORT).build()
					.create(UserService.class);
		} else {
			// Test server API directly
			service = new UserServer();
		}
	}

	@Test
	public void testListUsers() {
		service.resetUsers();
		List<User> result = service.listUsers();
		assertEquals(3, result.size());
	}

	@Test
	public void testAddUser() {
		service.resetUsers();
		User user = new User("Barney", "barney@bedrock.com", "test123", false);
		int result = service.addUser(user);
		assertNotEquals(0, result);

		List<User> users = service.listUsers();
		assertEquals(4, users.size());

	}
	@Test
	public void testRemoveUser() {
		service.resetUsers();
		User user = new User();
		user.setId(1);
		int result = service.removeUser(user);
		assertEquals(1, result);
		
		List<User> users = service.listUsers();
		assertEquals(2, users.size());	
	}
	
	// WEAPON TESTS
	@Test
	public void testListWeapons() {
		service.resetWeapons();
		List<Weapon> result = service.listWeapons();
		assertEquals(3, result.size());
	}

	@Test
	public void testAddWeapon() {
		service.resetWeapons();
		Weapon weapon = new Weapon("TestWeapon1", false);
		Weapon weapon2 = new Weapon("TestWeapon2", true);
		int result = service.addWeapon(weapon);
		assertNotEquals(0, result);
		int result2 = service.addWeapon(weapon2);
		assertNotEquals(0, result2);

		List<Weapon> weapons = service.listWeapons();
		assertEquals(5, weapons.size());

	}
	@Test
	public void testRemoveWeapon() {
		service.resetWeapons();
		Weapon weapon = new Weapon();
		weapon.setId(1);
		int result = service.removeWeapon(weapon);
		assertEquals(1, result);
		
		List<Weapon> weapons = service.listWeapons();
		assertEquals(2, weapons.size());	
	}
	
	// SHIP TEST
	@Test
	public void testListShip() {
		service.resetShip();
		List<ShipType> result = service.listShips();
		assertEquals(3, result.size());
	}

	@Test
	public void testAddShip() {
		service.resetShip();
		ShipType s = new ShipType("name", 2, 1);
		service.addShip(s);
		List<ShipType> ships = service.listShips();
		assertEquals(4, ships.size());

	}
	@Test
	public void testRemoveShip() {
		service.resetShip();
		ShipType ship = new ShipType();
		ship.setId(1);
		int result = service.removeShip(ship);
		assertEquals(1, result);
		
		List<ShipType> ships = service.listShips();
		assertEquals(2, ships.size());	
	}
	
	// EMPIRE TEST
		@Test
		public void testListEmpire() {
			service.resetEmpire();
			List<Empire> result = service.listEmpires();
			assertEquals(3, result.size());
		}

		@Test
		public void testAddEmpire() {
			service.resetEmpire();
			Empire e = new Empire("TestEmpire");
			service.addEmpire(e);
			List<Empire> empires = service.listEmpires();
			assertEquals(4, empires.size());

		}
		@Test
		public void testRemoveEmpire() {
			service.resetEmpire();
			Empire empire = new Empire();
			empire.setID(1);
			int result = service.removeEmpire(empire);
			assertEquals(1, result);
			
			List<Empire> empires = service.listEmpires();
			assertEquals(2, empires.size());	
		}

}
