using DataAccess;
using Domain;
using Exceptions;
using System.Collections.Generic;
using System.ServiceModel;

namespace BusinessLogic
{
    public class ProductController
    {
        public DbProduct _dbProduct { get; set; }
        public DbUniqueProduct _dbUniqueProduct { get; set; }

        /// <summary>
        /// This method will instantiate a new productcontroller
        /// </summary>
        public ProductController()
        {
            _dbProduct = new DbProduct();
            _dbUniqueProduct = new DbUniqueProduct();
        }

        /// <summary>
        /// This method will create a new product.
        /// </summary>
        /// <param name="product"></param>
        /// This is the product that will be created.
        /// <returns></returns>
        /// It will return the created product. 
        public int Create(Product product)
        {
            return _dbProduct.Create(product);
        }

        /// <summary>
        /// This method will create a new uniqueproduct. 
        /// </summary>
        /// <param name="uniqueProduct"></param>
        /// This is the uniquepriduct that will be created.
        /// <returns></returns>
        /// It will return the created uniqueproduct. 
        public int CreateUniqueProduct(UniqueProduct uniqueProduct)
        {
            return _dbUniqueProduct.Create(uniqueProduct);
        }

        /// <summary>
        /// This method will find a product based on its name.
        /// </summary>
        /// <param name="name"></param>
        /// This is the name of the products.
        /// <returns></returns>
        /// It will return the given product based on the name.
        public Product Read(string name)
        {
            return _dbProduct.Read(name);
        }


        /// <summary>
        /// This method will find a product based on the id
        /// </summary>
        /// <param name="id"></param>
        /// This is the id of the product.
        /// <returns></returns>
        /// It will return the given product with searched id.
        public Product FindById(int id)
        {
            return _dbProduct.FindById(id);
        }

        /// <summary>
        /// This method will create an unidentified list of uniqueproducts witht he amnount of the products.
        /// </summary>
        /// <param name="product"></param>
        /// The product there is going to be added to the list
        /// <param name="amount"></param>
        /// The maount of the product that is going to be added to the list.
        /// <returns></returns>
        /// It will return a list with the products and the amount. 
        public IEnumerable<UniqueProduct> FindUniqueByProducts(Product product, int amount)
        {
            return _dbUniqueProduct.FindUniqueProductsOnStock(product, amount);
        }

        /// <summary>
        /// This method will update the name of the product.
        /// </summary>
        /// <param name="product"></param>
        /// The products that is going to be updated
        /// <param name="oldName"></param>
        /// The old name of the product.
        public void Update(Product product, string oldName)
        {
            _dbProduct.Update(product, oldName);
        }

        /// <summary>
        /// This method will delete the product
        /// </summary>
        /// <param name="product"></param>
        /// This is the product that is going to be deleted.
        public void Delete(Product product)
        {
            _dbProduct.Delete(product);
        }

        /// <summary>
        /// This method will create an unidentifed list of products
        /// </summary>
        /// <returns></returns>
        /// It will return all the products in a list. 
        public IEnumerable<Product> GetAll()
        {
            return _dbProduct.GetAll();
        }
    }
}
