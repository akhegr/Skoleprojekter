using System.Runtime.Serialization;

namespace Exceptions
{
    [DataContract]
    public class CustomerNotDeletedException
    {
        [DataMember]
        public string Message { get; set; }
        /// <summary>
        /// This exception will tell if the customer has not been deleted. It will delete the customer based on the phone number. 
        /// </summary>
        /// <param name="phone"></param>
        /// The input paramter is the number that was not deleted. 
        public CustomerNotDeletedException(string phone)
        {
            Message = $"Kunden med telefon nr. {phone} er ikke blevet slettet";
        }
    }
}