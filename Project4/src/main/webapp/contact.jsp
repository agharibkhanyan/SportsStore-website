<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="components/head.jsp" />
<jsp:include page="components/header.jsp" />
        <div class="main">
            <div class="content">
                <h1>Contact Us</h1>
                <form name="contactform" method="post" action="mailto:info@sportstore.com" enctype="text/plain" class="all-align">
                    <label for="fname">First Name</label>
                    <input type="text" id="fname" name="firstname" placeholder="Your name..." required>
                    <label for="lname">Last Name</label>
                    <input type="text" id="lname" name="lastname" placeholder="Your last name..." required>
                    <label for="email">E-mail</label>
                    <input type="text" id="email" name="E-mail" placeholder="Your E-mail address..." required>
                    <label for="subject">Subject</label>
                    <textarea id="subject" name="subject" placeholder="Write something..." style="height:200px"></textarea>
                    <input type="submit" value="Submit">
                </form><br><br>
            </div>
        </div>
<jsp:include page="components/footer.jsp" />