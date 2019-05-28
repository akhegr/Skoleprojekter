using Domain;
using Exceptions;
using System.Collections.Generic;
using System.ServiceModel;

namespace OrderService
{
    [ServiceContract]
    public interface IEmployeeService
    {
        [OperationContract]
        [FaultContract(typeof(DbConnectionError))]
        [FaultContract(typeof(EmployeeNotCreatedException))]
        int Create(Employee employee);

        [OperationContract]
        [FaultContract(typeof(DbConnectionError))]
        Employee Read(int id);

        [OperationContract]
        [FaultContract(typeof(DbConnectionError))]
        [FaultContract(typeof(EmployeeNotExistException))]
        Employee ReadByUsername(string username);

        [OperationContract]
        [FaultContract(typeof(DbConnectionError))]
        [FaultContract(typeof(EmployeeNotExistException))]
        bool CompareHashes(string storedHash, string enteredHash);

        [OperationContract]
        [FaultContract(typeof(DbConnectionError))]
        bool Update(Employee employee, string oldUsername, string salt);
    }
}