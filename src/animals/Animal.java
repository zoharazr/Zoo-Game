package animals;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.concurrent.Future;
import javax.imageio.ImageIO;
import diet.IDiet;
import food.EFoodType;
import food.IEdible;
import graphics.ColoredAnimal;
import graphics.IAnimalBehavior;
import graphics.IDrawable;
import graphics.ZooPanel;
import mobility.ILocatable;
import mobility.Point;
/**
 * 
 * @author idan ben moshe 308118439 zohar azriav 201454899
 *
 */
public abstract class Animal extends Observable implements ILocatable,IEdible,IDrawable,IAnimalBehavior,Runnable,ColoredAnimal,Cloneable {

	protected final int EAT_DISTANCE = 5;
	private IDiet diet;
	protected String name;
	protected Point location;
	private double weight;
	protected int size;
	protected String col;
	protected int horSpeed;
	protected int verSpeed;
	protected boolean coordChanged;
	protected Thread thread;
	protected int x_dir;
	protected int y_dir;
	protected int eatCount;
	protected boolean threadSuspended;	 
	protected BufferedImage img1, img2;
	protected Future<?> task;
	protected boolean isRun;
	/**
	 * c'tor
	 */
	public Animal() {
		location = new Point(0, 0);
		x_dir = 1;
		y_dir = 1;
		isRun = false;
		threadSuspended = false;
	}
	/**
	 * c'tor
	 * @param nm The name of the animal
	 * @param sz The size of the animal
	 * @param w the weight of animal
	 * @param hor Horizontal speed
	 * @param ver Vertical speed
	 * @param c The color of the animal
	 */
	public Animal(String nm, int sz, int w, int hor, int ver, String c) {
		location = new Point(0, 0);
		name = new String(nm);
		size = sz;
		weight = w;
		horSpeed = hor;
		verSpeed = ver;
		col = c;
		x_dir = 1;
		y_dir = 1;
		isRun = false;
		threadSuspended = false;
	}	

	/**
	 * Clones the current instance of the animal and returns his clone
	 * @return clone animals clone
	 */
	public Animal clone() throws CloneNotSupportedException{
		return (Animal)super.clone();
	}
	
	/**
	 * @return the location of the animal
	 */
	@Override
	public Point getLocation() {
		return location;
	}

	/**
	 * Sets the location of the animal
	 * @param location animals location
	 */
	@Override
	public boolean setLocation(Point location) {
		this.location = location;
		return true;
	}
	
	public void setIsRun(boolean state)
	{
		isRun = state;
	}
	
	/**
	 * @return The food type.
	 */
	public EFoodType getFoodtype() { return EFoodType.MEAT;	}	
	
	/**
	 * @return the diet of the animal
	 */
	public IDiet getDiet() { return diet; }
	
	/**
	 * @return The name of the animal.
	 */
	public String getName() { return this.name;	}
	
	/**
	 * @return the weight of the animal
	 */
	public double getWeight() {	return this.weight;	}
	
	/**
	 * Sets the weight
	 * @param w the weight of the animal
	 */
	public void setWeight(double w) { weight = w; }
	
	/**
	 * Sets the diet of the animal.
	 * @param diet
	 */
	protected void setDiet(IDiet diet) { this.diet = diet;}
	
	/**
	 * @return the size of the animal.
	 */
	public int getSize() { return size; }
	
	/**
	 * Sets the size of animal
	 * @param size the size of animal
	 */
	public void setSize(int size) {this.size = size;}
	
	/**
	 * @return the horizontal speed
	 */
	public int getHorSpeed() { return horSpeed; }
	
	/**
	 * Sets the horizontal speed
	 * @param hor horizontal speed
	 */
	public void setHorSpeed(int hor) { horSpeed  = hor; }
	
	/**
	 * @return the vertical speed
	 */
	public int getVerSpeed() { return verSpeed; }
	
	/**
	 * Sets the vertical speed
	 * @param hor vertical speed
	 */
	public void setVerSpeed(int ver) { verSpeed  = ver; }
	
	/**
	 * Increase the eat count
	 */
	public void eatInc() { eatCount++; }
	
	/**
	 * @return the eat count
	 */
	public int getEatCount() { return eatCount; }
	
	/**
	 * Suspends the thread
	 */
	synchronized public void setSuspend() { threadSuspended = true; }
	
	/**
	 * Wakes up suspended threads 
	 */
	synchronized public void setResume() { threadSuspended = false; notify(); }
	
	/**
	 * Sets the coordChange state of the animal
	 * @param state state of the coordChanged of the animal
	 */
	synchronized public void setChange(){ this.clearChanged(); }
	
	/**
	 * @return the color of the animal
	 */
	public String getColor() { return col; }
	public void setTask(Future<?> task){this.task = task; }
	
	/**
	 * @return true if animal is running else return false
	 */
	public boolean isRunning() { return isRun; }
	
	/**
	 * Sets the color of the animal
	 * @param col color
	 */
	public void SetColor(String color){this.col = color;}
	
	/**
	 * Sets the color of the animal after decoration
	 * @param col animals color
	 */
	@Override
	public void paintAnimal(String col) {SetColor(col);}
	
	/**
	 * Sets the animal data after it is created.
	 * @param name The name of the animal
	 * @param sz The size of the animal
	 * @param hor Horizontal speed
	 * @param ver Vertical speed
	 * @param c The color of the animal
	 */
	public void setAnimalDetails(String name, int sz, int hor, int ver, String c)
	{
		this.name = new String(name);
		size= sz;
		horSpeed = hor;
		verSpeed = ver;
		col = c;
		this.setDetails(0,0);
	}

