package model;

public class Product
{
    private int barcode;
    private String category;
    private String name;
    private double price;
	private int amount;
    
    public Product(int barcode, String category, String name, double price, int amount)
    {
        setBarcode(barcode);
        setCategory(category);
        setName(name);
        setPrice(price);
        setAmount(amount);
    }
    
    //Get and set methods
    public int getBarcode()
    {
        return barcode;
    }
    
    public void setBarcode(int barcode)
    {
        this.barcode = barcode;
    }
    
    public String getCategory()
    {
        return category;
    }
    
    public void setCategory(String category)
    {
        this.category = category;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public double getPrice()
    {
        return price;
    }
    
    public void setPrice(double price)
    {
        this.price = price;
    }
    
    public int getAmount()
    {
         return amount;
    }
    
	public void setAmount(int amount) {
        this.amount = amount;
	}
    
}
