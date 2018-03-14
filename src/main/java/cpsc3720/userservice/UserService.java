package cpsc3720.userservice;

import java.util.List;

import retrofit.http.Body;
import retrofit.http.POST;
import cpsc3720.actions.ActionAIAttack;
import cpsc3720.actions.ActionFireTorpedo;
import cpsc3720.actions.ActionNavigate;
import cpsc3720.actions.ActionSetAlertLevel;
import cpsc3720.game.Game;
import cpsc3720.game.Grid;
import cpsc3720.ship.ShipType;
import cpsc3720.user.Player;
import cpsc3720.user.User;
import cpsc3720.weapon.Weapon;

public interface UserService {
	
	@POST("/ListUsers")
	List<User> listUsers();
	
	@POST("/AddUser")
	int addUser(@Body User user);
	
	@POST("/GetUser")
	User getUser(@Body String name);
	
	@POST("/RemoveUser")
	int removeUser(@Body User user);
	
	@POST("/ResetUsers")
	int resetUsers();
	
	@POST("/ListWeapons")
	List<Weapon> listWeapons();
	
	@POST("/AddWeapon")
	int addWeapon(@Body Weapon weapon);
	
	@POST("/RemoveWeapon")
	int removeWeapon(@Body Weapon weapon);
	
	@POST("/ResetWeapons")
	int resetWeapons();

	@POST("/ListShips")
	List<ShipType> listShips();//MOVE
	
	@POST("/AddShip")
	int addShip(@Body ShipType ship);
	
	@POST("/RemoveShip")
	int removeShip(@Body ShipType ship);
	
	@POST("/ResetShip")
	int resetShip();
	
	@POST("/ResetStatus")
	boolean resetStatus();
	
	@POST("/ResetServer")
	int resetServer();
	@POST("/LoadGame")
	Player loadGame(@Body String file);
	@POST("/GetGame")
	Game getGame(@Body Integer id);
	@POST("/GetGameByIndex")
	Game getGameByIndex(@Body Integer index);
	
	@POST("/GetGrid")
	Grid getGrid(@Body Integer index);
	
	@POST("/ListGames")
	List<String> listGames();
	
	@POST("/AddPlayer")
	int addPlayer(@Body Player p);
	
	@POST("/PerformFireTorpedo")
	int performFireTorpedo(@Body ActionFireTorpedo a);
	
	@POST("/PerformSetAlertLevel")
	int performSetAlertLevel(@Body ActionSetAlertLevel a);
	
	@POST("/PerformNavigation")
	Player performNavigation(@Body ActionNavigate a);
	
}
