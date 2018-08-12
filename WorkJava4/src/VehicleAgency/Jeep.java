package VehicleAgency;

public class Jeep extends landVehicles implements aMotorizedObject, productForCommercialUse {

	private float AverageFuel;
	private float AverageOfLifeTimeEngine;
	/***constructor*/
	public Jeep(int maxSpeed, String model, float AverageFuel, float AvOfLTEngine) {
		super(5, maxSpeed, model, 4, "Dirt");
		this.AverageFuel = AverageFuel;
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
	/***return License type*/
	public String getInformationCar() {return "Mini";} 
	/***return the parameter with string on this class */
	public String toString() {
		return "\nJeep:\n" + "------\n" + super.toString() + "\nEngine Oil: " + AverageFuel + "L," + " Lifetime of "
				+ AverageOfLifeTimeEngine + " years."; }
	/***return average fuel */
	public float getAverOil() {return AverageFuel;}
	/**
	 * equal function , check if the object other equal to local object
	 * @param other - object from the son
	 * @return true if the all parameter is equal to the object "other" and call to the father with "super" function
	 */
	public boolean equals(Object other) {
		if (other instanceof Jeep) {
			return ((this.AverageFuel == ((Jeep) other).AverageFuel)
					&& (this.AverageOfLifeTimeEngine == ((Jeep) other).AverageOfLifeTimeEngine) && super.equals(other));
		}
		return false;
	}
}
