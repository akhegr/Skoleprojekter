using Domain;
using System.Collections.Generic;

namespace DataAccess
{
    public interface IDbOrderLine
    {
        int Create(OrderLine orderLine);
        OrderLine Read(int orderId);
        IEnumerable<OrderLine> GetAllWithOrderId(int orderId);
        void Delete(OrderLine orderLine);
        IEnumerable<OrderLine> GetAll();
    }
}
