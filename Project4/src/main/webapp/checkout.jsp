<%@page import="com.s2020iae.project4.Product"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="components/head.jsp" />
<jsp:include page="components/header.jsp" />
        <div class="main">
            <div class="content cart">
                <% if(request.getAttribute("isEmpty") == "no") { %>
                    <% Object numOfItems = request.getAttribute("numOfItems");%>
                    <h1>Re-confirm <%=numOfItems%> item(s)</h1>
                    <table id="cartTable" border="1" width="100%" class="all-align">
                        <tr>
                            <th>name</th>
                            <th>thumbnail</th>
                            <th>price</th>
                            <th>summary</th>
                        </tr>
                        <%ArrayList<Product> tracks =
                            (ArrayList<Product>)request.getAttribute("cartData");
                            for(Product p:tracks){
                        %>
                        <tr>
                            <td><%=p.getName()%></td>
                            <td><img src="./assets/<%=p.getThumbnail()%>" height="30"></td>
                            <td><%=p.getPrice()%></td>
                            <td><%=p.getSummary()%></td>
                        </tr>
                        <%}%>
                    </table>
                <% } %>
                <br>
            </div>
            <div class="content">
                <div class="orderform">
                    <form name="submitform" id="submitform" method="post" action="./checkout">
                        <h1>Order Form</h1>
                        <% if(request.getAttribute("isEmpty") == "yes") { %>
                            <h3 class="all-align">Your cart is empty</h3>
                            <br /><br />
                        <% } else { %>
                        <div class="row">
                            <div class="col-75">
                                <h3>Buyer's Information</h3>
                                <br />
                                <label for="fname"> First Name</label>
                                <input type="text" id="fname" name="firstname" placeholder="John" required />
                                <label for="lname"> Last Name</label>
                                <input type="text" id="lname" name="lastname" placeholder="White" required />
                                <label for="email"> Email</label>
                                <input type="text" id="email" name="email" placeholder="john@example.com" required />
                                <label for="phone"> Phone Number</label>
                                <input type="text" id="phone" name="phone" placeholder="123-123-1234" required />
                                <h3>Shipping Address</h3>
                                <br />

                                <label for="adr"> Address</label>
                                <input type="text" id="adr" name="address" placeholder="542 W. 15th Street" required />
                                <label for="city"> City</label>
                                <input type="text" id="city" name="city" placeholder="New York" required />

                                <div class="row">
                                    <div class="col-50">
                                        <label for="state">State</label>
                                        <input type="text" id="state" name="state" placeholder="New York" required autocomplete="no"/>
                                        <div id="stateList"></div>
                                    </div>
                                    <div class="col-50">
                                        <label for="zip">Zip</label>
                                        <input type="text" id="zip" name="zip" placeholder="10001" required />
                                    </div>
                                </div>
                                <label>
                                    <input id="same-address" type="checkbox" checked="checked" name="sameaddr" />
                                    Billing address same as shipping
                                </label>
                                <label for="method">Shipping method</label>
                                <select id="method" name="method">
                                    <option>Overnight ($11.00)</option>
                                    <option selected>2-day expedited ($9.50)</option>
                                    <option>7-day ground ($6.25)</option>
                                </select>
                                <br />
                            </div>

                            <div class="col-50">
                                <h3>Payment Information</h3>
                                <br />
                                <label for="cname">Name on Card</label>
                                <input type="text" id="cname" name="cardname" placeholder="John More Doe" required />
                                <label for="ccnum">Credit card number</label>
                                <input type="text" id="ccnum" name="cardnumber" placeholder="1111-2222-3333-4444" required />
                                <label for="expmonth">Exp Month</label>
                                <select id="expmonth" name="expmonth" placeholder="September" required>
                                    <option selected>1</option>
                                    <option>2</option>
                                    <option>3</option>
                                    <option>4</option>
                                    <option>5</option>
                                    <option>6</option>
                                    <option>7</option>
                                    <option>8</option>
                                    <option>9</option>
                                    <option>10</option>
                                    <option>11</option>
                                    <option>12</option>
                                </select>
                                <div class="row">
                                    <div class="col-50">
                                        <label for="expyear">Exp Year</label>
                                        <input type="number" id="expyear" name="expyear" placeholder="2020" required />
                                    </div>
                                    <div class="col-50">
                                        <label for="cvv">CVV</label>
                                        <input type="number" id="cvv" name="cvv" placeholder="352" required />
                                    </div>
                                    <br /><br />
                                </div>
                                <div id="billing-address">
                                    <h3>Billing Address</h3>
                                    <br />
                                    <label for="billaddr">Address</label>
                                    <input type="text" id="billaddr" name="billaddr" placeholder="542 W. 15th Street"
                                        />
                                    <label for="billcity">City</label>
                                    <input type="text" id="billcity" name="billcity" placeholder="New York"
                                        />
                                    <div class="row">
                                        <div class="col-50">
                                            <label for="billstate">State</label>
                                            <input type="text" id="billstate" name="billstate" placeholder="New York"
                                                />
                                            <div id="stateList"></div>
                                        </div>
                                        <div class="col-50">
                                            <label for="billzip">Zip</label>
                                            <input type="text" id="billzip" name="billzip" placeholder="10001" />
                                        </div>
                                    </div>
                                </div>
                                <div id="price-table">
                                    <div>Total Price:</div>
                                    <% Object total = request.getAttribute("subTotal");%>
                                    <div class="price-item"><span id="total-price">$<%=total%></span></div>

                                    <div>Total Tax: </div>
                                    <div class="price-item">+ $<span id="tax-amount" ></span></div>
                                    <input type ="hidden" id="stateTax" name="tax" value ="" />
                                    <div>
                                        <div>Shipping: </div>
                                        <div class="price-item">+ $<span id="shipping"></span></div>
                                    </div>

                                    <div>
                                        <h4>Final Price</h4>
                                        <div class="price-item">= $<span id="final-price"></span></div>
                                        <input type="hidden" id="totalPrice" name="price" value="<%=total%>" />
                                    </div>
                                </div>
                            </div>
                        </div>
                        <button type="submit" id="order-submit" class="js-submit-order btn" tabindex="0" id="formSubmit" name="purchase">
                            Submit Order
                        </button>
                        <% } %>
                    </form>
                </div>
            </div>
        </div>
    </div>
<jsp:include page="components/footer.jsp" />