using System.Web.Mvc;

namespace WebUI.Controllers
{
    public class HomeController : Controller
    {
        /// <summary>
        /// Refers to call the View Index.cshtml
        /// </summary>
        /// <returns>ActionResult view</returns>
        public ActionResult Index()
        {
            return View();
        }

        /// <summary>
        /// Refers to call the View Contact.cshtml
        /// </summary>
        /// <returns>ActionResult view</returns>
        public ActionResult Contact()
        {
            return View();
        }
    }
}