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
     * Opret kunde
     *
     * @param address Medarbejderens adresse
     * @param email Medarbejderens email
     * @param name Medarbejderens navn
     * @param phone Medarbejderens telefonnummer
     * @param account Medarbejderens konto
     * @param discount Medarbejderens rabat
     * @param loan Medarbejderens lån
     * @param type Medarbejderens kontotype
     */
    public void createCustomer(String address, String email, String name, int phone, double account, double discount, double loan, int type)
    {
        customerContainer.createCustomer(address, email, name, phone, account, discount, loan, type);
    }
    
    /**
     * Find kunde
     *
     * @param phone Kundens telefonnummer
     * @return Customer Den søgte kunde
     */
    public Customer findCustomer(int phone) throws exceptions.PersonNotExistException
    {
        Customer foundCustomer = customerContainer.findCustomer(phone);
        return foundCustomer;
    }
    
    /**
     * Opdater kunde
     *
     * @param oldPhone Kundens gamle telefonnummer
     * @param address Kundens adresse
     * @param email Kundens email
     * @param name Kundens navn
     * @param phone Kundens telefonnummer
     * @param account Kundens konto
     * @param discount Kundens rabat
     * @param loan Kundens lån
     * @param type Kundens kontotype
     */
    public void updateCustomer(int oldPhone, String address, String email, String name, int phone, double account, 
                            double discount, double loan, int type) throws exceptions.PersonNotExistException
    {
        customerContainer.updateCustomer(oldPhone, address, email, name, phone, account, discount, loan, type);
    }
    
    /**
     * Find kundens navn
     *
     * @param phone Kundens telefonnummer
     * @return String Kundens navn
     */
    public String findName(int phone) throws exceptions.PersonNotExistException
    {
        Customer foundCustomer = findCustomer(phone);
        return foundCustomer.getName();
    }
    
    /**
     * Find kundens adresse
     *
     * @param phone Kundens telefonnummer
     * @return String Kundens adresse
     */
    public String findAddress(int phone) throws exceptions.PersonNotExistException
    {
        Customer foundCustomer = findCustomer(phone);
        return foundCustomer.getAddress();
    }
    
    /**
     * Find kundens email
     *
     * @param phone Kundens telefonnummer
     * @return String Kundens email
     */
    public String findEmail(int phone) throws exceptions.PersonNotExistException
    {
        Customer foundCustomer = findCustomer(phone);
        return foundCustomer.getEmail();
    }
    
    /**
     * Find kundens konto
     *
     * @param phone Kundens telefonnummer
     * @return double Kundens beløb på kontoen
     */
    public double findAccount(int phone) throws exceptions.PersonNotExistException
    {
        Customer foundCustomer = findCustomer(phone);
        return foundCustomer.getAccount();
    }
    
    /**
     * Find kundens rabat på kontoen
     *
     * @param phone Kundens telefonnummer
     * @return double Kundens rabat på kontoen
     */
    public double findDiscount(int phone) throws exceptions.PersonNotExistException
    {
        Customer foundCustomer = findCustomer(phone);
        return foundCustomer.getDiscount();
    }
    
    /**
     * Find kundens lån på kontoen
     *
     * @param phone Kundens telefonnummer
     * @return double Kundens lån på kontoen
     */
    public double findLoan(int phone) throws exceptions.PersonNotExistException
    {
        Customer foundCustomer = findCustomer(phone);
        return foundCustomer.getLoan();
    }
    
    /**
     * Find kundetypen
     *
     * @param phone Kundens telefonnummer
     * @return int kundentypen
     */
    public int findType(int phone) throws exceptions.PersonNotExistException
    {
        Customer foundCustomer = findCustomer(phone);
        return foundCustomer.getType();
    }
}
