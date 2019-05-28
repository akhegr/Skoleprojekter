using System.Collections.Generic;

namespace Domain
{
    public class OrderLine
    {
        public int _id { get; set; }
        public int _orderId { get; set; }
        public IEnumerable<UniqueProduct> _uniqueProducts { get; set; }
    }
}
