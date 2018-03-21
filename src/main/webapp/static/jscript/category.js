$(document).ready(function(){


	 $("#nav_result").load("navBar.html");

	var url = window.location.href;

  var category = url.substr(url.lastIndexOf("=")+1);


   $("#category").html(category)
	$.get("/Ashish/products/"+category, function(data, status){

		if(status=="success"){
    var html="";
    //			html+='<div class="col-sm-2">';
 ;
        	$.each(data, function (index, order) {
        		 html +=
            '<div class="col-sm-3 box">'  
            +    '<img src="images/addons.jpg" alt="product" width="90%"/>'
            + '<div class="box1">'+'<h5>'+ order.pdName + '</h5>' +'</div>'+'<span>'+'<div class="box1">'+'<h5>' + order.price +'</span>' + '</h>' +'</div>'
            
            +    '<div><span style="float: right"><a id="add_cart" href="#" class="btn btn-info add_cart box2" onclick="addtoCart('+ order.productId +')">Add To Cart </a></span>'
                            /*+  '<a id="show_details" class="btn btn-info" onclick="showDetails('+ order.productId +')">Detail</a>'*/
                            
                   +'<span><a id="show_details" href="/Ashish/static/products.html?id='+ order.productId +'" class="btn btn-info show_details box2" >Detail</a></span>'
                    
                           + '</div></div>' ;
                       
            });
            $("#product_list").html(html);
		}
	});

})

$(document).on('click', '#searchPrice', function(){
  var min = $("#min").val();
  var max = $("#max").val();
  var url = window.location.href;

  var category = url.substr(url.lastIndexOf("=")+1);
  $.get("/Ashish/products/"+category + "/filterPrice/"+min+"/"+max, function(data, status){

    if(status=="success"){
    var html="";
    $.each(data, function (index, order) {
             html +=
            '<div class="col-sm-3 box">'/* +'<div class="box ">' +'<div>'*/
            +'<a id="pro" href="/Ashish/static/products.html?id='+ order.productId +'">'
            +    '<img src="images/addons.jpg" alt="product" width="90%"/>'
            + '<div class="box1">'+'<h5>'+ order.pdName + '</h5>'+ '</a> </div>'+'<span >'+'<div class="box1">'+'<h5>'+ order.price +'</span>' + '</h5>'+ ' </div>'
            
            +    '<div><span style="float: right"><a id="add_cart" href="#" class="btn btn-info add_cart box2" onclick="addtoCart('+ order.productId +')">Add To Cart </a></span>'
                            /*+  '<a id="show_details" class="btn btn-info" onclick="showDetails('+ order.productId +')">Detail</a>'*/
                            
                   +'<span><a id="show_details" href="/Ashish/static/products.html?id='+ order.productId +'" class="btn btn-info show_details box2" >Detail</a></span>'
                    
                           + '</div></div>' ;
                       
            });
            $("#product_list").html(html);
    }
  });

})





function addtoCart(productId){
alert(productId);
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

