package model;

public class Person
{
    private String address;
    private String email;
    private String name;
    private int phone;
    
    public Person(String address, String email, String name, int phone)
    {
        setAddress(address);
        setEmail(email);
        setName(name);
        setPhone(phone);
    }
    
    //Get and set methods
    public String getAddress()
    {
        return address;
    }
    
    public void setAddress(String address)
    {
        this.address = address;
    }
    
    public String getEmail()
    {
        return email;
    }
    
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public int getPhone()
    {
        return phone;
    }
    
    public void setPhone(int phone)
    {
        this.phone = phone;
    }
}
