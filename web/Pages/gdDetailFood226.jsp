<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="Model.Food226" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Food Details</title>
</head>
<body>
    <h1>Details of Food: ${food.name}</h1>
    <p><strong>Name:</strong> ${food.name}</p>
    <p><strong>Description:</strong> ${food.description}</p>
    <p><strong>Price:</strong> ${food.price}</p>
    
    <a href="${pageContext.request.contextPath}/food/list">Back to Food List</a>
</body>
</html>