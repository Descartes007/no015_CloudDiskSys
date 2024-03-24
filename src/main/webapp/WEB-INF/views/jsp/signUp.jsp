<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@include file="common/taglib.jsp"%>
<html>
<head>
  <title>Login</title>
  <%@include file="common/head.jsp"%>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <script src="${basePathStatic}/common/signUp.js"></script>
</head>
<body>
<div class="container">
  <div class="row">
    <div id="loginBox" class="col-md-4 col-md-offset-4">
      <div class="panel panel-default">
        <div class="panel-heading">
          <h4 class="text-center">用户注册</h4>
        </div>
        <div class="panel-body">
          <form class="form-horizontal" action="${basePath}/signUp" method="post">
            <div class="form-group" id="nameInput">
              <label for="userName" class="control-label col-sm-3">用户名</label>
              <div class="col-sm-9">
                <input type="text" class="form-control" id="userName" name="username" autocomplete="off">
                <div class="errorMsg">
                  <label for="userName" class="control-label">${errorNameMsg}</label>
                </div>
              </div>
            </div>
            <div class="form-group" id="pwdInput">
              <label for="userPwd" class="control-label col-sm-3">密码</label>
              <div class="col-sm-9">
                <input type="password" class="form-control" id="userPwd" name="password" autocomplete="off">
                <div class="errorMsg">
                  <label for="userPwd" class="control-label">${errorPwdMsg}</label>
                </div>
              </div>
            </div>
            <div class="form-group" id="rePwdInput">
              <label for="userPwd" class="control-label col-sm-3">重复密码</label>
              <div class="col-sm-9">
                <input type="password" class="form-control" id="reUserPwd" name="reuserpwd" autocomplete="off">
                <div class="errorMsg">
                  <label for="userPwd" class="control-label">${errorMsg.pwd}</label>
                </div>
              </div>
            </div>
            <div class="form-group">
              <input class="btn btn-primary col-sm-offset-4 col-sm-4" type="submit" value="注册">
              <a href="${basePath}/login">登录</a>
            </div>
            <c:if test="${signUpError != null}">
              <div class="alert alert-danger">
                <p>${signUpError}</p>
              </div>
            </c:if>
            <c:if test="${signUpMsg != null}">
              <div class="alert alert-success">
                <p>${signUpMsg},请 <a href="${basePath}/login">登录</a></p>
              </div>
            </c:if>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>
<script>
    $("#userName").bind("input", function () {
        let valLength = $(this).val().length;
        if (valLength >= 5 && valLength <= 10) {
            $.post({
                url: "${basePath}/signUp/checkUserName",
                data: {userName: $(this).val()},
                success: (msg) => {
                    let nameInput = $("#nameInput");
                    if ("true" === msg['usable']) {
                        nameInput.removeClass("has-error");
                        nameInput.addClass("has-success");
                    } else {
                        nameInput.removeClass("has-success");
                        nameInput.addClass("has-error");
                    }
                    nameInput.find(".errorMsg>label").text(msg['msg']);
                },
                error: () => {
                    let nameInput = $("#nameInput");
                    nameInput.removeClass("has-error");
                    nameInput.removeClass("has-success");
                    nameInput.find(".errorMsg>label").text("");
                }

            });
        } else {
            let nameInput = $("#nameInput");
            nameInput.removeClass("has-success");
            nameInput.addClass("has-error");
            nameInput.find(".errorMsg>label").text("用户名长度在5-10之间");
        }
    });
</script>
<style>
  .container {
    display: table;
    height: 100%;
  }

  .row {
    display: table-cell;
    vertical-align: middle;
  }
</style>
</body>
</html>