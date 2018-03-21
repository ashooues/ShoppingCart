
package com.ShoppingCart.Services;

import com.ShoppingCart.Model.Product;
import com.ShoppingCart.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("ProductServices")
@Transactional
public class ProductServicesImpl implements ProductServices{
    @Autowired
    ProductDao dao;

    public Product findByPdId(int id){ return dao.findByPdId(id);}
    public Product findByName(String name){ return dao.findByName(name);}
    public List<Product> findByCategory(String ctg){ return dao.findByCategory(ctg);}
    public List<Product> findByBrand(String brand){ return dao.findByBrand(brand);}
    public List<Product>  findBySubCategory(String ctg, String sctg){ return dao.findBySubCategory(ctg, sctg);}
    public List<Product> listAllProducts() {
        return dao.listAllProducts();
    }
    public void updateProduct(Product product){ dao.updateProduct(product);}
    public List getProductsBySearchString(String searchString){return dao.getProductsBySearchString(searchString);}
    public Product addProduct(Product p) {
        dao.save(p);
        return p;
    }
    public List<Product> findByPrice(String category, double price1, double price2){ return dao.findByPrice(category, price1, price2);}
}

