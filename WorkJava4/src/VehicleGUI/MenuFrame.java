package VehicleGUI;
/***
@author Guy Hassan
ID : 307845032
Campus : Ashdod
 */
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.SwingWorker;

import VehicleAgency.interfaceVehicle;

public class MenuFrame extends JFrame 
{
	private static MenuFrame singletonCheck = null;
	private JPanel panel;
	private ArrayList<JButton> arrayButtons = new ArrayList<JButton>();
	private static ImageIcon[] imgArr = {new ImageIcon("img\\buyvehicle.png"),new ImageIcon("img\\testdrive.PNG"),
			new ImageIcon("img\\changeflag.png"),new ImageIcon("img\\setkm.png"),new ImageIcon("img\\addvehicle.png"),new ImageIcon("img\\report.png"),new ImageIcon("img\\exit.png")};
	private static ArrayList<JRadioButton> arrayRadio = new ArrayList<JRadioButton>();
	private ButtonGroup group = new ButtonGroup();
	private JPanel scrollPanel = new JPanel();
	private JScrollPane scroll = new JScrollPane(scrollPanel);
	private JLabel NoVehicleExist = new JLabel("There Are No Vehicles Available");
	private JLabel ChoseVehicle = new JLabel("~ Choose A Vehicle ~");
	static VehicleReportsFrame VehicleReportVar;
	private ArrayList<BuyVehicleYesOrNoFrame> fBuy = new ArrayList<BuyVehicleYesOrNoFrame>();
	private ArrayList<TextTestDriveFrame> fTestDrive = new ArrayList<TextTestDriveFrame>();
	private float totalKm;

	public MenuFrame() {
		
		//init new frame ----------------------------------------------------------------------------
		setTitle("Menu");
		setLayout(null);
		setBounds(10, 0, 600, 550);
		setLocationRelativeTo(null);//put the windows in the center
		setResizable(false);//cannot resize the windows
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close the windows when click X
		setVisible(true);
		
		//new panel for buttons ---------------------------------------------------------------------------
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 345, 600, 200);
		arrayButtons.add(new JButton("Buy Vehicle"));
		arrayButtons.add(new JButton("Test Drive"));
		arrayButtons.add(new JButton("Set Flag"));
		arrayButtons.add(new JButton("Reset KM"));
		arrayButtons.add(new JButton("Add Vehicle"));
		arrayButtons.add(new JButton("Vehicle Report"));
		arrayButtons.add(new JButton("Exit"));
		add(panel);
		
		//no vehicle exist label -------------------------------------------------------------------
		NoVehicleExist.setBounds(35, 50, 600, 250);
		NoVehicleExist.setFont(new Font(NoVehicleExist.getName(), Font.BOLD, 35));
		
		//click to choose label
		ChoseVehicle.setBounds(160, -20, 500,100);
		ChoseVehicle.setFont(new Font(NoVehicleExist.getName(), Font.BOLD, 30));
	
		//place panel on a scroll -------------------------------------------------------------------
		scrollPanel.setBounds(0, 0,600 ,250);
	
