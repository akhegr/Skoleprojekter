using Domain;
using System.Collections.Generic;

namespace DataAccess
{
    public interface IDbCustomer
    {
        int Create(Customer customer);
        Customer Read(int id);
        Customer ReadByPhone(string phone);
        bool Update(Customer customer, string oldPhone);
        bool Delete(Customer customer);
        IEnumerable<Customer> GetAll();
    }
}
