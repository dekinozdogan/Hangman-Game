package Player;

public class Player {
	
	private String name;
	private int totalPoint;
	
	public Player(String name, int totalPoint) {
		this.name = name;
		this.totalPoint = totalPoint;
	}
	
	public String getName() {
		return name;
	}


	public int getTotalPoint() {
		return totalPoint;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setTotalPoint(int totalPoint) {
		this.totalPoint = totalPoint;
	}


	@Override
	public String toString() {
		return "Player name = " + name + "\n Total Points = " + totalPoint;
	}
	
	
	
	

}
