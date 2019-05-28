using Domain;
using System.Collections.Generic;

namespace DataAccess
{
    public interface IDbOrderLineList
    {
        int Create(OrderLineList orderLineList);
        OrderLineList Read(int orderLineId, int uniqueProductId);
        void Delete(OrderLineList orderLineList);
        List<OrderLineList> GetAll();
        List<UniqueProduct> GetAllProductsByOlId(int orderLineId);
    }
}
