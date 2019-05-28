using Domain;

namespace DataAccess
{
    public interface IDbEmployee
    {
        int Create(Employee employee);

        Employee Read(int id);

        Employee ReadByUsername(string username);

        bool Update(Employee employee, string username, string salt);

        bool Delete(Employee employee);
    }
}
