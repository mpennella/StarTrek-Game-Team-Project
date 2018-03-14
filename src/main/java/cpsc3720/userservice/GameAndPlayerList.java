package cpsc3720.userservice;

import cpsc3720.game.Game;
import cpsc3720.user.Player;
import java.util.ArrayList;

public class GameAndPlayerList {

	
	public Game game;
	public ArrayList<Player> playerList;
	public GameAndPlayerList()
	{
		game=null;
		playerList=new ArrayList<Player>();
	}
	public GameAndPlayerList(Game setGame, ArrayList<Player> setList)
	{
		game=setGame;
		playerList=setList;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public ArrayList<Player> getPlayerList() {
		return playerList;
	}

	public void setPlayerList(ArrayList<Player> playerList) {
		this.playerList = playerList;
	}
	
	
}
