using System.Collections.Generic;
using System.Linq;
using System.ServiceModel;
using System.Web.Http;
using WebUI.ZipCityServiceReference;

namespace WebUI.Controllers
{
    public class ZipCityController : ApiController
    {
        private ZipCityServiceClient myProxy;
        public ZipCityController()
        {
            myProxy = new ZipCityServiceClient();
        }
        
        /// <summary>
        /// Gets a list of cities in Denmark from the database
        /// </summary>
        /// <returns>IEnumerable<ZipCity> listOfCities</returns>
        public IEnumerable<ZipCity> GetAllCities()
        {
            IEnumerable<ZipCity> cities = null;
            cities = myProxy.GetAll();
            return cities;
        }

        /// <summary>
        /// Gets a city based on its id in the database
        /// </summary>
        /// <param name="id">int cityId</param>
        /// <returns>IHttpActionResult</returns>
        public IHttpActionResult GetById(int id)
        {
            IEnumerable<ZipCity> zipCities = myProxy.GetAll();
            var zipCity = zipCities.FirstOrDefault((z) => z._id == id);
            if (zipCity == null)
            {
                return NotFound();
            }
            return Ok(zipCity);
        }

        /// <summary>
        /// Gets a city based on its zipCode
        /// </summary>
        /// <param name="zipCode">int zipCode</param>
        /// <returns>IHttpActionResult</returns>
        public IHttpActionResult GetByZipCode(int zipCode)
        {
            ZipCity zipCity = myProxy.ReadByZipCode(zipCode.ToString());
            
            if (zipCity == null || zipCity._id == 0)
            {
                  return NotFound();
            }
             
            return Ok(zipCity);

        }
    }
}