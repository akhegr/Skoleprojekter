using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data.SqlClient;
using Domain;

namespace DataAccess
{
    public class DbUniqueProduct : IDbUniqueProduct
    {
        private readonly string _connectionString = ConfigurationManager.ConnectionStrings["DBString"].ConnectionString;
        private DbProduct _dbProduct = new DbProduct();

        /// <summary>
        /// Creates an instance of a uniqueProduct in the database
        /// </summary>
        /// <param name="uniqueProduct"></param>
        /// <returns>int id</returns>
        public int Create(UniqueProduct uniqueProduct)
        {
            int id;
            using (SqlConnection connection = new SqlConnection(_connectionString))
            {
                connection.Open();
                
                using (SqlCommand cmd = connection.CreateCommand())
                {

                    cmd.CommandText = "INSERT INTO UniqueProduct (serialNo, warranty, productId) " +
                        "VALUES (@serialNo, @warranty, @productId); " +
                        "SELECT SCOPE_IDENTITY()";
                    cmd.Parameters.AddWithValue("serialNo", uniqueProduct._serialNo);
                    cmd.Parameters.AddWithValue("warranty", uniqueProduct._warranty);
                    cmd.Parameters.AddWithValue("productId", uniqueProduct._product._id);

                    id = Convert.ToInt32(cmd.ExecuteScalar());
                }
            }
            return id;
        }
        /// <summary>
        /// Returns a uniqueProduct based on its serialno
        /// </summary>
        /// <param name="serialNo"></param>
        /// <returns>UiqueProduct uniqueProduct</returns>
        public UniqueProduct Read(string serialNo)
        {
            UniqueProduct uniqueProduct = null;
            using (SqlConnection connection = new SqlConnection(_connectionString))
            {
                connection.Open();
                using (SqlCommand cmd = connection.CreateCommand())
                {
                    cmd.CommandText = "SELECT * FROM UniqueProduct WHERE serialNo = @serialNo";
                    cmd.Parameters.AddWithValue("serialNo", serialNo);
                    SqlDataReader reader = cmd.ExecuteReader();

                    while (reader.Read())
                    {
                        uniqueProduct = new UniqueProduct
                        {
                            _id = reader.GetInt32(reader.GetOrdinal("id")),
                            _serialNo = reader.GetString(reader.GetOrdinal("serialNo")),
                            _warranty = reader.GetString(reader.GetOrdinal("warranty")),
                            _product = _dbProduct.FindById(reader.GetInt32(reader.GetOrdinal("productId")))
                        };
                    }
                }
            }
            return uniqueProduct;
        }
        /// <summary>
        /// Returns a specific amount of uniqueProduct of a specific product
        /// </summary>
        /// <param name="product"></param>
        /// <param name="amount"></param>
        /// <returns>IEnumerable<UniqueProduct> uniqueProduct</returns>
        public IEnumerable<UniqueProduct> FindUniqueProductsOnStock(Product product, int amount)
        {
            List<UniqueProduct> uniqueProducts = new List<UniqueProduct>();
            int productId = product._id;

            using (SqlConnection connection = new SqlConnection(_connectionString))
            {
                connection.Open();
                using (SqlCommand cmd = connection.CreateCommand())
                {
                    cmd.CommandText = "select TOP " + amount + " * from UNIQUEPRODUCT up, PRODUCT p where up.productId = p.id " +
                                      "and p.id = @productId and ((SELECT COUNT(*) FROM OrderLineList WHERE uniqueProductId = up.id) = 0)";
                    cmd.Parameters.AddWithValue("productId", productId);
                    
                    SqlDataReader reader = cmd.ExecuteReader();
                    
                    while (reader.Read())
                    {
                        UniqueProduct uniqueProduct = new UniqueProduct
                        {
                            _id = reader.GetInt32(reader.GetOrdinal("id")),
                            _serialNo = reader.GetString(reader.GetOrdinal("serialNo")),
                            _warranty = reader.GetString(reader.GetOrdinal("warranty")),
                            _product = _dbProduct.FindById(reader.GetInt32(reader.GetOrdinal("productId")))
                        };

                        if(uniqueProduct is UniqueProduct)
                        {
                            uniqueProducts.Add(uniqueProduct);
                        }
                    }
                }
            }
            return uniqueProducts;
        }
        /// <summary>
        /// Updates a uniqueProduct in the database based on inputparameters
        /// </summary>
        /// <param name="uniqueProduct"></param>
        public void Update(UniqueProduct uniqueProduct)
        {
            using (SqlConnection connection = new SqlConnection(_connectionString))
            {
                connection.Open();
                using (SqlCommand cmd = connection.CreateCommand())
                {
                    cmd.CommandText = "UPDATE UniqueProduct " +
                        "set warranty = @warranty, productId = @productId " +
                        "where serialNo = @serialNo";
                    cmd.Parameters.AddWithValue("serialNo", uniqueProduct._serialNo);
                    cmd.Parameters.AddWithValue("warranty", uniqueProduct._warranty);
                    cmd.Parameters.AddWithValue("productId", uniqueProduct._product._id);
                    cmd.ExecuteNonQuery();
                }
            }
        }
        /// <summary>
        /// Returns all uniqueProducts in database
        /// </summary>
        /// <returns>IEnumerable<UniqueProduct> uniqueProduct</returns>
        public IEnumerable<UniqueProduct> GetAll()
        {
            List<UniqueProduct> uniqueProducts = new List<UniqueProduct>();
            using (SqlConnection connection = new SqlConnection(_connectionString))
            {
                connection.Open();
                using (SqlCommand cmd = connection.CreateCommand())
                {
                    cmd.CommandText = "select * from UniqueProduct";
                    SqlDataReader reader = cmd.ExecuteReader();

                    while (reader.Read())
                    {
                        UniqueProduct uniqueProduct = new UniqueProduct
                        {
                            _id = reader.GetInt32(reader.GetOrdinal("id")),
                            _serialNo = reader.GetString(reader.GetOrdinal("serialNo")),
                            _warranty = reader.GetString(reader.GetOrdinal("warranty")),
                            _product = _dbProduct.FindById(reader.GetInt32(reader.GetOrdinal("productId")))
                        };

                        if (uniqueProduct is UniqueProduct)
                        {
                            uniqueProducts.Add(uniqueProduct);
                        }
                    }
                }
            }
            return uniqueProducts;
        }
        /// <summary>
        /// Deletes an uniqueProduct in database based un input parameters
        /// </summary>
        /// <param name="uniqueProduct"></param>
        public void Delete(UniqueProduct uniqueProduct)
        {
            using (SqlConnection connection = new SqlConnection(_connectionString))
            {
                connection.Open();
                using (SqlCommand cmd = connection.CreateCommand())
                {
                    cmd.CommandText = "DELETE FROM UniqueProduct where serialNo = @serialNo";
                    cmd.Parameters.AddWithValue("serialNo", uniqueProduct._serialNo);
                    cmd.ExecuteNonQuery();
                }
            }
        }
    }
}
