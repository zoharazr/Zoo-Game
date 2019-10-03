package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import animals.Animal;
import food.EFoodType;
import food.Meat;
import mobility.Point;
import plants.Cabbage;
import plants.Lettuce;
/**
 * 
 * @author idan ben moshe 308118439 zohar azriav 201454899
 *
 */
public class ZooPanel extends JPanel implements ActionListener {
	private static volatile ZooPanel instance = null;
	private static final long serialVersionUID = 1L;
    private static final int MAX_ANIMAL_NUMBER  = 10;
    private final String BACKGROUND_PATH = Animal.PICTURE_PATH+"savanna.jpg";
    private JButton[] b_num;
    private String[] names = {"Add Animal","Sleep","Wake up","Clear",
    		"Food","Info","Decorate","Duplicate","Save state","Restore state","Exit"};
    private final static int P1Length = 6;
    private Vector<Animal> animals;
    private JPanel p;
    private JPanel p1;
    private JPanel p2;
    private String foodName;
    private JScrollPane scrollPane;
    private boolean isTableVisible;
    private int totalCount;
    private BufferedImage img;
    private boolean bgr;
    private ZooObserver controller;
    private Executor threadPool; 
    private ArrayList<ZooMemento> mementos;
    private AbstractZooFactory factory;
   
    /**
	 * ZooPanel constructor.
	 */
    public ZooPanel()
    {
    	mementos = new ArrayList<>();
 	    totalCount = 0;
 	    isTableVisible = false;
 	    animals = new Vector<Animal>();
 	    controller = new ZooObserver();
 	    controller.start();
 	    threadPool =  Executors.newFixedThreadPool(5);
 	    setLayout(new BorderLayout());
 	    setBackground(new Color(240,240,240));
 	    
 	    p = new JPanel(new BorderLayout());
		p.setBackground(new Color(240,240,240));
 	    p1 = new JPanel(new GridLayout(1,7,0,0));
		p1.setBackground(new Color(240,240,240));
		p2 = new JPanel(new GridLayout(1,7,0,0));
		p2.setBackground(new Color(240,240,240));

		b_num=new JButton[names.length];
		for(int i=0;i<P1Length;i++)
		{
		    b_num[i]=new JButton(names[i]);
		    b_num[i].addActionListener(this);
		    b_num[i].setBackground(Color.lightGray);
		    p1.add(b_num[i]);		
		}

		for (int i=P1Length;i<names.length;i++)
		{
			b_num[i]=new JButton(names[i]);
		    b_num[i].addActionListener(this);
		    b_num[i].setBackground(Color.lightGray);
		    p2.add(b_num[i]);
		}
		
		add("South",p);
		p.add("North",p1);
		p.add("South",p2);
 		
 		bgr = false;
 		img = null;
 		try { img = ImageIO.read(new File(BACKGROUND_PATH)); } 
		catch (IOException e) { System.out.println("Cannot load background"); }
    }		
    
    /**
     * 
     * @return instance of panel
     */
    public static ZooPanel getInstance() {
        if (instance == null)
           synchronized(ZooPanel.class){   
               if (instance == null)
                   instance = new ZooPanel();
           }
        return instance;
    }
    /**
     * 
     * @param index index of animal
     * @return animal
     */
    public Animal getAnimal(int index)
    {
    	Animal an = null;
    	if(animals.size() > 0)
    	{
    		if(index >= 0 && index < animals.size())
    			an = animals.get(index);
    	}
    	return an;
    }
    
    /**
     * Checks if there any food in the zoo
     * @return type of panel food
     */
    synchronized public EFoodType checkFood()
    {
 	   if(foodName == "Meat")
 		   return EFoodType.MEAT;
 	   else if(foodName == "Lettuce" || foodName == "Cabbage")
 		   return EFoodType.VEGETABLE;
 	   else 
 		   return EFoodType.NOTFOOD;
    }
    
