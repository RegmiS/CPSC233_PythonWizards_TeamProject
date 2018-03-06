
public class ChequingAccountTrace {

	public static void main(String[] args) {

		Customer c = new Customer("John Doe", 321); // new customer with name and ID
		BankAccount b1 = new BankAccount(c,100.0); // new BankAccount with accountHolder c and initial balance of 100.0
		ChequingAccount b2 = new ChequingAccount(c, 200.0, 12.0); 
		// ChequingAccount object with accountHolder c, initial balance of 200.0 and overdraftFee of 12.0
		b2.setOverdraftAmount(150.0); // change overdraft amount from 0.0 (default) to 150.0
		BankAccount b3 = b2; // new bank account which references the same object as b2. This works because the class
		// ChequingAccount is a child class of BankAccount thus BankAccount knows how to convert to ChequingAccount objects
		
		// since b3 references b2, every time b2s balance is changed so is b3 (they are referencing the same object)
		System.out.println(b1.getBalance() + ", " + b3.getBalance() + ", " + b2.getBalance());
		
		b1.withdraw(110);
		System.out.println(b1.getBalance() + ", " + b3.getBalance()); 
		// since BankAccount objects default to no overdraftAmount the balance remains unchanged
		
		b2.withdraw(300.0);
		System.out.println(b1.getBalance() + ", " + b3.getBalance());
		// b2 has an overdraftAmount of 150 so it allows the withdrawal and charges the fee of 12.0
		
		b1.transfer(50.0, b2);
		System.out.println(b1.getBalance() + ", " + b3.getBalance());
		// 50 is withdrawn from b1 and deposited into b2
		
		b2.transfer(88,b1);
		System.out.println(b1.getBalance() + ", " + b3.getBalance() + ", " + b2.getBalance());
		// since -62 - 88 = -150 the withdrawal is allowed and overdraftFee is charged again
		// leaving b2 a balance of - 162.0
		// and again we see that b2 and b3 are referencing the same object
	}

}
