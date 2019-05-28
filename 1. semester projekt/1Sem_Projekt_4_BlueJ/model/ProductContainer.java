package model;
import java.util.Map;
import java.util.HashMap;
import exceptions.*;

public class ProductContainer
{
    // instance variables - replace the example below with your own
    private Map<Integer, Product> productContainer;
    private static ProductContainer instance; 
    /**
     * Constructor for objects of class ProductContainer
     */
    private ProductContainer()
    {
        // initialise instance variables
        productContainer = new HashMap<Integer, Product>();
    }
    
    /**
     * getInstance()
     */
    public static ProductContainer getInstance()
    {
        if(instance == null)
        {
            instance = new ProductContainer();
        }
        
        return instance;
    }
    
    /**
     * Opret produkt
     *
     * @param barcode Produktets stregkode
     * @param category Produktets kategori
     * @param name Produktets navn
     * @param price Produktets pris
     */
    public void createProduct(int barcode, String category, String name, double price)
    {
        Product product = new Product(barcode, category, name, price);
        productContainer.put(barcode, product);
    }
    
    /**
     * Find produkt
     *
     * @param barcode Produktets stregkode
     * @return Product Det søgte produkt
     */
    public Product findProduct(int barcode)
          throws exceptions.ProductNotExistException
    {
        Product foundProduct = productContainer.get(barcode);
        if(foundProduct == null)
        {
            throw new exceptions.ProductNotExistException(barcode);
        }
        
        return foundProduct;
    }
    
    /**
     * Opdater produkt
     *
     * @param barcode Produktets stregkode
     * @param price Produktets nye pris
     */
    public void updateProduct(int barcode, double price)
          throws exceptions.ProductNotExistException
    {
        Product product = findProduct(barcode);
        product.setPrice(price);
        productContainer.put(barcode, product);
    }
    
}
