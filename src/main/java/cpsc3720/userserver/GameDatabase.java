package cpsc3720.userserver;

import java.util.*; 

import cpsc3720.game.*;
import cpsc3720.user.*;


public class GameDatabase {
	/* A Hashtable of Games.
	 * 		It's a Hashtable instead of an ArrayList, because Game.id is simply the index
	 * 		of that game in GameDatabase, and is being stored and used to access that game.
	 * 		If it were an ArrayList, when a Game was removed, all games after it would break.
	 * 
	 */
	
	private Hashtable<Integer,Game> games = new Hashtable<Integer,Game>();
	private ArrayList<Game> listForm=new ArrayList<Game>();
	static int curGameId=-1;
	
	//function to add a game to the database
	public int add(Game game) {
		//sets it id and increments the id
		curGameId++;
		game.setId(curGameId);
		//puts the game into hashtable and the arraylist
		games.put(curGameId,game);
		listForm.add(game);
		//returns the id
		return curGameId;
	}
	
	//removes a game based off of sending in the game
	public int remove(Game game){
		int id=game.getId();
		games.remove(id);
		listForm.remove(game);
		return id;
	}
	//removes the game based off of an index
	public Game remove(int id)
	{
		return games.remove(id);
	}
	//gets the game from the hashtable by its id num
	public Game getGame(Integer id){
		return games.get(id);
	}
	//gets the game based upon inex from the list
	public Game getGameByIndex(int index)
	{//Used for iterating over games
		return listForm.get(index);
	}
	//gets the arraylist of all the games
	public ArrayList<Game> getAll() {
		return listForm;
	}
	//empties the hashtable and the list of the games
	public void reset() {
		games.clear();
		listForm.clear();
	}
	
	//gets all game names
	public List<String> getAllNames(){
		ArrayList<String> names = new ArrayList<String>();
		
		for(int i = 0; i < games.size(); i++){
			names.add(getGame(i).getGameName());
		}
		return names;
	}
	
	//singleton of the database
	private static GameDatabase instance = new GameDatabase();

	//returns the instance of the database
	public static GameDatabase instance() {
		return instance;
	}
}
