
public class ChequingAccount extends BankAccount{
	
	// Instance variables
	private double overdraftAmount = 0.0;
	private double overdraftFee;
	private double transactionFee;
	private double transactionAmount;

	/**
	 * constructor with just a transaction fee
	 * @param num
	 */
	public ChequingAccount(double num)
	{
		this.setTransactionFee(num);;
	}
	
	/**
	 * constructor with accountHolder, initial balance and fee sets variables using setters (where applicable)
	 * @param c1: the accountHolder
	 * @param balance: the initial balance
	 * @param fee: the fee (test cases seemed to treat this as both fees)
	 */
	public ChequingAccount(Customer c1, double balance,  double fee)
	{
		this.setBalance(balance);
		this.accountHolder = c1;
		this.setTransactionFee(fee);
		this.setOverdraftFee(fee);
	}
	
	/**
	 * setter and getter for overdraftFee
	 */
	public double getOverdraftFee() {return this.overdraftFee; }
	public void setOverdraftFee(double num) { this.overdraftFee = num; }
	
	/**
	 * setter and getter for transactionFee
	 */
	public double getTransactionFee() { return this.transactionFee; }
	public void setTransactionFee(double num)
	{
		if (num >= 0)
			this.transactionFee = num;
	}
	
	/**
	 * setter and getter for transactionAmount
	 */
	public double getTransactionAmount() { return this.transactionAmount; }
	public void setTransactionAmount(double num)
	{
		if (num >= 0)
			this.transactionAmount = num;
	}
	
	/**
	 * Setter and getter method for overdraft amount
	 */
	public void setOverdraftAmount(double amount) 
	{
		if (amount >= 0)
			this.overdraftAmount = amount; 
	}
	public double getOverdraftAmount() { return this.overdraftAmount; }
	
	/**
	 * withdraw that overrides
	 */
	@Override
	public void withdraw(double amount)
	{
		if  (this.getBalance() - amount >= 0 - overdraftAmount)
		{
			this.setBalance(this.getBalance() - amount);
			if (this.getBalance() < 0)
				this.setBalance(this.getBalance() - this.overdraftFee);
		}
	}
}

