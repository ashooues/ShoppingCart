$(document).ready(function(){
    $("#nav_result").load("navBar.html"); 
          $.get("/Ashish/isLoggedIn",function(data,status)

            
              {
                if(data=="success")
                {

    $.get("http://localhost:8084/Ashish/order/getOrders",function(data, status){

        

        if(status=="success"){
	        var html="";
		$.each(data,function(i,order){

        $.each(order.orderItems,function(i,orderItem){
            var quan = orderItem.quantity;
            var price = orderItem.product.price;
            var total = quan * price;
            var product =  orderItem.product.pdName;

               html += 
              '<div class="container-float">'
               +   '<div class="row">'
               // +       '<div class="col-sm-12 col-md-10 col-md-offset-1">'
               +           '<table class="table table-hover">'

               +              '<tbody>'+
                                  '<tr>'+
                                      '<td class="col-sm-6 col-md-4">'+
                                      '<div class="media">'+
                                          '<a class="thumbnail pull-left" href="#"> <img class="media-object" src="http://icons.iconarchive.com/icons/custom-icon-design/flatastic-2/72/product-icon.png" style="width: 72px; height: 72px;"> </a>'
                                         + '<div class="media-body">'
                                          +    '<h4 class="media-heading"><a id="show_details" href="/Ashish/static/products.html?id='+ orderItem.product.productId +'" class="btn btn-link show_details center" >'+product+'</a></h4>'
                                           +   //'<h5 class="media-heading"> by <a href="#">'+"Brand name</a></h5>
                                            //  <span>Status: </span><span class="text-success"><strong>In Stock</strong></span>
                                          '</div>'
                                      +'</div></td>'

                                     + '<td class="col-sm-2 col-md-2 text-center"><strong>'+quan+'</strong></td>'
                                      +'</td>'
                                     + '<td class="col-sm-2 col-md-2 text-center"><strong>'+price+'</strong></td>'
                                     + '<td class="col-sm-2 col-md-2 text-center"><strong>'+total+'</strong></td>'
                                     +
                                  '</tr>';


        });
        html+'<hr><hr>'
        });
      
            $("#product_list").html(html);
          }
        });

 //            alert("Test3");
        }
    
  
  else {
    window.location.href="home.html";
  }
 }); 

});
