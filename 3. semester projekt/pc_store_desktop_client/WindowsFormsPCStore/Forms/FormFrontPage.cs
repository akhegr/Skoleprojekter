using System.Collections.Generic;
using System.Windows.Forms;
using WindowsFormsPCStore.DbServiceReference;
using WindowsFormsPCStore.OrderServiceReference;

namespace WindowsFormsPCStore.Forms
{
    public partial class FormFrontPage : Form
    {
        public FormFrontPage()
        {
            InitializeComponent();
            AddPage(new TabOrderList(), 0);
            AddPage(new PassCreate(), 1);
            //dbServiceClient.TestConnection();
        }
    
        private void AddPage(Form form, int pageId)
        {
            form.TopLevel = false;
            form.Visible = true;
            form.FormBorderStyle = FormBorderStyle.None;
            form.Dock = DockStyle.Fill;
            tabControl1.TabPages[pageId].Controls.Add(form);
        }
    }
}
