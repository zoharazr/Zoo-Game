package animals;

import diet.Herbivore;
import mobility.Point;
/**
 * 
 * @author idan ben moshe 308118439 zohar azriav 201454899
 *
 */
public class Turtle extends Animal {

	/**
	 * C'tor
	 */
	public Turtle(){super();}
	
	/**
	 * c'tor
	 * @param s size of animal
	 * @param x x location of animal
	 * @param y y location of animal
	 * @param h hor speed of animal
	 * @param v ver speed of animal
	 * @param c color of animal
	 */
	public Turtle(int s,int x, int y, int h, int v, String c) {
		 super("Turtle",s/2,s/2,h,v,c);
		 setLocation(new Point(x,y));
		 setDiet(new Herbivore());
		 loadImages("trt");
	 }

	/**
	 * set animal details
	 */
	@Override
	public void setDetails(int x, int y) {
		setLocation(new Point(x,y));
		setDiet(new Herbivore());
		loadImages("trt");
		setWeight(size/2);
	}
}
