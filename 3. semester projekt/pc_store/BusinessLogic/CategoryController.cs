using DataAccess;
using Domain;
using System.Collections.Generic;

namespace BusinessLogic
{
    public class CategoryController
    {
        public IDbCategory _dbCategory { get; set; }
        /// <summary>
        /// This method will instantiate a new categorycontroller.
        /// </summary>
        public CategoryController()
        {
            _dbCategory = new DbCategory();
        }

        /// <summary>
        /// This method will create a new category.
        /// </summary>
        /// <param name="category"></param>
        /// the category that will be created.
        public void Create(Category category)
        {
            _dbCategory.Create(category);
        }

        /// <summary>
        /// This method will find the given category based on the type.
        /// </summary>
        /// <param name="type"></param>
        /// It will find the category based on the type
        /// <returns></returns>
        /// return the category based on the type.
        public Category Read(string type)
        {
            return _dbCategory.Read(type);
        }

        /// <summary>
        /// This method will update the category based on the category and the old type.
        /// </summary>
        /// <param name="category"></param>
        /// the wanted category to be updated.
        /// <param name="oldType"></param>
        /// the old type of the category that wants to be changed.
        public void Update(Category category, string oldType)
        {
            _dbCategory.Update(category,oldType);
        }

        /// <summary>
        /// This method will delete a given category.
        /// </summary>
        /// <param name="category"></param>
        /// The given category which will be deleted. 
        public void Delete(Category category)
        {
            _dbCategory.Delete(category);
        }

        /// <summary>
        /// This is an unidentified list that contains categories.
        /// </summary>
        /// <returns></returns>
        /// This method will return all the categories. 
        public IEnumerable<Category> GetAll()
        {
            return _dbCategory.GetAll();
        }
    }
}