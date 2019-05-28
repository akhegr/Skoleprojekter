using Domain;
using System.Collections.Generic;

namespace DataAccess
{
    public interface IDbZipCity
    {
        ZipCity Read(int id);

        ZipCity ReadByZipCode(string zipCode);
        IEnumerable<ZipCity> GetAll();
    }
}
