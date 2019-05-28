namespace Domain
{
    public class Product
    {
        public int _id { get; set; }
        public string _name { get; set; }
        public decimal _price { get; set; }
        public string _description { get; set; }
        public int _supplierId { get; set; }
        public int _categoryId { get; set; }
    }
}
