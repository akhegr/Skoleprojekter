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
     * Opret medarbejder
     *
     * @param address Medarbejderens adresse
     * @param email Medarbejderens email
     * @param name Medarbejderens navn
     * @param phone Medarbejderens telefonnummer
     * @param position Medarbejderens rang
     */
    public void createEmployee(String address, String email, String name, int phone, String position)
    {
        employeeContainer.createEmployee(address, email, name, phone, position);
    }
    
    /**
     * Find medarbejder
     *
     * @param phone Medarbejderens telefonnummer
     * @return Employee Den søgte medarbejder
     */
    public Employee findEmployee(int phone) throws exceptions.PersonNotExistException
    {
        Employee foundEmployee = employeeContainer.findEmployee(phone);
        return foundEmployee;
    }

    /**
     * Opdater medarbejder
     *
     * @param oldPhone Medarbejderens gamle telefonnummer
     * @param address Medarbejderens nye adresse
     * @param email Medarbejderens nye email
     * @param name Medarbejderens nue navn
     * @param phone Medarbejderens nye telefonnummer
     * @param position Medarbejderens nye rang
     */
    public void updateEmployee(int oldPhone, String address, String email, String name, int phone, String position) throws exceptions.PersonNotExistException
    {
        employeeContainer.updateEmployee(oldPhone, address, email, name, phone, position);
    }
    
    /**
     * Find medarbejderens navn
     *
     * @param phone Medarbejderens telefonnummer
     * @return String Medarbejderens navn
     */
    public String findName(int phone) throws exceptions.PersonNotExistException
    {
        Employee foundEmployee = findEmployee(phone);
        return foundEmployee.getName();
    }
    
    /**
     * Find medarbejderens adresse
     *
     * @param phone Medarbejderens telefonnummer
     * @return String Medarbejderens adresse
     */
    public String findAddress(int phone) throws exceptions.PersonNotExistException
    {
        Employee foundEmployee = findEmployee(phone);
        return foundEmployee.getAddress();
    }
    
    /**
     * Find medarbejderens email
     *
     * @param phone Medarbejderens telefonnummer
     * @return String Medarbejderens email
     */
    public String findEmail(int phone) throws exceptions.PersonNotExistException
    {
        Employee foundEmployee = findEmployee(phone);
        return foundEmployee.getEmail();
    }
    
    /**
     * Find medarbejderens rang
     *
     * @param phone Medarbejderens telefonnummer
     * @return String Medarbejderens rang
     */
    public String findPosition(int phone) throws exceptions.PersonNotExistException
    {
        Employee foundEmployee = findEmployee(phone);
        return foundEmployee.getPosition();
    }
}
