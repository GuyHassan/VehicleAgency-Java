package Decorator;

import VehicleAgency.interfaceVehicle;
/**
 * class color decorator help me to add a new parameter to my vehicles without to get in my code and change all my code
 * @author Guy Hassan
 * ID : 307845032
 * Campus : Ashdod
 */
public class colorDecorator extends VehicleDeco {
	String color;
	public colorDecorator(interfaceVehicle tmpVehicle) {
		super(tmpVehicle);
	}
	@Override
	public String toString() {
		return vehicleTmp.toString() + ", His Color: " + color;
	}
	public void setColor(String c)
	{
		color = c;
	}
	public String getColor()
	{
		return color;
	}
	@Override
	public String getStatus() {
		return vehicleTmp.getStatus();
			}
	@Override
	public void setStatus(String s) {
		vehicleTmp.setStatus(s);
	}
}
