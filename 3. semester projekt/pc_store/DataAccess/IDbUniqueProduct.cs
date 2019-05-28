using Domain;
using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;

namespace DataAccess
{
    public interface IDbUniqueProduct
    {
        int Create(UniqueProduct uniqueProduct);
        UniqueProduct Read(string phone);
        void Update(UniqueProduct uniqueProduct);
        void Delete(UniqueProduct uniqueProduct);
        IEnumerable<UniqueProduct> GetAll();
    }
}
