using System;
using System.ComponentModel;
using System.ServiceModel;
using System.Threading;
using System.Windows.Forms;
using WindowsFormsPCStore.Controller;
using WindowsFormsPCStore.EmployeeServiceReference;

namespace WindowsFormsPCStore.Forms
{
    public partial class PassCreate : Form
    {
        public PassCreate()
        {
            InitializeComponent();
            //backgroundWorker1.DoWork += new DoWorkEventHandler(ConnectionLoop_DoWork);
        }
        
        private void btnCreate_Click(object sender, EventArgs e)
        {
            try
            {
                string fName = txtFName.Text;
                string lName = txtLName.Text;
                string username = txtUser.Text;
                string password = txtPassword.Text;
                PasswordHasher.Create(fName, lName, username, password);
                MessageBox.Show("Medarbejderen er nu oprettet", "Besked");
            }
            catch (FaultException<EmployeeNotCreatedException> exception)
            {
                MessageBox.Show(exception.Detail.Message, "Fejl");
            }

            CleanFields();
        }

        private void CleanFields()
        {
            txtFName.Text = "";
            txtLName.Text = "";
            txtUser.Text = "";
            txtPassword.Text = "";
        }
    }
}
