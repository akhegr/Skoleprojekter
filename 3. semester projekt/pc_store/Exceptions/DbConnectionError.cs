using System.Runtime.Serialization;

namespace Exceptions
{
    [DataContract]
    public class DbConnectionError
    {
        [DataMember]
        public string Message { get; set; }
        /// <summary>
        /// This exception will tell if there is not a valid connection to the database. 
        /// </summary>
        public DbConnectionError()
        {
            Message = $"Der kunne ikke skabes forbindelse til databasen";
        }
    }
}
