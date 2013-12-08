

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class is an event handler for the Timer.
 * This is set up in the main method in the BlockBreakers class.
 * @author Tom Bylander
 * @author Jonathan Haugen
 * @author Jamal Davis
 * @author Zach Salazar
 * @author Marvin Lopez
 */
public class BlockBreakersRepaintController implements ActionListener {
	private BlockBreakersModel model;
	private BlockBreakersView view;
	
	/**
	 * @param model the model of this BlockBreakers game
	 * @param view the view of this BlockBreakers game
	 */
	public BlockBreakersRepaintController(BlockBreakersModel model, BlockBreakersView view) {
		this.model = model;
		this.view = view;
	}
	
	/**
	 * Perform the needed actions when the timer goes off.
	 * When the timer goes off, move the ball to its next position,
	 * determine if the number of hits or misses changed,
	 * and repaint the window.
	 */
	public void actionPerformed(ActionEvent event) {
		int hits = model.getHits();
		int misses = model.getMisses();
		int chain = model.getChain();
		ScoreModel score = model.getScore();
		model.moveBall();
		String status = model.getChain() + " Chain, x" + score.getMultiplier() + " Multiplier";
		status += ", PowerUp: " + model.getPowerUpName();
		view.setStatus(status);
		//adding to the east panel
		view.setEastscores(score.getScore()+"");
		view.setEastballs(model.getBallsLeft()+"");
		String powerup_status = model.getPowerUpName();
		if (model.hasActivePowerUp()){
			int time = model.getPowerUpLife()/25;
			powerup_status += " (" + time + "s)";
		}
		view.setEastPowerUps(powerup_status);
		if (hits != model.getHits() || misses != model.getMisses() ||
			chain != model.getChain()) {
			view.setStatus(model.getChain() + " Chain, x" + score.getMultiplier() +
					   " Multiplier");
		//adding to the east panel
		view.setEastscores(score.getScore()+"");
		view.setEastballs(model.getBallsLeft()+"");
			
		}
		if (model.levelComplete()) {
			view.setStatus("Level Complete. You win!");
		}
		if (model.gameOver() && model.getBallsLeft() == 0) {
			view.setStatus("Game Over!");
		}
		if(model.beatTheGame()){
			view.setStatus("You Beat The Game");
		}
		// Handles removing the blocks that have been collided with
		
		ArrayList<BlockModel> Blocks = model.getBlockList();
		
		Iterator<BlockModel> iterator = Blocks.iterator();
        while (iterator.hasNext()) {
        	BlockModel block = (BlockModel)iterator.next();
        	if (block.getCollision()) {
        		iterator.remove();
        		System.out.println("# of blocks in list: " + Blocks.size());
        	}
        }
		view.repaint();
	}

}
