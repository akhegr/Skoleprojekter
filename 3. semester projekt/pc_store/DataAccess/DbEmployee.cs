using System;
using System.Configuration;
using System.Data.SqlClient;
using System.ServiceModel;
using Domain;
using Exceptions;

namespace DataAccess
{
    public class DbEmployee : IDbEmployee
    {
        private readonly string _connectionString = ConfigurationManager.ConnectionStrings["DBString"].ConnectionString;
        
        public int Create(Employee employee)
        {
            int id;
            try
            {
                using (SqlConnection connection = new SqlConnection(_connectionString))
                {
                    connection.Open();
                    
                    using (SqlCommand cmd = connection.CreateCommand())
                    {
                        cmd.CommandText = "INSERT INTO Employee (fName, lName, username, password, salt) " +
                                            "VALUES (@fName, @lName, @username, @password, @salt); " +
                                            "SELECT SCOPE_IDENTITY()";

                        cmd.Parameters.AddWithValue("fName", employee._fName);
                        cmd.Parameters.AddWithValue("lName", employee._lName);
                        cmd.Parameters.AddWithValue("username", employee._username);
                        cmd.Parameters.AddWithValue("password", employee._password);
                        cmd.Parameters.AddWithValue("salt", employee._salt);
                        id = Convert.ToInt32(cmd.ExecuteScalar());
                    }

                    if (id == 0)
                    {
                        throw new FaultException<EmployeeNotCreatedException>(new EmployeeNotCreatedException());
                    }
                }
            }
            catch (SqlException)
            {
                throw new FaultException<DbConnectionError>(new DbConnectionError());
            }

            return id;
        }
        
        public Employee Read(int id)
        {
            Employee employee = null;
            try
            {
                using (SqlConnection connection = new SqlConnection(_connectionString))
                {
                    connection.Open();
                    using (SqlCommand cmd = connection.CreateCommand())
                    {
                        cmd.CommandText = "SELECT * FROM EMPLOYEE WHERE id = @id";
                        cmd.Parameters.AddWithValue("id", id);
                        SqlDataReader reader = cmd.ExecuteReader();

                        while (reader.Read())
                        {
                            employee = new Employee
                            {
                                _id = reader.GetInt32(reader.GetOrdinal("id")),
                                _fName = reader.GetString(reader.GetOrdinal("fName")),
                                _lName = reader.GetString(reader.GetOrdinal("lName")),
                                _username = reader.GetString(reader.GetOrdinal("username")),
                                _password = reader.GetString(reader.GetOrdinal("password")),
                                _salt = reader.GetString(reader.GetOrdinal("salt"))
                            };
                        }
                    }
                }
            }
            catch (SqlException)
            {
                throw new FaultException<DbConnectionError>(new DbConnectionError());
            }

            return employee;
        }

        public Employee ReadByUsername(string username)
        {
            Employee employee = null;
            try
            {
                using (SqlConnection connection = new SqlConnection(_connectionString))
                {
                    connection.Open();
                    using (SqlCommand cmd = connection.CreateCommand())
                    {
                        cmd.CommandText = "SELECT * FROM EMPLOYEE WHERE username = @username";
                        cmd.Parameters.AddWithValue("username", username);
                        SqlDataReader reader = cmd.ExecuteReader();

                        while (reader.Read())
                        {
                            employee = new Employee
                            {
                                _id = reader.GetInt32(reader.GetOrdinal("id")),
                                _fName = reader.GetString(reader.GetOrdinal("fName")),
                                _lName = reader.GetString(reader.GetOrdinal("lName")),
                                _username = reader.GetString(reader.GetOrdinal("username")),
                                _password = reader.GetString(reader.GetOrdinal("password")),
                                _salt = reader.GetString(reader.GetOrdinal("salt"))
                            };
                        }

                        if (!(employee is Employee))
                        {
                            throw new FaultException<EmployeeNotExistException>(new EmployeeNotExistException());
                        }
                    }
                }
            }
            catch (SqlException)
            {
                throw new FaultException<DbConnectionError>(new DbConnectionError());
            }

            return employee;
        }

        public bool Update(Employee employee, string username, string salt)
        {
            bool status = false;
            int affected = 0;
            try {
                using (SqlConnection connection = new SqlConnection(_connectionString))
                {
                    connection.Open();
                    using (SqlCommand cmd = connection.CreateCommand())
                    {
                        cmd.CommandText = "UPDATE EMPLOYEE " +
                            "set fName = @fName, lName = @lName, username = @username, password = @password, salt = @salt " +
                            "where username = @oldUsername";
                        cmd.Parameters.AddWithValue("oldUsername", username);
                        cmd.Parameters.AddWithValue("fname", employee._fName);
                        cmd.Parameters.AddWithValue("lName", employee._lName);
                        cmd.Parameters.AddWithValue("username", employee._username);
                        cmd.Parameters.AddWithValue("password", employee._password);
                        cmd.Parameters.AddWithValue("salt", employee._salt);
                        affected = cmd.ExecuteNonQuery();
                    }
                }
            }
            catch (SqlException)
            {
                throw new FaultException<DbConnectionError>(new DbConnectionError());
            }

            status = affected > 0;
            
            return status;
        }
        
        public bool Delete(Employee employee)
        {
            int changes;
            try
            {
                using (SqlConnection connection = new SqlConnection(_connectionString))
                {
                    connection.Open();
                    using (SqlCommand cmd = connection.CreateCommand())
                    {
                        cmd.CommandText = "DELETE FROM EMPLOYEE where username = @username";
                        cmd.Parameters.AddWithValue("username", employee._username);
                        changes = cmd.ExecuteNonQuery();
                    }
                }
            }
            catch (SqlException)
            {
                throw new FaultException<DbConnectionError>(new DbConnectionError());
            }

            bool status = changes > 0;
            
            return status;
        }
    }
}
