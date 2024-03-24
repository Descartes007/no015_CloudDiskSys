<%--
  Created by IntelliJ IDEA.
  User: tang
  Date: 1/2/18
  Time: 11:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@include file="common/taglib.jsp"%>
<html>
<head>
  <%@ include file="common/head.jsp"%>
  <title>文件分享</title>
</head>
<body>
<div class="container">
  <div class="row">
    <div class="col-md-offset-3 col-md-6">
      <div class="panel panel-default">
        <div class="panel-heading">
          <h3 class="text-center">文件分享</h3>
        </div>
        <div class="panel-body">
          <table class="table table-bordered text-center col-sm-4 ">
            <tr>
              <td><label for="shareCode">分享码:</label></td>
              <td><input type="text" id="shareCode" autocomplete="off"></td>
            </tr>
            <tr>
              <td colspan="2">
                <button class="btn btn-primary" id="btnFindFile">提取文件</button>
              </td>
            </tr>
          </table>
          <div class="col-sm-12 table-bordered" id="fileBox" style="margin-bottom: 15px;padding-bottom: 15px" hidden>
            <h3 class="col-lg-12 text-center" id="fileName"></h3>
            <h4 class="col-lg-12 text-center" id="ownName"></h4>
            <a href="" class="btn btn-primary col-lg-offset-5 col-lg-2" id="fileDownload">文件下载</a>
          </div>
          <a href="${basePath}/login" class="btn btn-default col-sm-offset-5 col-sm-2">用户登录</a>
        </div>
      </div>
    </div>

  </div>
</div>
<script>
    $("#btnFindFile").click(function () {
        let shareCode = $("#shareCode").val();
        $.post({
            url: "${pageContext.request.contextPath}/shareFile/" + shareCode,
            success: (shareFileInfo) => {
                console.log(shareFileInfo);
                $("#fileBox").removeAttr("hidden");
                $("#fileName").text(shareFileInfo['fileName']);
                $("#ownName").text(shareFileInfo['ownName']);
                $("#fileDownload").attr("href","${basePath}/shareFile/" + shareFileInfo['shareCode']+"/download");
            },
            error: () => {
                $("#fileBox").attr("hidden", "");
                $("#fileName").text("");
                $("#ownName").text("");
                $("#fileDownload").attr("href", "#");
                alert("未查询到次分享码");
            }
        });
    })
</script>
</body>
</html>
