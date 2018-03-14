package cpsc3720.base;

import cpsc3720.empire.Empire;
import cpsc3720.util.Loc;

public class Base {
	int id=-1;
	static int curID=0;
	Empire e;
	@Override
	public String toString() {
		return "Base (id=" + id + ", e=" + e +")";
	}
	public Base(Empire e) {
		super();
		this.e = e;
	}
	public Base()
	{
		e=null;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Empire getE() {
		return e;
	}
	public void setE(Empire e) {
		this.e = e;
	}
	public static int getCurID() {
		return curID;
	}
	public static void setCurID(int curID) {
		Base.curID = curID;
	}
	
	

}
