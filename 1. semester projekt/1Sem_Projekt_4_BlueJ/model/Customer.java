package model;

public class Customer extends Person
{
    private double account;
    private double discount;
    private double loan;
    private int type;
    
    public Customer(String address, String email, String name, int phone, double account, double discount, double loan, int type)
    {
        super(address, email, name, phone);
        setAccount(account);
        setDiscount(discount);
        setLoan(loan);
        setType(type);
    }
    
    //Get og set metoder
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
    
    public double getLoan()
    {
        return loan;
    }
    
    public void setLoan(double loan)
    {
        this.loan = loan;
    }
    
    public int getType()
    {
        return type;
    }
    
    public void setType(int type)
    {
        this.type = type;
    }    
}
