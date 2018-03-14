package cpsc3720.user;

import java.util.*;
/* Each instance of this class contains the credentials of a specific user as well as their admin status and a hash table
of individual Player objects associated with that user */
public class User {
	int id;
	String name;
	String email;
	String pass;
	boolean admin;

	private Hashtable<Integer,Player> players;
	
	public User(){
		name="";
		email="@@";
		pass="p";
		admin=false;
		players=new Hashtable<Integer,Player>(4);
	}
	public User(String setname)
	{
		name=setname;
		email="@@";
		pass="p";
		admin=false;
		players=new Hashtable<Integer,Player>(4);
	}
	public User(String name, String email, String pass, boolean admin) {
		super();
		this.name = name;
		this.email = email;
		this.pass = pass;
		this.admin = admin;
		
		players=new Hashtable<Integer,Player>(4);
	}

	public Player getPlayer(int game)
	{
		return players.get(game);
	}
	public void addPlayer(int game,Player add)
	{
		players.put(game, add);
	}
	public Player removePlayer(int game)
	{
		return players.remove(game);
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public String toString() {
		if(admin)
			return id + " - " + name + " <" + email + "> pass: " + pass + " Admin" ;
		else
			return id + " - " + name + " <" +email + "> pass: " + pass + " User" ;

	}

	public Hashtable<Integer, Player> getPlayers() {
		return players;
	}

	public void setPlayers(Hashtable<Integer, Player> players) {
		this.players = players;
	}

}
