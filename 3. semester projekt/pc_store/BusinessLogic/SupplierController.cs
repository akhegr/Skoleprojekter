using DataAccess;
using Domain;
using System.Collections.Generic;

namespace BusinessLogic
{
    public class SupplierController
    {
        public DbSupplier _dbSupplier { get; set; }
        /// <summary>
        /// This method will instantiate a new supplier controller.
        /// </summary>
        public SupplierController()
        {
            _dbSupplier = new DbSupplier();
        }

        /// <summary>
        /// This method will create a new supplier.
        /// </summary>
        /// <param name="supplier"></param>
        /// This is the supplier that is going to be created.
        public void Create(Supplier supplier)
        {
            _dbSupplier.Create(supplier);
        }

        /// <summary>
        /// This method will find a supplier based on the cvrNo
        /// </summary>
        /// <param name="cvrNo"></param>
        /// This is the cvrNo on the supplier. Is is used to identify the supplier. 
        /// <returns></returns>
        /// It wil return the given supplier.
        public Supplier Read(string cvrNo)
        {
            return _dbSupplier.Read(cvrNo);
        }

        /// <summary>
        /// This method will update the supplier. It will use the phone number to do so.
        /// </summary>
        /// <param name="supplier"></param>
        /// This is the supplier there is going to be updated.
        /// <param name="oldPhone"></param>
        /// This is the old phone number of the supplier.
        public void Update(Supplier supplier, string oldPhone)
        {
            _dbSupplier.Update(supplier, oldPhone);
        }

        /// <summary>
        /// This method will delete the supplier. 
        /// </summary>
        /// <param name="supplier"></param>
        /// This is the supplier there is going to be deleted. 
        public void Delete(Supplier supplier)
        {
            _dbSupplier.Delete(supplier);
        }

        /// <summary>
        /// This method will create an unidentified list of all the suppliers. 
        /// </summary>
        /// <returns></returns>
        /// It will return the list of all the suppliers. 
        public IEnumerable<Supplier> GetAll()
        {
            return _dbSupplier.GetAll();
        }
    }
}
