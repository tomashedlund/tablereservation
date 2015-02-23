package gui;

import java.awt.*;
import javax.swing.*;

public class GBench extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4032008154011832807L;
	private int size;
	
	public GBench(int x, int y, int size){
		this.size = size;
		setBounds(x,y,size,size);
		setBackground(Color.ORANGE);
		repaint();
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.YELLOW);
		g.fillRect(1, 1, size-2, size-2);
	}
}
