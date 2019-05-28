using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data.SqlClient;
using Domain;

namespace DataAccess
{
    public class DbOrderLineList : IDbOrderLineList
    {
        private readonly string _connectionString = ConfigurationManager.ConnectionStrings["DBString"].ConnectionString;
        private DbProduct _dbProduct = new DbProduct();

        /// <summary>
        /// Creates an instance of an orderLineList in the database
        /// </summary>
        /// <param name="orderLineList"></param>
        /// <returns>int id</returns>
        public int Create(OrderLineList orderLineList)
        {
            int id;
            using (SqlConnection connection = new SqlConnection(_connectionString))
            {
                connection.Open();
                
                using (SqlCommand cmd = connection.CreateCommand())
                {
                    cmd.CommandText = "INSERT INTO OrderLineList(orderLineId, uniqueProductId) " +
                                      "VALUES (@orderLineId, @uniqueProductId); " +
                                      "SELECT SCOPE_IDENTITY()";
                    cmd.Parameters.AddWithValue("orderLineId", orderLineList._orderLineId);
                    cmd.Parameters.AddWithValue("uniqueProductId", orderLineList._uniqueProductId);
                    id = Convert.ToInt32(cmd.ExecuteScalar());
                }
            }
            return id;
        }
        /// <summary>
        /// Returns a list of orderlinelists based on input parameters
        /// </summary>
        /// <param name="orderLineId"></param>
        /// <param name="uniqueProductId"></param>
        /// <returns>OrderLineList orderLineList</returns>
        public OrderLineList Read(int orderLineId, int uniqueProductId)
        {
            OrderLineList orderLineList = new OrderLineList();
            using (SqlConnection connection = new SqlConnection(_connectionString))
            {
                connection.Open();
                using (SqlCommand cmd = connection.CreateCommand())
                {
                    cmd.CommandText = "SELECT * FROM OrderLineList WHERE id = @orderLineId, uniqueProductId = @uniqueProductId";
                    cmd.Parameters.AddWithValue("orderLineId", orderLineId);
                    cmd.Parameters.AddWithValue("uniqueProductId", uniqueProductId);
                    SqlDataReader reader = cmd.ExecuteReader();

                    while (reader.Read())
                    {
                        orderLineList = new OrderLineList
                        {
                            _id = reader.GetInt32(reader.GetOrdinal("id")),
                            _uniqueProductId = reader.GetInt32(reader.GetOrdinal("uniqueProductId"))
                        };
                    }
                }
            }
            return orderLineList;
        }

        /// <summary>
        /// Deletes an OrderLineList based on input parameters
        /// </summary>
        /// <param name="orderLineList"></param>
        public void Delete(OrderLineList orderLineList)
        {
            using (SqlConnection connection = new SqlConnection(_connectionString))
            {
                connection.Open();
                using (SqlCommand cmd = connection.CreateCommand())
                {
                    cmd.CommandText = "DELETE FROM OrderLineList where id = @id, uniqueProductId = @uniqueProductId";
                    cmd.Parameters.AddWithValue("orderLineId", orderLineList._orderLineId);
                    cmd.Parameters.AddWithValue("uniqueProductId", orderLineList._uniqueProductId);
                    cmd.ExecuteNonQuery();
                }
            }
        }
        /// <summary>
        /// Returns a list of products on a specific OrderLine
        /// </summary>
        /// <param name="orderLineId"></param>
        /// <returns>List<UniqueProduct> productList</returns>
        public List<UniqueProduct> GetAllProductsByOlId(int orderLineId)
        {
            List<UniqueProduct> productList = new List<UniqueProduct>();
            using (SqlConnection connection = new SqlConnection(_connectionString))
            {
                connection.Open();
                using (SqlCommand cmd = connection.CreateCommand())
                {
                    cmd.CommandText = "select uProduct.id, uProduct.productId, uProduct.serialNo, " +
                                        "uProduct.warranty from OrderLineList oll, UniqueProduct uProduct " +
                                        "where uProduct.Id = oll.UniqueProductId AND oll.orderLineId = @orderLineId";
                    cmd.Parameters.AddWithValue("orderLineId", orderLineId);
                    SqlDataReader reader = cmd.ExecuteReader();

                    while (reader.Read())
                    {
                        UniqueProduct uniqueProduct = new UniqueProduct
                        {
                            _id = reader.GetInt32(reader.GetOrdinal("id")),
                            _product = _dbProduct.FindById(reader.GetInt32(reader.GetOrdinal("productId"))),
                            _serialNo = reader.GetString(reader.GetOrdinal("serialNo")),
                            _warranty = reader.GetString(reader.GetOrdinal("warranty"))
                        };
                        productList.Add(uniqueProduct);
                    }
                }
            }
            return productList;
        }
        /// <summary>
        /// Returns all OrderLine list based on their ids
        /// </summary>
        /// <param name="orderLineId"></param>
        /// <returns>List<OrderLineList> orderLineLists</returns>
        public List<OrderLineList> GetAllById(int orderLineId)
        {
            List<OrderLineList> orderLineLists = new List<OrderLineList>();
            using (SqlConnection connection = new SqlConnection(_connectionString))
            {
                connection.Open();
                using (SqlCommand cmd = connection.CreateCommand())
                {
                    cmd.CommandText = "select * from OrderLineList where orderLineId = @orderLineId";
                    cmd.Parameters.AddWithValue("orderLineId", orderLineId);
                    SqlDataReader reader = cmd.ExecuteReader();

                    while (reader.Read())
                    {
                        OrderLineList orderLineList = new OrderLineList
                        {
                            _orderLineId = reader.GetInt32(reader.GetOrdinal("orderLineId")),
                            _uniqueProductId = reader.GetInt32(reader.GetOrdinal("productId"))
                        };
                        orderLineLists.Add(orderLineList);
                    }
                }
            }
            return orderLineLists;
        }

        /// <summary>
        /// Returns all orderLineLists
        /// </summary>
        /// <returns>List<OrderLineList> orderLineLists</returns>
        public List<OrderLineList> GetAll()
        {
            List<OrderLineList> orderLineLists = new List<OrderLineList>();
            using (SqlConnection connection = new SqlConnection(_connectionString))
            {
                connection.Open();
                using (SqlCommand cmd = connection.CreateCommand())
                {
                    cmd.CommandText = "select * from OrderLineList";
                    SqlDataReader reader = cmd.ExecuteReader();

                    while (reader.Read())
                    {
                        OrderLineList orderLineList = new OrderLineList
                        {
                            _orderLineId = reader.GetInt32(reader.GetOrdinal("orderLineId")),
                            _uniqueProductId = reader.GetInt32(reader.GetOrdinal("productId"))
                        };
                        orderLineLists.Add(orderLineList);
                    }
                }
            }
            return orderLineLists;
        }
    }
}
