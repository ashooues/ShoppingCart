$(document).ready(function(){
    $( "#nav_result" ).load( "navBar.html" );
    var uri=window.location.href;
    var id=uri.substr(uri.lastIndexOf("=")+1);
    url= "/Ashish/products/getById/"+id;
 
    $.get(url, function(data, status){
        var html="";
        if(status=="success"){
            // alert("Test2");
            html +='<div class="boxed">'
            +'<div class="row">'
            +   '<div class="col-xs-4 item-photo">'
            +        '<img style="max-width:100%;" src="https://ak1.ostkcdn.com/images/products/8818677/Samsung-Galaxy-S4-I337-16GB-AT-T-Unlocked-GSM-Android-Cell-Phone-85e3430e-6981-4252-a984-245862302c78_600.jpg" />'
            +    '</div>' +'<div class="col-xs-5" style="border:0px solid gray">'
            +'<h3>'+  data.pdName + '</h3>' +'<h6 class="title-price"><small>Offer Price</small></h6>'
            + '<h3 style="margin-top:0px;">INR ' +data.price+'</h3>'  
            +'<div class="section" style="padding-bottom:20px;">'
            +    '<h6 class="title-attr"><small>'+"QUANTITY = "+ 1 +'</small></h6>'

                    +'</div>' 
                    +'<div class="section" style="padding-bottom:20px;">'
                     +   '<button id = "button" class="btn btn-success" onclick="addtoCart('+ data.productId +')"><span style="margin-right:20px" class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span> Add to Cart</button>'
                    /* +   '<h6><a href="#"><span class="glyphicon glyphicon-heart-empty" style="cursor:pointer;"></span> Add to Wishlist</a></h6>'
                    +</div>' */
                    +'<hr>'
                    + '<h3>'+"Product Details" +'</h3>'
                    + '<h4>'+data.details+'</h4>'




            ; 
        



        }
            $("#product_list").html(html);

        

    });

     
})
function addtoCart(productId){

        $.ajax({
          url: "/Ashish/cart/add/" + productId,
          method: "GET",
          success : function(response){
            
            alert("Product Added");
          },
          error : function(response){
            alert("Failure");
        }
        });
}
