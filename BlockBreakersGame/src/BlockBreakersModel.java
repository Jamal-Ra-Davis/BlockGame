

import java.util.ArrayList;
import java.util.Iterator;
import java.awt.*;

import javax.swing.JOptionPane;

/**
 * This is the model of the simple BlockBreakers game.
 * It holds the properties of the ball, paddle, and BlockBreakersPanel.
 * It also determines the movement of the ball.
 * @author Tom Bylander
 * @author Jonathan Haugen
 * @author Jamal Davis
 * @author Zach Salazar
 * @author Marvin Lopez
 */
public class BlockBreakersModel {
    // Testing out ball with BallModel class
    private BallModel ball;
    private boolean ballLaunched;
    private int ballsLeft;
    
    //Properties of block group
    private ArrayList<BlockModel> Blocks;
    private int blockRows, blockCols;
    private boolean blockStart;
    private int blocksRemaining;
    
    //ScoreModel
    private ScoreModel score;
    
    // properties of the paddle
    private PaddleModel paddle;

    // properties of the panel
    private int width, height;

    // properties of the game
    private int hits, misses, chain;
    private boolean pause;
    private boolean gameOver;
    private boolean levelComplete;
    private boolean beatGame;
    private int level;
    private int reply;
    private LevelData nextLevel;
    //Forces test
    private ArrayList<ForceObject> forces;
    private ForceObject attract;

    //PowerUps
    private PowerUp activePowerUp;
    private ArrayList<PowerUp> fieldPowerUps;
    
    public BlockBreakersModel() {
    	// Not needed as class variables anymore
        double ballX = 10000;
        double ballY = 10000;
        int ballDiameter = 10;
        double change = 5;
        double velocityX = change;
        double velocityY = change;
        //---------------
        ball = new BallModel(ballX, ballY, velocityX, velocityY, ballDiameter);
        ballLaunched = false;
        ballsLeft = 4;
        
    	paddle = new PaddleModel();
        
        blockRows = 4;
        blockCols = 10;
        blockStart = true;
        Blocks = new ArrayList<BlockModel>();
        blocksRemaining = blockRows*blockCols;
        
        score = new ScoreModel();
        
        width = 0;
        height = 0;
        hits = 0;
        misses = 0;
        chain = 0;
        pause = false;
        gameOver = false;
        levelComplete = false;
        beatGame =false;
        level = 1;
        reply =0;
        forces = new ArrayList<ForceObject>();
        
        activePowerUp = null;
        fieldPowerUps = new ArrayList<PowerUp>();
    }

    /**
     * Set the position of the paddle based on the
     * position of the mouse
     * @param x horizontal position of the mouse
     * @param y vertical position of the mouse
     */
    public void setPaddle(int x, int y) {
        paddle.setPaddleX(x - paddle.getPaddleWidth() / 2);
        if (paddle.getPaddleX() > width - paddle.getPaddleWidth()) {
            paddle.setPaddleX(width - paddle.getPaddleWidth());
        }
        if (paddle.getPaddleX() < 0) {
            paddle.setPaddleX(0);
        }
    }
    
    /**
     * Ball will stay on paddle until the user clicks and launches the ball
     */
    public void launchBall() {
    	ballLaunched = true;
    }
    
