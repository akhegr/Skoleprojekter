using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data.SqlClient;
using Domain;

namespace DataAccess
{
    public class DbOrder : IDbOrder
    {
        private readonly string _connectionString = ConfigurationManager.ConnectionStrings["DBString"].ConnectionString;
        private readonly DbCustomer _dbCustomer;

        public DbOrder()
        {
            _dbCustomer = new DbCustomer();
        }

        /// <summary>
        /// Creates an order in the database based on input parameteres
        /// </summary>
        /// <param name="order"></param>
        /// <returns>int id</returns>
        public int Create(Order order)
        {
            int id;
            using (SqlConnection connection = new SqlConnection(_connectionString))
            {
                connection.Open();

                using (SqlCommand cmd = connection.CreateCommand())
                {
                    cmd.CommandText = "INSERT INTO MyOrder(delivery, processed) VALUES " +
                                        "(@delivery, @processed); " +
                                        "SELECT SCOPE_IDENTITY()";
                    cmd.Parameters.AddWithValue("delivery", order._delivery);
                    cmd.Parameters.AddWithValue("processed", order._processed);
                    if (order._customer != null)
                    {
                        cmd.Parameters.AddWithValue("customerId", order._customer._id);
                    }
                    else
                    {
                        cmd.Parameters.AddWithValue("customerId", DBNull.Value);
                    }
                    id = Convert.ToInt32(cmd.ExecuteScalar());
                }
            }
            return id;
        }

        /// <summary>
        /// Returns an Order from database based on its id
        /// </summary>
        /// <param name="id"></param>
        /// <returns>Order order</returns>
        public Order Read(int id)
        {
            Order order = null;
            using (SqlConnection connection = new SqlConnection(_connectionString))
            {
                connection.Open();
                using (SqlCommand cmd = connection.CreateCommand())
                {
                    cmd.CommandText = "SELECT * FROM MyOrder WHERE id = @id";
                    cmd.Parameters.AddWithValue("id", id);
                    SqlDataReader reader = cmd.ExecuteReader();

                    while (reader.Read())
                    {
                        Customer customer = null;
                        if (!reader.IsDBNull(reader.GetOrdinal("customerId")))
                        {
                            int customerId = reader.GetInt32(reader.GetOrdinal("customerId"));
                            customer = _dbCustomer.Read(customerId);
                        }

                        order = new Order
                        {
                            _id = id,
                            _delivery = reader.GetBoolean(reader.GetOrdinal("delivery")),
                            _processed = reader.GetBoolean(reader.GetOrdinal("processed")),
                            _lastChanged = (byte[])reader.GetValue(reader.GetOrdinal("lastChanged")),
                            _customer = customer
                        };
                    }
                }
            }
            return order;
        }
        
        /// <summary>
        /// Updates an existing order in the database based on input parameters
        /// </summary>
        /// <param name="order"></param>
        public bool Update(Order order)
        {
            bool status = false;
            using (SqlConnection connection = new SqlConnection(_connectionString))
            {
                connection.Open();
                using (SqlCommand cmd = connection.CreateCommand())
                {
                    cmd.CommandText = "UPDATE MyOrder " +
                        "set delivery = @delivery, processed = @processed, customerId = @customerId " +
                        "where id = @orderId AND lastChanged = @lastChanged";
                    cmd.Parameters.AddWithValue("delivery", order._delivery);
                    cmd.Parameters.AddWithValue("processed", order._processed);
                    cmd.Parameters.AddWithValue("lastChanged", order._lastChanged);
                    cmd.Parameters.AddWithValue("orderId", order._id);
                    if (order._customer != null)
                    {
                        cmd.Parameters.AddWithValue("customerId", order._customer._id);
                    }
                    else
                    {
                        cmd.Parameters.AddWithValue("customerId", DBNull.Value);
                    }

                    if(cmd.ExecuteNonQuery() == 1)
                    {
                        status = true;
                    }
                }
            }
            return status;
        }

        /// <summary>
        /// Deletes an order from the database based on input parameters
        /// </summary>
        /// <param name="order"></param>
        public void Delete(Order order)
        {
            using (SqlConnection connection = new SqlConnection(_connectionString))
            {
                connection.Open();
                using (SqlCommand cmd = connection.CreateCommand())
                {
                    cmd.CommandText = "DELETE FROM MyOrder where id = @orderId";
                    cmd.Parameters.AddWithValue("orderId", order._id);
                    cmd.ExecuteNonQuery();
                }
            }
        }

        /// <summary>
        ///  Returns all orders from database
        /// </summary>
        /// <returns>IEnumerable<Order> orders</returns>
        public IEnumerable<Order> GetAll()
        {
            List<Order> orders = new List<Order>();
            using (SqlConnection connection = new SqlConnection(_connectionString))
            {
                connection.Open();
                using (SqlCommand cmd = connection.CreateCommand())
                {
                    cmd.CommandText = "select * from MyOrder";
                    SqlDataReader reader = cmd.ExecuteReader();

                    while (reader.Read())
                    {
                        Customer customer = null;
                        if (!reader.IsDBNull(reader.GetOrdinal("customerId")))
                        {
                            int customerId = reader.GetInt32(reader.GetOrdinal("customerId"));
                            customer = _dbCustomer.Read(customerId);
                        }

                        Order order = new Order
                        {
                            _id = reader.GetInt32(reader.GetOrdinal("id")),
                            _delivery = reader.GetBoolean(reader.GetOrdinal("delivery")),
                            _processed = reader.GetBoolean(reader.GetOrdinal("processed")),
                            _lastChanged = (byte[])reader.GetValue(reader.GetOrdinal("lastChanged")),
                            _customer = customer
                        };
                        orders.Add(order);
                    }
                }
            }

            return orders;
        }
    }
}
