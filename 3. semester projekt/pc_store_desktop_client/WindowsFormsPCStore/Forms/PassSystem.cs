using System;
using System.ServiceModel;
using System.Threading;
using System.Windows.Forms;
using WindowsFormsPCStore.Controller;
using WindowsFormsPCStore.DbServiceReference;
using WindowsFormsPCStore.EmployeeServiceReference;

namespace WindowsFormsPCStore.Forms
{
    public partial class PassSystem : Form
    {
        private ConnectionThread conThread;
        public PassSystem()
        {
            InitializeComponent();
            conThread = new ConnectionThread();
            conThread.Run();        
        }

        private void btnLogin_Click(object sender, EventArgs e)
        {
            try
            {
                string username = txtUser.Text;
                string password = txtPassword.Text;
                bool status = PasswordHasher.ComparePasswords(username, password);

                if (status)
                {
                    StartNewWindow(new FormFrontPage());
                }
            }
            catch (FaultException<DbConnectionError> exception)
            {
                MessageBox.Show(exception.Detail.Message, "Fejl");
            }
            catch (FaultException<EmployeeNotExistException> exception)
            {
                MessageBox.Show(exception.Detail.Message, "Fejl");
            }
        }
        
        private void StartNewWindow(Form window)
        {
            this.Hide();
            window.Show();
        }

    }
}
