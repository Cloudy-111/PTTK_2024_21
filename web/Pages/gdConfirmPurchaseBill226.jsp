<%@ page import="java.util.List" %>
<%@ page import="Model.Supplier226" %>
<%@ page import="Model.Member226" %>
<%@ page import="Model.PurchaseBill226" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirm Purchase Bill</title>
        <script>
            function redirectToMain() {
//                window.location.href = '/PTTK_2024_21/main_warehouser';
            }
        </script>
    </head>
    <body>
        <h1>Confirm Purchase Bill</h1>
        <div>
            <p>Ma Hoa Don: ${purchaseBill.getId()}</p>
            <p>Ngay tao: ${formattedCreateDate}</p>
            
            <p>Nhan vien thanh toan: ${mem.getFullname()}</p>
            <p>Nha cung cap: ${supplier.getName()}</p>
            
            <div class="list">
                <table border="1">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Description</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Total</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="pbi" items="${list_pbi}">
                            <c:set var="ing" value="${ingredientMap[pbi.getIdIngredient()]}" />
                                <tr>
                                    <td>${ing != null ? ing.getId() : 'N/A'}</td>
                                    <td>${ing != null ? ing.getName() : 'N/A'}</td>
                                    <td>${ing != null ? ing.getDescription() : 'N/A'}</td>
                                    <td>${ing != null ? ing.getPrice() : 'N/A'}</td>
                                    <td>${pbi.getAmountIngredient()}</td>
                                    <td>${totalMap[pbi.getIdIngredient()]}</td>
                                </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <p><strong>Total: ${Total}</strong></p>
        </div>
        <form action="export_purchasebill_pdf" method="get" onsubmit="setTimeout(redirectToMain, 1000);">
            <button type="submit" id="export_pdf_btn">Export Purchase Bill</button>
        </form>
        <button onclick="window.location.href='${pageContext.request.contextPath}/main_warehouser'">Return to main</button>
    </body>
</html>
