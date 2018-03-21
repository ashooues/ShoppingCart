
package com.ShoppingCart.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "CART_ITEM")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartItemId;

    @JoinColumn(name = "productId", referencedColumnName = "productId")
    @OneToOne( fetch = FetchType.EAGER)
    private Product product;

    @Column(name = "QUANTITY")
    private int quantity;

    @JsonIgnore
    @JoinColumn(name = "cartId", referencedColumnName = "cartId")
    @ManyToOne( fetch = FetchType.EAGER)
    private Cart cart;



    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(int cartItemId) {
        this.cartItemId = cartItemId;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
