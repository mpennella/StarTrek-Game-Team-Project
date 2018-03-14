package cpsc3720.util;

import java.util.*;

import cpsc3720.game.*; 
import cpsc3720.ship.*;
import cpsc3720.base.*;

public class Loc
{
	/*
	 * Loc, short for Location.
	 * Used to access Squares, starting from the Grid.
	 * Holds two Coord's, for the Square and Sector coordinate.
	 * Useful for Player, which needs the location of its ship without storing the ship.
	 * 
	 */
	
	public Coord square;
	public Coord sector;
	
	public Loc(){};
	public Loc(Sector setsec,Square setsquare)
	{
		square=setsquare.getCoord();
		sector=setsec.getCoord();
	}
	public Loc(int scX,int scY,int sqX,int sqY)
	{
		sector=new Coord(scX,scY);
		square=new Coord(sqX,sqY);
	}
	public Loc(Coord setSc,Coord setSq)
	{
		sector=setSc;
		square=setSq;
	}
	public Coord getSquare()	{
		return square;
	}
	public Coord getSector()	{
		return sector;
	}
	public void setSquare(Coord setSqr)	{
		square=setSqr;
	}
	public void setSector(Coord setSec)	{
		sector=setSec;
	}
	public boolean equals(Loc other)
	{
		if (!square.equals(other.getSquare())) return false;
		if (!sector.equals(other.getSector())) return false;
		return true;
	}
	@Override
	public String toString() {
		return "Loc [square=" + square + ", sector=" + sector + "]";
	}
	
}