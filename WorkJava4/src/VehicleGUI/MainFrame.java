package VehicleGUI;
/***
@author Guy Hassan
ID : 307845032
Campus : Ashdod
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;

import Memento.MementoSaveState;
import ObserverPack.Observer;

public class MainFrame extends JFrame implements Observer
{	
	private static MainFrame singletonCheck = null; // Singleton DP , help me to create 1 main frame in all my system
	private static JButton[] ArrJButton = new JButton[10];
	private static String temp = null;
	static ArrayList<VehiclePanelsInterface> arrayVehiclePanels = new ArrayList<VehiclePanelsInterface>();
	private final JButton browse = new JButton("Browse Image");
	private static MenuFrame MenuFrameVar = null;
	static JLabel totalKm;
	private JButton saveState = new JButton("Save Current State");
	private JButton LoadingSaveState = new JButton("Loading Newest State");
	private ArrayList <MementoSaveState> mementoClass = new ArrayList<MementoSaveState>(3);
	
	public MainFrame()
	{	
		//init new frame -----------------------------------------------------------------
		setTitle("Vehicle Agency");
		setLayout(null);
		setBounds(0, 30, 600, 730);
		setLocationRelativeTo(null);//put the windows in the center
		setResizable(false);//cannot resize the windows
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close the windows when click X
		
		saveState.setBounds(25, 242, 270, 20);
		saveState.setBackground(new Color(220,220,250));
		add(saveState);
		LoadingSaveState.setBounds(300, 242, 270, 20);
		LoadingSaveState.setBackground(new Color(220,220,250));
		add(LoadingSaveState);
		CreatePanel_AndAction();
		browse.setBounds(140, 620, 120 , 75);
		browse.setIcon((new ImageIcon("img\\browse.png")));
		browse.setHorizontalTextPosition(JButton.CENTER);
		browse.setVerticalTextPosition(JButton.TOP);
		
		
	}
	
	
	public void CreatePanel_AndAction()
	{
		//Button add vehicle ------------------------------------------------------------------------
		JButton AddB = new JButton("Add");
		AddB.setBounds(10, 620, 120 , 75);
		AddB.setIcon((new ImageIcon("img\\VehicleAdd.png")));
		AddB.setHorizontalTextPosition(JButton.CENTER);
		AddB.setVerticalTextPosition(JButton.TOP);
		this.add(AddB).setVisible(false);
		this.add(browse).setVisible(false);
	   
		//help functions ------------------------------------------------------------------------------
		InitButtons();//init the buttons in main frame(Jeep , frigate ...)
		SetImageAndPlaceText();//set icon on the buttons
		initPanelsVehicle(); // init array vehicle panels
		
		//total kilometer label
		totalKm = new JLabel("   Total Kilometer: 0.0");
		totalKm.setFont(new Font(totalKm.getName(), Font.BOLD + Font.ITALIC, 20));
		totalKm.setForeground(new Color(0,0,100));
		totalKm.setBorder(BorderFactory.createLineBorder(new Color(180,180,250),2));
		totalKm.setBounds(160, 275, 260, 30);
		totalKm.setVisible(false);
		add(totalKm);
		
		//create main panel for buttons -----------------------------------------------------------------
		JPanel pane = new JPanel();
		pane.setLayout(null);
		pane.setBounds(15, 0, 600, 280);
		for(int i=0;i<ArrJButton.length;i++)
			pane.add(ArrJButton[i]);
		JLabel Choose = new JLabel("Choose Vehicle / Continue To The Menu .");
		Choose.setFont(new Font(Choose.getName(), Font.BOLD, 20));
		Choose.setBounds(90, -58, 400, 150);
		pane.add(Choose);
		this.add(pane);	
		
		//Main logo -------------------------------------------------------------------------------------
		ImageIcon Logo = new ImageIcon("img\\MainVehicle.jpg");
		Image im = Logo.getImage();
		Image tmp =  im.getScaledInstance(600, 420, Image.SCALE_DEFAULT);
		Logo = new ImageIcon(tmp);
		JLabel LogoLabel = new JLabel();
		LogoLabel.setBounds(0, 300, 600, 440);
		LogoLabel.setIcon(Logo);
		LogoLabel.setVisible(true);
		this.add(LogoLabel);
		
		//action to browse button ------------------------------------------------------------------------
	    browse.addActionListener(new ActionListener() 
	    {
	        public void actionPerformed(ActionEvent e) {
	        
	          JFileChooser file = new JFileChooser();
	          file.setCurrentDirectory(new File(System.getProperty("user.home")));
	          //filter the files
	          FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files","png","jpg","PNG","GIF","gif","png","JPG");
	          file.addChoosableFileFilter(filter);
	          int result = file.showSaveDialog(null);
	           //if the user click on save in Jfilechooser
	          if(result == JFileChooser.APPROVE_OPTION)
	          {
	              File selectedFile = file.getSelectedFile();
	              String path = selectedFile.getAbsolutePath();
	              for(int i=0;i<arrayVehiclePanels.size();i++)
	            	  if(arrayVehiclePanels.get(i).getRealName().equals(temp))
	            	  {
	            		  arrayVehiclePanels.get(i).setBrowseImg(path);
	            		  break;
	            	  }
	          }
	           //if the user click on save in Jfilechooser
	          else if(result == JFileChooser.CANCEL_OPTION){
	              System.out.println("No File Select");
	          }
	        }
	    });
	   
		
		//Action listener to the frame buttons ------------------------------------------------------------------------
		ActionListener Act = new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
	            for(int i=0;i<arrayVehiclePanels.size();i++)
	            	arrayVehiclePanels.get(i).PanelComp().setVisible(false);
	        	AddB.setVisible(true);
	        	browse.setVisible(true);
	        	totalKm.setVisible(true);
	            LogoLabel.setVisible(false);
	            temp = (e.getActionCommand() == "Menu")? temp : e.getActionCommand();
				if(e.getActionCommand().equals("Jeep"))
				{
					arrayVehiclePanels.get(0).PanelComp().setVisible(true);
					
				}
				else if(e.getActionCommand().equals("Frigate"))
				{
					arrayVehiclePanels.get(1).PanelComp().setVisible(true);

				}
				else if(e.getActionCommand().equals("SpyPlane"))
				{
					arrayVehiclePanels.get(2).PanelComp().setVisible(true);

				}
				else if(e.getActionCommand().equals("GamePlane"))
				{
					arrayVehiclePanels.get(3).PanelComp().setVisible(true);

				}
				else if(e.getActionCommand().equals("Amphibious"))
				{
					arrayVehiclePanels.get(4).PanelComp().setVisible(true);

				}
				else if(e.getActionCommand().equals("Bicycle"))
				{
					arrayVehiclePanels.get(5).PanelComp().setVisible(true);

				}
				else if(e.getActionCommand().equals("CruiseShip"))
				{
					arrayVehiclePanels.get(6).PanelComp().setVisible(true);

				}
				else if(e.getActionCommand().equals("HybridPlane"))
				{
					arrayVehiclePanels.get(7).PanelComp().setVisible(true);

				}
				else if(e.getActionCommand().equals("ElectricBike"))
				{
					arrayVehiclePanels.get(8).PanelComp().setVisible(true);

				}
				else if(e.getActionCommand().equals("Menu")) {
					AddB.setVisible(false);
		        	browse.setVisible(false);
		            LogoLabel.setVisible(true);
					MenuFrameVar = MenuFrame.createInstance();
					MenuFrameVar.setVisible(true);
				}

			}
			
		};
		//call action listener which user click ---------------------------------------------------------
		for(int i=0; i < ArrJButton.length;i++)
			ArrJButton[i].addActionListener(Act);
		
		//Buttons Add action listener -------------------------------------------------------------------
		AddB.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{		
				new SwingWorker<Object,Object>()
				{
					public String doInBackground() 
					{
				        	   int i = IndexOfButtonPanel(temp);
				        	   Thread t = new Thread(new MessageUpdateDatabase(i));
				        	   	synchronized (temp) 
				        	   	{
				        	   		if(arrayVehiclePanels.get(i).addVehicleToList()) {
				        	   			t.start();
										arrayVehiclePanels.get(i).resetField();
				        	   		}
				        	   	}
							return null;
				       }
				}.execute();	
		}});
		setVisible(true);
		saveOrLoadStateAction();
}
	/**
	 * save or load state action button , when the user click on save button i add to memento array new state
	 * and when he click load i try to load the last save existing
	 */
	public void saveOrLoadStateAction()
	{
		saveState.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(mementoClass.size() < 3)
					mementoClass.add(new MementoSaveState());
				else {
					mementoClass.remove(0);
					mementoClass.add(new MementoSaveState());
				}
			}
		});
		LoadingSaveState.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(mementoClass.size() != 0) {
					mementoClass.get(mementoClass.size()-1).getTheCurrentState();
					mementoClass.remove(mementoClass.size()-1);
				}
			}
		});
	}

	/**
	 * help function - bring me the index of button that exist on the main frame
	 * @param a - name of button
	 * @return - index
	 */
	public int IndexOfButtonPanel(String a)
	{
		String[] name = {"Jeep","Frigate","SpyPlane","GamePlane","Amphibious","Bicycle","CruiseShip","HybridPlane","ElectricBike"};
		for(int i=0;i<name.length;i++)
			if(name[i].equals(a))
				return i;
		return -1;
	}
	
	/**
	 * initialize the array panel vehicle
	 */
	public void initPanelsVehicle()
	{	
		arrayVehiclePanels.add(new JeepPanel());
		arrayVehiclePanels.add(new FrigatePanel());
		arrayVehiclePanels.add(new SpyPlanePanel());
		arrayVehiclePanels.add(new GamePlanePanel());
		arrayVehiclePanels.add(new AmphibiousPanel());
		arrayVehiclePanels.add(new BicyclePanel());
		arrayVehiclePanels.add(new CruiseShipPanel());
		arrayVehiclePanels.add(new hybridPlanePanel());
		arrayVehiclePanels.add(new electricBikePanel());
		for(int i=0 ; i < arrayVehiclePanels.size() ;i++) {
			arrayVehiclePanels.get(i).PanelComp().setBounds(15, 315 , 600 , 700);
			this.add(arrayVehiclePanels.get(i).PanelComp()).setVisible(false);
		}
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
	 * initialize and resize buttons
	 */
	public void InitButtons()
	{
		String[] strArray = {"Jeep","Frigate", "SpyPlane","GamePlane",
				"Amphibious","Bicycle","CruiseShip","HybridPlane","ElectricBike","Menu"};
		int x = 10,y = 35 ;
		for(int i = 0 ; i < ArrJButton.length ; i++)
		{
			ArrJButton[i] = new JButton(strArray[i]);//create new button 
			if(i == ArrJButton.length/2)//to get new line
			{
				y = 140;
				x = 10;
			}
			ArrJButton[i].setBounds(x, y, 105, 100);//resize the buttons
			x += 110;
		}
	}
	/**
	 * set image into the main buttons
	 * help function put image on button and place the test Top
	 */
	public void SetImageAndPlaceText()
	{
		String[] sourceImg = {"img\\Jeep.png","img\\Frigate.png","img\\spyPlane.png","img\\gamePlane.png"
				,"img\\Amphibious.png","img\\Bicycle.png","img\\cruiseShip.png","img\\hybridPlane.png",
				"img\\electricBike.png","img\\Menu.png"};
		for(int i=0 ; i<ArrJButton.length ;i++)
		{
			ArrJButton[i].setIcon(new ImageIcon(sourceImg[i]));
			ArrJButton[i].setHorizontalTextPosition(JButton.CENTER);
			ArrJButton[i].setVerticalTextPosition(JButton.TOP);
			ArrJButton[i].setBackground(new Color(220,220,250));
		}
		
	}
	/**
	 * createInstance singleton method ,initialize the singleton variable if he null 
	 * @return the instance from this class
	 */
	public static MainFrame createInstance() {
		if(singletonCheck == null)
			singletonCheck = new MainFrame();
		return singletonCheck;
	}
	/**
	 * getInstace method, return the singleton variable , help me to understand if i have an open frame from this class
	 * @return the instance of variable class
	 */
	public static MainFrame getInstance(){return singletonCheck;}


	@Override
	public void update() {
		totalKm.setText("   Total Kilometer: " + MenuFrame.getInstance().getTotalKm());
		totalKm.repaint();
		totalKm.revalidate();
	}
}
