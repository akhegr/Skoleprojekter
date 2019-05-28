using System.Collections.Generic;
using System.Linq;
using System.Web.Http;
using WebUI.OrderServiceReference;

namespace WebUI.Controllers
{
    public class OrderController : ApiController
    {
        private OrderServiceClient myProxy;
        public OrderController()
        {
            myProxy = new OrderServiceClient();
        }

        /// <summary>
        /// Returns all orders by orderId
        /// </summary>
        /// <returns>IHttpActionResult</returns>
        // GET api/order/?orderId=<id>
        public IHttpActionResult GetByOrderId(int orderId)
        {
            IEnumerable<UniqueProduct> products = myProxy.GetAllProductsByOrderId(orderId);
            
            //Checks if the list is empty
            if (!products.Any())
            {
                return NotFound();
            }
            return Ok(products);
        }
    }
}