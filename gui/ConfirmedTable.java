package gui;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import javax.swing.*;
import types.Order;
import methods.Methods;


public class ConfirmedTable extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1356431550505331944L;
	private JPanel display = new JPanel();
	private Map<Point, Order> orders = new HashMap<Point, Order>();
	
	ConfirmedTable(char[][] charMap, Set<Point> table, int blockSize) {
		setLayout(null);
		display.setLayout(null);
		
		char[][] thisTable = new char[100][100];
		
		int minX = -1, minY = -1, maxX = 0, maxY = 0;
		for (Point p : table) {
			if (p.x < minX || minX < 0)
				minX = p.x;
			if (p.x > maxX)
				maxX = p.x;
			if (p.y < minY || minY < 0)
				minY = p.y;
			if (p.y > maxY)
				maxY = p.y;
		}
		for (Point p : table)
			thisTable[p.y-minY][p.x-minX] = charMap[p.y][p.x];
		
		Map<Point,Object> map = Methods.charArrayToAnTableMap(thisTable, display, blockSize);
		GTable selected;
		for (Map.Entry<Point,Object> e : map.entrySet()){
			selected = ((GTable)e.getValue());
			if (selected.hasPlate())
				selected.addMouseListener(new PlateListener());
		}
		
		add(display);
		display.setBounds(2*blockSize, 2*blockSize, 8*blockSize, 8*blockSize);
		
		setSize(10*blockSize,10*blockSize);
		setVisible(true);
	}
	
	private class PlateListener extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent mev){
			GTable selected = (GTable) mev.getSource();
			if (orders.get(selected.possition()) == null)
				orders.put(selected.possition(), new Order());
			
			Order thisOrder = orders.get(selected.possition());
			Object[] options = {"OK","AVBRYT"};
			OrderForm dialog = new OrderForm(thisOrder);
			int answere = JOptionPane.showOptionDialog(null,dialog,"Selecte order",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
			if (answere != JOptionPane.OK_OPTION)
				return;
			
			thisOrder.setName(dialog.getOrderName());
			thisOrder.setFood(dialog.getFood());
			thisOrder.setDrink(dialog.getDrink());
			
			if(!thisOrder.getName().equals(""))
				selected.setOrdered();
			else if(!(thisOrder.getFood() instanceof types.EmptyPlate) || !(thisOrder.getDrink() instanceof types.EmptyGlass))
				selected.setOrderedMissingName();
			else
				selected.setGreen();
		}
	}
}
