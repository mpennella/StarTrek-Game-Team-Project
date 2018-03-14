package cpsc3720.game;

import java.util.ArrayList;
import java.util.List;

import cpsc3720.empire.Empire;
import cpsc3720.user.User;
import cpsc3720.userserver.EmpireDatabase;

public class Game {
	
	
	private int id=-1;
	private String gameName;
	private int stardate;
	private List<Empire> empires;
	
	private Grid grid;
	
	//blank constuctor that sets the value to place holders
	public Game(){
		gameName="";
		stardate=-1;
		empires = new ArrayList<Empire>();
		grid=new Grid();
	}
	
	//constructor that takes in all fields except the id
	public Game(String gameName, int stardate, ArrayList<User> players) 
	{
		super();
		this.gameName = gameName;
		this.stardate = stardate;
		empires=new ArrayList<Empire>();
		
		grid=new Grid();
	}
	
	//returns the list of empires in the game
	public List<Empire> getEmpires()
	{
		return empires;
	}
	
	//gets the id
	public int getId() {
		return id;
	}
	
	//sets the id
	public void setId(int Id) {
		this.id = Id;
	}
	//gets the game name
	public String getGameName() {
		return gameName;
	}
	//sets the gamename
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	//gets the stardate 
	public int getStardate() {
		return stardate;
	}
	//sets the stardate
	public void setStardate(int stardate) {
		this.stardate = stardate;
		this.grid.setStardate(stardate);
	}
	//gets the grid
	public Grid getGrid() {
		return grid;
	}
	//sets the grid
	public void setGrid(Grid grid) {
		this.grid = grid;
	}
	@Override
	public String toString() {
		return "Game [Id=" + id + ", gameName=" + gameName + ", stardate="
				+ stardate + ", empires=" + empires +"]";
	}
	//sets the empires
	public void setEmpires(List<Empire> empires) {
		this.empires = empires;
	}
	//adds an empire
	public void addEmpire(Empire add)
	{
		empires.add(add);
	}
	//gets an empire based upon an index
	public Empire getEmpire(int n) {
		return empires.get(n);
	}
	//gets the empire based upon its id
	public Empire getEmpire(String id) {
		for (int n=0;n<empires.size();n++)
		{
			if (empires.get(n).getID().equals(id))
				return empires.get(n);
		}
		return null;
	}
}
