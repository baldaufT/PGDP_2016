package pgdp9;

import java.util.LinkedList;

public class FruitBasket {

	private LinkedList<Fruit> fruits;

	public FruitBasket() {
		fruits = new LinkedList<Fruit>();
	}

	public void addFruit(Fruit f) {
		fruits.add(f);
	}

	public LinkedList<Apple> getApple() {
		LinkedList<Apple> l = new LinkedList<Apple>();
		for (Fruit f : fruits) {
			if (f instanceof Apple) {
				l.add((Apple) f);
			}
		}
		return l;
	}

	public LinkedList<Fruit> getEqualOrLongerShelfLife(int n) {
		LinkedList<Fruit> l = new LinkedList<Fruit>();
		for (Fruit f : fruits) {
			if (f.shelfLife() >= n) {
				l.add(f);
			}
		}
		return l;
	}
}
