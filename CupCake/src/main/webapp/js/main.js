$(document).ready(function() {
    
    // Validate Submit for Login Form
    $('#loginForm').submit( function(e) {
        $("#loginForm #errorBox").hide();
        if($("#loginForm input[name=username]").val() == "" 
                || $("#loginForm input[name=password]").val() == "") {
            e.preventDefault();
            $("#errorBox").html("Please fill out all fields");
            $("#errorBox").show();
        }
    });
    
    // Validate Submit for Register Form
     $('#registerForm').submit( function(e) {
        $("#registerForm #errorBox").hide();
        if($("#registerForm input[name=email]").val() == "" 
                || $("#registerForm input[name=username]").val() == ""
                || $("#registerForm input[name=password]").val() == "") {
            e.preventDefault();
            $("#errorBox").html("Please fill out all fields");
            $("#errorBox").show();
        }
    });
});