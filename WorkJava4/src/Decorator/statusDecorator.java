package Decorator;

import VehicleAgency.interfaceVehicle;
/**
 * class status decorator help me to add a new parameter to my vehicle without to get in my code and change all
 * @author Guy Hassan
 * ID : 307845032
 * Campus : Ashdod
 */
public class statusDecorator extends VehicleDeco {
	private String status;
	public statusDecorator(interfaceVehicle tmpVehicle) {
		super(tmpVehicle);
		
	}
	public String toString() {
		return vehicleTmp.toString() + ", Status: " + status;
	}
	@Override
	public String getColor() {
		return vehicleTmp.getColor();
	}

	@Override
	public void setColor(String c) {
		vehicleTmp.setColor(c);
	}

	@Override
	public String getStatus() {
		return status;
	}

	@Override
	public void setStatus(String s) {
		status = s;
	}

}
