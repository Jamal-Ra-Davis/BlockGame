

import java.awt.event.*;

/**
 * This class passes the decision to show the popup menu to the BlockBreakersView.
 * Adapted from Fig. 22.7.
 * @author Tom Bylander
 * @author Jonathan Haugen
 * @author Jamal Davis
 * @author Zach Salazar
 * @author Marvin Lopez
 */
public class BlockBreakersPopupController extends MouseAdapter {
	
	private BlockBreakersModel model;
	private BlockBreakersView view;
	
	/**
	 * @param model the model of this BlockBreakers game
	 * @param view the view of this BlockBreakers game
	 */
	public BlockBreakersPopupController(BlockBreakersModel model, BlockBreakersView view) {
		this.model = model;
		this.view = view;
	}
	
	/**
	 * Go back to the view object to do the pop up menu.
	 */
    public void mousePressed(MouseEvent event) { 
       view.checkForTriggerEvent(event); // check for trigger
    } // end method mousePressed

    /**
     * Go back to the view object to do the pop up menu.
     */
    public void mouseReleased(MouseEvent event) { 
       view.checkForTriggerEvent(event); // check for trigger
    } // end method mouseReleased
}
