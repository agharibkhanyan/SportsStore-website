<html>
<head>
    <title>Form to create a new resource</title>
</head>
<body>
<center><h2>Create a new Product</h2></center>
<form action="../SportStoreRestService/v1/api/items" method="POST">
    <label for="orderid">orderid</label>
        <input id="orderid" name="orderid" />
    <br/>
    <label for="productid">productid</label>
        <input id="productid" name="productid" />
    <br/>
    <input type="submit" value="Submit" />
</form>
<form action="../SportStoreRestService/v1/api/products" method="POST">
    <label for="name">Name</label>
        <input id="name" name="name" />
    <br/>
    <label for="summary">Summary</label>
        <input id="summary" name="summary" />
    <br/>
    <label for="thumbnail">Thumbnail</label>
        <input id="thumbnail" name="thumbnail" />
    <br/>
    <label for="category">Category</label>
        <input id="category" name="category" />
    <br/>
    <label for="detail">Detail</label>
        <input id="detail" name="detail" />
    <br/>
    <label for="price">Price</label>
        <input id="price" name="price" />
    <br/>
    <input type="submit" value="Submit" />
</form>

<!-- This form will submit a POST request and the POST method defined in TodoResource will respond to it -->
<center><h2>Create a new Order</h2></center>
<form action="../SportStoreRestService/v1/api/orders" method="POST">
<label for="firstname">firstname</label>
        <input id="firstname" name="firstname" />
    <br/>
    <label for="lastname">lastname</label>
        <input id="lastname" name="lastname" />
    <br/>
    <label for="email">email</label>
        <input id="email" name="email" />
    <br/>
    <label for="phone">phone</label>
        <input id="phone" name="phone" />
    <br/>
    <label for="address">address</label>
        <input id="address" name="address" />
    <br/>
    <label for="city">city</label>
        <input id="city" name="city" />
    <br/>
    <label for="state">state</label>
        <input id="state" name="state" />
    <br/>
    <label for="zip">zip</label>
        <input id="zip" name="zip" />
    <br/>
    <label for="billaddr">billaddr</label>
        <input id="billaddr" name="billaddr" />
    <br/>
    <label for="billcity">billcity</label>
        <input id="billcity" name="billcity" />
    <br/>
    <label for="billstate">billstate</label>
        <input id="billstate" name="billstate" />
    <br/>
    <label for="billzip">billzip</label>
        <input id="billzip" name="billzip" />
    <br/>
    <label for="method">method</label>
        <input id="method" name="method" />
    <br/>
    <label for="cardname">cardname</label>
        <input id="cardname" name="cardname" />
    <br/>
    <label for="cardnumber">cardnumber</label>
        <input id="cardnumber" name="cardnumber" />
    <br/>
    <label for="expmonth">expmonth</label>
        <input id="expmonth" name="expmonth" />
    <br/>
    <label for="expyear">expyear</label>
        <input id="expyear" name="expyear" />
    <br/>
    <label for="cvv">cvv</label>
        <input id="cvv" name="cvv" />
    <br/>
    <label for="price">price</label>
    <input id="price" name="price" />
    <br/>
    <input type="submit" value="Submit" />
</form>
</body>
</html>
