

import java.awt.Color;

import javax.swing.*;
/**
 * This implements a simple BlockBreakers game to illustrate a more complex
 * GUI with multiple classes for the controller and view parts of 
 * the MVC pattern.
 * @author Tom Bylander
 * @author Jonathan Haugen
 * @author Jamal Davis
 * @author Zach Salazar
 * @author Marvin Lopez
 */
public class BlockBreakers {
	/**
	 * Create the objects for the model, view and controller,
	 * and launch the application.
	 */
	public static void main(String[] args) {
		/* create new model, view and controller */
		BlockBreakersModel model = new BlockBreakersModel();
		BlockBreakersView view = new BlockBreakersView(model);
		BlockBreakersController mouseController = 
				new BlockBreakersController(model, view);
		BlockBreakersResizeController resizeController = 
				new BlockBreakersResizeController(model, view);
		BlockBreakersMenuController menuController = 
				new BlockBreakersMenuController(model, view);
		BlockBreakersPopupController popupController = 
				new BlockBreakersPopupController(model, view);
		BlockBreakersRepaintController repaintController = 
				new BlockBreakersRepaintController(model, view);
		BlockBreakersKeyBoardController keyboardController = 
				new BlockBreakersKeyBoardController(model, view);
		
		// repaint timer so that the window will update every 25 ms
	    new Timer(25, repaintController).start();
		
		/* register other controllers as listeners */
		view.registerListeners(mouseController, resizeController,
				menuController, popupController, keyboardController);
		
		/* start it up */
		view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		view.setSize(800, 600);//Larger screen
		view.setVisible(true);
		view.setResizable(false);
	}
}