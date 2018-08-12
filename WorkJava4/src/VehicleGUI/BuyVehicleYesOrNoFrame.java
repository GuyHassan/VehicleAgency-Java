package VehicleGUI;
/***
@author Guy Hassan
ID : 307845032
Campus : Ashdod
 */
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingWorker;

import VehicleAgency.interfaceVehicle;

public class BuyVehicleYesOrNoFrame extends JFrame {
	
	public static int count = 0;	
	private JButton ButtonYes = new JButton("Yes");
	private JButton ButtonNo = new JButton("No");
	private JLabel ProccesLabel = new JLabel("These Vehicles Are In The Process");
	private int index;

	public BuyVehicleYesOrNoFrame(int i) {
		setTitle("Buy Vehicle");
		setLayout(null);
		setBounds(0, 0, 250, 130);
		setLocationRelativeTo(null);//put the windows in the center
		setResizable(false);//cannot resize the windows
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //close the windows when click X
		
		JLabel NameLabel = new JLabel("Are you sure about buying vehicles?");
		NameLabel.setBounds(20, 0,230 , 40);
		add(NameLabel);
		
		ButtonYes.setBounds(30, 45, 70, 25);
		add(ButtonYes);
		
		ButtonNo.setBounds(135, 45, 70, 25);
		add(ButtonNo);
		
		ProccesLabel.setBounds(25, 72, 250, 30);
		ProccesLabel.setForeground(Color.red);
		add(ProccesLabel);
		ProccesLabel.setVisible(false);
		
		count++;
		index = i;
		waitFrame();
		MainVehicleAgency.arrV.get(index).setStatus("Buy Vehicle");
		MenuFrame.getInstance().refreshMenuFrame();
		//when you click X on the frame
		 this.addWindowListener(new java.awt.event.WindowAdapter() {
		        @Override
		        public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		           count--;
		           MainVehicleAgency.arraySemaphoreLock.get(i).release();
		           MenuFrame.getInstance().removeCurrentFrameBuyVehicleFromTheList(index);
		           MainVehicleAgency.arrV.get(index).setStatus("In Stock");
		        }
		    });
		 
		 new SwingWorker<Object, Object>(){

			protected Object doInBackground() {
				try {
				if(MainVehicleAgency.arraySemaphoreLock.get(i).availablePermits() == 1)
				{
					MainVehicleAgency.arraySemaphoreLock.get(i).acquire();
					ButtonYesOrNoAction();
				}
				 else {
					 ProccesLabel.setVisible(true);
					 MainVehicleAgency.arraySemaphoreLock.get(i).acquire();
					 ButtonYesOrNoAction();
					 ProccesLabel.setVisible(false);
				 }
				}
				catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				return null;
			}
		 }.execute();

	}
	
	/**
	 * action listener to the button yes or no
	 */
	public void ButtonYesOrNoAction()
	{
		ButtonYes.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) {
				try {
					MenuFrame.getInstance().thread_SetFlag_BuyVehicle_ResetKM(index,"Buy Vehicle");
					MainVehicleAgency.arraySemaphoreLock.get(index).release();
					MenuFrame.getInstance().removeCurrentFrameBuyVehicleFromTheList(index);
					MenuFrame.getInstance().removeCurrentFrameTestDriveFromTheList(index);
					count -= (count > 0)? 1 : 0;
					MainVehicleAgency.arrV.get(index).setStatus("In Stock");
					dispose();
				}
				catch(IndexOutOfBoundsException a)
				{
					System.out.println(a.getMessage());
				}
			}
		});
		ButtonNo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				count -= (count>0)? 1 : 0;
				MainVehicleAgency.arraySemaphoreLock.get(index).release();
				MenuFrame.getInstance().removeCurrentFrameBuyVehicleFromTheList(index);
				MainVehicleAgency.arrV.get(index).setStatus("In Stock");
				dispose();
			}
		});
	}
	/**
	 * 	this frame open and wait 5 - 10 second until the yes no frame open
	 */
	public void waitFrame()
	{
		JFrame tmp = new JFrame("Wait");
		tmp.setLayout(null);
		tmp.setBounds(0, 0, 200, 100);
		tmp.setLocationRelativeTo(null);//put the windows in the center
		tmp.setResizable(false);//cannot resize the windows
		tmp.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); //close the windows when click X
		
		JLabel wait = new JLabel("Waiting ...");
		wait.setBounds(70, -20, 160,60);
		
		
		Icon icon = new ImageIcon("img\\waiting2.gif");
		JLabel iconLabel = new JLabel(icon);
		iconLabel.setBounds(0, -5, 180, 100);
		iconLabel.add(wait);
		
		tmp.add(iconLabel);
		tmp.add(wait);
		
		//swing worker help me to work in background gui
		new SwingWorker<Object, Object>(){

			@Override
			protected Object doInBackground() throws Exception {
				tmp.setVisible(true);
				Random rand = new Random();
				Thread.sleep(rand.nextInt(10000) + 5000);	
				setVisible(true);
				tmp.dispose();
				return null;
			}
			
		}.execute();
		
	}
	
	/**
	 * 
	 * @return - the index of current vehicle
	 */
	public int getIndex()
	{
		return index;
	}
	/**
	 * update the current vehicle index - decreases 1 after delete a vehicles from the array
	 */
	public void decreasesIndex()
	{
		index-=1;
	}
	

}
