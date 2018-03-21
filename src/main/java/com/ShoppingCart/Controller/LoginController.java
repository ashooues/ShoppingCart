
package com.ShoppingCart.Controller;

import com.ShoppingCart.Model.Cart;
import com.ShoppingCart.Model.User;
import com.ShoppingCart.Model.UserProfile;
import com.ShoppingCart.Services.CartServices;
import com.ShoppingCart.Services.UserProfileService;
import com.ShoppingCart.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    UserProfileService userProfileService;

    @Autowired
    MessageSource messageSource;


    @Autowired
    CartServices cartServices;

/*
    @Autowired
    PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;
*/

    @Autowired
    AuthenticationTrustResolver authenticationTrustResolver;

    @Autowired
    DaoAuthenticationProvider authenticationProvider;

    @RequestMapping(value = { "/getProfile" }, method = RequestMethod.GET)
    public @ResponseBody User getProfile(){

        return userService.findByEmail(getPrincipal());
    }

    @RequestMapping(value = { "/home" }, method = RequestMethod.GET)
    public String homeRedirect(){

        return "home";
    }
/*    @RequestMapping(value = { "/product/{id}" }, method = RequestMethod.GET)
    public String homeRedirect(@PathVariable int id){

        return "productDetails";
    }*/


    @ModelAttribute("roles")
    public List<UserProfile> initializeProfiles() {
        return userProfileService.findAll();
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


    private boolean isCurrentAuthenticationAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authenticationTrustResolver.isAnonymous(authentication);
    }

    @RequestMapping(value = "/signup",method = RequestMethod.GET)
    public String signform()
    {
        return "signup";
    }



    @ResponseBody
    @RequestMapping(value = "/signup",method =RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public String newUser(@RequestBody User user)
    {

        if(userService.findByEmail(user.getEmailId()) == null)
        {
            userService.saveUser(user);

            User newUser = userService.findByEmail(user.getEmailId());
            cartServices.makeCart(newUser);

            return "registration successful";}
        else return "emailId already registered";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String loginform()
    {
        return "login";
    }

    @ResponseBody
    @RequestMapping(value = "/login",method =RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String>  loginUser(@RequestBody  User user)
    {
//        if(getPrincipal().equals("anonymousUser")){
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getEmailId(),user.getPassword());
            Authentication authentication = this.authenticationProvider.authenticate(token);
            System.out.println(authentication);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return new ResponseEntity<>("hi", HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            SecurityContextHolder.getContext().setAuthentication(null);
            return new ResponseEntity<>("Bad Credebtial", HttpStatus.FORBIDDEN);
        }
    //    }
/*        else {
            return new ResponseEntity<>("Logout First", HttpStatus.FORBIDDEN);
        }*/
    }
    @RequestMapping(value = { "/isLoggedIn" }, method = RequestMethod.GET)
    public ResponseEntity isLoggedIn() {
        if (getPrincipal() == "anonymousUser") {
            return new ResponseEntity("failure", HttpStatus.OK);
        }
        else return new ResponseEntity("success", HttpStatus.OK);
    }


    @RequestMapping(value = "/updateProfile",method = RequestMethod.GET)
    public String updateform()
    {
        return "updateProfile";
    }

    @ResponseBody
    @RequestMapping(value = "/updateProfile"/*, method =RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE*/)
    public String updateProfile(@RequestBody User user)
    {

        if(getPrincipal().equals(user.getEmailId()))
        {
            user.setPassword(userService.findByEmail(getPrincipal()).getPassword());
            user.setUserId(userService.findByEmail(getPrincipal()).getUserId());
            userService.updateUser(user);
            return "success";
        }
        else return "you are not authorized to update this profile";
    }

    @RequestMapping(value="/signout", method = RequestMethod.GET)
    @ResponseBody
    public String logoutHandler(HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(getPrincipal().equals("anonymousUser")){
            return "Login first";
        }
        else {
            if (auth != null) {
                new SecurityContextLogoutHandler().logout(request, response, auth);
                SecurityContextHolder.getContext().setAuthentication(null);
            }
            return "successfully logged out";
        }
    }
}