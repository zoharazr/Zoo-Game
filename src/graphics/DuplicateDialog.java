package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import animals.Animal;
import mobility.Point;
/**
 * 
 * @author idan ben moshe 308118439 zohar azriav 201454899
 *
 */
public class DuplicateDialog extends JDialog implements ActionListener {
	
	private static final long serialVersionUID = 1L;
    private JPanel mainPanel, selectPanel, speedPanel;
    private Animal an , clone;
    private JComboBox<String> list;
    private JLabel lbl_hor, lbl_ver;
    private JSlider sl_hor, sl_ver;
    private JButton ok;
 
    
	/**
	* C'tor
	*/   
    public DuplicateDialog()
    {
        super(new JFrame(),"Duplicate an animal",true);
    	an = clone = null;
 
    	setSize(750,250);
    	setResizable(false);
	
	    setBackground(new Color(100,230,255));
   	    mainPanel = new JPanel();
	    add(mainPanel);
		
	    mainPanel.setLayout(new GridLayout(1,2));
		
	    selectPanel = new JPanel();
	    selectPanel.setBorder(BorderFactory.createCompoundBorder(
		BorderFactory.createTitledBorder("Select Animal to clone"), 
		BorderFactory.createEmptyBorder(5,5,5,5)));
	    selectPanel.setLayout(new BorderLayout());
 
	    list = new JComboBox<String>();
	    list.addItem("No animal");
	    ZooPanel.getInstance().fillComboBox(list,"All");
	    list.addActionListener(this);
	    selectPanel.add("North",list);
		
	    ok = new JButton("OK");
	    ok.addActionListener(this);
	    selectPanel.add("South", ok);
	    mainPanel.add(selectPanel);
		
	    speedPanel = new JPanel();
	    speedPanel.setLayout(new GridLayout(4,1));
	    speedPanel.setBorder(BorderFactory.createCompoundBorder(
		BorderFactory.createTitledBorder("Speed may be changed..."), 
		BorderFactory.createEmptyBorder(5,5,5,5)));
		
	    lbl_hor = new JLabel("Horizontal speed",JLabel.CENTER);
	    speedPanel.add(lbl_hor);
 
	    sl_hor = new JSlider(0,10);
	    sl_hor.setMajorTickSpacing(2);
	    sl_hor.setMinorTickSpacing(1);
	    sl_hor.setPaintTicks(true);
	    sl_hor.setPaintLabels(true);
	    speedPanel.add(sl_hor);
		
	    lbl_ver = new JLabel("Vertical speed",JLabel.CENTER);
	    speedPanel.add(lbl_ver);
		
	    sl_ver = new JSlider(0,10);
	    sl_ver.setMajorTickSpacing(2);
	    sl_ver.setMinorTickSpacing(1);
	    sl_ver.setPaintTicks(true);
	    sl_ver.setPaintLabels(true);
	    speedPanel.add(sl_ver);
		
	    mainPanel.add(speedPanel);	
	    setVisible(true);
    }

    /**
	* @param e, event is passed to the ActionListener object that registered to receive such events using the component's addActionListener method.
	* make an operation under certain push of a button.
	* Sends the choice to the clone function to duplicate the chosen animal.
	*/
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ok) {
	    	if(an!=null) {
	    		try {clone = (Animal)an.clone();} catch (CloneNotSupportedException e1) {e1.printStackTrace();}
	    		clone.setLocation(new Point(0,0));
	    		clone.setHorSpeed(sl_hor.getValue());
	    		clone.setVerSpeed(sl_ver.getValue());
	    		ZooPanel.getInstance().addAnimal(clone);
	    	}
    		setVisible(false);
    	}
    	else {
	    	int index;
	    	if((index = list.getSelectedIndex()) != 0) {
	    		try { an = ZooPanel.getInstance().getAnimal(index-1); } 
	    		catch (Exception e1) { System.out.println("Duplicate error!."); an = null; }
		    	if(an!=null) {
		    		sl_hor.setValue(an.getHorSpeed());
		    		sl_ver.setValue(an.getVerSpeed());
		    	}
	    	}		
    	}
		
	}
}

