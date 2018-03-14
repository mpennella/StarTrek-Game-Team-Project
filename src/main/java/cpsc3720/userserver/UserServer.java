package cpsc3720.userserver;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cpsc3720.actions.ActionAIAttack;
import cpsc3720.actions.ActionFireTorpedo;
import cpsc3720.actions.ActionNavigate;
import cpsc3720.actions.ActionSetAlertLevel;
import cpsc3720.actions.AlertLevelException;
import cpsc3720.actions.FireTorpedoException;
import cpsc3720.actions.NavigationException;
import cpsc3720.base.Base;
import cpsc3720.empire.Empire;
import cpsc3720.game.Game;
import cpsc3720.game.Grid;
import cpsc3720.loader.FileLoader;
import cpsc3720.ship.Alert;
import cpsc3720.ship.Ship;
import cpsc3720.ship.ShipType;
import cpsc3720.user.Player;
import cpsc3720.user.User;
import cpsc3720.userservice.GameAndPlayerList;
import cpsc3720.userservice.UserService;
import cpsc3720.util.Loc;
import cpsc3720.weapon.Weapon;

@RestController
@EnableAutoConfiguration
public class UserServer implements UserService {
	Logger logger = LoggerFactory.getLogger(getClass());
	boolean resetStatus = false;
	//home for the server

    @RequestMapping("/")
    String home() {
    	resetServer();
        return "The Server is alive and well.";
    }
    
    //gets a list of users not currently used
    @RequestMapping("/ListUsers")
    public List<User> listUsers() {
    	logger.info("Returning Users...");
    	return UserDatabase.instance().getAll();
    }
    @RequestMapping("/GetUser")
    public User getUser(@RequestBody String name) {
    	logger.info("Getting user");
    	return UserDatabase.instance().getUser(name);
    }
    //adds a user to the user database
    @RequestMapping("/AddUser")
    public int addUser(@RequestBody User user) {
    	int newId = UserDatabase.instance().add(user);
			logger.info("Added user: " + user);
	    return newId;
    }
    
    //removes a user from the user database
    @RequestMapping("/RemoveUser")
	public int removeUser(@RequestBody User user) {
			int oldId = UserDatabase.instance().remove(user);
			logger.info("Removed user number " + oldId);
			return oldId;
	}
    
    //resets the user database
    @RequestMapping("/ResetUsers")
    public int resetUsers() {
    	logger.info("Reseting users.");
    	UserDatabase.instance().reset();
    	UserDatabase.instance().add(new User("admin", "admin@cpsc3720game.com", "p", true));
    	UserDatabase.instance().add(new User("kirk", "kirk@cpsc3720game.com", "p", false));
    	UserDatabase.instance().add(new User("dax", "dax@cpsc3720game.com", "p", false));
    	UserDatabase.instance().add(new User("lee", "lee@cpsc3720game.com", "p", false));
    	UserDatabase.instance().add(new User("grant", "grant@cpsc3720game.com", "p", false));
    	return 0;
    } 
    
    //resets the server and load the 2 basic games
    @RequestMapping("/ResetStatus")
    public boolean resetStatus(){
    	if(!resetStatus) {
    		resetServer();
    	}
    	resetStatus = true;
    	return true;
    }
    
