var isMenuVisible = false;
var $menu = $("main .menu");
if ($(document).width() < 1024) {
    $menu.hide();
}

$(document).ready(function () {
    $(document).click(function (event) {
        let $icon = $("header .current-user #user-name i");
        if (!$(event.target).closest('header .current-user').length) {
            if ($('header .current-user .user-menu').is(":visible")) {
                $('header .current-user .user-menu').slideToggle(100, function () {
                    $icon.toggleClass("fa-caret-down");
                    $icon.toggleClass("fa-caret-up");
                    $('header .current-user #user-name').toggleClass("active");
                });
            }
        }

        if (!$(event.target).closest('main .menu').length && !$(event.target).closest('header .menu-button').length) {
            if (isMenuVisible) {
                isMenuVisible = !isMenuVisible;

                $('main .menu').slideToggle({
                    duration: 250,
                    queue: true
                });
            }
        }
    })

    var $userButton = $("header .current-user #user-name");
    $userButton.click(function (event) {
        let $menu = $(this).parent().children(".user-menu");
        let $icon = $(this).children("i");
        if ($menu.is(":visible")) {
            $menu.slideToggle(100, function () {
                $icon.toggleClass("fa-caret-down");
                $icon.toggleClass("fa-caret-up");
                $('header .current-user #user-name').toggleClass("active");
            });
        } else {
            $(this).toggleClass("active");
            $menu.slideToggle(250);
            $icon.toggleClass("fa-caret-down");
            $icon.toggleClass("fa-caret-up");
        }
    })

    var $navButton = $("header .menu-button");
    $navButton.click(function () {
        isMenuVisible = !isMenuVisible;

        $menu.slideToggle({
            duration: 250,
            queue: true
        });
    })

    $(window).resize(function () {
        if ($(document).width() >= 1024) {
            $menu.show();
        }else{
            $menu.hide();
        }
    })
});
