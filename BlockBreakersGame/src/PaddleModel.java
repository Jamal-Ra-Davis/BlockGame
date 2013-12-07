

import java.awt.Color;
import java.awt.Graphics;

public class PaddleModel {
	
    // properties of the paddle
    private double paddleX, paddleY;
    private Vector2d pos;
    private int paddleWidth, paddleHeight;
    private Color color = Color.magenta;
    
    public PaddleModel(){
    	pos = new Vector2d(0,10);
        paddleX = 0;
        paddleY = 10;
        paddleWidth = 100;
        paddleHeight = 10;
    }
    
    public double getPaddleX(){
    	return paddleX;
    	//return pos.getX();
    }
    
    public double getPaddleY(){
    	return paddleY;
    	//return pos.getY();
    }
    
    public void setPaddleX(double value){
    	paddleX = value;
    	pos.setX(value);
    }
    
    public void setPaddleY(double value){
    	paddleY = value;
    	pos.setY(value);
    }
    
    public int getPaddleWidth(){
    	return paddleWidth;
    }
    
    public int getPaddleHeight(){
    	return paddleHeight;
    }
    public void setPaddleWidth(int W) {
    	paddleWidth = W;
    }
    public void setPaddleHeight(int H) {
    	paddleHeight = H;
    }
    private void handleCollision(BallModel ball) {
    	double val = 0.1;
    	if (ball.getCenterPosX() < val*paddleWidth + paddleX){
    		ball.addVelocityXLimited(-2);
    		
    	}
    	else if (ball.getCenterPosX() > paddleWidth*(1-val) + paddleX) {
    		ball.addVelocityXLimited(2);
    	}
    	ball.setPosY(this.getPaddleY() + this.getPaddleHeight());
    	ball.invertVelY();
    		
    }
    private boolean advancedCollison(BallModel ball) {
    //	boolean collide = false;
    	
    	Vector2d p1 = new Vector2d(paddleX, paddleY+paddleHeight);
    	Vector2d p2 = new Vector2d(paddleX+paddleWidth, paddleY+paddleHeight);
    	Vector2d side = Vector2d.makeVector(p1, p2);
    	
    	Vector2d ballPos = ball.getCenterPos();
    	ballPos.addY(-1*ball.getRadius());
    	Vector2d ballPrevPos = ball.getCenterPrevPos();
    	ballPrevPos.addY(-1*ball.getRadius());
    	Vector2d ballDisplacement = Vector2d.makeVector(ballPrevPos, ballPos);
    	
    	Vector2d impactPoint = null;
    	
    	double Spx, Svx, Spy, Svy;
		double Bpx, Bvx, Bpy, Bvy;
		Double G = null, H = null;
		
		Spx = p1.getX();
		Spy = p1.getY();
		Svx = side.getX();
		Svy = side.getY();
		
		Bpx = ballPrevPos.getX();
		Bpy = ballPrevPos.getY();
		Bvx = ballDisplacement.getX();
		Bvy = ballDisplacement.getY();
    	
		double X = 0, Y = 0;
		
		H = Spy - Bpy;
		H /= Bvy;
		Y = Bpy + Bvy*H;
		
		X = Bpx + Bvx*H; 
		
		impactPoint = new Vector2d(X,Y);
		if (impactPoint.getX() > paddleX && impactPoint.getX() < paddleX + paddleWidth){
			Vector2d setPoint = new Vector2d(impactPoint.getX()-ball.getRadius(), impactPoint.getY());
			ball.setPos(setPoint);
			ball.invertVelY();
			return true;
		}
			
		
    	return false;
    }
    public boolean checkCollision(BallModel ball) {
    	if (ball.getPosX() + ball.getDiameter()/2 > this.getPaddleX() &&
        	ball.getPosX() + ball.getDiameter()/2 < this.getPaddleX() + this.getPaddleWidth() &&
        	ball.getPosY() > this.getPaddleY() && ball.getPosY() < this.getPaddleY() + this.getPaddleHeight()) {
    		
    		handleCollision(ball);
    		return true;
    	}
    	else if (ball.getCenterPosY() - ball.getRadius() < paddleY && 
    			 ball.getCenterPrevPosX() > paddleY + paddleHeight) {
    		return advancedCollison(ball);
    	}
    	else
    		return false;
    }
    public boolean checkPowerUpCollision(PowerUp powerup) {
    	if (powerup.getCenterPosX() > this.getPaddleX() &&
    		powerup.getCenterPosX() < this.getPaddleX() + this.getPaddleWidth() &&
    		powerup.getCenterPosY() > this.getPaddleY() && powerup.getCenterPosY() < this.getPaddleY() + this.getPaddleHeight()) {
        		
        	return true;
        }
    	else
    		return false;
    }
    void drawPaddle(Graphics g, BlockBreakersView view){
		int x = (int)pos.getX();
		int y = (int)pos.getY();
		if (view.getFlipVertical()) {
			y = view.translateY(y) - paddleHeight;
		}
		g.setColor(Color.RED);
		g.fillRect(x, y, paddleWidth, paddleHeight);
		
	}
    
    
}
