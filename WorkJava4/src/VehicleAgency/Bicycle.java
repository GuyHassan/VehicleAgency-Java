package VehicleAgency;
/**
 * Bicycle class , inehrit from land vehicles and do a implament from non motorize object
 * @author GuyHassan
 *
 */
public class Bicycle extends landVehicles implements NonMotorizedObject {
	private String sourceEnergetic;
	private char energeticScore;
	public Bicycle(int maxPassenger, int maxSpeed, String model, String road) {
		super(maxPassenger, maxSpeed, model, 2, road);
		sourceEnergetic = "Manuel";
		energeticScore = 'A';
	}

	@Override
	public String getSourceOfEnergy() {
		return sourceEnergetic;
	}

	@Override
	public char getEnergeticScore() {
		return energeticScore;
	}
	public String toString() { 
		return "\nBicycle:\n"+ "--------\n" + super.toString() + "\nEnergetic Source is " + sourceEnergetic + ", And the Energetic Score is "
				+ energeticScore; }
	public boolean equals(Object other) {
		if (other instanceof Bicycle) 
			return (this.sourceEnergetic.equals(((Bicycle) other).sourceEnergetic))&& (this.energeticScore == ((Bicycle) other).energeticScore) && super.equals(other);
		return false;
	}

}
