package graphics;
/**
 * 
 * @author idan ben moshe 308118439 zohar azriav 201454899
 *
 */
public interface IAnimalBehavior {
	
	/**
	 * The behavior of the animal.
	 */
	 abstract public String getName();
	 abstract public void setSuspend();
	 abstract public void setResume();
	 abstract public int getSize();
	 abstract public void eatInc();
	 abstract public int getEatCount();
}