    /**
	 * Loads the selected image to the panel and updates the animal image by location,
	 * if there any food draws it on the panel.
	 * @param g, graphic context.
	 */
    public void paintComponent(Graphics g)
    {
 	   	super.paintComponent(g);	
 	   	
 	   	if(bgr && (img!=null))
            g.drawImage(img, 0, 0, getWidth(), getHeight(), this);

 	    if(bgr && (img!=null))
            g.drawImage(img, 0, 0, getWidth(), getHeight(), this);

	   	if(foodName == "Meat")
	   		Meat.getInstance().drawObject(g);
	   	else if(foodName == "Cabbage")
	   		Cabbage.getInstance().drawObject(g);
	   	else if(foodName == "Lettuce")
	   	    Lettuce.getInstance().drawObject(g);

 	   	synchronized(this) {
 		   	for(Animal an : animals)
 		    	an.drawObject(g);
 	   	}
    }
   
    /**
	 * set panel background
	 * @param num chosen background color/image
	 */
    public void setBackgr(int num) {
 	   switch(num) {
 	   case 0:
 		   setBackground(new Color(255,255,255));
 		   bgr = false; 
 		   break;
 	   case 1:
 		   setBackground(new Color(0,155,0));
 		   bgr = false; 
 		   break;
 	   default:
 			bgr = true;   
 	   }
 	   repaint();
    }

    /**
     * animal eat panel food 
     * @param an animal that eat food
     */
    synchronized public void eatFood(Animal an)
    {
 	   if(foodName != null)
 	   {
 		   	foodName = null;
 	   		an.eatInc();
 	   		totalCount++;
 	   		System.out.println("The "+an.getName()+" with "+an.getColor()+" color and size "+an.getSize()+" ate food.");
 	   }
 	   else
 	   {
 		   System.out.println("The "+an.getName()+" with "+an.getColor()+" color and size "+an.getSize()+" missed food.");
 	   }
    }

    /**
	 * Opens the dialog to select the type of animal.
	 */
    public void addDialog()
    {
 	   if(animals.size()==MAX_ANIMAL_NUMBER) {
 		   JOptionPane.showMessageDialog(this, "You cannot add more than "+MAX_ANIMAL_NUMBER+" animals");
 	   }
 	   else {
 		   FactoryDialog dialog = new FactoryDialog();
 		   String foodtype = dialog.ChooseFactory();
 		   if(foodtype != null)
 		       new AddAnimalDialog("Add an animal to zoo", foodtype);
 	   }
    }
   
    /**
	 * Creates an animal by using the abstract factory DP.
	 * Adds the observer.
	 * Adds the animal to the Vector/Queue.
	 * Inserts the animal to the thread pool and executes her thread.
	 * 
	 * @param animal chosen animal name
	 * @param sz chosen size
	 * @param hor chosen horizontal speed
	 * @param ver chosen vertical speed
	 * @param c chosen color
	 * @param foodtype animals food type
	 */
    public void addAnimal(String animal, int sz, int hor, int ver, String c, String foodtype)
    {
 	   Animal an = null;
 	   factory = AbstractZooFactory.createAnimalFactory(foodtype);
 	   an = factory.produceAnimal(animal);
 	   an.setAnimalDetails(animal, sz, hor, ver, c);
 	   animals.add(an);
 	   Future<?> task = ((ExecutorService)threadPool).submit(an);
 	   if(animals.size() > 5)
 		   an.setSuspend();
 	   an.setTask(task);
 	   an.addObserver(controller);
    }

    /**
	 * Starts back the thread after suspension
	 */
    public void start() {
	    for(Animal an : animals)
	    	if(an.isRunning())
	    	    an.setResume();
    }

    /**
	 * Stops the thread
	 */
 	public void stop() {
	    for(Animal an : animals)
	    	if(an.isRunning())
	    	    an.setSuspend();
    }

 	/**
	 * Clears all the active animals from the zoo (panel)
	 */
 	synchronized public void clear() {
 		Vector<Animal> ani = new Vector<>();
 		
 	    for(Animal an : animals)
 	    {
 	    	if(an.isRunning())
 	    		an.interrupt();
 	    	else 
 	    		ani.add(an);
 	    }
 	    
 	    animals.clear();
 	    animals = ani;
 	    
 	    for(Animal animal: animals)
 	    	animal.setResume();
 	    
 	    foodName = null;
 	    totalCount = 0;
 	    repaint();
    }

