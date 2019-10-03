package plants;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import food.EFoodType;
import food.IEdible;
import graphics.IDrawable;
import graphics.ZooPanel;
import mobility.ILocatable;
import mobility.Point;
/**
 * 
 * @author idan ben moshe 308118439 zohar azriav 201454899
 *
 */
public abstract class Plant implements IEdible, ILocatable, IDrawable {

	protected BufferedImage img;
	
	/**
	 * c'tor
	 */
	public Plant() {}
	
	/**
	 * loads the image of the selected plant.
	 * @param nm, the name of the selected plant.
	 */
	public void loadImages(String nm){
			try { 
				img = ImageIO.read(new File(PICTURE_PATH + nm + ".png"));
			}
			catch (IOException e) { System.out.println("Cannot load picture"); }
	}

	/**
	 * Draws the plant to the panel.
	 * @param g graphic context.
	 */
	public void drawObject(Graphics g) {
		g.drawImage(img, ZooPanel.getInstance().getWidth()/2 - 20,
				ZooPanel.getInstance().getHeight()/2 - 20, 40, 40, ZooPanel.getInstance());
	}
	
	public EFoodType getFoodtype() { return EFoodType.VEGETABLE; }
	public String getColor() { return "Green"; }	 
	public Point getLocation() { return null; }
	public boolean setLocation(Point location) { return false; }


}
