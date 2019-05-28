using System.Collections.Generic;
using System.Windows.Forms;
using WindowsFormsPCStore.OrderServiceReference;

namespace WindowsFormsPCStore.Forms
{
    public partial class FormViewOrder : Form
    {
        private int _orderId;
        private OrderServiceClient orderServiceClient;
        
        public FormViewOrder(int orderId)
        {
            InitializeComponent();
            _orderId = orderId;
            Init();
        }

        private void Init()
        {
            orderServiceClient = new OrderServiceClient();
            Order order = orderServiceClient.Read(_orderId);
            if(order != null)
            {
                Customer customer = order._customer;
                if(customer != null)
                {
                    lblCustomerName.Text = customer._fName + " " + customer._lName;
                    lblCustomerAddress.Text = customer._address;
                    lblCustomerCity.Text = customer._zipCity._zipCode + " " + customer._zipCity._city;
                    lblCustomerEmail.Text = customer._email;
                    lblCustomerPhone.Text = customer._phone;
                }

                IEnumerable<UniqueProduct> uniqueProducts = orderServiceClient.GetAllProductsByOrderId(_orderId);
                listBoxProducts.Items.Clear();
                foreach (UniqueProduct up in uniqueProducts)
                {
                    listBoxProducts.Items.Add(up._product._name);
                }
            }
        }
    }
}
