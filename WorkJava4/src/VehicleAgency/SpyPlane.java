package VehicleAgency;

public class SpyPlane extends airVehicles implements NonMotorizedObject {
	private String sourceEnergetic;
	private char energeticScore;
	/***constructor*/
	public SpyPlane(String sourceEnergetic) {
		super(1, 50, "Classified", "Miltary");
		this.energeticScore = 'C';
		this.sourceEnergetic = sourceEnergetic;

	}
	/***return the source energetic (automatic / manuel)*/
	public String getSourceOfEnergy() { return sourceEnergetic;}
	/***return the energetic score (A,B,C)*/
	public char getEnergeticScore() { return energeticScore; }
	/***return the parameter with string on this class*/
	public String toString() { 
		return "\nSpy Plane:\n"+ "-----------\n" + super.toString() + "\nEnergetic Source is " + sourceEnergetic + ", And the Energetic Score is "
				+ energeticScore; }
	/**
	 * equal function , check if the object other equal to local object
	 * @param other - object from the son
	 * @return true if the all parameter is equal to the object "other" and call to the father with "super" function
	 */
	public boolean equals(Object other) {
		if (other instanceof SpyPlane) 
			return (this.sourceEnergetic.equals(((SpyPlane) other).sourceEnergetic))&& (this.energeticScore == ((SpyPlane) other).energeticScore) && super.equals(other);
		return false;
	}

}
