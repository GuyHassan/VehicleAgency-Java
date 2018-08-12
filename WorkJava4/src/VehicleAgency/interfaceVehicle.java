package VehicleAgency;

import java.awt.Color;

public interface interfaceVehicle 
{
	public void Move(double km);
	public void setKM(double d);
	public int getMaxPassenger();
	public void setMaxPassenger(int maxPassenger);
	public int getMaxSpeed();
	public void setMaxSpeed(int maxSpeed);
	public String getModel();
	public void setModel(String model);
	public double getKm();
	public String getColor();
	public void setColor(String c);
	public String getStatus();
	public void setStatus(String s);
}
