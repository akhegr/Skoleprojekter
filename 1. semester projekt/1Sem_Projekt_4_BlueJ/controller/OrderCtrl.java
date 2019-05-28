package controller;
import model.*;
import exceptions.*;

public class OrderCtrl
{
    private OrderContainer orderContainer;
    private EmployeeContainer employeeContainer;
    private ProductContainer productContainer;
    
    private CustomerCtrl customerCtrl;
    private EmployeeCtrl employeeCtrl;
    private ProductCtrl productCtrl;
    
    public OrderCtrl()
    {
        orderContainer    = OrderContainer.getInstance();
        employeeContainer = EmployeeContainer.getInstance();
        productContainer  = ProductContainer.getInstance();
        
        customerCtrl      = new CustomerCtrl();
        employeeCtrl      = new EmployeeCtrl();
        productCtrl       = new ProductCtrl();
    }
    
    /**
     * Opret ordre
     *
     * @param date Datoen for købet
     * @param employeePhone Medarbejderens telefonnummer
     * @return int ordrenummer
     */
    public int createOrder(String date, int employeePhone)
               throws exceptions.PersonNotExistException
    {
        Employee foundEmployee = employeeCtrl.findEmployee(employeePhone);
        return orderContainer.createOrder(date, foundEmployee);
    }
    
    /**
     * Find order
     *
     * @param id Ordrens id
     * @return Order Den samlede ordre
     */
    public Order findOrder(int orderId) throws exceptions.OrderNotExistException
    {
        return orderContainer.findOrder(orderId);
    }
    
    /**
     * Tilføj produkt til ordren (OrderLineItem)
     *
     * @param amount Mængden af produktet
     * @param barcode Produktets stregkode
     * @param orderId Ordrens id
     * @return double Delordrens samlede pris
     */
    public double addOli(int amount, int barcode, int orderId)
          throws exceptions.ProductNotExistException, exceptions.OrderNotExistException
    {
        Product foundProduct = productCtrl.findProduct(barcode);
        Order order = findOrder(orderId);
        order.addOli(amount, foundProduct);
        double price = amount * foundProduct.getPrice();
        return price;
    }
    

    /**
     * Tilføj kunde
     *
     * @param customerPhone Kundens telefonnummer
     * @param orderId Ordrens id
     */
    public void addCustomer(int customerPhone, int orderId)
                 throws exceptions.PersonNotExistException, exceptions.OrderNotExistException
    {
        Customer foundCustomer = customerCtrl.findCustomer(customerPhone);
        Order order = findOrder(orderId);
        order.addCustomer(customerPhone);
    }
    
    /**
     * Fuldfør ordren
     *
     * @param orderId Ordrens id
     * @return boolean Ordrens status
     */
    public boolean finishOrder(int orderId) throws exceptions.OrderNotExistException
    {
        return orderContainer.finishOrder(orderId);
    }
    
}
