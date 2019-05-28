using System.Collections.Generic;

namespace Domain
{
    public interface ICRUD
    {
        T Create<T>();
        T Get<T>();
        List<T> GetAll<T>();
        T Update<T>();
        T Delete<T>();
    }
}