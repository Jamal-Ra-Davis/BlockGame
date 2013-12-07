

import java.awt.event.*;
/**
 * This is the mouse event handler for the PongPanel.
 * @author Tom Bylander
 * @author Jonathan Haugen
 * @author Jamal Davis
 * @author Zach Salazar
 * @author Marvin Lopez
 * 
 */
public class BlockBreakersKeyBoardController implements KeyListener {
	private BlockBreakersModel model;
	private BlockBreakersView view;
	boolean right = false;
	boolean left = false;
	boolean enter = false;
	/**
	 * @param model
	 *            the model of this BlockBreakers game
	 * @param view
	 *            the view of this BlockBreakers game
	 */
	public BlockBreakersKeyBoardController(BlockBreakersModel model,
			BlockBreakersView view) {
		this.model = model;
		this.view = view;
	}

	@Override
	public void keyPressed(KeyEvent key) {
		// TODO Auto-generated method stub
		if (key.getKeyCode() == KeyEvent.VK_LEFT) {
			//System.out.println("left presssed");
			//System.out.println(model.getPaddleX()+"this is left key");
			double paddlex = model.getPaddleX();
			//System.out.println(paddlex);
			//model.setPaddle(view.translateX((int)paddlex), 
			//		view.translateY((int)model.getPaddleY()));
			model.setPaddle((int) paddlex, (int) model.getPaddleY());
			left = true;
		} else if (key.getKeyCode() == KeyEvent.VK_RIGHT) { //doesn't work
			//System.out.println("right pressed");
			//System.out.println(model.getPaddleX()+"this is right key");
			double paddlex = model.getPaddleX();
			//System.out.println(paddlex);
			//model.setPaddle(view.translateX((int)paddlex), 
			//		view.translateY((int)model.getPaddleY()));
			model.setPaddle((int) paddlex+100, (int) model.getPaddleY());
			right = true;
		}else if(key.getKeyCode() == KeyEvent.VK_ENTER || key.getKeyCode() == KeyEvent.VK_SPACE){// enter or space to launch ball
			model.launchBall();
			enter = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent key) {
		// TODO Auto-generated method stub
		
		if (key.getKeyCode() == KeyEvent.VK_LEFT) {
			left = true;
		} else if (key.getKeyCode() == KeyEvent.VK_RIGHT) {
			right = true;
		} else if(key.getKeyCode() == KeyEvent.VK_ENTER){
			enter = true;
		} 
		
	}

	@Override
	public void keyTyped(KeyEvent key) {
		// TODO Auto-generated method stub
		//left empty
	}

}