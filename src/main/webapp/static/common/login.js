$(function () {
    $(".errorMsg > label").each((index, item) => {
        if ($(item).text().length !== 0) {
            $(item).parents("div.form-group").addClass("has-error");
        }
    });
    $("input#userName").bind("input",function () {
        let str = $(this).val();
        let parentNode = $("#nameInput");
        if (str === "" || str.length < 5 || str.length > 15) {
            parentNode.addClass("has-error");
        } else {
            parentNode.removeClass("has-error");
            parentNode.find("div > div.errorMsg > label").text("");
        }
    });
    $("input#userPwd").bind("input",function () {
        let str = $(this).val();
        let parentNode = $("#pwdInput");
        if (str === "" || str.length < 5 || str.length > 15) {
            parentNode.addClass("has-error");
        } else {
            parentNode.removeClass("has-error");
            parentNode.find("div > div.errorMsg > label").text("");
        }
    });
    $("form").submit(function () {
        let name = $("input#userName");
        let pwd = $("input#userPwd");
        let nameInput = $("#nameInput");
        let pwdInput = $("#pwdInput");
        if (name.val().length < 5 || name.val().length > 15) {
            alert("请填正确账号密码");
            nameInput.addClass("has-error");
            nameInput.find("div > div.errorMsg > label").text("用户名长度5-15");
            return false;
        }
        if (pwd.val().length < 5 || pwd.val().length > 15) {
            alert("请填正确账号密码");
            pwdInput.addClass("has-error");
            pwdInput.find("div > div.errorMsg > label").text("密码长度5-15");
            return false;
        }
        let divs = $("div.form-group.has-error");
        if (divs.length > 0) {
            alert("请填正确账号密码");
            return false;
        }
        return true;
    });
})
;