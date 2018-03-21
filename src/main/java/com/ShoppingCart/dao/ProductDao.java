
package com.ShoppingCart.dao;

import com.ShoppingCart.Model.Product;

import java.util.List;

public interface ProductDao {
    Product findByPdId(int id);
    Product findByName(String name);
    List<Product> findByCategory(String ctg);
    List<Product> findByBrand(String brand);
    List<Product>  findBySubCategory(String ctg,String sctg);
    void updateProduct(Product product);
    List<Product> listAllProducts();
    List getProductsBySearchString(String searchString);
//    Product addProduct(Product product);
    Product save(Product p);
    List<Product> findByPrice(String Category, double price1, double price2);
}


