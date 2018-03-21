$(document).ready(function(){

    $.get("http://localhost:8084/Ashish/getProfile",function(data, status){

        

        if(status=="success"){
	        var html="";
		$.each(data,function(i,order){

        $.each(order.orderItems,function(i,orderItem){
            var quan = orderItem.quantity;
            var price = orderItem.product.price;
            var total = quan * price;
            var product =  orderItem.product.pdName;

               html += 