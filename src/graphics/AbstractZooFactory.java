package graphics;

import animals.Animal;
/**
 * 
 * @author idan ben moshe 308118439 zohar azriav 201454899
 *
 */
public interface AbstractZooFactory {
	
	/**
	 * 
	 * @param type 
	 * @return Animal
	 */
	public Animal produceAnimal(String type);
	
	/**
	 * 
	 * @param foodType
	 * @return factory according to the foodType parameter.
	 */
	public static AbstractZooFactory createAnimalFactory(String foodtype){
		AbstractZooFactory factory = null;
		switch (foodtype) {
		case "Plant": // Herbivore
			factory = new HerbivoreFactory();
			break;
        case "Mix": // Omnivore
        	factory = new OmnivoreFactory();
        	break;
		case "Meat": // Carnivore
			factory = new CarnivoreFactory();
			break;
		}
		return factory;
	}
}
