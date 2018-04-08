document.addEventListener("DOMContentLoaded", function () {
    var elements = document.getElementsByTagName("INPUT");
    for (var i = 0; i < elements.length; i++) {
        if (elements[i] !== document.getElementById("confirm_password"))
            elements[i].oninput = function (event) {
                let target = event.target;
                let parent = target.parentElement;
                target.setCustomValidity("");
                if (!target.validity.valid) {
                    if (!parent.classList.contains("error")) parent.classList.add("error");
                } else {
                    if (parent.classList.contains("error")) parent.classList.remove("error");
                }
            };
    }

    var input_name = document.getElementById("name");
    input_name.oninvalid = function (event) {
        let target = event.target;
        let parent = target.parentElement;
        if (target.value.length === 0) {
            target.setCustomValidity("Username should not be empty!");
        } else {
            target.setCustomValidity("Username should contain only letters and be at least 3 and up to 15 characters long. ");
        }

        if (!target.validity.valid) {
            if (!parent.classList.contains("error")) parent.classList.add("error");
        }
    };

    var input_email = document.getElementById("email");
    input_email.oninvalid = function (event) {
        let target = event.target;
        let parent = target.parentElement;
        if (event.target.value.length === 0) {
            event.target.setCustomValidity("E-mail should not be empty!");
        } else {
            event.target.setCustomValidity("Please enter a valid e-mail address like: example@mail.com");
        }
        if (!target.validity.valid) {
            if (!parent.classList.contains("error")) parent.classList.add("error");
        }
    };

    var input_password = document.getElementById("password");
    input_password.oninvalid = function (event) {
        let target = event.target;
        let parent = target.parentElement;
        if (event.target.value.length === 0) {
            event.target.setCustomValidity("Password should not be empty!");
        } else {
            event.target.setCustomValidity("Password must be at least 8 characters, at least one letter and one number. ");
        }
        if (!target.validity.valid) {
            if (!parent.classList.contains("error")) parent.classList.add("error");
        }
    };


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
});
