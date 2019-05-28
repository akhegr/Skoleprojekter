using System.Collections.Generic;
using BusinessLogic;
using Domain;

namespace OrderService
{
    public class EmployeeService : IEmployeeService
    {
        public int Create(Employee employee)
        {
            EmployeeController employeeController = new EmployeeController();
            return employeeController.Create(employee);
        }

        public Employee Read(int id)
        {
            EmployeeController employeeController = new EmployeeController();
            return employeeController.Read(id);
        }

        public Employee ReadByUsername(string username)
        {
            EmployeeController employeeController = new EmployeeController();
            return employeeController.ReadByUsername(username);
        }

        public bool CompareHashes(string storedHash, string enteredHash)
        {
            EmployeeController employeeController = new EmployeeController();
            return employeeController.CompareHashes(storedHash, enteredHash);
        }

        public bool Update(Employee employee, string oldUsername, string salt)
        {
            EmployeeController employeeController = new EmployeeController();
            return employeeController.Update(employee, oldUsername, salt);
        }
    }
}