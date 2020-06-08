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
                <% String isDeleted = (String)request.getAttribute("isDeleted");%>
                <% String orderId = (String)request.getAttribute("orderId");%>
                <% if(isDeleted == "yes") { %>
                    <h3 class="all-align">Your order "<%=orderId%>" has cancelled.</h3>
                <%}else{%>
                    <h3 class="all-align">This order "<%=orderId%>" does not exist. Please make sure your input correct order ID.</h3>
                <% }%>
            </div>
        </div>
    </div>
</div>
<jsp:include page="components/footer.jsp" />
