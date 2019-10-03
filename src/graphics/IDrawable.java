package graphics;

import java.awt.Graphics;
/**
 * 
 * @author idan ben moshe 308118439 zohar azriav 201454899
 *
 */
public interface IDrawable {
	
	/**
	 * The path of the images.
	 */
	 public final static String PICTURE_PATH = "C:\\Users\\מאיה\\Desktop\\pictures\\";
	 
	 /**
	  * 
	  * @param nm the file name of the picture.
	  */
	 public void loadImages(String nm);
	 
	 /**
	  * draw the image on the panel.
	  * @param g
	  */
	 public void drawObject(Graphics g);
	 
	 /**
	  * get the color of the image.
	  * @return the color of the image.
	  */
	 public String getColor();	 
}

