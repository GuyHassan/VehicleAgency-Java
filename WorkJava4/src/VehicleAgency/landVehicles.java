package VehicleAgency;

public class landVehicles extends Vehicles implements interfaceLandVehicles{
	private int wheelsAmount;
	private String typeRoad;
	/***constructor*/
	public landVehicles(int maxPassenger, int maxSpeed, String model, int wheels, String road) {
		super(maxPassenger, maxSpeed, model);
		wheelsAmount = wheels;
		typeRoad = road;
	}
	
	public int getWheelsAmount() {
		return wheelsAmount;
	}

	public void setWheelsAmount(int wheelsAmount) {
		this.wheelsAmount = wheelsAmount;
	}

	public String getTypeRoad() {
		return typeRoad;
	}

	public void setTypeRoad(String typeRoad) {
		this.typeRoad = typeRoad;
	}

	/***return the parameter with string on this class*/
	public String toString() {
		return super.toString() + "The land Vehicle Has " + wheelsAmount + " Wheels," + " And He Moving on " + typeRoad;}
	/**
	 * equal function , check if the object other equal to local object
	 * @param other - object from the son
	 * @return true if the all parameter is equal to the object "other" and call to the father with "super" function
	 */
	public boolean equals(Object other) {
		if (other instanceof landVehicles)
			return ((this.wheelsAmount==((landVehicles)other).wheelsAmount) &&(this.typeRoad.equals(((landVehicles)other).typeRoad))&&super.equals(other));
		return false;
	}
}