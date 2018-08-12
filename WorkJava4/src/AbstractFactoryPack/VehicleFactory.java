package AbstractFactoryPack;

import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Decorator.colorDecorator;
import Decorator.statusDecorator;
import VehicleAgency.Amphibious;
import VehicleAgency.Bicycle;
import VehicleAgency.CruiseShip;
import VehicleAgency.Frigate;
import VehicleAgency.GamePlane;
import VehicleAgency.Jeep;
import VehicleAgency.SpyPlane;
import VehicleAgency.electricBike;
import VehicleAgency.hybridPlane;
import VehicleAgency.interfaceVehicle;
import VehicleGUI.MainVehicleAgency;

/**
 * this class extend from abstract factory , and he manage all my vehicles , when i get type and parameter i create new vehicle and return them !
 * @author Guy Hassan
 * ID : 307845032
 * Campus : Ashdod
 *
 */
public class VehicleFactory extends AbstractFactory {
	private interfaceVehicle vehicleTmp;
	/**
	 * create new vehicle in factory and return them
	 */
	public interfaceVehicle getNewVehicle(String typeVehicle, ArrayList<JTextField> text, ArrayList<JRadioButton> wind,JComboBox<String> color) 
		{
			if((!isEmptyFields(text)) && color.getSelectedIndex()!=0 && (wind == null || (wind.get(0).isSelected() || wind.get(1).isSelected()))) {
				try {
					switch(typeVehicle)
					{
						case "Jeep":
							vehicleTmp = new Jeep(Integer.parseInt(text.get(0).getText()),text.get(1).getText(),Float.parseFloat(text.get(2).getText()),Float.parseFloat(text.get(3).getText()));
							break;
						case "Frigate":
							Frigate F = new Frigate (Integer.parseInt(text.get(0).getText()),Integer.parseInt(text.get(1).getText()),text.get(2).getText(),wind.get(0).isSelected());
							vehicleTmp = F;
							MainVehicleAgency.arrM.add(F);
							break;
						case "SpyPlane":
							vehicleTmp = new SpyPlane(text.get(0).getText()); 
							break;
						case "GamePlane":
							vehicleTmp = new GamePlane();
							break;
						case "Amphibious":
							Amphibious A = new Amphibious(Float.parseFloat(text.get(3).getText()), Float.parseFloat(text.get(4).getText()), Integer.parseInt(text.get(0).getText()), Integer.parseInt(text.get(1).getText()), text.get(2).getText(), wind.get(0).isSelected(), text.get(6).getText(), Integer.parseInt(text.get(5).getText()));
							vehicleTmp = A;
							MainVehicleAgency.arrM.add(A);
							break;
						case "Bicycle":
							vehicleTmp = new Bicycle(Integer.parseInt(text.get(0).getText()),Integer.parseInt(text.get(1).getText()),text.get(2).getText(),text.get(3).getText());
							break; 
						case "CruiseShip":
							CruiseShip C = new CruiseShip(Integer.parseInt(text.get(0).getText()),Integer.parseInt(text.get(1).getText()),text.get(2).getText(), text.get(5).getText(), Float.parseFloat(text.get(3).getText()),  Float.parseFloat(text.get(4).getText()));
							vehicleTmp = C;
							break;
						case "HybridPlane":
							hybridPlane H = new hybridPlane(Float.parseFloat(text.get(3).getText()), Float.parseFloat(text.get(4).getText()), Integer.parseInt(text.get(0).getText()), Integer.parseInt(text.get(1).getText()), text.get(2).getText(), wind.get(0).isSelected(), text.get(6).getText(), Integer.parseInt(text.get(5).getText()));
							vehicleTmp = H;
							MainVehicleAgency.arrM.add(H);
							break;
						case "ElectricBike":
							vehicleTmp = new electricBike(Integer.parseInt(text.get(0).getText()),Integer.parseInt(text.get(1).getText()),text.get(2).getText(),Float.parseFloat(text.get(4).getText()),text.get(3).getText());
							break; 
					}
					setDecoratorColorStatus(color);
					return vehicleTmp;
				}
				catch(NumberFormatException e)
				{
					JOptionPane.showMessageDialog(null, "Something Worng !! Try Again");
				}
			}
		else
			JOptionPane.showMessageDialog(null, "~ Not All Parameters Have Been Inserted ~");	
		return null;
		
	}
	/**
	 * check if one of field is empty
	 * @param text - array text field
	 * @return boolean
	 */
	private boolean isEmptyFields(ArrayList<JTextField> text)
	{
		if(text == null)
			return false;
		for(int i=0;i<text.size();i++)
			if(text.get(i).getText().equals(""))
				return true;
		return false;
	}
	/**
	 * create the decorator color and status about each current vehicle
	 * @param color
	 */
	private void setDecoratorColorStatus(JComboBox<String> color)
	{
		vehicleTmp = new colorDecorator(vehicleTmp);
		vehicleTmp.setColor(color.getSelectedItem().toString());
		vehicleTmp = new statusDecorator(vehicleTmp);
		vehicleTmp.setStatus("In Stock");
	}
	




}
