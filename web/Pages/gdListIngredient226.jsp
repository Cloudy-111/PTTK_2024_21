<%@ page import="java.util.List" %>
<%@ page import="Model.Supplier226" %>
<%@ page import="Model.PurchaseBill226" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List Ingredient</title>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/script_send_search_keyword_food.js"></script>
        <script src="${pageContext.request.contextPath}/js/script_create_new_ingredient.js"></script>
        <script src="${pageContext.request.contextPath}/js/script_insert_ingredient_to_purchasebill.js"></script>
        <script src="${pageContext.request.contextPath}/js/script_show_confirm_purchase_bill.js"></script>
    </head>
    <body>
        <p>Purchase Bill: ${purchaseBill.id}</p>
        <p>Supplier: ${purchaseBill.idSupplier}</p>
        
        <form action="purchasebill_confirm" method="GET" id="showBillForm">
            <input type="hidden" name="idPurchaseBill" id="idPurchaseBill" value="${purchaseBill.id}" />
            <button type="submit" id="show_bill_btn" data-id="${purchaseBill.id}">SHOW PURCHASE BILL</button>
        </form>
        
        <div id="quantity_form" style="display:none; margin-top: 20px;">
            <h2>Enter Quantity</h2>
            <form id="ingredient_quantity_form">
                <input type="hidden" id="ingredient_id" />
                <input type="hidden" id="purchase_bill_id" value="${purchaseBill.id}" />
                <label for="quantity">Quantity:</label>
                <input type="number" id="quantity" required min="1" />
                <button type="submit">Submit</button>
                <button type="button" id="cancel">Cancel</button>
            </form>
        </div>
        
        <h1>Danh sach Nguyen Lieu</h1>
        <div class="search_section">
            <h2>Search Ingredient</h2>
            <form class="form_search" id="search_ingredient">
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
                    <th>Description</th>
                    <th>Price</th>
                    <th>Remain</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${empty ingList}">
                        <tr>
                            <td colspan="5">None of ingredient show.</td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="ing" items="${ingList}">
                            <tr>
                                <td>${ing.getId()}</td>
                                <td>${ing.getName()}</td>
                                <td>${ing.getDescription()}</td>
                                <td>${ing.getPrice()}</td>
                                <td>${ing.getRemain()}</td>
                                <td><a href="#" type="button" class="choose_ingredient" data-id="${ing.getId()}">Choose</a></td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
        </div>
    </body>
</html>
