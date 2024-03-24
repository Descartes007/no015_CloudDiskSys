<%--
  Created by IntelliJ IDEA.
  User: tang
  Date: 12/11/17
  Time: 6:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ include file="common/taglib.jsp" %>
<html>
<head>
  <%@ include file="common/head.jsp" %>
  <title>Title</title>
</head>
<body>
<div class="navbar navbar-default">
  <div class="container">
    <div class="navbar-default col-md-12">
      <p class="navbar-text h3">Cloud</p>
      <div class="navbar-right">
        <a href="#" class="navbar-link link">
          <p class="navbar-text h3">${userFileInfo.userName}</p>
        </a>
        <a href="${basePath}/file/cancel" class="btn btn-default navbar-btn">退出登录</a>
      </div>
    </div>
  </div>
</div>
<div class="container">
  <div class="row">
    <div class="panel panel-default" id="userCloudInfo">
      <div class="panel-heading">
        <p class="h3 col-md-offset-2">${user.username}</p>
      </div>
      <div class="panel-body">
        <div class="col-md-4 col-md-offset-1">
          <p class="h3 text-center">可用容量：<fmt:formatNumber pattern="##.##"
                                                           value="${files.filesSize/1024/1024}"
                                                           minFractionDigits="2"/> / <fmt:formatNumber pattern="##.##"
                                                                                                       value="${user.fileMaxSize/1024/1024}"/>
            MB</p>
          <div class="progress">
            <div class="progress-bar" aria-valuemin="0" aria-valuemax="100"
                 style="width: ${files.filesSize/user.fileMaxSize * 100}%;">
            </div>
          </div>
        </div>
        <div class="col-md-2 text-center" style="height: inherit">
          <button class="btn btn-primary" data-toggle="modal" data-target="#myModal">上传文件</button>
        </div>
        <div class="col-md-4">
          <p class="h3 text-center">文件数量：${files.filesCount} / ${user.fileMaxCount}</p>
          <div class="progress">
            <div class="progress-bar" aria-valuenow="30" aria-valuemin="0" aria-valuemax="100"
                 style="width: ${files.filesCount/user.fileMaxCount * 100}%;">
            </div>
          </div>
        </div>
      </div>
    </div>
    <table class="table table-bordered text-center">
      <tr>
        <th class="col-md-1 text-center">#</th>
        <th class="col-md-3 text-center">文件名</th>
        <th class="col-md-1 text-center">文件大小</th>
        <th class="col-md-2 text-center">上传日期</th>
        <th class="col-md-1 text-center">分享</th>
        <th class="col-md-4 text-center">操作</th>
      </tr>
      <%
        int a = 0;
      %>
      <c:forEach items="${files.uploadFiles}" var="fileInfo">
        <tr>
          <td><%=++a%>
          </td>
          <td>${fileInfo.fileName}</td>
          <td><fmt:formatNumber value="${fileInfo.fileSize/1024/1024}" pattern="##.##MB" minFractionDigits="2"/></td>
          <td><fmt:formatDate value="${fileInfo.uploadDate}" type="both" pattern="yyyy-MM-dd HH:mm"/></td>
          <td>${fileInfo.sharing?fileInfo.shareCode:"无分享"}</td>
          <td>
            <button class="btn btn-default shareBtn" value="${fileInfo.id}">${fileInfo.sharing?"取消分享":"分享"}</button>
            <a href="${pageContext.request.contextPath}/file/download/${fileInfo.id}" class="btn btn-default">下载</a>
            <a href="${pageContext.request.contextPath}/file/delete/${fileInfo.id}"
               class="btn btn-default deleteBtn">删除</a>
          </td>
        </tr>
      </c:forEach>
    </table>
    <%--<div class="col-md-offset-4 col-md-4">--%>
      <%--<nav aria-label="Page navigation">--%>
        <%--<ul class="pagination pagination-lg">--%>
          <%--<li>--%>
            <%--<a href="#" aria-label="Previous">--%>
              <%--<span aria-hidden="true">&laquo;</span>--%>
            <%--</a>--%>
          <%--</li>--%>
          <%--<li><a href="#">1</a></li>--%>
          <%--<li><a href="#">2</a></li>--%>
          <%--<li><a href="#">3</a></li>--%>
          <%--<li><a href="#">4</a></li>--%>
          <%--<li><a href="#">5</a></li>--%>
          <%--<li>--%>
            <%--<a href="#" aria-label="Next">--%>
              <%--<span aria-hidden="true">&raquo;</span>--%>
            <%--</a>--%>
          <%--</li>--%>
        <%--</ul>--%>
      <%--</nav>--%>
    <%--</div>--%>
  </div>
  <div class="modal fade" tabindex="-1" role="dialog" data-toggle="myModal" id="myModal">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <b class="modal-title h4">文件上传</b>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><b>&times;</b></button>
        </div>
        <div class="modal-body">
          <form enctype="multipart/form-data" id="fileUploadForm">
            <div class="form-group">
              <label for="fileUpload">
                文件上传
              </label>
              <input type="file" id="fileUpload" name="file">
            </div>
            <div class="progress" id="divProgress" hidden="">
              <div class="progress-bar" id="fileProgress" aria-valuemin="0" aria-valuemax="100"
                   style="width:0%;min-width: 5%">
                <b></b>
              </div>
            </div>
            <div class="form-group">
              <button class="btn btn-primary" id="upload" disabled="disabled">文件上传</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>
