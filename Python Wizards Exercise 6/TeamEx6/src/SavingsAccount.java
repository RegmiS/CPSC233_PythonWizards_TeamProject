
public class SavingsAccount extends BankAccount {
	// Instance variable
	private double annualInterestRate;
	
	// Getter and setter methods for annual interest rate
	public double getAnnualInterestRate() { return this.annualInterestRate; }
	public void setAnnualInterestRate(double rate) 
	{	
		if (rate >= 0.0)
			this.annualInterestRate = rate; 
	}
	
	/**
	 * Default constructor
	 * assigns initial values of 0.0
	 */
	public SavingsAccount() 
	{
		super();
		this.annualInterestRate = 0.0;
	}
	
	/**
	 * Constructor with customer object and balance
	 * @param c1 Customer object
	 * @param initialBalance
	 */
	public SavingsAccount(Customer c1, double initialBalance)
	{
		super(c1, initialBalance);
	}
	/**
	 * Constructor with:
	 * @param initialBalance
	 * @param interestRate
	 */
	public SavingsAccount(double initialBalance, double interestRate)
	{
		super(initialBalance);
		this.annualInterestRate = interestRate;
	}
	
	/**
	 * Multiplies the balance by the interest rate and rounds it to the nearest cent
	 */
	public void depositMonthlyInterest() 
	{
		if (this.getBalance() > 0)
		{
			double temp = 1.0 + (this.annualInterestRate/12) / 100.0;
			this.setBalance(Math.round((this.getBalance() * temp) * 100.0) / 100.0);
		}
	}
	
}
