

import java.awt.Color;

public class ScoreBonus extends PowerUp {
	private int score;
	private int increment;
	private ScoreModel scoretracker;
	
	ScoreBonus(Vector2d pos_, int life, String name, Color C, int S) {
		super(pos_, life, name, C);
		score = S;
		increment = score/life;
		if (increment <= 0)
			increment = 1;
		scoretracker = null;
		// TODO Auto-generated constructor stub
	}
	ScoreBonus(double X, double Y, int life, String name, Color C, int S) {
		super(X, Y, life, name, C);
		score = S;
		increment = score/life;
		if (increment <= 0)
			increment = 1;
		scoretracker = null;
	}
	/**
	 * Gets ScoreModel from BlockBreakersModel
	 */
	@Override
	public void activatePowerUp(BlockBreakersModel M) {
		// TODO Auto-generated method stub
		scoretracker = M.getScore();
		active = true;
	}
	/**
	 * Adds to score total each updates
	 */
	@Override
	public void updatePowerUp(BlockBreakersModel M) {
		// TODO Auto-generated method stub
		scoretracker.recievePoints(increment);
		lifeTime--;
		if (lifeTime <= 0) {
			active = false;
		}
	}
	/**
	 * No deactivate action necessary
	 */
	@Override
	public void deactivatePowerUp(BlockBreakersModel M) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PowerUp makeCopy() {
		// TODO Auto-generated method stub
		ScoreBonus temp = new ScoreBonus(pos, lifeTime, name, color, score);
		return temp;
	}

}
