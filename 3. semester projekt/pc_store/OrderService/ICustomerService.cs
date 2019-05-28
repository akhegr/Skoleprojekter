using Domain;
using Exceptions;
using System.Collections.Generic;
using System.ServiceModel;

namespace OrderService
{
    [ServiceContract]
    public interface ICustomerService
    {
        [OperationContract]
        int Create(Customer customer);

        [OperationContract]
        [FaultContract(typeof(CustomerNotExistException))]
        Customer Read(int id);

        [OperationContract]
        [FaultContract(typeof(CustomerNotExistException))]
        Customer ReadByPhone(string phone);

        [OperationContract]
        [FaultContract(typeof(CustomerNotExistException))]
        bool Update(Customer customer, string oldPhone);

        [OperationContract]
        [FaultContract(typeof(CustomerNotExistException))]
        IEnumerable<Customer> GetAll();
    }
}