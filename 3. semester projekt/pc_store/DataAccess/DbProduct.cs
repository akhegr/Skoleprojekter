using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data.SqlClient;
using System.ServiceModel;
using Domain;
using Exceptions;

namespace DataAccess
{
    public class DbProduct : IDbProduct
    {
        private readonly string _connectionString = ConfigurationManager.ConnectionStrings["DBString"].ConnectionString;

        /// <summary>
        /// Creates an instance of a product in the database
        /// </summary>
        /// <param name="product"></param>
        /// <returns>int productId</returns>
        public int Create(Product product)
        {
            int id;
            using (SqlConnection connection = new SqlConnection(_connectionString))
            {
                connection.Open();

                using (SqlCommand cmd = connection.CreateCommand())
                {
                    cmd.CommandText = "INSERT INTO Product(name, price, description, supplierId, categoryId) VALUES " +
                                        "(@name, @price, @description, @supplierId, @categoryId); " +
                                        "SELECT SCOPE_IDENTITY()";
                    cmd.Parameters.AddWithValue("name", product._name);
                    cmd.Parameters.AddWithValue("price", product._price);
                    cmd.Parameters.AddWithValue("description", product._description);
                    cmd.Parameters.AddWithValue("supplierId", product._supplierId);
                    cmd.Parameters.AddWithValue("categoryId", product._categoryId);
                    id = Convert.ToInt32(cmd.ExecuteScalar());
                }
            }

            return id;
        }

        /// <summary>
        /// Returns all produts with a specific category
        /// </summary>
        /// <param name="categoryId"></param>
        /// <returns>IEnumerable<Product> products</returns>
        public IEnumerable<Product> FindByCategoryId(int categoryId)
        {
            List<Product> products = new List<Product>();
            using (SqlConnection connection = new SqlConnection(_connectionString))
            {
                connection.Open();
                using (SqlCommand cmd = connection.CreateCommand())
                {
                    cmd.CommandText = "select * from Product where categoryId = @categoryId";
                    cmd.Parameters.AddWithValue("categoryId", categoryId);

                    SqlDataReader reader = cmd.ExecuteReader();

                    while (reader.Read())
                    {
                        Product product = new Product
                        {
                            _id = reader.GetInt32(reader.GetOrdinal("id")),
                            _name = reader.GetString(reader.GetOrdinal("name")),
                            _price = reader.GetDecimal(reader.GetOrdinal("price")),
                            _description = reader.GetString(reader.GetOrdinal("description")),
                            _supplierId = reader.GetInt32(reader.GetOrdinal("supplierId")),
                            _categoryId = reader.GetInt32(reader.GetOrdinal("categoryId"))
                        };
                        if (product is Product)
                        {
                            products.Add(product);
                        }
                    }
                }
            }
            return products;
        }

