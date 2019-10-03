package graphics;

import java.util.Vector;
import animals.Animal;
/**
 * 
 * @author idan ben moshe 308118439 zohar azriav 201454899
 *
 */
public class ZooMemento {

	private Vector<Animal> animals;
	private String food;
	private boolean bgr;
	
	/**
	 * C'tor
	 * @param an panel animals array
	 * @param fd panel food
	 * @param bgr panel background
	 */
	public ZooMemento(Vector<Animal> an, String fd, boolean bgr) throws CloneNotSupportedException {
		animals = new Vector<Animal>();
		if(an.size() > 0)
		for(Animal animal : an)//save animals
		{
			Animal new_animal = (Animal) animal.clone();
			animals.add(new_animal);
		}
		food = fd;//save food
		this.bgr = bgr;//save background
	}
	
	/**
	 * 
	 * @return animals array
	 */
	public Vector<Animal> getList(){
		return animals;
	}
	
	/**
	 * 
	 * @return food
	 */
	public String getFood(){
		return food;
	}
	
	/**
	 * 
	 * @return background
	 */
	public boolean getBgr(){
		return bgr;
	}
}
