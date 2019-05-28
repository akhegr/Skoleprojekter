using System;
using System.Configuration;
using System.Data.SqlClient;
using System.ServiceModel;
using Exceptions;

namespace DataAccess
{
    public class DbConnection
    {
        private readonly string _connectionString = ConfigurationManager.ConnectionStrings["DBString"].ConnectionString;

        public void ConnectionTest()
        {
            try
            {
                using (SqlConnection connection = new SqlConnection(_connectionString))
                {
                    connection.Open();

                    using (SqlCommand cmd = connection.CreateCommand())
                    {
                        cmd.CommandText = "SELECT 1";
                        int idStatus = Convert.ToInt32(cmd.ExecuteScalar());
                        if (idStatus != 1)
                        {
                            throw new FaultException<DbConnectionError>(new DbConnectionError());
                        }
                    }
                }
            }
            catch (SqlException)
            {
                throw new FaultException<DbConnectionError>(new DbConnectionError());
            }
        }
    }
}