    @RequestMapping("/ResetServer")
    public int resetServer(){
    	logger.info("Reseting the server");
    	resetUsers();
    	logger.info("Resting the games");
    	GameDatabase.instance().reset();
    	logger.info("Creating the star trek game");
    	Game game = new Game();
    	GameDatabase.instance().add(game);
    	game.setGameName("Star Trek Forever");
    	game.setStardate(2236);
    	Empire fed = new Empire("FED", "Federation", true);//MOVE
    	Empire kli = new Empire("KLI", "Klingon", false);
    	Empire baj = new Empire("BAJ", "Bajoran", true);
    	Empire car = new Empire("CAR", "Cardassian", false);
    	Weapon phas = new Weapon("PHAS", "Phaser", true, 100);//MOVE
    	Weapon ptor = new Weapon("PTOR", "Photon Torpedo", false, 300);
    	Weapon ator = new Weapon("ATOR", "Antimater Torpedo", false, 10000);
    	Weapon gtor = new Weapon("GTOR", "Gravimetric Torpedo", false, 800);
    	Weapon pcan = new Weapon("PCAN", "Pulse Cannon", true, 150);
    	ShipType stc = new ShipType("STC", "Starship", "Constitution", "FED",  phas, ptor, 3000, 9, 500, 10);//MOVE STORAGE
    	ShipType stm = new ShipType("STM", "Starship", "Miranda", "FED", phas, ator, 4000, 4, 400, 2);
    	ShipType bop = new ShipType("BOP", "Bird of Prey", "D-12", "KLI", pcan, gtor, 2500, 5, 600, 5);
    	ShipType cws = new ShipType("CWS", "Cruiser", "Galor", "CAR", phas, ptor, 200, 6, 300, 12);
    	ShipType bws = new ShipType("BWS", "Starship", "Antares", "BAJ", phas, ptor, 2500, 5, 300, 6);
    	fed.addShipType(stc);
    	fed.addShipType(stm);
    	kli.addShipType(bop);
    	baj.addShipType(bws);
    	car.addShipType(cws);
    	
    	Ship gs0 = new Ship(stc, 2800, 8, Alert.Red, 300);
    	Ship gs1 = new Ship(bop, 1900, 7, Alert.Yellow, 400);
    	Ship gs2 = new Ship(stm, 3000, 2, Alert.Yellow, 300);
    	Ship gs3 = new Ship(bop, 1500, 1, Alert.Red, 5);
    	
    	// Shouldn't this start from 0?
    	User u = UserDatabase.instance().getAll().get(1);
    	u.addPlayer(0, new Player("kirk", 0, fed, new Loc(1,3,5,4)));
    	gs0.setPlayerName(u.getPlayer(0));
    	User u1 = UserDatabase.instance().getAll().get(2);
    	u1.addPlayer(0,new Player("dax", 0, kli, new Loc(1,4,3,3)));
    	gs1.setPlayerName(u1.getPlayer(0));
    	gs2.setPlayerName("CPU");
    	gs3.setPlayerName("CPU");
    	game.getGrid().getSquare(new Loc(1,3,5,4)).setOccupant(gs0);
    	game.getGrid().getSquare(new Loc(1,4,3,3)).setOccupant(gs1);
    	game.getGrid().getSquare(new Loc(1,4,1,1)).setOccupant(gs2);
    	game.getGrid().getSquare(new Loc(2,5,0,4)).setOccupant(gs3);
    	game.getGrid().getSquare(new Loc(1,3,2,1)).setOccupant(new Base(fed));
    	game.getGrid().getSquare(new Loc(1,4,5,6)).setOccupant(new Base(fed));
    	game.getGrid().getSquare(new Loc(0,0,1,0)).setOccupant(new Base(kli));
    	

    	game.getEmpires().add(fed);
    	game.getEmpires().add(kli);
    	game.getEmpires().add(baj);
    	game.getEmpires().add(car);
    	
    	logger.info("Creating the civil war game");
    	Game civ = new Game();
    	civ.setId(1);
    	GameDatabase.instance().add(civ);
    	civ.setGameName("Civil War");
    	civ.setStardate(1862);
    	Empire nor = new Empire("NOR", "Union", false);
    	Empire sou = new Empire("SOU", "Confederacy", true);
    	civ.getEmpires().add(nor);
    	civ.getEmpires().add(sou);
    	Weapon can = new Weapon("CAN", "Water Cannon", true, 10);
    	Weapon tor = new Weapon("TOR", "Torepedo", false, 30);
    	ShipType mon = new ShipType("MON", "Monitor", "Ironclad", "NOR", can, tor, 300, 9, 50, 10);
    	ShipType sub = new ShipType("SUB", "Submarine", "Submarine", "SOU", can, tor, 400, 4, 40 ,2);
    	nor.addShipType(mon);
    	sou.addShipType(sub);
    	Ship cs0 = new Ship(mon, 280, 8, Alert.Red, 30);
    	Ship cs1 = new Ship(sub, 190, 2, Alert.Yellow, 40);
    	User cu = UserDatabase.instance().getAll().get(3);
    	cu.addPlayer(1 , new Player("lee", 1, sou, new Loc(1,4,1,0)));
    	cs0.setPlayerName(cu.getPlayer(1));
    	User cu1 = UserDatabase.instance().getAll().get(4);
    	cu1.addPlayer(1, new Player("grant", 1, nor, new Loc(2,3,3,2)));
    	cs0.setPlayerName(cu1.getPlayer(1));
    	civ.getGrid().getSquare(1, 4, 1, 0).setOccupant(cs1);
    	civ.getGrid().getSquare(2, 3, 3, 2).setOccupant(cs0);
    	civ.getGrid().getSquare(2,4,1,0).setOccupant(new Base(nor));
    	civ.getGrid().getSquare(1,3,2,1).setOccupant(new Base(sou));

    	
    	logger.info("Finished reseting the server");
    	return 1;
    }
    
    @RequestMapping("/LoadGame")
    public Player loadGame(@RequestBody String file)
    {
    	System.out.println("--reading game");
    	logger.info("Loading Game");
    	Player p=FileLoader.readGame(file);
    	System.out.println("-returning id");
    	return p;
    }
    
    
    //gets your the game that you request by and index
    @RequestMapping("/GetGame")
    public Game getGame(@RequestBody Integer id){
    	logger.info("Returning game: " + id);
    	return GameDatabase.instance().getGame(id);
    }
    
    //literally the same thing but I don't want to delete someones code
    	//not the same thing as above.
    @RequestMapping("/GetGameByIndex")
    public Game getGameByIndex(@RequestBody Integer index) {
    	logger.info("Returning game by Index: "+index);
    	return GameDatabase.instance().getGameByIndex(index);
    }
    
    //returns all games on the server
    @RequestMapping("/ListGames")
    public List<String> listGames(){
    	logger.info("Returning games..." );
    	return GameDatabase.instance().getAllNames();
    }
    
