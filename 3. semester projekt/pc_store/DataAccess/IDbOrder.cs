using Domain;
using System.Collections.Generic;

namespace DataAccess
{
    public interface IDbOrder
    {
        int Create(Order order);
        Order Read(int id);
        void Delete(Order order);
        bool Update(Order order);
        IEnumerable<Order> GetAll();
    }
}
