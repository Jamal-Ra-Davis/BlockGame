

import java.awt.Color;

public class PaddleExtend extends PowerUp {
	private int pWidth_new;
	private int pWidth_old;
	private Color old_color;
	private Color new_color;
	private int Max_life;
	private int toggle = 1;
	PaddleExtend(Vector2d pos_, int life, String name, Color C, int paddleWidth) {
		super(pos_, life, name, C);
		pWidth_new = paddleWidth;
		Max_life = life;
		// TODO Auto-generated constructor stub
	}
	PaddleExtend(double X, double Y, int life, String name, Color C, int paddleWidth) {
		super(X, Y, life, name, C);
		pWidth_new = paddleWidth;
		Max_life = life;
	}
	/**
	 * Sets paddle to new width
	 */
	@Override
	public void activatePowerUp(BlockBreakersModel M) {
		// TODO Auto-generated method stub
		active = true;
		pWidth_old = M.getPaddleWidth();
		old_color = M.getPaddle().getColor();
		new_color = old_color.brighter();
		new_color = new_color.brighter();
		new_color = Color.DARK_GRAY;
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
		if (lifeTime < Max_life/4) {
			if (lifeTime%10 == 0)
				toggle *= -1;
			if (toggle == 1)
				M.getPaddle().setColor(old_color);
			else
				M.getPaddle().setColor(new_color);
		}
	}
	/**
	 * Sets paddle to it's original width
	 */
	@Override
	public void deactivatePowerUp(BlockBreakersModel M) {
		// TODO Auto-generated method stub
		M.getPaddle().setPaddleWidth(pWidth_old);
		M.getPaddle().setColor(old_color);
		
	}
	@Override
	public PowerUp makeCopy() {
		// TODO Auto-generated method stub
		PaddleExtend temp = new PaddleExtend(pos, lifeTime, name, color, pWidth_new);
		return temp;
	}

}
