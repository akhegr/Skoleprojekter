package controller;
import model.*;
import exceptions.*;

public class CustomerCtrl
{
    private CustomerContainer customerContainer;
    
    public CustomerCtrl()
    {
        customerContainer = CustomerContainer.getInstance();
    }
    
    /**
     * Create Customer
     *
     * @param address Employee's address
     * @param email Employee's e-mail
     * @param name Employee's name
     * @param phone Employee's phone number
     * @param account Employee's account
     * @param discount Employee's discount
     * @throws TooHighDiscountException
     */
    public void createCustomer(String address, String email, String name, int phone, 
    			double account, double discount) throws TooHighDiscountException
    {
        customerContainer.createCustomer(address, email, name, phone, account, discount);
    }
    
    /**
     * Find customer
     *
     * @param phone Customer phone number
     * @return Customer The located/founded customer
     * @throws PersonNotExistException
     */
    public Customer findCustomer(int phone) throws PersonNotExistException
    {
        Customer foundCustomer = customerContainer.findCustomer(phone);
        return foundCustomer;
    }
    
    /**
     * Update customer
     *
     * @param oldPhone Customer's old phone number
     * @param address Customer's address
     * @param email Customer's e-mail
     * @param name Customer's name
     * @param phone Customer's phone number
     * @param account Customer's account
     * @param discount Customer's discount
     * @param loan Customer's loan
     * @throws PersonNotExistException 
     * @throws TooHighDiscountException 
     */
    public void updateCustomer(int oldPhone, String address, String email, String name, int phone, double account, 
                            double discount) throws PersonNotExistException, TooHighDiscountException
    {
        customerContainer.updateCustomer(oldPhone, address, email, name, phone, account, discount);
    }
    
    /**
     * Update account
     *
     * @param phone Customer's phine number
     * @param account Customer's account
     * @throws PersonNotExistException
     * @throws TooHighDiscountException 
     */
    public void updateAccount(int phone, double account) throws PersonNotExistException, TooHighDiscountException
    {
        customerContainer.updateAccount(phone, account);
    }
    
    /**
     * Find Customer's name
     *
     * @param phone Customer's phone number
     * @return String Customer's name
     */
    public String findName(int phone) throws PersonNotExistException
    {
        Customer foundCustomer = findCustomer(phone);
        return foundCustomer.getName();
    }
    
    /**
     * Find Customer's address
     *
     * @param phone Customer's phone number
     * @return String Customer's address
     */
    public String findAddress(int phone) throws PersonNotExistException
    {
        Customer foundCustomer = findCustomer(phone);
        return foundCustomer.getAddress();
    }
    
    /**
     * Find Customer's e-mail
     *
     * @param phone Customer's phone number
     * @return String Customer's e-mail
     */
    public String findEmail(int phone) throws PersonNotExistException
    {
        Customer foundCustomer = findCustomer(phone);
        return foundCustomer.getEmail();
    }
    
    /**
     * Find Customer's account
     *
     * @param phone Customer's phone number
     * @return double Customer's amount in the account
     */
    public double findAccount(int phone) throws PersonNotExistException
    {
        Customer foundCustomer = findCustomer(phone);
        return foundCustomer.getAccount();
    }
    
    /**
     * Find Customer's discount on the account
     *
     * @param phone Customer's phone number
     * @return double Customer's discount on the account
     */
    public double findDiscount(int phone) throws PersonNotExistException
    {
        Customer foundCustomer = findCustomer(phone);
        return foundCustomer.getDiscount();
    }
}
