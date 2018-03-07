
public class BankAccountTrace {
	public static void main(String[] args)
	{
		
		BankAccount b1 = new BankAccount();
		System.out.println("Balance of b1 is " + b1.getBalance() + "$");
		
		b1.deposit(100.0);
		System.out.println("Balance of b1 is " + b1.getBalance() + "$");

		
		BankAccount b2 = new BankAccount();
		b2.deposit(200.0);
		System.out.println("Balance of b2 is " + b2.getBalance() + "$");

		BankAccount b3 = b2;
		System.out.println("Balance of b3 is " + b3.getBalance() + "$");

		b3.withdraw(400.0);
		System.out.println("Balance of b3 is " + b3.getBalance() + "$");
		
		
	}
}
