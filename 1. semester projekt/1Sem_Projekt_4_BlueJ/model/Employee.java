package model;

public class Employee extends Person
{
    private String position;
    
    public Employee(String address, String email, String name, int phone, String position)
    {
        super(address, email, name, phone);
        setPosition(position);
    }
    
    //Get og set metoder
    public String getPosition()
    {
        return position;
    }
    
    public void setPosition(String position)
    {
        this.position = position;
    }
}
