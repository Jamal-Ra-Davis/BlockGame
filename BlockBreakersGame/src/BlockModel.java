
import java.awt.*;
import java.util.Random;

public class BlockModel {

    // properties of the block
    //private double blockX, blockY;
	private Vector2d pos;
    private int blockWidth, blockHeight;
    private Color blockColor;
    private boolean collision;
    private int id;
    private int hits;
    private int points;
    
    private int powerUpID;
    private static int POWERUP_CHANCE = 25;
    //Constructors
    public BlockModel(double x, double y, Color blockColor_, int ID, int type) {
    	pos = new Vector2d(x, y);
    	blockWidth = 60;
    	blockHeight = 30;
    	blockColor = blockColor_;
    	collision = false;
    	id = ID;
    	assignPowerUp();
    	
    	switch(type){
    		case 1: {
    			hits = 1;
    			points = 100;
    			//blockColor = Color.BLUE;
    			break;
    		}
    		case 2: {
    			hits = 2;
    			points = 250;
    			//blockColor = Color.GREEN;
    			break;
    		}
    		case 3: {
    			hits = 3;
    			points = 500;
    			//blockColor = Color.RED;
    			break;
    		}
    	}
    }
    public BlockModel(Vector2d pos_, Color blockColor_, int ID, int type) {
    	pos = new Vector2d(pos_);
    	blockWidth = 60;
    	blockHeight = 30;
    	blockColor = blockColor_;
    	collision = false;
    	id = ID;
    	assignPowerUp();
    	
    	switch(type){
			case 1: {
				hits = 1;
				points = 100;
				//blockColor = Color.BLUE;
				break;
			}
			case 2: {
				hits = 2;
				points = 250;
				//blockColor = Color.GREEN;
				break;
			}
			case 3: {
				hits = 3;
				points = 500;
				//blockColor = Color.RED;
				break;
			}
    	}
    }
    public BlockModel(double x, double y, int width, int height, Color blockColor_, int ID, int type){
        pos = new Vector2d(x, y);
        blockWidth = width;
        blockHeight = height;
        blockColor = blockColor_;
    	collision = false;
    	id = ID;
    	assignPowerUp();
    	
    	switch(type){
			case 1: {
				hits = 1;
				points = 100;
				//blockColor = Color.BLUE;
				break;
			}
			case 2: {
				hits = 2;
				points = 250;
				//blockColor = Color.GREEN;
				break;
			}
			case 3: {
				hits = 3;
				points = 500;
				//blockColor = Color.RED;
				break;
			}
    	}
    }
    public BlockModel(Vector2d pos_, int width, int height, Color blockColor_, int ID, int type) {
    	pos = new Vector2d(pos_);
    	blockWidth = width;
    	blockHeight = height;
    	blockColor = blockColor_;
    	collision = false;
    	id = ID;
    	assignPowerUp();
    	
    	switch(type){
			case 1: {
				hits = 1;
				points = 100;
				//blockColor = Color.BLUE;
				break;
			}
			case 2: {
				hits = 2;
				points = 250;
				//blockColor = Color.GREEN;
				break;
			}
			case 3: {
				hits = 3;
				points = 500;
				//blockColor = Color.RED;
				break;
			}
    	}
    }
    
