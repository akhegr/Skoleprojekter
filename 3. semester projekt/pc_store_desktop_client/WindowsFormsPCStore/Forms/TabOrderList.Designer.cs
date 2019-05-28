using System.Windows.Forms;

namespace WindowsFormsPCStore.Forms
{
    partial class TabOrderList
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.dataGridView1 = new System.Windows.Forms.DataGridView();
            this.customer = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.id = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.delivery = new System.Windows.Forms.DataGridViewCheckBoxColumn();
            this.fulfilled = new System.Windows.Forms.DataGridViewCheckBoxColumn();
            this.viewOrder = new System.Windows.Forms.DataGridViewButtonColumn();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).BeginInit();
            this.SuspendLayout();
            // 
            // dataGridView1
            // 
            this.dataGridView1.AllowUserToAddRows = false;
            this.dataGridView1.AllowUserToDeleteRows = false;
            this.dataGridView1.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridView1.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.customer,
            this.id,
            this.delivery,
            this.fulfilled,
            this.viewOrder});
            this.dataGridView1.Location = new System.Drawing.Point(2, 1);
            this.dataGridView1.Name = "dataGridView1";
            this.dataGridView1.ReadOnly = true;
            this.dataGridView1.Size = new System.Drawing.Size(617, 394);
            this.dataGridView1.TabIndex = 1;
            this.dataGridView1.CellClick += new DataGridViewCellEventHandler(DataGridView1_CellClick);
            // 
            // customer
            // 
            this.customer.HeaderText = "Kunde";
            this.customer.Name = "customer";
            this.customer.ReadOnly = true;
            this.customer.Width = 250;
            // 
            // id
            // 
            this.id.HeaderText = "Id";
            this.id.Name = "id";
            this.id.ReadOnly = true;
            this.id.Width = 75;
            // 
            // delivery
            // 
            this.delivery.HeaderText = "Levering";
            this.delivery.Name = "delivery";
            this.delivery.ReadOnly = true;
            this.delivery.Resizable = System.Windows.Forms.DataGridViewTriState.True;
            this.delivery.SortMode = System.Windows.Forms.DataGridViewColumnSortMode.Automatic;
            this.delivery.Width = 75;
            // 
            // fulfilled
            // 
            this.fulfilled.HeaderText = "Fuldført";
            this.fulfilled.Name = "fulfilled";
            this.fulfilled.ReadOnly = true;
            this.fulfilled.Resizable = System.Windows.Forms.DataGridViewTriState.True;
            this.fulfilled.SortMode = System.Windows.Forms.DataGridViewColumnSortMode.Automatic;
            this.fulfilled.Width = 75;
            // 
            // viewOrder
            // 
            this.viewOrder.HeaderText = "Se ordre";
            this.viewOrder.Name = "viewOrder";
            this.viewOrder.ReadOnly = true;
            this.viewOrder.Width = 75;
            // 
            // TabOrderList
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(625, 401);
            this.Controls.Add(this.dataGridView1);
            this.Name = "TabOrderList";
            this.Text = "TabOrderList";
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).EndInit();
            this.ResumeLayout(false);
            
            
        }

        #endregion

        private System.Windows.Forms.DataGridView dataGridView1;
        private System.Windows.Forms.DataGridViewTextBoxColumn customer;
        private System.Windows.Forms.DataGridViewTextBoxColumn id;
        private System.Windows.Forms.DataGridViewCheckBoxColumn delivery;
        private System.Windows.Forms.DataGridViewCheckBoxColumn fulfilled;
        private System.Windows.Forms.DataGridViewButtonColumn viewOrder;
    }
}