<%@page import="com.s2020iae.project4.Product"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="components/head.jsp" />
<jsp:include page="components/header.jsp" />
        <div class="main">
            <div class="content">
                <h1>Products for Sale</h1>
                <table width="100%" cellspacing="20">
                    <tbody>
                        <tr>
                        <%ArrayList<Product> std =
                            (ArrayList<Product>)request.getAttribute("data");
                            int count = 0;
                            for(Product p:std){
                            if(count % 5 == 0) {
                        %>
                        </tr><tr>
                        <%
                            }
                            count++;
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
        </div>
<jsp:include page="components/footer.jsp" />