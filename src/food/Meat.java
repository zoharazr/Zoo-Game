package food;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import graphics.IDrawable;
import graphics.ZooPanel;
import mobility.ILocatable;
import mobility.Point;
/**
 * 
 * @author idan ben moshe 308118439 zohar azriav 201454899
 *
 */
public class Meat implements IEdible,ILocatable,IDrawable
{
	private static volatile Meat instance = null;
	protected BufferedImage img;

	/**
	 * Meat constructor.
	 */
	public Meat()
	{
		loadImages("meat");
	}
	
	/**
	 * @return instance of the Meat class.
	 */
	public static Meat getInstance(){
        if (instance == null)
            instance = new Meat();
        return instance;
    }
	
	/**
	 * loads the image of the selected meat.
	 * @param nm, the name of the selected meat.
	 */
	public void loadImages(String nm){
		try { 
			img = ImageIO.read(new File(PICTURE_PATH + nm + ".gif"));
		}
		catch (IOException e) { System.out.println("Cannot load picture"); }
    }

	/**
	 * Draws the meat to the panel.
	 * @param g graphic context.
	 */
	public void drawObject(Graphics g) {
		g.drawImage(img, ZooPanel.getInstance().getWidth()/2 - 20,
				ZooPanel.getInstance().getHeight()/2 - 20, 40, 40, ZooPanel.getInstance());
	}

    public EFoodType getFoodtype() { return EFoodType.MEAT; }
    public String getColor() { return "Brown"; }	 
    public Point getLocation() { return null; }
    public boolean setLocation(Point location) { return false; }
}
