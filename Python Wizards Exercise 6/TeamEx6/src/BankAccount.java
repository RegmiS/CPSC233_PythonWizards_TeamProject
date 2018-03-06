
public class BankAccount {
	
	// Instance variables
	protected Customer accountHolder;
	private double balance = 0.0;
	
	
	/**
	 * No parameter constructor
	 */
	public BankAccount() 
	{
		this.accountHolder = new Customer();
	}
	
	/**
	 * new constructor that sets balance
	 */
	public BankAccount(double initialBalance) {
		this.balance = initialBalance; 
		this.accountHolder = new Customer();
		}
	
	
	/**
	 * Constructor with Customer object and money
	 * @param c1: Customer object
	 * @param money: new balance
	 */
	public BankAccount(Customer c1, double money) {
		this.accountHolder = c1;
		this.balance = money;
	}
	
	/**
	 * returns the objects info
	 */
	public String toString()
	{
		String name = this.accountHolder.getName();
		String ID = Integer.toString(this.accountHolder.getID());
		return "Name: " + name + " ID: " + ID + " Balance: " + this.balance;
//				" Over Draft Amount: "+ this.getOverdraftAmount();
	}
	
	/**
	 * creates a new copy of the customer object using the copy constructor
	 * @return the new customer object
	 */
	public Customer getCustomer() {
		Customer c = new Customer(this.accountHolder);
		return c; 
		}
	
	
	/**
	 * Getter method for balance
	 * @return objects balance
	 */
	public double getBalance() { return this.balance; }
	protected void setBalance(double num) { this.balance = num; }
	
	
	/**
	 * method for depositing money, does not accept negative amount
	 * @param amount: the money to add to the account
	 */ 
	public void deposit(double amount)
	{
		if (amount > 0)
			this.balance += amount;
	}
	
	/**
	 * withdraw method, checks overdraft amount
	 * @param amount: how much money is removed
	 */
	public void withdraw(double amount)
	{
		if ( amount > 0 && this.getBalance() - amount >= 0)
				this.balance -= amount;
	}
	
	/**
	 * withdraws the amount (if able to) from the object that calls the method and deposits
	 *  it into the other (b1) if the balance of the initial object changed
	 * @param amount: amount to be transfered
	 * @param b1: bankAccount being deposited into
	 */
	public void transfer(double amount, BankAccount b1)
	{
		double temp = this.getBalance();
		this.withdraw(amount);
		if (this.getBalance() != temp)
			b1.deposit(amount);
		
	}
}
