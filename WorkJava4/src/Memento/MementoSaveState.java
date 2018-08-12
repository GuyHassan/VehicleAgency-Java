package Memento;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import javax.swing.ImageIcon;

import VehicleAgency.interfaceMarineVehicles;
import VehicleAgency.interfaceVehicle;
import VehicleGUI.MainVehicleAgency;
import VehicleGUI.MenuFrame;
/**
 * memento class that help me to save the current state when user click on button save state
 * @author Guy Hassan
 * ID : 307845032
 * Campus : Ashdod
 *
 */
public class MementoSaveState {
	private ArrayList<interfaceMarineVehicles> arrayMarine = new ArrayList<interfaceMarineVehicles>();
	private ArrayList<interfaceVehicle> arrVehicle = new ArrayList<interfaceVehicle>();
	private ArrayList<ImageIcon> arrayImages = new ArrayList<ImageIcon>();
	private ArrayList<Semaphore> arraySemaphoreLocks = new ArrayList<Semaphore>();
	
	public MementoSaveState() {
		arrVehicle = new ArrayList<>(MainVehicleAgency.arrV);
		arrayMarine = new ArrayList<>(MainVehicleAgency.arrM);
		arrayImages = new ArrayList<>(MainVehicleAgency.arrayImage);
		arraySemaphoreLocks = new ArrayList<>(MainVehicleAgency.arraySemaphoreLock);
	}
	/**
	 * that give to me the current state that exist in this object
	 */
	public void getTheCurrentState()
	{
		MainVehicleAgency.arrV = new ArrayList<>(arrVehicle);
		MainVehicleAgency.arrM = new ArrayList<>(arrayMarine);
		MainVehicleAgency.arrayImage = new ArrayList<>(arrayImages);
		MainVehicleAgency.arraySemaphoreLock = new ArrayList<>(arraySemaphoreLocks);
		MenuFrame.getInstance().refreshMenuFrame();
	}
}
