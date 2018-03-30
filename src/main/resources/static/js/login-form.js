var $loginForm = $(".login");
if ($loginForm.length) {

    $(".login .head .exit").click(function () {
        $loginForm.fadeOut("fast");
    });

    $(".sign-in").click(function () {
        $loginForm.fadeToggle("fast");
    })
}
