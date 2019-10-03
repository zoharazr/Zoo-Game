package graphics;

import animals.Animal;
import animals.Elephant;
import animals.Giraffe;
import animals.Turtle;
/**
 * 
 * @author idan ben moshe 308118439 zohar azriav 201454899
 *
 */
public class HerbivoreFactory implements AbstractZooFactory {

	/**
	 * Creates an instance of the chosen animal.
	 * @param type animal type/name.
	 */
	@Override
	public Animal produceAnimal(String type) {
		Animal animal = null;
		switch (type) {
		case "Turtle": 
			animal = new Turtle();
			break;
		case "Giraffe":
			animal = new Giraffe();
			break;
		case "Elephant":
			animal = new Elephant();
			break;
		}
		return animal;
	}

}
