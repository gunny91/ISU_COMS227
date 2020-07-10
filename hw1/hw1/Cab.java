package hw1;

/**
 * This is the cab class for the hw1. This class models a cab. Passengers pay to get rides. 
 * There is a base fare charged for any ride when it starts, onto which is added a per-mile rate that
 * is charged when the cab drives. The cab has a meter showing how much is to be charged to the passenger,
 * and it also keeps track of the total cash collected and the total miles driven since the cab was constructed.
 * @author GeonHee Cho
 *
 */
public class Cab {

	/**
	 * base fare charged for any ride
	 */
	private double BaseFare;
	
	/**
	 * per-mile rate
	 */
	private double Per_Mile_Rate;
	
	/**
	 * total cash collected
	 */
	private double TotalCash;
	
	/**
	 * total miles driven
	 */
	private double TotalMiles;
	
	/**
	 * the current per-mile rate
	 */
	private double CurrentRate;
	
	/**
	 * amount of money shown on the meter
	 */
	private double Meter;
	
	/**
	 * true if the cab has a passenger, false otherwise
	 */
	private boolean Passenger;
	
	/**
	 * Constructs a cab that will use the given base fare and per-mile rate.
	 * @param givenBaseFare base fare charged for any ride
	 * @param givenPerMileRate per-mile rate
	 */
	public Cab(double givenBaseFare, double givenPerMileRate)
	{
		
		BaseFare = givenBaseFare;
		Per_Mile_Rate = givenPerMileRate;
		TotalMiles =0;
		Meter = 0;
		TotalCash =0; 
	}
	
	/**
	 * Starts a new ride, setting the meter to the base fare 
	 * and setting the current rate to the per mile charge. 
	 * If there had been a previous call to pickUp() without
	 * a corresponding call to dropOff(), the previous value on the meter is ignored.
	 */
	public void pickUp()
	{
		Passenger = true;
		Meter = BaseFare;
		CurrentRate = Per_Mile_Rate;
	}
	
	/**
	 * Ends the current ride, updating the total cash collected
	 * and resetting the meter and current rate to zero.
	 */
	public void dropOff()
	{	
		Passenger = false;
		TotalCash += Meter;
		CurrentRate = 0;
		Meter = 0;
	}
	
	/**
	 * Drives the cab the given number of miles, updating the total miles and possibly updating the meter.
 	 * In general, the current rate times the miles driven is added to the meter,
 	 * but the current rate might be zero. This method does not modify the total cash.
	 * @param miles number of miles to drive
	 */
	public void drive(double miles)
	{
		Meter += (CurrentRate*miles);
		TotalMiles += miles;
	}

	/**
	 * Returns the total miles driven by this cab during its lifetime.
	 * @return total miles driven
	 */
	public double getTotalMiles()
	{
		return TotalMiles;
	}
	
	/**
	 * Returns the total cash collected by this cab during its lifetime.
	 * @return total cash collected
	 */
	public double getTotalCash()
	{
		return TotalCash;
	}
	
	/**
	 * Returns the amount of money shown on the meter for the current ride.
	 * This will always be zero before start() is called.
	 * @return amount of money shown on the meter
	 */
	public double getMeter()
	{
		return Meter;	
	}
	
	/**
	 * Returns the current per-mile rate, which is always either zero
	 * or the per-mile rate given in the constructor.
	 * @return the current per-mile rate
	 */	
	public double getCurrentRate()
	{ 	
		return CurrentRate;
	}
	
	/**
	 * Determines whether the cab currently has a passenger 
	 * (i.e., the current rate is nonzero).
	 * @return true if the cab has a passenger, false otherwise
	 */
	
	public boolean hasPassenger()
	{
		return Passenger;
	}
	
	/**
	 * Returns the average income earned by this cab per mile driven.
	 * @return average income per mile
	 */
	public double getAverageIncomePerMile()
	{	
		double Average_Income = TotalCash/TotalMiles;
		return Average_Income;
	}
	
}
