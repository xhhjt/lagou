<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 2020/6/18
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<a href="/account/edit">新增</a>

<table>
    <tr>
        <th>卡号</th>
        <th>姓名</th>
        <th>金额</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${list}" varStatus="i" var="item">
        <tr>
            <td>${item.cardNo}</td>
            <td>${item.name}</td>
            <td>${item.money}</td>
            <td><a href="/account/edit?cardNo=" +#{item.cardNo}>修改</a> <a href="/account/delete?cardNo="
                                                                          +${item.cardNo}>删除</a></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
