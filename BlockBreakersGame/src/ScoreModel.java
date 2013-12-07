

public class ScoreModel {
	private int total;
	private int multiplier;
	private double milestone;
	
	public ScoreModel() {
		total = 0;
		multiplier = 1;
		milestone = 5000;
	}
	
	public int getScore() {
		return total;
	}
	public int getMultiplier() {
		return multiplier;
	}
	public void recievePoints(int pts) {
		total += pts*multiplier;
	}
	public void resetScore() {
		total = 0;
		multiplier = 1;
	}
	public void resetMultiplier() {
		multiplier = 1;
	}
	public void setMultipler(int chain){
		multiplier = (chain/5) + 1;
	}
	public boolean checkMilestone() {
		if (total >= milestone) {
			milestone = milestone + milestone*1.25;
			return true;
		}
		else
			return false;
	}
}
