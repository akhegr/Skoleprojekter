using System.Runtime.Serialization;

namespace Exceptions
{
    [DataContract]
    public class ZipCodeNotInDbException
    {
        [DataMember]
        private string Message { get; set; }
        /// <summary>
        /// This exception tells if the zipcode does not exist. 
        /// </summary>
        /// <param name="zipCode"></param>
        /// The input paramter that is going to be checked. 
        public ZipCodeNotInDbException(string zipCode)
        {
            Message = $"Postnumret {zipCode} eksisterer ikke";
        }
    }
}
