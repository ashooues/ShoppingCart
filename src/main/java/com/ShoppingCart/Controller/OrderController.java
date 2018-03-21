package com.ShoppingCart.Controller;

import com.ShoppingCart.Model.Order;
import com.ShoppingCart.Services.OrderService;
import com.ShoppingCart.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@RequestMapping("/order")
@SessionAttributes("roles")
@SuppressWarnings("unchecked")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/getOrders",method = RequestMethod.GET)
    public ResponseEntity<List> getOrder(){
        String email = getPrincipal();
        int userId = userService.findByEmail(email).getUserId();
        return new ResponseEntity(orderService.getOrders(userId),new HttpHeaders(), HttpStatus.ACCEPTED);
    }
    @RequestMapping(value = "/createOrder",method = RequestMethod.GET)
    public ResponseEntity createOrder(){
        String email = getPrincipal();
        int userId = userService.findByEmail(email).getUserId();
        return new ResponseEntity<Order>(orderService.createOrder(userId),new HttpHeaders(),HttpStatus.ACCEPTED);
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
