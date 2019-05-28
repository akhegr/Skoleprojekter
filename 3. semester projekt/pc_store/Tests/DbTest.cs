using System;
using System.ServiceModel;
using BusinessLogic;
using Exceptions;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace Tests
{
    [TestClass]
    public class DbTest
    {
        [TestMethod]
        public void TestDbConnection()
        {
            ConnectionController conCtrl = new ConnectionController();
            bool status = true;
            try
            {
                conCtrl.DbConnectionTest();
            }
            catch (FaultException<DbConnectionError> exception)
            {
                Console.WriteLine(exception.Detail.Message);
                status = false;
            }

            Assert.IsTrue(status);
        }
    }
}
