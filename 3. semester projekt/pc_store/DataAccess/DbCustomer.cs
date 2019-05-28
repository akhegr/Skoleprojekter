using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data.SqlClient;
using System.ServiceModel;
using Domain;
using Exceptions;

namespace DataAccess
{
    public class DbCustomer : IDbCustomer
    {
        private readonly string _connectionString = ConfigurationManager.ConnectionStrings["DBString"].ConnectionString;
        private IDbZipCity _dbZipCity;

        public DbCustomer()
        {
            _dbZipCity = new DbZipCity();
        }

        /// <summary>
        /// Creates an instance of a customer in the database
        /// </summary>
        /// <param name="customer"></param>
        /// <returns>int id</returns>
        public int Create(Customer customer)
        {
            int id;

            using (SqlConnection connection = new SqlConnection(_connectionString))
            {
                connection.Open();
                
                using (SqlCommand cmd = connection.CreateCommand())
                {
                    cmd.CommandText = "INSERT INTO Customer (fName, lName, email, address, phone, zipCityId) " +
                                        "VALUES (@fName, @lName, @email, @address, @phone, @zipCityId); " +
                                        "SELECT SCOPE_IDENTITY()";

                    cmd.Parameters.AddWithValue("fName", customer._fName);
                    cmd.Parameters.AddWithValue("lName", customer._lName);
                    cmd.Parameters.AddWithValue("email", customer._email);
                    cmd.Parameters.AddWithValue("address", customer._address);
                    cmd.Parameters.AddWithValue("phone", customer._phone);
                    cmd.Parameters.AddWithValue("zipCityId", customer._zipCity._id);
                    id = Convert.ToInt32(cmd.ExecuteScalar());
                }
            }
            return id;
        }

        /// <summary>
        /// Returns a Customer from the database based on its id 
        /// </summary>
        /// <param name="id"></param>
        /// <returns>Customer customer</returns>
        public Customer Read(int id)
        {
            Customer customer = null;
            using (SqlConnection connection = new SqlConnection(_connectionString))
            {
                connection.Open();
                using (SqlCommand cmd = connection.CreateCommand())
                {
                    cmd.CommandText = "SELECT * FROM Customer WHERE id = @id";
                    cmd.Parameters.AddWithValue("id", id);
                    SqlDataReader reader = cmd.ExecuteReader();

                    while (reader.Read())
                    {
                        customer = new Customer
                        {
                            _id = reader.GetInt32(reader.GetOrdinal("id")),
                            _fName = reader.GetString(reader.GetOrdinal("fName")),
                            _lName = reader.GetString(reader.GetOrdinal("lName")),
                            _email = reader.GetString(reader.GetOrdinal("email")),
                            _address = reader.GetString(reader.GetOrdinal("address")),
                            _phone = reader.GetString(reader.GetOrdinal("phone")),
                            _zipCity = _dbZipCity.Read(reader.GetInt32(reader.GetOrdinal("zipCityId")))
                        };
                    }
                    if (!(customer is Customer))
                    {
                        throw new FaultException<CustomerNotExistException>(new CustomerNotExistException());
                    }
                }
            }
            return customer;
        }

        /// <summary>
        /// Returns a Customer from the databse based on its phone number
        /// </summary>
        /// <param name="phone"></param>
        /// <returns>Customer customer</returns>
        public Customer ReadByPhone(string phone)
        {
            Customer customer = null;
            using (SqlConnection connection = new SqlConnection(_connectionString))
            {
                connection.Open();
                using (SqlCommand cmd = connection.CreateCommand())
                {
                    cmd.CommandText = "SELECT * FROM Customer WHERE phone = @phone";
                    cmd.Parameters.AddWithValue("phone", phone);
                    SqlDataReader reader = cmd.ExecuteReader();

                    while (reader.Read())
                    {
                        customer = new Customer
                        {
                            _id = reader.GetInt32(reader.GetOrdinal("id")),
                            _fName = reader.GetString(reader.GetOrdinal("fName")),
                            _lName = reader.GetString(reader.GetOrdinal("lName")),
                            _email = reader.GetString(reader.GetOrdinal("email")),
                            _address = reader.GetString(reader.GetOrdinal("address")),
                            _phone = reader.GetString(reader.GetOrdinal("phone")),
                            _zipCity = _dbZipCity.Read(reader.GetInt32(reader.GetOrdinal("zipCityId")))
                        };
                    }
                    if (!(customer is Customer))
                    {
                        throw new FaultException<CustomerNotExistException>(new CustomerNotExistException());
                    }
                }
            }
            return customer;
        }

