package graphics;

import javax.swing.JOptionPane;
/**
 * 
 * @author idan ben moshe 308118439 zohar azriav 201454899
 *
 */
@SuppressWarnings("serial")
public class FactoryDialog extends JOptionPane
{
	private Object msg[] = {"Please choose animal factory"};
	private Object type[] = { "Herbivore", "Omnivore", "Carnivore" };
	private int choice;
	
	/**
	 * 
	 * @param zooPanel
	 */
	public FactoryDialog() 
	{
		choice = showOptionDialog(null, msg, "Animals factory",
		        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, type,null);
	}
	
	/**
	 * 
	 * @return factory type
	 */
	public String ChooseFactory()
	{
		String foodtype = null;
		switch (choice) {
		case 0:
			foodtype = "Plant";
			break;
		case 1:
			foodtype = "Mix";
            break;
		case 2:
			foodtype = "Meat";
			break;
		}	
		return foodtype;
	}

}
