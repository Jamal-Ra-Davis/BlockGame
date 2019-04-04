

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * This class is an event handler for when the BlockBreaker's panel
 * is resized.
 * @author Tom Bylander
 * @author Jonathan Haugen
 * @author Jamal Davis
 * @author Zach Salazar
 * @author Marvin Lopez
 */
public class BlockBreakersResizeController extends ComponentAdapter {
	private BlockBreakersModel model;
	private BlockBreakersView view;
	
	/**
	 * @param model the model of this BlockBreakers game
	 * @param view the view of this BlockBreakers game
	 */
	public BlockBreakersResizeController(BlockBreakersModel model, BlockBreakersView view) {
		this.model = model;
		this.view = view;
	}

	/**
	 * Ensure that the model gets the correct size of the BlockBreakersPanel.
	 */
    public void componentResized(ComponentEvent event) {
    	Dimension size = view.getBlockBreakersPanelSize();
    	if (size.width != model.getWidth() || size.height != model.getHeight()) {
    		model.setSize(size.width, size.height);
            String status_ = String.format("Size is %d x %d", size.width, size.height);
    		//view.setStatus("Size is " + size.width + " x " + size.height);
            view.setStatus(status_);
    	}
    }
}


