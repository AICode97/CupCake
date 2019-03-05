$(document).ready(function () {

    // Validate Submit for Login Form
    $('#loginForm').submit(function (e) {
        $("#loginForm #errorBox").hide();
        if ($("#loginForm input[name=username]").val() == ""
                || $("#loginForm input[name=password]").val() == "") {
            e.preventDefault();
            $("#errorBox").html("Please fill out all fields");
            $("#errorBox").show();
        }
    });

    // Validate Submit for Register Form
    $('#registerForm').submit(function (e) {
        $("#registerForm #errorBox").hide();
        if ($("#registerForm input[name=email]").val() == ""
                || $("#registerForm input[name=username]").val() == ""
                || $("#registerForm input[name=password]").val() == "") {
            e.preventDefault();
            $("#errorBox").html("Please fill out all fields");
            $("#errorBox").show();
        } else {
            e.preventDefault();
            $.ajax({
                url: $(this).find('button').attr('formaction'),
                data: $(this).serialize()
            }).done(function (data) {
                if($(data).filter('#errorMessage').text() != "") {
                    $("#errorBox").html($(data).filter('#errorMessage').text());
                    $("#errorBox").show();
                    $("#successBox").hide();
                } else {
                    $("#successBox").html("Password has successfully been updated");
                    $("#successBox").show();
                    $("#errorBox").hide();
                }
            });
        }
    });

    // Validate Submit for Change Password Form
    $('#changePasswordForm').submit(function (e) {
        $("#changePasswordForm #errorBox").hide();
        if ($("#changePasswordForm input[name=currentPassword]").val() == ""
                || $("#changePasswordForm input[name=newPassword]").val() == ""
                || $("#changePasswordForm input[name=newPassword2]").val() == "") {
            e.preventDefault();
            $("#errorBox").html("Please fill out all fields");
            $("#errorBox").show();
        } else {
            e.preventDefault();
            ajax($(this));
        }
    });
});

function ajax(formObj) {
    $.ajax({
        url: $(formObj).find('button').attr('formaction'),
        data: $(formObj).serialize()
    }).done(function (data) {
        if($(data).filter('#errorMessage').text() != "") {
            $("#errorBox").html($(data).filter('#errorMessage').text());
            $("#errorBox").show();
            $("#successBox").hide();
        } else {
            $("#successBox").html("Password has successfully been updated");
            $("#successBox").show();
            $("#errorBox").hide();
        }
    });
}