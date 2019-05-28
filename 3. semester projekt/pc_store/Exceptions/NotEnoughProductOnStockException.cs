using System.Runtime.Serialization;

namespace Exceptions
{
    [DataContract]
    public class NotEnoughProductOnStockException
    {
        [DataMember]
        public string Message { get; set; }
        /// <summary>
        /// This exception tells if there are not enough products on stock. 
        /// </summary>
        /// <param name="productName"></param>
        /// This is the product which is not on stock. It is found based on its name. 
        public NotEnoughProductOnStockException(string productName)
        {
            Message = $"Der er ikke nok {productName} på lager";
        }
    }
}