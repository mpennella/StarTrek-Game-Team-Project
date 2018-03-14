/*
 * Written by Isaac Roberts
 */

package cpsc3720.game;

import java.util.*;

import cpsc3720.util.*; 
import cpsc3720.ship.*;
import cpsc3720.base.*;

/**
 *
 * @author Isaac
 */
public class Grid
{
    /*
    
    Holds all of the Sectors
    
    Grid Holds 8x8 Sector
    Each Sector holds 8x8 Squares. Each Square has an "Occupant" of type Ship,Base, or Boolean [Star]
    
    */
    private Sector[][] map;
    private int stardate;
    
    
    
    public int getStardate() {
		return stardate;
	}
	public void setStardate(int stardate) {
		this.stardate = stardate;
	}
	public int incStardate(){
		return stardate++;
	}
	public Grid()
    {
        map=new Sector[8][8];
        for (int x=0;x<map.length;x++)
        {
            for (int y=0;y<map[x].length;y++)
            {
                map[x][y]=new Sector(x,y);
            }
        }
    }
    //-----------Access
    public int xAmt(){
        return map.length;
    }
    public int yAmt() {
        return map[0].length;
    }
    public Sector getSector(int x,int y) {
        return map[x][y];
    }
	public Sector getSector(Coord sec) {
		return map[sec.x()][sec.y()];
	}
    public Sector get(int x,int y) {
        return map[x][y];
    }
    public Square getSquare(Loc loc) {
    	//Skips over Sector to get Square from a Loc (Location)
    	//Technically not good software design, but it makes the code a lot more readable
    	return map[loc.getSector().x()][loc.getSector().getY()].getSquare(loc.getSquare());
    }
    public Square getSquare(int scx,int scy,int sqx,int sqy) {
    	//Skips over Sector to get Square from four ints
    	// " "
    	return map[scx][scy].getSquare(sqx,sqy);
    }
    public boolean inBounds(int x,int y)
    {//Returns true if x,y is on the map
        return (x>=0 && x<xAmt() && y>=0 && y<yAmt());
    }
    public boolean xInBounds(int x) {
        return (x>=0 && x<xAmt());
    }
    public boolean yInBounds(int y) {
        return (y>=0 && y<yAmt());
    }
    public boolean inBounds(Coord start,Dir dir)
    {
        int x=start.x()+dir.x();
        if (x<0 || x>=xAmt())
            return false;
        int y=start.y()+ dir.y();
        return (y>=0 && y<yAmt());
    }
	public Sector[][] getMap() {
		return map;
	}
	public void setMap(Sector[][] map) {
		this.map = map;
	}
    

}
