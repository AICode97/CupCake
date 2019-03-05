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
            ajaxPost(document.getElementById("changePasswordForm"));
        }
    });
});

function ajaxPost(form) {
    var url = form.action,
            xhr = new XMLHttpRequest();
    alert(form.elements)
    var params = [].filter.call(form.elements, function (el) {
        return typeof (el.checked) === 'undefined' || el.checked;
    }).filter(function (el) {
        return !!el.name;
    }) //Nameless elements die.
    .filter(function (el) {
        return el.disabled;
    }) //Disabled elements die.
    .map(function (el) {
        //Map each field into a name=value string, make sure to properly escape!
        return encodeURIComponent(el.name) + '=' + encodeURIComponent(el.value);
    }).join('&'); //Then join all the strings by &

    xhr.open("POST", url);
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhr.send(params);
}