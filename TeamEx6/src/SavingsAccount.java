
public class SavingsAccount extends BankAccount {
	// Instance variable
	public double annualInterestRate;
	
	// Getter and setter methods for annual interest rate
	public double getAnnualInterestRate() { return this.annualInterestRate; }
	public void setAnnualInterestRate(double rate) {	this.annualInterestRate = rate; }
	
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
		double temp = 1.0 + this.annualInterestRate / 100.0;
		System.out.println(temp);
		this.balance = Math.round((this.balance * temp) * 100.0) / 100.0 ;
	}
	
}
