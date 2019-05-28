using System.Runtime.Serialization;

namespace Exceptions
{
    [DataContract]
    public class ZipCodeLengthException
    {
        [DataMember]
        public string Message { get; set; }
        /// <summary>
        /// This exception is there to make sure that the zipcode is legit and contains 4 characters. 
        /// </summary>
        /// <param name="zipCode"></param>
        /// The input paramter there is going to checked to see if it is legit. 
        public ZipCodeLengthException(string zipCode) {
            Message = $"Postnumret {zipCode} skal bestå af fire cifre";
        }
    }
}
