
# SpringBoot example

This is a very simple project using:
* Maven 
* Spring boot
* APIs 
* JPA (with h2 as DB)

**Functional description:**

*APIs*

**POST:** `<url>/api/create`

Body: 

    {
    "name" : <stringValue>
    }

Returns: 200 OK and the created object

*Example:*

`http://localhost:8080/api/create`

Body: 

    {
    "name" : "test"
    }

returns 200:

    {
    "id": 1,
    "name" : "test"
    }

**GET:** `<url>/api/values`
*Optional params:*
* name: filter by name
Returns: 200 OK and the object list

Example:

    http://localhost:8080/api/values

returns 200:

    [{
    "id": 1,
    "name" : "test1"
    },
    {
    "id": 2,
    "name" : "test2"
    }]
