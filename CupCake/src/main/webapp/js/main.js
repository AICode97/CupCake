$(document).ready(function () {

    // Validate Submit for Login Form
    $('#loginForm').submit(function (e) {
        $("#" + this.id + " #errorBox").hide();
        if ($("#" + this.id + " input[name=username]").val() == ""
                || $("#" + this.id + " input[name=password]").val() == "") {
            e.preventDefault();
            $("#" + this.id + " #errorBox").html("Please fill out all fields");
            $("#" + this.id + " #errorBox").show();
        } else {
            e.preventDefault();
            ajax($(this));
        }
    });

    // Validate Submit for Register Form
    $('#registerForm').submit(function (e) {
        $("#" + this.id + " #registerForm #errorBox").hide();
        if ($("#" + this.id + " #registerForm input[name=email]").val() == ""
                || $("#" + this.id + " #registerForm input[name=username]").val() == ""
                || $("#" + this.id + " #registerForm input[name=password]").val() == "") {
            e.preventDefault();
            $("#" + this.id + " #errorBox").html("Please fill out all fields");
            $("#" + this.id + " #errorBox").show();
        } else {
            e.preventDefault();
            ajax($(this));
        }
    });

    // Validate Submit for Change Password Form
    $('#changePasswordForm').submit(function (e) {
        $("#" + this.id + " #changePasswordForm #errorBox").hide();
        if ($("#" + this.id + " #changePasswordForm input[name=currentPassword]").val() == ""
                || $("#" + this.id + " #changePasswordForm input[name=newPassword]").val() == ""
                || $("#" + this.id + " #changePasswordForm input[name=newPassword2]").val() == "") {
            e.preventDefault();
            $("#" + this.id + " #errorBox").html("Please fill out all fields");
            $("#" + this.id + " #errorBox").show();
        } else {
            e.preventDefault();
            ajax($(this));
        }
    });
    
    if($("#" + this.id + " #totalPrice").length > 0) {
        if(parseInt($("#" + this.id + " #totalPrice").html()) > parseInt($("#" + this.id + " #balance").html())) {
            $("#" + this.id + " .checkoutBox button").attr("disabled", "");
            $("#" + this.id + " #balanceError").show();
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