        /// <summary>
        /// Updates an existing customer based form its inputparameters
        /// </summary>
        /// <param name="customer"></param>
        /// <param name="oldPhone"></param>
        /// <returns>bool status</returns>
        public bool Update(Customer customer, String oldPhone)
        {
            bool status = false;
            int affected = 0;
            using (SqlConnection connection = new SqlConnection(_connectionString))
            {
                connection.Open();
                using (SqlCommand cmd = connection.CreateCommand())
                {
                    cmd.CommandText = "UPDATE Customer " +
                        "set fName = @fName, lName = @lName, email = @email, address = @address, phone = @phone, zipCityId = @zipCityId " +
                        "where phone = @oldPhone";
                    cmd.Parameters.AddWithValue("oldPhone", oldPhone);
                    cmd.Parameters.AddWithValue("fname", customer._fName);
                    cmd.Parameters.AddWithValue("lName", customer._lName);
                    cmd.Parameters.AddWithValue("email", customer._email);
                    cmd.Parameters.AddWithValue("address", customer._address);
                    cmd.Parameters.AddWithValue("phone", customer._phone);
                    cmd.Parameters.AddWithValue("zipCityId", customer._zipCity._id);
                    affected = cmd.ExecuteNonQuery();
                }
            }
            status = affected > 0;

            if (status == false)
            {
                throw new FaultException<CustomerNotExistException>(new CustomerNotExistException());
            }
            return status;
        }

        /// <summary>
        /// Deletes the customer in the database
        /// </summary>
        /// <param name="customer"></param>
        /// <returns>bool status</returns>
        public bool Delete(Customer customer)
        {
            int changes;
            using (SqlConnection connection = new SqlConnection(_connectionString))
            {
                connection.Open();
                using (SqlCommand cmd = connection.CreateCommand())
                {
                    cmd.CommandText = "DELETE FROM Customer where phone = @phone";
                    cmd.Parameters.AddWithValue("phone", customer._phone);
                    changes = cmd.ExecuteNonQuery();
                }
            }

            bool status = changes > 0;

            if (status == false)
            {
                throw new FaultException<CustomerNotDeletedException>(new CustomerNotDeletedException(customer._phone));
            }
            return status;
        }

        /// <summary>
        /// Gets all customers from the database 
        /// </summary>
        /// <returns>IEnumerable<Customer> listOfCustomers</returns>
        public IEnumerable<Customer> GetAll()
        {
            List<Customer> customers = new List<Customer>();
            using (SqlConnection connection = new SqlConnection(_connectionString))
            {
                connection.Open();
                using (SqlCommand cmd = connection.CreateCommand())
                {
                    cmd.CommandText = "select * from Customer";
                    SqlDataReader reader = cmd.ExecuteReader();

                    while (reader.Read())
                    {
                        Customer customer = new Customer
                        {
                            _id = reader.GetInt32(reader.GetOrdinal("id")),
                            _fName = reader.GetString(reader.GetOrdinal("fName")),
                            _lName = reader.GetString(reader.GetOrdinal("lName")),
                            _email = reader.GetString(reader.GetOrdinal("email")),
                            _address = reader.GetString(reader.GetOrdinal("address")),
                            _phone = reader.GetString(reader.GetOrdinal("phone")),
                            _zipCity = _dbZipCity.Read(reader.GetInt32(reader.GetOrdinal("zipCityId"))),
                        };
                        if (customer is Customer)
                        {
                            customers.Add(customer);
                        }
                        else
                        {
                            throw new FaultException<CustomerNotExistException>(new CustomerNotExistException());
                        }
                    }
                }
            }

            return customers;
        }
    }
}
