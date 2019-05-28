namespace Domain
{
    public class Order
    {
        public int _id { get; set; }
        public bool _delivery { get; set; }
        public bool _processed { get; set; }
        public byte[] _lastChanged { get; set; }
        public Customer _customer { get; set; }
    }
}