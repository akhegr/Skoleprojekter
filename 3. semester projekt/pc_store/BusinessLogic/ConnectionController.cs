using DataAccess;

namespace BusinessLogic
{
    public class ConnectionController
    {
        public void DbConnectionTest()
        {
            DbConnection dbConnection = new DbConnection();
            dbConnection.ConnectionTest();
        }
    }
}
