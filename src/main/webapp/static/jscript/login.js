
$(document).on('click', '#login', function(){
    // alert("test1");
    // signNow();
    test();
});

function test(){

    var password = $("#password").val();
    var emailId = $("#emailId").val();
    $.ajax({
        url:"/Ashish/login",
        method:"POST",
        headers:{
            "content-type":"application/json",
        },
        data: '{"emailId":"'+emailId+'","password":"'+password+'"}',
        success : function(response){
            window.location.replace("home.html");
 //           alert("success1");
          },
          error : function(response){
            
            window.location.reload();
            alert("Error either in Email or Password");
        }
    });


    alert("Success");
}
