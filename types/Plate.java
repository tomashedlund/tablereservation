package types;

public class Plate {
	Food food;
	
	public Plate() {
		this.food = new EmptyPlate();
	}
	public void addFood(Food food) {
		this.food = food;
	}
}
