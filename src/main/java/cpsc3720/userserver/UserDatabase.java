package cpsc3720.userserver;

import java.util.ArrayList;

import cpsc3720.user.User;

public class UserDatabase {
	//list of all users
	private ArrayList<User> users = new ArrayList<User>();
	
	//adds a new user to the list sets their id and then increments it
	public int add(User user) {
		int newId = 0;
		for (User u : users) {
			newId = Math.max(newId, u.getId());
		}

		++newId;
		user.setId(newId);
		users.add(user);
		return newId;
	}
	
	//removes a user by sending in the user that you wish to remove
	public int remove(User user){
		for(int i = 0; i < users.size(); i++)
			if(users.get(i).getId() == user.getId()){
				users.remove(i);
				return i+1;
			}
		return 0;
	}
	
	//gets a list of all users
	public ArrayList<User> getAll() {
		return users;
	}
	public User getUser(String name)
	{
		for (int i=0;i<users.size();i++)
		{
			if (users.get(i).getName()==name)
				return users.get(i);
		}
		return null;
	}
	//find a user by its name
	public User findUser(String name)
	{
		for (int i=0;i<users.size();i++)
		{
			if (users.get(i).getName()==name)
				return users.get(i);
		}
		System.out.println("making a new user for "+name);
		User n00b=new User(name);
		add(n00b);
		return n00b;
	}
	//clears the list of users
	public void reset() {
		users.clear();
	}
	//singleton of the database
	private static UserDatabase instance = new UserDatabase();
	
	//returns an instance of the database
	public static UserDatabase instance() {
		return instance;
	}

}
