using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data.SqlClient;
using Domain;

namespace DataAccess
{
    public class DbCategory : IDbCategory
    {
        private readonly string _connectionString = ConfigurationManager.ConnectionStrings["DBString"].ConnectionString;
        private DbProduct _dbProduct = new DbProduct();

        /// <summary>
        /// This method creates an instance of a category in the database
        /// </summary>
        /// <param name="category"></param>
        /// <returns>int id</returns>
        public int Create(Category category)
        {
            int id;
            using (SqlConnection connection = new SqlConnection(_connectionString))
            {
                connection.Open();
                
                using (SqlCommand cmd = connection.CreateCommand())
                {
                    
                    cmd.CommandText = "INSERT INTO Category VALUES (@type); " +
                                      "SELECT SCOPE_IDENTITY()";
                    cmd.Parameters.AddWithValue("type", category._type);
                    id = Convert.ToInt32(cmd.ExecuteScalar());
                }
            }
            return id;
        }
        /// <summary>
        /// Returns a category from the database depending on the input parameter
        /// </summary>
        /// <param name="type"></param>
        /// <returns>Category category</returns>
        public Category Read(string type)
        {
            Category category = null;
            using (SqlConnection connection = new SqlConnection(_connectionString))
            {
                connection.Open();
                using (SqlCommand cmd = connection.CreateCommand())
                {
                    cmd.CommandText = "SELECT * FROM Category WHERE type = @type";
                    cmd.Parameters.AddWithValue("type", type);
                    SqlDataReader reader = cmd.ExecuteReader();

                    while (reader.Read())
                    {
                        int catId = reader.GetInt32(reader.GetOrdinal("id"));
                        IEnumerable<Product> products = _dbProduct.FindByCategoryId(catId);
                        category = new Category
                        {
                            _id = catId,
                            _type = reader.GetString(reader.GetOrdinal("type")),
                            _products = _dbProduct.FindByCategoryId(catId)
                        };
                    }
                }
            }
            return category;
        }


        /// <summary>
        /// Updates an existing category in the database based on the input parameters
        /// </summary>
        /// <param name="category"></param>
        /// <param name="oldType"></param>
        public void Update(Category category, String oldType)
        {
            using (SqlConnection connection = new SqlConnection(_connectionString))
            {
                connection.Open();
                using (SqlCommand cmd = connection.CreateCommand())
                {
                    cmd.CommandText = "UPDATE Category set type = @type where type = @oldType";
                    cmd.Parameters.AddWithValue("oldType", oldType);
                    cmd.Parameters.AddWithValue("type", category._type);
                    cmd.ExecuteNonQuery();
                }
            }
        }
        
        /// <summary>
        /// Deletes an existing category in the database
        /// </summary>
        /// <param name="category"></param>
        public void Delete(Category category)
        {
            using (SqlConnection connection = new SqlConnection(_connectionString))
            {
                connection.Open();
                using (SqlCommand cmd = connection.CreateCommand())
                {
                    cmd.CommandText = "DELETE FROM Category where type = @type";
                    cmd.Parameters.AddWithValue("type", category._type);
                    cmd.ExecuteNonQuery();
                }
            }
        }

        /// <summary>
        /// Returns all existing category in the database
        /// </summary>
        /// <returns>IEnumerable<Category></returns>
        public IEnumerable<Category> GetAll()
        {
            List<Category> categories = new List<Category>();
            using (SqlConnection connection = new SqlConnection(_connectionString))
            {
                connection.Open();
                using (SqlCommand cmd = connection.CreateCommand())
                {
                    cmd.CommandText = "select id, type from Category";
                    SqlDataReader reader = cmd.ExecuteReader();

                    while (reader.Read())
                    {
                        int catId = reader.GetInt32(reader.GetOrdinal("id"));
                        Category category = new Category
                        {
                            _id = catId,
                            _type = reader.GetString(reader.GetOrdinal("type")),
                            _products = _dbProduct.FindByCategoryId(catId)
                        };
                        categories.Add(category);
                    }
                }
            }

            return categories;
        }
    }
}
