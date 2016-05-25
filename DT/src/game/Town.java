package game;

public class Town {
	public String username;
	public String password;
	
	private boolean TownCenter = false;
	private boolean WareHouse = false;
	
	public Town(String usrname, String pass, boolean tc, boolean wh) {
		username = usrname;
		password = pass;
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
