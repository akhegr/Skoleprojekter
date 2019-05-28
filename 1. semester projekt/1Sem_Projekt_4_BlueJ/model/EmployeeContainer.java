package model;
import java.util.Map;
import java.util.HashMap;
import exceptions.*;

public class EmployeeContainer
{
    private Map<Integer, Employee> employeeContainer;
    private static EmployeeContainer instance;
    
    /**
     * Constructor for objects of class EmployeeContainer
     */
    private EmployeeContainer()
    {
        // initialise instance variables
        employeeContainer = new HashMap<Integer, Employee>();
    }
    
    /**
     * getInstance()
     */
    public static EmployeeContainer getInstance()
    {
        if(instance == null)
        {
            instance = new EmployeeContainer();
        }
        
        return instance;
    }
    
    /**
     * Create Employee
     *
     * @param  String address  The address of the Employee
     * @param  String email  The email address of the Employee
     * @param  String name  The name of the Employee
     * @param  int phone  The phonenumber of the Employee
     * @param  String position  The position of the Employee
     */
    public void createEmployee(String address, String email, String name, int phone, String position)
    {
        Employee empl = new Employee(address, email, name, phone, position);
        employeeContainer.put(phone, empl);
    }
    
    /**
     * Find the Employee
     *
     * @param  int phone  The phonenumber of the Employee
     * @return the found Employee
     */
    public Employee findEmployee(int phone) throws exceptions.PersonNotExistException
    {
        Employee foundEmployee = employeeContainer.get(phone);
        if(foundEmployee == null)
        {
            throw new exceptions.PersonNotExistException(0, phone);
        }
        
        return foundEmployee;
    }
    
    /**
     * Update Employee
     *
     * @param  int oldPhone  The old phonenumber of the Employee
     * @param  String address  The address of the Employee
     * @param  String email  The email address of the Employee
     * @param  String name  The name of the Employee
     * @param  int phone  The phonenumber of the Employee
     * @param  String position  The position of the Employee
     */
    public void updateEmployee(int oldPhone, String address, String email, String name,
                int phone, String position) throws exceptions.PersonNotExistException
    {
        Employee empl = findEmployee(oldPhone);
        empl.setAddress(address);
        empl.setEmail(email);
        empl.setName(name);
        empl.setPhone(phone);
        empl.setPosition(position);
    }
    
}
