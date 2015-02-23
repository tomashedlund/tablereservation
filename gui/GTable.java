package gui;

import java.awt.*;

import javax.swing.*;

public class GTable extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4032008154011832807L;
	private boolean hasPlate,selected = false,reservated = false,match=true, hasFood = false;
	private int size;
	private Point possition;
	
	public GTable(int x, int y, int size, boolean hasPlate, Point possition){
		this.size = size;
		this.hasPlate = hasPlate;
		this.possition = possition;
		setBounds(x,y,size,size);
		setBackground(Color.GREEN);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		repaint();
	}
	public Point possition(){
		return possition;
	}
	public int boxSize(){
		return size;
	}
	public void select() {
		if(!reservated && match){
			selected = !selected;
			if(selected)
				setBackground(Color.BLUE);
			else
				setBackground(Color.GREEN);
			repaint();
		}
	}
	public boolean hasPlate(){
		return hasPlate;
	}
	public boolean isItReserved(){
		return reservated;
	}
	public boolean isItMatched(){
		return match;
	}
	public boolean hasFood(){
		return hasFood;
	}
	public void reserve(){
		reservated = true;
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		setBackground(Color.RED);
		repaint();
	}
	public void setFree(){
		reservated = false;
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setBackground(Color.GREEN);
		repaint();
	}
	public void matched(){
		match = true;
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setBackground(Color.GREEN);
		repaint();
	}
	public void notMatched(){
		match = false;
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		setBackground(Color.YELLOW);
		repaint();
	}
	public void setOrdered(){
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setBackground(Color.CYAN);
		hasFood = true;
		repaint();
	}
	public void setOrderedMissingName(){
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setBackground(Color.ORANGE);
		hasFood = true;
		repaint();
	}
	public void setGreen(){
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setBackground(Color.GREEN);
		hasFood = false;
		repaint();
	}
	

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (hasPlate) {
			g.setColor(Color.LIGHT_GRAY);
			g.fillOval(4, 4, size-8, size-8);
			g.setColor(Color.WHITE);
			g.fillOval(5, 5, size-10, size-10);
		}
	}
}
