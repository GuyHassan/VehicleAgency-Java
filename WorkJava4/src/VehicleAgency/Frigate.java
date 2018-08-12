package VehicleAgency;

public class Frigate extends marineVehicles implements aMotorizedObject {
	private float averageFuel;
	private float averageOfLifeTimeEngine;
	/***constructor*/
	public Frigate(int maxPassenger, int maxSpeed, String model, boolean direction) {
		super(maxPassenger, maxSpeed, model, direction, "israel");
		averageFuel = 500;
		averageOfLifeTimeEngine = 4;

	}
	/***return the local average fuel*/
	public float getAverageFuel() {	return averageFuel;}
	public boolean setAverageFuel(float AverOil) {/***set a new average fuel*/
		if (AverOil >= 0) {
			averageFuel = AverOil;
			return true;
		}
		return false;
	}
	/***return the life engine*/
	public float getAverageOfLifeTimeEngine() {return averageOfLifeTimeEngine;}
	/***return the parameter with string on this class*/
	public String toString() {
		return "\nFrigate:\n"+ "--------\n" + super.toString() + "\nEngine: " + averageFuel + "L, " + "Lifetime of a "
				+ averageOfLifeTimeEngine + " years";
	}
	/**
	 * equal function , check if the object other equal to local object
	 * @param other - object from the son
	 * @return true if the all parameter is equal to the object "other" and call to the father with "super" function
	 */
	public boolean equals(Object other) {
		if (other instanceof Frigate) 
			return (this.averageFuel == ((Frigate) other).averageFuel) && (this.averageOfLifeTimeEngine == ((Frigate) other).averageOfLifeTimeEngine) && super.equals(other);
		return false;
	}
}
