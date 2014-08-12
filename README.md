#Libekorma#

This is a sample project whose only purpose is to show how one combines Liberator and Korma projects to build a RESTful webservice in Clojure around a relational database.

There is a post covering this topic at http://blog.alex-turok.com.

###We have###
1. Simple Korma entities representing DB objects in the app
2. Demo data insertion through Korma, allowing to have some data as well as see very basic Korma stuff
3. POST/GET/PUT/DELETE API on a couple resources that performs inserts, deletes, updates and selects (including those with joins)
4. Very basic Ring+Compojure harness around the resource
5. Simple, possibly unidiomatic, use of exists?, conflict? and malformed? handlers

###We use###
1. Postgres as a database engine - this does not affect the code much, but the schema definition and connection definition do depend on the underlying DBMS.
2. I completely ignore each and every advanced aspect of web applications, including authentication and everything else.
