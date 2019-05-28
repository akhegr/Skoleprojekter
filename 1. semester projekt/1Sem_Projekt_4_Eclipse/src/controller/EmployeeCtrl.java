package controller;
import model.*;
import exceptions.*;

public class EmployeeCtrl
{
    private EmployeeContainer employeeContainer;
    
    public EmployeeCtrl()
    {
        employeeContainer = EmployeeContainer.getInstance();
    }
    
    /**
     * Create employee
     *
     * @param address Employee's address
     * @param email Employee's e+mail
     * @param name Employee's name
     * @param phone Employee's phone number
     * @param position Employee's rank
     * @param password Medarbejderens kodeord
     */
    public void createEmployee(String address, String email, String name, int phone, String position, String password)
    {
        employeeContainer.createEmployee(address, email, name, phone, position, password);
    }
    
    /**
     * Find employee
     *
     * @param phone Employee's phone number
     * @return Employee the requested employee
     */
    public Employee findEmployee(int phone) throws PersonNotExistException
    {
        Employee foundEmployee = employeeContainer.findEmployee(phone);
        return foundEmployee;
    }

    /**
     * Update employee
     * 
     * @param oldPhone Employee's old phone number
     * @param address Employee's new address
     * @param email Employee's new e-mail
     * @param name Employee's new name
     * @param phone Employee's new phone number
     * @param position Employee's new rank
     */
    public void updateEmployee(int oldPhone, String address, String email, String name, int phone, String position)
    				throws PersonNotExistException
    {
        employeeContainer.updateEmployee(oldPhone, address, email, name, phone, position);
    }
    
    /**
     * Find Employee's name
     *
     * @param phone Employee's phone number
     * @return String Employee's name
     */
    public String findName(int phone) throws PersonNotExistException
    {
        Employee foundEmployee = findEmployee(phone);
        return foundEmployee.getName();
    }
    
    /**
     * Find Employee's address
     *
     * @param phone Employee's phone number
     * @return String Employee's address
     */
    public String findAddress(int phone) throws PersonNotExistException
    {
        Employee foundEmployee = findEmployee(phone);
        return foundEmployee.getAddress();
    }
    
    /**
     * Find Employee's e-mail
     *
     * @param phone Employee's phone number
     * @return String Employee's e-mail
     */
    public String findEmail(int phone) throws PersonNotExistException
    {
        Employee foundEmployee = findEmployee(phone);
        return foundEmployee.getEmail();
    }
    
    /**
     * Find Employee's rank
     *
     * @param phone Employee's phone number
     * @return String Employee's rank
     */
    public String findPosition(int phone) throws PersonNotExistException
    {
        Employee foundEmployee = findEmployee(phone);
        return foundEmployee.getPosition();
    }
    
    /**
     * Check password
     *
     * @param  String password  The employee input password
     * @return boolean status  Whether the password is true or false
     * @throws PersonNotExistException 
     */
    public boolean checkPassword(String tempPass, int phone) throws PersonNotExistException, PasswordIsIncorrectException
    {
    	Employee employee = employeeContainer.findEmployee(phone);
    	return employee.checkPassword(tempPass);
    }
}
