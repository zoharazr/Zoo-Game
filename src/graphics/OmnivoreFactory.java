package graphics;

import animals.Animal;
import animals.Bear;
/**
 * 
 * @author idan ben moshe 308118439 zohar azriav 201454899
 *
 */
public class OmnivoreFactory implements AbstractZooFactory {

	/**
	 * Creates an instance of the chosen animal.
	 * @param type animal type/name.
	 */
	@Override
	public Animal produceAnimal(String type) {
		return new Bear();
	}

}