    private void setNextLevel() {
    	level++;
    	if (level == LevelData.NUMBER_OF_LEVELS){
    		//level = LevelData.NUMBER_OF_LEVELS;
    		levelComplete = false;
    		beatGame =true;   		
    		//resetLevel();
    	}else{
    	nextLevel = LevelData.makeLevel(level, width, height);
    	Blocks = nextLevel.getBlockList();
    	forces = nextLevel.getForceList();
    	blocksRemaining = nextLevel.getBlockCount();
    	ballLaunched = false;
    	levelComplete = false;
    	fieldPowerUps.clear();
    	ball.setPosX(paddle.getPaddleX() + paddle.getPaddleWidth()/2 - ball.getDiameter()/2);
    	ball.setPosY(paddle.getPaddleY() + paddle.getPaddleHeight());
    	
    	ball.setVel(5, 5);
    	}
    }
   /**
    * reset to level 1
    */
    public void resetLevel(){
    	if(level ==1){Blocks.clear();}
    	else{
    		if (nextLevel != null)
    			nextLevel.resetLevelData();
    		    Blocks.clear();
    	}
    	level = 1;
    	nextLevel = LevelData.makeLevel(level, this.width, this.height);
    	Blocks = nextLevel.getBlockList();
    	forces = nextLevel.getForceList();
    	blocksRemaining = nextLevel.getBlockCount();
    	
    	
    	fieldPowerUps.clear();
    	ball.setPosX(paddle.getPaddleX() + paddle.getPaddleWidth()/2 - ball.getDiameter()/2);
    	ball.setPosY(paddle.getPaddleY() + paddle.getPaddleHeight());
    	
    	ballLaunched = false;
    	levelComplete = false;
    	beatGame =false;
    	gameOver = false;
    	score.resetScore();
    	ballsLeft = 4;
    	if (activePowerUp != null) {
    		activePowerUp.deactivatePowerUp(this);
    		activePowerUp = null;
    	}
		ball.setVel(5, 5);
		  	
    }
    /**
     * Move the ball to its next position based on its
     * current position, its velocity, and the position
     * of the walls and the paddle.
     */
    public void moveBall() {
        // Don't do anything if the game is paused.
        // Also, this might get called before the window is opened.
    	if (beatGame){
    	    int button = JOptionPane.showConfirmDialog(null,"Would you like to play again","Congrats you beat the game",JOptionPane.YES_NO_OPTION);
    		if(button==JOptionPane.YES_OPTION){
    			resetLevel();
    		}else{
    			JOptionPane.showMessageDialog(null, "GOODBYE","Quiter",JOptionPane.INFORMATION_MESSAGE);
    	       System.exit(0);
    		}
    	    System.out.println(button);
    	}
    	if (levelComplete) {
    		String prompt = "You beat the level! Click ok to move on the the next one";
    		JOptionPane.showMessageDialog(null,prompt,"Congrats",JOptionPane.INFORMATION_MESSAGE);
    		setNextLevel();		
    		return;
    	}
    	if (gameOver) {
    		//System.exit(0);
    		ball.setPos(-50, -50);
        	return;
        }
        if (height == 0 || pause) {
            return;
        }
        
        //Update powerup positions
        for (int i=0; i<fieldPowerUps.size(); i++)
        	fieldPowerUps.get(i).updatePos();
        
        // Sets the ball position to the same as paddle position until mouse is clicked
        if (!ballLaunched) {
        	ball.setPosX(paddle.getPaddleX() + paddle.getPaddleWidth()/2 - ball.getDiameter()/2);
        	ball.setPosY(paddle.getPaddleY() + paddle.getPaddleHeight());
        	return;
        }
        
        if (score.checkMilestone())
        	recieveExtraBall();
        
        //Update ball pos
        ball.clearAccleration();
        //attract.applyForce(ball);
        for (int i=0; i<forces.size(); i++)
        	forces.get(i).applyForce(ball);
        ball.updatePos();
        score.setMultipler(chain);
        
        
        
        //Update acivePowerUp status
        if (activePowerUp != null) {
        	if (activePowerUp.isActive()) {
        		activePowerUp.updatePowerUp(this);
        	}
        	else {
        		activePowerUp.deactivatePowerUp(this);
        		activePowerUp = null;
        	}
        }
        
        /* 
        // imperfect collision detection with the paddle
        if (ballX + ballDiameter / 2 > paddleX && 
                ballX + ballDiameter / 2 < paddleX + paddleWidth &&
                ballY > paddleY && ballY < paddleY + paddleHeight) {
            velocityY = change;
            hits++;
        }
		*/
        /*
        if (ball.getPosX() + ball.getDiameter()/2 > paddle.getPaddleX() &&
        		ball.getPosX() + ball.getDiameter()/2 < paddle.getPaddleX() + paddle.getPaddleWidth() &&
        		ball.getPosY() > paddle.getPaddleY() && ball.getPosY() < paddle.getPaddleY() + paddle.getPaddleHeight()) {
        	ball.setPosY(paddle.getPaddleY() + paddle.getPaddleHeight());
        	ball.invertVelY();
        	hits++;
        	chain++;
        }
        */
        if (paddle.checkCollision(ball)){
        	hits++;
        	chain++;
        	//Just for fun, exponentially increases the ball's velocity as ball hits paddle
        	Vector2d speed = ball.getVel();
        	double mag = speed.getMagnitude();
        	speed.normalize();
        	mag *= 1.1;
        	speed.sMultiply(mag);
        	ball.setVelLimited(speed);
        }
        // detect movement into walls and bounce appropriately
        // bounce into right wall
        /*
        if (ballX > width - ballDiameter) {
            ballX = width - ballDiameter;
            velocityX = -change;
        }
        */
        if (ball.getPosX() > width - ball.getDiameter()) {
        	ball.setPosX(width - ball.getDiameter());
        	ball.invertVelX();
        }
        	
        // bounce into left wall
        /*
        if (ballX < 0) {
            ballX = 0;
            velocityX = change;
        }
        */
        if (ball.getPosX() < 0) {
        	ball.setPosX(0);
        	ball.invertVelX();
        }
        
        // bounce into bottom wall (wall near paddle)
        /*
        if (ballY > height - ballDiameter && velocityY >= 0) {
            ballY = height - ballDiameter;
            velocityY = -change;
        }
        */
        if (ball.getPosY() > height - ball.getDiameter()) {
        	ball.setPosY(height - ball.getDiameter());
        	ball.invertVelY();
        }
        
        // bounce into top wall (wall far from paddle)
        /*
        if (ballY < -ballDiameter) { 
            ballY = height;
            velocityY = -change;
            misses++;
        }
        */
        if (ball.getPosY() < -1*ball.getDiameter()) {
//        	ball.setPosX(paddle.getPaddleX() + paddle.getPaddleWidth()/2);
//       	ball.setPosY(paddle.getPaddleY() + paddle.getPaddleHeight());
        	ball.invertVelY();
        	misses++;
        	ballsLeft--;
        	if (ballsLeft <= 0)
        		gameOver = true;
        	chain = 0;
        	ballLaunched = false;
        	ball.setVel(5, 5);//Resets balls speed when it goes out of bounds
        }
        // Checks for ball colliding with block, currently changes block color and
        // removes blocks.
        Iterator<BlockModel> iterator = Blocks.iterator();
        while (iterator.hasNext()) {
        	BlockModel block = (BlockModel)iterator.next();
        	if (block.checkCollision(ball)) {
        		//block.setColor(Color.BLACK);
        		//block.setCollision(true);
        		if (block.getCollision()){
        			score.recievePoints(block.getPoints());
        			blocksRemaining--;
        			//System.out.println("# of Blocks remaining: " + blocksRemaining);
        			if (blocksRemaining <= 0)
        				levelComplete = true;
        			if (block.getPowerUpID() != 0){
        				//Make powerup and put it into play
        				int ID = block.getPowerUpID();
        				Vector2d PU_pos = block.getPos();
        				PU_pos.add(block.getWidth()/2.0, block.getHeight()/2.0);
        				PowerUp temp = PowerUp.makePowerUp(ID, PU_pos);
        				if (temp != null)
        					fieldPowerUps.add(temp);
        			}
        		}
        			
        	}
        }
        
        Iterator<PowerUp> iterator_PU = fieldPowerUps.iterator();
        while (iterator_PU.hasNext()) {
        	PowerUp powerup = (PowerUp)iterator_PU.next();
        	if (paddle.checkPowerUpCollision(powerup)) {
        		if (activePowerUp != null) {
        			activePowerUp.deactivatePowerUp(this);
        			activePowerUp = powerup.makeCopy();
        			activePowerUp.activatePowerUp(this);
        		}
        		else {
        			activePowerUp = powerup.makeCopy();
        			activePowerUp.activatePowerUp(this);
        		}
        		iterator_PU.remove();
        	}
        	else if (powerup.getPosY() < -1*powerup.getDiameter()) {
        		//fieldPowerUps.remove(powerup);	
        		iterator_PU.remove();
        	}
        }     

    }
    
