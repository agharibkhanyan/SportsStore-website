<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<td class="product">
    <a href="detail?id=${param.id}"><img src="assets/${param.thumbnail}" alt="${param.name} picture"></a>
    <h4>${param.name}</h4>
    <h2>${param.category}</h2>
    <h3>${param.price}</h3>
    <p>${param.summary}</p>
    <hr />
    <a href="detail?id=${param.id}" class="addtocart">See Details</a>
</td>