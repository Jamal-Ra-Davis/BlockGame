

import java.awt.Color;
import java.awt.Graphics;

public abstract class PowerUp {
	public static int NUMBER_OF_POWERUPS = 4;
	protected Vector2d pos;
	protected Vector2d vel;
	protected boolean active;
	protected int lifeTime;
	protected double diameter;
	protected String name;
	protected Color color;
	
	public PowerUp(Vector2d pos_, int life, String name_, Color C) {
		pos = pos_;
		vel = new Vector2d(0, -4.5);
		active = false;
		lifeTime = life;
		diameter = 30;
		name = name_;
		color = C;
	}
	public PowerUp(double X, double Y, int life, String name_, Color C) {
		pos = new Vector2d(X,Y);
		vel = new Vector2d(0, 3);
		active = false;
		lifeTime = life;
		diameter = 30;
		name = name_;
	}
	//Getter methods
	public Vector2d getPos() {
		return pos;
	}
	public double getPosX() {
		return pos.getX();
	}
	public double getPosY() {
		return pos.getY();
	}
	//Updates powerup's position
	public void updatePos() {
		pos.add(vel);
	}
	
	//More getter methods
	public Vector2d getCenterPos() {
		return pos.getAddition(diameter/2.0, diameter/2.0);
	}
	public double getCenterPosX() {
		return pos.getX() + diameter/2.0;
	}
	public double getCenterPosY() {
		return pos.getY() + diameter/2.0;
	}
	public double getDiameter() {
		return diameter;
	}
	public boolean isActive() {
		return active;
	}
	public int getLifeTime() {
		return lifeTime;
	}
	public String getName() {
		return name;
	}
	public Color getColor() {
		return color;
	}
	/**
	 * Defines the behavior of the powerup when it is first activated. 
	 * Should be called when the powerup is placed into
	 * the models active powerup slot
	 * @param M
	 */
	abstract public void activatePowerUp(BlockBreakersModel M);
	/**
	 * Defines the behavior of the powerup each time the frame is updated.
	 * Typically the lifetime will be decremented in this method
	 * @param M
	 */
	abstract public void updatePowerUp(BlockBreakersModel M);
	/**
	 * Defines the behavior of the powerup when it's lifetime runs out.
	 * If powerup made any temporary changes to model, they should be reverted 
	 * in this method
	 * @param M
	 */
	abstract public void deactivatePowerUp(BlockBreakersModel M);
	abstract public PowerUp makeCopy();
	
	void drawPowerUp(Graphics g, BlockBreakersView view){
		g.setColor(color);
		int x = (int) pos.getX();
		int y = (int) pos.getY();
		if (view.getFlipVertical()) {
			y = view.translateY(y) - (int)diameter;
		}
		g.fillOval(x, y, (int)diameter, (int)diameter);
	}
	
	
	/**
	 * Takes in a powerup ID and position and returns the corresponding powerup
	 * @param ID
	 * @param pos
	 * @return
	 */
	public static PowerUp makePowerUp(int ID, Vector2d pos) {
		PowerUp temp = null;
		switch (ID) {
			case 1:{
				temp = new PaddleExtend(pos, 300, "Paddle Extend", Color.blue, 150);
				break;
			}
			case 2: {
				temp = new PaddleExtend(pos, 500, "Paddle Extend+", Color.cyan, 200);
				break;
			}
			case 3: {
				temp = new ScoreBonus(pos, 125, "Score Bonus", Color.YELLOW, 300);
				break;
			}
			case 4: {
				temp = new ScoreBonus(pos, 200, "Score Bonus+", Color.ORANGE, 500);
				break;
			}
				
		}
		
		return temp;
	}
}
