package model;

public class Customer extends Person
{
    private double account;
    private double discount;
    
    public Customer(String address, String email, String name, int phone, double account, double discount)
    {
        super(address, email, name, phone);
        setAccount(account);
        setDiscount(discount);
    }
    
    //Get and set methods
    public double getAccount()
    {
        return account;
    }
    
    public void setAccount(double account)
    {
        this.account = account;
    }
    
    public double getDiscount()
    {
        return discount;
    }
    
    public void setDiscount(double discount)
    {
        this.discount = discount;
    }
}
