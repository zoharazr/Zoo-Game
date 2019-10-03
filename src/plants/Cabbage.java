package plants;
/**
 * 
 * @author idan ben moshe 308118439 zohar azriav 201454899
 *
 */
public class Cabbage extends Plant {
	
	private static volatile Cabbage instance = null;
	
	/**
	 * c'tor
	 */
	public Cabbage() {
		super();
		loadImages("cabbage");
	}
	
	/**
	 * 
	 * @return instance of plant
	 */
	public static Cabbage getInstance(){
        if (instance == null)   
            instance = new Cabbage();
        return instance;
    }
}
