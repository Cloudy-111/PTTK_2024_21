<%@ page import="java.util.List" %>
<%@ page import="Model.Supplier226" %>
<%@ page import="Model.Member226" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List Supplier</title>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/script_send_search_keyword_food.js"></script>
        <script src="${pageContext.request.contextPath}/js/script_send_idSupplier.js"></script>
        <script src="${pageContext.request.contextPath}/js/script_create_new_supplier.js"></script>
    </head>
    <body>
        <p><strong>Full Name:</strong> ${mem.getFullname()}</p>
        <p><strong>Id:</strong> ${mem.getId()}</p>
        <div id="memberInfo" data-id="${mem.getId()}"></div>
        <h1>Danh sach Supplier</h1>
        <div class="search_section">
            <h2>Search Supplier</h2>
            <form class="form_search" id="search_supplier">
                <input id="keyword_search" placeholder="Search" />
                <button type="submit">SEARCH</button>
            </form>
        </div>


        <div class="list">
            <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Phone</th>
                    <th>Email</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${empty suppList}">
                        <tr>
                            <td colspan="5">None of supplier show.</td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="supp" items="${suppList}">
                            <tr>
                                <td>${supp.getId()}</td>
                                <td>${supp.getName()}</td>
                                <td>${supp.getEmail()}</td>
                                <td>${supp.getPhone()}</td>
                                <td><a href="#" type="button" class="choose_supplier" data-id="${supp.getId()}">Choose</a></td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </tbody>
            </table>
        </div>
    
    </body>
</html>
