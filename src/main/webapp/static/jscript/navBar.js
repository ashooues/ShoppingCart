
function searching(){

    var string = $("#search").val();


    $.get("http://localhost:8084/Ashish/products/search/"+ string, function(data, status){

        if(status=="success"){
        var html="";
        $.each(data,function(i,order){
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
};

function signoutfn(){
  window.location.href="/Ashish/static/login.html";
  $.ajax({
  url:"/Ashish/logout",
  method: "GET",
          success : function(response){
            
            alert("Logout Successfully");
          },
          error : function(response){
            alert("Failure in Logging out");
        }
        });


}

function showCateg(category)
{
   window.location.href="/Ashish/static/category.html?id="+category;
}

$(document).keypress(function(e) {
      if(e.which == 13) {
          searching();
      }
  });

$(document).ready(function()  {
  setCartSize();
});
function setCartSize(){
  $.get("/Ashish/cart/getCart", function(data, status){
    $("#item-number").text(data.cartItems.length);
  });
} 