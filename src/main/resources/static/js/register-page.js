

$("main .background .cover").css("opacity", "0");

$(".register-form").hover(function () {
    $("footer").css("opacity", "1");
    $("main .background .cover").css("opacity", "0.5");
}, function () {
    $("footer").css("opacity", "0.8");
    $("main .background .cover").css("opacity", "0");
})


document.addEventListener("DOMContentLoaded", function () {
    var elements = document.getElementsByTagName("INPUT");
    for (var i = 0; i < elements.length; i++) {
        if (elements[i] !== document.getElementById("confirm_password"))
            elements[i].oninvalid = function (e) {
                e.target.setCustomValidity("");
                if (!e.target.validity.valid) {
                    e.target.setCustomValidity("This field cannot be left blank");
                }
            };
        elements[i].oninput = function (e) {
            e.target.setCustomValidity("");
        };
    }
})

var password = document.getElementById("password")
    , confirm_password = document.getElementById("confirm_password");

function validatePassword() {
    if (password.value !== confirm_password.value) {
        confirm_password.setCustomValidity("Passwords Don't Match");
    } else {
        confirm_password.setCustomValidity('');
    }
}

password.onchange = validatePassword;
confirm_password.onkeyup = validatePassword;
