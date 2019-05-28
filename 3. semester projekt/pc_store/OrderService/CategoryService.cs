using BusinessLogic;
using Domain;
using System.Collections.Generic;

namespace OrderService
{
    public class CategoryService : ICategoryService
    {
        /// <summary>
        /// Creates a new categorycontroller and uses its method read with the given parameters.
        /// </summary>
        /// <param name="type"></param>
        /// <returns>Category category </returns>
        public Category Read(string type)
        {
            CategoryController categoryController = new CategoryController();
            return categoryController.Read(type);
        }
        /// <summary>
        /// Creates a new categorycontroller and uses its method getall to get all categories.
        /// </summary>
        /// <returns>IEnumerable<Category> listOfCategories</returns>
        public IEnumerable<Category> GetAll()
        {
            CategoryController categoryController = new CategoryController();
            return categoryController.GetAll();
        }
    }
}