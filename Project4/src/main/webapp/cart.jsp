<%@page import="com.s2020iae.project4.Product"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="components/head.jsp" />
<jsp:include page="components/header.jsp" />
    <div class="main">
        <div class="content">
            <h1>Cart</h1>
            <% if(request.getAttribute("isEmpty") == "yes") { %>
                <h3 class="all-align">Your cart is empty</h3>
            <% } else { %>
    	    <table id="cartTable" border="1" width="100%" class="all-align">
                <tr>
                    <th>id</th>
                    <th>name</th>
                    <th>thumbnail</th>
                    <th>category</th>
                    <th>price</th>
                    <th>summary</th>
                </tr>
                    <%ArrayList<Product> tracks =
                        (ArrayList<Product>)request.getAttribute("cartData");
                        for(Product p:tracks){
                    %>
                    <tr>
                        <td><%=p.getId()%></td>
                        <td><%=p.getName()%></td>
                        <td><img src="./assets/<%=p.getThumbnail()%>" height="75"></td>
                        <td><%=p.getCategory()%></td>
                        <td><%=p.getPrice()%></td>
                        <td><%=p.getSummary()%></td>
                    </tr>
                    <%}%>
    	    </table>
            <br>
            <% Object total = request.getAttribute("subTotal");%>
            <% Object numOfItems = request.getAttribute("numOfItems");%>
            <p class="text-right">Subtotal (<%=numOfItems%> items): <%=total%></p>
            <p class="text-right"><a href="./checkout" class="addtocart">Checkout</a></p>
            <% } %>
        </div>
        <br>

    </div>
<jsp:include page="components/footer.jsp" />