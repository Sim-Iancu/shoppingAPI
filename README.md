# Shopping API Documentation

A REST API built in Java using the Spring framework and JPA, where users can track and review orders and inventory for stores. 
It has full CRUD functionality, allowing users to create and edit products, shops, orders, and customers.

## Customers
* Customers are referenced in Orders, and one Customer can have many Orders 
* Every Customer will have a uniquely generated id
*	Headers must contain: 
```
[
  {"key":"Content-Type",
  "value":"application/json"}
]
```
*	Body must be in JSON format, and it must have a:
      * String name
      * String address
      *	String phone
* When doing a POST, it will return a body with ‘null’ values on it, but those values aren’t what actually got stored in the database. To see what got stored in the database, perform a GET on that object

### /GET
Returns all customers
```
http://localhost:8080/customers
```

### /GET
Returns customer with that id
```
http://localhost:8080/customers/{id}
```

### /POST
Creates a new customer
```
http://localhost:8080/customers
```
```
Example of body:
{
    "name": "Michael",
    "address": "208 Fake Crescent",
    "phone": "905-555-1890"
}
```

### /DELETE
Deletes customers with that id
```
http://localhost:8080/customers/{id}
```


## Shops
* Shops are also referenced in Orders, and one Shop can have many Orders 
* Every Shop will have a uniquely generated id
*	Headers must contain: 
```
[
  {"key":"Content-Type",
  "value":"application/json"}
]
```
*	Body must be in JSON format, and it must have a:
      * String name
      * String address
      *	String phone
* When doing a POST, it will return a body with ‘null’ values on it, but those values aren’t what actually got stored in the database. 
To see what got stored in the database, perform a GET on that object

### /GET
Returns all shops
```
http://localhost:8080/shops
```

### /GET
Returns shop with that id
```
http://localhost:8080/shops/{id}
```

### /POST
Creates a new shop
```
http://localhost:8080/shops
```
```
Example of body:
{
	"name":"Simona's Groceries", 
	"address":"123 Fake Street", 
	"phone":"519-807-1452"
}

```

### /DELETE
Deletes shop with that id
```
http://localhost:8080/shops/{id}
```

## Products
* Products are referenced indirectly in Orders, through Line Items (Line Items contain a Product and the quantity of that Product that was purchased). 
Every Order can have many Line Items 
* Every Product must be assigned a unique product code
*	Headers must contain: 
```
[
  {"key":"Content-Type",
  "value":"application/json"}
]
```
*	Body must be in JSON format, and it must have a:
      * Long productCode
      * String name
      * double price 
* When doing a POST, it will return a body with ‘null’ values on it, but those values aren’t what actually got stored in the database. 
To see what got stored in the database, perform a GET on that object

### /GET
Returns all products
```
http://localhost:8080/products
```

### /GET
Returns product with that id
```
http://localhost:8080/products/{id}
```

### /POST
Creates a new product
```
http://localhost:8080/products
```
```
Example of body:
{
	"productCode":51419,
	"name":"Banana",
	"price":0.45
}

```

### /DELETE
Deletes product with that id
```
http://localhost:8080/products/{id}
```


## Orders
* Orders are what keeps track of what Products a Customer bought, and at what Shop they bought it at
* Every Order will have a uniquely generated id
*	Headers must contain: 
```
[
  {"key":"Content-Type",
  "value":"application/json"}
]
```
* •	Important note: When performing a POST, you must reference the id of a Customer and a Shop that are both already created in the database 
(so you have to perform a POST on Customer and POST on Shop before being able to perform a POST on Order). 
Inside the list of Line Items you must also reference a Product that already exists, and you must include the entire Product, not just the product code
*	Body must be in JSON format, and it must have a:
      * Customer id of existing Customer
      * Shop id of existing Shop
      * String date
      * List of Line Items
          * Existing Product (must be whole object, not just product code)
          * int quanity
* When doing a POST, it will return a body with ‘null’ values on it, but those values aren’t what actually got stored in the database. 
To see what got stored in the database, perform a GET on that object

### /GET
Returns all orders
```
http://localhost:8080/orders
```

### /GET
Returns order with that id
```
http://localhost:8080/orders/{id}
```

### /POST
Creates a new order
```
http://localhost:8080/orders
```
```
Example of body:
{
	"customer": {"id":1},
	"shop":{"id":2},
	"date":"September 21, 2018",
	"lineItems": [
    {
	"product": { 
"productCode":"12305",
"name":"Peanut Butter",
"price":5.99
},
		"quantity":5
	    }
	]
}
```

### /DELETE
Deletes order with that id
```
http://localhost:8080/orders/{id}
```
