using Domain;
using System.Collections.Generic;

namespace DataAccess
{
    public interface IDbSupplier
    {
        int Create(Supplier supplier);
        Supplier Read(string cvrNo);
        void Update(Supplier supplier, string oldPhone);
        void Delete(Supplier supplier);
        IEnumerable<Supplier> GetAll();
    }
}
