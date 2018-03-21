$(document).ready(function(){
    $("#login").click(function(){
    	loginNow();
	});

	$(document).keypress(function(e) {
	    if(e.which == 13) {
	        loginNow();
	    }
	});

	function loginNow(){
	alert("here");
    	var password = $("#password").val();
    	var enc_password = password;
		$.ajax({
		  url: "/Ashish/login",
		  method: "POST",
		  headers: {
		    "content-type": "application/json",
		  },
		  data: "{\"emailId\" : \""+$("#emailId").val()+"\",\"password\" : \""+enc_password+"\"}",
		  statusCode: {
				200: function (response) {
					window.location.replace("home.html");
				},
				401: function (response) {
					// alert("{\"email\" : \""+$("#email").val()+"\",\"password\" : \""+$("#password").val()+"\"}",);
				 	alert('Bad Credentials');
				}
		  }
		});
	}

});