 	/**
 	 * 
 	 * @param predator animal that eat
 	 * @param prey animal We'll eat
 	 */
 	synchronized public void preyEating(Animal predator, Animal prey)
    {
 	   predator.eatInc();
 	   totalCount -= (prey.getEatCount()-1);
    }

 	/**
	 * Initialize the selected food.
	 */
 	synchronized public void addFood()
    {
 	   if(foodName == null){
 		   Object[] options = {"Meat", "Cabbage", "Lettuce"}; 
 		   int n = JOptionPane.showOptionDialog(null,
 		   		"Please choose food", "Food for animals", 
 		   		JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, 
 		   		null, options, options[2]);
 		   switch(n) {
 		   case 0: // Meat
 			   foodName = "Meat";
 			   break;
 		   case 1: // Cabbage
 			   foodName = "Cabbage";
 			   break;
 		   case 2: // Lettuce
 			   foodName = "Lettuce";
 			   break;
 		   default:
 			   foodName = null;
 		   }
 	   }
 	   else {
 		   foodName = null;
 	   }
 	   repaint();
   }
   
   /**
   * Kills all threads and clears the animals Vector.
   * Exits the program.
   */
   public void destroy()
   { 
	  for(Animal an : animals)
		  an.interrupt();
	  animals.clear();
	  controller.interrupt();
      System.exit(0);
   }
   
   /**
	 * Opens the decoration dialog.
	 * Checks if animals with natural color exists,
	 * if not, shows a message.
	 */
   public void Decorate()
   {
	   int count = 0;
	   
	   for(Animal animal: animals)
	   {
		   if(animal.getColor() == "Natural")
			   count++;
	   }
	   
	   if(count == 0)
		   DecorateErrorMessage();
	   else
	   {
		   new DecorateDialog(); 
	   }
   }
   
   /**
    * show decorate message
    */
   public void DecorateErrorMessage()
   {
	   JOptionPane.showMessageDialog(this,"You have not animals for decoration");
   }
   
   /**
	 * Duplicate animal dialog.
	 */
   public void Duplicate()
   {
	   new DuplicateDialog();
   }
   
   /**
	 * Saves the current state of the Zoo.
	 */
   public void SaveState() throws CloneNotSupportedException
   {
	   if(mementos.size() == 3)
		   mementos.remove(0);
	   mementos.add(new ZooMemento(animals, foodName, bgr));
   }
   
   /**
	 * Opens a dialog of the saved states only if saved state exists.
	 */
   public void RestoreState()
   {
	   if(mementos.size() > 0)
	   {
		   RestoreStateDialog state = new RestoreStateDialog();
		   int choice = state.getChoice();
		   getState(choice);
	   }
	   else
		   JOptionPane.showMessageDialog(this,"You have not saved ststes");
   }
   
   /**
    * fill all details to panel 
    * @param choice chosen state that saved
    */
   public void getState(int choice)
   {
	   if(choice == 1 && mementos.size() < 2
			   || choice == 2 && mementos.size() < 3)
	   {
		   JOptionPane.showMessageDialog(this,"This state is empty");
	   }
	   else if(choice != 3)
	   {
		   for(Animal an : animals)
			   an.interrupt();
		   animals.clear();
		   if(mementos.get(choice).getList().size() != 0)
		   {
			   Vector<Animal> temp = mementos.get(choice).getList();
			   for(Animal an : temp)
				   addAnimal(an);
		   }
		   foodName = mementos.get(choice).getFood();
		   bgr = mementos.get(choice).getBgr();
		   mementos.remove(choice);
		   repaint();
	   }
   }
   
   /**
	 * Shows all the details of the animals.
	 */
   public void info()
   {  	 
	   if(isTableVisible == false)
	   {
		  int i=0;
		  int sz = animals.size();

		  String[] columnNames = {"Animal","Color","Weight","Hor. speed","Ver. speed","Eat counter"};
	      String [][] data = new String[sz+1][columnNames.length];
		  for(Animal an : animals)
	      {
	    	  data[i][0] = an.getName();
	    	  data[i][1] = an.getColor();
	    	  data[i][2] = new Integer((int)(an.getWeight())).toString();
		      data[i][3] = new Integer(an.getHorSpeed()).toString();
		      data[i][4] = new Integer(an.getVerSpeed()).toString();
	    	  data[i][5] = new Integer(an.getEatCount()).toString();
	    	  i++;
	      }
	      data[i][0] = "Total";
	      data[i][5] = new Integer(totalCount).toString();
	      
	      JTable table = new JTable(data, columnNames);
	      scrollPane = new JScrollPane(table);
	      scrollPane.setSize(450,table.getRowHeight()*(sz+1)+24);
	      add( scrollPane, BorderLayout.CENTER );
	      isTableVisible = true;
	   }
	   else
	   {
		   isTableVisible = false;
	   }
	   scrollPane.setVisible(isTableVisible);
       repaint();
   }
      
