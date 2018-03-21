
package com.ShoppingCart.Controller;

import com.ShoppingCart.Model.Cart;
import com.ShoppingCart.Model.CartItem;
import com.ShoppingCart.Model.Product;
import com.ShoppingCart.Model.User;
import com.ShoppingCart.Services.CartItemServices;
import com.ShoppingCart.Services.CartServices;
import com.ShoppingCart.Services.ProductServices;
import com.ShoppingCart.Services.UserService;
import com.ShoppingCart.dto.Quantity;
import com.ShoppingCart.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart/")
@SessionAttributes("roles")

public class CartController {

    @Autowired
    CartServices cartServices;
    @Autowired
    CartItemServices cartItemServices;
    @Autowired
    UserService userService;

    @RequestMapping(value = "add/{productId}", method = RequestMethod.GET)
    public ResponseEntity<CartItem> addProductsIntoCart( @PathVariable int productId){
        String email = getPrincipal();
        int userId = userService.findByEmail(email).getUserId();
        return new ResponseEntity<CartItem>(cartItemServices.addProductsIntoCart(userId,productId), new HttpHeaders(), HttpStatus.ACCEPTED.OK);
    }

    @RequestMapping(value = {"getCart"}, method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Cart> getCart() {
        String email = getPrincipal();
        int userId = userService.findByEmail(email).getUserId();
        return new ResponseEntity<Cart>(cartServices.getCart(userId), new HttpHeaders(), HttpStatus.OK);

    }

    @RequestMapping(value = "remove/{productId}",method = RequestMethod.GET)
    public ResponseEntity<CartItem> removeCart( @PathVariable int productId) {
        String email = getPrincipal();
        System.out.println("fu");
        int userId = userService.findByEmail(email).getUserId();
        return new ResponseEntity<CartItem>(cartItemServices.removeFromCart(userId,productId),new HttpHeaders(),HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "changeQuantity/{productId}",method = RequestMethod.POST)
    public ResponseEntity changeQuantity( @PathVariable int productId,@RequestBody Quantity quantity){
        String email = getPrincipal();
        int userId = userService.findByEmail(email).getUserId();
        return new ResponseEntity(cartItemServices.updateQuantity(userId,productId,quantity.getQuantity()),new HttpHeaders(),HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "getCartItem/{productId}",method = RequestMethod.GET)
    public ResponseEntity<CartItem> getCartItem(@PathVariable int productId){
        String email = getPrincipal();
        int userId = userService.findByEmail(email).getUserId();
        return new ResponseEntity<CartItem>(cartItemServices.getCartItem(userId,productId),new HttpHeaders(),HttpStatus.ACCEPTED);
    }

    private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

}

