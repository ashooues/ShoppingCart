
$(document).ready(function(){
/*  $( "#nav_result" ).load( "navBar.html" );
*/

    $("#nav_result").load("navBar.html");
 //alert("cart");
               
    $.get("http://localhost:8084/Ashish/cart/getCart",function(cart, status){
    
    var html="";
    $("#item-number").html(cart.cartItems.length);
   // alert(cart.cartItems.length);
    if(cart.cartItems.length==0){

         // $("#check").css("display", "none");
          
          html+='<div><h1>Cart is empty</h1></div>'+
        '<div class="btn btn-default"><a href = "home.html">add products to cart</div>';

         $("#check").html(html);

        }
        else{

        
        var x = 0;
        $.each(cart.cartItems,function(i,cartItem){
            var quan = cartItem.quantity;
            var price = cartItem.product.price;
            var total = quan * price;
            x= x+total;



               html += 
              '<div class="container-float">'
               +   '<div class="row">'
               // +       '<div class="col-sm-12 col-md-10 col-md-offset-1">'
               +           '<table class="table table-hover">'

               +              '<tbody>'+
                                  '<tr>'+
                                      '<td class="col-sm-5 col-md-5">'+
                                      '<div class="media">'+
                                          '<a class="thumbnail pull-left" href="#"> <img class="media-object" src="http://www.telestream.net/images/home/logo-product-wirecast-go.png" style="width: 72px; height: 72px;"> </a>'
                                         + '<div class="media-body">'
                                          +'<a id="show_details" href="/Ashish/static/products.html?id='+ cartItem.product.productId +'" class="btn btn-link show_details center" ><h5>'+cartItem.product.pdName+'</h5></a>'
                                           +   
                                          '</div>'
                                      +'</div></td>'

                                            
                                             +'<td class="col-md-4">'
                                             +'<div class="input-group plus-minus-input">'
                                             +'<div class="input-group-button">'
                                             
                                             +'<button type="button"class="button hollow circle" data-quantity="minus" onclick="changeQuantity('+cartItem.product.productId+","+cartItem.quantity+","+"-1"+')">'+"-"+'</button>'

                                             +'<input  class="input-group-field" type="text" value="'+cartItem.quantity +'"/>'
                                             +'<button class="button hollow circle" onclick="changeQuantity('+cartItem.product.productId+","+cartItem.quantity+","+"1"+')">'+"+"+'</button>'
                                             
                                             +'</div>'
                                             +'</td>'
                                             +'</div>'
                                             // +'</div>'
//        
                         + '<td class="col-md-1 text-center"><strong>'+price+'</strong></td>'
                        + '<td class="col-md-1 text-center"><strong>'+total+'</strong></td>'
                        // + '<td class="col-sm-1 col-md-1 text-center"><strong>'+cartItem.product.productId+'</strong></td>'
                        + '<td class="col-sm-1 col-md-1">'
                        + '<button type="button" id="remove" onclick="remove('+ cartItem.product.productId +')" class="btn btn-danger ">'
                        +'<span class="glyphicon glyphicon-remove" ></span>' + 'Remove'
                        + '</button></td>'
                    +'</tr>';

               
        });
          
            $("#product_list").html(html);
            var totalprice='<h4>'+"Total "+x+'</h4>';
            $("#totalprice").html(totalprice);
          }


    });


});
       



function remove(productId){
   
        $.ajax({
          url: "http://localhost:8084/Ashish/cart/remove/" + productId,
          method: "GET",
  
          success : function(response){
            location.reload();
            alert("success");
          },
          error : function(response){
            alert("Failure");
        }
        });
}



function changeQuantity(productId, quantity,change){

    quantity += change;

    var Quantity = $("#Quantity").val();
    $.ajax({

          url: "/Ashish/cart/changeQuantity/"+productId,
          method: 'POST',

           contentType: 'application/json',

          data: '{"quantity":'+quantity+'}',

              
        success : function(response){
            location.reload();
            alert("success");
          },
          error : function(response){
            alert("Failure");
        }
      });


}