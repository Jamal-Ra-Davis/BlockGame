

import java.awt.Color;
import java.awt.Graphics;

public abstract class ForceObject {
	protected Vector2d pos;
	protected double diameter;
	protected Color color;
	
	public ForceObject(Vector2d pos_) {
		pos = new Vector2d(pos_);
		diameter = 10;
	}
	public ForceObject(double X, double Y) {
		pos = new Vector2d(X, Y);
		diameter = 10;
	}
	
	public abstract void applyForce(BallModel ball);
	public Vector2d getPos() {
		return pos;
	}
	public double getPosX() {
		return pos.getX();
	}
	public double getPosY() {
		return pos.getY();
	}
	public double getDiameter() {
		return diameter;
	}
	public double getRadius() {
		return diameter/2.0;
	}
	public Color getColor() {
		return color;
	}
	public Vector2d getCenterPos() {
		return pos.getAddition(getRadius(), getRadius());
	}
	public double getCenterPosX() {
		return pos.getX() + getRadius();
	}
	public double getCenterPosY() {
		return pos.getY() + getRadius();
	}
	public void setPos(Vector2d pos_) {
		pos.setVector(pos_);
	}
	public void setPos(double X, double Y) {
		pos.setVector(X, Y);
	}
	
	void drawForce(Graphics g, BlockBreakersView view){
		g.setColor(color);
		int x = (int) pos.getX();
		int y = (int) pos.getY();
		if (view.getFlipVertical()) {
			y = view.translateY(y) - (int)diameter;
		}
		g.fillOval(x, y, (int)diameter, (int)diameter);
	}
		
}
