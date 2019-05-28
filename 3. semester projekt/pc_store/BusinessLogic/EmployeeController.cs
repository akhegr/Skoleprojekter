using DataAccess;
using Domain;
using Exceptions;
using System.Collections.Generic;
using System.ServiceModel;

namespace BusinessLogic
{
    public class EmployeeController
    {
        public IDbEmployee _dbEmployee { get; set; }
        
        public EmployeeController()
        {
            _dbEmployee = new DbEmployee();
        }
        
        public int Create(Employee customer)
        {
            return _dbEmployee.Create(customer);
        }

        public Employee Read(int id)
        {
            return _dbEmployee.Read(id);
        }

        public Employee ReadByUsername(string username)
        {
            return _dbEmployee.ReadByUsername(username);
        }

        public bool Update(Employee customer, string oldUsername, string salt)
        {
            return _dbEmployee.Update(customer, oldUsername, salt);
        }

        public bool Delete(Employee customer)
        {
            return _dbEmployee.Delete(customer);
        }

        public bool CompareHashes(string storedHash, string enteredHash)
        {
            bool status = true;
            if (!(string.Equals(storedHash, enteredHash)))
            {
                throw new FaultException<EmployeeNotExistException>(new EmployeeNotExistException());
            }
            return status;
        }
    }
}
