package gui;

import types.*;

public class Reservation {
	private Table table;
	private int time;
	private boolean reserved;
	private String number = "";
	
	Reservation(Table table, int time) {
		this.table = table;
		this.time = time;
		
	}
	public boolean isItReserved(){
		return reserved;
	}
	public void reserve(String number){
		reserved = true;
		this.number = number;
	}
	public String whatNumber(){
		return number;
	}
	public void setFree(){
		reserved = false;
	}
	public Table table(){
		return table;
	}
	public int time(){
		return time;
	}
}
