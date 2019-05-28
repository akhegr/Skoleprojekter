using System.Runtime.Serialization;

namespace Exceptions
{
    [DataContract]
    public class SupplierNotExistException
    {
        [DataMember]
        public string Message { get; set; }
        /// <summary>
        /// This exception tells if the supplier does not exist. 
        /// </summary>
        /// <param name="supplier"></param>
        /// The input paramter that is going to be checked. 
        public SupplierNotExistException(string supplier)
        {
            Message = $"Leverandøren {supplier} er ikke oprettet i systemet";
        }
    }
}
