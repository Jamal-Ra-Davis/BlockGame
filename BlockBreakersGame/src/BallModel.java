

import java.awt.Color;
import java.awt.Graphics;

public class BallModel {
	//Properties of ball
	private Vector2d pos;
	private Vector2d prev_pos;
	private Vector2d vel;
	private Vector2d accl;
	private int diameter;
	private Color color = Color.red;
	
	private static double max_speed = 25;
	private static double max_accel = 2;
	
	
	// Constructors
	public BallModel() {}
	public BallModel(Vector2d pos_, Vector2d vel_, int D) {
		pos = new Vector2d(pos_);
		prev_pos = new Vector2d(pos);//previous position initially set to current position
		vel = new Vector2d(vel_);
		accl = new Vector2d();
		diameter = D;
	}
	public BallModel(double pos_x, double pos_y, double vel_x, double vel_y, int D) {
		pos = new Vector2d(pos_x, pos_y);
		prev_pos = new Vector2d(pos);//previous position initially set to current position
		vel = new Vector2d(vel_x, vel_y);
		accl = new Vector2d();
		diameter = D;
	}
	
	// Setting the ball
	void setBall(Vector2d pos_, Vector2d vel_, int D) {
		pos = new Vector2d(pos_);
		prev_pos = new Vector2d(pos);//previous position initially set to current position
		vel = new Vector2d(vel_);
		accl = new Vector2d();
		diameter = D;
	}
	void setBall (double pos_x, double pos_y, double vel_x, double vel_y, int D) {
		pos = new Vector2d(pos_x, pos_y);
		prev_pos = new Vector2d(pos);//previous position initially set to current position
		vel = new Vector2d(vel_x, vel_y);
		diameter = D;
	}
	
	// Getting and setting the ball's properties
	void setPos(Vector2d pos_) {
		prev_pos.setVector(pos);//Not sure if should be set before or after pos
		pos.setVector(pos_);
	}
	void setPos(double X, double Y) {
		prev_pos.setVector(pos);//Not sure if should be set before or after pos
		pos.setVector(X, Y);
	}
	
	//Sets the ball's previous position, only use if you really know you need to 
	void setPrevPos(Vector2d prev) {
		prev_pos.setVector(prev);
	}
	void setPrevPos(double X, double Y) {
		prev_pos.setVector(X, Y);
	}
	
	void setVel(Vector2d vel_) {
		vel.setVector(vel_);
	}
	void setVel(double X, double Y) {
		vel.setVector(X, Y);
	}
	void setVelLimited(Vector2d vel_) {
		double speed = vel_.getMagnitude();
		if (speed > max_speed){
			vel_.normalize();
			vel_.sMultiply(max_speed);
		}
		vel.setVector(vel_);
	}
	void setVelLimited(double X, double Y) {
		Vector2d vel_ = new Vector2d(X, Y);
		double speed = vel_.getMagnitude();
		if (speed > max_speed){
			vel_.normalize();
			vel_.sMultiply(max_speed);
		}
		vel.setVector(vel_);
	}
	//Returns copy of position vector
	Vector2d getPos() {
		return pos.makeCopy();
	}
	//Returns copy of velocity vector
	Vector2d getVel() {
		return vel.makeCopy();
	}
	
	/**
	 * The center of the ball's current position.
	 * @return Vector2d center value of ball
	 */
	Vector2d getCenterPos(){
		return pos.getAddition(getDiameter() / 2.0, getDiameter() / 2.0);
	}
	Vector2d getCenterPrevPos() {
		return prev_pos.getAddition(getDiameter() / 2.0, getDiameter() / 2.0);
	}
	
	double getCenterPosX() {
		return pos.getX() + getDiameter() / 2.0;
	}
	double getCenterPosY() {
		return pos.getY() + getDiameter() / 2.0;
	}
	double getCenterPrevPosX() {
		return prev_pos.getX() + getDiameter() / 2.0;
	}
	double getCenterPrevPosY() {
		return prev_pos.getY() + getDiameter() / 2.0;
	}
	
	double getPosX() {
		return pos.getX();
	}
	double getPosY() {
		return pos.getY();
	}
	double getPrevPosX() {
		return prev_pos.getX();
	}
	double getPrevPosY() {
		return prev_pos.getY();
	}
	
	double getVelX() {
		return vel.getX();
	}
	double getVelY() {
		return vel.getY();
	}
	int getDiameter() {
		return diameter;
	}
	double getRadius() {
		return diameter/2.0;
	}
	void updatePos() {
		prev_pos.setVector(pos);
		updateVelocity();
//		vel.add(accl);
//		double speed = vel.getMagnitude();
//		if (speed > max_speed) {
//			Vector2d 
//		}
		pos.add(vel);
	}
	void updateVelocity() {
		double accel_mag = accl.getMagnitude();
		if (accel_mag > max_accel) {
			Vector2d temp = accl.makeCopy();
			temp.normalize();
			temp.sMultiply(max_accel);
			accl.setVector(temp);
		}
		vel.add(accl);
		double speed = vel.getMagnitude();
		if (speed > max_speed) {
			Vector2d temp = vel.makeCopy();
			temp.normalize();
			temp.sMultiply(max_speed);
			vel.setVector(temp);
		}
	}
	void clearAccleration() {
		accl.setVector(0, 0);
	}
	void applyForce(Vector2d force) {
		accl.add(force);
	}
	void addVelocity(Vector2d in) {
		vel.add(in);
	}
	void addVelocityLimited(Vector2d in) {
		vel.add(in);
		double speed = vel.getMagnitude();
		if (speed > max_speed){
			Vector2d temp = vel.makeCopy();
			temp.normalize();
			temp.sMultiply(max_speed);
			vel.setVector(temp);
		}
	}
	void addVelocityX(double X){
		vel.addX(X);
	}
	void addVelocityXLimited(double X) {
		vel.addX(X);
		double speed = vel.getMagnitude();
		if (speed > max_speed){
			Vector2d temp = vel.makeCopy();
			temp.normalize();
			temp.sMultiply(max_speed);
			vel.setVector(temp);
		}
	}
	void addVelocityY(double Y){
		vel.addY(Y);
	}
	void addVelocityYLimited(double Y) {
		vel.addY(Y);
		double speed = vel.getMagnitude();
		if (speed > max_speed){
			Vector2d temp = vel.makeCopy();
			temp.normalize();
			temp.sMultiply(max_speed);
			vel.setVector(temp);
		}
	}
	void invertVelX() {
		vel.invertX();
	}
	void invertVelY() {
		vel.invertY();
	}
	void setPosX(double x_) {
		prev_pos.setVector(pos);//Not sure if should be set before or after pos
		pos.setX(x_);
	}
	void setPosY(double y_) {
		prev_pos.setVector(pos);//Not sure if should be set before or after pos
		pos.setY(y_);
	}
	
	void drawBall(Graphics g, BlockBreakersView view){
		g.setColor(color);
		int x = (int) pos.getX();
		int y = (int) pos.getY();
		if (view.getFlipVertical()) {
			y = view.translateY(y) - diameter;
		}
		g.fillOval(x, y, diameter, diameter);
	}
	
}
