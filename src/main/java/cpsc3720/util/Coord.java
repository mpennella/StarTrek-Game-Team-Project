/*
 * Written by Isaac Roberts
 */

package cpsc3720.util;
/**
 *
 * @author Isaac
 */

import java.util.*;

public class Coord 
{
	/* Coord, short for Coordinate
	 * Simply an int,int pair for X&Y coordinates
	 * Used to access Sectors and Squares
	 * 
	 */
	
	
	
    /*------------------Usage----------------------*/
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int x() {
        return x;
    }
    public int y() {
        return y;
    }
    public void setX(int set) {
        x=set;
    }
    public void setY(int set) {
        y=set;
    }
    
    /*---------------------For Overriding-------------------*/
    
    private int x,y;//Grid_X & Grid_Y
    
    public Coord(int setx,int sety)
    {
        x=setx;
        y=sety;
    }
    public Coord()
    {//Error Contstructor
    	//There is no static Coord.error like Sector.error and Square.error,
    		//but this may still be useful.
        x=-1;
        y=-1;
    }
    
    public boolean isError() {
        return x==-1 && y==-1;
    }
    public boolean notError() {
        return x!=-1 || y!=-1;
    }
    public boolean occupied() {
        return false;
    }
    /*-------------------Geographic-----------------*/
    public boolean isNeighbor(Coord other)
    {
        int xDif=Math.abs(other.x()-x);
        if (xDif>1)
            return false;
        int yDif=Math.abs(other.y()-y);
        if (yDif>1)
            return false;
        return ((xDif==1)^(yDif==1));
    }
    public Dir getDir(Coord to)
    {
    /*
        Gives the general direction from this square to another
    */
        if (Math.abs(x-to.x)>Math.abs(y-to.y))
        {
            if (x>to.x)
                return Dir.West;
            else 
                return Dir.East;
        }
        else
        {
            if (y>to.y)
                return Dir.North;
            else 
                return Dir.South;
        }
    }
    public boolean equals(Coord other)
    {
        if (x!=other.x) return false;
        if (y!=other.y) return false;
        return true;
    }
	public String toString()
	{
		return "Coord("+x+","+y+")";
	}
}