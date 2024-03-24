$(() => {
    $("#userPwd").bind("input", function () {
        let str = $(this).val();
        let pwdInput = $("#pwdInput");
        let reUserPwd = $("#reUserPwd");
        let rePwdInput = $("#rePwdInput");
        let patten = /^[0-9A-Za-z]{5,10}$/;
        if (!patten.test(str)) {
            pwdInput.addClass("has-error");
            pwdInput.find(".errorMsg > label").text("密码长度5-10,仅可使用0-9,A-Z,a-z");
        } else {
            pwdInput.removeClass("has-error");
            pwdInput.find(".errorMsg > label").text("");
        }
        if (str !== reUserPwd.val()) {
            rePwdInput.addClass("has-error");
            rePwdInput.find(".errorMsg > label").text("两次密码不一致");
        }else {
            rePwdInput.removeClass("has-error");
            rePwdInput.find(".errorMsg > label").text("");
        }

    });
    $("#reUserPwd").bind("input", function () {
        let str = $(this).val();
        let pwdInput = $("#rePwdInput");
        let userPwdStr = $("#userPwd").val();
        let patten = /^[0-9A-Za-z]{5,10}$/;
        if (!patten.test(str)) {
            pwdInput.addClass("has-error");
            pwdInput.find(".errorMsg > label").text("密码长度5-10,仅可使用0-9,A-Z,a-z");
        } else if (str !== userPwdStr) {
            pwdInput.addClass("has-error");
            pwdInput.find(".errorMsg > label").text("两次密码不一致");
        } else {
            pwdInput.removeClass("has-error");
            pwdInput.find(".errorMsg > label").text("");
        }
    });
    $("form").submit(function () {
        let name = $("#userName");
        let pwd = $("#userPwd");
        let rePwd = $("#reUserPwd");
        let nameInput = $("#nameInput");
        // let pwdInput = $("#pwdInput");
        // let rePwdInput = $("#rePwdInput");
        let patten = /^[0-9A-Za-z]{5,10}$/;
        if ((name.val().length < 5 || name.val().length > 10) || !patten.test(name.val()) || nameInput.hasClass("has-error")) {
            alert("请填正确账号密码");
            return false;
        }
        if ((pwd.val().length < 5 || pwd.val().length > 10) || !patten.test(pwd.val())) {
            alert("请填正确账号密码");
            return false;
        }
        if ((rePwd.val().length < 5 || rePwd.val().length > 10) || !patten.test(rePwd.val()) || !(rePwd.val() === pwd.val())) {
            alert("请填正确账号密码");
            return false;
        }
        return true;
    });
});