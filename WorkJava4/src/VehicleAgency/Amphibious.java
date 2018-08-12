package VehicleAgency;
/**
 * Amphibious class , he need to inehrit from 2 fathers , so we do a implement from each of fathers and do a tell him to implement them
 * and we hold 2 object from each father and initialize the constructor of them .
 * @author GuyHassan
 *
 */
public class Amphibious implements interfaceVehicle , interfaceMarineVehicles , interfaceLandVehicles , aMotorizedObject{

	private float AverageFuel;
	private float AverageOfLifeTimeEngine;
	private marineVehicles marine;
	private landVehicles land;
	
	public Amphibious(float averageFuel, float averageOfLifeTimeEngine,int maxPassenger,int maxSpeed,String model,boolean direction,String flag,int wheels) {
		marine = new marineVehicles(maxPassenger, maxSpeed, model, direction, flag);
		land = new landVehicles(maxPassenger, maxSpeed, model, wheels, "Paved");
		AverageFuel = averageFuel;
		AverageOfLifeTimeEngine = averageOfLifeTimeEngine;
	}

	@Override
	public float getAverageFuel() {
		// TODO Auto-generated method stub
		return AverageFuel;
	}

	@Override
	public boolean setAverageFuel(float AverOil) {
		this.AverageFuel=AverOil;
		return true;
	}

	@Override
	public float getAverageOfLifeTimeEngine() {
		return AverageOfLifeTimeEngine;
	}
	public void setAverageOfLifeTimeEngine(float avgLifeEngine)
	{
		this.AverageOfLifeTimeEngine=avgLifeEngine;
	}
	@Override
	public int getWheelsAmount() {
		
		return land.getWheelsAmount();
	}

	@Override
	public void setWheelsAmount(int wheelsAmount) {
		land.setWheelsAmount(wheelsAmount);
		
	}

	@Override
	public String getTypeRoad() {
		
		return land.getTypeRoad();
	}

	@Override
	public void setTypeRoad(String typeRoad) {
		land.setTypeRoad(typeRoad);
		
	}

	@Override
	public boolean getDirectionWind() {
		
		return marine.getDirectionWind();
	}

	@Override
	public void setDirectionWind(boolean directionWind) {
		marine.setDirectionWind(directionWind);
		
	}

	@Override
	public String getFlag() {
	
		return marine.getFlag();
	}

	@Override
	public void setFlag(String flag) {
		marine.setFlag(flag);
		
	}

	@Override
	public void Move(double km) {
		land.Move(km);
	}

	@Override
	public void setKM(double km) {
		land.setKM(km);
		
	}

	@Override
	public int getMaxPassenger() {
		
		return land.getMaxPassenger();
	}

	@Override
	public void setMaxPassenger(int maxPassenger) {
		land.setMaxPassenger(maxPassenger);
		
	}

	@Override
	public int getMaxSpeed() {
		return land.getMaxSpeed();
	}

	@Override
	public void setMaxSpeed(int maxSpeed) {
		land.setMaxSpeed(maxSpeed);
		
	}

	@Override
	public String getModel() {
		
		return land.getModel();
	}

	@Override
	public void setModel(String model) {
		land.setModel(model);
		
	}

	@Override
	public double getKm() {
		
		return land.getKm();
	}
	public String toString()
	{
		return "\nAmphibious:\n" + "----------\n" + marine.toString()
				+"\nEngine Fuel: " + AverageFuel + "L," + " Lifetime of "
				+ AverageOfLifeTimeEngine + " years."; 
	}
	public boolean equals(Object other) {
		if (other instanceof Amphibious) {
			return ((this.AverageFuel == ((Amphibious) other).AverageFuel)
					&& (this.AverageOfLifeTimeEngine == ((Amphibious) other).AverageOfLifeTimeEngine) && super.equals(other));
		}
		return false;
	}

	@Override
	public String getColor() {
		return null;
	}

	@Override
	public void setColor(String c) {
		//do nothing , override from the interfaceVehicle implement
	}

	@Override
	public String getStatus() {
		return null;
	}

	@Override
	public void setStatus(String s) {
		//do nothing , override from the interfaceVehicle implement
	}

}
