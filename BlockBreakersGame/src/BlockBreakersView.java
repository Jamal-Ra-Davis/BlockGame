

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
/**
 * This along with BlockBreakersPanel implements the view of the simple BlockBreakers game.
 * @author Tom Bylander
 * @author Jonathan Haugen
 * @author Jamal Davis
 * @author Zach Salazar
 * @author Marvin Lopez
 */
public class BlockBreakersView extends JFrame {
    private BlockBreakersModel model;
    private BlockBreakersPanel blockBreakerPanel;
    private JLabel statusBar;
    private JLabel scorel;
    private JLabel ballsleft;
    private JLabel powerup;
    private JMenu blockBreakerMenu;
    private JPopupMenu popupMenu;
    /* For east panel */
    private JPanel eastP;
    
    private JPanel menu;
    private JButton start;
    private JButton swap;
    /* controls whether paddle appears on top of JPanel */
    private boolean flipVertical;

    /**
     * The constructor creates the components and places them in the window.
     * @param model the model for this BlockBreakers game
     */
    public BlockBreakersView(BlockBreakersModel model) {
        super("BlockBreakers Game");
        this.model = model;
        flipVertical = true;

        // create the menu

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        blockBreakerMenu = new JMenu("Menu");
        blockBreakerMenu.setMnemonic('M');
        menuBar.add(blockBreakerMenu);

        JMenuItem flipVerticalItem = new JMenuItem("Flip Vertically");
        flipVerticalItem.setMnemonic('F');
        blockBreakerMenu.add(flipVerticalItem);

        JMenuItem pauseItem = new JMenuItem("Pause/Continue");
        pauseItem.setMnemonic('P');
        blockBreakerMenu.add(pauseItem);

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setMnemonic('E');
        blockBreakerMenu.add(exitItem);
        
        // added the reset button
        JMenuItem resetItem = new JMenuItem("Reset");
        resetItem.setMnemonic('R');
        blockBreakerMenu.add(resetItem);

        // create the popup menu, need new JMenuItems

        popupMenu = new JPopupMenu();
        popupMenu.add(new JMenuItem("Flip Vertically"));
        popupMenu.add(new JMenuItem("Pause/Continue"));
        popupMenu.add(new JMenuItem("Exit"));
        popupMenu.add(new JMenuItem("Reset"));

        /* CENTER:
         * The panel where BlockBreakers is played
         */
        
        //--------------------------------
        
        menu = new JPanel();
        menu.setLayout(new BorderLayout());
        JLabel title = new JLabel("Block Breakers", JLabel.CENTER);
        start = new JButton("Click to Play");
        
        start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent A) {
				showGame();
			}
		});
        
        menu.add(title, BorderLayout.CENTER);
        menu.add(start, BorderLayout.SOUTH);
        
        //----------------------------------
        
        blockBreakerPanel = new BlockBreakersPanel(model, this);
        add(blockBreakerPanel, BorderLayout.CENTER);
        blockBreakerPanel.setBackground(Color.WHITE);
        Dimension size = blockBreakerPanel.getSize();
        model.setSize(size.width, size.height);
        
        /* EAST
         * The East panel  will have the score of the game, 
         * balls left in game, select background, color scheme,
         * game mode, and difficulty.
         */
        
        eastP = new JPanel(new GridLayout(10,1));
        add(eastP, BorderLayout.EAST);
        eastP.setBackground(Color.LIGHT_GRAY);
        eastP.setPreferredSize(new Dimension(130, 200));
        
        JLabel label = new JLabel("Score:", JLabel.CENTER);
        label.setOpaque(true);
        label.setBackground(Color.LIGHT_GRAY);
        label.setFont(new Font("Arial", Font.BOLD, 30));
        eastP.add(label);
        
       //updates the score
        scorel = new JLabel("The status bar is open.",JLabel.CENTER);
        scorel.setOpaque(true); // need this for setBackground to work
        scorel.setBackground(Color.LIGHT_GRAY);
        scorel.setForeground(Color.RED);
        scorel.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 20));
        eastP.add(scorel);
        
   
        JLabel label2 = new JLabel("Balls Left:",JLabel.CENTER);
        label2.setOpaque(true);
        label2.setBackground(Color.LIGHT_GRAY);
        label2.setFont(new Font("Arial", Font.BOLD, 20));
        eastP.add(label2);
       
        //updates the balls left
        ballsleft = new JLabel("The status bar is open.",JLabel.CENTER);
        ballsleft.setOpaque(true); // need this for setBackground to work
        ballsleft.setBackground(Color.LIGHT_GRAY);
        ballsleft.setForeground(Color.RED);
        ballsleft.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 20));
        eastP.add(ballsleft);
       
        
        JLabel label3 = new JLabel("Power-Up", JLabel.CENTER);
        label3.setOpaque(true);
        label3.setBackground(Color.LIGHT_GRAY);
        label3.setFont(new Font("Arial", Font.BOLD, 20));
        eastP.add(label3);
        
        //updates powerup status
        powerup = new JLabel("The status bar is open.",JLabel.CENTER);
        powerup.setOpaque(true);
        powerup.setBackground(Color.LIGHT_GRAY);
        powerup.setFont(new Font("Arial", Font.BOLD, 10));
        powerup.setForeground(Color.WHITE);
        eastP.add(powerup);
        
        JButton b1 = new JButton("" +
        		"<html><div align=center>Change<br />Background<br />Color</div></html>");
        Font bFont=new Font(b1.getFont().getName(),b1.getFont().getStyle(),10); 
        b1.setFont(bFont);
        //b1.setBackground(Color.LIGHT_GRAY);
        eastP.add(b1);
        BlockBreakersEP handler1 = new BlockBreakersEP(blockBreakerPanel);
		b1.addActionListener(handler1);
        
		swap = new JButton("Main Menu");
		Font sFont = new Font(swap.getFont().getName(),swap.getFont().getStyle(),10);
		eastP.add(swap);
		
		swap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent A) {
				showMenu();
			}
		});
		
		
        /*JButton b2 = new JButton("" +
        		"<html><div align=center>Change Color<br />Scheme</div></html>");
        Font bFont2 = new Font(b2.getFont().getName(),b2.getFont().getStyle(),10); 
        b2.setFont(bFont2);
        eastP.add(b2);
        BlockBreakersEP handler2 = new BlockBreakersEP(blockBreakerPanel);
        b2.addActionListener(handler2);*/
        
        /*JButton b3 = new JButton("<html><div align=center>Change<br />Mode</div></html>");
        Font bFont3 = new Font(b3.getFont().getName(),b3.getFont().getStyle(),12); 
        b2.setFont(bFont3);
        eastP.add(b3);
        */
        
        //model.getBlockList().iterator().next().getColor().BLACK;

        /* SOUTH:
         * A status bar for telling us what happens.
         * Borrowed from Figure 14.15.
         */
        statusBar = new JLabel("The status bar is open.");
        statusBar.setBackground(Color.YELLOW);
        statusBar.setOpaque(true); // need this for setBackground to work
        add(statusBar, BorderLayout.SOUTH);

        // so the BlockBreakersPanel can listen to the keyboard
        blockBreakerPanel.requestFocus();
    }

    /**
     * Register event handlers with the appropriate components.
     * @param controller1 a BlockBreakersMouseController
     * @param controller2 a BlockBreakersResizeController
     * @param controller3 a BlockBreakersMenuController
     * @param controller4 a BlockBreakersPopupController
     */
    public void registerListeners(BlockBreakersController controller1, 
            BlockBreakersResizeController controller2,
            BlockBreakersMenuController controller3,
            BlockBreakersPopupController controller4, 
            BlockBreakersKeyBoardController controller5) {
        blockBreakerPanel.addMouseListener(controller1);
        blockBreakerPanel.addMouseMotionListener(controller1);
        blockBreakerPanel.addComponentListener(controller2);
        blockBreakerPanel.addKeyListener(controller3);
        this.addMouseListener(controller4);
        blockBreakerPanel.addKeyListener(controller5);

        Component[] components = blockBreakerMenu.getMenuComponents();
        for (Component component : components) {
            if (component instanceof AbstractButton) {
                AbstractButton button = (AbstractButton) component;
                button.addActionListener(controller3);
            }
        }

        components = popupMenu.getComponents();
        for (Component component : components) {
            if (component instanceof AbstractButton) {
                AbstractButton button = (AbstractButton) component;
                button.addActionListener(controller3);
            }
        }
    }

    /**
     * Set the text in the status bar.
     * @param text
     */
    public void setStatus(String text) {
        statusBar.setText(text);
    }
    /**
     * Set the text in the east bar for scores.
     * @param text
     */
    public void setEastscores(String text) {
       scorel.setText(text);
        
    }
    /**
     * Set the text in the east bar for scores.
     * @param text
     */
    public void setEastballs(String text) {
       ballsleft.setText(text);
        
    }
    public void setEastPowerUps(String text) {
    	powerup.setText(text);
    }
    /**
     * @return The size of the PongPanel.
     */
    public Dimension getBlockBreakersPanelSize() {
        return blockBreakerPanel.getSize();
    }

    /**
     * Translate from mouse x to model x depending on view mode.
     * @param x horizontal location of mouse
     */
    public int translateX(int x) {
        return x;
    }

    /**
     * Translate from mouse y to model y depending on view mode.
     * @param y vertical location of mouse or model
     */
    public int translateY(int y) {
        if (flipVertical) {
            Dimension size = getBlockBreakersPanelSize();
            y = size.height - y;
        }
        return y;
    }

    /**
     * @return value of flipVertical
     */
    public boolean getFlipVertical() {
        return flipVertical;
    }

    /**
     * Set value of flipVertical
     * @param b new value for flipVertical
     */
    public void setFlipVertical(boolean b) {
        flipVertical = b;
    }

    /**
     * Show popup menu if indicated.
     * @param event
     */
    public void checkForTriggerEvent(MouseEvent event) {
        if (event.isPopupTrigger()) {
            popupMenu.show(event.getComponent(), event.getX(), event.getY()); 
        }
    }
    public void showGame() {
    	remove(blockBreakerPanel);
    	remove(menu);
    	
    	add(blockBreakerPanel, BorderLayout.CENTER);
    	validate();
    	repaint();
    }
    public void showMenu() {
    	remove(blockBreakerPanel);
    	remove(menu);
    	
    	add(menu, BorderLayout.CENTER);
    	validate();
    	repaint();
    }
    
    
    
}