   /**
	 * @param e, event is passed to the ActionListener object that registered to receive such events using the component's addActionListener method.
	 * make an operation under certain push of a button.
	 */
   public void actionPerformed(ActionEvent e)
   {
	if(e.getSource() == b_num[0]) // "Add Animal"
		addDialog();
	else if(e.getSource() == b_num[1]) // "Sleep"
		stop();
	else if(e.getSource() == b_num[2]) // "Wake up"
		start();
	else if(e.getSource() == b_num[3]) // "Clear"
		clear();
	else if(e.getSource() == b_num[4]) // "Food"
		addFood();
	else if(e.getSource() == b_num[5]) // "Info"
		info();
	else if(e.getSource() == b_num[6]) // "Decorate"
		Decorate();
	else if(e.getSource() == b_num[7]) // "Duplicate"
		Duplicate();
	else if(e.getSource() == b_num[8])
		try {SaveState();} catch (CloneNotSupportedException e1) {e1.printStackTrace();}
	else if(e.getSource() == b_num[9]) // "Restore state"
		RestoreState();
	else if(e.getSource() == b_num[10]) // "Exit"
		destroy();
   }

   /**
	 * checks if a certain animal can eat other animal.
	 * if true, eats the animal else continue the check.
	 */
   public void prey_eaten() {
			
	   if(isChange())
	       repaint();
			
	   boolean prey_eaten = false;
		synchronized(this) {
			for(Animal predator : animals) {
				for(Animal prey : animals) {
					if(predator != prey && predator.getDiet().canEat(prey) && predator.getWeight()/prey.getWeight() >= 2 &&
					   (Math.abs(predator.getLocation().getX() - prey.getLocation().getX()) < prey.getSize()) &&
					   (Math.abs(predator.getLocation().getY() - prey.getLocation().getY()) < prey.getSize()) && predator.isRunning()) {
							preyEating(predator,prey);
							System.out.print("The "+predator+" cought up the "+prey+" ==> ");
							prey.interrupt();
							animals.remove(prey);
							repaint();
							//JOptionPane.showMessageDialog(frame, ""+prey+" killed by "+predator);
							prey_eaten = true;
							break;
					}
				}
				if(prey_eaten)
					break;
			}
		}
   }
   
   /**
	 * Checks if the animal has changed position and updates the location of the animal's image on the panel.
	 */
   public boolean isChange() {
		boolean rc = false;
		for(Animal an : animals) {
		    if(an.hasChanged()){
		    	rc = true;
		    	an.setChange();
			}
	    }
		return rc;
	}
   
   /**
	 * 
	 * @param list of strings
	 * @param string animal color to fill the combo box.
	 */
   public void fillComboBox(JComboBox<String> list, String string) {
		int count = 0;
		if(string == "Natural")
		{
			for(Animal animal: animals)
			{	
				if(animal.getColor() == string)
				    list.addItem((count + 1)+ "." + animal.toString());
				count++;
			}
		}
		else if(string == "All")
		{
			for(Animal animal: animals)
			{
				list.addItem((count + 1)+ "." + animal.toString());
				count++;
			}
		}
   }
   
   /**
	 * Adds the given clone to the Zoo.
	 * @param clone animal instance.
	 */
   synchronized public void addAnimal(Animal clone) {
	   animals.add(clone);
	   Future<?> task = ((ExecutorService)threadPool).submit(clone);
 	   if(animals.size() > 5)
 	   {
 		  clone.setSuspend();
 		  clone.setIsRun(false);
 	   }
 	   clone.setTask(task);
 	   clone.addObserver(controller);
 	   clone.setLocation(new Point(0, 0));
   }
}