package VehicleAgency;

public class GamePlane extends airVehicles implements NonMotorizedObject {
	private String sourceEnergetic;
	private char energeticScore;
	/***constructor*/
	public GamePlane() {
		super(0, 10, "Toy", "Civilian");
		this.energeticScore = 'A';
		this.sourceEnergetic = "Manual";
	}
	/***return the source energetic ( automatic / manual)*/
	public String getSourceOfEnergy() {return sourceEnergetic;} 
	/***return the energetic score (A,B,C)*/
	public char getEnergeticScore() {return energeticScore;}
	/***return the parameter with string on this class*/
	public String toString() { 
		return "\nGame Plane:\n"+ "----------\n" + super.toString() + "\nEnergetic Source is " + sourceEnergetic
				+ ", And the Energetic Score is " + energeticScore;	}
	/**
	 * equal function , check if the object other equal to local object
	 * @param other - object from the son
	 * @return true if the all parameter is equal to the object "other" and call to the father with "super" function
	 */
	public boolean equals(Object other) {
		if (other instanceof GamePlane)
			return (this.sourceEnergetic.equals(((GamePlane) other).sourceEnergetic))&& (this.energeticScore == ((GamePlane) other).energeticScore) && super.equals(other);
		return false;
	}

}