    /**
     * Places the blocks
     * @param width
     * @param length
     */
    public void setSize(int width, int length) {
        this.width = width;
        this.height = length;
        
        if (blockStart && this.width != 0 && this.height != 0) {
        	
        	nextLevel = LevelData.makeLevel(level, this.width, this.height);
        	Blocks = nextLevel.getBlockList();
        	forces = nextLevel.getForceList();
        	blocksRemaining = nextLevel.getBlockCount();
        	ballLaunched = false;
        	levelComplete = false;
        	fieldPowerUps.clear();
        	ball.setPosX(paddle.getPaddleX() + paddle.getPaddleWidth()/2 - ball.getDiameter()/2);
        	ball.setPosY(paddle.getPaddleY() + paddle.getPaddleHeight());
        	
        	blockStart = false;
        }
        
    }

    // Ball getters
    public double getBallX() { /*return ballX;*/return ball.getPosX(); }
    public double getBallY() { /*return ballY;*/return ball.getPosY(); }
    public int getBallDiameter() {	/*return ballDiameter;*/return ball.getDiameter(); }

    // Paddle getters
    public double getPaddleX() { /*return paddleX;*/ return paddle.getPaddleX(); }
    public double getPaddleY() { /*return paddleY;*/ return paddle.getPaddleY();}
    public int getPaddleWidth() { /*return paddleWidth;*/ return paddle.getPaddleWidth();}
    public int getPaddleHeight() { /*return paddleHeight;*/ return paddle.getPaddleHeight();}
    public PaddleModel getPaddle() {return paddle;}

