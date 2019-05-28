using Domain;
using System.Collections.Generic;

namespace DataAccess
{
    public interface IDbProduct
    {
        int Create(Product product);
        Product Read(string name);
        Product FindById(int id);
        void Update(Product product, string oldName);
        void Delete(Product product);
        IEnumerable<Product> GetAll();
    }
}
