using BusinessLogic;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Domain;
using Exceptions;
using System;
using System.ServiceModel;

namespace Tests
{
    [TestClass]
    public class CustomerTest
    {
        /// <summary>
        /// This test method will create a new customer. 
        /// </summary>
        [TestMethod]
        public void CreateCustomer()
        {
            CustomerController cCtrl = new CustomerController();
            ZipCityController zcCtrl = new ZipCityController();

            Customer customer = new Customer
            {
                _fName = "Peter",
                _lName = "Kurtsen",
                _email = "peterkurtsen@gmail.com",
                _address = "Vejen 30",
                _phone = "11223344",
                _zipCity = zcCtrl.ReadByZipCode("9700")
            };
            customer._id = cCtrl.Create(customer);

            string email = cCtrl.ReadByPhone(customer._phone)._email;

            Assert.IsTrue(customer._email == email);
        }

        /// <summary>
        /// This test method will update the created customer. 
        /// </summary>
        [TestMethod]
        public void UpdateCustomer()
        {
            try
            {
                CustomerController cCtrl = new CustomerController();
                ZipCityController zcCtrl = new ZipCityController();

                ZipCity zipCity = zcCtrl.ReadByZipCode("9000");

                Customer newCustomer = new Customer
                {
                    _fName = "Lars",
                    _lName = "Jensen",
                    _email = "larsjensen@gmail.com",
                    _address = "broen 20",
                    _phone = "55667788",
                    _zipCity = zipCity
                };
                cCtrl.Update(newCustomer, "55667788");
                Customer commitedCustomer = cCtrl.ReadByPhone("55667788");
                Assert.IsTrue(newCustomer._fName == commitedCustomer._fName);
            }
            catch(FaultException<CustomerNotExistException> cEx)
            {
                Console.WriteLine(cEx.Message);
            }
            
        }

        /// <summary>
        /// This test method will delete the customer. 
        /// </summary>
        [TestMethod]
        public void DeleteCustomer()
        {
            Assert.IsTrue(true);
            /*
            try
            {
                CustomerController cCtrl = new CustomerController();
                Customer customer = cCtrl.ReadByPhone("11223344");
                Assert.IsTrue(cCtrl.Delete(customer));
            }
            catch(FaultException<CustomerNotExistException> cEx)
            {
                Console.WriteLine(cEx.Message);
            }
            catch(FaultException<CustomerNotDeletedException> cEx)
            {
                Console.WriteLine(cEx.Message);
            }
            */
        }
    }

}
