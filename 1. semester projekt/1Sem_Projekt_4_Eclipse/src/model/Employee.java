package model;

import exceptions.PasswordIsIncorrectException;

public class Employee extends Person
{
    private String position;
	private String password;
    
    public Employee(String address, String email, String name, int phone, String position, String password)
    {
        super(address, email, name, phone);
        setPosition(position);
        setPassword(password);
    }
    
    //Get and set methods
    public String getPosition()
    {
        return position;
    }
    
    public void setPosition(String position)
    {
        this.position = position;
    }
    
    public void setPassword(String password)
    {
        this.password = password;
    }
    
    /**
     * Check password
     *
     * @param  String password  The employee input password
     * @return boolean status  Whether the password is true or false
     */
    public boolean checkPassword(String tempPass) throws PasswordIsIncorrectException
    {
    	boolean status = false;
    	if(tempPass.equals(password))
    	{
    		status = true;
    	}
    	else
    	{
    		int phone = super.getPhone();
    		throw new PasswordIsIncorrectException(phone);
    	}
    	return status;
    }
}
