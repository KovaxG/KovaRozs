package game;

public class Town {
	private boolean TownCenter = false;
	private boolean WareHouse = false;
	
	public Town(boolean tc, boolean wh) {
		TownCenter = tc;
		WareHouse = wh;
	}
	
	public void buildTownCenter() {
		TownCenter = true;
	}
	
	public void buildWareHouse() {
		WareHouse = true;
	}
	
	public boolean hasTownCenter() {
		return TownCenter;
	}
	
	public boolean hasWareHouse() {
		return WareHouse;
	}
}
