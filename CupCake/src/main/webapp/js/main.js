$(document).ready(function() {
    $('#loginForm').submit( function(e) {
        $("#errorBox").hide();
        if($("#loginForm input[name=username]").val() == "" || $("#loginForm input[name=password]").val() == "") {
            e.preventDefault();
            $("#errorBox").html("Please fill out all fields");
            $("#errorBox").show();
        }
    });
});