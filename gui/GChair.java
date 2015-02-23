package gui;

import java.awt.*;

import javax.swing.*;

public class GChair extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4032008154011832807L;
	private int size;
	
	public GChair(int x, int y, int size){
		this.size = size;
		setBounds(x,y,size,size);
		setOpaque(false);
		repaint();
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.ORANGE);
		g.fillOval(0, 0, size, size);
		g.setColor(Color.YELLOW);
		g.fillOval(1, 1, size-2, size-2);
	}
//	@Override
//	public boolean contains(int x, int y){
//		int r = size/2;
//		x-=r;
//		y-=r;
//		return (x*x)+(y*y) <= r*r && x*x <= r*r && y*y <= r*r;
//	}
}