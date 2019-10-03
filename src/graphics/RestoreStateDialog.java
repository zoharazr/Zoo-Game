package graphics;

import javax.swing.JOptionPane;
/**
 * 
 * @author idan ben moshe 308118439 zohar azriav 201454899
 *
 */
public class RestoreStateDialog extends JOptionPane {
	
	private static final long serialVersionUID = 1L;
	private Object msg[] = {"Please choose state for restore"};
	private Object type[] = { "State 1", "State 2", "State 3","Cancel"};
	private int choice;
	
	/**
	 * c'tor
	 */
	public RestoreStateDialog() {
		choice = showOptionDialog(null, msg, "Saves states",
		        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, type,null);
	}
	
	/**
	 * 
	 * @return choice of restore state
	 */
	public int getChoice(){
		return choice;
	}
}
