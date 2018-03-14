/*
 * Written by Isaac Roberts
 */

package cpsc3720.util;

/**
 *
 * @author Isaac.Tron
 */
import java.util.Random;

public enum Dir {
    
    /*
    
    Dir, short for Direction. 
    An advanced Enumeration with NESW, and Down, for errors
    Used for transforming between Sectors and Squares.
    Particularly useful for advanced map functions like findAllInRange() or pathfinding.
    		(Maybe not quite as useful in this game, but it's worth having.)
    
    */
    
    North(0,-1,0),
    East(1,0,1),
    South(0,1,2),
    West(-1,0,3),

    Down(0,0,-1);
    private int x,y;
    private byte id;

    Dir(int xTrans,int yTrans,int number) {
        x=xTrans;
        y=yTrans;
        id=(byte)number;
    }
    public int x() { return x; }
    public int y() { return y; }
    public int id() { return id; }
    
    
    public boolean isOpp(Dir two)
    {
        if (id==Dir.Down.id()) return false;
        if (id==(two.id+2)%4) return true;
        else return false;
    }
    
    public Dir getOpp()
    {
        return getDir((id+2)%4);
    }
    public Dir rotate(boolean clockwise)
    {//Great for iterating through Dirs
        return getDir((id + (clockwise?1:3)) % 4);
    }
    public static Dir getDir(int id)      
    {//Great for iterating over all Dirs in a for loop
        //  ex: for(int d=0;d<4;d++)
        //          square.transform(Dir.getDir(d));
        switch (id)
        {
            case 0:
                return Dir.North;
            case 1:
                return Dir.East;
            case 2:
                return Dir.South;
            case 3:
                return Dir.West;
            default:
                return Dir.Down;
        }
    }
    public static Dir getRandDir() {
        Random rand=new Random();
        return getDir(rand.nextInt(4));
    }
    public static boolean sameDimension(Dir one,Dir two) {
        //Returns true if parameters are North/South or East/West
        if (one.x()!=0 && two.x()!=0)
            return true;
        else if (one.y()!=0 && two.y()!=0)
            return true;
        else return false;
    }
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public byte getId() {
		return id;
	}
	public void setId(byte id) {
		this.id = id;
	}
}