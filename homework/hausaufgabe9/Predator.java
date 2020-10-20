package hausaufgabe9;

/**
 * Klasse der Raubtiere.
 */
public class Predator extends Animal {

	protected int withoutFood;
	protected int startwert;

	/**
	 * Dem Konstruktor wird das Geschlecht des Tiers uebergeben.
	 *
	 */
	public Predator(boolean female) {
		super(female);
	}

	public void setFood() {
		withoutFood = startwert;
	}

	@Override
	public void sunset() {
		if (aliveeerledigt == true)
			return;

		withoutFood--;
		if (withoutFood < 0)
			this.alive = false;
		if (this.alive == false) {
			this.square = "";
			this.aliveeerledigt = true;
			this.position.reduceNrAnimal(this.female);
		}
	}

}
