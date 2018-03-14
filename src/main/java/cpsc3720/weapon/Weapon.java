package cpsc3720.weapon;

// Each object of this class is responsible for representing one weapon type, including its energy/missile status and yield
public class Weapon {
	String id;
	String name;
	boolean energy;
	int yield;

	public Weapon(){
		id="+++";
		name="_";
		energy=false;
		yield=-1;
	}
	
	public Weapon(String id, String name, boolean energy, int yield) {
		super();
		this.id = id;
		this.name = name;
		this.energy = energy;
		this.yield = yield;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(boolean energy) {
		this.energy = energy;
	}
	
	public boolean isEnergy() {
		return energy;
	}

	public String toString() {
		return id + " - " + name +" Is energy: " + energy + " Yield: " + yield;
	}

	public int getYield() {
		return yield;
	}

	public void setYield(int yield) {
		this.yield = yield;
	}

	public void setEnergy(boolean energy) {
		this.energy = energy;
	}

}
