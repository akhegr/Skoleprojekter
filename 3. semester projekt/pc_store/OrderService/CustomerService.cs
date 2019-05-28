using System.Collections.Generic;
using BusinessLogic;
using Domain;

namespace OrderService
{
    public class CustomerService : ICustomerService
    {
        /// <summary>
        /// Creates a new customerController and uses its method create using the given parameters.
        /// </summary>
        /// <param name="customer"></param>
        /// <returns>int customerid</returns>
        public int Create(Customer customer)
        {
            CustomerController customerController = new CustomerController();
            return customerController.Create(customer);
        }
        /// <summary>
        /// Creates a new customercontroller and uses its method read with the given parameters.
        /// </summary>
        /// <param name="id"></param>
        /// <returns>Customer customer</returns>
        public Customer Read(int id)
        {
            CustomerController customerController = new CustomerController();
            return customerController.Read(id);
        }
        /// <summary>
        /// Creates a new customercontroller and uses its method readbyphone using the given parameters.
        /// </summary>
        /// <param name="phone"></param>
        /// <returns>Customer custoemer</returns>
        public Customer ReadByPhone(string phone)
        {
            CustomerController customerController = new CustomerController();
            return customerController.ReadByPhone(phone);
        }
        /// <summary>
        /// Creates a new customercontroller and uses its method update using the given parameters.
        /// </summary>
        /// <param name="customer"></param>
        /// <param name="oldPhone"></param>
        /// <returns>bool status</returns>
        public bool Update(Customer customer, string oldPhone)
        {
            CustomerController customerController = new CustomerController();
            return customerController.Update(customer, oldPhone);
        }
        /// <summary>
        /// Creates a new customercontroller and uses its method getall.
        /// </summary>
        /// <returns>IEnumerable<Customer> listOfCustomers</returns>
        public IEnumerable<Customer> GetAll()
        {
            CustomerController customerController = new CustomerController();
            return customerController.GetAll();
        }
    }
}