package VehicleGUI;
/***
@author Guy Hassan
ID : 307845032
Campus : Ashdod
 */
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.SwingWorker;

public class FlagsFrame extends JFrame  {
	private ImageIcon[] flagArray = {new ImageIcon("img\\israel.png"),new ImageIcon("img\\usa.png"),new ImageIcon("img\\germany.png"),new ImageIcon("img\\italy.png")
			,new ImageIcon("img\\greece.png"),new ImageIcon("img\\somalia.png"),new ImageIcon("img\\pirate.png")};
	private JLabel[] arrayLabel = new JLabel[7];
	private JRadioButton[] arrayRadio = new JRadioButton[7]; 
	private String[] country = {"Israel","USA","Germany","Italy","Greece","Somalia","Pirate"}; 
	
	public FlagsFrame()
	{
		//set new frame ----------------------------------------------------------
		setLayout(null);
		setBounds(0, 0, 700, 200);
		setLocationRelativeTo(null);//put the windows in the center
		setResizable(false);//cannot resize the windows
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//create group to radio buttons ----------------------------------------------------------
		ButtonGroup group = new ButtonGroup();
		for(int i=0;i<arrayRadio.length;i++)
		{
			initRadios(i);
			group.add(arrayRadio[i]);
		}
		
		//init array labels ----------------------------------------------------------
		initLabels();
		setLabels();
		JButton save = new JButton("Save");
		save.setBounds(290, 130, 120, 20);
		for(int i=0;i<flagArray.length;i++)
		{
			flagArray[i] = resizeImage(flagArray[i]);
			arrayLabel[i].setIcon(flagArray[i]);
		}
		
		add(save);
		
		//action listener to save buttons ----------------------------------------------------------
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = flagSelected();
				if(i != -1 ) 
				{
					if(MainVehicleAgency.arrM.size() > 0) {
						MainVehicleAgency.setNewFlag(country[i]);
						MenuFrame.getInstance().thread_SetFlag_BuyVehicle_ResetKM(-1,"Set Flag");
						dispose();
						MenuFrame.getInstance().setVisible(true);
					}
					else
						JOptionPane.showMessageDialog(null,"~ No Marine Vehicle Exist ~ ");
				}
				else
					JOptionPane.showMessageDialog(null, "No Flag Selected !");
			}
		});

	}

	public int flagSelected()
	{
		for(int i=0;i<arrayRadio.length;i++)
			if(arrayRadio[i].isSelected())
				return i;
		return -1;
	}
	
	/**
	 * initialize the  radio button
	 * @param i
	 */
	public void initRadios(int i)
	{	
			arrayRadio[i] = new JRadioButton(country[i]); 
	}
	/**
	 * initialize a label array
	 */
	public void initLabels()
	{
		for(int i=0;i<arrayLabel.length;i++)
		{
			arrayLabel[i] = new JLabel();
			arrayLabel[i].setBounds(0, 0, 80, 120);
		}
	}
	/**
	 * resize a label and add him to frame
	 */
	public void setLabels()
	{
		int x = 10;
		for(int i=0;i<arrayLabel.length;i++)
		{
			arrayLabel[i].setBounds(x + 10, 45, 80, 80);
			arrayRadio[i].setBounds(x, -20, 80, 80);
			add(arrayLabel[i]);
			add(arrayRadio[i]);
			x += 100;
		}
	}
	/**
	 * set size to the image label
	 */
	public ImageIcon resizeImage(ImageIcon img)
	{
		Image im = img.getImage();
		Image tmp =  im.getScaledInstance(50, 50, Image.SCALE_REPLICATE);
		img = new ImageIcon(tmp);
		return img;
	}
}
