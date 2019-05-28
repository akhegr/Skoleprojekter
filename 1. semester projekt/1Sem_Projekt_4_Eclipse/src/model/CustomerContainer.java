package model;
import java.util.Map;
import java.util.HashMap;
import exceptions.*;

public class CustomerContainer
{
    // instance variables - replace the example below with your own
    private Map<Integer, Customer> customerContainer;
    private static CustomerContainer instance; 
    /**
     * Constructor for objects of class CustomerContainer
     */
    private CustomerContainer()
    {
        // initialise instance variables
        customerContainer = new HashMap<Integer, Customer>();
    }
    
    /**
     * getInstance()
     */
    public static CustomerContainer getInstance()
    {
        if(instance == null)
        {
            instance = new CustomerContainer();
        }
        
        return instance;
    }
    
    /**
     * Create Customer
     *
     * @param  String address  The address of the Customer
     * @param  String email  The email address of the Customer
     * @param  String name  The name of the Customer
     * @param  int phone  The phone number of the Customer
     * @param  double account  The money on the account
     * @param  double discount  The discount the Customer got
     * @throws TooHighDiscountException
     */
    public void createCustomer(String address, String email, String name,
    			int phone, double account, double discount) throws TooHighDiscountException
    {
        Customer foundCustomer = new Customer(address, email, name, phone, account, discount);
        throwDiscountException(discount);
        customerContainer.put(phone, foundCustomer);
    }
    
    /**
     * Find the Customer
     *
     * @param  int phone  The phone number of the Customer
     * @return the found Customer
     * @throws PersonNotExistException
     */
    public Customer findCustomer(int phone) throws PersonNotExistException
    {
        Customer foundCustomer = customerContainer.get(phone);
        if(foundCustomer == null)
        {
            throw new exceptions.PersonNotExistException(1, phone);
        }
        
        return foundCustomer;
    }
    
    /**
     * Update Customer
     *
     * @param  int oldPhone  The old phonenumber of the Customer
     * @param  String address  The address of the Customer
     * @param  String email  The email address of the Customer
     * @param  String name  The name of the Customer
     * @param  int phone  The phonenumber of the Customer
     * @param  double account  The money on the account
     * @param  double discount  The discount the Customer got
     * @throws PersonNotExistException, TooHighDiscountException 
     */
    public void updateCustomer(int oldPhone, String address, String email, String name, int phone, double account, 
                    double discount) throws PersonNotExistException, TooHighDiscountException
    {
    	throwDiscountException(discount);
        Customer foundCustomer = findCustomer(oldPhone);
        foundCustomer.setAddress(address);
        foundCustomer.setEmail(email);
        foundCustomer.setName(name);
        foundCustomer.setPhone(phone);
        foundCustomer.setAccount(account);
        foundCustomer.setDiscount(discount);
    }

    /**
     * Update account
     *
     * @param phone Customer's phone number
     * @param account Customer's account
     * @throws PersonNotExistException, TooHighDiscountException
     */
	public void updateAccount(int phone, double account) throws PersonNotExistException, TooHighDiscountException
	{
		Customer foundCustomer = findCustomer(phone);
		foundCustomer.setAccount(account);
	}
	
	private void throwDiscountException(double discount) throws TooHighDiscountException
	{
		if (discount >= 100) {
	        throw new TooHighDiscountException(discount);
	    }
	}
	
}
