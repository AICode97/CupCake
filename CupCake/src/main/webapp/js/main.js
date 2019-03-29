$(document).ready(function () {

    // Validate Submit for Login Form
    $('#loginForm').submit(function (e) {
        $(this).find("#errorBox").hide();
        if ($(this).find("input[name=username]").val() == ""
                || $(this).find("input[name=password]").val() == "") {
            e.preventDefault();
            $(this).find("#errorBox").html("Please fill out all fields");
            $(this).find("#errorBox").show();
        } else {
            e.preventDefault();
            ajax($(this));
        }
    });

    // Validate Submit for Register Form
    $('#registerForm').submit(function (e) {
        $(this).find("#registerForm #errorBox").hide();
        if ($(this).find("#registerForm input[name=email]").val() == ""
                || $(this).find("#registerForm input[name=username]").val() == ""
                || $(this).find("#registerForm input[name=password]").val() == "") {
            e.preventDefault();
            $(this).find("#errorBox").html("Please fill out all fields");
            $(this).find("#errorBox").show();
        } else {
            e.preventDefault();
            ajax($(this));
        }
    });

    // Validate Submit for Change Password Form
    $('#changePasswordForm').submit(function (e) {
        $(this).find("#changePasswordForm #errorBox").hide();
        if ($(this).find("#changePasswordForm input[name=currentPassword]").val() == ""
                || $(this).find("#changePasswordForm input[name=newPassword]").val() == ""
                || $(this).find("#changePasswordForm input[name=newPassword2]").val() == "") {
            e.preventDefault();
            $(this).find("#errorBox").html("Please fill out all fields");
            $(this).find("#errorBox").show();
        } else {
            e.preventDefault();
            ajax($(this));
        }
    });
    
    if($(this).find("#totalPrice").length > 0) {
        if(parseInt($(this).find("#totalPrice").html()) > parseInt($(this).find("#balance").html())) {
            $(this).find(".checkoutBox button").attr("disabled", "");
            $(this).find("#balanceError").show();
        }
    }
});

// Ajax Command
function ajax(formObj) {
    $.ajax({
        url: $(formObj).find('button').attr('formaction'),
        data: $(formObj).serialize()
    }).done(function (data, textStatus, request) {
        if (request.getResponseHeader('error') !== null) {
            $("#" + formObj.attr("id") + " #errorBox").html(request.getResponseHeader('error'));
            $("#" + formObj.attr("id") + " #errorBox").show();
            $("#" + formObj.attr("id") + " #successBox").hide();
        } else {
            if (request.getResponseHeader('redirect') !== null) {
                window.location = request.getResponseHeader('redirect');
            } else if(request.getResponseHeader('success') !== null) {
                $("#" + formObj.attr("id") + " #successBox").html(request.getResponseHeader('success'));
                $("#" + formObj.attr("id") + " #successBox").show();
                $("#" + formObj.attr("id") + " #errorBox").hide();
            }
        }
    });
}