using Domain;
using System.Collections.Generic;
using System.ServiceModel;

namespace OrderService
{
    [ServiceContract]
    public interface ICategoryService
    {
        [OperationContract]
        Category Read(string type);    
        [OperationContract]
        IEnumerable<Category> GetAll();
    }
}