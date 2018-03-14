package cpsc3720.actions;

import java.util.ArrayList;
import java.util.List;

import cpsc3720.game.Grid;
import cpsc3720.game.Sector;
import cpsc3720.util.Loc;

public class ActionAIAttack {
	int gameId;
	Loc target;
	public ActionAIAttack(int gameId, Loc target) {
		super();
		this.gameId = gameId;
		this.target = target;
	}
	public int getGameId() {
		return gameId;
	}
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	public Loc getTarget() {
		return target;
	}
	public void setTarget(Loc target) {
		this.target = target;
	}
	
	//function to make the ai ships attack the designated target
	public void performAction(Grid grid){
		//list of strings to store a log of attacks by the ai ships
		
		//gets the sector that the target is in to find the ai ships there
		Sector s = grid.getSector(target.getSector().getX(), target.getSector().getY());
		
		//loops to go through all squares to find ai ships
		for(int x = 0; x < 8; x++){
			for(int y = 0; y < 8; y++){
				//sees if there is a ship and then checks to see if it ai controlled
				if(s.getSquare(x, y).hasShip()){
					if(s.getSquare(x, y).getShip().getPlayerName() == "CPU"){
						//if it is performs an energy attack on the ship
						//find the closest ship to the cpu
						Loc closestShip = null;
						double close = -100000;
						for(int i = 0; i < 8; i++){
							for(int j = 0; j < 8; j++){
								if(i != x && y !=j)
									if(s.getSquare(i, j).hasShip()){
										int hor = Math.abs(x - i);
										int vert = Math.abs(y-j);
										double total = Math.sqrt((hor * hor) + (vert * vert));
										if(total > close)
											closestShip = new Loc(s.getPos().getX(), s.getPos().getY(), i ,j);
									
									}
							}
							
						}
						ActionFireEnergy a = null;
						if(closestShip != null)
							a = new ActionFireEnergy(0, new Loc(target.getSector().getX(), target.getSector().getY(), x, y), closestShip);
						try {
							if(a != null)
								a.performAciton(grid);
						} catch (FireTorpedoException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		
	}
}
