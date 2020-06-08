<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>
    <div class="container">
        <div class="header">
            <div class="content">
                <div class="logo">
                    <a href="./home">
                        <h1>Sports Store</h1>
                    </a>
                </div>
            </div>
            <div class="navbar">
                <ul class="content">
                    <% if(request.getRequestURI().startsWith("/Project4/home")) { %>
                    <li><a href="./home" class="active">Home</a></li>
                    <% } else { %>
                    <li><a href="./home">Home</a></li>
                    <% } %>

                    <% if(request.getRequestURI().startsWith("/Project4/products")) { %>
                        <li><a href="./products" class="active">Products</a></li>
                    <% } else { %>
                        <li><a href="./products">Products</a></li>
                    <% } %>

                    <% if(request.getRequestURI().startsWith("/Project4/about.jsp")) { %>
                        <li><a href="./about.jsp" class="active">About Us</a></li>
                    <% } else { %>
                        <li><a href="./about.jsp">About Us</a></li>
                    <% } %>

                    <% if(request.getRequestURI().startsWith("/Project4/contact.jsp")) { %>
                        <li><a href="./contact.jsp" class="active">Contact Us</a></li>
                    <% } else { %>
                        <li><a href="./contact.jsp">Contact Us</a></li>
                    <% } %>

                    <% if(request.getRequestURI().startsWith("/Project4/cart") || request.getRequestURI().startsWith("/Project4/checkout")) { %>
                        <li><a href ="./cart" class="active">
                            <img src="./assets/cart.png" width="20"/>
                            <span class='badge badge-warning' id='lblCartCount'> 0 </span>
                        </a></li>
                    <% } else { %>
                        <li><a href ="./cart">
                            <img src="./assets/cart.png" width="20"/>
                            <span class='badge badge-warning' id='lblCartCount'> 0 </span>
                        </a></li>
                    <% } %>

                </ul>
            </div>
        </div>