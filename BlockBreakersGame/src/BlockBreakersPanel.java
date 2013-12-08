

import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

/**
 * The BlockBreakersPanel draws the paddle and the ball when repainted.
 * This class along with BlockBreakersView implements the view part
 * of the MVC pattern.
 * @author Tom Bylander
 * @author Jonathan Haugen
 * @author Jamal Davis
 * @author Zach Salazar
 * @author Marvin Lopez
 */
public class BlockBreakersPanel extends JPanel {
	private BlockBreakersModel model;
	private BlockBreakersView view;
	
	/**
	 * Set up the instance variables and the focus.
	 * @param model the model of this BlockBreakers game
	 * @param view the view of this BlockBreakers game
	 */
	public BlockBreakersPanel(BlockBreakersModel model, BlockBreakersView view) {
		this.model = model;
		this.view = view;
		// so this JPanel can listen to the keyboard
		this.setFocusable(true);
	}
	
	/**
	 * Draw the paddle and the ball.  Pay attention to whether
	 * flipVertical is true or not.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		model.drawModel(g, view);
		/*
		// Paddle drawing
		g.setColor(Color.RED);
		int x = (int) model.getPaddleX();
		int y = (int) model.getPaddleY();
		if (view.getFlipVertical()) {
			y = view.translateY(y) - model.getPaddleHeight();
		}
		g.fillRect(x, y, model.getPaddleWidth(), model.getPaddleHeight());
		
		// Ball drawing
		g.setColor(Color.RED);
		x = (int) model.getBallX();
		y = (int) model.getBallY();
		if (view.getFlipVertical()) {
			y = view.translateY(y) - model.getBallDiameter();
		}
		g.fillOval(x, y, model.getBallDiameter(), model.getBallDiameter());
		
		// Block drawing
		ArrayList<BlockModel> Blocks = model.getBlockList();
		for(BlockModel block : Blocks) {
			Color blockColor = block.getColor();
			x = (int)block.getPosX();
			y = (int)block.getPosY();
			int blk_width = block.getWidth();
			int blk_height = block.getHeight();
			if (view.getFlipVertical()) {
				y = view.translateY(y) - blk_height;
			}
			g.setColor(blockColor);
			g.fillRect(x, y, blk_width, blk_height);
		}
		
		ArrayList<ForceObject> forces = model.getForces();
		for (int i=0; i<forces.size(); i++) {
			g.setColor(forces.get(i).getColor());
			x = (int)forces.get(i).getPosX();
			y = (int)forces.get(i).getPosY();
			int D = (int)forces.get(i).getDiameter();
			if (view.getFlipVertical()) {
				y = view.translateY(y) - D;//model.getBallDiameter();
			}
			g.fillOval(x, y, D, D);
		}
		ArrayList<PowerUp> powerups = model.getPowerUps();
		for (int i=0; i<powerups.size(); i++) {
			g.setColor(powerups.get(i).getColor());
			x = (int)powerups.get(i).getPosX();
			y = (int)powerups.get(i).getPosY();
			int D = (int)powerups.get(i).getDiameter();
			if (view.getFlipVertical()) {
				y = view.translateY(y) - D;//model.getBallDiameter();
			}
			g.fillOval(x, y, D, D);
		}
		*/
	}
}
