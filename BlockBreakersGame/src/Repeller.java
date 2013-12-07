

import java.awt.Color;

public class Repeller extends ForceObject {
	private double mag;//Should probably be in the range of 200 - 2000 depending on how strong force is 
	private double MAX_FORCE_MAG;
	
	
	public Repeller(Vector2d pos_, double mag_) {
		super(pos_);
		mag = mag_;
		color = Color.RED;
//		MAX_FORCE_MAG = 10;
		// TODO Auto-generated constructor stub
	}
	public Repeller(double X, double Y, double mag_) {
		super(X,Y);
		mag = mag_;
		color = Color.RED;
//		MAX_FORCE_MAG = 10;
	}

	@Override
	public void applyForce(BallModel ball) {
		// TODO Auto-generated method stub
		Vector2d force = Vector2d.makeVector(getCenterPos(), ball.getCenterPos());
		double dist = force.getMagnitude();
//		if (dist > 150 || dist == 0)
		if (dist == 0)
			return;
		
		force.normalize();
		double force_mag = mag/(dist*dist);
//		if (force_mag > MAX_FORCE_MAG)
//			force_mag = MAX_FORCE_MAG;
		force.sMultiply(force_mag);
		ball.applyForce(force);
	}

}
