# Java 8 Project

* Spring boot
* Hibernate
* 30 Streams' queries
* Unit test
* Docker
* Jenkins

### 30 queries to master Java 8 Streams:

### Dependencies :

* springframework
* data-jpa
* mysql
* devtools
* lombok
* swagger2
* restdocs
* spring-test
* junit-jupiter

### Endpoints' list :

The class EndpointsListener return a list of all endpoints.

### Api documentation :

[http://localhost:5000/v2/api-docs](http://localhost:5000/v2/api-docs)

### DB connection :

You need to set your database connection to the application.yml file, in order to add the data in the database, using
EmployeeCommandLineRunner and OrderCommandLineRunner each time the program is launched again.
The database will be created automatically if not exists, be sure to add username and password.

    username : username
    password : password

### EmloyeeStreamService explains 15 queries examples :

    Query 01 : How many male and female employees are there in the organization?
    Query 02 : Print the name of all departments in the organization?
    Query 03 : What is the average age of male and female employees?
    Query 04 : Get the details of highest paid employee in the organization?
    Query 05 : Get the names of all employees who have joined after 2015?
    Query 06 : Count the number of employees in each department?
    Query 07 : What is the average salary of each department?
    Query 08 : Get the details of youngest male employee in the product development department?
    Query 09 : Who has the most working experience in the organization?
    Query 10 : How many male and female employees are there in the sales and marketing team?
    Query 11 : What is the average salary of male and female employees?
    Query 12 : List down the names of all employees in each department?
    Query 13 : What is the average salary and total salary of the whole organization?
    Query 14 : Separate the employees who are younger or equal to 25 years from those employees who are older than 25 years.
    Query 15 : Who is the oldest employee in the organization? What is his age and which department he belongs to?

### OrdersStreamService explains 15 queries examples :

    Query 01 — Obtain a list of products belongs to category “Books” with price > 100
    Query 02 — Obtain a list of order with products belong to category “Baby”
    Query 03 — Obtain a list of product with category = “Toys” and then apply 10% discount
    Query 04 — Obtain a list of products ordered by customer of tier 2 between 01-Feb-2021 and 01-Apr-2021
    Query 05 — Get the cheapest products of “Books” category
    Query 06 — Get the 3 most recent placed order
    Query 07 — Get a list of orders which were ordered on 15-Mar-2021, log the order records to the console and then return its product list
    Query 08 — Calculate total lump sum of all orders placed in Feb 2021
    Query 09 — Calculate order average payment placed on 14-Mar-2021
    Query 10 — Obtain a collection of statistic figures (i.e. sum, average, max, min, count) for all products of category “Books”
    Query 11 — Obtain a data map with order id and order’s product count
    Query 12 — Produce a data map with order records grouped by customer
    Query 13 — Produce a data map with order record and product total sum
    Query 14 — Obtain a data map with list of product name by category
    Query 15 — Get the most expensive product by category
