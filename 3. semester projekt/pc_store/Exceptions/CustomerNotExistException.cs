using System.Runtime.Serialization;

namespace Exceptions
{
    [DataContract]
    public class CustomerNotExistException
    {
        [DataMember]
        public string Message { get; set; }
        /// <summary>
        /// This exception will tell if the customer does not exist in the system. 
        /// </summary>
        public CustomerNotExistException()
        {
            Message = $"Kunden blev ikke fundet i systemet";
        }
    }
}