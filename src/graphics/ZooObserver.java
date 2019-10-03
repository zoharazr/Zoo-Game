package graphics;

import java.util.Observable;
import java.util.Observer;
/**
 * 
 * @author idan ben moshe 308118439 zohar azriav 201454899
 *
 */
public class ZooObserver extends Thread implements Observer{

	/**
	 * C'tor
	 */
	public ZooObserver() {}
	
	/**
	 * Observer update.
	 */
	@Override
	synchronized public void update(Observable o, Object arg) {
	    notify();
	}

	/**
	 * executing threads
	 */
	@Override
	public void run() {
		while(true)
		{
			synchronized (this) {
				try {wait();} catch (InterruptedException e) {System.out.println("panel Controller destroy");return;}
				ZooPanel.getInstance().prey_eaten();
				ZooPanel.getInstance().repaint();
			}
		}
	}
}
