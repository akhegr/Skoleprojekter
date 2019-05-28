using DataAccess;
using Domain;
using System.Collections.Generic;

namespace BusinessLogic
{
    public class OrderController
    {
        private IDbOrder _dbOrder { get; set; }
        private IDbOrderLine _dbOrderLine { get; set; }
        private IDbOrderLineList _dbOrderLineList { get; set; }

        /// <summary>
        /// This method will instantiate a new orderController. 
        /// </summary>
        public OrderController()
        {
            _dbOrder = new DbOrder();
            _dbOrderLine = new DbOrderLine();
            _dbOrderLineList = new DbOrderLineList();
        }

        /// <summary>
        /// This method will create a new order.
        /// </summary>
        /// <returns></returns>
        /// It will return the order that has been created. 
        public int CreateOrder()
        {
            Order order = new Order
            {
                _customer = null,
                _delivery = false,
                _processed = false
            };    

            return _dbOrder.Create(order);
        }

        /// <summary>
        /// This method will find an order based on the given order id.
        /// </summary>
        /// <param name="orderId"></param>
        /// The id that has been assigned to the order.
        /// <returns></returns>
        /// It will return the order with the chosen id.
        public Order ReadOrder(int orderId)
        {
            return _dbOrder.Read(orderId);
        }

        /// <summary>
        /// This method will add a uniqueproduct to an unidentified list. 
        /// </summary>
        /// <param name="orderId"></param>
        /// The id of the order.
        /// <param name="productId"></param>
        /// The id of the product.
        /// <param name="amount"></param>
        /// The chosen amount of the given product.
        /// <returns></returns>
        /// It will return the list of uniqueproducts. 
        public IEnumerable<UniqueProduct> AddProduct(int orderId, int productId, int amount)
        {
            ProductController pController = new ProductController();
            Product product = pController.FindById(productId);
            IEnumerable<UniqueProduct> listOfProducts = pController.FindUniqueByProducts(product, amount);

            OrderLine orderLine = new OrderLine
            {
                _orderId = orderId,
                _uniqueProducts = listOfProducts
            };
            
            _dbOrderLine.Create(orderLine);
            return listOfProducts;
        }

        /// <summary>
        /// This method will add a customer to an order.
        /// </summary>
        /// <param name="orderId"></param>
        /// The id of the order
        /// <param name="customerId"></param>
        /// The id of the customer
        public void AddCustomer(int orderId, int customerId)
        {
            CustomerController customerController = new CustomerController();
            Customer customer = customerController.Read(customerId);
            Order order = _dbOrder.Read(orderId);
            order._customer = customer;
            _dbOrder.Update(order);
        }

        /// <summary>
        /// This method will find all the products on a list based on an order id
        /// </summary>
        /// <param name="orderId"></param>
        /// The id of the order.
        /// <returns></returns>
        /// It will return all the products from the given order.
        public IEnumerable<UniqueProduct> GetAllProductsByOrderId(int orderId)
        {
            IEnumerable<OrderLine> olList = _dbOrderLine.GetAllWithOrderId(orderId);
            List<UniqueProduct> products = new List<UniqueProduct>();
            foreach (OrderLine ol in olList)
            {
                int orderLineId = ol._id;
                IEnumerable<UniqueProduct> localUniqueProducts = _dbOrderLineList.GetAllProductsByOlId(orderLineId);
                products.AddRange(localUniqueProducts);
            }

            return products;
        }


        public IEnumerable<Order> GetAll()
        {
            return _dbOrder.GetAll();
        }

        /// <summary>
        /// This method will change the processed status.
        /// </summary>
        /// <param name="orderId"></param>
        /// The id of the order.
        /// <param name="status"></param>
        /// The given status the order is in. If it is processed or not. 
        public bool SetProcessed(int orderId, bool status)
        {
            Order order = ReadOrder(orderId);
            order._processed = status;
            return _dbOrder.Update(order);
        }

        /// <summary>
        /// This method will tell if a specific order is processed. 
        /// </summary>
        /// <param name="orderId"></param>
        /// The id of the order.
        /// <returns></returns>
        /// It will return the status of the given order.
        public bool GetProcessed(int orderId)
        {
            Order order = ReadOrder(orderId);
            return order._processed;
        }
    }
}
