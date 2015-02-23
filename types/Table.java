package types;

public class Table implements Comparable<Table> {
	private static int totalNumber;
	private int id = totalNumber;
	private int numberOfPlates = 0;
	
	public Table(int numberOfPlates){
		totalNumber++;
		this.numberOfPlates = numberOfPlates;
	}
	public int id(){
		return id;
	}
	public int numberOfPlates(){
		return numberOfPlates;
	}
	@Override 
	public String toString(){
		return id + " (" + numberOfPlates + ")";
	}
	public int compareTo(Table object) {
		if (this.numberOfPlates != object.numberOfPlates)
			return this.numberOfPlates - object.numberOfPlates;
		else 
			return this.id - object.id;
	}
}
