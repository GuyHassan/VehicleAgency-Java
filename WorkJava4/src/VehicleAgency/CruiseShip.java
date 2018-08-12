package VehicleAgency;
/**
 * cruise ship class , inherit from marine vehicle and do a implments for aMotorized and product for commercial use
 * @author GuyHassan
 *
 */
public class CruiseShip extends marineVehicles implements aMotorizedObject , productForCommercialUse{
	private float AverageFuel;
	private float AverageOfLifeTimeEngine;
	public CruiseShip(int maxPassenger, int maxSpeed, String model, String flag,float avFuel,float avLifeEngine) {
		super(maxPassenger, maxSpeed, model, true, flag);
		AverageFuel = avFuel;
		AverageOfLifeTimeEngine = avLifeEngine;
	}

	@Override
	public String getInformationCar() {
		return "Unlimted";
	}

	@Override
	public float getAverageFuel() {
		return AverageFuel;
	}

	@Override
	public boolean setAverageFuel(float AverFuel) {
		if(AverFuel>=0)
		{
			this.AverageFuel=AverFuel;
			return true;
		}
		return false;
	}

	@Override
	public float getAverageOfLifeTimeEngine() {
		return AverageOfLifeTimeEngine;
	}
	public String toString()
	{
		return "\nCruise Ship:\n" + "-------------\n" + super.toString() + "\nLicense Type: " + getInformationCar()+ ", "
				+"\nEngine Fuel: " + AverageFuel + "L," + " Lifetime of "
				+ AverageOfLifeTimeEngine + " years."; 
	}
	
	public boolean equals(Object other) {
		if (other instanceof CruiseShip) {
			return ((this.AverageFuel == ((CruiseShip) other).AverageFuel)
					&& (this.AverageOfLifeTimeEngine == ((CruiseShip) other).AverageOfLifeTimeEngine) && super.equals(other));
		}
		return false;
	}
}
