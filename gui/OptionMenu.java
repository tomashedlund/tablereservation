package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;

public class OptionMenu extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 134527328306666687L;
	private Set<Reservation> allReservations;
	private List<Integer> availableTimes = new ArrayList<Integer>();
	private List<Integer> availableTables = new ArrayList<Integer>();
	private TableMap tableMap;
	private JComboBox timeList, tableList;
	private JButton reserve;
	private JLabel overviewLabel1, overviewLabel2;
	
	OptionMenu(TableMap tableMap, JButton reserve) {
		this.reserve = reserve;
		reserve.addActionListener(new ButtonListener());
		this.tableMap = tableMap;
		allReservations = tableMap.getReservationList();
		for(Reservation r : allReservations) {
			if(!availableTimes.contains(r.time()))
				availableTimes.add(r.time());
			if(!availableTables.contains(r.table().numberOfPlates()))
				availableTables.add(r.table().numberOfPlates());
		}
		Collections.sort(availableTimes);
		Collections.sort(availableTables);
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JComboBox<String> dateList = new JComboBox<String>();
		dateList.addItem("Today");
		dateList.setSelectedIndex(0);
		JLabel dateLabel = new JLabel("Date:");
		dateLabel.setAlignmentX(CENTER_ALIGNMENT);
		add(dateLabel);
		add(dateList);
		
		timeList = new JComboBox(availableTimes.toArray());
		timeList.setSelectedIndex(0);
		timeList.addActionListener(new TimeListlistener());
		JLabel timeLabel = new JLabel("Time:");
		timeLabel.setAlignmentX(CENTER_ALIGNMENT);
		add(timeLabel);
		add(timeList);
		
		tableList = new JComboBox(availableTables.toArray());
		tableList.setSelectedIndex(0);
		tableList.addActionListener(new TableListlistener());
		JLabel tableLabel = new JLabel("Guests:");
		tableLabel.setAlignmentX(CENTER_ALIGNMENT);
		add(tableLabel);
		add(tableList);
		
		int time = (int)timeList.getSelectedItem();
        int numberOfPlates = (int)tableList.getSelectedItem();
        tableMap.selectTables(numberOfPlates, time);
        
        int tableCounter = tableCounter(numberOfPlates);
        
        overviewLabel1 = new JLabel(numberOfPlates + "-seats tables left:");
        overviewLabel2 = new JLabel("" + tableCounter);
        overviewLabel1.setAlignmentX(CENTER_ALIGNMENT);
        overviewLabel2.setAlignmentX(CENTER_ALIGNMENT);
		add(overviewLabel1);
		add(overviewLabel2);
		
        add(Box.createRigidArea(new Dimension(0, 50)));
        
		reserve.setEnabled(false);
		reserve.setAlignmentX(CENTER_ALIGNMENT);
		add(reserve);
		
		add(Box.createRigidArea(new Dimension(0, 450)));
		
	}
	private int tableCounter(int numberOfPlates){
		int tableCounter = 0;
        for(Reservation r : allReservations)
        	if(!r.isItReserved() && r.table().numberOfPlates() == numberOfPlates)
        		tableCounter++;
        return tableCounter;
	}
	private class TableListlistener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
	        JComboBox cb = (JComboBox)e.getSource();
	        int time = (int)timeList.getSelectedItem();
	        int numberOfPlates = (int)cb.getSelectedItem();
	        tableMap.selectTables(numberOfPlates, time);
	        
	        int tableCounter = tableCounter(numberOfPlates);
	        overviewLabel1.setText(numberOfPlates + "-seats tables left:");
	        overviewLabel2.setText("" + tableCounter);
	    }
	}
	private class TimeListlistener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
	        JComboBox cb = (JComboBox)e.getSource();
	        int time = (int)cb.getSelectedItem();
	        int numberOfPlates = (int)tableList.getSelectedItem();
	        tableMap.selectTables(numberOfPlates, time);
	        
	        int tableCounter = tableCounter(numberOfPlates);
	        overviewLabel1.setText(numberOfPlates + "-seats tables left:");
	        overviewLabel2.setText("" + tableCounter);
	    }
	}
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) { 
			int tableCounter = tableCounter((int)tableList.getSelectedItem())-1;
	        overviewLabel1.setText((int)tableList.getSelectedItem() + "-seats tables left:");
	        overviewLabel2.setText("" + tableCounter);
		}
	}
}
