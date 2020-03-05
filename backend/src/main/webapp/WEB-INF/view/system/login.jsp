<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>
<html>
<head>
    <meta charset="UTF-8" />
    <title>账户登录</title>
    <meta name="renderer" content="webkit" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1" />
    <link rel="stylesheet" href="${path}/static/css/index.css" />
    <link rel="stylesheet" href="${path}/static/js/lib/layui/css/layui.css" />
</head>
<body class="login-warp">
<form class="layui-form" method="get" action="">
    <div class="login-box">
        <div class="layui-form-item">
            <label class="layui-form-label">账号：</label>
            <div class="layui-input-inline">
                <input type="text" name="useraccount" class="layui-input" lay-verify="useraccount" placeholder="请输入账号" autocomplete="on" />
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">密码：</label>
            <div class="layui-input-inline">
                <input type="password" name="password" lay-verify="pass" placeholder="请输入密码" autocomplete="off" maxlength="20" class="layui-input" />
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label"></label>
            <div class="layui-input-inline">
                <button class="layui-btn btn-submit" lay-submit="" lay-filter="login">立即登录</button>
            </div>
        </div>
    </div>
</form>
<script src="${path}/static/js/lib/layui/layui.js"></script>
<script>layui.use(["form","layer"],function(){var i=(layui.jquery,layui.form()),r=layui.layer;i.verify({useraccount:function(i){if(""==i)return"请输入用户名"},pass:function(i){if(""==i)return"请输入密码"}}),i.on("submit(login)",function(i){return r.alert(JSON.stringify(i.field),{title:"最终的提交信息"}),!1})})</script>
</body>
</html>