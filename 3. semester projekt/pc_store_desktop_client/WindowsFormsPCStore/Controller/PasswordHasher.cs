using System;
using System.Security.Cryptography;
using System.Text;
using WindowsFormsPCStore.EmployeeServiceReference;

namespace WindowsFormsPCStore.Controller
{
    public class PasswordHasher
    {
        // https://www.owasp.org/index.php/Using_Rfc2898DeriveBytes_for_PBKDF2
        private static string HashPassword(Employee employee, string enteredPassword)
        {
            byte[] salt = Encoding.ASCII.GetBytes(employee._salt);

            Rfc2898DeriveBytes rfc2898DeriveBytes = new Rfc2898DeriveBytes(enteredPassword, salt, 10000);
            byte[] hash = rfc2898DeriveBytes.GetBytes(20);
            
            //Return the salt and the hash
            return Convert.ToBase64String(hash);
        }

        // https://www.owasp.org/index.php/Using_Rfc2898DeriveBytes_for_PBKDF2
        private static string GenerateSalt(string password)
        {
            // Generate the hash, with an automatic 32 byte salt
            Rfc2898DeriveBytes rfc2898DeriveBytes = new Rfc2898DeriveBytes(password, 32);
            rfc2898DeriveBytes.IterationCount = 10000;
            byte[] salt = rfc2898DeriveBytes.Salt;
            
            //Return the salt and the hash
            return Convert.ToBase64String(salt);
        }

        private static Employee GetStoredEmployee(string username)
        {
            EmployeeServiceClient client = new EmployeeServiceClient();
            Employee employee = client.ReadByUsername(username);
            return employee;
        }

        private static string GetStoredHash(Employee employee)
        {
            return employee._password;
        }

        public static bool ComparePasswords(string username, string enteredPassword)
        {
            Employee employee = GetStoredEmployee(username);
            string storedHash = GetStoredHash(employee);
            string enteredHash = HashPassword(employee, enteredPassword);
                                    //FindUserHash(username, enteredPassword);
            Console.WriteLine("Lagret hash: " + storedHash);
            Console.WriteLine("Indtastet hash: " + enteredHash);

            EmployeeServiceClient client = new EmployeeServiceClient();
            return client.CompareHashes(storedHash, enteredHash);
        }

        public static void Create(string fName, string lName, string username, string password)
        {
            string salt = GenerateSalt(password);

            Employee employee = new Employee
            {
                _fName = fName,
                _lName = lName,
                _username = username,
                _password = null,
                _salt = salt
            };

            string hash = HashPassword(employee, password);

            employee._password = hash;

            EmployeeServiceClient client = new EmployeeServiceClient();
            client.Create(employee);
        }
    }
}
