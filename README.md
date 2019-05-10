# ShoppingCart
A shopping cart website using Java Web tech stack(Servlet, JSP, Hibernate, tomcat, to be continued...)

# How to Use:
1. import in Eclipse.
2. create a database on mysql named "cart", run the 3 sql files in /WebContent/sql_source folder.
	(ps: most of this project using pure JDBC at this time, 
		 so no auto created table by hibernate and you need to do it manually). 
3. Run as -> Run on Server

# Introduction of website:
1. Home page is a Login page.

    There're two type of users: administrator and normal customer. 
    - Administrator: id:admin, password:admin
    - Customer: id:tom, password:tom123

2. Admin page: you can do CURD operation on Product table.

3. Customer page: you can add items to cart, then do checking out in cart's page.
    - List product page: 
        - List all products 
        - Enter the number you wanna buy and add them to cart.
    - My Cart page: 
        - The items you added to your shopping cart. 
        - Reduce the number of items you added to cart
        - Click checkout to create the order.
