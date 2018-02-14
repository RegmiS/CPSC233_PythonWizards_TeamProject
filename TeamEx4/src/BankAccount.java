
public class BankAccount {
	
	// Instance variables
	public Customer c1;
	public double balance = 0.0;
	private double overdraftAmount = 100.0;
	
	/**
	 * No parameter constructor
	 */
	public BankAccount() 
	{
		this.c1 = new Customer();
	}
	
	/**
	 * new constructor that sets balance
	 */
	public BankAccount(double initialBalance) {
		this.balance = initialBalance; 
		this.c1 = new Customer();
		}
	
	
	/**
	 * Constructor with Customer object and money
	 * @param c1: Customer object
	 * @param money: new balance
	 */
	public BankAccount(Customer c1, double money) {
		this.c1 = c1;
		this.balance = money;
	}
	
	/**
	 * returns the objects info
	 */
	public String toString()
	{
		String name = this.c1.getName();
		String ID = Integer.toString(this.c1.getID());
		return "Name: " + name + " ID: " + ID + " Balance: " + this.balance +
				" Over Draft Amount: "+ this.overdraftAmount;
	}
	
	/**
	 * creates a new copy of the customer object using the copy constructor
	 * @return the new customer object
	 */
	public Customer getCustomer() {
		Customer c = new Customer(this.c1);
		return c; 
		}
	
	
	/**
	 * Getter method for balance
	 * @return objects balance
	 */
	public double getBalance() { return this.balance; }

	/**
	 * Setter method for overdraft amount
	 * @param amount: the new overdraft amount
	 */
	public void setOverdraftAmount(double amount) 
	{
		if (amount >= 0)
			this.overdraftAmount = amount; 
	}
	
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
		if ( amount > 0 && (this.balance - amount >= -this.overdraftAmount))
				this.balance -= amount;
	}
}
