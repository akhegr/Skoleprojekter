using System.Runtime.Serialization;

namespace Exceptions
{
    [DataContract]
    public class ProductNotOnStockException
    {
        [DataMember]
        public string Message { get; set; }
        /// <summary>
        /// This exception will appear if the product is no longer on stock.
        /// </summary>
        /// <param name="productName"></param>
        /// The product there is no longer on stock. It is found by its name.  
        public ProductNotOnStockException(string productName)
        {
            Message = $"Produktet {productName} er ikke på lager";
        }
    }
}
