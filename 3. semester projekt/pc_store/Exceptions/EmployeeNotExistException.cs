using System.Runtime.Serialization;

namespace Exceptions
{
    [DataContract]
    public class EmployeeNotExistException
    {
        [DataMember]
        public string Message { get; set; }

        public EmployeeNotExistException()
        {
            Message = $"Medarbejderens brugernavn eller password er forkert.";
        }
    }
}