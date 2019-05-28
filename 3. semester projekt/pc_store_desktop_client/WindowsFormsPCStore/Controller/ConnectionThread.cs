using System.ServiceModel;
using System.Threading;
using System.Threading.Tasks;
using System.Windows.Forms;
using WindowsFormsPCStore.DbServiceReference;

namespace WindowsFormsPCStore.Controller
{
    public class ConnectionThread
    {
        private Thread thread;
        private DbServiceClient _dbServiceClient;
        public ConnectionThread()
        {
            thread = new Thread(new ThreadStart(Run));
            thread.Start();
        }

        public void Run()
        {
            Task f = Task.Factory.StartNew(() =>
            {
                while (true)
                {
                    _dbServiceClient = new DbServiceClient();
                    try
                    {
                        _dbServiceClient.TestConnection();
                    }
                    catch (FaultException<DbConnectionError> exception)
                    {
                        MessageBox.Show(exception.Detail.Message, "Forbindelsesfejl");
                        break;
                    }
                    Thread.Sleep(5000);
                }
            });
        }
    }
}
