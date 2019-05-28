using BusinessLogic;
using Domain;
using System.Collections.Generic;
using System.ServiceModel;

namespace OrderService
{
    public class DbService : IDbService
    {
        public void TestConnection()
        {
            ConnectionController conCtrl = new ConnectionController();
            conCtrl.DbConnectionTest();
        }
    }
}