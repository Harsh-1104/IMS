function getCookie(cookieName) {
    var name = cookieName + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) === ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) === 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

function InternalServerError() {
    swal({
        title: 'Internal server error',
        text: 'Site is under maintainance,Try againg later',
        imageUrl: "/assets/images/error500.png",
        timer: 3500,
        showConfirmButton: false,
        showCancelButton: false
    });
}

function InvalidUser() {
    document.cookie = "apikey=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
    location.href = "/signin";
}

function userNotFound(iid) {
    swal({
        title: `Try to Re-Connect`,
        html: `<span class="text-danger">
                    May be, You are disconnected from instance
                <span>`,
        type: 'error',
        timer: 2000,
        showConfirmButton: false
    }).then(() => {
        $.ajax({
            url: "/updateData",
            method: "PUT",
            data: {
                "table": "instance",
                "paramstr": `isActive = 0`,
                "condition": `instance_id = '${iid}'`
            },
            success: function (val) {
                switch (val.status_code) {
                    case '400':
                    case '401': {
                        InvalidUser();
                        break;
                    }

                    case '404':
                    case '500': {
                        InternalServerError();
                        break;
                    }

                    case '200': {
                        $("#disconnect, #scanqr").addClass("d-none");
                        $("#scanqr").removeClass("d-none");
                        break;
                    }
                }
            }
        });
    });
}

function chkLogin() {
    const apikey = getCookie("apikey");
    if (apikey === '' || apikey === null) {
        InvalidUser();
    }
    $.ajax({
        url: "/getData",
        method: "POST",
        data: {
            obj: {
                table: `users`,
                paramstr: true
            }
        },
        success: function (val) {
            switch (val.status_code) {
                case '401':
                case '404': {
                    InvalidUser();
                    break;
                }

                case '500': {
                    InternalServerError();
                    break;
                }
            }
        }
    });
}

function getCheckedCheckboxesFor(checkboxName) {
    var checkboxes = document.querySelectorAll(`input[name="${checkboxName}"]:checked`), values = [];
    Array.prototype.forEach.call(checkboxes, function (el) {
        values.push(el.value);
    });
    return values;
}

function enterKeyEvent(tboxid, clickbtnid) {
    $(`#${tboxid}`).keypress((event) => {
        var keycode = (event.keyCode ? event.keyCode : event.which);
        if (keycode === '13') {
            $(`#${clickbtnid}`).click();
        }
    });
}

function userinfo() {
    const apikey = getCookie("apikey");
    $.ajax({
        url: "/getData",
        method: "POST",
        data: {
            obj: {
                table: `users`,
                paramstr: true
            }
        },
        success: function (val) {
            switch (val.status_code) {
                case '401':
                case '404': {
                    InvalidUser();
                    break;
                }

                case '500': {
                    InternalServerError();
                    break;
                }

                default: {
                    let Obj = {};
                    for (let i = 0; i < val.length; i++) {
                        Object.assign(Obj, val[i]);
                    }
                    if (val.length === 1) {
                        if (Obj.image === null || Obj.image === "") {
                            $('.profileimg').attr('src', `../assets/images/users/user-dummy-img.jpg`);
                        }
                        else {
                            if ((Obj.image).startsWith('http') || (Obj.image).startsWith('https')) {
                                $('.profileimg').attr('src', `${Obj.image}`);
                            }
                            else {
                                $('.profileimg').attr('src', `../assets/upload/profile/${apikey}/${Obj.image}`);
                            }
                        }

                        $('.profilename').html(Obj.uname);
                    }
                }
            }
        }
    });
}

function focusEvent(sourceid, targetid) {
    $(`#${sourceid}`).keypress((event) => {
        var keycode = (event.keyCode ? event.keyCode : event.which);
        if (keycode === '13') {
            $(`#${targetid}`).focus();
        }
    });
}

function copy_api(id) {
    var copyText = document.getElementById(id);
    // var copyText = $('#' + id);

    // copyText.select();
    // copyText.setSelectionRange(0, 99999);
    navigator.clipboard.writeText(copyText.value || copyText.textContent);
}

$(document).ready(function () {
    $(document).on("click", ".copy", function () {
        var param = $(this).attr("id").substring(5);
        copy_api(param);
    });

    let page = document.URL.split("/");

    sessionStorage.setItem(`data-preloader`, `enable`);

    $(document).on('click', '#logout', function () {
        document.cookie = "apikey=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
        sessionStorage.removeItem("apikey");
        sessionStorage.removeItem("iid");
        location.href = "/IMSAPP/login.jsf";
    });

    $(document).on('click', '.light-dark-mode', () => {
        // $('.logo-light,.logo-dark').addClass('d-none');
        if (sessionStorage.getItem("data-layout-mode") === "dark") {
            // $('.logo-dark').removeClass('d-none');
            sessionStorage.setItem("data-layout-mode", "light");
        } else {
            // $('.logo-light').removeClass('d-none');
            sessionStorage.setItem("data-layout-mode", "dark");
        }
    });
    $(document).on('click', '#topnav-hamburger-icon', () => {
        if (sessionStorage.getItem("data-sidebar-size") === "lg") {
            sessionStorage.setItem("data-sidebar-size", "sm");
        } else {
            sessionStorage.setItem("data-sidebar-size", "lg");
        }
    });
});