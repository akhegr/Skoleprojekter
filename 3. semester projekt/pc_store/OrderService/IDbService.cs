using Domain;
using System.Collections.Generic;
using System.ServiceModel;
using Exceptions;

namespace OrderService
{
    [ServiceContract]
    public interface IDbService
    {
        [OperationContract]
        [FaultContract(typeof(DbConnectionError))]
        void TestConnection();
    }
}