		//scroll panel ---------------------------------------------------------------
		scroll.setBounds(0, 50,600 , 290);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);//every image increases the scroll
		
		//initialize a scroll panel with a new object image on this frame 
		refreshMenuFrame();

		//resize the image to buttons size ----------------------------------------------------------------
		for(int i=0;i<imgArr.length;i++)
			imgArr[i] = resizeImage(imgArr[i],60,60);
		
		//arrange buttons ---------------------------------------------------------------------------------
		arrangeButton_andSetImg();

		totalKm = 0;
	//when you click X on the frame
	 this.addWindowListener(new java.awt.event.WindowAdapter() {
	        @Override
	        public void windowClosing(java.awt.event.WindowEvent windowEvent) {
	        	if(BuyVehicleYesOrNoFrame.count == 0 && TextTestDriveFrame.count == 0) {
					dispose();
					MainFrame.getInstance().dispose();
					if(VehicleReportsFrame.getInstance() != null)
						VehicleReportsFrame.getInstance().dispose();
				}
				else
					JOptionPane.showMessageDialog(null,"There are open Buy Vehicle or Test Drive windows\n");
	        		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
	        }
	    });
		
		// action listener about Menu Buttons ----------------------------------------------------------------
		for(int i=0;i<arrayButtons.size();i++)
		{	
			arrayButtons.get(i).addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) {
					boolean flag = false;
					//Buy Vehicle -------------------------------------------------------------------------------
					if(e.getActionCommand().equals("Buy Vehicle"))
					{
						if(MainVehicleAgency.arrV.size() != 0) {
							for(int i=0;i<arrayRadio.size();i++) 
									if(arrayRadio.get(i).isSelected())
									{
										if(!MainVehicleAgency.arrV.get(i).getStatus().equals("Test Drive")) {
											if(!MainVehicleAgency.arrV.get(i).getStatus().equals("Buy Vehicle"))
												fBuy.add(new BuyVehicleYesOrNoFrame(i));
											else
												JOptionPane.showMessageDialog(null,"~ This Vehicle With An Buy Frame Open ~");
										}	
										else JOptionPane.showMessageDialog(null,"~ This Vehicle In Test Drive Process ~");
										flag=!flag;
									}
							if(!flag) 
								JOptionPane.showMessageDialog(null, "No Vehicle Selected");
						}
						else
							JOptionPane.showMessageDialog(null, "~ There Are No Vehicles Available ~");
					}
					//Test Drive -------------------------------------------------------------------------------
					else if(e.getActionCommand().equals("Test Drive"))
					{
							if(MainVehicleAgency.arrV.size() != 0) 
							{
								for(int i=0;i<arrayRadio.size();i++) 
									if(arrayRadio.get(i).isSelected())
										{
											if(!MainVehicleAgency.arrV.get(i).getStatus().equals("Buy Vehicle")) {
												if(!MainVehicleAgency.arrV.get(i).getStatus().equals("Test Drive"))
													fTestDrive.add(new TextTestDriveFrame(i));
												else
													JOptionPane.showMessageDialog(null,"~ This Vehicle With An Test Drive Frame Open ~");
												
											}
											else JOptionPane.showMessageDialog(null,"~ This Vehicle In Buy Vehicle Process ~");
											flag = !flag;
										}
								if(!flag) 
									JOptionPane.showMessageDialog(null, "No Vehicle Selected");
							}
							else
								JOptionPane.showMessageDialog(null, "~ There Are No Vehicles Available ~");
					}
					//Set new Flag -------------------------------------------------------------------------------
					else if(e.getActionCommand().equals("Set Flag"))
					{
						if(MainVehicleAgency.arrM.size() != 0) 
							{
								FlagsFrame FlagsFrameVar = new FlagsFrame();
								FlagsFrameVar.setVisible(true);
							}
						else
							JOptionPane.showMessageDialog(null , "~ There Are No Marine Vehicles Available ~");
					}
					//Reset kilometer of all vehicle -------------------------------------------------------------------------------
					else if(e.getActionCommand().equals("Reset KM"))
					{
						if(MainVehicleAgency.arrV.size() != 0) {
							thread_SetFlag_BuyVehicle_ResetKM(-1, e.getActionCommand());
						}
						else
							JOptionPane.showMessageDialog(null, "~ There Are No Vehicles Available ~");
							
					}
					//return to the main frame -------------------------------------------------------------------------------
					else if(e.getActionCommand().equals("Add Vehicle"))
						MainFrame.getInstance().setVisible(true);
					else if(e.getActionCommand().equals("Vehicle Report")) {
						if(VehicleReportsFrame.getInstance() == null)
							VehicleReportVar = VehicleReportsFrame.createInstance();
						VehicleReportVar.setVisible(true);
					}
					else if(e.getActionCommand().equals("Exit"))
					{
						if(BuyVehicleYesOrNoFrame.count == 0 && TextTestDriveFrame.count == 0) {
							dispose();
							MainFrame.getInstance().dispose();
							if(VehicleReportsFrame.getInstance() != null)
								VehicleReportsFrame.getInstance().dispose();
						}
						else
							JOptionPane.showMessageDialog(null,"There are open Buy Vehicle or Test Drive windows\n");
					}
					
				}
			});
		}
	}
	
	/**
	 * this function remove from the test drive frame list if i finish with that
	 * @param index - for vehicle with test drive frame open 
	 */
	public void removeCurrentFrameTestDriveFromTheList(int index)
	{
		for(int i=0;i<fTestDrive.size();i++)
			if(fTestDrive.get(i).getIndex() == index)
			{
				fTestDrive.get(i).count -=(fTestDrive.get(i).count > 0)? 1 : 0;
				fTestDrive.get(i).dispose();
				fTestDrive.remove(i);
			}
	}
	/**
	 * this function remove from the buy frame list if i finish with that
	 * @param index - for vehicle with buy vehicle frame open 
	 */
	public void removeCurrentFrameBuyVehicleFromTheList(int index)
	{
		for(int i=0;i<fBuy.size();i++)
			if(fBuy.get(i).getIndex() == index)
				fBuy.remove(i);
	}

	/**
	 * when i delete a new vehicle and i had test drive or buy vehicle frame open in background i need to update the current index vehicle
	 * that why i need this function
	 * @param BuyFrameOrTestDrive - from where this function call
	 */
	public void updateIndexAfterDeleteVehicle(int Index)
	{
			for(int i=0;i<fBuy.size();i++)
				if(fBuy.get(i).getIndex() >= Index) 
					fBuy.get(i).decreasesIndex();
			for(int i=0;i<fTestDrive.size();i++)
				if(fTestDrive.get(i).getIndex() >= Index)
					fTestDrive.get(i).decreasesIndex();
	}

	/** -----------------------------------------------------------------------------
	 * arrange the buttons and set img
	 */
	public void arrangeButton_andSetImg()
	{
		int x = 100;
		for(int i =0;i<arrayButtons.size();i++)
		{
			if(i < arrayButtons.size()/2) 
				arrayButtons.get(i).setBounds(x , 0 , 130, 80);
			else 
				arrayButtons.get(i).setBounds(x , 90 , 130, 80);
			
			arrayButtons.get(i).setIcon(imgArr[i]);
			arrayButtons.get(i).setHorizontalTextPosition(JButton.CENTER);
			arrayButtons.get(i).setVerticalTextPosition(JButton.TOP);
			arrayButtons.get(i).setBackground(new Color(220,220,250));
			panel.add(arrayButtons.get(i));
			x += (i==2 )? -350 : 140;//tell the loop take a new line after 3 buttons	
		}
	}
	
	/**------------------------------------------------------------------------------
	 * set size to the image label
	 */
	public static ImageIcon resizeImage(ImageIcon img,int w , int h)
	{
		Image im = img.getImage();
		Image tmp =  im.getScaledInstance(w, h, Image.SCALE_REPLICATE);
		img = new ImageIcon(tmp);
		return img;
	}

	
	/**
	 * this function set a blue border on the image that i clicked
	 */
	public void setBorderWhenClickEachRadioButton()
	{
		for(int i=0; i<arrayRadio.size();i++)
			arrayRadio.get(i).addMouseListener(new MouseListener()
			{		
				@Override
				public void mouseReleased(MouseEvent e) {}
				@Override
				public void mousePressed(MouseEvent e) {}
				@Override
				public void mouseExited(MouseEvent e) {}
				@Override
				public void mouseEntered(MouseEvent e) {}
				@Override
				public void mouseClicked(MouseEvent e) {
					for(int i=0;i<arrayRadio.size();i++)
					{
						if(arrayRadio.get(i).isSelected())
							arrayRadio.get(i).setBackground(getColor(MainVehicleAgency.arrV.get(i).getColor()));
						else 
							arrayRadio.get(i).setBackground(null);
						}
				}
			});
	}
	
	
	/**
	 * this function refresh the Menu frame when i update something new in my code
	 */
	public void refreshMenuFrame()
	{
		add_remove_Labels();
		arrayRadio.clear();
		group.clearSelection();
		scrollPanel.removeAll();
		for (int i = 0; i < MainVehicleAgency.arrayImage.size(); i++)
		{
			add_remove_Labels();
			MainVehicleAgency.arrayImage.set(i,resizeImage(MainVehicleAgency.arrayImage.get(i), 270, 260));
			arrayRadio.add(new JRadioButton(MainVehicleAgency.arrayImage.get(i)));
			scrollPanel.add(arrayRadio.get(i)); 
			group.add(arrayRadio.get(i));
			arrayRadio.get(i).setToolTipText("<html><p width=\"500\">" +MainVehicleAgency.arrV.get(i).toString()+"</p></html>");
		}
		repaint_revalidate(scrollPanel);
		repaint_revalidate(scroll);
		repaint_revalidate(ChoseVehicle);
		repaint_revalidate(this);
		
		setBorderWhenClickEachRadioButton();
		
		if(VehicleReportsFrame.getInstance() != null)
			VehicleReportVar.refreshVehicleReportFrame();
	}
	/**
	 * if there are no vehicle exist in my list i remove the scroll panel and put label with 
	 * text accordingly otherwise i put the scroll panel again
	 */
	public void add_remove_Labels()
	{
		if(MainVehicleAgency.arrV.size() == 0)
		{
			add(NoVehicleExist);
			remove(scroll);
			remove(ChoseVehicle);
		}
		else {
		remove(NoVehicleExist);
		add(scroll);
		add(ChoseVehicle);}
	}
	
	public void repaint_revalidate(Component a)
	{
		a.revalidate();
		a.repaint();
	}
	/**
	 * that function delete all from all arrays the current object
	 * @param currentObject - that what i delete
	 */
	public void holdingObjectAndRemoveThem(int i)
	{	
		JOptionPane.showMessageDialog(null,"Congratulations On Buying The Vehicle: \n" + MainVehicleAgency.arrV.get(i).toString());
		MainVehicleAgency.arraySemaphoreLock.remove(i);
		MainVehicleAgency.arrayImage.remove(i);
		arrayRadio.remove(i);
		MainVehicleAgency.arrM.remove(MainVehicleAgency.arrV.get(i));
		MainVehicleAgency.arrV.remove(i);
		MenuFrame.getInstance().refreshMenuFrame();	
	}
	
