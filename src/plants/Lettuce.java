package plants;
/**
 * 
 * @author idan ben moshe 308118439 zohar azriav 201454899
 *
 */
public class Lettuce extends Plant {
	
	private static volatile Lettuce instance = null;
	
	/**
	 * c'tor
	 */
	public Lettuce() {
		super();
		loadImages("lettuce");
	}
	
	/**
	 * 
	 * @return instance of plant
	 */
	public static Lettuce getInstance(){
        if (instance == null)
            instance = new Lettuce();
        return instance;
    }
}
