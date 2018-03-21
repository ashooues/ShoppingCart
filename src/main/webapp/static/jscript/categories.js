$(document).ready(function(){
 // $( "#nav_result" ).load( "navBar.html" );
 var categ = [("#Electronics"), "#Watches", "#Mens", "Womens","Shoe","Home"];
	$.get("http://localhost:8084/Ashish/products" + , function(data, status){


        if(status=="success"){
		var html="";
        $.each(data,function(i,order){
            html +=
            '<div class="col-sm-3">' +'<div class="col-item">' 
            +    '<img src="image.png" alt="product" width="90%"/>'
            + '<h3>'+ order.pdName + '<span>'+ order.price +'</span>' + '</h3>' +'</div>'
            
            +    '<a id="add_cart" href="#" class="btn btn-info add_cart" onclick="addtoCart('+ order.productId +')">Add To Cart</a>'
                            +  '<a id="show_details" class="btn btn-info" onclick="showDetails('+ order.productId +')">Detail</a>'
                            
                   
                    
                           + '</div>';
            });
            $("#product_list").html(html);
            
        }
    });
});

