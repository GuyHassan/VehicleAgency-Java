package VehicleGUI;
/***
@author Guy Hassan
ID : 307845032
Campus : Ashdod
 */
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.ReentrantLock;

import javax.sql.PooledConnection;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import VehicleAgency.airVehicles;
import VehicleAgency.interfaceAirVehicle;
import VehicleAgency.interfaceLandVehicles;
import VehicleAgency.interfaceMarineVehicles;
import VehicleAgency.interfaceVehicle;
import VehicleAgency.landVehicles;
import VehicleAgency.marineVehicles;

public class TextTestDriveFrame extends JFrame  {
	
	private double km;
	private static final Semaphore vehicleTypesLocks = new Semaphore(7); //thread pool system , create a semaphore with a 7 permit and when the avaiblePermit is equal to 0 the system waiting until one is realse
	private JTextField kmText = new JTextField();
	private JLabel ProccesLabel = new JLabel("These Vehicles Are In The Process");
	JButton ButtonOk = new JButton("Ok");
	private int index;
	public static int count = 0;
	
	public TextTestDriveFrame(int i) {
		setTitle("Test Drive");
		setLayout(null);
		setBounds(0, 0, 250, 120);
		setLocationRelativeTo(null);//put the windows in the center
		setResizable(false);//cannot resize the windows
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //close the windows when click X
		
		JLabel NameLabel = new JLabel("Enter Kilometer: ");
		NameLabel.setBounds(30, -3,100 , 40);
		add(NameLabel);
		
		ProccesLabel.setBounds(25, 63, 250, 30);
		ProccesLabel.setForeground(Color.red);
		add(ProccesLabel);
		ProccesLabel.setVisible(false);
		
		kmText.setBounds(140, 10, 80, 20);
		add(kmText);
		
		ButtonOk.setBounds(75, 40, 80, 25);
		add(ButtonOk);
		
		setVisible(true);
		
		count++;
		index = i;
		MainVehicleAgency.arrV.get(index).setStatus("Test Drive");
		MenuFrame.getInstance().refreshMenuFrame();
		//when you click on X in the title frame -----------------------------------------
		 this.addWindowListener(new java.awt.event.WindowAdapter() {
		        @Override
		        public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		           count--;
		           if(MainVehicleAgency.arraySemaphoreLock.size() != 0)
		        	   MainVehicleAgency.arraySemaphoreLock.get(i).release();
		           MenuFrame.getInstance().removeCurrentFrameTestDriveFromTheList(index);
		           MainVehicleAgency.arrV.get(index).setStatus("In Stock");
		        }
		    });
		 
		 new SwingWorker<Object, Object>(){

			protected Object doInBackground() {
				try {
				if(MainVehicleAgency.arraySemaphoreLock.get(i).availablePermits() == 1)
				{
					MainVehicleAgency.arraySemaphoreLock.get(i).acquire();
					ButtonOkAction();
				}
				else {
					 ProccesLabel.setVisible(true);
					 MainVehicleAgency.arraySemaphoreLock.get(i).acquire();
					 ProccesLabel.setVisible(false);
					 ButtonOkAction();				 
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
	 * this function is a action listener of button "ok" on frame test drive , when i click ok i need to lock the current type vehicle 
	 * and update the other system
	 */
	public void ButtonOkAction()
	{
		ButtonOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				new SwingWorker<Object,Object>()
				{
					public String doInBackground() 
					{	
               			try {
               					ProccesLabel.setText("     ~ No Delegates Available ~");
               					ProccesLabel.setVisible(true);
               					vehicleTypesLocks.acquire();//thread pool , acquire until the amount of test drive is 7 , when our avaible permit is equal to 0 the system is waiting until 1 realse
               					ProccesLabel.setVisible(false);
	               				km = Double.parseDouble(kmText.getText());      	               				
	               				MenuFrame.getInstance().removeCurrentFrameTestDriveFromTheList(index);
								MainVehicleAgency.UpdateKilometer(MainVehicleAgency.arrV.get(index),km);
	               				JFrame fr = waitFrame();
	               				fr.setVisible(true);
								Thread.sleep((100*(int)km));
	      						fr.dispose();
               				}     
               			catch (InterruptedException ex) 
               			{
							ex.printStackTrace();
						}
               			catch(NumberFormatException a)
               			{
               				JOptionPane.showMessageDialog(null,"Something Wrong ! Enter Numbers Only .");
               			}
               			finally 
						{
               				vehicleTypesLocks.release();
               				MainVehicleAgency.arrV.get(index).setStatus("In Stock");
							MenuFrame.getInstance().setTotalKm((float) km);
							MenuFrame.getInstance().refreshMenuFrame();	
							MainVehicleAgency.notifyObserver();
							if(MainVehicleAgency.arraySemaphoreLock.size() != 0)
								MainVehicleAgency.arraySemaphoreLock.get(index).release();
						}
						return null;
					}
				}.execute();
			}
		});
		
	}
	/**
	 * this function open a new frame "wait" and waiting some time until the system is continue
	 * @return frame
	 */
	public JFrame waitFrame()
	{
		JFrame tmp = new JFrame("Waiting");
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
		
		return tmp;
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