/**
 * the function call when i need to start the message frame (Updating database… Please wait .)
 * @param currentVehicle - current vehicle that i use them
 * @param typeButton - name button that i clicked
 */
public void thread_SetFlag_BuyVehicle_ResetKM(int i,String typeButton){
		
		new SwingWorker<String,Object>()
		{
			public String doInBackground() 
			{
				Thread tr = new Thread(new MessageUpdateDatabase(-1));
				try {
					if(typeButton.equals("Set Flag")) {
						tr.start();
						tr.join();
						JOptionPane.showMessageDialog(null,"\n~ Flags is Change Successfully ~");
						refreshMenuFrame();
					}
					else if(typeButton.equals("Buy Vehicle"))
					{
							tr.start();
							tr.join();							
							holdingObjectAndRemoveThem(i);
							updateIndexAfterDeleteVehicle(i);
					}
					else if(typeButton.equals("Reset KM"))
					{
						tr.start();
						tr.join();
						MainVehicleAgency.resetKilometer();
						refreshMenuFrame();
						totalKm = 0;
						MainVehicleAgency.notifyObserver();
						JOptionPane.showMessageDialog(null,"~ The Kilometers updated by 0 ~");
					}
				}
				catch (InterruptedException e1) 
				{
					e1.printStackTrace();
				}
				return null;
			}
			}.execute();
    }

	/**
	 * createInstance singleton method , help me to create a single object from MenuFrame and use only him
	 * @return the instance from this class
	 */
	public static MenuFrame createInstance() {
		if(singletonCheck == null)
			singletonCheck = new MenuFrame();
		return singletonCheck;
	}
	/**
	 * getInstace method, return the singleton variable , help me to understand if i have an open frame from this class
	 * @return the instance of variable class
	 */
	public static MenuFrame getInstance(){return singletonCheck;}

	public float getTotalKm()
	{
		return totalKm;
	}
	public void setTotalKm(float km)
	{
		totalKm += km;
	}
	/**
	 * check which color get in col parameter and return the Color object
	 * @param col - name color
	 * @return color
	 */
	private Color getColor(String col) {
	    switch (col.toLowerCase()) {
	    case "black":
	        return Color.BLACK;
	    case "blue":
	        return Color.BLUE;
	    case "green":
	        return Color.GREEN;
	    case "yellow":
	        return Color.YELLOW;
	    case "orange":
	        return Color.ORANGE;
	    case "pink":
	        return Color.PINK;
	    case "red":
	        return  Color.RED;
	    case "white":
	       return Color.WHITE;
	        }
	    return null;
	    }
}
