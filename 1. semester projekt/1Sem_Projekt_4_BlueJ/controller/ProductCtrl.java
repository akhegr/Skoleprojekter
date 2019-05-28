package controller;
import model.*;
import exceptions.*;

public class ProductCtrl
{
    private ProductContainer productContainer;

    public ProductCtrl()
    {
        productContainer = ProductContainer.getInstance();
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
        productContainer.createProduct(barcode, category, name, price);
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
        return productContainer.findProduct(barcode);
    }
    
    /**
     * Opdater produkt
     *
     * @param barcode Produktets stregkode
     * @param price Produktets pris
     */
    public void updateProduct(int barcode, double price)
                 throws exceptions.ProductNotExistException
    {
        productContainer.updateProduct(barcode, price);
    }
    
    /**
     * Find produktets kategori
     *
     * @param barcode Produktets stregkode
     * @return String Produktets kategori
     */
    public String findCategory(int barcode)
          throws exceptions.ProductNotExistException
    {
        Product foundProduct = findProduct(barcode);
        return foundProduct.getCategory();
    }
    
    /**
     * Find produktets navn
     *
     * @param barcode Produktets stregkode
     * @return String Produktets navn
     */
    public String findName(int barcode)
          throws exceptions.ProductNotExistException
    {
        Product foundProduct = findProduct(barcode);
        return foundProduct.getName();
    }
    
    /**
     * Find produktets pris
     *
     * @param barcode Produktets stregkode
     * @return double Produktets pris
     */
    public double findPrice(int barcode)
          throws exceptions.ProductNotExistException
    {
        Product foundProduct = findProduct(barcode);
        return foundProduct.getPrice();
    }    
}