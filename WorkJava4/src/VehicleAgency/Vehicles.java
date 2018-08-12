package VehicleAgency;

/**
 * Vehicles the everyone father , he is abstract class , and all type of vehicles inherit from him
 * @author GuyHassan
 *
 */
public  class Vehicles implements interfaceVehicle {
	
	public int getMaxPassenger() {
		return maxPassenger;
	}

	public void setMaxPassenger(int maxPassenger) {
		this.maxPassenger = maxPassenger;
	}

	public int getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getKm() {
		return km;
	}
	/**
	 * set a new kilometer
	 * @param d - new kilometer
	 */
	public void setKM(double km) {
		this.km = km;
	}

	private int maxPassenger, maxSpeed;
	private String model;
	private double km;

	/**
	 * constructor
	 * @param maxPassenger - how many sit on the vehicles
	 * @param maxSpeed - max speed of vehicles
	 * @param model - model of vehicles
	 */
	public Vehicles(int maxPassenger, int maxSpeed, String model) {

		this.maxPassenger = maxPassenger;
		this.maxSpeed = maxSpeed;
		this.model = model;
		km = 0;
	}

	/**
	 * add the new kilometer to old
	 * @param km - new kilometer
	 */
	public void Move(double km) {
		
		if (km >= 0)
			this.km += km;
		else
			this.km+=0;
	}
	
	
	/***return the parameter with string on this class*/
	public String toString() {
		return "Model: " + model + ", Traveled: " + km + " Km, " + "Max speed of " + maxSpeed + "Mph, "
				+ "Can carry max of " + maxPassenger + " people, ";
	}

	/**
	 * equal function , check if the object other equal to local object
	 * @param other - object from the son
	 * @return true if the parameter equals else false
	 */
	public boolean equals(Object other) {
		if (other instanceof Vehicles)
			if((this.maxPassenger==((Vehicles)other).maxPassenger)&&(this.maxSpeed==((Vehicles)other).maxSpeed)&&(this.km==((Vehicles)other).km)&&(this.model.equals(((Vehicles)other).model)))
				return true;
		return false;
	}

	@Override
	public String getColor() {
		return null;
	}
	@Override
	public void setColor(String c) {
		//do nothing , override from the interfaceVehicle implement
	}
	@Override
	public String getStatus() {
		return null;
	}
	@Override
	public void setStatus(String s) {
		//do nothing , override from the interfaceVehicle implement		
	}

}