    private void handleCollision(BallModel ball) {
    	int close = 3;
        if (ball.getCenterPosX() - ball.getDiameter() > pos.getX() && ball.getPosX() > pos.getX() + blockWidth + close) 
        {
        	ball.setPosX(pos.getX() + blockWidth);
        	ball.invertVelX();
        }
        if(	ball.getPosY() > pos.getY() && ball.getPosY() < pos.getY() + blockHeight) {
        	ball.setPosY(pos.getY() + blockHeight);
        	ball.invertVelY();
        }
    }
    private void handleCollisionAlt(BallModel ball) {
    	Vector2d points[] = new Vector2d[]{new Vector2d(pos), 
    									   new Vector2d(pos.getX(), pos.getY()+blockHeight),
    									   new Vector2d(pos.getX()+blockWidth, pos.getY()+blockHeight),
    									   new Vector2d(pos.getX()+blockWidth, pos.getY())};
    	Vector2d sides[] = new Vector2d[]{Vector2d.makeVector(points[0], points[1]),
    									  Vector2d.makeVector(points[1], points[2]),
    									  Vector2d.makeVector(points[2], points[3]),
    									  Vector2d.makeVector(points[3], points[0])};
    	boolean collisionSide[] = new boolean[]{false, false, false, false};
    	
    	Vector2d ballPos = ball.getCenterPos();
    	Vector2d ballPrevPos = ball.getCenterPrevPos();
    	Vector2d ballDisplacement = Vector2d.makeVector(ballPrevPos, ballPos);
    	
    	Vector2d impactPoint = null;
    	int cnt = 0;//Used for debugging, increments when valid collision side if found. Should equal 1 at end of method
    	
    	/*
    	System.out.printf("BlockWidth: %d\n", blockWidth);
		System.out.printf("BlockHeight: %d\n", blockHeight);
		System.out.printf("BallPos cen: ");
		System.out.println(ballPos);
		System.out.printf("BallPrevPos cen: ");
		System.out.println(ballPrevPos);
		System.out.printf("BallPos: ");
		System.out.println(ball.getPos());
		System.out.printf("Ball Disp: ");
		System.out.println(ballDisplacement);
    	for (int i=0; i<4; i++){
    		System.out.printf("Point %d: ", i);
    		System.out.println(points[i]);
    		System.out.printf("Side %d: ", i);
    		System.out.println(sides[i]);
    		
    	}
    	*/
    	for (int i=0; i<4; i++){
    		if (ballDisplacement.isParallel(sides[i])) {
    			System.out.println("Ball is parallel to side " + i);
    			continue;
    		}
    		
    		double Spx, Svx, Spy, Svy;
    		double Bpx, Bvx, Bpy, Bvy;
    		Double G = null, H = null;
    		Spx = points[i].getX();
    		Spy = points[i].getY();
    		Svx = sides[i].getX();
    		Svy = sides[i].getY();
    		
    		Bpx = ballPrevPos.getX();
    		Bpy = ballPrevPos.getY();
    		Bvx = ballDisplacement.getX();
    		Bvy = ballDisplacement.getY();
    		
    		double X = 0, Y = 0;
    		if (i%2 == 0) {//Vertical Side check
    			//System.out.printf("Svx: %f, Bvx: %f, Spx: %f, Bpx: %f H: %f, G: %f, Side = %d\n", Svx, Bvx, Spx, Bpx, H, G, i);
    			if (Svx == 0 || Bvx == 0) {
    				if (Svx == 0){
    					H = Spx - Bpx;
    					H /= Bvx;
    					X = Bpx + Bvx*H;
    				}
    				else {
    					G = Bpx - Spx;
    					G /= Svx;
    					X = Spx + Svx*G;
    				}
    			}
    			//System.out.printf("Svy: %f, Bvy: %f, Spy: %f, Bpy: %f H: %f, G: %f, Side = %d\n", Svy, Bvy, Spy, Bpy, H, G, i);
    			//if (Svy == 0 || Bvy == 0) {
    				if (G == null) {//(Svy == 0){
    					Y = Bpy + Bvy*H;
    				}
    				else {
    					Y = Spy + Svx*G;
    				}
    			//}
    		}
    		else {//Horizontal side check
    			if (Svy == 0 || Bvy == 0){
    				if (Svy == 0) {
    					H = Spy - Bpy;
    					H /= Bvy;
    					Y = Bpy + Bvy*H;
    				}
    				else {
    					G = Bpy - Spy;
    					G /= Svy;
    					Y = Spy + Svy*G;
    				}
    			}
    			//if (Svx == 0 || Bvx == 0) {
    				if (G == null) {//(Svx == 0) {
    					X = Bpx + Bvx*H;
    				}
    				else {
    					X = Spx + Svx*G;
    				}
    			//}
    		}
    		if (X == 0 && Y == 0)
    			System.out.println("X and Y positions not getting initilized");
    		
    		
    		Vector2d tempPt = new Vector2d(X, Y);
    		Vector2d fromSide = Vector2d.makeVector(points[i], tempPt);
    		Vector2d fromBall = Vector2d.makeVector(ballPrevPos, tempPt);
    		System.out.print(tempPt);
    		//System.out.println(" Side: " + i);
    		if ((fromSide.getMagnitude() < sides[i].getMagnitude())&&(fromBall.getMagnitude() < ballDisplacement.getMagnitude())) {
    			collisionSide[i] = true;
    			impactPoint = tempPt;
    			cnt++;
    			break;
    		}
    		//--------------------------
    	}
    	boolean fail = false;
    	if (cnt != 1) {
    		//System.out.println("Too many or no valid collison sides have been found! - " + cnt);
    		fail = true;
    	}
    	if (impactPoint == null) {
    		System.out.println("Impact point not found!");
    		fail = true;
    	}
    	if (fail)
    		return;
    	//***
    	for (int i=0; i<4; i++) {
    		if (collisionSide[i]) {
    			ball.setPos(impactPoint);
    			if (i%2 == 0){
    				ball.invertVelX();
    			}
    			else {
    				ball.invertVelY();
    			}
    		}
    	}
    	/*Consider recursively calling checkCollision to calculate potential multiple collisions in single frame
    	 * would replace for loop above
    	 * for (int i=0; i<4; i++) {
    	 * 		if (collisionSide[i]) {
    	 * 			if (i%2 == 0) {
    	 * 				double x_dis = ball.getX() - impactPoint.getX();
    	 * 				ball.setPrevPos(impactPoint);
    	 * 				ball.setPosX(impactPoint.getX() - x_dis);
    	 * 				ball.invertVelX(); 
    	 * 			}
    	 * 			else {
    	 * 				//stuff for Y things
    	 * 			}
    	 * 			checkCollison(ball);//Need to figure out how to handle getting return value to blockBreakersModel
    	 * 		}
    	 * }
    	 */
    }
    
