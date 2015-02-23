package gui;

import java.awt.BorderLayout;
import javax.swing.*;
public class Window extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9212399885368612884L;
	
	public Window(){
		JButton reserve = new JButton("Reserve");
		TableMap tableMap = new TableMap(reserve);
		OptionMenu optionMenu = new OptionMenu(tableMap, reserve);
		
		setLayout(new BorderLayout());
		add(tableMap, BorderLayout.CENTER);
		add(optionMenu, BorderLayout.WEST);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		repaint();
		validate();
		pack();
		setSize(600,500);
		setVisible(true);
	}
	public static void main(String[] args) {
		new Window();
	}
}
