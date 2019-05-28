using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using BusinessLogic;
using Domain;
using Exceptions;
using System.ServiceModel;

namespace Tests
{
    [TestClass]
    public class ZipCityTest
    {
        /// <summary>
        /// Tests whether the zipcode exists in the database
        /// </summary>
        [TestMethod]
        public void CheckZipcodeInDb()
        {
            ZipCityController zcCtrl = new ZipCityController();
            try
            {
                ZipCity zipCity = zcCtrl.ReadByZipCode("3451");
                Assert.IsTrue(zipCity._zipCode == 3451);
            }
            catch (FaultException<ZipCodeNotInDbException> zipEx)
            {
                Console.WriteLine(zipEx.Message);
            }
        }

        /// <summary>
        /// Tests whether the zipcode is too long or short
        /// </summary>
        [TestMethod]
        public void CheckZipcodeIsIllegal()
        {
            ZipCityController zcCtrl = new ZipCityController();
            try
            {
                ZipCity zipCity = zcCtrl.ReadByZipCode("34512");
                Assert.IsTrue(zipCity._zipCode == 3451);
            }
            catch (FaultException<ZipCodeLengthException> zipEx)
            {
                Console.WriteLine(zipEx.Message);
            }
        }

        /// <summary>
        /// Tests the zipcode is legal
        /// </summary>
        [TestMethod]
        public void CheckZipcodeIsLegal()
        {
            ZipCityController zcCtrl = new ZipCityController();
            try
            {
                ZipCity zipCity = zcCtrl.ReadByZipCode("9200");
                Assert.IsTrue(zipCity._zipCode == 9200);
            }
            catch (FaultException<ZipCodeLengthException> zipEx)
            {
                Console.WriteLine(zipEx.Message);
            }
            catch (FaultException<ZipCodeNotInDbException> zipEx)
            {
                Console.WriteLine(zipEx.Message);
            }
        }
    }
}
