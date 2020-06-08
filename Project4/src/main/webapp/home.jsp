<%@page import="com.s2020iae.project4.Product"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="components/head.jsp" />
<jsp:include page="components/header.jsp" />
        <div class="main">
            <div class="content">
                <h1>New Products</h1>
                <table width="100%" height="100%" cellspacing="20">
                    <tbody>
                        <tr>
                        <%ArrayList<Product> std =
                            (ArrayList<Product>)request.getAttribute("data");
                            for(Product p:std){%>
                            <jsp:include page="components/product.jsp" flush="true">
                                <jsp:param name="id" value="<%=p.getId()%>" />
                                <jsp:param name="name" value="<%=p.getName()%>" />
                                <jsp:param name="thumbnail" value="<%=p.getThumbnail()%>" />
                                <jsp:param name="category" value="<%=p.getCategory()%>" />
                                <jsp:param name="price" value="<%=p.getPrice()%>" />
                                <jsp:param name="summary" value="<%=p.getSummary()%>" />
                            </jsp:include>
                        <%}%>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="hotdeal">
                <div class="detail">
                    <h1>Back to school deal</h1>
                    <div class="countdown">00 DAYS, 00 HOURS, 00 MINS, 00 SECS</div>
                    <h2>UP TO 95% OFF</h2>
                    <a href="./products" class="getdeal">Shop Now</a>
                </div>
                <script type="text/javascript">
                    var countDownDate = new Date("Jun 14, 2020 23:59:59").getTime();
                    var x = setInterval(function() {
                        var now = new Date().getTime();
                        var distance = countDownDate - now;
                        var days = Math.floor(distance / (1000 * 60 * 60 * 24));
                        var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
                        var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
                        var seconds = Math.floor((distance % (1000 * 60)) / 1000);
                        document.getElementsByClassName("countdown")[0].innerHTML = ("0" + days).slice(-2) + " DAYS, " + ("0" + hours).slice(-2) + " HOURS, " + ("0" + minutes).slice(-2) + " MINS, " + ("0" + seconds).slice(-2) + " SECS ";
                        if (distance < 0) {
                            clearInterval(x);
                            document.getElementsByClassName("countdown")[0].innerHTML = "EXPIRED";
                        }
                    }, 1000);
                </script>
            </div>
            <div class="content">
                <h1><%=request.getAttribute("historyTitle")%></h1>
                <table width="100%" cellspacing="20">
                    <tbody>
                        <tr>
                            <%ArrayList<Product> tracks =
                                (ArrayList<Product>)request.getAttribute("trackData");
                                for(Product p:tracks){
                            %>
                                <jsp:include page="components/product.jsp" flush="true">
                                    <jsp:param name="id" value="<%=p.getId()%>" />
                                    <jsp:param name="name" value="<%=p.getName()%>" />
                                    <jsp:param name="thumbnail" value="<%=p.getThumbnail()%>" />
                                    <jsp:param name="category" value="<%=p.getCategory()%>" />
                                    <jsp:param name="price" value="<%=p.getPrice()%>" />
                                    <jsp:param name="summary" value="<%=p.getSummary()%>" />
                                </jsp:include>
                            <%}%>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="content">
                <div class="subscribe">
                    <h3>Track your <b>ORDER</b></h3>
                    <form action="./confirmation" method="GET">
                        <input type="number" name="id" placeholder="Enter Your Order ID" required/>
                        <button type="submit">Track</button>
                    </form>
                </div>
            </div>
        </div>
<jsp:include page="components/footer.jsp" />