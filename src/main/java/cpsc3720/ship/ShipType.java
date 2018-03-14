package cpsc3720.ship;

import cpsc3720.user.Player;
import cpsc3720.weapon.Weapon;

/* Each object of this class represents a type of ship that a player or cpu may be, including which empire it 
represents as well as weapon types and maximum resource levels*/
public class ShipType {

	String id;
	String title;
	String shipClass;
	String empire;
	Weapon missile;
	Weapon energy;
	int maxEnergy;
	int maxSpeed;
	int maxShields;
	int maxMissiles;

	public ShipType(){
		id=")))";
		title="(";
		shipClass="{";
		empire="}";
		missile=null;
		energy=null;
		maxEnergy=-1;
		maxSpeed=-1;
		maxShields=-1;
		maxMissiles=-1;
	}

	public ShipType(String id, String title, String shipClass, String empire,
			Weapon energy, Weapon missle, int maxEnergy, int maxSpeed,
			int maxShields, int maxMissles) {
		super();
		this.id = id;
		this.title = title;
		this.shipClass = shipClass;
		this.empire = empire;
		this.missile = missle;
		this.energy = energy;
		this.maxEnergy = maxEnergy;
		this.maxSpeed = maxSpeed;
		this.maxShields = maxShields;
		this.maxMissiles = maxMissles;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getShipClass() {
		return shipClass;
	}

	public void setShipClass(String shipClass) {
		this.shipClass = shipClass;
	}

	public String getEmpire() {
		return empire;
	}

	public void setEmpire(String empire) {
		this.empire = empire;
	}

	public Weapon getMissile() {
		return missile;
	}

	public void setMissile(Weapon missle) {
		this.missile = missle;
	}

	public Weapon getEnergy() {
		return energy;
	}

	public void setEnergy(Weapon energy) {
		this.energy = energy;
	}

	public int getMaxEnergy() {
		return maxEnergy;
	}

	public void setMaxEnergy(int maxEnergy) {
		this.maxEnergy = maxEnergy;
	}

	public int getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public int getMaxShields() {
		return maxShields;
	}

	public void setMaxShields(int maxShields) {
		this.maxShields = maxShields;
	}

	public int getMaxMissiles() {
		return maxMissiles;
	}

	public void setMaxMissiles(int maxMissles) {
		this.maxMissiles = maxMissles;
	}

	@Override
	public String toString() {
		return "Ship [id=" + id + ", title=" + title + ", shipClass="
				+ shipClass + ", empire=" + empire + ", missle=" + missile
				+ ", energy=" + energy + ", maxEnergy=" + maxEnergy
				+ ", maxSpeed=" + maxSpeed + ", maxShields=" + maxShields
				+ ", maxMissiles=" + maxMissiles + "]";
	}

	

	
	

	


}
