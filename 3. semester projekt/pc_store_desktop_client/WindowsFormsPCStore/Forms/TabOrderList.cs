using System.Collections.Generic;
using System.Windows.Forms;
using WindowsFormsPCStore.OrderServiceReference;

namespace WindowsFormsPCStore.Forms
{
    public partial class TabOrderList : Form
    {
        private OrderServiceClient orderServiceClient;
        public TabOrderList()
        {
            InitializeComponent();
            Init();
        }

        private void Init()
        {
            orderServiceClient = new OrderServiceClient();
            IEnumerable<Order> orders = orderServiceClient.GetAll();
            int i = 0;

            foreach (Order order in orders)
            {
                dataGridView1.Rows.Add();
                if (order._customer != null)
                {
                    Customer c = order._customer;
                    dataGridView1.Rows[i].Cells[0].Value = c._fName + " " + c._lName;
                }

                bool done = order._processed;

                dataGridView1.Rows[i].Cells[1].Value = order._id;
                dataGridView1.Rows[i].Cells[2].Value = order._delivery;
                dataGridView1.Rows[i].Cells[3].Value = done;
                dataGridView1.Rows[i].Cells[4].Value = "->";

                i++;
            }
        }

        private void DataGridView1_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            if (e.ColumnIndex == dataGridView1.Columns["viewOrder"].Index)
            {
                int rowId = e.RowIndex;
                int orderId = (int)dataGridView1.Rows[rowId].Cells[1].Value;

                FormViewOrder formViewOrder = new FormViewOrder(orderId);
                formViewOrder.Show();
            }
        }
    }
}
