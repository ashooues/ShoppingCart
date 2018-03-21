                $(document)
                        .ready(
                                function() {

                                    var url = window.location.href;
                                    var id = url.split("/product")[1]
                                    var settings = {
                                        "async" : true,
                                        "crossDomain" : true,
                                        "url" : "../products/getById/"
                                                + id,
                                        "method" : "GET",
                                        "headers" : {
                                            "content-type" : "application/json",
                                            "cache-control" : "no-cache",
                                            "postman-token" : "314cc715-c098-e904-50c7-85380627c78d"
                                        },
                                        "processData" : false,
                                        "data" : "\n\t{\n\t\"brandE\":[\"samsung\", \"apple\"],\n\t\"specifications\":[\"4 GB RAM\", \"2 GB RAM\"]\n\t}"
                                    }

                                    $.ajax(settings).done(function(response) {
                                        var pro = '';
                                        var det = response.details.split("|");
                                        pro += '<div class="col-lg-5 col-md-5 col-sm-6 col-xl-6" ><div class="g-box"    style="background-image: url(../static/images/'
                                                + response.href
                                                +'.jpeg);"></div></div><div class="col-lg-1 col-md-5"></div><div class="col-lg-5 col-md-5 col-sm-6 col-xl-6"><div><h1>'
                                                + response.name
                                                +'</h1><h3>Rs. '
                                                + response.price
                                                +'</h3><h3>details :</h3>';
                                                
                                                for(i = 0; i<det.length; i++){
                                                    pro += '<h5>'+det[i] + '</h5>';
                                                    
                                                }
                                                pro += '<br>';
                                                pro += '<div name="add" style="width: 35%; background-color:#254e58;" id="add" onclick="add('
                                                        + response.productId
                                                        + ')" class="btn btn-info btn-block" ><a style="color: white; text-decoration: none;">Add to Cart</a></div>';
                                                
                                                pro += '</div></div>';
                                                
                                                    document.getElementById("some").innerHTML = pro;

                                    });
                                    $
                                    .ajax({
                                        url : "../getProfile",
                                        method : "GET"
                                    })
                                    .done(
                                            function(res) {
                                                //alert(res); 
                                                var respon = JSON
                                                        .stringify(res);
                                                //alert(respon);
                                                if (respon == '"anonymousUser"') {
                                                    //alert("home");
                                                    var signin = '<a href = "signin" style = "margin-top :16px; margin-right:10px; padding:inherit;">Signin</a>';
                                                    $('#check').html(signin);

                                                } else {

                                                    var loginB = '<li class="dropdown" style = "margin-top :16px; margin-right:10px; color: white;"><a style= "color:white; text-decoration : none;" class="dropdown-toggle" data-toggle="dropdown" role="button"'
                                            + 'aria-expanded="false" >'
                                                            + respon
                                                            + '<span class="caret"></span> </a> <ul class="dropdown-menu" role="menu">'
                                                            + '<li><a href="../profile">My Profile</a></li> <li><a href="../carts">My Cart</a></li> <li><a href="#">Something else here'
                                                            + '</a></li><li class="divider"></li><li><a href="logout">Logout</a></li></ul></li>';
                                                    $('#check').html(loginB);
                                                }
                                            });
                                    $(".dropdown").hover(
                                            function() {
                                                $('.dropdown-menu', this).not(
                                                        '.in .dropdown-menu').stop(
                                                        true, true).slideDown("400");
                                                $(this).toggleClass('open');
                                            },
                                            function() {
                                                $('.dropdown-menu', this).not(
                                                        '.in .dropdown-menu').stop(
                                                        true, true).slideUp("400");
                                                $(this).toggleClass('open');
                                            });
                                });


/*$(document).ready(function() {
    var url = window.location.href;
    var id = url.split("/product")[1]




    function showDetails(productId){

    $.get("/Ashish/products/getById/" + productId, function(data, status){
        if(status=="success"){
                        html +=
            '<div class="col-sm-3">' +'<div class="col-item">' 
            +    '<img src="image.png" alt="product" width="90%"/>'
            + '<h3>'+ data.pdName + '<span>'+ data.price +'</span>' + data.details + '<span>'+ data.categories +'</span>' + data.subCategories+'</h3>' +'</div>'
            
            +    '<a id="add_cart" href="#" class="btn btn-info add_cart" onclick="addtoCart('+ order.productId +')">Add To Cart</a>'
                            +  '<a id="show_details" class="btn btn-info" onclick="showDetails('+ order.productId +')">Detail</a>'
                          + '</div>';
                          
            $("#product_list").html(html);
                           /*
            $("#m_title").html(data.name);
            $("#m_detail").html(data.details);
            
        }

    });
}
});*/*/