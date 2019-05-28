using System.Collections.Generic;
using BusinessLogic;
using Domain;

namespace OrderService
{
    public class OrderService : IOrderService
    {
        private OrderController orderController;
        public OrderService()
        {
            orderController = new OrderController();
        }
        /// <summary>
        /// Uses the method Create from the orderController using the given parameters.
        /// </summary>
        /// <returns>int orderId</returns>
        public int Create()
        {
            return orderController.CreateOrder();
        }

        /// <summary>
        /// Uses the method Read from the orderController using the given parameters.
        /// </summary>
        /// <param name="orderId"></param>
        /// <returns>int orderId</returns>
        public Order Read(int orderId)
        {
            return orderController.ReadOrder(orderId);
        }
        
        /// <summary>
        /// Uses the method AddProduct from the orderController using the given parameters.
        /// </summary>
        /// <param name="orderId"></param>
        /// <param name="productId"></param>
        /// <param name="amount"></param>
        /// <returns>IEnumerable<UniqueProduct></returns>
        public IEnumerable<UniqueProduct> AddProduct(int orderId, int productId, int amount)
        {
            return orderController.AddProduct(orderId, productId, amount);
        }

        /// <summary>
        /// Uses the method AddCustomer from the orderController.
        /// </summary>
        /// <param name="orderId"></param>
        /// <param name="customerId"></param>
        public void AddCustomer(int orderId, int customerId)
        {
            orderController.AddCustomer(orderId, customerId);
        }


        public bool SetProcessed(int orderId, bool status)
        {
            return orderController.SetProcessed(orderId, status);
        }
        

        /// <summary>
        /// Uses the method GetAllProductsByOrderId from the orderController.
        /// </summary>
        /// <param name="id"></param>
        /// <returns>IEnumerable<UniqueProduct>uniqueProductList</returns>
        public IEnumerable<UniqueProduct> GetAllProductsByOrderId(int id)
        {
            return orderController.GetAllProductsByOrderId(id);
        }

        public IEnumerable<Order> GetAll()
        {
            return orderController.GetAll();
        }
    }
}