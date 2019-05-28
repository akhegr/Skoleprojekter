using DataAccess;
using Domain;
using Exceptions;
using System.Collections.Generic;
using System.ServiceModel;

namespace BusinessLogic
{
    public class ZipCityController
    {
        public DbZipCity _dbZipCity { get; set; }
        /// <summary>
        /// This method will instantiate a new zipcitycontroller. 
        /// </summary>
        public ZipCityController()
        {
            _dbZipCity = new DbZipCity();
        }

        /// <summary>
        /// This method will find a certain zipcity based on the id
        /// </summary>
        /// <param name="id"></param>
        /// This is the id a given zipcity has. 
        /// <returns></returns>
        /// It will return the specific zipcity found from the id. 
        public ZipCity Read(int id)
        {
            return _dbZipCity.Read(id);
        }

        /// <summary>
        /// This method will find a zipcity based on the zipcode
        /// @Throws FaultException<ZipCodeLengthException It will throw an exception if the length of the zipcode is over 4 characters and therefore invalid. 
        /// </summary>
        /// <param name="zipCode"></param>
        /// This is the zipcode of a given zipcity. 
        /// <returns></returns>
        /// It will the given zipcity based on the zipcode. 
        public ZipCity ReadByZipCode(string zipCode)
        {
            if(zipCode.Length != 4)
            {
                throw new FaultException<ZipCodeLengthException>(new ZipCodeLengthException(zipCode));
            }
            return _dbZipCity.ReadByZipCode(zipCode);
        }


        /// <summary>
        /// This method will create an unidentified list of all hte zipcites 
        /// </summary>
        /// <returns></returns>
        /// It will return an unidentified list with all the zipcities. 
        public IEnumerable<ZipCity> GetAll()
        {
            return _dbZipCity.GetAll();
        }
    }
}
