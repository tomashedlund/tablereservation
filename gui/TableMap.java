package gui;

import methods.*;
import types.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Map.Entry;

import javax.swing.*;

public class TableMap extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3867802155979694115L;
	private int blockSize = 20, time,plates;
	private Map<Point,Object> map;
	private Map<Table,Set<Point>> allTables = new HashMap<Table,Set<Point>>();
	private JPanel display = new JPanel();
	private boolean hasSelected = false;
	private Table selectedTable = null;
	private Map<Table,Set<Reservation>> allReservations = new HashMap<Table, Set<Reservation>>();
	private JButton reserve;
	private char[][] output;
	
	public TableMap(JButton reserve) {
		this.reserve = reserve;
		reserve.addActionListener(new ButtonListener());
		setLayout(new BorderLayout());
		display.setLayout(null);
		display.setBackground(Color.WHITE);
		
		output = Methods.textFileToCharArray("map.txt");
		this.map = Methods.charArrayToAnTableMap(output, display, blockSize);
		Methods.buildTables(map, allTables);
		
		TableListener tableListener = new TableListener();
		for(Entry<Point, Object> e : map.entrySet())
			if(e.getValue() instanceof GTable)
				((GTable)e.getValue()).addMouseListener(tableListener);
		
		createScenario();
		
		add(display);
	}
	public Set<Reservation> getReservationList(){
		Set<Reservation> reservationSet = new HashSet<Reservation>();
		for(Entry<Table, Set<Reservation>> e : allReservations.entrySet())
			reservationSet.addAll(e.getValue());
		return reservationSet;
	}
	public void selectTables(int numberOfPlates, int time){
		this.time = time;
		this.plates = numberOfPlates;
		if(hasSelected){ 
			for(Point p : allTables.get(selectedTable))
				((GTable)map.get(p)).select();
			hasSelected = false;
			selectedTable = null;
			reserve.setEnabled(false);
		}
		
		for(Entry<Table, Set<Point>> e : allTables.entrySet()) {
			if(e.getKey().numberOfPlates() == numberOfPlates)
				for(Point p : allTables.get(e.getKey())){
					((GTable)map.get(p)).matched();
					for(Reservation r : allReservations.get(e.getKey())){
						if(r.time() == time && r.isItReserved())
							((GTable)map.get(p)).reserve();
						else if (r.time() == time && !r.isItReserved())
							((GTable)map.get(p)).setFree();
					}
				}
			else
				for(Point p : allTables.get(e.getKey()))
					((GTable)map.get(p)).notMatched();
			repaint();
		}
	}
	
	private class TableListener extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent mev){
			GTable selected = (GTable) mev.getSource();
			if (selected.isItReserved() || !selected.isItMatched())
				return;
			if(hasSelected && !allTables.get(selectedTable).contains(selected.possition())){ 
				for(Point p : allTables.get(selectedTable))
					((GTable)map.get(p)).select();
				hasSelected = !hasSelected;
				selectedTable = null;
				reserve.setEnabled(false);
			}
			for (Entry<Table, Set<Point>> e : allTables.entrySet())
				if(e.getValue().contains(selected.possition())){
					hasSelected = !hasSelected;
					reserve.setEnabled(true);
					for(Point p : e.getValue())
						((GTable)map.get(p)).select();
					if(hasSelected){
						reserve.setEnabled(true);
						selectedTable = e.getKey();
					}
					else {
						reserve.setEnabled(false);
						selectedTable = null;
					}
					return;
				}
		}
	}

	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) { 
			for(Reservation r : allReservations.get(selectedTable))
				if (r.time() == time){
					String number = JOptionPane.showInputDialog("In which number?");
					if (number == null || number.equals(""))
						return;
					r.reserve(number);
					
					new ConfirmedTable(output, allTables.get(selectedTable), blockSize);
				}
			selectTables(plates,time);
		}
	}
	
	private void createScenario(){
		Random rand = new Random();
		int randomNum;
		for(Table t : allTables.keySet()){
			Set<Reservation> reservationSet = new HashSet<Reservation>();
			allReservations.put(t,reservationSet);
			for(int i = 18; i <= 20; i++){
				Reservation r = new Reservation(t, i);
				reservationSet.add(r);
				randomNum = rand.nextInt(4);
				if(randomNum == 3)
					r.reserve("000");
			}
		}
	}
}
