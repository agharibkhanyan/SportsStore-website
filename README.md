# inf124-assignment4

#### Building a web application using `Jersey REST APIs` & `Java Servlets`

Ecommerce Website that sells sports products.

### Requirements:

---

1. * [x] Using JSP reimplement the product list page. This is the page that contains the list of your products.

2. * [x] Create REST services to allow for interaction with the order and product resources stored in your application database.
      * [x] Ensure that proper REST principles and conventions are followed
      * [x] perform validation for certain methods (if the resource is not found, it throws a 404)
      * [x] Ensure it's a Jersey REST service Java application
      * [x] services communicate in JSON
  
3. * [x] Replace all the database interactions in your web application with REST calls.
      * [x] a backend application that provides RESTful APIs that essentially exposes the available operations in your database
      * [x] an application that is the client of the RESTful APIs, generates the HTML pages, and handles requests from the user 
      * [x] provide proper documentation highlighting the details for each RESTful service method

### DEMO ###
<img src="storefront.mp4" width=250><br>

### Bonus Feature:

---
Bonus: After getting the confirmation of a user's purchase. The user can use the orderID to see again what it 
       is they ordered from the homepage!


### RESTful Services:

---
```
    i.    GET
    
    ii.   http://localhost:8080/SportStoreRestService/v1/api/products
    
    iii.  {product: {productId, ...} ... , product: {productId, ...}}
```

```
    i.    GET
    
    ii.   http://localhost:8080/SportStoreRestService/v1/api/products/{id}
    
    iii.  {product: {productId, ...}}
```    

```
    i.    GET
    
    ii.   http://localhost:8080/SportStoreRestService/v1/api/orders/
    
    iii.  {order: {orderId, ...} ... , order: {orderId, ...}}
```   

```
    i.    GET
    
    ii.   http://localhost:8080/SportStoreRestService/v1/api/orders/{id}
    
    iii.  {order: {orderId, ...}}
``` 

```
    i.    GET
    
    ii.   http://localhost:8080/SportStoreRestService/v1/api/items/
    
    iii.  {item: {productId, orderId} }
``` 

```
    i.    POST
    
    ii.   http://localhost:8080/SportStoreRestService/v1/api/orders/
    
    iii.  "Order Added Successfully"
``` 

```
    i.    POST
    
    ii.   http://localhost:8080/SportStoreRestService/v1/api/products/
    
    iii.  "Product Added Successfully"
``` 

```
    i.    DELETE
    
    ii.   http://localhost:8080/SportStoreRestService/v1/api/orders/{id}
    
    iii.  "Order Deleted Successfully"
``` 

```
    i.    DELETE
    
    ii.   http://localhost:8080/SportStoreRestService/v1/api/items/{id}
    
    iii.  "Item Deleted Successfully"
``` 
