package hausaufgabe9;

/**
 * Klasse der Vegetarier.
 */
public class Vegetarian extends Animal {

	/**
	 * Dem Konstruktor wird das Geschlecht des Tiers uebergeben.
	 *
	 */
	public Vegetarian(boolean female) {
		super(female);
	}

	@Override
	public void sunset() {
		Animal[] animal = this.position.getAnimals();
		for (int i = 0; i < animal.length; i++) {
			if (animal[i] != this && animal[i] instanceof Predator && animal[i].female != this.female
					&& animal[i].square.equals(this.square)) {
				this.alive = false;
				((Predator) animal[i]).setFood();
			}
		}
		if (aliveeerledigt == true)
			return;
		if (this.alive == false) {
			this.square = "";
			this.aliveeerledigt = true;
			this.position.reduceNrAnimal(this.female);
		}

	}

}
