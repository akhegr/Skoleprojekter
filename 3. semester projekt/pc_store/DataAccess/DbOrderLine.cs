using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data.SqlClient;
using Domain;

namespace DataAccess
{
    public class DbOrderLine : IDbOrderLine
    {
        private readonly string _connectionString = ConfigurationManager.ConnectionStrings["DBString"].ConnectionString;
        private DbOrderLineList _dbOrderLineList = new DbOrderLineList();

        /// <summary>
        /// Creates an instance of OrderLine in database
        /// </summary>
        /// <param name="orderLine"></param>
        /// <returns>int id</returns>
        public int Create(OrderLine orderLine)
        {
            int id;
            using (SqlConnection connection = new SqlConnection(_connectionString))
            {
                connection.Open();
                
                using (SqlCommand cmd = connection.CreateCommand())
                {
                    cmd.CommandText = "INSERT INTO OrderLine(orderId) VALUES (@orderId); " +
                                      "SELECT SCOPE_IDENTITY()";
                    cmd.Parameters.AddWithValue("orderId", orderLine._orderId);
                    id = Convert.ToInt32(cmd.ExecuteScalar());
                    foreach(UniqueProduct p in orderLine._uniqueProducts)
                    {
                        OrderLineList oll = new OrderLineList
                        {
                            _uniqueProductId = p._id,
                            _orderLineId = id
                        };
                        _dbOrderLineList.Create(oll);
                    }
                }
            }
            return id;
        }

        /// <summary>
        /// Returns an OrderLine from the database based on orderId
        /// </summary>
        /// <param name="orderId"></param>
        /// <returns>int orderLine</returns>
        public OrderLine Read(int orderId)
        {
            OrderLine orderLine = null;
            using (SqlConnection connection = new SqlConnection(_connectionString))
            {
                connection.Open();
                using (SqlCommand cmd = connection.CreateCommand())
                {
                    cmd.CommandText = "SELECT * FROM OrderLine WHERE orderId = @id";
                    cmd.Parameters.AddWithValue("orderId", orderId);
                    SqlDataReader reader = cmd.ExecuteReader();

                    while (reader.Read())
                    {
                        orderLine = new OrderLine
                        {
                            _id = reader.GetInt32(reader.GetOrdinal("id")),
                            _orderId = reader.GetInt32(reader.GetOrdinal("orderId"))
                        };
                    }
                }
            }
            return orderLine;
        }
        /// <summary>
        /// Deletes an OrderLine in the database based on input parameters
        /// </summary>
        /// <param name="orderLine"></param>
        public void Delete(OrderLine orderLine)
        {
            using (SqlConnection connection = new SqlConnection(_connectionString))
            {
                connection.Open();
                using (SqlCommand cmd = connection.CreateCommand())
                {
                    cmd.CommandText = "DELETE FROM OrderLine where orderId = @orderId";
                    cmd.Parameters.AddWithValue("orderId", orderLine._orderId);
                    cmd.ExecuteNonQuery();
                }
            }
        }
        /// <summary>
        /// Returns all OrderLines from the database with a specific orderId
        /// </summary>
        /// <param name="orderId"></param>
        /// <returns>IEnumerable<OrderLine> listOfOrderLines</returns>
        public IEnumerable<OrderLine> GetAllWithOrderId(int orderId)
        {
            List<OrderLine> orderLines = new List<OrderLine>();
            using (SqlConnection connection = new SqlConnection(_connectionString))
            {
                connection.Open();
                using (SqlCommand cmd = connection.CreateCommand())
                {
                    cmd.CommandText = "select * from OrderLine where orderId = @orderId";
                    cmd.Parameters.AddWithValue("orderId", orderId);
                    SqlDataReader reader = cmd.ExecuteReader();

                    while (reader.Read())
                    {
                        OrderLine orderLine = new OrderLine
                        {
                            _id = reader.GetInt32(reader.GetOrdinal("id")),
                            _orderId = reader.GetInt32(reader.GetOrdinal("orderId"))
                        };
                        orderLines.Add(orderLine);
                    }
                }
            }

            return orderLines;
        }
        /// <summary>
        /// Returns all orderLines from the database
        /// </summary>
        /// <returns>IEnumerable<OrderLine> listOfOrderLines</returns>
        public IEnumerable<OrderLine> GetAll()
        {
            List<OrderLine> orderLines = new List<OrderLine>();
            using (SqlConnection connection = new SqlConnection(_connectionString))
            {
                connection.Open();
                using (SqlCommand cmd = connection.CreateCommand())
                {
                    cmd.CommandText = "select * from OrderLine";
                    SqlDataReader reader = cmd.ExecuteReader();

                    while (reader.Read())
                    {
                        OrderLine orderLine = new OrderLine
                        {
                            _id = reader.GetInt32(reader.GetOrdinal("id")),
                            _orderId = reader.GetInt32(reader.GetOrdinal("orderId"))
                        };
                        orderLines.Add(orderLine);
                    }
                }
            }

            return orderLines;
        }
    }
}
