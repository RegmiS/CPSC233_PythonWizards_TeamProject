
public class ChequingAccount extends BankAccount {
	private double overdraftAmount = 100.0;
	private double overDraftFee;
	
	
	public double getOverDraftFee() {return this.overDraftFee;}
	
	
	public void setOverDraftFee(double fee) {
		this.overDraftFee = fee;
	}
	
	
	public ChequingAccount(Customer c1, double balance, double overDraftFee) {
		setOverDraftFee(overDraftFee);
		setBalance(balance);
		
	}
	
	
	public void withdraw(double amount) {
		if ( amount > 0 && (this.balance - amount >= -this.overdraftAmount))
			this.balance -= amount;
	}
	
}
