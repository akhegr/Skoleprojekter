package controller;

import java.util.Map;
import model.*;
import exceptions.*;

public class OrderCtrl
{
    private OrderContainer orderContainer;
    //private EmployeeContainer employeeContainer;
    //private ProductContainer productContainer;
    
    //private CustomerCtrl customerCtrl;
    private EmployeeCtrl employeeCtrl;
    private ProductCtrl productCtrl;
    
    public OrderCtrl()
    {
        orderContainer    = OrderContainer.getInstance();
        //employeeContainer = EmployeeContainer.getInstance();
        //productContainer  = ProductContainer.getInstance();
        
        //customerCtrl      = new CustomerCtrl();
        employeeCtrl      = new EmployeeCtrl();
        productCtrl       = new ProductCtrl();
    }
    
    /**
     * Create order
     *
     * @param date Date for purchase 
     * @param employeePhone Employee's phone number
     * @return int order number
     * @throws PersonNotExistException
     */
    public int createOrder(String date, int employeePhone)
               throws PersonNotExistException
    {
        Employee foundEmployee = employeeCtrl.findEmployee(employeePhone);
        return orderContainer.createOrder(date, foundEmployee);
    }
    
    /**
     * Find order
     *
     * @param id order id
     * @return Order The total order
     * @throws OrderNotExistException
     */
    public Order findOrder(int orderId) throws OrderNotExistException
    {
        return orderContainer.findOrder(orderId);
    }
    
    /**
     * Add product to order (OrderLineItem)
     *
     * @param amount Amount of products
     * @param barcode Product's barcode
     * @param orderId The order id
     * @return double Sub order total price
     * @throws ProductNotExistException, OrderNotExistException
     */
    public double addOli(int amount, int barcode, int orderId)
          throws ProductNotExistException, OrderNotExistException
    {
        Product foundProduct = productCtrl.findProduct(barcode);
        Order order = findOrder(orderId);
        order.addOli(amount, foundProduct);
        double price = amount * foundProduct.getPrice();
        return price;
    }
    
    /**
     * Returns the purchased items
     *
     * @return Map A Map of the purchased goods
     * @throws OrderNotExistException 
     */
    public Map<Integer, OrderLineItem> getOli(int orderId) throws OrderNotExistException
    {
    	Order order = findOrder(orderId);
    	return order.getOli();
    }
    
    /**
     * Add customer
     *
     * @param customerPhone Customer's phone number
     * @param orderId The order id
     * @throws PersonNotExistException, OrderNotExistException
     */
    public void addCustomer(int customerPhone, int orderId)
                 throws PersonNotExistException, OrderNotExistException
    {
        //Customer foundCustomer = customerCtrl.findCustomer(customerPhone);
        Order order = findOrder(orderId);
        order.addCustomer(customerPhone);
    }
    
    /**
     * Returns the order's sum with offset discount
     * 
     * @param orderId The oder id
     * @return The order's total minus discount
     * @throws exceptions.OrderNotExistException
     */
    public double sumWithDiscount(int orderId) throws exceptions.OrderNotExistException
    {
    	return orderContainer.sumWithDiscount(orderId);
    }
    
    /**
     * Checking if the customer has enough money to pay the order
     *
     * @param orderId The order's number
     * @return boolean Status of money in the account
     */
    public boolean checkStatus(int orderId) throws OrderNotExistException
    {
		return orderContainer.checkStatus(orderId);
    }
    
    /**
     * Checking whether the order is paid
     *
     * @param orderId The order's number
     * @return boolean Whether the order is paid or not
     */
    public boolean checkIsPayed(int orderId) throws OrderNotExistException
    {
    	Order foundOrder = orderContainer.findOrder(orderId);
        return foundOrder.getIsPayed();
    }
    
    /**
     * Complete order
     *
     * @param orderId The order's id
     * @return boolean The oder's status
     * @throws OrderNotExistException
     */
    public boolean finishOrder(int orderId) throws OrderNotExistException
    {
        return orderContainer.finishOrder(orderId);
    }
 
    /**
     * Returns default comma
     *
     * @param value The order's total
     * @return String The sum with real commas
     */
    public String getCurrencyFormat(double value) {
        return orderContainer.getCurrencyFormat(value);
    }
}
