package types;

public class Order {
	private String name;
	private Food food;
	private Drink drink;
	
	public Order(String name, Food food, Drink drink){
		this.name = name;
		this.food = food;
		this.drink = drink;
	}
	public Order(){
		this.name = "";
		this.food = new EmptyPlate();
		this.drink = new EmptyGlass();
	}
	public String getName(){
		return name;
	}
	public Food getFood(){
		return food;
	}
	public Drink getDrink(){
		return drink;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setFood(Food food) {
		this.food = food;
	}
	public void setDrink(Drink drink) {
		this.drink = drink;
	}
}