        /// <summary>
        /// Returns a product based on its id
        /// </summary>
        /// <param name="id"></param>
        /// <returns>Product product</returns>
        public Product FindById(int id)
        {
            Product product = null;
            using (SqlConnection connection = new SqlConnection(_connectionString))
            {
                connection.Open();
                using (SqlCommand cmd = connection.CreateCommand())
                {
                    cmd.CommandText = "SELECT * FROM Product WHERE id = @id";
                    cmd.Parameters.AddWithValue("id", id);
                    SqlDataReader reader = cmd.ExecuteReader();

                    while (reader.Read())
                    {
                        product = new Product
                        {
                            _id = reader.GetInt32(reader.GetOrdinal("id")),
                            _name = reader.GetString(reader.GetOrdinal("name")),
                            _price = reader.GetDecimal(reader.GetOrdinal("price")),
                            _description = reader.GetString(reader.GetOrdinal("description")),
                            _supplierId = reader.GetInt32(reader.GetOrdinal("supplierId")),
                            _categoryId = reader.GetInt32(reader.GetOrdinal("categoryId"))
                        };
                    }
                    if (!(product is Product))
                    {
                        throw new FaultException<ProductNotExistException>(new ProductNotExistException(id.ToString()));
                    }
                }
            }
            return product;
        }
        /// <summary>
        /// Returns a product based on its name 
        /// </summary>
        /// <param name="name"></param>
        /// <returns>Product product</returns>
        public Product Read(string name)
        {
            Product product = null;
            using (SqlConnection connection = new SqlConnection(_connectionString))
            {
                connection.Open();
                using (SqlCommand cmd = connection.CreateCommand())
                {
                    cmd.CommandText = "SELECT * FROM Product WHERE name = @name";
                    cmd.Parameters.AddWithValue("name", name);
                    SqlDataReader reader = cmd.ExecuteReader();

                    while (reader.Read())
                    {
                        product = new Product
                        {
                            _id = reader.GetInt32(reader.GetOrdinal("id")),
                            _name = reader.GetString(reader.GetOrdinal("name")),
                            _price = reader.GetDecimal(reader.GetOrdinal("price")),
                            _description = reader.GetString(reader.GetOrdinal("description")),
                            _supplierId = reader.GetInt32(reader.GetOrdinal("supplierId")),
                            _categoryId = reader.GetInt32(reader.GetOrdinal("categoryId"))
                        };
                    }
                    if (!(product is Product))
                    {
                        throw new FaultException<ProductNotExistException>(new ProductNotExistException(name));
                    }
                }
            }
            return product;
        }
        /// <summary>
        /// Updates a product in the database based on input parameteres
        /// </summary>
        /// <param name="product"></param>
        /// <param name="oldName"></param>
        public void Update(Product product, string oldName)
        {
            using (SqlConnection connection = new SqlConnection(_connectionString))
            {
                connection.Open();
                using (SqlCommand cmd = connection.CreateCommand())
                {
                    cmd.CommandText = "UPDATE Product " +
                        "set name = @name, price = @price, description = @description, supplierId = @supplierId, categoryId = @categoryId " +
                        "where name = @oldName";
                    
                    cmd.Parameters.AddWithValue("name", product._name);
                    cmd.Parameters.AddWithValue("price", product._price);
                    cmd.Parameters.AddWithValue("description", product._description);
                    cmd.Parameters.AddWithValue("supplierId", product._supplierId);
                    cmd.Parameters.AddWithValue("categoryId", product._categoryId);
                    cmd.Parameters.AddWithValue("oldName", oldName);
                    cmd.ExecuteNonQuery();
                }
            }
        }


        /// <summary>
        /// Deletes a products in the database
        /// </summary>
        /// <param name="product"></param>
        public void Delete(Product product)
        {
            using (SqlConnection connection = new SqlConnection(_connectionString))
            {
                connection.Open();
                using (SqlCommand cmd = connection.CreateCommand())
                {
                    cmd.CommandText = "DELETE FROM Product where name = @name";
                    cmd.Parameters.AddWithValue("phone", product._name);
                    cmd.ExecuteNonQuery();
                }
            }
        }

        /// <summary>
        /// Returns all products in the database
        /// </summary>
        /// <returns>IEnumerable<Product> products</returns>
        public IEnumerable<Product> GetAll()
        {
            List<Product> products = new List<Product>();
            using (SqlConnection connection = new SqlConnection(_connectionString))
            {
                connection.Open();
                using (SqlCommand cmd = connection.CreateCommand())
                {
                    cmd.CommandText = "select * from Product";
                    SqlDataReader reader = cmd.ExecuteReader();

                    while (reader.Read())
                    {
                        Product product = new Product
                        {
                            _id = reader.GetInt32(reader.GetOrdinal("id")),
                            _name = reader.GetString(reader.GetOrdinal("name")),
                            _price = reader.GetDecimal(reader.GetOrdinal("price")),
                            _description = reader.GetString(reader.GetOrdinal("description")),
                            _supplierId = reader.GetInt32(reader.GetOrdinal("supplierId")),
                            _categoryId = reader.GetInt32(reader.GetOrdinal("categoryId"))
                        };
                        if(product is Product)
                        {
                            products.Add(product);
                        }
                    }
                }
            }
            return products;
        }
        /// <summary>
        /// Deletes a product based on its id
        /// </summary>
        /// <param name="id"></param>
        /// <returns>bool delete</returns>
        public bool Delete(int id)
        {
            bool delete = false;
            int amountOfEffected = 0;

            using (SqlConnection connection = new SqlConnection(_connectionString))
            {
                connection.Open();
                using (SqlCommand cmd = connection.CreateCommand())
                {
                    cmd.CommandText = "delete from Product where id = @id "
                        + "DBCC CHECKIDENT ('[Product]', RESEED, 0); GO";
                    cmd.Parameters.AddWithValue("id", id);
                    amountOfEffected = cmd.ExecuteNonQuery();
                }
            }
            if(amountOfEffected > 0)
            {
                delete = true;
            }
            return delete;
        }
    }
}
