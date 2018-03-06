
public class ChequingAccount extends BankAccount {
	private double TransactionAmount = 100.0;
	private double transactionFee=0;
	
	
	/**
	 * @return transactionFee
	 */
	public double getTransactionFee() {return this.transactionFee;}
	/**
	 * @return Transaction over draft fee
	 */
	public double getOverDraftAmount() {return this.TransactionAmount;}
	
	/**sets the overDraft amount
	 * @param amount, the new transaction amount
	 */
	public void  setOverdraftAmount(double amount) {
		this.TransactionAmount = amount;
	}
	
	/**
	 * sets the transaction fee
	 * @param fee, the transaction fee
	 */
	public void setTransactionFee(double fee) {
		this.transactionFee = fee;
	}
	
	/**
	 * Constructor for chequingAccount class
	 * @param fee, the transaction fee of the new account
	 */
	public ChequingAccount(double fee) {
		setTransactionFee(fee);
	}
	
	
	
	/**
	 * Constructor for the Chequing account class
	 * 
	 * @param c1, new customer
	 * @param balance, balance of the new account
	 * @param overDraftFee, the overdraftfee or transaction fee if you prefer to call it that
	 */
	public ChequingAccount(Customer c1, double balance, double overDraftFee) {
		Customer c = new Customer();
		setTransactionFee(overDraftFee);
		super.c1 = c1;
		setBalance(balance);
		
	}
	
	
	/**
	 * Withdraw class, checks to see if person can withdraw, if the person goes into overdraft
	 * the fee is applied
	 * 
	 * @param amount, the amount the customer is looking to withdraw
	 */
	public void withdraw(double amount) {
		
		if ( amount > 0 && (this.balance - amount >= 0)) {
			this.balance -= amount;
		} else if(( amount > 0 && (this.balance - amount >= -this.TransactionAmount))) {
			this.balance = this.balance - amount - this.transactionFee;
		}
	}
}