    /**
     * Checks for collision with the ball. Can be improved as of now it only uses the
     * top left corner as the collision detection.
     * @param ball
     * @return
     */
    public boolean checkCollision(BallModel ball) {
    	double cenX = ball.getCenterPosX();
    	double cenY = ball.getCenterPosY();
    	/*
    	if ((ball.getPosX() > pos.getX()) && (ball.getPosX() < pos.getX() + blockWidth)
    		&& (ball.getPosY() > pos.getY()) && (ball.getPosY() < pos.getY() + blockHeight)) {
    		*/
    	if ((cenX > pos.getX()) && (cenX < pos.getX() + blockWidth)
    		&& (cenY > pos.getY()) && (cenY < pos.getY() + blockHeight)) {
    		handleCollisionAlt(ball);
    		hits--;
    		blockColor = blockColor.darker();
    		if (hits <= 0)
    			collision = true;
    		return true;
    	}
    	else
    		return false;
    }
    private void assignPowerUp() {
    	powerUpID = 0;
    	Random rand = new Random();
    	int chance = rand.nextInt()%100;
    	if (chance < POWERUP_CHANCE){
    		powerUpID = rand.nextInt()%PowerUp.NUMBER_OF_POWERUPS + 1;
    	}
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
    public int getHeight() {
    	return blockHeight;
    }
    public int getWidth() {
    	return blockWidth;
    }
    public Color getColor() {
    	return blockColor;
    }
    public boolean getCollision(){
    	return collision;
    }
    public int getID() {
    	return id;
    }
    public int getPoints() {
    	return points;
    }
    public int getPowerUpID() {
    	return powerUpID;
    }
    //Setter methods
    public void setBlock(Vector2d pos_, int width, int height) {
    	pos = pos_;
    	blockWidth = width;
    	blockHeight = height;
    }
    public void setBlock(double x, double y, int width, int height) {
    	pos = new Vector2d(x,y);
    	blockWidth = width;
    	blockHeight = height;
    }
    public void setColor(Color in) {
    	blockColor = in;
    }
    public void setCollision(boolean value){
    	collision = value;
    }
    void drawBlock(Graphics g, BlockBreakersView view){
		int x = (int)pos.getX();
		int y = (int)pos.getY();
		
		if (view.getFlipVertical()) {
			y = view.translateY(y) - blockHeight;
		}
		g.setColor(blockColor);
		g.fillRect(x, y, blockWidth, blockHeight);
	}
    
    
}
