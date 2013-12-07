

import java.awt.event.*;

/**
 * This reacts to selections from the menu, and also
 * implements the keyboard shortcuts.
 * @author Tom Bylander
 * @author Jonathan Haugen
 * @author Jamal Davis
 * @author Zach Salazar
 * @author Marvin Lopez
 */
public class BlockBreakersMenuController extends KeyAdapter implements ActionListener {
	private BlockBreakersModel model;
	private BlockBreakersView view;
	
	/**
	 * @param model the model of this BlockBreakers game
	 * @param view the view of this BlockBreakers game
	 */
	public BlockBreakersMenuController(BlockBreakersModel model, BlockBreakersView view) {
		this.model = model;
		this.view = view;
	}
	
	/**
	 * Handle the menu item that was selected.
	 */
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		if (command.equals("Exit")) {
			view.dispose();
			System.exit(0);
		} else if (command.equals("Pause/Continue")) {
			model.setPause(! model.getPause());
		} else if (command.equals("Flip Vertically")) {
			view.setFlipVertical(! view.getFlipVertical());	
		} else if (command.equals("Reset")){
			System.out.println("RESET");
			
			model.resetLevel();			
		}
	}
	
	/**
	 * Handle the keyboard shortcut.
	 */
	public void keyTyped(KeyEvent event) {
		char c = event.getKeyChar();
		if (c == 'e' || c == 'E') {
			view.dispose();
			System.exit(0);
		} else if (c == 'p' || c == 'P') {
			model.setPause(! model.getPause());
		} else if (c == 'f' || c == 'F') {
			view.setFlipVertical(! view.getFlipVertical());
		} else if(c =='r' || c =='R'){
			System.out.println("RESET");
			model.resetLevel();
		}
	}
}
