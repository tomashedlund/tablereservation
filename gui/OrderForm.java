package gui;
import javax.swing.*;
import types.*;

public class OrderForm extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9065118317391548348L;
	private JTextField name;
	private JComboBox<Food> foodList = new JComboBox<Food>();
	private JComboBox<Drink> drinkList = new JComboBox<Drink>();
	OrderForm(Order order){
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		addFoodToList();
		addDrinkToList();
		
		JPanel line1 = new JPanel();
		line1.add(new JLabel("Make a pre-order"));
		add(line1);
		
		JPanel line2 = new JPanel();
		line2.add(new JLabel("Name: "));
		name = new JTextField(10);
		line2.add(name);
		line2.add(Box.createHorizontalGlue());
		add(line2);
		
		JPanel line3 = new JPanel();
		line3.add(new JLabel("Select Food: "));
		line3.add(foodList);
		line3.add(Box.createHorizontalGlue());
		add(line3);
		
		JPanel line4 = new JPanel();
		line4.add(new JLabel("Select Drink: "));
		line4.add(drinkList);
		line4.add(Box.createHorizontalGlue());
		add(line4);
		
		name.setText(order.getName());
		int i;
		for(i = 0; i<foodList.getItemCount(); i++)
			if (foodList.getItemAt(i).toString().equals(order.getFood().toString()))
		foodList.setSelectedIndex(i);
		
		for(i = 0; i<drinkList.getItemCount(); i++)
			if (drinkList.getItemAt(i).toString().equals(order.getDrink().toString()))
		drinkList.setSelectedIndex(i);
	}
	private void addFoodToList(){
		foodList.addItem(new EmptyPlate());
		foodList.addItem(new foods.Forest());
		foodList.addItem(new foods.Marguerita());
	}
	private void addDrinkToList(){
		drinkList.addItem(new EmptyGlass());
		drinkList.addItem(new drinks.CocaCola());
		drinkList.addItem(new drinks.Sprite());
	}
	public String getOrderName(){
		return this.name.getText();
	}
	public Food getFood(){
		return (Food)foodList.getSelectedItem();
	}
	public Drink getDrink(){
		return (Drink)drinkList.getSelectedItem();
	}
}
