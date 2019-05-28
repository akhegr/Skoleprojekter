using System.Collections.Generic;
using BusinessLogic;
using Domain;

namespace OrderService
{
    public class ZipCityService : IZipCityService
    {

        /// <summary>
        /// Creates a new ZipCitycontroller and uses its method read with the given parameters.
        /// </summary>
        /// <param name="id"></param>
        /// <returns>Zipcity zipcity</returns>
        public ZipCity Read(int id)
        {
            ZipCityController zipCityController = new ZipCityController();
            return zipCityController.Read(id);
        }

        /// <summary>
        /// Creates a new ZipCitycontroller and uses its method readByZipCode with the given parameters.
        /// </summary>
        /// <param name="zipCode"></param>
        /// <returns>Zipcity zipcity</returns>
        public ZipCity ReadByZipCode(string zipCode)
        {
            ZipCityController zipCityController = new ZipCityController();
            return zipCityController.ReadByZipCode(zipCode);
        }

        /// <summary>
        /// Creates a new ZipCitycontroller and uses its method getall.
        /// </summary>
        /// <returns>IEnumerable<ZipCity> ListOfzipcities</returns>
        public IEnumerable<ZipCity> GetAll()
        {
            ZipCityController zipCityController = new ZipCityController();
            return zipCityController.GetAll();
        }
    }
}