package VehicleGUI;
/***
@author Guy Hassan
ID : 307845032
Campus : Ashdod
 */
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import VehicleAgency.interfaceMarineVehicles;
import VehicleAgency.interfaceVehicle;
public class MainVehicleAgency 
{
	public static ArrayList<interfaceMarineVehicles> arrM = new ArrayList<interfaceMarineVehicles>();/***create a marine vehicle array to save the polymorphism*/
	public static ArrayList<interfaceVehicle> arrV = new ArrayList<interfaceVehicle>();/***create a vehicle array*/
	public static ArrayList<ImageIcon> arrayImage = new ArrayList<ImageIcon>();
	public static ArrayList<Semaphore> arraySemaphoreLock = new ArrayList<Semaphore>();
	static MainFrame MainFrameVar = null;
	
	public static void main(String[] args) 
	{
		MainFrameVar = MainFrame.createInstance();
	}

	public static void setNewFlag(String flag)
	{
			for(int i=0;i<arrM.size();i++)
				arrM.get(i).setFlag(flag);
			
	}
	public static void resetKilometer()
	{
		for(int i=0;i<arrV.size();i++)
			arrV.get(i).setKM(0.0);
	}
	public static void UpdateKilometer(interfaceVehicle currentVehicle, double km)
	{
		currentVehicle.Move(km);
	}
	
	public static void notifyObserver()
	{
		MainFrameVar.update();
	}

}
