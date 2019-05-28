namespace Domain
{
    public class Customer
    {
        public int _id { get; set; }
        public string _fName { get; set; }
        public string _lName { get; set; }
        public string _email { get; set; }
        public string _address { get; set; }
        public string _phone { get; set; }
        public ZipCity _zipCity { get; set; }

        public override string ToString()
        {
            return _fName + " " + _lName;
        }
    }
}
