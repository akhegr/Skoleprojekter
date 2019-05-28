using System.Collections.Generic;
using BusinessLogic;
using Domain;

namespace OrderService
{
    public class ProductService : IProductService
    {
        /// <summary>
        /// Creates a new Productcontroller and uses its method read with the given parameters.
        /// </summary>
        /// <param name="name"></param>
        /// <returns>Product product</returns>
        public Product Read(string name)
        {
            ProductController productController = new ProductController();
            return productController.Read(name);
        }

        /// <summary>
        /// Creates a new Productcontroller and uses its method FindById with the given parameters.
        /// </summary>
        /// <param name="id"></param>
        /// <returns>Product product</returns>

        public Product FindById(int id)
        {
            ProductController productController = new ProductController();
            return productController.FindById(id);
        }

        /// <summary>
        /// Creates a new Productcontroller and uses its method FindUniqueByProduct with the given parameters.
        /// </summary>
        /// <param name="product"></param>
        /// <param name="amount"></param>
        /// <returns>IEnumerable<UniqueProduct> ListOfUnqueProducts</returns>

        public IEnumerable<UniqueProduct> FindUniqueByProduct(Product product, int amount)
        {
            ProductController productController = new ProductController();
            return productController.FindUniqueByProducts(product, amount);
        }

        /// <summary>
        /// Creates a new Productcontroller and uses its method getall.
        /// </summary>
        /// <returns>IEnumerable<Product> ListOfProducts</returns>

        public IEnumerable<Product> GetAll()
        {
            ProductController productController = new ProductController();
            return productController.GetAll();
        }
    }
}