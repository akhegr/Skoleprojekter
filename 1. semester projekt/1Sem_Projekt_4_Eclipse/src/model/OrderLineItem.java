package model;

public class OrderLineItem
{
    private int amount;
    private Product product;
    
    public OrderLineItem(int amount, Product product)
    {
        setAmount(amount);
        setProduct(product);
    }
    
    public int getAmount()
    {
        return amount;
    }
    
    public void setAmount(int amount)
    {
        this.amount = amount;
    }
    
    public Product getProduct()
    {
        return product;
    }
    
    public void setProduct(Product product)
    {
        this.product = product;
    }
    
}
