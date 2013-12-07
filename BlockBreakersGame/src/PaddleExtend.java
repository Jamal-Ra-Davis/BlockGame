

import java.awt.Color;

public class PaddleExtend extends PowerUp {
	private int pWidth_new;
	private int pWidth_old;
	PaddleExtend(Vector2d pos_, int life, String name, Color C, int paddleWidth) {
		super(pos_, life, name, C);
		pWidth_new = paddleWidth;
		// TODO Auto-generated constructor stub
	}
	PaddleExtend(double X, double Y, int life, String name, Color C, int paddleWidth) {
		super(X, Y, life, name, C);
		pWidth_new = paddleWidth;
	}
	/**
	 * Sets paddle to new width
	 */
	@Override
	public void activatePowerUp(BlockBreakersModel M) {
		// TODO Auto-generated method stub
		active = true;
		pWidth_old = M.getPaddleWidth();
		M.getPaddle().setPaddleWidth(pWidth_new);
	}
	/**
	 * Decrements powerup lifetime. Sets active to false when lifetime runs out
	 */
	@Override
	public void updatePowerUp(BlockBreakersModel M) {
		// TODO Auto-generated method stub
		lifeTime--;
		if (lifeTime <= 0) {
			active = false;
		}
	}
	/**
	 * Sets paddle to it's original width
	 */
	@Override
	public void deactivatePowerUp(BlockBreakersModel M) {
		// TODO Auto-generated method stub
		M.getPaddle().setPaddleWidth(pWidth_old);
		
	}
	@Override
	public PowerUp makeCopy() {
		// TODO Auto-generated method stub
		PaddleExtend temp = new PaddleExtend(pos, lifeTime, name, color, pWidth_new);
		return temp;
	}

}
