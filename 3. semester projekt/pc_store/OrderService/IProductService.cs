using Domain;
using System.Collections.Generic;
using System.ServiceModel;

namespace OrderService
{
    [ServiceContract]
    public interface IProductService
    {
        [OperationContract]
        Product Read(string name);

        [OperationContract]
        Product FindById(int id);

        [OperationContract]
        IEnumerable<UniqueProduct> FindUniqueByProduct(Product product, int amount);

        [OperationContract]
        IEnumerable<Product> GetAll();
    }
}