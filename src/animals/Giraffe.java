package animals;

import diet.Herbivore;
import mobility.Point;
/**
 * 
 * @author idan ben moshe 308118439 zohar azriav 201454899
 *
 */
public class Giraffe extends Animal {

	/**
	 * C'tor
	 */
	public Giraffe(){super();}

	/**
	 * c'tor
	 * @param s size of animal
	 * @param x x location of animal
	 * @param y y location of animal
	 * @param h hor speed of animal
	 * @param v ver speed of animal
	 * @param c color of animal
	 */
	public Giraffe(int s,int x, int y, int h, int v, String c) {
		 super("Giraffe",s*4/3,s*2,h,v,c);
		 setLocation(new Point(0,0));
		 setDiet(new Herbivore());
		 loadImages("grf");
	 }

	/**
	 * set animal details
	 */
	@Override
	public void setDetails(int x, int y) {
		setLocation(new Point(0,0));
		setDiet(new Herbivore());
		loadImages("grf");
		setWeight(size*2);
	}
}
