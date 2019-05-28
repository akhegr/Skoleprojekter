using System.Runtime.Serialization;

namespace Exceptions
{
    [DataContract]
    public class EmployeeNotCreatedException
    {
        [DataMember]
        public string Message { get; set; }

        public EmployeeNotCreatedException()
        {
            Message = $"Medarbejderen blev ikke oprettet.";
        }
    }
}