<script>
    $("#fileUploadForm").submit(() => {
        let date = new FormData($("#fileUploadForm")[0]);
        $("#upload").attr('disabled', true);
        $("#divProgress").removeAttr("hidden");
        $.ajax({
            type: "post",
            url: "${basePath}/file/upload",
            data: date,
            cache: false,
            processData: false,
            contentType: false,
            xhr: xhr_provider,
            success: (msg) => {
                alert(msg);
                window.location.reload();
            },
            error: () => {
                alert("upload fail");
                window.location.reload();
            }
        });
        return false;
    });

    $("#fileUpload").change(function () {
        let upload = $("#upload");
        if ($(this).val() !== "") {
            upload.attr('disabled', false);
        } else {
            upload.attr('disabled', true);
        }
    });


    $("button.shareBtn").each(function () {
        if ($(this).text() === "分享") {
            $(this).bind("click", function () {
                shareAjax($(this).val());
            });
        } else {
            $(this).bind("click", function () {
                unshareAjax($(this).val());
            });
        }
    });

    $("a.deleteBtn").each(function () {
        $(this).click(() => {
            let r = confirm("是否确认删除文件?");
            return r === true;
        });
    });

    function unshareAjax(fileId) {
        $.post({
            url: "${basePath}/file/unShareFile",
            data: {fileIdStr: fileId},
            dataType: "text",
            success: function (msg) {
                alert(msg);
                window.location.reload();
            },
            error: function () {
                alert("取消失败");
                window.location.reload();
            }
        })
    }

    function shareAjax(fileId) {
        $.post({
            url: "${basePath}/file/shareFile",
            data: {fileIdStr: fileId},
            dataType: "text",
            success: function (msg) {
                console.log(msg);
                alert(msg);
                window.location.reload();
            },
            error: function () {
                alert("分享失败");
                window.location.reload();
            }
        });
    }

    function onProgress(evt) {
        let progress = $("#fileProgress");
        if (evt.lengthComputable) {
            let completePercent = Math.round(evt.loaded / evt.total * 100);
            progress.width(completePercent + "%");
            progress.find("b").text(completePercent + "%");
        }
    }

    let xhr_provider = function () {
        let xhr = jQuery.ajaxSettings.xhr();
        if (onProgress && xhr.upload) {
            xhr.upload.addEventListener('progress', onProgress, false);
        }
        return xhr;
    };
</script>
</body>
</html>
