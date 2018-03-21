function searching(){

    var string = $("#search").val();


    $.get("http://localhost:8084/Ashish/products/search/"+ string, function(data, status){

        if(status=="success"){
        var html="";
        $.each(data,function(i,order){
            html +=
            '<div class="col-sm-3">' +'<div class="col-item">' 
            +    '<img src="images/adons.jpg" alt="product" width="90%"/>'
            + '<h3>'+ order.pdName + '<span>'+ order.price +'</span>' + '</h3>' +'</div>'
            
            +    '<a id="add_cart" href="#" class="btn btn-info add_cart" onclick="addtoCart('+ order.productId +')">Add To Cart</a>'
                   +'<a id="show_details" href="../products/product/'+ order.productId +'" class="btn btn-info add_cart" >Detail</a>'
                    
                           + '</div>';
            });

            $("#product_list").html(html);
            
        }
    });
};