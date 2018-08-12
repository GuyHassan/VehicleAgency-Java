package AbstractFactoryPack;

import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import VehicleAgency.interfaceVehicle;
/**
 * this is abstract factory , that is manage all my vehicle factory 
 * @author Guy Hassan
 * ID : 307845032
 * Campus : Ashdod
 *
 */
public abstract class AbstractFactory {
	
	public abstract interfaceVehicle getNewVehicle(String typeVehicle,ArrayList<JTextField> text,ArrayList<JRadioButton> wind,JComboBox<String> color);
	
}