	/**
	 * Sets each animal details.
	 * @param x x location of animal
	 * @param y y location of animal
	 */
	public abstract void setDetails(int x, int y);
	
	
	/**
	 * Interrupt the thread, stops all working threads
	 */
	synchronized public void interrupt(){
		 isRun = false;       // to stop thread of animal
		 synchronized(this){ notify(); }
		 task.cancel(true); // to remove thread of animal from Threadpool
	}

	/**
	 * Loads the image of the animal by her name
	 * @param nm animals name
	 */
	public void loadImages(String nm){
		 switch(getColor()){
			 case "Red":
				 try { img1 = ImageIO.read(new File(PICTURE_PATH + nm + "_r_1.png"));
				 	   img2 = ImageIO.read(new File(PICTURE_PATH + nm + "_r_2.png"));} 
				 catch (IOException e) { System.out.println("Cannot load picture"); }
				 break;
			 case "Blue":
				 try { img1 = ImageIO.read(new File(PICTURE_PATH + nm + "_b_1.png"));
				 	   img2 = ImageIO.read(new File(PICTURE_PATH + nm + "_b_2.png"));} 
				 catch (IOException e) { System.out.println("Cannot load picture"); }
				 break;
			 default:
				 try { img1 = ImageIO.read(new File(PICTURE_PATH + nm + "_n_1.png"));
			 	       img2 = ImageIO.read(new File(PICTURE_PATH + nm + "_n_2.png"));} 
				 catch (IOException e) { System.out.println("Cannot load picture"); }			 
		 }
	}

	/**
	 * executing threads from the thread pool
	 */
    public void run() 
    {
    	isRun = true;
    	while (isRun) 
        {
            try 
            {
                Thread.sleep(50);
                
                synchronized(this) {
                    while (threadSuspended)
    						wait();
    				}  
           } 
            catch (InterruptedException e) 
            {
            	System.out.println(getName()+ " dead...");
            	return;
            }
                       
            if(this.getDiet().canEat(ZooPanel.getInstance().checkFood()))
            {
            		double oldSpead = Math.sqrt(horSpeed*horSpeed+verSpeed*verSpeed);
            		double newHorSpeed = oldSpead*(location.getX() - ZooPanel.getInstance().getWidth()/2)/
            				   (Math.sqrt(Math.pow(location.getX() - ZooPanel.getInstance().getWidth()/2,2)+
            						      Math.pow(location.getY() - ZooPanel.getInstance().getHeight()/2,2)));
            		double newVerSpeed = oldSpead*(location.getY() - ZooPanel.getInstance().getHeight()/2)/
            				   (Math.sqrt(Math.pow(location.getX() - ZooPanel.getInstance().getWidth()/2,2)+
            						      Math.pow(location.getY() - ZooPanel.getInstance().getHeight()/2,2)));
               	int v = 1;
                 if(newVerSpeed<0) { v=-1; newVerSpeed = -newVerSpeed; }
               	if(newVerSpeed > 10)
               		newVerSpeed = 10;
               	else if(newVerSpeed < 1) {
               	   if(location.getY() != ZooPanel.getInstance().getHeight()/2)
               		newVerSpeed = 1;   
               	   else
               		newVerSpeed = 0;  
               	}
               	int h = 1;
                 if(newHorSpeed<0) { h=-1; newHorSpeed = -newHorSpeed; }
               	if(newHorSpeed > 10)
               		newHorSpeed = 10;
               	else if(newHorSpeed < 1) {
               	   if(location.getX() != ZooPanel.getInstance().getWidth()/2)
               		newHorSpeed = 1;   
               	   else
               		newHorSpeed = 0;  
               	}
                	location.setX((int)(location.getX() - newHorSpeed*h));
                	location.setY((int)(location.getY() - newVerSpeed*v));
               	if(location.getX()<ZooPanel.getInstance().getWidth()/2)
               		x_dir = 1;
               	else
               		x_dir = -1;
               	synchronized(this){
	              	if((Math.abs(location.getX()-ZooPanel.getInstance().getWidth()/2)<EAT_DISTANCE) && 
	              	   (Math.abs(location.getY()-ZooPanel.getInstance().getHeight()/2)<EAT_DISTANCE)){
	              		ZooPanel.getInstance().eatFood(this);
	              	}
              	}
            }
            else
            {
 			    location.setX(location.getX() + horSpeed*x_dir);
 			    location.setY(location.getY() + verSpeed*y_dir);
            }

            if(location.getX() == 0)
    			x_dir = 1;
    		else if(location.getX() + size >= ZooPanel.getInstance().getWidth())
    			x_dir = -1;
            else if(location.getX() <= 0)
    		    x_dir = 1;
    		if(location.getY() == 0)
    			y_dir = 1;
    		else if(location.getY() + size  >= ZooPanel.getInstance().getHeight() - 50)
    	    	y_dir = -1;
    	 	else if(location.getY() <= 0)
    			y_dir = 1;

  		    setChanged();
  		    notifyObservers();
       }
    }
 
    /**
	 * Draws the animal to the panel.
	 * @param g, graphic context
	 */
    @Override
	public void drawObject (Graphics g)
	{
    	if(isRun)
    	{
    		if(x_dir==1) // giraffe goes to the right side
    		    g.drawImage(img1, location.getX(), location.getY(), size, size, ZooPanel.getInstance());
    	    else // giraffe goes to the left side
    		    g.drawImage(img2, location.getX(), location.getY(), size, size, ZooPanel.getInstance());
    	}
	}

    /**
     * return to string of animal
     */
    public String toString(){
    	return "["+getName() + ":running =" + isRun + ", weight=" + weight + ", color="+col+"]";
    }
}
