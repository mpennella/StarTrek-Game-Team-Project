/*
 * Written by Isaac Roberts
 */

package cpsc3720.game;

import cpsc3720.util.*; 
import cpsc3720.ship.*;
import cpsc3720.base.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.ArrayList;
import java.util.Random;
public class Square
{
	static Logger logger = LoggerFactory.getLogger(Square.class);
    /*--------------Usage--------------------*/
    public Coord getCoord()	{
		return coord;
	}
	public int x() {
		return coord.x();
	}
	public int y() {
		return coord.y();
	}
	public void setCoord(Coord set)	{
		coord=set;
	}
    	public boolean isError()	{
		return coord.isError();
	}
    
    /*----------------Class----------------*/
    
    //Ship just saves all 3 types of Occupants instead of polymorphism for ease of JSon
    private Ship ship;
	private boolean star;
	private Base base;
	
    private Coord coord;
    
    public Square(int x,int y)
    {
    	coord=new Coord(x,y);
    	clearOcc();
    }
	public Square(Coord c)
    {
		coord=c;
		clearOcc();
	}
    public Square()
    {
        //error constructor
       	coord=new Coord();
        clearOcc();
    }



//-------------Occupant Stuff-----------------
	public void clearOcc() {	
        ship=null;
		star=false;
		base=null;
	}
    public boolean isOccupied() {
    	//Checks for any occupant
        if (ship!=null) return true;
        if (star) return true;
        if (base!=null) return true;
        return false;
    }
    public void setStar(boolean setStar)
    {//All sets clear other occupants
    	ship=null;
    	base=null;
		star=setStar;
    }
	public void setShip(Ship set) {
		if (isOccupied())//TO REMOVE
			logger.info("ERROR: Collision in "+toString()+"between "+set.toString()+" and "+(star?"Star":base.toString()));
		star=false;
		base=null;
		ship=set;
	}
	public void setBase(Base set) {
		if (isOccupied())//TO REMOVE
			logger.info("ERROR: Collision in "+toString()+"between "+set.toString()+" and "+(star?"Star":ship.toString()));
		star=false;
		ship=null;
		base=set;
	}
	public void setOccupant(Object set)
	{//Determines the type of the occupant 
		logger.info("square "+toString()+" setting "+set);
		if(set == null){
			ship = null;
			return;
		}
		else if (isOccupied())//TO REMOVE
			logger.info("ERROR: Collision in "+toString()+"between "+set.toString()+" and "+getOccupant().toString());
		if (set instanceof Ship)
			ship=(Ship)set;
		else if (set instanceof Base)
			base=(Base)set;
		else if (set instanceof Boolean)
			star=(Boolean)set;
		else
			logger.info("Error: Non-Unit Occupant set in Square "+toString());
	}
	public Ship getShip()	{
		return ship;
	}
	public boolean hasShip()	{
		return ship!=null;
	}
	public Base getBase() {
		return base;	
	}
	public boolean hasBase()	{
		return base!=null;
	}
	public boolean hasStar() {
		return star;
	}
	public char getOccType()
	{//Returns 'S', 'B', '*', or '-' to convey Occupant type
		if (ship!=null)
			return 'S';
		else if (base != null)
			return 'B';
		else if (star)
			return '*';
		else return '-';
	}
	public Object getOccupant()	{
		//returns generalized Occupant Object
		if (ship!=null) return ship;
		else if (base!=null) return base;
		else if (star) return '*';
		else return null;
	}

//----------------Other----------------------
	public String toString()	{
		return super.toString()+"@:"+coord.toString();
	}
	public boolean isStar() {
		return star;
	}
	
}