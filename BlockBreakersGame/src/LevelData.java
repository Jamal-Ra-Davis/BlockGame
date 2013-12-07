

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

public class LevelData {
	public static int NUMBER_OF_LEVELS = 5;
	private ArrayList<BlockModel> blocks;
	private ArrayList<ForceObject> forces;
    private ForceObject attract;
	
	LevelData(int ID, int width, int height) {
		blocks = new ArrayList<BlockModel>();
		forces = new ArrayList<ForceObject>();
		
		double blockSpace_w;
        double blockSpace_h;
        double blockHeight, blockWidth;
        int blockRows, blockCols;
       
        
        double x_start, y_start;
		switch(ID) {
			case 1: {
				//BlockModel block = new BlockModel(width*0.5, height*0.5, 40, 30, Color.GREEN, -1, 1);
				//blocks.add(block);
				Color[] blockColors = new Color[]{Color.BLUE, Color.RED, Color.GREEN, Color.PINK, Color.GRAY, Color.MAGENTA};
				
				blockSpace_w = width*0.7;
				blockSpace_h = height*0.35;
				
				blockRows = 4;
				blockCols = 10;
				
				blockWidth = blockSpace_w/blockCols;
		        blockHeight = blockSpace_h/blockRows;
				
		        x_start = width*.15;
		        y_start = 3*height/6.0;
		        for (int i=0; i<blockRows; i++) {
	        		for (int j=0; j<blockCols; j++) {
	        			int k = j+ blockCols*i;
	        			
	        			int blockType = 1;
	        			int temp = i;
	        			if (temp >= blockRows/2.0)
	        				blockType++;
	        			if (i >= blockRows/4.0)
	        				blockType++;
	        			
	        			Color tempColor = blockColors[k%6];
	        			blocks.add(new BlockModel(x_start+j*blockWidth, y_start+i*blockHeight,
	        					   (int)blockWidth, (int)blockHeight, tempColor, k, blockType));
	        		}
		        }
		        
		        
		        
		        
				break;
			}
			case 2: {
				blockSpace_w = width;
				blockSpace_h = height*0.6;
				blockRows = 6;
				blockCols = 8;
				blockWidth = blockSpace_w/blockCols;
				blockHeight = blockSpace_h/blockRows;
				
				x_start = 0;
				y_start = height*0.4;
				for (int i=0; i<blockCols; i++) {
					BlockModel block = new BlockModel(x_start+i*blockWidth, y_start, (int)blockWidth,
													  (int)blockHeight, Color.BLUE, -1, 3);
					blocks.add(block);
				}
				x_start = blockWidth;
				y_start = height*0.4 + 2*blockHeight;
				for (int i=0; i<2; i++) {
					for (int j=0; j<2; j++) {
						BlockModel block = new BlockModel(x_start+j*blockWidth, y_start+i*blockHeight,
														  (int)blockWidth, (int)blockHeight, Color.RED, -1, 1);
						blocks.add(block);
					}
				}
				x_start = blockWidth*5;
				y_start = height*0.4 + 2*blockHeight;
				for (int i=0; i<2; i++) {
					for (int j=0; j<2; j++) {
						BlockModel block = new BlockModel(x_start+j*blockWidth, y_start+i*blockHeight,
														  (int)blockWidth, (int)blockHeight, Color.PINK, -1, 1);
						blocks.add(block);
					}
				}
				x_start = 0;
				y_start = height*0.4 + 5*blockHeight;
				for (int i=0; i<blockCols; i++) {
					BlockModel block = new BlockModel(x_start+i*blockWidth, y_start, (int)blockWidth,
													  (int)blockHeight, Color.GREEN, -1, 2);
					blocks.add(block);
				}
				break;
			}
			case 3:{
				 
		        blockSpace_w = width*(.70);
		        blockSpace_h = height*(.35);
		        blockRows = 10;
		        blockCols = 10;
		        
		        blockWidth = blockSpace_w/blockCols;
		        blockHeight = blockSpace_h/blockRows;
		        
		        x_start = 0; //width/8.0;
		        y_start = 3*height/6.0;
		        
		        attract = new Attractor(new Vector2d(width*0.5, height*0.25), 2500);
		        forces.add(attract);
		        forces.add(new Repeller(new Vector2d(-5, height*0.25), 1500));
		        forces.add(new Repeller(new Vector2d(width-5, height*0.25), 1500));
		        for (int i=0; i<14; i+=2) {
		        	BlockModel block = new BlockModel(x_start+i*blockWidth, y_start, (int)blockWidth, (int)blockHeight, Color.BLUE, -1, 3);
                    blocks.add(block);
		        } 		      
		        
		        x_start = 50; //width/8.0;
		        y_start = 3*height/5.0;
		        
		        for (int i=0; i<14; i+=2) {
		        	BlockModel block = new BlockModel(x_start+i*blockWidth, y_start, (int)blockWidth, (int)blockHeight, Color.RED, -1, 3);
                    blocks.add(block);
		        } 	
		        
		        x_start = 0; //width/8.0;
		        y_start = 3*height/4.0;
		        
		        for (int i=0; i<14; i+=2) {
		        	BlockModel block = new BlockModel(x_start+i*blockWidth, y_start, (int)blockWidth, (int)blockHeight, Color.BLUE, -1, 3);
                    blocks.add(block);
		        } 	
		        
		        x_start = 50; //width/8.0;
		        y_start = 3*height/3.5;
		        
		        for (int i=0; i<14; i+=2) {
		        	BlockModel block = new BlockModel(x_start+i*blockWidth, y_start, (int)blockWidth, (int)blockHeight, Color.RED, -1, 3);
                    blocks.add(block);
		        } 	
				break;
		       }
			case 4:{
				blockSpace_w = width*(.70);
		        blockSpace_h = height*(.55);
		        blockRows = 10;
		        blockCols = 10;
		        
		        blockWidth = blockSpace_w/blockCols;
		        blockHeight = blockSpace_h/blockRows;
		        
		        x_start = 10;
		        y_start = height*(.70);
		        
		        for (int i=0; i<14; i++) {
		        	BlockModel block = new BlockModel(x_start+i*blockWidth, y_start, 
		        			(int)blockWidth, (int)blockHeight, Color.BLUE, -1, 2);
                    blocks.add(block);
		        } 		      
		        
		        x_start = 10; 
		        y_start = height*(.70) + blockHeight;
		        
		        
		        for (int i=0; i<14; i++) {
		        	BlockModel block = new BlockModel(x_start+i*blockWidth, y_start, 
		        			(int)blockWidth, (int)blockHeight, Color.GREEN, -1, 2);
                    blocks.add(block);
		        } 	
		        
		        x_start = 10; 
		        y_start = height*(.70) + 2*blockHeight;
		        
		        for (int i=0; i<14; i++) {
		        	BlockModel block = new BlockModel(x_start+i*blockWidth, y_start, 
		        			(int)blockWidth, (int)blockHeight, Color.RED, -1, 2);
                    blocks.add(block);
		        } 	
		        
		        x_start = 10; 
		        y_start = height*(.70) + 3*blockHeight;
		        
		        for (int i=0; i<14; i++) {
		        	BlockModel block = new BlockModel(x_start+i*blockWidth, y_start, 
		        			(int)blockWidth, (int)blockHeight, Color.ORANGE, -1, 2);
                    blocks.add(block);
		        } 	
				break;
			}
			default:{
				System.out.println("you beat the game");
			}
					
		}
	}
	public ArrayList<BlockModel> getBlockList() {
		return blocks;
	}
	public ArrayList<ForceObject> getForceList() {
		return forces;
	}
	public int getBlockCount() {
		return blocks.size();
	}
	public int getForceCount() {
		return forces.size();
	}
	public void resetLevelData(){
		blocks.clear();
		attract = null;
		forces.clear();
	}
	
	public static LevelData makeLevel(int ID, int width, int height) {
		if (ID <= 0)
			return null;
		
		return (new LevelData(ID, width, height));
	}
}
