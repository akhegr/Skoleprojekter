using Domain;
using System.Collections.Generic;

namespace DataAccess
{
    public interface IDbCategory
    {
        int Create(Category category);
        Category Read(string type);
        void Update(Category category, string oldType);
        void Delete(Category category);
        IEnumerable<Category> GetAll();
    }
}
