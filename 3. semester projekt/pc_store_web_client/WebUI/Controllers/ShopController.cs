using System;
using System.Linq;
using System.ServiceModel;
using System.Web.Mvc;
using WebUI.CategoryServiceReference;
using WebUI.CustomerServiceReference;
using WebUI.OrderServiceReference;

namespace WebUI.Controllers
{
    public class ShopController : Controller
    {
        
        private CategoryServiceClient categoryProxy;
        private OrderServiceClient orderProxy;
        private CustomerServiceClient customerProxy;
        private static int orderId;
        private static int tempOrderId;

        public ShopController()
        {
            categoryProxy = new CategoryServiceClient();
            orderProxy = new OrderServiceClient();
            customerProxy = new CustomerServiceClient();
        }

        /// <summary>
        /// Creates an order, if it not already exists
        /// </summary>
        public void CreateOrder()
        {
            if (orderId == 0)
            {
                orderId = orderProxy.Create();
            }
        }

        /// <summary>
        /// Refers to call the View Index.cshtml
        /// Calls CreateOrder(), and returns products on the current order
        /// </summary>
        /// <returns>ActionResult view</returns>
        public ActionResult Index()
        {
            CreateOrder();
            var orderList = orderProxy.GetAllProductsByOrderId(orderId);
            return View(orderList);
        }

        /// <summary>
        /// Refers to call the View BuyComponent.cshtml
        /// Calls CreateOrder(), and returns all categories on the database
        /// </summary>
        /// <returns>ActionResult view</returns>
        public ActionResult BuyComponent()
        {
            CreateOrder();
            var items = categoryProxy.GetAll();
            return View(items);
        }

        /// <summary>
        /// Adds the product, the customer has chosen, to the order
        /// </summary>
        /// <param name="pId">int productId</param>
        /// <param name="amount">int amount</param>
        /// <returns>ActionResult view</returns>
        [HttpPost]
        public ActionResult BuyComponent(int pId, int amount)
        {
            CreateOrder();
            orderProxy.AddProduct(orderId, pId, amount);
            return RedirectToAction("Index");
        }

        /// <summary>
        /// Refers to call the View AddCustomer.cshtml
        /// </summary>
        /// <returns>ActionResult view</returns>
        public ActionResult AddCustomer()
        {
            return View();
        }

        /// <summary>
        /// Adds the customer to the order in the database, and redirects to the invoice
        /// </summary>
        /// <param name="customer">Customer customer</param>
        /// <returns>ActionResult view</returns>
        [HttpPost]
        public ActionResult AddCustomer(CustomerServiceReference.Customer customer)
        {
            int customerId = customerProxy.Create(customer);
            orderProxy.AddCustomer(orderId, customerId);
            tempOrderId = orderId;
            orderId = 0;
            CreateOrder();

            return RedirectToAction("Invoice");
        }

        /// <summary>
        /// Calls CreateOrder(), and reads the content on the order on the database.
        /// If the order exists, and has content, it returns the content from the shopping cart
        /// If the order does not exist or has not content, it redirects to the shopping cart
        /// </summary>
        /// <returns>ActionResult view</returns>
        public ActionResult Invoice()
        {
            ActionResult returnValue = null;
            if (tempOrderId != 0)
            {
                CreateOrder();
                Order order = orderProxy.Read(tempOrderId);
                orderProxy.SetProcessed(tempOrderId, true);
                
                if (order != null)
                {
                    OrderServiceReference.Customer customer = order._customer;
                    ViewBag.OrderId = tempOrderId;

                    ViewBag.Name = customer._fName + " " + customer._lName;
                    ViewBag.Address = customer._address;
                    ViewBag.Zip = customer._zipCity._zipCode + " " + customer._zipCity._city;
                    ViewBag.Email = customer._email;
                    ViewBag.Phone = customer._phone;

                    var orderList = orderProxy.GetAllProductsByOrderId(tempOrderId);

                    if (!orderList.Any())
                    {
                        returnValue = RedirectToAction("Index");
                    }
                    returnValue = View(orderList);
                }
                else
                {
                    returnValue = RedirectToAction("Index");
                }
            }
            else
            {
                returnValue = HttpNotFound();
            }
            return returnValue;
        }
    }
}