    // Screen Size getters
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    
    // Game Info getters and setters
    public int getHits() { return hits; }
    public int getMisses() { return misses; }
    public int getChain() {return chain;}
    public int getBallsLeft() {return ballsLeft;}
    public boolean getPause() { return pause; }
    public boolean gameOver() { return gameOver;}
    public boolean levelComplete() {return levelComplete;}
    public boolean beatTheGame(){ return beatGame; };
    public void setPause(boolean pause) { this.pause = pause; }
    public void recieveExtraBall() {
    	ballsLeft++;
    }
    
    // Block List getter
    public ArrayList<BlockModel> getBlockList() {return Blocks;}
    public ScoreModel getScore() {return score;}
    
    
    //Force list getters
    public ForceObject getForceObject() {return attract;}
    public ArrayList<ForceObject> getForces() {return forces;}
    
    //PowerUp  getters
    public boolean hasActivePowerUp() {
    	if (activePowerUp != null)
    		return activePowerUp.isActive();
    	else
    		return false;
    }
    public String getPowerUpName() {
    	String name = "[No powerup active]";
    	if (activePowerUp != null) {
    		name = activePowerUp.getName();
    	}
    	return name;
    }
    public int getPowerUpLife() {
    	return activePowerUp.getLifeTime();
    }
    public ArrayList<PowerUp> getPowerUps() {return fieldPowerUps;}
    
    public void drawModel(Graphics g, BlockBreakersView view) {
    	paddle.drawPaddle(g, view);
    	ball.drawBall(g, view);
    	for (BlockModel block : Blocks) {
    		block.drawBlock(g, view);
    	}
    	for (ForceObject force : forces) {
    		force.drawForce(g, view);
    	}
    	for (PowerUp powerup : fieldPowerUps) {
    		powerup.drawPowerUp(g, view);
    	}
    }
    
}
