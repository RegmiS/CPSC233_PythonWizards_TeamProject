
public class Customer {

	// Instance variables
	private String name;
	private int ID;
	
	/**
	 * returns the info of the current object
	 */
	public String toString()
	{
		String ID = Integer.toString(this.ID);
		return "Name: " + this.name + " ID: " + ID;
	}
	
	/**
	 * no parameter constructor
	 * sets default values
	 */
	public Customer() 
	{
		this.name = "";
		this.ID = 0;
	}
	
	/**
	 * constructor with name and ID
	 * @param name: objects name
	 * @param ID: objects ID
	 */
	public Customer(String name, int ID)
	{
		this.name = name;
		this.ID = ID;
	}
	
	/**
	 * copy constructor
	 * @param c1: object to be copied from
	 */
	public Customer(Customer c1)
	{
		this.name = c1.name;
		this.ID = c1.ID;
	}
	
	// getter and setter methods
	public void setName(String name) { this.name = name; }
	public String getName() { return this.name; }
	
	public void setID(int num) { this.ID = num; }
	public int getID() { return this.ID; }
	
	

}
