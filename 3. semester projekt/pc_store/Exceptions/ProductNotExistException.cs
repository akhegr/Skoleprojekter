using System.Runtime.Serialization;

namespace Exceptions
{
    [DataContract]
    public class ProductNotExistException
    {
        [DataMember]
        public string Message { get; set; }
        /// <summary>
        /// This exception tells if the product does not exist in the system. 
        /// </summary>
        /// <param name="product"></param>
        /// The product that does not exist in the system. 
        public ProductNotExistException(string product)
        {
            Message = $"Varenr. {product} eksisterer ikke i systemet";
        }
        
    }
}
