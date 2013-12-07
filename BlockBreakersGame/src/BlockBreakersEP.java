

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

/**
 * @author Zachary Salazar
 */
public class BlockBreakersEP extends JPanel implements ActionListener {
	private JPanel panel;
	private int bgc = 0;
	public BlockBreakersEP(BlockBreakersPanel blockBreakerPanel) {
		// TODO Auto-generated constructor stub
		this.panel = blockBreakerPanel;
	}
	/** handles the two buttons in the east panel*/
	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		String str = event.getActionCommand();
		if (str.equals("" +
				"<html><div align=center>Change<br />Background<br />Color</div></html>")) {
			 if (bgc == 0) {
			 //view.setBackground(Color.BLACK);
			 panel.setBackground(Color.BLACK);
			 bgc = 1;
			 } else {
				 panel.setBackground(Color.WHITE);
				 bgc = 0;
			 }
		}
		//ALSO
		if (str.equals("<html><div align=center>Change Color<br />Scheme</div></html>")) {
			
			System.out.println("ENTERED FOOOLLL");
		}
	}
}
