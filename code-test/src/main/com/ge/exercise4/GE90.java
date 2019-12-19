package com.ge.exercise4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GE90 {
	private static final Logger logger = LogManager.getLogger(GE90.class);

	private static final String ENGINE_MODEL = GE90.class.getSimpleName();
	private final String serialNumber;

	public final int maxNumRebuilds = 3;
	public final double flightHoursBeforeRebuild = 25_000;
	public final double dryWeight = 14_502;
	public final double wetWeight = 15_100;
	public final double takeoffThrust = 81_070;

	private double flightHours;
	private int numRebuilds;

	public GE90(String serialNumber, double flightHours, int numRebuilds) {
		this.serialNumber = serialNumber;
		this.flightHours = flightHours;
		this.numRebuilds = numRebuilds;
	}

	public GE90(String serialNumber, double flightHours) {
		this(serialNumber, flightHours, 0);
	}

	public GE90(String serialNumber) {
		this(serialNumber, 0.0);
	}

	public double getFlightHours() {
		return flightHours;
	}

	public void setFlightHours(double flightHours) {
		this.flightHours = flightHours;
	}

	public double thrustToWeightRatio() {
		return takeoffThrust / dryWeight;
	}

	public String toString() {
		return ENGINE_MODEL + " SN: " + serialNumber;
	}

	public double flightHoursBeforeRebuild() {
		return this.flightHoursBeforeRebuild;
	}

	public double serviceTimeLeft() {

		int getNumRebuilds = this.getNumRebuilds();
		double getFlightHoursBeforeEachBuild = this.getFlightHoursBeforeRebuild();
		double serviceHoursLeft = this.getFlightHours() - getNumRebuilds * getFlightHoursBeforeEachBuild;
		return serviceHoursLeft;
	}
	
	public int getNumRebuilds() {
		return numRebuilds;
	}

	public void setNumRebuilds(int numRebuilds) {
		this.numRebuilds = numRebuilds;
	}

	
	public String getSerialNumber() {
		return serialNumber;
	}

	public int getMaxNumRebuilds() {
		return maxNumRebuilds;
	}

	public double getFlightHoursBeforeRebuild() {
		return flightHoursBeforeRebuild;
	}

	public double getDryWeight() {
		return dryWeight;
	}

	public double getWetWeight() {
		return wetWeight;
	}

	public double getTakeoffThrust() {
		return takeoffThrust;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(dryWeight);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(flightHours);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(flightHoursBeforeRebuild);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + maxNumRebuilds;
		result = prime * result + numRebuilds;
		result = prime * result + ((serialNumber == null) ? 0 : serialNumber.hashCode());
		temp = Double.doubleToLongBits(takeoffThrust);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(wetWeight);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GE90 other = (GE90) obj;
		if (Double.doubleToLongBits(dryWeight) != Double.doubleToLongBits(other.dryWeight))
			return false;
		if (Double.doubleToLongBits(flightHours) != Double.doubleToLongBits(other.flightHours))
			return false;
		if (Double.doubleToLongBits(flightHoursBeforeRebuild) != Double
				.doubleToLongBits(other.flightHoursBeforeRebuild))
			return false;
		if (maxNumRebuilds != other.maxNumRebuilds)
			return false;
		if (numRebuilds != other.numRebuilds)
			return false;
		if (serialNumber == null) {
			if (other.serialNumber != null)
				return false;
		} else if (!serialNumber.equals(other.serialNumber))
			return false;
		if (Double.doubleToLongBits(takeoffThrust) != Double.doubleToLongBits(other.takeoffThrust))
			return false;
		if (Double.doubleToLongBits(wetWeight) != Double.doubleToLongBits(other.wetWeight))
			return false;
		return true;
	}



}
