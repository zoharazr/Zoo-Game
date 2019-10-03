package animals;

import diet.Herbivore;
import mobility.Point;
/**
 * 
 * @author idan ben moshe 308118439 zohar azriav 201454899
 *
 */
public class Elephant extends Animal {


	/**
	 * C'tor
	 */
	public Elephant(){super();}
	
	/**
	 * c'tor
	 * @param s size of animal
	 * @param x x location of animal
	 * @param y y location of animal
	 * @param h hor speed of animal
	 * @param v ver speed of animal
	 * @param c color of animal
	 */
	public Elephant(int s,int x, int y, int h, int v, String c) {
		 super("Elephant",s*4/3,s*2,h,v,c);
		 setLocation(new Point(0,0));
		 setDiet(new Herbivore());
		 loadImages("elf");
	 }

	/**
	 * set animal details
	 */
	@Override
	public void setDetails(int x, int y) {
		setLocation(new Point(x,y));
		setDiet(new Herbivore());
		loadImages("elf");
		setWeight(size*2);
	}
	 
}
