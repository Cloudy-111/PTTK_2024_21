<%@ page import="Model.Member226" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <a href="${pageContext.request.contextPath}/">Log out</a>
        <h1>Welcome, Warehouser!</h1>
        <p><strong>Full Name:</strong> ${mem.getFullname()}</p>
        <a type="button" href="${pageContext.request.contextPath}/list_supplier">Nhap hang</a>
    </body>
</html>
