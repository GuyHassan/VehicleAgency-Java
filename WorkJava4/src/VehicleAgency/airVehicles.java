package VehicleAgency;

public class airVehicles extends Vehicles implements interfaceAirVehicle {
	private String miltaryOrCivilian;
	/***constructor*/
	public airVehicles(int maxPassenger, int maxSpeed, String model, String milOrciv) {
		super(maxPassenger, maxSpeed, model);
		this.miltaryOrCivilian = milOrciv;
	}
	/***return the parameter with string on this class*/
	public String toString() {return super.toString() + "The Use of the Vehicle is " + miltaryOrCivilian + ", ";}
	/***return the type of uses military or civilian*/
	public String getMiltaryOrCivilian() {return miltaryOrCivilian;}
	/***set a new type*/
	public void setMiltaryOrCivilian(String milOrCiv) {miltaryOrCivilian = milOrCiv;} 
	/**
	 * equal function , check if the object other equal to local object
	 * @param other - object from the son
	 * @return true if the all parameter is equal to the object "other" and call to the father with "super" function
	 */
	public boolean equals(Object other) {
		if (other instanceof airVehicles)
			return ((this.miltaryOrCivilian.equals(((airVehicles) other).miltaryOrCivilian))&& super.equals(other));
		return false;
	}
}
