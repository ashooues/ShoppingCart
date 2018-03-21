
package com.ShoppingCart.Controller;

import com.ShoppingCart.Model.Product;
import com.ShoppingCart.Services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/products")
@SessionAttributes("roles")
@SuppressWarnings("unchecked")
public class ProductController {


    @Autowired
    ProductServices productServices;

//
//    @Autowired
//    LoginController loginController;

    @RequestMapping(value = "/check")

    public String helloWorld()
    {
        return "signin";
    }


    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public ResponseEntity<List> getProduct(){
        return  new ResponseEntity(productServices.listAllProducts(), new HttpHeaders(), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/addProduct",method = RequestMethod.GET)
    public String signform()
    {
        return "addProduct";
    }

    @RequestMapping(value = "/addProduct", method = RequestMethod.POST ,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addProduct(@RequestBody Product product){
        System.out.println(product.getCategory());
        return new ResponseEntity(productServices.addProduct(product),new HttpHeaders(), HttpStatus.OK);
    }
    @RequestMapping(value = "/update",method = RequestMethod.GET)
    public String updateform()
    {
        return "updateProduct";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateProduct(@RequestBody Product product){
        productServices.updateProduct(product);
        return new ResponseEntity("Success", new HttpHeaders(),HttpStatus.OK);
    }

    @RequestMapping(value = "/getById/{productId}", method = RequestMethod.GET)
    public ResponseEntity findByPdId(@PathVariable("productId") int pd){
        return new ResponseEntity(productServices.findByPdId(pd),new HttpHeaders(),HttpStatus.ACCEPTED.OK);
    }

    @RequestMapping(value = "/{category}",method = RequestMethod.GET)
    public ResponseEntity<Product> findByCategory(@PathVariable("category") String ctg){
        return new ResponseEntity(productServices.findByCategory(ctg), new HttpHeaders(),HttpStatus.ACCEPTED.OK);
    }


    @RequestMapping(value = "{category}/{subcategory}",method = RequestMethod.GET)
    public ResponseEntity<Product> findBySubCategory(@PathVariable("category") String ctg, @PathVariable("subcategory") String sctg){
        return new ResponseEntity(productServices.findBySubCategory(ctg, sctg), new HttpHeaders(),HttpStatus.ACCEPTED.OK);
    }

    @RequestMapping(value = "/Brand/{brand}",method = RequestMethod.GET)
    public ResponseEntity<Product> findByBrand(@PathVariable("brand") String brand){
        return new ResponseEntity(productServices.findByBrand(brand), new HttpHeaders(),HttpStatus.ACCEPTED.OK);
    }

    @RequestMapping(value = "/search/{searchString}", method = RequestMethod.GET)
    public ResponseEntity getProductsBySearchString(@PathVariable("searchString") String searchString){
    List product = productServices.getProductsBySearchString(searchString);
    return new ResponseEntity(product,new HttpHeaders(),HttpStatus.ACCEPTED.OK);
    }

    @RequestMapping(value = { "/product/{id}" }, method = RequestMethod.GET)
    public String productRedirect(@PathVariable int id){
        return "productDetails";
    }

    @RequestMapping(value = "{category}/filterPrice/{price1}/{price2}", method = RequestMethod.GET)
    public ResponseEntity<List> filterPrice(@PathVariable String category, @PathVariable ("price1") double price1, @PathVariable ("price2") double price2 ){
        return new ResponseEntity<List>(productServices.findByPrice(category, price1, price2), new HttpHeaders(),HttpStatus.ACCEPTED.OK);
    }
}
