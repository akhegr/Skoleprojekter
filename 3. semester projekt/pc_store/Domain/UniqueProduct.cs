namespace Domain
{
    public class UniqueProduct
    {
        public int _id { get; set; }
        public string _serialNo { get; set; }
        public string _warranty { get; set; }
        public Product _product { get; set; }
    }
}
