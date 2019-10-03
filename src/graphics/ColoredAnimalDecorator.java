package graphics;

import animals.Animal;
/**
 * 
 * @author idan ben moshe 308118439 zohar azriav 201454899
 *
 */
public class ColoredAnimalDecorator implements ColoredAnimal {

	private Animal animal;
	
	/**
	 * C'tor
	 * @param animal animal instance.
	 */
	public ColoredAnimalDecorator(Animal animal) {
		this.animal = animal;
	}
	
	/**
	 * Paints the chosen animal for decoration by the chosen animal and color.
	 * @param col animals color.
	 */
	@Override
	public void paintAnimal(String col) {
		animal.paintAnimal(col);
		Animal an = ((Animal)animal);
		an.SetColor(col);
		String nm = null;
		switch (an.getName()) {
		case "Bear":
			nm = "bea";
			break;
		case "Elephant":
		    nm = "elf";
		    break;
		case "Giraffe":
			nm = "grf";
			break;
		case "Lion":
			nm = "lio";
		    break;
		case "Turtle":
			nm = "trt";
			break;
		}
		an.loadImages(nm);
	}

}
