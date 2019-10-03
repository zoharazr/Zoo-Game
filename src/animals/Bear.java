package animals;

import diet.Omnivore;
import mobility.Point;
/**
 * 
 * @author idan ben moshe 308118439 zohar azriav 201454899
 *
 */
public class Bear extends Animal {

	/**
	 * C'tor
	 */
	public Bear(){super();}
	
	/**
	 * c'tor
	 * @param s size of animal
	 * @param x x location of animal
	 * @param y y location of animal
	 * @param h hor speed of animal
	 * @param v ver speed of animal
	 * @param c color of animal
	 */
	public Bear(int s,int x, int y, int h, int v, String c) {
		 super("Bear",(int)(s*0.7),s,h,v,c);
		 setLocation(new Point(x,y));
		 setDiet(new Omnivore());
		 loadImages("bea");
	 }

	/**
	 * set animal details
	 */
	@Override
	public void setDetails(int x, int y){
	    setLocation(new Point(x,y));
		setDiet(new Omnivore());
		loadImages("bea");
		setWeight((int)(size*0.7));
	}
}
