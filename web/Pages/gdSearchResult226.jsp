<%@ page import="java.util.List" %>
<%@ page import="Model.Food226" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Food List</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/script_send_search_keyword_food.js"></script>
</head>
<body>
    <h1>Danh sach Food</h1>
    <div class="search_section">
        <h2>Search Detail Food</h2>
        <form class="form_search" id="search_food">
            <input id="keyword_search" placeholder="Search" />
            <button type="submit">SEARCH</button>
        </form>
    </div>
    
    
    <div class="list_food">
        <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Price</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:choose>
                <c:when test="${empty foodList}">
                    <tr>
                        <td colspan="5">None of grocery show.</td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <c:forEach var="food" items="${foodList}">
                        <tr>
                            <td>${food.id}</td>
                            <td>${food.name}</td>
                            <td>${food.price}</td>
                            <td><a href="${pageContext.request.contextPath}/food/details/${food.id}">Details</a></td>
                        </tr>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </tbody>
    </table>
    </div>
    
</body>
</html>
