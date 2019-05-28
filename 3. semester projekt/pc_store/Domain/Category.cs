using System.Collections.Generic;

namespace Domain
{
    public class Category
    {
        public int _id { get; set; }
        public string _type { get; set; }
        public IEnumerable<Product> _products { get; set; }

    }
}
