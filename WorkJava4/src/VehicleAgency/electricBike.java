package VehicleAgency;

public class electricBike extends landVehicles implements aMotorizedObject {
	
	private float AverageFuel;
	private float AverageOfLifeTimeEngine;
	/***constructor*/
	public  electricBike(int maxPassenger,int maxSpeed, String model, float AvOfLTEngine,String road) {
		super(maxPassenger, maxSpeed, model, 2, road);
		this.AverageFuel = 20;
		this.AverageOfLifeTimeEngine = AvOfLTEngine;
	}
	/***return average fuel*/
	public float getAverageFuel() {return AverageFuel;}
	/***set new average fuel*/
	public boolean setAverageFuel(float AverFuel) {
		if (AverFuel >= 0) {
			this.AverageFuel = AverFuel;
			return true;
		}
		return false;
	}
	/***return life engine */
	public float getAverageOfLifeTimeEngine() {return AverageOfLifeTimeEngine;}

	/***return the parameter with string on this class */
	public String toString() {
		return "\nElectric Bike:\n" + "----------------\n" + super.toString() + ", Engine Oil: " + AverageFuel + "L," + " Lifetime of "
				+ AverageOfLifeTimeEngine + " years."; }
	/***return average fuel */
	public float getAverOil() {return AverageFuel;}
	/**
	 * equal function , check if the object other equal to local object
	 * @param other - object from the son
	 * @return true if the all parameter is equal to the object "other" and call to the father with "super" function
	 */
	public boolean equals(Object other) {
		if (other instanceof electricBike) {
			return ((this.AverageFuel == ((electricBike) other).AverageFuel)
					&& (this.AverageOfLifeTimeEngine == ((electricBike) other).AverageOfLifeTimeEngine) && super.equals(other));
		}
		return false;
	}

}
