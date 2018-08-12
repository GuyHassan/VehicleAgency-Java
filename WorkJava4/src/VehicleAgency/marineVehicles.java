package VehicleAgency;

public class marineVehicles extends Vehicles implements interfaceMarineVehicles 
{
	private boolean directionWind;
	private String flag;
	/***constructor*/
	public marineVehicles(int maxPassenger, int maxSpeed, String model, boolean direction, String flag) {
		super(maxPassenger, maxSpeed, model);
		directionWind = direction;
		this.flag = flag;
	}
	/***get direction*/
	public boolean getDirectionWind() { return directionWind;}
	/***set new direction*/
	public void setDirectionWind(boolean directionWind) {this.directionWind = directionWind;}
	/****get local flag*/
	public String getFlag() {return flag;}
	/***set new flag*/
	public void setFlag(String flag) {this.flag = flag;}
	/***return the parameter with string on this class*/
	public String toString() {
		String name = ((this.directionWind == true) ? "With the Wind. " : "Not with the Wind. ");
		return super.toString() + "Under " + flag + " flag, " + name;
	}
	/**
	 * equal function , check if the object other equal to local object
	 * @param other - object from the son
	 * @return true if the all parameter is equal to the object "other" and call to the father with "super" function
	 */
	public boolean equals(Object other) {
		if (other instanceof marineVehicles) {
			return ((this.directionWind == ((marineVehicles) other).directionWind)
					&& (this.flag.equals(((marineVehicles) other).flag)) && super.equals(other));
		}
		return false;
	}
}
