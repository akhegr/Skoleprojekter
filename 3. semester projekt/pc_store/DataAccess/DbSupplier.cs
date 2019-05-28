using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data.SqlClient;
using System.ServiceModel;
using Domain;
using Exceptions;

namespace DataAccess
{
    public class DbSupplier : IDbSupplier
    {
        private readonly string _connectionString = ConfigurationManager.ConnectionStrings["DBString"].ConnectionString;

        /// <summary>
        /// Creates an instance of a supplier in the database
        /// </summary>
        /// <param name="supplier"></param>
        /// <returns>int id</returns>
        public int Create(Supplier supplier)
        {
            int id;
            using (SqlConnection connection = new SqlConnection(_connectionString))
            {
                connection.Open();
                
                using (SqlCommand cmd = connection.CreateCommand())
                {
                    cmd.CommandText = "INSERT INTO Supplier VALUES (@name, @cvrNo, @phone, @email, @address); " +
                                        "SELECT SCOPE_IDENTITY()";
                    cmd.Parameters.AddWithValue("name", supplier._name);
                    cmd.Parameters.AddWithValue("cvrNo", supplier._cvrNo);
                    cmd.Parameters.AddWithValue("phone", supplier._phone);
                    cmd.Parameters.AddWithValue("email", supplier._email);
                    cmd.Parameters.AddWithValue("address", supplier._address);
                    id = Convert.ToInt32(cmd.ExecuteScalar());
                }
            }
            return id;
        }
        /// <summary>
        /// Returns a supplier based on its cvrno
        /// </summary>
        /// <param name="cvrNo"></param>
        /// <returns>Supplier supplier</returns>
        public Supplier Read(string cvrNo)
        {
            Supplier supplier = null;
            using (SqlConnection connection = new SqlConnection(_connectionString))
            {
                connection.Open();
                using (SqlCommand cmd = connection.CreateCommand())
                {
                    cmd.CommandText = "SELECT * FROM Supplier WHERE cvrNo = @cvrNo";
                    cmd.Parameters.AddWithValue("cvrNo", cvrNo);
                    SqlDataReader reader = cmd.ExecuteReader();

                    while (reader.Read())
                    {
                        supplier = new Supplier
                        {
                            _id = reader.GetInt32(reader.GetOrdinal("id")),
                            _name = reader.GetString(reader.GetOrdinal("name")),
                            _cvrNo = reader.GetString(reader.GetOrdinal("cvrNo")),
                            _phone = reader.GetString(reader.GetOrdinal("phone")),
                            _email = reader.GetString(reader.GetOrdinal("email")),
                            _address = reader.GetString(reader.GetOrdinal("address"))
                        };
                    }
                    if (!(supplier is Supplier))
                    {
                        throw new FaultException<SupplierNotExistException>(new SupplierNotExistException(cvrNo));
                    }
                }
            }
            return supplier;
        }

        /// <summary>
        /// Updates a supplier based on input parameters
        /// </summary>
        /// <param name="supplier"></param>
        /// <param name="oldPhone"></param>
        public void Update(Supplier supplier, String oldPhone)
        {
            using (SqlConnection connection = new SqlConnection(_connectionString))
            {
                connection.Open();
                using (SqlCommand cmd = connection.CreateCommand())
                {
                    cmd.CommandText = "UPDATE Supplier " +
                        "set name = @name, cvrNo = @cvrNo, phone = @newPhone, email = @email, address = @address " +
                        "where phone = @oldPhone";
                    
                    cmd.Parameters.AddWithValue("name", supplier._name);
                    cmd.Parameters.AddWithValue("cvrNo", supplier._cvrNo);
                    cmd.Parameters.AddWithValue("phone", supplier._phone);
                    cmd.Parameters.AddWithValue("email", supplier._email);
                    cmd.Parameters.AddWithValue("address", supplier._address);
                    cmd.Parameters.AddWithValue("oldPhone", oldPhone);
                    cmd.ExecuteNonQuery();
                }
            }
        }

        /// <summary>
        /// Deletes a supplier based on input parameters
        /// </summary>
        /// <param name="supplier"></param>
        public void Delete(Supplier supplier)
        {
            using (SqlConnection connection = new SqlConnection(_connectionString))
            {
                connection.Open();
                using (SqlCommand cmd = connection.CreateCommand())
                {
                    cmd.CommandText = "DELETE FROM Supplier where phone = @phone";
                    cmd.Parameters.AddWithValue("phone", supplier._phone);
                    cmd.ExecuteNonQuery();
                }
            }
        }
        /// <summary>
        /// Returns all suppliers
        /// </summary>
        /// <returns>IEnumerable<Supplier> supplier</returns>
        public IEnumerable<Supplier> GetAll()
        {
            List<Supplier> suppliers = new List<Supplier>();
            using (SqlConnection connection = new SqlConnection(_connectionString))
            {
                connection.Open();
                using (SqlCommand cmd = connection.CreateCommand())
                {
                    cmd.CommandText = "select id, name, cvrNo, phone, email, address from Supplier";
                    SqlDataReader reader = cmd.ExecuteReader();

                    while (reader.Read())
                    {
                        Supplier supplier = new Supplier
                        {
                            _id = reader.GetInt32(reader.GetOrdinal("id")),
                            _name = reader.GetString(reader.GetOrdinal("name")),
                            _cvrNo = reader.GetString(reader.GetOrdinal("cvrNo")),
                            _phone = reader.GetString(reader.GetOrdinal("phone")),
                            _email = reader.GetString(reader.GetOrdinal("email")),
                            _address = reader.GetString(reader.GetOrdinal("address"))
                        };
                        if (supplier is Supplier)
                        {
                            suppliers.Add(supplier);
                        }
                    }
                }
            }

            return suppliers;
        }
    }
}
