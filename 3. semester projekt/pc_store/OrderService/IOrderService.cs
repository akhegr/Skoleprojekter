using Domain;
using Exceptions;
using System.Collections.Generic;
using System.ServiceModel;

namespace OrderService
{
    [ServiceContract]
    public interface IOrderService
    {
        [OperationContract]
        int Create();

        [OperationContract]
        Order Read(int orderId);

        [OperationContract]
        //[FaultContract(typeof(ProductNotExistException))]
        //[FaultContract(typeof(ProductNotOnStockException))]
        //[FaultContract(typeof(NotEnoughProductOnStockException))]
        IEnumerable<UniqueProduct> AddProduct(int orderId, int productId, int amount);

        [OperationContract]
        [FaultContract(typeof(CustomerNotExistException))]
        void AddCustomer(int orderId, int customerId);

        [OperationContract]
        bool SetProcessed(int orderId, bool status);

        [OperationContract]
        IEnumerable<UniqueProduct> GetAllProductsByOrderId(int id);

        [OperationContract]
        IEnumerable<Order> GetAll();
    }
}