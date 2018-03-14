/*
 * Written by Isaac Roberts
 */

package cpsc3720.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cpsc3720.util.Coord;
/**
 *
 * @author Isaac.Tron
 */

public class Sector
{


    private Square[][] matrix;
    private Coord pos;
    private List<String> attackLog;
    
    
	public Sector(int setX,int setY)
    {
		attackLog = new ArrayList<String>();
        pos=new Coord(setX,setY);
        matrix=new Square[8][8];
        for (int x=0;x<matrix.length;x++)
        {
            for (int y=0;y<matrix[x].length;y++)
            {
                matrix[x][y]=new Square(x,y);
            }
        }
    }
	public Sector()//error constructor
	{
		//System.out.println("Sector.error constructed: This should be printed\n"
			//+"once per run");
		attackLog = new ArrayList<String>();
		matrix=null;
		pos=new Coord();
	}
    
    public int xAmt()  {
        return matrix.length;
    }
    public int yAmt(){
        return matrix[0].length;
    }
    public Square getSquare(int x,int y) {
        return matrix[x][y];
    }
    public Square getSquare(Coord c){
        return matrix[c.x()][c.y()];
    }
    public Coord getPos() {
        return pos;
    }
    public Coord getCoord()	{
    	//I like duplicating getters to make code easier to read & write
    	return pos;
    }
	public int x()	{
		return pos.x();
	}
	public int y()	{
		return pos.y();
	}
	public List<String> getAttackLog() {
		return attackLog;
	}
	public void setAttackLog(List<String> attackLog) {
		this.attackLog = attackLog;
	}
	//add an attack to the attack log
	public void addAttack(String attack){
		while(attackLog.size() >= 20){
			attackLog.remove(0);
		}
		attackLog.add(attack);
	}
	@Override
	public String toString() {
		return "Sector [matrix=" + Arrays.toString(matrix) + ", pos=" + pos
				+ "]";
	}
	public Square[][] getMatrix() {
		return matrix;
	}
	public void setMatrix(Square[][] matrix) {
		this.matrix = matrix;
	}
	public void setPos(Coord pos) {
		this.pos = pos;
	}
	
	
}