using System.Collections.Generic;
using System.Configuration;
using System.Data.SqlClient;
using System.ServiceModel;
using Domain;
using Exceptions;

namespace DataAccess
{
    public class DbZipCity : IDbZipCity
    {
        private readonly string _connectionString = ConfigurationManager.ConnectionStrings["DBString"].ConnectionString;

        /// <summary>
        /// Returns an zipCity based on its id in database
        /// </summary>
        /// <param name="id"></param>
        /// <returns>ZipCity zipCity</returns>
        public ZipCity Read(int id)
        {
            ZipCity zipCity = null;
            using (SqlConnection connection = new SqlConnection(_connectionString))
            {
                connection.Open();

                using (SqlCommand cmd = connection.CreateCommand())
                {
                    cmd.CommandText = "SELECT * FROM ZipCity WHERE id = @id";
                    cmd.Parameters.AddWithValue("id", id);
                    SqlDataReader reader = cmd.ExecuteReader();

                    while (reader.Read())
                    {
                        zipCity = new ZipCity
                        {
                            _id = id,
                            _city = reader.GetString(reader.GetOrdinal("city")),
                            _zipCode = reader.GetInt32(reader.GetOrdinal("zipCode"))
                        };
                    }

                    if (!(zipCity is ZipCity))
                    {
                        throw new FaultException<ZipCodeNotInDbException>(new ZipCodeNotInDbException(""));
                    }
                }
            }
            return zipCity;
        }
        /// <summary>
        /// Returns an zipCity based on its zipcode
        /// </summary>
        /// <param name="zipCode"></param>
        /// <returns>Zipcity zipCity</returns>
        public ZipCity ReadByZipCode(string zipCode)
        {
            ZipCity zipCity = null;
            using (SqlConnection connection = new SqlConnection(_connectionString))
            {
                connection.Open();
                
                using (SqlCommand cmd = connection.CreateCommand())
                {
                    cmd.CommandText = "SELECT * FROM ZipCity WHERE zipCode = @zipCode";
                    cmd.Parameters.AddWithValue("zipCode", zipCode);
                    SqlDataReader reader = cmd.ExecuteReader();

                    while (reader.Read())
                    {
                        zipCity = new ZipCity
                        {
                            _id = reader.GetInt32(reader.GetOrdinal("id")),
                            _city = reader.GetString(reader.GetOrdinal("city")),
                            _zipCode = int.Parse(zipCode)
                        };
                    }

                    if(!(zipCity is ZipCity))
                    {
                        throw new FaultException<ZipCodeNotInDbException>(new ZipCodeNotInDbException(zipCode));
                    }
                }
            }
            return zipCity;
        }

        /// <summary>
        /// Returns all zipcities in database
        /// </summary>
        /// <returns>IEnumerable<ZipCity> zipCity</returns>
        public IEnumerable<ZipCity> GetAll()
        {
            List<ZipCity> zipCities = new List<ZipCity>();
            using (SqlConnection connection = new SqlConnection(_connectionString))
            {
                connection.Open();
                using (SqlCommand cmd = connection.CreateCommand())
                {
                    cmd.CommandText = "select id, zipcode, city from ZipCity";
                    SqlDataReader reader = cmd.ExecuteReader();
                    while (reader.Read())
                    {
                        ZipCity zipCity = new ZipCity {
                            _id = reader.GetInt32(reader.GetOrdinal("id")),
                            _zipCode = reader.GetInt32(reader.GetOrdinal("zipCode")),
                            _city = reader.GetString(reader.GetOrdinal("city"))
                        };

                        if (zipCity is ZipCity)
                        {
                            zipCities.Add(zipCity);
                        }
                    }

                    if (zipCities.Count == 0)
                    {
                        throw new FaultException<ZipCodeNotInDbException>(new ZipCodeNotInDbException(""));
                    }
                }
            }
            
            return zipCities;
        }
    }
}
