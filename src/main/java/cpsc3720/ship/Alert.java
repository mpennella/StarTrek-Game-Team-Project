package cpsc3720.ship;

public enum Alert {
	//the differnt alert levels
	Red(10,10),
	Yellow(5,4),
	Green(0,0);
	
	//these aren't even used
	public int energyDrain;
	public int shieldBlock;
	private Alert(int drain,int setShieldBlock)
	{
		energyDrain=drain;
		shieldBlock=setShieldBlock;
	}
	//not used
	public int getEnergyDrain() {
		return energyDrain;
	}
	//not used
	public void setEnergyDrain(int energyDrain) {
		this.energyDrain = energyDrain;
	}
	//not used
	public int getShieldBlock() {
		return shieldBlock;
	}
	//not used
	public void setShieldBlock(int shieldBlock) {
		this.shieldBlock = shieldBlock;
	}
	
}
