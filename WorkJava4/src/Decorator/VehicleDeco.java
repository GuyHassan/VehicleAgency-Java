package Decorator;


import VehicleAgency.interfaceVehicle;
/**
 * decorator abstract class , the father of all my decorator that i need , he implement from interfacevehicle to add parameters to my vehicle
 * @author Guy Hassan
 * ID : 307845032
 * Campus : Ashdod
 */
public abstract class VehicleDeco implements interfaceVehicle {
	protected interfaceVehicle vehicleTmp;
	public VehicleDeco(interfaceVehicle tmpVehicle)
	{
		this.vehicleTmp = tmpVehicle;
	}
	@Override
	public void Move(double km) {
		vehicleTmp.Move(km);
	}

	@Override
	public void setKM(double d) {
		vehicleTmp.setKM(d);
	}

	@Override
	public int getMaxPassenger() {
		return vehicleTmp.getMaxPassenger();
	}

	@Override
	public void setMaxPassenger(int maxPassenger) {
		vehicleTmp.setMaxPassenger(maxPassenger);
	}

	@Override
	public int getMaxSpeed() {
		return vehicleTmp.getMaxSpeed();
	}

	@Override
	public void setMaxSpeed(int maxSpeed) {
		vehicleTmp.setMaxSpeed(maxSpeed);
	}

	@Override
	public String getModel() {
		return vehicleTmp.getModel();
	}

	@Override
	public void setModel(String model) {
		vehicleTmp.setModel(model);
	}

	@Override
	public double getKm() {
		return vehicleTmp.getKm();
	}

}
