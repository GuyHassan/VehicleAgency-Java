package VehicleGUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import AbstractFactoryPack.VehicleFactory;
import VehicleAgency.GamePlane;
import VehicleAgency.SpyPlane;
import VehicleAgency.interfaceMarineVehicles;
import VehicleAgency.interfaceVehicle;

public class GamePlanePanel extends JPanel implements VehiclePanelsInterface{

	private static JLabel pic;
	private static ImageIcon[] img = {new ImageIcon("img\\gameplane1.jpg"),new ImageIcon("img\\gameplane2.jpg"),new ImageIcon("img\\gameplane3.jpg"),new ImageIcon()};
	private  ArrayList<Component> Label_Text = new ArrayList<Component>();
	private String[] colorName = {"Choose Color","Black","Blue", "Green","Yellow","Orange","Pink","Red","White"};
	private  JComboBox<String> colorCH = new JComboBox<>(colorName);
	private JComboBox comboBox;
	
	
	public GamePlanePanel()
	{
		//set layout panel
		setLayout(null);
		setBounds(15, 280 , 600 , 700);
		
		//create label
		JLabel textTmp = new JLabel("A Game Plane Does Not Need To Fill Fields.");
		textTmp.setFont(new Font(textTmp.getName(), Font.BOLD, 12));
		textTmp.setBounds(0 , 10 , 250, 100);
		this.add(textTmp);

		//init pic label
		pic = new JLabel();
		pic.setBounds(255, 0, 320, 380);
		
		//resize array image
		for(int i=0;i<img.length-1;i++)
			img[i] = resizeImage(img[i]);
		String[] names = {"Choose Image","Image 1","Image 2","Image 3"};
	    comboBox = new JComboBox(names);

	}
	/**
	 * set size to the image label
	 */
	public static ImageIcon resizeImage(ImageIcon img)
	{
		Image im = img.getImage();
		Image tmp =  im.getScaledInstance(320, 380, Image.SCALE_REPLICATE);
		img = new ImageIcon(tmp);
		return img;
	}
	/**
	 * return a panel to the main frame
	 */
	public Component PanelComp()
	{
		comboBox.setBounds(0, 120 , 150, 20);
		colorCH.setBounds(0, 90, 150, 20);
		comboBox.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) {
				int i = comboBox.getSelectedIndex();
				if(i > 0)
				{
					pic.setIcon(img[i-1]);
					img[img.length-1] = img[i-1];
				}
				else
					pic.setIcon(null);
			}
		});
		add(colorCH);
		this.add(pic);
	    this.add(comboBox);
		return this;
	}
	/**
	 * add a new vehicle to the lists (array vehicle , marine , and image)  -----------------------------------------------------------
	 */
	public boolean addVehicleToList() {
		if(pic.getIcon() != null) {
			VehicleFactory factory = new VehicleFactory();
			interfaceVehicle gameP = factory.getNewVehicle("GamePlane", null ,null,colorCH);
			if(!gameP.equals(null)) {
				MainVehicleAgency.arrV.add(gameP);
				MainVehicleAgency.arrayImage.add(img[img.length-1]);
				MainVehicleAgency.arraySemaphoreLock.add((new Semaphore(1)));
				return true;
			}
			else return false;
		}	
		else 
			JOptionPane.showMessageDialog(null, "Choose Image!!");
		return false;
}
	
	/**
	 * reset the field on the panel after we add a vehicle
	 */
	public void resetField() {
		comboBox.setSelectedIndex(0);
		colorCH.setSelectedIndex(0);
	}
	/**
	 * set browse image on the label
	 */
	public void setBrowseImg(String nameImg) {
		img[img.length-1] = new ImageIcon(nameImg);
		img[img.length-1] = resizeImage(img[img.length-1]);
		pic.setIcon(img[img.length-1]);
		
	}
	/**
	 * help me to understand with which panel i work
	 */
	@Override
	public String getRealName() {
		return "GamePlane";
	}
}
