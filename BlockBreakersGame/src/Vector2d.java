


public class Vector2d {
	private double x, y;
	
	public Vector2d() {
		x = 0;
		y = 0;
	}
	public Vector2d(double x_, double y_) {
		x = x_;
		y = y_;
	}
	public Vector2d(Vector2d in) {
		x = in.getX();
		y = in.getY();
	}
	
	//Getter functions
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	
	//Setter functions
	public void setVector(double x_, double y_) {
		x = x_;
		y = y_;
	}
	public void setVector(Vector2d in) {
		x = in.getX();
		y = in.getY();
	}
	public void setX(double x_) {
		x = x_;
	}
	public void setY(double y_) {
		y = y_;
	}
	
	//Functions for adding to internal values
	public void add(Vector2d in) {
		x += in.getX();
		y += in.getY();
	}
	public void add(double x_, double y_) {
		x += x_;
		y += y_;
	}
	public void addX(double x_) {
		x += x_;
	}
	public void addY(double y_) {
		y += y_;
	}
	
	//Functions for subtracting from internal values
	public void subtract(Vector2d in) {
		x -= in.getX();
		y -= in.getY();
	}
	public void subtract(double x_, double y_) {
		x -= x_;
		y -= y_;
	}
	public void subtractX(double x_) {
		x -= x_;
	}
	public void subtractY(double y_) {
		y -= y_;
	}
	
	//Scalar operations
	public void sMultiply(double M) {
		x *= M;
		y *= M;
	}
	public void sDivide(double D) {
		x /= D;
		y /= D;
	}
	
	//General Functions
	public void invert() {
		x *= -1;
		y *= -1;
	}
	public void invertX() {
		x *= -1;
	}
	public void invertY() {
		y *= -1;
	}
	public double getMagnitude() {
		return Math.sqrt(x*x + y*y);
	}
	public void normalize() {
		double mag = getMagnitude();
		sDivide(mag);
	}
	/**
	 * Returns a double value of the distance between two Vector2d.
	 * @param other Vector2d of the value to be tested
	 * @return
	 */
	public double getDistance(Vector2d other){
		return Math.sqrt(((other.getX() - this.getX()) * (other.getX() - this.getX())) 
				+ ((other.getY() - this.getY()) * (other.getY() - this.getY())));
	}
	
	/**
	 * Returns a double value of the distance between this position and the 
	 * otherX and otherY value.
	 * @param otherX double
	 * @param otherY double
	 * @return distance value
	 */
	public double getDistance(double otherX, double otherY){
		return Math.sqrt(((otherX - this.getX()) * (otherX - this.getX())) 
				+ ((otherY - this.getY()) * (otherY - this.getY())));
	}
	
	//Function for returning new vectors
	public Vector2d makeCopy() {
		Vector2d out = new Vector2d(x, y);
		return out;
	}
	public Vector2d getUnit() {
		Vector2d out = makeCopy();
		out.normalize();
		return out;
	}
	public Vector2d getInversion() {
		Vector2d out = makeCopy();
		out.invert();
		return out;
	}
	public Vector2d getDifference(Vector2d in) {
		Vector2d out;
		double X, Y;
		X = in.getX() - x;
		Y = in.getY() - y;
		out = new Vector2d(X, Y);
		return out;
	}
	public Vector2d getDifference(double x_, double y_)
	{
		Vector2d out;
		double X, Y;
		X = x_ - x;
		Y = y_ - y;
		out = new Vector2d(X, Y);
		return out;
	}
	public Vector2d getAddition(Vector2d in) {
		Vector2d out;
		double X, Y;
		X = in.getX() + x;
		Y = in.getY() + y;
		out = new Vector2d(X, Y);
		return out;
	}
	public Vector2d getAddition(double x_, double y_)
	{
		Vector2d out;
		double X, Y;
		X = x_ + x;
		Y = y_ + y;
		out = new Vector2d(X, Y);
		return out;
	}
	public Vector2d getPerpendicular() {
		return (new Vector2d(-1*y, x));
	}
	//
	/**
	 * Checks to see if 2 vectors are parallel, 2 very similar vectors will be considered
	 * parallel. Tolerance is determined by epsilon value. 
	 * @param in
	 * @return
	 */
	public boolean isParallel(Vector2d in) {
		Vector2d this_ = getUnit();
		Vector2d other = in.getUnit();
		
		double epsilon = .01;
		boolean parallel = true;
		double diffX = this_.getX() - other.getX();
		double diffY = this_.getY() - other.getY();
		
		if (diffX < 0)
			diffX *= -1;
		if (diffY < 0)
			diffY *= -1;
		
		if (diffX > epsilon)
			parallel = false;
		if (diffY > epsilon)
			parallel = false;
		
		return parallel;
	}
	public boolean isParallel(double X, double Y) {
		Vector2d this_ = getUnit();
		Vector2d other = new Vector2d(X,Y);
		other.normalize();
		
		double epsilon = .01;
		boolean parallel = true;
		double diffX = this_.getX() - other.getX();
		double diffY = this_.getY() - other.getY();
		
		if (diffX < 0)
			diffX *= -1;
		if (diffY < 0)
			diffY *= -1;
		
		if (diffX > epsilon)
			parallel = false;
		if (diffY > epsilon)
			parallel = false;
		
		return parallel;
	}
	
	public String toString() {
		String out = String.format("X: %f, Y: %f", x, y);
		return out;
	}
	
	public static Vector2d makeVector(Vector2d source, Vector2d dest) {
		return source.getDifference(dest);
	}
}
