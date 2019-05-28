using DataAccess;
using Domain;
using Exceptions;
using System.Collections.Generic;
using System.ServiceModel;

namespace BusinessLogic
{
    public class CustomerController
    {
        public IDbCustomer _dbCustomer { get; set; }
        /// <summary>
        /// This method will instantiate a new customercontroller.
        /// </summary>
        public CustomerController()
        {
            _dbCustomer = new DbCustomer();
        }

        /// <summary>
        /// This method will create a new customer.
        /// </summary>
        /// <param name="customer"></param>
        /// the customer thats going to be created.
        /// <returns></returns>
        /// It will return the given customer that has been created.
        public int Create(Customer customer)
        {
            return _dbCustomer.Create(customer);
        }

        /// <summary>
        /// This method will find a customer based on the id assigned to the customer.
        /// </summary>
        /// <param name="id"></param>
        /// the specific id the customer has been assigned to. 
        /// <returns></returns>
        /// it will return the customer with the given id. 
        public Customer Read(int id)
        {
            return _dbCustomer.Read(id);
        }

        /// <summary>
        /// This method will find a customer based on his phonenumber
        /// </summary>
        /// <param name="phone"></param>
        /// The phonenumber the customer gave in his contact information.
        /// <returns></returns>
        /// It will return the customer with the chosen phonenumber.
        public Customer ReadByPhone(string phone)
        {
            return _dbCustomer.ReadByPhone(phone);
        }

        /// <summary>
        /// This method will tell if it updated a customer based on a phonenumber.
        /// </summary>
        /// <param name="customer"></param>
        /// The chosen that customer there is going to be updated.
        /// <param name="oldPhone"></param>
        /// The old phone number. 
        /// <returns></returns>
        /// It will return true or false depending on if the customer was updated.
        public bool Update(Customer customer, string oldPhone)
        {
            return _dbCustomer.Update(customer, oldPhone);
        }

        /// <summary>
        /// This method will tell if it deleted a customer.
        /// </summary>
        /// <param name="customer"></param>
        /// The customer it will check and delete.
        /// <returns></returns>
        /// It will return true or false depending on if the customer was deleted. 
        public bool Delete(Customer customer)
        {
            bool status = _dbCustomer.Delete(customer);
            if (status == false)
            {
                throw new FaultException<CustomerNotDeletedException>(new CustomerNotDeletedException(customer._phone));
            }
            return status;
        }

        /// <summary>
        /// This method contains an unidentified list of customer.
        /// </summary>
        /// <returns></returns>
        /// It will return an unidentified list of customers. 
        public IEnumerable<Customer> GetAll()
        {
            return _dbCustomer.GetAll();
        }
    }
}