    //gets just the grid from a game by an index
    @RequestMapping("/GetGrid")
    public Grid getGrid(@RequestBody Integer index){
    	logger.info("Returing the grid for game " + index);
    	return GameDatabase.instance().getGame(index).getGrid();
    }
    
    //old code that I don't want to delete incase we need it later
    @RequestMapping("/AddPlayer")
    public int addPlayer(@RequestBody Player p){//MOVE
    	logger.info("Adding player " + p.getName() + " to game " + p.getId());
    	//return GameDatabase.instance().addPlayer(p.getId(), p);  
    	return 0;
    }
    
    //old code from the first part of the project no longer used
    @RequestMapping("/ListWeapons")
    public List<Weapon> listWeapons() {
    	logger.info("Returning Weapons...");
    	return WeaponDatabase.instance().getAll();
    }
    
    //old code from the first part of the project no longer used
    @RequestMapping("/AddWeapon")
    public int addWeapon(@RequestBody Weapon weapon) {
    	WeaponDatabase.instance().add(weapon);
    	logger.info("Added weapon: " + weapon.getId());
	    return 1;
    }

    //old code from the first part of the project no longer used
    @RequestMapping("/RemoveWeapon")
		public int removeWeapon(@RequestBody Weapon weapon) {
			int oldId = WeaponDatabase.instance().remove(weapon);
			if(oldId != -1) {
				logger.info("Removed weapon number " + oldId);
			} else {
				logger.info("Failed to find weapon!");
			}
			return oldId;
	}
    
    //old code from the first part of the project no longer used
    @RequestMapping("/ResetWeapons")
    public int resetWeapons() {
    	WeaponDatabase.instance().reset();
    	return 0;
    };
    
    //old code from the first part of the project no longer used
    @RequestMapping("/ListShips")
    public List<ShipType> listShips() {
    	logger.info("Returning Ships...");
    	return ShipTypeDatabase.instance().getAll();
    }
    
    //old code from the first part of the project no longer used
    @RequestMapping("/AddShip")
    public int addShip(@RequestBody ShipType ship) {
    	ShipTypeDatabase.instance().add(ship);
    	logger.info("Added ship: " + ship.getId());
    	return 1;
    }
    
    //old code from the first part of the project no longer used
    @RequestMapping("/RemoveShip")
    public int removeShip(@RequestBody ShipType ship) {
    	int oldId = ShipTypeDatabase.instance().remove(ship);
    	if(oldId != -1) {
    		logger.info("Removed ship number " + oldId);
    	} else {
    		logger.info("Failed to find ship!");
    	}
    	return oldId;
    }
    
    //old code from the first part of the project no longer used
    @RequestMapping("/ResetShip")
    public int resetShip() {
    	ShipTypeDatabase.instance().reset();
    	return 0;
   	}

    //Function to fire a torpedo from a desired location that should have a ship 
    //If it doesn't have a ship it doesn't do anything 
    //Takes in a fire torpedo action
    @RequestMapping("/PerformFireTorpedo")
	public int performFireTorpedo(@RequestBody ActionFireTorpedo a){
    	logger.info("Attack from " + a.getAttacker() + " to " + a.getDefender());
    	try {
			a.performAction(GameDatabase.instance().getGame(a.getGameId()).getGrid());
			new ActionAIAttack(0, a.getAttacker()).performAction(GameDatabase.instance().getGame(a.getGameId()).getGrid());
		} catch (FireTorpedoException e) {
			e.printStackTrace();
		}
    	return 1;
    }
    
    //Function to change the alert level of a ship at a desired location
    //If there isn't a ship there it does nothing
    //Takes in a ActionSetAlertLevel
    @RequestMapping("/PerformSetAlertLevel")
	public int performSetAlertLevel(@RequestBody ActionSetAlertLevel a){
    	logger.info("Setting the alert level of ship at " + a.getShipLoc() + " to " + a.getAlert());
    	try {
			a.performAction(GameDatabase.instance().getGame(a.getGameId()).getGrid());
			new ActionAIAttack(0, a.getShipLoc()).performAction(GameDatabase.instance().getGame(a.getGameId()).getGrid());
		} catch (AlertLevelException e) {
			e.printStackTrace();
		}
    	return 1;
    }
    
    //Function to navigate a ship based upon the players ship loc and updates the players ship loc
    //If there isn't a ship there it does nothing
    //takes in a actionnavigation
    @RequestMapping("/PerformNavigation")
    public Player performNavigation(@RequestBody ActionNavigate a){
    	logger.info("Ship moving from " + a.getStart().getShipLoc() + " to " + a.getDestination());
    	try {
			a.performAction(GameDatabase.instance().getGame(a.getGameId()).getGrid());
			new ActionAIAttack(0, a.getStart().getShipLoc()).performAction(GameDatabase.instance().getGame(a.getGameId()).getGrid());
		} catch (NavigationException e) {
			e.printStackTrace(); 
		}
    	return a.getStart();
    }
    
    

    // Exception handler reports errors via JSON
    
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public String handleException(Exception ex) {
    	logger.warn("Uh oh", ex);
    	return ex.getMessage();
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(UserServer.class, args);
        
    }
}
