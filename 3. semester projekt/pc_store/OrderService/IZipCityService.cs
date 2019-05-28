using Domain;
using System.Collections.Generic;
using System.ServiceModel;
using Exceptions;

namespace OrderService
{
    [ServiceContract]
    public interface IZipCityService
    {
        [OperationContract]
        ZipCity Read(int id);

        [OperationContract]
        //[FaultContract(typeof(ZipCodeLengthException))]
        //[FaultContract(typeof(ZipCodeNotInDbException))]
        ZipCity ReadByZipCode(string zipCode);

        [OperationContract]
        //[FaultContract(typeof(ZipCodeNotInDbException))]
        IEnumerable<ZipCity> GetAll();
        
    }
}