Liberator+Korma blog post

Goal: give a tutorial demonstrating the way one can use Liberator to build a RESTful webservice backed by a relational database.

Cover:
1. Example scheme
2. Korma entities representing DB objects in the app
4. DB connection configuration
5. Demo data insertion through Korma, allowing to have demo data as well as see very basic Korma stuff
6. POST/GET/PUT/DELETE API on a resource
7. Basic Ring+Compojure harness around the resource
8. The use of exists?, conflict? and malformed? handlers
9. Additional simple resource with GET only and minor filtering

Assumptions:
1. Postgres - esp. in relation to timestamps
2. No authentication