package com.ShoppingCart.Services;

import com.ShoppingCart.Model.Product;

import java.util.List;

public interface ProductServices {
    Product findByPdId(int id);
    Product findByName(String name);
    List<Product> findByCategory(String ctg);
    List<Product> findByBrand(String brand);
    List<Product>  findBySubCategory(String ctg, String sctg);
    List<Product> listAllProducts();
    Product addProduct(Product p);
    List getProductsBySearchString(String searchString);
    void updateProduct(Product product);
    List<Product> findByPrice(String Category, double price1, double price2);
}

