package animals;

import diet.Carnivore;
import mobility.Point;
/**
 * 
 * @author idan ben moshe 308118439 zohar azriav 201454899
 *
 */
public class Lion extends Animal {

	/**
	 * C'tor
	 */
    public Lion(){super();}
	
    /**
	 * c'tor
	 * @param s size of animal
	 * @param x x location of animal
	 * @param y y location of animal
	 * @param h hor speed of animal
	 * @param v ver speed of animal
	 * @param c color of animal
	 */
	public Lion(int s,int x, int y, int h, int v, String c) {
		super("Lion",(int)(s*0.745),(int)(s*0.8),h,v,c);
		 setLocation(new Point(x,y));
		 setDiet(new Carnivore());
		 loadImages("lio");
	}
	
	/**
	 * set animal details
	 */
	@Override
	public void setDetails(int x, int y)
	{
		setLocation(new Point(x,y));
		setDiet(new Carnivore());
		loadImages("lio");
		setWeight((int)(size*0.8));
	}
}
