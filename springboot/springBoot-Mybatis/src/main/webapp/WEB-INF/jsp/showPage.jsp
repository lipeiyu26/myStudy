<%--
  Created by IntelliJ IDEA.
  User: 李佩玉
  Date: 2019/1/24
  Time: 16:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <a href="select" >查询</a>

    <form action="add" method="post">
       编号: <input type="text" name="id" id="id">
       姓名: <input type="text" name="name" id="name">
        年纪<input type="text" name="age" id="age">
        <input type="submit" value="提交">
    </form>
</body>

</html>
