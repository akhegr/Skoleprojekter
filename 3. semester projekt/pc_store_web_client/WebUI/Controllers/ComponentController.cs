using System.Collections.Generic;
using System.Linq;
using System.Web.Http;
using WebUI.ProductServiceReference;

namespace WebUI.Controllers
{
    public class ComponentController : ApiController
    {
        private ProductServiceClient myProxy;

        public ComponentController()
        {
            myProxy = new ProductServiceClient();
        }

        /// <summary>
        /// Gets an IEnumerable of all products in the database
        /// </summary>
        /// <returns>IEnumerable<Product> listOfProducts</returns>
        public IEnumerable<Product> GetAllComponents()
        {
            return myProxy.GetAll();
        }

        /// <summary>
        /// Gets a product with a specific id in the database
        /// </summary>
        /// <returns>IHttpActionResult</returns>
        public IHttpActionResult GetById(int id)
        {
            Product product = myProxy.FindById(id);
            if (product == null)
            {
                return NotFound();
            }
            return Ok(product);
        }

        /// <summary>
        /// Gets a list of products with a specific category in the database
        /// </summary>
        /// <param name="categoryId">int categoryId</param>
        /// <returns>IHttpActionResult</returns>
        public IHttpActionResult GetByCatId(int categoryId)
        {
            IEnumerable<Product> products = myProxy.GetAll();
            List<Product> catProducts = products.Where((c) => c._categoryId == categoryId).ToList();
            
            //Checks if the list is empty
            if (!catProducts.Any())
            {
                return NotFound();
            }
            return Ok(catProducts);
        }
    
    }
}