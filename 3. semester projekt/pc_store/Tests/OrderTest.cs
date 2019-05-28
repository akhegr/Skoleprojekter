using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using BusinessLogic;
using Domain;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace Tests
{
    [TestClass]
    public class OrderTest
    {
        private static Random random = new Random();

        /// <summary>
        /// This method will create a new order. 
        /// </summary>
        [TestMethod]
        public void CreateOrder()
        {
            // arrange
            OrderController oCtrl = new OrderController();

            // act
            int orderId = oCtrl.CreateOrder();

            // assert
            Assert.IsTrue(orderId != 0);
        }

        /// <summary>
        /// This test method will create a random string. 
        /// </summary>
        /// <param name="length"></param>
        /// <returns></returns>
        private static string RandomString(int length)
        {
            const string chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
            return new string(Enumerable.Repeat(chars, length)
              .Select(s => s[random.Next(s.Length)]).ToArray());
        }

        /// <summary>
        /// This test method will add a product to the order. 
        /// </summary>
        [TestMethod]
        public void AddProductToOrder()
        {
            // arrange
            OrderController oCtrl = new OrderController();
            ProductController pCtrl = new ProductController();
            
            int amount = 1;
            Product testProduct = new Product
            {
                _description = "Testprodukt",
                _name = "Testprodukt",
                _price = 13,
                _categoryId = 2,
                _supplierId = 2
            };
            
            // act
            int productId = pCtrl.Create(testProduct);
            testProduct._id = productId;
            
            UniqueProduct testUniqueProduct = new UniqueProduct
            {
                _product = testProduct,
                _serialNo = RandomString(9),
                _warranty = "forever"
            };
            pCtrl.CreateUniqueProduct(testUniqueProduct);

            int orderId = oCtrl.CreateOrder();
            oCtrl.AddProduct(orderId, productId, amount);

            // assert
            Assert.IsTrue(orderId != 0);
        }

        /// <summary>
        /// This test method will get all the uniqueprodcuts based on the orderid.
        /// </summary>
        [TestMethod]
        public void TestGetAllUniqueProductsByOrderId()
        {
            // arrange
            OrderController orderController = new OrderController();

            // act
            IEnumerable<UniqueProduct> ups = orderController.GetAllProductsByOrderId(1);
            foreach (UniqueProduct up in ups)
            {
                Console.WriteLine(up._product._name + " - " + up._serialNo);
            }

            // assert
            Assert.IsTrue(ups != null);
        }

        /// <summary>
        /// This test method will add a customer to the order. 
        /// </summary>
        [TestMethod]
        public void AddCustomerToOrder()
        {
            // arrange
            OrderController oCtrl = new OrderController();
            CustomerController cCtrl = new CustomerController();
            ZipCityController zCtrl = new ZipCityController();

            Customer customer = new Customer
            {
                _address = "Ibsvej 1",
                _email = "ib@vej.dk",
                _fName = "Ib",
                _lName = "Ibsen",
                _phone = "24440744",
                _zipCity = zCtrl.ReadByZipCode("9000")
            };
            
            // act
            int orderId = oCtrl.CreateOrder();
            int customerId = cCtrl.Create(customer);

            oCtrl.AddCustomer(orderId, customerId);
            Order newOrder = oCtrl.ReadOrder(orderId);

            // assert
            Assert.IsTrue(newOrder._customer._id == customerId);
        }

        /// <summary>
        /// This test method will check if the order has been processed. 
        /// </summary>
        [TestMethod]
        public void TestProcessedOrder()
        {
            // arrange
            OrderController orderController = new OrderController();
            
            // act
            int orderId = orderController.CreateOrder();
            bool updated = orderController.SetProcessed(orderId, true);
            Thread.Sleep(30000);
            bool status = orderController.GetProcessed(orderId);

            // assert
            Assert.IsTrue(updated == true && status == true);
        }   
    }
}
