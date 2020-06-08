<%@page import="com.s2020iae.project4.Product"%>
<%@page import="com.s2020iae.project4.Order"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.s2020iae.project4.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="components/head.jsp" />
<jsp:include page="components/header.jsp" />
<div class="main">
    <div class="container">
        <div class="content">
            <h1>Track your order</h1>
        </div>
        <div class="content confirmation">
            <div class="detail">
                <% String orderID = (String)request.getAttribute("orderID");%>
                <% String isExist = (String)request.getAttribute("isExist");%>
                <% if(isExist == "yes") { %>
                <% Order anOrder = (Order)request.getAttribute("OrderObject");%>
                    <h2>Order Information</h2>
                    <p>Order ID: <%=anOrder.getId()%></p>
                    <p>Email: <%=anOrder.getEmail()%></p>
                    <p>Phone Number: <%=anOrder.getPhone()%></p>
                    <p>Total Price: $<%=anOrder.getPrice()%></p>

                    <h2>Shipping Information</h2>
                    <p>First Name: <%=anOrder.getFirstName()%></p>
                    <p>Last Name: <%=anOrder.getLastName()%></p>
                    <p>Method: <%=anOrder.getMethod()%></p>
                    <p>Address: <%=anOrder.getAddress()%></p>
                    <p>City: <%=anOrder.getCity()%></p>
                    <p>State: <%=anOrder.getState()%></p>
                    <p>Zip Code: <%=anOrder.getZip()%></p>
            </div>
            <div class="confirmTable">
                <h2>Product Information</h2>
                <table id="cartTable" border="1" width="100%" class="all-align">
                    <tr>
                        <th>id</th>
                        <th>name</th>
                        <th>thumbnail</th>
                        <th>category</th>
                        <th>price</th>
                        <th>summary</th>
                    </tr>
                    <%
                       ArrayList<Product> items = (ArrayList<Product>)request.getAttribute("OrderItems");
                            for(Product it:items){
                    %>
                    <tr>
                        <td><%=it.getId()%></td>
                        <td><%=it.getName()%></td>
                        <td><img src="./assets/<%=it.getThumbnail()%>" height="50"></td>
                        <td><%=it.getCategory()%></td>
                        <td><%=it.getPrice()%></td>
                        <td><%=it.getSummary()%></td>
                    </tr>
                    <%
                        }
                    %>
                </table>
                <p style="text-align: right;"><a href="./cancelOrder?id=<%=orderID%>" class="addtocart" onclick="return confirm('Are you sure?\nYou cannot undo this action.');">Cancel Order</a></p>
                <%}else{%>
                    <h3 class="all-align">This order does not exist. Please make sure your input correct order ID.</h3>
                <% }%>
            </div>
        </div>
    </div>
</div>
<jsp:include page="components/footer.jsp" />
