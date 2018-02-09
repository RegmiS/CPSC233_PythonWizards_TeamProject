
public class CustomerTrace {
	
	public static void main(String[] args)
	{
		Customer c1 = new Customer("Alison Brown", 123);  
		System.out.println(c1 + " 1");
		// 1. New Customer created with specified name and ID
		BankAccount b1 = new BankAccount(c1, 100.00); 
		System.out.println(b1 + " 2");
		// 2. New bank account is created with the data from c1 

		Customer c2 = b1.getCustomer();
		c2.setName("Charles Green");
		System.out.println(c1 + " 3");
		// 3. c1 name remains as "Alison Brown" since c2 is only a copy of c1
		System.out.println(c2 + " 4");
		// 4. c2 has name "Charles Green" seperate from c1
		
		Customer c3 = c1;
		c3.setName("Eva White");
		System.out.println(b1.getCustomer() + " 5");
		// 5. since c3 now references c1 when the name is changed for c3 it changes for c1
		System.out.println(c3 + " 6");
		// 6. again c3 is referencing c1 so the same thing prints again
		
	}

}
